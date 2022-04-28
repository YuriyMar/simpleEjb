
//---------------------------------------------------------
// Application: EnergyNet
// Author     : Kherson
//
// Copyright © 2009 SIT
// Generated at Sat Sep 26 14:38:30 EEST 2009
// Created with ModelJ - the RAD tool for J2EE
// http://modelj.sourceforge.net
//---------------------------------------------------------

package com.ksoe.energynet.ejb;


  /**
  * EJB Controller for ENPlanWork;
  *
  */



import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.callcenter.ejb.SMSInformController;
import com.ksoe.callcenter.ejb.SMSInformControllerHome;
import com.ksoe.callcenter.valueobject.SMSInform;
import com.ksoe.callcenter.valueobject.SMSInformOperator;
import com.ksoe.docflow.dataminer.DFDocServicesConsumerDAO;
import com.ksoe.docflow.logic.DocFlowLogic;
import com.ksoe.docflow.valueobject.DFDocServicesConsumer;
import com.ksoe.energynet.dataminer.BuffetDAO;
import com.ksoe.energynet.dataminer.ENAct2ENPlanWorkDAO;
import com.ksoe.energynet.dataminer.ENActDAO;
import com.ksoe.energynet.dataminer.ENDepartmentDAO;
import com.ksoe.energynet.dataminer.ENElementDAO;
import com.ksoe.energynet.dataminer.ENEstimateItem2ENEstimateItemDAO;
import com.ksoe.energynet.dataminer.ENEstimateItemDAO;
import com.ksoe.energynet.dataminer.ENGeneralContractsDAO;
import com.ksoe.energynet.dataminer.ENMarkEstimateDAO;
import com.ksoe.energynet.dataminer.ENNormVolumeAVZItemDAO;
import com.ksoe.energynet.dataminer.ENPlanCorrectHistoryDAO;
import com.ksoe.energynet.dataminer.ENPlanInformCustomerDAO;
import com.ksoe.energynet.dataminer.ENPlanWork2CCDamageLogDAO;
import com.ksoe.energynet.dataminer.ENPlanWorkDAO;
import com.ksoe.energynet.dataminer.ENPlanWorkItem2TKKoefDAO;
import com.ksoe.energynet.dataminer.ENPlanWorkItemDAO;
import com.ksoe.energynet.dataminer.ENPlanwork2GeneralContractsDAO;
import com.ksoe.energynet.dataminer.ENServFromSide2PlanWorkDAO;
import com.ksoe.energynet.dataminer.ENServices2PlanDAO;
import com.ksoe.energynet.dataminer.ENServicesFromSideObjectDAO;
import com.ksoe.energynet.dataminer.ENServicesObjectDAO;
import com.ksoe.energynet.dataminer.ENSizObjectDAO;
import com.ksoe.energynet.dataminer.ENSzBrigadeDAO;
import com.ksoe.energynet.dataminer.ENTechAgreement2ServicesObjectDAO;
import com.ksoe.energynet.dataminer.ENTechCond2PlanWorkDAO;
import com.ksoe.energynet.dataminer.ENTechConditionsServicesDAO;
import com.ksoe.energynet.dataminer.ENWorkOrderDAO;
import com.ksoe.energynet.dataminer.FINDoc2MolDataDAO;
import com.ksoe.energynet.dataminer.FINExecutor2PlanDAO;
import com.ksoe.energynet.dataminer.FINExecutorDAO;
import com.ksoe.energynet.dataminer.FINMaterialsDAO;
import com.ksoe.energynet.dataminer.FINMolDataDAO;
import com.ksoe.energynet.ejb.generated.ENPlanWorkControllerEJBGen;
import com.ksoe.energynet.logic.AuthLogic;
import com.ksoe.energynet.logic.ContractLogic;
import com.ksoe.energynet.logic.ENConsts;
import com.ksoe.energynet.logic.ENSettingsKeysConsts;
import com.ksoe.energynet.logic.ENSettingsLogic;
import com.ksoe.energynet.logic.ElementLogic;
import com.ksoe.energynet.logic.EstimateLogic;
import com.ksoe.energynet.logic.FINExecutorLogic;
import com.ksoe.energynet.logic.FINLogic;
import com.ksoe.energynet.logic.HumenLogic;
import com.ksoe.energynet.logic.PlanWorkItemLogic;
import com.ksoe.energynet.logic.PlanWorkLogic;
import com.ksoe.energynet.logic.ServicesLogic;
import com.ksoe.energynet.logic.SiZLogic;
import com.ksoe.energynet.logic.TechConditionsLogic;
import com.ksoe.energynet.util.CCIdentifierEjbCache;
import com.ksoe.energynet.util.DBConnector;
import com.ksoe.energynet.util.tools.CollectionTools;
import com.ksoe.energynet.valueobject.ENAct;
import com.ksoe.energynet.valueobject.ENAct2ENPlanWork;
import com.ksoe.energynet.valueobject.ENActStatus;
import com.ksoe.energynet.valueobject.ENDepartment;
import com.ksoe.energynet.valueobject.ENElement;
import com.ksoe.energynet.valueobject.ENElementType;
import com.ksoe.energynet.valueobject.ENEstimateItem;
import com.ksoe.energynet.valueobject.ENEstimateItem2ENEstimateItem;
import com.ksoe.energynet.valueobject.ENEstimateItem2Type;
import com.ksoe.energynet.valueobject.ENEstimateItemKind;
import com.ksoe.energynet.valueobject.ENEstimateItemStatus;
import com.ksoe.energynet.valueobject.ENEstimateItemType;
import com.ksoe.energynet.valueobject.ENGeneralContracts;
import com.ksoe.energynet.valueobject.ENMOL2PlanWork;
import com.ksoe.energynet.valueobject.ENManagement;
import com.ksoe.energynet.valueobject.ENMarkEstimate;
import com.ksoe.energynet.valueobject.ENPlan2CCDamageLogType;
import com.ksoe.energynet.valueobject.ENPlanCorrectHistory;
import com.ksoe.energynet.valueobject.ENPlanCorrectReason;
import com.ksoe.energynet.valueobject.ENPlanInformCustomer;
import com.ksoe.energynet.valueobject.ENPlanWork;
import com.ksoe.energynet.valueobject.ENPlanWork2CCDamageLog;
import com.ksoe.energynet.valueobject.ENPlanWorkForm;
import com.ksoe.energynet.valueobject.ENPlanWorkItem;
import com.ksoe.energynet.valueobject.ENPlanWorkKind;
import com.ksoe.energynet.valueobject.ENPlanWorkSource;
import com.ksoe.energynet.valueobject.ENPlanWorkState;
import com.ksoe.energynet.valueobject.ENPlanWorkStatus;
import com.ksoe.energynet.valueobject.ENPlanWorkType;
import com.ksoe.energynet.valueobject.ENPlanwork2GeneralContracts;
import com.ksoe.energynet.valueobject.ENServFromSide2PlanWork;
import com.ksoe.energynet.valueobject.ENServices2Plan;
import com.ksoe.energynet.valueobject.ENServicesContractKind;
import com.ksoe.energynet.valueobject.ENServicesContractType;
import com.ksoe.energynet.valueobject.ENServicesFromSideObject;
import com.ksoe.energynet.valueobject.ENServicesObject;
import com.ksoe.energynet.valueobject.ENServicesObjectStatus;
import com.ksoe.energynet.valueobject.ENSzBrigade;
import com.ksoe.energynet.valueobject.ENTechAgreement2ServicesObject;
import com.ksoe.energynet.valueobject.ENTechCond2PlanWork;
import com.ksoe.energynet.valueobject.ENTechConditionsServices;
import com.ksoe.energynet.valueobject.ENWorkOrder;
import com.ksoe.energynet.valueobject.ENWorkOrder2ENPlanWork;
import com.ksoe.energynet.valueobject.FINExecutor;
import com.ksoe.energynet.valueobject.FINExecutor2Plan;
import com.ksoe.energynet.valueobject.FINExecutorType;
import com.ksoe.energynet.valueobject.FINMaterials;
import com.ksoe.energynet.valueobject.FINMaterialsStatus;
import com.ksoe.energynet.valueobject.FINMolData;
import com.ksoe.energynet.valueobject.FINMolType;
import com.ksoe.energynet.valueobject.brief.BufetOrderShort;
import com.ksoe.energynet.valueobject.brief.ENPlanWorkItemShort;
import com.ksoe.energynet.valueobject.brief.FINMaterialsShort;
import com.ksoe.energynet.valueobject.filter.ENAct2ENPlanWorkFilter;
import com.ksoe.energynet.valueobject.filter.ENActFilter;
import com.ksoe.energynet.valueobject.filter.ENEstimateItemFilter;
import com.ksoe.energynet.valueobject.filter.ENMOL2PlanWorkFilter;
import com.ksoe.energynet.valueobject.filter.ENMarkEstimateFilter;
import com.ksoe.energynet.valueobject.filter.ENNormVolumeAVZItemFilter;
import com.ksoe.energynet.valueobject.filter.ENPlanCorrectHistoryFilter;
import com.ksoe.energynet.valueobject.filter.ENPlanInformCustomerFilter;
import com.ksoe.energynet.valueobject.filter.ENPlanWork2CCDamageLogFilter;
import com.ksoe.energynet.valueobject.filter.ENPlanWorkFilter;
import com.ksoe.energynet.valueobject.filter.ENPlanWorkItemFilter;
import com.ksoe.energynet.valueobject.filter.ENServices2PlanFilter;
import com.ksoe.energynet.valueobject.filter.ENPlanwork2GeneralContractsFilter;
import com.ksoe.energynet.valueobject.filter.ENServicesFromSideObjectFilter;
import com.ksoe.energynet.valueobject.filter.ENServicesObjectFilter;
import com.ksoe.energynet.valueobject.filter.ENSzBrigadeFilter;
import com.ksoe.energynet.valueobject.filter.ENTechAgreement2ServicesObjectFilter;
import com.ksoe.energynet.valueobject.filter.ENTechCond2PlanWorkFilter;
import com.ksoe.energynet.valueobject.filter.ENWorkOrderBytItemFilter;
import com.ksoe.energynet.valueobject.filter.ENWorkOrderFilter;
import com.ksoe.energynet.valueobject.filter.FINDoc2MolDataFilter;
import com.ksoe.energynet.valueobject.filter.FINExecutor2PlanFilter;
import com.ksoe.energynet.valueobject.filter.FINMaterialsFilter;
import com.ksoe.energynet.valueobject.filter.FINMolDataFilter;
import com.ksoe.energynet.valueobject.lists.BufetOrderShortList;
import com.ksoe.energynet.valueobject.lists.ENEstimateItemShortList;
import com.ksoe.energynet.valueobject.lists.ENMOL2PlanWorkShortList;
import com.ksoe.energynet.valueobject.lists.ENMaterialsShortList;
import com.ksoe.energynet.valueobject.lists.ENNormVolumeAVZItemShortList;
import com.ksoe.energynet.valueobject.lists.ENPlanCorrectHistoryShortList;
import com.ksoe.energynet.valueobject.lists.ENPlanInformCustomerShortList;
import com.ksoe.energynet.valueobject.lists.ENPlanWork2CCDamageLogShortList;
import com.ksoe.energynet.valueobject.lists.ENPlanWorkBillingShortList;
import com.ksoe.energynet.valueobject.lists.ENPlanWorkItemForClosePlanShortList;
import com.ksoe.energynet.valueobject.lists.ENPlanWorkItemShortList;
import com.ksoe.energynet.valueobject.lists.ENPlanWorkShortList;
import com.ksoe.energynet.valueobject.lists.ENServices2PlanShortList;
import com.ksoe.energynet.valueobject.lists.ENServicesFromSideObjectShortList;
import com.ksoe.energynet.valueobject.lists.ENServicesObjectShortList;
import com.ksoe.energynet.valueobject.lists.ENWorkOrderShortList;
import com.ksoe.energynet.valueobject.lists.FINDoc2MolDataShortList;
import com.ksoe.energynet.valueobject.lists.FINExecutor2PlanShortList;
import com.ksoe.energynet.valueobject.lists.FINMaterialsList;
import com.ksoe.energynet.valueobject.lists.FINMaterialsShortList;
import com.ksoe.energynet.valueobject.lists.FINMolDataShortList;
import com.ksoe.energynet.valueobject.references.FINMolTypeRef;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.energypro.util.Tools;
import com.ksoe.fin.logic.FKOSLogic;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;
import com.ksoe.lla.security.UserProfile;
import com.ksoe.netobjects.dataminer.ENCallCenterObjectDAO;
import com.ksoe.netobjects.valueobject.filter.ENCallCenterObjectFilter;
import com.ksoe.rqorder.dataminer.RQOrderItem2ENEstimateItemDAO;
import com.ksoe.rqorder.dataminer.RQOrderItemDAO;
import com.ksoe.rqorder.dataminer.RQOrgDAO;
import com.ksoe.rqorder.logic.FKOrderLogic;
import com.ksoe.rqorder.logic.PlanPurchaseLogic;
import com.ksoe.rqorder.logic.RQConsts;
import com.ksoe.rqorder.valueobject.RQOrderItem;
import com.ksoe.rqorder.valueobject.RQOrg;
import com.ksoe.rqorder.valueobject.filter.RQOrderItem2ENEstimateItemFilter;
import com.ksoe.rqorder.valueobject.filter.RQOrderItemFilter;
import com.ksoe.rqorder.valueobject.filter.RQOrgFilter;
import com.ksoe.rqorder.valueobject.lists.RQOrgShortList;
import com.ksoe.techcard.dataminer.TEMPNomenclaturesDAO;
import com.ksoe.techcard.dataminer.TKMaterialsDAO;
import com.ksoe.techcard.dataminer.TKTechCardDAO;
import com.ksoe.techcard.dataminer.TKTransportRealDAO;
import com.ksoe.techcard.logic.TKConsts;
import com.ksoe.techcard.valueobject.TKAccountingType;
import com.ksoe.techcard.valueobject.filter.TEMPNomenclaturesFilter;
import com.ksoe.techcard.valueobject.filter.TKMaterialsFilter;
import com.ksoe.techcard.valueobject.filter.TKTransportRealFilter;
import com.ksoe.techcard.valueobject.lists.TEMPNomenclaturesShortList;
import com.ksoe.techcard.valueobject.lists.TKMaterialsShortList;
import com.ksoe.techcard.valueobject.lists.TKTransportRealShortList;

public class ENPlanWorkControllerEJB extends ENPlanWorkControllerEJBGen
 {

  /**
	 *
	 */
	private static final long serialVersionUID = 1L;

public ENPlanWorkControllerEJB() {}


  public ENMaterialsShortList getMaterialsFromPlans(ENPlanWorkFilter f, int tkMaterialCode)
  {
    try
    {
        ENPlanWorkDAO objDAO = new ENPlanWorkDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
        ENMaterialsShortList list = objDAO.getMaterialsList(f, tkMaterialCode);
        return list;

    }
        catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't connect do DataSource",e);}
        catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
        finally                              {closeConnection();}
  }

  public ENMaterialsShortList getMaterialsFromPlans(ENPlanWorkFilter f, String materialCondition, int tkMaterialCode)
  {
    try
    {
        ENPlanWorkDAO objDAO = new ENPlanWorkDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
        ENMaterialsShortList list = objDAO.getMaterialsList(f, materialCondition, tkMaterialCode);
        return list;

    }
        catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't connect do DataSource",e);}
        catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
        finally                              {closeConnection();}
  }

  public ENMaterialsShortList getServicesFromPlans(ENPlanWorkFilter f, int tkMaterialCode)
  {
    try
    {
        ENPlanWorkDAO objDAO = new ENPlanWorkDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
        ENMaterialsShortList list = objDAO.getServicesList(f, tkMaterialCode);
        return list;

    }
        catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't connect do DataSource",e);}
        catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
        finally                              {closeConnection();}
  }


  public int[] getFilteredCodeArray(ENPlanWorkFilter f)
  {
    try
    {
        ENPlanWorkDAO objDAO = new ENPlanWorkDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
        return  objDAO.getFilteredCodeArray(f, 0, -1);

    }
        catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't connect do DataSource",e);}
        catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
        finally                              {closeConnection();}
  }


  public ENPlanWorkShortList getScrollableFilteredListForMetrologyCounters(ENPlanWorkFilter aFilterObject, int fromPosition, int quantity)
  {
	    try
	    {
	        ENPlanWorkDAO objectDAO = new ENPlanWorkDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
	        return objectDAO.getScrollableFilteredListForMetrologyCounters(aFilterObject, fromPosition, quantity);
	    }
        catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't connect to DataSource",e);}
        catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
        finally                              {closeConnection();}
  }

  public ENPlanWorkShortList getScrollableFilteredListForEnergosbyt(ENPlanWorkFilter aFilterObject, int fromPosition, int quantity)
  {
	  try
	  {
	      ENPlanWorkDAO objectDAO = new ENPlanWorkDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
	      return objectDAO.getScrollableFilteredListForEnergosbyt(aFilterObject, fromPosition, quantity);
	  }
      catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't connect to DataSource",e);}
      catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
      finally                              {closeConnection();}
  }

  // метод для проверки редактирования полуУтвержденных ... типа для редакторов из ХОЕ ;)
 public void editPreConfirm(int planCode)
 {
	 try
	 {
		 ///// NET-4415 Для автоматически созданных планов на изготовление проверим права
 	     PlanWorkLogic planLogic = new PlanWorkLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
 	    AuthLogic authLogic = new AuthLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

 	     if (planLogic.isAutoPlanForOwnProduction(planCode))
 	     {

		     if (! authLogic.checkPermission("ksoe/energynet/ENPlanWorkController", "editPreConfirmForCPP"))
		     {
		         throw new EnergyproSystemException("У Вас немає прав на цю операцію!");
		     }
 	     }
	     /////

 	     ENPlanWork plan = planLogic.getPlanByCode(planCode);

 	     if(plan.status.code == ENPlanWorkStatus.PRECONFIRMED) {
		     if (! authLogic.checkPermission("ksoe/energynet/ENPlanWorkController", "editPreConfirm"))
		     {
		         throw new EnergyproSystemException("У Вас немає прав на цю операцію!");
		     }
 	     }
	 }
     catch (DatasourceConnectException e)
     {
    	 throw new EnergyproSystemException(e.getMessage(), e);
     }
	 catch (PersistenceException e)
     {
    	 throw new EnergyproSystemException(e.getMessage(), e);
     }
     finally
     {
    	 closeConnection();
     }
 }

  // типа РЭС отправляет ПОТОЧНЫЙ на утверждение(корректировку в Обл)
  public void preConfirm(int planCode)
  {
    try
    {

        ENPlanWorkDAO objDAO = new ENPlanWorkDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

        ENPlanWork obj = objDAO.getObject(planCode);

        //PlanWorkLogic pLogic = new PlanWorkLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

        if (
                ( obj.kind.code != ENPlanWorkKind.CURRENT ) // типа ТЕКУЩИЙ
                &&  ( obj.status.code != ENPlanWorkStatus.GOOD )// и черновой
            ){
            throw new EnergyproSystemException("На утверждение отправляеться только ТЕКУЩИЙ ЧЕРНОВОЙ план, code="+planCode);
        }

        // запишем в историю коректировок ...
        ENPlanCorrectHistoryDAO chDAO = new ENPlanCorrectHistoryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

        ENPlanCorrectHistory ch = new ENPlanCorrectHistory();

        ENPlanCorrectHistoryFilter f = new ENPlanCorrectHistoryFilter();
        f.planNewRef.code = obj.code;
        ENPlanCorrectHistoryShortList l = chDAO.getScrollableFilteredList(f,0,1);
        if (l.totalCount > 0 ){
            ch.planRef.code = l.get(0).planRefCode;
        }
        else{
            ch.planRef.code = obj.code;
        }


        ch.dateEdit = new Date();
        ch.dateGen = new Date();
        //ch.planRef.code = obj.code;
        ch.planNewRef.code = obj.code;
        ch.planOldRef.code = obj.code;
        ch.userGen = getUserProfile().userName;
        ch.reason.code = ENPlanCorrectReason.MOVE_TO_preCONFIRM;
        chDAO.add(ch);

        obj.status.code = ENPlanWorkStatus.PRECONFIRMED;

        objDAO.save(obj);


    // рэсы типа отсылают на утверждение !!!
/*
    ENPlanWorkDAO objDAO = new ENPlanWorkDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

    ENPlanWork obj = objDAO.getObject(planCode);

    PlanWorkLogic pLogic = new PlanWorkLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

    if (( obj.kind.code != ENPlanWorkKind.NPW ) || pLogic.isNotEditablePlan(obj)){
        throw new EnergyproSystemException("PlanWork not Editable or not NPZ , code="+planCode);
    }

    obj.status.code = ENPlanWorkStatus.PRECONFIRMED;

    objDAO.save(obj);
*/
    }
        catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't preConfirm plan. Code=" + planCode,e);}
        catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
        finally                              {closeConnection();}
  }


  public void confirm(int planCode){
    try
    {
        // типа ХОЕ утверждает ..  !!!
        ENPlanWorkDAO objDAO = new ENPlanWorkDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

        ENPlanWork obj = objDAO.getObject(planCode);


        if (
                ( obj.kind.code != ENPlanWorkKind.CURRENT ) // типа ТЕКУЩИЙ
                &&  ( obj.status.code != ENPlanWorkStatus.PRECONFIRMED )// и черновой
            ){
            throw new EnergyproSystemException("Утверждение производиться только ТЕКУЩИЙ На Утверждении план, code="+planCode);
        }

        /**
         *  SUPP-17251... 26.05.2014 +++ ограничение правами для утверждения месячных планов,
         *  в которых присутствуют работы из типовых проектов... ВРТУ....
         */
        PlanWorkLogic planLogic = new PlanWorkLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
        planLogic.checkModelProjectWorks(planCode);


        // запишем в историю коректировок ...
        ENPlanCorrectHistoryDAO chDAO = new ENPlanCorrectHistoryDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

        ENPlanCorrectHistory ch = new ENPlanCorrectHistory();

        ENPlanCorrectHistoryFilter f = new ENPlanCorrectHistoryFilter();
        f.planNewRef.code = obj.code;
        ENPlanCorrectHistoryShortList l = chDAO.getScrollableFilteredList(f,0,1);
        if (l.totalCount > 0 ){
            ch.planRef.code = l.get(0).planRefCode;
        }
        else{
            ch.planRef.code = obj.code;
        }


        ch.dateEdit = new Date();
        ch.dateGen = new Date();
        //ch.planRef.code = obj.code;
        ch.planNewRef.code = obj.code;
        ch.planOldRef.code = obj.code;
        ch.userGen = getUserProfile().userName;
        ch.reason.code = ENPlanCorrectReason.MOVE_TO_CONFIRM;
        chDAO.add(ch);

        obj.status.code = ENPlanWorkStatus.LOCKED;

        objDAO.save(obj);

    }
        catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't preConfirm plan. Code=" + planCode,e);}
        catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
        finally                              {closeConnection();}

  }


	public void undoConfirm(int planCode) {
		try {
		    // типа ХОЕ !!! НЕ !!! ... утверждает ..  !!!
		    // и перебрасывает на предыдущий уровень ... с утвержденного в полуУтвержденны ..
		        // с полуУтвержденного в черновой ...
		    ENPlanWorkDAO objDAO = new ENPlanWorkDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

		    ENPlanWork obj = objDAO.getObject(planCode);
		    //PlanWorkLogic pLogic = new PlanWorkLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

		    if (
		            (( obj.kind.code != ENPlanWorkKind.CURRENT )
		            &&
		            (
		                ( obj.status.code != ENPlanWorkStatus.PRECONFIRMED )
		                || (obj.status.code != ENPlanWorkStatus.LOCKED)
		            ))

		            // 19.07 2011   временно разрешили рихтоваиать годовые 2012 года
		            && (obj.kind.code != ENPlanWorkKind.YEAR /*&& obj.yearGen != 2012*/)
		        )
		    {
		        throw new EnergyproSystemException("Отменять Утверждение можно только у ТЕКУЩИХ На Утверждении или Утвержденных планах, code="+planCode);
		    }

		    // 26.06.17 SUPP-63287 Пока откроем
		    /*
		    //SUPP-10190
		    if (obj.yearGen != 2014 && obj.yearGen != 2015 && obj.yearGen != 2016 && obj.yearGen != 2017) // 2016 - нельзя!!! (16.10.15 SUPP-40280)
		    	// уже можно и 2016 год :)
		    {
		    	if (! (getUserProfile().userName.toUpperCase().equals("DyadyovOE".toUpperCase()) ||
		    		   getUserProfile().userName.toUpperCase().equals("energynet".toUpperCase())) )
		    		throw new SystemException("Дозволяється редагувати тільки плани 2014, 2015 років!");
		    }
		    */

		    ///// NET-4401 Не даем перевести автоматически созданный план на пополнение АВЗ в статус "На затвердженні"
		    ElementLogic elementLogic = new ElementLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

		    int elementType = elementLogic.getElementTypeByCode(obj.elementRef.code);

		    if (elementType == ENElementType.SERVICES_OBJECT) {
		    	// 06.10.2020 Для типов договоров на выполнение работ эта проверка не должна работать (просьба ОКС)
		    	ServicesLogic servicesLogic = new ServicesLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
		    	ENServicesObject servicesObject = servicesLogic.getServicesObjectByElementCode(obj.elementRef.code);
		    	if(servicesObject == null || servicesObject.contractTypeRef.code != ENServicesContractType.SHIFT_LINES)
		    	throw new SystemException("\n\n"
		    			+ "Для послуг ця операція заборонена!");
		    }

		    if (elementType == ENElementType.NO_OBJECT_AVZ)
		    {
		    	throw new SystemException("\n\nNET-4401 Для автоматично складених планів на поповнення АВЗ ця операція заборонена!");
		    }
		    /////

		    ///// NET-4415 Для автоматически созданных планов на изготовление
		    PlanWorkLogic planLogic = new PlanWorkLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

		    if (planLogic.isAutoPlanForOwnProduction(planCode))
			{
				// Не дадим перевести из статуса "На затвердженні" в "Чорновий"
				if (obj.status.code == ENPlanWorkStatus.PRECONFIRMED)
				{
					throw new SystemException("\n\nNET-4415 Для автоматично складених планів на виготовлення ця операція заборонена!");
				}

				// При переводе в статус "На затвердженні" проверим права
				AuthLogic authLogic = new AuthLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
		        if (! authLogic.checkPermission("ksoe/energynet/ENPlanWorkController", "editPreConfirmForCPP"))
		        {
		            throw new EnergyproSystemException("У Вас немає прав на цю операцію!");
		        }
			}
		    /////

			int newStatus = Integer.MIN_VALUE;
			int reason = Integer.MIN_VALUE;
			if (obj.status.code == ENPlanWorkStatus.LOCKED) {
				newStatus = ENPlanWorkStatus.PRECONFIRMED;
				reason = ENPlanCorrectReason.MOVE_TO_CONFIRM;
			}

			if (obj.status.code == ENPlanWorkStatus.PRECONFIRMED) {
				newStatus = ENPlanWorkStatus.GOOD;
				reason = ENPlanCorrectReason.UNDO_MOVE_TO_preCONFIRM;
			}

			if ((newStatus == Integer.MIN_VALUE) || (reason == Integer.MIN_VALUE))
				throw new EnergyproSystemException(
						"Ошибка при определении текущего статуса плана ... code=" + obj.code);

			// запишем в историю коректировок ...
			ENPlanCorrectHistoryDAO chDAO = new ENPlanCorrectHistoryDAO(getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			ENPlanCorrectHistory ch = new ENPlanCorrectHistory();

			ENPlanCorrectHistoryFilter f = new ENPlanCorrectHistoryFilter();
			f.planNewRef.code = obj.code;
			ENPlanCorrectHistoryShortList l = chDAO.getScrollableFilteredList(f, 0, 1);
			if (l.totalCount > 0) {
				ch.planRef.code = l.get(0).planRefCode;
			} else {
				ch.planRef.code = obj.code;
			}


			ch.dateEdit = new Date();
			ch.dateGen = new Date();
			// ch.planRef.code = obj.code;
			ch.planNewRef.code = obj.code;
			ch.planOldRef.code = obj.code;
			ch.userGen = getUserProfile().userName;
			ch.reason.code = reason;
			chDAO.add(ch);

			obj.status.code = newStatus;

			objDAO.save(obj);

		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException("Can't preConfirm plan. Code=" + planCode, e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}





  public String getPlanStatusFromCN(int planCode)
  {
    //if (1 == 1) throw new EnergyproSystemException("Шо надо??");

    // Connection fConn = null;
        try
        {
            ENPlanWorkDAO planDAO = new ENPlanWorkDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
            ENPlanWork plan = planDAO.getObject(planCode);

            if (plan.status.code == ENPlanWorkStatus.PRECONFIRMED)
            {
                plan.status.code = ENPlanWorkStatus.GOOD;
                planDAO.save(plan);
            }

            PlanWorkLogic l = new PlanWorkLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
            l.closePlan(planCode);

            /*
            //fConn = getRemoteConnection(AuthorizationJNDINames.SCANCOUNTERS_DATASOURCE);
            SCLogic scLogic = new SCLogic(getRemoteConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

            SCUsageInputItemDAO uiiDAO = new SCUsageInputItemDAO(getRemoteConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
            SCUsageInputItemOZDAO ozDAO = new SCUsageInputItemOZDAO(getRemoteConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
            SCUsageInputDAO uiDAO = new SCUsageInputDAO(getRemoteConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
            SCUsageInputItemOZ2SCCounterDAO o2cDAO = new SCUsageInputItemOZ2SCCounterDAO(getRemoteConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

            SCUsageInputItemOZInfoDAO ozInfoDAO = new SCUsageInputItemOZInfoDAO(getRemoteConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

            SCUsageInputItemFilter f = new SCUsageInputItemFilter();
            //f.code = 3;
            f.kindRef.code = SCUsageInputItemKind.InputUsing;
            f.conditionSQL = "code in (select uii.code from scusageinput ui, scusageinputitem uii where ui.statusrefcode = 3 and ui.code = uii.usageinputrefcode " +
                            " and uii.kindrefcode = 3 and uii.sccode is not null )";
            int[] uiiArr = uiiDAO.getFilteredCodeArray(f, 0, -1);
            for (int i=0; i < uiiArr.length; i++){


                SCUsageInputItem uii = uiiDAO.getObject(uiiArr[i]);

                SCUsageInput ui = uiDAO.getObject(uii.usageInputRef.code);

                scLogic.unTransferInvoiceInSC(uii.scCode, SCUsageInputItemKind.InputUsing, 0);
                scLogic.unTransferInvoiceInSC(uii.scCode, SCUsageInputItemKind.InputUsing, 1);

                SCUsageInputItemOZFilter f2 = new SCUsageInputItemOZFilter();
                f2.usageInputItemRef.code = uii.code;
                int[] ozArr = ozDAO.getFilteredCodeArray(f2, 0, -1);
                for (int j=0; j < ozArr.length; j++){
                    SCUsageInputItemOZ oz = ozDAO.getObject(ozArr[j]);
                    oz.scCode = scLogic.transferInvoiceInSC(uii.kindRef.code, oz.numberDoc , ui.dateGen, oz.countGen, scLogic.mol2podr(uii.molCode), uii.molCode);

                    SCUsageInputItemOZ2SCCounterFilter o2cFilter = new SCUsageInputItemOZ2SCCounterFilter();
                    o2cFilter.ozRef.code = oz.code;
                    SCUsageInputItemOZ2SCCounterShortList o2cList = o2cDAO.getScrollableFilteredList(o2cFilter, 0, -1);
                    for (int k=0; k<o2cList.totalCount; k++){

                        SCUsageInputItemOZ2SCCounterShort o2cShort = o2cList.get(k);

                        String yearBuild = "";
                        if ( o2cShort.scCounterRefDateBuild != null){
                            int qq__ = (1900 + o2cShort.scCounterRefDateBuild.getYear());
                            yearBuild = "" + qq__;
                        }

                        SCCounterSetupInfo rpData = scLogic.counterInstallPlace(o2cShort.scCounterEstimateItemRefCode, false);

                        scLogic.transferCounterABUnMountInSC(o2cShort.scCounterRefName , o2cShort.scCounterRefBuildNumber,
                                yearBuild,  oz.scCode , rpData.installInfo );

                    }

                    // доп инфо ...
                    SCUsageInputItemOZInfoFilter ozInfoFilter = new SCUsageInputItemOZInfoFilter();
                    ozInfoFilter.usageInputItemOZRef.code = oz.code;
                    SCUsageInputItemOZInfoShortList ozInfoList = ozInfoDAO.getScrollableFilteredList(ozInfoFilter, 0, -1);
                    if (ozInfoList.totalCount == 0){
                        throw new EnergyproSystemException("Не знайдено бух. інформацію для проведення приходу БУ лічільників від абонентів ...");
                    }

                    SCUsageInputItemOZInfoShort ozInfo = ozInfoList.get(0);
                    scLogic.moveCounterABUnMountInSC( oz.scCode , ozInfo.account, scLogic.mol2podr(uii.molCode), uii.molCode, ozInfo.sourceCode, ozInfo.expensesCode , oz.counterType
                            ,  null,  oz.numberDoc, ui.dateGen, ui.dateGen, ozInfo.sumWithNds, ozInfo.sumNds, ozInfo.sumGen );

                    ozDAO.save(oz);
                }

                uii.scCode = Integer.MIN_VALUE;

                uiiDAO.save(uii);
            }
            */


            /*
            PlanWorkLogic l = new PlanWorkLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

            String pCodes = l.getCodesHistoryDown(planCode);

            ENPlanWorkFilter f = new ENPlanWorkFilter();
            f.conditionSQL = "code in (" + pCodes + ") and code <> " + planCode;

            ENPlanWorkDAO dao = new ENPlanWorkDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

            int[] arr = dao.getFilteredCodeArray(f,0, -1);
            for (int i = 0; i < arr.length; i++){
                l.openPlanQQQ(arr[i]);
            }

            l.removeMoveHistory(planCode);

            l.deletePlan(planCode);
            */

/*
            //наплуживший сбыт ...
            PlanWorkLogic l = new PlanWorkLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
            EstimateLogic eLogic = new EstimateLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
            HumenLogic hLogic = new HumenLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
            TransportLogic tLogic = new TransportLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

            ENPlanWorkItemFilter f = new ENPlanWorkItemFilter();
            f.planRef.code = planCode;

            ENPlanWorkItemDAO dao = new ENPlanWorkItemDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
            ENPlanWorkItemShortList list = dao.getScrollableFilteredList(f, 0, -1);

            for (int i = 0; i < list.totalCount; i++){
            if (
                    list.get(i).kartaNum.equals("01030401")
                    ||list.get(i).kartaNum.equals("01030402")
                    ||list.get(i).kartaNum.equals("02030101")
                    ||list.get(i).kartaNum.equals("02030102")
                )
            {
                eLogic.removeENEstimateItemsByPlanItemCode(list.get(i).code);
                hLogic.removeHumenItemsByPlanItemCode(list.get(i).code);
                tLogic.removeENTransportItemsByPlanItemCode(list.get(i).code);
                dao.remove(list.get(i).code);
            }


            int tkCode = Integer.MIN_VALUE;

            if ( list.get(i).kartaNum.equals("01030401")
                    ||list.get(i).kartaNum.equals("01030402"))
            {
                tkCode = 75001320;
            }

            if ( list.get(i).kartaNum.equals("02030101")
                    ||list.get(i).kartaNum.equals("02030102"))
            {
                tkCode = 75001712;
            }

            if ((tkCode > Integer.MIN_VALUE)&&( list.get(i).code != 182014746))
            {
                ENPlanWorkItem obj = new ENPlanWorkItem();
                obj.planRef.code = planCode;
                obj.kartaRef.code = tkCode;
                obj.countGen = new BigDecimal(1.0);
                l.addPlanWorkItem(obj);
            }
        }

// пересчет времени доставки
            /*
    TransportLogic tl = new TransportLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
    tl.createDeliveryTimeForPlan(planCode);

        HumenLogic hl = new HumenLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
        hl.createDeliveryTime(planCode);
        */
    return null;
        }
        catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENPlanWork%} object.",e);}
        catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
        finally                              {closeConnection();}
  }



	public void closePlanWorkBySRS(int planCode) {
		closePlanWork(planCode);
	}

	public void closePlanWorkBySVES(int planCode) {
		closePlanWork(planCode);
	}

	public void closePlanWorkBySPS(int planCode) {
		closePlanWork(planCode);
	}

	public int closePlanWorkBilling(int planCode) {
		return closePlanWork(planCode);
	}

	public int closePlanWork(int planCode) {
		return closePlanWork(planCode, false);
	}



	public int closePlanWork(int planCode, boolean isFromBilling) {
		return closePlanWork(planCode, isFromBilling, null);
	}

	/**
	 *  Создание НПЗ....
	 *
	 *  @param planCode - код месячного плана
	 *  @param planWorkItemArr - ENPlanWorkItemShort[]
	 */
	public int closePlanWork(int planCode, ENPlanWorkItemShort[] planWorkItemArr) {
		return closePlanWork(planCode, false, planWorkItemArr);
	}

	public int closePlanWork(int planCode, boolean isFromBilling,
			ENPlanWorkItemShort[] planWorkItemArr) {
		return closePlanWork(planCode, isFromBilling, planWorkItemArr, false);
	}

	public int closePlanWork(int planCode, boolean isFromBilling,
			ENPlanWorkItemShort[] planWorkItemArr, boolean isForSupplier) {
		return closePlanWork(planCode, isFromBilling, planWorkItemArr, isForSupplier, null);
	}

	public int closePlanWork(int planCode, boolean isFromBilling,
			ENPlanWorkItemShort[] planWorkItemArr, boolean isForSupplier, Date newPlanDate) {

		try {

			/** проверка подключения */
			DBConnector dbConnector = new DBConnector();
			dbConnector.checkOracleConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);

			boolean isServices = false;
	        ENPlanWorkDAO objectDAO = new ENPlanWorkDAO(getUserProfile(), getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

	        ENPlanWork plan = objectDAO.getObject(planCode);

	        PlanWorkLogic logic = new PlanWorkLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());


	        /** SUPP-74627... 01.08.2018... +++ проверка отключена... САФ-1696 от 11.07.2018 "Про пропередження споживачів"... */
	        /**	Блокируем утвеждение, если план без сроков публикации (отключения из Call Центра)
	         *  и признаком "Работы выполняются с отключением потребителей"
	         */
	        /*
			if (plan.causeDisconnection == 1 && plan.kind.code != ENPlanWorkKind.YEAR) {
	        	// тут будет мегалогика проверки на заведенные даты проведения работ из кил-центра :)
	        	// Найдем месячный план
		        // PlanWorkLogic pLogic = new PlanWorkLogic(enConn, getUserProfile());

		        // определяем код месячного плана для НЕ месячных планов, иначе он у нас уже есть.
				int monthPlanCode;
				if (plan.kind.code != ENPlanWorkKind.CURRENT) {
					monthPlanCode = logic.getMonthPlanCodeByAnyPlanCode(plan.code);
				} else {
					monthPlanCode = plan.code;
				}

		        ENPlanWork2CCDamageLogDAO plan2damageDAO = new ENPlanWork2CCDamageLogDAO(netConnection, getUserProfile());
		        ENPlanWork2CCDamageLogFilter plan2damageFilter = new ENPlanWork2CCDamageLogFilter();
				plan2damageFilter.planRef.code = monthPlanCode;

				// если нет публикации под месячный план с признаком "Работы с отключением потребителей"
				// то будем ругаться, дальше будем проверять даты наряд-задания
				ENPlanWork2CCDamageLogShortList plan2damageList = plan2damageDAO.getScrollableFilteredList(plan2damageFilter, 0, -1);
				if (plan2damageList.totalCount == 0) {  throw new EnergyproSystemException("\n" +
		                   "\n Для месячного плана с кодом - " + monthPlanCode + " не найдено разрешенных дат(публикаций) выполнения работ");
		        }

				if (plan.kind.code == ENPlanWorkKind.NPW) {
					plan2damageFilter = new ENPlanWork2CCDamageLogFilter();
					plan2damageFilter.planRef.code = monthPlanCode;
		        	plan2damageFilter.conditionSQL = " '" + new SimpleDateFormat("dd.MM.yyyy").format(plan.dateStart) + "'"
		        			+ " between enplanwork2ccdamagelog.datebegin and enplanwork2ccdamagelog.datefinish";
		        	plan2damageList = plan2damageDAO.getScrollableFilteredList(plan2damageFilter, 0, -1);

					if (plan2damageList.totalCount == 0) {
		        		 throw new EnergyproSystemException("\n" +
		        				 "\n Нельзя создавать факт на эту дату, т.к. её нет в списке разрешенных дат(публикация) выполнения работ!");
					}
				}
			}*/



        if (plan.kind.code == ENPlanWorkKind.YEAR)
        {
        	// 30.10.14 NET-4395 Не даем создавать вручную планы на пополнение АВЗ (месячные из годовых)
        	logic.checkForAVZObject(plan.elementRef.code);

            /////
        	// NET-4415 Не даем вручную утверждать автоматически созданный Годовой план на изготовление -
        	// он утверждается автоматом при утверждении основного плана
        	if (logic.isAutoPlanForOwnProduction(planCode))
        	{
        		throw new SystemException("\n\nNET-4415 Для автоматично складених планів на виготовлення ця операція заборонена!");
        	}
            /////
        }


		/** NET-4555... +++ 23.08.2017....
		 *  проверка: относится ли план к заявке потребителя....
		 *  для бытовых потребителей...
		 */
        boolean planConsumerServices = false;
        ElementLogic elementLogic = new ElementLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
        int elementType = elementLogic.getElementTypeByCode(plan.elementRef.code);

        if (elementType == ENElementType.TY_BYT) {

        	int monthPlanCode = logic.getMonthPlanCodeByAnyPlanCode(plan.code);
    		DocFlowLogic docFlowLogic = new DocFlowLogic(getConnection(AuthorizationJNDINames.DOCFLOW_DATASOURCE), getUserProfile());
    		planConsumerServices = docFlowLogic.checkPlanConsumerServices(monthPlanCode);
        }


        /** 12.02.2018... +++ если план связан с заявкой из CallCenter'а - не проверяем права для утверждения плана... */
		int monthPlanCode = logic.getMonthPlanCodeByAnyPlanCode(plan.code);
		planConsumerServices = logic.checkPlan2CCDamageLog(monthPlanCode);

		if (!planConsumerServices) {

			///// 06.04.15 Проверим права на создание внеплановых планов для Энергосбыта
	        logic.checkEZNoplannedPlansClosing(plan);
	        /////
		}


        /** SUPP-3618... 18.05.2013...
         *  +++ утверждают со стороны планов, а потом "СТРАШНАЯ ОШИБКА" при подписании договора */
        if (plan.kind.code == ENPlanWorkKind.CALCULATION
                || plan.kind.code == ENPlanWorkKind.CALCULATION_SINGLE) {
            throw new EnergyproSystemException("\n" +
                    "\n План/кошторис затверджується при підписанні договору робіт на сторону!!!");
        }


        if (plan.typeRef.code == ENPlanWorkType.SERVICES_FROM_SIDE
                || plan.typeRef.code == ENPlanWorkType.SERVICES_FROM_SIDE_INVEST) {
            isServices = true;
        }

        if (isServices && plan.kind.code != ENPlanWorkKind.YEAR) {
            throw new EnergyproSystemException(
                    "Плани на послуги закриваютсься з Акту виконаних робіт та послуг");
        }

        AuthLogic al = new AuthLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
        if (!al.checkPermission4PlanItems(planCode)) {
            throw new EnergyproSystemException(
                    "Access denied for addBy... plan code=" + planCode);
        }

        // Для Буфета проверим права на соответствующие типы актов
        if (plan.stateRef.code == ENPlanWorkState.WRITINGS_BUFET_REALIZATION || plan.stateRef.code == ENPlanWorkState.WRITINGS_BUFET_NONEREALIZATION || plan.stateRef.code == ENPlanWorkState.WRITINGS_BUFET_REALIZATION_BEZNAL)
        {
            if (!al.checkPermission("ksoe/energynet/ENPlanWorkController", "checkWritingsOffBufet")) {
                throw new EnergyproSystemException("У Вас немає прав на цю операцію!");
            }
        }

        // NET-1026 Для списания счетчиков
        if (plan.stateRef.code == ENPlanWorkState.WRITINGS_COUNTERS)
        {
            if (!al.checkPermission("ksoe/energynet/SCCounterController", "addCountersForWriteOff")) {
                throw new EnergyproSystemException("У Вас немає прав на цю операцію!");
            }
        }

        // System.out.println("start");

        //PlanWorkLogic logic = new PlanWorkLogic(enConn, getUserProfile());

        /** 06.08.2013 by Tsehovles +++ для присоединений нельзя делать наряды из месячных планов по отмененным калькулациям */
        if (plan.kind.code == ENPlanWorkKind.CURRENT
                && logic.checkPriconnectionByServicesObject(plan.elementRef.code)) {
            String pwLinkedCodeArr = logic.getCodesHistoryUp(planCode);
            ENPlanWorkFilter planFilter = new ENPlanWorkFilter();
            planFilter.conditionSQL = "code in (" + pwLinkedCodeArr + ")";
            ENPlanWorkShortList planList = objectDAO.getScrollableFilteredList(planFilter, 0, -1);
                for (int i = 0; i < planList.totalCount; i++) {
                    if ((planList.get(i).kindCode == ENPlanWorkKind.CALCULATION
                            || planList.get(i).kindCode == ENPlanWorkKind.CALCULATION_SINGLE)
                                && planList.get(i).statusCode == ENPlanWorkStatus.CANCELED_) {
                        throw new EnergyproSystemException("\n" +
                                "\n Місячний план відноситься до поперднього розірваного договору!!!");
                    }
                }
        }


        /////// 25.02.2013
        logic.checkForCorrectInvNumber(plan);
        ///////


        //int newPlanCode = logic.closePlan(planCode);

        /** 03.07.2016... +++ closePlan вызывается с разных мест на клиенте...  */
        if (plan.kind.code == ENPlanWorkKind.CURRENT
        		&& plan.formRef.code == ENPlanWorkForm.PLANNED
        		&& planWorkItemArr == null) {

        	double totalCountWorks = 0;
        	boolean noWorks = true;

        	ENPlanWorkItemForClosePlanShortList planWorkItemForClosePlanList = logic.getPlanWorkItemForClosePlanList(planCode);
        	for (int i = 0; i < planWorkItemForClosePlanList.totalCount; i++) {
        		noWorks = false;
        		totalCountWorks = totalCountWorks + planWorkItemForClosePlanList.get(i).possibleCountGen.doubleValue();
        	}

        	if (totalCountWorks == 0 && !noWorks && elementType != ENElementType.ROUTE_BYT /* !!!! Временно исключим маршруты с проверки , посмотреть биллинг makePlanRouteProblemFeeder */ ) {
        		throw new SystemException("\n\n"
        				+ "Обсяг планових робіт згідно місячного плану повністю виконаний! \n"
        				+ "Код місячного плану: " + planCode + ".\n"
						+ "При необхідності використовуйте непланові роботи.");
        	}
        }

        	int newPlanCode = logic.closePlan(planCode, 1, false, isFromBilling, planWorkItemArr, isForSupplier, newPlanDate);

        	// System.out.println("final");

        	return newPlanCode;

		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException("Can't closePlanWork.", e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}


	public int closePlanWorkWithDate(int planCode, Date newPlanDate) {
		return closePlanWorkWithDate(planCode, newPlanDate, null);
	}

	public int closePlanWorkWithDate(int planCode, Date newPlanDate, ENPlanWorkItemShort[] planWorkItemArr) {
		return closePlanWorkWithDate(planCode, newPlanDate, planWorkItemArr, false);
	}

  /**
  *  Метод создает из заданного Месячного плана Задание-План и устанавливает ему заданную дату
  *
  *  @param planCode - код Месячного плана
  *  @param newPlanDate - дата для созданного нового Задания-Плана
  *
  *  @return код созданного Задания-Плана
  */
  public int closePlanWorkWithDate(int planCode, Date newPlanDate, ENPlanWorkItemShort[] planWorkItemArr, boolean isForSupplier)
  {
      try
      {
    	  ENPlanWorkDAO planDAO = new ENPlanWorkDAO(getUserProfile(), getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
    	  ENPlanWork plan = planDAO.getObject(planCode);

    	  if (plan == null)
    	  {
    		  throw new SystemException("\n\nNET-4350 Не знайдено план з кодом " + planCode + " [1]!");
    	  }

    	  if (plan.kind.code != ENPlanWorkKind.CURRENT)
    	  {
    		  throw new SystemException("\n\nNET-4350 Ця операція використовується тільки для Місячних планів!\n" +
    				  "План з кодом " + planCode + " не є Місячним!");
    	  }

    	  int newPlanCode = this.closePlanWork(planCode, false, planWorkItemArr, isForSupplier, newPlanDate);

    	  ENPlanWork newPlan = planDAO.getObject(newPlanCode);

    	  if (newPlan == null)
    	  {
    		  throw new SystemException("\n\nNET-4350 Не знайдено план з кодом " + newPlanCode + " [2]!");
    	  }

    	  if (newPlan.kind.code != ENPlanWorkKind.NPW)
    	  {
    		  throw new SystemException("\n\nNET-4350 План з кодом " + newPlanCode + " не є Завданням-Планом!");
    	  }

    	  newPlan.dateStart = newPlanDate;
    	  newPlan.dateFinal = newPlanDate;

    	  // копируем связку плана и договора если єто подрядный способ
    	  if(plan.typeRef.code == ENPlanWorkType.TMC_TRANSFER && plan.servicesFSideCnNum != null  ){
    		    ENPlanwork2GeneralContractsDAO p2genDAO = new ENPlanwork2GeneralContractsDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
                ENGeneralContractsDAO gencontrDAO = new ENGeneralContractsDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

		    	ENPlanwork2GeneralContractsFilter p2genFil = new ENPlanwork2GeneralContractsFilter();
				p2genFil.planRef.code = planCode;
				int[] p2genArr = p2genDAO.getFilteredCodeArray(p2genFil, 0, -1);

				if (p2genArr.length>0){
					ENPlanwork2GeneralContracts pp2gg = p2genDAO.getObject(p2genArr[0]);
					ENGeneralContracts gencontr =gencontrDAO.getObject(pp2gg.generalContractRef.code);
					newPlan.partnerCode=gencontr.partnerCode;
					newPlan.finDocCode=gencontr.finDocCode;
				}
/*				ENPlanwork2GeneralContracts p2gen = new ENPlanwork2GeneralContracts();
		    	p2gen.dateEdit=new Date();
		    	p2gen.generalContractRef.code=generalContractCode;
		    	p2gen.planRef.code=object.code;
		    	p2gen.userGen=getUserProfile().userName;
		    	p2genDAO.add(p2gen);
*/		    }

    	  this.save(newPlan, false, false, false, isForSupplier);

    	  return newPlanCode;
      }
      catch (DatasourceConnectException e)
      {
    	  throw new SystemException(e.getMessage(), e);
      }
      catch (PersistenceException e)
      {
    	  throw new SystemException(e.getMessage(), e);
      }
  }

  /*
  public int closePlanWorkForByt(int planCode, String molCode, String molName, FINExecutor finExecutor, Date newPlanDate)
  {
	  return this.closePlanWorkForByt(planCode, molCode, molName, finExecutor, newPlanDate, "", "", "", "");
  }
  */

  /**
  *  Метод для создания из Месячного плана Задания-Плана с заданной датой (для ЭНЕРГОСБЫТА!)
  *
  *  @param planCode - код Месячного плана
  *  @param molCode - код МОЛа для разнарядки
  *  @param molName - ФИО МОЛа для разнарядки
  *  @param finExecutor - исполнитель (объект класса FINExecutor)
  *  @param newPlanDate - дата для созданного нового Задания-Плана
  *  @param masterCode - код МОЛа-мастера для наряд-задания
  *  @param masterName - ФИО МОЛа-мастера для наряд-задания
  *  @param mechanicCode - код МОЛа-механика для наряд-задания
  *  @param mechanicName - ФИО МОЛа-механика для наряд-задания
  *
  *  @return код созданного Задания-Плана
  */
  public int closePlanWorkForByt(int planCode, String molCode, String molName, FINExecutor finExecutor, Date newPlanDate,
		  String masterCode, String masterName, String mechanicCode, String mechanicName)
  {
	  Context context;

	  try
	  {
		  context = new InitialContext();

          ENPlanWork plan = getObject(planCode);

          if (plan == null)
          {
        	  throw new SystemException("\n\nNET-4350 План з кодом " + planCode + " не знайдено!");
          }

          // Признак того, необходимо ли сохранять план (true - нужно сохранить)
          boolean needsSaving = false;

          ///////////////////// Добавляем МОЛа для разнарядки
          if (molCode != null && molName != null)
          {
        	  if ( (! molCode.equals("")) && (! molName.equals("")) )
        	  {
        	      Object mol2planRef = context.lookup(ENMOL2PlanWorkController.JNDI_NAME);

        	      ENMOL2PlanWorkControllerHome mol2planHome = (ENMOL2PlanWorkControllerHome) PortableRemoteObject.narrow(mol2planRef, ENMOL2PlanWorkControllerHome.class);
        	      ENMOL2PlanWorkController mol2planController = mol2planHome.create();

                  ENMOL2PlanWorkFilter mol2planFilter = new ENMOL2PlanWorkFilter();
                  mol2planFilter.code = Integer.MIN_VALUE;
                  mol2planFilter.planRef.code = planCode;

                  ENMOL2PlanWorkShortList mol2planList = mol2planController.getScrollableFilteredList(mol2planFilter, 0, -1);

                  if (mol2planList.totalCount == 0)
                  {
                      ENMOL2PlanWork mol2planObj = new ENMOL2PlanWork();
                      mol2planObj.code = Integer.MIN_VALUE;
                      mol2planObj.planRef.code = planCode;
                      mol2planObj.molCode = molCode;
                      mol2planObj.molName = molName;

                      mol2planController.add(mol2planObj);
                  }
                  else
                  {
                	  ENMOL2PlanWork mol2planObj = mol2planController.getObject(mol2planList.get(0).code);

                	  if (mol2planObj == null)
                	  {
                		  throw new SystemException("\n\nNET-4350 Помилка під час пошуку МВО для рознарядки!");
                	  }

                	  mol2planObj.molCode = molCode;
                	  mol2planObj.molName = molName;

                	  mol2planController.save(mol2planObj);
                  }
        	  }
          }
          /////////////////////

          ///////////////////// Добавляем исполнителя
          if (finExecutor != null)
          {
        	  if (finExecutor.finCode > Integer.MIN_VALUE || finExecutor.axOrgId != null)
        	  {
        		  //finExecutor.code = Integer.MIN_VALUE;

        		  //FINExecutorDAO finExecutorDAO = new FINExecutorDAO(getUserProfile(), getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
                  //plan.finExecutor.code = finExecutorDAO.add(finExecutor);

        		  plan.finExecutor.code = Integer.MIN_VALUE;

                  plan.finExecutor.finCode = finExecutor.finCode;
                  plan.finExecutor.name = finExecutor.name;
                  plan.finExecutor.finCehCode = finExecutor.finCehCode;
                  plan.finExecutor.finCehName = finExecutor.finCehName;
                  plan.finExecutor.finKindCode = finExecutor.finKindCode;
                  plan.finExecutor.finKindName = finExecutor.finKindName;
                  plan.finExecutor.finTypeCode = finExecutor.finTypeCode;
                  plan.finExecutor.finTypeName = finExecutor.finTypeName;
                  ///// MDAX-441
                  plan.finExecutor.axOrgId = finExecutor.axOrgId;
                  plan.finExecutor.axParentOrgId = finExecutor.axParentOrgId;
                  plan.finExecutor.axParentOrgName = finExecutor.axParentOrgName;
                  plan.finExecutor.axOrgTypeId = finExecutor.axOrgTypeId;
                  plan.finExecutor.axOrgTypeName = finExecutor.axOrgTypeName;
                  /////
                  // Если изменили исполнителя - нужно пересохранить план
                  needsSaving = true;
              }
          }
          /////////////////////

          ///////////////////// Сохраняем Месячный План
          // На плане может уже быть бюджетодержатель и центр ответственности, если это план по точке учета (может быть ВРТУ!!!)
          if (plan.budgetRef == null)
          {
        	  plan.budgetRef.code = ENConsts.ENBUDGET_ENERGOSBYT;
              // Если изменили бюджетодержателя - нужно пересохранить план
              needsSaving = true;
          }
          else if (plan.budgetRef.code == Integer.MIN_VALUE)
          {
        	  plan.budgetRef.code = ENConsts.ENBUDGET_ENERGOSBYT;
              // Если изменили бюджетодержателя - нужно пересохранить план
              needsSaving = true;
          }

          ///// 06.04.15 Проверим права на создание внеплановых планов для Энергосбыта
          PlanWorkLogic planLogic = new PlanWorkLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
          planLogic.checkEZNoplannedPlansClosing(plan);
          /////

          if (plan.responsibilityRef == null)
          {
        	  plan.responsibilityRef.code = ENConsts.ENRESPONSIBILITY_ENERGOSBYT;
              // Если изменили центр ответственности - нужно пересохранить план
              needsSaving = true;
          }
          else if (plan.responsibilityRef.code == Integer.MIN_VALUE)
          {
        	  plan.responsibilityRef.code = ENConsts.ENRESPONSIBILITY_ENERGOSBYT;
              // Если изменили центр ответственности - нужно пересохранить план
              needsSaving = true;
          }

          // Пересохраняем только в том случае, если что-то изменилось
          if (needsSaving)
          {
        	  //save(plan);
        	  planLogic.saveExecutorAndBudget(plan);
          }
          /////////////////////

          // Создаем Задание-План на заданную дату
          //return this.closePlanWorkWithDate(planCode, newPlanDate);
          int newPlanCode = this.closePlanWorkWithDate(planCode, newPlanDate);

          // ******************************************************************************************************
          /////// Создаем наряд-задание
          if (masterCode != null && masterName != null)
          {
        	  if ( (! masterCode.equals("")) && (! masterName.equals("")) )
        	  {
                  ENWorkOrder2ENPlanWork wo2plan = new ENWorkOrder2ENPlanWork();
                  wo2plan.code = Integer.MIN_VALUE;

                  wo2plan.plan = new ENPlanWork();
                  wo2plan.plan.code = newPlanCode;

                  wo2plan.workOrder = new ENWorkOrder();
                  wo2plan.workOrder.code = Integer.MIN_VALUE;
                  wo2plan.workOrder.department = new ENDepartment();
                  wo2plan.workOrder.department.code = plan.departmentRef.code;
                  wo2plan.workOrder.dateGen = newPlanDate;
                  wo2plan.workOrder.commentGen = "Автоматично створене Наряд-Завдання";
                  wo2plan.workOrder.finMolCode = masterCode;
                  wo2plan.workOrder.finMolName = masterName;
                  //wo2plan.workOrder.finMechanicCode = mechanicCode;
                  //wo2plan.workOrder.finMechanicName = mechanicName;

                  Object wo2planRef = context.lookup(ENWorkOrder2ENPlanWorkController.JNDI_NAME);

                  ENWorkOrder2ENPlanWorkControllerHome wo2planHome = (ENWorkOrder2ENPlanWorkControllerHome) PortableRemoteObject.narrow(wo2planRef, ENWorkOrder2ENPlanWorkControllerHome.class);
                  ENWorkOrder2ENPlanWorkController wo2planController = wo2planHome.create();

                  int wo2planCode = wo2planController.add(wo2plan);
                  ENWorkOrder2ENPlanWork wo2planObj = wo2planController.getObject(wo2planCode);

                  if (wo2planObj == null)
                  {
                      throw new EnergyproSystemException("\n\nNET-4350 Помилка під час створення наряд-завдання!");
                  }

                  if (wo2planObj.workOrder == null)
                  {
                      throw new EnergyproSystemException("\n\nNET-4350 Помилка під час створення наряд-завдання!");
                  }

                  if (wo2planObj.workOrder.code == Integer.MIN_VALUE)
                  {
                      throw new EnergyproSystemException("\n\nNET-4350 Помилка під час створення наряд-завдання!");
                  }

                  // Добавляем в наряд мастера
                  FINMolData finMOLDataObj = new FINMolData();
                  finMOLDataObj.workOrder = new ENWorkOrder();
                  finMOLDataObj.workOrder.code = wo2planObj.workOrder.code;
                  finMOLDataObj.finMolCode = masterCode;
                  finMOLDataObj.finMolName = masterName;
                  finMOLDataObj.molTypeRef = new FINMolTypeRef();
                  finMOLDataObj.molTypeRef.code = FINMolType.MASTER;
                  finMOLDataObj.code = Integer.MIN_VALUE;

                  Object finMOLDataRef = context.lookup(FINMolDataController.JNDI_NAME);

                  FINMolDataControllerHome finMOLDataHome = (FINMolDataControllerHome) PortableRemoteObject.narrow(finMOLDataRef, FINMolDataControllerHome.class);
                  FINMolDataController finMOLDataController = finMOLDataHome.create();

                  finMOLDataController.add(finMOLDataObj, wo2planObj.workOrder.code, 1);

                  // Добавляем в наряд механика
                  if (mechanicCode != null && mechanicName != null)
                  {
                      if ( (! mechanicCode.equals("")) && (! mechanicName.equals("")) )
                      {
                          finMOLDataObj = new FINMolData();
                          finMOLDataObj.workOrder = new ENWorkOrder();
                          finMOLDataObj.workOrder.code = wo2planObj.workOrder.code;
                          finMOLDataObj.finMolCode = mechanicCode;
                          finMOLDataObj.finMolName = mechanicName;
                          finMOLDataObj.molTypeRef = new FINMolTypeRef();
                          finMOLDataObj.molTypeRef.code = FINMolType.MECHANIC;
                          finMOLDataObj.code = Integer.MIN_VALUE;

                          finMOLDataController.add(finMOLDataObj, wo2planObj.workOrder.code, 1);
                      }
                  }
        	  }
          }
          ///////
          // ******************************************************************************************************

          return newPlanCode;
	  }
	  catch (NamingException e)
	  {
		  throw new SystemException(e.getMessage(), e);
	  }
	  catch (RemoteException e)
	  {
		  throw new SystemException(e.getMessage(), e);
	  }
	  catch (CreateException e)
	  {
		  throw new SystemException(e.getMessage(), e);
	  }
	  catch (DatasourceConnectException e)
	  {
		  throw new SystemException(e.getMessage(), e);
	  }
  }


  public int closePlanWorkWithParams(int planCode, Date newPlanDate,
		  String masterCode, String masterName, String mechanicCode, String mechanicName, ENPlanWorkItemShort[] planWorkItemArr) {
	  return closePlanWorkWithParams(planCode,  newPlanDate,  null, null,
			   masterCode,  masterName,  mechanicCode,  mechanicName, planWorkItemArr);
  }

  public int closePlanWorkWithParams(int planCode, Date newPlanDate,
		  String masterCode, String masterName, String mechanicCode, String mechanicName) {
	  return closePlanWorkWithParams(planCode,  newPlanDate,  null, null,
			   masterCode,  masterName,  mechanicCode,  mechanicName, null);
  }

  public int closePlanWorkWithParams(int planCode, Date newPlanDate, Date timeStart, Date timeFinal,
		  String masterCode, String masterName, String mechanicCode, String mechanicName) {
	  return closePlanWorkWithParams(planCode,  newPlanDate,  timeStart, timeFinal,
			   masterCode,  masterName,  mechanicCode,  mechanicName, null);
  }

	public int closePlanWorkWithParams(int planCode, Date newPlanDate, Date timeStart, Date timeFinal,
			String masterCode, String masterName, String mechanicCode, String mechanicName,
			ENPlanWorkItemShort[] planWorkItemArr) {
		return closePlanWorkWithParams(planCode, newPlanDate, timeStart, timeFinal,
				masterCode, masterName, mechanicCode, mechanicName,
				planWorkItemArr, false);
	}

  /**
  *  Метод для создания из Месячного плана Задания-Плана с заданной датой
  *
  *  @param planCode - код Месячного плана
  *  @param newPlanDate - дата для созданного нового Задания-Плана
  *  @param timeStart - время для СМС информирования
  *  @param timeFinal - время для СМС информирования
  *  @param masterCode - код МОЛа-мастера для наряд-задания
  *  @param masterName - ФИО МОЛа-мастера для наряд-задания
  *  @param mechanicCode - код МОЛа-механика для наряд-задания
  *  @param mechanicName - ФИО МОЛа-механика для наряд-задания
  *  @param planWorkItemArr
  *
  *  @return код созданного Задания-Плана
  *  @throws PersistenceException
  */
	public int closePlanWorkWithParams(int planCode, Date newPlanDate, Date timeStart, Date timeFinal,
			String masterCode, String masterName, String mechanicCode, String mechanicName,
			ENPlanWorkItemShort[] planWorkItemArr, boolean isForSupplier) {

		Context context;
		try {
		  ServicesLogic servicesLogic =  new ServicesLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

		  context = new InitialContext();

          ENPlanWork plan = getObject(planCode);

          if (plan == null)
          {
        	  throw new SystemException("\n\nNET-4350 План з кодом " + planCode + " не знайдено!");
          }

          ///////////////////// Добавляем МОЛа для разнарядки
          if (masterCode != null && masterName != null)
          {
        	  if ( (! masterCode.equals("")) && (! masterName.equals("")) )
        	  {
        	      Object mol2planRef = context.lookup(ENMOL2PlanWorkController.JNDI_NAME);

        	      ENMOL2PlanWorkControllerHome mol2planHome = (ENMOL2PlanWorkControllerHome) PortableRemoteObject.narrow(mol2planRef, ENMOL2PlanWorkControllerHome.class);
        	      ENMOL2PlanWorkController mol2planController = mol2planHome.create();

                  ENMOL2PlanWorkFilter mol2planFilter = new ENMOL2PlanWorkFilter();
                  mol2planFilter.code = Integer.MIN_VALUE;
                  mol2planFilter.planRef.code = planCode;

                  ENMOL2PlanWorkShortList mol2planList = mol2planController.getScrollableFilteredList(mol2planFilter, 0, -1);

                  if (mol2planList.totalCount == 0)
                  {
                      ENMOL2PlanWork mol2planObj = new ENMOL2PlanWork();
                      mol2planObj.code = Integer.MIN_VALUE;
                      mol2planObj.planRef.code = planCode;
                      mol2planObj.molCode = masterCode;
                      mol2planObj.molName = masterName;

                      mol2planController.add(mol2planObj);
                  }
                  else
                  {
                	  ENMOL2PlanWork mol2planObj = mol2planController.getObject(mol2planList.get(0).code);

                	  if (mol2planObj == null)
                	  {
                		  throw new SystemException("\n\nNET-4350 Помилка під час пошуку МВО для рознарядки!");
                	  }

                	  mol2planObj.molCode = masterCode;
                	  mol2planObj.molName = masterName;

                	  mol2planController.save(mol2planObj);
                  }
        	  }
          }
          /////////////////////

          // Создаем Задание-План на заданную дату
          //return this.closePlanWorkWithDate(planCode, newPlanDate);
          int newPlanCode = this.closePlanWorkWithDate(planCode, newPlanDate, planWorkItemArr, isForSupplier);

          // ******************************************************************************************************
          /////// Создаем наряд-задание
          if (masterCode != null && masterName != null)
          {
        	  if ( (! masterCode.equals("")) && (! masterName.equals("")) )
        	  {
                  ENWorkOrder2ENPlanWork wo2plan = new ENWorkOrder2ENPlanWork();
                  wo2plan.code = Integer.MIN_VALUE;

                  wo2plan.plan = new ENPlanWork();
                  wo2plan.plan.code = newPlanCode;

                  wo2plan.workOrder = new ENWorkOrder();
                  wo2plan.workOrder.code = Integer.MIN_VALUE;
                  wo2plan.workOrder.department = new ENDepartment();
                  wo2plan.workOrder.department.code = plan.departmentRef.code;
                  wo2plan.workOrder.dateGen = newPlanDate;
                  wo2plan.workOrder.commentGen = "Автоматично створене Наряд-Завдання";
                  wo2plan.workOrder.finMolCode = masterCode;
                  wo2plan.workOrder.finMolName = masterName;

                  Object wo2planRef = context.lookup(ENWorkOrder2ENPlanWorkController.JNDI_NAME);

                  ENWorkOrder2ENPlanWorkControllerHome wo2planHome = (ENWorkOrder2ENPlanWorkControllerHome) PortableRemoteObject.narrow(wo2planRef, ENWorkOrder2ENPlanWorkControllerHome.class);
                  ENWorkOrder2ENPlanWorkController wo2planController = wo2planHome.create();

                  int wo2planCode = wo2planController.add(wo2plan);
                  ENWorkOrder2ENPlanWork wo2planObj = wo2planController.getObject(wo2planCode);

                  if (wo2planObj == null)
                  {
                      throw new SystemException("\n\nNET-4350 Помилка під час створення наряд-завдання!");
                  }

                  if (wo2planObj.workOrder == null)
                  {
                      throw new SystemException("\n\nNET-4350 Помилка під час створення наряд-завдання!");
                  }

                  if (wo2planObj.workOrder.code == Integer.MIN_VALUE)
                  {
                      throw new SystemException("\n\nNET-4350 Помилка під час створення наряд-завдання!");
                  }

                  // Добавляем в наряд мастера
                  FINMolData finMOLDataObj = new FINMolData();
                  finMOLDataObj.workOrder = new ENWorkOrder();
                  finMOLDataObj.workOrder.code = wo2planObj.workOrder.code;
                  finMOLDataObj.finMolCode = masterCode;
                  finMOLDataObj.finMolName = masterName;
                  finMOLDataObj.molTypeRef = new FINMolTypeRef();
                  finMOLDataObj.molTypeRef.code = FINMolType.MASTER;
                  finMOLDataObj.code = Integer.MIN_VALUE;

                  Object finMOLDataRef = context.lookup(FINMolDataController.JNDI_NAME);

                  FINMolDataControllerHome finMOLDataHome = (FINMolDataControllerHome) PortableRemoteObject.narrow(finMOLDataRef, FINMolDataControllerHome.class);
                  FINMolDataController finMOLDataController = finMOLDataHome.create();

                  finMOLDataController.add(finMOLDataObj, wo2planObj.workOrder.code, 1);

                  // Добавляем в наряд механика
                  if (mechanicCode != null && mechanicName != null)
                  {
                      if ( (! mechanicCode.equals("")) && (! mechanicName.equals("")) )
                      {
                          finMOLDataObj = new FINMolData();
                          finMOLDataObj.workOrder = new ENWorkOrder();
                          finMOLDataObj.workOrder.code = wo2planObj.workOrder.code;
                          finMOLDataObj.finMolCode = mechanicCode;
                          finMOLDataObj.finMolName = mechanicName;
                          finMOLDataObj.molTypeRef = new FINMolTypeRef();
                          finMOLDataObj.molTypeRef.code = FINMolType.MECHANIC;
                          finMOLDataObj.code = Integer.MIN_VALUE;

                          finMOLDataController.add(finMOLDataObj, wo2planObj.workOrder.code, 1);
                      }
                  }
        	  }
          }


          if (timeStart != null && timeFinal != null) {
        	  if (newPlanDate.after(new Date())) {
          /// если план для услуг на сторону и создаётся план-задание,
          // то будем сразу информировать заказчика через СМС сообщение
          if (plan.stateRef.code == ENPlanWorkState.WORK_IN_OUT && plan.kind.code == ENPlanWorkKind.CURRENT) {

        	  // для начала найдем номер телефона, на который мы будем отправлять СМС
        	  String telephoneNumber = "";

        	  ENServicesObjectDAO soDAO = new ENServicesObjectDAO(getUserProfile(), getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
        	  ENServicesObjectFilter soFilter = new ENServicesObjectFilter();
        	  soFilter.element.code = plan.elementRef.code;
        	  ENServicesObjectShortList soList = soDAO.getScrollableFilteredList(soFilter, 0, -1);

        	  if (soList.totalCount > 0) {
        		  ENServicesObject so = soDAO.getObject(soList.get(0).code);

            	  String headMessage = "Za dogovorom " + so.contractNumberServices;

            	  /** если услуга связана с заявкой с сайта - в СМС идет № заявки */
            	  int servicesConsumerCode = servicesLogic.getServicesConsumerCode(so.code);
            	  if (servicesConsumerCode != Integer.MIN_VALUE) {
            		  DFDocServicesConsumerDAO servicesConsumerDao = new DFDocServicesConsumerDAO(getConnection(AuthorizationJNDINames.DOCFLOW_DATASOURCE), getUserProfile());
            		  DFDocServicesConsumer servicesConsumer = servicesConsumerDao.getObject(servicesConsumerCode);

            		  headMessage = "Za zayavkoyu " + servicesConsumer.numberGen;
            	  }

            	  /// будем отправлять только для обычных услуг
            	  if (so.contragentPhoneNumber != null &&
            			  so.contractKindRef.code == ENServicesContractKind.SERVICES &&
            			  so.contractTypeRef.code == ENServicesContractType.OTHERS) {
            		if  (so.contragentPhoneNumber != "" && so.contragentPhoneNumber.length() >= 10 ) {

            		  telephoneNumber = so.contragentPhoneNumber.replaceAll("\\D", "");

                	  ENPlanInformCustomerDAO infoCustDAO = new ENPlanInformCustomerDAO(getUserProfile(), getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
                	  ENPlanInformCustomerFilter infoFilter = new ENPlanInformCustomerFilter();
                	  infoFilter.conditionSQL = " enplaninformcustomer.planrefcode in (select code from enplanwork where enplanwork.elementrefcode = " + so.element.code +")";
                	  ENPlanInformCustomerShortList infoList = infoCustDAO.getScrollableFilteredList(infoFilter, 0, -1);
                	  if (infoList.totalCount == 0) {

    	            	  ENPlanInformCustomer infoCust = new ENPlanInformCustomer();
    	            	  infoCust.planRef.code = newPlanCode;
    	            	  infoCust.timeStart = timeStart;
    	            	  infoCust.timeFinal = timeFinal;
    	            	  infoCust.isSent = ENPlanInformCustomer.IS_NOT_SENT;
    	            	  int infCode =  infoCustDAO.add(infoCust);
    	            	  infoCust = infoCustDAO.getObject(infCode);

    	      			/// здесь будет вставка в КилЦентр
    	      			SMSInformControllerHome ejbSMSInformHome = (SMSInformControllerHome) CCIdentifierEjbCache.getHome(SMSInformController.JNDI_NAME, "com.ksoe.callcenter.ejb.SMSInformControllerHome");
    	      			SMSInformController smsInformController = ejbSMSInformHome.create();

    	                  	if (telephoneNumber.length() >= 10)  {
    	              			SMSInform sms = new SMSInform();
    	              			if (telephoneNumber.startsWith("38")) {telephoneNumber = telephoneNumber.substring(0,12);}
    	              			else telephoneNumber = telephoneNumber.substring(0,10);
    	              			sms.phonenumber = telephoneNumber;
    	              			sms.message = headMessage + ", na Vashomu obyekti budut' vykonuvatys' roboty " +
    	              					new SimpleDateFormat("dd.MM.yyyy 'z' HH:mm").format(infoCust.timeStart) +
    	              					" po " + new SimpleDateFormat("HH:mm").format(infoCust.timeFinal)  + ". Info: 0800500629";
    	              			sms.info = "SMS сформирована из задания-плана Енота";
    	              			sms.operator.code =  SMSInformOperator.VODAFONE;
    	              			sms.source = "ЕнотСМСингКомпани";
    	              			sms.priority = SMSInform.PRIORITY_DEFAULT;
    	              			sms.needConfirm =  SMSInform.NEED_CONFIRM_NO;
    	              			sms.status.code = 0;
    	              			smsInformController.add(sms);

    	              			infoCust.isSent = ENPlanInformCustomer.IS_SENT;
    	              			infoCustDAO.save(infoCust);
                      	     }
            	          }
            		    }
            		  }
        	  		}
          		}
             }
          }

			return newPlanCode;

		} catch (NamingException e) {
			throw new SystemException(e.getMessage(), e);
		} catch (RemoteException e) {
			throw new SystemException(e.getMessage(), e);
		} catch (CreateException e) {
			throw new SystemException(e.getMessage(), e);
		} catch (DatasourceConnectException e) {
			throw new SystemException(e.getMessage(), e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}


  public void openPlanWork(int planCode)
  {
	  openPlanWork(planCode, false);
  }

  public void openPlanWork(int planCode, boolean isFromCallCenter)
  {
	  openPlanWork(planCode, isFromCallCenter, false);
  }


  public void openPlanWork(int planCode, boolean isFromCallCenter, boolean isFromBilling) {
	  try {
	        //    throw new EnergyproSystemException("Пока такую оперицию делать НЕЛЬЗЯ !!!!");

		  	/** проверка подключения */
			DBConnector dbConnector = new DBConnector();
			dbConnector.checkOracleConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);


	        AuthLogic al = new AuthLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
	        if (! al.checkPermission4PlanItems(planCode)){
	            throw new EnergyproSystemException("Access denied for addBy... plan code="+planCode);
	        }

	        //UserProfile userprof = getUserProfile();
	        PlanWorkLogic logic = new PlanWorkLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());


	        ENPlanWorkDAO objectDAO = new ENPlanWorkDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
	        ENPlanWork p = objectDAO.getObject(planCode);

	        //ENElementDAO eDao = new ENElementDAO(userprof,enConn);
	        //ENElement elem = eDao.getObject(p.elementRef.code);

	        // Для Буфета проверим права на соответствующие типы актов
	        if (p.stateRef.code == ENPlanWorkState.WRITINGS_BUFET_REALIZATION || p.stateRef.code == ENPlanWorkState.WRITINGS_BUFET_NONEREALIZATION || p.stateRef.code == ENPlanWorkState.WRITINGS_BUFET_REALIZATION_BEZNAL)
	        {
	            if (!al.checkPermission("ksoe/energynet/ENPlanWorkController", "checkWritingsOffBufet")) {
	                throw new EnergyproSystemException("У Вас немає прав на цю операцію!");
	            }
	        }

	        // NET-1026 Для списания счетчиков
	        if (p.stateRef.code == ENPlanWorkState.WRITINGS_COUNTERS)
	        {

	            if (!al.checkPermission("ksoe/energynet/SCCounterController", "removeCountersForWriteOff")) {
	                throw new EnergyproSystemException("У Вас немає прав на цю операцію!");
	            }
	        }

	        boolean isServices = false;
	        if (p.typeRef.code == ENPlanWorkType.SERVICES_FROM_SIDE
	                || p.typeRef.code == ENPlanWorkType.SERVICES_FROM_SIDE_INVEST) {
	            isServices = true;
	        }

	        logic.checkPlanAccessByLogin(p, getUserProfile().userName);

	        /////

	        if ((isServices)&&(p.kind.code != ENPlanWorkKind.YEAR)&&(p.kind.code != ENPlanWorkKind.CURRENT)&&(p.status.code != ENPlanWorkStatus.GOOD))
	        {
	            throw new EnergyproSystemException(
	                    "Плани на послуги видаляються при відміні підписання Акту виконаних робіт та послуг");
	        }

	        if ( p.kind.code == ENPlanWorkKind.CURRENT && p.typeRef.code == ENPlanWorkType.WORK_IN_OUT ) {
	            throw new EnergyproSystemException(" Видаляти поточний план неможливо!!! Потрібно відмінити підписання договору по роботі на сторону!!!  " + planCode);
	        }

	        ///// NET-4415 Не даем удалять автоматически созданный план на изготовление
	    	if (logic.isAutoPlanForOwnProduction(planCode))
	    	{
	    		throw new SystemException("\n\nNET-4415 Для автоматично складених планів на виготовлення ця операція заборонена!");
	    	}
	        /////


			/** проверка: относится - ли план к заявке потребителя.... */
			DocFlowLogic docFlowLogic = new DocFlowLogic(getConnection(AuthorizationJNDINames.DOCFLOW_DATASOURCE), getUserProfile());
			if (docFlowLogic.checkPlanConsumerServices(planCode)) {
				throw new SystemException("\n\n"
						+ "План був створений за заявкою споживача, видалення неможливе! \n"
						+ "Для закриття заявки необхідно відпрацювати за планом.");
			}

			if (docFlowLogic.isPlanConnected2Doc(planCode)) {
				throw new SystemException("\n\nSUPP-92662 План зв'язаний із заявкою на ремонт, видалення неможливе!");
			}

        	logic.openPlan(planCode, 1, isFromCallCenter, isFromBilling);
        }

        catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENPlanWork%} object.",e);}
        catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
        finally                              {closeConnection();}
  }


  	/* ENPlanWork. Удалить */
	@Override
	public void remove(int code) {

		try {

			AuthLogic l = new AuthLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
			if (!l.checkPermission4PlanItems(code)) {
				throw new EnergyproSystemException("Acces denied for method addBy... from method ENPlanWork.save()");
			}

			PlanWorkLogic planLogic = new PlanWorkLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
			if (planLogic.isNotEditablePlan(code)){
				throw new EnergyproSystemException("Plan CLOSED. Using Reopen");
			}

			ENPlanWorkDAO objectDAO = new ENPlanWorkDAO(getUserProfile(),
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
			ENPlanWork p = objectDAO.getObject(code);


			/** Обновление стоимости на договоре подряда на выполнение ПКД... */
			ServicesLogic servicesLogic = new ServicesLogic(
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

			boolean servicesProject = servicesLogic.checkServicesProject(p.elementRef.code);
			int agreement2soCode = Integer.MIN_VALUE;

			if (servicesProject) {
				agreement2soCode = servicesLogic.getAgreement2soCode(p.elementRef.code);
			}


			if (p.kind.code == ENPlanWorkKind.CURRENT && p.typeRef.code == ENPlanWorkType.WORK_IN_OUT) {
				throw new EnergyproSystemException(
						" Видаляти поточний план неможливо!!! Потрібно відмінити підписання договору по роботі на сторону!!! " + code);
			}

	      if ( p.kind.code == ENPlanWorkKind.CALCULATION || p.kind.code == ENPlanWorkKind.CALCULATION_SINGLE ) {
	        throw new EnergyproSystemException(" Видаляти план/кошторис неможливо!!! Потрібно видалити договір по роботі на сторону!!!  " + code);
	      }

	      if (p.elementRef.code == Integer.MIN_VALUE){
	        throw new EnergyproSystemException("Plan without element !!! Plan code = " + code);
	      }

	      planLogic.checkPlanAccessByLogin(p, getUserProfile().userName);

	      // Для Буфета проверим права на соответствующие типы актов
	      if (p.stateRef.code == ENPlanWorkState.WRITINGS_BUFET_REALIZATION || p.stateRef.code == ENPlanWorkState.WRITINGS_BUFET_NONEREALIZATION || p.stateRef.code == ENPlanWorkState.WRITINGS_BUFET_REALIZATION_BEZNAL)
	      {
		        AuthLogic al = new AuthLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

		        if (!al.checkPermission("ksoe/energynet/ENPlanWorkController", "checkWritingsOffBufet")) {
		            throw new EnergyproSystemException("У Вас немає прав на цю операцію!");
		        }
	      }

	    // NET-1026 Для списания счетчиков
	    if (p.stateRef.code == ENPlanWorkState.WRITINGS_COUNTERS)
	    {

	        if (!l.checkPermission("ksoe/energynet/SCCounterController", "addCountersForWriteOff")) {
	            throw new EnergyproSystemException("У Вас немає прав на цю операцію!");
	        }
	    }

	    // Для сохранения планов на перевезення проверим может ли пользователь добавлять их
	    if(p.typeRef.code == ENPlanWorkType.TRUCKING)
	    {
	        if (! l.checkPermission("ksoe/energynet/ENPlanWorkController", "addByTrucking")) {
	            throw new EnergyproSystemException("У Вас немає прав на цю операцію!");
	        }
	    }


	    ///// NET-4415 Не даем удалять автоматически созданный план на изготовление
		if (planLogic.isAutoPlanForOwnProduction(code))
		{
			throw new SystemException("\n\nNET-4415 Для автоматично складених планів на виготовлення ця операція заборонена!");
		}
	    /////


		/** NET-4555... +++ 23.08.2017....
		 *  проверка: относится - ли план к заявке потребителя....
		 */
		DocFlowLogic docFlowLogic = new DocFlowLogic(getConnection(AuthorizationJNDINames.DOCFLOW_DATASOURCE), getUserProfile());
		if (docFlowLogic.checkPlanConsumerServices(code)) {
			throw new SystemException("\n\n"
					+ "План був створений за заявкою споживача, видалення неможливе! \n"
					+ "Для закриття заявки необхідно відпрацювати за планом.");
		}

		if (docFlowLogic.isPlanConnected2Doc(code)) {
			throw new SystemException("\n\nSUPP-92662 План зв'язаний із заявкою на ремонт, видалення неможливе!");
		}

		planLogic.deletePlan(code);


		/** Обновление стоимости на договоре подряда на выполнение ПКД... */
		if (servicesProject) {
			servicesLogic.recalcServicesProject(agreement2soCode);
		}


        //ENElementDAO eDao = new ENElementDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
	    //ENElement e = eDao.getObject(p.elementRef.code);



		//objectDAO.remove(code);

	    // вынесем уже ;) НЕИСПОЛЬЗОВАННОГО Екзекутора ;)
	    //if (p.finExecutor.code > Integer.MIN_VALUE){
	    //      FINExecutorDAO d = new FINExecutorDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
	    //      d.remove(p.finExecutor.code);
	    // }

		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENPlanWork%} object.", e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

  public int addByServicesObject(ENPlanWork object){
    return add(object);
  }


  public int addByOperativeObject(ENPlanWork object){
    return add(object);
  }

  public int addByTransformerObject(ENPlanWork object){
        return add(object);
   }

  public int addByPurchasesObject(ENPlanWork object){
        return add(object);
    }

  public int addByPurchasesNoObject(ENPlanWork object){
        return add(object);
    }


  public int addBySDTU(ENPlanWork object){
        return add(object);
    }

  public int addByRZA(ENPlanWork object){
        return add(object);
    }


  public int addBySRS(ENPlanWork object){
    return add(object);
  }

  public int addBySVES(ENPlanWork object){
        return add(object);
  }

  public int addBySPS(ENPlanWork object){
        return add(object);
  }

  public int addByByt(ENPlanWork object) {
        return add(object);
    }

    public int addByProm(ENPlanWork object) {
        return add(object);
    }


  public int addByTransport(ENPlanWork object){

    try
    {
        /* Дл планов на перевезення - проверка, чтобы со старых клиентов не вызывали этот метод для
            * планов на перевезення*/
        if(object.typeRef.code == ENPlanWorkType.TRUCKING)
        {
            AuthLogic l = new AuthLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

            if (! l.checkPermission("ksoe/energynet/ENPlanWorkController", "addByTrucking")) {
                throw new EnergyproSystemException("У Вас немає прав на цю операцію!");
            }
        }

        return add(object);
    }
        catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENPlanWork%} object.",e);}
        finally                              {closeConnection();}

  }

  /**
   *
   * Добавить план на перевезення
   *
   * @param object - план на перевезення
   * @return код плана
   */
  public int addByTrucking(ENPlanWork object){
    return add(object);
  }

  public int addByMetrology(ENPlanWork object){
    return add(object);
  }

  public int addByMetrologyCounters(ENPlanWork object){
    return add(object);
  }

  public int addByServicesFromSideObject(ENPlanWork object) {
        return add(object);
  }

  public void removeByServicesFromSideObject(int code) {
    remove(code);
  }


  public int addByParent(ENPlanWork object, int parentPlanCode){

    try
    {

        ENPlanWorkDAO planDAO = new ENPlanWorkDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

        ENPlanWork parentPlan = planDAO.getObject(parentPlanCode);

        parentPlan.status.code = ENPlanWorkStatus.LOCKED;

        planDAO.save(parentPlan);

        int outCode = this.add(object);

        AuthLogic al = new AuthLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
        if (! al.checkPermission4PlanItems(object.code)){
            throw new EnergyproSystemException("Access denied for addBy... plan code="+object.code);
        }

        ///// 13.02.18 NET-4561 Для планов на списание пломб при проведении экспертизы скопируем материалы из Мес. плана
        if (object.kind.code == ENPlanWorkKind.NPW) {

            ENElementDAO elementDAO = new ENElementDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
            ENElement e = elementDAO.getObject(object.elementRef.code);

        	if (e.elementInRef.code == ENConsts.ENELEMENT_METROLOGY_OBJECT_WRITEOFF) {

        		ENEstimateItemDAO estDAO = new ENEstimateItemDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
        		ENEstimateItem2ENEstimateItemDAO e2eDAO = new ENEstimateItem2ENEstimateItemDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
        		ENMarkEstimateDAO meDAO = new ENMarkEstimateDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

                // перекинем материалы, которые не привязаны к строкам плана
                ENEstimateItemFilter estFilter = new ENEstimateItemFilter();
                estFilter.planRef.code = parentPlanCode;
                estFilter.conditionSQL = "planitemrefcode is null";
                //ENEstimateItemShortList estList = estDAO.getScrollableFilteredList(estFilter,0,-1);
                int[] estArr = estDAO.getFilteredCodeArray(estFilter, 0, -1);
                for (int i=0; i < estArr.length; i++) {
                    ENEstimateItem est = new ENEstimateItem();
                    ENEstimateItem newEst = new ENEstimateItem();
                    est = estDAO.getObject(estArr[i]);
                    newEst = est;
                    newEst.planRef.code = outCode;
                    newEst.dateEdit = new Date();
                    newEst.userGen = getUserProfile().userName;

                    newEst.purchaseItemRef.code = Integer.MIN_VALUE;

                    int newEstimateCode = estDAO.add(newEst);

                    // копируем развязку мат-лов с маркерами ...
                    ENMarkEstimateFilter meFilter = new ENMarkEstimateFilter();
                    meFilter.estimateItem.code = estArr[i];
                    int[] meArr = meDAO.getFilteredCodeArray(meFilter, 0, -1);
                    for (int me=0; me < meArr.length; me++){
                        ENMarkEstimate meObj = meDAO.getObject(meArr[me]);
                        meObj.estimateItem.code = newEstimateCode;
                        meDAO.add(meObj);
                    }

                    ENEstimateItem2ENEstimateItem e2e = new ENEstimateItem2ENEstimateItem();
                    e2e.estimateItemInRef.code = estArr[i];
                    e2e.estimateItemOutRef.code = newEstimateCode;
                    e2e.countGen = est.countFact;
                    e2e.typeRef.code = ENEstimateItem2Type.PLAN_MOVED;
                    e2eDAO.add(e2e);

                }
        	}

        }
        /////

        ENPlanCorrectHistoryFilter f = new ENPlanCorrectHistoryFilter();
        f.planNewRef.code = parentPlanCode;

        ENPlanCorrectHistoryDAO chDAO = new ENPlanCorrectHistoryDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

        ENPlanCorrectHistoryShortList l = chDAO.getScrollableFilteredList(f,0,-1);

        int pPlan = Integer.MIN_VALUE;
        if (l.totalCount > 0 ){
            pPlan = l.get(0).planRefCode;
        }
        else{
            pPlan = parentPlanCode; //object.planOldRef.code;
        }

        int cReason = Integer.MIN_VALUE;

        if (parentPlan.kind.code == ENPlanWorkKind.CURRENT){
            cReason = ENPlanCorrectReason.MOVE_TO_NPW;
        }
        else{
            throw new EnergyproSystemException("error in addByParent : unknoun CorrectionREASOn :)");
        }

        ENPlanCorrectHistory ch = new ENPlanCorrectHistory();

        ch.dateGen = new Date();
        ch.userGen = getUserProfile().userName;
        ch.dateEdit = new Date();
        ch.planRef.code = pPlan;
        ch.reason.code = cReason;
        ch.planOldRef.code = parentPlanCode;
        ch.planNewRef.code = outCode;

        chDAO.add(ch);

        return outCode;
    }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENPlanWork%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}

  }

  public int addByMetrologyObject(ENPlanWork object){
    return add(object);
  }

  public int addByMetrologyDevices(ENPlanWork object){
    return add(object);
  }

  public int addByPVZ(ENPlanWork object){
    return add(object);
  }

  public int addByGift(ENPlanWork object){

    if(object.stateRef.code != ENPlanWorkState.GIFT)
        throw new EnergyproSystemException("Для об'єктів благодійності тип акту повинен бути \"Благодійна допомога\"");

    if(object.typeRef.code != ENPlanWorkType.GIFT)
        throw new EnergyproSystemException("Для об'єктів благодійності підвид робіт повинен бути \"Благодійна допомога\"");

    if(object.formRef.code != ENPlanWorkForm.NOPLANNED)
        throw new EnergyproSystemException("Для благодійної допомоги можна складати тільки позапланові плани");

    if(object.kind.code != ENPlanWorkKind.CURRENT)
        throw new EnergyproSystemException("Для благодійної допомоги складаються тільки місячні плани");

    return add(object);
  }

  public int addByAVR16(ENPlanWork object){

        if(object.kind.code != ENPlanWorkKind.CURRENT && object.kind.code != ENPlanWorkKind.YEAR)
            throw new EnergyproSystemException("Для Поповнення АВР-16 складаються тільки місячні (або річні) плани");

        return add(object);
  }

	public int addByBuilder(ENPlanWork object) {
		return add(object);
	}

	public int addByIzolation(ENPlanWork object) {
		return add(object);
	}

	public int addBySIT(ENPlanWork object) {
		return add(object);
	}

	public int addByPreproductionObject(ENPlanWork object) {
		return add(object);
	}

	public int addByMetrologySubstation(ENPlanWork object) {
		return add(object);
	}

	public int addByEB(ENPlanWork object) {
		return add(object);
	}

	public int addByWritingNoObject(ENPlanWork object) {
		return add(object);
	}

	public int addBySiz(ENPlanWork object) {
		return add(object);
	}

	public int addByEquipmentRepair(ENPlanWork object) {
		return add(object);
	}

	public int addByEquipment(ENPlanWork object) {
		return add(object);
	}

	public int addByWritingOffProtection(ENPlanWork object) {
		return add(object);
	}

	public void saveBySRS(ENPlanWork object) {
		save(object);
	}

	public void saveBySVES(ENPlanWork object) {
		save(object);
	}

	public void saveBySPS(ENPlanWork object) {
		save(object);
	}

	public void saveByByt(ENPlanWork object) {
		save(object);
	}

	public void saveByProm(ENPlanWork object) {
		save(object);
	}

	public void removeBySRS(int code) {
		remove(code);
	}

	public void removeBySVES(int code) {
		remove(code);
	}

	public void removeBySPS(int code) {
		remove(code);
	}

	public void removeByByt(int code) {
		remove(code);
	}

	public void removeByProm(int code) {
		remove(code);
	}


	@Override
	public int add(ENPlanWork object) {
		return this.add(object, false);
	}

	public int add(ENPlanWork object, boolean isForTechConditions) {
		return this.add(object, isForTechConditions, false);
	}

	public int add(ENPlanWork object, boolean isForTechConditions, boolean isForAVR) {
		return this.add(object, isForTechConditions, isForAVR, false);
	}

	public int add(ENPlanWork object, boolean isForTechConditions, boolean isForAVR, boolean isFromCallCenter) {
		return add(object, isForTechConditions, isForAVR, isFromCallCenter, false);
	}


	/**
	 *
	 * @param object
	 * @param isForTechConditions
	 * @param isForAVR
	 * @param isFromCallCenter
	 * @param createPlanFromInspectionSheet - создание плана на основании листа осмотра.
	 *
	 * @return
	 */
	public int add(ENPlanWork object, boolean isForTechConditions, boolean isForAVR, boolean isFromCallCenter,
			boolean createPlanFromInspectionSheet) {

		Connection conn = null;
		Connection finConn = null;
		Connection authCon = null;

		try {

        authCon = getConnection(AuthorizationJNDINames.AUTHORIZATION_DATASOURCE);
        conn = getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE);

        UserProfile up = getUserProfile();

        ENElementDAO eDao = new ENElementDAO(up,conn);


        System.out.print("######## getenelement  object.elementRef.code = " + object.elementRef.code );
        ENElement e = eDao.getObject(object.elementRef.code, false);
        ENPlanwork2GeneralContractsDAO p2genDAO = new ENPlanwork2GeneralContractsDAO(up,conn);

        AuthLogic al = new AuthLogic(authCon, up);
        PlanWorkLogic pl = new PlanWorkLogic(conn, up);


        /*Запретим создавать планы на обходы с элементом не маршрут*/

            if ( e == null ){
			   throw new EnergyproSystemException("Не знайдено об`єкт планування в EnergyNET ( object.elementRef.code = "+ object.elementRef.code +")!!!");
		    }

            if (e.typeRef == null){
 			   throw new EnergyproSystemException("Не визначено тип об`єкту планування!!!");
 		    }

            if(object.typeRef.code == ENPlanWorkType.EZ_PLANED_ROUND && e.typeRef.code != ENElementType.ROUTE_BYT)
            {
                throw new EnergyproSystemException("План з таким підвидом можна складати тільки для маршрутів з Energy");
            }

			if(object.typeRef.code == ENPlanWorkType.EZ_PLANED_ROUND_ADD && e.typeRef.code != ENElementType.ROUTE_BYT)
            {
                throw new EnergyproSystemException("План з таким підвидом можна складати тільки для маршрутів з Energy");
            }

			if (!up.userName.toUpperCase().equals("energynet".toUpperCase())) {


            if(e.typeRef.code == ENElementType.SERVICES_OBJECT)
            {
                throw new EnergyproSystemException("План для робіт на сторону складається автоматично при підписанні договору з меню 'Послуги на сторону'!");
            }
			}

           ///// 28.01.14 SUPP-11863 Запретим создавать планы на АВР из старого интерфейса (он криво работает, а новый еще и проще ;-))
           if (object.typeRef.code == ENPlanWorkType.AVR && !isForAVR)
           {
        	   throw new EnergyproSystemException("\n\nSUPP-11863 Для складання планів на АВР використовуйте пункт меню \"Планування -> Плани (АВР)\"!");
           }
           /////

           ///// 25.03.14 Нельзя создавать вручную планы на виртуальные объекты CallCenter'а !!!
           if (! isFromCallCenter && e.typeRef.code == ENElementType.CALLCENTER_OBJECT)
           {
        	   throw new EnergyproSystemException("\n\nNET-4337 Плани на ці об'єкти складаються автоматично з CallCenter'у!");
           }



           	/** SUPP-91969... 01.06.2020... +++ ограничение для СРМ... */
			if (!createPlanFromInspectionSheet && object.budgetRef.code == ENConsts.ENBUDGET_SRS) {

				if (e.typeRef.code == ENElementType.LINE10
						|| e.typeRef.code == ENElementType.LINE04
						|| e.typeRef.code == ENElementType.SUBSTATION04) {

					if (object.kind.code == ENPlanWorkKind.YEAR
							|| object.kind.code == ENPlanWorkKind.CURRENT) {

						if (object.formRef.code == ENPlanWorkForm.PLANNED
								|| object.formRef.code == ENPlanWorkForm.NOPLANNED) {

							if (object.typeRef.code != ENPlanWorkType.AVR) {

								if (object.stateRef.code == ENPlanWorkState.CAPITAL_REPAIR
										|| object.stateRef.code == ENPlanWorkState.TO) {

									/** SUPP-92565, SUPP-92594... 25.06.2020... +++ разрешили годовые, ТО */
									if (object.kind.code != ENPlanWorkKind.YEAR && object.stateRef.code != ENPlanWorkState.TO) {

										throw new SystemException("\n\n "
												+ "За вказівкою керівництва, \n "
												+ "для об'єктів ПЛ 6-10 кВ, ПЛ 0.4 кВ та ТП 0.4 кВ \n "
												+ "плани з типом акту \"ТО\" та \"КР\" створюються з листів огляду!");
									}

								}
							}
						}

					}
				}
			}



           /**
            *  NET-4548... 06.02.2017 +++ бригадные средства защиты....
            *  планирование только на дейтсвующие бригады...
            */
           if (e.typeRef.code == ENElementType.BSZ) {
        	   ENSzBrigadeDAO szDao = new ENSzBrigadeDAO(up,conn);
        	   ENSzBrigadeFilter szFilter = new ENSzBrigadeFilter();
        	   szFilter.element.code = e.code;

        	   int szArr[] = szDao.getFilteredCodeArray(szFilter, 0, 1);
        	   for (int s = 0; s < szArr.length; s++) {
        		   ENSzBrigade sz = szDao.getObject( szArr[s]);

        		   if (sz.statusCode != ENSzBrigade.WORKING) {
        			   throw new SystemException("\n\n"
        			   		+ "Ця бригада закрита. Планування неможливо!");
        		   }
        	   }
           }

            boolean isServices = false;
            if (object.typeRef.code == ENPlanWorkType.SERVICES_FROM_SIDE
                    || object.typeRef.code == ENPlanWorkType.SERVICES_FROM_SIDE_INVEST) {
                isServices = true;
            }

//            ///// 29.11.2013 Для ТМЦ запрещаем создание планов
//
////            if (object.yearGen != 2016 &&  ! isServices && object.kind.code != ENPlanWorkKind.NPW && object.kind.code != ENPlanWorkKind.FACT /*SUPP-10470*/)
//            if (
//            	(object.yearGen != 2017) &&
//                (((object.yearGen == 2014) || (object.yearGen == 2015)) && (object.formRef.code == ENPlanWorkForm.PLANNED)
//                		&& (object.kind.code != ENPlanWorkKind.NPW) && (object.kind.code != ENPlanWorkKind.FACT)
//                		&& (!isServices) && (e.typeRef.code!=ENElementType.ROUTE_BYT ))
//                	&& (object.typeRef.code != 13)
//                	/* 28.09.2015 Нахрена оно тут - ниже ж то же самое есть??? Тем более проверяться будут ТОЛЬКО 2014 и 2015 г. (см. условие чуть выше)!
//                	 * Короче говоря, эта проверка никогда не срабатывала :-D
//                	&& ((object.kind.code == ENPlanWorkKind.YEAR && object.yearGen == 2016)
//                			&& (object.budgetRef.code != ENConsts.ENBUDGET_ENERGOSBYT && object.budgetRef.code != ENConsts.ENBUDGET_SRS &&
//                    				object.budgetRef.code != ENConsts.ENBUDGET_TRANSPORT && object.budgetRef.code != ENConsts.ENBUDGET_IZOLATION &&
//                    				object.budgetRef.code != ENConsts.ENBUDGET_SKEM && object.budgetRef.code != ENConsts.ENBUDGET_OKS &&
//                    				object.budgetRef.code != ENConsts.ENBUDGET_METROLOGY && object.budgetRef.code != ENConsts.ENBUDGET_SPS &&
//                    				object.budgetRef.code != ENConsts.ENBUDGET_SVES))
//                    */
//               )
//
//            {
//            	if(!
//            			(
//               			        getUserProfile().userName.toUpperCase().equals("energynet".toUpperCase())
//                                ||getUserProfile().userName.toUpperCase().equals("NovohatskayaSS".toUpperCase())
//                                || getUserProfile().userName.toUpperCase().equals("StulovaAP".toUpperCase())
//                                || /*SUPP-42833*/getUserProfile().userName.toUpperCase().equals("SirenkoOA".toUpperCase())
//                                || getUserProfile().userName.toUpperCase().equals("AndrusenkoTV".toUpperCase())
//                                || getUserProfile().userName.toUpperCase().equals("NazarenkoOY".toUpperCase())
//                                || getUserProfile().userName.toUpperCase().equals("KalinichenkoEL".toUpperCase())
//                                || /*SUPP-43155*/ getUserProfile().userName.toUpperCase().equals("SteblovskayaNG".toUpperCase())
//                                || /*SUPP-43155*/ getUserProfile().userName.toUpperCase().equals("PanchenkoLP".toUpperCase())
//            			)
//            	  )
//
//                {
//                    throw new EnergyproSystemException("\n\nРедагування ПЛАНОВИХ планів на " + object.yearGen + " р. заборонено керівництвом компанії!");
//                }
//            }
//
//
//
//            /////
//
//            /** SUPP-2375 разрешаем на 2014 */
//            // запрещаем все годовые до лучших времен
//            //20140704 - разрешаем)
//            if //((object.kind.code == ENPlanWorkKind.YEAR && object.yearGen != 2017)
//               (/*object.kind.code == ENPlanWorkKind.YEAR && */object.yearGen == 2016 && object.formRef.code == ENPlanWorkForm.PLANNED
//               && ((object.kind.code != ENPlanWorkKind.NPW) && (object.kind.code != ENPlanWorkKind.FACT)))
////            	&&(!al.checkPermission4YearPlans()))
//            {
//            	/*
//    			if (object.budgetRef.code != ENConsts.ENBUDGET_ENERGOSBYT && object.budgetRef.code != ENConsts.ENBUDGET_SRS &&
//    				object.budgetRef.code != ENConsts.ENBUDGET_TRANSPORT && object.budgetRef.code != ENConsts.ENBUDGET_IZOLATION &&
//    				object.budgetRef.code != ENConsts.ENBUDGET_SKEM && object.budgetRef.code != ENConsts.ENBUDGET_OKS &&
//    				object.budgetRef.code != ENConsts.ENBUDGET_METROLOGY && object.budgetRef.code != ENConsts.ENBUDGET_SPS &&
//    				object.budgetRef.code != ENConsts.ENBUDGET_SVES && object.budgetRef.code != ENConsts.ENBUDGET_SOT &&
//    				object.budgetRef.code != ENConsts.ENBUDGET_CPP && object.budgetRef.code != ENConsts.ENBUDGET_RBS &&
//    				object.budgetRef.code != ENConsts.ENBUDGET_VMTP)
//    			{
//    				if(!getUserProfile().userName.toLowerCase().equals("energynet")) {
//    					throw new EnergyproSystemException("\n \n NET-3664 Складання річних планів заборонено керівництвом компанії!");
//    				}
//    			}
//    			else
//            	// 28.09.2015 Дадим права только тем, кто в группе "Админ текущих планов ХОЭ" (типа только Бюджетодержателям)
//            	if (! al.checkPermissionSilent("ksoe/energynet/ENPlanWorkController", "editPreConfirm"))
//            	{
//            		throw new EnergyproSystemException("\n \n NET-3664 Складання річних планів заборонено керівництвом компанії!");
//            	}
//            	*/
//            	if(!getUserProfile().userName.toLowerCase().equals("energynet")
//            			&& !getUserProfile().userName.toLowerCase().equals("stulovaap")
//                        && /*SUPP-42833*/!(getUserProfile().userName.toUpperCase().equals("SirenkoOA".toUpperCase())
//                        || getUserProfile().userName.toUpperCase().equals("AndrusenkoTV".toUpperCase())
//                        || getUserProfile().userName.toUpperCase().equals("NazarenkoOY".toUpperCase())
//                        || getUserProfile().userName.toUpperCase().equals("KalinichenkoEL".toUpperCase())
//                        || /*SUPP-43155*/ getUserProfile().userName.toUpperCase().equals("SteblovskayaNG".toUpperCase())
//                        || /*SUPP-43155*/ getUserProfile().userName.toUpperCase().equals("PanchenkoLP".toUpperCase()))) {
//
//            		// 24.11.15 SUPP-41878
//            		if (object.typeRef.code != ENPlanWorkType.SERVICES_FROM_SIDE
//            				&& object.budgetRef.code != ENConsts.ENBUDGET_ENERGOSBYT)
//            		{
//            			// 26.11.15 SUPP-41965 Засоби захисту
//            			if (object.typeRef.code != ENPlanWorkType.SIZ)
//            			{
//            				throw new EnergyproSystemException("\n\nSUPP-40280 Складання планів на 2016 рік заборонено керівництвом компанії!");
//            			}
//            		}
//
//            	}
//            }





            /*
            // запрещаем планирование на 2016
            if
               ((object.yearGen == 2016 || object.yearGen == 2017 ||
               object.yearGen == 2018 || object.yearGen == 2019 || object.yearGen == 2020 ||
               object.yearGen == 2021
               )
            		   && object.formRef.code == ENPlanWorkForm.PLANNED
               && ((object.kind.code != ENPlanWorkKind.NPW) && (object.kind.code != ENPlanWorkKind.FACT)))

            {
            		if (object.typeRef.code != ENPlanWorkType.SERVICES_FROM_SIDE)
            		{
            			if ((object.typeRef.code != ENPlanWorkType.SIZ)
            				&& (object.typeRef.code != ENPlanWorkType.EZ_PLANED_ROUND)
            				&& (object.typeRef.code != ENPlanWorkType.EZ_PLANED_ROUND_ADD)
            				&& (object.typeRef.code != ENPlanWorkType.EZ_CONTROL_MEASUREMENT) )
            			{
            				pl.checkPlanAccessByLogin(object, getUserProfile().userName);
            			}
            		}
            }
            */
            pl.checkPlanAccessByLogin(object, getUserProfile().userName);

            // NET-3213 Ограничение использования запрещенного транспорта
            if(e.typeRef.code == ENElementType.TRANSPORT)
            {
                TKTransportRealDAO trDAO = new TKTransportRealDAO(up, conn);
                TKTransportRealFilter trFilter = new TKTransportRealFilter();
                trFilter.enelement.code = e.code;
                TKTransportRealShortList trList = trDAO.getScrollableFilteredList(trFilter, 0, -1);

                if(trList.totalCount != 1)
                    throw new EnergyproSystemException("Помилка у кількості автотранспорту");

                if(trList.get(0).isNotUsed == ENConsts.TRANSPORT_REAL_ISNOTUSED)
                    throw new EnergyproSystemException("Транспорт " + trList.get(0).name + " заборонено використовувати");

            }






        ElementLogic elLogic = new ElementLogic(conn, up);
        String invNumb = elLogic.getElementInvNumber(object.elementRef.code);

        /////// 25.02.2013
        pl.checkForCorrectInvNumber(object);
        ///////

        // 30.10.14 NET-4395 Не даем создавать вручную месячные планы на пополнение АВЗ
        // 26.11.14 И годовые тоже!
        //if (object.kind.code == ENPlanWorkKind.CURRENT)
        //{
        	pl.checkForAVZObject(object.elementRef.code);
        //}

            /*NET-2938*/
            try
            {
                finConn = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
                /*Перебитие connection'a чтобы соединение с нетом опять стало основным*/
                conn = getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE);

                FKOSLogic fkOsLogic = new FKOSLogic(finConn, up);
                boolean isOS = fkOsLogic.isOS(object.elementRef.code);
                if(isOS == true)
                {
                    String invNumber = invNumb;
                    if(invNumber.length() == 5) invNumber = "0" + invNumber;
                    /*Если объект плана - основное средство, то проверяется введенность в эксплуатацию*/
                    boolean isInService = fkOsLogic.isInService(invNumber);

                    if(!isInService)
                        throw new EnergyproSystemException("Основний засіб з інв. номером " + invNumber + " не введенно в эксплуатацію");
                }
            }
            catch(DatasourceConnectException exc)
            {

            }

        // добавлять можно ТОЛЬКО ручные !!!
        object.sourceRef.code = ENPlanWorkSource.MANUAL;

        /* 24.01.2012 +++ для планов на Услуги со стороны */


        if (isServices) {
            if (object.ddsCodes.code == Integer.MIN_VALUE) {
                throw new EnergyproSystemException(
                        "Не вказаний код РГК!!!");
            }
        }

        if ((isServices) && (object.budgetRef.code == ENConsts.ENBUDGET_TRANSPORT))
            {
                if ((object.stateRef.code != ENPlanWorkState.TO) &&
                    (object.stateRef.code != ENPlanWorkState.CURRENT_REPAIR) &&
                    (object.stateRef.code != ENPlanWorkState.TRANSPORT_SERVICES_4_SIDE) &&
                    /*19.06.2013 +++ SUPP-4498 Добавлено условине на Реконструкцию/модернизацию по согласовнию с Софяник*/
                    (object.stateRef.code != ENPlanWorkState.RECONSTRUCTION_MODERNIZATION))

                    {
                    throw new EnergyproSystemException("\n" +
                            "\n Для служби транспорту заборонено використовувати інши типи актів крім - ТО, ПР та Транспортні послуги!");
                    }
            }

        /* 04.04.2012 +++ Для планов по ВРТУ (чтобы создавали только из формы договора) */
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        if (! isForTechConditions)
        {
            if (object.typeRef.code == ENPlanWorkType.PRIEDNANNY || object.typeRef.code == ENPlanWorkType.DESIGNING_TECHCONDITIONS)
            {
                // 10.04.12 10:25 Убираем ограничение пока, ВРТУшники хотят планы на старые договора заводить по-старому (Гончаров)
                if (object.typeRef.code == ENPlanWorkType.DESIGNING_TECHCONDITIONS) // Оставим проверку только для планов на проектирование
                {
                    throw new EnergyproSystemException("Ці плани потрібно додавати з форми договору (меню \"Послуги на сторону\" -> \"Приєднання\")!");
                }
            }

            if (object.priConnectionNumber != null)
            {
                if (! object.priConnectionNumber.equals(""))
                {
                    // 10.04.12 10:25 Убираем ограничение пока, ВРТУшники хотят планы на старые договора заводить по-старому (Гончаров)
                    // throw new EnergyproSystemException("Ці плани потрібно додавати з форми договору (меню \"Послуги на сторону\" -> \"Приєднання\")!");
                }
            }
        }
        ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


        if (!isForTechConditions && object.typeRef.code == ENPlanWorkType.SALE_PRODUCTS) {
            throw new EnergyproSystemException(
                    "\n Ці плани потрібно додавати з форми договору (меню \"Реалізація\" -> \"Реалізація ТМЦ\")!");
        }

        /*  03.01.2012 +++ для объектов без инвентарного можно только на планы с типом:
        *  инвест-программа, присоединение, перемещение ТМЦ для договоров подряда,
        *  кроме объектов метрологии, СиЗ, БсЗ, договоров и строительства
        *
        *  06.03.2012 +++ нарисоваласть перевозка грузов
        */

        if ((e.typeRef.code != ENElementType.METROLOGY_OBJECT && e.typeRef.code != ENElementType.SIZ
                && e.typeRef.code != ENElementType.BSZ && e.typeRef.code != ENElementType.OPERATIVE_OBJECT
                && e.typeRef.code != ENElementType.BUILDER
                && e.typeRef.code != ENElementType.ROUTE_BYT
                && e.typeRef.code != ENElementType.CARGO_OBJECT) && (invNumb.trim().length() < 4)) {
            if (object.typeRef.code != ENPlanWorkType.INVEST && object.typeRef.code != ENPlanWorkType.PRIEDNANNY
                    && object.typeRef.code != ENPlanWorkType.TMC_TRANSFER
                    && object.typeRef.code != ENPlanWorkType.SERVICES_FROM_SIDE
                    && object.typeRef.code != ENPlanWorkType.SERVICES_FROM_SIDE_INVEST

                    /* 31.05.2012 +++ разрешаем для проектирования и кап. строительства */
                    && object.stateRef.code != ENPlanWorkState.DESIGNING
                    && object.stateRef.code != ENPlanWorkState.CAPITAL_BUILDER
                    && object.stateRef.code != ENPlanWorkState.WORK_IN_OUT
                    ) {
                throw new EnergyproSystemException(
                        "Плани можливо заносити тільки для об'єктів з інвентарним номером!!!");
                }
        }

        /* 30.11.2011 +++ энергосбыт - перевезення в сад!!!! */
        if (object.typeRef.code == ENPlanWorkType.TRUCKING
                && object.budgetRef.code == ENConsts.ENBUDGET_ENERGOSBYT) {
            throw new EnergyproSystemException(
                    "Для енергозбуту заборонено складати плани з підвидом робіт \"Перевезення\"!");
        }

        boolean isYearPlanNotExists=true;

        if (object.typeRef.code == ENPlanWorkType.EZ_PLANED_CHANGE_COUNTER)
        {
            ENPlanWorkFilter pwFilter = new ENPlanWorkFilter();
            ENPlanWorkShortList enPlanWorkList;
            pwFilter.conditionSQL=" yeargen="+object.yearGen+" and monthgen="+object.monthGen+"  and kindcode=1 and elementrefcode="+object.elementRef.code;
                enPlanWorkList = getScrollableFilteredList(pwFilter,0,1);
                if (enPlanWorkList.list.size() > 0)
                {
                    isYearPlanNotExists=false;
                }
        }

        //if object.typeRef.code != ENPlanWorkType.EZ_PLANED_CHANGE_COUNTER

        /* 12.01.2012 +++ месячные можно только для "Услуг со стороны"!!!! */
        if ((object.kind.code == ENPlanWorkKind.CURRENT && object.formRef.code == ENPlanWorkForm.PLANNED)
                && (object.typeRef.code != ENPlanWorkType.SERVICES_FROM_SIDE
                    && object.typeRef.code != ENPlanWorkType.SERVICES_FROM_SIDE_INVEST)
                    && e.typeRef.code != ENElementType.SIZ
                    && e.typeRef.code != ENElementType.BSZ

                    && object.typeRef.code != ENPlanWorkType.EZ_PLANNED
                    && object.typeRef.code != ENPlanWorkType.EZ_PLANED_ROUND

                    && object.typeRef.code != ENPlanWorkType.EZ_CHANGE_ZKU
                    && object.typeRef.code != ENPlanWorkType.EZ_PLANED_CHANGE_COUNTER

                    && object.typeRef.code != ENPlanWorkType.EZ_PLANED_ROUND_ADD
                    && !((object.typeRef.code == ENPlanWorkType.EZ_PLANED_CHANGE_COUNTER)&&(isYearPlanNotExists))
                    /** средства защиты для подстанций */
                    && (object.stateRef.code != ENPlanWorkState.BSZ && e.typeRef.code != ENElementType.SUBSTATION150)
                    /* пообъектные безобъектные тоже пропускаем */
                    && (e.typeRef.code != ENElementType.PURCHASES_OBJECT)
                    && (e.typeRef.code != ENElementType.PURCHASES_NO_OBJECT)

            ) {
        		if(!getUserProfile().userName.toLowerCase().equals("energynet"))
        		throw new EnergyproSystemException(
                    //"Місячний план складається тільки на \"Послуги зі сторони\"!");
                        "ПЛАНОВІ місячні плани зараз можливо складати тільки на \"Послуги зі сторони\"!");
        }

        // как закончиться закупочная компания - сдвинуть ПЕРИОД


        ///// 06.09.12 Запрет создания годовых планов на 2013 год для Тех. Дирекции
        ENDepartmentDAO depDAO = new ENDepartmentDAO(up, conn);
        ENDepartment dep = depDAO.getObject(object.budgetRef.code);
        if (dep.managementRef != null)
        {
            if (dep.managementRef.code > Integer.MIN_VALUE)
            {
                if (dep.managementRef.code == ENManagement.TECHNICAL)
                {
                    pl.checkPeriodYearNEW(object, true);
                }
            }
        }
        /////



        // и не создавать поточные на текущий - 6
        if (
                (object.yearGen == 2010) &&
                ( (object.monthGen == 6) || (object.monthGen == 7)
                    || (object.monthGen == 8) || (object.monthGen == 9) || (object.monthGen == 10))
                && (object.kind.code == ENPlanWorkKind.CURRENT)
                && (object.typeRef.code != ENPlanWorkType.NOT_PLANED)
                && (e.typeRef.code != ENElementType.METROLOGY_OBJECT)
                && (e.typeRef.code != ENElementType.TY_BYT)
                && (e.typeRef.code != ENElementType.TY_PROM)
                && (e.typeRef.code != ENElementType.RZA)
                && (e.typeRef.code != ENElementType.SDTU)
                && (e.typeRef.code != ENElementType.SIT)
                && (e.typeRef.code != ENElementType.PURCHASES_OBJECT)
                && (e.typeRef.code != ENElementType.PURCHASES_NO_OBJECT)
                && (object.typeRef.code != ENPlanWorkType.PRIEDNANNY)
                && (object.typeRef.code != ENPlanWorkType.PREDPISANIE) // предписания !!!
                && (e.typeRef.code != ENElementType.BUILDER)

                && (e.typeRef.code != ENElementType.PREPRODUCTION_OBJECT)

                && (object.budgetRef.code != ENConsts.ENBUDGET_OKS) // бюджетодержатель - ОКС
                && (object.budgetRef.code != ENConsts.ENBUDGET_TRANSPORT) // служба транспорта

                && (e.typeRef.code != ENElementType.SIZ)  // средсва защиты
            )
        {
            throw new EnergyproSystemException("Материалы уже заказаны  .. изменится разнарядка !!!");
        }


        if(object.formRef.code != ENPlanWorkForm.NOPLANNED
                && (object.kind.code == ENPlanWorkKind.CURRENT || object.kind.code == ENPlanWorkKind.YEAR))
            pl.checkPeriod(object, true);


        if (
                (e.typeRef.code != ENElementType.LINE150)
                && ( (e.typeRef.code != ENElementType.TRANSPORT) && (object.budgetRef.code != ENConsts.ENBUDGET_TRANSPORT) ) // бюджет тра-та
                && ( object.budgetRef.code != ENConsts.ENBUDGET_SOT) // бюджет СОТ
                && (  e.typeRef.code != ENElementType.METROLOGY_SUBSTATION ) // группа по учету на ПС150-35-10
            )
        {
            pl.checkPeriodYear(object, true);
        }


        if ((e.typeRef.code == ENElementType.WRITING_NO_OBJECT ||
        	 e.code == ENConsts.ENELEMENT_METROLOGY_OBJECT_WRITEOFF ||
        	 e.elementInRef.code == ENConsts.ENELEMENT_METROLOGY_OBJECT_WRITEOFF)
        		&& object.typeRef.code != ENPlanWorkType.WRITINGOFFPROTECTION
        		&& object.stateRef.code != ENPlanWorkState.WRITINGS_OS // NET-4383 - пропускаем если списание ОС
        		){

        	if (e.elementInRef.code != ENConsts.ENELEMENT_METROLOGY_OBJECT_WRITEOFF) {
        		if (object.kind.code != ENPlanWorkKind.CURRENT ) throw new EnergyproSystemException("Для списань треба складати Місячний план ...");
        	}
            if (object.typeRef.code != ENPlanWorkType.WRITINGS) throw new EnergyproSystemException("ПідВид робіт повинен бути 'Списання матеріалів' !!!");
            if (object.formRef.code != ENPlanWorkForm.NOPLANNED) throw new EnergyproSystemException("Вид робіт повинен бути 'Позапланові' !!!");
            /*
            if (object.stateRef.code != ENPlanWorkState.WRITINGS_TMC) throw new EnergyproSystemException("Тип Акту повинен бути 'Списання матеріалів' !!!");
            if (object.stateRef.code != ENPlanWorkState.WRITINGS_MNMA) throw new EnergyproSystemException("Тип Акту повинен бути 'Списання МБП' !!!");
            if (object.stateRef.code != ENPlanWorkState.WRITINGS_OS) throw new EnergyproSystemException("Тип Акту повинен бути 'Списання ОЗ (11 рах.)' !!!");
            */

            if (
                (object.stateRef.code != ENPlanWorkState.WRITINGS_TMC) &&
                (object.stateRef.code != ENPlanWorkState.WRITINGS_MBP) &&
                (object.stateRef.code != ENPlanWorkState.WRITINGS_MBP_INSTRUMENTS) /*SUPP-13237*/ &&
                (object.stateRef.code != ENPlanWorkState.WRITINGS_MNMA) &&
                (object.stateRef.code != ENPlanWorkState.WRITINGS_OS) &&
                (object.stateRef.code != ENPlanWorkState.WRITINGS_BUFET_REALIZATION) &&
                (object.stateRef.code != ENPlanWorkState.WRITINGS_BUFET_NONEREALIZATION)&&
                (object.stateRef.code != ENPlanWorkState.WRITINGS_BUFET_REALIZATION_BEZNAL) &&
                (object.stateRef.code != ENPlanWorkState.WRITINGS_COUNTERS) /*NET-1026*/
            )
                throw new EnergyproSystemException("Тип Акту повинен бути 'Списання...' !!!");

        }


        // NET-4383 -- если списание ОЗ то не проверяем
        if(object.stateRef.code != ENPlanWorkState.WRITINGS_OS || object.typeRef.code != ENPlanWorkType.ENPLANWORKTYPE_WRITEOFF_OS) {
        	if (
        			(
                        (object.typeRef.code == ENPlanWorkType.WRITINGS)
                        || (object.stateRef.code == ENPlanWorkState.WRITINGS_TMC)
                        || (object.stateRef.code == ENPlanWorkState.WRITINGS_MBP)
                        || (object.stateRef.code == ENPlanWorkState.WRITINGS_MNMA)
                        || (object.stateRef.code == ENPlanWorkState.WRITINGS_MBP_INSTRUMENTS) /*SUPP-13237*/
                        || (object.stateRef.code == ENPlanWorkState.WRITINGS_OS)
                        || (object.stateRef.code == ENPlanWorkState.WRITINGS_BUFET_REALIZATION)
                        || (object.stateRef.code == ENPlanWorkState.WRITINGS_BUFET_NONEREALIZATION)
                        || (object.stateRef.code == ENPlanWorkState.WRITINGS_BUFET_REALIZATION_BEZNAL)
                        || (object.stateRef.code == ENPlanWorkState.WRITINGS_COUNTERS) /*1026*/
        			)
        			&& (e.typeRef.code != ENElementType.WRITING_NO_OBJECT))
        	{

        		// 06.02.18 NET-4561 Для вирт. объектов для списания пломб и сейф-пакетов можно
        		if (e.code != ENConsts.ENELEMENT_METROLOGY_OBJECT_WRITEOFF &&
        			e.elementInRef.code != ENConsts.ENELEMENT_METROLOGY_OBJECT_WRITEOFF) {

        			throw new EnergyproSystemException("На такі об'єкти такі плани не складаються ;)");

        		}

        	}
        }


    // запрет Плановых работ для АВРа
    // ВС
    if ( object.typeRef.code == ENPlanWorkType.AVR ){

        if ( object.formRef.code != ENPlanWorkForm.NOPLANNED ){
            throw new EnergyproSystemException("АВР заводяться тільки як ПОЗАПЛАНОВІ роботи ...");
        }

    }

    // 29.08.11 Проверка подвида работ/типа акта для планов на объекты договоров на ТО и ремонт
    if (e.typeRef.code == ENElementType.OPERATIVE_OBJECT)
    {
        if ((object.typeRef.code != ENPlanWorkType.AVR) &&
            (object.typeRef.code != ENPlanWorkType.PLANOVIE) &&
            (object.typeRef.code != ENPlanWorkType.NOT_PLANED))
        {
            throw new EnergyproSystemException("Для цих об'єктів можна складати тільки плани з наступними підвидами робіт: \n" +
                                                "\"Планові роботи\", \"Непланові роботи\", \"АВР\"!");
        }

        if ((object.stateRef.code != ENPlanWorkState.WORK_IN_OUT)
            && (object.stateRef.code != ENPlanWorkState.MEASUREMENT))
        {
            throw new EnergyproSystemException("Для цих об'єктів можна складати тільки плани з типом акту \"Роботи на сторону\"!");
        }
    }

    // 03.10.11 Проверка подвида работ/типа акта для планов по услугам на сторону
    if (e.typeRef.code == ENElementType.SERVICES_OBJECT && (!up.userName.toUpperCase().equals("energynet".toUpperCase())))
    {
        if (object.typeRef.code != ENPlanWorkType.WORK_IN_OUT)
        {
            throw new EnergyproSystemException("Для цих об'єктів можна складати тільки плани з підвидом робіт \"Роботи на сторону\"!");
        }

        if (object.stateRef.code != ENPlanWorkState.WORK_IN_OUT)
        {
            throw new EnergyproSystemException("Для цих об'єктів можна складати тільки плани з типом акту \"Роботи на сторону\"!");
        }
    }


    // Для Буфета проверим права на соответствующие типы актов
    if (object.stateRef.code == ENPlanWorkState.WRITINGS_BUFET_REALIZATION || object.stateRef.code == ENPlanWorkState.WRITINGS_BUFET_NONEREALIZATION || object.stateRef.code == ENPlanWorkState.WRITINGS_BUFET_REALIZATION_BEZNAL)
    {

        if (!al.checkPermission("ksoe/energynet/ENPlanWorkController", "checkWritingsOffBufet")) {
            throw new EnergyproSystemException("У Вас немає прав на цю операцію!");
        }
    }

    // NET-1026 Для списания счетчиков
    if (object.stateRef.code == ENPlanWorkState.WRITINGS_COUNTERS)
    {

        if (!al.checkPermission("ksoe/energynet/SCCounterController", "addCountersForWriteOff")) {
            throw new EnergyproSystemException("У Вас немає прав на цю операцію!");
        }
    }

    object.dateEdit = new Date();
    object.userGen = getUserProfile().userName;

    ENPlanWorkDAO objectDAO = new ENPlanWorkDAO(getUserProfile(),conn);


    object.renRef.code = e.renRef.code;


    // теперь еще заежжает Исполнитель из ФинКол
    // по идее не всегда он есть ...
    // а даже если его и нет, то будем подставлять из привязанного на элементе
    this.setFinExecutor(object, e);

    // даты начала работ и период плана


    // из за ГЛЮКА с датами у 11.14 .. для БЫТА проверку уберем ...
    if ( ( e.typeRef.code != ENElementType.TY_BYT )
            &&(e.typeRef.code != ENElementType.TY_PROM)
    ){
        pl.validatePlanPeriod(object);
    }


    		/* 26.02.2012 +++ даём делать для Услуг со стороны  */
			if (!(object.typeRef.code == ENPlanWorkType.SERVICES_FROM_SIDE
					|| object.typeRef.code == ENPlanWorkType.SERVICES_FROM_SIDE_INVEST
					|| object.stateRef.code == ENPlanWorkState.WRITINGS_OS) ) {

				/** 31.03.2021... +++ пропускаем CallCenter */
				if (!isFromCallCenter) {
					// проверка уникальности ..
					pl.checkUnicPlan(object);
				}
			}


    // по предписанию - создавать ТОЛЬКО текущий !!!
    if (
            (object.typeRef.code == ENPlanWorkType.PREDPISANIE)
        &&
        (object.kind.code != ENPlanWorkKind.CURRENT)
    ){
        throw new EnergyproSystemException("Работы по предписанию заводяться ТОЛЬКО на текущий план ...");
    }


    // проверим неплановые планы в будущем ... + такое же в SAVE
    if ( object.formRef.code == ENPlanWorkForm.NOPLANNED){

            Calendar calendar_ = Calendar.getInstance();
            Calendar calendar_11 = Calendar.getInstance();
            calendar_11.set(object.yearGen, object.monthGen - 1 ,1);

            calendar_.setTime(new Date());

            int monthDelta = 1;
            // типа для таких инвалидов мона на 3 месяца вперед делать неплановые ...
            if (
                    ((object.budgetRef.code == ENConsts.ENBUDGET_OKS) // ОКС
                    || ( object.budgetRef.code == ENConsts.ENBUDGET_RBS ) // РБС служ от 04.03 про планы на май 2011 ;)  + 3 мес ..
                    || (object.budgetRef.code == ENConsts.ENBUDGET_TRANSPORT)) // Служба транспорта - SUPP-10064
                    && (object.typeRef.code == ENPlanWorkType.INVEST)
                    && ((object.stateRef.code == ENPlanWorkState.RECONSTRUCTION_MODERNIZATION) || (object.stateRef.code == ENPlanWorkState.CAPITAL_BUILDER)
                            || (object.stateRef.code == ENPlanWorkState.TO && object.budgetRef.code == ENConsts.ENBUDGET_TRANSPORT /*ТО только для СТ*/))
            )
            {
                monthDelta = 3;
            }

            /** SUPP-11830... +++ а для инвалидов со спец.одеждой на 4 месяца вперед */
            if (e.typeRef.code == ENElementType.SIZ) {
                monthDelta = 4;
            }

            /** Для SDTU делаем неплановые планы на весь год вперед*/
            if (object.budgetRef.code == ENConsts.ENBUDGET_SDTU) {
            	monthDelta = 12;
            }

            calendar_.add(Calendar.MONTH, monthDelta);

            //if (object.monthGen > (calendar_.get(Calendar.MONTH) + 1))
            if (calendar_11.after(calendar_))
            {
            	if (!up.userName.equalsIgnoreCase("energynet") && !up.userName.equalsIgnoreCase("PrometnayaOP")) // Юрквский А.В. сказал что на время открыть пользователю заносить планы
                throw new EnergyproSystemException("Позапланові плани складаються не більше ніж на місяць вперед (не більше "+ (calendar_.get(Calendar.MONTH) + 1 )+")...", getUserProfile());
            }

            /*
            Calendar calendar_ = Calendar.getInstance();
            calendar_.setTime(new Date());
            calendar_.add(Calendar.MONTH, 1);

            if (object.monthGen > (calendar_.get(Calendar.MONTH) + 1)){
                throw new EnergyproSystemException("Позапланові плани складаються не більше ніж на місяць вперед (не більше "+ (calendar_.get(Calendar.MONTH) + 1 )+" місяць)...", getUserProfile());
            }
            */
    }

      object.yearOriginal = Integer.MIN_VALUE;
      object.monthOriginal = Integer.MIN_VALUE;

    ///// 29.12.10
      // Пока будем сразу ставить статус "На затвердженні"

      /* :)
    if (object.yearGen == 2011)
    {

        if ( !
                ((object.formRef.code == ENPlanWorkForm.NOPLANNED) &&
                    (object.kind.code == ENPlanWorkKind.CURRENT) &&
                    (object.typeRef.code == ENPlanWorkType.AVR || object.typeRef.code == ENPlanWorkType.NOT_PLANED)
                )
            ) // Планы на АВР и Неплановые работы с видом "Позапланові" делаем черновыми, остальные - "На затвердженні"
        object.status.code = ENPlanWorkStatus.PRECONFIRMED;

        //         нах опять метрология ... счетчики - черновой план ..
        if  (( e.typeRef.code == ENElementType.METROLOGY_COUNTER) && (object.kind.code == ENPlanWorkKind.NPW))
            object.status.code = ENPlanWorkStatus.GOOD;
    }
    /////
      */


      if (object.typeRef.code == ENPlanWorkType.SERVICES_FROM_SIDE
            || object.typeRef.code == ENPlanWorkType.SERVICES_FROM_SIDE_INVEST) {
        if (e.typeRef.code == ENElementType.SERVICES_FROM_SIDE_OBJECT) {
            ENServicesFromSideObjectDAO fsDAO = new ENServicesFromSideObjectDAO(up, conn);
            ENServicesFromSideObjectFilter fsFilter = new ENServicesFromSideObjectFilter();
            fsFilter.element.code = e.code;

            int fsA[] = fsDAO.getFilteredCodeArrayNOSEGR(fsFilter, 0, -1);
            ENServicesFromSideObject fsObject = fsDAO.getObjectNOSEGR(fsA[0]);
            object.servicesFSideFinId = fsObject.finDocID;
            object.servicesFSideCnNum = fsObject.contractNumber;
        }
      }

      // CALL-123... +++  02.06.2014 DEBUG!!!!
      // boolean isPlanForSetCauseDisconnection = pl.checkPlanForSetCauseDisconnection(object);
      // if (isPlanForSetCauseDisconnection) {object.causeDisconnection = 1;}

    int planCode = objectDAO.add(object);

    // для списаний - накинем хитрый материал ...
    // если списание средств защиты то не будем накидывать хитрый материал
    if ((e.typeRef.code == ENElementType.WRITING_NO_OBJECT || e.code == ENConsts.ENELEMENT_METROLOGY_OBJECT_WRITEOFF)
    		&& object.typeRef.code != ENPlanWorkType.WRITINGOFFPROTECTION  ){
        ENEstimateItem est = new ENEstimateItem();
        ENEstimateItemDAO estDAO = new ENEstimateItemDAO(conn, up);
        est.planRef.code = planCode;
        est.countGen = est.countFact = new BigDecimal(1);
        est.materialRef.code = TKConsts.TKMATERIALS_WRITING;
        est.kindRef.code = ENEstimateItemKind.MATERIALS;
        est.statusRef.code = ENEstimateItemStatus.PRESENT;
        est.typeRef.code = ENEstimateItemType.MANUAL_BY_PLAN;
        est.commentGen = "Автоматично створений ... НЕ КОРИГУВАТИ !!!";
        est.price = est.cost = new BigDecimal(0);
        est.dateEdit = new Date();
        est.userGen = getUserProfile().userName;
        estDAO.add(est);

    }

    // 07.02.18 NET-4561 Для вирт. объектов для списания пломб и сейф-пакетов накинем еще и пломбу
    if (e.code == ENConsts.ENELEMENT_METROLOGY_OBJECT_WRITEOFF) {
        ENEstimateItem est = new ENEstimateItem();
        ENEstimateItemDAO estDAO = new ENEstimateItemDAO(conn, up);
        est.planRef.code = planCode;
        est.countGen = est.countFact = new BigDecimal(1);
        est.materialRef.code = TKConsts.TKMATERIALS_SEAL;
        est.kindRef.code = ENEstimateItemKind.MATERIALS;
        est.statusRef.code = ENEstimateItemStatus.PRESENT;
        est.typeRef.code = ENEstimateItemType.MANUAL_BY_PLAN;
        est.commentGen = "Автоматично створений матеріал";
        est.price = est.cost = new BigDecimal(0);
        est.dateEdit = new Date();
        est.userGen = getUserProfile().userName;
        estDAO.add(est);
    }

    // материал для пополнения производственного запаса...
    // они его будут юзать для ЗАЯВОК ...
    /*
    if (e.typeRef.code == ENElementType.NO_OBJECT_RESTOCKING) {
        ENEstimateItem est = new ENEstimateItem();
        ENEstimateItemDAO estDAO = new ENEstimateItemDAO(conn, up);
        est.planRef.code = planCode;
        est.countGen = est.countFact = new BigDecimal(1);
        est.materialRef.code = TKConsts.TKMATERIALS_RESTOCKING;
        est.kindRef.code = ENEstimateItemKind.MATERIALS;
        est.statusRef.code = ENEstimateItemStatus.PRESENT;
        est.typeRef.code = ENEstimateItemType.MANUAL_BY_PLAN;
        est.commentGen = "Автоматично створений ... НЕ КОРИГУВАТИ !!!";
        est.price = est.cost = new BigDecimal(0);
        est.dateEdit = new Date();
        est.userGen = getUserProfile().userName;
        estDAO.add(est);
    }
    */

    // работа для СиЗ ...
    if (e.typeRef.code == ENElementType.SIZ) {

        ENPlanWorkItem plItem = new ENPlanWorkItem();
        PlanWorkLogic plg = new PlanWorkLogic(conn, up);

        ENSizObjectDAO sizDao = new ENSizObjectDAO(conn, up);
        TKTechCardDAO kDao = new TKTechCardDAO(conn, up);
        SiZLogic siZLogic = new SiZLogic(conn, up);

        // планы СиЗ (для работников) - проверка штатного...
        int sizObjectCode = sizDao.getSizObjectCode(object.elementRef.code);
        String invNumber = sizDao.getInvNumberSizObject(object.elementRef.code);
        boolean checkInShtatWorker = pl.checkInShtatWorker(invNumber, true, sizObjectCode);

        if (checkInShtatWorker) {
            objectDAO.remove(object.code);
            return Integer.MIN_VALUE;
        }

        int sizCode = sizDao.getSizCode(object.elementRef.code);
        if (sizCode == Integer.MIN_VALUE) {
            throw new EnergyproSystemException("На робітнику відсутній норматив ЗіЗ ... " + object.elementRef.code);
        }


        int tkCode = siZLogic.getKartaBySiz(sizCode);
        if (tkCode == Integer.MIN_VALUE) {
            throw new EnergyproSystemException("Відсутній норматив ЗіЗ ... " + sizCode);
        }

        plItem.planRef.code = planCode;
        plItem.countGen = new BigDecimal(1.0);
        plItem.kartaRef.code = tkCode;

        plItem.dateEdit = new Date();
        plItem.userGen = getUserProfile().userName;

        plg.addPlanWorkItem(plItem, object);
    }

    // работа для бригадных СЗ ...
    if (e.typeRef.code == ENElementType.BSZ) {

        ENPlanWorkItem plItem = new ENPlanWorkItem();
        PlanWorkLogic plg = new PlanWorkLogic(conn, up);
        SiZLogic siZLogic = new SiZLogic(conn, up);

        ENSzBrigadeDAO bszDao = new ENSzBrigadeDAO(conn, up);
        TKTechCardDAO kDao = new TKTechCardDAO(conn, up);

        int sizCode = bszDao.getSizCode(object.elementRef.code);
        if (sizCode == Integer.MIN_VALUE) {
            throw new EnergyproSystemException("На бригаді відсутній норматив ЗЗ ... " + object.elementRef.code);
        }

        int tkCode = siZLogic.getKartaByBsz(sizCode);
        if (tkCode == Integer.MIN_VALUE) {
            throw new EnergyproSystemException("Відсутній норматив ЗБЗ ... " + sizCode);
        }

        int quantity = bszDao.getBrigadeQuantity(object.elementRef.code);

        plItem.planRef.code = planCode;

        if (quantity != Integer.MIN_VALUE) {
            plItem.countGen = new BigDecimal(quantity);
        } else {
            plItem.countGen = new BigDecimal(1.0);
        }

        plItem.kartaRef.code = tkCode;

        plItem.dateEdit = new Date();
        plItem.userGen = getUserProfile().userName;

        plg.addPlanWorkItem(plItem, object);
    }


    // работа для ТП-04 при условии, что это средства защиты ...
    if (e.typeRef.code == ENElementType.SUBSTATION04
            && object.kind.code == ENPlanWorkKind.CURRENT
            && object.formRef.code == ENPlanWorkForm.PLANNED
            && object.typeRef.code == ENPlanWorkType.SIZ
            && object.stateRef.code == ENPlanWorkState.BSZ) {

        ENPlanWorkItem plItem = new ENPlanWorkItem();
        PlanWorkLogic plg = new PlanWorkLogic(conn, up);
        SiZLogic siZLogic = new SiZLogic(conn, up);

        ENSzBrigadeDAO bszDao = new ENSzBrigadeDAO(conn, up);
        TKTechCardDAO kDao = new TKTechCardDAO(conn, up);

        int sizCode = bszDao.getSizCodeByTP(object.elementRef.code);

        if (sizCode == Integer.MIN_VALUE) {
            throw new EnergyproSystemException("На ТП-04 відсутній норматив ЗЗ ... " + object.elementRef.code);
        }

        int tkCode = siZLogic.getKartaByTP(sizCode);
        if (tkCode == Integer.MIN_VALUE) {
            throw new EnergyproSystemException("Відсутній норматив ЗЗ ... " + sizCode);
        }

        plItem.planRef.code = planCode;
        plItem.countGen = new BigDecimal(1.0);
        plItem.kartaRef.code = tkCode;
        plItem.dateEdit = new Date();
        plItem.userGen = getUserProfile().userName;

        plg.addPlanWorkItem(plItem, object);
    }

    // работа для ПС-35/150 при условии, что это средства защиты ...
    if (e.typeRef.code == ENElementType.SUBSTATION150
            && object.kind.code == ENPlanWorkKind.CURRENT
            && object.formRef.code == ENPlanWorkForm.PLANNED
            && object.typeRef.code == ENPlanWorkType.SIZ
            && object.stateRef.code == ENPlanWorkState.BSZ) {

        ENPlanWorkItem plItem = new ENPlanWorkItem();
        PlanWorkLogic plg = new PlanWorkLogic(conn, up);
        SiZLogic siZLogic = new SiZLogic(conn, up);

        ENSzBrigadeDAO bszDao = new ENSzBrigadeDAO(conn, up);
        TKTechCardDAO kDao = new TKTechCardDAO(conn, up);

        int sizCode = bszDao.getSizCodeByPS(object.elementRef.code);
        if (sizCode == Integer.MIN_VALUE) {
            throw new EnergyproSystemException("На ПС-35/150 відсутній норматив ЗЗ ... " + object.elementRef.code);
        }

        int tkCode = siZLogic.getKartaByTP(sizCode);
        if (tkCode == Integer.MIN_VALUE) {
            throw new EnergyproSystemException("Відсутній норматив ЗЗ ... " + sizCode);
        }

        plItem.planRef.code = planCode;
        plItem.countGen = new BigDecimal(1.0);
        plItem.kartaRef.code = tkCode;
        plItem.dateEdit = new Date();
        plItem.userGen = getUserProfile().userName;

        plg.addPlanWorkItem(plItem, object);
    }


    // можно ли добавлять ТАКОЙ план ;)
    if (object.kind.code == ENPlanWorkKind.FACT &&  object.typeRef.code != ENPlanWorkType.WRITINGOFFPROTECTION ){
    	// NET-4383 -- если списание ОЗ то не проверяем
        if(object.stateRef.code != ENPlanWorkState.WRITINGS_OS || object.typeRef.code != ENPlanWorkType.ENPLANWORKTYPE_WRITEOFF_OS) {
          throw new EnergyproSystemException("Задание-ФАКТ заводить НЕЛЬЗЯ ...");
        }
    }
    else
    if (object.kind.code == ENPlanWorkKind.NPW){
        /* из за заказов материалов - ВСЕ планы в поточном !!!
        * ВС 08112010
        if (
                (object.typeRef.code != ENPlanWorkType.AVR)
                && (e.typeRef.code != ENElementType.METROLOGY_COUNTER)
                && (e.typeRef.code != ENElementType.TRANSPORT )
                && (e.typeRef.code != ENElementType.SIT)
                && (object.typeRef.code != ENPlanWorkType.NOT_PLANED)
                && (object.typeRef.code != ENPlanWorkType.INVEST)
                && (object.typeRef.code != ENPlanWorkType.WORK_IN_OUT)
                && (object.typeRef.code != ENPlanWorkType.PREDPISANIE)
            )
            */
        {

            //throw new EnergyproSystemException("Задание-план можно заводить только на АВР ...");
            // меторология проснулась ;) ..
            if ( e.typeRef.code != ENElementType.METROLOGY_COUNTER && object.typeRef.code != ENPlanWorkType.WRITINGOFFPROTECTION   ){
                throw new EnergyproSystemException("Завдання-план складаеться тільки з МІСЯЧНОГО плану ...");
            }
        }
    }
    else
    if (object.kind.code == ENPlanWorkKind.YEAR && object.formRef.code == ENPlanWorkForm.PLANNED) {
        // НАХ .. как оно работало пока заводили половину годовых ;)))))))) .. понятно - ненаходились утвержденные с другого домена ;)
        /*
        ENPlanWorkFilter f = new ENPlanWorkFilter();
        f.yearGen = object.yearGen;
        f.status.code = ENPlanWorkStatus.LOCKED;
        int arr[] = objectDAO.getFilteredCodeArray(f, null, null, 0, 1, null);
        if ( arr.length > 0 ) {
            throw new EnergyproSystemException("На этот год есть утвержденные планы .. измените год или тип (с годового на текущий)");
        }
        */
            //Date d = new Date();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());

            if ( calendar.get(Calendar.YEAR) >=  object.yearGen) {
            	ENSettingsLogic settingsLogic = new ENSettingsLogic(conn, up);
            	Vector<String> users = settingsLogic.getVectorWithValues(ENSettingsKeysConsts
            			.USERS_ALLOWED_ADDITION_ANNUAL_PLANS_WHEN_PROHIBITED);
            	if(!CollectionTools.checkCollectionContainsStringIgnoreCase(users, up.userName)) {
                    throw new EnergyproSystemException("Годовые планы на заданный Вами год уже должны были быть заведены!");
            	}
            //throw new EnergyproSystemException("Годовые планы уже должны были быть заведены ... причем давно ... до 25.08." + object.yearGen);
        }
    }
    else
    if (object.kind.code == ENPlanWorkKind.CURRENT) {
        if (object.typeRef.code != ENPlanWorkType.PRIEDNANNY){
            ENPlanCorrectHistoryDAO cDAO = new ENPlanCorrectHistoryDAO(conn, up);
            ENPlanCorrectHistory c = new ENPlanCorrectHistory();
            c.dateEdit = new Date();
            c.dateGen = new Date();
            c.userGen = up.userName;
            c.reason.code = ENPlanCorrectReason.NO_YEAR;
            c.planNewRef.code = planCode;
            c.planOldRef.code = planCode;
            c.planRef.code = planCode;
            cDAO.add(c);
        }
    }


    /**
    *  17.08.2012 +++ Несколько исполнителей на плане
    *  при наличии испонителя на месячном плане,
    *  добавляем его в звязку с типом "Основной"
    *  100% загрузка
    *
    */

    if ((object.kind.code == ENPlanWorkKind.CURRENT || object.kind.code == ENPlanWorkKind.YEAR) &&
            (object.finExecutor != null && object.finExecutor.code > Integer.MIN_VALUE)) {

        FINExecutor2PlanDAO executor2PlanDAO = new FINExecutor2PlanDAO(
                    getUserProfile(),
                    conn);

        /*
        *  NET-3092...
        *  22.10.2012 +++ основной исполнитель может добавиться раньше в лоджике
        *
        */

        FINExecutor2PlanFilter finExecutor2PlanFilter = new FINExecutor2PlanFilter();
        finExecutor2PlanFilter.planRef.code = object.code;
        finExecutor2PlanFilter.finExecutorTypeRef.code = FINExecutorType.PRIMARY;
        FINExecutor2PlanShortList finExecutor2PlanList = executor2PlanDAO.getScrollableFilteredList(finExecutor2PlanFilter, 0, -1);

        if (finExecutor2PlanList.totalCount == 0) {

            FINExecutor2Plan executor2Plan = new FINExecutor2Plan();
            executor2Plan.code = Integer.MIN_VALUE;
            executor2Plan.finExecutorTypeRef.code = FINExecutorType.PRIMARY;
            executor2Plan.planRef.code = planCode;
            executor2Plan.finExecutor.code = object.finExecutor.code;
            executor2Plan.dateStart = object.dateStart;
            executor2Plan.dateFinal = object.dateFinal;
            executor2Plan.percentLoad = new BigDecimal(100);
            executor2Plan.budgetRef.code = object.budgetRef.code;

            executor2PlanDAO.add(executor2Plan);
        }


        // NET-4383 сгенерим наряд задание для списания материалов
       // ддд
    }


    System.out.print("object partnecode=" + object.partnerCode);
    System.out.print("object finDocCode=" + object.finDocCode);

    if(object.typeRef.code == ENPlanWorkType.TMC_TRANSFER && object.servicesFSideCnNum != null  ){
    	boolean isCustomer = false;
		boolean isException = true;
    	ContractLogic contractLogic = new ContractLogic(conn, getUserProfile());

    	// получим код договора со вставкой в engeneralcontract
    	int generalContractCode = contractLogic.addByContractNumber(object.servicesFSideCnNum, object.partnerCode, object.finDocCode, isCustomer, isException);



		if (generalContractCode == Integer.MIN_VALUE ){
			throw new EnergyproSystemException("Не знайдено договір в ФК " +  object.servicesFSideCnNum + "!!!" );
		}

		ENPlanwork2GeneralContracts p2gen = new ENPlanwork2GeneralContracts();
    	p2gen.dateEdit=new Date();
    	p2gen.generalContractRef.code=generalContractCode;
    	p2gen.planRef.code=planCode;
    	p2gen.userGen=getUserProfile().userName;
    	p2genDAO.add(p2gen);
    }

    return planCode;

    }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't generateEsentialByPlan ",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {
        try {
            if (conn != null && !conn.isClosed()) {
                conn.close();
                conn = null;
            }
        } catch (SQLException e) {
        }
        try {
            if (finConn != null && !finConn.isClosed()) {
                finConn.close();
                finConn = null;
            }
        } catch (SQLException e) {
        }
        try {
            if (authCon != null && !authCon.isClosed()) {
                authCon.close();
                authCon = null;
            }
        } catch (SQLException e) {
        }
    }
  }


	public void saveAddInfo(ENPlanWork object) {
		try {

            if (( object.kind.code == ENPlanWorkKind.YEAR ) || (object.kind.code == ENPlanWorkKind.FACT)) {
                throw new EnergyproSystemException("Эта операция делается ТОЛЬКО на Месячных и Ежедневных планах!");
            }

            AuthLogic l = new AuthLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
            if ( ! l.checkPermission4PlanItems(object.code) ){
                throw new EnergyproSystemException("Access denied for method addBy... from method ENPlanWork.save()");
            }

			object.setDomain_info(null);

			ENPlanWorkDAO objectDAO = new ENPlanWorkDAO(getUserProfile(), getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
			ENPlanWork plan = objectDAO.getObject(object.code);


        	/** 09.09.2018... +++ перебивают дату на меньшую договору, а потом борода..... отрицательный период выполнения услуги... */
			ElementLogic elementLogic = new ElementLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
			int elementType = elementLogic.getElementTypeByPlan(plan);

			if (elementType == ENElementType.SERVICES_OBJECT && plan.kind.code == ENPlanWorkKind.CURRENT
					&& plan.dateStart.before(object.dateStart)) {
				throw new SystemException("\n\n" + "Дата початку робіт за договором "
						+ new SimpleDateFormat("dd.MM.yyyy").format(plan.dateStart) + " не зменшується!");
			}


			// Признак изменения даты плана
			boolean dateChanged = false;
			if (!object.dateStart.equals(plan.dateStart)) {
				dateChanged = true;
			}

			PlanWorkLogic planLogic = new PlanWorkLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

			// пока меняем ТОЛЬКО дату нач/конца и Исполнителя ...
			if (object.kind.code == ENPlanWorkKind.CURRENT) {
				plan.dateStart = object.dateStart;
				plan.dateFinal = object.dateFinal;

				///// 29.02.12 NET-1355 Проверяем только дату начала работ, дата окончания вычисляется автоматом!
				// planLogic.validatePlanPeriod(plan);
				planLogic.validatePlanPeriodOnlyStart(plan);
				/////
			}

	        /* В этом методе все равно запрещается изменять месяц/год плана (проверка выполняется в planLogic.validatePlanPeriodOnlyStart - вызов выше)
	        if (plan.yearGen != 2013 && ! isServices)
	        {
	            throw new EnergyproSystemException("\n\nСкладання планів на " + object.yearGen + " р. заборонено керівництвом компанії!");
	        }
	        */
	        /////

			if (object.finExecutor != null) {

				FINExecutorDAO d = new FINExecutorDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE),
						getUserProfile());
				FINExecutor f = null;
				if (object.finExecutor.finCode > Integer.MIN_VALUE || object.finExecutor.axOrgId != null) {
					if (object.finExecutor.code > Integer.MIN_VALUE)
						f = d.getObject(object.finExecutor.code);
					else
					{
						f = new FINExecutor();
						f.code = Integer.MIN_VALUE;
					}

	                f.finCode = object.finExecutor.finCode;
	                f.name =  object.finExecutor.name;
	                f.finCehCode = object.finExecutor.finCehCode;
	                f.finCehName = object.finExecutor.finCehName;
	                f.finKindCode = object.finExecutor.finKindCode;
	                f.finKindName = object.finExecutor.finKindName;
	                f.finTypeCode = object.finExecutor.finTypeCode;
	                f.finTypeName = object.finExecutor.finTypeName;
	                ///// MDAX-441
	                f.axOrgId = object.finExecutor.axOrgId;
	                f.axParentOrgId = object.finExecutor.axParentOrgId;
	                f.axParentOrgName = object.finExecutor.axParentOrgName;
	                f.axOrgTypeId = object.finExecutor.axOrgTypeId;
	                f.axOrgTypeName = object.finExecutor.axOrgTypeName;
	                /////
	                f.code = Integer.MIN_VALUE;
	                plan.finExecutor.code = d.add(f);

	                ////////////////////////////////////////
	                // NET-2800 Апдейтим основного исполнителя на плане (или создаем) - в развязке FINExecutor2Plan
	                planLogic.updatePrimaryFinExecutor(object);
	                ////////////////////////////////////////
				}
			}

			objectDAO.save(object);

            ///////////////// 29.08.12 NET-2800
            // При изменении дат выполнения плана дату начала всем исполнителям ставим такую же, как в плане
            if (plan.kind.code == ENPlanWorkKind.YEAR)
            {
                FINExecutor2PlanDAO finExecutor2PlanDAO = new FINExecutor2PlanDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
                FINExecutor2PlanFilter finExecutor2PlanFilter = new FINExecutor2PlanFilter();
                finExecutor2PlanFilter.planRef.code = plan.code;
                int[] finExecutor2PlanArr = finExecutor2PlanDAO.getFilteredCodeArray(finExecutor2PlanFilter, 0, -1);

                for (int i = 0; i < finExecutor2PlanArr.length; i++)
                {
                    FINExecutor2Plan finExecutor2Plan = finExecutor2PlanDAO.getObject(finExecutor2PlanArr[i]);
                    /*
                    Calendar c = Calendar.getInstance();
                    c.setTime(Tools.clearTimeOfDate(finExecutor2Plan.dateStart));
                    c.set(Calendar.MONTH, plan.monthGen - 1);
                    c.set(Calendar.YEAR, plan.yearGen);

                    finExecutor2Plan.dateStart = c.getTime();
                    */
                    finExecutor2Plan.dateStart = plan.dateStart;
                    finExecutor2PlanDAO.save(finExecutor2Plan);
                }
            }
            else if (object.kind.code == ENPlanWorkKind.CURRENT)
            {
                FINExecutor2PlanDAO finExecutor2PlanDAO = new FINExecutor2PlanDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
                FINExecutor2PlanFilter finExecutor2PlanFilter = new FINExecutor2PlanFilter();
                finExecutor2PlanFilter.planRef.code = plan.code;
                int[] finExecutor2PlanArr = finExecutor2PlanDAO.getFilteredCodeArray(finExecutor2PlanFilter, 0, -1);

                if (finExecutor2PlanArr.length == 1)
                {
                    FINExecutor2Plan finExecutor2Plan = finExecutor2PlanDAO.getObject(finExecutor2PlanArr[0]);
                    finExecutor2Plan.dateStart = plan.dateStart;
                    finExecutor2PlanDAO.save(finExecutor2Plan);
                }
            }


            // 27.02.12 NET-1355 Пересчитываем дату окончания и общее время выполнения работ
            planLogic.recalcTotalTime(object.code);

			if (dateChanged) {
				///// NET-4440 При изменении даты плана сохраняем историю ГСМ по этому плану
				planLogic.generatePlanFuelHistory(object.code, object.dateStart);
				/////
			}

		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENPlanWork%} object.", e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}


	/* ENPlanWork. Получить список для просмотра по фильтру */
	@Override
	public ENPlanWorkShortList getScrollableFilteredList(
			ENPlanWorkFilter filterObject, int fromPosition, int quantity) {
		try {
			ENPlanWorkDAO objectDAO = new ENPlanWorkDAO(
					getUserProfile(), getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getScrollableFilteredList(filterObject,
					fromPosition, quantity, null);

		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get list of {%com.ksoe.energynet.valueobject.ENPlanWork%} objects.", e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		}
		finally {
			closeConnection();
		}
	}


    /* ENPlanWork. Получить объект */
	@Override
	public ENPlanWork getObject(int code) {
		try {
			ENPlanWorkDAO objectDAO = new ENPlanWorkDAO(
					getUserProfile(), getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

			return objectDAO.getObject(code);

		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't get {%com.ksoe.energynet.valueobject.ENPlanWork%} object.", e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		}
		finally {
			closeConnection();
		}
	}


	@Override
	public void save(ENPlanWork object) {
		this.save(object, false);
	}

	public void save(ENPlanWork object, boolean isFromWorkOrderByt) {
		this.save(object, isFromWorkOrderByt, false);
	}

	public void save(ENPlanWork object, boolean isFromWorkOrderByt,
			boolean isFromBilling) {
		this.save(object, isFromWorkOrderByt, isFromBilling, false);
	}

	public void save(ENPlanWork object, boolean isFromWorkOrderByt,
			boolean isFromBilling, boolean isOnlyRebinding) {
		this.save(object, isFromWorkOrderByt, isFromBilling, isOnlyRebinding, false);
	}

	public void save(ENPlanWork object, boolean isFromWorkOrderByt,
			boolean isFromBilling, boolean isOnlyRebinding, boolean isForSupplier) {
		try {
			object.setDomain_info(null);

			PlanWorkLogic planLogic = new PlanWorkLogic(
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

			planLogic.save(object, isFromWorkOrderByt, isFromBilling, isOnlyRebinding, isForSupplier);

		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(
					"Can't save {%com.ksoe.energynet.valueobject.ENPlanWork%} object.", e);
		}
		finally {
			closeConnection();
		}
	}



  public void saveInner(ENPlanWork object)
  {
    this.save(object);
  }

  public void generateEsentialByPlan(int planCode){
    try
    {
        //EstimateLogic logic = new EstimateLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
        //logic.createENEstimate(planCode);
        throw new EnergyproSystemException("NOT run  generateEsentialByPlan");
    }
        //catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't generateEsentialByPlan ",e);}
        //catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
        finally                              {closeConnection();}
  }


  public void generateEsentialByPlanItem(int planItemCode){
    try
    {
        EstimateLogic logic = new EstimateLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
        logic.createENEstimateItems(planItemCode, true);
    }
        catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't generateEsentialByPlan ",e);}
        catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
        finally                              {closeConnection();}
  }

  public void cancelEsentialByPlan(int planCode){
    try
    {
        //EstimateLogic logic = new EstimateLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
        //logic.cancelENEstimate(planCode);
        throw new EnergyproSystemException("NOT run  cancelEsentialByPlan");
    }
        //catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't generateEsentialByPlan ",e);}
        //catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
        finally                              {closeConnection();}
  }

  public void cancelEsentialByPlanItem(int planItemCode){
    try
    {
        //EstimateLogic logic = new EstimateLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
        //logic.cancelENEstimateItem(planItemCode);
        throw new EnergyproSystemException("NOT run  cancelEsentialByPlanItem");
    }
        //catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't generateEsentialByPlan ",e);}
        //catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
        finally                              {closeConnection();}
  }


  public String getPlanCodesHistoryUp(int planCode)
  {
   try
    {
        PlanWorkLogic planLogic = new PlanWorkLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

        return planLogic.getCodesHistoryUp(planCode);

    }
   catch (DatasourceConnectException e) {throw new EnergyproSystemException("planLogic.getCodesHistoryUp",e);}
   catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
   finally                              {closeConnection();}
  }

  public String getPlanCodesHistoryDown(int planCode)
  {
   try
    {
        PlanWorkLogic planLogic = new PlanWorkLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

        return planLogic.getCodesHistoryDown(planCode);

    }
   catch (DatasourceConnectException e) {throw new EnergyproSystemException("planLogic.getCodesHistoryDown",e);}
   catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
   finally                              {closeConnection();}
  }

  public int getFilteredCount(ENPlanWork afilterObject, String anCondition) {
        try {
            ENPlanWorkDAO objectDAO = new ENPlanWorkDAO(getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
            return objectDAO.getFilteredCount(afilterObject, anCondition, null);
        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENPlanWork%} objects.",e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

  public void bind2parentPlan(int planCode, int parentPlanCode, int correctionReasonCode){

    try
    {

        ENPlanWorkDAO planDAO = new ENPlanWorkDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

        ENPlanWork parentPlan = planDAO.getObject(parentPlanCode);

        //parentPlan.status.code = ENPlanWorkStatus.LOCKED; ??
        //planDAO.save(parentPlan);
        //ENPlanWork object = planDAO.getObject(planCode);

        /*
        int outCode = this.add(object);
        */
        AuthLogic al = new AuthLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
        if (! al.checkPermission4PlanItems(planCode)){
            throw new EnergyproSystemException("Access denied for addBy... plan code="+planCode);
        }


        ENPlanCorrectHistoryFilter f = new ENPlanCorrectHistoryFilter();
        f.planNewRef.code = parentPlanCode;

        ENPlanCorrectHistoryDAO chDAO = new ENPlanCorrectHistoryDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

        ENPlanCorrectHistoryShortList l = chDAO.getScrollableFilteredList(f,0,-1);

        int pPlan = Integer.MIN_VALUE;
        if (l.totalCount > 0 ){
            pPlan = l.get(0).planRefCode;
        }
        else{
            pPlan = parentPlanCode; //object.planOldRef.code;
        }

        int cReason = Integer.MIN_VALUE;

        if (parentPlan.kind.code == ENPlanWorkKind.CURRENT){
            cReason = correctionReasonCode; //ENPlanCorrectReason.PURCHASES_BINDING; //ENPlanCorrectReason.MOVE_TO_NPW;
        }
        else{
            throw new EnergyproSystemException("error in addByParent : unknoun CorrectionREASOn :)");
        }

        ENPlanCorrectHistory ch = new ENPlanCorrectHistory();

        ch.dateGen = new Date();
        ch.userGen = getUserProfile().userName;
        ch.dateEdit = new Date();
        ch.planRef.code = pPlan;
        ch.reason.code = cReason;
        ch.planOldRef.code = parentPlanCode;
        ch.planNewRef.code = planCode; //outCode;

        chDAO.add(ch);

        //return outCode;
    }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENPlanWork%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}

  }

  // 03.11.11 Перевод плана в статус "Работы завершены" с выкидыванием м-лов, зависших на нем в Транзите, в Опер. запас
  public void finishPlanWork(int planCode) {
    try
    {

        ENPlanWorkDAO objectDAO = new ENPlanWorkDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
        ENPlanWork plan = objectDAO.getObject(planCode);

        // проверка для запрещения перевода плана в "Работи завершені" для определенных типов работ
        if (plan.stateRef.code == ENPlanWorkState.CAPITAL_REPAIR) {

        	AuthLogic al = new AuthLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

            String actionJNDI = "ksoe/energynet/ENPlanWorkController";
            String action = "finishPlanWorkRestricted";
            al.checkPermission(actionJNDI, action);

        }

        if (plan.kind.code != ENPlanWorkKind.CURRENT) {
            throw new EnergyproSystemException("Цей план не є поточним! (Код плана: " + planCode + ")");
        }

        // По идее, закрывать можно все планы, кроме уже закрытых и черновых
        if (plan.status.code == ENPlanWorkStatus.WORKS_FINISHED) {
            throw new EnergyproSystemException("Цей план вже знаходиться в статусі \"Роботи завершені\"! (Код плана: " + planCode + ")");

        }

        boolean isServices = false;
        if (plan.typeRef.code == ENPlanWorkType.SERVICES_FROM_SIDE || plan.typeRef.code == ENPlanWorkType.SERVICES_FROM_SIDE_INVEST)
        {
            isServices = true;
        }

        // NET-1405 Для услуг со стороны можно перевести в статус "Роботи завершені" всегда
        if (!isServices)
        {
            if (plan.status.code == ENPlanWorkStatus.GOOD) {
                throw new EnergyproSystemException("План у статусі \"Чорновий\" заборонено переводити в статус \"Роботи завершені\"! (Код плана: " + planCode + ")");
            }

            /////
            PlanWorkLogic planLogic = new PlanWorkLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
            String planCodesDown = planLogic.getCodesHistoryDown(planCode);
            ENPlanWorkFilter planFilter = new ENPlanWorkFilter();
            planFilter.conditionSQL = "code in (" + planCodesDown + ")";
            ENPlanWorkShortList planList = objectDAO.getScrollableFilteredList(planFilter, 0, -1);
            for (int i = 0; i < planList.totalCount; i++)
            {
                if (planList.get(i).kindCode == ENPlanWorkKind.NPW || planList.get(i).kindCode == ENPlanWorkKind.FACT)
                {
                    if (planList.get(i).statusCode != ENPlanWorkStatus.LOCKED)
                    {
                        throw new EnergyproSystemException("Є незатверджені НПЗ або Завдання-Факти з цього плану. Код = " + planList.get(i).code);
                    }
                }
            }
            /////
        }

        FKOrderLogic fkOrderLogic = new FKOrderLogic(getUserProfile(), getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
        fkOrderLogic.throwFromTransitToOperativeByPlanCode(planCode);

        plan.status.code = ENPlanWorkStatus.WORKS_FINISHED;
        objectDAO.save(plan);
    }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENPlanWork%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
  }

  // 03.11.11 Отмена перевода плана в статус "Работы завершены"
  public void undoFinishPlanWork(int planCode) {
    try
    {
        ENPlanWorkDAO objectDAO = new ENPlanWorkDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
        ENPlanWork plan = objectDAO.getObject(planCode);

        if (plan.kind.code != ENPlanWorkKind.CURRENT) {
            throw new EnergyproSystemException("Цей план не є поточним! (Код плана: " + planCode + ")");
        }

        if (plan.status.code != ENPlanWorkStatus.WORKS_FINISHED) {
            throw new EnergyproSystemException("Цей план НЕ знаходиться в статусі \"Роботи завершені\"! (Код плана: " + planCode + ")");
        }

        FKOrderLogic fkOrderLogic = new FKOrderLogic(getUserProfile(), getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
        fkOrderLogic.undoThrowingFromTransitToOperativeByPlanCode(planCode);

        // Ставим статус "Затверджений"
        plan.status.code = ENPlanWorkStatus.LOCKED;
        objectDAO.save(plan);

        /* 18.11.2011 +++  естимайтам возвращаем предыдущий статус */
        /* 22.11.2011 +++ наверное не понравилась формулировка
        ENEstimateItemDAO estDAO = new ENEstimateItemDAO(getUserProfile(), getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
        ENEstimateItemFilter eFilter = new ENEstimateItemFilter();
        eFilter.kindRef.code = ENEstimateItemKind.MATERIALS;
        eFilter.planRef.code = planCode;

        int[] eArr = estDAO.getFilteredCodeArray(eFilter, 0, -1);
        for (int y = 0; y < eArr.length; y++) {
            ENEstimateItem est = new ENEstimateItem();
            ENEstimateItem oldEst = new ENEstimateItem();

            est = estDAO.getObject(eArr[y]);
            oldEst = estDAO.getObject(eArr[y]);

            est.statusRef.code = oldEst.oldStatusRef.code;
            est.oldStatusRef.code = oldEst.statusRef.code;
            estDAO.save(est);
        } */

    }
    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't remove {%com.ksoe.energynet.valueobject.ENPlanWork%} object.",e);}
    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
    finally                              {closeConnection();}
  }


    public int addAutoBySiz(ENPlanWork object, UserProfile up) {
        Connection conn = null;
        try {

            ///// 29.11.2013 Для ТМЦ запрещаем создание планов // 11.01.14 Спецодежду уже нужно заказывать!!!
            //if (object.yearGen != 2013)
            //{
            //    throw new EnergyproSystemException("\n\nСкладання планів на " + object.yearGen + " р. заборонено керівництвом компанії!");
            //}
            /////


            conn = getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE);

            ENElementDAO eDao = new ENElementDAO(up, conn);
            ENElement e = eDao.getObject(object.elementRef.code);

            PlanWorkLogic pl = new PlanWorkLogic(conn, up);

            // добавлять можно ТОЛЬКО ручные !!!
            object.sourceRef.code = ENPlanWorkSource.MANUAL;

            pl.checkPeriod(object, true);

            object.dateEdit = new Date();
            object.userGen = up.userName;

            ENPlanWorkDAO objectDAO = new ENPlanWorkDAO(up, getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            object.renRef.code = e.renRef.code;

            // теперь еще заежжает Исполнитель из ФинКол
            // по идее не всегда он есть ...
            if (object.finExecutor != null) {
                if (object.finExecutor.finCode > Integer.MIN_VALUE || object.finExecutor.axOrgId != null) {
                    FINExecutor f = new FINExecutor();
                    f.finCode = object.finExecutor.finCode;
                    f.name = object.finExecutor.name;
                    f.finCehCode = object.finExecutor.finCehCode;
                    f.finCehName = object.finExecutor.finCehName;
                    f.finKindCode = object.finExecutor.finKindCode;
                    f.finKindName = object.finExecutor.finKindName;
                    f.finTypeCode = object.finExecutor.finTypeCode;
                    f.finTypeName = object.finExecutor.finTypeName;
                    ///// MDAX-441
                    f.axOrgId = object.finExecutor.axOrgId;
                    f.axParentOrgId = object.finExecutor.axParentOrgId;
                    f.axParentOrgName = object.finExecutor.axParentOrgName;
                    f.axOrgTypeId = object.finExecutor.axOrgTypeId;
                    f.axOrgTypeName = object.finExecutor.axOrgTypeName;
                    /////
                    f.code = new FINExecutorDAO(conn, up).add(f);
                    object.finExecutor.code = f.code;
                }
            }

            // проверка уникальности ..
            pl.checkUnicPlan(object);

            object.yearOriginal = Integer.MIN_VALUE;
            object.monthOriginal = Integer.MIN_VALUE;

            int planCode = objectDAO.add(object);

            // работа для СиЗ ...
            if (e.typeRef.code == ENElementType.SIZ) {

                ENPlanWorkItem plItem = new ENPlanWorkItem();
                PlanWorkLogic plg = new PlanWorkLogic(conn, up);
                SiZLogic siZLogic = new SiZLogic(conn, up);

                ENSizObjectDAO sizDao = new ENSizObjectDAO(conn, up);
                TKTechCardDAO kDao = new TKTechCardDAO(conn, up);

                // планы СиЗ (для работников) - проверка штатного...
                int sizObjectCode = sizDao.getSizObjectCode(object.elementRef.code);
                String invNumber = sizDao.getInvNumberSizObject(object.elementRef.code);
                boolean checkInShtatWorker = pl.checkInShtatWorker(invNumber, true, sizObjectCode);

                if (checkInShtatWorker) {
                    objectDAO.remove(object.code);
                    return Integer.MIN_VALUE;
                }

                int sizCode = sizDao.getSizCode(object.elementRef.code);
                if (sizCode == Integer.MIN_VALUE) {
                    System.out.println("На робітнику відсутній норматив ЗіЗ ... element.code = " + object.elementRef.code);
                    objectDAO.remove(object.code);
                    return Integer.MIN_VALUE;
                }

                int tkCode = siZLogic.getKartaBySiz(sizCode);
                if (tkCode == Integer.MIN_VALUE) {
                    System.out.println("Відсутній норматив ЗіЗ ... " + sizCode);
                    objectDAO.remove(object.code);
                    return Integer.MIN_VALUE;
                }

                plItem.planRef.code = planCode;
                plItem.countGen = new BigDecimal(1.0);
                plItem.kartaRef.code = tkCode;

                plItem.dateEdit = new Date();
                plItem.userGen = up.userName;

                plg.addPlanWorkItem(plItem, object);
            }

            return planCode;

        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException("Can't generateEsentialByPlan ",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException e) {
            }
        }
    }


    public void recalcTotalTime(int planCode) {
        try {
            PlanWorkLogic planLogic = new PlanWorkLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
            planLogic.recalcTotalTime(planCode);
        }
        catch (DatasourceConnectException e) {throw new EnergyproSystemException("planLogic.recalcTotalTime",e);}
        catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
        finally                              {closeConnection();}
    }


    public int addAutoPlanByTires(ENPlanWork object, UserProfile up) {
        Connection conn = null;
        try {

            conn = getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE);

            ENElementDAO eDao = new ENElementDAO(up, conn);
            ENElement e = eDao.getObject(object.elementRef.code);

            PlanWorkLogic pl = new PlanWorkLogic(conn, up);

            object.sourceRef.code = ENPlanWorkSource.MANUAL;

            pl.checkPeriod(object, true);

            object.dateEdit = new Date();
            object.userGen = up.userName;

            ENPlanWorkDAO objectDAO = new ENPlanWorkDAO(up, getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            if (object.renRef.code == Integer.MIN_VALUE) {
                object.renRef.code = e.renRef.code;
            }

            // теперь еще заежжает Исполнитель из ФинКол
            // по идее не всегда он есть ...
            if (object.finExecutor != null) {
                if (object.finExecutor.finCode > Integer.MIN_VALUE || object.finExecutor.axOrgId != null) {
                    FINExecutor f = new FINExecutor();
                    f.finCode = object.finExecutor.finCode;
                    f.name = object.finExecutor.name;
                    f.finCehCode = object.finExecutor.finCehCode;
                    f.finCehName = object.finExecutor.finCehName;
                    f.finKindCode = object.finExecutor.finKindCode;
                    f.finKindName = object.finExecutor.finKindName;
                    f.finTypeCode = object.finExecutor.finTypeCode;
                    f.finTypeName = object.finExecutor.finTypeName;
                    ///// MDAX-441
                    f.axOrgId = object.finExecutor.axOrgId;
                    f.axParentOrgId = object.finExecutor.axParentOrgId;
                    f.axParentOrgName = object.finExecutor.axParentOrgName;
                    f.axOrgTypeId = object.finExecutor.axOrgTypeId;
                    f.axOrgTypeName = object.finExecutor.axOrgTypeName;
                    /////
                    f.code = new FINExecutorDAO(conn, up).add(f);
                    object.finExecutor.code = f.code;
                }
            }

            // проверка уникальности .. пока уберу для проверки !!!!
            //pl.checkUnicPlan(object);

            object.yearOriginal = Integer.MIN_VALUE;
            object.monthOriginal = Integer.MIN_VALUE;

            int planCode = objectDAO.add(object);

            return planCode;

        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException("Can't generateEsentialByPlan ",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException e) {
            }
        }
    }


    /**
    *  20.03.2012 +++ add с добавлением связки "план - договор на ТУ"
    */

    public int addPlanByTechConditions(ENPlanWork object,
            int techCondServicesCode) {
        Connection conn = null;
        try {

            conn = getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE);
            UserProfile up = getUserProfile();


            TechConditionsLogic techConditionsLogic = new TechConditionsLogic(conn, up);
            techConditionsLogic.checkActIncone(techCondServicesCode);


            ////////////////////////////////////////////////////////////////////////////////////////////////
            // 25.05.2017 Если договор уже проведен, то планы на него даем создавать только юзеру energynet!
            ENServicesObjectDAO soDAO = new ENServicesObjectDAO(conn, up);
            ENServicesObjectFilter soFilter = new ENServicesObjectFilter();
            soFilter.conditionSQL = " code = (" +
            		" select so2tc.servicesobjectrefcode " +
            		" from enservicesobject2techcondtnsservices so2tc " +
            		" where so2tc.techcondservrefcode = " + techCondServicesCode + ")";
            int[] soArr = soDAO.getFilteredCodeArrayNOSEGR(soFilter, 0, -1);

            if (soArr.length != 1) {
            	throw new SystemException("\n\nПомилка під час пошуку договору! [techCondServicesCode = " + techCondServicesCode + "]!");
            }

            ENServicesObject sObject = soDAO.getObjectNoSegr(soArr[0]);

            if (sObject == null) {
            	throw new SystemException("\n\nПомилка під час пошуку договору! [techCondServicesCode = " + techCondServicesCode + "]!");
            }

            if (sObject.statusRef.code == ENServicesObjectStatus.CLOSED ||
            	sObject.statusRef.code == ENServicesObjectStatus.CLOSED_BY_BUH_SPRAV) {

            	if (! up.userName.toLowerCase().equals("energynet")) {
            		throw new SystemException("\n\nДоговір вже проведений!\n" +
            				"Для складання нових планів за цим договором спочатку відмініть проведення!");
            	}
            }
            ////////////////////////////////////////////////////////////////////////////////////////////////

            int planCode = this.add(object, true);

            ENTechCond2PlanWorkDAO ty2plDAO = new ENTechCond2PlanWorkDAO(conn, up);
            ENTechConditionsServicesDAO techCondDao = new ENTechConditionsServicesDAO(conn, up);
            ENTechCond2PlanWorkFilter tc2planFilter = new ENTechCond2PlanWorkFilter();
            tc2planFilter.planRef.code = object.code;
            int[] tc2plArr = ty2plDAO.getFilteredCodeArray(tc2planFilter,0,-1);
            if (tc2plArr.length > 0) {
                ENTechCond2PlanWork tc2pl = ty2plDAO.getObject(tc2plArr[0]);
                throw new SystemException("Цей план вже використовується у договорі - " +
                        soDAO.getObjectByTechConditionsServices(techCondDao.getObject(tc2pl.techConServicesRef.code)).contractNumberServices);
            }

            /* связка "план - договор на ТУ" */
            ENTechCond2PlanWork ty2pl = new ENTechCond2PlanWork();
            ty2pl.code = Integer.MIN_VALUE;
            ty2pl.planRef.code = planCode;
            ty2pl.techConServicesRef.code = techCondServicesCode;
            ty2plDAO.add(ty2pl);


            PlanWorkLogic planWorkLogic = new PlanWorkLogic(conn, up);

            if((object.kind.code == ENPlanWorkKind.CURRENT)  //Місячний
            		 && (object.typeRef.code == ENPlanWorkType.SERVICES_FROM_SIDE)){ //Послуги зі сторони
            	planWorkLogic.addFinDocIdFromENPlanWork(sObject.code, sObject.element.code, techCondServicesCode, object.servicesFSideFinId);
            }

            return planCode;

        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException("Can't addPlanByTechConditions ",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException e) {
            }
        }
    }


    /* ENPlanWork. Добавить план по услугам на сторону а так же при наличии присоединения будет заполнен  techCondServicesCode */
    public int addPlanByTechConditionsAndServicesFromSide(ENPlanWork object, int techCondServicesCode , int servicesFromSideCode ) {
        Connection conn = null;
        try {

            conn = getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE);
            UserProfile up = getUserProfile();


            TechConditionsLogic techConditionsLogic = new TechConditionsLogic(conn, up);
            techConditionsLogic.checkActIncone(techCondServicesCode);


            int planCode = Integer.MIN_VALUE;

            ENServicesFromSideObjectDAO sideDAO = new ENServicesFromSideObjectDAO(conn,up);
            ENServicesFromSideObjectFilter sideFil = new ENServicesFromSideObjectFilter();
            sideFil.code=servicesFromSideCode;

            ENServicesFromSideObjectShortList sideList = sideDAO.getScrollableFilteredList(sideFil, 0, -1);
            if(sideList.totalCount>0){
            	object.servicesFSideCnNum = sideList.get(0).contractNumber;
            	object.servicesFSideFinId = sideList.get(0).finDocID;
            	object.partnerCode = sideList.get(0).partnerCode;
            	object.finDocCode = sideList.get(0).finDocCode;
            }




           if (techCondServicesCode!= Integer.MIN_VALUE) {
            ////////////////////////////////////////////////////////////////////////////////////////////////
            // 25.05.2017 Если договор уже проведен, то планы на него даем создавать только юзеру energynet!
            ENServicesObjectDAO soDAO = new ENServicesObjectDAO(conn, up);
            ENServicesObjectFilter soFilter = new ENServicesObjectFilter();
            soFilter.conditionSQL = " code = (" +
            		" select so2tc.servicesobjectrefcode " +
            		" from enservicesobject2techcondtnsservices so2tc " +
            		" where so2tc.techcondservrefcode = " + techCondServicesCode + ")";
            int[] soArr = soDAO.getFilteredCodeArrayNOSEGR(soFilter, 0, -1);

            if (soArr.length != 1) {
            	throw new SystemException("\n\nПомилка під час пошуку договору! [techCondServicesCode = " + techCondServicesCode + "]!");
            }

            ENServicesObject sObject = soDAO.getObjectNoSegr(soArr[0]);

            if (sObject == null) {
            	throw new SystemException("\n\nПомилка під час пошуку договору! [techCondServicesCode = " + techCondServicesCode + "]!");
            }

            if (sObject.statusRef.code == ENServicesObjectStatus.CLOSED ||
            	sObject.statusRef.code == ENServicesObjectStatus.CLOSED_BY_BUH_SPRAV) {

            	if (! up.userName.toLowerCase().equals("energynet")) {
            		throw new SystemException("\n\nДоговір вже проведений!\n" +
            				"Для складання нових планів за цим договором спочатку відмініть проведення!");
            	}
            }
            ////////////////////////////////////////////////////////////////////////////////////////////////

            planCode = this.add(object, true);

               ENTechCond2PlanWorkDAO ty2plDAO = new ENTechCond2PlanWorkDAO(conn, up);
               ENTechConditionsServicesDAO techCondDao = new ENTechConditionsServicesDAO(conn, up);
               ENTechCond2PlanWorkFilter tc2planFilter = new ENTechCond2PlanWorkFilter();
               tc2planFilter.planRef.code = object.code;
               int[] tc2plArr = ty2plDAO.getFilteredCodeArray(tc2planFilter,0,-1);
               if (tc2plArr.length > 0) {
                   ENTechCond2PlanWork tc2pl = ty2plDAO.getObject(tc2plArr[0]);
                   throw new SystemException("Цей план вже використовується у договорі - " +
                           soDAO.getObjectByTechConditionsServices(techCondDao.getObject(tc2pl.techConServicesRef.code)).contractNumberServices);
               }

            /* связка "план - договор на ТУ" */
            ENTechCond2PlanWork ty2pl = new ENTechCond2PlanWork();
            ty2pl.code = Integer.MIN_VALUE;
            ty2pl.planRef.code = planCode;
            ty2pl.techConServicesRef.code = techCondServicesCode;
            ty2plDAO.add(ty2pl);


            ENServFromSide2PlanWorkDAO serv2plDAO = new ENServFromSide2PlanWorkDAO(conn, up);
     	    ENServFromSide2PlanWork serv2pl = new ENServFromSide2PlanWork();
     	    serv2pl.code = Integer.MIN_VALUE;
     	    serv2pl.servFromSideRef.code = servicesFromSideCode;
     	    serv2pl.planRef.code = planCode;
     	    serv2plDAO.add(serv2pl);


            PlanWorkLogic planWorkLogic = new PlanWorkLogic(conn, up);

            if((object.kind.code == ENPlanWorkKind.CURRENT)  //Місячний
            		 && (object.typeRef.code == ENPlanWorkType.SERVICES_FROM_SIDE)){ //Послуги зі сторони
            	planWorkLogic.addFinDocIdFromENPlanWork(sObject.code, sObject.element.code, techCondServicesCode, object.servicesFSideFinId);
            }

        }

           if (techCondServicesCode == Integer.MIN_VALUE ){

        	   planCode = this.add(object, false);

        	   ENServFromSide2PlanWorkDAO serv2plDAO = new ENServFromSide2PlanWorkDAO(conn, up);
        	   ENServFromSide2PlanWork serv2pl = new ENServFromSide2PlanWork();
        	   serv2pl.code = Integer.MIN_VALUE;
        	   serv2pl.servFromSideRef.code = servicesFromSideCode;
        	   serv2pl.planRef.code = planCode;
        	   serv2plDAO.add(serv2pl);
           }


            return planCode;

        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException("Can't addPlanByTechConditions ",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException e) {
            }
        }
    }



    /**
     *
     * @param object
     * @param techCondServicesCode
     */
	public void addPlan2TechConditions(ENPlanWork object, int techCondServicesCode) {

		Connection conn = null;

		try {

            conn = getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE);
            UserProfile up = getUserProfile();

            TechConditionsLogic techConditionsLogic = new TechConditionsLogic(conn, up);
            techConditionsLogic.checkActIncone(techCondServicesCode);


            /* связка "план - договор на ТУ" */
            ENTechCond2PlanWorkDAO ty2plDAO = new ENTechCond2PlanWorkDAO(conn, up);
            ENTechConditionsServicesDAO techCondDao = new ENTechConditionsServicesDAO(conn, up);
            ServicesLogic logic = new ServicesLogic(conn, up);
            ENServicesObjectDAO soDao = new ENServicesObjectDAO(conn, up);

            ENTechConditionsServices techCond = techCondDao.getObject(techCondServicesCode);
            ENServicesObject so = soDao.getObjectByTechConditionsServices(techCond);

            ENTechCond2PlanWorkFilter tc2planFilter = new ENTechCond2PlanWorkFilter();
            tc2planFilter.planRef.code = object.code;
            int[] tc2plArr = ty2plDAO.getFilteredCodeArray(tc2planFilter,0,-1);
            if (tc2plArr.length > 0) {
                ENTechCond2PlanWork tc2pl = ty2plDAO.getObject(tc2plArr[0]);
                throw new SystemException("Цей план вже використовується у договорі - " +
                        soDao.getObjectByTechConditionsServices(techCondDao.getObject(tc2pl.techConServicesRef.code)).contractNumberServices);
            }

            ENTechCond2PlanWork ty2pl = new ENTechCond2PlanWork();
            ty2pl.code = Integer.MIN_VALUE;
            ty2pl.planRef.code = object.code;
            ty2pl.techConServicesRef.code = techCondServicesCode;
            ty2plDAO.add(ty2pl);

            logic.calculateENSOValuesTermsIfNeeded(so);


        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException("Can't addPlan2TechConditions ",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException e) {
            }
        }
    }



	public void removePlanFromTechConditions(ENPlanWork object, int techCondServicesCode) {

		Connection conn = null;

		try {

             conn = getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE);
             UserProfile up = getUserProfile();

             ENPlanWorkDAO objectDAO = new ENPlanWorkDAO(conn,up);
             ENActDAO actDAO = new ENActDAO(conn,up);
             ENTechCond2PlanWorkDAO ty2plDAO = new ENTechCond2PlanWorkDAO(conn, up);
             ENTechConditionsServicesDAO techCondDao = new ENTechConditionsServicesDAO(conn, up);
             ServicesLogic logic = new ServicesLogic(conn, up);
             ENServicesObjectDAO soDao = new ENServicesObjectDAO(conn, up);


             TechConditionsLogic techConditionsLogic = new TechConditionsLogic(conn, up);
             techConditionsLogic.checkActIncone(techCondServicesCode);


             ENTechConditionsServices techCond = techCondDao.getObject(techCondServicesCode);
             ENServicesObject so = soDao.getObjectByTechConditionsServices(techCond);

             if ((object.kind.code != ENPlanWorkKind.CURRENT &&
                     object.kind.code != ENPlanWorkKind.NPW &&
                     object.kind.code != ENPlanWorkKind.FACT)
                     || (object.stateRef.code == ENPlanWorkState.WORK_IN_OUT)) {
                 throw new SystemException("Для цього плану ця функція не використовується!");
             }

             // проверим наличие проведенных актов по планам
             ENActFilter actFilter = new ENActFilter();
             actFilter.conditionSQL = " code in (select a.code from enact a where \n" +
                     "a.statusrefcode = 3 and \n" +
                     "a.code in (\n" +
                     "select a2pw.actrefcode from enact2enplanwork a2pw\n" +
                     "where a2pw.plancode in ((select pch2.plannewrefcode\n" +
                     "from enplancorrecthistory pch2\n" +
                     "where pch2.planrefcode in\n" +
                     "(select pch.planrefcode from enplancorrecthistory pch\n" +
                     "where pch.reasoncode in (3,4,5,6,7,8)\n" +
                     "and (pch.planoldrefcode = " + object.code + "\n" +
                     "or pch.plannewrefcode = " + object.code + "))\n" +
                     "and pch2.reasoncode in (3,4,5,6,7,8)\n" +
                     "))))";
              int[] actArr = actDAO.getFilteredCodeArray(actFilter,0,-1);
              if (actArr.length > 0) {
                  throw new SystemException("Для обраного плану вже є проведений акт! Видалення з договору заборонено!");
              }

              ENPlanWorkFilter objectFilter = new ENPlanWorkFilter();
              objectFilter.conditionSQL = " code in (select pch2.plannewrefcode\n" +
                      "from enplancorrecthistory pch2\n" +
                      "where pch2.planrefcode in\n" +
                      "(select pch.planrefcode from enplancorrecthistory pch\n" +
                      "where pch.reasoncode in (3,4,5,6,7,8)\n" +
                      "and (pch.planoldrefcode = " + object.code + "\n" +
                      "or pch.plannewrefcode = " + object.code + "))\n" +
                      "and pch2.reasoncode in (3,4,5,6,7,8))";
              int[] planArr = objectDAO.getFilteredCodeArray(objectFilter,0,-1);

              for (int i=0;i<planArr.length;i++) {
                  ENPlanWork plan = objectDAO.getObject(planArr[i]);
                  plan.commentGen = "";
                  plan.priConnectionNumber = "";
                  plan.dateEndPriConnection = null;
                  objectDAO.save(plan);
              }

             /* удаление связки "план - договор на ТУ" */
            ENTechCond2PlanWorkFilter t2pFilter = new ENTechCond2PlanWorkFilter();
            t2pFilter.conditionSQL = " planrefcode in (select pch2.plannewrefcode\n" +
                     "from enplancorrecthistory pch2\n" +
                     "where pch2.planrefcode in\n" +
                     "(select pch.planrefcode from enplancorrecthistory pch\n" +
                     "where pch.reasoncode in (3,4,5,6,7,8)\n" +
                     "and (pch.planoldrefcode = " + object.code + "\n" +
                     "or pch.plannewrefcode = " + object.code + "))\n" +
                     "and pch2.reasoncode in (3,4,5,6,7,8)) and " +
                    " techconservicesrefcode = " + techCondServicesCode;

            int[] t2pArr = ty2plDAO.getFilteredCodeArray(t2pFilter,0,-1);
             for (int z=0;z<planArr.length;z++) {
                 ty2plDAO.remove(t2pArr[z]);
             }

             logic.calculateENSOValuesTermsIfNeeded(so);


         } catch (DatasourceConnectException e) {
             throw new EnergyproSystemException("Can't removePlanFromTechConditions ",
                     e);
         } catch (PersistenceException e) {
             throw new EnergyproSystemException(e.getMessage(), e);
         } finally {
             try {
                 if (conn != null && !conn.isClosed()) {
                     conn.close();
                     conn = null;
                 }
             } catch (SQLException e) {
             }
         }
     }


    /* ENPlanWork. Список планов для договоров на ТУ */
	public ENPlanWorkShortList getTechConditionsPlanList(
            ENPlanWorkFilter filterObject, int fromPosition, int quantity) {
        try {
            ENPlanWorkDAO objectDAO = new ENPlanWorkDAO(getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
            return objectDAO.getTechConditionsPlanList(filterObject, fromPosition, quantity);
        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't get list of {%com.ksoe.energynet.valueobject.ENPlanWork%} objects.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    /**
    *  25.04.2017 +++ add с добавлением связки "план - договор на услуги на сторону"
    */

    /**
     * @param object
     * @param servicesObjectCode
     * @return
     */
    public int addPlanByShiftLinesServices(ENPlanWork object,
            int servicesObjectCode) {
        Connection conn = null;
        try {

            conn = getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE);
            UserProfile up = getUserProfile();

            int planCode = this.add(object, true);

            /* связка "план - договор на услуги на сторону" */
            ENServices2PlanDAO s2pDAO = new ENServices2PlanDAO(conn, up);
        	ENServices2Plan s2p = new ENServices2Plan();
        	s2p.code = Integer.MIN_VALUE;
        	s2p.planRef.code = planCode;
        	s2p.servicesObjectRef.code = servicesObjectCode;
        	s2pDAO.add(s2p);

            return planCode;

        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException("Can't addPlanByShiftLinesServices ",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            try {
                if (conn != null && !conn.isClosed()) {
                    conn.close();
                    conn = null;
                }
            } catch (SQLException e) {
            }
        }
    }


	public void addPlan2ShiftLineServices(ENPlanWork object, int servicesObjectCode) {
		Connection conn = null;
		try {

			conn = getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE);
			UserProfile up = getUserProfile();

			/* связка "план - договор на услуги на сторону" */
			ENServices2PlanDAO s2pDAO = new ENServices2PlanDAO(conn, up);
			ENServices2Plan s2p = new ENServices2Plan();
			s2p.code = Integer.MIN_VALUE;
			s2p.planRef.code = object.code;
			s2p.servicesObjectRef.code = servicesObjectCode;
			ENServices2PlanFilter planfilter = new ENServices2PlanFilter();
			planfilter.planRef.code = object.code;
			ENServices2PlanShortList s2pList = s2pDAO.getScrollableFilteredList(planfilter, 0, -1);
			if (s2pList.totalCount > 0) {
				throw new SystemException("Для обраного Плана вже є прив'язаний Договір № "
						+ s2pList.get(0).servicesObjectRefContractNumberServices + " !");
			}
			s2pDAO.add(s2p);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException("Can't addPlan2ShiftLineServices ", e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
			}
		}
	}

	public void removePlan2Rent(ENPlanWork object, int servicesObjectCode) {
		Connection conn = null;
		try {

			conn = getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE);
			UserProfile up = getUserProfile();

			/* связка "план - договор на услуги на сторону" */
			ENServices2PlanDAO s2pDAO = new ENServices2PlanDAO(conn, up);
			ENServices2PlanFilter planfilter = new ENServices2PlanFilter();
			planfilter.planRef.code = object.code;
			planfilter.servicesObjectRef.code = servicesObjectCode;
			int[] actArr = s2pDAO.getFilteredCodeArray(planfilter, 0, -1);
			if (actArr.length == 0) {
				throw new SystemException("Для обраного Плана Видалення заборонено!");
			}
			s2pDAO.remove(actArr[0]);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException("Can't removePlan2Rent ", e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} finally {
			try {
				if (conn != null && !conn.isClosed()) {
					conn.close();
					conn = null;
				}
			} catch (SQLException e) {
			}
		}
	}

    public int copyPlanForTemplate(int planCode, ENPlanWork newPlan)
    {
        Connection enConn = null;

        try {
            enConn = getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE);

            AuthLogic l = new AuthLogic(enConn, getUserProfile());
            if ( ! l.checkPermission4PlanItems(planCode) ){
                throw new EnergyproSystemException("Access denied for method addBy... from method ENPlanWork.copyPlanForTemplate()");
            }

            //PlanWorkLogic planLogic = new PlanWorkLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
            PlanWorkLogic planLogic = new PlanWorkLogic(enConn, getUserProfile());
            return planLogic.copyPlanForTemplate(planCode, newPlan);
        }
        catch (DatasourceConnectException e) {throw new EnergyproSystemException("planLogic.copyPlanForTemplate", e);}
        catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(), e);}
        finally
        {
            //closeConnection();
            try {
                if (enConn != null && !enConn.isClosed()) {
                    enConn.close();
                    enConn = null;
                }
            } catch (SQLException e) {
            }
        }
    }

    public void changeInvestDescription(int planCode, String description) {
        try {

            ENPlanWorkDAO objectDAO = new ENPlanWorkDAO(getUserProfile(), getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
            ENPlanWork object = objectDAO.getObject(planCode);

            object.investWorksDescription = description;
            objectDAO.save(object);

        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't save {%com.ksoe.energynet.valueobject.ENWorkOrder%} object.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

    public void changeInvestAmount(int planCode, BigDecimal investWorksAmount) {
        try {

            ENPlanWorkDAO objectDAO = new ENPlanWorkDAO(getUserProfile(), getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
            ENPlanWork object = objectDAO.getObject(planCode);

            object.investWorksAmount = investWorksAmount;
            objectDAO.save(object);

        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't save {%com.ksoe.energynet.valueobject.ENWorkOrder%} object.",
                    e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }

     public void changeInvestStartDate(int planCode, Date investWorkDateStart) {
         try {

             ENPlanWorkDAO objectDAO = new ENPlanWorkDAO(getUserProfile(), getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
             ENPlanWork object = objectDAO.getObject(planCode);

             object.investDateStartWork = investWorkDateStart;
             objectDAO.save(object);

         } catch (DatasourceConnectException e) {
             throw new EnergyproSystemException(
                     "Can't save {%com.ksoe.energynet.valueobject.ENPlanWork%} object.",
                     e);
         } catch (PersistenceException e) {
             throw new EnergyproSystemException(e.getMessage(), e);
         } finally {
             closeConnection();
         }
     }

     public void changeInvestMeasurement(int planCode, int investMeasurementCode) {
         try {

             ENPlanWorkDAO objectDAO = new ENPlanWorkDAO(getUserProfile(), getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
             ENPlanWork object = objectDAO.getObject(planCode);

             object.investWorkMeasCode = investMeasurementCode;
             objectDAO.save(object);

         } catch (DatasourceConnectException e) {
             throw new EnergyproSystemException(
                     "Can't save {%com.ksoe.energynet.valueobject.ENPlanWork%} object.",
                     e);
         } catch (PersistenceException e) {
             throw new EnergyproSystemException(e.getMessage(), e);
         } finally {
             closeConnection();
         }
     }


    public BufetOrderShortList getBufetOrderList(Date aDate, int aType)
    {

        BufetOrderShortList result = new BufetOrderShortList();
        result.list = new Vector<>();
        BufetOrderShort anObject;

        try
        {

        String     selectStr;

        Connection connection = getConnection(AuthorizationJNDINames.BUFET_DATASOURCE);


        PreparedStatement statement = null;
        ResultSet  set = null;

        String aTypeStr="";

        if (aType==1) //нал
        {
            aTypeStr=" and (nal_beznal is null and id_to_sklad<>10000) ";
        }
        else
        {
            if (aType==2) //нал
            {
                aTypeStr=" and id_to_sklad=10000 ";
            }
            else
            {
                //aTypeStr=" and (nal_beznal=1 and id_to_sklad<>10000) ";
            	// пока будут отображаться все накладные, а пользователь который
            	// заводит акты списания буфета будет выбирать безналичные накладные на свое усмотрение
            	aTypeStr=" and (id_to_sklad<>10000) ";
            }
        }

        selectStr=" select nom_nak , data, sum(summa) "+
            " from jur_nak  where data="+Tools.convertDateToSQLString(aDate)+
            " and tip=2 "+
            " and proved=1 "+
            aTypeStr+
            "  group by nom_nak,data ";


        statement = connection.prepareStatement(selectStr);

        set = statement.executeQuery();
        int i;

        for(i = 0;set.next();i++)
        {
            anObject = new BufetOrderShort();
            anObject.numberDoc=set.getString(1);
            anObject.dateGen=set.getDate(2);
            anObject.sum=set.getBigDecimal(3).setScale(2, BigDecimal.ROUND_HALF_UP);
            result.list.add(anObject);
        }

        set.close();
        statement.close();




     result.setTotalCount(i);
    return result;
        }
    catch (SQLException e) {
    return null;
    } catch (DatasourceConnectException e) {
		throw new SystemException(e);
	}

    }


    public BufetOrderShortList getBufetOrderMaterialList(Date aDate)
    {

        BufetOrderShortList result = new BufetOrderShortList();
        result.list = new Vector<>();
        BufetOrderShort anObject;

        try
        {

        String     selectStr;

        Connection connection = getConnection(AuthorizationJNDINames.BUFET_DATASOURCE);


        PreparedStatement statement = null;
        ResultSet  set = null;


        selectStr=" select o.nom_dok as nom_nak , "+
        		" cast(na_data_tim as date) as data, sum(kolvo_prod*sena_prod_in) as summa "+
        		" from banket o,b_menu m "+
        		" where o.nom_dok=m.id_menu "+
        		" and cast(o.na_data_tim as date) " +
        		" between cast("+Tools.convertDateToSQLString(aDate)+" as date) "+
        		" and (cast("+Tools.convertDateToSQLString(aDate)+" as date)+6) "+
        		" group by o.nom_dok,na_data_tim "
        ;

        statement = connection.prepareStatement(selectStr);

        set = statement.executeQuery();
        int i;

        for(i = 0;set.next();i++)
        {
            anObject = new BufetOrderShort();
            anObject.numberDoc=set.getString(1);
            anObject.dateGen=set.getDate(2);
            anObject.sum=set.getBigDecimal(3).setScale(2, BigDecimal.ROUND_HALF_UP);
            result.list.add(anObject);
        }

        set.close();
        statement.close();




     result.setTotalCount(i);
    return result;
        }
    catch (SQLException e) {
    return null;
    } catch (DatasourceConnectException e) {
		throw new SystemException(e);
	}

    }


    public BufetOrderShortList getBufetPlanMenuOrderList(Date aDate, int aType)
    {

        BufetOrderShortList result = new BufetOrderShortList();
        result.list = new Vector<>();
        BufetOrderShort anObject;

        try
        {

        String     selectStr;

        Connection connection = getConnection(AuthorizationJNDINames.BUFET_DATASOURCE);


        PreparedStatement statement = null;
        ResultSet  set = null;

        selectStr=" select nom_nak , data, sum(summa) "+
            " from jur_nak  where data="+Tools.convertDateToSQLString(aDate)+
            " and tip=4 "+
            " and proved=1 "+
            "  group by nom_nak,data ";


        statement = connection.prepareStatement(selectStr);

        set = statement.executeQuery();
        int i;

        for(i = 0;set.next();i++)
        {
            anObject = new BufetOrderShort();
            anObject.numberDoc=set.getString(1);
            anObject.dateGen=set.getDate(2);
            anObject.sum=set.getBigDecimal(3).setScale(2, BigDecimal.ROUND_HALF_UP);
            result.list.add(anObject);
        }

        set.close();
        statement.close();




     result.setTotalCount(i);
    return result;
        }
    catch (SQLException e) {
    return null;
    } catch (DatasourceConnectException e) {
		throw new SystemException(e);
	}

    }


	public void linkMaterials(int aENPlanWorkCode) {

		try {
			ENPlanWorkDAO planDAO = new ENPlanWorkDAO(
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
			ENPlanWork plan = new ENPlanWork();

			plan = planDAO.getObject(aENPlanWorkCode);

			ENPlanWorkItemDAO piDAO = new ENPlanWorkItemDAO(
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

			ENPlanWorkItemShortList piList = new ENPlanWorkItemShortList();
			ENPlanWorkItemFilter piFilterList = new ENPlanWorkItemFilter();
			piFilterList.planRef.code = aENPlanWorkCode;
			piFilterList.conditionSQL = " enplanworkitem.countgen>0";
			piList = piDAO.getScrollableFilteredList(piFilterList, 0, 1);


			if (piList.totalCount == 0) {
				throw new EnergyproSystemException(
						"На плані відсутня робота...");
			}

			ENEstimateItemDAO eiDAO = new ENEstimateItemDAO(
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

			ENEstimateItemShortList eiList = new ENEstimateItemShortList();
			ENEstimateItemFilter eiFilterList = new ENEstimateItemFilter();
			eiFilterList.planRef.code = aENPlanWorkCode;
			eiFilterList.conditionSQL = " enestimateitem.materialrefcode in (500008913) and enestimateitem.countfact > 0 ";

			eiList = eiDAO.getScrollableFilteredList(eiFilterList, 0, -1);

			if (eiList.totalCount > 0) {
				ENWorkOrderDAO woDAO = new ENWorkOrderDAO(
						getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
				ENWorkOrderShortList woList = new ENWorkOrderShortList();
				ENWorkOrderFilter woFilterList = new ENWorkOrderFilter();

				woFilterList.conditionSQL = " enworkorder.code in "
						+ " (select wp.workordercode from enworkorder2enplanwork wp "
						+ " where wp.plancode=" + aENPlanWorkCode + ")";

				woList = woDAO.getScrollableFilteredList(woFilterList, 0, 1);

				FINMolDataDAO finMolDAO = new FINMolDataDAO(
						getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

				FINMolDataShortList finMolList = new FINMolDataShortList();
				FINMolDataFilter finMolFilterList = new FINMolDataFilter();
				finMolFilterList.workOrder.code = woList.get(0).code;

				finMolList = finMolDAO.getScrollableFilteredList(finMolFilterList, 0, 1);

				String molCode = finMolList.get(0).finMolCode;

				FINDoc2MolDataDAO finDocDAO = new FINDoc2MolDataDAO(
						getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

				FINDoc2MolDataShortList finDocList = new FINDoc2MolDataShortList();
				FINDoc2MolDataFilter finDocFilterList = new FINDoc2MolDataFilter();

				finDocFilterList.molData.code = finMolList.get(0).code;
				finDocFilterList.finDocTypeRef.code = 1;

				finDocList = finDocDAO.getScrollableFilteredList(finDocFilterList, 0, 1);

				int finDoc = Integer.MIN_VALUE;

				if (finDocList.totalCount > 0) {
					finDoc = finDocList.get(0).finDocCode;
				}

				if (finDoc == Integer.MIN_VALUE) {
					throw new EnergyproSystemException("Перезаведіть МОЛа "
							+ molCode + " у наряді ...");
				}

				// удалим привязку - если она есть

				Context cnt = new InitialContext();
				Object objRef = cnt.lookup(FINMaterialsController.JNDI_NAME);
				FINMaterialsControllerHome fmHome = (FINMaterialsControllerHome) PortableRemoteObject
						.narrow(objRef, FINMaterialsControllerHome.class);
				FINMaterialsController fmController = fmHome.create();


				FINMaterialsDAO fmDAO = new FINMaterialsDAO(
						getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
				FINMaterialsFilter fmFilter = new FINMaterialsFilter();

				FINMaterialsShortList fmsList;

				int j;
				for (j = 0; j < eiList.totalCount; j++) {
					fmFilter.estimateItemRef.code = eiList.get(j).code;
					fmFilter.statusRef.code = FINMaterialsStatus.GOOD;

					fmsList = fmDAO.getScrollableFilteredList(fmFilter, 0, -1);
					for (int i = 0; i < fmsList.totalCount; i++) {
						fmController.removeMaterials(fmsList.get(i).code);
					}
				}
				// ////////////


				String nn = "506020010, 506020009";
				String balansNumberCondition = "";
				String materialCondition = " and h.op_kind_id not in ('5','10','310','15') ";

				FINMaterialsFilter finFilter = new FINMaterialsFilter();

				finFilter.party_id = Integer.MIN_VALUE;
				finFilter.conditionSQL = " isCN is null and dat.quantity>0 "
						+ " and ( substr(dat.bal_sch,1,2) in ('20', '22') ) "
						+ " and dat.rest_purpose_type_id <> 5 "
						+ " and dat.mat_id in ( select id from umc_dba.tmatherial where nn in (" + nn + "))";
				finFilter.orderBySQL = " dat.doc_date,dat.party_id ";

				FINMaterialsList finList;
				finList = fmController.getMaterialsList(finFilter,
						balansNumberCondition, molCode, materialCondition, plan.dateStart, finDoc);


				double kolvo_prod;

				for (j = 0; j < eiList.totalCount; j++) {

					if (eiList.get(j).materialRefCode == 500008913) {
						nn = "506020010"; // трос
					} else {
						//nn = "506020009"; // пломба
					}

					/*
					tkeFilterList.conditionSQL = " tkelement2techcard.code in "
							+ " ( "
							+ " select tke2.code from tktechcard tk,tkelement2techcard tke2,tkelement tke,tkmaterials tm "
							+ " where tk.techkartnumber='" + kartaNum + "' "
							+ " and tk.code=tke2.techkartcode "
							+ " and tke.code=tke2.elementcode "
							+ " and tm.elementcode=tke.code " + " and tm.code="
							+ eiList.get(j).materialRefCode + ")";

					tkeList = tkeDAO.getScrollableFilteredList(tkeFilterList, 0, 1);

					if (tkeList.totalCount > 0) {
						kolvo_prod = eiList.get(j).countFact.doubleValue();
					} else {
						throw new EnergyproSystemException("\n\n" +
								"Відсутня нормативна кількість матеріалу ");
					}
					*/

					kolvo_prod = eiList.get(j).countFact.doubleValue();

					double minCount;

					for (int i = 0; i < finList.totalCount; i++) {
						if (nn.equals(finList.get(i).nn)) {

							if (kolvo_prod < 0.01) {
								break;
							}

							minCount = finList.get(i).quantity.doubleValue();

							FINMaterials m = new FINMaterials();
							m.estimateItemRef.code = eiList.get(j).code;

							// !!!!! m.div_code = woList.get(0).finMolCode;
							m.div_code = molCode;

							m.finDocItemCode = -1;

							if (kolvo_prod >= minCount) {
								m.quantity = new BigDecimal(minCount);
							} else {
								m.quantity = new BigDecimal(kolvo_prod);
							}

							m.nn = finList.get(i).nn;
							m.mat_name = finList.get(i).mat_name;
							m.mu_name = finList.get(i).mu_name;
							m.div_name = finList.get(i).div_name;
							m.rest_purpose_name = finList.get(i).rest_purpose_name;

							m.partner_name = finList.get(i).partner_name;
							m.doc_date = finList.get(i).doc_date;
							m.party_discription = finList.get(i).party_discription;
							m.rest_purpose_id = finList.get(i).rest_purpose_id;

							m.rest_purpose_type_id = finList.get(i).rest_purpose_type_id;
							m.budget_core_id = finList.get(i).budget_core_id;
							m.frc_code = finList.get(i).frc_code;
							m.frc_name = finList.get(i).frc_name;
							m.calc_price = finList.get(i).calc_price;

							m.price = finList.get(i).price;
							m.cost = finList.get(i).cost;
							m.bal_sch = finList.get(i).bal_sch;

							m.mat_id = finList.get(i).mat_id;
							m.party_id = finList.get(i).party_id;
							m.partner = finList.get(i).partner;
							m.mu_id = finList.get(i).mu_id;
							m.doc_num = finList.get(i).doc_num;

							m.fullQuantity = finList.get(i).fullQuantity;
							m.fullCost = finList.get(i).fullCost;

							if (finList.get(i).wear_date != null) {
								m.wear_date = finList.get(i).wear_date;
							}

							m.molDataRef.code = finMolList.get(0).code;

							if ((m.quantity.doubleValue() >= 0.01)
									&& (m.cost.doubleValue() >= 0.01)
									&& (m.cost.doubleValue()
											/ finList.get(i).quantity.doubleValue() > 0.1)
									&& (((m.price.doubleValue() * m.quantity.doubleValue()) - m.cost.doubleValue()) < 0.01)) {

								/** 24.10.2014... +++ при проверке красного остатка не уходим в исключение.... */

								int finCode = fmController.addMaterials(m, false);
								if (finCode == Integer.MIN_VALUE) {
									continue;
								}

								kolvo_prod = new BigDecimal(kolvo_prod
										- m.quantity.doubleValue()).setScale(3, BigDecimal.ROUND_HALF_UP).doubleValue();

								System.out.println("############# nn = " + m.nn);
								System.out.println("############# m.quantity = " + m.quantity);
								System.out.println("############# plan.code = " + plan.code);
							}
						}
					}
				}
			}


		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException("Can't connect do DataSource", e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} catch (NamingException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} catch (RemoteException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		} catch (CreateException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		}
	}



     public void unlinkMaterials(int aENPlanWorkCode) {

         try
         {

         ENEstimateItemDAO eiDAO = new ENEstimateItemDAO(
        		 getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
         ENEstimateItemShortList eiList=new ENEstimateItemShortList();
         ENEstimateItemFilter eiFilterList = new ENEstimateItemFilter();
         eiFilterList.planRef.code=aENPlanWorkCode;
         eiFilterList.conditionSQL=" enestimateitem.materialrefcode in (500008913,500008911) and enestimateitem.countfact>0 ";

         eiList = eiDAO.getScrollableFilteredList(eiFilterList,0,-1);

         if (eiList.totalCount>0)

         {


         //удалим привязку - если она есть
         Context cnt = new InitialContext();
         Object objRef = cnt.lookup(FINMaterialsController.JNDI_NAME);
         FINMaterialsControllerHome fmHome = (FINMaterialsControllerHome) PortableRemoteObject.narrow(objRef, FINMaterialsControllerHome.class);

         FINMaterialsController fmController = fmHome.create();

         FINMaterialsDAO fmDAO = new FINMaterialsDAO(
        		 getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
         FINMaterialsFilter fmFilter = new FINMaterialsFilter();

         FINMaterialsShortList fmsList;



         int j;

         for (j=0; j<eiList.totalCount; j++){

         fmFilter.estimateItemRef.code=eiList.get(j).code;
         fmFilter.statusRef.code=FINMaterialsStatus.GOOD;

         fmsList=fmDAO.getScrollableFilteredList(fmFilter,0,-1);


         for (int i=0; i<fmsList.totalCount; i++){
             fmController.removeMaterials(fmsList.get(i).code);
             }


         }
         }
         }
         catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't connect do DataSource",e);}
         catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
         catch (NamingException e) {throw new EnergyproSystemException(e.getMessage(), e);}
         catch (RemoteException e) {throw new EnergyproSystemException(e.getMessage(), e);}
         catch (CreateException e) {throw new EnergyproSystemException(e.getMessage(), e);}

     }




	public void import2Bufet(int aENPlanWorkCode, String numberDoc, int typeCode) {

		Connection finConn = null;
		Connection netConn = null;
		try {

		    finConn = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
		    netConn = getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE);

			ENPlanWorkDAO planDAO = new ENPlanWorkDAO(netConn, getUserProfile());
			ENEstimateItemDAO eiDAO = new ENEstimateItemDAO(netConn, getUserProfile());
			ENWorkOrderDAO woDAO = new ENWorkOrderDAO(netConn, getUserProfile());
			FINMolDataDAO finMolDAO = new FINMolDataDAO(netConn, getUserProfile());
			FINDoc2MolDataDAO finDocDAO = new FINDoc2MolDataDAO(netConn, getUserProfile());
			FINMaterialsDAO fmDAO = new FINMaterialsDAO(netConn, getUserProfile());


			ENPlanWork plan = new ENPlanWork();
			plan = planDAO.getObject(aENPlanWorkCode);

			ENEstimateItemShortList eiList = new ENEstimateItemShortList();
			ENEstimateItemFilter eiFilterList = new ENEstimateItemFilter();
			eiFilterList.planRef.code = aENPlanWorkCode;

			eiList = eiDAO.getScrollableFilteredList(eiFilterList, 0, 1);


			ENWorkOrderShortList woList = new ENWorkOrderShortList();
			ENWorkOrderFilter woFilterList = new ENWorkOrderFilter();

			woFilterList.conditionSQL = " enworkorder.code in "
					+ " (select wp.workordercode from enworkorder2enplanwork wp "
					+ " where wp.plancode=" + aENPlanWorkCode + ")";

			woList = woDAO.getScrollableFilteredList(woFilterList, 0, 1);


			FINMolDataShortList finMolList = new FINMolDataShortList();
			FINMolDataFilter finMolFilterList = new FINMolDataFilter();
			finMolFilterList.workOrder.code = woList.get(0).code;

			finMolList = finMolDAO.getScrollableFilteredList(finMolFilterList, 0, 1);
			String molCode = finMolList.get(0).finMolCode;


			FINDoc2MolDataShortList finDocList = new FINDoc2MolDataShortList();
			FINDoc2MolDataFilter finDocFilterList = new FINDoc2MolDataFilter();

			finDocFilterList.molData.code = finMolList.get(0).code;
			finDocFilterList.finDocTypeRef.code = 50;

			finDocList = finDocDAO.getScrollableFilteredList(finDocFilterList, 0, 1);

			int finDoc = Integer.MIN_VALUE;

			if (finDocList.totalCount > 0) {
				finDoc = finDocList.get(0).finDocCode;
			}

			if (finDoc == Integer.MIN_VALUE) {
				throw new EnergyproSystemException("\n\nПерезаведіть МОЛа "
						+ molCode + " у наряді ...");
			}

			// удалим привязку - если она есть
			Context cnt = new InitialContext();
			Object objRef = cnt.lookup(FINMaterialsController.JNDI_NAME);
			FINMaterialsControllerHome fmHome = (FINMaterialsControllerHome) PortableRemoteObject.narrow(objRef, FINMaterialsControllerHome.class);

			FINMaterialsController fmController = fmHome.create();
			FINMaterialsFilter fmFilter = new FINMaterialsFilter();
			FINMaterialsShortList fmsList;

			fmFilter.estimateItemRef.code = eiList.get(0).code;
			fmFilter.statusRef.code = FINMaterialsStatus.GOOD;

			fmsList = fmDAO.getScrollableFilteredList(fmFilter, 0, -1);


			for (int i = 0; i < fmsList.totalCount; i++) {
				fmController.removeMaterials(fmsList.get(i).code);
			}

			// привязка материалов с финколлекции по буфетной накладной
			Connection connection = getConnection(AuthorizationJNDINames.BUFET_DATASOURCE);

			BuffetDAO buffetDao = new BuffetDAO(connection, getUserProfile());

			BigDecimal invoiceOverallSumBuffetWithoutVAT = buffetDao.getInvoiceSumWithoutVAT(numberDoc, plan.dateStart);
			BigDecimal invoiceOverallSumBuffetWithVAT = buffetDao.getInvoiceSumWithVAT(numberDoc, plan.dateStart);
			BigDecimal invoiceOverallSumEnergyNetWithoutVAT = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);
			BigDecimal invoiceOverallSumEnergyNetWithVAT = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);

			String selectStr;
			PreparedStatement selectStatement = null;
			ResultSet set = null;

			selectStr = "select p.gost,jn.kolvo_prod,"
					+ " jn.sena_prod as sena_prod_nds,"
					+ " jn.summa as summa_nds,jn.sena_prod_wvat,jn.summa_wvat "
					+ " from jur_nak jn,prod p where jn.id_prod>0 and jn.nom_nak = '" + numberDoc + "'"
					+ " and jn.tip=2 and jn.data= " + Tools.convertDateToSQLString(plan.dateStart)
					+ " and jn.id_prod=p.id ";

			if (typeCode == 1) //Втрати
			{
				selectStr = "select p.gost,jn.kolvo_prod,"
						+ " jn.sena_prod as sena_prod_nds, "
						+ " jn.summa as summa_nds,jn.sena_prod_wvat,jn.summa_wvat "
						+ " from jur_nak jn,prod p where jn.id_prod>0 and jn.nom_nak = '" + numberDoc + "'"
						+ " and jn.tip=2 and jn.data= " + Tools.convertDateToSQLString(plan.dateStart)
						+ " and jn.id_prod=p.id ";
			}

			selectStatement = connection.prepareStatement(selectStr);
			set = selectStatement.executeQuery();

			FINLogic finLogic = new FINLogic(finConn, getUserProfile());
			FINMaterialsFilter finFilter = new FINMaterialsFilter();

			while (set.next()) {

				String nn = set.getString(1);
				BigDecimal kolvo_prod = set.getBigDecimal(2);
				if(kolvo_prod != null) {
					kolvo_prod = kolvo_prod.setScale(6, BigDecimal.ROUND_HALF_UP);
				}
				BigDecimal sena_prod_nds = set.getBigDecimal(3);
				BigDecimal summa_nds = set.getBigDecimal(4);
				BigDecimal sena_prod_wvat = set.getBigDecimal(5);
				BigDecimal summa_wvat = set.getBigDecimal(6);

				BigDecimal sena_prod = sena_prod_wvat.setScale(2, BigDecimal.ROUND_HALF_UP);
				BigDecimal summa = summa_wvat.setScale(2, BigDecimal.ROUND_HALF_UP);

				String balansNumberCondition = "";
				String materialCondition = " and h.op_kind_id not in ('5','10','310','15') ";


				finFilter.nn = "*" + nn + "*";
				finFilter.conditionSQL = " isCN is null and dat.quantity>0 and ( substr(dat.bal_sch,1,2) in ('20', '28') ) ";
				finFilter.orderBySQL = "dat.bal_sch desc, dat.doc_date,dat.party_id ";


				Date fd=fmController.getOpenPeriodDate();
				Calendar newPeriodCal = Calendar.getInstance();

				if (fd.compareTo(plan.dateStart) >= 0) {
					newPeriodCal.setTime(fd);
				} else {
					newPeriodCal.setTime(plan.dateStart);
				}

				newPeriodCal.set(Calendar.DATE, newPeriodCal.getActualMaximum(Calendar.DAY_OF_MONTH));
				Date endDate = newPeriodCal.getTime();

				FINMaterialsList finList = fmController.getMaterialsList(
						finFilter, balansNumberCondition, molCode,
						materialCondition, endDate, finDoc);

				for (int i = 0; i < finList.totalCount; i++) {
					if (kolvo_prod.compareTo(BigDecimal.ZERO) <= 0) {
						break;
					}

					FINMaterials m = new FINMaterials();
					m.estimateItemRef.code = eiList.get(0).code;
					m.div_code = woList.get(0).finMolCode;
					m.finDocItemCode = -1;

					m.extra_cargo = sena_prod;
					m.extra_cargo_nds = sena_prod_nds;

					if (kolvo_prod.compareTo(finList.get(i).quantity) >= 0) {
						m.quantity = finList.get(i).quantity;
						m.cost_ext = m.quantity.multiply(m.extra_cargo);
						m.cost_ext_nds = m.quantity.multiply(m.extra_cargo_nds);
					} else {
						m.quantity = kolvo_prod;
						m.cost_ext = summa;
						m.cost_ext_nds = summa_nds;
					}

					kolvo_prod = kolvo_prod.subtract(m.quantity);

					summa = summa.subtract(m.cost_ext);
					summa_nds = summa_nds.subtract(m.cost_ext_nds);

					if (summa_nds.compareTo(new BigDecimal(0.01)) < 0) {
						m.cost_ext = m.cost_ext.add(summa);
						m.cost_ext_nds = m.cost_ext_nds.add(summa_nds);
					}

					m.nn = finList.get(i).nn;
					m.mat_name = finList.get(i).mat_name;
					m.mu_name = finList.get(i).mu_name;
					m.div_name = finList.get(i).div_name;
					m.rest_purpose_name = finList.get(i).rest_purpose_name;


					m.partner_name = finList.get(i).partner_name;
					m.doc_date = finList.get(i).doc_date;
					m.party_discription = finList.get(i).party_discription;
					m.rest_purpose_id = finList.get(i).rest_purpose_id;

					m.rest_purpose_type_id = finList.get(i).rest_purpose_type_id;
					m.budget_core_id = finList.get(i).budget_core_id;
					m.frc_code = finList.get(i).frc_code;
					m.frc_name = finList.get(i).frc_name;
					m.calc_price = finList.get(i).calc_price;

					m.price = finList.get(i).price;
					m.cost = finList.get(i).cost;
					m.bal_sch = finList.get(i).bal_sch;

					m.mat_id = finList.get(i).mat_id;
					m.party_id = finList.get(i).party_id;
					m.partner = finList.get(i).partner;
					m.mu_id = finList.get(i).mu_id;
					m.doc_num = finList.get(i).doc_num;

					m.fullQuantity = finList.get(i).fullQuantity;
					m.fullCost = finList.get(i).fullCost;

					if (finList.get(i).wear_date != null) {
						m.wear_date = finList.get(i).wear_date;
					}

					m.molDataRef.code = finMolList.get(0).code;

					// Округление стоимостей до двух знаков после запятой
					m.cost_ext = m.cost_ext.setScale(2, BigDecimal.ROUND_HALF_UP);
					m.cost_ext_nds = m.cost_ext_nds.setScale(2, BigDecimal.ROUND_HALF_UP);

					finLogic.setExtraCargo(nn, sena_prod);

					fmController.addMaterials(m);

					invoiceOverallSumEnergyNetWithoutVAT = invoiceOverallSumEnergyNetWithoutVAT.add(m.cost_ext);
					invoiceOverallSumEnergyNetWithVAT = invoiceOverallSumEnergyNetWithVAT.add(m.cost_ext_nds);

				}

				if (kolvo_prod.compareTo(new BigDecimal(0.001)) == 1) {
					throw new EnergyproSystemException("\n\nКол-во в партиях в учете материалов меньше , чем кол-во реализованной продукции -" + nn);
				}

			}

			set.close();

			BigDecimal zeroCostExt = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP)
					, zeroCostExtNds = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);
			FINMaterialsFilter zeroFilter = new FINMaterialsFilter();
			zeroFilter.estimateItemRef.code = eiList.get(0).code;
			zeroFilter.statusRef.code = FINMaterialsStatus.GOOD;
			zeroFilter.cost = BigDecimal.ZERO;
			FINMaterialsShortList zeroList = fmDAO.getScrollableFilteredList(zeroFilter, 0, -1);
			Vector<Integer> zeroCodes = new Vector<>();
			for(FINMaterialsShort item : zeroList.list) {
				FINMaterials obj = fmDAO.getObject(item.code);
				zeroCostExt = zeroCostExt.add(obj.cost_ext);
				zeroCostExtNds = zeroCostExtNds.add(obj.cost_ext_nds);
				obj.cost_ext = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);
				obj.cost_ext_nds = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);
				fmDAO.save(obj);
				zeroCodes.add(obj.code);
			}

			invoiceOverallSumEnergyNetWithoutVAT = invoiceOverallSumEnergyNetWithoutVAT.subtract(zeroCostExt);
			invoiceOverallSumEnergyNetWithVAT = invoiceOverallSumEnergyNetWithVAT.subtract(zeroCostExtNds);


			BigDecimal step = new BigDecimal(0.01).setScale(2, BigDecimal.ROUND_HALF_UP);

			System.out.println(String.format("EnergyNet Sum without VAT: %f", invoiceOverallSumEnergyNetWithoutVAT));
			System.out.println(String.format("EnergyNet Sum with VAT: %f", invoiceOverallSumEnergyNetWithVAT));

			System.out.println(String.format("Buffet Sum without VAT: %f", invoiceOverallSumBuffetWithoutVAT));
			System.out.println(String.format("Buffet Sum with VAT: %f", invoiceOverallSumBuffetWithVAT));

			FINMaterialsFilter finMatFilter = new FINMaterialsFilter();
			finMatFilter.estimateItemRef.code = eiList.get(0).code;
			finMatFilter.statusRef.code = FINMaterialsStatus.GOOD;
			if(zeroCodes.size() > 0) {
				finMatFilter.conditionSQL = String.format("%s not in (%s)", FINMaterials.code_QFielld
						, com.ksoe.energynet.util.Tools.repeatSymbol("?", ",", zeroCodes.size()));
			}

			if(invoiceOverallSumEnergyNetWithoutVAT.compareTo(invoiceOverallSumBuffetWithoutVAT) != 0) {

				finMatFilter.orderBySQL = String.format("%s desc", FINMaterials.cost_ext_QFielld);
				FINMaterialsShortList finMatList = fmDAO.getScrollableFilteredList(finMatFilter, 0, -1, zeroCodes);

				int listCount = finMatList.totalCount;

				BigDecimal delta = invoiceOverallSumBuffetWithoutVAT.subtract(invoiceOverallSumEnergyNetWithoutVAT);
				boolean isAdding = delta.compareTo(BigDecimal.ZERO) > 0;
				while(delta.abs().compareTo(step) >= 0 && listCount > 0) {
					FINMaterialsShort materialShort = finMatList.get(--listCount);
					FINMaterials material = fmDAO.getObject(materialShort.code);
					if(isAdding) {
						material.cost_ext = material.cost_ext.add(step);
					} else {
						if(material.cost_ext.compareTo(step) > 0) {
							material.cost_ext = material.cost_ext.subtract(step);
						}
					}
					fmDAO.save(material);

					if(isAdding) {
						delta = delta.subtract(step);
					} else {
						delta = delta.add(step);
					}

				}
			}

			if(invoiceOverallSumEnergyNetWithVAT.compareTo(invoiceOverallSumBuffetWithVAT) != 0) {

				finMatFilter.orderBySQL = String.format("%s desc", FINMaterials.cost_ext_nds_QFielld);
				FINMaterialsShortList finMatList = fmDAO.getScrollableFilteredList(finMatFilter, 0, -1, zeroCodes);

				int listCount = finMatList.totalCount;

				BigDecimal delta = invoiceOverallSumBuffetWithVAT.subtract(invoiceOverallSumEnergyNetWithVAT);
				boolean isAdding = delta.compareTo(BigDecimal.ZERO) > 0;
				while(delta.abs().compareTo(step) >= 0 && listCount > 0) {
					FINMaterialsShort materialShort = finMatList.get(--listCount);
					FINMaterials material = fmDAO.getObject(materialShort.code);
					if(isAdding) {
						material.cost_ext_nds = material.cost_ext_nds.add(step);
					} else {
						if(material.cost_ext_nds.compareTo(step) > 0) {
							material.cost_ext_nds = material.cost_ext_nds.subtract(step);
						}
					}
					fmDAO.save(material);

					if(isAdding) {
						delta = delta.subtract(step);
					} else {
						delta = delta.add(step);
					}

				}
			}

		} catch (DatasourceConnectException e) {
			throw new SystemException("Can't start import2Bufet", e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} catch (SQLException e) {
			throw new SystemException(e.getMessage(), e);
		} catch (NamingException e) {
			throw new SystemException(e.getMessage(), e);
		} catch (RemoteException e) {
			throw new SystemException(e.getMessage(), e);
		} catch (CreateException e) {
			throw new SystemException(e.getMessage(), e);
		}

		finally {
			try {
				if (finConn != null && !finConn.isClosed()) {
					finConn.close();
					finConn = null;
				}
				if (netConn != null && !netConn.isClosed()) {
					netConn.close();
					netConn = null;
				}
			} catch (SQLException e) {
			}
		}
	}


	public void importOrderBufet(int aENPlanWorkCode, String numberDoc) {

		Connection finConn = null;
		Connection netConn = null;
		try {

		    finConn = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
		    netConn = getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE);

			ENPlanWorkDAO planDAO = new ENPlanWorkDAO(netConn, getUserProfile());
			ENEstimateItemDAO eiDAO = new ENEstimateItemDAO(netConn, getUserProfile());
			TKMaterialsDAO tkDAO = new TKMaterialsDAO(netConn, getUserProfile());
			TKMaterialsFilter tkFilter = new TKMaterialsFilter();
			TKMaterialsShortList tkList;
			TEMPNomenclaturesDAO tempNomDAO = new TEMPNomenclaturesDAO(netConn,getUserProfile());
			TEMPNomenclaturesFilter tempNomFilter = new TEMPNomenclaturesFilter();
			TEMPNomenclaturesShortList tempNomList;
			ENNormVolumeAVZItemDAO normItemDAO  = new ENNormVolumeAVZItemDAO(netConn, getUserProfile());
			ENNormVolumeAVZItemFilter normItemFilter = new ENNormVolumeAVZItemFilter();
			ENNormVolumeAVZItemShortList normItemList;

			ENPlanWork plan = new ENPlanWork();
			plan = planDAO.getObject(aENPlanWorkCode);

			ENEstimateItemShortList eiList = new ENEstimateItemShortList();
			ENEstimateItemFilter eiFilterList = new ENEstimateItemFilter();
			eiFilterList.planRef.code = aENPlanWorkCode;

			eiList = eiDAO.getScrollableFilteredList(eiFilterList, 0, -1);

			// удалим привязку - если она есть

			for (int ei=0;ei<eiList.list.size();ei++) {
				eiDAO.remove(eiList.get(ei).code);
			}

			// привязка материалов с финколлекции по буфетной накладной
			Connection connection = getConnection(AuthorizationJNDINames.BUFET_DATASOURCE);

			BuffetDAO buffetDao = new BuffetDAO(connection, getUserProfile());
			 FINLogic finLogic = new FINLogic(
		    		 getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE), getUserProfile());

			String selectStr;
			PreparedStatement selectStatement = null;
			ResultSet set = null;

			selectStr =

			" select gost,round(sum(kolvo_prod),2) as kolvo_prod,round(max(sena_prod_nds),2) as sena_prod_nds, "+
			" round(round(sum(kolvo_prod),2)*round(max(sena_prod_nds),2),2) as summa_nds, "+
			" round(max(sena_prod_wvat),2) as sena_prod_wvat, "+
			" round(round(sum(kolvo_prod),2)*round(max(sena_prod_wvat),2),2) as summa_wvat "+
			" from "+
			" ( "+
			" select p.gost,m.kolvo_prod, "+
			"                      m.sena_prod_in as sena_prod_nds, "+
			"                      round(m.kolvo_prod*m.sena_prod_in,3) as summa_nds, "+
			"                      round(m.sena_prod_in*0.833,3) as sena_prod_wvat, "+
			"                      round(m.kolvo_prod*m.sena_prod_in*0.833,3) as summa_wvat "+
			"                      from banket o,b_menu_prod m,prod p "+
			"                      where m.id_prod>0 "+
			"                      and m.id_menu=o.nom_dok "+
			"                      and cast(o.na_data_tim as date) "+
			"                      between cast("+Tools.convertDateToSQLString(plan.dateStart)+" as date) "+
			"                      and (cast("+Tools.convertDateToSQLString(plan.dateStart)+" as date)+7) "+
			"                      and m.id_prod=p.id "+
			"    ) d "+
			"  group by gost "
			 ;


			selectStatement = connection.prepareStatement(selectStr);
			set = selectStatement.executeQuery();
			ENEstimateItem item;


			while (set.next()) {

				String nn = set.getString(1);

				tkFilter.conditionSQL=
						" tk1.code in ( "+
						"		select tn.materialrefcode "+
						"		from tempnomenclatures tn "+
						"		where tn.nn = '"+nn+"'" +
								" ) "
						;

				tkList = tkDAO.getScrollableFilteredList(tkFilter,tkFilter.conditionSQL,  0, -1);

				if (tkList.list.size()<=0) {
					continue;
				}

				tempNomFilter.nn = nn;
				tempNomList = tempNomDAO.getScrollableFilteredList(tempNomFilter, 0, -1);

				if (tempNomList.list.size()<=0) {
					continue;
				}

				// вытянем нормативные величины
				normItemFilter.materialRef.code = tkList.get(0).code;
				normItemFilter.normativeVolumeRef.code = 93;
				normItemList = normItemDAO.getScrollableFilteredList(normItemFilter, 0, -1);

				BigDecimal normRestQty = new BigDecimal(0.0);
				BigDecimal minPartyQty = new BigDecimal(0.0);

				if (normItemList.list.size() > 0) {
					normRestQty = normItemList.get(0).countGen;
					minPartyQty = normItemList.get(0).countRequired;
				}

				BigDecimal bufetQty = set.getBigDecimal(2);
				BigDecimal sena_prod_nds = set.getBigDecimal(3);
				BigDecimal summa_nds = set.getBigDecimal(4);
				BigDecimal sena_prod_wvat = set.getBigDecimal(5);
				BigDecimal summa_wvat = set.getBigDecimal(6);

				BigDecimal sena_prod = sena_prod_wvat.setScale(2, BigDecimal.ROUND_HALF_UP);
				BigDecimal summa = summa_wvat.setScale(2, BigDecimal.ROUND_HALF_UP);

				item = new ENEstimateItem();

			     BigDecimal restQty = finLogic.getRestByMatIdAndMOLCodeOnDate("4709", new SimpleDateFormat("dd.MM.yyyy").format(new Date()), "" + tempNomList.get(0).mat_id);
			     // расчет количества материала
			     // buferQty - количество из буфета

			     //1
			     BigDecimal needQty = bufetQty.subtract(restQty).setScale(2, BigDecimal.ROUND_HALF_UP);
			     if (needQty.compareTo(new BigDecimal(0)) == -1) {
			    	 needQty=new BigDecimal(0);
			     }

			     BigDecimal needToAddForAVR =
			    		 (normRestQty.doubleValue()-(needQty.doubleValue()+restQty.doubleValue()))>0 ?
			    		new BigDecimal((normRestQty.doubleValue()-(needQty.doubleValue()+restQty.doubleValue()))) :
			    		new BigDecimal(0);

			     //2
	    		 needQty=new BigDecimal(needQty.doubleValue()+needToAddForAVR.doubleValue());

			     //3
	    		 if (needQty.doubleValue()<minPartyQty.doubleValue()) {
	    			 needQty=minPartyQty;
	    		 }


				//
				if (needQty.doubleValue()<=0) {
					continue;
				}

				item.countGen=needQty;
				item.countFact=needQty;

				item.materialRef.code=tkList.get(0).code;

				item.planRef.code=aENPlanWorkCode;

				item.price=sena_prod_nds;
				item.cost=summa_nds;
				item.deliveryTime = tkList.get(0).deliveryDate;
				item.commentGen="Замовлення буфет";
				item.userGen="promadmin";
				item.dateEdit=new Date() ;

				item.typeRef.code=ENEstimateItemType.AUTO;
			    item.kindRef.code=ENEstimateItemKind.MATERIALS;
			    item.statusRef.code = ENEstimateItemStatus.PLANNED;
			    item.accountingTypeRef.code=TKAccountingType.TMC;

			    eiDAO.add(item);

			}

			set.close();

		} catch (DatasourceConnectException e) {
			throw new SystemException("Can't start importOrderBufet", e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} catch (SQLException e) {
			throw new SystemException(e.getMessage(), e);
		}

		finally {
			try {
				if (finConn != null && !finConn.isClosed()) {
					finConn.close();
					finConn = null;
				}
				if (netConn != null && !netConn.isClosed()) {
					netConn.close();
					netConn = null;
				}
			} catch (SQLException e) {
			}
		}
	}


    public void export2BufetVtrati(int aPlanCode) {

        try
            {


                Connection connectionEN = getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE);

                Connection connection = getConnection(AuthorizationJNDINames.BUFET_DATASOURCE);

            String updateStr;
            String selectStr;
            PreparedStatement updateStatement = null;
            PreparedStatement selectStatement = null;
            ResultSet set = null;

            ENPlanWorkDAO pDAO = new ENPlanWorkDAO(getUserProfile(),connectionEN);
            ENPlanWork p = pDAO.getObject(aPlanCode);

            FINMaterialsDAO finDAO = new FINMaterialsDAO(getUserProfile(),connectionEN);
            FINMaterialsFilter ff = new FINMaterialsFilter();


            ENEstimateItemDAO eiDAO = new ENEstimateItemDAO(getUserProfile(),connectionEN);
            ENEstimateItemFilter eif = new ENEstimateItemFilter();
            eif.planRef.code=aPlanCode;
            ENEstimateItemShortList eiList = eiDAO.getScrollableFilteredList(eif,0,-1);
            ff.estimateItemRef.code=eiList.get(0).code;
            ff.statusRef.code=1;
            FINMaterialsShortList finList = finDAO.getScrollableFilteredList(ff,0,-1);

            if (finList.totalCount == 0){
                throw new EnergyproSystemException("У плані немає строк з матеріалами ...");
            }

            FINMaterialsShort itm;

            for (int i=0; i<finList.totalCount; i++){
                itm = finList.get(i);

                selectStr="select * from prod where gost = '"+itm.nn +"'";
                selectStatement = connection.prepareStatement(selectStr);
                    set = selectStatement.executeQuery();

                    if (! set.next())
                    {
                    throw new EnergyproSystemException("Немає номенклатури у буфеті-"+itm.nn);
                    }

                    selectStatement.close();
            }

            set.close();

            int ID_PROD=0,ID_GRUP_PROD=0;
            double SENA_PROD=0;
            String NAME_PROD="",NAME_GRUP_PROD="",NOM_NAK="",NAME_SKLAD="",NAME_USER="";

            selectStr="SELECT GEN_ID(NOM_RASH_NAK,1) FROM RDB$DATABASE";
            selectStatement = connection.prepareStatement(selectStr);
                set = selectStatement.executeQuery();


                if (set.next())
                {
                NOM_NAK =set.getString(1);
                }


                set.close();

            for (int i=0; i<finList.totalCount; i++){
                itm = finList.get(i);
                selectStr="select id,id_grup,sena_in,name,grup from prod where NN = '"+itm.nn +"'";
                selectStatement = connection.prepareStatement(selectStr);
                    set = selectStatement.executeQuery();

                    if (set.next())
                    {
                    ID_PROD=set.getInt(1);
                    ID_GRUP_PROD=set.getInt(2);
                    SENA_PROD=itm.price.doubleValue();
                    }
                    selectStatement.close();
                    set.close();

        updateStr=" INSERT INTO JUR_NAK "+
            " (ID,TIP,DATA,ID_PROD,NAME_PROD,SENA_PROD,KOLVO_PROD,ID_FROM_SKLAD,ID_TO_SKLAD, "+
      " ID_GRUP_PROD,NOM_NAK,NAME_TO_SKLAD,NAME_FROM_SKLAD,SENA_PROD_IN,SENA_PROD_OUT,NOM_PRIH_DOK, "+
      " TIM,PROVED,NAME_USER,ID_USER,NAME_GRUP_PROD,OPLATA) "+
      " VALUES( "+
      " (SELECT GEN_ID(JUR_NAK_ID,1) FROM RDB$DATABASE)," +
      " 2,"+
      Tools.convertDateToSQLString(p.dateStart) +","+
      ID_PROD+","+"'"+NAME_PROD +"',"+
      SENA_PROD+","+itm.quantity.doubleValue()+","+
      "5,10000,"+ID_GRUP_PROD+",'"+NOM_NAK+"',"+
      "'"+NAME_SKLAD+"',"+"'EnergyNet',"+SENA_PROD+","+SENA_PROD+",null,"+
      Tools.convertDateToSQLString(p.dateStart) +","+
      "null,'"+NAME_USER+"',2,"+
      "'"+NAME_GRUP_PROD+"',0)";

      System.out.println("JUR_NAK-"+updateStr);

        updateStatement = connection.prepareStatement(updateStr);
            updateStatement.execute();
            updateStatement.close();

            }

            updateStr="update jur_nak jn " +
                    " set NAME_PROD = (select name from prod p where p.id=jn.ID_PROD), "+
                    " NAME_GRUP_PROD= (select grup from prod p where p.id=jn.ID_PROD), "+
                    " NAME_TO_SKLAD= (select name from sp_rash where id=10000), "+
                    " NAME_FROM_SKLAD= (select name from sklad where id=5), "+
                    " PR_SPIS= (select name from sp_pr_spis where id=1), "+
                    " NAME_USER=(select name from users where id=2) "+
            " where tip=2 and NOM_NAK='"+NOM_NAK+"'";
            updateStatement = connection.prepareStatement(updateStr);
            updateStatement.execute();
            updateStatement.close();



            }
            catch (DatasourceConnectException e) {
                throw new EnergyproSystemException(
                        "\n \n Нет связи с Буфетом...");
            }

            catch (PersistenceException e)       {
                throw new EnergyproSystemException(e.getMessage(),e);
                }

            catch (SQLException e) {
                    System.out.println(e.getMessage());
                }

            finally {
                closeConnection();
            }

    }





    public void saveAddInfoExecutorDepartment(ENPlanWork object)
    {
        /*
            try
            {

                if (( object.kind.code == ENPlanWorkKind.YEAR ) || (object.kind.code == ENPlanWorkKind.FACT)) {
                    throw new EnergyproSystemException("Эта операция делается ТОЛЬКО на Месячных и Ежедневных планах!");
                }


                AuthLogic l = new AuthLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
                if ( ! l.checkPermission4PlanItems(object.code) ){
                    throw new EnergyproSystemException("Access denied for method addBy... from method ENPlanWork.save()");
                }

            object.setDomain_info(null);

            ENPlanWorkDAO objectDAO = new ENPlanWorkDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

            PlanWorkLogic planLogic = new PlanWorkLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

            if (object.finExecutor != null){

                FINExecutorDAO d = new FINExecutorDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
                FINExecutor f = null;
                if (object.finExecutor.finCode > Integer.MIN_VALUE)
                {

                    if (object.finExecutor.code > Integer.MIN_VALUE)
                        f = d.getObject(object.finExecutor.code);
                    else
                    {
                        f = new FINExecutor();
                        f.code = Integer.MIN_VALUE;
                    }

                    f.finCode = object.finExecutor.finCode;
                    f.name =  object.finExecutor.name;
                    f.finCehCode = object.finExecutor.finCehCode;
                    f.finCehName = object.finExecutor.finCehName;
                    f.finKindCode = object.finExecutor.finKindCode;
                    f.finKindName = object.finExecutor.finKindName;
                    f.finTypeCode = object.finExecutor.finTypeCode;
                    f.finTypeName = object.finExecutor.finTypeName;

                    f.code = Integer.MIN_VALUE;
                    object.finExecutor.code = d.add(f);

                    ////////////////////////////////////////
                    // NET-2800 Апдейтим основного исполнителя на плане (или создаем) - в развязке FINExecutor2Plan
                    planLogic.updatePrimaryFinExecutor(object);
                    ////////////////////////////////////////
                }

            }

            // обновим исполнителя и подразделение исполнителя во всех последующих планах которые выше связаны с текущим планом
            ENPlanWorkDAO plDAO = new ENPlanWorkDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
            ENAct2ENPlanWorkDAO a2pDAO = new ENAct2ENPlanWorkDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
            ENActDAO aDAO = new ENActDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
            ENPlanWork newplObj =null;
            PlanWorkLogic plogic = new PlanWorkLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
            String codesPlanChild = plogic.getCodesHistoryUp2(object.code);
            ENPlanWorkFilter plFiltr = new ENPlanWorkFilter();
            plFiltr.conditionSQL = " ENPLANWORK.CODE in (  " + codesPlanChild + ")" ;

            int[] plArr  =plDAO.getFilteredCodeArray(plFiltr, 0, -1);
            for (int i = 0; i < plArr.length; i++){
                newplObj = plDAO.getObject(plArr[i]);
                if (newplObj.kind.code == ENPlanWorkKind.FACT) {
                    // если есть акт то не обновляем исполнителя и подразделение \
                    ENAct2ENPlanWorkFilter a2pFilter = new ENAct2ENPlanWorkFilter();
                    a2pFilter.plan.code = newplObj.code;
                    int[] a2pArr  =a2pDAO.getFilteredCodeArray(a2pFilter, null, null, 0, -1, null);
                    if (a2pArr.length > 0 ) {
                        ENAct2ENPlanWork a2pObj = a2pDAO.getObject(a2pArr[0]);

                        ENAct aObj = aDAO.getObject(a2pObj.actRef.code);
                        throw new EnergyproSystemException("Плани зв`язані з Актом " + aObj.numberGen + " від " + new SimpleDateFormat("dd.MM.yyyy").format(aObj.dateAct) + " Змінити виконавця на планах неможливо  ...");
                    }


                }

                // обновляем исполнителей и подразделений на связаных выше планах
                if (object.finExecutor != null && object.code != newplObj.code){

                    FINExecutorDAO d = new FINExecutorDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
                    FINExecutor f = null;
                    if (newplObj.finExecutor.finCode > Integer.MIN_VALUE)
                    {

                        if (newplObj.finExecutor.code > Integer.MIN_VALUE)
                            f = d.getObject(newplObj.finExecutor.code);
                        else
                        {
                            f = new FINExecutor();
                            f.code = Integer.MIN_VALUE;
                        }

                        f.finCode = object.finExecutor.finCode;
                        f.name =  object.finExecutor.name;
                        f.finCehCode = object.finExecutor.finCehCode;
                        f.finCehName = object.finExecutor.finCehName;
                        f.finKindCode = object.finExecutor.finKindCode;
                        f.finKindName = object.finExecutor.finKindName;
                        f.finTypeCode = object.finExecutor.finTypeCode;
                        f.finTypeName = object.finExecutor.finTypeName;

                        f.code = Integer.MIN_VALUE;
                        newplObj.finExecutor.code = d.add(f);
                    }
                }

                // конец обновления исполнителя на связаных планах
                // пересохраним связаные выше планы
                if ( object.code != newplObj.code && newplObj != null ) {
                    newplObj.departmentRef.code = object.departmentRef.code;
                    newplObj.setDomain_info(null);
                    objectDAO.save(newplObj);
                }
                }

            objectDAO.save(object);


            }
            catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENPlanWork%} object.",e);}
            catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
            finally                              {closeConnection();}
        */

        try
        {
            PlanWorkLogic planLogic = new PlanWorkLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
            planLogic.saveAddInfoExecutorDepartment(object);
        }
        catch (DatasourceConnectException e)
        {
            throw new EnergyproSystemException(e.getMessage(), e);
        }
        catch (PersistenceException e)
        {
            throw new EnergyproSystemException(e.getMessage(), e);
        }
    }


    /* ENPlanWork. Общий (упрощенный) Список планов */
    public ENPlanWorkShortList getPlanWorkGeneralList(
            ENPlanWorkFilter filterObject, int fromPosition, int quantity) {
        try {
            ENPlanWorkDAO objectDAO = new ENPlanWorkDAO(getUserProfile(),
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
            return objectDAO.getPlanWorkGeneralList(filterObject, fromPosition,
                    quantity);
        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException(
                    "Can't getPlanWorkGeneralList...", e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            closeConnection();
        }
    }


    /**
     *  Копирование плана для договора на присоединение
     *
     *  @param planCode - код плана
     *  @param monthGen - месяц плана
     *  @param yearGen - год плана
     *  @param soCode - код договора
     *  @param elementCode - код элемента
     *
     *  @return newPlanCode - код созданного плана
     *
     */
	public int copyPlanPriconnections(int planCode, int monthGen, int yearGen, int soCode, int elementCode) {

		Connection enConn = null;

		try {
			enConn = getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE);

            AuthLogic l = new AuthLogic(enConn, getUserProfile());
            if (!l.checkPermission4PlanItems(planCode)) {
                throw new EnergyproSystemException(
                        "Access denied from method ENPlanWork.copyPlanPriconnections()");
            }


            TechConditionsLogic techConditionsLogic = new TechConditionsLogic(enConn, getUserProfile());
            int techCondServicesCode = techConditionsLogic.getTechCodeBySoCode(soCode);
            if (techCondServicesCode != Integer.MIN_VALUE) {
            	techConditionsLogic.checkActIncone(techCondServicesCode);
            }



            /** копируем только месячный план */
            ENPlanWorkDAO planDao = new ENPlanWorkDAO(
                    getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
            ENPlanWork plan = planDao.getObject(planCode);

            if (plan.kind.code != ENPlanWorkKind.CURRENT) {
                throw new EnergyproSystemException("\n" +
                        "\n Копіювати можливо лише місячні плани!!!");
            }

            PlanWorkLogic planLogic = new PlanWorkLogic(enConn, getUserProfile());
            return planLogic.copyPlanPriconnections(planCode, monthGen, yearGen,
                    soCode, elementCode);

        } catch (DatasourceConnectException e) {
            throw new EnergyproSystemException("planLogic.copyPlanPriconnections", e);
        } catch (PersistenceException e) {
            throw new EnergyproSystemException(e.getMessage(), e);
        } finally {
            try {
                if (enConn != null && !enConn.isClosed()) {
                    enConn.close();
                    enConn = null;
                }
            } catch (SQLException e) {
            }
        }
    }

    /**
     *  Изменение раздела и пункта ИнвестПрограммы на плане
     *
     *  @param planCode - код плана
     *  @param investGroupCode - код раздела ИнвестПрограммы
     *  @param investItemNumber - номер пункта ИнвестПрограммы
     *
     */
    public void updateInvestProgramData(int planCode, int investGroupCode, String investItemNumber)
    {
        try
        {
            ENPlanWorkDAO planDAO = new ENPlanWorkDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
            planDAO.updateInvestProgramData(planCode, investGroupCode, investItemNumber);
        }
        catch (DatasourceConnectException e)
        {
            throw new EnergyproSystemException(e.getMessage(), e);
        }
        catch (PersistenceException e)
        {
            throw new EnergyproSystemException(e.getMessage(), e);
        }
    }


    /**
    * Метод для изменения объекта в планах на АВР, созданных из CallCenter'а
    *
    * @param plan - объект плана (ENPlanWork)
    *
    */
    public void changeObjectForCallCenterAVRPlan(ENPlanWork plan)
    {
        if (plan.kind.code != ENPlanWorkKind.NPW) // Пока только с Заданий-Планов
        {
            throw new EnergyproSystemException("\n\nNET-4337 Ця операція припустима тільки для Завдань-планів! " +
                                            "На зв'язаних Місячних планах об'єкт буде змінено автоматично!");
        }

        if (plan.kind.code == ENPlanWorkKind.NPW && plan.status.code != ENPlanWorkStatus.GOOD)
        {
            throw new EnergyproSystemException("\n\nNET-4337 Ця операція припустима тільки для ЧОРНОВИХ Завдань-планів!");
        }

        try
        {
            ENPlanWorkDAO planDAO = new ENPlanWorkDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
            ENPlanWork oldPlanObj = planDAO.getObject(plan.code);

            int oldElementCode = oldPlanObj.elementRef.code;

            // Если объект не изменился, выходим
            if (plan.elementRef.code == oldPlanObj.elementRef.code)
            {
                return;
            }

            ElementLogic elementLogic = new ElementLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
            int elementType = elementLogic.getElementTypeByCode(oldPlanObj.elementRef.code);

            if (elementType != ENElementType.CALLCENTER_OBJECT)
            {
                throw new EnergyproSystemException("\n\nNET-4337 Ця операція припустима тільки для планів на віртуальні об'єкти CallCenter'у!");
            }

            // Проверяем новый объект на предмет допустимых типов элемента (ПЛ, ТП и т.д.)
            int newElementType = elementLogic.getElementTypeByCode(plan.elementRef.code);
            if (newElementType != ENElementType.LINE04 &&
            	newElementType != ENElementType.LINE10 &&
            	newElementType != ENElementType.LINE150 &&
            	newElementType != ENElementType.SUBSTATION04 &&
            	newElementType != ENElementType.SUBSTATION150 &&
            	newElementType != ENElementType.LINE_CABLE)
            {
            	throw new EnergyproSystemException("\n\nNET-4337 Ви обрали неприпустимий об'єкт планування! Код типу: " + newElementType);
            }

            // Изменяем код элемента на Задании-плане (т.е. объект) на новый
            oldPlanObj.elementRef.code = plan.elementRef.code;
            planDAO.save(oldPlanObj);

            // Ищем связанный Месячный план и изменяем объект на нем
            ENPlanCorrectHistoryDAO planHistoryDAO = new ENPlanCorrectHistoryDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
            ENPlanCorrectHistoryFilter planHistoryFilter = new ENPlanCorrectHistoryFilter();
            planHistoryFilter.planNewRef.code = oldPlanObj.code;
            planHistoryFilter.reason.code = ENPlanCorrectReason.MOVE_TO_NPW;
            ENPlanCorrectHistoryShortList planHistoryList = planHistoryDAO.getScrollableFilteredList(planHistoryFilter, 0, -1);

            if (planHistoryList.totalCount != 1)
            {
                throw new EnergyproSystemException("\n\nNET-4337 Помилка під час пошуку Місячного плану! Код Завдання-плану: " + oldPlanObj.code);
            }

            int monthPlanCode = planHistoryList.get(0).planOldRefCode;
            if (monthPlanCode == Integer.MIN_VALUE)
            {
                throw new EnergyproSystemException("\n\nNET-4337 Помилка під час пошуку Місячного плану! Код Завдання-плану: " + oldPlanObj.code);
            }

            ENPlanWork monthPlan = planDAO.getObject(monthPlanCode);
            if (monthPlan == null)
            {
                throw new EnergyproSystemException("\n\nNET-4337 Помилка під час пошуку Місячного плану!\n" +
                                                "Код Завдання-плану: " + oldPlanObj.code + ", " +
                                                "код Місячного плану: " + monthPlanCode);
            }

            // Изменяем код элемента на Месячном плане (т.е. объект) на новый
            monthPlan.elementRef.code = plan.elementRef.code;
            planDAO.save(monthPlan);

            /** 12.11.2014 +++ может быт не одно наряд-задание.... (гении из Высокополья)... */
            ENPlanWorkFilter pFilter = new ENPlanWorkFilter();
            pFilter.elementRef.code = oldElementCode;

            int pArr[] = planDAO.getFilteredCodeArrayNOSEGR(pFilter, 0, -1);
            for (int p = 0; p < pArr.length; p++) {
            	 ENPlanWork oldPlan = planDAO.getObject(pArr[p]);
            	 oldPlan.elementRef.code = plan.elementRef.code;

            	 planDAO.save(oldPlan);
            }


            // 07.03.14 Удаляем виртуальный объект, чтобы не засорять систему и никого не смущать непонятными объектами без планов на них
            ENCallCenterObjectDAO ccObjDAO = new ENCallCenterObjectDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

            ENCallCenterObjectFilter ccObjFilter = new ENCallCenterObjectFilter();
            ccObjFilter.element.code = oldElementCode;

            int[] ccObjArr = ccObjDAO.getFilteredCodeArray(ccObjFilter, 0, -1);

            if (ccObjArr.length > 0)
            {
                ccObjDAO.remove(ccObjArr[0]);
            }
        }
        catch (DatasourceConnectException e)
        {
            throw new EnergyproSystemException(e.getMessage(), e);
        }
        catch (PersistenceException e)
        {
            throw new EnergyproSystemException(e.getMessage(), e);
        }
    }


    /**
    * Метод для удаления плана на АВР, созданного из CallCenter'а
    *
    * @param ccDamageLogCode - код заявки CallCenter'а
    *
    */
    public void removeAVRPlanFromCallCenter(int ccDamageLogCode)
    {
    	if (ccDamageLogCode == Integer.MIN_VALUE)
    	{
    		throw new EnergyproSystemException("\n\nSUPP-13672 Не вказано код заявки з CallCenter'у!");
    	}

    	try
    	{
			ENPlanWork2CCDamageLogDAO plan2ccDAO = new ENPlanWork2CCDamageLogDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
			ENPlanWork2CCDamageLogFilter plan2ccFilter = new ENPlanWork2CCDamageLogFilter();
			plan2ccFilter.ccDamageLogCode = ccDamageLogCode;

			int[] plan2ccArr = plan2ccDAO.getFilteredCodeArray(plan2ccFilter, 0, -1);

			if (plan2ccArr.length > 0)
			{
				ENPlanCorrectHistoryDAO planHistoryDAO = new ENPlanCorrectHistoryDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
				ElementLogic elementLogic = new ElementLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
				//ENPlanWorkDAO planDAO = new ENPlanWorkDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
				//ENCallCenterObjectDAO ccObjDAO = new ENCallCenterObjectDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
				//ENCallCenterObjectDAO ccObjDAO = new ENCallCenterObjectDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
				PlanWorkLogic planLogic = new PlanWorkLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

				for (int i = 0; i < plan2ccArr.length; i++)
				{
					ENPlanWork2CCDamageLog plan2ccObj = plan2ccDAO.getObject(plan2ccArr[i]);
					ENPlanWork planObj = getObject(plan2ccObj.planRef.code);
			        if (planObj == null) continue;

					// код Задания-плана
					int planCode = planObj.code;
					int monthPlanCode;
					if (planObj.kind.code == ENPlanWorkKind.CURRENT) {
						monthPlanCode = planCode;
					} else {
						// Ищем связанный Месячный план
				        ENPlanCorrectHistoryFilter planHistoryFilter = new ENPlanCorrectHistoryFilter();
				        planHistoryFilter.planNewRef.code = planCode;
				        planHistoryFilter.reason.code = ENPlanCorrectReason.MOVE_TO_NPW;
				        ENPlanCorrectHistoryShortList planHistoryList = planHistoryDAO.getScrollableFilteredList(planHistoryFilter, 0, -1);

				        if (planHistoryList.totalCount != 1)
				        {
				            throw new EnergyproSystemException("\n\nSUPP-13672 Помилка під час пошуку Місячного плану! Код Завдання-плану: " + planCode);
				        }

				        monthPlanCode = planHistoryList.get(0).planOldRefCode;
				        if (monthPlanCode == Integer.MIN_VALUE)
				        {
				            throw new EnergyproSystemException("\n\nSUPP-13672 Помилка під час пошуку Місячного плану! Код Завдання-плану: " + planCode);
				        }

				        /*
				        ENPlanWork monthPlan = objectDAO.getObject(monthPlanCode);
				        if (monthPlan == null)
				        {
				            throw new EnergyproSystemException("\n\nSUPP-13672 Помилка під час пошуку Місячного плану!\n" +
				                                            "Код Завдання-плану: " + object.code + ", " +
				                                            "код Місячного плану: " + monthPlanCode);
				        }
				        */
					}

					///// 26.03.14
			        int elementCode = Integer.MIN_VALUE;

			        int elementType = elementLogic.getElementTypeByPlanCode(planCode);
					if (elementType == ENElementType.CALLCENTER_OBJECT)
					{
						elementCode = elementLogic.getElementCodeByPlanCode(planCode);
					}
					/////

			        // В начале вызываем "Видалити для коригування попереднього" для Задания-плана
			        if (planObj.kind.code != ENPlanWorkKind.CURRENT) {
			        	this.openPlanWork(planCode, true);
			        } else {
			        	ENPlanCorrectHistoryFilter planHistoryFilter = new ENPlanCorrectHistoryFilter();
				        planHistoryFilter.planOldRef.code = planCode;
				        planHistoryFilter.reason.code = ENPlanCorrectReason.MOVE_TO_NPW;
				        ENPlanCorrectHistoryShortList planHistoryList = planHistoryDAO.getScrollableFilteredList(planHistoryFilter, 0, -1);

				        for (int j = 0; j < planHistoryList.totalCount; j++){
				        	this.openPlanWork(planHistoryList.get(j).planNewRefCode, true);
				        }
			        }
			        // Если Задание-план успешно удалено, вызываем "Удалить" для Месячного плана
					this.remove(monthPlanCode);

				    ///// 26.03.14 Если объект в плане - "Виртуальный объект CallCenter'а", пытаемся его удалить
					if (elementCode > 0)
					{
						/*
						ENPlanWorkFilter planFilter = new ENPlanWorkFilter();
						planFilter.elementRef.code = elementCode;

						int[] planArr = planDAO.getFilteredCodeArrayNOSEGR(planFilter, 0, -1);

						// Если на объект нет планов, удаляем его
						if (planArr.length == 0)
						{
							ENCallCenterObjectFilter ccObjFilter = new ENCallCenterObjectFilter();
							ccObjFilter.element.code = elementCode;

							int[] ccObjArr = ccObjDAO.getFilteredCodeArray(ccObjFilter, 0, -1);

							for (int j = 0; j < ccObjArr.length; j++)
							{
								ccObjDAO.remove(ccObjArr[j]);
							}
						}
						*/
						planLogic.removeENCallCenterObjectByElementCode(elementCode);
					}
					/////

				}
			}

		}
    	catch (DatasourceConnectException e)
    	{
			throw new EnergyproSystemException(e.getMessage(), e);
		}
    	catch (PersistenceException e)
    	{
    		throw new EnergyproSystemException(e.getMessage(), e);
		}
    }


    public void editENIPImplementationType(int objPlanCode , int ipImplementationTypeCode)
    {

    	try
    	{
    		ENPlanWorkDAO planDAO = new ENPlanWorkDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
    		if (objPlanCode != Integer.MIN_VALUE && ipImplementationTypeCode != Integer.MIN_VALUE ) {
    	    	ENPlanWork planObj = planDAO.getObject(objPlanCode);
    	    	planObj.ipImplementTypeRef.code = ipImplementationTypeCode;
    	    	planDAO.save(planObj);
    	       }
    		else
    			throw new EnergyproSystemException("Помилка при визначенні плану, або типу освоєння ІП!!!");

		}
    	catch (PersistenceException e)
    	{
    		throw new EnergyproSystemException(e.getMessage(), e);
		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		}
    }


    public ENPlanWorkShortList getScrollableFilteredListIPitem2plan(ENPlanWorkFilter filterObject, int fromPosition, int quantity)
    {
     try
      {
       ENPlanWorkDAO objectDAO = new ENPlanWorkDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
       return objectDAO.getScrollableFilteredListIPitem2plan(filterObject, filterObject.conditionSQL, filterObject.orderBySQL, 0, quantity, null);

      }
     catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENPlanWork%} objects.",e);}
     catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
     finally                              {closeConnection();}
    }

    public void linkPlanWorksToCallCenter(int[] damageCodesList, int[] planWorkCodesList, String newspaperName, String newspaperNumber, Date datePubl, Date dateBegin, Date dateFinish, int needsApprovalByCustomer){
    try
    {

    	ENPlanWork2CCDamageLogDAO pw2damDAO = new ENPlanWork2CCDamageLogDAO(
    			getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
    	ENPlanWorkDAO pwDAO = new ENPlanWorkDAO(
    			getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

    	Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date(System.currentTimeMillis()));

    	for (int i=0; i<planWorkCodesList.length; i++){
    		for (int j=0; j<damageCodesList.length; j++){
	    		ENPlanWork2CCDamageLog pw2damObj = new ENPlanWork2CCDamageLog();
	    		pw2damObj.ccDamageLogCode = damageCodesList[j];
	    		pw2damObj.planRef.code = planWorkCodesList[i];
	    		pw2damObj.dateAdd = calendar.getTime();
	    		pw2damObj.userAdd = getUserProfile().userName;
	    		pw2damObj.newsPaperName = newspaperName;
	    		pw2damObj.newsPaperNumber = newspaperNumber;
	    		pw2damObj.datePubl = datePubl;
	    		pw2damObj.dateBegin = dateBegin;
	    		pw2damObj.dateFinish = dateFinish;
	    		pw2damObj.code = Integer.MIN_VALUE;

	    		// SUPP-87593 Признак "Погодження проведення планових робіт споживачем"
	    		pw2damObj.needsApprovalByCustomer = needsApprovalByCustomer;

	    		// 11.09.14 Если метод будет вызываться и для Энергосбытовых планов (а вдруг??),
	    		// то надо будет, наверное, тип связки передавать в linkPlanWorksToCallCenter параметром
	    		pw2damObj.typeRef.code = ENPlan2CCDamageLogType.AVR;

	    		pw2damDAO.add(pw2damObj);
    		}

    		// Для опубликованных отключений
    		if (datePubl!=null){

	    		// Находим минимальную дату dateBegin из связанных публикаций
	    		Date beginDateInPubl = null;
	    		Calendar calPubl = null;
	    		ENPlanWork2CCDamageLogFilter pw2damFilter = new ENPlanWork2CCDamageLogFilter();
	    		pw2damFilter.planRef.code = planWorkCodesList[i];
	    		pw2damFilter.conditionSQL = "datepubl is not null";
	        	pw2damFilter.orderBySQL = "datebegin";
	        	ENPlanWork2CCDamageLogShortList pw2damList = pw2damDAO.getScrollableFilteredList(pw2damFilter, 0, -1);
	        	if (pw2damList.size()>0){
		        	beginDateInPubl = pw2damList.get(0).dateBegin;
		        	calPubl = Calendar.getInstance();
		        	calPubl.setTime(beginDateInPubl);
		        	calPubl.set(Calendar.HOUR, 0);
		        	calPubl.set(Calendar.MINUTE, 0);
		        	calPubl.set(Calendar.SECOND, 0);
		        	calPubl.set(Calendar.MILLISECOND, 0);
		        	beginDateInPubl = calPubl.getTime();
	        	}

	        	//System.out.println("Publ date: "+pw2damObj.dateBegin);
	        	//System.out.println("Publ Clear date: "+beginDateInPubl);

	        	ENPlanWork pwObj = pwDAO.getObjectNOSEGR(planWorkCodesList[i]);
	        	//System.out.println("Plan date: "+pwObj.dateStart);

	 		    PlanWorkLogic pwLogic = new PlanWorkLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
		    	if (! pwLogic.checkPlanForSetCauseDisconnection(pwObj)){
	 		    		throw new EnergyproSystemException("План з кодом "+pwObj.code+" не передбачає відключеня споживачів");
	 	    	}

	        	if (pwObj.causeDisconnection!=1){
	        		pwObj.causeDisconnection=1;
	        		pwDAO.save(pwObj);
	        		pwObj = pwDAO.getObjectNOSEGR(pwObj.code);
	        	}

	        	Calendar calPlan = Calendar.getInstance();
	        	calPlan.setTime(pwObj.dateStart);
	        	calPlan.set(Calendar.HOUR, 0);
	        	calPlan.set(Calendar.MINUTE, 0);
	        	calPlan.set(Calendar.SECOND, 0);
	        	calPlan.set(Calendar.MILLISECOND, 0);
	        	Date beginDateInPlan = calPlan.getTime();

	        	//System.out.println("Plan clear date: "+beginDateInPlan);

	        	// Если дата по публикации и по плану отличается:
	        	if ( (beginDateInPubl!=null) && (calPubl!=null)
	        		&& (! beginDateInPlan.equals(beginDateInPubl) )
	        		// ... но месяц тот же. Иначе даты не меняем (SUPP-18529)
	        		&& (calPlan.get(Calendar.YEAR)==calPubl.get(Calendar.YEAR))
	        		&& (calPlan.get(Calendar.MONTH)==calPubl.get(Calendar.MONTH)) )
	        	{

	        		int daysDiff = new BigDecimal(beginDateInPubl.getTime() - beginDateInPlan.getTime()).divide(new BigDecimal(1000*60*60*24),2).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
	        		Calendar calTmp = Calendar.getInstance();

	        		// даты на самом плане
	        		calTmp.setTime(pwObj.dateStart);
	        		calTmp.add(Calendar.DATE, daysDiff);
	        		pwObj.dateStart = calTmp.getTime();

	        		calTmp.setTime(pwObj.dateFinal);
	        		calTmp.add(Calendar.DATE, daysDiff);
	        		pwObj.dateFinal = calTmp.getTime();

	        		pwDAO.save(pwObj);

	        		// FINExecutor2Plan - "Виконавці плану".
	        		// Именно по датам из этой таблицы производится пересчет в recalcTotalTime
	        		FINExecutor2PlanDAO execDAO = new FINExecutor2PlanDAO(
	        				getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
	        		FINExecutor2PlanFilter execFilter = new FINExecutor2PlanFilter();
	        		execFilter.planRef.code = pwObj.code;
	        		int[] execCodesList = execDAO.getFilteredCodeArray(execFilter, 0, -1);
	        		for (int j=0; j<execCodesList.length; j++){
	        			FINExecutor2Plan execObj = execDAO.getObject(execCodesList[j]);

	        			calTmp.setTime(execObj.dateStart);
	            		calTmp.add(Calendar.DATE, daysDiff);
	            		execObj.dateStart = calTmp.getTime();

	            		calTmp.setTime(execObj.dateFinal);
	            		calTmp.add(Calendar.DATE, daysDiff);
	            		execObj.dateFinal = calTmp.getTime();

	            		execDAO.save(execObj);
	        		}

	        		recalcTotalTime(pwObj.code);

	        	}

    		} // if (datePubl!=null)
    	} // for (int i=0; i<planWorkCodesList.length; i++)

    }
    catch (DatasourceConnectException e)
    {
        throw new EnergyproSystemException("Can't linkPlanWorksToCallCenter", e);
    }
    catch (PersistenceException e)
    {
        throw new EnergyproSystemException("Can't linkPlanWorksToCallCenter", e);
    }
    finally
    {
    	closeConnection();
    }

    }

    public void unlinkPlanWorksToCallCenter(int damageCode){
        try {

    	ENPlanWork2CCDamageLogDAO pw2damDAO = new ENPlanWork2CCDamageLogDAO(
    			getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
    	ENPlanWorkDAO pwDAO = new ENPlanWorkDAO(
    			getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

    	ENPlanWork2CCDamageLogFilter pw2damFilter = new ENPlanWork2CCDamageLogFilter();
    	pw2damFilter.ccDamageLogCode = damageCode;
    	int[] pw2damList = pw2damDAO.getFilteredCodeArray(pw2damFilter, 0, -1);

    	for (int i=0; i<pw2damList.length; i++){

    		ENPlanWork2CCDamageLog pw2damObj = pw2damDAO.getObject(pw2damList[i]);
    		ENPlanWork pwObj = pwDAO.getObject(pw2damObj.planRef.code);

    		pw2damDAO.remove(pw2damList[i]);

    		// Если это план "С отключением потребителей"
    		if (pwObj.causeDisconnection==1){
	    		ENPlanWork2CCDamageLogFilter pw2damRestFilter = new ENPlanWork2CCDamageLogFilter();
	    		pw2damRestFilter.planRef.code = pwObj.code;
	    		pw2damRestFilter.conditionSQL = "datepubl is not null";
	    		pw2damRestFilter.orderBySQL = "datebegin";

	    		ENPlanWork2CCDamageLogShortList pw2damRestList = pw2damDAO.getScrollableFilteredList(pw2damRestFilter, 0, -1);
	    		// Снимаем птичку, если больше связанных публикаций нет
	    		if (pw2damRestList.totalCount==0) {
	    			pwObj.causeDisconnection = 0;
		        	pwDAO.save(pwObj);
	    		}
    		}
    	}
        }
        catch (DatasourceConnectException e)
        {
            throw new EnergyproSystemException("Can't linkPlanWorksToCallCenter", e);
        }
        catch (PersistenceException e)
        {
            throw new EnergyproSystemException("Can't linkPlanWorksToCallCenter", e);
        }
        finally
        {
        	closeConnection();
        }
    }

   public void setCauseDisconnectionOn(int planCode) {
	   try {
		   PlanWorkLogic pwLogic = new PlanWorkLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

		   ENPlanWork plan = super.getObject(planCode);

		   if (plan.causeDisconnection==1){
			   throw new EnergyproSystemException("Ознака 'З відключенням споживачів' на цьому плані вже встановлена");
		   }

	    	if (! pwLogic.checkPlanForSetCauseDisconnection(plan)){
	    		throw new EnergyproSystemException("Цей план не передбачає відключеня споживачів");
	    	}

	    	plan.causeDisconnection = 1;
	    	super.save(plan);
	   }
	        catch (DatasourceConnectException e)
	        {
	            throw new EnergyproSystemException("Database connecting error", e);
	        }
	        finally
	        {
	        	closeConnection();
	        }
   }



	/**
	 *  Проверка возможности установить признак "С отключением потребителей"
	 */
	public void checkCauseDisconnectionOn(ENPlanWork planWork) {
		try {
			PlanWorkLogic pwLogic = new PlanWorkLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

			if (!pwLogic.checkPlanForSetCauseDisconnection(planWork)){
	    		throw new SystemException("\n\n"
	    				+ "Цей план не передбачає відключеня споживачів!");
	    	}

		} catch (DatasourceConnectException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}



   /**
    *
    * Присвоить значения исполнителя {@link FINExecutor} для плана {@link ENPlanWork}
    *
    * @param object сущность {@link ENPlanWork}
    * @param e сущность {@link ENElement}
    *
    * @throws PersistenceException
    * @throws DatasourceConnectException
    */
   private Integer setFinExecutor(ENPlanWork object, ENElement e) throws PersistenceException, DatasourceConnectException {
	   Connection conn = getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE);
	   FINExecutorLogic finExecLogic = new FINExecutorLogic(conn, getUserProfile());
	   return finExecLogic.setFinExecutorInPlan(object, e);
   }

   public void setCauseDisconnectionOff(int planCode) {
	   try {
		   PlanWorkLogic pwLogic = new PlanWorkLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

		   ENPlanWork plan = super.getObject(planCode);

		   if (plan.causeDisconnection==0){
			   throw new EnergyproSystemException("Ознака 'З відключенням споживачів' на цьому плані вже знята");
		   }

	    	if (! pwLogic.checkPlanForSetCauseDisconnection(plan)){
	    		throw new EnergyproSystemException("Цей план не передбачає відключеня споживачів");
	    	}

	    	plan.causeDisconnection = 0;
	    	super.save(plan);
	   }
	        catch (DatasourceConnectException e)
	        {
	            throw new EnergyproSystemException("Database connecting error", e);
	        }
	        finally
	        {
	        	closeConnection();
	        }
   }

	/** Возвращает код месячного плана по коду любого плана
	 * 	@param plancode
	 * 	@return plancode
	 */
	public int getMonthPlanCodeByAnyPlanCode(int plancode, boolean isException) {
		try {
			PlanWorkLogic planLogic = new PlanWorkLogic(
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE),
					getUserProfile());

			return planLogic.getMonthPlanCodeByAnyPlanCode(plancode, isException);

		} catch (DatasourceConnectException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}


	/**
	 *  Создание плана на услуги по договору на тех.надзор к договору на присоединение
	 *
	 *  @param finDocID - PK договора в Фин.кол.
	 *  @param techCondServicesCode - код договора на ТУ
	 *
	 *  @return planCode - код плана
	 */
	public int creatingPlanForServicesByTechAgreement(int finDocID, int techCondServicesCode) {
		int planCode = Integer.MIN_VALUE;
		int elementCode = Integer.MIN_VALUE;
		int sfsObjCode = Integer.MIN_VALUE;

		try {
			ENServicesFromSideObjectDAO sfsDao = new ENServicesFromSideObjectDAO(
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
			ENTechAgreement2ServicesObjectDAO ta2soDao = new ENTechAgreement2ServicesObjectDAO(
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

			ENServicesFromSideObject sfsObj;
			ENTechAgreement2ServicesObject ta2so = new ENTechAgreement2ServicesObject();

			ENServicesFromSideObjectFilter sfsFilter = new ENServicesFromSideObjectFilter();
			sfsFilter.finDocID = finDocID;

			ENTechAgreement2ServicesObjectFilter ta2soFilter = new ENTechAgreement2ServicesObjectFilter();
			ta2soFilter.finDocID = finDocID;

			int ta2soArr[] = ta2soDao.getFilteredCodeArray(ta2soFilter, 0, -1);
			if (ta2soArr.length > 0) {
				ta2so = ta2soDao.getObject(ta2soArr[0]);
			}

			/** если есть такой договор, используем его.... */
			int sfsArr[] = sfsDao.getFilteredCodeArray(sfsFilter, 0, -1);
			if (sfsArr.length > 0) {
				sfsObj = sfsDao.getObject(sfsArr[0]);
				sfsObjCode = sfsObj.code;
				elementCode = sfsObj.element.code;
			} else {
				/** создаем новый объект */
				sfsObj = new ENServicesFromSideObject();
				sfsObj.contractNumber = ta2so.contractNumber;
				sfsObj.contractDate = ta2so.contractDate;
				sfsObj.name = ta2so.partnerName;
				sfsObj.partnerCode = ta2so.partnerCode;
				sfsObj.finDocCode = ta2so.finDocCode;
				sfsObj.finDocID = ta2so.finDocID;
				sfsObj.commentGen = ta2so.commentGen;

				Context cnt = new InitialContext();
				Object objRef = cnt.lookup(ENServicesFromSideObjectController.JNDI_NAME);
				ENServicesFromSideObjectControllerHome sfsHome = (ENServicesFromSideObjectControllerHome) PortableRemoteObject
						.narrow(objRef, ENServicesFromSideObjectControllerHome.class);
				ENServicesFromSideObjectController sfsController = sfsHome.create();

				sfsObjCode = sfsController.add(sfsObj);

				elementCode = sfsDao.getObject(sfsObjCode).element.code;
			}

			/** создание плана */
			ENPlanWork plan = new ENPlanWork();

			Calendar calendar = Calendar.getInstance();
			calendar.setTime(new java.util.Date());

            plan.dateStart = calendar.getTime();
            plan.dateFinal = calendar.getTime();
            plan.yearGen = calendar.get(Calendar.YEAR);
            plan.monthGen = calendar.get(Calendar.MONTH) + 1;

			plan.elementRef.code = elementCode;

            plan.kind.code = ENPlanWorkKind.CURRENT;
            plan.formRef.code = ENPlanWorkForm.NOPLANNED;
            plan.status.code = ENPlanWorkStatus.GOOD;
            plan.stateRef.code = ENPlanWorkState.SERVICES_FROM_OUT;
            plan.typeRef.code = ENPlanWorkType.SERVICES_FROM_SIDE;

            plan.departmentRef.code = ENConsts.ENDEPARTMENT_VRTU;
            plan.budgetRef.code = ENConsts.ENBUDGET_VRTU;
            plan.responsibilityRef.code = ENConsts.ENRESPONSIBILITY_VRTUVD;

           plan.ddsCodes.code = RQConsts.RQDDSCODES_PROJECT_WORKS;

            planCode = this.add(plan);

            /** сохраним ссылку на договоре */
            ta2so.servicesFromSideRef.code = sfsObjCode;
            ta2soDao.save(ta2so);


            /** связка "план - договор на ТУ" */
            ENTechCond2PlanWorkDAO ty2plDAO = new ENTechCond2PlanWorkDAO(
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

            ENTechCond2PlanWork ty2pl = new ENTechCond2PlanWork();
            ty2pl.code = Integer.MIN_VALUE;
            ty2pl.planRef.code = planCode;
            ty2pl.techConServicesRef.code = techCondServicesCode;
            ty2plDAO.add(ty2pl);


            /** добовляем договор в договора подряда если план: ENPlanWorkKind.CURRENT и ENPlanWorkType.SERVICES_FROM_SIDE  */
            PlanWorkLogic planWorkLogic = new PlanWorkLogic(
            		getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

          	if((plan.kind.code == ENPlanWorkKind.CURRENT)  //Місячний
             		 && (plan.typeRef.code == ENPlanWorkType.SERVICES_FROM_SIDE)){ //Послуги зі сторони

          	     ENServicesObjectDAO soDAO = new ENServicesObjectDAO(
                 		getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
                 ENServicesObjectFilter soFilter = new ENServicesObjectFilter();
                 soFilter.conditionSQL = " code = (" +
                 		" select so2tc.servicesobjectrefcode " +
                 		" from enservicesobject2techcondtnsservices so2tc " +
                 		" where so2tc.techcondservrefcode = " + techCondServicesCode + ")";
                 int[] soArr = soDAO.getFilteredCodeArrayNOSEGR(soFilter, 0, -1);

                 if (soArr.length != 1) {
                 	throw new SystemException("\n\nПомилка під час пошуку договору! [techCondServicesCode = " + techCondServicesCode + "]!");
                 }

                 ENServicesObject sObject = soDAO.getObjectNoSegr(soArr[0]);

                 if (sObject == null) {
                 	throw new SystemException("\n\nПомилка під час пошуку договору! [techCondServicesCode = " + techCondServicesCode + "]!");
                 }

            	planWorkLogic.addFinDocIdFromENPlanWork(sObject.code, sObject.element.code, techCondServicesCode, finDocID);
            }

            return planCode;

		} catch (DatasourceConnectException e) {
			throw new SystemException(e.getMessage(), e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} catch (NamingException e) {
			throw new SystemException(e.getMessage(), e);
		} catch (RemoteException e) {
			throw new SystemException(e.getMessage(), e);
		} catch (CreateException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}


	/**
	 * Удаление плана из сменного задания Энергосбыта
	 * @param planCode - код плана
	 */
	public void removeFromWorkOrderByt(int planCode)
	{
		Context cnt;

		try
		{
			cnt = new InitialContext();

			Object objRef = cnt.lookup(ENWorkOrderBytItemController.JNDI_NAME);
			ENWorkOrderBytItemControllerHome bytItemHome = (ENWorkOrderBytItemControllerHome) PortableRemoteObject
					.narrow(objRef, ENWorkOrderBytItemControllerHome.class);
			ENWorkOrderBytItemController bytItemController = bytItemHome.create();

			ENWorkOrderBytItemFilter bytItemFilter = new ENWorkOrderBytItemFilter();
			bytItemFilter.planRef.code = planCode;

			int[] bytItemArr = bytItemController.getScrollableFilteredCodeArray(bytItemFilter, 0, -1);

			for (int i = 0; i < bytItemArr.length; i++)
			{
				bytItemController.remove(bytItemArr[i]);
			}
		}
		catch (NamingException e)
		{
			throw new SystemException(e.getMessage(), e);
		}
		catch (RemoteException e)
		{
			throw new SystemException(e.getMessage(), e);
		}
		catch (CreateException e)
		{
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}


    /**
     *  Изменение объекта планирования
     *  @param plan - объект плана (ENPlanWork)
     */
    public void changeObjectOfPlanning(ENPlanWork plan) {
		try {

			ENPlanWorkDAO planDAO = new ENPlanWorkDAO(
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE),
					getUserProfile());

			ENElementDAO elementDao = new ENElementDAO(
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE),
					getUserProfile());

			ENPlanWork oldPlanObj = planDAO.getObject(plan.code);

			// Если объект не изменился, выходим
			if ((plan.elementRef.code == oldPlanObj.elementRef.code) && !getUserProfile().userName.equals("energynet")  ) {
				return;
			}

			int oldElementType = elementDao.getObject(oldPlanObj.elementRef.code).typeRef.code;

			ENElement newElement = elementDao.getObject(plan.elementRef.code);
			int newElementType = newElement.typeRef.code;

			if (oldElementType != newElementType && !getUserProfile().userName.equals("energynet")) {
				throw new SystemException("\n\n"
						+ " Можлива заміна об’єктів тільки одного типу!!!");
			}


			ENPlanWorkFilter linkedPlansFilter = new ENPlanWorkFilter();
		    linkedPlansFilter.conditionSQL = " code in (" +
		      " select plannewrefcode from enplancorrecthistory where planrefcode = "
		          + oldPlanObj.code + // 1. Все дочерние. Сработает, если это - "корневой" план
		      " union " +
		      " select " + oldPlanObj.code + " as planrefcode " + // 2. Добавим себя
		      " union " +
		      " select planrefcode from enplancorrecthistory where plannewrefcode = "
		          + oldPlanObj.code + // 3. Присоедим "корневой", если это дочерний план
		      " union " +
		      " select plannewrefcode from enplancorrecthistory where planrefcode in " +
		      " (select planrefcode from enplancorrecthistory where plannewrefcode = "
		          + oldPlanObj.code + ")" + // 4. Все планы с таким же "корневым"
		      ")";

		    int planArr[] = planDAO.getFilteredCodeArray(linkedPlansFilter, 0, -1);


		    for (int i = 0; i < planArr.length; i++) {

		    	ENPlanWork planWork = planDAO.getObject(planArr[i]);

				/** если это факт - может быть акт... заменить объект в акте, если акт черновой..... */
				if (planWork.kind.code == ENPlanWorkKind.FACT) {
					ENActDAO actDao = new ENActDAO(
							getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE),
							getUserProfile());
					ENAct2ENPlanWorkDAO act2PlanDao = new ENAct2ENPlanWorkDAO(
							getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE),
							getUserProfile());

					ENAct2ENPlanWorkFilter act2PlanFilter = new ENAct2ENPlanWorkFilter();
					act2PlanFilter.plan.code = planWork.code;

					int act2PlanArr[] = act2PlanDao.getFilteredCodeArray(act2PlanFilter, 0, -1);
					for (int p = 0; p < act2PlanArr.length; p++) {
						ENAct2ENPlanWork act2Plan = act2PlanDao.getObject(act2PlanArr[p]);
						ENAct act = actDao.getObject(act2Plan.actRef.code);

						if (act.statusRef.code != ENActStatus.GOOD) {
							throw new SystemException("\n\n"
									+ " Акт повинен бути в статусі \"Чорновий\"!!! \n"
									+ " номер акту = " + act.numberGen + " від " + new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct));
						}

						act.element.code = plan.elementRef.code;
						actDao.save(act);
					}
				}

				/** для услуг со стороны меняем договор в заявке... */
				if (newElementType == ENElementType.SERVICES_FROM_SIDE_OBJECT) {

					ENServicesFromSideObjectDAO sfsDao = new ENServicesFromSideObjectDAO(
							getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

					ENServicesFromSideObjectFilter sfsFilter = new ENServicesFromSideObjectFilter();
					sfsFilter.element.code = plan.elementRef.code;

					int sfsArr[] = sfsDao.getFilteredCodeArray(sfsFilter, 0, -1);
					ENServicesFromSideObject sfsObject = sfsDao.getObject(sfsArr[0]);

					planWork.servicesFSideCnNum = sfsObject.contractNumber;
					planWork.servicesFSideFinId = sfsObject.finDocID;
					String partnerCode = sfsObject.partnerCode;


					RQOrgDAO finOrgDao = new RQOrgDAO(
							getUserProfile(), getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE));
					RQOrgDAO rqOrgDAO = new RQOrgDAO(
							getUserProfile(), getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

					RQOrgFilter orgFilter = new RQOrgFilter();
					orgFilter.codeorg = partnerCode;
					RQOrgShortList orgList = finOrgDao.getRQOrgList(orgFilter, 0, -1);

					if (orgList.totalCount > 0) {
						RQOrg org = rqOrgDAO.makeObjectFromShort(orgList.get(0));
						int orgCode = rqOrgDAO.add(org);

						RQOrderItemDAO oiDao = new RQOrderItemDAO(
								getUserProfile(), getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

						RQOrderItemFilter oiFilter = new RQOrderItemFilter();
						oiFilter.conditionSQL = " rqorderitem.code in ("
								+ " select o2e.orderitemcode from rqorderitem2enestimttm o2e where o2e.estimateitemcode in ("
								+ " select ei.code from enestimateitem ei where ei.planrefcode = " + planWork.code
								+ " and ei.statusrefcode = " + ENEstimateItemStatus.ORDERED + "))";

						int oiArr[] = oiDao.getFilteredCodeArray(oiFilter, 0, -1);
						for (int r = 0; r < oiArr.length; r++) {
							RQOrderItem oItem = oiDao.getObject(oiArr[r]);

							oItem.org.code = orgCode;
							oItem.contractNumber = sfsObject.contractNumber;
							oItem.contractDate = sfsObject.contractDate;
							oItem.finDocCode = sfsObject.finDocCode;
							oItem.finDocID = sfsObject.finDocID;

							oiDao.save(oItem);
						}
					}
				}

				planWork.elementRef.code = plan.elementRef.code;
				planWork.renRef.code = newElement.renRef.code;

				planDAO.save(planWork);
		    }

		} catch (DatasourceConnectException e) {
			throw new SystemException(e.getMessage(), e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}


	/**
	 * 	Изменение типа акта
	 *  @param plan - объект плана (ENPlanWork)
	 */
	public void changePlanState(ENPlanWork plan) {
		try {

			ENPlanWorkDAO planDAO = new ENPlanWorkDAO(
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE),
					getUserProfile());

			ENPlanWork oldPlanObj = planDAO.getObject(plan.code);

			// Если тип акта не изменился, выходим
			if (plan.stateRef.code == oldPlanObj.stateRef.code) {
				return;
			}

			ENPlanWorkFilter linkedPlansFilter = new ENPlanWorkFilter();
		    linkedPlansFilter.conditionSQL = " code in (" +
		      " select plannewrefcode from enplancorrecthistory where planrefcode = "
		          + oldPlanObj.code + // 1. Все дочерние. Сработает, если это - "корневой" план
		      " union " +
		      " select " + oldPlanObj.code + " as planrefcode " + // 2. Добавим себя
		      " union " +
		      " select planrefcode from enplancorrecthistory where plannewrefcode = "
		          + oldPlanObj.code + // 3. Присоедим "корневой", если это дочерний план
		      " union " +
		      " select plannewrefcode from enplancorrecthistory where planrefcode in " +
		      " (select planrefcode from enplancorrecthistory where plannewrefcode = "
		          + oldPlanObj.code + ")" + // 4. Все планы с таким же "корневым"
		      ")";

		    int planArr[] = planDAO.getFilteredCodeArray(linkedPlansFilter, 0, -1);
		    for (int i = 0; i < planArr.length; i++) {

		    	ENPlanWork planWork = planDAO.getObject(planArr[i]);

				if (planWork.kind.code == ENPlanWorkKind.FACT) {
					ENActDAO actDao = new ENActDAO(
							getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE),
							getUserProfile());
					ENAct2ENPlanWorkDAO act2PlanDao = new ENAct2ENPlanWorkDAO(
							getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE),
							getUserProfile());

					ENAct2ENPlanWorkFilter act2PlanFilter = new ENAct2ENPlanWorkFilter();
					act2PlanFilter.plan.code = planWork.code;

					int act2PlanArr[] = act2PlanDao.getFilteredCodeArray(act2PlanFilter, 0, -1);
					for (int p = 0; p < act2PlanArr.length; p++) {
						ENAct2ENPlanWork act2Plan = act2PlanDao.getObject(act2PlanArr[p]);
						ENAct act = actDao.getObject(act2Plan.actRef.code);

						if (act.statusRef.code != ENActStatus.GOOD) {
							throw new SystemException("\n\n"
									+ " Акт повинен бути в статусі \"Чорновий\"!!! \n"
									+ " номер акту = " + act.numberGen + " від " + new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct));
						}

						act.actTypeRef.code = plan.stateRef.code;
						actDao.save(act);
					}
				}

				planWork.stateRef.code = plan.stateRef.code;
				planDAO.save(planWork);
		    }

		} catch (DatasourceConnectException e) {
			throw new SystemException(e.getMessage(), e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}


	/**
	 * 	Изменение вида работ
	 *  @param plan - объект план (ENPlanWork)
	 */
	public void changePlanWorkForm(ENPlanWork plan) {
		try {

			ENPlanWorkDAO planDAO = new ENPlanWorkDAO(
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

			RQOrderItem2ENEstimateItemDAO i2eDAO = new RQOrderItem2ENEstimateItemDAO(
            		getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

			EstimateLogic estmateLogic = new EstimateLogic(
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

			ENEstimateItemDAO estmateDao = new ENEstimateItemDAO(
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

			PlanPurchaseLogic purchaseLogic = new PlanPurchaseLogic(
					getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

			ENPlanWork oldPlanObj = planDAO.getObject(plan.code);

			// Если вид работ не изменился, выходим
			if (plan.formRef.code == oldPlanObj.formRef.code) {
				return;
			}

			ENPlanWorkFilter linkedPlansFilter = new ENPlanWorkFilter();
		    linkedPlansFilter.conditionSQL = " code in (" +
		      " select plannewrefcode from enplancorrecthistory where planrefcode = "
		          + oldPlanObj.code + // 1. Все дочерние. Сработает, если это - "корневой" план
		      " union " +
		      " select " + oldPlanObj.code + " as planrefcode " + // 2. Добавим себя
		      " union " +
		      " select planrefcode from enplancorrecthistory where plannewrefcode = "
		          + oldPlanObj.code + // 3. Присоедим "корневой", если это дочерний план
		      " union " +
		      " select plannewrefcode from enplancorrecthistory where planrefcode in " +
		      " (select planrefcode from enplancorrecthistory where plannewrefcode = "
		          + oldPlanObj.code + ")" + // 4. Все планы с таким же "корневым"
		      ")";

		    int planArr[] = planDAO.getFilteredCodeArray(linkedPlansFilter, 0, -1);
		    for (int i = 0; i < planArr.length; i++) {

		    	ENPlanWork planWork = planDAO.getObject(planArr[i]);
		    	planWork.formRef.code = plan.formRef.code;

		    	ENEstimateItemFilter eFilter = new ENEstimateItemFilter();
		    	eFilter.planRef.code = plan.code;
		    	eFilter.kindRef.code = ENEstimateItemKind.MATERIALS;

		    	int eArr[] = estmateDao.getFilteredCodeArray(eFilter, 0, -1);
		    	for (int e = 0; e < eArr.length; e++) {
		    		ENEstimateItem estimate = estmateDao.getObject(eArr[e]);

		    		RQOrderItem2ENEstimateItemFilter i2eFilter = new RQOrderItem2ENEstimateItemFilter();
	                i2eFilter.estimateItem.code = estimate.code;
	                int[] i2eArr = i2eDAO.getFilteredCodeArray(i2eFilter, 0, -1);
	                if (i2eArr.length > 0) {
	                    throw new EnergyproSystemException("\n\n"
	                    		+ "Матеріал з цього плану вже замовлений...");
	                }

		    		if (estimate.countFact.doubleValue() > 0
		    				&& estimate.statusRef.code == ENEstimateItemStatus.PLANNED) {
				    	if (plan.formRef.code == ENPlanWorkForm.PLANNED) {
				    		estmateLogic.checkInRQOrder(estimate, true, planWork);
				    	}
		    		}
		    	}

				planDAO.save(planWork);

				// план закупок проверка на перенос плановых планов в год в котором есть план закупок
				if( purchaseLogic.checkHavePlanPurchaseByEstimate(planWork.code)  > 0  ) {
						throw new EnergyproSystemException(" \n PlanWorkLogic.movePlanWork  \n \n Вже складений річний план закупівель. Змінювати вид робіт на \"Плановий\" заборонено. ");
				}
		    }

		} catch (DatasourceConnectException e) {
			throw new SystemException(e.getMessage(), e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}


	public void changePlanDateForByt(int planCode, Date newDate)
	{
		try {

			PlanWorkLogic planLogic = new PlanWorkLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
			planLogic.changePlanDateForByt(planCode, newDate);

		} catch (DatasourceConnectException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}


	public void saveAttributes(ENPlanWork object) {
		this.saveAttributes(object, false);
	}

	public void saveAttributes(ENPlanWork object, boolean forAllPlans)
	{
		try {
			ENPlanWorkDAO planDao = new ENPlanWorkDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
			ENElementDAO elDao = new ENElementDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
			ENPlanwork2GeneralContractsDAO p2genDAO = new ENPlanwork2GeneralContractsDAO(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

			ENPlanWork oldPlan = planDao.getObject(object.code);

			if(oldPlan.kind.code != ENPlanWorkKind.CURRENT) {
				throw new SystemException("Ця операція припустима тільки для місячних планів");
			}

			if ((oldPlan.typeRef.code != ENPlanWorkType.SERVICES_FROM_SIDE
					&& oldPlan.typeRef.code != ENPlanWorkType.SERVICES_FROM_SIDE_INVEST)
					&& !forAllPlans)
			{
				if (! getUserProfile().userName.equalsIgnoreCase("energynet")) {
					throw new SystemException("Ця операція припустима тільки для планів з послуг зі сторони");
				}
			}

			ENElement e = elDao.getObject(object.elementRef.code);

			this.setFinExecutor(object, e);

			//// this.setContract SUPP-84293
/*	zzzzz покашо не будем тут договор трогать 		if(object.typeRef.code == ENPlanWorkType.TMC_TRANSFER && object.servicesFSideCnNum != null  ){
		    	boolean isCustomer = false;
				boolean isException = true;
		    	ContractLogic contractLogic = new ContractLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());

		    	// получим код договора со вставкой в engeneralcontract
		    	int generalContractCode = contractLogic.addByContractNumber(object.servicesFSideCnNum, object.partnerCode, object.finDocCode, isCustomer, isException);



				if (generalContractCode == Integer.MIN_VALUE ){
					throw new EnergyproSystemException("Не знайдено договір в ФК " +  object.servicesFSideCnNum + "!!!" );
				}

				ENPlanwork2GeneralContractsFilter p2genFil = new ENPlanwork2GeneralContractsFilter();
				p2genFil.planRef.code = object.code;
				int[] p2genArr = p2genDAO.getFilteredCodeArray(p2genFil, 0, -1);

				for (int i = 0; i < p2genArr.length; i++) {
					p2genDAO.remove(p2genArr[i]);
				}

				ENPlanwork2GeneralContracts p2gen = new ENPlanwork2GeneralContracts();
		    	p2gen.dateEdit=new Date();
		    	p2gen.generalContractRef.code=generalContractCode;
		    	p2gen.planRef.code=object.code;
		    	p2gen.userGen=getUserProfile().userName;
		    	p2genDAO.add(p2gen);
		    }*/

			planDao.save(object);

		} catch (DatasourceConnectException e) {
			throw new SystemException(e.getMessage(), e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	public int makePlanDisconnectionSupplier(int elementCode, int tkCode, Date planDate, FINExecutor finExecutor,
			String masterCode, String masterName, String mechanicCode, String mechanicName) {
		return makePlanDisconnectionSupplier(elementCode, tkCode, planDate, finExecutor,
				masterCode, masterName, mechanicCode, mechanicName, Integer.MIN_VALUE , Integer.MIN_VALUE );
	}

	public int makePlanDisconnectionSupplier(int elementCode, int tkCode, Date planDate, FINExecutor finExecutor,
			String masterCode, String masterName, String mechanicCode, String mechanicName, int dfPackCode , int departmentCode  
			) {
		return makePlanDisconnectionSupplier(elementCode, tkCode, planDate, finExecutor,
				masterCode, masterName, mechanicCode, mechanicName, Integer.MIN_VALUE , Integer.MIN_VALUE , Integer.MIN_VALUE );
	}
	public int makePlanDisconnectionSupplier(int elementCode, int tkCode, Date planDate, FINExecutor finExecutor,
			String masterCode, String masterName, String mechanicCode, String mechanicName, int dfPackCode , int departmentCode , 
			int budgetCode) {
		try {
			PlanWorkLogic planLogic = new PlanWorkLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
			return planLogic.makePlanDisconnectionSupplier(elementCode, tkCode, planDate, finExecutor,
					masterCode, masterName, mechanicCode, mechanicName, dfPackCode , departmentCode , budgetCode );
		} catch (DatasourceConnectException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}

	public void removePlanDisconnectionSupplier(int planCode) {
		try {
			PlanWorkLogic planLogic = new PlanWorkLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
			planLogic.removePlanDisconnectionSupplier(planCode);
		} catch (DatasourceConnectException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			closeConnection();
		}
	}


	/*пересчитать время на работе и хуменах */
	  public void recalcPlanworkItemAndHumenItemsByPlanItemCode(int planworkItemCode )
	   {
	    try
	     {


	      ENPlanWorkItem2TKKoefDAO objectDAO = new ENPlanWorkItem2TKKoefDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

	      ///// 02.07.12 NET-2345 Запрет добавления более одного корректирующего коэффициента для работ на плане
/*			      ENPlanWorkItem2TKKoefFilter koefFilter = new ENPlanWorkItem2TKKoefFilter();
	      koefFilter.planWorkItemRef.code = object.planWorkItemRef.code;
	      ENPlanWorkItem2TKKoefShortList koefList = objectDAO.getScrollableFilteredList(koefFilter, 0, -1);

	      if (koefList.totalCount > 0)
	      {
	    	  throw new EnergyproSystemException("NET-2345 Заборонено використовувати більше одного корегуючого коефіцієнта для роботи!");
	      }*/
	      /////



	      new PlanWorkItemLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE) ,getUserProfile()).updateCoef(planworkItemCode);

	      // время на людях ..
	      new HumenLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE) ,getUserProfile()).recalcHumenItemsByPlanItemCode(planworkItemCode);


	     }
	    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't add {%com.ksoe.energynet.valueobject.ENPlanWorkItem2TKKoef%} object.",e);}
	    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
	    finally                              {closeConnection();}
	   }


	  public void saveComment(int aENPlanWorkCode,String comment)
	  {
		    try
		     {
		      ENPlanWorkDAO objectDAO = new ENPlanWorkDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
		      ENPlanWork object=objectDAO.getObject(aENPlanWorkCode);
		      object.setDomain_info(null);
		      object.commentGen=comment;
		      objectDAO.save(object);
		     }
		    catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't save {%com.ksoe.energynet.valueobject.ENWorkOrder%} object.",e);}
		    catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
		    finally                              {closeConnection();}
	  }

	  public ENPlanWorkBillingShortList getScrollableBillingFilteredList(ENPlanWorkFilter filterObject, int fromPosition, int quantity)
	  {
	   try
	    {
	     ENPlanWorkDAO objectDAO = new ENPlanWorkDAO(getUserProfile(),getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));
	     return objectDAO.getScrollableBillingFilteredList(filterObject, fromPosition, quantity, null);
	    }
	   catch (DatasourceConnectException e) {throw new EnergyproSystemException("Can't get list of {%com.ksoe.energynet.valueobject.ENPlanWork%} objects.",e);}
	   catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
	   finally                              {closeConnection();}
	  }

	  public boolean isPlanForRepairRequest(ENPlanWork plan) {
		  try {
			  PlanWorkLogic planLogic = new PlanWorkLogic(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), getUserProfile());
			  return planLogic.isPlanForRepairRequest(plan);
		  } catch (DatasourceConnectException e) {
			  throw new EnergyproSystemException(e.getMessage(), e);
		  } finally {
			  closeConnection();
		  }
	  }


	  /* ENPlanWork. Получить список для просмотра по фильтру */

		public ENPlanWorkShortList getScrollableFilteredListServicesFromSide(
				ENPlanWorkFilter filterObject, int fromPosition, int quantity) {
			try {
				ENPlanWorkDAO objectDAO = new ENPlanWorkDAO(
						getUserProfile(), getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE));

				return objectDAO.getScrollableFilteredListServicesFromSide(filterObject,filterObject.conditionSQL,filterObject.orderBySQL, fromPosition, quantity, null);


			} catch (DatasourceConnectException e) {
				throw new EnergyproSystemException(
						"Can't get list of {%com.ksoe.energynet.valueobject.ENPlanWork%} objects.", e);
			} catch (PersistenceException e) {
				throw new EnergyproSystemException(e.getMessage(), e);
			}
			finally {
				closeConnection();
			}
		}

} // end of EJB Controller for ENPlanWork

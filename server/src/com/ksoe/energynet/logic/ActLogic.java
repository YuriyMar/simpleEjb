package com.ksoe.energynet.logic;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.sql.DataSource;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.docflow.logic.DFConsts;
import com.ksoe.docflow.logic.DocFlowLogic;
import com.ksoe.docflow.logic.signing.DocSigningLogic;
import com.ksoe.docflow.valueobject.DFDoc;
import com.ksoe.docflow.valueobject.DFDocSigner;
import com.ksoe.energynet.dataminer.ENAct2DFDocDAO;
import com.ksoe.energynet.dataminer.ENAct2ENPlanWorkDAO;
import com.ksoe.energynet.dataminer.ENAct2FinInfoProvDAO;
import com.ksoe.energynet.dataminer.ENAct2FinKodIstDAO;
import com.ksoe.energynet.dataminer.ENAct2HumenDAO;
import com.ksoe.energynet.dataminer.ENAct2OSTableDAO;
import com.ksoe.energynet.dataminer.ENAct2ProvDAO;
import com.ksoe.energynet.dataminer.ENAct2RQFKOrderDAO;
import com.ksoe.energynet.dataminer.ENAct2TransportDAO;
import com.ksoe.energynet.dataminer.ENActDAO;
import com.ksoe.energynet.dataminer.ENActIncomServ2ENActDAO;
import com.ksoe.energynet.dataminer.ENActIncomeServicesDAO;
import com.ksoe.energynet.dataminer.ENActPostingKindDAO;
import com.ksoe.energynet.dataminer.ENConnectionKindDAO;
import com.ksoe.energynet.dataminer.ENContragentDAO;
import com.ksoe.energynet.dataminer.ENCountersStateVerificationDAO;
import com.ksoe.energynet.dataminer.ENDeliveryTimeDAO;
import com.ksoe.energynet.dataminer.ENDepartment2EPRenDAO;
import com.ksoe.energynet.dataminer.ENDepartmentDAO;
import com.ksoe.energynet.dataminer.ENEstimateItem2PlanDAO;
import com.ksoe.energynet.dataminer.ENEstimateItemDAO;
import com.ksoe.energynet.dataminer.ENGeneralContractsDAO;
import com.ksoe.energynet.dataminer.ENHumenItemDAO;
import com.ksoe.energynet.dataminer.ENMetrologyCounterDAO;
import com.ksoe.energynet.dataminer.ENMolDAO;
import com.ksoe.energynet.dataminer.ENPlan2HumenDAO;
import com.ksoe.energynet.dataminer.ENPlanWorkDAO;
import com.ksoe.energynet.dataminer.ENPlanWorkItem2HumenDAO;
import com.ksoe.energynet.dataminer.ENPlanWorkStateDAO;
import com.ksoe.energynet.dataminer.ENPlanWorkTypeDAO;
import com.ksoe.energynet.dataminer.ENReconstrModernOZ2ENactDAO;
import com.ksoe.energynet.dataminer.ENReconstrModernOZDAO;
import com.ksoe.energynet.dataminer.ENServicesObjectDAO;
import com.ksoe.energynet.dataminer.ENStandardConstDAO;
import com.ksoe.energynet.dataminer.ENTechConditionsServicesDAO;
import com.ksoe.energynet.dataminer.ENTransportItemDAO;
import com.ksoe.energynet.dataminer.ENWorkOrder2ENPlanWorkDAO;
import com.ksoe.energynet.dataminer.FINMaterialsDAO;
import com.ksoe.energynet.dataminer.FINMolDataDAO;
import com.ksoe.energynet.dataminer.FINWorkerDAO;
import com.ksoe.energynet.dataminer.FinKodIstDAO;
import com.ksoe.energynet.dataminer.SCCounterDAO;
import com.ksoe.energynet.dataminer.SCSealDAO;
import com.ksoe.energynet.dataminer.SCUsageInputDAO;
import com.ksoe.energynet.dataminer.SCUsageInputItemDAO;
import com.ksoe.energynet.dataminer.SCUsageInputItemOZ2ENActDAO;
import com.ksoe.energynet.dataminer.SCUsageInputItemOZDAO;
import com.ksoe.energynet.ejb.ENAct2RQFKOrderController;
import com.ksoe.energynet.ejb.ENAct2RQFKOrderControllerHome;
import com.ksoe.energynet.ejb.ENServicesObjectController;
import com.ksoe.energynet.ejb.ENServicesObjectControllerHome;
import com.ksoe.energynet.util.LoggableStatement;
import com.ksoe.energynet.util.Tools;
import com.ksoe.energynet.valueobject.ENAct;
import com.ksoe.energynet.valueobject.ENAct2DFDoc;
import com.ksoe.energynet.valueobject.ENAct2ENPlanWork;
import com.ksoe.energynet.valueobject.ENAct2FinKodIst;
import com.ksoe.energynet.valueobject.ENAct2Humen;
import com.ksoe.energynet.valueobject.ENAct2OSTable;
import com.ksoe.energynet.valueobject.ENAct2Prov;
import com.ksoe.energynet.valueobject.ENAct2Transport;
import com.ksoe.energynet.valueobject.ENActIncomServ2ENAct;
import com.ksoe.energynet.valueobject.ENActIncomeServices;
import com.ksoe.energynet.valueobject.ENActPostingKind;
import com.ksoe.energynet.valueobject.ENActStatus;
import com.ksoe.energynet.valueobject.ENConnectionKind;
import com.ksoe.energynet.valueobject.ENCountersStateVerification;
import com.ksoe.energynet.valueobject.ENDepartment;
import com.ksoe.energynet.valueobject.ENElement;
import com.ksoe.energynet.valueobject.ENElementType;
import com.ksoe.energynet.valueobject.ENEstimateItem;
import com.ksoe.energynet.valueobject.ENEstimateItem2PlanType;
import com.ksoe.energynet.valueobject.ENEstimateItemKind;
import com.ksoe.energynet.valueobject.ENEstimateItemStatus;
import com.ksoe.energynet.valueobject.ENGeneralContracts;
import com.ksoe.energynet.valueobject.ENHumenItemKind;
import com.ksoe.energynet.valueobject.ENMetrologyCounter;
import com.ksoe.energynet.valueobject.ENMol;
import com.ksoe.energynet.valueobject.ENMolStatus;
import com.ksoe.energynet.valueobject.ENMolType;
import com.ksoe.energynet.valueobject.ENPlanWork;
import com.ksoe.energynet.valueobject.ENPlanWorkItem2Humen;
import com.ksoe.energynet.valueobject.ENPlanWorkState;
import com.ksoe.energynet.valueobject.ENPlanWorkType;
import com.ksoe.energynet.valueobject.ENReconstrModernOZ;
import com.ksoe.energynet.valueobject.ENReconstrModernOZ2ENact;
import com.ksoe.energynet.valueobject.ENServicesContractKind;
import com.ksoe.energynet.valueobject.ENServicesContractStatus;
import com.ksoe.energynet.valueobject.ENServicesContractType;
import com.ksoe.energynet.valueobject.ENServicesObject;
import com.ksoe.energynet.valueobject.ENServicesObjectCalcType;
import com.ksoe.energynet.valueobject.ENServicesObjectStatus;
import com.ksoe.energynet.valueobject.ENStandardConst;
import com.ksoe.energynet.valueobject.ENTechConditionsServices;
import com.ksoe.energynet.valueobject.ENWorkOrder;
import com.ksoe.energynet.valueobject.FINChargeType;
import com.ksoe.energynet.valueobject.FINMaterials;
import com.ksoe.energynet.valueobject.FINMaterialsStatus;
import com.ksoe.energynet.valueobject.FINMolData;
import com.ksoe.energynet.valueobject.FINMolType;
import com.ksoe.energynet.valueobject.FINWorker;
import com.ksoe.energynet.valueobject.FINWorkerKind;
import com.ksoe.energynet.valueobject.FinKodIst;
import com.ksoe.energynet.valueobject.SCCounter;
import com.ksoe.energynet.valueobject.SCCounterKind;
import com.ksoe.energynet.valueobject.SCCounterStatus;
import com.ksoe.energynet.valueobject.SCSeal;
import com.ksoe.energynet.valueobject.SCUsageInput;
import com.ksoe.energynet.valueobject.SCUsageInputItem;
import com.ksoe.energynet.valueobject.SCUsageInputItemKind;
import com.ksoe.energynet.valueobject.SCUsageInputItemOZ;
import com.ksoe.energynet.valueobject.brief.ENAct2HumenShort;
import com.ksoe.energynet.valueobject.brief.ENAct2OSTableShort;
import com.ksoe.energynet.valueobject.brief.ENAct2TransportShort;
import com.ksoe.energynet.valueobject.brief.FINMaterialsShort;
import com.ksoe.energynet.valueobject.brief.FKTrans2AXTransItemShort;
import com.ksoe.energynet.valueobject.brief.SCCounterShort;
import com.ksoe.energynet.valueobject.brief.SCSealShort;
import com.ksoe.energynet.valueobject.filter.ENAct2DFDocFilter;
import com.ksoe.energynet.valueobject.filter.ENAct2ENPlanWorkFilter;
import com.ksoe.energynet.valueobject.filter.ENAct2FinInfoProvFilter;
import com.ksoe.energynet.valueobject.filter.ENAct2FinKodIstFilter;
import com.ksoe.energynet.valueobject.filter.ENAct2HumenFilter;
import com.ksoe.energynet.valueobject.filter.ENAct2OSTableFilter;
import com.ksoe.energynet.valueobject.filter.ENAct2ProvFilter;
import com.ksoe.energynet.valueobject.filter.ENAct2RQFKOrderFilter;
import com.ksoe.energynet.valueobject.filter.ENAct2TransportFilter;
import com.ksoe.energynet.valueobject.filter.ENActFilter;
import com.ksoe.energynet.valueobject.filter.ENActIncomServ2ENActFilter;
import com.ksoe.energynet.valueobject.filter.ENActPostingKindFilter;
import com.ksoe.energynet.valueobject.filter.ENConnectionKindFilter;
import com.ksoe.energynet.valueobject.filter.ENContragentFilter;
import com.ksoe.energynet.valueobject.filter.ENCountersStateVerificationFilter;
import com.ksoe.energynet.valueobject.filter.ENDeliveryTimeFilter;
import com.ksoe.energynet.valueobject.filter.ENDepartment2EPRenFilter;
import com.ksoe.energynet.valueobject.filter.ENDepartmentFilter;
import com.ksoe.energynet.valueobject.filter.ENEstimateItem2PlanFilter;
import com.ksoe.energynet.valueobject.filter.ENEstimateItemFilter;
import com.ksoe.energynet.valueobject.filter.ENHumenItemFilter;
import com.ksoe.energynet.valueobject.filter.ENMetrologyCounterFilter;
import com.ksoe.energynet.valueobject.filter.ENMolFilter;
import com.ksoe.energynet.valueobject.filter.ENPlan2HumenFilter;
import com.ksoe.energynet.valueobject.filter.ENPlanWorkFilter;
import com.ksoe.energynet.valueobject.filter.ENPlanWorkItem2HumenFilter;
import com.ksoe.energynet.valueobject.filter.ENPlanWorkStateFilter;
import com.ksoe.energynet.valueobject.filter.ENReconstrModernOZ2ENactFilter;
import com.ksoe.energynet.valueobject.filter.ENTransportItemFilter;
import com.ksoe.energynet.valueobject.filter.ENWorkOrder2ENPlanWorkFilter;
import com.ksoe.energynet.valueobject.filter.FINMaterialsFilter;
import com.ksoe.energynet.valueobject.filter.FINMolDataFilter;
import com.ksoe.energynet.valueobject.filter.FINWorkerFilter;
import com.ksoe.energynet.valueobject.filter.SCCounterFilter;
import com.ksoe.energynet.valueobject.filter.SCSealFilter;
import com.ksoe.energynet.valueobject.filter.SCUsageInputFilter;
import com.ksoe.energynet.valueobject.filter.SCUsageInputItemFilter;
import com.ksoe.energynet.valueobject.filter.SCUsageInputItemOZ2ENActFilter;
import com.ksoe.energynet.valueobject.filter.SCUsageInputItemOZFilter;
import com.ksoe.energynet.valueobject.lists.ENAct2ENPlanWorkShortList;
import com.ksoe.energynet.valueobject.lists.ENAct2FinKodIstShortList;
import com.ksoe.energynet.valueobject.lists.ENAct2HumenShortList;
import com.ksoe.energynet.valueobject.lists.ENAct2OSTableShortList;
import com.ksoe.energynet.valueobject.lists.ENAct2ProvShortList;
import com.ksoe.energynet.valueobject.lists.ENAct2TransportShortList;
import com.ksoe.energynet.valueobject.lists.ENActShortList;
import com.ksoe.energynet.valueobject.lists.ENContragentShortList;
import com.ksoe.energynet.valueobject.lists.ENDeliveryTimeShortList;
import com.ksoe.energynet.valueobject.lists.ENDepartment2EPRenShortList;
import com.ksoe.energynet.valueobject.lists.ENEstimateItemShortList;
import com.ksoe.energynet.valueobject.lists.ENGeneralContractsShortList;
import com.ksoe.energynet.valueobject.lists.ENHumenItemShortList;
import com.ksoe.energynet.valueobject.lists.ENPlanWorkItem2HumenShortList;
import com.ksoe.energynet.valueobject.lists.ENPlanWorkStateShortList;
import com.ksoe.energynet.valueobject.lists.ENServicesObjectShortList;
import com.ksoe.energynet.valueobject.lists.ENTransportItemShortList;
import com.ksoe.energynet.valueobject.lists.ENWorkOrder2ENPlanWorkShortList;
import com.ksoe.energynet.valueobject.lists.FINMaterialsShortList;
import com.ksoe.energynet.valueobject.lists.FINWorkerShortList;
import com.ksoe.energynet.valueobject.lists.FKTrans2AXTransItemShortList;
import com.ksoe.energynet.valueobject.lists.SCCounterShortList;
import com.ksoe.energynet.valueobject.lists.SCSealShortList;
import com.ksoe.energynet.valueobject.lists.SCUsageInputItemOZ2ENActShortList;
import com.ksoe.energynet.valueobject.lists.SCUsageInputItemShortList;
import com.ksoe.energypro.exception.EnergyproBusinessException;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.energypro.logic.LogicModule;
import com.ksoe.fin.logic.FKLogic;
import com.ksoe.fin.logic.FKOSLogic;
import com.ksoe.fin.logic.FKPostingLogic;
import com.ksoe.fin.valueobject.FKProvResult;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;
import com.ksoe.lla.security.UserProfile;
import com.ksoe.rqorder.dataminer.RQFKOrderDAO;
import com.ksoe.rqorder.valueobject.RQFKOrder;
import com.ksoe.rqorder.valueobject.RQFKOrderKind;
import com.ksoe.rqorder.valueobject.RQFKOrderStatus;
import com.ksoe.techcard.dataminer.TKAccountingTypeDAO;
import com.ksoe.techcard.dataminer.TKFINWorkTypeDAO;
import com.ksoe.techcard.dataminer.TKTransportRealDAO;
import com.ksoe.techcard.logic.TechCardLogic;
import com.ksoe.techcard.valueobject.TKAccountingType;
import com.ksoe.techcard.valueobject.TKCalcKind;
import com.ksoe.techcard.valueobject.TKTransportStatus;
import com.ksoe.techcard.valueobject.TKTransportType;
import com.ksoe.techcard.valueobject.filter.TKTransportRealFilter;
import com.ksoe.techcard.valueobject.lists.TKTransportRealShortList;

public class ActLogic extends LogicModule{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;


	/**
	 *
	 * ���������� ���� �� ������� ��� ���� (������ ����, �� ��������)
	 *
	 * SUPP-67738 ��������� ������� �������� - ������ ����� ����������
	 * � �� ������� ��� ��� - ��� ��� ��� �������� ���������
	 * � ������ ���� ����� ������������� ����� ��
	 *
	 * @param actCode ��� ���� {@link ENAct}
	 * @param isException ���������� �� ������������ ����������
	 * @return 0 - ���, 1 - ����
	 * @throws PersistenceException
	 */
	public int checkInSCCounterByActCode(int actCode, boolean isException) throws PersistenceException {
		return this.checkInSCCounterByActCode(actCode, isException, true);
	}

	public int checkInSCCounterByActCode(int actCode, boolean isException, Boolean isMount) throws PersistenceException {
		return this.checkInSCCounterByActCode(actCode, isException, isMount, false);
	}

	/**
	 *
	 * ���������� ���� �� ������� ��� ���� (���� � �����)
	 *
	 * SUPP-67738 ��������� ������� �������� - ������ ����� ����������
	 * � �� ������� ��� ��� - ��� ��� ��� �������� ���������
	 * � ������ ���� ����� ������������� ����� ��
	 *
	 * @param actCode ��� ���� {@link ENAct}
	 * @param isException ���������� �� ������������ ����������
	 * @param isMount {@code true} - ���� �� ������� �� ����, {@code false} - ���� �� ������� �� �����
	 * , {@code null} - ���� �� ����� �������
	 * @param doNotCheckProm {@code true} - ����������� �������� �� ������������ - ����� ������ ����� ���������� 0, {@code false} - ��������� �������� �� ������������,
	 * ����� ����������� ������ �������� � ��������� ��������� ��� ������������� �������
	 * @return 0 - ���, 1 - ����
	 * @throws PersistenceException
	 */
    public int checkInSCCounterByActCode(int actCode, boolean isException, Boolean isMount, boolean doNotCheckProm) throws PersistenceException
    {

    	/**
		 *  SUPP-65483... 06.09.2017... +++ ���������� �������� ������������� �������...
		 *  ��� ��-���� ������ �� ���������...
		 */
    	ENActDAO actDao = new ENActDAO(connection, userProfile);
    	ENAct act = actDao.getObject(actCode);

    	if (doNotCheckProm && act.element.typeRef.code == ENElementType.TY_PROM) {
			return 0;
		}

        SCCounterDAO cDAO = new SCCounterDAO(connection, userProfile);
        SCCounterFilter cFilter = new SCCounterFilter();
        cFilter.kindRef.code = SCCounterKind.FOR_FACT;
        cFilter.conditionSQL =  "code in (select cc.code from  " +
        " sccounter cc , enestimateitem e , enact2enplanwork a2p, enplanwork p " +
        " where " +
        " cc.estimateitemrefcode = e.code " +
        " and e.planrefcode = a2p.plancode " +
        " and a2p.plancode=p.code "+
        " and a2p.actrefcode = ?" +
        ((isMount == null) ? "" : " and e.kindrefcode = ?")
        + ")" ;

        Vector<Object> binded = new Vector<>();
        binded.add(actCode);
        if(isMount != null) {
        	if(isMount) {
        		binded.add(ENEstimateItemKind.MATERIALS);
        	} else {
        		binded.add(ENEstimateItemKind.UNMOUNT);
        	}
        }

        //cFilter.conditionSQL = "estimateitemrefcode in " +
        //    "(select e.code from enestimateitem e, enact2enplanwork a2p where e.planrefcode = a2p.plancode and a2p.actrefcode = "+ actCode +")";
        int[] cArr = cDAO.getFilteredCodeArray(cFilter, 0, -1, binded);
        if (cArr.length != 0){
            if (isException)
                throw new EnergyproSystemException("������� � ���� ��'������ � ���������� ... ����������� ���������� ... ");
            else
                return 1;
        }
        return 0;
    }

    /**
     *
     * ���������� �� � ���� ������ �� ��������� ���
     *
     * SUPP-73861 31.07.2018 �� ������ ���������� ������� �������� �������� ���, ��� � ���� ��� �� ���������
     * ��� ������ ��������� ���� ����
     *
     * � ������������ ���� ������ ����� ����� ��������� �������� � ����� � ����� ��������� ���
     * (���� ����� ������� � ����������)
     *
     * @param act ������ ���� {@link ENAct}
     * @return {@code true} - � ���� ���� ����� � �������� ����� ��������� ���, {@code false} - � ���� ��� ������ � ����� ��������� ���
     * @throws PersistenceException
     */
    public boolean checkZKUMountingByAct(ENAct act) throws PersistenceException {

    	String planCodesStr = this.getPlanCodesByAct(act.code);

    	ENPlanWorkDAO planDao = new ENPlanWorkDAO(connection, userProfile);
    	ENTechConditionsServicesDAO techCondServicesDao = new ENTechConditionsServicesDAO(connection, userProfile);
    	ENSettingsLogic settingsLogic = new ENSettingsLogic(connection, userProfile);

    	ENPlanWorkFilter filter = new ENPlanWorkFilter();
    	filter.conditionSQL = String.format("%s in (%s)", ENPlanWork.code_QFielld, planCodesStr);
    	int[] planCodes = planDao.getFilteredCodeArray(filter, 0, 1);

    	// ���� ������� ������� ����� ������������ � ����� � ��������
    	Date dateToCompare = act.dateAct;

    	if(planCodes.length == 0) return false;

    	ENPlanWork plan = planDao.getObject(planCodes[0]);

    	ENTechConditionsServices techCondServices = techCondServicesDao.getENTechConditionsServicesByPlan(plan);

    	/* SUPP-78547 � ������� ���������� ���� ����������� ��������.
    	 * � ������, ���� ��� ����� ���� ������ � ��������� �� �������������,
    	 * �� ����� ����������� ���� ����� ��������, � �� ����. */
    	/*
    	 * ��������� �������� ��� ���� ����������, ������ ��� ���� ��������
    	 * ������� �� �� ������
    	 */
    	if(techCondServices != null) {
    		ENContragentDAO cntDAO = new ENContragentDAO(connection, userProfile);
    		ENContragentFilter cntFilter = new ENContragentFilter();
    		cntFilter.techCondServicesRef.code = techCondServices.code;
    		ENContragentShortList cntList = cntDAO.getScrollableFilteredList(cntFilter, 0, -1);
    		if (cntList.totalCount > 0) {
    			dateToCompare = cntList.get(0).techConObjectsDateGen;;
    		}

    	}

    	boolean hasCounter = this.checkInSCCounterByActCode(act.code, false, null, false) > 0;
    	boolean isZkuMount =  planDao.checkENPlanWorkTypes(plan, false, true, ENPlanWorkType.EZ_SETUP_ZKU);

    	// 24.07.2018  ���� ��� ������ �� ��������� ����� ������ ������ ����������� �� �������,
    	//			   �� ���� ����� �������� ����� ��������� � ������������ ����� ���
    	// 30.07.2018 ��� ����� ����������� �� ������������� ����� - ����� �������� ����������
    	// ����� ��������� ����� ������.
    	if(hasCounter) {
    		Date checkDate = settingsLogic.getDateValue(ENSettingsKeysConsts.DATE_MOUNT_ZKU_WITHOUT_COUNTER, act.dateAct);
    		if(isZkuMount && dateToCompare.compareTo(checkDate) >= 0) {
    			ENPlanWorkTypeDAO planTypeDao = new ENPlanWorkTypeDAO(connection, userProfile);
    			ENPlanWorkType planType = planTypeDao.getObject(ENPlanWorkType.EZ_SETUP_ZKU);
    			throw new SystemException(String.format("�� %s ��������� ������ ����� ���� \"%s\" ����� �� ���������� � ����! ��� �����: %d"
    					, Tools.dateFormatDefault.format(checkDate)
    					, planType.name
    					, plan.code));
    		}
    		return false;
    	}

    	return isZkuMount;
    }
    public boolean checkIfServicesObjectByAct(ENAct act, boolean isException) throws PersistenceException
    {
        boolean result = false;

        if (act.element.typeRef.code == ENElementType.SERVICES_OBJECT)
        {
            //ENServicesObjectDAO soDAO = new ENServicesObjectDAO(connection, userProfile);
            ServicesLogic soLogic = new ServicesLogic(connection, userProfile);
            ENServicesObject soObj = soLogic.getServicesObjectByElementCode(act.element.code);

            /** SUPP-3844 +++ ��� ������������� ���� �������� ������������� ���� */
            if (soObj.contractTypeRef.code == ENServicesContractType.CONNECTION
                    || soObj.contractStatusRef.code == ENServicesContractStatus.DISCLAIMER_CUSTOMER_SERVICES) {
                return true;
            }

            /**
             *  SUPP-6949... 10.09.2013 +++ �� ������������ ��� - ��������� ���� ����� ���������/�������� ��������!!!
             *  �� ��� ��� ���� ���.������ - ��������....
             */
            if (soObj.statusRef.code == ENServicesObjectStatus.CLOSED) {
                throw new EnergyproSystemException("\n \n" +
                        "�� ��������� " + soObj.contractNumberServices + " ��� �������� �������� � \"Գ�����\"!!! \n" +
                        "³����� ����������, � ���� ���!!!" );
            }

            /*
            if (soObj.contractNumberServices != null)
            {
                if (soObj.contractNumberServices != "")
                {
                    if (isException)
                    throw new EnergyproSystemException("��� �������� ������� ��������� (��� �������) � ���� \"������� �� �������\" - ����� ����������� ��������, ������� \"����\"!");
                else
                    return true;
                }
            }
            */
        }

        return result;
    }


        public void fillActData(int actCode, boolean isSignatured) throws PersistenceException
        {
            ENAct act = new ENActDAO(connection, userProfile).getObject(actCode);

            /*  06.08.2011
            *  �� ������������� ����������� (��������)
            */
            if (
//            (act.actTypeRef.code == ENPlanWorkState.TRUCKING)
//                      ||
                        (act.actTypeRef.code == ENPlanWorkState.WRITINGS_TMC)
                    || (act.actTypeRef.code == ENPlanWorkState.WRITINGS_MBP)
                    || (act.actTypeRef.code == ENPlanWorkState.WRITINGS_MBP_INSTRUMENTS /*SUPP_13237*/)
                    || (act.actTypeRef.code == ENPlanWorkState.WRITINGS_MNMA)
                    || (act.actTypeRef.code == ENPlanWorkState.WRITINGS_BUFET_REALIZATION)
                    || (act.actTypeRef.code == ENPlanWorkState.WRITINGS_BUFET_NONEREALIZATION)
                    || (act.actTypeRef.code == ENPlanWorkState.WRITINGS_BUFET_REALIZATION_BEZNAL)
                    || (act.actTypeRef.code == ENPlanWorkState.WRITINGS_COUNTERS /*NET-1026*/)
            )
                return;

            // SUPP-73861 � ���� ������ ������������� ��������, ��� ��������� ���
            // ���� ����������� ��� �������� � �������� (������ ���������� � ������������ ����)
            this.checkZKUMountingByAct(act);

            Calendar c = Calendar.getInstance();
            c.setTime(act.dateGen);
            if (
                    (( c.get(Calendar.YEAR) == 2011 ) && ( c.get(Calendar.MONTH) > Calendar.JUNE ))
                    || (c.get(Calendar.YEAR) > 2011)
                )
            {
                fillActDataALL(act.code, isSignatured);
            }
            else{
                fillActData2(act.code);
            }
        }

    public void deleteAct2Humen(int actCode) throws PersistenceException
    {
        ENAct2HumenDAO a2hDAO = new ENAct2HumenDAO(connection, userProfile);
        ENAct2HumenFilter a2Filter = new ENAct2HumenFilter();
        a2Filter.actRef.code = actCode;
        int[] a2Arr = a2hDAO.getFilteredCodeArray(a2Filter, 0, -1);
        for (int a2=0; a2 < a2Arr.length; a2++){
            a2hDAO.remove(a2Arr[a2]);
        }
    }


    public void fillActDataALL(int actCode, boolean isSignatured) throws PersistenceException
    {
        Connection fkConnection = null;

        try
        {
            fkConnection = getNEWConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            ENAct act = new ENActDAO(connection, userProfile).getObject(actCode);

            System.out.println("Start fillActDataAll : act �" + act.numberGen + " ("+ act.code+")");

            /** 12.07.2012 +++ ���� �������� ����������� �� ���� ���������� ����?!?!?  */
            /** 19.01.2015 +++ ��������� �������� �������� �������, ����� ������������ ��� ������� ����������...  */
            /*
            BigDecimal[] monthTimes = finLogic.getWorkTime(act.dateAct);
            BigDecimal monthTime = monthTimes[0]; //finLogic.getWorkTimeInMonth(act.dateGen).setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal monthDay = monthTimes[1];

            BigDecimal calendarTime = monthTime.divide(monthDay, 2, BigDecimal.ROUND_HALF_UP);
            */

            ///// 12.12.11 ������� ��� �� ���������� �������� ��� ������� ������������
            ENPlan2HumenDAO p2hDAO = new ENPlan2HumenDAO(connection, userProfile);
            ENPlan2HumenFilter p2hFilter = new ENPlan2HumenFilter();
            p2hFilter.conditionSQL = " planrefcode in " +
                                    "(select a2p.plancode from enact2enplanwork a2p where a2p.actrefcode = " + actCode + ")";
            int[] p2hArr = p2hDAO.getFilteredCodeArray(p2hFilter, 0, -1);
            for (int i = 0; i < p2hArr.length; i++)
            {
                p2hDAO.remove(p2hArr[i]);
            }
            /////

            ActCalculator actCalc = new ActCalculator(connection, userProfile);

            ///// 07.09.12 ��������� � ActCalculator.fillActs2 - ��� ������������ ������� �/� �� ���� ��������� �����
            // ����������� �/� � ����� ������
            //actCalc.updateSalary(act);
            /////

            //// DEBUG!!!

            /** ����� ����� ������� ���� (EnergyNet vs mDax)  */
            actCalc.calcActs(actCode, isSignatured);



            /*
            _actsTable = new Hashtable();
            _plansData2 = new Hashtable();

            fillActs2(act.code, ""+ Integer.MIN_VALUE);


            Vector v = new Vector(_plansData2.keySet());

                for (Enumeration e = v.elements(); e.hasMoreElements();) {
                PlansData key = (PlansData)e.nextElement();
                PlansData qq_ = (PlansData) _plansData2.get(key);
                try
                {
                    recalc4Norms2(new Integer(qq_.tabNumber).intValue(), new SimpleDateFormat("dd.MM.yyyy").parse(qq_.planDate), calendarTime );
                }
                catch (ParseException exception){
                    throw new EnergyproSystemException(exception.getMessage());
                }
            }

                Vector v1 = new Vector(_actsTable.keySet());
                // Collections.sort(v, Collections.reverseOrder());

                // Display (sorted) hashtable.
                    for (Enumeration e = v1.elements(); e.hasMoreElements();) {
                    ActsData key1 = (ActsData)e.nextElement();
                    ActsData qq_1 = (ActsData) _actsTable.get(key1);
                    fillActData99(new Integer(qq_1.actCode).intValue());
                }
            */
            System.out.println("Final fillActDataAll : act �" + act.numberGen + " ("+ act.code+")");

            } catch (Exception e) {
                throw new SystemException(e.getMessage(), e);
            } finally {

                //_actsTable = null;
                //_plansData2 = null;

                try {
                    if  ( (fkConnection != null) && ! fkConnection.isClosed() )
                        fkConnection.close();
                } catch (SQLException e) {
                }
            }


    }





    public void fillActData2(int actCode) throws PersistenceException
    {
        Connection fkConnection = null;

        try
        {
            fkConnection = getNEWConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            ENAct act = new ENActDAO(connection, userProfile).getObject(actCode);

            System.out.println("Start fillActData : act �" + act.numberGen + " ("+ act.code+")");

            ENAct2HumenDAO a2hDAO = new ENAct2HumenDAO(connection, userProfile);
            ENTransportItemDAO transportDAO = new ENTransportItemDAO(connection, userProfile);

            FINLogic finLogic = new FINLogic(fkConnection, userProfile);

            FINWorkerDAO finWorkerFKDAO = new FINWorkerDAO(fkConnection, userProfile);

            ENHumenItemDAO humenDAO = new ENHumenItemDAO(connection, userProfile);
            ENDeliveryTimeDAO delTimeDAO = new ENDeliveryTimeDAO(connection, userProfile);

            /** 12.07.2012 +++ ���� �������� ����������� �� ���� ���������� ����?!?!?  */
            /** 19.01.2015 +++ ��������� �������� �������� �������, ����� ������������ ��� ������� ����������...  */
            /*
            BigDecimal[] monthTimes = finLogic.getWorkTime(act.dateAct);
            BigDecimal monthTime = monthTimes[0]; //finLogic.getWorkTimeInMonth(act.dateGen).setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal monthDay = monthTimes[1];
            */

            BigDecimal month2Time = null;
            BigDecimal month2Pays = null;

            FINWorkerDAO fwDAO = new FINWorkerDAO(connection, userProfile);


            String finWorkerCondition = " finworker.code in ( " +
            " select enhumenitem.finworkercode  " +
            " from enhumenitem , enplanwork , enplanworkitem , enact2enplanwork " +
            "  where " +
            "  enact2enplanwork.actrefcode = " + act.code +
            "  and enact2enplanwork.plancode = enplanwork.code " +
            "  and enplanwork.code = enplanworkitem.planrefcode " +
            "  and enhumenitem.planitemrefcode = enplanworkitem.code  " +
            "  and enhumenitem.countfact<>0   " +
            "  and enplanworkitem.countgen <> 0 " +
            "  ) " ;

            String finWorkerConditionHH = " enhumenitem.finworkercode in ( " +
            " select enhumenitem.finworkercode  " +
            " from enhumenitem , enplanwork , enplanworkitem , enact2enplanwork " +
            "  where " +
            "  enact2enplanwork.actrefcode = " + act.code +
            "  and enact2enplanwork.plancode = enplanwork.code " +
            "  and enplanwork.code = enplanworkitem.planrefcode " +
            "  and enhumenitem.planitemrefcode = enplanworkitem.code  " +
            "  and enhumenitem.countfact<>0   " +
            "  and enplanworkitem.countgen <> 0 " +
            "  ) " +
            // ������������� ��� �� ���� ??? ;)
            " and enhumenitem.finworkercode is not null "
            ;


            /* 08.06.2012 +++ �������� ������� ����� �������� �� PostgreSQL9 */

            ENHumenItemFilter h2Filter = new ENHumenItemFilter();
            h2Filter.conditionSQL = finWorkerConditionHH;
            int h2Arr[] = humenDAO.getFilteredCodeArray(h2Filter, 0, -1);
            if (h2Arr.length > 0) {
                for (int j=0; j < h2Arr.length; j++) {

                    // ���� ������� �� �� �� ...
                    ENHumenItemFilter hhFilter = new ENHumenItemFilter();
                    hhFilter.conditionSQL = finWorkerConditionHH;
                    hhFilter.code = h2Arr[j];

                    ENHumenItemShortList hhList = humenDAO.getScrollableFilteredList(hhFilter, 0, -1);
                    for (int qq=0; qq < hhList.totalCount; qq++){

                        // �������� ����� � ������ ������� � ��� ������� ...
                        if (
                                (Tools.getMonth(hhList.get(qq).planRefDateStart) != Tools.getMonth(act.dateGen))
                                || (Tools.getYear(hhList.get(qq).planRefDateStart) != Tools.getYear(act.dateGen))
                        )
                        {
                            throw new EnergyproSystemException("г� �� ����� ���� � ����� ������ ��������� ...");
                        }

                        if (hhList.get(qq).finWorkerTabNumber != null) {
                            FINWorkerFilter ff = new FINWorkerFilter();
                            ff.tabNumber = hhList.get(qq).finWorkerTabNumber;
                            FINWorkerShortList fList = finWorkerFKDAO.getFINWorkerList(ff,0,-1, hhList.get(qq).planRefDateStart, true);
                            if (fList.totalCount > 0){
                                FINWorker w = fwDAO.getObject(hhList.get(qq).finWorkerCode);
                                w.categor = fList.get(0).categor;
                                w.departmentCode = fList.get(0).departmentCode;
                                w.departmentName = fList.get(0).departmentName;
                                w.finCode = fList.get(0).finCode;
                                w.kindRef.code = (fList.get(0).categor == Integer.MIN_VALUE) ? FINWorkerKind.OTHER : fList.get(0).categor ;//fList.get(0).kindRefCode;
                                w.name = fList.get(0).name;
                                w.positionCode = fList.get(0).positionCode;
                                w.positionName = fList.get(0).positionName;
                                w.priceGen = fList.get(0).priceGen;
                                w.tabNumber = fList.get(0).tabNumber;
                    			/////
                    			w.categorId = fList.get(0).categorId;
                    			w.categorName = fList.get(0).categorName;
                    			w.workTimeId = fList.get(0).workTimeId;
                    			/////
                                // MDAX-441
                                w.positionId = fList.get(0).positionId;

                                fwDAO.save(w);
                            }
                        }
                    }
                }
            }



            // �� ��������� !!! ����� �� ���������� ;) .. � �� ������� ;)
            finWorkerConditionHH = " entransportitem.finworkercode in ( " +
            " Select " +
            " fw.code " +
            " From entransportitem ent ,  finworker fw,  enact2enplanwork ena2, enplanworkitem pwi " +
            " Where " +
            " ena2.actrefcode = " + act.code +
            " and ent.planrefcode = ena2.plancode " +
            " and ent.finworkercode = fw.code " +
            " and ent.planitemrefcode = pwi.code " +
            " and pwi.countgen > 0 " +
            "  ) " ;


            /* 08.06.2012 +++ �������� ������� ����� �������� �� PostgreSQL9 */

            ENTransportItemFilter tr2Filter = new ENTransportItemFilter();
            tr2Filter.conditionSQL = finWorkerConditionHH;
            int tr2Arr[] = transportDAO.getFilteredCodeArray(tr2Filter, 0, -1);
            if (tr2Arr.length > 0) {
                for (int j=0; j < tr2Arr.length; j++) {

                    // ���� ������� �� �� �� ...
                    ENTransportItemFilter trFilter1 = new ENTransportItemFilter();
                    trFilter1.conditionSQL = finWorkerConditionHH;
                    trFilter1.code = tr2Arr[j];

                    ENTransportItemShortList trList1 = transportDAO.getScrollableFilteredList(trFilter1, 0, -1);
                    for (int qq=0; qq < trList1.totalCount; qq++){

                        // �������� ����� � ������ ������� � ��� ������� ...
                        if (
                                (Tools.getMonth(trList1.get(qq).planRefDateStart) != Tools.getMonth(act.dateGen))
                                || (Tools.getYear(trList1.get(qq).planRefDateStart) != Tools.getYear(act.dateGen))
                        )
                        {
                            throw new EnergyproSystemException("г� �� ����� ���� � ����� ������ ��������� ...");
                        }

                        if (trList1.get(qq).finWorkerTabNumber != null) {
                            FINWorkerFilter ff = new FINWorkerFilter();
                            ff.tabNumber = trList1.get(qq).finWorkerTabNumber;
                            FINWorkerShortList fList = finWorkerFKDAO.getFINWorkerList(ff,0,-1, trList1.get(qq).planRefDateStart, true);
                            if (fList.totalCount > 0){
                                FINWorker w = fwDAO.getObject(trList1.get(qq).finWorkerCode);
                                w.categor = fList.get(0).categor;
                                w.departmentCode = fList.get(0).departmentCode;
                                w.departmentName = fList.get(0).departmentName;
                                w.finCode = fList.get(0).finCode;
                                w.kindRef.code = (fList.get(0).categor == Integer.MIN_VALUE) ? FINWorkerKind.OTHER : fList.get(0).categor ;//fList.get(0).kindRefCode;
                                w.name = fList.get(0).name;
                                w.positionCode = fList.get(0).positionCode;
                                w.positionName = fList.get(0).positionName;
                                w.priceGen = fList.get(0).priceGen;
                                w.tabNumber = fList.get(0).tabNumber;
                                // MDAX-441
                                w.positionId = fList.get(0).positionId;

                                fwDAO.save(w);
                            }
                        }
                    }
                }
            }


            System.out.println("##Elmonters fillActData : act �" + act.numberGen + " ("+ act.code+")");

            int order = 1;
            ENAct2Humen a2h = null;

            ENAct2HumenFilter a2hFilter = new ENAct2HumenFilter();
            a2hFilter.actRef.code = actCode;
            a2hFilter.humenKindRef.code = ENHumenItemKind.ELTEH;
            int[] a2hArr = a2hDAO.getFilteredCodeArray(a2hFilter, null, null, 0, -1, null);

            // �������������� ...

            FINWorkerShortList finWorkerList = fwDAO.getGroupedListByTabNumber2(null, finWorkerCondition, "FINWORKER.NAME", 0, -1, null);
            for (int i=0; i<finWorkerList.totalCount; i++) {

                /** 19.01.2015 +++ ��������� �������� �������� �������, ����� ������������ ��� ������� ����������...  */
                BigDecimal[] monthTimes = finLogic.getWorkTime(act.dateAct, finWorkerList.get(i).tabNumber);
                BigDecimal monthTime = monthTimes[0]; //finLogic.getWorkTimeInMonth(act.dateGen).setScale(2, BigDecimal.ROUND_HALF_UP);
                BigDecimal monthDay = monthTimes[1];

                BigDecimal workerWorkTime = new BigDecimal(0);
                BigDecimal workerWorkTimeOnly = new BigDecimal(0);
                BigDecimal workerDeliveryTime = new BigDecimal(0);

                ENHumenItemFilter humenFilter = new ENHumenItemFilter();
                humenFilter.conditionSQL = "enhumenitem.code in (" +
                " select enhumenitem.code  " +
                " from enhumenitem , enplanwork , enplanworkitem , enact2enplanwork , finworker " +
                "  where " +
                "  enact2enplanwork.actrefcode = " + act.code +
                "  and enact2enplanwork.plancode = enplanwork.code " +
                "  and enplanwork.code = enplanworkitem.planrefcode " +
                "  and enhumenitem.planitemrefcode = enplanworkitem.code  " +
                "  and enhumenitem.countfact<>0   " +
                "  and enplanworkitem.countgen <> 0 " +
                "  and enhumenitem.finworkercode = finworker.code" +
                "  and finworker.tabnumber = '" + finWorkerList.get(i).tabNumber +  "'" +
                "  and finworker.issentassignment = " + finWorkerList.get(i).isSentAssignment +
                //"  and finworker.pricegen = " + finWorkerList.get(i).priceGen +
                /// 27.05.11 �����, ����� ����������� ��������� �� ������ ����������
                "  and finworker.positioncode = " + finWorkerList.get(i).positionCode +
                ///
                ")";

                if ( i < a2hArr.length ){
                    a2h = a2hDAO.getObject(a2hArr[i]);
                }
                else
                {
                    a2h = new ENAct2Humen();
                    a2h.code = Integer.MIN_VALUE;
                }

                a2h.fio = null;
                a2h.actRef.code = actCode;
                a2h.humenKindRef.code = ENHumenItemKind.ELTEH;
                a2h.orederNum = order;
                order = order + 1;
                //a2h.fio = finWorkerList.get(i).name + " " + finWorkerList.get(i).positionName;
                a2h.tabNumber = new Integer(finWorkerList.get(i).tabNumber).toString();

                a2h.daysMonth = monthDay;

                ENHumenItemShortList humenList = humenDAO.getScrollableFilteredList(humenFilter, 0, -1);
                for (int j=0; j < humenList.totalCount; j++){


                    if (a2h.fio == null ){
                        FINWorker fw = fwDAO.getObject(humenList.get(j).finWorkerCode);
                        a2h.fio = fw.name + " " + fw.positionName;
                        a2h.salary = fw.priceGen.setScale(2, BigDecimal.ROUND_HALF_UP);
                    }

                    workerWorkTimeOnly = workerWorkTimeOnly.add(humenList.get(j).countFact.setScale(2, BigDecimal.ROUND_HALF_UP)).setScale(2, BigDecimal.ROUND_HALF_UP);
                    workerWorkTime = workerWorkTime.add(humenList.get(j).countFact.setScale(2, BigDecimal.ROUND_HALF_UP)).setScale(2, BigDecimal.ROUND_HALF_UP);

                    // ������� ����� �������� ...
                    ENDeliveryTimeFilter delTimeFilter = new ENDeliveryTimeFilter();
                    delTimeFilter.humenItemRef.code = humenList.get(j).code;
                    ENDeliveryTimeShortList delTimeList = delTimeDAO.getScrollableFilteredList(delTimeFilter, 0, -1);
                    for (int k=0; k < delTimeList.totalCount; k++){
                        workerDeliveryTime = workerDeliveryTime.add(delTimeList.get(k).deliveryTimeFact.setScale(2, BigDecimal.ROUND_HALF_UP)).setScale(2, BigDecimal.ROUND_HALF_UP);
                        workerWorkTime = workerWorkTime.add(delTimeList.get(k).deliveryTimeFact.setScale(2, BigDecimal.ROUND_HALF_UP)).setScale(2, BigDecimal.ROUND_HALF_UP);
                    }
                }

                //finWorkerList.get(i).positionCode


                if ( finWorkerList.get(i).isSentAssignment == 1)
                //if (fw.is == 1)
                {


                    month2Time = finLogic.getWorkTimeTwoPreviousMonth(act.dateGen).setScale(2, BigDecimal.ROUND_HALF_UP);
                    if ( month2Time.abs().doubleValue() < 0.1 ){
                        throw new EnergyproSystemException("ʳ������ ������� ����� � ����� = 0 !!! ... ");
                    }

                    System.out.println("getMiddlePayTwoPreviousMonth  act �" + act.numberGen + " ("+ act.code+"): " + finWorkerList.get(i).tabNumber);

                    month2Pays = finLogic.getMiddlePayTwoPreviousMonth(act.dateGen, new Integer(finWorkerList.get(i).tabNumber).toString() );
                    // ������ ��������� - ��� � ������������ ...
                    if (month2Pays == null){

                        a2h.timeMonth = monthTime;
                        if ( monthTime.abs().doubleValue() < 0.1 ){
                            throw new EnergyproSystemException("ʳ������ ������� ����� � ����� = 0 !!! ... ");
                        }
                        a2h.salaryHours = a2h.salary.setScale(2,java.math.BigDecimal.ROUND_HALF_UP).divide(monthTime,2,java.math.BigDecimal.ROUND_HALF_UP).setScale(2,java.math.BigDecimal.ROUND_HALF_UP).setScale(2,java.math.BigDecimal.ROUND_HALF_UP);

                        a2h.salary = null;
                        a2h.timeMonth = null;
                    }
                    else
                    {
                        a2h.salary = null;
                        a2h.timeMonth = null;
                        month2Pays = month2Pays.setScale(2, BigDecimal.ROUND_HALF_UP);
                        a2h.salaryHours = month2Pays.setScale(2,java.math.BigDecimal.ROUND_HALF_UP).divide(month2Time,2,java.math.BigDecimal.ROUND_HALF_UP).setScale(2,java.math.BigDecimal.ROUND_HALF_UP).setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
                    }

                }
                else
                {
                    //a2h.salary = a2h.salary;
                    a2h.timeMonth = monthTime;
                    if ( monthTime.abs().doubleValue() < 0.1 ){
                        throw new EnergyproSystemException("ʳ������ ������� ����� � ����� = 0 !!! ... ");
                    }
                    a2h.salaryHours = a2h.salary.setScale(2,java.math.BigDecimal.ROUND_HALF_UP).divide(monthTime,2,java.math.BigDecimal.ROUND_HALF_UP).setScale(2,java.math.BigDecimal.ROUND_HALF_UP).setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
                }

                a2h.timeObjectWork = workerWorkTimeOnly;
                a2h.timeDelivery = workerDeliveryTime;
                a2h.timeWork = workerWorkTime.setScale(2, BigDecimal.ROUND_HALF_UP); // ���� �������� ... workerWorkTimeOnly.add(workerDeliveryTime).setScale(2, BigDecimal.ROUND_HALF_UP);
                a2h.timeWorkFact = a2h.timeWork;
                a2h.paysWork = a2h.salaryHours.multiply(a2h.timeWork.setScale(2,java.math.BigDecimal.ROUND_HALF_UP)).setScale(2,java.math.BigDecimal.ROUND_HALF_UP);

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                // 27.05.11 ����������, ������������ �� ���� ��. ������, �� ���� ��������� (���� �� �� ��������� ��������)
                for (int d = 0; d < ENConsts.DRIVERS_POSITIONS.length; d++)
                {
                    if (finWorkerList.get(i).positionCode == ENConsts.DRIVERS_POSITIONS[d])
                    {
                        // ���� ������������ .. ������� 5%
                        if (a2h.salary != null){
                            //throw new EnergyproSystemException("� �������� � ��� "+ a2h.tabNumber +" �� ������� �����");
                            a2h.salary = ( a2h.salary.multiply(new BigDecimal(0.05)).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }

                        a2h.salaryHours = ( a2h.salaryHours.multiply(new BigDecimal(0.05)).setScale(2, BigDecimal.ROUND_HALF_UP));
                        a2h.paysWork = ( a2h.paysWork.multiply(new BigDecimal(0.05)).setScale(2, BigDecimal.ROUND_HALF_UP));

                        break;
                    }
                }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                if (a2h.tabNumber.equals("12979"))
                {
                    // ���� ������������ .. ������� 5%
                    if (a2h.salary != null){
                        //throw new EnergyproSystemException("� �������� � ��� "+ a2h.tabNumber +" �� ������� �����");
                        a2h.salary = (a2h.salary.multiply(new BigDecimal(0.05)).setScale(2, BigDecimal.ROUND_HALF_UP));
                    }

                    a2h.salaryHours = (a2h.salaryHours.multiply(new BigDecimal(0.05)).setScale(2, BigDecimal.ROUND_HALF_UP));
                    a2h.paysWork = (a2h.paysWork.multiply(new BigDecimal(0.05)).setScale(2, BigDecimal.ROUND_HALF_UP));
                }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                // ������� ��� ;)
                if (a2h.tabNumber.equals("13029"))
                {
                    // ���� ������������ .. ������� 30%
                    if (a2h.salary != null){
                        //throw new EnergyproSystemException("� �������� � ��� "+ a2h.tabNumber +" �� ������� �����");
                        a2h.salary = (a2h.salary.multiply(new BigDecimal(0.3)).setScale(2, BigDecimal.ROUND_HALF_UP));
                    }

                    a2h.salaryHours = (a2h.salaryHours.multiply(new BigDecimal(0.3)).setScale(2, BigDecimal.ROUND_HALF_UP));
                    a2h.paysWork = (a2h.paysWork.multiply(new BigDecimal(0.3)).setScale(2, BigDecimal.ROUND_HALF_UP));
                }
/////////////////////////////////////////////////////////


                if (a2h.code == Integer.MIN_VALUE){
                    a2hDAO.add(a2h);
                }
                else{
                    a2hDAO.save(a2h);
                }

            } // ����� ��������������


            // ������ �� ��� �������� �� ���������� ����� ����
            if ( finWorkerList.totalCount < a2hArr.length){
                for (int i= finWorkerList.totalCount; i < a2hArr.length ; i++){
                    a2hDAO.remove(a2hArr[i]);
                }
            }

            System.out.println("##Drivers fillActData : act �" + act.numberGen + " ("+ act.code+")");

            // �� ��������� ...
            TransportLogic transportLogic = new TransportLogic(connection, userProfile);
            PlanWorkLogic planLogic = new PlanWorkLogic(connection, userProfile);

            finWorkerCondition = " finworker.code in ( " +
            " Select " +
            " fw.code " +
            " From entransportitem ent ,  finworker fw,  enact2enplanwork ena2, enplanworkitem pwi " +
            " Where " +
            " ena2.actrefcode = " + act.code +
            " and ent.planrefcode = ena2.plancode " +
            " and ent.finworkercode = fw.code " +
            " and ent.planitemrefcode = pwi.code " +
            " and pwi.countgen > 0 " +
            "  ) " ;


            order = 1;
            a2h = null;

            a2hFilter = new ENAct2HumenFilter();
            a2hFilter.actRef.code = actCode;
            a2hFilter.humenKindRef.code = ENHumenItemKind.DRIVER;
            a2hArr = a2hDAO.getFilteredCodeArray(a2hFilter, null, null, 0, -1, null);



            // �������� ....
            finWorkerList = fwDAO.getGroupedListByTabNumber2(null, finWorkerCondition, "FINWORKER.NAME", 0, -1, null);

            String addCondition = "";
            if (finWorkerList.totalCount > 0){
                ENAct2ENPlanWorkDAO a2pDAO = new ENAct2ENPlanWorkDAO(connection, userProfile);
                ENAct2ENPlanWorkFilter a2pFilter = new ENAct2ENPlanWorkFilter();
                a2pFilter.actRef.code = actCode;
                ENAct2ENPlanWorkShortList a2pList = a2pDAO.getScrollableFilteredList(a2pFilter, 0, -1);
                for (int ss=0; ss < a2pList.totalCount; ss++){
                    if (addCondition.length() == 0){
                        addCondition = "" + a2pList.get(ss).planCode;
                    }
                    else{
                        addCondition = addCondition + ", " + a2pList.get(ss).planCode;
                    }
                }
            }

            for (int i=0; i<finWorkerList.totalCount; i++){

                /** 19.01.2015 +++ ��������� �������� �������� �������, ����� ������������ ��� ������� ����������...  */
                BigDecimal[] monthTimes = finLogic.getWorkTime(act.dateAct, finWorkerList.get(i).tabNumber);
                BigDecimal monthTime = monthTimes[0]; //finLogic.getWorkTimeInMonth(act.dateGen).setScale(2, BigDecimal.ROUND_HALF_UP);
                BigDecimal monthDay = monthTimes[1];

                BigDecimal workerWorkTime = new BigDecimal(0);
                BigDecimal workerWorkTimeOnly = new BigDecimal(0);
                BigDecimal workerDeliveryTime = new BigDecimal(0);


                ENTransportItemFilter transportFilter = new ENTransportItemFilter();

                transportFilter.conditionSQL = ( addCondition.length() > 0 ? " entransportitem.planrefcode in (" + addCondition + ") and " : "" ) +
                    "entransportitem.code in ( " +
                    " Select " +
                    " ent.code  " +
                    " From entransportitem ent ,  finworker fw,  enact2enplanwork ena2, enplanworkitem pwi " +
                    " Where " +
                    " ena2.actrefcode = " + act.code +
                    " and ent.planrefcode = ena2.plancode " +
                    " and ent.finworkercode = fw.code " +
                    " and ent.planitemrefcode = pwi.code " +
                    " and pwi.countgen > 0  " +
                    " and fw.tabnumber = " + finWorkerList.get(i).tabNumber +
                    " and fw.issentassignment = " + finWorkerList.get(i).isSentAssignment +
                    //"  and fw.pricegen = " + finWorkerList.get(i).priceGen +
                    /// 27.05.11 �����, ����� ����������� ��������� �� ������ ����������
                    " and fw.positioncode = " + finWorkerList.get(i).positionCode +
                    ///
                    " ) " ;

                if ( i < a2hArr.length ){
                    a2h = a2hDAO.getObject(a2hArr[i]);
                }
                else
                {
                    a2h = new ENAct2Humen();
                    a2h.code = Integer.MIN_VALUE;
                }

                a2h.fio = null;
                a2h.actRef.code = actCode;
                a2h.humenKindRef.code = ENHumenItemKind.DRIVER;
                a2h.orederNum = order;
                order = order + 1;
                //a2h.fio = finWorkerList.get(i).name + " " + finWorkerList.get(i).positionName;
                a2h.tabNumber = new Integer(finWorkerList.get(i).tabNumber).toString();
                a2h.daysMonth = monthDay;

                ENTransportItemShortList transportList = transportDAO.getScrollableFilteredList(transportFilter, 0, -1);
                String trPlanCodes = "" + Integer.MIN_VALUE;
                for (int j=0; j < transportList.totalCount; j++){

                    if (a2h.fio == null ){
                        FINWorker fw = fwDAO.getObject(transportList.get(j).finWorkerCode);
                        a2h.fio = fw.name + " " + fw.positionName;
                        a2h.salary = fw.priceGen.setScale(2, BigDecimal.ROUND_HALF_UP);
                    }
                    //a2h.fio = transportList.get(j).finWorkerName + " " + transportList.get(j).finWorkerPositionName;

                    // ����� ������ � ������ ��� ���� ����������� .. ����� ���������� ����
                    if (transportList.get(j).tktransportTypeCode != TKTransportType.BRIGADE)
                    {
                        workerWorkTime = workerWorkTime.add(transportList.get(j).countWorkFact).setScale(2, BigDecimal.ROUND_HALF_UP);
                        workerWorkTimeOnly = workerWorkTimeOnly.add(transportList.get(j).countWorkFact).setScale(2, BigDecimal.ROUND_HALF_UP);
                    }

                    // ����� ��� ������� �������� ������ ��������� ���� � ������������ ..
                    if ((transportList.get(j).tktransportTypeCode == TKTransportType.BRIGADE)
                        // ����� ��� ... ����� ���� ���������� �������� - �  �����. 0 ....
                        //&& (transportList.get(j).distance.doubleValue() > 0 )
                    )
                    {
                        trPlanCodes = trPlanCodes + ", " + transportList.get(j).planRefCode;
                    }

                    // ������� ����� �������� ...
                    // ����� ������ .. ����� �������������� !!!
                    // �� 01.06.2011 ������������������ ;))) :(

                    BigDecimal[] distArr = transportLogic.getDistancesByTransport(transportList.get(j).code, false); // ��� ����� ������� ��� ���������� ...
                    if ( distArr != null){
                        BigDecimal delTime = transportLogic.calcTimeByDistaces(distArr[0], distArr[1], distArr[2], planLogic.isWinterMonth( transportList.get(j).planRefDateStart), transportLogic.isTraktor(transportList.get(j).code)).setScale(2, BigDecimal.ROUND_HALF_UP);
                        workerWorkTime = workerWorkTime.add(delTime).setScale(2, BigDecimal.ROUND_HALF_UP);
                    }

                }

                /* �� ���� .. ������������������
                //if (addCondition.length() > 0){
                if ( ! trPlanCodes.equals(""+Integer.MIN_VALUE)){
                    ENDeliveryTimePlanDAO dpDAO = new ENDeliveryTimePlanDAO(connection, userProfile);
                    ENDeliveryTimePlanFilter dpFilter = new ENDeliveryTimePlanFilter();
                    dpFilter.conditionSQL = " planworkrefcode in (" + trPlanCodes + ")";
                    ENDeliveryTimePlanShortList dpList = dpDAO.getScrollableFilteredList(dpFilter, 0, -1);
                    for (int qq1=0; qq1 < dpList.totalCount; qq1++){
                        workerWorkTime = workerWorkTime.add(dpList.get(qq1).deliveryTimeFact);
                        workerDeliveryTime = workerDeliveryTime.add(dpList.get(qq1).deliveryTimeFact);
                    }
                }
                */

                if ( finWorkerList.get(i).isSentAssignment == 1)
                {
                    a2h.salary = null;
                    a2h.timeMonth = null;

                    month2Time = finLogic.getWorkTimeTwoPreviousMonth(act.dateGen);

                    month2Pays = finLogic.getMiddlePayTwoPreviousMonth(act.dateGen, new Integer(finWorkerList.get(i).tabNumber).toString() );
                    a2h.salaryHours = month2Pays.setScale(2,java.math.BigDecimal.ROUND_HALF_UP).divide(month2Time,2,java.math.BigDecimal.ROUND_HALF_UP).setScale(2,java.math.BigDecimal.ROUND_HALF_UP).setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
                }
                else
                {
                    //a2h.salary = finWorkerList.get(i).priceGen;
                    a2h.timeMonth = monthTime;
                    a2h.salaryHours = a2h.salary.setScale(2,java.math.BigDecimal.ROUND_HALF_UP).divide(monthTime,2,java.math.BigDecimal.ROUND_HALF_UP).setScale(2,java.math.BigDecimal.ROUND_HALF_UP).setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
                }

                a2h.timeObjectWork = workerWorkTimeOnly;
                a2h.timeDelivery = workerDeliveryTime;
                a2h.timeWork = workerWorkTime.setScale(2, BigDecimal.ROUND_HALF_UP);
                a2h.timeWorkFact = a2h.timeWork;
                a2h.paysWork = a2h.salaryHours.multiply(a2h.timeWork.setScale(2,java.math.BigDecimal.ROUND_HALF_UP)).setScale(2,java.math.BigDecimal.ROUND_HALF_UP);

                ///////////////
                // 29.04.11 ������ ������������ � ��������� 12240
                if (
                        (a2h.tabNumber.equals("12240"))
                        ||(a2h.tabNumber.equals("274"))
                        ||(a2h.tabNumber.equals("12791"))
                        ||(a2h.tabNumber.equals("13080"))
                        ||(a2h.tabNumber.equals("10735") )
                )
                {
                    // ���� ������������ ... ������� 15% (��������!!!)
                    if (a2h.salary != null){
                        //throw new EnergyproSystemException("� �������� � ��� " + a2h.tabNumber + " �� ������� �����");
                        a2h.salary = (a2h.salary.multiply(new BigDecimal(0.15)).setScale(2, BigDecimal.ROUND_HALF_UP));
                    }

                    a2h.salaryHours = (a2h.salaryHours.multiply(new BigDecimal(0.15)).setScale(2, BigDecimal.ROUND_HALF_UP));
                    a2h.paysWork = (a2h.paysWork.multiply(new BigDecimal(0.15)).setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                ///////////////


                if (a2h.code == Integer.MIN_VALUE){
                    a2hDAO.add(a2h);
                }
                else{
                    a2hDAO.save(a2h);
                }

                // �������� ���� �� �������� � ��������������� ..

                /* 27.05.11 ����������, ������������ �� ���� ��. ������, �� ���� ��������� (���� �� �� ��������� ��������) - ��. ����
                // 29.04.11 ������ ������������ � ��������� 12240 - ��� ������� ��������� ����
                if (
                        (! a2h.tabNumber.equals("12240"))
                        &&(! a2h.tabNumber.equals("274"))
                        &&(! a2h.tabNumber.equals("12791"))
                        &&(! a2h.tabNumber.equals("13080"))
                )
                {
                    a2hFilter = new ENAct2HumenFilter();
                    a2hFilter.actRef.code = actCode;
                    a2hFilter.humenKindRef.code = ENHumenItemKind.ELTEH;
                    a2hFilter.tabNumber = a2h.tabNumber;
                    int[] a2hArr_ = a2hDAO.getFilteredCodeArray(a2hFilter, null, null, 0, -1, null);
                    for (int ii=0; ii < a2hArr_.length; ii++){
                        ENAct2Humen a2h_ = a2hDAO.getObject(a2hArr_[ii]);
                        // ���� ������������ .. ������� 5%
                        if (a2h_.salary != null){
                            //throw new EnergyproSystemException("� �������� � ��� "+ a2h.tabNumber +" �� ������� �����");
                            a2h_.salary = ( a2h_.salary.multiply(new BigDecimal(0.05)).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }

                        a2h_.salaryHours = ( a2h_.salaryHours.multiply(new BigDecimal(0.05)).setScale(2, BigDecimal.ROUND_HALF_UP));
                        a2h_.paysWork = ( a2h_.paysWork.multiply(new BigDecimal(0.05)).setScale(2, BigDecimal.ROUND_HALF_UP));
                        a2hDAO.save(a2h_);
                    }
                }
                */

                ////////////////////////////////////////////////////////////////////////////////////////////////////////////
                // 30.05.11 ��������, ���� �� �������� � ��������������� - �����, ����� ��� 2 ���� �� ��������� ����� �������
                a2hFilter = new ENAct2HumenFilter();
                a2hFilter.actRef.code = actCode;
                a2hFilter.humenKindRef.code = ENHumenItemKind.ELTEH;
                a2hFilter.tabNumber = a2h.tabNumber;
                int[] a2hArr_ = a2hDAO.getFilteredCodeArray(a2hFilter, null, null, 0, -1, null);
                for (int ii=0; ii < a2hArr_.length; ii++)
                {
                    ENAct2Humen a2h_ = a2hDAO.getObject(a2hArr_[ii]);
                    // �������� ����� �������� � ��. ������� � ����� �� ���������, ��� � � �������� �������� (�.�. � ��������-������������)
                    a2h_.timeWork = a2h_.timeWork.subtract(a2h.timeDelivery).setScale(2, BigDecimal.ROUND_HALF_UP);
                    a2h_.timeWorkFact = a2h_.timeWork;
                    a2h_.paysWork = a2h_.salaryHours.multiply(a2h_.timeWork.setScale(2,java.math.BigDecimal.ROUND_HALF_UP)).setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
                    a2hDAO.save(a2h_);
                }
                ////////////////////////////////////////////////////////////////////////////////////////////////////////////

            } // �����             // �������� ....


            // ������ �� ��� �������� �� ���������� ����� ����
            if ( finWorkerList.totalCount < a2hArr.length){
                for (int i = finWorkerList.totalCount; i < a2hArr.length ; i++){
                    a2hDAO.remove(a2hArr[i]);
                }
            }

            String prevTabNumber = "";
            //BigDecimal calendarTime = monthTime.divide(monthDay, 2, BigDecimal.ROUND_HALF_UP);

            // �������� ������� �� ������ ....
            /*
            ENAct2HumenFilter a3Filter = new ENAct2HumenFilter();
            a3Filter.actRef.code = act.code;
            a3Filter.orderBySQL = "enact2humen.tabnumber, enact2humen.humenkindrefcode desc";
            ENAct2HumenShortList a3List = a2hDAO.getScrollableFilteredList(a3Filter, 0, -1);
            for (int a3=0; a3 < a3List.totalCount; a3++){
                if ( ! prevTabNumber.equals(a3List.get(a3).tabNumber) )
                {
                    recalc4Norms(new Integer(a3List.get(a3).tabNumber).intValue(), act.code, calendarTime);
                    prevTabNumber = a3List.get(a3).tabNumber;
                }
            }
            */


            /////////////////////////////////
            // �������� ���� ....
            // ������� � ���� 2011
            Calendar c = Calendar.getInstance();
            c.setTime(act.dateGen);

            if (
                ( c.get(Calendar.YEAR) == 2011 ) && ( c.get(Calendar.MONTH) > Calendar.MAY )
                || (c.get(Calendar.YEAR) > 2011)
            )
            {
                ENAct2HumenFilter a2Filter = new ENAct2HumenFilter();
                a2Filter.actRef.code = act.code;
                a2Filter.orderBySQL = "enact2humen.tabnumber, enact2humen.humenkindrefcode desc";
                ENAct2HumenShortList a2List = a2hDAO.getScrollableFilteredList(a2Filter, 0, -1);
                prevTabNumber = "";
                int kindCode = Integer.MIN_VALUE;

                BigDecimal timeWork = new BigDecimal(0);
                BigDecimal timeWork2 = new BigDecimal(0);
                boolean isRemoveElTech = false;
                for (int a2=0; a2 < a2List.totalCount; a2++){

                    /** 19.01.2015 +++ ��������� �������� �������� �������, ����� ������������ ��� ������� ����������...  */
                    BigDecimal[] monthTimes = finLogic.getWorkTime(act.dateAct, a2List.get(a2).tabNumber);
                    BigDecimal monthTime = monthTimes[0]; //finLogic.getWorkTimeInMonth(act.dateGen).setScale(2, BigDecimal.ROUND_HALF_UP);
                    BigDecimal monthDay = monthTimes[1];

                    if ( ! prevTabNumber.equals(a2List.get(a2).tabNumber) )
                    {
                        prevTabNumber = a2List.get(a2).tabNumber;
                        kindCode = a2List.get(a2).humenKindRefCode;
                        timeWork2 = new BigDecimal(0);


                        int countDays = a2hDAO.getCountDaysInActByTabNumber(act.code, a2List.get(a2).tabNumber);

                        timeWork = monthTime.divide(monthDay, 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(countDays)).setScale(2, BigDecimal.ROUND_HALF_UP);


                        if (a2List.get(a2).timeWork.doubleValue() > timeWork.doubleValue()){
                            ENAct2Humen a2h__ = a2hDAO.getObject(a2List.get(a2).code);
                            a2h__.timeWork = timeWork;
                            a2h__.paysWork = a2h__.salaryHours.multiply(a2h__.timeWork.setScale(2,java.math.BigDecimal.ROUND_HALF_UP)).setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
                            timeWork2 = timeWork;
                            a2hDAO.save(a2h__);
                            isRemoveElTech = true;
                        }
                        else{
                            timeWork2 = timeWork.subtract(a2List.get(a2).timeWork);
                            isRemoveElTech = false;
                        }
                    }

                    if (
                            (prevTabNumber.equals(a2List.get(a2).tabNumber))
                            && (kindCode != a2List.get(a2).humenKindRefCode)
                    )
                    {
                        if ( isRemoveElTech ){
                            a2hDAO.remove(a2List.get(a2).code);
                        }
                        else{
                            ENAct2Humen a2h__ = a2hDAO.getObject(a2List.get(a2).code);
                            if ( a2h__.timeWork.doubleValue() > timeWork2.doubleValue() ){
                                a2h__.timeWork = timeWork2;
                                a2h__.paysWork = a2h__.salaryHours.multiply(a2h__.timeWork.setScale(2,java.math.BigDecimal.ROUND_HALF_UP)).setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
                                a2hDAO.save(a2h__);
                            }
                        }
                    }
                }
            }
            ////////////////////////////////


            // ������������ �������
            ENAct2TransportFilter a2tFilter = new ENAct2TransportFilter();
            ENAct2TransportDAO a2tDAO = new ENAct2TransportDAO(connection, userProfile);
            a2tFilter.actRef.code = act.code;
            int[] a2tArr = a2tDAO.getFilteredCodeArray(a2tFilter, null, null, 0, -1, null);


            TKTransportRealFilter trFilter = new TKTransportRealFilter();
            trFilter.conditionSQL =
                "tktransportreal.code in ( " +
                " Select " +
                " ent.transportrealcode  " +
                " From entransportitem ent left join  finworker fw on ent.finworkercode = fw.code,  enact2enplanwork ena2, enplanworkitem pwi " +
                " Where " +
                " ena2.actrefcode = " + act.code +
                " and ent.planrefcode = ena2.plancode " +
                //" and ent.finworkercode = fw.code " +
                // � ��������� �� ��������� ����������� ����������, ������ ��� ��� �� ����������� �� ��� �������� �����
                //" and (ent.finworkercode = fw.code or (ent.finworkercode is null and ent.tktransporttypecode = " + TKTransportType.MECHANIZM + ")) " +
                " and ent.planitemrefcode = pwi.code " +
                " and pwi.countgen > 0  " +
                " and coalesce(ent.transportrealcode, -1) <> -1 " +
                " ) " ;

            /// ��� ���� ��� �� ������ ������ ��������� �� �������� � ������. ���������� ��������� ���������
            trFilter.transportstatus.code = TKTransportStatus.TKTRANSPORTSTATUS_WORK;  ///
            ///

            trFilter.orderBySQL = "tktransportreal.invnumber";

            TKTransportRealDAO trDAO = new TKTransportRealDAO(connection, userProfile);
            TKTransportRealShortList trList = trDAO.getScrollableFilteredList(trFilter, 0, -1);
            ENAct2Transport a2t = null;
            for (int i=0; i < trList.totalCount; i++){
                BigDecimal workerWorkTime = new BigDecimal(0);

                ENTransportItemFilter transportFilter = new ENTransportItemFilter();
                transportFilter.transportReal.code = trList.get(i).code;
                transportFilter.conditionSQL =
                    "entransportitem.code in ( " +
                    " Select " +
                    " ent.code  " +
                    " From entransportitem ent left join finworker fw on ent.finworkercode = fw.code,  enact2enplanwork ena2, enplanworkitem pwi " +
                    " Where " +
                    " ena2.actrefcode = " + act.code +
                    " and ent.planrefcode = ena2.plancode " +
                    //" and ent.finworkercode = fw.code " +
                    // � ��������� �� ��������� ����������� ����������, ������ ��� ��� �� ����������� �� ��� �������� �����
                    //" and (ent.finworkercode = fw.code or (ent.finworkercode is null and ent.tktransporttypecode = " + TKTransportType.MECHANIZM + ")) " +
                    " and ent.planitemrefcode = pwi.code " +
                    " and pwi.countgen > 0  " +
                    " ) " ;

                ENTransportItemShortList transportList = transportDAO.getScrollableFilteredList(transportFilter, 0, -1);


                if ( i < a2tArr.length ){
                    a2t = a2tDAO.getObject(a2tArr[i]);
                }
                else
                {
                    a2t = new ENAct2Transport();
                    a2t.code = Integer.MIN_VALUE;
                }

                a2t.actRef.code = act.code;
                a2t.invNumber = trList.get(i).invNumber;
                a2t.name = trList.get(i).name + " " + trList.get(i).gosNumber;
                BigDecimal gsm = new BigDecimal(0);
                for (int j=0; j < transportList.totalCount; j++){
                    // ��� �� ��� �� ��������� !!!!
                    // �� ���� � ���������� ��� ��������� ���� ����� ���������� ��� ������ !!!
                    if (transportList.get(j).tktransportTypeCode != TKTransportType.BRIGADE )
                    {
                        workerWorkTime = workerWorkTime.add(transportList.get(j).countWorkFact)    ;
                    }
                    // ������� ����� ������� ...
                    BigDecimal[] distArr = transportLogic.getDistancesByTransport(transportList.get(j).code, false);
                    if ( distArr != null){
                        BigDecimal delTime = transportLogic.calcTimeByDistaces(distArr[0], distArr[1], distArr[2], planLogic.isWinterMonth( transportList.get(j).planRefDateStart), transportLogic.isTraktor(transportList.get(j).code)).setScale(2, BigDecimal.ROUND_HALF_UP);
                        workerWorkTime = workerWorkTime.add(delTime).setScale(2, BigDecimal.ROUND_HALF_UP);
                    }
                    // ���-�� ���
                    FINMaterialsDAO fDAO = new FINMaterialsDAO(connection, userProfile);
                    FINMaterialsFilter fFilter = new FINMaterialsFilter();
                    fFilter.statusRef.code = FINMaterialsStatus.GOOD;
                    fFilter.conditionSQL = "finmaterials.estimateitemrefcode in ("+
                    "select entransport2enestimate.estimaterefcode from entransport2enestimate where entransport2enestimate.transportrefcode = " + transportList.get(j).code +
                    ")";
                    FINMaterialsShortList fList = fDAO.getScrollableFilteredList(fFilter, 0, -1);
                    for (int j1 = 0; j1 < fList.totalCount; j1++ ){
                        gsm = gsm.add(fList.get(j1).quantity).setScale(2, BigDecimal.ROUND_HALF_UP);
                    }
                }

                a2t.expense = gsm;
                a2t.timeWork = workerWorkTime;
                if (trList.get(i).transportstatusCode == TKTransportStatus.TKTRANSPORTSTATUS_WORK) {
                a2t.depreciationMonth = finLogic.getDepreciation(trList.get(i).invNumber, act.dateGen).setScale(3, BigDecimal.ROUND_HALF_UP);

                BigDecimal[] monthTimes = finLogic.getWorkTime(act.dateAct);
                BigDecimal monthTime = monthTimes[0];

                a2t.depreciationHours = a2t.depreciationMonth.divide(monthTime, 3 , java.math.BigDecimal.ROUND_HALF_UP);

                BigDecimal vPaysWork = new BigDecimal(0);
                vPaysWork =  (a2t.depreciationHours.multiply(workerWorkTime)).setScale(5, java.math.BigDecimal.ROUND_HALF_UP);
                if (vPaysWork.doubleValue() > 0.01 ){
                    a2t.paysWork = vPaysWork.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
                }
                else if (vPaysWork.doubleValue() == 0.0 ){
                    a2t.paysWork = new BigDecimal(0.0);
                }
                else
                {
                    a2t.paysWork = new BigDecimal(0.01);
                }
                // ���� ������� ������ 0,01 �� ���������� ��������� 0,01
                //a2t.paysWork = (a2t.depreciationHours.multiply(workerWorkTime)).setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
                //
                }
                if (a2t.code == Integer.MIN_VALUE){
                    a2tDAO.add(a2t);
                }
                else{
                    a2tDAO.save(a2t);
                }


            }
            // ������ �� ��� �������� �� ���������� ����� ����
            if ( trList.totalCount < a2tArr.length){
                for (int i = trList.totalCount; i < a2tArr.length ; i++){
                    a2tDAO.remove(a2tArr[i]);
                }
            }

            System.out.println("Final fillActData : act �" + act.numberGen + " ("+ act.code+")");

            } catch (Exception e) {
                throw new EnergyproSystemException(e);
            } finally {

                try {
                    if  ( (fkConnection != null) && ! fkConnection.isClosed() )
                        fkConnection.close();
                } catch (SQLException e) {
                }
            }


    }



    public ENActShortList getActsList(int actCode, boolean isException) throws PersistenceException, ParseException
    {
        return new ActCalculator(connection, userProfile).getActsList(actCode, isException);

        /*
        //Hashtable _actsList = new Hashtable();
        _actsList = new Hashtable();

        ENActShortList out = new ENActShortList();
        ENActDAO dao = new ENActDAO(connection, userProfile);
        ENActShort as = dao.getShortObject(actCode);

        //synchronized (_actsList)
        {
            getActs(actCode, "" + Integer.MIN_VALUE, isException);

            ActsShort hKey = new ActsShort("" + actCode, new SimpleDateFormat("dd.MM.yyyy").format(as.dateGen), as.numberGen);
            _actsList.put(hKey, hKey);


        Vector v1 = new Vector(_actsList.keySet());
        // Collections.sort(v, Collections.reverseOrder());

         // Display (sorted) hashtable.
        int i = 0;
        String actCodes = "" + Integer.MIN_VALUE;
         for (Enumeration e = v1.elements(); e.hasMoreElements();)
         {
            ActsShort key1 = (ActsShort)e.nextElement();
            ActsShort qq_1 = (ActsShort) _actsList.get(key1);
            actCodes = actCodes + "," + qq_1.actCode;

         }
         ENActFilter f = new ENActFilter();
         f.conditionSQL = "code in (" + actCodes + ")";
         out = dao.getScrollableFilteredList(f, 0, -1);
         //out.totalCount = i;

        }
        */
        //ENActShortList out = new ENActShortList();
        //return out;
    }


	public BigDecimal getTimeGen4DriverByPositionAndSalary(String tabNumber,
			int planCode, Date planDate, int positionCode, BigDecimal salary,
			String positionId) throws PersistenceException {

		ENTransportItemDAO transportDAO = new ENTransportItemDAO(connection, userProfile);
		TransportLogic transportLogic = new TransportLogic(connection, userProfile);
		PlanWorkLogic planLogic = new PlanWorkLogic(connection, userProfile);

        BigDecimal out = new BigDecimal(0);

        BigDecimal workerWorkTime = new BigDecimal(0);
        BigDecimal workerWorkTimeOnly = new BigDecimal(0);

        ENTransportItemShortList transportList = transportDAO.getListForActCalcNormsByPositionAndSalary(tabNumber, planCode, positionCode, salary, positionId);
        String trPlanCodes = "" + Integer.MIN_VALUE;
        for (int j=0; j < transportList.totalCount; j++){


            // ����� ������ � ������ ��� ���� ����������� .. ����� ���������� ����
            if (transportList.get(j).tktransportTypeCode != TKTransportType.BRIGADE)
            {
                workerWorkTime = workerWorkTime.add(transportList.get(j).countWorkFact).setScale(2, BigDecimal.ROUND_HALF_UP);
                workerWorkTimeOnly = workerWorkTimeOnly.add(transportList.get(j).countWorkFact).setScale(2, BigDecimal.ROUND_HALF_UP);
            }

            // ����� ��� ������� �������� ������ ��������� ���� � ������������ ..
            if ((transportList.get(j).tktransportTypeCode == TKTransportType.BRIGADE)
                // ����� ��� ... ����� ���� ���������� �������� - �  �����. 0 ....
                //&& (transportList.get(j).distance.doubleValue() > 0 )
            )
            {
                trPlanCodes = trPlanCodes + ", " + transportList.get(j).planRefCode;
            }

            // ������� ����� �������� ...
            // ����� ������ .. ����� �������������� !!!
            // �� 01.06.2011 ������������������ ;))) :(

            BigDecimal[] distArr = transportLogic.getDistancesByTransport(transportList.get(j).code, false); // ��� ����� ������� ��� ���������� ...
            if ( distArr != null){
                BigDecimal delTime = transportLogic.calcTimeByDistaces(distArr[0], distArr[1], distArr[2], planLogic.isWinterMonth( planDate ), transportLogic.isTraktor(transportList.get(j).code)).setScale(2, BigDecimal.ROUND_HALF_UP);
                workerWorkTime = workerWorkTime.add(delTime).setScale(2, BigDecimal.ROUND_HALF_UP);
            }

        }

        /* �� ���� .. ������������������
        //if (addCondition.length() > 0){
        if ( ! trPlanCodes.equals(""+Integer.MIN_VALUE)){
            ENDeliveryTimePlanDAO dpDAO = new ENDeliveryTimePlanDAO(connection, userProfile);
            ENDeliveryTimePlanFilter dpFilter = new ENDeliveryTimePlanFilter();
            dpFilter.conditionSQL = " planworkrefcode in (" + trPlanCodes + ")";
            ENDeliveryTimePlanShortList dpList = dpDAO.getScrollableFilteredList(dpFilter, 0, -1);
            for (int qq1=0; qq1 < dpList.totalCount; qq1++){
                workerWorkTime = workerWorkTime.add(dpList.get(qq1).deliveryTimeFact);
                workerDeliveryTime = workerDeliveryTime.add(dpList.get(qq1).deliveryTimeFact);
            }
        }
        */


        out = workerWorkTime.setScale(2, BigDecimal.ROUND_HALF_UP);

        return out;
    }

    public ENAct getActByPlanCode(int planCode, boolean isGenException)  throws PersistenceException
    {

        if (planCode == Integer.MIN_VALUE){
            throw new EnergyproSystemException("��� ����� ������ ��� �������� ������� ����� � ���� ...");
        }

        ENAct out = new ENAct();

        ENAct2ENPlanWorkDAO a2DAO = new ENAct2ENPlanWorkDAO(connection, userProfile);
        ENAct2ENPlanWorkFilter a2Filter = new ENAct2ENPlanWorkFilter();
        a2Filter.plan.code = planCode;
        ENAct2ENPlanWorkShortList a2List = a2DAO.getScrollableFilteredList(a2Filter,0,-1);
        //int[] arr = a2DAO.getFilteredCodeArray(a2Filter, null, null, 0, 1, null);

        if ( a2List.totalCount != 1 &&  isGenException){
        //if ( arr.length != 1 &&  isGenException){
            throw new EnergyproBusinessException("error in count act in plans, code="+planCode);
        }

        if (( a2List.totalCount == 0 ) && ( ! isGenException)){
        //if (( arr.length == 0 ) && ( ! isGenException)){
            return null;
        }

        ENActDAO aDAO = new ENActDAO(connection, userProfile);
        //out = aDAO.getObject(arr[0]);
        out = aDAO.getObject(a2List.get(0).actRefCode);

        if ( out.statusRef.code == ENActStatus.CANCELED){
            if (isGenException){
                throw new EnergyproSystemException("��� ���������� !!!");
            }
            else
                return null;
        }
                return out;
    }

    public ENAct getActByWorkOrderCode(int workOrderCode, boolean isGenException) throws PersistenceException
    {
        ENWorkOrder2ENPlanWorkDAO dao = new ENWorkOrder2ENPlanWorkDAO(connection, userProfile);
        ENWorkOrder2ENPlanWorkFilter f = new ENWorkOrder2ENPlanWorkFilter();
        f.workOrder.code = workOrderCode;

        ENWorkOrder2ENPlanWorkShortList list = dao.getScrollableFilteredList(f,0,-1);

        if (list.totalCount > 0){
            return getActByPlanCode(list.get(0).planCode, isGenException);
        }
        else{
            return null;
        }
    }

    public void validateAct2Plan(ENAct act, int planCode)
    {

        /*
        ENPlanWorkDAO planDAO = new ENPlanWorkDAO(connection, userProfile);
        ENPlanWork plan = planDAO.getObject(planCode);

        if (plan == null){
            throw new EnergyproBusinessException("Plan code null");
        }
        */

        // ��������� ������� � �.�.
        if ((act.statusRef.code == ENActStatus.CANCELED) || (act.statusRef.code == ENActStatus.CLOSED)){
            throw new EnergyproBusinessException("Act canceled, code=" + act.code);
        }

        /*
        * act.actTypeRef �������������� ����� � ����� ���������� �������� !!!
        *
        if (act.actTypeRef.code != plan.stateRef.code ) {
            throw new EnergyproBusinessException("Act state("+act.actTypeRef.code+") and planstate("+plan.stateRef.code+") not equals" + actCode);
        }
        */


    }

    public void validateAct2Plan(int actCode, int planCode)  throws PersistenceException
    {
        if (actCode == Integer.MIN_VALUE){
            throw new EnergyproBusinessException("Act code null");
        }
        if (planCode == Integer.MIN_VALUE){
            throw new EnergyproBusinessException("Plan code null");
        }

        ENActDAO actDAO = new ENActDAO(connection, userProfile);
        ENAct act = actDAO.getObject(actCode);

        if (act == null){
            throw new EnergyproBusinessException("Act not found? code="+actCode);
        }

        validateAct2Plan(act, planCode);
    }


    public String getPlanCodesByAct(int actCode) throws PersistenceException
    {
        String out = "" + Integer.MIN_VALUE;
        ENAct2ENPlanWorkDAO dao = new ENAct2ENPlanWorkDAO(connection, userProfile);
        ENAct2ENPlanWorkFilter f = new ENAct2ENPlanWorkFilter();
        f.actRef.code = actCode;
        ENAct2ENPlanWorkShortList l = dao.getScrollableFilteredList(f, 0, -1);
        for (int i = 0; i < l.totalCount; i++){
            out = out + ", " + l.get(i).planCode;
        }
        return out;
    }


        protected java.sql.Connection getNEWConnection(String dataSourceName) throws DatasourceConnectException
        {
            java.sql.Connection    _connection = null;
            try {

                InitialContext initialContext = new InitialContext();
                DataSource dataSource = (DataSource) initialContext
                        .lookup(dataSourceName);
                _connection = dataSource.getConnection();

                return _connection;
            } catch (NamingException e) {
                //System.out.print("error");
                throw new DatasourceConnectException(dataSourceName, e);
            } catch (SQLException e) {
                //System.out.print("error");
                throw new DatasourceConnectException("��� ����� � ���.���������� ... ������� ������ ��� ��� ��� �� ...", e);
            }
        }

        /**
         * 	���������, ��� � ��� ������ ���� �� ���� �������-���� ���������
         *  � ����� �� ������������, ���������������� ��������� ��� ���������
         *  ��������� ������� "������ �����������"
         *
         * @param actCode ��� ����
         * @return true - ���� ���� �������-���� �� ��������������� ��������� �����, false - ���� ���
         * @throws PersistenceException
         */
        public boolean checkActProductionHasAutomaticPlan(int actCode) throws PersistenceException {
        	ENAct2ENPlanWorkDAO act2planDao = new ENAct2ENPlanWorkDAO(connection, userProfile);
        	PlanWorkLogic planWorkLogic = new PlanWorkLogic(connection, userProfile);
        	ENEstimateItem2PlanDAO estimate2PlanDao = new ENEstimateItem2PlanDAO(connection, userProfile);

        	ENAct2ENPlanWorkFilter act2planFilter = new ENAct2ENPlanWorkFilter();
        	act2planFilter.actRef.code = actCode;

        	ENAct2ENPlanWorkShortList act2planList = act2planDao.getScrollableFilteredList(act2planFilter, 0, -1);

        	for(int i = 0; i < act2planList.totalCount; i++) {
        		int currentMonthCode = planWorkLogic.getParentCurrentPlanCode(act2planList.get(i).planCode);

        		ENEstimateItem2PlanFilter estimate2PlanFilter = new ENEstimateItem2PlanFilter();
        		estimate2PlanFilter.planRef.code = currentMonthCode;
        		estimate2PlanFilter.typeRef.code = ENEstimateItem2PlanType.OWN_PRODUCTION;

        		int[] estimate2PlanCodes = estimate2PlanDao.getFilteredCodeArray(estimate2PlanFilter, 0, -1);
        		if(estimate2PlanCodes.length > 0) {
        			return true;
        		}
        	}


        	return false;

        }

        /**
         *
         * ������� ��� ���������� ����, � ����� ����������� ��������� � ��� �����
         *
         * @param actCode ��� ����
         * @param isCalcAct ����� �� ���������� ���� � ���������
         * @throws PersistenceException
         */
        public void signatured(int actCode, boolean isCalcAct) throws PersistenceException
        {
        	Connection fConn = null;

        	try {
				fConn = getNEWConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
			} catch (DatasourceConnectException e) {
				throw new EnergyproSystemException(e.getMessage());
			}

            ENActDAO dao = new ENActDAO(connection, userProfile);
            FINLogic finLogic = new FINLogic(fConn, userProfile);
            ENPlanWorkDAO planDao = new ENPlanWorkDAO(connection, userProfile);
            ENAct2HumenDAO a2hDAO = new ENAct2HumenDAO(connection, userProfile);
            ENPlanWorkStateDAO pwStDAO = new ENPlanWorkStateDAO(connection, userProfile);
			SCLogic scLogic = new SCLogic(connection, fConn, userProfile);
			ENAct2FinKodIstDAO actFinKodIstDao = new ENAct2FinKodIstDAO(connection, userProfile);
			
            ENAct act = dao.getObject(actCode);
            if (act.statusRef.code != ENActStatus.GOOD){
                throw new EnergyproSystemException("� ������ �� ��������� ������������ ����� �����² ���� ...", userProfile);
            }

            if(this.checkZKUMountingByAct(act)) {
            	this.insertZKUsByAct(act);
            }
            
            if(scLogic.checkSealsInAct(act.code, ENEstimateItemKind.MATERIALS)) {
            	FinKodIst ist = this.getFinKodIstByAct(act);
            	ENAct2FinKodIstShortList actFinKodIstList = actFinKodIstDao.getListByAct(act);
            	if(ist != null && actFinKodIstList.totalCount == 0) {
            		ENAct2FinKodIst actFinKodIst = new ENAct2FinKodIst();
            		actFinKodIst.actRef.code = act.code;
            		actFinKodIst.kodIstRef.code = ist.code;
            		actFinKodIstDao.add(actFinKodIst);
            	}
            }

            /** SUPP-12236... 13.02.2014 +++ ������ ����������� ������ �� ���� ��� ����� �� ������� */
            boolean recalcServicesFact = false;
            ServicesLogic soLogic = new ServicesLogic(connection, userProfile);

            ENServicesObject sObj = null;
            if (act.element.typeRef.code == ENElementType.SERVICES_OBJECT) {
                sObj = soLogic.getServicesObjectByElementCode(act.element.code);

                if (sObj.calcTypeRef.code == ENServicesObjectCalcType.BY_FACT) {
                    recalcServicesFact = true;
                    // �������� ������ ����������� ������...
                    soLogic.recalcServicesOnlyFactCalcByAct(act.code);
                }
            }


            /** NET-4429... +++ �������� ���� ���� � ������ ������� */
            if (act.element.typeRef.code == ENElementType.SERVICES_OBJECT) {

            	boolean allowed = false;
            	ENAct2ENPlanWorkDAO act2planDao = new ENAct2ENPlanWorkDAO(connection, userProfile);

            	ENAct2ENPlanWorkFilter act2planFilter = new ENAct2ENPlanWorkFilter();
            	act2planFilter.actRef.code = act.code;

            	int act2planArr[] = act2planDao.getFilteredCodeArray(act2planFilter, 0, -1);
            	for (int i = 0; i < act2planArr.length; i++) {
            		ENAct2ENPlanWork act2plan = act2planDao.getObject(act2planArr[i]);
            		ENPlanWork plan = planDao.getObject(act2plan.plan.code);

            		if (act.dateAct.equals(plan.dateStart)) {
            			allowed = true;
            		}
            	}

            	if (!allowed) {
            		throw new SystemException("\n\n NET-4429..."
            				+ "\n ���� ���� �� ������� ���� ������� �� ���� ���������� ������!!!");
            	}
            }

            // �������� ��� ����������, ����� �� ���� ��� �������� ���������� ��� ��� ����
            // (������ ����������� �� �� ����������)
            if (act.element.typeRef.code == ENElementType.EQUIPMENT_OBJECTS ||
            	act.element.typeRef.code == ENElementType.EQUIPMENT_REPAIR_OBJECTS  ||
            	act.element.typeRef.code == ENElementType.SDTU ||
            	act.element.typeRef.code == ENElementType.RZA ||
            	act.element.typeRef.code == ENElementType.BUILDER ||
            	act.element.typeRef.code == ENElementType.SIT ||
            	act.element.typeRef.code == ENElementType.ISOLATION ||
            	act.element.typeRef.code == ENElementType.PREPRODUCTION_OBJECT ||
            	act.element.typeRef.code == ENElementType.EB_OBJECTS)
            {
            	ElementLogic elLogic = new ElementLogic(connection, userProfile);
            	String invNumber = elLogic.getElementInvNumber(act.element.code);

            	if (invNumber.length() > 6) {
	        		// ������� ��� � ������������� �� ����������
	        		getBalansWithMainCeh(act, fConn);
            	}
            }

           if (recalcServicesFact) {
                try {
                    Context cnt = new InitialContext();

                    Object soRef = cnt.lookup(ENServicesObjectController.JNDI_NAME);
                    ENServicesObjectControllerHome soHome = (ENServicesObjectControllerHome) PortableRemoteObject
                            .narrow(soRef, ENServicesObjectControllerHome.class);
                    ENServicesObjectController soController = soHome.create();

                    soController.finishWorks(sObj.code, true);

                } catch (RemoteException e) {
                    throw new SystemException(e.getMessage(), e);
                } catch (CreateException e) {
                    throw new SystemException(e.getMessage(), e);
                } catch (NamingException e) {
                    throw new SystemException(e.getMessage(), e);
                }

            } else {

                boolean isServices = false;
                ENPlanWorkFilter planFilter = new ENPlanWorkFilter();
                planFilter.conditionSQL = " enplanwork.code in (select e2a.plancode from enact2enplanwork e2a " +
                        " where e2a.actrefcode = " + actCode + ")";
                int Pln[] = planDao.getFilteredCodeArray(planFilter, 0, -1);
                if (Pln.length > 0) {
                    ENPlanWork plan = planDao.getObject(Pln[0]);
                    if (plan.typeRef.code == ENPlanWorkType.SERVICES_FROM_SIDE
                            || plan.typeRef.code == ENPlanWorkType.SERVICES_FROM_SIDE_INVEST) {
                        isServices = true;
                    }
                }

                if(isCalcAct)
                    this.fillActData(actCode, false);

				if (!isServices) {

					// 28.05.15 NET-4453 ���������� ���������� c �/�
					calculateSalaryCharges(actCode);

				}


				/** SUPP-97236... 13.01.2021... ��� ������� ������ ��������... */
                soLogic.validateProfitability(sObj);


                /*
                 * ��� ������������ ����������� ���� ���� ������ ���������,
                 * � ���� ������ � �����������, ��� ������� �������������
                 * �� ������������� ��������� ��������� ������
                 */
                if(act.actTypeRef.code == ENPlanWorkState.PRODUCTION) {
                	if(this.checkActProductionHasAutomaticPlan(act.code)) {
                		finLogic.createRQFKOrderFromActProduced(act);
                	}
                }

                act = dao.getObjectNOSEGR(act.code);
                act.statusRef.code = ENActStatus.SIGNATURE;
                dao.save(act);
            }

            ///20.10.2017  �������� �� ���������� � ����
            ////           ���� ���� ����� �/� �� �������� � ��� ���������� �� �� ������ ���������� �� ����������
               ENAct2HumenFilter ah2Fil = new ENAct2HumenFilter();
               // ah2Fil.actRef.code = act.code;
               ah2Fil.conditionSQL = " actrefcode = " + act.code + " and paysworkbonus > 0 and coalesce(chargesum, 0) = 0";
               int[] ah2Arr = a2hDAO.getFilteredCodeArray(ah2Fil, 0, -1);

               System.out.println("###############  check chargesum...  act.code = " + act.code + " ::  ah2Arr.length = " + ah2Arr.length
            		   + " :: act.actTypeCode = " + act.actTypeRef.code);

               if (ah2Arr.length > 0 )
               {
            	  // ����� ����� �� ������� � ������� �� ��������� �������
            	  if ((act.actTypeRef.code != ENPlanWorkState.WORK_IN_OUT)
            	       && (act.actTypeRef.code != ENPlanWorkState.REFINEMENT_BY_CONTRACT) 
            	       && (act.actTypeRef.code != ENPlanWorkState.TMC_TRANSFER)){
	            	   ENPlanWorkStateFilter pwSFil = new ENPlanWorkStateFilter();
	               	   pwSFil.code = act.actTypeRef.code;
	               	   ENPlanWorkStateShortList pwSList = pwStDAO.getScrollableFilteredList(pwSFil, 0, -1);

	               	   String pwState = "";

	               	   if (pwSList.totalCount>0){
	               		  pwState = pwSList.get(0).name;
	               	   }

	               	   throw new EnergyproSystemException("\n\n � ���( ��.�= " + act.code +"), ��� ���� = " + pwState + ", ���������� = " +userProfile.userName  + "  �� ��������� ���� ���������� ... ����������� �� ... (���� ����)");
            	  }

               }
               
            // ����������� ������� �������� �� ������� �� �������  
   			if (act.element.typeRef.code == ENElementType.SERVICES_OBJECT 
   				|| act.actTypeRef.code == ENPlanWorkState.DESIGNING
   				|| act.element.typeRef.code == ENElementType.OPERATIVE_OBJECT ) {
   				this.setPostingTemplateAndFinKodIstByAct(act,true);
   			}

        }

        public void unSignaturedServicesFS(int actCode) throws PersistenceException {
            ENActDAO dao = new ENActDAO(connection, userProfile);
            ENAct act = dao.getObject(actCode);
            if (act.statusRef.code != ENActStatus.SIGNATURE) {
                throw new EnergyproSystemException(
                        "� ������ �������� ������������ ����� ���� �� ��������...", userProfile);
            }

            /** 24.04.2013 +++ �������� �� ������� �� */
            ENReconstrModernOZ2ENactDAO oz2actDao = new ENReconstrModernOZ2ENactDAO(connection, userProfile);
            ENReconstrModernOZ2ENactFilter oz2actFilter = new ENReconstrModernOZ2ENactFilter();
            oz2actFilter.actRef.code = actCode;
            int oz2actArr[] = oz2actDao.getFilteredCodeArray(oz2actFilter, 0, -1);
            if (oz2actArr.length > 0) {
                ENReconstrModernOZ2ENact oz2act = oz2actDao.getObject(oz2actArr[0]);
                ENReconstrModernOZDAO ozDao = new ENReconstrModernOZDAO(connection, userProfile);
                ENReconstrModernOZ oz = ozDao.getObject(oz2act.ENReconstrModernOZRef.code);
                throw new EnergyproSystemException("\n" +
                        "\n SUPP-3191..." +
                        "\n ��� ��� ������ �� �� �� �������������/����������� �������� ������ �=" + oz.numbergen);
            }

            deleteAct2Humen(actCode);
            act.statusRef.code = ENActStatus.GOOD;
            dao.save(act);
        }

        public void unSigantured(int actCode) throws PersistenceException
        {
            ENActDAO dao = new ENActDAO(connection, userProfile);
            FINLogic finLogic = new FINLogic(connection, userProfile);

            ENAct act = dao.getObject(actCode);
            if (act.statusRef.code != ENActStatus.SIGNATURE){
                throw new EnergyproSystemException("� ������ �������� ������������ ����� ���� �� ��������...", userProfile);
            }

            if(this.checkZKUMountingByAct(act)) {
            	this.removeZKUsInsertedByAct(act);
            }

            /*
            *  16.02.2012 +++ ��� ����� �� �������,
            *  ������ � ����� ���� ����������� ����� � ����� ������ � ���������
            */

            boolean isServices = false;
            ENPlanWorkDAO planDao = new ENPlanWorkDAO(connection, userProfile);
            ENPlanWorkFilter planFilter = new ENPlanWorkFilter();
            planFilter.conditionSQL = " enplanwork.code in (select e2a.plancode from enact2enplanwork e2a " +
                    " where e2a.actrefcode = " + actCode + ")";
            int Pln[] = planDao.getFilteredCodeArray(planFilter, 0, -1);
            if (Pln.length > 0) {
                ENPlanWork plan = planDao.getObject(Pln[0]);
                if (plan.typeRef.code == ENPlanWorkType.SERVICES_FROM_SIDE
                        || plan.typeRef.code == ENPlanWorkType.SERVICES_FROM_SIDE_INVEST) {
                    isServices = true;
                }
            }

            if (isServices) {
                throw new EnergyproSystemException(
                        "��� �������� ������� ��������� (��� �������) � ���� " +
                        " \"��� ��������\" - \"������\" - \"���� ��������� ���� �� ������\" "  +
                        " \"³������ ���������\"!");
            }

            // NET-2139 �� ����� �������� ������ �� ���������� ��� ��������� ����� ������� � ������� � ��������� ������ �� ���������� ��� �������������� � ����������
            /* 05.11.2013 SUPP-6949 �.�. ��������� ���� ������ ����� ��������� �������� �� ��������, �������� �������
            ENActInTechCond2ENActDAO actInc2ActDAO = new ENActInTechCond2ENActDAO(connection, userProfile);
            ENActInTechCond2ENAct actInc2ActObj = null;
            ENActIncomeTechConditions actIncObj = null;
            ENActIncomeTechConditionsDAO actIncDAO = new ENActIncomeTechConditionsDAO(connection, userProfile);
            ENActInTechCond2ENActFilter actInc2ActFilter = new ENActInTechCond2ENActFilter();
            actInc2ActFilter.actRef.code = act.code;
            int actInc2Act[] = actInc2ActDAO.getFilteredCodeArray(actInc2ActFilter, 0, -1);
            if (actInc2Act.length > 0) {
                actInc2ActObj = actInc2ActDAO.getObject(actInc2Act[0]);
                actIncObj = actIncDAO.getObject(actInc2ActObj.actIncomeRef.code);
                throw new EnergyproSystemException(" ��������� ������� ��������� !!! ��� ��� ��`������ � ����������� ����� �  " + actIncObj.numbergen + " �� " + new SimpleDateFormat("dd.MM.yyyy").format(actIncObj.dategen).toString()  );
            }
            */

            /** 24.04.2013 +++ �������� �� ������� �� */
            ENReconstrModernOZ2ENactDAO oz2actDao = new ENReconstrModernOZ2ENactDAO(connection, userProfile);
            ENReconstrModernOZ2ENactFilter oz2actFilter = new ENReconstrModernOZ2ENactFilter();
            oz2actFilter.actRef.code = actCode;
            int oz2actArr[] = oz2actDao.getFilteredCodeArray(oz2actFilter, 0, -1);
            if (oz2actArr.length > 0) {
                ENReconstrModernOZ2ENact oz2act = oz2actDao.getObject(oz2actArr[0]);
                ENReconstrModernOZDAO ozDao = new ENReconstrModernOZDAO(connection, userProfile);
                ENReconstrModernOZ oz = ozDao.getObject(oz2act.ENReconstrModernOZRef.code);
                throw new EnergyproSystemException("\n" +
                        "\n SUPP-3191..." +
                        "\n ��� ��� ������ �� �� �� �������������/����������� �������� ������ �=" + oz.numbergen);
            }

            ////////// 14.05.13 NET-4235
            //if (checkIfServicesObjectByAct(act, false))
            if (act.element.typeRef.code == ENElementType.SERVICES_OBJECT)
            {
                ServicesLogic soLogic = new ServicesLogic(connection, userProfile);
                ENServicesObject soObj = soLogic.getServicesObjectByElementCode(act.element.code);

                if (soObj.contractNumberServices != null)
                {
                    if (! soObj.contractNumberServices.equals(""))
                    {
                        if (soObj.contractStatusRef.code == ENServicesContractStatus.COMPLETED
                        		&& /*SUPP-89496 ������������� �� ����������� �.�.
                        		 � ��� ������ ��������� �������� ��������� ���
                        		 ���������� ����*/soObj.contractTypeRef.code != ENServicesContractType.CONNECTION)
                        {
                            throw new EnergyproSystemException("\n\nNET-4235 ������ � ������ �� ������� (� " + soObj.contractNumberServices + "), " +
                                    "� ���� ��'������ ��� ���, ����������� � ������ \"������ �������\"!\n" +
                                    "³������ ��������� ���� ����������!");
                        }
                    }
                }
            }
            //////////


            // ������� ... ����� ������������� ... ;))
            //ENAct2HumenDAO a2hDAO = new ENAct2HumenDAO(connection, userProfile);
            //a2hDAO.removeHumensByActCode(actCode);
            deleteAct2Humen(actCode);

            /*
            ENAct2HumenFilter a2hFilter = new ENAct2HumenFilter();
            a2hFilter.actRef.code = act.code;
            int[] a2hArr = a2hDAO.getFilteredCodeArray(a2hFilter,null,null,0,-1,null);
            for (int i=0; i < a2hArr.length; i++){
                a2hDAO.remove(a2hArr[i]);
            }
            */

            /*
             * �������� ��������� ������� �� ���� �� ������������ (���� ����)
             */
            if(act.actTypeRef.code == ENPlanWorkState.PRODUCTION) {
            	if(finLogic.checkActOrRQFKOrderForProductionLink(act.code, true)) {
            		finLogic.deleteRQFKOrdersForProductionByAct(act.code);
            	}
            }

            act.statusRef.code = ENActStatus.GOOD;
            dao.save(act);
        }

        public void unCloseStatusEstimateForWriteOff(int planCode) throws PersistenceException
        {
            ENEstimateItemDAO estDAO = new ENEstimateItemDAO(connection, userProfile);
            ENEstimateItemFilter estFilter = new ENEstimateItemFilter();
            // ������� �� ��������� ������� ��������������� �� ��� ����� ���� ������� ���������
            estFilter.planRef.code = planCode;
            estFilter.kindRef.code = ENEstimateItemKind.MATERIALS;
            ENEstimateItemShortList estList = estDAO.getScrollableFilteredList(estFilter,0,-1);
            for (int i=0; i < estList.totalCount; i++){
                ENEstimateItem est = estDAO.getObject(estList.get(i).code);
                est.statusRef.code = ENEstimateItemStatus.IN_EXPLOITATION;
                estDAO.save(est);
            }

            estFilter = new ENEstimateItemFilter();
            // ������� �� ��������� ������� ��������������� �� ��� ����� ���� ������� ���������
            estFilter.conditionSQL = " ENESTIMATEITEM.CODE in " +
            " ( Select e2e.estimateiteminrefcode from enestimateitem2nstmttm e2e , enestimateitem e , enplanwork p " +
            "  where e2e.estimateitemoutrefcode = e.code  " +
            "    and e.planrefcode = p.code " +
            "    and e2e.typerefcode = 6 " +
            "    and e.kindrefcode = 1 " +
            "    and p.code = " +  planCode +
            " ) ";
            ENEstimateItemShortList estList2 = estDAO.getScrollableFilteredList(estFilter,0,-1);
            for (int i=0; i < estList2.totalCount; i++){
                ENEstimateItem est2 = estDAO.getObject(estList2.get(i).code);
                est2.statusRef.code = ENEstimateItemStatus.IN_EXPLOITATION;
                estDAO.save(est2);
            }



        }


        public void CloseStatusEstimateForWriteOff(int planCode, int actCode) throws PersistenceException
        {
            ENEstimateItemDAO estDAO = new ENEstimateItemDAO(connection, userProfile);
            ENEstimateItemFilter estFilter = new ENEstimateItemFilter();
            // ������� �� ��������� ������� ��������������� �� ��� ����� ���� ������� ���������
            estFilter.planRef.code = planCode;
            estFilter.kindRef.code = ENEstimateItemKind.MATERIALS;
            ENEstimateItemShortList estList = estDAO.getScrollableFilteredList(estFilter,0,-1);
            for (int i=0; i < estList.totalCount; i++){
                ENEstimateItem est = estDAO.getObject(estList.get(i).code);
                est.statusRef.code = ENEstimateItemStatus.WRITE_OFF;
                estDAO.save(est);
            }

            estFilter = new ENEstimateItemFilter();
            // ������� �� ��������� � ������� ���������
            estFilter.conditionSQL = " ENESTIMATEITEM.CODE in " +
            " ( Select e2e.estimateiteminrefcode from enestimateitem2nstmttm e2e , enestimateitem e , enplanwork p " +
            "  where e2e.estimateitemoutrefcode = e.code  " +
            "    and e.planrefcode = p.code " +
            "    and e2e.typerefcode = 6 " +
            "    and e.kindrefcode = 1 " +
            "    and p.code = " +  planCode +
            " ) ";
            ENEstimateItemShortList estList2 = estDAO.getScrollableFilteredList(estFilter,0,-1);
            for (int i=0; i < estList2.totalCount; i++){

                // ������� ��� ���-�� ��������� ��������� �� ������ ������ ��������� ������� ��������� ������� ������ ()
                EstimateLogic estlogic = new EstimateLogic(connection, userProfile);
                BigDecimal countOtherAct = estlogic.getSumCountE2EByEnEstimateINForWriteOff(estList2.get(i).code  ); // ���-�� ������������ ��������� � ������ ����������� �����
            //    BigDecimal countCurrAct = estlogic.getSumCountE2EByEnEstimateINForWriteOffCurrAct(estList2.get(i).code , actCode ); // ���-�� ������������ ��������� � �������� ����
                BigDecimal count = countOtherAct; //.add(countCurrAct);
                // ���� ��������� ���������� ����� �� ��� �� ��� ��������� ����� ������ ������ ��������� ��� ������� ����� ����� ��� ��������� � ������������.
                if (count.doubleValue() == estList2.get(i).countFact.doubleValue() ) {

                ENEstimateItem est2 = estDAO.getObject(estList2.get(i).code);
                est2.statusRef.code = ENEstimateItemStatus.WRITE_OFF;
                estDAO.save(est2);
                }
            }



        }

        /*������� ����� �� ������� ��� ����� "��� ��������� ���� �������� ������"*/
        public BigDecimal getSumByWorkForActReceptTransmisMaterials(int codeAct) {
            BigDecimal result = new BigDecimal(0);
            PreparedStatement statement = null;
            ResultSet set = null;


            String sql = " select \n" +
            "  sum(cost) as cost \n" +
            "   from \n" +
            "  (Select \n" +
            "     tk.code \n" +
            "     , tk.name||(case when substr(tk.name, length(tk.name)) <> '.' then '.' else '' end)||' K = '||cast(coeff.coefficient as varchar) as name \n" +
            "     , tk.techkartnumber \n" +
            "     , tkm.name as namemeasure \n" +
            "     , eni.timegen \n" +
            "     , eni.countgen \n" +
            "     , tk.normoftime \n" +
            "     , eni.costgen as costgen \n" +
            "     , round(cast((coalesce(eni.countgen, 0) * coalesce(eni.costgen,0)) as decimal), 2) as cost \n" +
            "     from  enact2enplanwork ena2 , enplanworkitem eni , tktechcard tk , tkmeasurement tkm, \n" +
            " /*������������� ������������ �������� ���������*/ \n" +
            "     (select \n" +
            "         pi.planrefcode, acpw.actrefcode, pi.kartarefcode, coalesce((case \n" +
            "                 when min(ko.koef) = 0 \n" +
            "             then min(ko.koef) \n" +
            "         else \n" +
            "             (case \n" +
            "                         when sum(case when ko.koef < 0  then 1 else 0 end) = 0 then 1 else -1 END)* \n" +
            "                     round(cast(exp(sum(ln(case when coalesce(ko.koef,0) = 0 then 1 else abs(ko.koef) end))) as decimal),2) \n" +
            "         end), 1) as coefficient \n" +
            "     from \n" +
            "             enplanworkitem as pi  inner join enact2enplanwork as acpw on pi.planrefcode = acpw.plancode \n" +
            "             left join enplanworkitem2tkkoef as piko on pi.code = piko.planworkitemrefcode \n" +
            "         left join tktechcardsourcekoef as ko on piko.sourcekoefcode = ko.code \n" +
            "     where coalesce(pi.countgen,0) <> 0 \n" +
            "     group by \n" +
            "         pi.kartarefcode, \n" +
            "         pi.planrefcode, \n" +
            "         acpw.actrefcode) as coeff /* ����� �������� ������������ �������� ���������*/ \n" +
            " where ena2.actrefcode = " + codeAct +
            "   and ena2.plancode = eni.planrefcode \n" +
            "   and eni.kartarefcode = tk.code \n" +
            "   and tkm.code = tk.measurementcode \n" +
            "   and coeff.kartarefcode = eni.kartarefcode \n" +
            "   and coeff.planrefcode = eni.planrefcode \n" +
            "   and coeff.actrefcode = ena2.actrefcode \n" +
            "   and coalesce(eni.countgen , 0) <> 0) as query \n" +
            "  \n" ;


            try {
                statement = connection.prepareStatement(sql);
                set = statement.executeQuery();

                while (set.next()) {
                    result = set.getBigDecimal(1);
                }

                if (result == null)
                    result = new BigDecimal(0);

                return result;

            } catch (SQLException e) {
                System.out.println(e.getMessage() + "\nstatement - " + sql);
                return result;
            } finally {
                try {
                    if (set != null)
                        set.close();
                } catch (SQLException e) {
                }
                try {
                    if (statement != null)
                        statement.close();
                } catch (SQLException e) {
                }
            }
        }

    /** ������� ������� + ��������  ���� �� �� ���� ��������� ����� **/
        public BigDecimal getTimeGen4DriverByPositionAndSalarySomeTechCard(String tabNumber, int planCode, Date planDate, int positionCode, BigDecimal salary , String TechCardCodes ) throws PersistenceException
        {

            ENTransportItemDAO transportDAO = new  ENTransportItemDAO(connection, userProfile);
            TransportLogic transportLogic = new TransportLogic(connection, userProfile);
            PlanWorkLogic planLogic = new PlanWorkLogic(connection, userProfile);

            BigDecimal out = new BigDecimal(0);

            BigDecimal workerWorkTime = new BigDecimal(0);
            BigDecimal workerWorkTimeOnly = new BigDecimal(0);


            ENTransportItemShortList transportList = transportDAO.getListForActCalcNormsByPositionAndSalarySomeTechCard(tabNumber, planCode, positionCode, salary,TechCardCodes) ;
            String trPlanCodes = "" + Integer.MIN_VALUE;
            for (int j=0; j < transportList.totalCount; j++){


                // ����� ������ � ������ ��� ���� ����������� .. ����� ���������� ����
                if (transportList.get(j).tktransportTypeCode != TKTransportType.BRIGADE)
                {
                    workerWorkTime = workerWorkTime.add(transportList.get(j).countWorkFact).setScale(2, BigDecimal.ROUND_HALF_UP);
                    workerWorkTimeOnly = workerWorkTimeOnly.add(transportList.get(j).countWorkFact).setScale(2, BigDecimal.ROUND_HALF_UP);
                }

                // ����� ��� ������� �������� ������ ��������� ���� � ������������ ..
                if ((transportList.get(j).tktransportTypeCode == TKTransportType.BRIGADE)
                )
                {
                    trPlanCodes = trPlanCodes + ", " + transportList.get(j).planRefCode;
                }

                // ������� ����� �������� ...
                // ����� ������ .. ����� �������������� !!!
                // �� 01.06.2011 ������������������ ;))) :(

                BigDecimal[] distArr = transportLogic.getDistancesByTransport(transportList.get(j).code, false); // ��� ����� ������� ��� ���������� ...
                if ( distArr != null){
                    BigDecimal delTime = transportLogic.calcTimeByDistaces(distArr[0], distArr[1], distArr[2], planLogic.isWinterMonth( planDate ), transportLogic.isTraktor(transportList.get(j).code)).setScale(2, BigDecimal.ROUND_HALF_UP);
                    workerWorkTime = workerWorkTime.add(delTime).setScale(2, BigDecimal.ROUND_HALF_UP);
                }

            }


            out = workerWorkTime.setScale(2, BigDecimal.ROUND_HALF_UP);

            return out;
        }


    public void createSalaryPostings(ENAct act)
    {

    }

    /**
     * ������ ���������� � �/� ��� ���� ����������� �����
     *
     * @param actCode - ��� ����
     */
    //public void calculateSalaryCharges(ENAct act)
    public void calculateSalaryCharges(int actCode)
    {
    	/*
    	if (act == null)
    	{
    		throw new SystemException("\n\nNET-4453 �� ������� ���!");
    	}
    	*/


    	if (actCode == Integer.MIN_VALUE)
    	{
    		throw new SystemException("\n\nNET-4453 �� ������� ��� ����!");
    	}

        Connection fkConnection = null;

    	try
    	{
    		ENActDAO actDao = new ENActDAO(connection, userProfile);
	    	ENAct2HumenDAO a2hDAO = new ENAct2HumenDAO(connection, userProfile);
	    	ENAct2HumenShortList a2hList = a2hDAO.getSalaryChargesList(actCode);

	    	ENAct act = actDao.getObject(actCode);

			if ((act.element.typeRef.code != ENElementType.WRITING_NO_OBJECT)
					&& (act.actTypeRef.code != ENPlanWorkState.TMC_TRANSFER)
					&& (act.actTypeRef.code != ENPlanWorkState.TRUCKING)
					&& (act.actTypeRef.code != ENPlanWorkState.REFINEMENT_BY_CONTRACT)
					&& (act.actTypeRef.code != ENPlanWorkState.WRITINGS_OS) // NET-4383 �������� ��������� ��� ������� ���������
					&& (act.element.code != ENConsts.ENELEMENT_METROLOGY_OBJECT_WRITEOFF) // NET-4561 �������� �����
			) {
				// ����� �� ���� ����� ��� ����� ;))
				ENAct2HumenFilter a2hFilter = new ENAct2HumenFilter();
				a2hFilter.actRef.code = act.code;
				int[] a2hArr = a2hDAO.getFilteredCodeArray(a2hFilter, null, null, 0, -1, null);

				if (a2hArr.length == 0) {
					System.out.println("ActCode = " + act.code);
					throw new EnergyproSystemException("\n\n"
							+ "� ��� ���� �������� ... ����������� �� ... (���� ����)...  � ���� = " + act.numberGen + " ��������� ��� = " + act.code);
				}

    	    	if (a2hList.totalCount == 0)
    	    	{
    	    		throw new SystemException("\n\nNET-4453 ��� ���� �� �������� ����� ��� ���������� ���������� � �/�! ��� ����: " + actCode);
    	    	}

    	    	fkConnection = getNEWConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);

    	    	FINLogic finLogic = new FINLogic(fkConnection, userProfile);


    	    	List<String> tabNumbers = new ArrayList<>();
    	    	for (int i = 0; i < a2hList.totalCount; i++) {
    	    		String tabNumber = a2hList.get(i).tabNumber;

    	    		if (tabNumbers.isEmpty()) {
    	    			tabNumbers.add(tabNumber);
                    } else {
                        for (int t = 0; t < tabNumbers.size(); t++) {
                            if (tabNumbers.get(t).equals(tabNumber)) {
                                break;
                            }
                            if (t == tabNumbers.size() - 1) {
                            	tabNumbers.add(tabNumber);
                                break;
                            }
                        }
                    }
    	    	}


    	    	for (int t = 0; t < tabNumbers.size(); t++) {

    	    		ENAct2Humen a2hObj = finLogic.getBalansWithMainCeh(tabNumbers.get(t), act.dateAct);
    	    		String balans = a2hObj.balans;
    	    		String cehId = a2hObj.cehId;

    	    		for (int i = 0; i < a2hList.totalCount; i++) {
    	    			if (tabNumbers.get(t).equals(a2hList.get(i).tabNumber) ) { // (����. ������) ��� ����� ������� � ������ ����������� ��� ������, ���� ���������� ��� ������ ��� ����������� ����������

    	    				ENAct2Humen a2h = a2hDAO.getObjectNOSEGR(a2hList.get(i).code);

    			    		a2h.chargePercent = a2hList.get(i).chargePercent;
    			    		a2h.chargeRef.code = a2hList.get(i).chargeRefCode;
    			    		if (a2hList.get(i).chargePercent == null ){
    			    			System.out.print(" ������  actCode = " +  actCode );
    			    		}
    			    		a2h.chargeSum = (a2hList.get(i).paysWork.multiply(a2hList.get(i).chargePercent)).divide(new BigDecimal(100), 10/*??*/, java.math.BigDecimal.ROUND_HALF_UP);

    			    		a2h.balans = balans;
    			    		a2h.podrcod = a2hList.get(i).podrcod;
    			    		a2h.cehId = cehId;

    			    		a2hDAO.save(a2h);
    	    			}

    	    		}
    	    	}
            }
    	}
		catch (PersistenceException e)
		{
			throw new SystemException(e.getMessage(), e);
		}
    	catch (DatasourceConnectException e)
    	{
    		throw new SystemException(e.getMessage(), e);
		}
    	finally
    	{
            try
            {
            	if ( (fkConnection != null) && ! fkConnection.isClosed() )
            	{
                    fkConnection.close();
            	}
            }
            catch (SQLException e)
            {
            }
        }
    }

    /**
     * ������ ���������� � �/� ��� ���� ����������� ����� �� ������� �� ������� (� ������� �����)
     *
     * @param actCode - ��� ����
     */
    //public void calculateSalaryChargesForServices(ENAct act)
    public void calculateSalaryChargesForServices(int actCode)
    {
    	/*
    	if (act == null)
    	{
    		throw new SystemException("\n\nNET-4453 �� ������� ���!");
    	}
    	*/

    	if (actCode == Integer.MIN_VALUE)
    	{
    		throw new SystemException("\n\nNET-4453 �� ������� ��� ����!");
    	}

        Connection fkConnection = null;

    	try
    	{
    		ENActDAO actDao = new ENActDAO(connection, userProfile);
    		ENPlanWorkItem2HumenDAO pi2hDAO = new ENPlanWorkItem2HumenDAO(connection, userProfile);
    		ENPlanWorkItem2HumenShortList pi2hList = pi2hDAO.getSalaryChargesList(actCode);

    		ENAct act = actDao.getObject(actCode);

	    	if (pi2hList.totalCount == 0)
	    	{
	    		throw new SystemException("\n\nNET-4453 ��� ���� � ������ �� ������� �� �������� ����� ��� ���������� ���������� � �/�! ��� ����: " + actCode);
	    	}

	    	fkConnection = getNEWConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);

	    	FINLogic finLogic = new FINLogic(fkConnection, userProfile);


	    	List<String> tabNumbers = new ArrayList<>();
	    	for (int i = 0; i < pi2hList.totalCount; i++) {
	    		String tabNumber = pi2hList.get(i).tabNumber;

	    		if (tabNumbers.isEmpty()) {
	    			tabNumbers.add(tabNumber);
                } else {
                    for (int t = 0; t < tabNumbers.size(); t++) {
                        if (tabNumbers.get(t).equals(tabNumber)) {
                            break;
                        }
                        if (t == tabNumbers.size() - 1) {
                        	tabNumbers.add(tabNumber);
                            break;
                        }
                    }
                }
	    	}

	    	for (int t = 0; t < tabNumbers.size(); t++) {

	    		ENAct2Humen a2cObj = finLogic.getBalansWithMainCeh(tabNumbers.get(t), act.dateAct);
	    		String balans = a2cObj.balans;
	    		String cehId = a2cObj.cehId;

	    		for (int i = 0; i < pi2hList.totalCount; i++) {
	    			ENPlanWorkItem2Humen pi2h = pi2hDAO.getObject(pi2hList.get(i).code);

	    			if (tabNumbers.get(t).equals(pi2hList.get(i).tabNumber) ) { // (����. ������) ��� ����� ������� � ������ ����������� ��� ������, ���� ���������� ��� ������ ��� ����������� ����������

		    			pi2h.chargePercent = pi2hList.get(i).chargePercent;
			    		pi2h.chargeRef.code = pi2hList.get(i).chargeRefCode;
			    		pi2h.podrcod = pi2hList.get(i).podrcod;

			    	  //  pi2h.chargeSum = (pi2hList.get(i).paysWork.multiply(pi2hList.get(i).chargePercent)).divide(new BigDecimal(100), 10/*??*/, java.math.BigDecimal.ROUND_HALF_UP);

			    		pi2h.chargeSum = ((pi2hList.get(i).paysWork
			    				.add(pi2hList.get(i).paysWorkSurcharge)
			    				.add(pi2hList.get(i).paysWorkPremium)
			    				.add(pi2hList.get(i).paysWorkAdditional)).multiply(pi2hList.get(i).chargePercent)).divide(new BigDecimal(100), 10/*??*/, java.math.BigDecimal.ROUND_HALF_UP);
			    		// ����� ��������� ��� ��������� � ��������
			    		pi2h.chargeSumWithoutDeliv = new BigDecimal(0);
			    		if (pi2h.humenKindRef.code == ENHumenItemKind.ELTEH){
			    			pi2h.chargeSumWithoutDeliv = ((pi2hList.get(i).paysWorkBonusWithoutDeliv
				    	    		.add(pi2hList.get(i).paysWorkSurchargeWithoutDeliv)
				    	    		.add(pi2hList.get(i).paysWorkPremiumWithoutDeliv)
				    	    		.add(pi2hList.get(i).paysWorkAdditionalWithoutDeliv)).multiply(pi2hList.get(i).chargePercent)).divide(new BigDecimal(100), 10/*??*/, java.math.BigDecimal.ROUND_HALF_UP);
			    		}


			    		pi2h.balans = balans;
			    		pi2h.cehId = cehId;

			    		pi2hDAO.save(pi2h);
	    			}
	    		}
	    	}

		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} catch (DatasourceConnectException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			try {
				if ((fkConnection != null) && !fkConnection.isClosed()) {
					fkConnection.close();
				}
			} catch (SQLException e) {
			}
		}
	}

    /**
     *
     * ���������� ����� ���������� � ����
     *
     * @param act ��� {@link ENAct} ��� �������� ��������� �����
     * @param accountsBU - ���� {@code true} - ������ ����� ��, ���� {@code false} - ��� �����, ����� ��
     * , ���� {@code null} - ��� ����� ��� �������
     * @param accountingTypeCodes ���� ����� ����� ���������� {@link com.ksoe.techcard.valueobject.TKAccountingType}
     * ��� ������� �������������� ����� ��� {@code null} ���� ��� ����
     * @return ����� ���������� �� ����
     * @throws PersistenceException
     * @throws DatasourceConnectException
     */
    public BigDecimal getSumOfMaterialsInAct(ENAct act, Boolean accountsBU, boolean includeAccountingTypes, Integer... accountingTypeCodes) throws PersistenceException, DatasourceConnectException {

    	if(act == null || act.code == Integer.MIN_VALUE) throw new java.lang.NullPointerException("act or act.code is null");

    	BigDecimal sumOut = new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP);

    	Connection fkConnection = null;

    	try {
        	List<Integer> listAccountingTypeCodes = null;
        	if(accountingTypeCodes != null) {
        		if(includeAccountingTypes) {
            		listAccountingTypeCodes = Arrays.asList(accountingTypeCodes);
        		} else {
        			TKAccountingTypeDAO dao = new TKAccountingTypeDAO(connection, userProfile);
        			int[] accountingTypeCodesAll = dao.getFilteredCodeArray(null, 0, -1);
        			List<Integer> listAccountingTypeCodesNotIncluded = Arrays.asList(accountingTypeCodes);
        			listAccountingTypeCodes = new Vector<>();
        			for(int item : accountingTypeCodesAll) {
        				if(!listAccountingTypeCodesNotIncluded.contains(item)) {
        					listAccountingTypeCodes.add(item);
        				}
        			}
        		}

        	}

        	fkConnection = getNEWConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);

        	FKLogic fkLogic = new FKLogic(fkConnection, userProfile);
        	List<String> accountsBUList = fkLogic.getAccountsBU();

        	String planCodes = this.getPlanCodesByAct(act.code);

        	ENEstimateItemDAO estimateDao = new ENEstimateItemDAO(connection, userProfile);
        	ENEstimateItemFilter estimateFilter = new ENEstimateItemFilter();
        	estimateFilter.conditionSQL = String.format("%s in (%s)", ENEstimateItem.planRef_QFielld, planCodes);
        	//estimateFilter.kindRef.code = ENEstimateItemKind.MATERIALS;
        	Vector<Integer> bindedParams = new Vector<>(Arrays.asList(ENEstimateItemKind.MATERIALS, ENEstimateItemKind.GSM));
        	estimateFilter.conditionSQL += String.format(" and %s in (%s)", ENEstimateItem.kindRef_QFielld, Tools.repeatSymbol("?", ",", bindedParams.size()));

        	if(listAccountingTypeCodes != null) {
        		estimateFilter.conditionSQL += String.format(" and %s in (%s)", ENEstimateItem.accountingTypeRef_QFielld
        				, Tools.repeatSymbol("?", ",", listAccountingTypeCodes.size()));
        		bindedParams.addAll(listAccountingTypeCodes);
        	}
        	int[] estimateCodes = estimateDao.getFilteredCodeArray(estimateFilter, 0, -1, bindedParams);
        	if(estimateCodes.length == 0) return sumOut;

        	Vector<Integer> bindedEstimates = new Vector<>();
        	for(int estimateCode : estimateCodes) bindedEstimates.add(estimateCode);

        	if(accountingTypeCodes == null || listAccountingTypeCodes.contains(TKAccountingType.TMC)) {
        		FINMaterialsDAO dao = new FINMaterialsDAO(connection, userProfile);
        		FINMaterialsFilter filter = new FINMaterialsFilter();
        		filter.statusRef.code = FINMaterialsStatus.GOOD;
        		filter.conditionSQL = String.format("%s in (%s)"
        				, FINMaterials.estimateItemRef_QFielld, Tools.repeatSymbol("?", ",", bindedEstimates.size()));


        		FINMaterialsShortList list = dao.getScrollableFilteredList(filter, 0, -1, bindedEstimates);
        		for(FINMaterialsShort item : list.list) {
        			if(accountsBU != null) {
        				String accountToCheck = item.bal_sch;
        				if(accountToCheck == null || accountToCheck.trim().length() == 0) {
        					throw new SystemException("������� � ����������� ������� ��������");
        				}
        				if(accountsBU && !accountsBUList.contains(accountToCheck)) {
        					continue;
        				}
        				if(!accountsBU && accountsBUList.contains(accountToCheck)) {
        					continue;
        				}
        			}
        			sumOut = sumOut.add(item.cost);
        		}
        	}

        	if(accountingTypeCodes == null || listAccountingTypeCodes.contains(TKAccountingType.COUNTERS)) {
        		SCCounterDAO dao = new SCCounterDAO(connection, userProfile);
        		SCCounterFilter filter = new SCCounterFilter();
        		filter.conditionSQL = String.format("%s <> ?", SCCounter.statusRef_QFielld);
        		filter.conditionSQL += String.format(" and %s in (%s)"
        				, SCCounter.estimateItemRef_QFielld, Tools.repeatSymbol("?", ",", bindedEstimates.size()));

        		Vector<Integer> binded = new Vector<>();
        		binded.add(SCCounterStatus.CANCELED);
        		binded.addAll(bindedEstimates);

        		SCCounterShortList list = dao.getScrollableFilteredList(filter, 0, -1, binded);
        		for(SCCounterShort item : list.list) {
        			if(accountsBU != null) {
        				String accountToCheck = item.account;
        				if(accountToCheck == null || accountToCheck.trim().length() == 0) {
        					throw new SystemException("������� � ����������� ������� ��������");
        				}
        				if(accountsBU && !accountsBUList.contains(accountToCheck)) {
        					continue;
        				}
        				if(!accountsBU && accountsBUList.contains(accountToCheck)) {
        					continue;
        				}
        			}
        			sumOut = sumOut.add(item.cost);
        		}
        	}
        	if(accountingTypeCodes == null || listAccountingTypeCodes.contains(TKAccountingType.HOLO)
        			|| listAccountingTypeCodes.contains(TKAccountingType.IMP)
        			|| listAccountingTypeCodes.contains(TKAccountingType.SEAL)) {
        		SCSealDAO dao = new SCSealDAO(connection, userProfile);
        		SCSealFilter filter = new SCSealFilter();
        		filter.conditionSQL = String.format("%s in (%s)"
        				, SCSeal.estimateItemRef_QFielld, Tools.repeatSymbol("?", ",", bindedEstimates.size()));

        		SCSealShortList list = dao.getScrollableFilteredList(filter, 0, -1, bindedEstimates);
        		for(SCSealShort item : list.list) {
        			if(accountsBU != null) {
        				String accountToCheck = item.account;
        				if(accountToCheck == null || accountToCheck.trim().length() == 0) {
        					throw new SystemException("������� � ����������� ������� ��������");
        				}
        				if(accountsBU && !accountsBUList.contains(accountToCheck)) {
        					continue;
        				}
        				if(!accountsBU && accountsBUList.contains(accountToCheck)) {
        					continue;
        				}
        			}
        			sumOut = sumOut.add(item.cost);
        		}
        	}

        	return sumOut;
    	} finally {
			try {
				if ((fkConnection != null) && !fkConnection.isClosed()) {
					fkConnection.close();
				}
			} catch (SQLException e) {
			}
			this.closeConnection();
    	}
    }

    /*������� ����� ���� �� ����*/
    public BigDecimal getSumByActCode(int codeAct) {
        BigDecimal result = new BigDecimal(0);
        PreparedStatement statement = null;
        ResultSet set = null;

        String sql = " select * from get_sum_by_act(" + codeAct + ")";

        try {
            statement = connection.prepareStatement(sql);
            set = statement.executeQuery();

            while (set.next()) {
                result = set.getBigDecimal(1);
            }

            if (result == null)
                result = new BigDecimal(0);

            return result;

        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\nstatement - " + sql);
            return result;
        } finally {
            try {
                if (set != null)
                    set.close();
            } catch (SQLException e) {
            }
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
            }
        }
    }

    public boolean checkForRecyclableMaterials(ENAct act)
    {
    	return checkForRecyclableMaterials(act, true);
    }

    public boolean checkForRecyclableMaterials(ENAct act, boolean isException)
    {
    	if (act.element.code == ENElement.NO_OBJECT_RECYCLABLE_MATERIALS)
    	{
	        AuthLogic al = new AuthLogic(connection, userProfile);

	        if (! al.checkPermissionSilent("ksoe/energynet/ENActController", "createActForRecyclableMaterials"))
	        {
	        	if (isException)
	        	{
	        		throw new EnergyproSystemException("\n\nNET-4543 � ��� ���� ���� �� �� �������� (����������� �������� �� ����.��������)!");
	        	}
	        	else
	        	{
	        		return false;
	        	}
	        }
    	}

    	return true;
    }

    /**
     * ���������� ����� �������� ���.  �� ���� ����� �� ������� ��� ������������ ��������
     *
     * @param isWithDelivery -- ���� �� ���� � ������ �������� � �� ���������
     * */
        public FKTrans2AXTransItemShortList getDataForProvsSalaryByActServicesRed(int actCode , int tkcalckind , boolean isWithDelivery)  throws PersistenceException
        {
        	FKTrans2AXTransItemShortList result = new FKTrans2AXTransItemShortList();
    		FKTrans2AXTransItemShort anObject;
    		result.list = new Vector<>();

    		String selectStr;
    		PreparedStatement statement = null;
    		ResultSet  set = null;

    		selectStr =

    				 new String( tkcalckind == TKCalcKind.OLD ? " select sum(p2h.paysworkbonus)::numeric(15,2) as payswork  \n"
    		                            : isWithDelivery == true ? " select (   sum(coalesce(p2h.paysworkbonus,0)) \n" +
    		                              " 		 + sum(coalesce(p2h.paysworksurcharge,0))  \n" +
    		                              " 	     + sum(coalesce(p2h.paysworkpremium,0))  \n" +
    		                              " 	     + sum(coalesce(p2h.paysworkadditional,0)) )::numeric(15,2) "
    		                            :  " select (   sum(coalesce(p2h.paysworkbonuswithotdlv,0)) \n" +
											 " 		  + sum(coalesce(p2h.paysworksurchrgwthtdlv,0)) \n" +
											 "	      + sum(coalesce(p2h.paysworkpremiumwthtdlv,0))  \n" +
											 "	      + sum(coalesce(p2h.paysworkadditnlwthtdlv,0)) )::numeric(15,2) "
    		                       ) +
    		                              " ,  p2h.cehid   " +
    		                              " ,  p2h.balans as balans " +
    				" from \n"+
    				" enact2enplanwork as a2p \n"+
    				" inner join enplanwork as pw on a2p.plancode = pw.code \n"+
    				" inner join enplanworkitem as pwi on pw.code = pwi.planrefcode \n"+
    				" inner join enplanworkitem2humen as p2h on pwi.code = p2h.plaitemrefcode \n"+
    				" inner join tktechcard as tc on pwi.kartarefcode = tc.code \n"+
    				" inner join tkclassificationtype as tkcls on tc.classificationtypecode = tkcls.code \n"+
    				" where  a2p.actrefcode =  " + actCode +
    				" and pwi.countgen <> 0 \n"+
    				" and (tkcls.costworkscontractor is null or tkcls.costworkscontractor = 0) \n"+
    				" group by p2h.cehid , p2h.balans  \n";
    		try {
    			statement = connection.prepareStatement(selectStr);
    			set = statement.executeQuery();
    			int c=0;
    			while(set.next()) {
    				if(set.getBigDecimal(1).doubleValue()>0){

    					anObject = new FKTrans2AXTransItemShort();

        				anObject.amountCur = set.getBigDecimal(1);
        				if(anObject.amountCur != null) {
        					anObject.amountCur = anObject.amountCur.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
        				}
        				anObject.balCeh = set.getString(2);

        				anObject.balans = set.getString(3);

        				result.list.add(anObject);
        				c++;
    				}

    			}

    			result.setTotalCount(c);
    			return result;
    		} catch (SQLException e) {
                System.out.println(e.getMessage() + "\nstatement - " + selectStr);
                return result;
            } finally {
                try {
                    if (set != null)
                        set.close();
                } catch (SQLException e) {
                }
                try {
                    if (statement != null)
                        statement.close();
                } catch (SQLException e) {
                }
            }
    	}


        /**
         * ���������� ����� �������� ���.  �� ���� ����� �� ������� ��� ������������ ��������
         * */
            public FKTrans2AXTransItemShortList getDataForProvsSalaryByActServices(int actCode , int tkcalckind , boolean isWithDelivery )  throws PersistenceException
            {
            	FKTrans2AXTransItemShortList result = new FKTrans2AXTransItemShortList();
        		FKTrans2AXTransItemShort anObject;
        		result.list = new Vector<>();

        		String selectStr;
        		PreparedStatement statement = null;
        		ResultSet  set = null;

        		selectStr =     " select  sum(payswork) as payswork, cehid from( "   +
        				        new String( tkcalckind == TKCalcKind.OLD ? " select sum(p2h.paysworkbonus)::numeric(15,2) as payswork  \n"
        				        : isWithDelivery == true ? " select (   sum(coalesce(p2h.paysworkbonus,0)) \n" +
	                              " 		 + sum(coalesce(p2h.paysworksurcharge,0))  \n" +
	                              " 	     + sum(coalesce(p2h.paysworkpremium,0))  \n" +
	                              " 	     + sum(coalesce(p2h.paysworkadditional,0)) )::numeric(15,2)  as payswork "
	                            :  " select (   sum(coalesce(p2h.paysworkbonuswithotdlv,0)) \n" +
									 " 		  + sum(coalesce(p2h.paysworksurchrgwthtdlv,0)) \n" +
									 "	      + sum(coalesce(p2h.paysworkpremiumwthtdlv,0))  \n" +
									 "	      + sum(coalesce(p2h.paysworkadditnlwthtdlv,0)) )::numeric(15,2) as payswork "
									 ) +
        		                              " ,  p2h.cehid   " +
        				" from \n"+
        				" enact2enplanwork as a2p \n"+
        				" inner join enplanwork as pw on a2p.plancode = pw.code \n"+
        				" inner join enplanworkitem as pwi on pw.code = pwi.planrefcode \n"+
        				" inner join enplanworkitem2humen as p2h on pwi.code = p2h.plaitemrefcode \n"+
        				" inner join tktechcard as tc on pwi.kartarefcode = tc.code \n"+
        				" inner join tkclassificationtype as tkcls on tc.classificationtypecode = tkcls.code \n"+
        				" where  a2p.actrefcode =  " + actCode +
        				" and pwi.countgen <> 0 \n"+
        				" and (tkcls.costworkscontractor is null or tkcls.costworkscontractor = 0) \n"+
        				" group by p2h.cehid \n"+
        				" , p2h.chargepercent \n"+
        				" , p2h.chargerefcode \n"+
        				" , a2p.actrefcode \n"+
        				" , p2h.bonus \n"+
        				" , p2h.tabnumber \n"+
        				" , p2h.fio \n"+
        				" , p2h.salary \n"+
        				" , p2h.timemonth \n"+
        				" , p2h.salaryhours \n"+
        				" , p2h.humenkindrefcode \n"+
        				" , p2h.balans \n"+
        				" ,substring( p2h.podrcod from 1 for 3 ) \n"+
        				" ) as s1 group by cehid "; /// zzzz

        		try {
        			statement = connection.prepareStatement(selectStr);
        			set = statement.executeQuery();
        			int c=0;
        			while (set.next()) {

        				if(set.getBigDecimal(1).doubleValue() > 0  ){

        					anObject = new FKTrans2AXTransItemShort();

            				anObject.amountCur = set.getBigDecimal(1);
            				if(anObject.amountCur != null) {
            					anObject.amountCur = anObject.amountCur.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
            				}
            				anObject.balCeh = set.getString(2);


            				result.list.add(anObject);
            				c++;
        				}

        			}

        			result.setTotalCount(c);
        			return result;
        		} catch (SQLException e) {
                    System.out.println(e.getMessage() + "\nstatement - " + selectStr);
                    return result;
                } finally {
                    try {
                        if (set != null)
                            set.close();
                    } catch (SQLException e) {
                    }
                    try {
                        if (statement != null)
                            statement.close();
                    } catch (SQLException e) {
                    }
                }
        	}


            /**
             * ���������� ����� ��� ���.  �� ���� ����� �� ������� ��� ������������ ��������
             * */
                public FKTrans2AXTransItemShortList getDataForProvsESVByActServicesRed(int actCode , int tkcalckind, boolean isWithDelivery )  throws PersistenceException
                {
                	FKTrans2AXTransItemShortList result = new FKTrans2AXTransItemShortList();
            		FKTrans2AXTransItemShort anObject;
            		result.list = new Vector<>();

            		String selectStr;
            		PreparedStatement statement = null;
            		ResultSet  set = null;

//            		selectStr =  new String (isWithDelivery==true ? " select sum(p2h.CHARGESUM)::numeric(15,2) as CHARGESUM  \n"
//            				                          : " select sum(p2h.CHARGESUMWITHOUTDELIV)::numeric(15,2) as CHARGESUMWITHOUTDELIV  \n") +
//            		             " ,  p2h.cehid   " +
//            		             " ,  overlay(p2h.balans placing '3' from 8 ) as balans " +
//            				" from \n"+
//            				" enact2enplanwork as a2p \n"+
//            				" inner join enplanwork as pw on a2p.plancode = pw.code \n"+
//            				" inner join enplanworkitem as pwi on pw.code = pwi.planrefcode \n"+
//            				" inner join enplanworkitem2humen as p2h on pwi.code = p2h.plaitemrefcode \n"+
//            				" inner join tktechcard as tc on pwi.kartarefcode = tc.code \n"+
//            				" inner join tkclassificationtype as tkcls on tc.classificationtypecode = tkcls.code \n"+
//            				" where  a2p.actrefcode =  " + actCode +
//            				" and pwi.countgen <> 0 \n"+
//            				" and (tkcls.costworkscontractor is null or tkcls.costworkscontractor = 0) \n"+
//            				" group by p2h.cehid ,  overlay(p2h.balans placing '3' from 8 ) \n";
            		selectStr =  new String (isWithDelivery==true ? " select sum(CHARGESUM)::numeric(15,2) as CHARGESUM  \n"
                    : " select sum(CHARGESUMWITHOUTDELIV)::numeric(15,2) as CHARGESUMWITHOUTDELIV  \n") +
            		"             ,cehid,overlay(balans placing '3' from 8 ) as balans \n" +
            		"                                 from ( \n" +
            		"                       Select \n" +
            		"                        tabnumber \n" +
            		"                      , fio \n" +
            		"                      , salary \n" +
            		"                      , timemonth \n" +
            		"                      , salaryhours \n" +
            		"                      , sum(timework) as timework \n" +
            		"                      , (sum(payswork))::numeric(15,2)  as payswork \n" +
            		"                      , sum(paysWorkBonusWithoutDelivery)::numeric(15,2) as paysWorkBonusWithoutDelivery /* �� ��������� ��� ������� � ��� ��������� */ \n" +
            		"                      , cast(sum(paysworkAdditional) as numeric(15,2)) as paysworkAdditional \n" +
            		"                      , case when replacecounterkindcode = 2 and isnopay = 1 then 0 else \n" +
            		"                          cast(sum(generalexpenses) as numeric(15,2)) end as generalexpenses \n" +
            		"                      , chargepercent \n" +
            		"                      , chargerefcode \n" +
            		"                      , balanskod as balans  \n" +
            		"                      , bonus \n" +
            		"                      , bonusdditional \n" +
            		"                      , humenkindrefcode \n" +
            		"                      , sum(CHARGESUM)::numeric(15,2) as  CHARGESUM \n" +
            		"                      , sum(CHARGESUMWITHOUTDELIV)::numeric(15,2) as CHARGESUMWITHOUTDELIV /*������ ������ �� ������������'������ �������� ��������� ����������� ���������*/ \n" +
            		"                      , coalesce((select * from getENconnectionKindByActCode(actrefcode) ),'') as isPriconnection \n" +
            		"                      , sum(timeDelivery) as timeDelivery \n" +
            		"                      , cehid \n" +
            		"                      from ( \n" +
            		"                          Select \n" +
            		"                            p2h.tabnumber \n" +
            		"                          , p2h.fio \n" +
            		"                          , cast(p2h.salary as numeric) as salary \n" +
            		"                          , cast(p2h.timemonth as numeric) as timemonth \n" +
            		"                          , cast(p2h.salaryhours as numeric) as salaryhours \n" +
            		"                          , cast(p2h.timework as numeric) as timework \n" +
            		"                          , (coalesce(p2h.paysworkbonus,0) + coalesce(p2h.paysworksurcharge,0) + coalesce(p2h.paysworkpremium,0) + coalesce(p2h.paysworkadditional,0) ) as payswork \n" +
            		"                          , (coalesce(p2h.paysworkbonuswithotdlv,0) + coalesce(p2h.paysworksurchrgwthtdlv,0) + coalesce(p2h.paysworkpremiumwthtdlv,0) + coalesce(p2h.paysworkadditnlwthtdlv,0)) as paysWorkBonusWithoutDelivery \n" +
            		"                          , p2h.generalexpenses \n" +
            		"                          , p2h.chargepercent \n" +
            		"                          , p2h.chargerefcode \n" +
            		"                          , p2h.balans  as balanskod \n" +
            		"                          , p2h.bonus \n" +
            		"                          , coalesce((coalesce(p2h.percentsurcharge,0) + coalesce(p2h.percentpremium,0)  + coalesce(p2h.percentadditional,0) ),0) as bonusdditional \n" +
            		"                          , tkcls.replacecounterkindcode \n" +
            		"                          , coalesce((p2h.paysworksurcharge + p2h.paysworkpremium + p2h.paysworkadditional),0)::numeric(15,8) as paysworkAdditional \n" +
            		"                          , so.isnopay , a2p.actrefcode , p2h.humenkindrefcode \n" +
            		"                          , p2h.timeDelivery \n" +
            		"                          , p2h.CHARGESUM \n" +
            		"                          , p2h.CHARGESUMWITHOUTDELIV \n" +
            		"                          , p2h.cehid \n" +
            		"                          , tc.classificationtypecode \n" +
            		"                          from \n" +
            		"                          enact2enplanwork as a2p \n" +
            		"                          inner join enplanwork as pw on a2p.plancode = pw.code          \n" +
            		"                          inner join enplanworkitem as pwi on pw.code = pwi.planrefcode \n" +
            		"                          inner join enplanworkitem2humen as p2h on pwi.code = p2h.plaitemrefcode \n" +
            		"                          inner join tktechcard as tc on pwi.kartarefcode = tc.code \n" +
            		"                          inner join tkclassificationtype as tkcls on tc.classificationtypecode = tkcls.code \n" +
            		"                          inner join enservicesobject as so on pw.elementrefcode = so.elementcode \n" +
            		"                          where  a2p.actrefcode = "+ actCode +" \n" +
            		"                           and pwi.countgen <> 0 \n" +
            		"                          and (tkcls.costworkscontractor is null or tkcls.costworkscontractor = 0) \n" +
            		"                      ) as q1 \n" +
            		"                      group by chargepercent \n" +
            		"                      , chargerefcode \n" +
            		"                      , actrefcode \n" +
            		"                      , tabnumber \n" +
            		"                      , fio \n" +
            		"                      , salary \n" +
            		"                      , timemonth \n" +
            		"                      , salaryhours \n" +
            		"                      , humenkindrefcode \n" +
            		"                      , replacecounterkindcode \n" +
            		"                      , isnopay \n" +
            		"                      , bonus \n" +
            		"                      , bonusdditional  \n" +
            		"                      , balanskod \n" +
            		"                      , cehid \n" +
            		"                      , classificationtypecode \n" +
            		"                      order by tabnumber \n" +
            		"                              ) as qq \n" +
            		"   group by cehid ,  overlay(balans placing '3' from 8 ) ";

            		try {
            			statement = connection.prepareStatement(selectStr);
            			set = statement.executeQuery();
            			int c=0;
            			while (set.next()) {

            				if(set.getBigDecimal(1).doubleValue() > 0  ){

            					anObject = new FKTrans2AXTransItemShort();

                				anObject.amountCur = set.getBigDecimal(1);
                				if(anObject.amountCur != null) {
                					anObject.amountCur = anObject.amountCur.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
                				}
                				anObject.balCeh = set.getString(2);

                				anObject.balans = set.getString(3);

                				result.list.add(anObject);
                				c++;
            				}

            			}

            			result.setTotalCount(c);
            			return result;
            		} catch (SQLException e) {
                        System.out.println(e.getMessage() + "\nstatement - " + selectStr);
                        return result;
                    } finally {
                        try {
                            if (set != null)
                                set.close();
                        } catch (SQLException e) {
                        }
                        try {
                            if (statement != null)
                                statement.close();
                        } catch (SQLException e) {
                        }
                    }
            	}
                
/**
 * ���������� ����� ��� ���.  �� ���� ����� �� �������  -- ������  �������� �� ������������ � ��������������� �/� 
 * @param isRed - ��� �������� ������� ����� ����������� �� ����������� ���������� 
 * 
 * */
    public FKTrans2AXTransItemShortList getDataForProvsESVByActServicesSupplierContract
    (int actCode , int tkcalckind, boolean isWithDelivery, boolean isRed )  throws PersistenceException
    {
                    	FKTrans2AXTransItemShortList result = new FKTrans2AXTransItemShortList();
                		FKTrans2AXTransItemShort anObject;
                		result.list = new Vector<>();

                		String selectStr;
                		PreparedStatement statement = null;
                		ResultSet  set = null;

                		selectStr =  new String (isWithDelivery==true ? 
                				" select sum(CHARGESUM)::numeric(15,2) as CHARGESUM  \n"
                        :       " select sum(CHARGESUMWITHOUTDELIV)::numeric(15,2) as CHARGESUMWITHOUTDELIV  \n") +
                				"  , cehid  \n" +
                				new String(isRed ? "  , overlay(balans placing '3' from 8 ) as balans " : "" ) +
                				"  from ( \n" +
                				"  Select \n" +
                				"    tabnumber \n" +
                				"  , fio \n" +
                				"  , salary \n" +
                				"  , timemonth \n" +
                				"  , salaryhours \n" +
                				"  , sum(timework) as timework \n" +
                				"  , (sum(payswork))::numeric(15,2)  as payswork \n" +
                				"  , sum(paysWorkBonusWithoutDelivery)::numeric(15,2) as paysWorkBonusWithoutDelivery /* �� ��������� ��� ������� � ��� ��������� */ \n" +
                				"  , cast(sum(paysworkAdditional) as numeric(15,2)) as paysworkAdditional \n" +
                				"  , case when replacecounterkindcode = 2 and isnopay = 1 then 0 else \n" +
                				"      cast(sum(generalexpenses) as numeric(15,2)) end as generalexpenses \n" +
                				"  , chargepercent \n" +
                				"  , chargerefcode \n" +
                				"  , balans \n" +
                				"  , bonus                          \n" +
                				"  , bonusdditional \n" +
                				"  , humenkindrefcode \n" +
                				"  , sum(CHARGESUM)::numeric(15,2) as  CHARGESUM \n" +
                				"  , sum(CHARGESUMWITHOUTDELIV)::numeric(15,2) as CHARGESUMWITHOUTDELIV/*������ ������ �� ������������'������ �������� ��������� ����������� ���������*/ \n" +
                				"  , coalesce((select * from getENconnectionKindByActCode(actrefcode) ),'') as isPriconnection \n" +
                				"  , sum(timeDelivery) as timeDelivery \n" +
                				"  , cehid \n" +
                				"  from ( \n" +
                				"      Select \n" +
                				"        p2h.tabnumber \n" +
                				"      , p2h.fio \n" +
                				"      , cast(p2h.salary as numeric) as salary \n" +
                				"      , cast(p2h.timemonth as numeric) as timemonth \n" +
                				"      , cast(p2h.salaryhours as numeric) as salaryhours \n" +
                				"      , cast(p2h.timework as numeric) as timework \n" +
                				"      , (coalesce(p2h.paysworkbonus,0) + coalesce(p2h.paysworksurcharge,0) + coalesce(p2h.paysworkpremium,0) + coalesce(p2h.paysworkadditional,0) ) as payswork \n" +
                				"      , (coalesce(p2h.paysworkbonuswithotdlv,0) + coalesce(p2h.paysworksurchrgwthtdlv,0) + coalesce(p2h.paysworkpremiumwthtdlv,0) + coalesce(p2h.paysworkadditnlwthtdlv,0)) as paysWorkBonusWithoutDelivery \n" +
                				"      , p2h.generalexpenses \n" +
                				"      , p2h.chargepercent \n" +
                				"      , p2h.chargerefcode      \n" +
                				"      , p2h.balans||'; '||substring( p2h.podrcod from 1 for 3 )  as balans \n" +
                				"      , p2h.bonus \n" +
                				"      , coalesce((coalesce(p2h.percentsurcharge,0) + coalesce(p2h.percentpremium,0)  + coalesce(p2h.percentadditional,0) ),0) as bonusdditional \n" +
                				"      , tkcls.replacecounterkindcode \n" +
                				"      , coalesce((p2h.paysworksurcharge + p2h.paysworkpremium + p2h.paysworkadditional),0)::numeric(15,8) as paysworkAdditional \n" +
                				"      , so.isnopay , a2p.actrefcode , p2h.humenkindrefcode \n" +
                				"      , p2h.timeDelivery \n" +
                				"      , p2h.CHARGESUM \n" +
                				"      , p2h.CHARGESUMWITHOUTDELIV \n" +
                				"      , p2h.cehid \n" +
                				"  from \n" +
                				"      enservicesobject as so, enservices2plan as s2p, \n" +
                				"      enact2enplanwork as a2p \n" +
                				"      inner join enplanwork as pw on a2p.plancode = pw.code \n" +
                				"      inner join enplanworkitem as pwi on pw.code = pwi.planrefcode \n" +
                				"      inner join enplanworkitem2humen as p2h on pwi.code = p2h.plaitemrefcode \n" +
                				"      inner join tktechcard as tc on pwi.kartarefcode = tc.code \n" +
                				"      inner join tkclassificationtype as tkcls on tc.classificationtypecode = tkcls.code \n" +
                				"  where \n" +
                				"    a2p.actrefcode = "+ actCode +" \n" +
                				"    and s2p.servicesobjectrefcode = so.code \n" +
                				"    and s2p.planrefcode = a2p.plancode \n" +
                				"    and pwi.countgen <> 0 \n" +
                				"    and (tkcls.costworkscontractor is null or tkcls.costworkscontractor = 0) \n" +
                				"  ) as q1 \n" +
                				"  group by chargepercent \n" +
                				"  , chargerefcode \n" +
                				"  , actrefcode \n" +
                				"  , tabnumber \n" +
                				"  , fio \n" +
                				"  , salary \n" +
                				"  , timemonth \n" +
                				"  , salaryhours \n" +
                				"  , humenkindrefcode \n" +
                				"  , replacecounterkindcode \n" +
                				"  , isnopay \n" +
                				"  , bonus \n" +
                				"  , bonusdditional /*��� �� �������*/ \n" +
                				"  , balans \n" +
                				"  , cehid \n" +
                				"  order by tabnumber \n" +
                				"  )as qq \n" +
                				"  group by cehid  \n" +
                				new String(isRed ? "  ,  overlay(balans placing '3' from 8 ) \n" : "" );

                		try {
                			statement = connection.prepareStatement(selectStr);
                			set = statement.executeQuery();
                			int c=0;
                			while (set.next()) {

                				if(set.getBigDecimal(1).doubleValue() > 0  ){

                					anObject = new FKTrans2AXTransItemShort();

                    				anObject.amountCur = set.getBigDecimal(1);
                    				if(anObject.amountCur != null) {
                    					anObject.amountCur = anObject.amountCur.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
                    				}
                    				anObject.balCeh = set.getString(2);

                    				anObject.balans = isRed ? set.getString(3) : new String("") ;

                    				result.list.add(anObject);
                    				c++;
                				}

                			}

                			result.setTotalCount(c);
                			return result;
                		} catch (SQLException e) {
                            System.out.println(e.getMessage() + "\nstatement - " + selectStr);
                            return result;
                        } finally {
                            try {
                                if (set != null)
                                    set.close();
                            } catch (SQLException e) {
                            }
                            try {
                                if (statement != null)
                                    statement.close();
                            } catch (SQLException e) {
                            }
                        }
                	}                

                /**
                 * ���������� ����� ����������������� ������� ���.  �� ���� ����� �� ������� ��� ������������ ��������
                 * */
                    public FKTrans2AXTransItemShortList getDataForProvsTotalExpencByActServices(int actCode , int tkcalckind
                    		, boolean isWithDelivery)  throws PersistenceException
                    {
                    	FKTrans2AXTransItemShortList result = new FKTrans2AXTransItemShortList();
                		FKTrans2AXTransItemShort anObject;
                		result.list = new Vector<>();

                		String selectStr;
                		PreparedStatement statement = null;
                		ResultSet  set = null;

            			/* 14.01.2020 SUPP-88628 ����������� ������� ����� �������������������� ������ ��� ������ ������� ����� � ������
            			 * ���������� � ���������

            			*/

            			String selectSum = null;
            			switch(tkcalckind) {
            			case TKCalcKind.OLD:
            				selectSum = "paysworkbonus";
            				break;
            			case TKCalcKind.NEW:
            				selectSum = (isWithDelivery) ? "paysworkbonusWithAdditional" : "paysworkbonusWithAdditionalWithoutDelivery";
            				break;
            			case TKCalcKind.NEW2:
            				selectSum = "paysworkbonusWithAdditional";
            				break;
            			default:
            				throw new SystemException(String.format("�������� ��� ���������� ����������� - %d", tkcalckind));
            			}

	selectStr = /*new String( tkcalckind == TKCalcKind.OLD ? " select ( sum(p2h.paysworkbonus)::numeric(15,2) * calc_info.productionexpenssprcnt / 100 )::numeric(15,2) as payswork  \n"
	        : isWithDelivery == true ? " select ((   sum(coalesce(p2h.paysworkbonus,0)) \n" +
              " 		 + sum(coalesce(p2h.paysworksurcharge,0))  \n" +
              " 	     + sum(coalesce(p2h.paysworkpremium,0))  \n" +
              " 	     + sum(coalesce(p2h.paysworkadditional,0)) )::numeric(15,2) * calc_info.productionexpenssprcnt / 100 )::numeric(15,2) "
                                     : " select ((   sum(coalesce(p2h.paysworkbonuswithotdlv,0)) \n" +
				 " 		  + sum(coalesce(p2h.paysworksurchrgwthtdlv,0)) \n" +
				 "	      + sum(coalesce(p2h.paysworkpremiumwthtdlv,0))  \n" +
				 "	      + sum(coalesce(p2h.paysworkadditnlwthtdlv,0)) )::numeric(15,2) * calc_info.productionexpenssprcnt / 100 )::numeric(15,2) "
				 ) +*/
//			" select ( sum(paysworkbonusExpence)  )::numeric(15,2)  from ( \n" +
//			new String( tkcalckind == TKCalcKind.OLD ? " select sum(p2h.paysworkbonus)::numeric(15,2) * productionexpenssprcnt / 100  as paysworkbonusExpence \n"
//	          : isWithDelivery == true ? " select sum(paysworkbonusWithAdditional)::numeric(15,2) * productionexpenssprcnt / 100 as paysworkbonusExpence \n"
//                                       : " select sum(paysworkbonusWithAdditionalWithoutDelivery)::numeric(15,2)  * productionexpenssprcnt / 100 as paysworkbonusExpence \n"





			// SUPP-83425 ������� ����������������� ������� �� �� ������ �� �������
" select ( sum(paysworkbonusExpence)  )::numeric(15,2)  from ( \n" +
			" select (sum(" + selectSum + ")::numeric(15,2)  * productionexpenssprcnt / 100)::numeric(15,2) as paysworkbonusExpence \n"	+
				 " , productionexpenssprcnt \n" +
			" from ( \n"+
			" select sum(p2h.paysworkbonus)::numeric(15,2) as paysworkbonus \n"+
			" , sum(coalesce(p2h.paysworkbonus,0) + coalesce(p2h.paysworksurcharge,0) + coalesce(p2h.paysworkpremium,0) + coalesce(p2h.paysworkadditional,0))::numeric(15,2) as paysworkbonusWithAdditional \n"+
			" , sum(coalesce(p2h.paysworkbonuswithotdlv,0) + coalesce(p2h.paysworksurchrgwthtdlv,0) + coalesce(p2h.paysworkpremiumwthtdlv,0) + coalesce(p2h.paysworkadditnlwthtdlv,0))::numeric(15,2) as paysworkbonusWithAdditionalWithoutDelivery \n"+
			" , tc.classificationtypecode \n"+
			" , calc_info.productionexpenssprcnt \n"+
			" , p2h.tabnumber \n"+
			" from \n"+
			" enact2enplanwork as a2p \n"+
			" inner join enplanwork as pw on a2p.plancode = pw.code \n"+
			" inner join enplanworkitem as pwi on pw.code = pwi.planrefcode \n"+
			" inner join enplanworkitem2humen as p2h on pwi.code = p2h.plaitemrefcode \n"+
			" inner join tktechcard as tc on pwi.kartarefcode = tc.code \n"+
			" inner join tkclassificationtype as tkcls on tc.classificationtypecode = tkcls.code \n"+
			" left join ( \n"+
			" 		select acpw1.actrefcode, pwcl1.productionexpenssprcnt, pwcl1.calckindrefcode from enact2enplanwork as acpw1 \n"+
			" 		    inner join enplancorrecthistory as his_f1 on acpw1.plancode = his_f1.plannewrefcode \n"+
			" 		    inner join enplancorrecthistory as his_p1 on his_f1.planoldrefcode = his_p1.plannewrefcode \n"+
			" 		    inner join enplancorrecthistory as his_m1 on his_p1.planoldrefcode = his_m1.plannewrefcode \n"+
			" 		    inner join enplanwork2classfctntp as pwcl1 on his_m1.planoldrefcode = pwcl1.planrefcode \n"+
			"       where his_f1.reasoncode = 5 and his_p1.reasoncode = 4 and his_m1.reasoncode = 13 and acpw1.actrefcode = " + actCode +
			" 		union all \n"+
			" 		select acpw1.actrefcode, co1.productionexpenssprcnt, co1.calckindrefcode from enact2enplanwork as acpw1 \n"+
			" 		    inner join enplancorrecthistory as his_f1 on acpw1.plancode = his_f1.plannewrefcode \n"+
			" 		    inner join enplancorrecthistory as his_p1 on his_f1.planoldrefcode = his_p1.plannewrefcode \n"+
			" 		    inner join enservicescost as sco1 on his_p1.planoldrefcode = sco1.planrefcode \n"+
			" 		    inner join tkcalccost as co1 on sco1.tkcalccostrefcode = co1.code \n"+
			" 		    where his_f1.reasoncode = 5 and his_p1.reasoncode = 4 and acpw1.actrefcode = " + actCode +
			" 		    limit 1) as calc_info on calc_info.actrefcode = a2p.actrefcode \n"+
			" where  a2p.actrefcode =  " + actCode +
			" and pwi.countgen <> 0 \n"+
			" and (tkcls.costworkscontractor is null or tkcls.costworkscontractor = 0) \n"+
			" group by tc.classificationtypecode, calc_info.productionexpenssprcnt, p2h.tabnumber   \n" +
			" ) as q1 \n"+
	        " group by productionexpenssprcnt , classificationtypecode  \n" +
	        " ) as q2  "
	        + " \n";
                		try {
                			statement = connection.prepareStatement(selectStr);
                			set = statement.executeQuery();
                			int c=0;
                			while (set.next()) {

                				if(set.getBigDecimal(1).doubleValue() > 0  ) {

                					anObject = new FKTrans2AXTransItemShort();

                    				anObject.amountCur = set.getBigDecimal(1);
                    				if(anObject.amountCur != null) {
                    					anObject.amountCur = anObject.amountCur.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
                    				}

                    				result.list.add(anObject);
                    				c++;
                				}

                			}

                			result.setTotalCount(c);
                			return result;
                		} catch (SQLException e) {
                			throw new SystemException(e);
                        } finally {
                            try {
                                if (set != null)
                                    set.close();
                            } catch (SQLException e) {
                            }
                            try {
                                if (statement != null)
                                    statement.close();
                            } catch (SQLException e) {
                            }
                        }
                	}


                    /**
                     * ���������� ����� ����������������� ������� ���.  �� ���� ��� ������������ ��������
                     * */
         public FKTrans2AXTransItemShortList getDataForProvsTotalExpencByAct(int actCode  )  throws PersistenceException
                        {
                        	FKTrans2AXTransItemShortList result = new FKTrans2AXTransItemShortList();
                    		FKTrans2AXTransItemShort anObject;
                    		result.list = new Vector<>();

                    		String selectStr;
                    		PreparedStatement statement = null;
                    		ResultSet  set = null;

    	selectStr = " select ( sum(a2h.paysworkbonus)::numeric(15,2) " +
    			    "                 * " +
    			    "                 (select * from get_productionexpencespercent(a2h.actrefcode::integer)) / 100 )::numeric(15,2) as payswork " +
    			    "  from enact2humen a2h where a2h.paysworkbonus::numeric(15,2) > 0  " +
    			    "  and a2h.actrefcode =  " + actCode +
    			    "  group by a2h.actrefcode ";

                    		try {
                    			statement = connection.prepareStatement(selectStr);
                    			set = statement.executeQuery();
                    			int c=0;
                    			while (set.next()) {

                    				if(set.getBigDecimal(1).doubleValue() > 0  ) {

                    					anObject = new FKTrans2AXTransItemShort();

                        				anObject.amountCur = set.getBigDecimal(1);
                        				if(anObject.amountCur != null) {
                        					anObject.amountCur = anObject.amountCur.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
                        				}

                        				result.list.add(anObject);
                        				c++;
                    				}

                    			}

                    			result.setTotalCount(c);
                    			return result;
                    		} catch (SQLException e) {
                                System.out.println(e.getMessage() + "\nstatement - " + selectStr);
                                return result;
                            } finally {
                                try {
                                    if (set != null)
                                        set.close();
                                } catch (SQLException e) {
                                }
                                try {
                                    if (statement != null)
                                        statement.close();
                                } catch (SQLException e) {
                                }
                            }
                    	}

                /**
                 * ���������� ����� ��� ���.  �� ���� ����� �� ������� ��� ������������ ��������
                 * */
                    public FKTrans2AXTransItemShortList getDataForProvsESVByActServices(int actCode , int tkcalckind  , boolean isWithDelivery  )  throws PersistenceException
                    {
                    	FKTrans2AXTransItemShortList result = new FKTrans2AXTransItemShortList();
                		FKTrans2AXTransItemShort anObject;
                		result.list = new Vector<>();

                		String selectStr;
                		PreparedStatement statement = null;
                		ResultSet  set = null;

                		/*selectStr = new String( isWithDelivery == true ? " select sum(p2h.CHARGESUM)::numeric(15,2) as CHARGESUM  \n"
                				                                       : " select sum(p2h.CHARGESUMWITHOUTDELIV)::numeric(15,2) as CHARGESUMWITHOUTDELIV  \n" ) +
                		             " ,  p2h.cehid   " +
                				" from \n"+
                				" enact2enplanwork as a2p \n"+
                				" inner join enplanwork as pw on a2p.plancode = pw.code \n"+
                				" inner join enplanworkitem as pwi on pw.code = pwi.planrefcode \n"+
                				" inner join enplanworkitem2humen as p2h on pwi.code = p2h.plaitemrefcode \n"+
                				" inner join tktechcard as tc on pwi.kartarefcode = tc.code \n"+
                				" inner join tkclassificationtype as tkcls on tc.classificationtypecode = tkcls.code \n"+
                				" where  a2p.actrefcode =  " + actCode +
                				" and pwi.countgen <> 0 \n"+
                				" and (tkcls.costworkscontractor is null or tkcls.costworkscontractor = 0) \n"+
                				" group by p2h.cehid \n";*/


                		selectStr =  new String (isWithDelivery==true ? " select sum(CHARGESUM)::numeric(15,2) as CHARGESUM  \n"
                                : " select sum(CHARGESUMWITHOUTDELIV)::numeric(15,2) as CHARGESUMWITHOUTDELIV  \n") +
                        		"             ,cehid  \n" +
                        		"                                 from ( \n" +
                        		"                       Select \n" +
                        		"                        tabnumber \n" +
                        		"                      , fio \n" +
                        		"                      , salary \n" +
                        		"                      , timemonth \n" +
                        		"                      , salaryhours \n" +
                        		"                      , sum(timework) as timework \n" +
                        		"                      , (sum(payswork))::numeric(15,2)  as payswork \n" +
                        		"                      , sum(paysWorkBonusWithoutDelivery)::numeric(15,2) as paysWorkBonusWithoutDelivery /* �� ��������� ��� ������� � ��� ��������� */ \n" +
                        		"                      , cast(sum(paysworkAdditional) as numeric(15,2)) as paysworkAdditional \n" +
                        		"                      , case when replacecounterkindcode = 2 and isnopay = 1 then 0 else \n" +
                        		"                          cast(sum(generalexpenses) as numeric(15,2)) end as generalexpenses \n" +
                        		"                      , chargepercent \n" +
                        		"                      , chargerefcode \n" +
                        		"                      , balanskod as balans  \n" +
                        		"                      , bonus \n" +
                        		"                      , bonusdditional \n" +
                        		"                      , humenkindrefcode \n" +
                        		"                      , sum(CHARGESUM)::numeric(15,2) as  CHARGESUM \n" +
                        		"                      , sum(CHARGESUMWITHOUTDELIV)::numeric(15,2) as CHARGESUMWITHOUTDELIV /*������ ������ �� ������������'������ �������� ��������� ����������� ���������*/ \n" +
                        		"                      , coalesce((select * from getENconnectionKindByActCode(actrefcode) ),'') as isPriconnection \n" +
                        		"                      , sum(timeDelivery) as timeDelivery \n" +
                        		"                      , cehid \n" +
                        		"                      from ( \n" +
                        		"                          Select \n" +
                        		"                            p2h.tabnumber \n" +
                        		"                          , p2h.fio \n" +
                        		"                          , cast(p2h.salary as numeric) as salary \n" +
                        		"                          , cast(p2h.timemonth as numeric) as timemonth \n" +
                        		"                          , cast(p2h.salaryhours as numeric) as salaryhours \n" +
                        		"                          , cast(p2h.timework as numeric) as timework \n" +
                        		"                          , (coalesce(p2h.paysworkbonus,0) + coalesce(p2h.paysworksurcharge,0) + coalesce(p2h.paysworkpremium,0) + coalesce(p2h.paysworkadditional,0) ) as payswork \n" +
                        		"                          , (coalesce(p2h.paysworkbonuswithotdlv,0) + coalesce(p2h.paysworksurchrgwthtdlv,0) + coalesce(p2h.paysworkpremiumwthtdlv,0) + coalesce(p2h.paysworkadditnlwthtdlv,0)) as paysWorkBonusWithoutDelivery \n" +
                        		"                          , p2h.generalexpenses \n" +
                        		"                          , p2h.chargepercent \n" +
                        		"                          , p2h.chargerefcode \n" +
                        		"                          , p2h.balans  as balanskod \n" +
                        		"                          , p2h.bonus \n" +
                        		"                          , coalesce((coalesce(p2h.percentsurcharge,0) + coalesce(p2h.percentpremium,0)  + coalesce(p2h.percentadditional,0) ),0) as bonusdditional \n" +
                        		"                          , tkcls.replacecounterkindcode \n" +
                        		"                          , coalesce((p2h.paysworksurcharge + p2h.paysworkpremium + p2h.paysworkadditional),0)::numeric(15,8) as paysworkAdditional \n" +
                        		"                          , so.isnopay , a2p.actrefcode , p2h.humenkindrefcode \n" +
                        		"                          , p2h.timeDelivery \n" +
                        		"                          , p2h.CHARGESUM \n" +
                        		"                          , p2h.CHARGESUMWITHOUTDELIV \n" +
                        		"                          , p2h.cehid \n" +
                        		"                          , tc.classificationtypecode \n" +
                        		"                          from \n" +
                        		"                          enact2enplanwork as a2p \n" +
                        		"                          inner join enplanwork as pw on a2p.plancode = pw.code          \n" +
                        		"                          inner join enplanworkitem as pwi on pw.code = pwi.planrefcode \n" +
                        		"                          inner join enplanworkitem2humen as p2h on pwi.code = p2h.plaitemrefcode \n" +
                        		"                          inner join tktechcard as tc on pwi.kartarefcode = tc.code \n" +
                        		"                          inner join tkclassificationtype as tkcls on tc.classificationtypecode = tkcls.code \n" +
                        		"                          inner join enservicesobject as so on pw.elementrefcode = so.elementcode \n" +
                        		"                          where  a2p.actrefcode = "+ actCode +" \n" +
                        		"                           and pwi.countgen <> 0 \n" +
                        		"                          and (tkcls.costworkscontractor is null or tkcls.costworkscontractor = 0) \n" +
                        		"                      ) as q1 \n" +
                        		"                      group by chargepercent \n" +
                        		"                      , chargerefcode \n" +
                        		"                      , actrefcode \n" +
                        		"                      , tabnumber \n" +
                        		"                      , fio \n" +
                        		"                      , salary \n" +
                        		"                      , timemonth \n" +
                        		"                      , salaryhours \n" +
                        		"                      , humenkindrefcode \n" +
                        		"                      , replacecounterkindcode \n" +
                        		"                      , isnopay \n" +
                        		"                      , bonus \n" +
                        		"                      , bonusdditional  \n" +
                        		"                      , balanskod \n" +
                        		"                      , cehid \n" +
                        		"                      , classificationtypecode \n" +
                        		"                      order by tabnumber \n" +
                        		"                              ) as qq \n" +
                        		"   group by cehid  ";
                		try {
                			statement = connection.prepareStatement(selectStr);
                			set = statement.executeQuery();
                			int c=0;
                			while (set.next()) {

                				if (set.getBigDecimal(1).doubleValue() > 0  ){

                					anObject = new FKTrans2AXTransItemShort();

                    				anObject.amountCur = set.getBigDecimal(1);
                    				if(anObject.amountCur != null) {
                    					anObject.amountCur = anObject.amountCur.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
                    				}
                    				anObject.balCeh = set.getString(2);

                    				result.list.add(anObject);
                    				c++;

                				}

                			}

                			result.setTotalCount(c);
                			return result;
                		} catch (SQLException e) {
                            System.out.println(e.getMessage() + "\nstatement - " + selectStr);
                            return result;
                        } finally {
                            try {
                                if (set != null)
                                    set.close();
                            } catch (SQLException e) {
                            }
                            try {
                                if (statement != null)
                                    statement.close();
                            } catch (SQLException e) {
                            }
                        }
                	}


                    /**
                     * ���������� ����� ��� ���.  �� ����  ��� ������������ ��������
                     * */
                        public FKTrans2AXTransItemShortList getDataForProvsESVByAct(int actCode   )  throws PersistenceException
                        {
                        	FKTrans2AXTransItemShortList result = new FKTrans2AXTransItemShortList();
                    		FKTrans2AXTransItemShort anObject;
                    		result.list = new Vector<>();

                    		String selectStr;
                    		PreparedStatement statement = null;
                    		ResultSet  set = null;

                    		selectStr = " select sum(CHARGESUM)::numeric(15,2) as CHARGESUM , a2h.cehid  " +
                			        " from enact2humen a2h where a2h.paysworkbonus::numeric(15,2) > 0  " +
                			        " and a2h.actrefcode =  " + actCode    +
                			        " group by a2h.cehid  "  ;

                    		try {
                    			statement = connection.prepareStatement(selectStr);
                    			set = statement.executeQuery();
                    			int c=0;
                    			while (set.next()) {

                    				if (set.getBigDecimal(1).doubleValue() > 0  ){

                    					anObject = new FKTrans2AXTransItemShort();

                        				anObject.amountCur = set.getBigDecimal(1);
                        				if(anObject.amountCur != null) {
                        					anObject.amountCur = anObject.amountCur.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
                        				}
                        				anObject.balCeh = set.getString(2);

                        				result.list.add(anObject);
                        				c++;

                    				}

                    			}

                    			result.setTotalCount(c);
                    			return result;
                    		} catch (SQLException e) {
                                System.out.println(e.getMessage() + "\nstatement - " + selectStr);
                                return result;
                            } finally {
                                try {
                                    if (set != null)
                                        set.close();
                                } catch (SQLException e) {
                                }
                                try {
                                    if (statement != null)
                                        statement.close();
                                } catch (SQLException e) {
                                }
                            }
                    	}

                    /**
                     * ���������� ����� ��� ���.  �� ����  ��� ������������ ��������
                     * */
                        public FKTrans2AXTransItemShortList getDataForProvsESVByActRed(int actCode)  throws PersistenceException
                        {
                        	FKTrans2AXTransItemShortList result = new FKTrans2AXTransItemShortList();
                    		FKTrans2AXTransItemShort anObject;
                    		result.list = new Vector<>();

                    		String selectStr;
                    		PreparedStatement statement = null;
                    		ResultSet  set = null;

                    		selectStr = " select sum(CHARGESUM)::numeric(15,2) as CHARGESUM ,a2h.cehid, overlay(a2h.balans placing '3' from 8 ) as balans " +
                    			        " from enact2humen a2h where a2h.paysworkbonus::numeric(15,2) > 0  " +
                    			        " and a2h.actrefcode =  " + actCode    +
                    			        " group by a2h.cehid ,  overlay(a2h.balans placing '3' from 8 ) "  ;
                    		try {
                    			statement = connection.prepareStatement(selectStr);
                    			set = statement.executeQuery();

                    			int c=0;
                    			while (set.next()) {

                    				if(set.getBigDecimal(1).doubleValue() > 0  ){

                    					anObject = new FKTrans2AXTransItemShort();

                        				anObject.amountCur = set.getBigDecimal(1);
                        				if(anObject.amountCur != null) {
                        					anObject.amountCur = anObject.amountCur.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
                        				}
                        				anObject.balCeh = set.getString(2);

                        				anObject.balans = set.getString(3);

                        				result.list.add(anObject);
                        				c++;
                    				}

                    			}

                    			result.setTotalCount(c);
                    			return result;
                    		} catch (SQLException e) {
                                System.out.println(e.getMessage() + "\nstatement - " + selectStr);
                                return result;
                            } finally {
                                try {
                                    if (set != null)
                                        set.close();
                                } catch (SQLException e) {
                                }
                                try {
                                    if (statement != null)
                                        statement.close();
                                } catch (SQLException e) {
                                }
                            }
                    	}


    public FKTrans2AXTransItemShortList getDataForProvsSalaryByAct(int actCode) throws PersistenceException {
    	return getDataForProvsSalaryByAct(actCode, false);
    }
    
/**
 * ���������� ����� �������� ���.  �� ���� ��� ������������ ��������
 * */
    public FKTrans2AXTransItemShortList getDataForProvsSalaryByAct(int actCode, boolean groupByPlans)  throws PersistenceException {
    	FKTrans2AXTransItemShortList result = new FKTrans2AXTransItemShortList();
		FKTrans2AXTransItemShort anObject;
		result.list = new Vector<>();

		String selectStr;
		PreparedStatement statement = null;
		ResultSet  set = null;

		selectStr = " select sum(a2h.paysworkbonus)::numeric(15,2) , a2h.cehid  from enact2humen a2h" +
					" where  a2h.paysworkbonus > 0 and a2h.actrefcode =  " + actCode + " group by a2h.cehid " ;
		
		if(groupByPlans) {
			 selectStr =  "SELECT round(cast(sum(payswork) AS decimal), 2) \n " +
					 ", cehid FROM \n " +
					 "( \n " +
					 "SELECT pw.code \n " +
					 ", fw.tabnumber \n " +
					 ", fw.pricegen as oklad \n " +
					 ", p2h.timeworkfact as wortime \n " +
					 ", fw.chargepercent \n " +
					 ", fw.chargerefcode \n " +
					 ", round(cast(round(cast(fw.pricegen / achu.timemonth AS decimal), 8) * 1.5 * p2h.timeworkfact AS decimal), 2) AS payswork \n " +
					 ", round(cast((round(cast(fw.pricegen / achu.timemonth AS decimal), 8) * 1.5 * p2h.timeworkfact) * (fw.chargepercent / 100) AS decimal), 2) AS charges \n " +
					 ", achu.cehid \n " +
					 "FROM finworker AS fw  \n " +
					 "INNER JOIN enhumenitem AS hu ON fw.code = hu.finworkercode \n " +
					 "INNER JOIN enplanwork AS pw ON hu.planrefcode = pw.code \n " +
					 "INNER JOIN enact2enplanwork AS acpw ON pw.code = acpw.plancode \n " +
					 "INNER JOIN enplan2humen AS p2h ON (hu.planrefcode = p2h.planrefcode and p2h.tabnumber = fw.tabnumber) \n " +
					 "INNER JOIN enact2humen AS achu ON (achu.actrefcode = acpw.actrefcode AND achu.tabnumber = fw.tabnumber) \n " +
					 "WHERE acpw.actrefcode = " + actCode + " \n " +
					 "AND hu.countfact <> 0 \n " +
					 "GROUP BY pw.code \n " +
					 ", fw.tabnumber \n " +
					 ", fw.pricegen \n " +
					 ", fw.chargepercent \n " +
					 ", fw.chargerefcode \n " +
					 ", achu.cehid \n " +
					 ", achu.balans \n" +
					 ", achu.timemonth \n " +
					 ", p2h.timeworkfact) AS payswork \n " +
					 "GROUP BY cehid \n ";
		}

		try {
			statement = connection.prepareStatement(selectStr);
			set = statement.executeQuery();
			int i;
			for (i = 0; set.next(); i++) {
				anObject = new FKTrans2AXTransItemShort();

				anObject.amountCur = set.getBigDecimal(1);
				if(anObject.amountCur != null) {
					anObject.amountCur = anObject.amountCur.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}
				anObject.balCeh = set.getString(2);

				result.list.add(anObject);
			}

			result.setTotalCount(i);
			return result;
		} catch (SQLException e) {
            System.out.println(e.getMessage() + "\nstatement - " + selectStr);
            return result;
        } finally {
            try {
                if (set != null)
                    set.close();
            } catch (SQLException e) {
            }
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
            }
        }
	}

    /**
     * ���������� ����� �������� ���.  �� �� ��� ������������ ��������
     * */
        public FKTrans2AXTransItemShortList getDataForProvsSalaryByOZ(int ozCode , int iszku , int scUsageInputItemKind )  throws PersistenceException
        {
        	FKTrans2AXTransItemShortList result = new FKTrans2AXTransItemShortList();
    		FKTrans2AXTransItemShort anObject;
    		result.list = new Vector<>();

    		String selectStr = "";
    		PreparedStatement statement = null;
    		ResultSet  set = null;



    		if(iszku==0 && scUsageInputItemKind != 4 && scUsageInputItemKind != 5 ){
    			selectStr = " select sum(a2h.paysworkbonus)::numeric(15,2) , a2h.cehid  from enact2humen a2h "+
    				    " where a2h.actrefcode in (select oz2act.enactrefcode from scusageinputitemoz oz , scusageinputitemoz2nct oz2act "+
    				    " 	where oz.code= "+ ozCode +
    				    " 	and oz.code = oz2act.usageinputitemozrefcod) "+
    				    " 		group by a2h.cehid ";
    		}
    		if(iszku==1 && scUsageInputItemKind != 4 && scUsageInputItemKind != 5 ){
    			/*��� ���������� ���� � ����  '201109/ActZKU/NormalAct/Act21';
    		 	*  ��� ������� �������� � ������ � ������ � ����� 112 -  �� - ������ �������� � ������� ��� */
    			selectStr =  " select coalesce(sum(payswork),0)::numeric(15,2) as payswork , cehid from (  " +
        				" select a2h.payworkcou as payswork, a2h.cehid  " +
        				" from enact2humen a2h " +
        				" where a2h.actrefcode in (select oz2act.enactrefcode from scusageinputitemoz oz , scusageinputitemoz2nct oz2act " +
        				" where oz.code=" + ozCode +
        				 	" and oz.code = oz2act.usageinputitemozrefcod" +
        				 	" ) " +
        				 	//SUPP-106909" and a2h.payworkcou>0  " +
        				 	" and a2h.humenkindrefcode = 1 " +  // ������������ ��������� � ���������
        				 " union all " +
        				 " select a2h.paysworkbonus as payswork, a2h.cehid 	" +
        				 " from enact2humen a2h , enact2enplanwork ap , enplanwork p " +
        				 " where a2h.actrefcode = ap.actrefcode " +
        				 " and ap.plancode = p.code " +
        				 " and p.typerefcode= 112 " +
        				 " And a2h.humenkindrefcode = 2 " +
        				 " and a2h.actrefcode in (select oz2act.enactrefcode from scusageinputitemoz oz , scusageinputitemoz2nct oz2act " +
        				 	" where oz.code=" + ozCode +
        				 	" and oz.code = oz2act.usageinputitemozrefcod " +
        				 	" ) " +
        				 	//SUPP-106909" and a2h.payworkcou>0 " +
        				 " ) as dat1 " +
        				 " group by dat1.cehid  " ;
    		}
    		// �� ������� ������� reportName := '201109/ActZKU/Act21';
    		if(scUsageInputItemKind == 4 ){
    			selectStr ="  select sum(payswork)::numeric(15,2) as payswork , cehid  , korPodr from \n" +
    					"  ( \n" +
    					"  select  case when en2h.paysworkbonus-coalesce(en2h.payworkcou,0) = 0 then en2h.paysworkbonus else en2h.paysworkbonus-coalesce(en2h.payworkcou,0) end as payswork  , en2h.cehid \n" +
    					"   /* ����������� ������������� ��� */ \n" +
    					"    , ( select /*string_agg(sc.code::text ,',')*/ coalesce(sc.podrcodezku,'') From \n" +
    					"              sccounter sc , \n" +
    					"              enestimateitem eni , \n" +
    					"              enplanwork enp , \n" +
    					"              enelement el \n" +
    					"          where sc.statusrefcode = 2 /*� ��� (��-1 � ���.)*/ \n" +
    					"            and sc.estimateitemrefcode = eni.code \n" +
    					"            and eni.accountingtyperefcode = 2 /*�������� */ \n" +
    					"            and enp.code = eni.planrefcode \n" +
    					"            and enp.elementrefcode = el.code \n" +
    					"            and enp.code = ap.plancode \n" +
    					"            and eni.kindrefcode=1 \n" +
    					"            and eni.accountingtyperefcode=2 \n" +
    					"     ) as korPodr \n" +
    					"     From enact2humen en2h, enact2enplanwork ap , enhumenitemkind hk \n" +
    					"     Where en2h.humenkindrefcode = 1 \n" +
    					"       and en2h.actrefcode = ap.actrefcode \n" +
    					"       and en2h.humenkindrefcode = hk.code \n" +
    					"       /*SUPP-107906*/ and case when (en2h.paysworkbonus-coalesce(en2h.payworkcou,0))>0 then (en2h.paysworkbonus-coalesce(en2h.payworkcou,0))>0 else  en2h.paysworkbonus > 0  end \n" +
    					"       and ap.plancode in (select enp.code as planworkcode  From \n" +
    					"                          scusageinputtmz2sccntr sci2scc , \n" +
    					"                          sccounter sc , \n" +
    					"                          enestimateitem eni , \n" +
    					"                          enplanwork enp , \n" +
    					"                          enelement el, \n" +
    					"                          finmoldata  md,enworkorder2enplanwork  wp \n" +
    					"                          where sci2scc.ozrefcode = " + ozCode + " \n" +
    					"                            and sc.statusrefcode = 2  \n" +
    					"                            and sc.code = sci2scc.sccounterrefcode \n" +
    					"                            and sc.estimateitemrefcode = eni.code \n" +
    					"                            and eni.accountingtyperefcode = 2 /*�������� */ \n" +
    					"                            and enp.code = eni.planrefcode \n" +
    					"                            and enp.elementrefcode = el.code \n" +
    					"                            and md.workordercode=wp.workordercode \n" +
    					"                            and md.moltyperefcode=1 \n" +
    					"                            and wp.plancode=enp.code \n" +
    					"                            and sc.code = sci2scc.sccounterrefcode) \n" +
    					"         \n" +
    					"  union all \n" +
    					"  Select en2h.paysworkbonus as payswork, en2h.cehid  \n" +
    					"   /* ����������� ������������� ��� */ \n" +
    					"    , ( select /*string_agg(sc.code::text ,',')*/ coalesce(sc.podrcodezku,'') From \n" +
    					"              sccounter sc , \n" +
    					"              enestimateitem eni , \n" +
    					"              enplanwork enp , \n" +
    					"              enelement el \n" +
    					"          where sc.statusrefcode = 2 /*� ��� (��-1 � ���.)*/ \n" +
    					"            and sc.estimateitemrefcode = eni.code \n" +
    					"            and eni.accountingtyperefcode = 2 /*�������� */ \n" +
    					"            and enp.code = eni.planrefcode \n" +
    					"            and enp.elementrefcode = el.code \n" +
    					"            and enp.code = ap.plancode \n" +
    					"            and eni.kindrefcode=1 \n" +
    					"            and eni.accountingtyperefcode=2 \n" +
    					"     ) as korPodr \n" +
    					"     From enact2humen en2h, enact2enplanwork ap , enhumenitemkind hk \n" +
    					"     Where en2h.humenkindrefcode = 2 \n" +
    					"     and en2h.actrefcode = ap.actrefcode \n" +
    					"     and en2h.humenkindrefcode = hk.code \n" +
    					"     and ap.plancode in (select enp.code as planworkcode  From \n" +
    					"                          scusageinputtmz2sccntr sci2scc , \n" +
    					"                          sccounter sc , \n" +
    					"                          enestimateitem eni , \n" +
    					"                          enplanwork enp , \n" +
    					"                          enelement el, \n" +
    					"                          finmoldata  md,enworkorder2enplanwork  wp \n" +
    					"                          where sci2scc.ozrefcode = " + ozCode + " \n" +
    					"                            and sc.statusrefcode = 2  \n" +
    					"                            and sc.code = sci2scc.sccounterrefcode \n" +
    					"                            and sc.estimateitemrefcode = eni.code \n" +
    					"                            and eni.accountingtyperefcode = 2 /*�������� */ \n" +
    					"                            and enp.code = eni.planrefcode \n" +
    					"                            and enp.elementrefcode = el.code \n" +
    					"                            and md.workordercode=wp.workordercode \n" +
    					"                            and md.moltyperefcode=1 \n" +
    					"                            and wp.plancode=enp.code \n" +
    					"                            and sc.code = sci2scc.sccounterrefcode) \n" +
    					"       \n" +
    					"  ) dat \n" +
    					"  group by cehid , korPodr \n" ;
    		}


    		if(selectStr.length()== 0 ){
    			throw new EnergyproSystemException(" �� �������� ������ ��� �������� !!! ");
    		}
    		try {

    			statement = connection.prepareStatement(selectStr);
    			set = statement.executeQuery();
    			while(set.next()) {
    				anObject = new FKTrans2AXTransItemShort();

    				anObject.amountCur = set.getBigDecimal(1);
    				if(anObject.amountCur != null) {
    					anObject.amountCur = anObject.amountCur.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
    				}
    				anObject.balCeh = set.getString(2);

    				if(scUsageInputItemKind == 4 ){
    					anObject.korCeh = set.getString(3);
    				}

    				/*SUPP-97119 � ���� ��� �������� �� �������� �� ����� �������� � ������ ������ 0*/
    				if(!(anObject.amountCur == null || anObject.amountCur.compareTo(BigDecimal.ZERO) == 0)) {
        				result.list.add(anObject);
    				}
    			}

    			result.setTotalCount(result.list.size());
    			return result;
    		} catch (SQLException e) {
                System.out.println(e.getMessage() + "\nstatement - " + selectStr);
                return result;
            } finally {
                try {
                    if (set != null)
                        set.close();
                } catch (SQLException e) {
                }
                try {
                    if (statement != null)
                        statement.close();
                } catch (SQLException e) {
                }
            }
    	}


        public FKTrans2AXTransItemShortList getDataForProvsSalaryByActRed(int actCode) throws PersistenceException {
        	return this.getDataForProvsSalaryByActRed(actCode, false);
        }
    /**
     * ���������� ����� �������� ���.  �� ���� ��� ������������ ��������   c ����������� �� ���������� ���� ����������
     * */
        public FKTrans2AXTransItemShortList getDataForProvsSalaryByActRed(int actCode, boolean groupByPlans)  throws PersistenceException
        {
        	FKTrans2AXTransItemShortList result = new FKTrans2AXTransItemShortList();
    		FKTrans2AXTransItemShort anObject;
    		result.list = new Vector<>();

    		String selectStr;
    		PreparedStatement statement = null;
    		ResultSet  set = null;

    		selectStr = " select sum(a2h.paysworkbonus)::numeric(15,2) , overlay(a2h.balans placing '1' from 5) AS balans , a2h.cehid  from enact2humen a2h" +
    					" where a2h.paysworkbonus::numeric(15,2) > 0 and a2h.actrefcode =  " + actCode + " group by a2h.balans  , a2h.cehid  " ;

    		if(groupByPlans) {
   			 selectStr =  "SELECT round(cast(sum(payswork) AS decimal), 2) \n " +
   					 ", OVERLAY(payswork.balans PLACING '1' FROM 5) AS balans \n" +
   					 ", cehid FROM \n " +
   					 "( \n " +
   					 "SELECT pw.code \n " +
   					 ", fw.tabnumber \n " +
   					 ", fw.pricegen as oklad \n " +
   					 ", p2h.timeworkfact as wortime \n " +
   					 ", fw.chargepercent \n " +
   					 ", fw.chargerefcode \n " +
   					 ", round(cast(round(cast(fw.pricegen / achu.timemonth AS decimal), 8) * 1.5 * p2h.timeworkfact AS decimal), 2) AS payswork \n " +
   					 ", round(cast((round(cast(fw.pricegen / achu.timemonth AS decimal), 8) * 1.5 * p2h.timeworkfact) * (fw.chargepercent / 100) AS decimal), 2) AS charges \n " +
   					 ", achu.cehid \n " +
   					 ", achu.balans \n" +
   					 "FROM finworker AS fw  \n " +
   					 "INNER JOIN enhumenitem AS hu ON fw.code = hu.finworkercode \n " +
   					 "INNER JOIN enplanwork AS pw ON hu.planrefcode = pw.code \n " +
   					 "INNER JOIN enact2enplanwork AS acpw ON pw.code = acpw.plancode \n " +
   					 "INNER JOIN enplan2humen AS p2h ON (hu.planrefcode = p2h.planrefcode and p2h.tabnumber = fw.tabnumber) \n " +
   					 "INNER JOIN enact2humen AS achu ON (achu.actrefcode = acpw.actrefcode AND achu.tabnumber = fw.tabnumber) \n " +
   					 "WHERE acpw.actrefcode = " + actCode + " \n " +
   					 "AND hu.countfact <> 0 \n " +
   					 "GROUP BY pw.code \n " +
   					 ", fw.tabnumber \n " +
   					 ", fw.pricegen \n " +
   					 ", fw.chargepercent \n " +
   					 ", fw.chargerefcode \n " +
   					 ", achu.cehid \n " +
   					 ", achu.balans \n" +
   					 ", achu.timemonth \n " +
   					 ", p2h.timeworkfact) AS payswork \n " +
   					 "GROUP BY balans, cehid \n ";
   		}
    		try {
    			statement = connection.prepareStatement(selectStr);
    			set = statement.executeQuery();
    			while(set.next()) {
    				anObject = new FKTrans2AXTransItemShort();

    				anObject.amountCur = set.getBigDecimal(1);
    				if(anObject.amountCur != null) {
    					anObject.amountCur = anObject.amountCur.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
    				}
    				anObject.balans = set.getString(2);
    				anObject.balCeh = set.getString(3);

    				result.list.add(anObject);
    			}
    			
    			result.setTotalCount(result.list.size());
    			return result;
    		} catch (SQLException e) {
                System.out.println(e.getMessage() + "\nstatement - " + selectStr);
                return result;
            } finally {
                try {
                    if (set != null)
                        set.close();
                } catch (SQLException e) {
                }
                try {
                    if (statement != null)
                        statement.close();
                } catch (SQLException e) {
                }
            }
    	}

        /**
         * ���������� ����� �������� ���.  �� �� ��� ������������ ��������   c ����������� �� ���������� ���� ����������
         * */
            public FKTrans2AXTransItemShortList getDataForProvsSalaryByOZRed(int ozCode , int iszku , int scUsageInputItemKind )  throws PersistenceException
            {
            	FKTrans2AXTransItemShortList result = new FKTrans2AXTransItemShortList();
        		FKTrans2AXTransItemShort anObject;
        		result.list = new Vector<>();

        		String selectStr= "";
        		PreparedStatement statement = null;
        		ResultSet  set = null;

        		if(iszku==0){
        			selectStr = " select sum(a2h.paysworkbonus)::numeric(15,2) , a2h.balans , a2h.cehid  from enact2humen a2h " +
          					    " where a2h.actrefcode in (select oz2act.enactrefcode from scusageinputitemoz oz , scusageinputitemoz2nct oz2act " +
          					    " 		where oz.code= " + ozCode +
          					    " 	and oz.code = oz2act.usageinputitemozrefcod) " +
          					    " 	group by a2h.balans  , a2h.cehid  " ;
        		}

        		if(iszku==1){
        			/*��� ���������� ���� � ����
            		 * ��� ������� �������� � ������ � ������ � ����� 112 -  �� - ������ �������� � ������� ��� */
            		    		selectStr =  " select sum(payswork)::numeric(15,2) as payswork , balans , cehid from (  " +
            		    				" select a2h.payworkcou as payswork , a2h.cehid , a2h.balans  " +
            		    				" from enact2humen a2h " +
            		    				" where a2h.actrefcode in (select oz2act.enactrefcode from scusageinputitemoz oz , scusageinputitemoz2nct oz2act " +
            		    				" where oz.code=" + ozCode +
            		    				 	" and oz.code = oz2act.usageinputitemozrefcod" +
            		    				 	" ) " +
            		    				 	//SUPP-106909" and a2h.payworkcou>0 " +
            		    				 	" and a2h.humenkindrefcode = 1 " +  // ������������ ��������� � ���������
            		    				 " union all " +
            		    				 " select a2h.paysworkbonus as payswork , a2h.cehid  , a2h.balans	" +
            		    				  " from enact2humen a2h , enact2enplanwork ap , enplanwork p " +
            		    				 " where a2h.actrefcode = ap.actrefcode " +
            		    				 " and ap.plancode = p.code " +
            		    				 " and p.typerefcode= 112 " +
            		    				 " And a2h.humenkindrefcode = 2 " +
            		    				 " and a2h.actrefcode in (select oz2act.enactrefcode from scusageinputitemoz oz , scusageinputitemoz2nct oz2act " +
            		    				 	" where oz.code=" + ozCode +
            		    				 	" and oz.code = oz2act.usageinputitemozrefcod " +
            		    				 	" ) " +
            		    				 	//SUPP-106909" and a2h.payworkcou>0 " +
            		    				 " ) as dat1 " +
            		    				  " group by dat1.balans ,  dat1.cehid  " ;
        		}

        		// �� ������� ������� reportName := '201109/ActZKU/Act21';
        		if(scUsageInputItemKind == 4 ){
        			selectStr = "   \n" +
        					"  select sum(payswork)::numeric(15,2) as payswork , balans , cehid from \n" +
        					"  (  \n" +
        					"  select  /*SUPP-107906*/ case when en2h.paysworkbonus-coalesce(en2h.payworkcou,0) = 0 then en2h.paysworkbonus else en2h.paysworkbonus-coalesce(en2h.payworkcou,0) end as payswork  , en2h.cehid , en2h.balans  \n" +
        					"     From enact2humen en2h, enact2enplanwork ap , enhumenitemkind hk \n" +
        					"     Where en2h.humenkindrefcode = 1 \n" +
        					"       and en2h.actrefcode = ap.actrefcode \n" +
        					"       and en2h.humenkindrefcode = hk.code \n" +
        					"       /*SUPP-107906*/ and case when (en2h.paysworkbonus-coalesce(en2h.payworkcou,0))>0 then (en2h.paysworkbonus-coalesce(en2h.payworkcou,0))>0 else  en2h.paysworkbonus > 0  end \n" +
        					"       and ap.plancode in (select enp.code as planworkcode  From \n" +
        					"                          scusageinputtmz2sccntr sci2scc , \n" +
        					"                          sccounter sc , \n" +
        					"                          enestimateitem eni , \n" +
        					"                          enplanwork enp , \n" +
        					"                          enelement el, \n" +
        					"                          finmoldata  md,enworkorder2enplanwork  wp \n" +
        					"                          where sci2scc.ozrefcode = "+ ozCode +" \n" +
        					"                            and sc.statusrefcode = 2  \n" +
        					"                            and sc.code = sci2scc.sccounterrefcode \n" +
        					"                            and sc.estimateitemrefcode = eni.code \n" +
        					"                            and eni.accountingtyperefcode = 2 /*�������� */ \n" +
        					"                            and enp.code = eni.planrefcode \n" +
        					"                            and enp.elementrefcode = el.code \n" +
        					"                            and md.workordercode=wp.workordercode \n" +
        					"                            and md.moltyperefcode=1 \n" +
        					"                            and wp.plancode=enp.code \n" +
        					"                            and sc.code = sci2scc.sccounterrefcode) \n" +
        					"         \n" +
        					"  union all \n" +
        					"  Select en2h.paysworkbonus as payswork, en2h.cehid , en2h.balans  \n" +
        					"     From enact2humen en2h, enact2enplanwork ap , enhumenitemkind hk \n" +
        					"     Where en2h.humenkindrefcode = 2 \n" +
        					"     and en2h.actrefcode = ap.actrefcode \n" +
        					"     and en2h.humenkindrefcode = hk.code \n" +
        					"     and ap.plancode in (select enp.code as planworkcode  From \n" +
        					"                          scusageinputtmz2sccntr sci2scc , \n" +
        					"                          sccounter sc , \n" +
        					"                          enestimateitem eni , \n" +
        					"                          enplanwork enp , \n" +
        					"                          enelement el, \n" +
        					"                          finmoldata  md,enworkorder2enplanwork  wp \n" +
        					"                          where sci2scc.ozrefcode = "+ ozCode +" \n" +
        					"                            and sc.statusrefcode = 2  \n" +
        					"                            and sc.code = sci2scc.sccounterrefcode \n" +
        					"                            and sc.estimateitemrefcode = eni.code \n" +
        					"                            and eni.accountingtyperefcode = 2 /*�������� */ \n" +
        					"                            and enp.code = eni.planrefcode \n" +
        					"                            and enp.elementrefcode = el.code \n" +
        					"                            and md.workordercode=wp.workordercode \n" +
        					"                            and md.moltyperefcode=1 \n" +
        					"                            and wp.plancode=enp.code \n" +
        					"                            and sc.code = sci2scc.sccounterrefcode) \n" +
        					"       \n" +
        					"  ) dat \n" +
        					"  group by cehid , balans ";
        		}

        		try {
        			statement = connection.prepareStatement(selectStr);
        			set = statement.executeQuery();
        			while(set.next()) {
        				anObject = new FKTrans2AXTransItemShort();

        				anObject.amountCur = set.getBigDecimal(1);
        				if(anObject.amountCur != null) {
        					anObject.amountCur = anObject.amountCur.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
        				}
        				anObject.balans = set.getString(2);
        				anObject.balCeh = set.getString(3);

        				/*SUPP-97119 � ���� ��� �������� �� �������� �� ����� �������� � ������ ������ 0*/
        				if(anObject.amountCur == null || anObject.amountCur.compareTo(BigDecimal.ZERO) != 0) {
        					result.list.add(anObject);
        				}
        			}

        			result.setTotalCount(result.list.size());
        			return result;
        		} catch (SQLException e) {
                    System.out.println(e.getMessage() + "\nstatement - " + selectStr);
                    return result;
                } finally {
                    try {
                        if (set != null)
                            set.close();
                    } catch (SQLException e) {
                    }
                    try {
                        if (statement != null)
                            statement.close();
                    } catch (SQLException e) {
                    }
                }
        	}
            
       public FKTrans2AXTransItemShortList getDataForBlackProvs�hargesumByAct(int actCode) throws PersistenceException {
    	   return this.getDataForBlackProvs�hargesumByAct(actCode, false);
       }

    /**
     * ���������� ����� ���������� ��� �����  �� ���� �������������� �� ���� � ������� ��������� - ������� /�� �������  ��� ������������ �������� ������
     *
     * */
        public FKTrans2AXTransItemShortList getDataForBlackProvs�hargesumByAct(int actCode, boolean groupByPlans)  throws PersistenceException
        {
        	FKTrans2AXTransItemShortList result = new FKTrans2AXTransItemShortList();
    		FKTrans2AXTransItemShort anObject;
    		result.list = new Vector<>();

    		String selectStr;
    		PreparedStatement statement = null;
    		ResultSet  set = null;

    		selectStr =  /*" select sum(a2h.chargesum)::numeric(15,2) , a2h.cehid , a2h.chargerefcode from enact2humen a2h \n" +
    				 " where a2h.actrefcode =  " + actCode +
    				 " group by a2h.cehid , a2h.chargerefcode ";*/
    				 " select sum(chargesum) , cehid , chargerefcode from ( " +
    				 " select a2h.chargesum::numeric(15,2) as chargesum , a2h.cehid , a2h.chargerefcode from enact2humen a2h " +
    				 " where a2h.chargesum::numeric(15,2) > 0 and a2h.actrefcode =  " + actCode + " ) as ddd group by cehid , chargerefcode " ;

    		if(groupByPlans) {
      			 selectStr =  "SELECT round(cast(sum(charges) AS decimal), 2) \n " +
      					 ", cehid \n " +
      					 ", chargerefcode \n " +
      					 " FROM \n" +
      					 "( \n " +
      					 "SELECT pw.code \n " +
      					 ", fw.tabnumber \n " +
      					 ", fw.pricegen as oklad \n " +
      					 ", p2h.timeworkfact as wortime \n " +
      					 ", fw.chargepercent \n " +
      					 ", fw.chargerefcode \n " +
      					 ", round(cast(round(cast(fw.pricegen / achu.timemonth AS decimal), 8) * 1.5 * p2h.timeworkfact AS decimal), 2) AS payswork \n " +
      					 ", round(cast(round(cast(round(cast(fw.pricegen / achu.timemonth AS decimal), 8) * 1.5 * p2h.timeworkfact AS decimal), 2) * (fw.chargepercent / 100) AS decimal), 2) AS charges \n " +
      					 ", achu.cehid \n " +
      					 ", achu.balans \n" +
      					 "FROM finworker AS fw  \n " +
      					 "INNER JOIN enhumenitem AS hu ON fw.code = hu.finworkercode \n " +
      					 "INNER JOIN enplanwork AS pw ON hu.planrefcode = pw.code \n " +
      					 "INNER JOIN enact2enplanwork AS acpw ON pw.code = acpw.plancode \n " +
      					 "INNER JOIN enplan2humen AS p2h ON (hu.planrefcode = p2h.planrefcode and p2h.tabnumber = fw.tabnumber) \n " +
      					 "INNER JOIN enact2humen AS achu ON (achu.actrefcode = acpw.actrefcode AND achu.tabnumber = fw.tabnumber) \n " +
      					 "WHERE acpw.actrefcode = " + actCode + " \n " +
      					 "AND hu.countfact <> 0 \n " +
      					 "GROUP BY pw.code \n " +
      					 ", fw.tabnumber \n " +
      					 ", fw.pricegen \n " +
      					 ", fw.chargepercent \n " +
      					 ", fw.chargerefcode \n " +
      					 ", achu.cehid \n " +
      					 ", achu.balans \n" +
      					 ", achu.timemonth \n " +
      					 ", p2h.timeworkfact) AS payswork \n " +
      					 "GROUP BY chargerefcode, cehid \n ";
      		}
    		
    		try {
    			statement = connection.prepareStatement(selectStr);
    			set = statement.executeQuery();
    			int i;
    			for (i = 0; set.next(); i++) {
    				anObject = new FKTrans2AXTransItemShort();



    				anObject.amountCur = set.getBigDecimal(1);
    				if(anObject.amountCur != null) {
    					anObject.amountCur = anObject.amountCur.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
    				}

    				anObject.balCeh = set.getString(2);
    				anObject.chargerefcode = set.getInt(3);

    				result.list.add(anObject);
    			}

    			result.setTotalCount(i);
    			return result;
    		} catch (SQLException e) {
                System.out.println(e.getMessage() + "\nstatement - " + selectStr);
                return result;
            } finally {
                try {
                    if (set != null)
                        set.close();
                } catch (SQLException e) {
                }
                try {
                    if (statement != null)
                        statement.close();
                } catch (SQLException e) {
                }
            }
    	}


        /**
         * ���������� ����� ���������� ��� �����  �� �� �������������� �� ���� � �� ���� ��������� - ������� /�� �������  ��� ������������ �������� ������
         *
         * */
            public FKTrans2AXTransItemShortList getDataForBlackProvs�hargesumByOZ(int ozCode , int iszku , int scUsageInputItemKind )  throws PersistenceException
            {
            	FKTrans2AXTransItemShortList result = new FKTrans2AXTransItemShortList();
        		FKTrans2AXTransItemShort anObject;
        		result.list = new Vector<>();

        		String selectStr="";
        		PreparedStatement statement = null;
        		ResultSet  set = null;

        		if(iszku==0){
        			selectStr = " select sum(chargesum) , cehid , chargerefcode from ( " +
            				"  select a2h.chargesum::numeric(15,2) as chargesum , a2h.cehid , a2h.chargerefcode from enact2humen a2h " +
            				"   where a2h.paysworkbonus > 0 " +
            				"     and a2h.actrefcode in (select oz2act.enactrefcode from scusageinputitemoz oz , scusageinputitemoz2nct oz2act " +
            				"     							where oz.code= " + ozCode +
            				" 							and oz.code = oz2act.usageinputitemozrefcod)   ) as ddd group by cehid , chargerefcode ";
        		}
        		if(iszku==1){
        			selectStr =	" select sum(chargesum) as chargesum , cehid ,  chargerefcode from ( " +
    				" select /*a2h.chargesum::numeric(15,2)*/ ((coalesce(a2h.payworkcou,0)*a2h.chargepercent)/100)::numeric(15,2) as chargesum , a2h.cehid  ,  a2h.chargerefcode " +
    				" from enact2humen a2h " +
    				" where a2h.actrefcode in (select oz2act.enactrefcode from scusageinputitemoz oz , scusageinputitemoz2nct oz2act " +
    				" where oz.code= " + ozCode +
    				" and oz.code = oz2act.usageinputitemozrefcod " +
    				" )  " +
    				//SUPP-106909" and a2h.payworkcou>0  " +
    				" and a2h.humenkindrefcode = 1 " +  // ������������ ��������� � ���������
    				" union all " +
    				" select /*a2h.chargesum::numeric(15,2)*/ (((a2h.paysworkbonus/* � ������ �� ���������� -coalesce(a2h.payworkcou,0)*/)*a2h.chargepercent)/100)::numeric(15,2) as chargesum , a2h.cehid , a2h.chargerefcode " +
    				" from enact2humen a2h , enact2enplanwork ap , enplanwork p  " +
    				" where a2h.actrefcode = ap.actrefcode  " +
    				" and ap.plancode = p.code  " +
    				" and p.typerefcode= 112 " +
    				" And a2h.humenkindrefcode = 2 " +
    				" and a2h.actrefcode in (select oz2act.enactrefcode from scusageinputitemoz oz , scusageinputitemoz2nct oz2act " +
    				" where oz.code= " + ozCode +
    				" and oz.code = oz2act.usageinputitemozrefcod " +
    				" )  " +
    				//SUPP-106909" and a2h.payworkcou>0  " +
    				" ) as dat1 " +
    				" group by dat1.cehid , dat1.chargerefcode " ;
        		}


        		// �� ������� ������� reportName := '201109/ActZKU/Act21';
        		if(scUsageInputItemKind == 4 ){
        			selectStr = "  select sum(chargesum) as chargesum , cehid ,  chargerefcode  , korPodr  from \n" +
        					"  (  \n" +
        					"  select ((( case when en2h.paysworkbonus-coalesce(en2h.payworkcou,0) = 0 then en2h.paysworkbonus else en2h.paysworkbonus-coalesce(en2h.payworkcou,0) end )*en2h.chargepercent)/100)::numeric(15,2) as chargesum , en2h.cehid  ,  en2h.chargerefcode  \n" +
        					"   /* ����������� ������������� ��� */ \n" +
        					"    , ( select /*string_agg(sc.code::text ,',')*/ coalesce(sc.podrcodezku,'') From \n" +
        					"              sccounter sc , \n" +
        					"              enestimateitem eni , \n" +
        					"              enplanwork enp , \n" +
        					"              enelement el \n" +
        					"          where sc.statusrefcode = 2 /*� ��� (��-1 � ���.)*/ \n" +
        					"            and sc.estimateitemrefcode = eni.code \n" +
        					"            and eni.accountingtyperefcode = 2 /*�������� */ \n" +
        					"            and enp.code = eni.planrefcode \n" +
        					"            and enp.elementrefcode = el.code \n" +
        					"            and enp.code = ap.plancode \n" +
        					"            and eni.kindrefcode=1 \n" +
        					"            and eni.accountingtyperefcode=2 \n" +
        					"     ) as korPodr       \n" +
        					"     From enact2humen en2h, enact2enplanwork ap , enhumenitemkind hk \n" +
        					"     Where en2h.humenkindrefcode = 1 \n" +
        					"       and en2h.actrefcode = ap.actrefcode \n" +
        					"       and en2h.humenkindrefcode = hk.code \n" +
        					"            /*SUPP-107906*/ and case when (en2h.paysworkbonus-coalesce(en2h.payworkcou,0))>0 then (en2h.paysworkbonus-coalesce(en2h.payworkcou,0))>0 else  en2h.paysworkbonus > 0  end \n" +
        					"       and ap.plancode in (select enp.code as planworkcode  From \n" +
        					"                          scusageinputtmz2sccntr sci2scc , \n" +
        					"                          sccounter sc , \n" +
        					"                          enestimateitem eni , \n" +
        					"                          enplanwork enp , \n" +
        					"                          enelement el, \n" +
        					"                          finmoldata  md,enworkorder2enplanwork  wp \n" +
        					"                          where sci2scc.ozrefcode = "+ ozCode +" \n" +
        					"                            and sc.statusrefcode = 2  \n" +
        					"                            and sc.code = sci2scc.sccounterrefcode \n" +
        					"                            and sc.estimateitemrefcode = eni.code \n" +
        					"                            and eni.accountingtyperefcode = 2 /*�������� */ \n" +
        					"                            and enp.code = eni.planrefcode \n" +
        					"                            and enp.elementrefcode = el.code \n" +
        					"                            and md.workordercode=wp.workordercode \n" +
        					"                            and md.moltyperefcode=1 \n" +
        					"                            and wp.plancode=enp.code \n" +
        					"                            and sc.code = sci2scc.sccounterrefcode) \n" +
        					"         \n" +
        					"  union all \n" +
        					"  Select (((/*SUPP-107906*/ case when en2h.paysworkbonus-coalesce(en2h.payworkcou,0) = 0 then en2h.paysworkbonus else en2h.paysworkbonus-coalesce(en2h.payworkcou,0) end)*en2h.chargepercent)/100)::numeric(15,2) as chargesum , en2h.cehid  ,  en2h.chargerefcode  \n" +
        					"   /* ����������� ������������� ��� */ \n" +
        					"    , ( select /*string_agg(sc.code::text ,',')*/ coalesce(sc.podrcodezku,'') From \n" +
        					"              sccounter sc , \n" +
        					"              enestimateitem eni , \n" +
        					"              enplanwork enp , \n" +
        					"              enelement el \n" +
        					"          where sc.statusrefcode = 2 /*� ��� (��-1 � ���.)*/ \n" +
        					"            and sc.estimateitemrefcode = eni.code \n" +
        					"            and eni.accountingtyperefcode = 2 /*�������� */ \n" +
        					"            and enp.code = eni.planrefcode \n" +
        					"            and enp.elementrefcode = el.code \n" +
        					"            and enp.code = ap.plancode \n" +
        					"            and eni.kindrefcode=1 \n" +
        					"            and eni.accountingtyperefcode=2 \n" +
        					"     ) as korPodr       \n" +
        					"     From enact2humen en2h, enact2enplanwork ap , enhumenitemkind hk \n" +
        					"     Where en2h.humenkindrefcode = 2 \n" +
        					"     and en2h.actrefcode = ap.actrefcode \n" +
        					"     and en2h.humenkindrefcode = hk.code \n" +
        					"     /*SUPP-107906*/ and case when (en2h.paysworkbonus-coalesce(en2h.payworkcou,0))>0 then (en2h.paysworkbonus-coalesce(en2h.payworkcou,0))>0 else  en2h.paysworkbonus > 0  end  \n" +
        					"     and ap.plancode in (select enp.code as planworkcode  From \n" +
        					"                          scusageinputtmz2sccntr sci2scc , \n" +
        					"                          sccounter sc , \n" +
        					"                          enestimateitem eni , \n" +
        					"                          enplanwork enp , \n" +
        					"                          enelement el, \n" +
        					"                          finmoldata  md,enworkorder2enplanwork  wp \n" +
        					"                          where sci2scc.ozrefcode = "+ ozCode +" \n" +
        					"                            and sc.statusrefcode = 2  \n" +
        					"                            and sc.code = sci2scc.sccounterrefcode \n" +
        					"                            and sc.estimateitemrefcode = eni.code \n" +
        					"                            and eni.accountingtyperefcode = 2 /*�������� */ \n" +
        					"                            and enp.code = eni.planrefcode \n" +
        					"                            and enp.elementrefcode = el.code \n" +
        					"                            and md.workordercode=wp.workordercode \n" +
        					"                            and md.moltyperefcode=1 \n" +
        					"                            and wp.plancode=enp.code \n" +
        					"                            and sc.code = sci2scc.sccounterrefcode) \n" +
        					"       \n" +
        					"  ) dat \n" +
        					"  group by cehid , chargerefcode , korPodr  ";
        		}

        		try {
        			statement = connection.prepareStatement(selectStr);
        			set = statement.executeQuery();
        			int i;
        			for (i = 0; set.next(); i++) {
        				anObject = new FKTrans2AXTransItemShort();



        				anObject.amountCur = set.getBigDecimal(1);
        				if(anObject.amountCur != null) {
        					anObject.amountCur = anObject.amountCur.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
        				}

        				anObject.balCeh = set.getString(2);
        				anObject.chargerefcode = set.getInt(3);

        				if(scUsageInputItemKind == 4 ){
        					anObject.korCeh = set.getString(4);
        				}

        				if(!(anObject.amountCur == null || anObject.amountCur.compareTo(BigDecimal.ZERO) == 0))
        				result.list.add(anObject);
        			}

        			result.setTotalCount(result.list.size());
        			return result;
        		} catch (SQLException e) {
                    System.out.println(e.getMessage() + "\nstatement - " + selectStr);
                    return result;
                } finally {
                    try {
                        if (set != null)
                            set.close();
                    } catch (SQLException e) {
                    }
                    try {
                        if (statement != null)
                            statement.close();
                    } catch (SQLException e) {
                    }
                }
        	}


            public FKTrans2AXTransItemShortList getDataForRedProvs�hargesumByAct(int actCode) throws PersistenceException {
            	return this.getDataForRedProvs�hargesumByAct(actCode, false);
            }
        /**
         * ���������� ����� ���������� ��� �����  �� ���� �������������� �� ���� , ��� � ������� ��������� - ������� /�� �������  ��� ������������ �������� �������
         *  !!!!!!!!!!!!! ������ 8 ������� � ��� �� ����� 3
         * */
            public FKTrans2AXTransItemShortList getDataForRedProvs�hargesumByAct(int actCode, boolean groupByPlans)  throws PersistenceException
            {
            	FKTrans2AXTransItemShortList result = new FKTrans2AXTransItemShortList();
        		FKTrans2AXTransItemShort anObject;
        		result.list = new Vector<>();

        		String selectStr;
        		PreparedStatement statement = null;
        		ResultSet  set = null;

        		selectStr = " select sum(chargesum) as chargesum , cehid  , chargerefcode , balans from ( " +
        				 " select a2h.chargesum::numeric(15,2) as chargesum , a2h.cehid  , a2h.chargerefcode"
        				 + ", overlay(overlay(a2h.balans placing '3' from 8 ) placing '2' from 5) as balans from enact2humen a2h \n" +
        				 " where a2h.chargesum::numeric(15,2) > 0 and a2h.actrefcode =  " + actCode + " ) as ddd "+
        				 " group by cehid , chargerefcode  , balans ";
        		
        		if(groupByPlans) {
         			 selectStr =  "SELECT round(cast(sum(charges) AS decimal), 2) \n " +
         					 ", cehid \n " +
         					 ", chargerefcode \n " +
         					 ", OVERLAY(OVERLAY(balans PLACING '3' FROM 8 ) PLACING '2' FROM 5) \n" +
         					 " FROM \n" +
         					 "( \n " +
         					 "SELECT pw.code \n " +
         					 ", fw.tabnumber \n " +
         					 ", fw.pricegen as oklad \n " +
         					 ", p2h.timeworkfact as wortime \n " +
         					 ", fw.chargepercent \n " +
         					 ", fw.chargerefcode \n " +
         					 ", round(cast(round(cast(fw.pricegen / achu.timemonth AS decimal), 8) * 1.5 * p2h.timeworkfact AS decimal), 2) AS payswork \n " +
         					 ", round(cast(round(cast(round(cast(fw.pricegen / achu.timemonth AS decimal), 8) * 1.5 * p2h.timeworkfact AS decimal), 2) * (fw.chargepercent / 100) AS decimal), 2) AS charges \n " +
         					 ", achu.cehid \n " +
         					 ", achu.balans \n" +
         					 "FROM finworker AS fw  \n " +
         					 "INNER JOIN enhumenitem AS hu ON fw.code = hu.finworkercode \n " +
         					 "INNER JOIN enplanwork AS pw ON hu.planrefcode = pw.code \n " +
         					 "INNER JOIN enact2enplanwork AS acpw ON pw.code = acpw.plancode \n " +
         					 "INNER JOIN enplan2humen AS p2h ON (hu.planrefcode = p2h.planrefcode and p2h.tabnumber = fw.tabnumber) \n " +
         					 "INNER JOIN enact2humen AS achu ON (achu.actrefcode = acpw.actrefcode AND achu.tabnumber = fw.tabnumber) \n " +
         					 "WHERE acpw.actrefcode = " + actCode + " \n " +
         					 "AND hu.countfact <> 0 \n " +
         					 "GROUP BY pw.code \n " +
         					 ", fw.tabnumber \n " +
         					 ", fw.pricegen \n " +
         					 ", fw.chargepercent \n " +
         					 ", fw.chargerefcode \n " +
         					 ", achu.cehid \n " +
         					 ", achu.balans \n " +
         					 ", achu.timemonth \n " +
         					 ", p2h.timeworkfact) AS payswork \n " +
         					 "GROUP BY chargerefcode, cehid, balans \n ";
         		}
        		
        		try {
        			statement = connection.prepareStatement(selectStr);
        			set = statement.executeQuery();
        			int i;
        			for (i = 0; set.next(); i++) {
        				anObject = new FKTrans2AXTransItemShort();



        				anObject.amountCur = set.getBigDecimal(1);
        				if(anObject.amountCur != null) {
        					anObject.amountCur = anObject.amountCur.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
        				}

        				anObject.balCeh = set.getString(2);
        				anObject.chargerefcode = set.getInt(3);
        				anObject.balans = set.getString(4);

        				result.list.add(anObject);
        			}

        			result.setTotalCount(i);
        			return result;
        		} catch (SQLException e) {
                    System.out.println(e.getMessage() + "\nstatement - " + selectStr);
                    return result;
                } finally {
                    try {
                        if (set != null)
                            set.close();
                    } catch (SQLException e) {
                    }
                    try {
                        if (statement != null)
                            statement.close();
                    } catch (SQLException e) {
                    }
                }
        	}


    /**
     * ���������� ����� ���������� ��� �����  �� ���� �������������� �� ���� , ��� � ������� ��������� - ������� /�� �������  ��� ������������ �������� �������
     *  !!!!!!!!!!!!! ������ 8 ������� � ��� �� ����� 3
     * */
        public FKTrans2AXTransItemShortList getDataForRedProvs�hargesumByOZ(int ozCode ,int iszku , int scUsageInputItemKind )  throws PersistenceException
        {
        	FKTrans2AXTransItemShortList result = new FKTrans2AXTransItemShortList();
    		FKTrans2AXTransItemShort anObject;
    		result.list = new Vector<>();

    		String selectStr="";
    		PreparedStatement statement = null;
    		ResultSet  set = null;

    		if(iszku==0){
    			selectStr = " select sum(chargesum) as chargesum , cehid ,chargerefcode , balans from ( " +
    					    " select a2h.chargesum::numeric(15,2) as chargesum  , a2h.cehid , a2h.chargerefcode , overlay(a2h.balans placing '3' from 8 ) as balans " +
    					    " from enact2humen a2h  " +
    					    " where a2h.paysworkbonus > 0 " +
    					    "  and a2h.actrefcode in (select oz2act.enactrefcode from scusageinputitemoz oz , scusageinputitemoz2nct oz2act " +
    					    " where oz.code=" + ozCode  + " 	and oz.code = oz2act.usageinputitemozrefcod) " +
    					    " ) as dat1 " +
    					    " group by cehid , chargerefcode  , balans " ;
    		}

    		if(iszku==1){
    			selectStr =  " select sum(chargesum)::numeric(15,2) as chargesum , cehid ,  chargerefcode , overlay(balans placing '3' from 8 ) from ( " +
    					" select /*a2h.chargesum::numeric(15,2)*/ ((coalesce(a2h.payworkcou,0)*a2h.chargepercent)/100)::numeric(15,2) as chargesum  , a2h.cehid  ,  a2h.chargerefcode , a2h.balans  " +
    					" from enact2humen a2h  " +
    					" where a2h.actrefcode in (select oz2act.enactrefcode from scusageinputitemoz oz , scusageinputitemoz2nct oz2act " +
    					" where oz.code=" + ozCode  +
    					" and oz.code = oz2act.usageinputitemozrefcod " +
    					" )  " +
    					//SUPP-106909" and a2h.payworkcou>0  " +
    					" and a2h.humenkindrefcode = 1 " +  // ������������ ��������� � ���������
    					" union all " +
    					" select /*a2h.chargesum::numeric(15,2)*/ (((a2h.paysworkbonus/* � ������ �� ���������� -coalesce(a2h.payworkcou,0)*/)*a2h.chargepercent)/100)::numeric(15,2) as chargesum , a2h.cehid , a2h.chargerefcode , a2h.balans " +
    					" from enact2humen a2h , enact2enplanwork ap , enplanwork p  " +
    					" where a2h.actrefcode = ap.actrefcode " +
    					" and ap.plancode = p.code " +
    					" and p.typerefcode= 112 " +
    					" And a2h.humenkindrefcode = 2 " +
    					" and a2h.actrefcode in (select oz2act.enactrefcode from scusageinputitemoz oz , scusageinputitemoz2nct oz2act " +
    					" where oz.code=" + ozCode +
    					" and oz.code = oz2act.usageinputitemozrefcod " +
    					" ) " +
    					//SUPP-106909" and a2h.payworkcou>0  " +
    					" ) as dat1 " +
    					" group by dat1.cehid , dat1.chargerefcode , overlay(balans placing '3' from 8 )" ;
    		}

    		// �� ������� ������� reportName := '201109/ActZKU/Act21';
    		if(scUsageInputItemKind == 4 ){
    			selectStr = "  select sum(chargesum) as chargesum , cehid ,  chargerefcode  , overlay(balans placing '3' from 8 ) as balans  from \n" +
    					"  (  \n" +
    					"  select ((( /*SUPP-107906*/ case when en2h.paysworkbonus-coalesce(en2h.payworkcou,0) = 0 then en2h.paysworkbonus else en2h.paysworkbonus-coalesce(en2h.payworkcou,0) end )*en2h.chargepercent)/100)::numeric(15,2) as chargesum , en2h.cehid  ,  en2h.chargerefcode , en2h.balans    \n" +
    					"     From enact2humen en2h, enact2enplanwork ap , enhumenitemkind hk \n" +
    					"     Where en2h.humenkindrefcode = 1 \n" +
    					"       and en2h.actrefcode = ap.actrefcode \n" +
    					"       and en2h.humenkindrefcode = hk.code \n" +
    					"       /*SUPP-107906*/ and case when (en2h.paysworkbonus-coalesce(en2h.payworkcou,0))>0 then (en2h.paysworkbonus-coalesce(en2h.payworkcou,0))>0 else  en2h.paysworkbonus > 0  end  \n" +
    					"       and ap.plancode in (select enp.code as planworkcode  From \n" +
    					"                          scusageinputtmz2sccntr sci2scc , \n" +
    					"                          sccounter sc , \n" +
    					"                          enestimateitem eni , \n" +
    					"                          enplanwork enp , \n" +
    					"                          enelement el, \n" +
    					"                          finmoldata  md,enworkorder2enplanwork  wp \n" +
    					"                          where sci2scc.ozrefcode = "+ ozCode +" \n" +
    					"                            and sc.statusrefcode = 2  \n" +
    					"                            and sc.code = sci2scc.sccounterrefcode \n" +
    					"                            and sc.estimateitemrefcode = eni.code \n" +
    					"                            and eni.accountingtyperefcode = 2 /*�������� */ \n" +
    					"                            and enp.code = eni.planrefcode \n" +
    					"                            and enp.elementrefcode = el.code \n" +
    					"                            and md.workordercode=wp.workordercode \n" +
    					"                            and md.moltyperefcode=1 \n" +
    					"                            and wp.plancode=enp.code \n" +
    					"                            and sc.code = sci2scc.sccounterrefcode) \n" +
    					"         \n" +
    					"  union all \n" +
    					"  Select (( ( /*SUPP-107906*/ case when en2h.paysworkbonus-coalesce(en2h.payworkcou,0) = 0 then en2h.paysworkbonus else en2h.paysworkbonus-coalesce(en2h.payworkcou,0) end ) *en2h.chargepercent)/100)::numeric(15,2) as chargesum , en2h.cehid  ,  en2h.chargerefcode  , en2h.balans  \n" +
    					"     From enact2humen en2h, enact2enplanwork ap , enhumenitemkind hk \n" +
    					"     Where en2h.humenkindrefcode = 2 \n" +
    					"     and en2h.actrefcode = ap.actrefcode \n" +
    					"     and en2h.humenkindrefcode = hk.code \n" +
    					"     /*SUPP-107906*/ and case when (en2h.paysworkbonus-coalesce(en2h.payworkcou,0))>0 then (en2h.paysworkbonus-coalesce(en2h.payworkcou,0))>0 else  en2h.paysworkbonus > 0  end  \n" +
    					"     and ap.plancode in (select enp.code as planworkcode  From \n" +
    					"                          scusageinputtmz2sccntr sci2scc , \n" +
    					"                          sccounter sc , \n" +
    					"                          enestimateitem eni , \n" +
    					"                          enplanwork enp , \n" +
    					"                          enelement el, \n" +
    					"                          finmoldata  md,enworkorder2enplanwork  wp \n" +
    					"                          where sci2scc.ozrefcode = "+ ozCode +" \n" +
    					"                            and sc.statusrefcode = 2  \n" +
    					"                            and sc.code = sci2scc.sccounterrefcode \n" +
    					"                            and sc.estimateitemrefcode = eni.code \n" +
    					"                            and eni.accountingtyperefcode = 2 /*�������� */ \n" +
    					"                            and enp.code = eni.planrefcode \n" +
    					"                            and enp.elementrefcode = el.code \n" +
    					"                            and md.workordercode=wp.workordercode \n" +
    					"                            and md.moltyperefcode=1 \n" +
    					"                            and wp.plancode=enp.code \n" +
    					"                            and sc.code = sci2scc.sccounterrefcode) \n" +
    					"       \n" +
    					"  ) dat \n" +
    					"  group by cehid , chargerefcode , overlay(balans placing '3' from 8 ) ";
    		}

    		try {

    			statement = connection.prepareStatement(selectStr);
    			set = statement.executeQuery();

    			int i;
    			for (i = 0; set.next(); i++) {
    				anObject = new FKTrans2AXTransItemShort();

    				anObject.amountCur = set.getBigDecimal(1);
    				if(anObject.amountCur != null) {
    					anObject.amountCur = anObject.amountCur.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
    				}

    				anObject.balCeh = set.getString(2);
    				anObject.chargerefcode = set.getInt(3);
    				anObject.balans = set.getString(4);

    				if(!(anObject.amountCur == null || anObject.amountCur.compareTo(BigDecimal.ZERO) == 0))
    				 result.list.add(anObject);
    			}

    			result.setTotalCount(result.list.size());
    			return result;
    		} catch (SQLException e) {
                System.out.println(e.getMessage() + "\nstatement - " + selectStr);
                return result;
            } finally {
                try {
                    if (set != null)
                        set.close();
                } catch (SQLException e) {
                }
                try {
                    if (statement != null)
                        statement.close();
                } catch (SQLException e) {
                }
            }
    	}
        
        public Map.Entry<List<DataForTransAmortizacBlack>, List<DataForTransAmortizacRed>> getDataForProvsAmortization(ENAct act) throws PersistenceException {
			// IV ����� �������� �����������
        	
        	Connection finConn = null;
    		try {
    			finConn = getNEWConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
    			FKPostingLogic fpLogic = new FKPostingLogic(finConn, userProfile);
    			mDaxLogic mdLogic = new mDaxLogic(connection, userProfile);
            	ENAct2TransportDAO dao = new ENAct2TransportDAO(connection, userProfile);

    			ENAct2TransportFilter a2tFilter = new ENAct2TransportFilter();
    			a2tFilter.actRef.code = act.code;
    			ENAct2TransportShortList a2tList = dao.getScrollableFilteredList(a2tFilter, 0, -1);
    			
    			List<DataForTransAmortizacBlack> resultedListBlack = new ArrayList<>();
    			List<DataForTransAmortizacRed> resultedListRed = new ArrayList<>();
    			
    			for(ENAct2TransportShort item : a2tList.list) {
    				String cehtransport = fpLogic.getKodPodr(item.invNumber);
    				// ������� ��� ���� �� �������� mdax
    				cehtransport = mdLogic.getMAINORGANIZATIONIDByHRMORGANIZATIONID(cehtransport, act.dateGen);
    				String kodSubschB = fpLogic.getKodSubschB(item.invNumber);
    				String vidOStransp = fpLogic.getG45(item.invNumber);
    				String kodZatrTransp = fpLogic.getNNNN(item.invNumber);
    				
    				DataForTransAmortizacBlack bKey = new DataForTransAmortizacBlack(cehtransport, vidOStransp
    						, "131" + kodSubschB.substring(2, 3), item.paysWork);
    				if(resultedListBlack.contains(bKey)) {
    					DataForTransAmortizacBlack contained = resultedListBlack.get(resultedListBlack.indexOf(bKey));
    					contained.summa = contained.summa.add(bKey.summa);
    				} else {
    					resultedListBlack.add(bKey);
    				}
    				
					DataForTransAmortizacRed rKey = new DataForTransAmortizacRed(cehtransport, kodZatrTransp, vidOStransp,
							"131" + kodSubschB.substring(2, 3), item.paysWork);
					
					if(resultedListRed.contains(rKey)) {
						DataForTransAmortizacRed contained = resultedListRed.get(resultedListRed.indexOf(rKey));
						contained.summa = contained.summa.add(rKey.summa);
					} else {
						resultedListRed.add(rKey);
					}
    			}
    			return new AbstractMap.SimpleImmutableEntry<>(resultedListBlack, resultedListRed);
    			//return resultedList;
    		} catch (DatasourceConnectException e) {
    			throw new SystemException(e);
			} finally {
    			try {
					if (finConn != null && !finConn.isClosed()) {
						finConn.close();
						finConn = null;
					}
				} catch (SQLException e) {}

    		}
        }
    		
        

	/**
	 *	���������� ��� ������������� (�����������/�� �����������) �� ���� ����������� �����
	 *
	 *	@param actCode - ��� ����
	 *	@return connectionKind - ��� �������������
	 */
	public int getConnectionKind(int actCode) {
		int connectionKind = Integer.MIN_VALUE;

		try {
			ENConnectionKindDAO ckDao = new ENConnectionKindDAO(connection, userProfile);

			ENConnectionKindFilter ckFilter = new ENConnectionKindFilter();
			ckFilter.conditionSQL = " code = ( "
					+ " select distinct k.code from enact2enplanwork a2p , entechcond2planwork tc2p , enservicesobject2techcondtnsservices s2tc , "
				    + " entechconditionsservcs ts, enconnectionkind k " +
								" where a2p.actrefcode = " + actCode +
								" and a2p.plancode= tc2p.planrefcode " +
								" and tc2p.techconservicesrefcode = s2tc.techcondservrefcode " +
								" and ts.code = s2tc.techcondservrefcode  \n" +
								" and ts.connectionkindrefcode = k.code   \n" +
								" union all \n" +
								" select distinct k.code \n" +
								"  from  \n" +
								"  enservicesobject so , enact a  ,  enservicesobject2techcondtnsservices so2pric , \n" +
								"  entechconditionsservcs ts, enconnectionkind k   \n" +
								"  where so.elementcode = a.elementcode  \n" +
								"  and a.code = " + actCode +
								"  and so.code = so2pric.servicesobjectrefcode \n" +
								"  and ts.code = so2pric.techcondservrefcode  \n" +
								"  and ts.connectionkindrefcode = k.code \n" +
								" union all \n" +
								" select distinct k.code  from  ENSOProj2SOConn pfj2so , enservicesobject so , enact a , enservicesobject2techcondtnsservices so2pric , entechconditionsservcs ts, enconnectionkind k \n" +
								" where pfj2so.soprojrefcode = so.code \n" +
								" and so.elementcode = a.elementcode \n" +
								" and a.code = " + actCode +
								" and so2pric.servicesobjectrefcode = pfj2so.soconnrefcode \n" +
								" and ts.code = so2pric.techcondservrefcode   \n" +
								" and ts.connectionkindrefcode = k.code \n"+
								" union all \n" +
								" select distinct k.code  from enact2enplanwork a2p , entechcond2planwork tc2p  , entechconditionsservcs vcs  ,  enconnectionkind k \n"+
								" where a2p.actrefcode =   " + actCode +  "\n"+
								" and a2p.plancode= tc2p.planrefcode   \n"+
								" and tc2p.techconservicesrefcode = vcs.code \n"+
								" and vcs.connectionkindrefcode = k.code \n"+
                                " limit 1 \n"
								+ ") ";


			int ckArr[] = ckDao.getFilteredCodeArray(ckFilter, 0, -1);

			if (ckArr.length > 0) {

				connectionKind = ckArr[0];
			}

		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		}

		return connectionKind;
	}


/**
 *
 * ��������� ���������� ������������� �������� �������������������� ������
 *
 * �� ���� ���� ������� �������������������� ������ ��������� 50%, �� ������� ��������� 0.5
 *
 * @param codeAct ��� ����
 * @return ���������� ������������� �������� �������������������� ������
 */
public BigDecimal getProductionExpencesRateByAct(int codeAct) {
	BigDecimal percent = getProductionExpencesPercentByAct(codeAct);
	return (percent == null) ? null : percent.divide(new BigDecimal(100).setScale(2, BigDecimal.ROUND_HALF_UP));
}

/**
 *
 * ��������� ���������� ������������� �������� �������������������� ������ �� �������� ����
 *
 * �� ���� ���� ������� �������������������� ������ ��������� 50%, �� ������� ��������� 0.5
 *
 * @param date �������� ����
 * @return ���������� ������������� �������� �������������������� ������
 */
public BigDecimal getProductionExpencesRateByDate(Date date) {
	BigDecimal percent = this.getProductionExpencesPercentByDate(date);
	return (percent == null) ? null : percent.divide(new BigDecimal(100).setScale(2, BigDecimal.ROUND_HALF_UP));
}

/**
 *
 * ��������� ������� �������������������� ������ �� �������� ����
 *
 * @param date �������� ����
 * @return ������� �������������������� ������
 */
public BigDecimal getProductionExpencesPercentByDate(Date date) {
	ENStandardConstDAO dao = new ENStandardConstDAO(connection, userProfile);
	return dao.getENStandardConstEntryOnDate(ENStandardConst.PERCENT_COMPANY, date);
}

/**
 *
 * ��������� ������� �������������������� ������ �� ���� ����
 *
 * @param codeAct ��� ����
 * @return ������� �������������������� ������
 */
public BigDecimal getProductionExpencesPercentByAct(int codeAct) {

    BigDecimal result = new BigDecimal(0);
    PreparedStatement statement = null;
    ResultSet set = null;


    String sql = " select * from get_productionexpencespercent("+codeAct+")";


    try {
        statement = connection.prepareStatement(sql);
        set = statement.executeQuery();

        while (set.next()) {
            result = set.getBigDecimal(1);
        }

        if (result == null)
            result = new BigDecimal(0);

        return result;

    } catch (SQLException e) {
        System.out.println(e.getMessage() + "\nstatement - " + sql);
        return result;
    } finally {
        try {
            if (set != null)
                set.close();
        } catch (SQLException e) {
        }
        try {
            if (statement != null)
                statement.close();
        } catch (SQLException e) {
        }
    }
}


/**
 * ���������� ����� ����������� ���.  �� ���� ����� �� ������� ��� ������������ ��������
 * */
    public FKTrans2AXTransItemShortList getDataForProvsAmortizationByActServices(int actCode , int tkcalckind , boolean isWithDelivery )  throws PersistenceException
    {
    	FKTrans2AXTransItemShortList result = new FKTrans2AXTransItemShortList();
		FKTrans2AXTransItemShort anObject;
		result.list = new Vector<>();

		String selectStr;
		PreparedStatement statement = null;
		ResultSet  set = null;

		selectStr = " SELECT \n " +
				   " en2tr.invnumber, en2tr.name , sum(en2tr.payswork) as payswork \n " +
				   " FROM \n " +
				   "  enact2transport en2tr , entransportitem ti, enact2enplanwork a2pw, tktransportreal treal, \n " +
				"  enplanworkitem pwi \n " +
				"    where en2tr.actrefcode = " + actCode +
				"   and ti.planrefcode =  a2pw.plancode \n " +
				"   and a2pw.actrefcode = en2tr.actrefcode \n " +
				"   and en2tr.invnumber = treal.invnumber \n " +
				"   and treal.code = ti.transportrealcode \n " +
				"   and a2pw.plancode = pwi.planrefcode \n " +
				"   and ti.planitemrefcode = pwi.code \n " +
				"  group by en2tr.invnumber, en2tr.name , en2tr.payswork \n " +
				"  Order by en2tr.invnumber " ;
		try {
			statement = connection.prepareStatement(selectStr);
			set = statement.executeQuery();
			int i;
			for (i = 0; set.next(); i++) {
				anObject = new FKTrans2AXTransItemShort();

				anObject.accountDimension1 = set.getString(1); // ��� ���
				anObject.accountDimension2 = set.getString(2); // ������������ ���

				anObject.amountCur = set.getBigDecimal(3);
				if(anObject.amountCur != null) {
					anObject.amountCur = anObject.amountCur.setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
				}



				result.list.add(anObject);
			}

			result.setTotalCount(i);
			return result;
		} catch (SQLException e) {
            System.out.println(e.getMessage() + "\nstatement - " + selectStr);
            return result;
        } finally {
            try {
                if (set != null)
                    set.close();
            } catch (SQLException e) {
            }
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
            }
        }
	}
    
    /**
     * 
     * �������� ����������������� ���� ��� ����� ������������� � ������������
     * 
     * @param act ��� {@link ENAct}
     * @param invNumber ����������� ����� ��������� �������� ��� ��������� �������������
     * @return ����������������� ����
     */
    public String getCorrespondingAccountForReconstructionAndModernization(ENAct act, String invNumber) {
    	if(act == null || act.code == Integer.MIN_VALUE) throw new java.lang.NullPointerException();

    	if (invNumber == null || invNumber.trim().isEmpty()) {
    		throw new IllegalArgumentException("\n\n���������� �������� ���� �������������/����������� �� ��'���� ��� ������������ ������!");
    	}

    	Connection finConn = null;
    	try {
    		finConn = getNEWConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
        	FKPostingLogic fpLogic = new FKPostingLogic(finConn , userProfile);
        	boolean isPricon = this.getConnectionKind(act.code) != Integer.MIN_VALUE;
    		/* ������� �����/������� �� �� */
            String nalog = fpLogic.getM(invNumber);
            /* ��������� ��� ������ �� */
            String kod_zatr = fpLogic.getNNNN(invNumber);
        	
            String korSch_black = (isPricon && nalog.equals("1") && kod_zatr.equals("94")) ? "1526" :
                (isPricon && nalog.equals("1") && !kod_zatr.equals("94")) ? "1521" :
                (isPricon && nalog.equals("0")) ? "1521" :
                (!isPricon && nalog.equals("1") && !kod_zatr.equals("94")) ? "1527" :
                (!isPricon && nalog.equals("0")) ? 	"1527" : "";
            return korSch_black;    		
    	} catch (DatasourceConnectException e) {
    		throw new SystemException(e);
		} finally {
	    	  try {
	              if (finConn != null && ! finConn.isClosed()) {
	                  finConn.close();
	                  finConn = null;
	              }
	          } catch (SQLException e) {
	          }
    	}
    }
    
    /**
     * 
     * �������� ����������������� ���� ��� ����� �� ������� �� �������
     * 
     * @param ��� ����� {@link ENAct}
     * @return ����������������� ����
     */
    public String getCorrespondingAccountForServices(ENAct act) throws PersistenceException {
    	ENAct2ProvDAO dao = new ENAct2ProvDAO(connection, userProfile);
    	boolean chk = act.code == 1019649682 ? false : true; 
    	ENAct2ProvShortList list = dao.getListByAct(act, chk);
    	return this.getCorrespondingAccountForServices(list.get(0).actPostingKindRefCode, list.get(0).isIncomeAct == 1);
    }
    
    /**
     * 
     * �������� ����. ���� �� �� ��� ����� � ������������ ���������
     * 
     * @param oz ������ �� ��� ��������� �����
     * @return ����. ����
     * @throws PersistenceException 
     */
    public String getCorrespondingAccountForOZ(SCUsageInputItemOZ oz) throws PersistenceException {
    	SCUsageInputItemDAO usiDao = new SCUsageInputItemDAO(connection, userProfile);    	
    	SCUsageInputItem usiObject = usiDao.getObject(oz.usageInputItemRef.code);
    	String IIII;
    	
        // SUPP-67738 ����������� �� ��� ��������� ���������
        boolean isAbonCounter = (oz.account == null || oz.account.trim().length() == 0);
        
	    if( usiObject.kindRef.code == SCUsageInputItemKind.UsageInput ||
		        usiObject.kindRef.code == SCUsageInputItemKind.UsageInputInZKU) {
	    	 if(isAbonCounter) {
	    		 IIII = "2320";
	    	 } else {
		    	 if(  oz.account.substring(0, 2).equals("11") ){
		    		 IIII = "1532";
		    	 } else
		    	 {
		    		 IIII = oz.account;
		    	 }
	    	 }
	    	 
	    } else {
	    	IIII = "1531";
	    }
        return IIII;
    }
    
    public String getCorrespondingCAAForOZ(SCUsageInputItemOZ oz, boolean isPriconnection) throws PersistenceException {
    	SCUsageInputItemDAO usiDao = new SCUsageInputItemDAO(connection, userProfile);
    	SCUsageInputDAO uiDao = new SCUsageInputDAO(connection, userProfile);
    	//BBBBBBBBBBB= "2"+ new String(isPricon == true ? "5" : "4") + "000000000";
    	SCUsageInputItem usiObject = usiDao.getObject(oz.usageInputItemRef.code);
    	SCUsageInput ui = uiDao.getObject(usiObject.usageInputRef.code);   	
    	boolean isAbonCounter = (oz.account == null || oz.account.trim().length() == 0);
	    if( usiObject.kindRef.code == SCUsageInputItemKind.UsageInput ||
		        usiObject.kindRef.code == SCUsageInputItemKind.UsageInputInZKU) {
	    	if(isAbonCounter) {
		   		 return (ui.iszku == 1) ? "10506300001" : "10503300001";
			} else {
				
		    	return "14000000000";
			}	    	
	    } else {
	    	return "2" + new String(isPriconnection == true ? "5" : "4") + "000000000";
	    }
    }
    
    /**
     * 
     * �������� ����������������� ���� ��� ����� �� ������� �� �������
     * 
     * @param postingKind ��� ������� �������� {@link ENActPostingKind}
     * @param isIncomeAct �������� � ������� ��������� ����
     * @return ����������������� ����
     */
    public String getCorrespondingAccountForServices(int postingKind, boolean isIncomeAct) {
    	String out = null;
    	switch(postingKind) {
    	case ENActPostingKind.SERVICES_CONTRACT_PROJECT:
    		out = "2375";
    		break;
    	case ENActPostingKind.SERVICES_CONTRACT:
    		out = (isIncomeAct) ? "9033" : "2374";
    		break;
    	case ENActPostingKind.SERVICES_CONTRACT_PROJECT_PRICONNECTION:
    		out = "2373";
    		break;
    	case ENActPostingKind.SERVICES_CONTRACT_INSTALL_COUNTER:
    	case ENActPostingKind.SERVICES_CONTRACT_PARAMETERIZATION:
    	case ENActPostingKind.SERVICES_CONTRACT_PROGRAMMING:
    	case ENActPostingKind.SERVICES_PRICONNECTION_AFTER_01062021:
    		out = "2320";
    		break;
    	case ENActPostingKind.SERVICES_SUPPLIER_CONTRACT:
    		out = "9033";
    		break;	
    		default:
    			throw new SystemException("������������ ������ ��������: " + postingKind);
    	}
    	return out;
    }
    
    
    private String getFinDocCodeByENAct(ENAct act) throws PersistenceException {
    	if(act == null || act.code == Integer.MIN_VALUE) throw new NullPointerException("�� ������� ���!");
		ENServicesObjectDAO soDao = new ENServicesObjectDAO(connection, userProfile);
		ENGeneralContractsDAO genContractDAO = new ENGeneralContractsDAO(connection, userProfile);
        ENServicesObject soObj = soDao.getObjectByENAct(act);
        ENServicesObjectShortList soByTechConditions = soDao.getListByENActThroughTechConditions(act);
        ENGeneralContracts genContract = null;
        
       	if (soByTechConditions.totalCount > 0){
          		genContract = genContractDAO.getObject(soByTechConditions.get(0).generalContractRefCode);
        } else if(soObj != null) {
          		genContract = genContractDAO.getObject(soObj.generalContractRef.code);
        }
       	
       	int connectionKind = this.getConnectionKind(act.code);

        if (soObj == null && soByTechConditions.totalCount == 0 && connectionKind == ENConnectionKind.GENERAL_CONNECTION  ) {
        	ENGeneralContractsShortList gecContrlist = genContractDAO.getListByAct(act);
	   	    if (gecContrlist.totalCount > 0 ) {
	   	    	genContract = genContractDAO.getObject(gecContrlist.get(0).code);
	   		}
        }
         
        return (genContract != null) ? genContract.finDocCode : "";
    }
    
    /**
     * 
     * �������� ����������������� ��� ��� ����� ������������� � ������������
     * 
     * @param act ��� {@link ENAct}
     * @param invNumber ����������� ����� ��������� �������� ��� ��������� �������������
     * @param correspondentAccount ����������������� ����
     * @return ������ � ����������������� ���
     * @throws PersistenceException
     * @throws DatasourceConnectException
     */
    public String getCorrespondingCAAForReconstructionAndModernization(ENAct act, String invNumber, String correspondentAccount) throws PersistenceException, DatasourceConnectException {
    	if(invNumber == null || correspondentAccount == null) throw new java.lang.NullPointerException();
    	Connection finConn = null;
    	try {
    		finConn = getNEWConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
    		FKPostingLogic fpLogic = new FKPostingLogic(finConn , userProfile);
    		ENPlanWorkDAO plDao = new ENPlanWorkDAO(connection, userProfile);
    		
			// ��� ��������� �� ��
			String vidOS = fpLogic.getG45(invNumber);
			String connectionKind_KAU = "_";
	       	int connectionKind = this.getConnectionKind(act.code);
	         // 2019.06.27 ���� ��� ������������� �� ������� �� ��� �������� ����� ��� ��� ������������ ( ������� ��� ������� )
	       	if (connectionKind == ENConnectionKind.STANDART || connectionKind == ENConnectionKind.UNDEFINED || connectionKind == ENConnectionKind.GENERAL_CONNECTION) {
	       		connectionKind_KAU = "�";
	        }
	        if (connectionKind == ENConnectionKind.NO_STANDART || connectionKind == ENConnectionKind.READY_MADE ) {
	        	connectionKind_KAU = "�";
	        }
	        
	         boolean isInvest = false;
	         ENPlanWorkFilter plFilter = new ENPlanWorkFilter();
	         plFilter.conditionSQL = " enplanwork.code = ( select p.code from enact2enplanwork a2p , enplanwork p \n" +
											" where a2p.actrefcode = " + act.code +
											" and a2p.plancode = p.code  \n" +
											" and p.typerefcode in (" +ENPlanWorkType.INVEST + ","+ ENPlanWorkType.SERVICES_FROM_SIDE_INVEST + ") \n" +
											" limit 1  ) " ;
	         int[] plIPArr = plDao.getFilteredCodeArrayNOSEGR(plFilter, 0, -1);
	         if (plIPArr.length > 0 ){
	        	 isInvest = true;
	         }
	         
	         String finDocCode = this.getFinDocCodeByENAct(act);
	         
        	return new String((correspondentAccount.equals("1521") || correspondentAccount.equals("1526")) ? connectionKind_KAU : isInvest==true ? "0":"2"  )
            		/*���� ������ - ����������� ������� */
            		+ invNumber.substring(1, 6)
            		/*���� ������ - ���� ������� (1521,1526) - �� ������� ��  ����� '030'+��� ��  */
            		+new String( (correspondentAccount.equals("1521") || correspondentAccount.equals("1526")) ? finDocCode : "030" + vidOS );
    	} finally {
	    	  try {
	              if (finConn != null && ! finConn.isClosed()) {
	                  finConn.close();
	                  finConn = null;
	              }
	          } catch (SQLException e) {
	          }
    	}
    }
    
    /**
     * 
     * �������� ����������������� ��� ��� ����� �������� � ������������ ��������
     * 
     * ��� ����� 2361 � 6� ������ ������ 1
     * 
     * @param act ��� {@link ENAct}
     * @param invNumber ����������� ����� ��������� �������� ��� ��������� �������������
     * @return ������ � ����������������� ���
     * @throws PersistenceException
     * @throws DatasourceConnectException
     */
    public String getCorrespondingCAAForRepairing(ENAct act, String invNumber) throws DatasourceConnectException {
    	if(act == null || act.code == Integer.MIN_VALUE 
    			|| act.actTypeRef == null || act.actTypeRef.code == Integer.MIN_VALUE) 
    		throw new java.lang.NullPointerException();
    	if(act.actTypeRef.code != ENPlanWorkState.CAPITAL_REPAIR
    			&& act.actTypeRef.code != ENPlanWorkState.CURRENT_REPAIR) {
    		throw new SystemException("������������ ��� ����!");
    	}
    	Connection finConn = null;
    	try {
    		finConn = getNEWConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
    		FKPostingLogic fpLogic = new FKPostingLogic(finConn , userProfile);
        	StringBuilder builder = new StringBuilder(Tools.repeatSymbol("0", "", 11));
        	String TT = fpLogic.getTT(invNumber);
        	String NNNN = fpLogic.getNNNN(invNumber);
        	int Y = fpLogic.getY(NNNN);
        	String M = fpLogic.getM(invNumber);
        	
        	builder.setCharAt(0, (act.actTypeRef.code == ENPlanWorkState.CAPITAL_REPAIR ? '2' : '1'));
        	builder.replace(1, 3, TT);
        	builder.setCharAt(3, ("" + Y).charAt(0));
        	builder.setCharAt(4, M.charAt(0));
        	builder.setCharAt(5, '1');
        	builder.replace(6, 11, invNumber.substring(1));

        	
        	return builder.toString();
    	} finally {
	    	  try {
	              if (finConn != null && ! finConn.isClosed()) {
	                  finConn.close();
	                  finConn = null;
	              }
	          } catch (SQLException e) {
	          }
    	}
    }
    
    /**
     * 
     * �������� ����������������� ��� ��� ����� �� ������� �� �������
     * 
     * @param ��� ����� {@link ENAct}
     * @return ����������������� ���
     */
    public String getCorrespondingCAAForServices(ENAct act) throws PersistenceException {
    	ENAct2ProvDAO provDao = new ENAct2ProvDAO(connection, userProfile);
    	TKFINWorkTypeDAO finWorkTypeDao = new TKFINWorkTypeDAO(connection, userProfile);
    	boolean chk = act.code == 1019649682 ? false : true;
    	ENAct2ProvShortList provList = provDao.getListByAct(act, chk);
		String connectionKind_KAU = "_";
       	int connectionKind = this.getConnectionKind(act.code);
         // 2019.06.27 ���� ��� ������������� �� ������� �� ��� �������� ����� ��� ��� ������������ ( ������� ��� ������� )
       	if (connectionKind == ENConnectionKind.STANDART || connectionKind == ENConnectionKind.UNDEFINED || connectionKind == ENConnectionKind.GENERAL_CONNECTION) {
       		connectionKind_KAU = "�";
        }
        if (connectionKind == ENConnectionKind.NO_STANDART || connectionKind == ENConnectionKind.READY_MADE ) {
        	connectionKind_KAU = "�";
        }
        String X = null;
        if(provList.get(0).actPostingKindRefCode == ENActPostingKind.SERVICES_CONTRACT) {
            X = finWorkTypeDao.getFinCodeByActCode(act.code, connectionKind == Integer.MIN_VALUE);	
        }
        String finDocCode = this.getFinDocCodeByENAct(act);
        
    	return this.getCorrespondingCAAForServices(provList.get(0).actPostingKindRefCode
    			, X, connectionKind_KAU, finDocCode, provList.get(0).isIncomeAct == 1);
    }
    		
    public String getCorrespondingCAAForServices(int postingKind, String finWorkType, String connectionKind
    		, String finDocCode, boolean isIncomeAct) {
    	String out = null;
    	switch(postingKind) {
    	case ENActPostingKind.SERVICES_CONTRACT_PROJECT:
    		out = connectionKind + "06662" + finDocCode;
    		break;
    	case ENActPostingKind.SERVICES_CONTRACT:
    		out = finWorkType + "6662" + ((isIncomeAct) ? "00001" : finDocCode);
    		break;
    	case ENActPostingKind.SERVICES_CONTRACT_PROJECT_PRICONNECTION:
    		out = connectionKind + "06662" + finDocCode;
    		break;
    	case ENActPostingKind.SERVICES_CONTRACT_INSTALL_COUNTER:
    		out = "10507500001";
    		break;        		
    	case ENActPostingKind.SERVICES_CONTRACT_PARAMETERIZATION:
    		out = "10507700001";
    		break;    		
    	case ENActPostingKind.SERVICES_CONTRACT_PROGRAMMING:
    		out = "10507800001";
    		break;
    	case ENActPostingKind.SERVICES_PRICONNECTION_AFTER_01062021:
    		out = "10508500001";
    		break;
    	case ENActPostingKind.SERVICES_SUPPLIER_CONTRACT:
    		out = finWorkType + "6662" +  "00001" ;
    		break;	
    		default:
    			throw new SystemException("������������ ������ ��������: " + postingKind);
    	}
    	return out;
    }
    
    /**
     * ����� ������� ��� "������� �� ���������� �� �� �� ������"
     * */
    public String getStrProv_OPERATIVE_OBJECT(ENAct act, String userName 
    		, BigDecimal productionExpencesPercent
    		, BigDecimal chargepercent) throws PersistenceException, DatasourceConnectException {
    	 
    	Connection finConn = null;
    	try {
    		finConn = getNEWConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
    		FKPostingLogic fpLogic = new FKPostingLogic(finConn , userProfile);
    		ENPlanWorkDAO plDao = new ENPlanWorkDAO(connection, userProfile);
    		mDaxLogic mdLogic = new mDaxLogic(connection, userProfile);
    		ENAct2TransportDAO a2tDAO = new ENAct2TransportDAO(connection, userProfile);
    		String V_Prov_Buffer = "";
    		BigDecimal zpByAct = new BigDecimal(0);
    		// I �������� �� �� ������
			FKTrans2AXTransItemShortList fkTrans2axTransList = this.getDataForProvsSalaryByAct(act.code);
			for (int i = 0; i < fkTrans2axTransList.totalCount; i++) {
				zpByAct = zpByAct.add(fkTrans2axTransList.get(i).amountCur);
				V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
						+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
								fkTrans2axTransList.get(i).amountCur, // ����� � �������
								"000", 
								"6610", // ���������� ����
								"00000000130", // ���������� ���
								"000", // ����������������� ���
								"9033", // ����������������� ����
								"�0666200001", // ��� ��� 
								act.numberGen, act.dateGen,
								"��� � " + act.numberGen + ", "
										+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct)
										+ " �ᒺ��� �������� �� �� �� ������ "
										 
							 ,	userName
						);
			} 
			// I �������� �� �� �������, �������� ����� �� � ������������ �� ��� ���������� � ���
			
			fkTrans2axTransList = this.getDataForProvsSalaryByActRed(act.code);
			for (int ir = 0; ir < fkTrans2axTransList.totalCount; ir++) {
				V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
						+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
								fkTrans2axTransList.get(ir).amountCur.negate(), // ����� � �������
								fkTrans2axTransList.get(ir).balCeh, 
								"6610", // ���������� ����
								"00000000130", // ���������� ���
								fkTrans2axTransList.get(ir).balCeh, // ����������������� ���
								fkTrans2axTransList.get(ir).balans.substring(0, 4), // ����������������� ���� ���
								// ����������������� ���
								fkTrans2axTransList.get(ir).balans.substring(4, 15), act.numberGen, act.dateGen,
								"��� � " + act.numberGen + ", "
										+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct)
										+ " �ᒺ��� �������� �� �� �� ������" // ����������
										, userName
						);
			}
			
			// II �������� 
			// (�) �����������   ����������������� ������� �� ���� (�����������������
			// ������� * 100/122 ) // ������
		        BigDecimal amountCur = zpByAct.multiply(productionExpencesPercent);
				amountCur = amountCur.divide(new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP);
				amountCur = amountCur.multiply(new BigDecimal(100));
				amountCur = amountCur.divide(new BigDecimal(100).add(chargepercent), 6, BigDecimal.ROUND_HALF_UP)
						.setScale(2, BigDecimal.ROUND_HALF_UP);
				V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
						+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
								// ����� � ������� = �� �� ���� * ������� ����������� ������ / 100 * 100 /
								// (100+22 - ��� 22 ��� ��� �� ��������� - ��� �������� ����� �������� �
								// �������� ��������� - �� �������� )
								amountCur,
								"000", 
								"6610", // ���������� ����
								"10000000130", // ���������� ���
								"000", // ����������������� ���
								"9033", // ����������������� ����
								"�0666500001",// ����������������� ���						
								act.numberGen, act.dateGen,
								"��� � " + act.numberGen + ", "
										+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct)
										+ " �ᒺ��� �������� �� �� �� ������ "
								 , userName 
								
						);
				/*** II (�)_red �������� �� ����������� ����� ������� */
				V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
						+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
								// ����� � ������� = �� �� ���� * ������� ����������� ������ / 100 * 100 /
								// (100+22 - ��� 22 ��� ��� �� ��������� - ��� �������� ����� �������� �
								// �������� ��������� - �� �������� )
								amountCur.negate(),
								"000",
								"6610", // ���������� ����
								"10000000130", // ���������� ���
								"000", // ����������������� ���
								"9100", // ����������������� ����
								"10110000001", // ����������������� ���
								 act.numberGen, 
								 act.dateGen,
								"��� � " + act.numberGen + ", "
										+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct)
										+ " �ᒺ��� �������� �� �� �� ������ "
								 , userName
						);
				/*
				 * II_2+ black ������ ����� �������� (����������������� ������� 50% �� ����
				 * �������� ����� - ����� (�) // ������ )
				 */
				BigDecimal amountCur_2 = zpByAct.multiply(productionExpencesPercent);
				amountCur_2 = amountCur_2.divide(new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP);
				amountCur_2 = amountCur_2.subtract(amountCur).setScale(2, BigDecimal.ROUND_HALF_UP);
				V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
						+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
								amountCur_2, 
								"000",
								"6510", // ���������� ����
								"10000000130", // ���������� ���
								"000", // ����������������� ���
								"9033", // ����������������� ����
								"�0666500001",// ����������������� ���
								  act.numberGen, act.dateGen,
								"��� � " + act.numberGen + ", "
										+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct)
										+ " �ᒺ��� �������� �� �� �� ������ "
								 ,userName   
								
						);
				/*
				 * II_2+ red ������ ����� �������� (����������������� ������� 50% �� ����
				 * �������� ����� - ����� (�) // ������� )
				 */
				V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
						+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
								amountCur_2.negate(), 
								"000", 
								"6510", // ���������� ����
								"10000000130", // ���������� ���
								"000", // ����������������� ���
								"9100", // ����������������� ����
								"20130000001" , // ����������������� ���
								act.numberGen, act.dateGen,
								"��� � " + act.numberGen + ", "
										+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct)
										+ " �ᒺ��� �������� �� �� �� ������ "
								, userName
								
						);
				
				
				// III ����� �������� ������ ������ �� ������������'������ �������� ���������
				// ����������� - ������
				fkTrans2axTransList = this.getDataForBlackProvs�hargesumByAct(act.code);
				BigDecimal esvSum = new BigDecimal(0);
				for (int c = 0; c < fkTrans2axTransList.totalCount; c++) {
					esvSum = esvSum.add(fkTrans2axTransList.get(c).amountCur);
					V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
							+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
									fkTrans2axTransList.get(c).amountCur, // ����� � �������
									"000",
									"6510" , // ���������� ����
									"00000000130", // ���������� ���
									"000", // ����������������� ���
									"9033", // ����������������� ����
									 "�0666300001", 
									 act.numberGen, 
									 act.dateGen,
									"��� � " + act.numberGen + ", "
											+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct)
											+ " �ᒺ��� �������� �� �� �� ������  " , // ����������
									userName
							);
				}

				// ������ ������ �� ������������'������ �������� ��������� ����������� - �������
				// , �������� �� ���� , ��� , ���� ��������� - ������� / �� �������
				fkTrans2axTransList = this.getDataForRedProvs�hargesumByAct(act.code);
				for (int c = 0; c < fkTrans2axTransList.totalCount; c++) {
					V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
							+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
									fkTrans2axTransList.get(c).amountCur.negate(), // ����� � �������
									"000",
									fkTrans2axTransList.get(c).chargerefcode == FINChargeType.IS_NOT_INVALID ? "6510"
											: "6512", // ���������� ����
									"00000000130", // ���������� ���
									"000", // ����������������� ���
									fkTrans2axTransList.get(c).balans.substring(0, 4), // ����������������� ���� ���
																						// ���������� ����
									// ����������������� ���
									fkTrans2axTransList.get(c).balans.substring(4, 15),
									act.numberGen,
									act.dateGen,
									"��� � " + act.numberGen + ", "
											+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct)
											+ " �ᒺ��� �������� �� �� �� ������ " , // ����������
									userName
							);
				}
				
				
				// IV ����� �������� �����������

				ENAct2TransportFilter a2tFilter = new ENAct2TransportFilter();
				a2tFilter.actRef.code = act.code;
				ENAct2TransportShortList a2tList = a2tDAO.getScrollableFilteredList(a2tFilter, 0, -1);

				// ������ ��� �������� �� ����������� �������
				HashMap<DataForTransAmortizacRed, DataForTransAmortizacRed> transDataAmortizacRed = new HashMap<>();
				// ������ ��� �������� �� ����������� ������
				HashMap<DataForTransAmortizacBlack, DataForTransAmortizacBlack> transDataAmortizacBlack = new HashMap<>();

				for (int tr = 0; tr < a2tList.totalCount; tr++) {

					String cehtransport = fpLogic.getKodPodr(a2tList.get(tr).invNumber);
					// ������� ��� ���� �� �������� mdax
					cehtransport = mdLogic.getMAINORGANIZATIONIDByHRMORGANIZATIONID(cehtransport, act.dateGen);
					String kodSubschB = fpLogic.getKodSubschB(a2tList.get(tr).invNumber);
					String vidOStransp = fpLogic.getG45(a2tList.get(tr).invNumber);
					String kodZatrTransp = fpLogic.getNNNN(a2tList.get(tr).invNumber);

					// !!!!!RED ������� ������ �� ���������� . ���� �������� �� ���� ���������� ,
					// ���� ������ , ��� �� - ��� �������� �������
					DataForTransAmortizacRed rKey;
					rKey = new DataForTransAmortizacRed(cehtransport, kodZatrTransp, vidOStransp,
							"131" + kodSubschB.substring(2, 3), a2tList.get(tr).paysWork);

					if (transDataAmortizacRed.containsValue(rKey)) {
						DataForTransAmortizacRed dValue = (DataForTransAmortizacRed) transDataAmortizacRed.get(rKey);
						rKey.summa = rKey.summa.add(dValue.summa);
						// ������ �� ������ ������ ��� ���� � ������� � ������ ������
						transDataAmortizacRed.remove(rKey);
					}
					transDataAmortizacRed.put(rKey, rKey);

					// !!!!!BLACK ������� ������ �� ���������� . ���� �������� �� ���� ���������� ,
					// ��� �� - ��� �������� ������
					DataForTransAmortizacBlack bKey;
					bKey = new DataForTransAmortizacBlack(cehtransport, vidOStransp, "131" + kodSubschB.substring(2, 3),
							a2tList.get(tr).paysWork);

					if (transDataAmortizacBlack.containsValue(bKey)) {
						DataForTransAmortizacBlack bValue = (DataForTransAmortizacBlack) transDataAmortizacBlack
								.get(bKey);
						bKey.summa = bKey.summa.add(bValue.summa);
						// ������ �� ������ ������ ��� ���� � ������� � ������ ������
						transDataAmortizacBlack.remove(bKey);
					}
					transDataAmortizacBlack.put(bKey, bKey);

				}

				// ������������ �������� �� ����������� �������
				DataForTransAmortizacRed rrKey = null;
				Iterator<DataForTransAmortizacRed> rrIterator = transDataAmortizacRed.keySet().iterator();
				while (rrIterator.hasNext()) {
					rrKey = (DataForTransAmortizacRed) rrIterator.next();
					DataForTransAmortizacRed rrValue = (DataForTransAmortizacRed) transDataAmortizacRed.get(rrKey);
					System.out.println("Value for trans amortizac RED: " + rrValue.ceh + " // " + rrValue.kodZatrat
							+ " // " + rrValue.vidOS + " // " + rrValue.balSch + " // " + rrValue.summa + " // ");
					if (rrValue.summa.doubleValue() > 0) {
						V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
								+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
										rrValue.summa.negate(), // ����� � �������
										rrValue.ceh,
										rrValue.balSch, // ���������� ���� 131 + ( 3 - � ���� ����� ��
																		// �������� ��- ���������� )
										"310000000"+rrValue.ceh.substring(0, 2), // ���������� ���
										"000", // ����������������� ���
										rrValue.kodZatrat.substring(0, 4), // ����������������� ���� ��� �������������
																			// ����
										rrValue.kodZatrat.substring(4, 15), // ����������������� ��� ������� �� ���
																			// �������������
										act.numberGen, act.dateGen,
										"��� � " + act.numberGen + ", "
												+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct)
												+ " �ᒺ��� �������� �� �� �� ������ " , // ����������
												 
										userName
								);
					}

				}

				// ������������ �������� �� ����������� ������
				DataForTransAmortizacBlack bbKey = null;
				Iterator<DataForTransAmortizacBlack> bbIterator = transDataAmortizacBlack.keySet().iterator();
				BigDecimal sumAmort = new BigDecimal(0);
				while (bbIterator.hasNext()) {
					bbKey = (DataForTransAmortizacBlack) bbIterator.next();
					DataForTransAmortizacBlack bbValue = (DataForTransAmortizacBlack) transDataAmortizacBlack
							.get(bbKey);
					System.out.println("Value for trans amortizac BLACK: " + bbValue.ceh + " // " + bbValue.vidOS
							+ " // " + bbValue.balSch + " // " + bbValue.summa + " // ");
					if (bbValue.summa.doubleValue() > 0) {
						sumAmort = sumAmort.add(bbValue.summa);
						V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
								+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
										bbValue.summa, // ����� � �������
										bbValue.ceh, 
										bbValue.balSch, // ���������� ���� 131 + ( 3 - � ���� ����� ��
																		// �������� ��- ���������� )
										"310000000"+bbValue.ceh.substring(0, 2) , // ���������� ���
										"000", // ����������������� ���
										"9033", // ����������������� ����
										"�0666100001", // ����������������� ���										
										 act.numberGen, 
										 act.dateGen,
										"��� � " + act.numberGen + ", "
												 + new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct)
												 + " �ᒺ��� �������� �� �� �� ������ " , // ����������
												 
										userName
								);
					}

				}
				
			// �������� ����������� �� ����  
			  if(sumAmort.doubleValue()>0) {	
				V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
						+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
								sumAmort, // ����� � �������
								"000", 
								"9033", // ���������� ����  											
								"�0666100001" , // ���������� ���
								"000", // ����������������� ���
								"7913", // ����������������� ����
								"11000000001", // ����������������� ���										
								 act.numberGen, 
								 act.dateGen,
								"��� � " + act.numberGen + ", "
										 + new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct)
										 + " �ᒺ��� �������� �� �� �� ������ close amort" , // ����������
										 
								userName
						);
			  }
		 // ��������  ���  �� ���� 	
				if (esvSum.doubleValue()>0) {
					V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
							+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
									esvSum, // ����� � �������
									"000", 
									"9033", // ���������� ����  											
									"�0666300001" , // ���������� ���
									"000", // ����������������� ���
									"7913", // ����������������� ����
									"11000000001", // ����������������� ���										
									 act.numberGen, 
									 act.dateGen,
									"��� � " + act.numberGen + ", "
											 + new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct)
											 + " �ᒺ��� �������� �� �� �� ������ close esv" , // ����������
											 
									userName
							);
				}
				
				// �������� ����������� �� ����
               if (amountCur.doubleValue()>0){
            	   V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
							+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
									amountCur, // ����� � �������
									"000", 
									"9033", // ���������� ����  											
									"�0666500001" , // ���������� ���
									"000", // ����������������� ���
									"7913", // ����������������� ����
									"11000000001", // ����������������� ���										
									 act.numberGen, 
									 act.dateGen,
									"��� � " + act.numberGen + ", "
											 + new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct)
											 + " �ᒺ��� �������� �� �� �� ������ close zagal vitrati" , // ����������
											 
									userName
							);  
               }
               
               /*
				 * �������� ������ ����� �������� (����������������� ������� 50% �� ����
				 * �������� ����� - ����� (�) // ������ )
				 */
               if (amountCur.doubleValue()>0){
            	   V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
							+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
									amountCur_2, // ����� � �������
									"000", 
									"9033", // ���������� ����  											
									"�0666500001" , // ���������� ���
									"000", // ����������������� ���
									"7913", // ����������������� ����
									"11000000001", // ����������������� ���										
									 act.numberGen, 
									 act.dateGen,
									"��� � " + act.numberGen + ", "
											 + new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct)
											 + " �ᒺ��� �������� �� �� �� ������ close zagal vitrati_50proc" , // ����������
											 
									userName
							);  
               }
               
               // ��������  �������� �� ����
			 if (zpByAct.doubleValue()>0) {
				 V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
							+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
									zpByAct, // ����� � �������
									"000", 
									"9033", // ���������� ����  											
									"�0666200001" , // ���������� ���
									"000", // ����������������� ���
									"7913", // ����������������� ����
									"11000000001", // ����������������� ���										
									 act.numberGen, 
									 act.dateGen,
									"��� � " + act.numberGen + ", "
											 + new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct)
											 + " �ᒺ��� �������� �� �� �� ������ close zp" , // ����������
											 
									userName
							); 
			 }
	         
        	return V_Prov_Buffer;
    	} finally {
	    	  try {
	              if (finConn != null && ! finConn.isClosed()) {
	                  finConn.close();
	                  finConn = null;
	              }
	          } catch (SQLException e) {
	          }
    	}
    }

// NET-2335 �������� �������� �� ���� ��������� ����� FK & AX
	public FKProvResult movePostingByActWork(ENAct act) {
		Connection finConn = null;
		try {
			FKProvResult provResult = null;

			finConn = getNEWConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);

			boolean isZKUMounting = this.checkZKUMountingByAct(act);

			FKPostingLogic fpLogic = new FKPostingLogic(finConn, userProfile);
			ENSettingsLogic settingsLogic = new ENSettingsLogic(connection, userProfile);
			ENServicesObjectDAO soDao = new ENServicesObjectDAO(connection, userProfile);
			ServicesLogic soLogic = new ServicesLogic(connection, userProfile);
			ElementLogic elLogic = new ElementLogic(connection, userProfile);
			ENPlanWorkDAO plDao = new ENPlanWorkDAO(connection, userProfile);
			ENGeneralContractsDAO genContractDAO = new ENGeneralContractsDAO(connection, userProfile);
			ENAct2TransportDAO a2tDAO = new ENAct2TransportDAO(connection, userProfile);

			ENAct2HumenDAO a2hDAO = new ENAct2HumenDAO(connection, userProfile);
			HumenLogic hLogic = new HumenLogic(connection, userProfile);
			mDaxLogic mdLogic = new mDaxLogic(connection, userProfile);
			ENDepartment2EPRenDAO d2pDAO = new ENDepartment2EPRenDAO(connection, userProfile);

			FINMolDataDAO fmDataDAO = new FINMolDataDAO(connection, userProfile);
			ENDepartmentDAO departmDAO = new ENDepartmentDAO(connection, userProfile);
			FKOSLogic fkOsLogic = new FKOSLogic(finConn, userProfile);
			TKFINWorkTypeDAO fwTpDAO = new TKFINWorkTypeDAO(connection, userProfile);
			ENAct2ProvDAO a2pDAO = new ENAct2ProvDAO(connection, userProfile);
			ENActPostingKindDAO pstKindDAO = new ENActPostingKindDAO(connection, userProfile);
			ENPlanWorkStateDAO planWorkStateDao = new ENPlanWorkStateDAO(connection, userProfile);
			DepartmentLogic departmentLogic = new DepartmentLogic(connection, userProfile);
			
			ENPlanWorkState planWorkState = planWorkStateDao.getObject(act.actTypeRef.code);

			TechCardLogic techCardLogic = new TechCardLogic(connection, userProfile);

			/*
			 * �������� ���������� �� ���� , ��� �������� ����������� ����� �� ��� ��������
			 * ���������
			 */
			BigDecimal chargepercent = hLogic.getChargePercentByDate(FINChargeType.IS_NOT_INVALID, act.dateGen);
			/* �� ����������� ����� � ���� */
			ENAct2HumenFilter a2hFilter = new ENAct2HumenFilter();
			a2hFilter.chargeRef.code = FINChargeType.IS_NOT_INVALID;
			a2hFilter.actRef.code = act.code;
			ENAct2HumenShortList a2hList = a2hDAO.getScrollableFilteredList(a2hFilter, 0, -1);
			if (a2hList.totalCount > 0) {
				chargepercent = a2hList.get(0).chargePercent;
			}

			/*
			 * !!!! ���� ��� ���� = ����������� ������������ � ������ ����� � ������ � ����
			 * ���� ���� �� ������� () ( 100 �� - ������� ����� ��������� 102 �� -
			 * ��������� ����� ��������� 103 �� - ��������� ������������ ��������� 106 ��
			 * - ��������� ��� 111 �� - ��������� ��� � �������(���������) �������� 112 �� -
			 * ������ �������� � ������� ��� ) !!!!! �� ����� !!!! �� �������� �� ��
			 * ���������� ��� ���������� ��
			 */
			ENPlanWorkFilter plFil = new ENPlanWorkFilter();
			plFil.conditionSQL = "  enplanwork.code in ( select p.code from enplanwork p  , enact2enplanwork a2p \n"
					+ " where a2p.actrefcode = " + act.code + " \n" + " and a2p.plancode = p.code  \n"
					+ " and p.typerefcode in ( " + ENPlanWorkType.EZ_PLANED_CHANGE_COUNTER + " , "
					+ ENPlanWorkType.EZ_NOPLANED_CHANGE_COUNTER + " , " + ENPlanWorkType.EZ_NOPLANED_SETUP_COUNTER
					+ " , " + ENPlanWorkType.EZ_SETUP_ZKU + " , " + ENPlanWorkType.EZ_CHANGE_ZKU + " , "
					+ ENPlanWorkType.EZ_CHANGE_COUNTER_IN_ZKU + " )  "
					// SUPP-72333 ������ ��� ���� ���� ������ ���� � ������ �� �������� ���� �������
					// . ����� �� ����� ��� � ����
					+ " and a2p.actrefcode in ( select qq.enactrefcode from scusageinputitemoz2nct qq where qq.enactrefcode = "
					+ act.code + " ) ) ";
			int[] plFilArr = plDao.getFilteredCodeArrayNOSEGR(plFil, 0, -1);
			if (plFilArr.length > 0) {
				return provResult;
			}
			
			Set<Integer> budgetCodes = departmentLogic.getBudgetCodesByAct(act);

			// !!!!!!!!! ���� � �������� ���2humen ���� ��������� �� ���� ������ , ����� ��
			// �����
			ENAct2HumenFilter a2hFIl = new ENAct2HumenFilter();
			a2hFIl.actRef.code = act.code;
			int[] a2hArr = a2hDAO.getFilteredCodeArray(a2hFIl, 0, -1);
			if (a2hArr.length == 0) {
				return provResult;
			}
			
			
           int planWorkType = Integer.MIN_VALUE;
           ENPlanWorkFilter pF = new ENPlanWorkFilter();
           pF.conditionSQL = " enplanwork.code = ( select p.code from enact2enplanwork a2p , enplanwork p \n"
					+ " where a2p.actrefcode = " + act.code + " and a2p.plancode = p.code limit 1  ) " ;
			int[] pA = plDao.getFilteredCodeArrayNOSEGR(pF, 0, -1);
			if (pA.length > 0) {
				ENPlanWork planObj = plDao.getObject(pA[0]);
				planWorkType = planObj.typeRef.code;
			} 
	       
	        
	         

			/***** ������� ����������������� ������� */
			BigDecimal productionExpencesPercent = this.getProductionExpencesPercentByAct(act.code);

			/***************** ����������� ������ �� ������ */
			boolean isInvest = false;
			ENPlanWorkFilter plFilter = new ENPlanWorkFilter();
			plFilter.conditionSQL = " enplanwork.code = ( select p.code from enact2enplanwork a2p , enplanwork p \n"
					+ " where a2p.actrefcode = " + act.code + " and a2p.plancode = p.code  \n"
					+ " and p.typerefcode in (" + ENPlanWorkType.INVEST + "," + ENPlanWorkType.SERVICES_FROM_SIDE_INVEST
					+ ") \n" + " limit 1  ) ";
			int[] plIPArr = plDao.getFilteredCodeArrayNOSEGR(plFilter, 0, -1);
			if (plIPArr.length > 0) {
				isInvest = true;
			}
			/*****************/

			int connectionKind = Integer.MIN_VALUE;
			String connectionKind_KAU = "_";
			boolean isPricon = false;

			connectionKind = this.getConnectionKind(act.code);
			if (connectionKind != Integer.MIN_VALUE) {
				isPricon = true;
			}

			ENServicesObject soObj = soDao.getObjectByENAct(act);
			ENServicesObjectShortList soByTechConditions = soDao.getListByENActThroughTechConditions(act);
			ENGeneralContracts genContract = null;

			boolean isLicensedWork = false;

			if (isPricon) {
				isLicensedWork = true;
			}

			if (soByTechConditions.totalCount > 0) {
				soObj = soDao.getObjectNoSegr(soByTechConditions.get(0).code);
				genContract = genContractDAO.getObject(soByTechConditions.get(0).generalContractRefCode);
				if (!isPricon) {
					isLicensedWork = soLogic.isLicensed(soDao.getObjectNoSegr(soByTechConditions.get(0).code));
				}
			} else if (soObj != null) {
				genContract = genContractDAO.getObject(soObj.generalContractRef.code);
				if (!isPricon) {
					isLicensedWork = soLogic.isLicensed(soObj);
				}
			}

			if (soObj == null && soByTechConditions.totalCount == 0
					&& connectionKind == ENConnectionKind.GENERAL_CONNECTION) {
				ENGeneralContractsShortList gecContrlist = genContractDAO.getListByAct(act);
				if (gecContrlist.totalCount > 0) {
					genContract = genContractDAO.getObject(gecContrlist.get(0).code);
				}
			}

			/******** �������� �������� */

			String finDocCode = "";

			if ( /* isPricon */ genContract != null) {

				// ENGeneralContracts genContract =
				// genContractDAO.getObject(fkOItem.generalContractRef.code);
				finDocCode = genContract.finDocCode;
			}

			// 2019.06.27 ���� ��� ������������� �� ������� �� ��� �������� ����� ��� ���
			// ������������ ( ������� ��� ������� )
			if (connectionKind == ENConnectionKind.STANDART || connectionKind == ENConnectionKind.UNDEFINED
					|| connectionKind == ENConnectionKind.GENERAL_CONNECTION) {
				connectionKind_KAU = "�";
			}

			if (connectionKind == ENConnectionKind.NO_STANDART || connectionKind == ENConnectionKind.READY_MADE) {
				connectionKind_KAU = "�";
			}

			String invNumber = "";
			if (act.element.typeRef.code != ENElementType.TY_BYT && act.element.typeRef.code != ENElementType.TY_PROM) {
				invNumber = elLogic.getElementInvNumber(act.element.code);
			}

			// ���� ��� ���������� � ����������� ���, ����� ��������� ��� ������ energynet
			String userName = getUserNameForFK(act.code);
			
			
			//// �������� �� ��'���� ������в� �� �� �� ������
			String V_Prov_Buffer = "";
			
			if (act.element.typeRef.code == ENElementType.OPERATIVE_OBJECT) {
				
				V_Prov_Buffer = this.getStrProv_OPERATIVE_OBJECT(act , userName 
						,productionExpencesPercent 
						,chargepercent );
			}

			
			// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			// !!!!!! ������������� ������������
			// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			if (act.actTypeRef.code == ENPlanWorkState.RECONSTRUCTION_MODERNIZATION
					/*
					 * SUPP-87098 �������� ���� ��� ���� �������������� � ������ ����� = ������
					 * ��������� � ���� �������� ��� �����
					 */
					|| (act.actTypeRef.code == ENPlanWorkState.DESIGNING && isInvest
							&& (!invNumber.equals("000000") && !invNumber.equals("")))) {

				if (invNumber == null) {
					System.out.print(" movePostingByActWork_Null  act_code= " + act.code + "   act_number = "
							+ act.numberGen + "  invNumber =  " + invNumber);
				}

				if (invNumber.equals("")) {
					System.out.print(" movePostingByActWork_equals\"\"  act_code= " + act.code + "   act_number = "
							+ act.numberGen + "  invNumber =  " + invNumber);
				}

				if (invNumber.length() < 6) {
					System.out.print(" movePostingByActWork_<6  act_code= " + act.code + "   act_number = "
							+ act.numberGen + "  invNumber =  " + invNumber);
				}
				if (invNumber.length() > 6) {
					System.out.print(" movePostingByActWork_>6  act_code= " + act.code + "   act_number = "
							+ act.numberGen + "  invNumber =  " + invNumber);
				}

				if (invNumber.length() == 5) {
					invNumber = "0" + invNumber;
				}

				if (!fkOsLogic.getInvNumberIsExist(invNumber)) {
					throw new SystemException("�� �������� ��'��� � ����������� - " + invNumber);
				}
				;

				/* ��� ���� ��������� */
				String KodPodr = fpLogic.getKodPodr(invNumber);

				// I �������� �� �� ������
				FKTrans2AXTransItemShortList fkTrans2axTransList = this.getDataForProvsSalaryByAct(act.code);

				if (fkTrans2axTransList == null || fkTrans2axTransList.list == null || fkTrans2axTransList.list.isEmpty()
						|| fkTrans2axTransList.get(0).balCeh == null || fkTrans2axTransList.get(0).balCeh.equals("")) {
					this.recalcENAct2HumenCehIdbyAct(act.code); // ���� ������ ��� ������������ � ������� ������ ������
					fkTrans2axTransList = this.getDataForProvsSalaryByAct(act.code);
				}
				// ���� �������� �� ���������� ��� - ����� ������
				if (fkTrans2axTransList == null || fkTrans2axTransList.list == null || fkTrans2axTransList.list.isEmpty()
						|| fkTrans2axTransList.get(0).balCeh == null || fkTrans2axTransList.get(0).balCeh.equals("")) {
					throw new EnergyproSystemException(
							" \n ��� �������� �� ��������� ��� ���� !!! ������� ������� ���������� ���� !!! ");
				}

				String korSch_black = this.getCorrespondingAccountForReconstructionAndModernization(act, invNumber);
				String correspondingCaa = this.getCorrespondingCAAForReconstructionAndModernization(act, invNumber,
						korSch_black);

				BigDecimal zpByAct = new BigDecimal(0);
				for (int i = 0; i < fkTrans2axTransList.totalCount; i++) {
					zpByAct = zpByAct.add(fkTrans2axTransList.get(i).amountCur);
					V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
							+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
									fkTrans2axTransList.get(i).amountCur, // ����� � �������
									fkTrans2axTransList.get(i).balCeh, "6610", // ���������� ����
									"00000000130", // ���������� ���
									KodPodr, // ����������������� ���
									korSch_black, // ����������������� ����
									// ����������������� ���
									/* ���� ���� */
									correspondingCaa, act.numberGen, act.dateGen,
									"��� � " + act.numberGen + ", "
											+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct)
											+ " ������������� � ������������ �� � ���� "
											+ new String(isPricon == true ? "(�������������)" : ""), // ����������
									userName
							);
				}
				// I �������� �� �� �������, �������� ����� �� � ������������ �� ��� ����������
				// � ���
				fkTrans2axTransList = this.getDataForProvsSalaryByActRed(act.code);
				for (int ir = 0; ir < fkTrans2axTransList.totalCount; ir++) {
					V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
							+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
									fkTrans2axTransList.get(ir).amountCur.negate(), // ����� � �������
									fkTrans2axTransList.get(ir).balCeh, "6610", // ���������� ����
									"00000000130", // ���������� ���
									fkTrans2axTransList.get(ir).balCeh, // ����������������� ���
									fkTrans2axTransList.get(ir).balans.substring(0, 4), // ����������������� ���� ���
																						// ���������� ����
									// ����������������� ���
									fkTrans2axTransList.get(ir).balans.substring(4, 15), act.numberGen, act.dateGen,
									"��� � " + act.numberGen + ", "
											+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct)
											+ " ������������� � ������������ �� � ���� "
											+ new String(isPricon == true ? "(�������������)" : ""), // ����������
									userName
							);
				}

				// II �������� ���� �������
				// (�) ����������� �� ����������������� ������� �� ���� (�����������������
				// ������� * 100/122 ) // ������
				if (isPricon) {

					BigDecimal amountCur = zpByAct.multiply(productionExpencesPercent);
					amountCur = amountCur.divide(new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP);
					amountCur = amountCur.multiply(new BigDecimal(100));
					amountCur = amountCur.divide(new BigDecimal(100).add(chargepercent), 6, BigDecimal.ROUND_HALF_UP)
							.setScale(2, BigDecimal.ROUND_HALF_UP);
					V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
							+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
									// ����� � ������� = �� �� ���� * ������� ����������� ������ / 100 * 100 /
									// (100+22 - ��� 22 ��� ��� �� ��������� - ��� �������� ����� �������� �
									// �������� ��������� - �� �������� )
									amountCur, KodPodr, "6610", // ���������� ����
									"10000000130", // ���������� ���
									KodPodr, // ����������������� ���
									korSch_black, // ����������������� ����
									// ����������������� ���
									/* ���� ���� */
									correspondingCaa, act.numberGen, act.dateGen,
									"��� � " + act.numberGen + ", "
											+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct)
											+ " ������������� � ������������ �� � ���� "
											+ new String(isPricon == true ? "(�������������)" : ""), // ����������
									userName
							);
					/*** II (�)_red �������� �� ����������� ����� ������� */
					V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
							+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
									// ����� � ������� = �� �� ���� * ������� ����������� ������ / 100 * 100 /
									// (100+22 - ��� 22 ��� ��� �� ��������� - ��� �������� ����� �������� �
									// �������� ��������� - �� �������� )
									amountCur.negate(), KodPodr, "6610", // ���������� ����
									"10000000130", // ���������� ���
									KodPodr, // ����������������� ���
									"9100", // ����������������� ����
									// ����������������� ���
									/* ���� ���� */
									"00110000001", act.numberGen, act.dateGen,
									"��� � " + act.numberGen + ", "
											+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct)
											+ " ������������� � ������������ �� � ���� "
											+ new String(isPricon == true ? "(�������������)" : ""), // ����������
									userName
							);
					/*
					 * II_2+ black ������ ����� �������� (����������������� ������� 50% �� ����
					 * �������� ����� - ����� (�) // ������ )
					 */
					BigDecimal amountCur_2 = zpByAct.multiply(productionExpencesPercent);
					amountCur_2 = amountCur_2.divide(new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP);
					amountCur_2 = amountCur_2.subtract(amountCur).setScale(2, BigDecimal.ROUND_HALF_UP);
					V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
							+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
									amountCur_2, KodPodr, "6510", // ���������� ����
									"10000000130", // ���������� ���
									KodPodr, // ����������������� ���
									korSch_black, // ����������������� ����
									// ����������������� ���
									/* ���� ���� */
									correspondingCaa, act.numberGen, act.dateGen,
									"��� � " + act.numberGen + ", "
											+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct)
											+ " ������������� � ������������ �� � ���� "
											+ new String(isPricon == true ? "(�������������)" : ""), // ����������
									userName
							);
					/*
					 * II_2+ red ������ ����� �������� (����������������� ������� 50% �� ����
					 * �������� ����� - ����� (�) // ������� )
					 */
					V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
							+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
									amountCur_2.negate(), KodPodr, "6510", // ���������� ����
									"10000000130", // ���������� ���
									KodPodr, // ����������������� ���
									"9100", // ����������������� ����
									// ����������������� ���
									/* ���� ���� */
									"00130000001", act.numberGen, act.dateGen,
									"��� � " + act.numberGen + ", "
											+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct)
											+ " ������������� � ������������ �� � ���� "
											+ new String(isPricon == true ? "(�������������)" : ""), // ����������
									userName
							);

				}

				// III ����� �������� ������ ������ �� ������������'������ �������� ���������
				// ����������� - ������
				fkTrans2axTransList = this.getDataForBlackProvs�hargesumByAct(act.code);
				for (int c = 0; c < fkTrans2axTransList.totalCount; c++) {
					V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
							+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
									fkTrans2axTransList.get(c).amountCur, // ����� � �������
									fkTrans2axTransList.get(c).balCeh,
									fkTrans2axTransList.get(c).chargerefcode == FINChargeType.IS_NOT_INVALID ? "6510"
											: "6512", // ���������� ����
									"00000000130", // ���������� ���
									KodPodr, // ����������������� ���
									korSch_black, // ����������������� ����
									// ����������������� ���
									/* ���� ���� */
									correspondingCaa, act.numberGen, act.dateGen,
									"��� � " + act.numberGen + ", "
											+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct)
											+ " ������������� � ������������ �� � ���� "
											+ new String(isPricon == true ? "(�������������)" : ""), // ����������
									userName
							);
				}

				// ������ ������ �� ������������'������ �������� ��������� ����������� - �������
				// , �������� �� ���� , ��� , ���� ��������� - ������� / �� �������
				fkTrans2axTransList = this.getDataForRedProvs�hargesumByAct(act.code);
				for (int c = 0; c < fkTrans2axTransList.totalCount; c++) {
					V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
							+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
									fkTrans2axTransList.get(c).amountCur.negate(), // ����� � �������
									fkTrans2axTransList.get(c).balCeh,
									fkTrans2axTransList.get(c).chargerefcode == FINChargeType.IS_NOT_INVALID ? "6510"
											: "6512", // ���������� ����
									"00000000130", // ���������� ���
									fkTrans2axTransList.get(c).balCeh, // ����������������� ���
									fkTrans2axTransList.get(c).balans.substring(0, 4), // ����������������� ���� ���
																						// ���������� ����
									// ����������������� ���
									fkTrans2axTransList.get(c).balans.substring(4, 15), act.numberGen, act.dateGen,
									"��� � " + act.numberGen + ", "
											+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct)
											+ " ������������� � ������������ �� � ���� "
											+ new String(isPricon == true ? "(�������������)" : ""), // ����������
									userName
							);
				}

				// IV ����� �������� �����������

				ENAct2TransportFilter a2tFilter = new ENAct2TransportFilter();
				a2tFilter.actRef.code = act.code;
				ENAct2TransportShortList a2tList = a2tDAO.getScrollableFilteredList(a2tFilter, 0, -1);

				// ������ ��� �������� �� ����������� �������
				HashMap<DataForTransAmortizacRed, DataForTransAmortizacRed> transDataAmortizacRed = new HashMap<>();
				// ������ ��� �������� �� ����������� ������
				HashMap<DataForTransAmortizacBlack, DataForTransAmortizacBlack> transDataAmortizacBlack = new HashMap<>();

				for (int tr = 0; tr < a2tList.totalCount; tr++) {

					String cehtransport = fpLogic.getKodPodr(a2tList.get(tr).invNumber);
					// ������� ��� ���� �� �������� mdax
					cehtransport = mdLogic.getMAINORGANIZATIONIDByHRMORGANIZATIONID(cehtransport, act.dateGen);
					String kodSubschB = fpLogic.getKodSubschB(a2tList.get(tr).invNumber);
					String vidOStransp = fpLogic.getG45(a2tList.get(tr).invNumber);
					String kodZatrTransp = fpLogic.getNNNN(a2tList.get(tr).invNumber);

					// !!!!!RED ������� ������ �� ���������� . ���� �������� �� ���� ���������� ,
					// ���� ������ , ��� �� - ��� �������� �������
					DataForTransAmortizacRed rKey;
					rKey = new DataForTransAmortizacRed(cehtransport, kodZatrTransp, vidOStransp,
							"131" + kodSubschB.substring(2, 3), a2tList.get(tr).paysWork);

					if (transDataAmortizacRed.containsValue(rKey)) {
						DataForTransAmortizacRed dValue = (DataForTransAmortizacRed) transDataAmortizacRed.get(rKey);
						rKey.summa = rKey.summa.add(dValue.summa);
						// ������ �� ������ ������ ��� ���� � ������� � ������ ������
						transDataAmortizacRed.remove(rKey);
					}
					transDataAmortizacRed.put(rKey, rKey);

					// !!!!!BLACK ������� ������ �� ���������� . ���� �������� �� ���� ���������� ,
					// ��� �� - ��� �������� ������
					DataForTransAmortizacBlack bKey;
					bKey = new DataForTransAmortizacBlack(cehtransport, vidOStransp, "131" + kodSubschB.substring(2, 3),
							a2tList.get(tr).paysWork);

					if (transDataAmortizacBlack.containsValue(bKey)) {
						DataForTransAmortizacBlack bValue = (DataForTransAmortizacBlack) transDataAmortizacBlack
								.get(bKey);
						bKey.summa = bKey.summa.add(bValue.summa);
						// ������ �� ������ ������ ��� ���� � ������� � ������ ������
						transDataAmortizacBlack.remove(bKey);
					}
					transDataAmortizacBlack.put(bKey, bKey);

				}

				// ������������ �������� �� ����������� �������
				DataForTransAmortizacRed rrKey = null;
				Iterator<DataForTransAmortizacRed> rrIterator = transDataAmortizacRed.keySet().iterator();
				while (rrIterator.hasNext()) {
					rrKey = (DataForTransAmortizacRed) rrIterator.next();
					DataForTransAmortizacRed rrValue = (DataForTransAmortizacRed) transDataAmortizacRed.get(rrKey);
					System.out.println("Value for trans amortizac RED: " + rrValue.ceh + " // " + rrValue.kodZatrat
							+ " // " + rrValue.vidOS + " // " + rrValue.balSch + " // " + rrValue.summa + " // ");
					if (rrValue.summa.doubleValue() > 0) {
						V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
								+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
										rrValue.summa.negate(), // ����� � �������
										rrValue.ceh, rrValue.balSch, // ���������� ���� 131 + ( 3 - � ���� ����� ��
																		// �������� ��- ���������� )
										rrValue.vidOS + "000000000", // ���������� ���
										KodPodr, // ����������������� ���
										rrValue.kodZatrat.substring(0, 4), // ����������������� ���� ��� �������������
																			// ����
										rrValue.kodZatrat.substring(4, 15), // ����������������� ��� ������� �� ���
																			// �������������
										act.numberGen, act.dateGen,
										"��� � " + act.numberGen + ", "
												+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct)
												+ " ������������� � ������������ �� � ���� "
												+ new String(isPricon == true ? "(�������������)" : ""), // ����������
										userName
								);
					}

				}

				// ������������ �������� �� ����������� ������
				DataForTransAmortizacBlack bbKey = null;
				Iterator<DataForTransAmortizacBlack> bbIterator = transDataAmortizacBlack.keySet().iterator();
				while (bbIterator.hasNext()) {
					bbKey = (DataForTransAmortizacBlack) bbIterator.next();
					DataForTransAmortizacBlack bbValue = (DataForTransAmortizacBlack) transDataAmortizacBlack
							.get(bbKey);
					System.out.println("Value for trans amortizac BLACK: " + bbValue.ceh + " // " + bbValue.vidOS
							+ " // " + bbValue.balSch + " // " + bbValue.summa + " // ");
					if (bbValue.summa.doubleValue() > 0) {
						V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
								+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
										bbValue.summa, // ����� � �������
										bbValue.ceh, bbValue.balSch, // ���������� ���� 131 + ( 3 - � ���� ����� ��
																		// �������� ��- ���������� )
										bbValue.vidOS + "000000000", // ���������� ���
										KodPodr, // ����������������� ���
										korSch_black, // ����������������� ����
										// ����������������� ���
										// ���� ����
										correspondingCaa, act.numberGen, act.dateGen,
										"��� � " + act.numberGen + ", "
												+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct)
												+ " ������������� � ������������ �� � ���� "
												+ new String(isPricon == true ? "(�������������)" : ""), // ����������
										userName
								);
					}

				}

			}

			// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			// !!!!!!!!!! TO
			// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			if (act.actTypeRef.code == ENPlanWorkState.TO
					|| act.actTypeRef.code == ENPlanWorkState.COUNTERS_PARAMETRIZATION_FREE_OF_CHARGE) {
				/*
				 * ���� ����. �������� ������� ���������. ������� ���� ��� �� ��� ��
				 * 
				 * 1 ���� 18/05/17 ��� 6610 00000000130 ��� ���� ����������� - 18/05/17 ��� 6510
				 * 00000000130 ��� ���� ����������� - 2 ���� 18/05/17 ��� 6510 00000000130 ���
				 * ���� ����������� + 18/05/17 ��� 6610 00000000130 ��� ���� ����������� +
				 */

				/*
				 * 1 ���� �� ����-���������� ���� � ��� �� ���� ��� ����������� ��� ��� ���� ���
				 * 000 ���� ��������������� �� �� 6610 ����� ����������� �������� - �� �� 6510
				 * ����� ���������� -
				 * 
				 * 2 ���� �� ��� ��� ������� �� ������� ��������� ������ �� ���������������
				 * --���� ��� ���� ��������������� (��������� �������������� �������� ) ��
				 * ����=2320 , �����������=00507500001 (!!! ������������ - ���� ��� ���� =
				 * ��������� �������������� �������� ) --���� ��� ���� ��������������� �� ����=
				 * ��� ������ ������ ����� ���� ������ �� ������������ ������ �� �� �������
				 * ���������� ������ �c�� ��� �� = 01 ��� 02 �� ���= 00503000001 ���� ��� �� ��
				 * ����� 01 � 02 �� ��� =00503300001, -- ���� �������� ��������������� ���������
				 * �� ��� =00506300001.( ��������� ��� ��� ��� ��� �� ���� ���� = ������ ����
				 * ���.����������� � ������ �� ��� ��� �� ���� !! ��������� �������� � ����� ���
				 * ��� �� ���� � ���� � ����� ������� "������� ���") -- ���� � ���������� ������
				 * �� ��� ������ � 1 = 94�� �� ��� = 00505900001 � ������ ���� ��������������
				 * �������� �� + �� 94�� 00505900001 �� 7915 40100000001 + ����� ��������
				 */

				if (act.element.typeRef.code != ENElementType.TY_BYT
						&& act.element.typeRef.code != ENElementType.TY_PROM
						&& act.element.typeRef.code != ENElementType.ROUTE_BYT) {
					invNumber = elLogic.getElementInvNumber(act.element.code);

					if (invNumber != null && invNumber.trim().length() == 6 && !invNumber.equals("000000")
							/*SUPP-102170*/
							&& !invNumber.equals("111111")
							&& !fkOsLogic.getInvNumberIsExist(invNumber)) {
						throw new SystemException("�� �������� ��'��� � ����������� - " + invNumber);
					}
					;
				}

				// ��� ��������� �� ��
				String vidOS = fpLogic.getG45(invNumber);

				/* ��� ������ �� */
				String kod_zatr = fpLogic.getNNNN(invNumber);

				/* ��� ��������� ��������� ��� ������� */
				int kod_ist_prihod = fkOsLogic.getKod_IstPrihod(invNumber);

				String KodPodr = "????";
				String IIII = "2320"; // ��� ���� �� ���������

				// ���� �������� ���� ��� = 000000 � ��� ������ =0910
				boolean isTO_000000 = false;
				FINMolDataFilter fmDataFilter = new FINMolDataFilter();
				fmDataFilter.act.code = act.code;
				fmDataFilter.molTypeRef.code = ENMolType.MASTER;
				// fmDataFilter.finMolCode="0910"; ��������� ��� ���� SUPP-69111
				fmDataFilter.conditionSQL = " FINMolData.finmolcode in ('0910' , '0920')  ";
				int[] fmDataArr = fmDataDAO.getFilteredCodeArray(fmDataFilter, 0, -1);
				if (fmDataArr.length > 0 && invNumber.equals("000000")) {
					isTO_000000 = true;
				}

				// ���� ��� �������� ���������� � ������ ��� ������������ ������ (��������� �
				// �.�.)
				// � ����� ������������ > 6 �������� �� KodPodr(���) � IIII(��� ����) ���������
				// �� ���� �� ������� �������� ������

				boolean isMNMA = false; // ���� ������ ����
				if ((act.element.typeRef.code == ENElementType.EQUIPMENT_OBJECTS
						|| act.element.typeRef.code == ENElementType.EQUIPMENT_REPAIR_OBJECTS
						|| act.element.typeRef.code == ENElementType.SDTU
						|| act.element.typeRef.code == ENElementType.RZA
						|| act.element.typeRef.code == ENElementType.BUILDER
						|| act.element.typeRef.code == ENElementType.SIT
						|| act.element.typeRef.code == ENElementType.ISOLATION
						|| act.element.typeRef.code == ENElementType.PREPRODUCTION_OBJECT
						|| act.element.typeRef.code == ENElementType.EB_OBJECTS

				) && invNumber.length() > 6) {

					// ������� ��� �� ���������� � �������������
					ENAct2Humen a2hObj = getBalansWithMainCeh(act, finConn);
					String balans = a2hObj.balans;
					String cehId = a2hObj.cehId;

					KodPodr = cehId;
					IIII = balans.substring(0, 4);
					isMNMA = true;

				} else {
					/* ��� ���� ��������� */
					KodPodr = fpLogic.getKodPodr(invNumber);

					if (KodPodr.equalsIgnoreCase("????")) {
						/* ��� ���� ��������� -- �� ������� � ���� */
						ENDepartment2EPRenFilter d2pFilter = new ENDepartment2EPRenFilter();
						d2pFilter.conditionSQL = " ENDepartment2EPRen.code in ( " + " select d2r.code  "
								+ " from enact a  , enelementdata eld , enelement el , endepartment2epren d2r "
								+ " where a.code = " + act.code + " and a.elementcode =eld.ecode "
								+ " and eld.ecode=el.code " + " and el.renrefcode=d2r.renrefcode " + " limit 1 ) ";

						// String KodPodr = fpLogic.getKodPodr(invNumber);
						ENDepartment2EPRenShortList d2pList = d2pDAO.getScrollableFilteredList(d2pFilter, 0, -1);
						if (d2pList.totalCount > 0)
							KodPodr = d2pList.get(0).finServicesCode;

					}

					// ��������� ��� ���� �� �������� mdax
					KodPodr = mdLogic.getMAINORGANIZATIONIDByHRMORGANIZATIONID(KodPodr, act.dateGen);
				}

				/// 20.09.2017 - �������� ���������� .. ���� ��� ������� ��-��� ��� ��-���� �
				/// ��� = 015 �� ������ �� 052 ��� ��� ������� ��������� �
				/// ���� � �� � �����

				if (KodPodr.equals("051") && (act.element.typeRef.code == ENElementType.TY_BYT
						|| act.element.typeRef.code == ENElementType.TY_PROM)
				/* �� ���� ��� ������� ��� */
						&& !isZKUMounting) {
					KodPodr = "052";
				}

				// 13.08.2018 �������� �.�. ������� ��� �� ��������� ��� ������ ���� 051
				// �������������

				/*
				 * !!!!26.10.2018 ���� �� ������� � ��������� ������ ��� ��� �� �����
				 * ������������� ������� 51 ��� if(isZKUMounting && KodPodr.equals("052")) {
				 * KodPodr = "051"; }
				 */

				if (isZKUMounting) {
					// 08.04.2019 ���� ��� ��������� ���, �� ������������� ����� ���� ��� �������
					// ��� (��������� �.�.)
					if (act.finMolCode == null || act.finMolCode.length() == 0) {
						throw new SystemException(
								"��������� ��������� ������� ��� �������� �������� �� ��������� ���");
					}
					KodPodr = String.format("0%s", act.finMolCode.substring(0, 2));
				}

				String BBBBBBBBBBB_nachisl = "";
				String BBBBBBBBBBB_otchisl = "";
				if (act.actTypeRef.code == ENPlanWorkState.COUNTERS_PARAMETRIZATION_FREE_OF_CHARGE) {
					BBBBBBBBBBB_nachisl = "00507800001"; // "00507500301";
					BBBBBBBBBBB_otchisl = "00507800001"; // "00507500201";
				} /* �� ��������� */
				else if (act.actTypeRef.code == ENPlanWorkState.TO && (act.element.typeRef.code == ENElementType.TY_BYT
						|| act.element.typeRef.code == ENElementType.TY_PROM
						|| act.element.typeRef.code == ENElementType.ROUTE_BYT)) {

					BBBBBBBBBBB_nachisl = "00503300001"; // "00506300301";
					BBBBBBBBBBB_otchisl = "00503300001"; // "00506300201";
				} // ���� �� �� �� �������������� � �� �� ��������� �� ����= ��� ������ ������
					// ����� ���� ������ �� ������������ ������ �� �� ������� ���������� ������
				else if (act.actTypeRef.code == ENPlanWorkState.TO && (act.element.typeRef.code != ENElementType.TY_BYT
						&& act.element.typeRef.code != ENElementType.TY_PROM
						&& act.element.typeRef.code != ENElementType.ROUTE_BYT)) {

					if (!isMNMA) {
						IIII = kod_zatr.substring(0, 4);
					}

					if (vidOS.substring(0, 2).equals("01") || vidOS.substring(0, 2).equals("02")) {
						BBBBBBBBBBB_nachisl = "00503300001"; // "00503000301";
						BBBBBBBBBBB_otchisl = "00503300001"; // "00503000201";
					}
					// 19.09.2017 - ���. ���������� ������� . ���� ��� �� = 22 � ���� ������ = 2320
					// �� ��� ������ = 00506600001 ������ 00503300001
					else if (kod_ist_prihod == 22
							&& (IIII.substring(0, 2).equals("23") || IIII.substring(0, 2).equals("91"))) {
						BBBBBBBBBBB_nachisl = "00503300001";
						BBBBBBBBBBB_otchisl = "00503300001";
					} else {
						BBBBBBBBBBB_nachisl = "00503300001"; // "00503300301";
						BBBBBBBBBBB_otchisl = "00503300001"; // "00503300201"
					}

				}

				if (isTO_000000) {
					IIII = "9100";
				}
				/*SUPP-102170*/
				if((invNumber.equals("111111") || invNumber.equals("000000")) 
						&& budgetCodes != null && budgetCodes.contains(ENConsts.ENBUDGET_IZOLATION)) {
					IIII = "2321";
				}

				if (isZKUMounting) {
					IIII = "1531";
					BBBBBBBBBBB_nachisl = BBBBBBBBBBB_otchisl = new String(isPricon ? "25000000000" : "24000000000");
				}

				String infoDescript = "";
				if (act.actTypeRef.code == ENPlanWorkState.TO)
					infoDescript = " � ��������� �������������� ";
				if (act.actTypeRef.code == ENPlanWorkState.COUNTERS_PARAMETRIZATION_FREE_OF_CHARGE)
					infoDescript = " � �������������� ��������� ";

				/*
				 * +++++++++++++++++++ �������� ���� �������������� , �� ������ � enact2humen
				 * ������ ���� ��������� � ������� �����-�����
				 */
				if (act.actTypeRef.code == ENPlanWorkState.COUNTERS_PARAMETRIZATION_FREE_OF_CHARGE
						|| act.actTypeRef.code == ENPlanWorkState.COUNTERS_PARAMETRIZATION) {
					ENAct2HumenFilter a2hff = new ENAct2HumenFilter();
					a2hff.actRef.code = act.code;
					ENAct2HumenShortList a2hffList = a2hDAO.getScrollableFilteredList(a2hff, 0, -1);
					for (int ff = 0; ff < a2hffList.totalCount; ff++) {
						if (a2hffList.get(ff).planRefCode == Integer.MIN_VALUE) {
							throw new EnergyproSystemException(
									" \n ������ ����������� �� ��������. ������� ������� ���������� ���� !!! ");
						}
					}
				}

				// I �������� �� �� ������
				FKTrans2AXTransItemShortList fkTrans2axTransList = this.getDataForProvsSalaryByAct(act.code);

				if (fkTrans2axTransList == null || fkTrans2axTransList.list == null || fkTrans2axTransList.list.isEmpty()
						|| fkTrans2axTransList.get(0).balCeh == null || fkTrans2axTransList.get(0).balCeh.equals("")) {
					this.recalcENAct2HumenCehIdbyAct(act.code); // ���� ������ ��� ������������ � ������� ������ ������
					fkTrans2axTransList = this.getDataForProvsSalaryByAct(act.code);
				}
				// ���� �������� �� ���������� ��� - ����� ������
				if (fkTrans2axTransList == null || fkTrans2axTransList.list == null || fkTrans2axTransList.list.isEmpty()
						|| fkTrans2axTransList.get(0).balCeh == null || fkTrans2axTransList.get(0).balCeh.equals("")) {
					throw new EnergyproSystemException(
							" \n ��� �������� �� ��������� ��� ���� !!! ������� ������� ���������� ���� !!! ");
				}

				BigDecimal zpByAct = new BigDecimal(0);
				for (int i = 0; i < fkTrans2axTransList.totalCount; i++) {
					zpByAct = zpByAct.add(fkTrans2axTransList.get(i).amountCur);
					V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
							+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
									fkTrans2axTransList.get(i).amountCur, // ����� � �������
									fkTrans2axTransList.get(i).balCeh, "6610", // ���������� ����
									"00000000130", // ���������� ���
									new String((IIII.substring(0, 3).equals("232") || IIII.equals("9100")
											|| IIII.equals("9200")) ? "000" : KodPodr), // ����.��� ( ��� ���� ��� 000
																						// ���� ������������� ��� )
									IIII, // ����.����
									// ����.���
									new String((IIII.substring(0, 3).equals("232") || IIII.equals("9100")
											|| IIII.equals("9200")) ? "1"
													+ BBBBBBBBBBB_nachisl.substring(1, BBBBBBBBBBB_nachisl.length())
													: BBBBBBBBBBB_nachisl),
									act.numberGen, act.dateGen,
									"TO_net ��� � " + act.numberGen + ", "
											+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct) + infoDescript
											+ new String(isPricon == true ? "(�������������)" : ""), // ����������
									userName
							);
				}
				// I �������� �� �� �������, �������� ����� �� � ������������ �� ��� ����������
				// � ���
				fkTrans2axTransList = this.getDataForProvsSalaryByActRed(act.code);
				for (int ir = 0; ir < fkTrans2axTransList.totalCount; ir++) {
					V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
							+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
									fkTrans2axTransList.get(ir).amountCur.negate(), // ����� � �������
									fkTrans2axTransList.get(ir).balCeh, "6610", // ���������� ����
									"00000000130", // ���������� ���
									new String((fkTrans2axTransList.get(ir).balans.substring(0, 4).substring(0, 3)
											.equals("232")
											|| fkTrans2axTransList.get(ir).balans.substring(0, 4).equals("9100")
											|| fkTrans2axTransList.get(ir).balans.substring(0, 4).equals("9200"))
													? "000"
													: fkTrans2axTransList.get(ir).balCeh), // ����������������� ���
									fkTrans2axTransList.get(ir).balans.substring(0, 4), // ����������������� ���� ���
																						// ���������� ����
									// ����������������� ���
									new String((fkTrans2axTransList.get(ir).balans.substring(0, 4).substring(0, 3)
											.equals("232")
											|| fkTrans2axTransList.get(ir).balans.substring(0, 4).equals("9100")
											|| fkTrans2axTransList.get(ir).balans.substring(0, 4).equals("9200")) ? "1"
													+ fkTrans2axTransList.get(ir).balans.substring(4, 15).substring(1,
															fkTrans2axTransList.get(ir).balans.substring(4, 15)
																	.length())
													: fkTrans2axTransList.get(ir).balans.substring(4, 15))

									, act.numberGen, act.dateGen,
									"TO_net ��� � " + act.numberGen + ", "
											+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct) + infoDescript
											+ new String(isPricon == true ? "(�������������)" : ""), // ����������
									userName
							);
				}

				// II ����� �������� ������ ������ �� ������������'������ �������� ���������
				// ����������� - ������
				fkTrans2axTransList = this.getDataForBlackProvs�hargesumByAct(act.code);
				for (int c = 0; c < fkTrans2axTransList.totalCount; c++) {
					V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
							+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
									fkTrans2axTransList.get(c).amountCur, // ����� � �������
									fkTrans2axTransList.get(c).balCeh,
									fkTrans2axTransList.get(c).chargerefcode == FINChargeType.IS_NOT_INVALID ? "6510"
											: "6512", // ���������� ����
									"00000000130", // ���������� ���
									new String((IIII.substring(0, 3).equals("232") || IIII.equals("9100")
											|| IIII.equals("9200")) ? "000" : KodPodr), // ����������������� ���
									IIII, // ����������������� ����
									new String((IIII.substring(0, 3).equals("232") || IIII.equals("9100")
											|| IIII.equals("9200")) ? "2"
													+ BBBBBBBBBBB_otchisl.substring(1, BBBBBBBBBBB_otchisl.length())
													: BBBBBBBBBBB_otchisl), // ����������������� ���
									act.numberGen, act.dateGen,
									"TO_net ��� � " + act.numberGen + ", "
											+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct) + infoDescript
											+ new String(isPricon == true ? "(�������������)" : ""), // ����������
									userName
							);
				}

				// ������ ������ �� ������������'������ �������� ��������� ����������� - �������
				// , �������� �� ���� , ��� , ���� ��������� - ������� / �� �������
				fkTrans2axTransList = this.getDataForRedProvs�hargesumByAct(act.code);
				for (int c = 0; c < fkTrans2axTransList.totalCount; c++) {
					V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
							+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
									fkTrans2axTransList.get(c).amountCur.negate(), // ����� � �������
									fkTrans2axTransList.get(c).balCeh,
									fkTrans2axTransList.get(c).chargerefcode == FINChargeType.IS_NOT_INVALID ? "6510"
											: "6512", // ���������� ����
									"00000000130", // ���������� ���
									new String((fkTrans2axTransList.get(c).balans.substring(0, 4).substring(0, 3)
											.equals("232")
											|| fkTrans2axTransList.get(c).balans.substring(0, 4).equals("9100")
											|| fkTrans2axTransList.get(c).balans.substring(0, 4).equals("9200")) ? "000"
													: fkTrans2axTransList.get(c).balCeh), // ����������������� ���
									fkTrans2axTransList.get(c).balans.substring(0, 4), // ����������������� ���� ���
																						// ���������� ����
									// ����������������� ���
									new String((fkTrans2axTransList.get(c).balans.substring(0, 4).substring(0, 3)
											.equals("232")
											|| fkTrans2axTransList.get(c).balans.substring(0, 4).equals("9100")
											|| fkTrans2axTransList.get(c).balans.substring(0, 4).equals("9200")) ? "2"
													+ fkTrans2axTransList.get(c).balans.substring(4, 15).substring(1,
															fkTrans2axTransList.get(c).balans.substring(4, 15).length())
													: fkTrans2axTransList.get(c).balans.substring(4, 15)),
									act.numberGen, act.dateGen,
									"TO_net ��� � " + act.numberGen + ", "
											+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct) + infoDescript
											+ new String(isPricon == true ? "(�������������)" : ""), // ����������
									userName
							);
				}

			}
			// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!! �������� ������ PROV SERVICES
			// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			// !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
			if (act.element.typeRef.code == ENElementType.SERVICES_OBJECT
					|| (act.actTypeRef.code == ENPlanWorkState.DESIGNING && !isInvest)
					|| (act.actTypeRef.code == ENPlanWorkState.WORK_IN_OUT && planWorkType == ENPlanWorkType.WORK_IN_OUT /*zzz777*/ ) ) /*
					 * SUPP-87098 �������� ���� ��� ���� �������������� ��� �������� �� ��������
					 * ����� �� ��� ��� ������������ � ���� �� �������
					 */
			{
				// �������� ��������� �� ��� � "�������� �� ������������ � ��������������� �/�" 
				ENServicesObject servicesObjectSupplier = soLogic.getServicesObjectForSupplierByActCode(act.code, false);
				if (servicesObjectSupplier == null 
						|| servicesObjectSupplier.contractKindRef.code != ENServicesContractKind.SUPPLIER_CONTRACT) {
					servicesObjectSupplier = null;
				}
				
				System.out.print("\n \n \n  start generate prov services by act  " + act.numberGen);
				// !!!!!!!!!!!!!!!!! ���� ������ �� ������� ������� �� �������
				if ( (soObj == null && servicesObjectSupplier == null) || 
						(soObj != null && soObj.contractKindRef.code != ENServicesContractKind.SERVICES
						&& soObj != null &&  soObj.contractKindRef.code != ENServicesContractKind.PROJECT
						&& soObj != null &&  soObj.contractKindRef.code != ENServicesContractKind.SUPPLIER_CONTRACT ) 
						||
						 (  servicesObjectSupplier != null && servicesObjectSupplier.contractKindRef.code != ENServicesContractKind.SUPPLIER_CONTRACT) ) {
					return provResult;
				}

				String KodPodr = "000";
				String infoDescript = "������� �� �������";

				String X = fwTpDAO.getFinCodeByActCode(act.code, !isPricon);

				ENActPostingKindFilter pstFil = new ENActPostingKindFilter();
				int[] pstKindArr = pstKindDAO.getFilteredCodeArray(pstFil, 0, -1);
				List<Integer> pstKindList = new ArrayList<>();
				for (int ar : pstKindArr) {
					pstKindList.add(ar);
				}

				// ���������� ������ ��� �������� �� ������� �� �������
				ENAct2ProvFilter a2pFil = new ENAct2ProvFilter();
				a2pFil.actRef.code = act.code;
				ENAct2ProvShortList a2pList = a2pDAO.getScrollableFilteredList(a2pFil, 0, -1);

				boolean isIncomeAct;
				if (a2pList.totalCount > 0) {

					if (!/* Arrays.asList(pstKindArr) */pstKindList.contains(a2pList.get(0).actPostingKindRefCode)) {
						throw new EnergyproSystemException(
								" \n �� �������� ������ �������� ��� ���� ��������� ����!!! (���/�������� �� ����) ");
					}

					if (a2pList.get(0).isIncomeAct == 1) {
						isIncomeAct = true;
					} else if (a2pList.get(0).isIncomeAct == 0 || a2pList.get(0).isIncomeAct == Integer.MIN_VALUE) {
						isIncomeAct = false;
					} else
						throw new EnergyproSystemException(
								" \n �� ������� ������ �������� ������������ ���� ��� ���� ��������� ���� !!! (���/�������� �� ����) ");

					infoDescript = a2pList.get(0).actPostingKindRefName;

				} else {
					throw new EnergyproSystemException(
							" \n �� �������� ������ ��� ��������, ��� ������ �������� ������������ ���� ��� ���� ��������� ����!!! (���/�������� �� ����) ");
				}

				// ��� ���������� �����������
				int tkCalcKindByActCode = techCardLogic.getTKCalcKindByActCode(act.code);
				if (tkCalcKindByActCode == Integer.MIN_VALUE) {

					if (!isPricon) {
						throw new EnergyproSystemException(" \n �� �������� ��� ���������� ����������� !!! ");
					}
				}

				///// �� �� ���� ���� �� ������������� � ����� ������� ��� �� ��������� ���
				///// ������ �� �������� � �� ���������
				boolean isWithDelivery = false;
				if (isPricon || (tkCalcKindByActCode == TKCalcKind.OLD || tkCalcKindByActCode == TKCalcKind.NEW2)) {
					isWithDelivery = true;
				}

				// ��������� ������ ��� �� ���������� ��� � enact2humen, ������ ���� �� ����
				// ���� ������������� ��� �� �� �������
				ENAct2HumenFilter a2hFilt = new ENAct2HumenFilter();
				a2hFilt.actRef.code = act.code;
				ENAct2HumenShortList a2hll = a2hDAO.getScrollableFilteredList(a2hFilt, 0, -1);
				for (int ll = 0; ll < a2hll.totalCount; ll++) {
					if (a2hll.get(ll).cehId.equalsIgnoreCase("") || a2hll.get(ll).cehId == null) {
						this.recalcENAct2HumenCehIdbyAct(act.code); // ���� ������ ��� ������������ � ������� ������
																	// ������
						this.recalcenplanworkitem2humenbyAct(act.code);
						break;
					}
				}

				FKTrans2AXTransItemShortList fkTrans2axTransList = null;
				// ************** I �������� �� �� ������� ****/
				if (act.element.typeRef.code != ENElementType.SERVICES_OBJECT  
						 && ( servicesObjectSupplier== null  )  ) {
					fkTrans2axTransList = this.getDataForProvsSalaryByActRed(act.code);
				} else {
					fkTrans2axTransList = this.getDataForProvsSalaryByActServicesRed(act.code, tkCalcKindByActCode,
							isWithDelivery);
				}

				if (fkTrans2axTransList.get(0).balCeh == null || fkTrans2axTransList.get(0).balCeh.equals("")) {
					throw new EnergyproSystemException(
							" \n ��� �������� �� ��������� ��� ���� !!! ������� ������� ���������� ���� !!! ");
				}

				BigDecimal zpByAct = new BigDecimal(0);
				for (int i = 0; i < fkTrans2axTransList.totalCount; i++) {
					zpByAct = zpByAct.add(fkTrans2axTransList.get(i).amountCur);

					// �� ������ ��� ���� �������� ���������
					V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
							+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
									fkTrans2axTransList.get(i).amountCur.negate(), // ����� � �������� �������
									/* fkTrans2axTransList.get(i).balCeh SUPP-98754 ���� 000 */ "000", "6610", // ����������
																												// ����
									"00000000130", // ���������� ���
									fkTrans2axTransList.get(i).balCeh,
									fkTrans2axTransList.get(i).balans.substring(0, 4), // ���� ���� �� ��� ����������

									new String((fkTrans2axTransList.get(i).balans.substring(0, 4).substring(0, 3)
											.equals("232")
											|| fkTrans2axTransList.get(i).balans.substring(0, 4).equals("9100")
											|| fkTrans2axTransList.get(i).balans.substring(0, 4).equals("9200")) ? "1"
													+ fkTrans2axTransList.get(i).balans.substring(5, 15)
													: fkTrans2axTransList.get(i).balans.substring(4, 15)), // ����.���
																											// �� ���
																											// ����������

									// SUPP-98754 fkTrans2axTransList.get(i).balans.substring(4, 15) , // ����.���
									// �� ��� ����������
									act.numberGen, act.dateGen, "servise" + act.numberGen + ", "
											+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct) + infoDescript, // ����������
									userName
							);

				}

				// START************** �� �������� �� �� ������ ****/
				fkTrans2axTransList = null;
				if (act.element.typeRef.code != ENElementType.SERVICES_OBJECT 
						 && ( servicesObjectSupplier== null  ) ) {
					fkTrans2axTransList = this.getDataForProvsSalaryByAct(act.code);
				} else {
					fkTrans2axTransList = this.getDataForProvsSalaryByActServices(act.code, tkCalcKindByActCode,
							isWithDelivery);
				}

				if (fkTrans2axTransList == null || fkTrans2axTransList.list == null || fkTrans2axTransList.list.isEmpty()
						|| fkTrans2axTransList.get(0).balCeh == null || fkTrans2axTransList.get(0).balCeh.equals("")) {
					throw new EnergyproSystemException(
							" \n ��� �������� �� ��������� ��� ���� !!! ������� ������� ���������� ���� !!! ");
				}
				
				String correspondingAccount = this.getCorrespondingAccountForServices(a2pList.get(0).actPostingKindRefCode, isIncomeAct);
				String correspondingCAA = this.getCorrespondingCAAForServices(a2pList.get(0).actPostingKindRefCode
						, X, connectionKind_KAU, finDocCode, isIncomeAct);
				
				
				// ����������
				// ��� ���������  �������������� ��������� ������ ���� ��������  ��������� ������� ���������� ��� ���������� � ����� ������ 42117825 ��� ����ʻ.
				// ��� ��������� ����, �� ����������� �� ��������� � ����� ����������� ������ ������� ������������� ����� �, ���������� �� ����������� ��  �������� ���� ����� �� ����� ����������� ��������� �TechCard�
              	if (servicesObjectSupplier != null) {
					if (servicesObjectSupplier.contragentOkpo.equals("42117825")) {
						correspondingCAA = new StringBuilder(correspondingCAA).replace(0, 1, "�").toString();
						 if (X.length()!= 0 ) {
							 X = new StringBuilder(X).replace(0, 1, "�").toString();	 
						 }
						
					} 
				}
              	
              	 
				
				for(FKTrans2AXTransItemShort fkTrans2axTrans : fkTrans2axTransList.list) {
					V_Prov_Buffer = (V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
							+ fpLogic.generateQueryTransSHABLON(act.dateGen
									, fkTrans2axTrans.amountCur
									, "000"
									, "6610"
									, "00000000130"
									, ((a2pList.get(0).actPostingKindRefCode == ENActPostingKind.SERVICES_CONTRACT && !isIncomeAct)
											|| (a2pList.get(0).actPostingKindRefCode == ENActPostingKind.SERVICES_CONTRACT_PROJECT)
											|| (a2pList.get(0).actPostingKindRefCode == ENActPostingKind.SERVICES_CONTRACT_PROJECT_PRICONNECTION))
										? "000" : KodPodr
									, correspondingAccount
									, correspondingCAA
									, act.numberGen, act.dateGen, "servise" + act.numberGen + " , "
											+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct) + infoDescript
									, userName
								);
					// �������� -- ��������  �������� �� ���� 
					if( (a2pList.get(0).actPostingKindRefCode == ENActPostingKind.SERVICES_CONTRACT && isIncomeAct) 
							 || (a2pList.get(0).actPostingKindRefCode == ENActPostingKind.SERVICES_SUPPLIER_CONTRACT ) ) {
						V_Prov_Buffer = (V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
								+ fpLogic.generateQueryTransSHABLON(act.dateGen
										, fkTrans2axTrans.amountCur
										, KodPodr
										, "9033"
										, X + "666200001"
										, KodPodr
										, "7913"
										, "11000000001" 
										, act.numberGen, act.dateGen, "servise" + act.numberGen + ", "
													+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct)
												+ infoDescript
										, userName
									);
						}
					}
				// END************** �� �������� �� �� ������
				// *******************************************************************************************************/

				// START************** ��I �������� �� ��� ������� ****/

				fkTrans2axTransList = null;
				if (act.element.typeRef.code != ENElementType.SERVICES_OBJECT && servicesObjectSupplier == null 
						 ) {
					fkTrans2axTransList = this.getDataForProvsESVByActRed(act.code);
				} else 
				if ( servicesObjectSupplier != null  ) {
					fkTrans2axTransList = this.getDataForProvsESVByActServicesSupplierContract(act.code, tkCalcKindByActCode,
							isWithDelivery,true);
				} 
				else {
					fkTrans2axTransList = this.getDataForProvsESVByActServicesRed(act.code, tkCalcKindByActCode,
							isWithDelivery);
				}

				if (fkTrans2axTransList == null || fkTrans2axTransList.list == null || fkTrans2axTransList.list.isEmpty()
						|| fkTrans2axTransList.get(0).balCeh == null || fkTrans2axTransList.get(0).balCeh.equals("")) {
					throw new EnergyproSystemException(
							" \n ��� �������� �� ��������� ��� ���� !!! ������� ������� ���������� ���� !!! ");
				}

				for (int i = 0; i < fkTrans2axTransList.totalCount; i++) {

					V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
							+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
									fkTrans2axTransList.get(i).amountCur.negate(), // ����� � ������� �������
									/* fkTrans2axTransList.get(i).balCeh SUPP-98754 ���� 000 */ "000", "6510", // ����������
																												// ����
									"00000000130", // ���������� ���
									fkTrans2axTransList.get(i).balCeh,
									fkTrans2axTransList.get(i).balans.substring(0, 4), // ���� ���� �� ��� ����������
									new String((fkTrans2axTransList.get(i).balans.substring(0, 4).substring(0, 3)
											.equals("232")
											|| fkTrans2axTransList.get(i).balans.substring(0, 4).equals("9100")
											|| fkTrans2axTransList.get(i).balans.substring(0, 4).equals("9200")) ? "2"
													+ fkTrans2axTransList.get(i).balans.substring(4, 15).substring(1,
															fkTrans2axTransList.get(i).balans.substring(4, 15).length())
													: fkTrans2axTransList.get(i).balans.substring(4, 15)),
									// fkTrans2axTransList.get(i).balans.substring(4, 15) , // ����.��� �� ���
									// ����������
									act.numberGen, act.dateGen, "servise" + act.numberGen + ", "
											+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct) + infoDescript, // ����������
									userName
							);

				}
				// END************** ��I �������� �� ��� ������� ****/

				// START************** �V �������� �� ��� ������ ****/
				fkTrans2axTransList = null;
				if (act.element.typeRef.code != ENElementType.SERVICES_OBJECT && servicesObjectSupplier == null  ) {
					fkTrans2axTransList = this.getDataForProvsESVByAct(act.code);
				} else 
				if ( servicesObjectSupplier != null  ) {
					fkTrans2axTransList = this.getDataForProvsESVByActServicesSupplierContract(act.code, tkCalcKindByActCode,
							isWithDelivery,false);
				}
				else {
					fkTrans2axTransList = this.getDataForProvsESVByActServices(act.code, tkCalcKindByActCode,
							isWithDelivery);
				}

				if (fkTrans2axTransList == null || fkTrans2axTransList.list == null || fkTrans2axTransList.list.isEmpty()
						|| fkTrans2axTransList.get(0).balCeh == null || fkTrans2axTransList.get(0).balCeh.equals("")) {
					throw new EnergyproSystemException(
							" \n ��� �������� �� ��������� ��� ���� !!! ������� ������� ���������� ���� !!! ");
				}
				
				for(FKTrans2AXTransItemShort fkTrans2axTrans : fkTrans2axTransList.list) {
					V_Prov_Buffer = (V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
							+ fpLogic.generateQueryTransSHABLON(act.dateGen
									, fkTrans2axTrans.amountCur
									, "000"
									, "6510"
									, "00000000130"
									, ((a2pList.get(0).actPostingKindRefCode == ENActPostingKind.SERVICES_CONTRACT && !isIncomeAct)
											|| (a2pList.get(0).actPostingKindRefCode == ENActPostingKind.SERVICES_CONTRACT_PROJECT)
											|| (a2pList.get(0).actPostingKindRefCode == ENActPostingKind.SERVICES_CONTRACT_PROJECT_PRICONNECTION))
										? "000" : KodPodr
									, correspondingAccount
									, (a2pList.get(0).actPostingKindRefCode < ENActPostingKind.SERVICES_CONTRACT_INSTALL_COUNTER 
										||
									   a2pList.get(0).actPostingKindRefCode == ENActPostingKind.SERVICES_SUPPLIER_CONTRACT 	
							 				? new StringBuilder(correspondingCAA).replace(5, 6, "3").toString()
											: new StringBuilder(correspondingCAA).replace(0, 1, "2").toString())
									, act.numberGen, act.dateGen, "servise" + act.numberGen + " , "
											+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct) + infoDescript
									, userName
								);
					// �������� -- ��������  ���  �� ����  
					if( (a2pList.get(0).actPostingKindRefCode == ENActPostingKind.SERVICES_CONTRACT && isIncomeAct ) 
							|| a2pList.get(0).actPostingKindRefCode == ENActPostingKind.SERVICES_SUPPLIER_CONTRACT ) {
						V_Prov_Buffer = (V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
								+ fpLogic.generateQueryTransSHABLON(act.dateGen
										, fkTrans2axTrans.amountCur
										, KodPodr
										, "9033"
										, X + "666300001"
										, KodPodr
										, "7913"
										, "11000000001"
										, act.numberGen, act.dateGen
										, "servise" + act.numberGen + ", "
													+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct)
												+ infoDescript
										, userName
									);
						}
					}
				// END************** �V �������� �� ��� ������ ****/

				// START************** V �������� �� �� � �������������������� � ��� �
				// �������������������� ****/
				// if (isPricon == true || tkCalcKindByActCode == TKCalcKind.OLD
				// ){isWithDelivery =true;}

				fkTrans2axTransList = null;
				if ((act.element.typeRef.code != ENElementType.SERVICES_OBJECT) 
						&& ( servicesObjectSupplier== null  ) ) {
					fkTrans2axTransList = this.getDataForProvsTotalExpencByAct(act.code);
				} else {
					fkTrans2axTransList = this.getDataForProvsTotalExpencByActServices(act.code, tkCalcKindByActCode,
							isWithDelivery);
				}

				if (fkTrans2axTransList == null || fkTrans2axTransList.list == null || fkTrans2axTransList.list.isEmpty()) {
					throw new SystemException("\n\n������� ��������� ����� �� �������! �������� ���������� ��������� ����!");
				}
				BigDecimal sumTotalExpenc = fkTrans2axTransList.get(0).amountCur;

				BigDecimal sumZPInTotalExpenc = new BigDecimal(((sumTotalExpenc.doubleValue() * 100) / 122)).setScale(2,
						BigDecimal.ROUND_HALF_UP);
				BigDecimal sumESVInTotalExpenc = sumTotalExpenc.subtract(sumZPInTotalExpenc).setScale(2,
						BigDecimal.ROUND_HALF_UP);

				// ���� ����������� ������ �� �������������� �� �������������������� �� �������
				boolean isMakeProvsTotalExpenc = true;
				if (soObj != null) {
					isMakeProvsTotalExpenc = !(soLogic.checkParameterizationCounterServices(soObj.element.code)
							&& soObj.isNoPay == ENConsts.ENSERVICES_OBJECT_ISNOPAY);
				}

				if (isMakeProvsTotalExpenc) {
					
					for(FKTrans2AXTransItemShort fkTrans2axTrans : fkTrans2axTransList.list) {
						
						// ������ �� � �������������������� //// ��� ���� �������� ����������
						V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
								+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
										sumZPInTotalExpenc.negate(), // ����� � �������
										/* KodPodrSUPP-98754 ���� 000 */ "000", "6610", // ���������� ����
										"10000000130", // ���������� ���
										KodPodr, // ����.��� ( ��� ���� ��� 000 ���� ������������� ��� )
										"9100", // ���� ����
										"10110000001", // ����.���
										act.numberGen, act.dateGen, "servise" + act.numberGen + ", "
												+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct) + infoDescript, // ����������
										userName
								);
						// ������ ��� � �������������������� //// ��� ���� �������� ����������
						V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
								+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
										sumESVInTotalExpenc.negate(), // ����� � �������
										/* KodPodrSUPP-98754 ���� 000 */ "000", "6510", // ���������� ����
										"10000000130", // ���������� ���
										KodPodr, // ����.��� ( ��� ���� ��� 000 ���� ������������� ��� )
										"9100", // ���� ����
										"20130000001", // ����.���
										act.numberGen, act.dateGen, "servise" + act.numberGen + ", "
												+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct) + infoDescript, // ����������
										userName
								);
						
						V_Prov_Buffer = (V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
								+ fpLogic.generateQueryTransSHABLON(act.dateGen
										, sumZPInTotalExpenc
										, "000"
										, "6610"
										, "10000000130"
										, ((a2pList.get(0).actPostingKindRefCode == ENActPostingKind.SERVICES_CONTRACT && !isIncomeAct)
												|| (a2pList.get(0).actPostingKindRefCode == ENActPostingKind.SERVICES_CONTRACT_PROJECT)
												|| (a2pList.get(0).actPostingKindRefCode == ENActPostingKind.SERVICES_CONTRACT_PROJECT_PRICONNECTION))
											? "000" : KodPodr
										, correspondingAccount
										, (a2pList.get(0).actPostingKindRefCode < ENActPostingKind.SERVICES_CONTRACT_INSTALL_COUNTER
												|| a2pList.get(0).actPostingKindRefCode == ENActPostingKind.SERVICES_SUPPLIER_CONTRACT
												? new StringBuilder(correspondingCAA).replace(5, 6, "5").toString()
												: correspondingCAA)
										, act.numberGen, act.dateGen, "servise" + act.numberGen + " , "
												+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct) + infoDescript
										, userName
									);
						V_Prov_Buffer = (V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
								+ fpLogic.generateQueryTransSHABLON(act.dateGen
										, sumESVInTotalExpenc
										, "000"
										, "6510"
										, "10000000130"
										, ((a2pList.get(0).actPostingKindRefCode == ENActPostingKind.SERVICES_CONTRACT && !isIncomeAct)
												|| (a2pList.get(0).actPostingKindRefCode == ENActPostingKind.SERVICES_CONTRACT_PROJECT)
												|| (a2pList.get(0).actPostingKindRefCode == ENActPostingKind.SERVICES_CONTRACT_PROJECT_PRICONNECTION))
											? "000" : KodPodr
										, correspondingAccount
										, (a2pList.get(0).actPostingKindRefCode < ENActPostingKind.SERVICES_CONTRACT_INSTALL_COUNTER 
												|| a2pList.get(0).actPostingKindRefCode == ENActPostingKind.SERVICES_SUPPLIER_CONTRACT
												? new StringBuilder(correspondingCAA).replace(5, 6, "5").toString()
												: new StringBuilder(correspondingCAA).replace(0, 1, "2").toString())
										, act.numberGen, act.dateGen, "servise" + act.numberGen + " , "
												+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct) + infoDescript
										, userName
									);
						if( (a2pList.get(0).actPostingKindRefCode == ENActPostingKind.SERVICES_CONTRACT && isIncomeAct ) 
							|| a2pList.get(0).actPostingKindRefCode == ENActPostingKind.SERVICES_SUPPLIER_CONTRACT	) {
							V_Prov_Buffer = (V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
									+ fpLogic.generateQueryTransSHABLON(act.dateGen
											, sumESVInTotalExpenc.add(sumZPInTotalExpenc)
											, KodPodr
											, "9033"
											, new StringBuilder(correspondingCAA).replace(5, 6, "5").toString()
											, KodPodr
											, "7913"
											, "11000000001"
											, act.numberGen, act.dateGen
											, "servise" + act.numberGen + ", "
													+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct)
													+ infoDescript
											, userName
									);
							}
						}
					// END************** V �������� �� �� � �������������������� � ��� �
					// �������������������� ****/
				}

				//// ���� �� ������������ ������ �� ��������� �������� �� �����������
				if (!isLicensedWork) {

					ENAct2TransportFilter a2tFilter = new ENAct2TransportFilter();
					a2tFilter.actRef.code = act.code;
					ENAct2TransportShortList a2tList = a2tDAO.getScrollableFilteredList(a2tFilter, 0, -1);

					// ������ ��� �������� �� ����������� �������
					HashMap<DataForTransAmortizacRed, DataForTransAmortizacRed> transDataAmortizacRed = new HashMap<>();
					// ������ ��� �������� �� ����������� ������
					HashMap<DataForTransAmortizacBlack, DataForTransAmortizacBlack> transDataAmortizacBlack = new HashMap<>();

					for (int tr = 0; tr < a2tList.totalCount; tr++) {

						String cehtransport = fpLogic.getKodPodr(a2tList.get(tr).invNumber);
						// ������� ��� ���� �� �������� mdax
						// 05.08.2021 ����������� � ��, ��� �������� ���������� � ����� ������.
						cehtransport = "000";
						String vidOStransp = fpLogic.getG45(a2tList.get(tr).invNumber);
						String kodZatrTransp = fpLogic.getNNNN(a2tList.get(tr).invNumber);

						// !!!!!RED ������� ������ �� ���������� . ���� �������� �� ���� ���������� ,
						// ���� ������ , ��� �� - ��� �������� �������
						DataForTransAmortizacRed rKey;
						rKey = new DataForTransAmortizacRed(cehtransport, kodZatrTransp, vidOStransp, "1315",
								a2tList.get(tr).paysWork);

						if (transDataAmortizacRed.containsValue(rKey)) {
							DataForTransAmortizacRed dValue = (DataForTransAmortizacRed) transDataAmortizacRed
									.get(rKey);
							rKey.summa = rKey.summa.add(dValue.summa);
							// ������ �� ������ ������ ��� ���� � ������� � ������ ������
							transDataAmortizacRed.remove(rKey);
						}
						transDataAmortizacRed.put(rKey, rKey);

						// !!!!!BLACK ������� ������ �� ���������� . ���� �������� �� ���� ���������� ,
						// ��� �� - ��� �������� ������
						DataForTransAmortizacBlack bKey;
						bKey = new DataForTransAmortizacBlack(cehtransport, vidOStransp, "1315",
								a2tList.get(tr).paysWork);

						if (transDataAmortizacBlack.containsValue(bKey)) {
							DataForTransAmortizacBlack bValue = (DataForTransAmortizacBlack) transDataAmortizacBlack
									.get(bKey);
							bKey.summa = bKey.summa.add(bValue.summa);
							// ������ �� ������ ������ ��� ���� � ������� � ������ ������
							transDataAmortizacBlack.remove(bKey);
						}
						transDataAmortizacBlack.put(bKey, bKey);

					}

					// ������������ �������� �� ����������� �������

					DataForTransAmortizacRed rrKey = null;
					Iterator<DataForTransAmortizacRed> rrIterator = transDataAmortizacRed.keySet().iterator();
					while (rrIterator.hasNext()) {
						rrKey = (DataForTransAmortizacRed) rrIterator.next();
						DataForTransAmortizacRed rrValue = (DataForTransAmortizacRed) transDataAmortizacRed.get(rrKey);
						System.out.println("Value for trans amortizac RED: " + rrValue.ceh + " // " + rrValue.kodZatrat
								+ " // " + rrValue.vidOS + " // " + rrValue.balSch + " // " + rrValue.summa + " // ");
						if (rrValue.summa.doubleValue() > 0) {
							V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
									+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
											rrValue.summa.negate(), // ����� � ��������
											rrValue.ceh, rrValue.balSch, // ���������� ���� 131 + ( 3 - � ���� ����� ��
																			// �������� ��- ���������� )
											rrValue.vidOS + "000000000", // ���������� ���
											KodPodr, // ����������������� ���
											rrValue.kodZatrat.substring(0, 4), // ����������������� ���� ���
																				// ������������� ����
											rrValue.kodZatrat.substring(4, 15), // ����������������� ��� ������� �� ���
																				// �������������
											act.numberGen, act.dateGen,
											"servise" + act.numberGen + ", "
													+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct)
													+ infoDescript, // ����������
											userName
									);

						}

					}

//		           if ( a2pList.get(0).actPostingKindRefCode == ENActPostingKind.SERVICES_CONTRACT_PROJECT ||
//     		   (a2pList.get(0).actPostingKindRefCode ==  ENActPostingKind.SERVICES_CONTRACT &&  isIncomeAct )   )

					// ������������ �������� �� ����������� ������
					DataForTransAmortizacBlack bbKey = null;
					Iterator<DataForTransAmortizacBlack> bbIterator = transDataAmortizacBlack.keySet().iterator();
					while (bbIterator.hasNext()) {
						bbKey = (DataForTransAmortizacBlack) bbIterator.next();
						DataForTransAmortizacBlack bbValue = (DataForTransAmortizacBlack) transDataAmortizacBlack
								.get(bbKey);
						System.out.println("Value for trans amortizac BLACK: " + bbValue.ceh + " // " + bbValue.vidOS
								+ " // " + bbValue.balSch + " // " + bbValue.summa + " // ");
						if (bbValue.summa.doubleValue() > 0) {
							V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
									+ fpLogic.generateQueryTransSHABLON(act.dateGen
											, bbValue.summa
											, bbValue.ceh
											, bbValue.balSch
											, bbValue.vidOS + "000000000"
											, KodPodr
											, correspondingAccount
											// ����������������� ���
											, (a2pList.get(0).actPostingKindRefCode < ENActPostingKind.SERVICES_CONTRACT_INSTALL_COUNTER 
												? new StringBuilder((a2pList.get(0).actPostingKindRefCode 
														== ENActPostingKind.SERVICES_CONTRACT && isIncomeAct) 
														? new StringBuilder(correspondingCAA).replace(10, 11, "2").toString() 
														: correspondingCAA).replace(5, 6, "1").toString()
												: a2pList.get(0).actPostingKindRefCode == ENActPostingKind.SERVICES_SUPPLIER_CONTRACT 
												  ? new StringBuilder(correspondingCAA).replace(5, 6, "1").toString() 
												: new StringBuilder(correspondingCAA).replace(0, 1, "0").replace(10, 11, "2").toString()
											   )
											, act.numberGen, act.dateGen
											, "servise" + act.numberGen + ", "
													+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct)
													+ infoDescript // ����������
											, userName
									);

							// ��� �������� �� ����������� ���� ���� �������� ���
							if ((a2pList.get(0).actPostingKindRefCode == ENActPostingKind.SERVICES_CONTRACT
									&& isIncomeAct)  
									||   a2pList.get(0).actPostingKindRefCode == ENActPostingKind.SERVICES_SUPPLIER_CONTRACT ) {
								V_Prov_Buffer = new String(
										V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
										+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
												bbValue.summa, // ����� � �������
												bbValue.ceh, "9033", // ���������� ���� 131 + ( 3 - � ���� ����� ��
																		// �������� ��- ���������� )
												a2pList.get(0).actPostingKindRefCode == ENActPostingKind.SERVICES_SUPPLIER_CONTRACT 
												? X + "666100001" 
											    : X + "666100002", // ���������� ���
												KodPodr, // ����������������� ���
												// ����������������� ����
												"7913",
												// ����������������� ���
												a2pList.get(0).actPostingKindRefCode == ENActPostingKind.SERVICES_SUPPLIER_CONTRACT 
												? "11000000001" : "11000000002", act.numberGen, act.dateGen,
												"servise" + act.numberGen + ", "
														+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct)
														+ infoDescript, // ����������
												userName
										);
							}
						}

					}

				}

			}
			/* zzzzz */
			if (act.actTypeRef.code == ENPlanWorkState.DESIGNING && isInvest
					&& (invNumber.equals("000000") || invNumber.equals(""))) {

				String X = isInvest == true ? "0" : "2"; // 0-������������ �������� 2-����
				/* ���- ����� �������� �ᒺ��� */
				String AAA = "";
				String B = ""; /*
								 * ������� ������ 1-��������� ������� ������ 3- ������� ������ 4- ����
								 * �������
								 */

				ENAct2FinInfoProvDAO a2pInfoDAO = new ENAct2FinInfoProvDAO(connection, userProfile);

				ENPlanWorkFilter plF = new ENPlanWorkFilter();
				plF.conditionSQL = " enplanwork.code in (select a2p.plancode from enact2enplanwork a2p where a2p.actrefcode = "
						+ act.code + " ) ";
				int[] plArr = plDao.getFilteredCodeArray(plF, 0, -1);

				ENPlanWork plFObj = null;

				if (plArr.length > 0) {
					plFObj = plDao.getObjectNOSEGR(plArr[0]);
				}

				ENDepartmentFilter depFil = new ENDepartmentFilter();
				depFil.code = plFObj.departmentRef.code;
				int[] depArr = departmDAO.getFilteredCodeArray(depFil, 0, -1);
				ENDepartment depObj = departmDAO.getObject(depArr[0]);

				ENAct2FinInfoProvFilter a2pInfoFil = new ENAct2FinInfoProvFilter();
				a2pInfoFil.actRef.code = act.code;
				int[] a2pInfoArr = a2pInfoDAO.getFilteredCodeArray(a2pInfoFil, 0, -1);

				if (a2pInfoArr.length == 0) {
					throw new SystemException(
							" ��� ���������� �������� ��������� � ������� \" �������� �� ����\"  ��������� ��������� ���!!!  ");
				}

				AAA = a2pInfoDAO.getObject(a2pInfoArr[0]).kau_card_object_kod;

				B = a2pInfoDAO.getObject(a2pInfoArr[0]).kau_element_expenses_kod;
				if (AAA == null) {
					throw new SystemException(
							" ��� ���������� �������� ��������� � ������� \" �������� �� ����\"  ��������� ��������� ���!!!  ");
				}
				if (B == null) {
					throw new SystemException(
							" ��� ���������� �������� ��������� � ������� \" �������� �� ����\"  ��������� ��������� ���!!!  ");
				}

				// I �������� �� �� ������
				FKTrans2AXTransItemShortList fkTrans2axTransList = this.getDataForProvsSalaryByAct(act.code);

				if (fkTrans2axTransList == null || fkTrans2axTransList.list == null || fkTrans2axTransList.list.isEmpty()
						|| fkTrans2axTransList.get(0).balCeh == null || fkTrans2axTransList.get(0).balCeh.equals("")) {
					this.recalcENAct2HumenCehIdbyAct(act.code); // ���� ������ ��� ������������ � ������� ������ ������
					fkTrans2axTransList = this.getDataForProvsSalaryByAct(act.code);
				}
				// ���� �������� �� ���������� ��� - ����� ������
				if (fkTrans2axTransList == null || fkTrans2axTransList.list == null || fkTrans2axTransList.list.isEmpty()
						|| fkTrans2axTransList.get(0).balCeh == null || fkTrans2axTransList.get(0).balCeh.equals("")) {
					throw new EnergyproSystemException(
							" \n ��� �������� �� ��������� ��� ���� !!! ������� ������� ���������� ���� !!! ");
				}

				// String KodPodr =
				// depLogic.getFINCehCodeByDepartmentCode(pllist.get(0).departmentRefCode ,
				// false);
				String KodPodr = mdLogic.getMAINORGANIZATIONIDByHRMORGANIZATIONID(depObj.hrmorganizationid,
						act.dateGen);

				BigDecimal zpByAct = new BigDecimal(0);
				for (int i = 0; i < fkTrans2axTransList.totalCount; i++) {
					zpByAct = zpByAct.add(fkTrans2axTransList.get(i).amountCur);
					V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
							+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
									fkTrans2axTransList.get(i).amountCur, // ����� � �������
									fkTrans2axTransList.get(i).balCeh, "6610", // ���������� ����
									"00000000001", // ���������� ���
									KodPodr, // ����������������� ���
									isPricon == false ? "1511" : "1513", // ����������������� ����
									// ����������������� ���
									new String(isPricon == false ? X : connectionKind_KAU) + AAA + B
											+ new String(isPricon == false ? "000000" : "0" + finDocCode),
									act.numberGen, act.dateGen,
									"��� � " + act.numberGen + ", "
											+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct) + " ������������ "
											+ new String(isPricon == true ? "(���������)" : ""), // ����������
									userName
							);
				}

				// I �������� �� �� �������, �������� ����� �� � ������������ �� ��� ����������
				// � ���
				fkTrans2axTransList = this.getDataForProvsSalaryByActRed(act.code);
				for (int ir = 0; ir < fkTrans2axTransList.totalCount; ir++) {
					V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
							+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
									fkTrans2axTransList.get(ir).amountCur.negate(), // ����� � �������
									fkTrans2axTransList.get(ir).balCeh, "6610", // ���������� ����
									"00000000001", // ���������� ���
									fkTrans2axTransList.get(ir).balCeh, // ����������������� ���
									fkTrans2axTransList.get(ir).balans.substring(0, 4), // ����������������� ���� ���
																						// ���������� ����
									// ����������������� ���
									fkTrans2axTransList.get(ir).balans.substring(4, 15), act.numberGen, act.dateGen,
									"��� � " + act.numberGen + ", "
											+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct) + " ������������ "
											+ new String(isPricon == true ? "(���������)" : ""), // ����������
									userName
							);
				}

				// II ����� �������� ������ ������ �� ������������'������ �������� ���������
				// ����������� - ������
				fkTrans2axTransList = this.getDataForBlackProvs�hargesumByAct(act.code);
				for (int c = 0; c < fkTrans2axTransList.totalCount; c++) {
					V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
							+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
									fkTrans2axTransList.get(c).amountCur, // ����� � �������
									fkTrans2axTransList.get(c).balCeh,
									fkTrans2axTransList.get(c).chargerefcode == FINChargeType.IS_NOT_INVALID ? "6510"
											: "6512", // ���������� ����
									"00000000001", // ���������� ���
									KodPodr, // ����������������� ���
									isPricon == false ? "1511" : "1513", // ����������������� ����
									// ����������������� ���
									new String(isPricon == false ? X : connectionKind_KAU) + AAA + B
											+ new String(isPricon == false ? "000000" : "0" + finDocCode),
									act.numberGen, act.dateGen,
									"��� � " + act.numberGen + ", "
											+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct) + " ������������ "
											+ new String(isPricon == true ? "(���������)" : ""), // ����������
									userName
							);
				}

				// ������ ������ �� ������������'������ �������� ��������� ����������� - �������
				// , �������� �� ���� , ��� , ���� ��������� - ������� / �� �������
				fkTrans2axTransList = this.getDataForRedProvs�hargesumByAct(act.code);
				for (int c = 0; c < fkTrans2axTransList.totalCount; c++) {
					V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
							+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
									fkTrans2axTransList.get(c).amountCur.negate(), // ����� � �������
									fkTrans2axTransList.get(c).balCeh,
									fkTrans2axTransList.get(c).chargerefcode == FINChargeType.IS_NOT_INVALID ? "6510"
											: "6512", // ���������� ����
									"00000000001", // ���������� ���
									fkTrans2axTransList.get(c).balCeh, // ����������������� ���
									fkTrans2axTransList.get(c).balans.substring(0, 4), // ����������������� ���� ���
																						// ���������� ����
									// ����������������� ���
									fkTrans2axTransList.get(c).balans.substring(4, 15), act.numberGen, act.dateGen,
									"��� � " + act.numberGen + ", "
											+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct) + " ������������ "
											+ new String(isPricon == true ? "(���������)" : ""), // ����������
									userName
							);
				}

				// III �������� ���� ������� �������� � ���������� ������ �-�� � ����������
				// (�) ����������� �� ����������������� ������� �� ���� (�����������������
				// ������� * 100/122 ) // ������
				if (isPricon) {

					BigDecimal amountCur = zpByAct.multiply(productionExpencesPercent);
					amountCur = amountCur.divide(new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP);
					amountCur = amountCur.multiply(new BigDecimal(100));
					amountCur = amountCur.divide(new BigDecimal(100).add(chargepercent), 6, BigDecimal.ROUND_HALF_UP)
							.setScale(2, BigDecimal.ROUND_HALF_UP);
					V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
							+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
									// ����� � ������� = �� �� ���� * ������� ����������� ������ / 100 * 100 /
									// (100+22 - ��� 22 ��� ��� �� ��������� - ��� �������� ����� �������� �
									// �������� ��������� - �� �������� )
									amountCur, KodPodr, "6610", // ���������� ����
									"10000000001", // ���������� ���
									KodPodr, // ����������������� ���
									"1513", // ����������������� ����
									// ����������������� ���
									connectionKind_KAU + AAA + B + "0" + finDocCode, act.numberGen, act.dateGen,
									"��� � " + act.numberGen + ", "
											+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct) + " ������������ "
											+ new String(isPricon == true ? "(���������)" : ""), // ����������
									userName
							);
					/*** II (�)_red �������� �� ����������� ����� ������� */
					V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
							+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
									// ����� � ������� = �� �� ���� * ������� ����������� ������ / 100 * 100 /
									// (100+22 - ��� 22 ��� ��� �� ��������� - ��� �������� ����� �������� �
									// �������� ��������� - �� �������� )
									amountCur.negate(), KodPodr, "6610", // ���������� ����
									"10000000001", // ���������� ���
									KodPodr, // ����������������� ���
									"9100", // ����������������� ����
									// ����������������� ���
									/* ���� ���� */
									"00110000001", act.numberGen, act.dateGen,
									"��� � " + act.numberGen + ", "
											+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct) + " ������������ "
											+ new String(isPricon == true ? "(���������)" : ""), // ����������
									userName
							);
					/*
					 * II_2+ black ������ ����� �������� (����������������� ������� 50% �� ����
					 * �������� ����� - ����� (�) // ������ )
					 */
					BigDecimal amountCur_2 = zpByAct.multiply(productionExpencesPercent);
					amountCur_2 = amountCur_2.divide(new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP);
					amountCur_2 = amountCur_2.subtract(amountCur).setScale(2, BigDecimal.ROUND_HALF_UP);
					V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
							+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
									amountCur_2, KodPodr, "6510", // ���������� ����
									"10000000001", // ���������� ���
									KodPodr, // ����������������� ���
									"1513", // ����������������� ����
									// ����������������� ���
									connectionKind_KAU + AAA + B + "0" + finDocCode, act.numberGen, act.dateGen,
									"��� � " + act.numberGen + ", "
											+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct) + " ������������ "
											+ new String(isPricon == true ? "(���������)" : ""), // ����������
									userName
							);
					/*
					 * II_2+ red ������ ����� �������� (����������������� ������� 50% �� ����
					 * �������� ����� - ����� (�) // ������� )
					 */
					V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
							+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
									amountCur_2.negate(), KodPodr, "6510", // ���������� ����
									"10000000001", // ���������� ���
									KodPodr, // ����������������� ���
									"9100", // ����������������� ����
									// ����������������� ���
									/* ���� ���� */
									"00130000001", act.numberGen, act.dateGen,
									"��� � " + act.numberGen + ", "
											+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct) + " ������������ "
											+ new String(isPricon == true ? "(���������)" : ""), // ����������
									userName
							);

				}

				// IV ����� �������� �����������

				ENAct2TransportFilter a2tFilter = new ENAct2TransportFilter();
				a2tFilter.actRef.code = act.code;
				ENAct2TransportShortList a2tList = a2tDAO.getScrollableFilteredList(a2tFilter, 0, -1);

				// ������ ��� �������� �� ����������� �������
				HashMap<DataForTransAmortizacRed, DataForTransAmortizacRed> transDataAmortizacRed = new HashMap<>();
				// ������ ��� �������� �� ����������� ������
				HashMap<DataForTransAmortizacBlack, DataForTransAmortizacBlack> transDataAmortizacBlack = new HashMap<>();

				for (int tr = 0; tr < a2tList.totalCount; tr++) {

					String cehtransport = fpLogic.getKodPodr(a2tList.get(tr).invNumber);
					// ������� ��� ���� �� �������� mdax
					cehtransport = mdLogic.getMAINORGANIZATIONIDByHRMORGANIZATIONID(cehtransport, act.dateGen);
					String kodSubschB = fpLogic.getKodSubschB(a2tList.get(tr).invNumber);
					String vidOStransp = fpLogic.getG45(a2tList.get(tr).invNumber);
					String kodZatrTransp = fpLogic.getNNNN(a2tList.get(tr).invNumber);

					// !!!!!RED ������� ������ �� ���������� . ���� �������� �� ���� ���������� ,
					// ���� ������ , ��� �� - ��� �������� �������
					DataForTransAmortizacRed rKey;
					rKey = new DataForTransAmortizacRed(cehtransport, kodZatrTransp, vidOStransp,
							"131" + kodSubschB.substring(2, 3), a2tList.get(tr).paysWork);

					if (transDataAmortizacRed.containsValue(rKey)) {
						DataForTransAmortizacRed dValue = (DataForTransAmortizacRed) transDataAmortizacRed.get(rKey);
						rKey.summa = rKey.summa.add(dValue.summa);
						// ������ �� ������ ������ ��� ���� � ������� � ������ ������
						transDataAmortizacRed.remove(rKey);
					}
					transDataAmortizacRed.put(rKey, rKey);

					// !!!!!BLACK ������� ������ �� ���������� . ���� �������� �� ���� ���������� ,
					// ��� �� - ��� �������� ������
					DataForTransAmortizacBlack bKey;
					bKey = new DataForTransAmortizacBlack(cehtransport, vidOStransp, "131" + kodSubschB.substring(2, 3),
							a2tList.get(tr).paysWork);

					if (transDataAmortizacBlack.containsValue(bKey)) {
						DataForTransAmortizacBlack bValue = (DataForTransAmortizacBlack) transDataAmortizacBlack
								.get(bKey);
						bKey.summa = bKey.summa.add(bValue.summa);
						// ������ �� ������ ������ ��� ���� � ������� � ������ ������
						transDataAmortizacBlack.remove(bKey);
					}
					transDataAmortizacBlack.put(bKey, bKey);

				}

				// ������������ �������� �� ����������� �������
				DataForTransAmortizacRed rrKey = null;
				Iterator<DataForTransAmortizacRed> rrIterator = transDataAmortizacRed.keySet().iterator();
				while (rrIterator.hasNext()) {
					rrKey = (DataForTransAmortizacRed) rrIterator.next();
					DataForTransAmortizacRed rrValue = (DataForTransAmortizacRed) transDataAmortizacRed.get(rrKey);
					System.out.println("Value for trans amortizac RED: " + rrValue.ceh + " // " + rrValue.kodZatrat
							+ " // " + rrValue.vidOS + " // " + rrValue.balSch + " // " + rrValue.summa + " // ");
					if (rrValue.summa.doubleValue() > 0) {
						V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
								+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
										rrValue.summa.negate(), // ����� � �������
										rrValue.ceh, rrValue.balSch, // ���������� ���� 131 + ( 3 - � ���� ����� ��
																		// �������� ��- ���������� )
										rrValue.vidOS + "000000000", // ���������� ���
										KodPodr, // ����������������� ���
										rrValue.kodZatrat.substring(0, 4), // ����������������� ���� ��� �������������
																			// ����
										rrValue.kodZatrat.substring(4, 15), // ����������������� ��� ������� �� ���
																			// �������������
										act.numberGen, act.dateGen,
										"��� � " + act.numberGen + ", "
												+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct)
												+ " ������������ " + new String(isPricon == true ? "(���������)" : ""), // ����������
										userName
								);
					}

				}

				// ������������ �������� �� ����������� ������
				DataForTransAmortizacBlack bbKey = null;
				Iterator<DataForTransAmortizacBlack> bbIterator = transDataAmortizacBlack.keySet().iterator();
				while (bbIterator.hasNext()) {
					bbKey = (DataForTransAmortizacBlack) bbIterator.next();
					DataForTransAmortizacBlack bbValue = (DataForTransAmortizacBlack) transDataAmortizacBlack
							.get(bbKey);
					System.out.println("Value for trans amortizac BLACK: " + bbValue.ceh + " // " + bbValue.vidOS
							+ " // " + bbValue.balSch + " // " + bbValue.summa + " // ");
					if (bbValue.summa.doubleValue() > 0) {
						V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
								+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
										bbValue.summa, // ����� � �������
										bbValue.ceh, bbValue.balSch, // ���������� ���� 131 + ( 3 - � ���� ����� ��
																		// �������� ��- ���������� )
										bbValue.vidOS + "000000000", // ���������� ���
										KodPodr, // ����������������� ���
										isPricon == false ? "1511" : "1513", // ����������������� ���� , //
																				// ����������������� ����
										// ����������������� ���
										new String(isPricon == false ? X : connectionKind_KAU) + AAA + B
												+ new String(isPricon == false ? "000000" : "0" + finDocCode),
										act.numberGen, act.dateGen,
										"��� � " + act.numberGen + ", "
												+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct)
												+ " ������������ " + new String(isPricon == true ? "(���������)" : ""), // ����������
										userName
								);
					}

				}

			}
			
			if (act.actTypeRef.code == ENPlanWorkState.CURRENT_REPAIR || act.actTypeRef.code == ENPlanWorkState.CAPITAL_REPAIR) {
				
				if (invNumber.length() == 5) {
					invNumber = "0" + invNumber;
				}
				
				String KodPodr = "000";
				String korSch_black;
				String correspondingCaa;
				
            	switch(act.element.typeRef.code) {
            	case ENElementType.METROLOGY_OBJECT:
            		korSch_black = settingsLogic.getValue(ENSettingsKeysConsts.COUNTER_REPAIRING_CORRESPONDING_ACCOUNT);
            		correspondingCaa = settingsLogic.getValue(ENSettingsKeysConsts.COUNTER_REPAIRING_CORRESPONDING_CAA);
            		break;
            	case ENElementType.METROLOGY_DEVICE:
            		korSch_black = "2320";
            		correspondingCaa = "10503300001";
            		break;
            		default:
            			korSch_black = "2361";
            			correspondingCaa = this.getCorrespondingCAAForRepairing(act,  invNumber); 
            			break;
            	}
				
				String NNNN = (korSch_black.equals("2361") ? fpLogic.getNNNN(invNumber) : "????");
				String M = (korSch_black.equals("2361") ? fpLogic.getM(invNumber) : "0");
				String E = (M.equals("1") ? "9" : "8");
				String kauZatr = "000" + E + (act.actTypeRef.code == ENPlanWorkState.CURRENT_REPAIR ? "1" : "2")									
						+ "000001";
				
				String comment = "��� � " + act.numberGen + ", " + new SimpleDateFormat("dd.MM.yyyy").format(act.dateAct)
						+ " " + planWorkState.name + " �� �� ����";
				
				// I �������� �� �� ������
				FKTrans2AXTransItemShortList fkTrans2axTransList = this.getDataForProvsSalaryByAct(act.code);

				if (fkTrans2axTransList == null || fkTrans2axTransList.list == null || fkTrans2axTransList.list.isEmpty()
						|| fkTrans2axTransList.get(0).balCeh == null || fkTrans2axTransList.get(0).balCeh.equals("")) {
					this.recalcENAct2HumenCehIdbyAct(act.code); // ���� ������ ��� ������������ � ������� ������ ������
					fkTrans2axTransList = this.getDataForProvsSalaryByAct(act.code);
				}
				
				BigDecimal totalSum = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);

				for (int i = 0; i < fkTrans2axTransList.totalCount; i++) {
					//zpByAct = zpByAct.add(fkTrans2axTransList.get(i).amountCur);
					V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
							+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
									fkTrans2axTransList.get(i).amountCur, // ����� � �������
									fkTrans2axTransList.get(i).balCeh, "6610", // ���������� ����
									"00000000130", // ���������� ���
									KodPodr, // ����������������� ���
									korSch_black, // ����������������� ����
									// ����������������� ���
									/* ���� ���� */
									(korSch_black.equals("2361") ? 
											new StringBuilder(correspondingCaa).replace(5, 6, "2").toString() : correspondingCaa)
									, act.numberGen, act.dateGen,
									comment,
									userName
							);
					totalSum = totalSum.add(fkTrans2axTransList.get(i).amountCur);
				}
				
				if(korSch_black.equals("2361")) {
					V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
							+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
									totalSum, // ����� � �������
									KodPodr,
									korSch_black, // ���������� ����
									(korSch_black.equals("2361") ? 
											new StringBuilder(correspondingCaa).replace(5, 6, "2").toString() : correspondingCaa), // ���������� ���
									KodPodr, // ����������������� ���
									NNNN, // ����������������� ����
									// ����������������� ���
									/* ���� ���� */
									new StringBuilder(kauZatr).replace(0, 1, "1").replace(5, 6, "2").replace(10, 11, "1").toString(),
									act.numberGen, act.dateGen,
									comment,
									userName
							);
				}
				totalSum = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);

				// I �������� �� �� �������, �������� ����� �� � ������������ �� ��� ����������
				// � ���
				fkTrans2axTransList = this.getDataForProvsSalaryByActRed(act.code);
				for (int ir = 0; ir < fkTrans2axTransList.totalCount; ir++) {
					if (fkTrans2axTransList.get(ir).balans == null || fkTrans2axTransList.get(ir).balans.isEmpty()) {
						throw new SystemException("\n\n�� ������ ��� ��������! ��� ����: " + act.code);
					}

					V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
							+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
									fkTrans2axTransList.get(ir).amountCur.negate(), // ����� � �������
									fkTrans2axTransList.get(ir).balCeh, "6610", // ���������� ����
									"00000000130", // ���������� ���
									fkTrans2axTransList.get(ir).balCeh, // ����������������� ���
									fkTrans2axTransList.get(ir).balans.substring(0, 4), // ����������������� ���� ���
																						// ���������� ����
									// ����������������� ���
									fkTrans2axTransList.get(ir).balans.substring(4, 15)
									, act.numberGen, act.dateGen, comment,
									userName
							);
				}



				// III ����� �������� ������ ������ �� ������������'������ �������� ���������
				// ����������� - ������
				fkTrans2axTransList = this.getDataForBlackProvs�hargesumByAct(act.code);
				for (int c = 0; c < fkTrans2axTransList.totalCount; c++) {
					totalSum = totalSum.add(fkTrans2axTransList.get(c).amountCur);
					V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
							+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
									fkTrans2axTransList.get(c).amountCur, // ����� � �������
									fkTrans2axTransList.get(c).balCeh,
									fkTrans2axTransList.get(c).chargerefcode == FINChargeType.IS_NOT_INVALID ? "6510"
											: "6512", // ���������� ����
									"00000000130", // ���������� ���
									KodPodr, // ����������������� ���
									korSch_black, // ����������������� ����
									// ����������������� ���
									/* ���� ���� */
									(korSch_black.equals("2361") ? 
											new StringBuilder(correspondingCaa).replace(5, 6, "4").toString() 
											: (korSch_black.equals("2320") ? 
													new StringBuilder(correspondingCaa).replace(0,1, "2").toString()
													: correspondingCaa))
									, act.numberGen, act.dateGen,
									comment,
									userName
							);
				}
				
				if(korSch_black.equals("2361")) {
					V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
							+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
									totalSum, // ����� � �������
									KodPodr,
									korSch_black, // ���������� ����
									(korSch_black.equals("2361") ? 
											new StringBuilder(correspondingCaa).replace(5, 6, "4").toString() : correspondingCaa), // ���������� ���
									KodPodr, // ����������������� ���
									NNNN, // ����������������� ����
									// ����������������� ���
									/* ���� ���� */
									new StringBuilder(kauZatr).replace(0, 1, "2").replace(5, 6, "4").replace(10, 11, "1").toString(),
									act.numberGen, act.dateGen,
									comment,
									userName
							);						
				}
				
				totalSum = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);

				// ������ ������ �� ������������'������ �������� ��������� ����������� - �������
				// , �������� �� ���� , ��� , ���� ��������� - ������� / �� �������
				fkTrans2axTransList = this.getDataForRedProvs�hargesumByAct(act.code);
				for (int c = 0; c < fkTrans2axTransList.totalCount; c++) {
					V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
							+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
									fkTrans2axTransList.get(c).amountCur.negate(), // ����� � �������
									fkTrans2axTransList.get(c).balCeh,
									fkTrans2axTransList.get(c).chargerefcode == FINChargeType.IS_NOT_INVALID ? "6510"
											: "6512", // ���������� ����
									"00000000130", // ���������� ���
									fkTrans2axTransList.get(c).balCeh, // ����������������� ���
									fkTrans2axTransList.get(c).balans.substring(0, 4), // ����������������� ���� ���
																						// ���������� ����
									// ����������������� ���
									fkTrans2axTransList.get(c).balans.substring(4, 15)
									, act.numberGen, act.dateGen, comment,
									userName
							);
				}
				
				Map.Entry<List<DataForTransAmortizacBlack>,List<DataForTransAmortizacRed>> amortisationData 
					= this.getDataForProvsAmortization(act);
				
				List<DataForTransAmortizacBlack> listBlackAmortisation = amortisationData.getKey();
				List<DataForTransAmortizacRed> listRedAmortisation = amortisationData.getValue();
				for(DataForTransAmortizacBlack item : listBlackAmortisation) {
					if (item.summa.doubleValue() > 0) {
						V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
								+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
										item.summa, // ����� � �������
										item.ceh, item.balSch, // ���������� ���� 131 + ( 3 - � ���� ����� ��
																		// �������� ��- ���������� )
										item.vidOS + "000000000", // ���������� ���
										KodPodr, // ����������������� ���
										korSch_black, // ����������������� ����
										// ����������������� ���
										// ���� ����
										(korSch_black.equals("2361") ? 
												new StringBuilder(correspondingCaa).replace(5, 6, "5").toString() : correspondingCaa)
										, act.numberGen, act.dateGen,
										comment,
										userName
								);
						totalSum = totalSum.add(item.summa);
					}
				}
				
				for(DataForTransAmortizacRed item : listRedAmortisation) {
					if (item.summa.doubleValue() > 0) {
						V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
								+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
										item.summa.negate(), // ����� � �������
										item.ceh, item.balSch, // ���������� ���� 131 + ( 3 - � ���� ����� ��
																		// �������� ��- ���������� )
										item.vidOS + "000000000", // ���������� ���
										KodPodr, // ����������������� ���
										item.kodZatrat.substring(0, 4), // ����������������� ���� ��� �������������
																			// ����
										item.kodZatrat.substring(4, 15), // ����������������� ��� ������� �� ���
																			// �������������
										act.numberGen, act.dateGen,
										comment,
										userName
								);
					}
				}
				
				if(korSch_black.equals("2361") && totalSum.compareTo(BigDecimal.ZERO) == 1) {
					V_Prov_Buffer = new String(V_Prov_Buffer.length() > 0 ? V_Prov_Buffer + " UNION ALL " : "")
							+ fpLogic.generateQueryTransSHABLON(act.dateGen, // ���� ���������
									totalSum, // ����� � �������
									KodPodr, korSch_black, // ���������� ���� 131 + ( 3 - � ���� ����� ��
																	// �������� ��- ���������� )
									(korSch_black.equals("2361") ? 
											new StringBuilder(correspondingCaa).replace(5, 6, "5").toString() 
											: correspondingCaa),
									KodPodr, // ����������������� ���
									NNNN, // ����������������� ���� ��� �������������
																		// ����
									new StringBuilder(kauZatr).replace(5, 6, "5").replace(10, 11, "2").toString(),
																		// �������������
									act.numberGen, act.dateGen,
									comment,
									userName
							);
				}
			}

			provResult = fpLogic.createPostings(V_Prov_Buffer);
			String badprovstring = "";
			if (provResult.badProvList.totalCount > 0) {
				badprovstring = "  ������ �� :  ";
				for (int b = 0; b < provResult.badProvList.totalCount; b++) {

					badprovstring = badprovstring + " ����� ������ = "
							+ provResult.badProvList.get(b).getDetailedList().get(0).err_mes + "\n ����������  = "
							+ provResult.badProvList.get(b).primechan + "\n  bal_ceh "
							+ provResult.badProvList.get(b).bal_ceh + "  bal_kau "
							+ provResult.badProvList.get(b).bal_kau + "  bal_sch "
							+ provResult.badProvList.get(b).bal_sch + "  bal_sub_sch "
							+ provResult.badProvList.get(b).bal_sub_sch + "\n  kor_ceh "
							+ provResult.badProvList.get(b).kor_ceh + "  kor_kau "
							+ provResult.badProvList.get(b).kor_kau + "  kor_sch "
							+ provResult.badProvList.get(b).kor_sch + "  kor_sub_sch "
							+ provResult.badProvList.get(b).kor_sub_sch;

				}

				System.out.print("\n \n \n partid badprovstring =  error provResult = " + badprovstring);
			}

			return provResult;

		} catch (DatasourceConnectException e) {
			throw new EnergyproSystemException("�� ������� �������� ��������!", e);
		} catch (PersistenceException e) {
			throw new EnergyproSystemException(e.getMessage(), e);
		}
		// catch (ParseException e) {throw new
		// EnergyproSystemException(e.getMessage(),e);}

		finally {

			try {
				if (finConn != null && !finConn.isClosed()) {
					finConn.close();
					finConn = null;
				}
			} catch (SQLException e) {
			}
		}
	}

	/**
	 *
	 * ������� ��� � ������������ �� ������ ��� ����
	 *
	 * @param act {@link ENAct} ��� �� �������� ���������� �������� ���
	 */
	public void insertZKUsByAct(ENAct act) {
		Connection finConn = null;
		try {
			if(act == null || act.code == Integer.MIN_VALUE) {
				throw new java.lang.NullPointerException();
			}

			finConn = getNEWConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);

			ENActDAO actDao = new ENActDAO(connection, userProfile);
			ENPlanWorkDAO planDao = new ENPlanWorkDAO(connection, userProfile);
			FINMolLogic finMolLogic = new FINMolLogic(connection, userProfile);
			PlanWorkLogic planWorkLogic = new PlanWorkLogic(connection, userProfile);
			ENTechConditionsServicesDAO tcServicesDao = new ENTechConditionsServicesDAO(connection, userProfile);
			ElementLogic elementLogic = new ElementLogic(connection, userProfile);
			com.ksoe.energynet.dataminer.SCLogicDAO scDao = new com.ksoe.energynet.dataminer.SCLogicDAO(finConn, userProfile);
			SCCounterDAO counterDao = new SCCounterDAO(connection, userProfile);
			ENEstimateItemDAO estimateDao = new ENEstimateItemDAO(connection, userProfile);
			ENAct2OSTableDAO act2OstableDao = new ENAct2OSTableDAO(connection, userProfile);

			actDao.checkENActStatuses(act, true, true, ENActStatus.GOOD);

			String planCodesStr = this.getPlanCodesByAct(act.code);
			ENPlanWorkFilter planFilter = new ENPlanWorkFilter();
			planFilter.conditionSQL = String.format("%s in (%s)", ENPlanWork.code_QFielld, planCodesStr);
			int[] planCodes = planDao.getFilteredCodeArray(planFilter, 0, -1);
			if(planCodes.length == 0) throw new SystemException(String.format("� ��� � %s �� %s ���� �����!"
					, act.numberGen, new SimpleDateFormat("dd.mm.yyyy").format(act.dateAct)));
			for(int planCode : planCodes) {
				ENPlanWork plan = planDao.getObject(planCode);
				// ���� ���� ����� ��� �������� �� "��������� ���", �� ����� ����������� ������
				planDao.checkENPlanWorkTypes(plan, true, true, ENPlanWorkType.EZ_SETUP_ZKU);
				ENWorkOrder workOrder = planWorkLogic.getWorkOrderByPlanCode(plan.code);
				FINMolData finMolData = finMolLogic.getFINMolDataByWorkOrderCode(workOrder.code, FINMolType.MASTER);
				ENTechConditionsServices tcServices = tcServicesDao.getENTechConditionsServicesByPlan(plan);
				Boolean isStandardConnection = null;
				if(tcServices != null) {
				            switch (tcServices.connectionKindRef.code) {
				            case ENConnectionKind.STANDART:
				                isStandardConnection = true;
				                break;
				            case ENConnectionKind.NO_STANDART:
				                isStandardConnection = false;
				                break;
				            case ENConnectionKind.READY_MADE:
				                isStandardConnection = false;
				                break;
				            default:
				                throw new SystemException(String.format("��� �������� � %s ����� %s ����� ���������� ��� �������������",
				                        tcServices.contractNumber, new SimpleDateFormat("dd.MM.yyyy").format(tcServices.contractDate)));
				            }
				}
				if(finMolData == null) throw new SystemException(String.format("�� �������� ����� �� ��� ��� ����� � ����� %d", plan.code));

				String accountNumber = elementLogic.getElementInvNumber(plan.elementRef.code);

				ENEstimateItemFilter estimateFilter = new ENEstimateItemFilter();
				estimateFilter.planRef.code = plan.code;
				int[] estimateCodes = estimateDao.getFilteredCodeArray(estimateFilter, 0, -1);

				int codeZKU = scDao.transferCounterMountInSCZKU(
						Integer.MIN_VALUE
						, act.numberGen
						, act.dateGen
						, userProfile.userName
						, new Date()
						, ""
						, counterDao.getAddr(estimateCodes[0])
						, act.dateAct
						, accountNumber
						, "���-" + accountNumber
						, "���"
						, finMolData.finMolCode
						, (tcServices == null ? null : tcServices.contractNumber)
						, (tcServices == null ? null : tcServices.contractDate)
						, isStandardConnection);

				String invNumberZKU = scDao.getInvNumberZKU(codeZKU);

				// ������ ����������� ���� � ������������ � ������ � �����
				ENAct2OSTable act2Ostable = new ENAct2OSTable();
				act2Ostable.num_un = codeZKU;
				act2Ostable.invNumber = invNumberZKU;
				act2Ostable.actRef.code = act.code;
				act2OstableDao.add(act2Ostable);
			}
		} catch (PersistenceException e) {
			throw new SystemException(e);
		} catch (DatasourceConnectException e) {
			throw new SystemException(e);
		} finally {
	          try {
	              if (finConn != null && ! finConn.isClosed()) {
	                  finConn.close();
	                  finConn = null;
	              }
	          } catch (SQLException e) {
	          }
	          this.closeConnection();
		}
	}

	private static final int ZKUS_INSERTED_BY_ACT_REMOVAL = 0;
	private static final int ZKUS_INSERTED_BY_ACT_MOVE = 1;
	private static final int ZKUS_INSERTED_BY_ACT_UNMOVE = 2;

	/**
	 *
	 * �������� ����������� ��� �� ����
	 *
	 * @param act {@link ENAct} ��� �� �������� ���������� ������� ���
	 */
	public void removeZKUsInsertedByAct(ENAct act) {
		this.workWithZKUsInsertedByAct(act, ENActStatus.SIGNATURE, ZKUS_INSERTED_BY_ACT_REMOVAL);
	}

	/**
	 *
	 * �������� ����������� ��� �� ����
	 *
	 * @param act {@link ENAct} ��� �� �������� ���������� �������� ���
	 */
	public void moveZKUsInsertedByAct(ENAct act) {
		this.workWithZKUsInsertedByAct(act, ENActStatus.SIGNATURE, ZKUS_INSERTED_BY_ACT_MOVE);
	}

	/**
	 *
	 * ������ ���������� ��� �� ����
	 *
	 * @param act {@link ENAct} ��� �� �������� ���������� �������� ���������� ���
	 */
	public void unMoveZKUsInsertedByAct(ENAct act) {
		this.workWithZKUsInsertedByAct(act, ENActStatus.CLOSED, ZKUS_INSERTED_BY_ACT_UNMOVE);
	}

	/**
	 *
	 * �����, �������, � ����������� �� ���������� � ���� ��������� {@code action}
	 * ������������ ������������ �������� � ��� ���������� � ������
	 *
	 * @param act ������ ����
	 * @param actStatusCode ������, � ������� ������ ���� ���
	 * (���� ��� �� ����� � ���� �������, �� ����� ����������� ����������)
	 * @param action ����� �����, � ����� �� �������� �������� (0 - �������� ���;
	 * 1 - ���������� ���; 2 - ������ ���������� ���)
	 */
	private void workWithZKUsInsertedByAct(ENAct act, int actStatusCode, int action) {
		Connection finConn = null;
		try {
			if(act == null || act.code == Integer.MIN_VALUE) {
				throw new java.lang.NullPointerException();
			}
			finConn = getNEWConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
			com.ksoe.energynet.dataminer.SCLogicDAO scDao = new com.ksoe.energynet.dataminer.SCLogicDAO(finConn, userProfile);
			ENPlanWorkDAO planDao = new ENPlanWorkDAO(connection, userProfile);
			ENAct2OSTableDAO act2OstableDao = new ENAct2OSTableDAO(connection, userProfile);
			ENActDAO actDao = new ENActDAO(connection, userProfile);

			actDao.checkENActStatuses(act, true, true, actStatusCode);

			String planCodesStr = this.getPlanCodesByAct(act.code);
			ENPlanWorkFilter planFilter = new ENPlanWorkFilter();
			planFilter.conditionSQL = String.format("%s in (%s)", ENPlanWork.code_QFielld, planCodesStr);
			int[] planCodes = planDao.getFilteredCodeArray(planFilter, 0, -1);
			if(planCodes.length == 0) throw new SystemException(String.format("� ��� � %s �� %s ���� �����!"
					, act.numberGen, new SimpleDateFormat("dd.mm.yyyy").format(act.dateAct)));
			for(int planCode : planCodes) {
				ENPlanWork plan = planDao.getObject(planCode);
				// ���� ���� ����� ��� �������� �� "��������� ���", �� ����� ����������� ������
				planDao.checkENPlanWorkTypes(plan, true, true, ENPlanWorkType.EZ_SETUP_ZKU);
			}

			ENAct2OSTableFilter act2OstableFilter = new ENAct2OSTableFilter();
			act2OstableFilter.actRef.code = act.code;

			ENAct2OSTableShortList act2OstableList = act2OstableDao.getScrollableFilteredList(act2OstableFilter, 0, -1);

			for(ENAct2OSTableShort act2Ostable : act2OstableList.list) {
				switch(action) {
				case ZKUS_INSERTED_BY_ACT_REMOVAL:
					scDao.untransferCounterMountInSCZKU(act2Ostable.num_un);
					act2OstableDao.remove(act2Ostable.code);
					break;
				case ZKUS_INSERTED_BY_ACT_MOVE:


					ENAct2HumenDAO actHumenDao = new ENAct2HumenDAO(connection, userProfile);

					ENAct2HumenFilter actHumenFilter = new ENAct2HumenFilter();
					actHumenFilter.actRef.code = act.code;

					ENAct2HumenShortList actHumenList = actHumenDao.getFilteredList(actHumenFilter);

					BigDecimal sumSalary = new BigDecimal(0).setScale(2,  BigDecimal.ROUND_HALF_UP);
					BigDecimal sumTaxes = new BigDecimal(0).setScale(2,  BigDecimal.ROUND_HALF_UP);

					for(ENAct2HumenShort actHumen : actHumenList.list) {
						sumSalary = sumSalary.add(actHumen.paysWorkBonus);
						sumTaxes = sumTaxes.add(actHumen.chargeSum);
					}

					int idZKU = scDao.provCounterMountInSCZKU(
					        Integer.MIN_VALUE,
					         act2Ostable.num_un,
					         act.dateGen,
					         this.getSumByActCode(act.code),
					         this.getSumOfMaterialsInAct(act, null, false, TKAccountingType.COUNTERS),
					         this.getSumOfMaterialsInAct(act, true, false, TKAccountingType.COUNTERS),
					         sumSalary,
					         sumTaxes
					);

					for(ENAct2HumenShort actHumen : actHumenList.list) {
						scDao.dovvodCounterMountInSCZKU(idZKU, actHumen.tabNumber, actHumen.balans, actHumen.paysWorkBonus, actHumen.chargeSum);
					}
					break;
				case ZKUS_INSERTED_BY_ACT_UNMOVE:
					scDao.unprovCounterMountInSCZKU(act2Ostable.num_un);
					break;
				default: throw new java.lang.IllegalArgumentException("������������ �������� ��� ��������� action");
				}
			}


		} catch (DatasourceConnectException e) {
			throw new SystemException(e);
		} catch (PersistenceException e) {
			throw new SystemException(e);
		} finally {
	          try {
	              if (finConn != null && ! finConn.isClosed()) {
	                  finConn.close();
	                  finConn = null;
	              }
	          } catch (SQLException e) {
	          }
	          this.closeConnection();
		}
	}


//NET-2335 �������� �������� �� ���� ��������� ����� FK & AX
public FKProvResult movePostingByOZ(SCUsageInputItemOZ oz , SCUsageInput ui ){
	    Connection finConn = null;
		//Connection enConn = null;

		try {
	 FKProvResult provResult = null;

	 finConn = getNEWConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);

     FKPostingLogic fpLogic = new FKPostingLogic(finConn , userProfile);
     ENPlanWorkDAO plDao = new ENPlanWorkDAO(connection, userProfile);
     SCUsageInputItemOZ2ENActDAO oz2actDAO = new SCUsageInputItemOZ2ENActDAO(connection, userProfile);
     ENActDAO aDAO = new ENActDAO(connection, userProfile);

     mDaxLogic mdLogic = new mDaxLogic(connection, userProfile);

     SCUsageInputItemDAO usiDAO = new SCUsageInputItemDAO(connection, userProfile);
     SCCounterDAO cDAO = new SCCounterDAO(connection, userProfile);

     SCUsageInputItemFilter usiFilter = new SCUsageInputItemFilter();
     usiFilter.conditionSQL =  " SCUsageInputItem.code in (select ii.code from scusageinputitem ii, scusageinputitemoz oz "
     		+ " where ii.code = oz.usageinputitemrefcode and oz.code = " + oz.code +" )";
     SCUsageInputItemShortList usiList = usiDAO.getScrollableFilteredList(usiFilter, 0, -1);
     SCUsageInputItem usiObject = usiDAO.getObject(usiList.get(0).code);
     // SUPP-67738 ����������� �� ��� ��������� ���������
     boolean isAbonCounter = (oz.account == null || oz.account.trim().length() == 0);

     /*�� ����������� ����� � ����*/
     /*ENAct2HumenFilter a2hFilter = new ENAct2HumenFilter();
     a2hFilter.chargeRef.code = FINChargeType.IS_NOT_INVALID;
     a2hFilter.actRef.code = act.code;
     ENAct2HumenShortList a2hList = a2hDAO.getScrollableFilteredList(a2hFilter, 0, -1);
     if (a2hList.totalCount>0){
     	chargepercent = a2hList.get(0).chargePercent;
     }*/

      /*!!!! ���� ��� ���� = ����������� ������������ � ������ ����� � ������ � ���� ���� ���� �� ������� ()
       * ( 100	�� - ������� ����� ���������
       *   102	�� - ��������� ����� ���������
       *   103	�� - ��������� ������������ ���������
       *   106	�� - ��������� ���
       *   111	�� - ��������� ��� � �������(���������) ��������
       *   112	�� - ������ �������� � ������� ��� )
      !!!!! �� ���������� , ����� ����� ...�.� ����� ��� �������� �������� �� ��*/

     ENPlanWorkFilter plFil = new ENPlanWorkFilter();
     plFil.conditionSQL = "  enplanwork.code in ( select p.code from enplanwork p  , enact2enplanwork a2p \n"+
                         " where a2p.actrefcode in (select oz2act.enactrefcode from scusageinputitemoz oz , scusageinputitemoz2nct oz2act " +
													" where oz.code=" + oz.code  +
													" and oz.code = oz2act.usageinputitemozrefcod ) " +
                         " and a2p.plancode = p.code  \n"+
                         " and p.typerefcode in ( "+ENPlanWorkType.EZ_PLANED_CHANGE_COUNTER +" , "
                         +ENPlanWorkType.EZ_NOPLANED_CHANGE_COUNTER +" , "
                         +ENPlanWorkType.EZ_NOPLANED_SETUP_COUNTER +" , "
                         +ENPlanWorkType.EZ_SETUP_ZKU  +" , "
                         +ENPlanWorkType.EZ_CHANGE_ZKU  +" , "
                         +ENPlanWorkType.EZ_CHANGE_COUNTER_IN_ZKU +" ) "
                         		+ " union \n" + // ������ ��� �� ������� ���� � scusage
                         		"  /* ������� ���*/ \n" +
                         		"  select enp.code  From \n" +
                         		"  scusageinputtmz2sccntr sci2scc , \n" +
                         		"  sccounter sc , \n" +
                         		"  enestimateitem eni , \n" +
                         		"  enplanwork enp , \n" +
                         		"  enelement el, \n" +
                         		"  finmoldata  md, \n" +
                         		"  enworkorder2enplanwork  wp \n" +
                         		"  where sci2scc.ozrefcode = " + oz.code  + "  /*�������� ���� ��*/ \n" +
                         		"    and sc.statusrefcode = 2 /*� ��� (��-1 � ���.)*/ \n" +
                         		"    and sc.code = sci2scc.sccounterrefcode \n" +
                         		"    and sc.estimateitemrefcode = eni.code \n" +
                         		"    and eni.accountingtyperefcode = 2 /*�������� */ \n" +
                         		"    and enp.code = eni.planrefcode \n" +
                         		"    and enp.elementrefcode = el.code \n" +
                         		"    and md.workordercode=wp.workordercode \n" +
                         		"    and md.moltyperefcode=1 \n" +
                         		"    and wp.plancode=enp.code  \n" +
                         		"    and sc.code = sci2scc.sccounterrefcode \n" +
                         		") "   ;
     int[] plFilArr = plDao.getFilteredCodeArrayNOSEGR(plFil, 0, -1);
     // �� �������� �������� ��� ����� ��� ��������� (���� ��)
     // - ��������� � ������������
     // - ������ �/� � ��
     // - ��������� � ������������ � ������ ��� . //
     if (plFilArr.length == 0 || usiObject.kindRef.code == SCUsageInputItemKind.UsageOut
    		                  || usiObject.kindRef.code == SCUsageInputItemKind.InputUsing
    		                  || usiObject.kindRef.code == SCUsageInputItemKind.UsageInputInZKU  ){
     	return provResult;
     }

     ////
     int connectionKind = Integer.MIN_VALUE;
     boolean isPricon = false;

     ENActFilter aFil = new ENActFilter();
     aFil.conditionSQL = " enact.code in (  select u2a.enactrefcode from scusageinputitemoz2nct u2a where u2a.usageinputitemozrefcod = "+ oz.code+" \n" +
						"  union  \n" +
						"  select a2p.actrefcode from scusageinputtmz2sccntr oz2sc, sccounter sc , enestimateitem ei , enact2enplanwork a2p \n" +
						"  where oz2sc.ozrefcode = "+ oz.code+"  \n" +
						"  and sc.code = oz2sc.sccounterrefcode \n" +
						"  and sc.estimateitemrefcode = ei.code  \n" +
						"  and ei.planrefcode = a2p.plancode )       ";

     int[] aArr = aDAO.getFilteredCodeArray(aFil, 0, -1);

     if (aArr.length == 0 ){
    	 throw new EnergyproSystemException(" \n ��� �������� �������� �� ��������� ������ ���� ��������� ���� ");
     }

     // ���� �������� ���������� � ����������� ���, ����� ��������� ��� ������ energynet
     SCUsageInputLogic scUsageInputLogic = new SCUsageInputLogic(connection, userProfile);
     String userName = scUsageInputLogic.getUserNameForFKBySCUsageInput(ui.code);

     connectionKind = this.getConnectionKind(aArr[0]);
     if(connectionKind != Integer.MIN_VALUE){
    	isPricon = true;
     }
     String IIII = this.getCorrespondingAccountForOZ(oz);
     String BBBBBBBBBBB = this.getCorrespondingCAAForOZ(oz, isPricon);

		String V_Prov_Buffer = "";
		// ��������� �������� ��� ���
		if ( ui.iszku == Integer.MIN_VALUE || ui.iszku == 0 ){
			/*SUPP-103845,SUPP-104514 */  String  kodPodr = ((isAbonCounter) ? "000" : "0" + usiObject.molCode.substring(0, 2));

			if(kodPodr.equals("") ){
				throw new EnergyproSystemException(" \n ��� �������� �������� �� ��������� ��� ���� �� �� !!! \n ui.department.hrmorganizationid = " + ui.department.hrmorganizationid  );
			}



	         // -- ���� �� �� ����� � ������������ �������� �� ��� ���� ������� �� �������� ������� ��������  ,, 1-� ���� ��� =1 , 2-� ���� ��� = (���� ������� �� = 5 ������ 4)
	         // -- ���� �� �� ����� � ������������ ��� �� ��� ���� = 1531 ,, 1-� ���� ��� = 2 , 2 ���� ��� = (���� ������� �� = 5 ������ 4)

		     //~!!!!!!!! 14.09.2017  --- ��������� �� ����������� ��� ����� ���� ������� ����� � 15 ����� �� ���� 4 ����� ��������� ���������� 15..
		     ///!!!!!!!!!!!!!!!  � ���� ����� � 11 ����� �� ��� ���� = 1532 ����� , ��� ������� �������� � ��� ����� ����� � ����� ��������� � ������� ���
			 ///String BBBBBBBBBBB_otchisl="";
		     String infoDescript = " ������ ��������� ";

		     if( usiObject.kindRef.code == SCUsageInputItemKind.UsageInput ||
		         usiObject.kindRef.code == SCUsageInputItemKind.UsageInputInZKU	 ){
		    	 infoDescript = " ������ ��������� ";

		     } else {
		    	 infoDescript = " ������ ���  ";
		     }




  	         // I �������� �� �� ������
		    FKTrans2AXTransItemShortList fkTrans2axTransList = this.getDataForProvsSalaryByOZ(oz.code, ui.iszku , usiObject.kindRef.code);

	        if(fkTrans2axTransList.get(0).balCeh == null || fkTrans2axTransList.get(0).balCeh.equals("") ){
	        	SCUsageInputItemOZ2ENActFilter oz2actFilter = new SCUsageInputItemOZ2ENActFilter();

	        	oz2actFilter.conditionSQL = " scusageinputitemoz2nct.enactrefcode in (  select u2a.enactrefcode from scusageinputitemoz2nct u2a where u2a.usageinputitemozrefcod = "+ oz.code+" \n" +
						"  union  \n" +
						"  select a2p.actrefcode from scusageinputtmz2sccntr oz2sc, sccounter sc , enestimateitem ei , enact2enplanwork a2p \n" +
						"  where oz2sc.ozrefcode = "+ oz.code+"  \n" +
						"  and sc.code = oz2sc.sccounterrefcode \n" +
						"  and sc.estimateitemrefcode = ei.code  \n" +
						"  and ei.planrefcode = a2p.plancode )       ";
	        	SCUsageInputItemOZ2ENActShortList oz2actList = oz2actDAO.getScrollableFilteredList(oz2actFilter, 0, -1);
	        	for (int a = 0; a < oz2actList.totalCount ; a++) {
	        		this.recalcENAct2HumenCehIdbyAct(oz2actList.get(a).enActRefCode); // ���� ������ ��� ������������ � ������� ������ ������
				}

	        	  fkTrans2axTransList = this.getDataForProvsSalaryByOZ(oz.code, ui.iszku , usiObject.kindRef.code);
	         }
	          // ���� �������� �� ���������� ��� - ����� ������
	          if(fkTrans2axTransList.get(0).balCeh == null || fkTrans2axTransList.get(0).balCeh.equals("") ){
	        	  throw new EnergyproSystemException(" \n ��� �������� �� ��������� ��� ���� !!! ������� ������� ���������� ���� !!! ");
	          }

	          BigDecimal zpByAct = new BigDecimal(0);
	          for (int i = 0; i < fkTrans2axTransList.totalCount; i++) {

	        	  if(usiObject.kindRef.code == SCUsageInputItemKind.UsageInputZKU ){ // !!!!!!!!  ���� ��������� ��� �� ��� ��� ��� ��� ���
	        		  kodPodr = fkTrans2axTransList.get(i).korCeh;
	        	  }


	        	  zpByAct = zpByAct.add(fkTrans2axTransList.get(i).amountCur);
	        	  V_Prov_Buffer = new String(V_Prov_Buffer.length()>0 ? V_Prov_Buffer + " UNION ALL " : "")  +	fpLogic.generateQueryTransSHABLON(
	        		ui.dateGen,              // ���� ��������� (���� ��� ���������� �� ������� �������� ��� )
	        		fkTrans2axTransList.get(i).amountCur ,          //����� � �������
	        		fkTrans2axTransList.get(i).balCeh ,
	          		"6610" ,           // ���������� ����
	          		"00000000130",     // ����������  ���
	          		kodPodr,           // ����.��� ( ��� ���� ��� 000 ���� ������������� ��� )
	          		IIII ,             // ����.����
	             	// ����.���
	          		BBBBBBBBBBB	,
	        		oz.numberDoc  ,
	          		ui.dateGen ,
	          		"to_net_oz ��� � "+oz.numberDoc+", "+ new SimpleDateFormat("dd.MM.yyyy").format(ui.dateGen) + infoDescript,
	          		userName
			            );
				}
	       // I �������� �� �� �������, �������� ����� �� � ������������ �� ��� ����������   � ���
	           fkTrans2axTransList = this.getDataForProvsSalaryByOZRed(oz.code, ui.iszku , usiObject.kindRef.code );
	           for (int ir = 0; ir < fkTrans2axTransList.totalCount; ir++) {
		        	  V_Prov_Buffer = new String(V_Prov_Buffer.length()>0 ? V_Prov_Buffer + " UNION ALL " : "")  +	fpLogic.generateQueryTransSHABLON(
		        		ui.dateGen,              // ���� ���������
		        		fkTrans2axTransList.get(ir).amountCur.negate() ,          //����� � �������
		        		fkTrans2axTransList.get(ir).balCeh ,
		          		"6610" ,           // ���������� ����
		          		"00000000130",             // ����������  ���
		          		fkTrans2axTransList.get(ir).balCeh,                    // �����������������  ���
		          		fkTrans2axTransList.get(ir).balans.substring(0, 4) ,            // �����������������  ���� ��� ���������� ����
		             	// �����������������  ���
		          		fkTrans2axTransList.get(ir).balans.substring(4, 15)
		        		,
		        		oz.numberDoc  ,
		          		ui.dateGen ,
		          		"to_net_oz ��� � "+oz.numberDoc +", "+ new SimpleDateFormat("dd.MM.yyyy").format(ui.dateGen) + infoDescript,
		          		userName
				            );
					}

	            // II ����� �������� ������ ������ �� ������������'������ �������� ��������� ����������� - ������
	            fkTrans2axTransList = this.getDataForBlackProvs�hargesumByOZ(oz.code, ui.iszku , usiObject.kindRef.code );
		           for (int c = 0; c < fkTrans2axTransList.totalCount; c++) {
		        	   V_Prov_Buffer = new String(V_Prov_Buffer.length()>0 ? V_Prov_Buffer + " UNION ALL " : "")  +	fpLogic.generateQueryTransSHABLON(
		   	        		ui.dateGen,              // ���� ���������
		   	        		fkTrans2axTransList.get(c).amountCur ,          //����� � �������
		   	        		fkTrans2axTransList.get(c).balCeh ,
		   	        		fkTrans2axTransList.get(c).chargerefcode == FINChargeType.IS_NOT_INVALID ? "6510"  : "6512",           // ���������� ����
		   	          		"00000000130",             // ����������  ���
		   	          		kodPodr,                   // �����������������  ���
		   	             	IIII ,             // �����������������  ����
		   	          // �����������������  ���  09.04.2021 ���  65 ����� ������ ���� ��� 2
		   	             new String((IIII.substring(0, 4).substring(0, 3).equals("232") || 
		   	            		IIII.substring(0, 4).equals("9100") ||
		   	            		IIII.substring(0, 4).equals("9200") ) 
			      	          	   ? "2"+BBBBBBBBBBB.substring(1)  
			      	          	 : BBBBBBBBBBB ) , 
		   	        		oz.numberDoc ,
		   	          		ui.dateGen ,
		   	          		"to_net_oz ��� � "+ oz.numberDoc+", "+ new SimpleDateFormat("dd.MM.yyyy").format(ui.dateGen) + infoDescript,
		   	          		userName
		   			            );
		           }

		        // ������ ������ �� ������������'������ �������� ��������� ����������� - �������  , �������� �� ����  , ��� , ���� ��������� - ������� / �� �������
		            fkTrans2axTransList = this.getDataForRedProvs�hargesumByOZ(oz.code, ui.iszku , usiObject.kindRef.code );
			           for (int c = 0; c < fkTrans2axTransList.totalCount; c++) {
			        	  if (fkTrans2axTransList.get(c).amountCur.doubleValue() > 0 ){
			        		  V_Prov_Buffer = new String(V_Prov_Buffer.length()>0 ? V_Prov_Buffer + " UNION ALL " : "")  +	fpLogic.generateQueryTransSHABLON(
					   	        		ui.dateGen,              // ���� ���������
					   	        		fkTrans2axTransList.get(c).amountCur.negate() ,          //����� � �������
					   	        		fkTrans2axTransList.get(c).balCeh ,
					   	        		fkTrans2axTransList.get(c).chargerefcode == FINChargeType.IS_NOT_INVALID ? "6510"  : "6512",           // ���������� ����
					   	          		"00000000130",                                                 // ����������  ���
					   	          	    fkTrans2axTransList.get(c).balCeh,                             // �����������������  ���
					   	             	fkTrans2axTransList.get(c).balans.substring(0, 4) ,            // �����������������  ���� ��� ���������� ����
					             	  // �����������������  ���
					          	    	 
					   	             new String((fkTrans2axTransList.get(c).balans.substring(0, 4).substring(0, 3).equals("232") || 
						      	          		fkTrans2axTransList.get(c).balans.substring(0, 4).equals("9100") ||
						      	          		fkTrans2axTransList.get(c).balans.substring(0, 4).equals("9200") ) 
						      	          	   ? "2"+fkTrans2axTransList.get(c).balans.substring(4, 15).substring(1, fkTrans2axTransList.get(c).balans.substring(4, 15).length())  
						      	          	 : fkTrans2axTransList.get(c).balans.substring(4, 15) )
					   	             	
					   	        		,
					   	        		oz.numberDoc  ,
					   	          		ui.dateGen ,
					   	          		"to_net_oz ��� � "+oz.numberDoc+", "+ new SimpleDateFormat("dd.MM.yyyy").format(ui.dateGen) + infoDescript,
					   	          		userName
					   			            );
			        	      }

			           }

			}
		// ��������� �������� d� ������ ��� !!!!!!!!!!!!!!!!!!!!!!!!!!!!
				if ( ui.iszku == 1 ){
					String  kodPodr = "";
					// ��������� ��� ���� �� �������� mdax
					if( usiObject.kindRef.code == SCUsageInputItemKind.UsageInput){
					 // ��� ������������� � ������� �������� ���
						SCCounterFilter cFil = new SCCounterFilter();
                        cFil.conditionSQL = " sccounter.invnumber is not null and sccounter.code in ( select qq.sccounterrefcode from scusageinputtmz2sccntr qq where qq.ozrefcode = "+ oz.code +" )";
						int[] cArr = cDAO.getFilteredCodeArray(cFil, 0, -1);
						if(cArr.length == 0 ){
							if(isAbonCounter) {
								kodPodr = mdLogic.getMAINORGANIZATIONIDByHRMORGANIZATIONID(ui.department.hrmorganizationid, ui.dateGen);
							}
						} else {
							SCCounter cObj = cDAO.getObject(cArr[0]);
							// kodPodr = cObj.departmetFKCode; 04.10.2017
							kodPodr = mdLogic.getMAINORGANIZATIONIDByHRMORGANIZATIONID(cObj.departmetFKCode, ui.dateGen);
						}
					} else if (	usiObject.kindRef.code == SCUsageInputItemKind.UsageInputZKU  ){
						// ������������� ������������ �� ���� ��� ���
						kodPodr = "0"+usiObject.molCode.substring(0, 2);
					} else {
						kodPodr = mdLogic.getMAINORGANIZATIONIDByHRMORGANIZATIONID(ui.department.hrmorganizationid, ui.dateGen);
					}



					if(kodPodr.equals("") ){
						throw new EnergyproSystemException(" \n ��� �������� �������� �� ��������� ��� ���� �� �� !!! \n ui.department.hrmorganizationid = " + ui.department.hrmorganizationid  );
					}

				  // -- ���� �� �� ����� � ������������ �������� �� ��� ���� ������� �� �������� ������� ��������  ,, 1-� ���� ��� =1 , 2-� ���� ��� = (���� ������� �� = 5 ������ 4)
			         // -- ���� �� �� ����� � ������������ ��� �� ��� ���� = 1531 ,, 1-� ���� ��� = 2 , 2 ���� ��� = (���� ������� �� = 5 ������ 4)
				     String infoDescript;

				     if( usiObject.kindRef.code == SCUsageInputItemKind.UsageInput ||
				         usiObject.kindRef.code == SCUsageInputItemKind.UsageInputInZKU	 ){
				    	 infoDescript = " ������ ��������� ";

				     } else {
				    	 infoDescript = " ������ ��� ";
				     }




		  	         // I �������� �� �� ������
				    FKTrans2AXTransItemShortList fkTrans2axTransList = this.getDataForProvsSalaryByOZ(oz.code , ui.iszku , usiObject.kindRef.code);
				    if(fkTrans2axTransList.totalCount == 0) {
			        	  throw new EnergyproSystemException(" \n ��� ������ �� �� ��� ������������ ��������  getDataForProvsSalaryByOZ for oz code =  " + oz.code
			        			   + " ui.iszku =  " + ui.iszku  
			        			   + " usiObject.kindRef.code = " + usiObject.kindRef.code );
			          }
			        if(fkTrans2axTransList.get(0).balCeh == null || fkTrans2axTransList.get(0).balCeh.equals("") ){
			        	SCUsageInputItemOZ2ENActFilter oz2actFilter = new SCUsageInputItemOZ2ENActFilter();
			        	oz2actFilter.conditionSQL = " scusageinputitemoz2nct.enactrefcode in (  select u2a.enactrefcode from scusageinputitemoz2nct u2a where u2a.usageinputitemozrefcod = "+ oz.code+" \n" +
						"  union  \n" +
						"  select a2p.actrefcode from scusageinputtmz2sccntr oz2sc, sccounter sc , enestimateitem ei , enact2enplanwork a2p \n" +
						"  where oz2sc.ozrefcode = "+ oz.code+"  \n" +
						"  and sc.code = oz2sc.sccounterrefcode \n" +
						"  and sc.estimateitemrefcode = ei.code  \n" +
						"  and ei.planrefcode = a2p.plancode )       ";
			        	SCUsageInputItemOZ2ENActShortList oz2actList = oz2actDAO.getScrollableFilteredList(oz2actFilter, 0, -1);
			        	for (int a = 0; a < oz2actList.totalCount ; a++) {
			        		this.recalcENAct2HumenCehIdbyAct(oz2actList.get(a).enActRefCode); // ���� ������ ��� ������������ � ������� ������ ������
						}

			        	  fkTrans2axTransList = this.getDataForProvsSalaryByOZ(oz.code , ui.iszku , usiObject.kindRef.code );
			         }
			          // ���� �������� �� ���������� ��� - ����� ������
			          if(fkTrans2axTransList.get(0).balCeh == null || fkTrans2axTransList.get(0).balCeh.equals("") ){
			        	  throw new EnergyproSystemException(" \n ��� �������� �� ��������� ��� ���� !!! ������� ������� ���������� ���� !!! ");
			          }

			          BigDecimal zpByAct = new BigDecimal(0);
			          for (int i = 0; i < fkTrans2axTransList.totalCount; i++) {

			        	  if(usiObject.kindRef.code == SCUsageInputItemKind.UsageInputZKU ){ // !!!!!!!!  ���� ��������� ��� �� ��� ��� ��� ��� ���
			        		  kodPodr = fkTrans2axTransList.get(i).korCeh;
			        		  kodPodr = mdLogic.getMAINORGANIZATIONIDByHRMORGANIZATIONID(kodPodr, ui.dateGen);
			        	  }

			        	  zpByAct = zpByAct.add(fkTrans2axTransList.get(i).amountCur);
			        	  V_Prov_Buffer = new String(V_Prov_Buffer.length()>0 ? V_Prov_Buffer + " UNION ALL " : "")  +	fpLogic.generateQueryTransSHABLON(
			        		ui.dateGen,              // ���� ���������
			        		fkTrans2axTransList.get(i).amountCur ,          //����� � �������
			        		fkTrans2axTransList.get(i).balCeh ,
			          		"6610" ,           // ���������� ����
			          		"00000000130",     // ����������  ���
			          		kodPodr,           // ����.��� ( ��� ���� ��� 000 ���� ������������� ��� )
			          		IIII ,             // ����.����
			             	// ����.���
			          		BBBBBBBBBBB	,
			        		oz.numberDoc  ,
			          		ui.dateGen ,
			          		"to_net_oz ��� � "+oz.numberDoc+", "+ new SimpleDateFormat("dd.MM.yyyy").format(ui.dateGen) + infoDescript,
			          		userName
					            );
						}
			       // I �������� �� �� �������, �������� ����� �� � ������������ �� ��� ����������   � ���
			           fkTrans2axTransList = this.getDataForProvsSalaryByOZRed(oz.code , ui.iszku , usiObject.kindRef.code );
			           for (int ir = 0; ir < fkTrans2axTransList.totalCount; ir++) {
				        	  V_Prov_Buffer = new String(V_Prov_Buffer.length()>0 ? V_Prov_Buffer + " UNION ALL " : "")  +	fpLogic.generateQueryTransSHABLON(
				        		ui.dateGen,              // ���� ���������
				        		fkTrans2axTransList.get(ir).amountCur.negate() ,          //����� � �������
				        		fkTrans2axTransList.get(ir).balCeh ,
				          		"6610" ,           // ���������� ����
				          		"00000000130",             // ����������  ���
				          		fkTrans2axTransList.get(ir).balCeh,                    // �����������������  ���
				          		fkTrans2axTransList.get(ir).balans.substring(0, 4) ,            // �����������������  ���� ��� ���������� ����
				             	// �����������������  ���
				          		fkTrans2axTransList.get(ir).balans.substring(4, 15)
				        		,
				        		oz.numberDoc  ,
				          		ui.dateGen ,
				          		"to_net_oz ��� � "+oz.numberDoc +", "+ new SimpleDateFormat("dd.MM.yyyy").format(ui.dateGen) + infoDescript,
				          		userName
						            );
							}

			            // II ����� �������� ������ ������ �� ������������'������ �������� ��������� ����������� - ������
			         fkTrans2axTransList = this.getDataForBlackProvs�hargesumByOZ(oz.code, ui.iszku , usiObject.kindRef.code );
				           for (int c = 0; c < fkTrans2axTransList.totalCount; c++) {

				        	   if(usiObject.kindRef.code == SCUsageInputItemKind.UsageInputZKU ){ // !!!!!!!!  ���� ��������� ��� �� ��� ��� ��� ��� ���
					        		  kodPodr = fkTrans2axTransList.get(c).korCeh;
					        		  kodPodr = mdLogic.getMAINORGANIZATIONIDByHRMORGANIZATIONID(kodPodr, ui.dateGen);
					        	  }

				        	   V_Prov_Buffer = new String(V_Prov_Buffer.length()>0 ? V_Prov_Buffer + " UNION ALL " : "")  +	fpLogic.generateQueryTransSHABLON(
				   	        		ui.dateGen,              // ���� ���������
				   	        		fkTrans2axTransList.get(c).amountCur ,          //����� � �������
				   	        		fkTrans2axTransList.get(c).balCeh ,
				   	        		fkTrans2axTransList.get(c).chargerefcode == FINChargeType.IS_NOT_INVALID ? "6510"  : "6512",           // ���������� ����
				   	          		"00000000130",             // ����������  ���
				   	          		kodPodr,                   // �����������������  ���
				   	             	IIII ,             // �����������������  ����
				   	             // �����������������  ���  09.04.2021 ���  65 ����� ������ ���� ��� 2
					   	             new String((IIII.substring(0, 4).substring(0, 3).equals("232") || 
					   	            		IIII.substring(0, 4).equals("9100") ||
					   	            		IIII.substring(0, 4).equals("9200") ) 
						      	          	   ? "2"+BBBBBBBBBBB.substring(1)  
						      	          	 : BBBBBBBBBBB ) , 
				   	        		oz.numberDoc ,
				   	          		ui.dateGen ,
				   	          		"to_net_oz ��� � "+ oz.numberDoc+", "+ new SimpleDateFormat("dd.MM.yyyy").format(ui.dateGen) + infoDescript,
				   	          		userName
				   			            );
				           }

				        // ������ ������ �� ������������'������ �������� ��������� ����������� - �������  , �������� �� ����  , ��� , ���� ��������� - ������� / �� �������
				            fkTrans2axTransList = this.getDataForRedProvs�hargesumByOZ(oz.code, ui.iszku , usiObject.kindRef.code );
					           for (int c = 0; c < fkTrans2axTransList.totalCount; c++) {
					        	   V_Prov_Buffer = new String(V_Prov_Buffer.length()>0 ? V_Prov_Buffer + " UNION ALL " : "")  +	fpLogic.generateQueryTransSHABLON(
					   	        		ui.dateGen,              // ���� ���������
					   	        		fkTrans2axTransList.get(c).amountCur.negate() ,          //����� � �������
					   	        		fkTrans2axTransList.get(c).balCeh ,
					   	        		fkTrans2axTransList.get(c).chargerefcode == FINChargeType.IS_NOT_INVALID ? "6510"  : "6512",           // ���������� ����
					   	          		"00000000130",                                                 // ����������  ���
					   	          	    fkTrans2axTransList.get(c).balCeh,                             // �����������������  ���
					   	             	fkTrans2axTransList.get(c).balans.substring(0, 4) ,            // �����������������  ���� ��� ���������� ����
					             	  // �����������������  ���
					   	             new String((fkTrans2axTransList.get(c).balans.substring(0, 4).substring(0, 3).equals("232") || 
						      	          		fkTrans2axTransList.get(c).balans.substring(0, 4).equals("9100") ||
						      	          		fkTrans2axTransList.get(c).balans.substring(0, 4).equals("9200") ) 
						      	          	   ? "2"+fkTrans2axTransList.get(c).balans.substring(4, 15).substring(1, fkTrans2axTransList.get(c).balans.substring(4, 15).length())  
						      	          	 : fkTrans2axTransList.get(c).balans.substring(4, 15) ) 
					   	        		,
					   	        		oz.numberDoc  ,
					   	          		ui.dateGen ,
					   	          		"to_net_oz ��� � "+oz.numberDoc+", "+ new SimpleDateFormat("dd.MM.yyyy").format(ui.dateGen) + infoDescript,
					   	          		userName
					   			            );
					           }

					}

			provResult = new FKProvResult();
			provResult =  fpLogic.createPostings(V_Prov_Buffer);
			String badprovstring = "";
			if (provResult.badProvList.totalCount > 0 ) {
         	badprovstring="  ������ �� :  ";
         	for (int b = 0; b < provResult.badProvList.totalCount; b++) {

         		badprovstring = badprovstring
         				+  " ����� ������ = " + provResult.badProvList.get(b).getDetailedList().get(0).err_mes
         				+  " \n ����������  = " + provResult.badProvList.get(b).primechan
         				+    " \n  bal_ceh " + provResult.badProvList.get(b).bal_ceh
         				+    "  bal_kau " + provResult.badProvList.get(b).bal_kau
         				+    "  bal_sch " + provResult.badProvList.get(b).bal_sch
         				+    "    bal_sub_sch " + provResult.badProvList.get(b).bal_sub_sch
         				+    " \n  kor_ceh " + provResult.badProvList.get(b).kor_ceh
         				+    "    kor_kau " + provResult.badProvList.get(b).kor_kau
         				+    "    kor_sch " + provResult.badProvList.get(b).kor_sch
         				+    "    kor_sub_sch " + provResult.badProvList.get(b).kor_sub_sch;
				}

         	System.out.print("\n \n \n hernya partid badprovstring =  error provResult = " +  badprovstring );
         }


		    return provResult;
   }
   catch (DatasourceConnectException e) {throw new EnergyproSystemException("�� ������� �������� ��������!",e);}
   catch (PersistenceException e)       {throw new EnergyproSystemException(e.getMessage(),e);}
	 // catch (ParseException e)             {throw new EnergyproSystemException(e.getMessage(),e);}

   finally
   {

       try {
           if (finConn != null && ! finConn.isClosed()) {
               finConn.close();
               finConn = null;
           }
       } catch (SQLException e) {
       }
   }
}




    public ActLogic(Connection connection, UserProfile userProfile)
    {
        super(connection, userProfile);
    }

    public class DataForTransAmortizacRed
    {

        String ceh;
        String kodZatrat;
        String vidOS;
        String balSch;
        BigDecimal summa;

        public DataForTransAmortizacRed(String vCeh,  String vKodZatrat,  String vVidOS, String vBalSch , BigDecimal vSumma)
        {
        	ceh = vCeh;
        	kodZatrat = vKodZatrat;
        	vidOS = vVidOS;
        	balSch = vBalSch;
        	summa = vSumma;
        }

        @Override
		public int hashCode()
        {
            // return agree_code.hashCode();
            return (ceh + kodZatrat+vidOS+balSch).hashCode();
        }

        @Override
		public boolean equals(Object obj)
        {
            if (obj instanceof DataForTransAmortizacRed)
            {
            	DataForTransAmortizacRed other = (DataForTransAmortizacRed)obj;
                // return this.agree_code.equals(other.agree_code);
            	return this.ceh.equals(other.ceh) && this.kodZatrat.equals(other.kodZatrat) &&  this.vidOS.equals(other.vidOS) &&  this.balSch.equals(other.balSch);
            }
            else
            {
                return false;
            }
        }
    }

    public class DataForTransAmortizacBlack
    {

        String ceh;
        String vidOS;
        String balSch;
        BigDecimal summa;

        public DataForTransAmortizacBlack(String vCeh,  String vVidOS, String vBalSch , BigDecimal vSumma)
        {
        	ceh = vCeh;
        	vidOS = vVidOS;
        	balSch = vBalSch;
        	summa = vSumma;
        }

        @Override
		public int hashCode()
        {
            // return agree_code.hashCode();
            return (ceh + vidOS + balSch ).hashCode();
        }

        @Override
		public boolean equals(Object obj)
        {
            if (obj instanceof DataForTransAmortizacBlack)
            {
            	DataForTransAmortizacBlack other = (DataForTransAmortizacBlack)obj;
                // return this.agree_code.equals(other.agree_code);
            	return this.ceh.equals(other.ceh) &&  this.vidOS.equals(other.vidOS) &&  this.balSch.equals(other.balSch) ;
            }
            else
            {
                return false;
            }
        }
    }


    public void recalcENAct2HumenCehIdbyAct(int actCode )
	{
		PreparedStatement statement = null;
        ResultSet  resultSet = null;

        Connection fkConnection = null;


        try {
        	fkConnection = getNEWConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            FINLogic finLogic = new FINLogic(fkConnection, userProfile);
        	ENAct2HumenDAO a2hDAO = new ENAct2HumenDAO(connection, userProfile);

        String sql = " select distinct a2h.tabnumber , a.dategen from enact2humen a2h , enact a \n"+
        		" where a2h.actrefcode = a.code \n"+
        		" and a.code = " + actCode  +
        		// " and a2h.cehid is null \n"+
        		" group by a2h.tabnumber , a.dategen \n"+
        		" order by a.dategen  ";


        statement = getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE).prepareStatement(sql);
        resultSet = statement.executeQuery();

        while(resultSet.next()) {

    		ENAct2Humen a2hObj = finLogic.getBalansWithMainCeh(resultSet.getString(1), resultSet.getDate(2));
    	    		String balans = a2hObj.balans;
    	    		String cehId = a2hObj.cehId;

    	    ENAct2HumenFilter a2hFil = new ENAct2HumenFilter();
    	    a2hFil.conditionSQL = " enact2humen.code in ( select a2h.code from enact2humen a2h , enact a " +
					              " where a2h.actrefcode = a.code " +
					              " and a.dategen = '"+ resultSet.getDate(2) +"'" +
					              " and a2h.tabnumber = '"+ resultSet.getString(1) +"' )";
    	    int[] a2hArr = a2hDAO.getFilteredCodeArray(a2hFil, 0, -1);
    	    if (a2hArr.length > 0 ){
    	    	for (int j = 0; j < a2hArr.length; j++) {
    	    		ENAct2Humen a2hObjUpd = a2hDAO.getObject(a2hArr[j]);
    	    		a2hObjUpd.cehId = cehId;
    	    		a2hObjUpd.balans = balans;
    	    		a2hDAO.save(a2hObjUpd);
    	    		System.out.println(" recalcENAct2HumenCehIdbyAct enact2humen.code = " + a2hArr[j] + " tabn =" + resultSet.getString(1) + " cehid = " + cehId );
				}
    	    }
        }
        }
        catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
            throw new SystemException(e) ;
        }


	}

    public void recalcenplanworkitem2humenbyAct(int actCode )
   	{
   		   PreparedStatement statement = null;
           ResultSet  resultSet = null;

           Connection fkConnection = null;


           try {
           	fkConnection = getNEWConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
               FINLogic finLogic = new FINLogic(fkConnection, userProfile);
               ENPlanWorkItem2HumenDAO pi2hDAO = new ENPlanWorkItem2HumenDAO(connection, userProfile);

           String sql = " select distinct pi2h.tabnumber , a.dategen  from  enact2enplanwork a2p ,  enplanworkitem pii , enplanworkitem2humen pi2h , enact a " +
        		   "  where a2p.actrefcode = " + actCode  +
        		   " 		 and a2p.plancode = pii.planrefcode " +
        		   " 		 and pi2h.plaitemrefcode = pii.code " +
        		   " 		 and a.code = a2p.actrefcode ";


           statement = getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE).prepareStatement(sql);
           resultSet = statement.executeQuery();

           while(resultSet.next()) {

       		ENAct2Humen a2hObj = finLogic.getBalansWithMainCeh(resultSet.getString(1), resultSet.getDate(2));
       	    		String balans = a2hObj.balans;
       	    		String cehId = a2hObj.cehId;

       	    ENPlanWorkItem2HumenFilter pi2hFil = new ENPlanWorkItem2HumenFilter();
       	    /*pi2hFil.conditionSQL = " ENPLANWORKITEM2HUMEN.code in ( select a2h.code from enact2humen a2h , enact a " +
   					              " where a2h.actrefcode = a.code " +
   					              " and a.dategen = '"+ resultSet.getDate(2) +"'" +
   					              " and a2h.tabnumber = '"+ resultSet.getString(1) +"   ' )";*/

       	 pi2hFil.conditionSQL = " ENPLANWORKITEM2HUMEN.code in (  select pi2h.code from  enact2enplanwork a2p ,  enplanworkitem pii , enplanworkitem2humen pi2h , enact a " +
       	 " where a2p.actrefcode = " + actCode  +
       	 " and a2p.plancode = pii.planrefcode " +
       	 " and pi2h.plaitemrefcode = pii.code " +
       	 " and a.code = a2p.actrefcode " +
       	 " and pi2h.tabnumber = '"+ resultSet.getString(1) +"')";

       	    int[] pi2hArr = pi2hDAO.getFilteredCodeArray(pi2hFil, 0, -1);
       	    if (pi2hArr.length > 0 ){
       	    	for (int j = 0; j < pi2hArr.length; j++) {
       	    		ENPlanWorkItem2Humen pi2hObjUpd = pi2hDAO.getObject(pi2hArr[j]);
       	    		//if(pi2hObjUpd.cehId.equalsIgnoreCase("") || pi2hObjUpd.balans.equalsIgnoreCase("") ){
       	    			pi2hObjUpd.cehId = cehId;
           	    		pi2hObjUpd.balans = balans;
           	    		pi2hDAO.save(pi2hObjUpd);
           	    		System.out.println(" recalcenplanworkitem2humenbyAct lanworkitem2humen.code = " + pi2hArr[j] + " tabn =" + resultSet.getString(1) + " cehid = " + cehId );
       	    		//}

   				}
       	    }
           }
           }
           catch (Exception e) {
               System.out.println("Error :" + e.getMessage());
               throw new SystemException(e) ;
           }


   	}

    public void generateCountersStateVerification(int actCode, int fkOrderCode) {

    	if (actCode == Integer.MIN_VALUE) {
    		throw new SystemException("\n\nNET-4559 �� ������� ��� ����������� ����!");
    	}

    	if (fkOrderCode == Integer.MIN_VALUE) {
    		throw new SystemException("\n\nNET-4559 �� ������� ��� ���� � ������ � �������!");
    	}

    	AuthLogic authLogic = new AuthLogic(connection, userProfile);

    	if (! authLogic.checkPermissionSilent("ksoe/energynet/ENCountersStateVerificationController", "add"))
    	{
    		throw new SystemException("\n\nNET-4559 � ��� ���� ���� �� �� ��������!");
    	}

    	try {

	    	ENPlanWorkDAO planDAO = new ENPlanWorkDAO(connection, userProfile);
	    	RQFKOrderDAO fkOrderDAO = new RQFKOrderDAO(connection, userProfile);
	    	ENMetrologyCounterDAO counterDAO = new ENMetrologyCounterDAO(connection, userProfile);
	    	ENCountersStateVerificationDAO stateVerificationDAO = new ENCountersStateVerificationDAO(connection, userProfile);

	    	ENPlanWorkFilter planFilter = new ENPlanWorkFilter();
	    	planFilter.conditionSQL =
				" code in " +
				" ( " +
				"   select ap.plancode " +
				"   from enact2enplanwork ap " +
				"   where ap.actrefcode = " + actCode +
				" ) ";

	    	int[] planArr = planDAO.getFilteredCodeArrayNOSEGR(planFilter, 0, -1);

	    	if (planArr.length == 0) {
	    		throw new SystemException("\n\nNET-4559 � ���������� ��� � ����� " + actCode + " �� �������� ������� ��������-�����!");
	    	}

	    	RQFKOrder fkOrder = fkOrderDAO.getObjectNOSEGR(fkOrderCode);

	    	if (fkOrder == null) {
	    		throw new SystemException("\n\nNET-4559 �� �������� ��� � ������ � ������� � ����� " + fkOrderCode + " !");
	    	}

	    	if (fkOrder.kind.code != RQFKOrderKind.SERVICES_FROM_SIDE) {
	    		throw new SystemException("\n\nNET-4559 �������� (�����) � ����� " + fkOrderCode + " �� � ����� � ������ � �������!");
	    	}

	    	if (fkOrder.status.code != RQFKOrderStatus.CREATED) {
	    		throw new SystemException("\n\nNET-4559 ��� � ������ � ������� ������� ���� � ������ \"���������\"! ��� ����: " +
	    				fkOrderCode);
	    	}

	    	if (fkOrder.sumWithoutNds == null) {
	    		throw new SystemException("\n\nNET-4559 � ��� � ������ � ������� � ����� " + fkOrderCode + " �� ������� ���� ��� ���!");
	    	}

	    	// ���� �� ���� ������� ����������, �������� ����� ����� �� ���� �� ������ �� ������� �� ���-�� ��������� (������)
	    	BigDecimal counterPrice = fkOrder.sumWithoutNds.divide(new BigDecimal(planArr.length), 2, BigDecimal.ROUND_HALF_UP);

	    	for (int i = 0; i < planArr.length; i++) {

	    		ENPlanWork plan = planDAO.getObjectNOSEGR(planArr[i]);

	    		ENMetrologyCounterFilter counterFilter = new ENMetrologyCounterFilter();
	    		counterFilter.element.code = plan.elementRef.code;
	    		int[] counterArr = counterDAO.getFilteredCodeArray(counterFilter, 0, -1);

		    	if (counterArr.length == 0) {
		    		throw new SystemException("\n\nNET-4559 ������� ������ ��������� ��� ����� � ����� " + planArr[i] + " !");
		    	}

	    		ENMetrologyCounter counter = counterDAO.getObject(counterArr[0]);

		    	if (counter == null) {
		    		throw new SystemException("\n\nNET-4559 ������� ������ ��������� ��� ����� � ����� " + planArr[i] + " !");
		    	}

	    		ENCountersStateVerification stateVerification = new ENCountersStateVerification();
	    		stateVerification.invNumber = counter.invNumber;
	    		stateVerification.buildNumber = counter.buildNumber;
	    		stateVerification.name = counter.name;
	    		stateVerification.planRef.code = planArr[i];
	    		stateVerification.actRef.code = actCode;
	    		stateVerification.fkOrderRef.code = fkOrderCode;
	    		stateVerification.priceGen = counterPrice;
	    		stateVerificationDAO.add(stateVerification);

	    	}

    	} catch (PersistenceException e) {

    		throw new SystemException(e.getMessage(), e);

    	}

    }

    public boolean checkCountersStateVerificationForAct(int actCode) {

    	try {

	    	ENCountersStateVerificationDAO stateVerificationDAO = new ENCountersStateVerificationDAO(connection, userProfile);

    		ENCountersStateVerificationFilter stateVerificationFilter = new ENCountersStateVerificationFilter();
    		stateVerificationFilter.actRef.code = actCode;

    		int[] stateVerificationArr = stateVerificationDAO.getFilteredCodeArray(stateVerificationFilter, 0, -1);

    		return (stateVerificationArr.length > 0);

    	} catch (PersistenceException e) {

    		throw new SystemException(e.getMessage(), e);

    	}

    }

    public boolean checkCountersStateVerificationForFKOrder(int fkOrderCode) {

    	try {

	    	ENCountersStateVerificationDAO stateVerificationDAO = new ENCountersStateVerificationDAO(connection, userProfile);

    		ENCountersStateVerificationFilter stateVerificationFilter = new ENCountersStateVerificationFilter();
    		stateVerificationFilter.fkOrderRef.code = fkOrderCode;

    		int[] stateVerificationArr = stateVerificationDAO.getFilteredCodeArray(stateVerificationFilter, 0, -1);

    		return (stateVerificationArr.length > 0);

    	} catch (PersistenceException e) {

    		throw new SystemException(e.getMessage(), e);

    	}

    }

    public boolean checkActTypeForCountersStateVerification(ENAct act) {

    	boolean result = false;

    	if (act.element.typeRef.code == ENElementType.METROLOGY_OBJECT) {

    		if (act.actTypeRef.code == ENPlanWorkState.CURRENT_REPAIR ||
    			act.actTypeRef.code == ENPlanWorkState.REFINEMENT ||
      			act.actTypeRef.code == ENPlanWorkState.COUNTERS_STATE_VERIFICATION ||
      			act.actTypeRef.code == ENPlanWorkState.COUNTERS_EXPERTISE) {

    			result = true;
    		}

    	}

    	return result;
    }

    public ENAct2Humen getBalansWithMainCeh(ENAct act, Connection finConn) {

    	try {

			if ( act.molCodeObject == null || act.molCodeObject == "" ){
				throw new EnergyproSystemException("\n\n�� ���(������� �������) ��������� ������� ��� ��'���� ���� !!! ");
			}

			ENMolDAO molDAO = new ENMolDAO(connection, userProfile);

			ENMolFilter molFil = new ENMolFilter();
			molFil.finCode = act.molCodeObject;
			molFil.statusRef.code = ENMolStatus.VALID;
			int[] enmolArr = molDAO.getFilteredCodeArray(molFil, 0, -1);
			if (enmolArr.length == 0 ){
				throw new EnergyproSystemException("\n\n��������� ������ ���������� �� ���� " + act.molCodeObject + " � ������� (��������-���������-������� ���(����))!!! ");
			}

			ENMol molObj = molDAO.getObject(enmolArr[0]);

			if (molObj.tabNumber == null || molObj.tabNumber.equals("")){
				throw new EnergyproSystemException("\n\n� �������� (��������-���������-������� ���(����)) ��� ���� " + act.molCodeObject + " ��������� ������� ��������� ����� ����������� !!! ");
			}

			FINLogic finLogic = new FINLogic(finConn, userProfile);

			// ������� ��� � ������������� �� ����������
			ENAct2Humen a2hObj = finLogic.getBalansWithMainCeh(molObj.tabNumber, act.dateGen,
					"\n������ ��������� ��� ��'���� ���� �� ��� � " + act.numberGen + " �� " +
					new SimpleDateFormat("dd.MM.yyyy").format(act.dateGen) + " !");

			String balans = a2hObj.balans;
			String cehId = a2hObj.cehId;

			if (balans == null || balans.equals("")){
				throw new EnergyproSystemException("\n\n������� ��� ��������� ��� ���� ��`���� !!!");
			}

			if (cehId == null || cehId.equals("")){
				throw new EnergyproSystemException("\n\n������� ��� ��������� �������� ���� ��`���� !!!");
			}

			return a2hObj;

    	} catch (PersistenceException e) {
    		throw new SystemException(e.getMessage(), e);
    	}
    }


    /**
     *	�������� ������� ��������� ����
     *	@param actCode - ��� ����
     */
	public void checkActIncomeServices(int actCode) {
		try {
			ENActIncomServ2ENActDAO actIncomServ2ENActDao = new ENActIncomServ2ENActDAO(connection, userProfile);
			ENActIncomeServicesDAO actIncomeServicesDao = new ENActIncomeServicesDAO(connection, userProfile);

			ENActIncomServ2ENActFilter actIncomServ2ENActFilter = new ENActIncomServ2ENActFilter();
			actIncomServ2ENActFilter.actRef.code = actCode;

			int aArr[] = actIncomServ2ENActDao.getFilteredCodeArray(actIncomServ2ENActFilter, 0, -1);
			if (aArr.length > 0) {

				ENActIncomServ2ENAct actIncomServ2ENAct = actIncomServ2ENActDao.getObject(aArr[0]);
				ENActIncomeServices actIncomeServices = actIncomeServicesDao.getObject(actIncomServ2ENAct.actIncomeRef.code);

				throw new SystemException("\n\n"
						+ "���������� ��������� ���������!\n"
						+ "��� �������� �� ������������ ���� � = " + actIncomeServices.numberGen
						+ " �� " + new SimpleDateFormat("dd.MM.yyyy").format(actIncomeServices.dateGen) + ".");
			}

		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		}
	}

/* 
 * ����������� �������� ��� ����� 
 * */
	public void setPostingTemplateAndFinKodIstByAct(ENAct actObj , boolean finKodIst ) /*SUPP-102789*/
	{
		LoggableStatement statement = null;
        ResultSet  resultSet = null;
        
        

        try {

        ENAct2ProvDAO a2pDAO = new ENAct2ProvDAO(connection, userProfile);
        ENAct2FinKodIstDAO istDAO = new ENAct2FinKodIstDAO(connection, userProfile);
        SCSealDAO sDAO = new SCSealDAO(connection, userProfile);

        String sql = "      /*����������� ������� ��������*/ \n" +
        		"      select coalesce( postingtemplatecode ,-1) as postingtemplatecode ,finkodist \n" +
        		"      from (   \n" +
        		"       /*������ '���� ������������'  */  \n" +
        		"       select 1 as postingtemplatecode  , -1 as finkodist   \n" +
        		"       from  enservicesobject so , enact a   \n" +
        		"       where so.elementcode = a.elementcode    \n" +
        		"       and a.code = "+ actObj.code +"  \n" +
        		"       AND so.CONTRACTTYPEREFCODE = 8/*������� ������� �� �������*/   \n" +
        		"       AND coalesce(so.contracttyperefcode, -1) <> 5    \n" +
        		"       UNION ALL      \n" +
        		"      Select 1 as postingtemplatecode  , -1 as finkodist   \n" +
        		"      from enact2enplanwork a2p , entechcond2planwork tc2p , enservicesobject2techcondtnsservices s2tc ,  entechconditionsservcs ts, enconnectionkind k , enact act  \n" +
        		"      where a2p.actrefcode =  "+ actObj.code +"   \n" +
        		"      and a2p.plancode= tc2p.planrefcode   \n" +
        		"      and tc2p.techconservicesrefcode = s2tc.techcondservrefcode   \n" +
        		"      and ts.code = s2tc.techcondservrefcode    \n" +
        		"      and ts.connectionkindrefcode = k.code   \n" +
        		"      and a2p.actrefcode=act.code   \n" +
        		"      and act.acttyperefcode in (  29 )        \n" +
        		"       UNION ALL           \n" +
        		"       /*������ '������� �� �������' � '�������� ������' */  \n" +
        		"      select 2 as postingtemplatecode , 4 as finkodist   \n" +
        		"      from tkclassificationtype tct , tktechcard tk , enplanworkitem pi , enplanwork pp , enact a  , enservicesobject so    \n" +
        		"      where tct.finworktypecode <> 25 /*25    �0    ��������� �������������������� ��������*/  \n" +
        		"      and tct.code = tk.classificationtypecode  \n" +
        		"      and pi.kartarefcode=tk.code  \n" +
        		"      and pi.planrefcode=pp.code   \n" +
        		"      and pp.kindcode=4  \n" +
        		"      and pp.elementrefcode=a.elementcode  \n" +
        		"      and so.elementcode=a.elementcode  \n" +
        		"      and so.elementcode=pp.elementrefcode  \n" +
        		"      and coalesce(so.contracttyperefcode, -1) <> 5  \n" +
        		"      and (so.CONTRACTKINDREFCODE = 1/*������� ������� �� �������*/  or so.CONTRACTTYPEREFCODE = 7  /*������� �������� ������*/ )  \n" +
        		"      and a.code = "+ actObj.code +"   \n" +
        		"      and not ( tct.kod  like '2.2.04%' and coalesce(so.isnopay,0) <> 0 )  \n" +
        		"      and not ( tct.kod like '2.1.07%' and coalesce(so.isnopay,0) <> 0 )  \n" +
        		"        \n" +                                                 
        		"       UNION ALL   \n" +
        		"         \n" +
        		"       (/*������ ��� (������ �� ������� ���  ������������) �� ���������*/  \n" +
        		"       select 3 as postingtemplatecode , 3 as finkodist  \n" +
        		"       from enact2enplanwork a2p , entechcond2planwork tc2p , enservicesobject2techcondtnsservices s2tc ,  entechconditionsservcs ts, enconnectionkind k , enact act     \n" +
        		"       where a2p.actrefcode =  "+ actObj.code +"    \n" +
        		"       and a2p.plancode= tc2p.planrefcode    \n" +
        		"       and tc2p.techconservicesrefcode = s2tc.techcondservrefcode    \n" +
        		"       and ts.code = s2tc.techcondservrefcode     \n" +
        		"       and ts.connectionkindrefcode = k.code   \n" +
        		"       and a2p.actrefcode=act.code   \n" +
        		"       and act.acttyperefcode in (  7 )  \n" +
        		"       union all  \n" +
        		"       /*������ ��� (������ �� ������� ���  ������������) �� ���������*/  \n" +
        		"       select 3 as postingtemplatecode  , 3 as finkodist  \n" +
        		"        from enservicesobject so , enact act  ,  enservicesobject2techcondtnsservices so2pric ,    \n" +
        		"        entechconditionsservcs ts, enconnectionkind k      \n" +
        		"        where so.elementcode = act.elementcode     \n" +
        		"        and act.code =   "+ actObj.code +"    \n" +
        		"        and so.code = so2pric.servicesobjectrefcode    \n" +
        		"        and ts.code = so2pric.techcondservrefcode     \n" +
        		"        and ts.connectionkindrefcode = k.code   \n" +
        		"        AND so.CONTRACTTYPEREFCODE = 5    \n" +
        		"        AND so.CONTRACTKINDREFCODE = 1  \n" +
        		"        and act.acttyperefcode in (  7 ) \n" +
        		"        and so.contractdateservices < '01.06.2021' \n" +
        		"       union all  \n" +
        		"       /*������ ��� (������ �� ������� ���  ������������) �� ���������*/  \n" +
        		"       select 3 as postingtemplatecode   , 3 as finkodist   \n" +
        		"       from  ENSOProj2SOConn pfj2so , enservicesobject so , enact act , enservicesobject2techcondtnsservices so2pric , entechconditionsservcs ts, enconnectionkind k    \n" +
        		"       where pfj2so.soprojrefcode = so.code    \n" +
        		"       and so.elementcode = act.elementcode    \n" +
        		"       and act.code =  "+ actObj.code +"     \n" +
        		"       and so2pric.servicesobjectrefcode = pfj2so.soconnrefcode    \n" +
        		"       and ts.code = so2pric.techcondservrefcode      \n" +
        		"       and ts.connectionkindrefcode = k.code  \n" +
        		"       AND so.CONTRACTTYPEREFCODE = 5    \n" +
        		"       AND so.CONTRACTKINDREFCODE = 1   \n" +
        		"       and act.acttyperefcode in (  7 ) \n" +
        		"       and so.contractdateservices < '01.06.2021' \n" +
        		"       )  \n" +
        		"          \n" +
        		"       UNION ALL  \n" +
        		"         \n" +
        		"       /*������ '������� �� �������(��������� �������������������� ��������) ��� ���� � ������ = 0E' */  \n" +
        		"      select 4 as postingtemplatecode , 7 as finkodist  \n" +
        		"      from tkclassificationtype tct , tktechcard tk , enplanworkitem pi , enplanwork pp , enact a  , enservicesobject so    \n" +
        		"      where tct.finworktypecode = 25 /*25    �0    ��������� �������������������� ��������*/  \n" +
        		"      and tct.code = tk.classificationtypecode  \n" +
        		"      and pi.kartarefcode=tk.code  \n" +
        		"      and pi.planrefcode=pp.code   \n" +
        		"      and pp.kindcode=4  \n" +
        		"      and pp.elementrefcode=a.elementcode  \n" +
        		"      and so.elementcode=a.elementcode  \n" +
        		"      and so.elementcode=pp.elementrefcode  \n" +
        		"      and coalesce(so.contracttyperefcode, -1) <> 5  \n" +
        		"      and (so.CONTRACTKINDREFCODE = 1/*������� ������� �� �������*/  or so.CONTRACTTYPEREFCODE = 7  /*������� �������� ������*/ )  \n" +
        		"      and a.code = "+ actObj.code +"   \n" +
        		"        \n" +
        		"         UNION ALL  \n" +
        		"         \n" +
        		"       /*������ ��� ���� ������� �� ������� (���������) ����������� 2.2.04�..(��������������) */  \n" +
        		"      select 5 as postingtemplatecode , -1 as finkodist  \n" +
        		"      from tkclassificationtype tct , tktechcard tk , enplanworkitem pi , enplanwork pp , enact a  , enservicesobject so    \n" +
        		"      where tct.finworktypecode <> 25 /*25    �0    ��������� �������������������� ��������*/  \n" +
        		"      and tct.code = tk.classificationtypecode  \n" +
        		"      and pi.kartarefcode=tk.code  \n" +
        		"      and pi.planrefcode=pp.code   \n" +
        		"      and pp.kindcode=4  \n" +
        		"      and pp.elementrefcode=a.elementcode  \n" +
        		"      and so.elementcode=a.elementcode  \n" +
        		"      and so.elementcode=pp.elementrefcode  \n" +
        		"      and coalesce(so.contracttyperefcode, -1) <> 5  \n" +
        		"      and (so.CONTRACTKINDREFCODE = 1/*������� ������� �� �������*/  or so.CONTRACTTYPEREFCODE = 7  /*������� �������� ������*/ )  \n" +
        		"      and tct.kod like '2.2.04%'  \n" +
        		"      and coalesce(so.isnopay,0) <> 0  \n" +
        		"      and a.code = "+ actObj.code +"  \n" +
        		"      \n" +
        		"      UNION ALL  \n" +
        		"        \n" +
        		"      /*������ ��� ���� ������� �� ������� (���������) ����������� 2.1.07�..(�������������) */  \n" +
        		"      select 6 as postingtemplatecode , -1 as finkodist  \n" +
        		"      from tkclassificationtype tct , tktechcard tk , enplanworkitem pi , enplanwork pp , enact a  , enservicesobject so    \n" +
        		"      where tct.finworktypecode <> 25 /*25    �0    ��������� �������������������� ��������*/  \n" +
        		"      and tct.code = tk.classificationtypecode  \n" +
        		"      and pi.kartarefcode=tk.code  \n" +
        		"      and pi.planrefcode=pp.code   \n" +
        		"      and pp.kindcode=4  \n" +
        		"      and pp.elementrefcode=a.elementcode  \n" +
        		"      and so.elementcode=a.elementcode  \n" +
        		"      and so.elementcode=pp.elementrefcode  \n" +
        		"      and coalesce(so.contracttyperefcode, -1) <> 5  \n" +
        		"      and (so.CONTRACTKINDREFCODE = 1/*������� ������� �� �������*/  or so.CONTRACTTYPEREFCODE = 7  /*������� �������� ������*/ )  \n" +
        		"      and tct.kod like '2.1.07%'  \n" +
        		"      and coalesce(so.isnopay,0) <> 0  \n" +
        		"      and a.code = "+ actObj.code +"  \n" +
        		"       \n" +
        		"      UNION ALL   \n" +
        		"          (/*������� �  ��������� (��������� � 01.06.2021)*/  \n" +
        		"          select 7 as postingtemplatecode  , 3 as finkodist  \n" +
        		"          from enact2enplanwork a2p , entechcond2planwork tc2p , enservicesobject2techcondtnsservices s2tc ,  entechconditionsservcs ts, enconnectionkind k , enact act  \n" +
        		"          where a2p.actrefcode =  "+ actObj.code +"   \n" +
        		"          and a2p.plancode= tc2p.planrefcode   \n" +
        		"          and tc2p.techconservicesrefcode = s2tc.techcondservrefcode   \n" +
        		"          and ts.code = s2tc.techcondservrefcode    \n" +
        		"          and ts.connectionkindrefcode = k.code   \n" +
        		"          and a2p.actrefcode=act.code   \n" +
        		"          and act.acttyperefcode in (   7 )  \n" +
        		"           and ts.contractdate  >= '01.06.2021' \n" +
        		"          union all  \n" +
        		"          /*������� �  ��������� (��������� � 01.06.2021)*/  \n" +
        		"          select 7 as postingtemplatecode  , 3 as finkodist  \n" +
        		"       from enservicesobject so , enact act  ,  enservicesobject2techcondtnsservices so2pric ,   \n" +
        		"       entechconditionsservcs ts, enconnectionkind k   \n" +
        		"       where so.elementcode = act.elementcode     \n" +
        		"       and act.code =   "+ actObj.code +"    \n" +
        		"       and so.code = so2pric.servicesobjectrefcode    \n" +
        		"       and ts.code = so2pric.techcondservrefcode     \n" +
        		"       and ts.connectionkindrefcode = k.code   \n" +
        		"       AND so.CONTRACTTYPEREFCODE = 5    \n" +
        		"       AND so.CONTRACTKINDREFCODE = 1  \n" +
        		"       and act.acttyperefcode in (   7 )  \n" +
        		"      and so.contractdateservices >= '01.06.2021'  \n" +
        		"      union all  \n" +
        		"      /*������� �  ��������� (��������� � 01.06.2021)*/  \n" +
        		"      select 7 as postingtemplatecode   , 3 as finkodist \n" +
        		"      from  ENSOProj2SOConn pfj2so , enservicesobject so , enact act , enservicesobject2techcondtnsservices so2pric , entechconditionsservcs ts, enconnectionkind k  \n" +
        		"      where pfj2so.soprojrefcode = so.code   \n" +
        		"      and so.elementcode = act.elementcode    \n" +
        		"      and act.code =  "+ actObj.code +"     \n" +
        		"      and so2pric.servicesobjectrefcode = pfj2so.soconnrefcode    \n" +
        		"      and ts.code = so2pric.techcondservrefcode   \n" +
        		"      and ts.connectionkindrefcode = k.code  \n" +
        		"      AND so.CONTRACTTYPEREFCODE = 5    \n" +
        		"      AND so.CONTRACTKINDREFCODE = 1   \n" +
        		"      and act.acttyperefcode in (  7 )  \n" +
        		"      and so.contractdateservices >= '01.06.2021'  \n" +
        		"      )  \n" +
        		" UNION/*������� �� ���������� �� �� �� ������ */ \n" + 
        	    "  select 9 as postingtemplatecode   , -1 as finkodist \n" + 
        	    "  from enact act , enactincome2enact ee where act.code = ee.actrefcode and act.code =  "+ actObj.code +"     \n" +
        	    "  UNION \n" +
        	    "   select 10 as postingtemplatecode , 4 /*������ �� �������, ��������� � �������� (������������) ������������� �/� 90 */ as finkodist  \n" +
        	    "   from enact act where act.code in ( select acpw1.actrefcode from enact2enplanwork as acpw1  \n" +
        	    "   inner join enservices2plan as sopl1 on acpw1.plancode = sopl1.planrefcode  \n" +
        	    "   inner join enservicesobject so on sopl1.servicesobjectrefcode = so.code   \n" +
        	    "   where so.contractkindrefcode = " + ENServicesContractKind.SUPPLIER_CONTRACT +" 	/*14 �������� � ��������������� �/�*/ \n" +
        	    "   and acpw1.actrefcode = "+ actObj.code +"  \n" +
        	    "   union  \n" +
        	    "   select elac1.actrefcode from enelement2act as elac1 where elac1.actrefcode =  "+ actObj.code + "  ) \n" +
        	    "   and act.acttyperefcode not in (20, 24) \n" + 
        	    "      ) as sel1 order by coalesce( postingtemplatecode ,-1) desc limit 1   \n" +
        		"        \n" ;

        
        statement = new LoggableStatement(getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE), sql);
        //statement = getConnection(AuthorizationJNDINames.ENERGYNET_DATASOURCE).prepareStatement(sql);
        System.out.println("############### setPostingTemplateAndFinKodIstByAct_sql ############################"
     	        + "Executing query: " + ((LoggableStatement)statement).getQueryString());
        resultSet = statement.executeQuery();

        while(resultSet.next()) {

        	ENAct2ProvFilter a2pFilter = new ENAct2ProvFilter();
        	a2pFilter.actRef.code = actObj.code;
        	
        	// ��� ������� ������� �� ������� � �������� ������ ��������� ����������� ������� ��������� ���� (���� � ��� �������� ����������� 01.10.2021)
        	int isIncomeAct = 0;
        	if (resultSet.getInt(1) == ENActPostingKind.SERVICES_CONTRACT ) {
        		isIncomeAct = 1;
        	}

        	int[] a2pArr = a2pDAO.getFilteredCodeArray(a2pFilter, 0, -1);
        	if (a2pArr.length==0){
        	 ENAct2Prov a2pObj = new ENAct2Prov();
        	 a2pObj.isIncomeAct = Integer.MIN_VALUE;

        	 a2pObj.actRef.code = actObj.code;
        	 a2pObj.actPostingKindRef.code = resultSet.getInt(1);
        	 a2pObj.partId = -1;
        	 a2pObj.isIncomeAct = isIncomeAct;
        	 a2pDAO.add(a2pObj);
        	 	 
        	}
        	else
        	{
        		ENAct2Prov a2pObj = a2pDAO.getObject(a2pArr[0]);
        		a2pObj.actPostingKindRef.code = resultSet.getInt(1);
        		a2pObj.isIncomeAct = isIncomeAct;
        		a2pDAO.save(a2pObj);
        	}
        	
        	
        	
        	 //��������� ��� ������������� 
        	SCSealFilter sFil = new SCSealFilter();
        	sFil.conditionSQL = " scseal.estimateitemrefcode in ( " + 
        			" select ei.code from enplanwork p, enestimateitem ei, " +
        			" enact2enplanwork a2p " +
        			" where p.code = a2p.plancode " +
        			" and ei.planrefcode = p.code " +
        			" and ei.kindrefcode = " +  ENEstimateItemKind.MATERIALS + 
        			" and a2p.actrefcode = " + actObj.code + ')';
        	SCSealShortList sList = sDAO.getScrollableFilteredList(sFil,0,-1);

             
             boolean actHasSeals = (sList.totalCount > 0);
       	 
       	 if (finKodIst && actHasSeals &&  resultSet.getInt(2) > 0 ) {
       		 ENAct2FinKodIstFilter istFil = new ENAct2FinKodIstFilter();
       		 istFil.actRef.code = actObj.code;
       		 int[] istArr = istDAO.getFilteredCodeArray(istFil, 0, -1);
       		if (istArr.length==0){
       			ENAct2FinKodIst ist = new ENAct2FinKodIst();
       			ist.actRef.code = actObj.code;
       			ist.kodIstRef.code = resultSet.getInt(2);
       			istDAO.add(ist);
       		} else 
       		{
       			ENAct2FinKodIst ist = istDAO.getObject(istArr[0]);
       			ist.kodIstRef.code = resultSet.getInt(2);
       			istDAO.save(ist);
       		}
       	 }

        	break;
        }
        }
        catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
            throw new SystemException(e) ;
        }


	}

	public void addENAct2DFDoc(ENAct act, DFDoc doc) {
		if (act == null || act.code <= 0) {
			throw new IllegalArgumentException("\n\nNET-4596 �� ������� ��'��� ����!");
		}
		if (doc == null || doc.code <= 0) {
			throw new IllegalArgumentException("\n\nNET-4596 �� ������� ��'��� ���������!");
		}

		try {
			ENAct2DFDocDAO act2DFDocDao = new ENAct2DFDocDAO(connection, userProfile);
			ENAct2DFDoc act2DFDoc = new ENAct2DFDoc();
			act2DFDoc.actRef.code = act.code;
			act2DFDoc.dfDocCode = doc.code;
			act2DFDoc.dfDocDate = doc.dateRegistration;
			act2DFDoc.dfDocDescription = doc.description;
			act2DFDoc.dfDocNumber = doc.docNum;
			act2DFDoc.dfDocSubtypeCode = doc.docSubTypeRef.code;
			act2DFDoc.dfDocTypeCode = doc.docTypeRef.code;
			act2DFDocDao.add(act2DFDoc);
		} catch (PersistenceException e) {
			throw new SystemException(e);
		}
	}

	public int getDFDocCodeForENAct(int actCode) {
		if (actCode <= 0) {
			throw new IllegalArgumentException("\n\nNET-4596 �� ������� ��� ����!");
		}

		Connection dfConnection = null;

		try {
			ENAct2DFDocDAO act2DocDao = new ENAct2DFDocDAO(connection, userProfile);
			ENAct2DFDocFilter act2DocFilter = new ENAct2DFDocFilter();
			act2DocFilter.actRef.code = actCode;
			int[] act2DocCodes = act2DocDao.getFilteredCodeArray(act2DocFilter, 0, -1);

			if (act2DocCodes.length == 0) {
				return Integer.MIN_VALUE;
			}

			String dfDocCodes = "";
			for (int act2DocCode : act2DocCodes) {
				ENAct2DFDoc act2Doc = act2DocDao.getObject(act2DocCode);
				dfDocCodes += (dfDocCodes.isEmpty() ? "" + act2Doc.dfDocCode : ", " + act2Doc.dfDocCode); 
			}

			dfConnection = getConnection(AuthorizationJNDINames.DOCFLOW_DATASOURCE);
			DocFlowLogic docFlowLogic = new DocFlowLogic(dfConnection, userProfile);
			return docFlowLogic.getActiveDocCode(dfDocCodes);

		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} catch (DatasourceConnectException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			try {
				if (dfConnection != null && !dfConnection.isClosed()) {
					dfConnection.close();
					dfConnection = null;
				}
			} catch (SQLException e) {
			}
		}
	}

	public ENAct getENActByDFDocCode(int dfDocCode) {
		if (dfDocCode <= 0) {
			throw new IllegalArgumentException("\n\nNET-4596 �� ������� ��� ���������!");
		}

		try {
			ENAct2DFDocDAO act2DocDao = new ENAct2DFDocDAO(connection, userProfile);
			ENAct2DFDocFilter act2DocFilter = new ENAct2DFDocFilter();
			act2DocFilter.dfDocCode = dfDocCode;
			int[] act2DocCodes = act2DocDao.getFilteredCodeArray(act2DocFilter, 0, -1);

			if (act2DocCodes.length == 0) {
				return null;
			}

			ENAct2DFDoc act2Doc = act2DocDao.getObject(act2DocCodes[0]);
			ENActDAO actDao = new ENActDAO(connection, userProfile);
			return actDao.getObjectNOSEGR(act2Doc.actRef.code);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		}
	}

	public String getActTypeShortName(int actTypeCode) {
		if (actTypeCode <= 0) {
			throw new IllegalArgumentException("\n\n�� ������� ��� ���� ����!");
		}

		try {
			ENPlanWorkStateDAO planStateDao = new ENPlanWorkStateDAO(connection, userProfile);
			ENPlanWorkState planState = planStateDao.getObject(actTypeCode);
			return planState.shortName;
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		}
	}

	public void cancelDFDoc(int actCode) {
		if (actCode <= 0) {
			throw new IllegalArgumentException("\n\nNET-4596 �� ������� ��� ����!");
		}

		int docCode = getDFDocCodeForENAct(actCode);
		if (docCode <= 0) {
			return;
		}

		Connection dfConnection = null;

		try {
			dfConnection = getConnection(AuthorizationJNDINames.DOCFLOW_DATASOURCE);
			DocFlowLogic docFlowLogic = new DocFlowLogic(dfConnection, userProfile);
			docFlowLogic.setCancel(docCode, true);
		} catch (DatasourceConnectException e) {
			throw new SystemException(e);
		} finally {
			try {
				if (dfConnection != null && !dfConnection.isClosed()) {
					dfConnection.close();
					dfConnection = null;
				}
			} catch (SQLException e) {
			}
		}
	}

	public void removeENAct2DFDoc(int actCode, int docCode) {
		removeENAct2DFDoc(actCode, docCode, false);
	}

	public void removeENAct2DFDoc(int actCode, int docCode, boolean removeAll) {
		if (actCode <= 0) {
			throw new IllegalArgumentException("\n\nNET-4596 �� ������� ��� ����!");
		}
		if (docCode <= 0 && !removeAll) {
			throw new IllegalArgumentException("\n\nNET-4596 �� ������� ��� ���������!");
		}

		try {
			ENAct2DFDocDAO act2DFDocDao = new ENAct2DFDocDAO(connection, userProfile);
			ENAct2DFDocFilter act2DFDocFilter = new ENAct2DFDocFilter();
			act2DFDocFilter.actRef.code = actCode;
			act2DFDocFilter.dfDocCode = docCode;
			int[] act2DFDocCodes = act2DFDocDao.getFilteredCodeArray(act2DFDocFilter, 0, -1);

			if (act2DFDocCodes.length > 0) {
				ENActDAO actDao = new ENActDAO(connection, userProfile);
				ENAct act = actDao.getObjectNOSEGR(actCode);
				if (act.statusRef.code != ENActStatus.GOOD) {
					throw new SystemException("\n\nNET-4596 ��� ������� ���� ��������! ��� � ����� " + act.code + " �� � ��������!");
				}
			}

			for (int act2DFDocCode : act2DFDocCodes) {
				act2DFDocDao.remove(act2DFDocCode);
			}
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		}
	}

    public String getUserNameForFK(int actCode) {
		if (actCode <= 0) {
			throw new IllegalArgumentException("\n\n�� ������� ��� ����!");
		}

		String userName = userProfile.userName;

		int dfDocCode = getDFDocCodeForENAct(actCode);
		// ��� ���������� ����� � ������� ��� ����� ��������� �������� � �� ��� ������ "energynet"
		if (dfDocCode > 0) {
			userName = "energynet";
		} else {
			// ���� ��� ���������� ��� ���������� ��� ��������� �� ���� ��������� � ������������,
			// ���� �������� ��� "energynet"
			SCUsageInputLogic scUsageInputLogic = new SCUsageInputLogic(connection, userProfile);
			int scUsageInputCode = scUsageInputLogic.getSCUsageInputCodeByActCode(actCode);
			if (scUsageInputCode > 0) {
				userName = scUsageInputLogic.getUserNameForFKBySCUsageInput(scUsageInputCode);
			}
		}

		return userName;
    }

    public FinKodIst getFinKodIstByAct(ENAct act) throws PersistenceException {
    	if(act == null || act.actTypeRef == null || act.actTypeRef.code == Integer.MIN_VALUE) {
    		throw new java.lang.NullPointerException();
    	}
    	
    	FinKodIstDAO dao = new FinKodIstDAO(connection, userProfile);
    	
    	Integer kodIstCode = null;
    	FinKodIst kodIst = null;
    	
    	boolean actForCounters = this.checkInSCCounterByActCode(act.code, false) == 1;
    	boolean zkuMounting = this.checkZKUMountingByAct(act);
    	
    	switch(act.actTypeRef.code) {
    	case ENPlanWorkState.CAPITAL_BUILDER:
    		kodIstCode = FinKodIst.CAPITAL_BUILDER;
    		break;
    	case ENPlanWorkState.RECONSTRUCTION_MODERNIZATION:
    		kodIstCode = FinKodIst.RECONSTRUCTION_MODERNIZATION;
    		break;
    	case ENPlanWorkState.TO:
    		kodIstCode = actForCounters || zkuMounting ? FinKodIst.TECHNICAL_MAINTENANCE_WITH_COUNTERS_ZKU
    				: FinKodIst.TECHNICAL_MAINTENANCE;
    		break;
    	}
    	
    	if(kodIstCode != null) {
    		kodIst = dao.getObject(kodIstCode);
    	}
    	
    	return kodIst;
    }

	public void checkDatePostings(ENAct act) {
		if (act == null) {
			throw new IllegalArgumentException("\n\n�� ������� ��'��� ����!");
		}
	
		Date dateAct = Tools.clearTimeOfDate(act.dateAct);
		Date dateGen = Tools.clearTimeOfDate(act.dateGen);
		if (dateAct.equals(dateGen)) {
			return;
		}

		AuthLogic authLogic = new AuthLogic(connection, userProfile);

		if (! authLogic.isUserBuh()) {
			throw new SystemException("\n\nSUPP-103456 ���� ���������� ���� ������� ��������� � ����� ����!\n" +
					"���� ��� ������� ������ ���� ���������� ����, ��������� ��� \"�� ���������\"" +
					" �� ��������� �� ���������� ���������!");
		}
	}

	/**
	 * ��������� ������� ����������� ��� ���� 
	 *
	 * @param act - ������ ���� ({@link com.ksoe.energynet.valueobject.ENAct})
	 *
	 * @return ������ ����������� (�������� {@link com.ksoe.docflow.valueobject.DFDocSigner})
	 */
	public DFDocSigner[] getDFDocSigners(ENAct act) {
		if (act == null) {
			throw new IllegalArgumentException("\n\nNET-4596 �� ������� ��'��� ����!");
		}

		Connection dfConnection = null;

		try {
			dfConnection = getConnection(AuthorizationJNDINames.DOCFLOW_DATASOURCE);

			if (DocSigningLogic.isSignable(act)) {
				DocSigningLogic docSigningLogic = DocSigningLogic.getInstanceForObject(dfConnection, userProfile, act);
				int docCode = docSigningLogic.getDocCodeForObject(act);
				if (docCode > 0) {
					return docSigningLogic.getDFDocSigners(docCode);
				}
			}

			return null;
		} catch (DatasourceConnectException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			try {
				if (dfConnection != null && !dfConnection.isClosed()) {
					dfConnection.close();
					dfConnection = null;
				}
			} catch (SQLException e) {
			}
		}
	}

	public void removeENAct2RQFKOrder(int actCode) {
		if (actCode <= 0) {
			throw new IllegalArgumentException("\n\n�� ������� ��� ����!");
		}

		try {
			ENAct2RQFKOrderDAO act2FkOrderDao = new ENAct2RQFKOrderDAO(connection, userProfile);
			ENAct2RQFKOrderFilter act2FKOrderFilter = new ENAct2RQFKOrderFilter();
			act2FKOrderFilter.actRef.code = actCode;

			int[] act2FkOrderCodes = act2FkOrderDao.getFilteredCodeArray(act2FKOrderFilter, 0, -1);

			if (act2FkOrderCodes.length > 0) {
				Context context = new InitialContext();
				Object objRef = context.lookup(ENAct2RQFKOrderController.JNDI_NAME);
				ENAct2RQFKOrderControllerHome act2FkOrderControllerHome = 
						(ENAct2RQFKOrderControllerHome) PortableRemoteObject.narrow(objRef, ENAct2RQFKOrderControllerHome.class);
				ENAct2RQFKOrderController act2FkOrderController = act2FkOrderControllerHome.create();

				for (int act2FkOrderCode : act2FkOrderCodes) {
					act2FkOrderController.remove(act2FkOrderCode);
				}
			}
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} catch (NamingException e) {
			throw new SystemException(e.getMessage(), e);
		} catch (RemoteException e) {
			throw new SystemException(e.getMessage(), e);
		} catch (CreateException e) {
			throw new SystemException(e.getMessage(), e);
		}
	}

	public int[] getSCUsageInputCodesForENAct(int actCode) {
		if (actCode <= 0) {
			throw new IllegalArgumentException("\n\n�� ������� ��� ����!");
		}

		try {
			SCUsageInputDAO scUsageInputDao = new SCUsageInputDAO(userProfile, connection);
			SCUsageInputFilter scUsageInputFilter = new SCUsageInputFilter();
			scUsageInputFilter.conditionSQL = "scusageinput.code in ( " +
				      " select a2s.scusageinputrefcode from enact2scusageinput a2s " +
				      " where a2s.actrefcode = " + actCode + " )";

			return scUsageInputDao.getFilteredCodeArrayNOSEGR(scUsageInputFilter, 0, -1);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		}
	}

	public boolean actMustHaveSentAndReceivedSigners(ENAct act) {
		if (act == null) {
			throw new IllegalArgumentException("\n\nNET-4596 �� ������� ��'��� ����!");
		}
		if (act.dateAct == null) {
			throw new IllegalArgumentException("\n\nNET-4596 �� ������ ���� ����! ��� ����: " + act.code);
		}

		try {
			return (act.dateAct.compareTo(DFConsts.SCUSAGEINPUT_SIGNING_START) >= 0
					&& (checkZKUMountingByAct(act) ||
						containsOzForSigning(act.code) ||
						(act.element.typeRef.code == ENElementType.SERVICES_OBJECT && containsSCCounterForOz(act.code))));
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		}
	}

	/**
	 * ���� �� � ���� ���� �������� (�� �����������)
	 *
	 * @param actCode - ��� ����
	 *
	 * @return {@code true} - ���� � ���� ���� ���� ��������, ����� {@code false}
	 *
	 * @throws PersistenceException
	 */
	private boolean containsSCCounterForOz(int actCode) throws PersistenceException {
		SCCounterDAO cDAO = new SCCounterDAO(connection, userProfile);
		SCCounterFilter cFilter = new SCCounterFilter();
		cFilter.kindRef.code = SCCounterKind.FOR_FACT;
		cFilter.conditionSQL =  "code in (select cc.code from " +
		" sccounter cc, enestimateitem e, enact2enplanwork a2p, enplanwork p " +
		" where " +
		" cc.invnumber is not null " + // ��� �� ���������� ����������� ��������!!!
		" and cc.estimateitemrefcode = e.code " +
		" and e.planrefcode = a2p.plancode " +
		" and a2p.plancode = p.code "+
		" and a2p.actrefcode = ? " +
		" and e.kindrefcode = ?)" ;
		
		Vector<Object> binded = new Vector<>();
		binded.add(actCode);
		binded.add(ENEstimateItemKind.MATERIALS);
		
		int[] cArr = cDAO.getFilteredCodeArray(cFilter, 0, -1, binded);
		return (cArr.length > 0);
	}

	/**
	 * ���� �� � ���� ��������� ��-���, � ������� ���� ���� �������� (�� �����������) -
	 * � ���� ������ ���������� ����������� � ����������� ��� �� �� ���� � ������������
	 *
	 * @param actCode - ��� ����
	 *
	 * @return {@code true} - ���� ���� ��-��� � ������ ����������, ����� {@code false}
	 */
	private boolean containsOzForSigning(int actCode) {
		if (actCode <= 0) {
			throw new IllegalArgumentException("\n\n�� ������� ��� ����!");
		}

		int[] scUsageInputCodes = getSCUsageInputCodesForENAct(actCode);
		if (scUsageInputCodes.length == 0) {
			return false;
		}

		String strCodes = Tools.intArrayToStr(scUsageInputCodes, ",");
		if (strCodes == null || strCodes.isEmpty()) {
			return false;
		}

		try {
			SCUsageInputItemDAO scUsageInputItemDAO = new SCUsageInputItemDAO(userProfile, connection);
			SCUsageInputItemFilter scUsageInputItemFilter = new SCUsageInputItemFilter();
			scUsageInputItemFilter.conditionSQL = "scusageinputitem.usageinputrefcode in (" + strCodes + ")";

			int[] scUsageInputItemCodes = scUsageInputItemDAO.getFilteredCodeArray(scUsageInputItemFilter, 0, -1);
			if (scUsageInputItemCodes.length == 0) {
				return false;
			}

			SCUsageInputItemOZDAO scUsageInputItemOZDao = new SCUsageInputItemOZDAO(connection, userProfile);
			SCUsageInputLogic scUsageInputLogic = new SCUsageInputLogic(connection, userProfile);

			for (int scUsageInputItemCode : scUsageInputItemCodes) {
				SCUsageInputItem scUsageInputItem = scUsageInputItemDAO.getObject(scUsageInputItemCode);

				SCUsageInputItemOZFilter scUsageInputItemOZFilter = new SCUsageInputItemOZFilter();
				scUsageInputItemOZFilter.usageInputItemRef.code = scUsageInputItemCode;
				int[] scUsageInputItemOZCodes = scUsageInputItemOZDao.getFilteredCodeArray(scUsageInputItemOZFilter, 0, -1);

				for (int scUsageInputItemOZCode : scUsageInputItemOZCodes) {
					switch (scUsageInputItem.kindRef.code) {
						// ��� ����� � ����� ��������� ��-1 ������ � ���
						case SCUsageInputItemKind.UsageInput:
							SCUsageInputItemOZ oz = scUsageInputItemOZDao.getObject(scUsageInputItemOZCode);
							// SUPP-67738 ���� ��� �����, ������, ��� ������� ��������, � �� ���� �������� �������
							if (oz.account != null && oz.account.trim().length() > 0) {
								return true;
							}
							break;

						// ��� ������ �� ����� ��������� ��-1 �� ������
						case SCUsageInputItemKind.UsageOut:
							return true;

						// ��� ������ �� ��������� ��������� ���� �����
						case SCUsageInputItemKind.InputUsing:
							if (! scUsageInputLogic.isForExpertise(scUsageInputItemOZCode)) {
								// ��������� �����
								return true;
							}
							break;

						case SCUsageInputItemKind.UsageInputZKU:
							return true;
					}
				}

			}

			return false;
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		}
	}

}

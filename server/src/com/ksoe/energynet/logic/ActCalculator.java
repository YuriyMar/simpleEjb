package com.ksoe.energynet.logic;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Vector;
import java.util.stream.Collectors;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ksoe.authorization.dataminer.BaseDAOUtils;
import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.docflow.logic.signing.DocSigningLogic;
import com.ksoe.energynet.dataminer.ENAct2CostRecoveryDAO;
import com.ksoe.energynet.dataminer.ENAct2ENPlanWorkDAO;
import com.ksoe.energynet.dataminer.ENAct2HumenDAO;
import com.ksoe.energynet.dataminer.ENAct2TransportDAO;
import com.ksoe.energynet.dataminer.ENActDAO;
import com.ksoe.energynet.dataminer.ENEstimateItemDAO;
import com.ksoe.energynet.dataminer.ENHumenItemDAO;
import com.ksoe.energynet.dataminer.ENPlan2HumenDAO;
import com.ksoe.energynet.dataminer.ENPlanWork2ClassificationTypeDAO;
import com.ksoe.energynet.dataminer.ENPlanWorkDAO;
import com.ksoe.energynet.dataminer.ENPlanWorkItem2HumenDAO;
import com.ksoe.energynet.dataminer.ENServicesCostDAO;
import com.ksoe.energynet.dataminer.ENStandardConstDAO;
import com.ksoe.energynet.dataminer.ENTransportItemDAO;
import com.ksoe.energynet.dataminer.FINMaterialsDAO;
import com.ksoe.energynet.dataminer.FINWorkerDAO;
import com.ksoe.energynet.dataminer.SCSealDAO;
import com.ksoe.energynet.dataminer.SCUsageInputItemOZ2ENActDAO;
import com.ksoe.energynet.util.Tools;
import com.ksoe.energynet.valueobject.ENAct;
import com.ksoe.energynet.valueobject.ENAct2Humen;
import com.ksoe.energynet.valueobject.ENAct2RQFKOrderType;
import com.ksoe.energynet.valueobject.ENAct2Transport;
import com.ksoe.energynet.valueobject.ENActStatus;
import com.ksoe.energynet.valueobject.ENElementType;
import com.ksoe.energynet.valueobject.ENEstimateItem;
import com.ksoe.energynet.valueobject.ENEstimateItemKind;
import com.ksoe.energynet.valueobject.ENHumenItem;
import com.ksoe.energynet.valueobject.ENHumenItemKind;
import com.ksoe.energynet.valueobject.ENPlan2Humen;
import com.ksoe.energynet.valueobject.ENPlanWork;
import com.ksoe.energynet.valueobject.ENPlanWork2ClassificationType;
import com.ksoe.energynet.valueobject.ENPlanWorkItem2Humen;
import com.ksoe.energynet.valueobject.ENPlanWorkKind;
import com.ksoe.energynet.valueobject.ENPlanWorkState;
import com.ksoe.energynet.valueobject.ENPlanWorkType;
import com.ksoe.energynet.valueobject.ENServicesCost;
import com.ksoe.energynet.valueobject.ENServicesObject;
import com.ksoe.energynet.valueobject.ENServicesObjectCalcType;
import com.ksoe.energynet.valueobject.ENStandardConst;
import com.ksoe.energynet.valueobject.ENTransportItem;
import com.ksoe.energynet.valueobject.FINChargeType;
import com.ksoe.energynet.valueobject.FINMaterials;
import com.ksoe.energynet.valueobject.FINMaterialsStatus;
import com.ksoe.energynet.valueobject.FINWorker;
import com.ksoe.energynet.valueobject.FINWorkerKind;
import com.ksoe.energynet.valueobject.brief.ENAct2HumenShort;
import com.ksoe.energynet.valueobject.brief.ENActShort;
import com.ksoe.energynet.valueobject.brief.ENHumenItemShort;
import com.ksoe.energynet.valueobject.brief.ENPlan2HumenShort;
import com.ksoe.energynet.valueobject.brief.ENPlanWorkItem2HumenShort;
import com.ksoe.energynet.valueobject.brief.ENTransportItemShort;
import com.ksoe.energynet.valueobject.brief.FINWorkerShort;
import com.ksoe.energynet.valueobject.filter.ENAct2CostRecoveryFilter;
import com.ksoe.energynet.valueobject.filter.ENAct2ENPlanWorkFilter;
import com.ksoe.energynet.valueobject.filter.ENAct2HumenFilter;
import com.ksoe.energynet.valueobject.filter.ENAct2TransportFilter;
import com.ksoe.energynet.valueobject.filter.ENActFilter;
import com.ksoe.energynet.valueobject.filter.ENEstimateItemFilter;
import com.ksoe.energynet.valueobject.filter.ENHumenItemFilter;
import com.ksoe.energynet.valueobject.filter.ENPlan2HumenFilter;
import com.ksoe.energynet.valueobject.filter.ENPlanWorkFilter;
import com.ksoe.energynet.valueobject.filter.ENPlanWorkItem2HumenFilter;
import com.ksoe.energynet.valueobject.filter.ENTransportItemFilter;
import com.ksoe.energynet.valueobject.filter.FINMaterialsFilter;
import com.ksoe.energynet.valueobject.filter.FINWorkerFilter;
import com.ksoe.energynet.valueobject.filter.SCUsageInputItemOZ2ENActFilter;
import com.ksoe.energynet.valueobject.lists.ENAct2CostRecoveryShortList;
import com.ksoe.energynet.valueobject.lists.ENAct2ENPlanWorkShortList;
import com.ksoe.energynet.valueobject.lists.ENAct2HumenShortList;
import com.ksoe.energynet.valueobject.lists.ENAct2TransportShortList;
import com.ksoe.energynet.valueobject.lists.ENActShortList;
import com.ksoe.energynet.valueobject.lists.ENEstimateItemShortList;
import com.ksoe.energynet.valueobject.lists.ENHumenItemShortList;
import com.ksoe.energynet.valueobject.lists.ENPlan2HumenShortList;
import com.ksoe.energynet.valueobject.lists.ENPlanWorkItem2HumenShortList;
import com.ksoe.energynet.valueobject.lists.ENPlanWorkShortList;
import com.ksoe.energynet.valueobject.lists.ENTransportItemShortList;
import com.ksoe.energynet.valueobject.lists.FINMaterialsShortList;
import com.ksoe.energynet.valueobject.lists.FINWorkerShortList;
import com.ksoe.energypro.exception.EnergyproSystemException;
import com.ksoe.energypro.logic.LogicModule;
import com.ksoe.fin.logic.FKOSLogic;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;
import com.ksoe.lla.security.UserProfile;
import com.ksoe.rqorder.dataminer.RQFKOrderDAO;
import com.ksoe.rqorder.valueobject.RQFKOrderKind;
import com.ksoe.rqorder.valueobject.RQFKOrderStatus;
import com.ksoe.rqorder.valueobject.filter.RQFKOrderFilter;
import com.ksoe.rqorder.valueobject.lists.RQFKOrderShortList;
import com.ksoe.techcard.dataminer.TKCalcCostDAO;
import com.ksoe.techcard.dataminer.TKCalcHumenSalaryDAO;
import com.ksoe.techcard.dataminer.TKClassificationTypeDAO;
import com.ksoe.techcard.dataminer.TKTransportHistoryDAO;
import com.ksoe.techcard.dataminer.TKTransportRealDAO;
import com.ksoe.techcard.logic.TKConsts;
import com.ksoe.techcard.valueobject.TKCalcCost;
import com.ksoe.techcard.valueobject.TKCalcHumenSalary;
import com.ksoe.techcard.valueobject.TKCalcKind;
import com.ksoe.techcard.valueobject.TKClassificationType;
import com.ksoe.techcard.valueobject.TKTechCard;
import com.ksoe.techcard.valueobject.TKTransportHistory;
import com.ksoe.techcard.valueobject.TKTransportStatus;
import com.ksoe.techcard.valueobject.TKTransportType;
import com.ksoe.techcard.valueobject.filter.TKClassificationTypeFilter;
import com.ksoe.techcard.valueobject.filter.TKTransportRealFilter;
import com.ksoe.techcard.valueobject.lists.TKTransportRealShortList;
import com.ksoe.techcard.valueobject.references.TKCalcKindRef;

public class ActCalculator extends LogicModule {

    /**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public ActCalculator(Connection connection, UserProfile userProfile)
    {
        super(connection, userProfile);
    }


    public void deletePlan2HumenByTabNumberPlanCodeKindCode__(String tabNumber, int planCode, int humenKindCode) throws PersistenceException
    {
        ENPlan2HumenDAO dao = new ENPlan2HumenDAO(connection, userProfile);
        ENPlan2HumenFilter f = new ENPlan2HumenFilter();
        f.tabNumber = tabNumber;
        f.planRef.code = planCode;
        f.humenKindRef.code = humenKindCode;
        int[] arr = dao.getFilteredCodeArray(f, 0, -1);
        for (int i=0; i < arr.length; i++){
            dao.remove(arr[i]);
        }
    }

        public void getActs(int actCode, String actCodes, boolean isExeption , int departmentCode) throws PersistenceException
        {
            //System.out.println("Start get act code = " + actCode);
            ENPlan2HumenDAO p2hDAO = new ENPlan2HumenDAO(connection, userProfile);
            ENPlan2HumenShortList p2hList = p2hDAO.getTabNumberList(actCode, departmentCode);

            String actCodes_ = actCodes + ", " + actCode;

            for (int i=0; i < p2hList.totalCount; i++)
            {
                ENPlan2HumenShortList actList = p2hDAO.getActListByTabNumberAndDate2(p2hList.get(i).tabNumber, p2hList.get(i).planRefDateStart, actCodes_ , departmentCode);

                for (int j=0; j < actList.totalCount; j++){

                    ActsShort hKey = new ActsShort("" + actList.get(j).actRefCode, new SimpleDateFormat("dd.MM.yyyy").format(actList.get(j).actRefDateGen), actList.get(j).actRefNumberGen);
                    ActsShort vKey;
                    // уже все по другому ;)
                    /*
                    if ((actList.get(j).actRefStatusCode != ENActStatus.GOOD ) && (isExeption))
                    {
                        //это типа делать незя ... _actsList = null;
                        throw new EnergyproSystemException("Акт № " + actList.get(j).actRefNumberGen + " від " + new SimpleDateFormat("dd.MM.yyyy").format(actList.get(j).actRefDateGen)+ " вже проведено у ФК \n Что делать?? отменить проведение ...");
                    }
                    */

                    //hKey = new ActsShort("" + actList.get(j).actRefCode);

                    vKey = (ActsShort) _actsList.get(hKey);
                    if (vKey == null){
                        _actsList.put(hKey, hKey);
                        //actCodes_ = actCodes_ + ", " + actCode;
                        getActs(actList.get(j).actRefCode, actCodes_ , isExeption, departmentCode);
                    }
                }
            }
            //System.out.println("Final get act code = " + actCode);
        }



        public ENPlanWorkShortList getWorkOrdersList(int actCode) throws PersistenceException
        {
            ENPlanWorkShortList out = new ENPlanWorkShortList();

            _actsTable_ = new Hashtable<ActsData, ActsData>();
            _plansData_ = new Hashtable<PlansData, PlansData>();

            int departmentCode = getDepartmentInAct(actCode);

            getWorkersList_(actCode, "" + Integer.MIN_VALUE, departmentCode);

            String tabNumbers = "";
            String dates = "";

            Vector<PlansData> v = new Vector<PlansData>(_plansData_.keySet());

            for (Enumeration<PlansData> e = v.elements(); e.hasMoreElements();)
            {
                PlansData key = e.nextElement();

                if (tabNumbers.equals("")){
                    tabNumbers = "'" + key.tabNumber + "'";
                    dates = "'" + key.planDate + "'";
                }
                else
                {
                    tabNumbers = tabNumbers + ", '" + key.tabNumber + "'";
                    dates = dates + ", '" + key.planDate + "'";
                }
            }

            if ((dates.length() > 0) && (tabNumbers.length() > 0)){
                out = new ENPlan2HumenDAO(connection, userProfile).getRelatedWorkOrders(tabNumbers, dates);
            }
            return out;
        }


        public ENPlan2HumenShortList getWorkOrdersListByTabNumber(int actCode, String tabNumber) throws PersistenceException
        {
            ENPlan2HumenShortList out = new ENPlan2HumenShortList();

            int departmentCode = getDepartmentInAct(actCode);

            _actsTable_ = new Hashtable<ActsData, ActsData>();
            _plansData_ = new Hashtable<PlansData, PlansData>();

            String tabNumberStr = tabNumber;

            getWorkersList_(actCode, "" + Integer.MIN_VALUE, departmentCode);

            String tabNumbers = "";
            String dates = "";

            Vector<ActsData> v1 = new Vector<ActsData>(_actsTable_.keySet());
            String actCodes = "" + Integer.MIN_VALUE;
            for (Enumeration<ActsData> e = v1.elements(); e.hasMoreElements();)
            {
                ActsData key1 = e.nextElement();
                actCodes = actCodes + "," + key1.actCode;
            }


            Vector<PlansData> v = new Vector<PlansData>(_plansData_.keySet());

            for (Enumeration<PlansData> e = v.elements(); e.hasMoreElements();)
            {
                PlansData key = (PlansData)e.nextElement();
                if (key.tabNumber.equals(tabNumberStr)){
                    if (tabNumbers.equals("")){
                        tabNumbers = key.tabNumber;
                        dates = "'" + key.planDate + "'";
                    }
                    else
                    {
                        tabNumbers = tabNumbers + ", '" + key.tabNumber + "'";
                        dates = dates + ", '" + key.planDate + "'";
                    }
                }
            }

            if (dates.length() > 0)
            {
                out = new ENPlan2HumenDAO(connection, userProfile).getRelatedWorkOrdersByTabNumber(tabNumberStr,actCodes, dates);
            }

            return out;
        }

        public void getWorkersList_(int actCode, String actCodes, int departmentCode) throws PersistenceException
        {

            ActsData hKey = new ActsData("" + actCode);
            ActsData vKey;

            //if (vKey == null){
            //_actsTable.put(hKey, hKey);

            _actsTable_.put(hKey, hKey);
            //actCodes_ = actCodes_ + ", " + actCode;

            ENPlan2HumenDAO p2hDAO = new ENPlan2HumenDAO(connection, userProfile);


            ENPlan2HumenShortList p2hList = p2hDAO.getTabNumberList(actCode, departmentCode);

            String actCodes_ = actCodes + ", " + actCode;

            for (int i=0; i < p2hList.totalCount; i++)
            {

                PlansData hPlan;
                PlansData vPlan = new PlansData(p2hList.get(i).tabNumber, p2hList.get(i).planRefDateStart, p2hList.get(i).humenKindRefCode, p2hList.get(i).positionCode, p2hList.get(i).priceGen);

                hPlan = (PlansData) _plansData_.get(vPlan);
                if (hPlan == null){
                    _plansData_.put(vPlan, vPlan);
                }

                ENPlan2HumenShortList actList = p2hDAO.getActListByTabNumberAndDate2(p2hList.get(i).tabNumber, p2hList.get(i).planRefDateStart, actCodes_ , departmentCode);

                for (int j=0; j < actList.totalCount; j++){
                    /*
                    if (actList.get(j).actRefStatusCode != ENActStatus.GOOD ){
                        //_plansData2 = null;
                        //_actsTable = null;
                        throw new EnergyproSystemException("Акт № " + actList.get(j).actRefNumberGen + " від " + new SimpleDateFormat("dd.MM.yyyy").format(actList.get(j).actRefDateGen)+ " вже затверджено ... \n відмініть затвердження ... або перерахунок вже не потрібний ...", userProfile);
                    }
                    */
                    hKey = new ActsData("" + actList.get(j).actRefCode);

                    vKey = (ActsData) _actsTable_.get(hKey);
                    if (vKey == null){
                        _actsTable_.put(hKey, hKey);
                        //actCodes_ = actCodes_ + ", " + actCode;
                        getWorkersList_(actList.get(j).actRefCode, actCodes_ , departmentCode);
                    }
                }
            }


        }

        public ENActShortList getActsList(int actCode, boolean isException) throws PersistenceException, ParseException
        {
            //Hashtable _actsList = new Hashtable();
            _actsList = new Hashtable<ActsShort, ActsShort>();

            int departmentCode = getDepartmentInAct(actCode);

            ENActShortList out = new ENActShortList();
            ENActDAO dao = new ENActDAO(connection, userProfile);
            ENActShort as = dao.getShortObject(actCode);

            // типа перевезення не учитывать ...
            if ( as.actTypeRefCode == ENPlanWorkState.TRUCKING){
                out.list.add(as);
                out.totalCount = 1;
                return out;
            }

            //synchronized (_actsList)
            {
                getActs(actCode, "" + Integer.MIN_VALUE, isException, departmentCode);

                ActsShort hKey = new ActsShort("" + actCode, new SimpleDateFormat("dd.MM.yyyy").format(as.dateGen), as.numberGen);
                _actsList.put(hKey, hKey);


            Vector<ActsShort> v1 = new Vector<ActsShort>(_actsList.keySet());
            // Collections.sort(v, Collections.reverseOrder());

            // Display (sorted) hashtable.
            String actCodes = "" + Integer.MIN_VALUE;
            for (Enumeration<ActsShort> e = v1.elements(); e.hasMoreElements();)
            {
                ActsShort key1 = (ActsShort)e.nextElement();
                //ActsShort qq_1 = (ActsShort) _actsList.get(key1);
                actCodes = actCodes + "," + key1.actCode;
            }
            ENActFilter f = new ENActFilter();
            f.conditionSQL = "code in (" + actCodes + ")";
            out = dao.getScrollableFilteredListNOSEGR(f, f.conditionSQL, null, 0, -1, null);
            //out.totalCount = i;

            }

            return out;
        }


        public int getDepartmentInAct(int actCode) throws PersistenceException
        {

        int out = Integer.MIN_VALUE;
        // определим подразделение - первый попавшийся ПЛАН ;) ... и только по нем будем собирать планы ;)
        ActLogic actLogic = new ActLogic(connection, userProfile);
        String plansCodes = actLogic.getPlanCodesByAct(actCode);
        ENPlanWorkFilter f = new ENPlanWorkFilter();
        f.kind.code = ENPlanWorkKind.FACT;
        f.conditionSQL = "code in ("+ plansCodes +")";
        ENPlanWorkShortList pList = new ENPlanWorkDAO(connection, userProfile).getScrollableFilteredList(f, 0, -1);
        for (int dd = 0; dd < pList.totalCount; dd++)
        {
            if (out == Integer.MIN_VALUE) out = pList.get(dd).departmentRefCode;
            if (out != pList.get(dd).departmentRefCode){
                removeFullActList("" + actCode);
                throw new EnergyproSystemException("У наряді " + pList.get(dd).workOrderNumber + " не співпадає підрозділ з попереднім нарядом ...");
            }
        }

        if ((out == Integer.MIN_VALUE) && (pList.totalCount > 0))
        {
            removeFullActList("" + actCode);
            throw new EnergyproSystemException("Підрозділ не знайдено для Акта з кодом " + actCode);
        }

        return out;
        }


    public void calcProduced(int actCode) throws PersistenceException
    {
        /*процедура рассчитывает цену и количество всех произведенных материалов на планах в акте Виготовлення*/
        ENActDAO aDAO = new ENActDAO(connection, userProfile);
        ENEstimateItemDAO eDAO = new ENEstimateItemDAO(connection, userProfile);
        FINMaterialsDAO fDAO = new FINMaterialsDAO(connection, userProfile);
        FINWorkerDAO fiwDAO = new FINWorkerDAO(connection, userProfile);

        ENAct act = aDAO.getObject(actCode);


        /*Если тип акта - не виготовлення, то тогда выходим*/
        if(act.actTypeRef.code != ENPlanWorkState.PRODUCTION)
            return;

        ActLogic aLogic = new ActLogic(connection, userProfile);
        String planCodes = aLogic.getPlanCodesByAct(actCode);

        /*Логика расчета: цена на ед. продукции = (материалы + зарплата + затраты на транспорт) / (кол-во произвед. продукции)*/

        /*Зарплата*/
        ENAct2HumenDAO achuDAO = new ENAct2HumenDAO(connection, userProfile);
        ENAct2HumenFilter achuFilter = new ENAct2HumenFilter();
        achuFilter.actRef.code = actCode;
        //achuFilter.humenKindRef.code = ENHumenItemKind.ELTEH;
        ENAct2HumenShortList achuList = achuDAO.getFilteredList(achuFilter);

        BigDecimal totalSalary = new BigDecimal(0);
        BigDecimal totalESV = new BigDecimal(0);

        BigDecimal totalESVElectro = new BigDecimal(0);
        BigDecimal totalESVDriver = new BigDecimal(0);


        for(int i=0; i < achuList.totalCount; i++)
        {
            totalSalary = totalSalary.add(achuList.get(i).paysWorkBonus).setScale(10, BigDecimal.ROUND_HALF_UP);

            /*Посчитаем ЕСВ для инвалидов и неинвалидов*/
            if(achuList.get(i).humenKindRefCode == ENHumenItemKind.ELTEH)
            {
                FINWorkerFilter fiwFilter = new FINWorkerFilter();
                fiwFilter.conditionSQL = "FINWORKER.CODE " +
                                            " IN " +
                                                "(SELECT " +
                                                    " MAX(FINWORKER.CODE) " +
                                                " FROM " +
                                                    " FINWORKER, " +
                                                    " ENHUMENITEM " +
                                                " WHERE " +
                                                    " FINWORKER.CODE = ENHUMENITEM.FINWORKERCODE " +
                                                    " AND ENHUMENITEM.PLANREFCODE in ("+planCodes+")" +
                                                    " AND FINWORKER.TABNUMBER = '"+achuList.get(i).tabNumber+"')";

                FINWorkerShortList fiwList = fiwDAO.getScrollableFilteredList(fiwFilter,fiwFilter.conditionSQL, 0, -1);

                BigDecimal esvPercent = new BigDecimal(0);

                if(fiwList.totalCount > 0)
                    esvPercent = fiwList.get(0).chargePercent.divide(new BigDecimal(100),4, BigDecimal.ROUND_HALF_UP);

                totalESVElectro = totalESVElectro.add(achuList.get(i).paysWorkBonus.multiply(esvPercent).setScale(2, BigDecimal.ROUND_HALF_UP));

            }

            if(achuList.get(i).humenKindRefCode == ENHumenItemKind.DRIVER)
            {
                FINWorkerFilter fiwFilter = new FINWorkerFilter();
                fiwFilter.conditionSQL = "FINWORKER.CODE " +
                                            " IN " +
                                                "(SELECT " +
                                                    " MAX(FINWORKER.CODE) " +
                                                " FROM " +
                                                    " FINWORKER, " +
                                                    " ENTRANSPORTITEM " +
                                                " WHERE " +
                                                    " FINWORKER.CODE = ENTRANSPORTITEM.FINWORKERCODE " +
                                                    " AND ENTRANSPORTITEM.PLANREFCODE in ("+planCodes+")" +
                                                    " AND FINWORKER.TABNUMBER ='"+achuList.get(i).tabNumber+"')";

                FINWorkerShortList fiwList = fiwDAO.getScrollableFilteredList(fiwFilter,fiwFilter.conditionSQL, 0, -1);

                BigDecimal esvPercent = new BigDecimal(0);

                if(fiwList.totalCount > 0)
                    esvPercent = fiwList.get(0).chargePercent.divide(new BigDecimal(100),4, BigDecimal.ROUND_HALF_UP);

                totalESVDriver = totalESVDriver.add(achuList.get(i).paysWorkBonus.multiply(esvPercent).setScale(2, BigDecimal.ROUND_HALF_UP));
            }

        }

        totalESVElectro = totalESVElectro.setScale(2, BigDecimal.ROUND_HALF_UP);
        totalESVDriver = totalESVDriver.setScale(2, BigDecimal.ROUND_HALF_UP);
        totalESV = totalESVElectro.add(totalESVDriver);

        /*Транспорт*/
        ENAct2TransportDAO actrDAO = new ENAct2TransportDAO(connection, userProfile);
        ENAct2TransportFilter actrFilter = new ENAct2TransportFilter();
        actrFilter.actRef.code = actCode;
        ENAct2TransportShortList actrList = actrDAO.getFilteredList(actrFilter);
        BigDecimal totalTransport = new BigDecimal(0);
        for(int i=0; i<actrList.totalCount; i++)
        {
            totalTransport = totalTransport.add(actrList.get(i).paysWork).setScale(2, BigDecimal.ROUND_HALF_UP);
        }

        /*Материалы*/
        ENEstimateItemFilter eFilter = new ENEstimateItemFilter();
        eFilter.conditionSQL = "ENESTIMATEITEM.PLANREFCODE IN (" +planCodes+ ") and ENESTIMATEITEM.KINDREFCODE in (1,2)";
        ENEstimateItemShortList eList = eDAO.getScrollableFilteredList(eFilter,eFilter.conditionSQL, 0, -1);

        BigDecimal totalMaterialsCost = new BigDecimal(0);

        for(int i=0; i<eList.totalCount; i++)
        {
            FINMaterialsFilter fFilter = new FINMaterialsFilter();
            fFilter.estimateItemRef.code = eList.get(i).code;
            fFilter.statusRef.code = FINMaterialsStatus.GOOD;
            FINMaterialsShortList fList = fDAO.getFilteredList(fFilter);
            for(int j=0; j < fList.totalCount; j++)
                totalMaterialsCost = totalMaterialsCost.add(fList.get(j).cost);

        }

        /*Количество изготовленных материалов*/
        ENEstimateItemFilter eProducedFilter = new ENEstimateItemFilter();
        eProducedFilter.kindRef.code = ENEstimateItemKind.PRODUCED;
        eProducedFilter.conditionSQL = " ENESTIMATEITEM.PLANREFCODE IN (" +planCodes+ ") ";
        ENEstimateItemShortList eProducedList = eDAO.getScrollableFilteredList(eProducedFilter,eProducedFilter.conditionSQL, 0, -1);

        BigDecimal totalProducedQty = new BigDecimal(0);

        for(int i=0; i < eProducedList.totalCount; i++)
        {
            FINMaterialsFilter fFilter = new FINMaterialsFilter();
            fFilter.estimateItemRef.code = eProducedList.get(i).code;
            fFilter.statusRef.code = FINMaterialsStatus.GOOD;
            FINMaterialsShortList fList = fDAO.getFilteredList(fFilter);
            for(int j=0; j < fList.totalCount; j++)
                totalProducedQty = totalProducedQty.add(fList.get(j).quantity);
        }

        /*На зарплату накинем сумму ЕСВ*/

        totalSalary = totalSalary.add(totalESV).setScale(2, BigDecimal.ROUND_HALF_UP);

        /*Всего*/
        BigDecimal totalCost = totalSalary.add(totalTransport.add(totalMaterialsCost));

        /*Цена*/
            BigDecimal price = new BigDecimal(0).setScale(3, BigDecimal.ROUND_HALF_UP);

            if(totalProducedQty.compareTo(new BigDecimal(0)) != 0)
                price = totalCost.divide(totalProducedQty, BigDecimal.ROUND_HALF_UP, 3);
            else
                price = new BigDecimal(0).setScale(3, BigDecimal.ROUND_HALF_UP);


        /*Перерасчет и сохранение изготовленных материалов (FINMaterials)*/
        for(int i=0; i < eProducedList.totalCount; i++) {
            FINMaterialsFilter fFilter = new FINMaterialsFilter();
            fFilter.estimateItemRef.code = eProducedList.get(i).code;
            fFilter.statusRef.code = FINMaterialsStatus.GOOD;
            FINMaterialsShortList fList = fDAO.getFilteredList(fFilter);

            FINMaterials fObj = fDAO.getObject(fList.get(0).code);

            fObj.price = price;
            fObj.calc_price = price;
            fObj.cost = fObj.quantity.multiply(price).setScale(2, BigDecimal.ROUND_HALF_UP);
            fObj.fullCost = totalCost;
            fObj.fullQuantity = totalProducedQty;

            fDAO.save(fObj);


        }

        /*Корректировка округлений для разных мест хранения и запись этого в fullCost
         * (если несколько finMaterials произведено)*/
        FINMaterialsFilter fFilter = new FINMaterialsFilter();
        fFilter.statusRef.code = FINMaterialsStatus.GOOD;
        fFilter.conditionSQL = " EXISTS (select 1 from " + ENEstimateItem.tableName + " as es where es.code = " + FINMaterials.estimateItemRef_QFielld + " and es." + ENEstimateItemFilter.planRef_Field + " in ("  + planCodes + ") and es." + ENEstimateItem.kindRef_Field+ " = " + ENEstimateItemKind.PRODUCED + ")";
        int[] fCodesOfProduced = fDAO.getFilteredCodeArray(fFilter, 0, -1);
        Hashtable<Integer, FINMaterials> restPurposeToFINMaterials = new Hashtable<Integer, FINMaterials>();
        Hashtable<Integer, FINMaterials> finMatByCode = new Hashtable<Integer, FINMaterials>();
        if(fCodesOfProduced.length > 1) {
            for(int i = 0; i < fCodesOfProduced.length; i++) {
            	FINMaterials fObj = fDAO.getObject(fCodesOfProduced[i]);
            	if(i == 0) {
        			FINMaterials fObj_temp = fDAO.getObject(fCodesOfProduced[i]);
            		restPurposeToFINMaterials.put(fObj_temp.rest_purpose_id, fObj_temp);
            	} else {
            		if(restPurposeToFINMaterials.containsKey(fObj.rest_purpose_id)) {
            			FINMaterials tempObj = restPurposeToFINMaterials.get(fObj.rest_purpose_id);
            			tempObj.quantity = tempObj.quantity.add(fObj.quantity);
            			restPurposeToFINMaterials.put(tempObj.rest_purpose_id, tempObj);
            		} else {
            			FINMaterials fObj_temp = fDAO.getObject(fCodesOfProduced[i]);
                		restPurposeToFINMaterials.put(fObj_temp.rest_purpose_id, fObj_temp);
            		}
            	}

            	finMatByCode.put(fObj.code, fObj);
            }

        	BigDecimal totalSum = new BigDecimal(0);
        	BigDecimal fullCost = null;
        	Enumeration<FINMaterials> enumeration = restPurposeToFINMaterials.elements();
        	while(enumeration.hasMoreElements()) {
        		FINMaterials tempObj = enumeration.nextElement();
        		fullCost = tempObj.fullCost;
        		tempObj.cost = tempObj.quantity.multiply(tempObj.price).setScale(2, BigDecimal.ROUND_HALF_UP);
        		restPurposeToFINMaterials.put(tempObj.rest_purpose_id, tempObj);
        		totalSum = totalSum.add(tempObj.cost);
        	}

        	BigDecimal diff = totalSum.subtract(fullCost).setScale(2, BigDecimal.ROUND_HALF_UP);

			if(totalSum.compareTo(diff) == -1) {
				throw new EnergyproSystemException("Error in evaluation. totalSum equals diff");
			}

			if(diff.compareTo(new BigDecimal(0)) > 0) {
				while(diff.compareTo(new BigDecimal(0)) > 0) {
					 enumeration = restPurposeToFINMaterials.elements();
					 while(enumeration.hasMoreElements() && diff.compareTo(new BigDecimal(0)) > 0) {
						 FINMaterials tempObj = enumeration.nextElement();
						 if(tempObj.cost.compareTo(new BigDecimal(0.01)) > 0) {
							 tempObj.cost = tempObj.cost.subtract(new BigDecimal(0.01));
							 diff = diff.subtract(new BigDecimal(0.01));
							 restPurposeToFINMaterials.put(tempObj.rest_purpose_id, tempObj);
						 }
			        }
				}
			} else if(diff.compareTo(new BigDecimal(0)) < 0) {
				while(diff.compareTo(new BigDecimal(0)) < 0) {
					 enumeration = restPurposeToFINMaterials.elements();
					 while(enumeration.hasMoreElements() && diff.compareTo(new BigDecimal(0)) < 0) {
						 FINMaterials tempObj = enumeration.nextElement();
						 if(tempObj.cost.compareTo(new BigDecimal(0.01)) > 0) {
							 tempObj.cost = tempObj.cost.add(new BigDecimal(0.01));
							 diff = diff.add(new BigDecimal(0.01));
							 restPurposeToFINMaterials.put(tempObj.rest_purpose_id, tempObj);
						 }
			        }
				}
			}

	        enumeration = restPurposeToFINMaterials.elements();
	        while(enumeration.hasMoreElements()) {
	        	FINMaterials tempObj = enumeration.nextElement();
	        	for(int i = 0; i < fCodesOfProduced.length; i++) {
	        		FINMaterials temp__ = finMatByCode.get(fCodesOfProduced[i]);
	        		if(tempObj.rest_purpose_id == temp__.rest_purpose_id) {
	        			temp__.fullQuantity = tempObj.quantity;
	        			temp__.fullCost = tempObj.cost;
	        			fDAO.save(temp__);
	        		}
	        	}

	        }
        }

    }

    public String removeFullActList(String actCode){
    	StringBuilder actCodes = new StringBuilder(actCode);

        ActsData vAct = new ActsData("" + actCode);
        ActsData hAct = (ActsData) fullActList.get(vAct);
        if (hAct != null){
            synchronized (fullActList) {
                fullActList.remove(vAct);
            }
        }

        Vector<ActsData> v1 = new Vector<ActsData>(_actsTable.keySet());

        for (Enumeration<ActsData> e = v1.elements(); e.hasMoreElements();) {
            ActsData key1 = e.nextElement();

            vAct = new ActsData(key1.actCode);

            if (! key1.actCode.equals(actCode)) {
            	actCodes.append(", ").append(key1.actCode);
            }

            hAct = (ActsData) fullActList.get(vAct);
            if (hAct != null) {
                synchronized (fullActList) {
                    fullActList.remove(vAct);
                }
            }

        }

        return actCodes.toString();
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
                throw new DatasourceConnectException(dataSourceName, e);
            }
        }

    class HumenData {
        String tabNumber;
        String fio;
        String positionName;
        String positionCode;
        String salary;

        public HumenData(String vTabNumber, String vFio, String vPositionName, int vPositionCode, BigDecimal vSalary){
            tabNumber = vTabNumber;
            fio = vFio;
            positionName = vPositionName;
            positionCode = "" + vPositionCode;
            salary = vSalary == null ? "0" : vSalary.toString();
        }

            @Override
			public int hashCode() {
                return (tabNumber + positionCode + salary).hashCode();
            }

            @Override
			public boolean equals(Object obj) {
                if (obj instanceof HumenData) {
                    HumenData other = (HumenData) obj;

                    //return this.tabNumber.equals(other.tabNumber);

                    return this.tabNumber.equals(other.tabNumber)
                        && this.positionCode.equals(other.positionCode)
                        && this.salary.equals(other.salary);
                } else {
                    return false;
                }
            }

    }


        public void deletePlanWorkItem2Humen(int planCode) throws PersistenceException
        {
            ENPlanWorkItem2HumenDAO dao = new ENPlanWorkItem2HumenDAO(connection, userProfile);
            ENPlanWorkItem2HumenFilter f = new ENPlanWorkItem2HumenFilter();
            f.conditionSQL = "enplanworkitem2humen.plaitemrefcode in ( " +
                                " select pi.code from enplanworkitem pi where pi.planrefcode = "+ planCode+")";
            int[] arr = dao.getFilteredCodeArray(f, 0, -1);
            for (int i=0; i < arr.length; i++){
                dao.remove(arr[i]);
            }
        }

        public void recalcPlanWorkItem2Humen(int actCode) throws PersistenceException
        {
            ENPlanWorkItem2HumenDAO pi2hDAO = new ENPlanWorkItem2HumenDAO(connection, userProfile);
            ENPlanWork2ClassificationTypeDAO pl2clDao = new ENPlanWork2ClassificationTypeDAO(connection, userProfile);
            ENServicesCostDAO servicesCostDao = new ENServicesCostDAO(connection, userProfile);
            TKCalcCostDAO calcCostDao = new TKCalcCostDAO(connection, userProfile);
            ENActDAO actDao = new ENActDAO(connection, userProfile);
            TKCalcHumenSalaryDAO calcHumenSalaryDao = new TKCalcHumenSalaryDAO(connection, userProfile);
            TKTransportHistoryDAO transportHistoryDao = new TKTransportHistoryDAO(connection, userProfile);
            TKClassificationTypeDAO classificationTypeDao = new TKClassificationTypeDAO(connection, userProfile);
            PlanWorkLogic planLogic = new PlanWorkLogic(connection, userProfile);
            ServicesLogic servicesLogic = new ServicesLogic(connection, userProfile);
            
            ENAct act = actDao.getObject(actCode);

            ENPlanWorkItem2HumenFilter pi2hFilter = new ENPlanWorkItem2HumenFilter();
            pi2hFilter.conditionSQL = " code in (select p2h.code from enact2enplanwork a2p , enplanworkitem pi, enplanworkitem2humen p2h " +
                                    "    where a2p.actrefcode = "+ actCode +"    and pi.planrefcode = a2p.plancode and pi.code = p2h.plaitemrefcode )";
            int[] pi2hArr = pi2hDAO.getFilteredCodeArray(pi2hFilter, 0, -1);
            for (int i=0; i < pi2hArr.length; i++){
                pi2hDAO.remove(pi2hArr[i]);
            }


            // NET-3801 Служебка от 29.11.2012 - Нововоронцовка --откорректировать акт.
            // Вектор для записи транспорта, с кот. вытягивались расстояния, т.к. у водителей задваивалось время (если один водитель два раза отмечен на работе)
            Vector<Integer> intTransportItems = new Vector<Integer>();

            ENPlanWorkItem2HumenShortList pi2hList = pi2hDAO.getDataList(actCode);

            if (pi2hList.totalCount == 0) return;
            //ENPlanWorkItem2HumenShort piShort = pi2hList.get(0);

            
            BigDecimal Bonus = ENConsts.BONUS_PERCENT;
            
             
            
          for (ENPlanWorkItem2HumenShort piShort : pi2hList.list) {

                ENPlanWorkItem2Humen pi2hObj = new ENPlanWorkItem2Humen();
                pi2hObj.positionRef.code = piShort.positionRefCode; // заполняется для ел.тех персонала 
                pi2hObj.transport.code = piShort.transportCode; // заполняется для водителей 
                pi2hObj.plaItemRef.code = piShort.plaItemRefCode;
                pi2hObj.classificationTypeRef.code = piShort.classificationTypeRefCode;
                pi2hObj.tabNumber = piShort.tabNumber;
                pi2hObj.fio = piShort.fio;
                pi2hObj.daysMonth = piShort.daysMonth;
                pi2hObj.salary = piShort.salary;

                pi2hObj.salaryHours = piShort.salaryHours;
                pi2hObj.timeMonth = piShort.timeMonth;
                pi2hObj.humenKindRef.code = piShort.humenKindRefCode;

                BigDecimal coeff = new BigDecimal(0);

                try {                    /*9col*/              /*10 col*/
                    coeff = piShort.timeWorkFact.divide(piShort.timeWork, 6, BigDecimal.ROUND_HALF_UP);
                } catch (ArithmeticException e) {
                    System.out.println("\n Деление на 0: " + e);
                    System.out.println("\n У працівника " + piShort.fio + " не встановлено час роботи!!!"
                            + "\n Час роботи: " + piShort.timeWork);
                }
                
                TKClassificationType clsType = classificationTypeDao.getObject(piShort.classificationTypeRefCode);
                
                TKCalcKindRef calculationCalcKind = null;
                BigDecimal calculationProductionExpensesPercent = null;
                BigDecimal calculationAdministrativeExpensesPercent = null;
                
                ENServicesCost servicesCost = null;
                
                if (servicesLogic.isActForServicesObjectSupplierContract(actCode)) {
                	int factCode = planLogic.getPlanCodeByItem(piShort.plaItemRefCode);
                	int monthPlanCode = planLogic.getMonthPlanCodeByAnyPlanCode(factCode);
                	servicesCost = servicesCostDao.getObjectByClassficiationTypeAndPlanCode(monthPlanCode, piShort.classificationTypeRefCode);
                } else {
                	servicesCost = servicesCostDao.getObjectByENServicesObjectAndAct(act.element, act, clsType);
                }

                if(servicesCost == null)  {
                    ENPlanWork2ClassificationType planWork2ClassificationType = pl2clDao.getENPlanWork2ClassificationType(act.element.code
                    		, piShort.classificationTypeRefCode);
                    calculationCalcKind = planWork2ClassificationType.calcKindRef;
                    calculationProductionExpensesPercent = planWork2ClassificationType.productionExpensesPercent;
                    calculationAdministrativeExpensesPercent = planWork2ClassificationType.administrativeExpensesPercent;
                } else {
                	TKCalcCost calcCost = calcCostDao.getObject(servicesCost.tkCalcCostRef.code);
                	calculationCalcKind = calcCost.calcKindRef;
                	calculationProductionExpensesPercent = calcCost.productionExpensesPercent;
                	calculationAdministrativeExpensesPercent = calcCost.administrativeExpensesPercent;
                }
                
                pi2hObj.bonus = new BigDecimal(0); 
            	pi2hObj.percentSurcharge = new BigDecimal(0);
            	pi2hObj.percentPremium = new BigDecimal(0);
            	pi2hObj.percentAdditional = new BigDecimal(0);
            	
             // NET-4570 процент премии , услуги на сторону.... Если метод расчета = Новый то проценты премии брать которые определились при составлении договора 
                if (calculationCalcKind.code != TKCalcKind.OLD   )
                {
                	pi2hObj.bonus = piShort.bonus == null ? new BigDecimal(0) : piShort.bonus; 
                	pi2hObj.percentSurcharge = piShort.percentSurcharge == null ? new BigDecimal(0) : piShort.percentSurcharge;
                	pi2hObj.percentPremium = piShort.percentPremium == null ? new BigDecimal(0) : piShort.percentPremium; 
                	pi2hObj.percentAdditional = piShort.percentAdditional == null ? new BigDecimal(0) : piShort.percentAdditional;  
                	
                	// 21.03.2019 Если используются расчеты калькуляций, то вышеперечисленных процентов премий не будет
                	// их надо получить из другой структуры данных
                	if(servicesCost != null) {
                		TKCalcCost calcCost = calcCostDao.getObject(servicesCost.tkCalcCostRef.code);
                		if(pi2hObj.positionRef.code != Integer.MIN_VALUE) {
                    		TKCalcHumenSalary calcHumenSalary = calcHumenSalaryDao.getObjectByTKCalcCostAndPosition(calcCost, pi2hObj.positionRef);
                    		if(calcHumenSalary != null) {
                        		pi2hObj.bonus = calcHumenSalary.percentBonus;
                        		pi2hObj.percentAdditional =  calcHumenSalary.percentAdditional;
                        		pi2hObj.percentPremium = calcHumenSalary.percentPremium;
                        		pi2hObj.percentSurcharge = calcHumenSalary.percentSurcharge;                    			
                    		}
                		} else {
                			if(pi2hObj.transport.code != Integer.MIN_VALUE) {
                				TKTransportHistory transportHistory = transportHistoryDao.getObjectOnDate(pi2hObj.transport.code
                						, calcCost.dateGen);
                				
                				if (transportHistory.bonus == null || transportHistory.salaryAdditional == null ||
                						transportHistory.salaryPremium == null || transportHistory.salarySurcharge == null) {
                					throw new SystemException("Для транспорту " + transportHistory.tktransport.name + 
                							 " з кодом - " + pi2hObj.transport.code + " не вказані показники відсотків премії!");
                				}
                				
                        		pi2hObj.bonus = transportHistory.bonus;
                        		pi2hObj.percentAdditional =  transportHistory.salaryAdditional;
                        		pi2hObj.percentPremium = transportHistory.salaryPremium;
                        		pi2hObj.percentSurcharge = transportHistory.salarySurcharge;
                			}
                		}
                	}
                	
                	// просумируем все начисления если метод расчета не старый
                	Bonus = pi2hObj.bonus.add(pi2hObj.percentSurcharge).add(pi2hObj.percentPremium).add(pi2hObj.percentAdditional);
                } else 
                {
                	pi2hObj.bonus = Bonus ;	
                }

                // для водителей из листа возвращаем только отработанное время
                //   отдельно нужно посчитать время на доставку по табельному в разрезе калькуляции
                BigDecimal delTime = new BigDecimal(0);
                if (pi2hObj.humenKindRef.code ==  ENHumenItemKind.DRIVER ) {

                    TransportLogic transportLogic = new TransportLogic(connection, userProfile);
                    //PlanWorkLogic planLogic = new PlanWorkLogic(connection, userProfile);
                    ENTransportItemDAO tiDAO = new ENTransportItemDAO(connection, userProfile);
                    ENTransportItemFilter tiFilter = new ENTransportItemFilter();
                    tiFilter.planItemRef.code = piShort.plaItemRefCode;
                // tiFilter.finWorker.tabNumber = Integer.parseInt(piShort.tabNumber);
                    tiFilter.conditionSQL = " entransportitem.code in ( " +
                                            " select q.code from entransportitem q  , finworker f " +
                                            " where q.planitemrefcode =  " + piShort.plaItemRefCode +
                                            " and q.finworkercode = f.code " +
                                            " and f.tabnumber = '" + piShort.tabNumber + "'" +
                                            " ) ";

                    ENTransportItemShortList transportList = tiDAO.getScrollableFilteredList(tiFilter, 0, -1);
                    for (int j=0; j < transportList.totalCount; j++){

                        BigDecimal[] distArr = transportLogic.getDistancesByTransport(transportList.get(j).code, false); // для Водил считаем ВСЕ расстояния ...
                        if ( distArr != null){

                            // NET-3801 Проверка, что с ENTransportItem'a не было еще считывания дистанций
                            boolean isExist = intTransportItems.contains(new Integer(transportList.get(j).code));
                            if(!isExist)
                            {
                                delTime = transportLogic.calcTimeByDistaces(distArr[0], distArr[1], distArr[2], planLogic.isWinterMonth( transportList.get(j).planRefDateStart), transportLogic.isTraktor(transportList.get(j).code)).setScale(2, BigDecimal.ROUND_HALF_UP);

                                //NET-3801 Запись кода строки транспорта
                                Integer intTransportItem = new Integer(transportList.get(j).code);
                                intTransportItems.add(intTransportItem);
                            }

                        }

                    }
                }

                /*timeObjectWork col 11 - время персонала с учетом доставки */
                pi2hObj.timeObjectWork = piShort.timeObjectWork;
                pi2hObj.timeWork = piShort.timeObjectWork.add(delTime).setScale(2, BigDecimal.ROUND_HALF_UP);  // факт из работы/хумена
                
                BigDecimal timeWorkWithoutDeliv = piShort.timeObjectWork.subtract(piShort.timeDelivery).setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
                
                pi2hObj.timeWorkFact = pi2hObj.timeWork.multiply(coeff).setScale(2, BigDecimal.ROUND_HALF_UP);

                ///// 12.05.13 NET-4235
                //pi2hObj.timeDelivery = new BigDecimal(delTime.doubleValue()).setScale(2, BigDecimal.ROUND_HALF_UP);
                pi2hObj.timeDelivery = piShort.timeDelivery.setScale(2, BigDecimal.ROUND_HALF_UP);
                //pi2hObj.costDelivery = pi2hObj.salaryHours.multiply(pi2hObj.timeDelivery).setScale(2, BigDecimal.ROUND_HALF_UP);
                /// 20.05.13 Стоимость доставки должна учитываться с учетом процента премии!!!
                //pi2hObj.costDelivery = piShort.costDelivery; //piShort.costDelivery.setScale(2, BigDecimal.ROUND_HALF_UP);
                //pi2hObj.costDelivery = piShort.costDelivery.multiply(new BigDecimal(1).add((Bonus).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP) ) ).setScale(6, BigDecimal.ROUND_HALF_UP); // 6 знаков для точности!!!
                
                // SUPP-89331 12.02.2020 Рассчитаем отдельно стоимость доставки по премиальным во избежание проблем с округлениями
                // и соблюдения логики, что у нас стоимость доставки для человека равна полная з/п за время работы за минусом з/п 
                // за временем работы без учета доставки
                BigDecimal costDeliveryBonus = piShort.costDelivery.multiply(pi2hObj.bonus.divide(new BigDecimal(100))).setScale(8, BigDecimal.ROUND_HALF_UP);
                BigDecimal costDeliveryPremium = piShort.costDelivery.multiply(pi2hObj.percentPremium.divide(new BigDecimal(100))).setScale(8, BigDecimal.ROUND_HALF_UP);
                BigDecimal costDeliverySurcharge = piShort.costDelivery.multiply(pi2hObj.percentSurcharge.divide(new BigDecimal(100))).setScale(8, BigDecimal.ROUND_HALF_UP);
                BigDecimal costDeliveryAdditional = piShort.costDelivery.multiply(pi2hObj.percentAdditional.divide(new BigDecimal(100))).setScale(8, BigDecimal.ROUND_HALF_UP);
                pi2hObj.costDelivery = piShort.costDelivery.add(costDeliveryBonus).add(costDeliveryPremium).add(costDeliverySurcharge).add(costDeliveryAdditional);

                /////

                pi2hObj.paysWork = pi2hObj.salaryHours.multiply(pi2hObj.timeWorkFact).setScale(2, BigDecimal.ROUND_HALF_UP);
                
                //// для расчета --- на необрезанное время 
                BigDecimal paysWork_ = pi2hObj.salaryHours.multiply(pi2hObj.timeWork).setScale(6, BigDecimal.ROUND_HALF_UP);
                
                //// для расчета --- на необрезанное время исключая время доставки 
                BigDecimal paysWork_WithoutDeliv = pi2hObj.salaryHours.multiply(timeWorkWithoutDeliv).setScale(6, BigDecimal.ROUND_HALF_UP);

                pi2hObj.salaryHoursBonus = pi2hObj.salaryHours.multiply(new BigDecimal(1).add((pi2hObj.bonus).divide(new BigDecimal(100), 6, BigDecimal.ROUND_HALF_UP) ) ).setScale(6, BigDecimal.ROUND_HALF_UP);
                // paysWorkBonus зарплата за отработанное время с учетом базовой премии   	
                pi2hObj.paysWorkBonus = new BigDecimal( pi2hObj.salaryHoursBonus.doubleValue() * pi2hObj.timeWork.doubleValue() ).setScale(8,java.math.BigDecimal.ROUND_HALF_UP)  ;
                
               
                pi2hObj.salaryHoursSurcharge = new BigDecimal(pi2hObj.salaryHours.doubleValue() * ( pi2hObj.percentSurcharge.doubleValue() / 100 ) ).setScale(8, BigDecimal.ROUND_HALF_UP) ;
                // доплата без ЗП
                pi2hObj.paysWorkSurcharge = new BigDecimal(/*pi2hObj.paysWork*/paysWork_.doubleValue() * (pi2hObj.percentSurcharge.doubleValue()/100 /* , 6, BigDecimal.ROUND_HALF_UP)*/ )).setScale(8, BigDecimal.ROUND_HALF_UP);
                //pi2hObj.paysWorkSurcharge = pi2hObj.salaryHoursSurcharge.multiply(pi2hObj.timeWork.setScale(2,java.math.BigDecimal.ROUND_HALF_UP)).setScale(2,java.math.BigDecimal.ROUND_HALF_UP);

                
                pi2hObj.salaryHoursPremium = new BigDecimal(pi2hObj.salaryHours.doubleValue() * ( pi2hObj.percentPremium.doubleValue() / 100 ) ).setScale(8, BigDecimal.ROUND_HALF_UP) ;
                // надбавка без зп
                pi2hObj.paysWorkPremium = new BigDecimal(/*pi2hObj.paysWork*/paysWork_.doubleValue() * (pi2hObj.percentPremium.doubleValue()/100 /* , 6, BigDecimal.ROUND_HALF_UP)*/ )).setScale(8, BigDecimal.ROUND_HALF_UP);
                 /*pi2hObj.paysWork*///paysWork_.multiply(pi2hObj.percentPremium.divide(new BigDecimal(100),/*6,*/ BigDecimal.ROUND_HALF_UP) ).setScale(6, BigDecimal.ROUND_HALF_UP);
               // pi2hObj.paysWorkPremium = pi2hObj.salaryHoursPremium.multiply(pi2hObj.timeWork.setScale(2,java.math.BigDecimal.ROUND_HALF_UP)).setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
                
                pi2hObj.salaryHoursAdditional = new BigDecimal(pi2hObj.salaryHours.doubleValue() * ( pi2hObj.percentAdditional.doubleValue() / 100 ) ).setScale(8, BigDecimal.ROUND_HALF_UP) ;
                // інші витрати без зп 
                pi2hObj.paysWorkAdditional = new BigDecimal(/*pi2hObj.paysWork*/paysWork_.doubleValue() * (pi2hObj.percentAdditional.doubleValue()/100 /* , 6, BigDecimal.ROUND_HALF_UP)*/ )).setScale(8, BigDecimal.ROUND_HALF_UP);
                //pi2hObj.paysWorkAdditional = pi2hObj.salaryHoursAdditional.multiply(pi2hObj.timeWork.setScale(2,java.math.BigDecimal.ROUND_HALF_UP)).setScale(2,java.math.BigDecimal.ROUND_HALF_UP);
                
                pi2hObj.paysWorkWithAllSurcharge = (pi2hObj.paysWorkBonus.add(pi2hObj.paysWorkSurcharge).add(pi2hObj.paysWorkPremium).add(pi2hObj.paysWorkAdditional)).setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
                
                
                pi2hObj.paysWorkBonusWithoutDeliv = new BigDecimal(0);
                pi2hObj.paysWorkSurchargeWithoutDeliv = new BigDecimal(0);
                pi2hObj.paysWorkPremiumWithoutDeliv = new BigDecimal(0);
                pi2hObj.paysWorkAdditionalWithoutDeliv = new BigDecimal(0);
                pi2hObj.paysWorkWithAllSurchargeWithoutDeliv = new BigDecimal(0);
                		
                ///// 14.05.13 NET-4235 Расчет общепроизводственных затрат
                //    (70% считается только от зарплаты основного персонала)
                if (pi2hObj.humenKindRef.code == ENHumenItemKind.ELTEH)
                {
                	 // зарплата за отработанное время с учетом базовой премии без доставки и водителей 
                     pi2hObj.paysWorkBonusWithoutDeliv = new BigDecimal( pi2hObj.salaryHoursBonus.doubleValue() * timeWorkWithoutDeliv.doubleValue() ).setScale(8,java.math.BigDecimal.ROUND_HALF_UP)  ;
                     
                     pi2hObj.paysWorkSurchargeWithoutDeliv = new BigDecimal(paysWork_WithoutDeliv.doubleValue() * (pi2hObj.percentSurcharge.doubleValue()/100 )).setScale(8, BigDecimal.ROUND_HALF_UP);
                     pi2hObj.paysWorkPremiumWithoutDeliv = new BigDecimal(paysWork_WithoutDeliv.doubleValue() * (pi2hObj.percentPremium.doubleValue()/100 )).setScale(8, BigDecimal.ROUND_HALF_UP);
                     pi2hObj.paysWorkAdditionalWithoutDeliv = new BigDecimal(paysWork_WithoutDeliv.doubleValue() * (pi2hObj.percentAdditional.doubleValue()/100  )).setScale(8, BigDecimal.ROUND_HALF_UP);
                   
                     pi2hObj.paysWorkWithAllSurchargeWithoutDeliv = (pi2hObj.paysWorkBonusWithoutDeliv.add(pi2hObj.paysWorkSurchargeWithoutDeliv).add(pi2hObj.paysWorkPremiumWithoutDeliv).add(pi2hObj.paysWorkAdditionalWithoutDeliv)).setScale(8,java.math.BigDecimal.ROUND_HALF_UP);
                	

                	BigDecimal paysWorkBonusWithoutDelivery = new BigDecimal(0);
                    ///// 18.05.13 Общепроизводственные затраты должны насчитываться только на время работы без учета доставки!!!
                	 // NET-4570 Если метод расчета не старый то учитываем все надбавки  
                    if (calculationCalcKind.code != TKCalcKind.OLD   )
                    {
                    	 // paysWorkBonusWithoutDelivery =  (pi2hObj.salaryHoursBonus.add(pi2hObj.salaryHoursSurcharge).add(pi2hObj.salaryHoursPremium).add(pi2hObj.salaryHoursAdditional)).multiply(pi2hObj.timeWork.subtract(pi2hObj.timeDelivery).setScale(2,java.math.BigDecimal.ROUND_HALF_UP)).setScale(2,java.math.BigDecimal.ROUND_HALF_UP);	
                    	paysWorkBonusWithoutDelivery = pi2hObj.paysWorkWithAllSurchargeWithoutDeliv; // для нового расчета уже посчитали общую ЗП без доставки и водителей  
                    } else 
                    {
                    	paysWorkBonusWithoutDelivery = pi2hObj.salaryHoursBonus.multiply(pi2hObj.timeWork.subtract(pi2hObj.timeDelivery).setScale(2,java.math.BigDecimal.ROUND_HALF_UP)).setScale(2,java.math.BigDecimal.ROUND_HALF_UP);	
                    }  
                    

                    BigDecimal productionExpensesRate = calculationProductionExpensesPercent.divide(new BigDecimal(100))
                    		.setScale(6, BigDecimal.ROUND_HALF_UP);
                    BigDecimal administrativeExpensesRate = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);
                    if(calculationAdministrativeExpensesPercent != null) {
                    	administrativeExpensesRate = calculationAdministrativeExpensesPercent.divide(new BigDecimal(100))
                        		.setScale(6, BigDecimal.ROUND_HALF_UP); 
                    }
                    pi2hObj.generalExpenses = paysWorkBonusWithoutDelivery.multiply(productionExpensesRate);
                    pi2hObj.administrativeExpenses = paysWorkBonusWithoutDelivery.multiply(administrativeExpensesRate);
                }
                else
                {
                    pi2hObj.generalExpenses = BigDecimal.ZERO;
                    pi2hObj.administrativeExpenses = BigDecimal.ZERO;
                }
                /////

                pi2hDAO.add(pi2hObj);

            }

            // 04.06.15 NET-4453 Рассчитаем отчисления c з/п
            ActLogic actLogic = new ActLogic(connection, userProfile);
            actLogic.calculateSalaryChargesForServices(actCode);
        }


        /**
        *
        * Обновление зарплатных данных персонала в таблице finworker по акту
        *
        * @param act акт
        */
        public void updateSalary(ENAct act)
        {
            Connection fkConnection = null;

            try
            {
                    fkConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);

                    FINWorkerDAO finWorkerFKDAO = new FINWorkerDAO(fkConnection, userProfile);

                    FINWorkerDAO fwDAO = new FINWorkerDAO(connection, userProfile);

                    System.out.println("Start fillActData : act №" + act.numberGen + " ("+ act.code+")");

                    ENTransportItemDAO transportDAO = new ENTransportItemDAO(connection, userProfile);



                    Hashtable<HumenData, HumenData> humenData = new Hashtable<HumenData,HumenData>();
                    HumenData vKey;

                    String finWorkerConditionHH = " enhumenitem.code in ( " +
                    " select enhumenitem.code  " +
                    " from enhumenitem , enplanwork , enplanworkitem , enact2enplanwork " +
                    "  where " +
                    "  enact2enplanwork.actrefcode = " + act.code +
                    "  and enact2enplanwork.plancode = enplanwork.code " +
                    "  and enplanwork.code = enplanworkitem.planrefcode " +
                    "  and enhumenitem.planitemrefcode = enplanworkitem.code  " +
                    "  and enhumenitem.countfact<>0   " +
                    "  and enplanworkitem.countgen <> 0 " +
                    "  ) " +
                    // непривязанные нам НЕ НАДО ??? ;)
                    " and enhumenitem.finworkercode is not null "
                    ;


                ENHumenItemDAO humenDAO = new ENHumenItemDAO(connection, userProfile);


                /* 08.06.2012 +++ страшные тормоза после перехода на PostgreSQL9 */

                ENHumenItemFilter h2Filter = new ENHumenItemFilter();
                h2Filter.conditionSQL = finWorkerConditionHH;
                int h2Arr[] = humenDAO.getFilteredCodeArray(h2Filter, 0, -1);
                if (h2Arr.length > 0) {
                    for (int j=0; j < h2Arr.length; j++) {

                        // типа обновим ЗП из ФК ...
                        ENHumenItemFilter hhFilter = new ENHumenItemFilter();
                        hhFilter.conditionSQL = finWorkerConditionHH;
                        hhFilter.code = h2Arr[j];

                        ENHumenItemShortList hhList = humenDAO.getScrollableFilteredList(hhFilter, 0, -1);
                        for (int qq=0; qq < hhList.totalCount; qq++){

                            if (hhList.get(qq).finWorkerTabNumber != null){
                                FINWorkerFilter ff = new FINWorkerFilter();
                                ff.tabNumber = hhList.get(qq).finWorkerTabNumber;
                                FINWorkerShortList fList = finWorkerFKDAO.getFINWorkerList(ff,0,-1, hhList.get(qq).planRefDateStart, true);
                                if (fList.totalCount > 0)
                                {
                                    FINWorkerFilter fwFilter = new FINWorkerFilter();
                                    fwFilter.tabNumber = hhList.get(qq).finWorkerTabNumber;
                                    fwFilter.conditionSQL =
                                        " finworker.code in " +
                                        " ( " +
                                        "   select distinct fw.code " +
                                        "     from enplanwork p, enhumenitem hi, finworker fw " +
                                        "    where p.datestart = '" + new SimpleDateFormat("dd.MM.yyyy").format(hhList.get(qq).planRefDateStart) + "' " +
                                        "      and p.kindcode = 4 " +
                                        "      and p.code = hi.planrefcode " +
                                        "      and hi.finworkercode = fw.code " +
                                        "      and fw.tabnumber = '" + hhList.get(qq).finWorkerTabNumber + "'" +
                                        " ) ";

                                    int[] fwArr = fwDAO.getFilteredCodeArray(fwFilter, 0, -1);
                                    for (int f = 0; f < fwArr.length; f++)
                                    {
                                        FINWorker w = fwDAO.getObject(fwArr[f]);

                                        if ((w.priceGen.doubleValue() != fList.get(0).priceGen.doubleValue()) || ( ! w.positionName.equals(fList.get(0).positionName)))
                                        {
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

                                            ////// 31.10.12
                                            //fwDAO.save(w);
                                            ENHumenItem humen = humenDAO.getObject(hhList.get(qq).code);
                                            int fwCode = fwDAO.add(w);
                                            humen.finWorker.code = fwCode;
                                            humenDAO.save(humen);
                                            //////
                                        }

                                        vKey = new HumenData(w.tabNumber, w.name, w.positionName, w.positionCode, w.priceGen);
                                        humenData.put(vKey, vKey);
                                    }

                                }
                            }
                        }
                    }
                }

                // ЗП водителей !!! хитро из ТРАНСПОРТА ;) .. а не Хуменов ;)
                finWorkerConditionHH = " entransportitem.code in ( " +
                " Select " +
                " ent.code " +
                " From entransportitem ent ,  finworker fw,  enact2enplanwork ena2, enplanworkitem pwi " +
                " Where " +
                " ena2.actrefcode = " + act.code +
                " and ent.planrefcode = ena2.plancode " +
                " and ent.finworkercode = fw.code " +
                " and ent.planitemrefcode = pwi.code " +
                " and pwi.countgen > 0 " +
                "  ) " ;



                /* 08.06.2012 +++ страшные тормоза после перехода на PostgreSQL9 */
                ENTransportItemFilter tr2Filter = new ENTransportItemFilter();
                tr2Filter.conditionSQL = finWorkerConditionHH;
                int tr2Arr[] = transportDAO.getFilteredCodeArray(tr2Filter, 0, -1);
                if (tr2Arr.length > 0) {
                    for (int j=0; j < tr2Arr.length; j++) {

                        // типа обновим ЗП из ФК ...
                        ENTransportItemFilter trFilter1 = new ENTransportItemFilter();
                        trFilter1.conditionSQL = finWorkerConditionHH;
                        trFilter1.code = tr2Arr[j];

                        ENTransportItemShortList trList1 = transportDAO.getScrollableFilteredList(trFilter1, 0, -1);
                        for (int qq=0; qq < trList1.totalCount; qq++){

                            if (trList1.get(qq).finWorkerTabNumber != null){
                                FINWorkerFilter ff = new FINWorkerFilter();
                                ff.tabNumber = trList1.get(qq).finWorkerTabNumber;
                                FINWorkerShortList fList = finWorkerFKDAO.getFINWorkerList(ff,0,-1, trList1.get(qq).planRefDateStart, true);
                                if (fList.totalCount > 0){

                                    FINWorkerFilter fwFilter = new FINWorkerFilter();
                                    fwFilter.tabNumber = trList1.get(qq).finWorkerTabNumber;
                                    fwFilter.conditionSQL =
                                        " finworker.code in " +
                                        " ( " +
                                        "   select distinct fw.code " +
                                        "     from enplanwork p, entransportitem ent, finworker fw " +
                                        "    where p.datestart = '" + new SimpleDateFormat("dd.MM.yyyy").format(trList1.get(qq).planRefDateStart) + "' " +
                                        "      and p.kindcode = 4 " +
                                        "      and p.code = ent.planrefcode " +
                                        "      and ent.finworkercode = fw.code " +
                                        "      and fw.tabnumber = '" + trList1.get(qq).finWorkerTabNumber + "'" +
                                        " ) ";

                                    int[] fwArr = fwDAO.getFilteredCodeArray(fwFilter, 0, -1);
                                    for (int f = 0; f < fwArr.length; f++)
                                    {
                                        FINWorker w = fwDAO.getObject(fwArr[f]);

                                        if ((w.priceGen.doubleValue() != fList.get(0).priceGen.doubleValue()) || ( ! w.positionName.equals(fList.get(0).positionName)))
                                        {
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

                                            ////// 31.10.12
                                            //fwDAO.save(w);
                                            ENTransportItem transport = transportDAO.getObject(trList1.get(qq).code);
                                            int fwCode = fwDAO.add(w);
                                            transport.finWorker.code = fwCode;
                                            transportDAO.save(transport);
                                            //////
                                        }

                                        vKey = new HumenData(w.tabNumber, w.name, w.positionName, w.positionCode, w.priceGen);
                                        humenData.put(vKey, vKey);
                                    }

                                }
                            }
                        }
                    }
                }

            } catch (Exception e) {
                removeFullActList("" + act.code);
                throw new EnergyproSystemException(e);
            } finally {

                try {
                    if  ( (fkConnection != null) && ! fkConnection.isClosed() )
                        fkConnection.close();
                } catch (SQLException e) {
                }
            }
        }

        public void removePlan2HumenByActCode__(int actCode) throws PersistenceException
        {
            ENPlan2HumenDAO dao = new ENPlan2HumenDAO(connection, userProfile);
            ENPlan2HumenFilter f = new ENPlan2HumenFilter();
            f.conditionSQL = "enplan2humen.planrefcode in (select qq.plancode from enact2enplanwork qq where qq.actrefcode = "+ actCode +")";
            int[] arr = dao.getFilteredCodeArray(f, 0, -1);
            for (int i=0; i < arr.length; i++){
                dao.remove(arr[i]);
            }
        }

        private Hashtable<ActsData, ActsData> _actsTable_ = new Hashtable<ActsData, ActsData>();
        private Hashtable<PlansData, PlansData> _plansData_ = new Hashtable<PlansData, PlansData>();

        private Hashtable<ActsData, ActsData> _actsTable = new Hashtable<ActsData, ActsData>();

        private Hashtable<ActsShort, ActsShort> _actsList = new Hashtable<ActsShort, ActsShort>();


        private Hashtable<PlansData15, PlansData15> _plansData2 = new Hashtable<PlansData15, PlansData15>();

        private static Hashtable<ActsData, ActsData> fullActList = new Hashtable<ActsData, ActsData>();

        class PlansData{
            String tabNumber;
            String planDate;
            String humenKind;
            BigDecimal sumGen;
            BigDecimal calendarTime;
            ///// 12.12.11
            String positionCode;
            String salary;
            /////

            public PlansData(String vTabNumber, Date vPlanDate, int vHumenKind, int vPositionCode, BigDecimal vSalary){
                tabNumber = vTabNumber;
                planDate = new SimpleDateFormat("dd.MM.yyyy").format(vPlanDate);
                humenKind = "" + vHumenKind;
                sumGen = new BigDecimal(0);
                calendarTime = new BigDecimal(0);

                positionCode = "" + vPositionCode;
                salary = vSalary == null ? "0" : vSalary.toString();
            }
            @Override
			public int hashCode() {
                return (tabNumber + planDate + humenKind + positionCode + salary).hashCode();
            }
            @Override
			public boolean equals(Object obj) {
                if (obj instanceof PlansData) {
                    PlansData other = (PlansData) obj;
                    return this.tabNumber.equals(other.tabNumber)
                        && this.planDate.equals(other.planDate)
                        && this.humenKind.equals(other.humenKind)
                        && this.positionCode.equals(other.positionCode)
                        && this.salary.equals(other.salary);
                } else {
                    return false;
                }
            }
        }

        class PlansData2{
            String tabNumber;
            String planDate;
            BigDecimal sumGen;
            BigDecimal calendarTime;
            ///// 12.12.11
            String positionCode;
            String salary;
            /////

            public PlansData2(String vTabNumber, Date vPlanDate, int vPositionCode, BigDecimal vSalary){
                tabNumber = vTabNumber;
                planDate = new SimpleDateFormat("dd.MM.yyyy").format(vPlanDate);
                sumGen = new BigDecimal(0);
                calendarTime = new BigDecimal(0);

                positionCode = "" + vPositionCode;
                salary = vSalary == null ? "0" : vSalary.toString();
            }
            @Override
			public int hashCode() {
                return (tabNumber + planDate + positionCode + salary).hashCode();
            }
            @Override
			public boolean equals(Object obj) {
                if (obj instanceof PlansData2) {
                    PlansData2 other = (PlansData2) obj;
                    return this.tabNumber.equals(other.tabNumber)
                        && this.planDate.equals(other.planDate)
                        && this.positionCode.equals(other.positionCode)
                        && this.salary.equals(other.salary);
                } else {
                    return false;
                }
            }
        }

        public class ActsData{
            public String actCode;
            public String userName;

            public ActsData(String vActCode){
                actCode = vActCode;
                userName = userProfile.userName;
            }

            @Override
			public int hashCode() {
                return (actCode).hashCode();
            }

            @Override
			public boolean equals(Object obj) {
                if (obj instanceof ActsData) {
                    ActsData other = (ActsData) obj;
                    return this.actCode.equals(other.actCode);
                } else {
                    return false;
                }
            }
        }

        public class ActsShort{
            public String actCode;
            public String actDate;
            public String actNumber;
            //public String actStatus;

            public ActsShort(String vActCode, String vActDate, String vActNumber){
                actCode = vActCode;
                actDate = vActDate;
                actNumber = vActNumber;
            }

            @Override
			public int hashCode() {
                return (actCode).hashCode();
            }

            @Override
			public boolean equals(Object obj) {
                if (obj instanceof ActsShort) {
                    ActsShort other = (ActsShort) obj;
                    return this.actCode.equals(other.actCode);
                } else {
                    return false;
                }
            }
        }

    public BigDecimal calcTotalCostByAct(int actCode) throws PersistenceException {
        BigDecimal totalCost = new BigDecimal(0);
        /*
        * процедура рассчитывает цену и количество всех произведенных
        * материалов на планах в акте Виготовлення
        */

        ENEstimateItemDAO eDAO = new ENEstimateItemDAO(connection, userProfile);
        FINMaterialsDAO fDAO = new FINMaterialsDAO(connection, userProfile);
        ENActDAO aDAO = new ENActDAO(connection, userProfile);
        RQFKOrderDAO fkServiceDAO = new RQFKOrderDAO(connection, userProfile);


        ENAct act = aDAO.getObject(actCode);

        ActLogic aLogic = new ActLogic(connection, userProfile);
        String planCodes = aLogic.getPlanCodesByAct(actCode);

        /*
        * Логика расчета: цена на ед. продукции = (материалы + зарплата + затраты на транспорт) / (кол-во произвед. продукции)
        */

        /* Зарплата */
        ENAct2HumenDAO achuDAO = new ENAct2HumenDAO(connection, userProfile);
        ENAct2HumenShortList achuList = achuDAO.getSalaryChargesList(actCode);

        BigDecimal totalSalary = new BigDecimal(0);
        BigDecimal totalESV = new BigDecimal(0);

        for (int i = 0; i < achuList.totalCount; i++) {
            totalSalary = totalSalary.add(achuList.get(i).paysWork).setScale(2, BigDecimal.ROUND_HALF_UP);
            BigDecimal chargePercent = achuList.get(i).chargePercent.divide(new BigDecimal(100)).setScale(4, BigDecimal.ROUND_HALF_UP);
            BigDecimal esv = achuList.get(i).paysWork.multiply(chargePercent);
            totalESV = totalESV.add(esv).setScale(2, BigDecimal.ROUND_HALF_UP);
        }

        totalESV = totalESV.setScale(2, BigDecimal.ROUND_HALF_UP);

        /* Транспорт */
        ENAct2TransportDAO actrDAO = new ENAct2TransportDAO(connection, userProfile);
        ENAct2TransportFilter actrFilter = new ENAct2TransportFilter();
        actrFilter.actRef.code = actCode;
        ENAct2TransportShortList actrList = actrDAO.getFilteredList(actrFilter);
        BigDecimal totalTransport = new BigDecimal(0);
        for (int i = 0; i < actrList.totalCount; i++) {
            totalTransport = totalTransport.add(actrList.get(i).paysWork).setScale(2, BigDecimal.ROUND_HALF_UP);
        }

        /* Материалы */
        /*Для актов по доработке не берутся в расчет актов дорабатываемые материалы*/
        String matConditionSQL = "select es_beforerefined.code " +
                                " from enestimateitem as es_beforerefined inner join enestimateitem as es_refined " +
                                " on (es_beforerefined.planrefcode = es_refined.planrefcode " +
                                " and es_beforerefined.materialrefcode = es_refined.materialrefcode " +
                                "    and (es_beforerefined.planitemrefcode = es_refined.planitemrefcode " +
                                "           or es_refined.planitemrefcode is null)) " +
                                " where es_refined.kindrefcode = " + ENEstimateItemKind.REFINEMENT +
                                " and es_beforerefined.planrefcode in ("+planCodes+")" +
                                " and es_beforerefined.kindrefcode = " + ENEstimateItemKind.MATERIALS;
        ENEstimateItemFilter eFilter = new ENEstimateItemFilter();
        eFilter.conditionSQL = "ENESTIMATEITEM.PLANREFCODE IN (" + planCodes + ") and ENESTIMATEITEM.KINDREFCODE in (1,2) and ENESTIMATEITEM.CODE NOT IN ("+matConditionSQL+")";
        ENEstimateItemShortList eList = eDAO.getScrollableFilteredList(eFilter, eFilter.conditionSQL, 0, -1);

        BigDecimal totalMaterialsCost = new BigDecimal(0);

        for (int i = 0; i < eList.totalCount; i++) {
            FINMaterialsFilter fFilter = new FINMaterialsFilter();
            fFilter.estimateItemRef.code = eList.get(i).code;
            fFilter.statusRef.code = FINMaterialsStatus.GOOD;
            FINMaterialsShortList fList = fDAO.getFilteredList(fFilter);
            for (int j = 0; j < fList.totalCount; j++)
                totalMaterialsCost = totalMaterialsCost.add(fList.get(j).cost);

        }

        /*Высчитаем загальнопроизводственные затраты (70%) из необлагаемой ЕСВ зарплатой*/
        BigDecimal commonEnterpriseCosts = new BigDecimal(0);

        // Общепроизводственные затраты
        if(act.actTypeRef.code != ENPlanWorkState.REFINEMENT) {
        	commonEnterpriseCosts = totalSalary.multiply(aLogic.getProductionExpencesRateByAct(actCode)).setScale(2, BigDecimal.ROUND_HALF_UP);
        }

        /* На зарплату накинем сумму ЕСВ */
        totalSalary = totalSalary.add(totalESV).setScale(2, BigDecimal.ROUND_HALF_UP);

        /* Всего + 70% */
        totalCost = totalSalary.add(totalTransport.add(totalMaterialsCost));
        totalCost = totalCost.add(commonEnterpriseCosts);

        ///// SUPP-30045 - для доробки поищем сумму без ндс с услуг со стороны (связка enact2rqfkorder с типом 3 )
        BigDecimal sumwithoutnds = new BigDecimal(0);
        if (act.actTypeRef.code == ENPlanWorkState.REFINEMENT ){
           RQFKOrderFilter fkServiceFilter = new RQFKOrderFilter();
           fkServiceFilter.kind.code = RQFKOrderKind.SERVICES_FROM_SIDE;
           fkServiceFilter.conditionSQL = " rqfkorder.code in ( select a2f.fkorderrefcode from enact2rqfkorder a2f where a2f.typerefcode = " + ENAct2RQFKOrderType.ACT_REFINEMENT_AND_SERVICES +  " and a2f.actrefcode = " + act.code+ " ) and rqfkorder.statuscode <> " + RQFKOrderStatus.GOOD;

           RQFKOrderShortList fkServiceList = fkServiceDAO.getScrollableFilteredList(fkServiceFilter, 0, -1);

           for (int s = 0; s < fkServiceList.totalCount; s++){
        	   sumwithoutnds = sumwithoutnds.add(fkServiceList.get(s).sumWithoutNds);
           }
           totalCost = totalCost.add(sumwithoutnds);
        }

        return totalCost;
    }

    // расчет суммы по акту по собственным работам и услугам на сторону
    public BigDecimal calcTotalCostByActRM(int actCode) throws PersistenceException {
        BigDecimal totalCost = new BigDecimal(0);

        ENEstimateItemDAO eDAO = new ENEstimateItemDAO(connection, userProfile);
        FINMaterialsDAO fDAO = new FINMaterialsDAO(connection, userProfile);
        FINWorkerDAO fiwDAO = new FINWorkerDAO(connection, userProfile);
        SCSealDAO sealDao = new SCSealDAO(connection, userProfile);

        ActLogic aLogic = new ActLogic(connection, userProfile);
        String planCodes = aLogic.getPlanCodesByAct(actCode);

        ENActDAO actDAO = new ENActDAO(connection, userProfile);
        ENAct actObj = actDAO.getObject(actCode);


        /*
        * Логика расчета: цена на ед. продукции = (материалы + зарплата + затраты на транспорт) / (кол-во произвед. продукции)
        */

        /* Зарплата */

        BigDecimal totalSalary = new BigDecimal(0);
        BigDecimal totalESV = new BigDecimal(0);
        // если Акт виполненных работ договоров подряда тогда сумму по ЗП считаем иначе (не из хуманов )
        if ( actObj.actTypeRef.code == ENPlanWorkState.TMC_TRANSFER )
        {
        totalSalary = aLogic.getSumByWorkForActReceptTransmisMaterials(actCode); }
        else {
        ENAct2HumenDAO achuDAO = new ENAct2HumenDAO(connection, userProfile);
        ENAct2HumenFilter achuFilter = new ENAct2HumenFilter();
        achuFilter.actRef.code = actCode;
        ENAct2HumenShortList achuList = achuDAO.getFilteredList(achuFilter);






        for (int i = 0; i < achuList.totalCount; i++) {
            BigDecimal paysWorkBonus = achuList.get(i).paysWorkBonus;
            if (paysWorkBonus == null) {paysWorkBonus = new BigDecimal(0);}

            totalSalary = totalSalary.add(paysWorkBonus).setScale(2, BigDecimal.ROUND_HALF_UP);

            /* Посчитаем ЕСВ для инвалидов и неинвалидов */
            if (achuList.get(i).humenKindRefCode == ENHumenItemKind.ELTEH) {
                FINWorkerFilter fiwFilter = new FINWorkerFilter();
                fiwFilter.conditionSQL = " FINWORKER.CODE IN ( " +
                        " SELECT MAX(FINWORKER.CODE) FROM FINWORKER, ENHUMENITEM " +
                        " WHERE FINWORKER.CODE = ENHUMENITEM.FINWORKERCODE " +
                        " AND ENHUMENITEM.PLANREFCODE in (" + planCodes + ")" +
                        " AND FINWORKER.TABNUMBER = '" + achuList.get(i).tabNumber + "')";

                FINWorkerShortList fiwList = fiwDAO.getScrollableFilteredList(fiwFilter, fiwFilter.conditionSQL, 0, -1);

                BigDecimal esvPercent = new BigDecimal(0);

                if (fiwList.totalCount > 0)
                    esvPercent = fiwList.get(0).chargePercent.divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP);

                // totalESV = totalESV.add(achuList.get(i).paysWorkBonus.multiply(esvPercent).setScale(2, BigDecimal.ROUND_HALF_UP));
                totalESV = totalESV.add(paysWorkBonus.multiply(esvPercent).setScale(2, BigDecimal.ROUND_HALF_UP));
            }

            if (achuList.get(i).humenKindRefCode == ENHumenItemKind.DRIVER) {
                FINWorkerFilter fiwFilter = new FINWorkerFilter();
                fiwFilter.conditionSQL = " FINWORKER.CODE IN ( " +
                        " SELECT MAX(FINWORKER.CODE) FROM FINWORKER, ENTRANSPORTITEM  " +
                        " WHERE FINWORKER.CODE = ENTRANSPORTITEM.FINWORKERCODE " +
                        " AND ENTRANSPORTITEM.PLANREFCODE in (" + planCodes    + ")" +
                        " AND FINWORKER.TABNUMBER = '" + achuList.get(i).tabNumber + "')";

                FINWorkerShortList fiwList = fiwDAO.getScrollableFilteredList(fiwFilter, fiwFilter.conditionSQL, 0, -1);

                BigDecimal esvPercent = new BigDecimal(0);

                if (fiwList.totalCount > 0)
                    esvPercent = fiwList.get(0).chargePercent.divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP);

                // totalESV = totalESV.add(achuList.get(i).paysWorkBonus.multiply(esvPercent).setScale(2, BigDecimal.ROUND_HALF_UP));
                // yyytotalESV = totalESV.add(paysWorkBonus.multiply(esvPercent).setScale(4, BigDecimal.ROUND_HALF_UP));
                totalESV = totalESV.add(paysWorkBonus.multiply(esvPercent).setScale(2, BigDecimal.ROUND_HALF_UP));
            }
        }
        }


        /* Транспорт */
        ENAct2TransportDAO actrDAO = new ENAct2TransportDAO(connection, userProfile);
        ENAct2TransportFilter actrFilter = new ENAct2TransportFilter();
        actrFilter.actRef.code = actCode;
        ENAct2TransportShortList actrList = actrDAO.getFilteredList(actrFilter);
        BigDecimal totalTransport = new BigDecimal(0);
        for (int i = 0; i < actrList.totalCount; i++) {
            totalTransport = totalTransport.add(actrList.get(i).paysWork).setScale(2, BigDecimal.ROUND_HALF_UP);
        }

        /* Материалы */
        ENEstimateItemFilter eFilter = new ENEstimateItemFilter();
        eFilter.conditionSQL = "ENESTIMATEITEM.PLANREFCODE IN (" + planCodes + ") and ENESTIMATEITEM.KINDREFCODE in (1,2)";
        ENEstimateItemShortList eList = eDAO.getScrollableFilteredList(eFilter, eFilter.conditionSQL, 0, -1);

        BigDecimal totalMaterialsCost = new BigDecimal(0);

        for (int i = 0; i < eList.totalCount; i++) {
            FINMaterialsFilter fFilter = new FINMaterialsFilter();
            fFilter.estimateItemRef.code = eList.get(i).code;
            fFilter.statusRef.code = FINMaterialsStatus.GOOD;
            FINMaterialsShortList fList = fDAO.getFilteredList(fFilter);
            for (int j = 0; j < fList.totalCount; j++)
                totalMaterialsCost = totalMaterialsCost.add(fList.get(j).cost);

        }

        totalMaterialsCost = totalMaterialsCost.add(sealDao.getSumByActCode(actCode));

        /* Количество изготовленных материалов */
        ENEstimateItemFilter eProducedFilter = new ENEstimateItemFilter();
        eProducedFilter.kindRef.code = ENEstimateItemKind.PRODUCED;
        eProducedFilter.conditionSQL = " ENESTIMATEITEM.PLANREFCODE IN (" + planCodes + ") ";
        ENEstimateItemShortList eProducedList = eDAO.getScrollableFilteredList(eProducedFilter, eProducedFilter.conditionSQL, 0, -1);

        BigDecimal totalProducedQty = new BigDecimal(0);

        for (int i = 0; i < eProducedList.totalCount; i++) {
            FINMaterialsFilter fFilter = new FINMaterialsFilter();
            fFilter.estimateItemRef.code = eProducedList.get(i).code;
            fFilter.statusRef.code = FINMaterialsStatus.GOOD;
            FINMaterialsShortList fList = fDAO.getFilteredList(fFilter);
            for (int j = 0; j < fList.totalCount; j++)
                totalProducedQty = totalProducedQty.add(fList.get(j).quantity);
        }

        BigDecimal totalSalaryWithoutEsv = totalSalary;
        // если Акт виполненных работ договоров подряда тогда сумму ЕСВ не берем
        if ( actObj.actTypeRef.code != ENPlanWorkState.TMC_TRANSFER )
        {
        /* На зарплату накинем сумму ЕСВ */

        totalSalary = totalSalary.add(totalESV).setScale(2, BigDecimal.ROUND_HALF_UP);
        }

        /* определим нужно ли накидывать общепроизводственные расходы*/
        BigDecimal generalProductionCost = new BigDecimal(0);
        /* фильтранем акты по условиям
        * если
        * planworkstate = 4 (Кап. строй ) И planworktype = 7 (присоеденение(хоз.способ) )
        *  или
        * planworkstate = 2 (Реконструкция и модернизац ) И planworktype = 7 (присоеденение(хоз.способ) )
        *  или
        * planworkstate = 2 (Реконструкция и модернизац ) И planworktype = 23 (вынос объектов кабельных линий  )
        *  или
        * тип елемента для акта = 22 ( объекты договоров на ТО та ремонт )
        * или
        * тип элемента услуги на сторону  (el.typerefcode = 23 )
        *  или
        * planworkstate = 29 (Проэктирование)
        *
        *
        * если выборка вернет больше 0 значит нужно накидывать общепроизводственный процент на ЧИСТУЮ ЗП */
        actDAO = new ENActDAO(connection, userProfile);
        ENActFilter actFilter = new ENActFilter();
        actFilter.conditionSQL = " ENACT.CODE in ( select distinct a.code from enact a , enact2enplanwork a2p   , enplanwork p , enelement el " +
                            " where a.code = a2p.actrefcode " +
                            " and p.code = a2p.plancode " +
                            " and a.elementcode = el.code " +
                            " and a.code = " + actCode +
                            " and " +
                            "  ( (p.staterefcode = 4 and p.typerefcode = 7 ) " +
                            "   or  " +
                            "   (p.staterefcode = 2 and p.typerefcode = 7 ) " +
                            "   or  " +
                            "   (p.staterefcode = 2 and p.typerefcode = 23) " +
                            "   or  " +
                            "   (p.staterefcode = 2 and p.typerefcode = 115) " +
                            "   or " +
                            "   (el.typerefcode = 22 ) " +
                            "   or " +
                            "   (el.typerefcode = 23 ) " +
                            "   or " +
                            "   (p.staterefcode = 29 ) " +
                            " ) ) ";
        ENActShortList actList = actDAO.getScrollableFilteredList(actFilter, 0, -1);
        if (actList.totalCount > 0 ) {

            /*Общепроизводственные затраты*/
            generalProductionCost = totalSalaryWithoutEsv.multiply(aLogic.getProductionExpencesRateByAct(actCode)).setScale(4, BigDecimal.ROUND_HALF_UP);
            generalProductionCost = generalProductionCost.setScale(2, BigDecimal.ROUND_HALF_UP);


        }

        // для работ на сторону еще может быть ситуация когда в кошторисе есть такая калькуляция где указана СТОИМОСТЬ РАБОТ ПОДРЯДЧИКА
        // ТОГДА ДОБАВЛЯЕМ ЕЩЕ СУММУ ИЗ РАЗВЯЗКИ enact2costrecovery
        BigDecimal costrecovery = new BigDecimal(0);
        ENAct2CostRecoveryDAO a2recovDAO = new ENAct2CostRecoveryDAO(connection, userProfile);
        ENAct2CostRecoveryFilter a2recovFilter = new ENAct2CostRecoveryFilter();
        a2recovFilter.actRef.code = actCode;
        ENAct2CostRecoveryShortList a2recovList = a2recovDAO.getScrollableFilteredList(a2recovFilter, 0, -1);
        if (a2recovList.totalCount > 0 ){
            for (int r=0; r < a2recovList.totalCount; r++)
                {
                costrecovery = costrecovery.add(a2recovList.get(r).summa);
                }
        }
        /* Всего  */
        totalCost = costrecovery.add(generalProductionCost.add(totalSalary.add(totalTransport.add(totalMaterialsCost))));



        return totalCost;
    }


    public BigDecimal calcTotalCostForServices(int actCode) throws PersistenceException {
        BigDecimal totalCost = new BigDecimal(0);

        ENEstimateItemDAO eDAO = new ENEstimateItemDAO(connection, userProfile);
        FINMaterialsDAO fDAO = new FINMaterialsDAO(connection, userProfile);
        FINWorkerDAO fiwDAO = new FINWorkerDAO(connection, userProfile);
        ENActDAO aDAO = new ENActDAO(connection, userProfile);


        ENAct act = aDAO.getObject(actCode);

        ActLogic aLogic = new ActLogic(connection, userProfile);
        String planCodes = aLogic.getPlanCodesByAct(actCode);

        /*
        * Логика расчета: сумма акта = (материалы + зарплата + затраты на транспорт)
        */

        /* Зарплата */
        ENPlanWorkItem2HumenDAO phDAO = new ENPlanWorkItem2HumenDAO(connection, userProfile);
        ENPlanWorkItem2HumenFilter phFilter = new ENPlanWorkItem2HumenFilter();
        phFilter.conditionSQL = " code in " +
                " ( \n" +
                "   select ph.code \n" +
                "   from enplanworkitem2humen ph, enplanworkitem pi \n" +
                "   where ph.plaitemrefcode = pi.code \n" +
                "     and pi.planrefcode in \n" +
                "   ( \n" +
                "   select ap.plancode from enact2enplanwork ap \n" +
                "   where ap.actrefcode = " + actCode +
                "   ) \n" +
                " ) \n";


        //ENPlanWorkItem2HumenShortList phList = phDAO.getFilteredList(phFilter);
        int phArr[] = phDAO.getFilteredCodeArray(phFilter, 0, -1);

        BigDecimal totalSalary = new BigDecimal(0);
        BigDecimal totalESV = new BigDecimal(0);

        BigDecimal totalESVElectro = new BigDecimal(0);
        BigDecimal totalESVDriver = new BigDecimal(0);


        //for (int i = 0; i < phList.totalCount; i++) {
        for (int i = 0; i < phArr.length; i++) {
            //totalSalary = totalSalary.add(phList.get(i).paysWorkBonus).setScale(2, BigDecimal.ROUND_HALF_UP);
            ENPlanWorkItem2Humen phObj = phDAO.getObject(phArr[i]);

            //totalSalary = totalSalary.add(phList.get(i).paysWorkBonus).setScale(2, BigDecimal.ROUND_HALF_UP);
            totalSalary = totalSalary.add(phObj.paysWorkBonus).setScale(2, BigDecimal.ROUND_HALF_UP);

            /* Посчитаем ЕСВ для инвалидов и неинвалидов */
            //if (phList.get(i).humenKindRefCode == ENHumenItemKind.ELTEH) {
            if (phObj.humenKindRef.code == ENHumenItemKind.ELTEH) {
                FINWorkerFilter fiwFilter = new FINWorkerFilter();
                fiwFilter.conditionSQL = " FINWORKER.CODE IN ( " +
                        " SELECT MAX(FINWORKER.CODE) FROM FINWORKER, ENHUMENITEM " +
                        " WHERE FINWORKER.CODE = ENHUMENITEM.FINWORKERCODE " +
                        " AND ENHUMENITEM.PLANREFCODE in (" + planCodes + ")" +
                        //" AND FINWORKER.TABNUMBER = " + phList.get(i).tabNumber + ")";
                        " AND FINWORKER.TABNUMBER = '" + phObj.tabNumber + "')";

                FINWorkerShortList fiwList = fiwDAO.getScrollableFilteredList(fiwFilter, fiwFilter.conditionSQL, 0, -1);

                BigDecimal esvPercent = new BigDecimal(0);

                if (fiwList.totalCount > 0)
                    esvPercent = fiwList.get(0).chargePercent.divide(
                            new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP);

                //totalESVElectro = totalESVElectro.add(phList.get(i).paysWorkBonus.multiply(
                totalESVElectro = totalESVElectro.add(phObj.paysWorkBonus.multiply(
                        esvPercent).setScale(10, BigDecimal.ROUND_HALF_UP));
            }

            //if (phList.get(i).humenKindRefCode == ENHumenItemKind.DRIVER) {
            if (phObj.humenKindRef.code == ENHumenItemKind.DRIVER) {
                FINWorkerFilter fiwFilter = new FINWorkerFilter();
                fiwFilter.conditionSQL = " FINWORKER.CODE IN ( " +
                        " SELECT MAX(FINWORKER.CODE) FROM FINWORKER, ENTRANSPORTITEM  " +
                        " WHERE FINWORKER.CODE = ENTRANSPORTITEM.FINWORKERCODE " +
                        " AND ENTRANSPORTITEM.PLANREFCODE in (" + planCodes    + ")" +
                        //" AND FINWORKER.TABNUMBER = " + phList.get(i).tabNumber + ")";
                        " AND FINWORKER.TABNUMBER = '" + phObj.tabNumber + "')";

                FINWorkerShortList fiwList = fiwDAO.getScrollableFilteredList(fiwFilter, fiwFilter.conditionSQL, 0, -1);

                BigDecimal esvPercent = new BigDecimal(0);

                if (fiwList.totalCount > 0)
                    esvPercent = fiwList.get(0).chargePercent.divide(new BigDecimal(100), 4, BigDecimal.ROUND_HALF_UP);

                //totalESVDriver = totalESVDriver.add(phList.get(i).paysWorkBonus.multiply(esvPercent).setScale(10, BigDecimal.ROUND_HALF_UP));
                totalESVDriver = totalESVDriver.add(phObj.paysWorkBonus.multiply(esvPercent).setScale(10, BigDecimal.ROUND_HALF_UP));
            }
        }

        totalESVElectro = totalESVElectro.setScale(2, BigDecimal.ROUND_HALF_UP);
        totalESVDriver = totalESVDriver.setScale(2, BigDecimal.ROUND_HALF_UP);
        totalESV = totalESVElectro.add(totalESVDriver);

        /* Транспорт */
        ENAct2TransportDAO actrDAO = new ENAct2TransportDAO(connection, userProfile);
        ENAct2TransportFilter actrFilter = new ENAct2TransportFilter();
        actrFilter.actRef.code = actCode;
        ENAct2TransportShortList actrList = actrDAO.getFilteredList(actrFilter);
        BigDecimal totalTransport = new BigDecimal(0);
        for (int i = 0; i < actrList.totalCount; i++) {
            totalTransport = totalTransport.add(actrList.get(i).paysWork).setScale(2, BigDecimal.ROUND_HALF_UP);
        }

        /* Материалы */
        /*Для актов по доработке не берутся в расчет актов дорабатываемые материалы*/
        String matConditionSQL = "select es_beforerefined.code " +
                                " from enestimateitem as es_beforerefined inner join enestimateitem as es_refined " +
                                " on (es_beforerefined.planrefcode = es_refined.planrefcode " +
                                " and es_beforerefined.materialrefcode = es_refined.materialrefcode " +
                                "    and (es_beforerefined.planitemrefcode = es_refined.planitemrefcode " +
                                "           or es_refined.planitemrefcode is null)) " +
                                " where es_refined.kindrefcode = " + ENEstimateItemKind.REFINEMENT +
                                " and es_beforerefined.planrefcode in ("+planCodes+")" +
                                " and es_beforerefined.kindrefcode = " + ENEstimateItemKind.MATERIALS;
        ENEstimateItemFilter eFilter = new ENEstimateItemFilter();
        eFilter.conditionSQL = "ENESTIMATEITEM.PLANREFCODE IN (" + planCodes + ") and ENESTIMATEITEM.KINDREFCODE in (1,2) and ENESTIMATEITEM.CODE NOT IN ("+matConditionSQL+")";
        ENEstimateItemShortList eList = eDAO.getScrollableFilteredList(eFilter, eFilter.conditionSQL, 0, -1);

        BigDecimal totalMaterialsCost = new BigDecimal(0);

        for (int i = 0; i < eList.totalCount; i++) {
            FINMaterialsFilter fFilter = new FINMaterialsFilter();
            fFilter.estimateItemRef.code = eList.get(i).code;
            fFilter.statusRef.code = FINMaterialsStatus.GOOD;
            FINMaterialsShortList fList = fDAO.getFilteredList(fFilter);
            for (int j = 0; j < fList.totalCount; j++)
                totalMaterialsCost = totalMaterialsCost.add(fList.get(j).cost);

        }

        /*Высчитаем загальнопроизводственные затраты (70%) из необлагаемой ЕСВ зарплатой*/
        BigDecimal commonEnterpriseCosts = new BigDecimal(0);

        if(act.actTypeRef.code != ENPlanWorkState.REFINEMENT) {
        	commonEnterpriseCosts = totalSalary.multiply(aLogic.getProductionExpencesRateByAct(actCode)).setScale(2, BigDecimal.ROUND_HALF_UP);
        }

        /* На зарплату накинем сумму ЕСВ */
        totalSalary = totalSalary.add(totalESV).setScale(2, BigDecimal.ROUND_HALF_UP);

        /* Всего + 70% */
        totalCost = totalSalary.add(totalTransport.add(totalMaterialsCost));
        totalCost = totalCost.add(commonEnterpriseCosts);

        return totalCost;
    }


public void calcTransport4SideWorks  (ENAct act) throws PersistenceException, DatasourceConnectException
{
    // транспортные расходы
    ENAct2TransportFilter a2tFilter = new ENAct2TransportFilter();
    ENAct2TransportDAO a2tDAO = new ENAct2TransportDAO(connection, userProfile);
    ENTransportItemDAO transportDAO =  new ENTransportItemDAO(connection, userProfile);
    TKClassificationTypeDAO tkcDAO = new TKClassificationTypeDAO(connection, userProfile);
    TKClassificationTypeFilter tkcFilter = new TKClassificationTypeFilter();
    TransportLogic transportLogic = new TransportLogic(connection, userProfile);
    PlanWorkLogic planLogic = new PlanWorkLogic(connection, userProfile);
    TKTransportHistoryDAO transportHistoryDao = new TKTransportHistoryDAO(connection, userProfile);

    Connection fkConnection = getNEWConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
    FINLogic finLogic = new FINLogic(fkConnection, userProfile);
    FKOSLogic osLogic = new FKOSLogic(fkConnection, userProfile);

    tkcFilter.conditionSQL = " tk.code in (select tktechcard.classificationtypecode " +
        " from enact , enact2enplanwork , enplanworkitem , tktechcard " +
        " where enact.code = enact2enplanwork.actrefcode " +
        " and enplanworkitem.planrefcode = enact2enplanwork.plancode " +
        " and enplanworkitem.kartarefcode = tktechcard.code " +
        " and enact.code = " + act.code + ")";

    int[] class2act = tkcDAO.getFilteredCodeArray(tkcFilter, tkcFilter.conditionSQL, null, 0, -1, null);

    BigDecimal[] monthTimes = finLogic.getWorkTime(act.dateAct);
    BigDecimal monthTime = monthTimes[0];

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
        " and ent.planitemrefcode = pwi.code " +
        " and pwi.countgen > 0  " +
        " and coalesce(ent.transportrealcode, -1) <> -1 " +
        " ) " ;
    /// Для того что бы брался только транспорт со статусом в работе. Откидываем сторонний транспорт
    trFilter.transportstatus.code = TKTransportStatus.TKTRANSPORTSTATUS_WORK;  ///
    ///
    trFilter.orderBySQL = "tktransportreal.invnumber";

    TKTransportRealDAO trDAO = new TKTransportRealDAO(connection, userProfile);
    TKTransportRealShortList trList = trDAO.getScrollableFilteredList(trFilter, 0, -1);

    // удалим то что осталось от предыдущих строк акта
        for (int i = 0; i < a2tArr.length ; i++){
            a2tDAO.remove(a2tArr[i]);
        }

    ENAct2Transport a2t = null;
    for (int c=0; c < class2act.length; c++)
    {
    	TKClassificationType clsType = tkcDAO.getObject(class2act[c]);
    for (int i=0; i < trList.totalCount; i++){

        BigDecimal workerWorkTime = new BigDecimal(0);

        ENTransportItemFilter transportFilter = new ENTransportItemFilter();
        transportFilter.transportReal.code = trList.get(i).code;
        transportFilter.conditionSQL =
            "entransportitem.code in ( " +
            " Select " +
            " ent.code  " +
            " From entransportitem ent left join finworker fw on ent.finworkercode = fw.code,  enact2enplanwork ena2, enplanworkitem pwi, tktechcard tk " +
            " Where " +
            " ena2.actrefcode = " + act.code +
            " and ent.planrefcode = ena2.plancode " +
            " and tk.code = pwi.kartarefcode " +
            " and tk.classificationtypecode = " + class2act[c] +
            " and ent.planitemrefcode = pwi.code " +
            " and pwi.countgen > 0  " +
            " ) " ;

        ENTransportItemShortList transportList = transportDAO.getScrollableFilteredList(transportFilter, 0, -1);

        a2t = new ENAct2Transport();
        a2t.code = Integer.MIN_VALUE;

        a2t.actRef.code = act.code;
        a2t.classificationTypeRef.code = class2act[c];
        a2t.invNumber = trList.get(i).invNumber;
        a2t.name = trList.get(i).name + " (" + trList.get(i).gosNumber+")";
        BigDecimal gsm = new BigDecimal(0);
        for (int j=0; j < transportList.totalCount; j++){
            // как то ЭТО не правильно !!!!
            // по идее в нормативах для Бригадных авто время СПЕЦИАЛЬНО для износа !!!
            if (transportList.get(j).tktransportTypeCode != TKTransportType.BRIGADE )
            {
                workerWorkTime = workerWorkTime.add(transportList.get(j).countWorkFact)    ;
            }
            // добавим время проезда ...
            BigDecimal[] distArr = transportLogic.getDistancesByTransport(transportList.get(j).code, false);
            if ( distArr != null){
                BigDecimal delTime = transportLogic.calcTimeByDistaces(distArr[0], distArr[1], distArr[2], planLogic.isWinterMonth( transportList.get(j).planRefDateStart), transportLogic.isTraktor(transportList.get(j).code)).setScale(2, BigDecimal.ROUND_HALF_UP);
                workerWorkTime = workerWorkTime.add(delTime).setScale(2, BigDecimal.ROUND_HALF_UP);
            }
            // кол-во ГСМ
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
	        a2t.depreciationHours = a2t.depreciationMonth.divide(monthTime, 3 , java.math.BigDecimal.ROUND_HALF_UP);
	        
	        
	        a2t.repairCostsPerHour = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);
	        
	        //SUPP-88288
	        if(transportList != null && transportList.totalCount > 0) {
		        TKTransportHistory transportHistory = transportHistoryDao.getObjectOnDate(transportList.get(0).transportCode, act.dateAct);
		        

		        if(clsType.isnotlicensedactivity == TKConsts.ISNOTLICENSEDACTIVITY_SERVICE_TRANSPORT
		        		&& transportHistory != null && transportHistory.annualRepairCostsPercent != null) {
		        	Calendar cPreviousYear = Calendar.getInstance();
		        	cPreviousYear.setTime(act.dateAct);
		        	cPreviousYear.set(Calendar.MONTH, Calendar.DECEMBER);
		        	cPreviousYear.set(Calendar.DATE, 1);
		        	cPreviousYear.add(Calendar.YEAR, -1);
		        	BigDecimal vehicleCost = osLogic.getResidualValue(a2t.invNumber, cPreviousYear.getTime());
		        	BigDecimal repairCost = vehicleCost.multiply(transportHistory.annualRepairCostsPercent.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP));
		        	a2t.repairCostsPerHour = repairCost.divide(new BigDecimal(12), 2, BigDecimal.ROUND_HALF_UP)
		        			.divide(monthTime, 2, java.math.BigDecimal.ROUND_HALF_UP);
		        }	        	
	        }
        
        

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
        
        a2t.paysWork = a2t.paysWork.add(a2t.repairCostsPerHour.multiply(workerWorkTime).setScale(2, BigDecimal.ROUND_HALF_UP));
       }
        // если затраты меньше 0,01 то необходимо присвоить 0,01
        //a2t.paysWork = (a2t.depreciationHours.multiply(workerWorkTime)).setScale(2, java.math.BigDecimal.ROUND_HALF_UP);


        if (a2t.code == Integer.MIN_VALUE &&  a2t.timeWork.doubleValue() != 0.0)
        {
            a2tDAO.add(a2t);
        }


    }
    }

}

public BigDecimal getVehicleRepairCostsPerHour(String invNumber, Date date) {
	if(date == null) throw new java.lang.NullPointerException("Не задана дата");
	if(invNumber == null) throw new java.lang.NullPointerException("Не заданий інвентарний номер");
	
	BigDecimal result = BigDecimal.ZERO.setScale(2, BigDecimal.ROUND_HALF_UP);
	
	return result;
}


public int[] getCalculationInAct(int actCode) throws PersistenceException
{

  int[] out = null;
 TKClassificationTypeDAO tkcDAO = new TKClassificationTypeDAO(connection, userProfile);
 TKClassificationTypeFilter tkcFilter = new TKClassificationTypeFilter();
 tkcFilter.conditionSQL = " tk.code in (select tktechcard.classificationtypecode " +
        " from enact , enact2enplanwork , enplanworkitem , tktechcard " +
        " where enact.code = enact2enplanwork.actrefcode " +
        " and enplanworkitem.planrefcode = enact2enplanwork.plancode " +
        " and enplanworkitem.kartarefcode = tktechcard.code " +
        " and enact.code = " + actCode + ")";

 out = tkcDAO.getFilteredCodeArray(tkcFilter, tkcFilter.conditionSQL, null, 0, -1, null);

  return out;
}



	/**
	 * расчет акта
	 *
	 * @param actCode - код акта
	 * @param isSignatured - указание для перевода в статус "На подписи"
	 *
	 */
	public void calcActs(int actCode, boolean isSignatured)
			throws PersistenceException, DatasourceConnectException {

		Connection docFlowConnection = null;

		try {

			/*
			if (userProfile.userName.equals("energynet")) {
				removeFullActList("" + actCode);
				removeFullActList("" + Integer.MIN_VALUE);
			}
			*/

			long calcActStartTime = System.nanoTime();

			ActsData vAct = new ActsData("" + actCode);
			ActsData hAct = fullActList.get(vAct);
			if (hAct != null) {
				throw new EnergyproSystemException("\n\n "
						+ "Цей акт розраховує користувач " + hAct.userName);
			}

			synchronized (fullActList) {
				fullActList.put(vAct, vAct);
			}

			_actsTable = new Hashtable<ActsData, ActsData>();
			_plansData2 = new Hashtable<PlansData15, PlansData15>();
			Hashtable<HumenData15, HumenData15> humenData = new Hashtable<HumenData15, HumenData15>();

			int departmentCode = getDepartmentInAct(actCode);

			ENPlan2HumenDAO p2hDAO = new ENPlan2HumenDAO(connection, userProfile);
			ENPlan2HumenShortList p2hList = p2hDAO.getTabNumberList(actCode, departmentCode);

			// Список табельных из основного акта, который мы переводим на подписание
			List<String> tabNumbers = p2hList.list.stream().map(p2h -> p2h.tabNumber).collect(Collectors.toList());
			System.out.println("!!!!!!!!!!! ACT: " + actCode + " | TABNUMBERS: " + tabNumbers);

			{
				System.out.println("#_# Start calcActs : " + actCode);
				fillActs(actCode, "" + Integer.MIN_VALUE, departmentCode, humenData, tabNumbers);
				Vector<PlansData15> v = new Vector<PlansData15>(_plansData2.keySet());

				System.out.println("#_# Start recalc4Norms : " + actCode);
				for (Enumeration<PlansData15> e = v.elements(); e.hasMoreElements();) {
					PlansData15 key = e.nextElement();
					try {
						Date planDate = new SimpleDateFormat("dd.MM.yyyy").parse(key.planDate);

						// 19.10.2021 Начиная с 01.10.2021 (по дате плана), НЕ пересчитываем связанные акты
						// (т.к. мы начали обрезать людям время еще на этапе планирования)
						int actCodeForRecalc = Integer.MIN_VALUE;
						/* 21.10.2021 Пока будем выбирать данные для человека по планам за день не только для текущего акта,
						 * а для всех, где есть план по нему за ту же дату, как и раньше (потому что иначе сумма по времени для 
						 * человека за день по всем актам вылезет за 8 часов, из-за того, что в планах мы ограничиваем время
						 * без учета фактической доставки)
						if (planDate.compareTo(ENConsts.ENACT_SIMPLE_CALC_START_DATE) >= 0) {
							actCodeForRecalc = actCode;
							System.out.println("#_# recalc ONLY for act: " + actCode + ", tabNumber: " + key.tabNumber);
						}
						*/

						recalc4Norms(
								key.tabNumber,
								planDate,
								new Integer(key.positionCode).intValue(),
								new BigDecimal(key.salary),
								key.positionId,
								actCodeForRecalc);

					} catch (ParseException exception) {
						removeFullActList("" + Integer.MIN_VALUE);
						throw new EnergyproSystemException(
								exception.getMessage());
					}
				}

				System.out.println("#_# Final recalc4Norms : " + actCode);


				ENActDAO actDAO = new ENActDAO(connection, userProfile);
				ENAct actObj = actDAO.getObject(actCode);

				docFlowConnection = getConnection(AuthorizationJNDINames.DOCFLOW_DATASOURCE);

				//ServicesLogic servicesLogic = new ServicesLogic(connection, userProfile);

				Vector<ActsData> v1 = new Vector<ActsData>(_actsTable.keySet());
				for (Enumeration<ActsData> e = v1.elements(); e.hasMoreElements();) {
					ActsData key1 = e.nextElement();
					ENAct act__ = actDAO.getObject(new Integer(key1.actCode).intValue());

					// Попробуем не вызывать для других актов, кроме текущего
					//if (act__.code != actCode) {
					//	continue;
					//}

					if (act__.statusRef.code == ENActStatus.GOOD) {
	                    fillActDatas(new Integer(key1.actCode).intValue(), humenData, tabNumbers);

	                    boolean mustBeSigned = false;

						if (isSignatured) {
							/**
							 * 21.03.2015 +++ для услуг на сторону зависимым актам статус не изменяем...
							 */
							if (act__.actTypeRef.code == ENPlanWorkState.WORK_IN_OUT) {
								if (act__.code == actCode) {
									mustBeSigned = true;
								}
								/*
								// Для работ по заявкам поставщиков акты переводим на подписание
								if (servicesLogic.isActForServicesObjectSupplierContract(act__.code)) {
									System.out.println("@@@@@ ACT for SUPPLIER - SIGNING: " + act__.code);
									mustBeSigned = true;
								}
								*/
							} else {
								mustBeSigned = true;
							}
						} else {
							/*
							// Для работ по заявкам поставщиков акты переводим на подписание
							if (servicesLogic.isActForServicesObjectSupplierContract(act__.code)) {
								System.out.println("@@@@@ ACT for SUPPLIER - SIGNING (2): " + act__.code);
								mustBeSigned = true;
							}
							*/
						}

						if (mustBeSigned) {
							act__.statusRef.code = ENActStatus.SIGNATURE;
							actDAO.save(act__);

							// NET-4596 Подписание документа
							if (DocSigningLogic.isSignable(act__)) {
								DocSigningLogic docSigningLogic = DocSigningLogic.getInstanceForObject(docFlowConnection, userProfile, act__);
								// Для актов, которые формируются автоматически из ОЗ по счетчикам, пока не будем проверять
								if (! checkIfActIsAutoByOZ(act__)) {
									docSigningLogic.checkDocCodeForObject(act__);
								}
								docSigningLogic.createFirstDocMovementForSigning(act__);
							}
						}
	                }
				}

				/*Расчет стоимости готовой продукции*/
				calcProduced(actCode);

				/*Расчет стоимости доработанного материала*/
				if (actObj.actTypeRef.code == ENPlanWorkState.REFINEMENT
						&& actObj.element.typeRef.code != ENElementType.METROLOGY_OBJECT) {
					ActLogic aLogic = new ActLogic(connection, userProfile);
					FINMaterialsDAO fDAO = new FINMaterialsDAO(connection,userProfile);

					String planCodes = aLogic.getPlanCodesByAct(actCode);
					String conditionSQL = "select ma.code from "
							+ " enestimateitem as es inner join finmaterials as ma on ma.estimateitemrefcode = es.code "
							+ " where es.kindrefcode = " + ENEstimateItemKind.REFINEMENT + " and es.planrefcode in (" +planCodes + ")";

							FINMaterialsFilter maRefinedFilter = new FINMaterialsFilter();
							maRefinedFilter.conditionSQL = "FINMATERIALS.CODE IN ("+conditionSQL+")";
							FINMaterialsShortList fRefinedList = fDAO.getScrollableFilteredList(maRefinedFilter, maRefinedFilter.conditionSQL, 0, -1);

					if (fRefinedList.totalCount == 1) {
						/*Нахождение материала до доработки*/
						conditionSQL = "select ma.code from "
								+ " enestimateitem as es inner join finmaterials as ma on ma.estimateitemrefcode = es.code "
								+ " where es.kindrefcode = " + ENEstimateItemKind.MATERIALS + " and es.planrefcode in (" +planCodes + ") "
								+ " and es.materialrefcode = ( "
								+ " select es1.materialrefcode from enestimateitem as es1 where es1.code = " + fRefinedList.get(0).estimateItemRefCode+ ")";

						FINMaterialsFilter maBeforeRefinedFilter = new FINMaterialsFilter();
						maBeforeRefinedFilter.statusRef.code = FINMaterialsStatus.GOOD;
						maBeforeRefinedFilter.conditionSQL = "FINMATERIALS.CODE IN ("+conditionSQL+")";
						FINMaterialsShortList maBeforeRefinedList = fDAO.getScrollableFilteredList(maBeforeRefinedFilter, maBeforeRefinedFilter.conditionSQL, 0, -1);

						if (maBeforeRefinedList.totalCount != 1) {
							removeFullActList("" + actCode);
							throw new EnergyproSystemException("\n\n "
									+ "Помилка у кількості матеріалів до доробки");
						}

						/*Расчет суммы доработанного материала по сумме акта и сумме материала до доработки*/
						FINMaterials fMaterial = fDAO.getObject(fRefinedList.get(0).code);
						BigDecimal totalCost = this.calcTotalCostByAct(actCode);
						totalCost = totalCost.add(maBeforeRefinedList.get(0).cost);

						BigDecimal price = totalCost.divide(fMaterial.quantity, BigDecimal.ROUND_HALF_UP).setScale(3, BigDecimal.ROUND_HALF_UP);
						fMaterial.price = price;
						fMaterial.calc_price = price;
						fMaterial.cost = totalCost;
						fMaterial.fullCost = totalCost;
						fDAO.save(fMaterial);
					}
				}

				System.out.println("#_# Final calcActs : " + actCode);
				removeFullActList("" + actCode);
			
				ActLogic actLogic = new ActLogic(connection, userProfile);
				// определение шаблона проводок по услугам на сторону  
	   			if (actObj.element.typeRef.code == ENElementType.SERVICES_OBJECT 
	   				|| actObj.actTypeRef.code == ENPlanWorkState.DESIGNING
	   				|| actObj.element.typeRef.code == ENElementType.OPERATIVE_OBJECT 
	   				// Договори на відшкодування з постачальниками е/е
	   				|| ( (actObj.element.typeRef.code  == ENElementType.TY_BYT || actObj.element.typeRef.code  == ENElementType.TY_PROM )  
	   						&& actObj.actTypeRef.code == ENPlanWorkState.WORK_IN_OUT )
	   				) {
	   				actLogic.setPostingTemplateAndFinKodIstByAct(actObj,true);
	   			}
			}

			long calcActEndTime = System.nanoTime();
			long calcActTime = (calcActEndTime - calcActStartTime) / 1000000000;
			System.out.println("############# calcActTime = " + calcActTime + " sec");
			
			

		}
		catch (PersistenceException e) {
			removeFullActList("" + actCode);

	        System.out.println("#_#_#_err");
	        System.out.println(e.getMessage());
	        throw new EnergyproSystemException("\n\n "
	        		+ "Акт (цей або зв_язаний з ним) розраховується іншим користувачем ... запустіть розрахунок ще раз.\n" + e.getMessage(), userProfile);
	    }
		catch (NullPointerException e) {
			removeFullActList("" + actCode);

	        System.out.println("#_#_#_err_null");
	        e.printStackTrace();
	        throw new EnergyproSystemException("\n\n "
	        		+ "Акт (цей або зв_язаний з ним) вже видалений іншим користувачем ... запустіть розрахунок ще раз", userProfile);
	    }
		catch (Exception e) {
			String actCodes = removeFullActList("" + actCode);
			throw new SystemException(e.getMessage() + "\nКоди актів: " + actCodes, e);
	    } finally {
	        try {
				if (docFlowConnection != null && !docFlowConnection.isClosed()) {
					docFlowConnection.close();
					docFlowConnection = null;
				}
			} catch (SQLException e) {
			}
	    }
	}


	public void fillActDatas(int actCode, Hashtable<HumenData15, HumenData15> humenData, 
			List<String> tabNumbers) throws PersistenceException, DatasourceConnectException {
		Connection fkConnection = null;

		try {
			fkConnection = getNEWConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
			ENAct act = new ENActDAO(connection, userProfile).getObject(actCode);
			ENPlanWorkDAO planDao = new ENPlanWorkDAO(connection, userProfile);

			 boolean isServices = false;
             ENPlanWorkFilter planFilter = new ENPlanWorkFilter();
             planFilter.conditionSQL = " enplanwork.code in (select e2a.plancode from enact2enplanwork e2a " +
                     " where e2a.actrefcode = " + actCode + ")";
             int pln[] = planDao.getFilteredCodeArray(planFilter, 0, -1);
             if (pln.length > 0) {
                 ENPlanWork plan = planDao.getObject(pln[0]);
                 if (plan.typeRef.code == ENPlanWorkType.SERVICES_FROM_SIDE
                         || plan.typeRef.code == ENPlanWorkType.SERVICES_FROM_SIDE_INVEST) {
                     isServices = true;
                 }
             }

            System.out.println("#_# Start fillActDatas : act №" + act.numberGen + " ("+ act.code+")");

            System.out.println("!!!!!!!!!!! ACT: " + actCode + " | TABNUMBERS: " + tabNumbers);
            String tabNumbersList = Tools.arrayToStr(Tools.getQuotedStringArray(tabNumbers), ",");

            boolean notEmptyTabNumbersList = (tabNumbersList != null && !tabNumbersList.isEmpty());

            ENStandardConstDAO dao = new ENStandardConstDAO(connection, userProfile);
            ENActDAO actDAO = new ENActDAO(connection, userProfile);
			ENAct2HumenDAO a2hDAO = new ENAct2HumenDAO(connection, userProfile);
			ENTransportItemDAO transportDAO = new ENTransportItemDAO(connection, userProfile);
			FINLogic finLogic = new FINLogic(fkConnection, userProfile);
			ENHumenItemDAO humenDAO = new ENHumenItemDAO(connection,userProfile);


            BigDecimal month2Time = null;
            BigDecimal month2Pays = null;

            FINWorkerDAO fwDAO = new FINWorkerDAO(connection, userProfile);
            FINWorkerDAO fkWorkerDAO = new FINWorkerDAO(fkConnection, userProfile);

            int eType = act.element.typeRef.code;

			String finWorkerCondition = " finworker.code in ( "
					+ " select enhumenitem.finworkercode  "
					+ " from enhumenitem, enplanwork, enplanworkitem, enact2enplanwork "
					+ "  where enact2enplanwork.actrefcode = " + act.code
					+ "  and enact2enplanwork.plancode = enplanwork.code "
					+ "  and enplanwork.code = enplanworkitem.planrefcode "
					+ "  and enhumenitem.planitemrefcode = enplanworkitem.code  "
					+ "  and enhumenitem.countfact <> 0 "
					+ "  and enplanworkitem.countgen <> 0 "
					/* SUPP-17561 карты по проверкам рабочих мест не попадают в расчет заработной платы */
					+ "  and case when enplanwork.typerefcode = " + ENPlanWorkType.PRIEDNANNY
					+ "  and enplanworkitem.kartarefcode in (" + com.ksoe.energynet.util.Tools.intArrayToStr(TKTechCard.CHECKING_WORKS, ",")
					+ ") then 1 = -1 else 1 = 1 end " + " ) " 
					+ (notEmptyTabNumbersList ? " and finworker.tabnumber in (" + tabNumbersList + ")" : "");

			String humenItemCondition = " enhumenitem.code in ( "
					+ " select enhumenitem.code  "
					+ " from enhumenitem, enplanwork, enplanworkitem, enact2enplanwork "
					+ "  where enact2enplanwork.actrefcode = " + act.code
					+ "  and enact2enplanwork.plancode = enplanwork.code "
					+ "  and enplanwork.code = enplanworkitem.planrefcode "
					+ "  and enhumenitem.planitemrefcode = enplanworkitem.code  "
					+ "  and enhumenitem.countfact <> 0 "
					+ "  and enplanworkitem.countgen <> 0 "
					/* SUPP-17561 карты по проверкам рабочих мест не попадают в расчет заработной платы */
					+ "  and case when enplanwork.typerefcode = " + ENPlanWorkType.PRIEDNANNY
					+ "  and enplanworkitem.kartarefcode in (" + com.ksoe.energynet.util.Tools.intArrayToStr(TKTechCard.CHECKING_WORKS, ",")
					+ ") then 1 = -1 else 1 = 1 end " + " ) " +
					// непривязанные нам НЕ НАДО ??? ;)
					" and enhumenitem.finworkercode is not null ";

            boolean isENERGOSBYT = false;

            //////////////////
            // список ПЛАНОВ для этого акта ...
            String planCodes = "" + Integer.MIN_VALUE;
            ENAct2ENPlanWorkDAO a2pDAO = new ENAct2ENPlanWorkDAO(connection, userProfile);
            ENAct2ENPlanWorkFilter a2pFilter = new ENAct2ENPlanWorkFilter();
            a2pFilter.actRef.code = act.code;

			ENAct2ENPlanWorkShortList a2pList = a2pDAO.getScrollableFilteredList(a2pFilter, 0, -1);
			for (int i = 0; i < a2pList.totalCount; i++) {
				planCodes = planCodes + ", " + a2pList.get(i).planCode;
			}
            //////////////////


            HumenData15 vKey;
            HumenData15 hKey;

			ENHumenItemFilter h2Filter = new ENHumenItemFilter();
			h2Filter.conditionSQL = humenItemCondition;

			Calendar cPlanDateStart = Calendar.getInstance();
			Calendar cActDateGen = Calendar.getInstance();

			ENHumenItemShortList h2List = humenDAO.getScrollableFilteredList(h2Filter, 0, -1);
			for(ENHumenItemShort humenItem : h2List.list) {
				cPlanDateStart.setTime(humenItem.planRefDateStart);
				cActDateGen.setTime(act.dateGen);
				if((cPlanDateStart.get(Calendar.YEAR) != cActDateGen.get(Calendar.YEAR))
						|| (cPlanDateStart.get(Calendar.MONTH) != cActDateGen.get(Calendar.MONTH))) {
					if(eType != ENElementType.SERVICES_OBJECT && isENERGOSBYT) {
						this.removeFullActList("" + act.code);
						throw new EnergyproSystemException("\n\n "
								+ "Рік та місяць акту і плану повинні співпадати ...");
					}
				}
			}

			String transportItemCondition = " entransportitem.finworkercode in (" + transportDAO.getFinWorkerByAct(act.code) + ")"
					+ " and ENPLANWORK.CODE IN (select e.plancode from enact2enplanwork e where e.actrefcode = " + act.code + ")";

			ENTransportItemFilter trFilter1 = new ENTransportItemFilter();
			trFilter1.conditionSQL = transportItemCondition;
			ENTransportItemShortList trList1 = transportDAO.getScrollableFilteredList(trFilter1, 0, -1);
			for(ENTransportItemShort transportItem : trList1.list) {
				cPlanDateStart.setTime(transportItem.planRefDateStart);
				cActDateGen.setTime(act.dateGen);
				if((cPlanDateStart.get(Calendar.YEAR) != cActDateGen.get(Calendar.YEAR))
						|| (cPlanDateStart.get(Calendar.MONTH) != cActDateGen.get(Calendar.MONTH))) {
					if(eType != ENElementType.SERVICES_OBJECT && isENERGOSBYT) {
						this.removeFullActList("" + act.code);
						throw new EnergyproSystemException("\n\n "
								+ "Рік та місяць акту і плану повинні співпадати ...");
					}
				}
			}

            System.out.println("##Elmonters fillActDatas : act №" + act.numberGen + " ("+ act.code+")");


            ///// 22.05.2013 NET-4235 Сафронов: для услуг на сторону водителям-совместителям берем не 5%, а полный оклад!!!
			boolean isServicesFactCalc = false;
			ServicesLogic soLogic = new ServicesLogic(connection, userProfile);

			if (act.element.typeRef.code == ENElementType.SERVICES_OBJECT) {

				ENServicesObject soObj = soLogic.getServicesObjectByElementCode(act.element.code);

				if (soObj != null) {
					if (soObj.calcTypeRef.code == ENServicesObjectCalcType.BY_FACT) {
						isServicesFactCalc = true;
					}
				} else {
					throw new EnergyproSystemException("\n\n "
							+ "NET-4235 Не знайдено договір з послуг на сторону для акту! Код акту: " + act.code, userProfile);
				}
			}
            /////

			int order = 1;
			ENAct2Humen a2h = null;

			ENAct2HumenFilter a2hFilter = new ENAct2HumenFilter();
			a2hFilter.actRef.code = actCode;
			a2hFilter.humenKindRef.code = ENHumenItemKind.ELTEH; 
			a2hFilter.conditionSQL = (notEmptyTabNumbersList ? "tabnumber in (" + tabNumbersList + ")" : null);

			int[] a2hArr = a2hDAO.getFilteredCodeArray(a2hFilter, a2hFilter.conditionSQL, null, 0, -1, null);

			FINWorkerShortList finWorkerList;
            // электромонтеры ...
			// 07,09,2017 для параметризац отдельным запросом , с групировкой по плану
			if (act.actTypeRef.code == ENPlanWorkState.COUNTERS_PARAMETRIZATION_FREE_OF_CHARGE
					||
					act.actTypeRef.code == ENPlanWorkState.COUNTERS_PARAMETRIZATION	){
				finWorkerList = fwDAO.getGroupedListByTabNumberWithGroupByPlan(null, finWorkerCondition, "FINWORKER.tabnumber", 0, -1, null  , planCodes );
			}
			else{
				finWorkerList = fwDAO.getGroupedListByTabNumber2(null, finWorkerCondition, "FINWORKER.NAME", 0, -1, null);
			}

			BigDecimal[] monthTimes = { new BigDecimal(0), new BigDecimal(0) };
			String tabNumber = "";
			for (int i = 0; i < finWorkerList.totalCount; i++) {

				// сделаем, что бы для пациента бралась дата его последнего наряда в акте
				ENPlanWorkDAO maxPlanDAO = new ENPlanWorkDAO(connection, userProfile);
				ENPlanWorkFilter maxPlanFilter = new ENPlanWorkFilter();
				maxPlanFilter.conditionSQL = " enplanwork.code in (" + planCodes + ")" +
				" and enplanwork.datestart  in ( " +
				" select max(pw.datestart) from enhumenitem hi, finworker fw, enplanwork pw  where " +
				" hi.planrefcode in (" + planCodes + ")" +
				" and hi.finworkercode = fw.code " +
				" and fw.tabnumber = '" + finWorkerList.get(i).tabNumber + "'" +
				" and hi.planrefcode = pw.code " +
				" union all " +
				" select max(pw.datestart) from entransportitem ti, finworker fw, enplanwork pw where " +
				" ti.planrefcode in (" + planCodes + ")" +
				" and ti.finworkercode = fw.code " +
				" and fw.tabnumber = '" + finWorkerList.get(i).tabNumber + "'" +
				" and ti.planrefcode = pw.code )";
				ENPlanWorkShortList maxPlanList = maxPlanDAO.getScrollableFilteredList(maxPlanFilter, 0, -1);

				System.out.println("maxPlanCode:" + maxPlanList.get(0).code + " ("+ planCodes +"): " + finWorkerList.get(i).tabNumber);
				/** 19.01.2015 +++ несколько графиков рабочего времени, норма определяется для каждого табельного...  */
				monthTimes = getWorkTime(maxPlanList.get(0).dateStart, finWorkerList.get(i).tabNumber);
				if (i==0 || !tabNumber.equals(finWorkerList.get(i).tabNumber)  ){
					monthTimes = getWorkTime(act.dateAct, finWorkerList.get(i).tabNumber);
				}
				tabNumber = finWorkerList.get(i).tabNumber;

                BigDecimal monthTime = monthTimes[0];
                BigDecimal monthDay = monthTimes[1];

                BigDecimal workerDeliveryTime = new BigDecimal(0);
                BigDecimal workerWorkTimeGEN = new BigDecimal(0);
                BigDecimal workerWorkTimeFACT = new BigDecimal(0);

				if (i < a2hArr.length) {
					a2h = a2hDAO.getObject(a2hArr[i]);
				} else {
					a2h = new ENAct2Humen();
					a2h.code = Integer.MIN_VALUE;
				}

				a2h.fio = null;
				a2h.actRef.code = actCode;
				a2h.humenKindRef.code = ENHumenItemKind.ELTEH;
				a2h.orederNum = order;
				order = order + 1;

				hKey = null;
				vKey = new HumenData15(
						finWorkerList.get(i).tabNumber,
						null,
						null,
						finWorkerList.get(i).positionCode,
						finWorkerList.get(i).priceGen,
						finWorkerList.get(i).positionId);

				hKey = (HumenData15) humenData.get(vKey);
				if (hKey != null) {
					a2h.fio = hKey.fio + " " + hKey.positionName;
					a2h.salary = new BigDecimal(hKey.salary);
				} else {
					removeFullActList("" + act.code);
					throw new EnergyproSystemException("\n\n "
							+ "Данные по таб № " + finWorkerList.get(i).tabNumber + " не найдены ...");
				}

                a2h.tabNumber = finWorkerList.get(i).tabNumber;
                a2h.daysMonth = monthDay;
                if (act.actTypeRef.code == ENPlanWorkState.COUNTERS_PARAMETRIZATION_FREE_OF_CHARGE
    					||
    					act.actTypeRef.code == ENPlanWorkState.COUNTERS_PARAMETRIZATION	){
                a2h.planRef.code = finWorkerList.get(i).planCode;
                }


				ENPlan2HumenFilter p2hFilter = new ENPlan2HumenFilter();
				ENPlan2HumenDAO p2hDAO = new ENPlan2HumenDAO(connection, userProfile);
				p2hFilter.tabNumber = "" + finWorkerList.get(i).tabNumber;
				p2hFilter.humenKindRef.code = ENHumenItemKind.ELTEH;
				///// 12.12.11

				if (finWorkerList.get(i).positionId != null && !finWorkerList.get(i).positionId.equals("")) {
					p2hFilter.positionId = finWorkerList.get(i).positionId;
				} else {
					p2hFilter.positionCode = finWorkerList.get(i).positionCode;
				}

				p2hFilter.priceGen = new BigDecimal(finWorkerList.get(i).priceGen.doubleValue());
				/////
				if (act.actTypeRef.code == ENPlanWorkState.COUNTERS_PARAMETRIZATION_FREE_OF_CHARGE
						||
						act.actTypeRef.code == ENPlanWorkState.COUNTERS_PARAMETRIZATION	){
					p2hFilter.conditionSQL = "planrefcode in (" + finWorkerList.get(i).planCode + ")";
				} else {
					p2hFilter.conditionSQL = "planrefcode in (" + planCodes + ")";
				}


				ENPlan2HumenShortList p2hList = p2hDAO.getScrollableFilteredList(p2hFilter, 0, -1);
				for (int jj = 0; jj < p2hList.totalCount; jj++) {
					workerWorkTimeGEN = workerWorkTimeGEN.add(p2hList.get(jj).timeWorkGen).setScale(2, BigDecimal.ROUND_HALF_UP);
					workerWorkTimeFACT = workerWorkTimeFACT.add(p2hList.get(jj).timeWorkFact).setScale(2, BigDecimal.ROUND_HALF_UP);
				}

                workerDeliveryTime = new BigDecimal(0);

				if (finWorkerList.get(i).isSentAssignment == 1) {

					month2Time = finLogic.getWorkTimeTwoPreviousMonth(act.dateGen).setScale(2, BigDecimal.ROUND_HALF_UP);

					if (month2Time.abs().doubleValue() < 0.1) {
						removeFullActList("" + act.code);
						throw new EnergyproSystemException("\n\n "
								+ "Кількість робочих годин у місяці = 0 !!! ... ");
					}

                    System.out.println("getMiddlePayTwoPreviousMonth  act №" + act.numberGen + " ("+ act.code+"): " + finWorkerList.get(i).tabNumber);

					month2Pays = finLogic.getMiddlePayTwoPreviousMonth(act.dateGen, finWorkerList.get(i).tabNumber);
					// только устроился - его в командировку ...
					if (month2Pays == null) {
						a2h.timeMonth = monthTime;
						if (monthTime.abs().doubleValue() < 0.1) {
							removeFullActList("" + act.code);
							throw new EnergyproSystemException("\n\n "
									+ "Кількість робочих годин у місяці = 0 !!! ... ");
						}

						a2h.salaryHours = a2h.salary.setScale(2, java.math.BigDecimal.ROUND_HALF_UP).divide(monthTime, 2, java.math.BigDecimal.ROUND_HALF_UP)
								.setScale(2, java.math.BigDecimal.ROUND_HALF_UP).setScale(2, java.math.BigDecimal.ROUND_HALF_UP);

						a2h.salary = null;
						a2h.timeMonth = null;

					} else {
						a2h.salary = null;
						a2h.timeMonth = null;
						month2Pays = month2Pays.setScale(2, BigDecimal.ROUND_HALF_UP);
						a2h.salaryHours = month2Pays.setScale(2, java.math.BigDecimal.ROUND_HALF_UP).divide(month2Time, 2, java.math.BigDecimal.ROUND_HALF_UP)
								.setScale(2, java.math.BigDecimal.ROUND_HALF_UP).setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
					}

				} else {
					a2h.timeMonth = monthTime;
					if (monthTime.abs().doubleValue() < 0.1) {
						removeFullActList("" + act.code);
						throw new EnergyproSystemException("\n\n "
								+ "Кількість робочих годин у місяці = 0 !!! ... ");
					}
					a2h.salaryHours = a2h.salary.setScale(2, java.math.BigDecimal.ROUND_HALF_UP).divide(monthTime, 2, java.math.BigDecimal.ROUND_HALF_UP)
							.setScale(2, java.math.BigDecimal.ROUND_HALF_UP).setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
				}


                // така ж фигня ниже в дрАверах ...
				a2h.timeObjectWork = workerWorkTimeGEN;
				a2h.timeDelivery = workerDeliveryTime;
				a2h.timeWork = workerWorkTimeFACT;
				a2h.timeWorkFact = workerWorkTimeGEN;
				a2h.paysWork = a2h.salaryHours.multiply(a2h.timeWork.setScale(2, java.math.BigDecimal.ROUND_HALF_UP)).setScale(2, java.math.BigDecimal.ROUND_HALF_UP);

                ///// 22.05.2013 NET-4235 Сафронов: для услуг на сторону водителям-совместителям берем не 5%, а полный оклад!!!
				if (!isServicesFactCalc) {
                    // для обьекта  - автомобиль - эти люди НЕ совместители ;))
					if (eType != ENElementType.TRANSPORT) {
						////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    /
						// 27.05.11 Определяем, совместитель ли этот эл. монтер, по коду доджности (если он по должности водитель)

						AuthLogic netAuth = new AuthLogic(connection, userProfile);
						if (netAuth.checkUsesMDAXData()) {
							System.out.println("######### get AXDrivers fillActDatas");

							for (int d = 0; d < ENConsts.AX_DRIVERS_POSITIONS.length; d++) {
								if (hKey.positionId.toUpperCase().equals(ENConsts.AX_DRIVERS_POSITIONS[d].toUpperCase())) {
	                                // типа совместитель .. накинем 5%
									if (a2h.salary != null) {
	                                    //throw new EnergyproSystemException("У сумісника з таб№ "+ a2h.tabNumber +" не знайден оклад");
	                                    a2h.salary = ( a2h.salary.multiply(new BigDecimal(0.05)).setScale(2, BigDecimal.ROUND_HALF_UP));
	                                }

									a2h.salaryHours = (a2h.salaryHours.multiply(new BigDecimal(0.05)).setScale(2, BigDecimal.ROUND_HALF_UP));
									a2h.paysWork = (a2h.paysWork.multiply(new BigDecimal(0.05)).setScale(2, BigDecimal.ROUND_HALF_UP));

									break;
								}
							}
						} else {
							for (int d = 0; d < ENConsts.DRIVERS_POSITIONS.length; d++) {
								if (new Integer(hKey.positionCode).intValue() == ENConsts.DRIVERS_POSITIONS[d]) {
	                                // типа совместитель .. накинем 5%
									if (a2h.salary != null) {
	                                    //throw new EnergyproSystemException("У сумісника з таб№ "+ a2h.tabNumber +" не знайден оклад");
	                                    a2h.salary = ( a2h.salary.multiply(new BigDecimal(0.05)).setScale(2, BigDecimal.ROUND_HALF_UP));
	                                }

									a2h.salaryHours = (a2h.salaryHours.multiply(new BigDecimal(0.05)).setScale(2, BigDecimal.ROUND_HALF_UP));
									a2h.paysWork = (a2h.paysWork.multiply(new BigDecimal(0.05)).setScale(2, BigDecimal.ROUND_HALF_UP));

									break;
								}
							}
						}

					} // транспорт ..

                    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    /
                    if (a2h.tabNumber.equals("012979")) {
                        // типа совместитель .. накинем 5%
						if (a2h.salary != null) {
                            //throw new EnergyproSystemException("У сумісника з таб№ "+ a2h.tabNumber +" не знайден оклад");
                            a2h.salary = (a2h.salary.multiply(new BigDecimal(0.05)).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }

                        a2h.salaryHours = (a2h.salaryHours.multiply(new BigDecimal(0.05)).setScale(2, BigDecimal.ROUND_HALF_UP));
                        a2h.paysWork = (a2h.paysWork.multiply(new BigDecimal(0.05)).setScale(2, BigDecimal.ROUND_HALF_UP));
                    }

                    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////    /
                    // сварщик СПС ;)
                    if (a2h.tabNumber.equals("013029")) {
                        // типа совместитель .. накинем 30%
                        if (a2h.salary != null) {
                            //throw new EnergyproSystemException("У сумісника з таб№ "+ a2h.tabNumber +" не знайден оклад");
                            a2h.salary = (a2h.salary.multiply(new BigDecimal(0.3)).setScale(2, BigDecimal.ROUND_HALF_UP));
                        }

                        a2h.salaryHours = (a2h.salaryHours.multiply(new BigDecimal(0.3)).setScale(2, BigDecimal.ROUND_HALF_UP));
                        a2h.paysWork = (a2h.paysWork.multiply(new BigDecimal(0.3)).setScale(2, BigDecimal.ROUND_HALF_UP));
                    }
                    ////////////////////////////////////////////////////////
                }
                /////


                // если для ТН не хватает времени для Акта ...
                // + НЕ матюкать если он есть в водителях ...
                if (a2h.timeWork.doubleValue() <= 0) {
                    String errorMessage = "\n\n "
                    		+ "У робітника з таб. № " + a2h.tabNumber + " вже нема часу для включення в Акт № " + act.numberGen
                    		+ " від "+ new SimpleDateFormat("dd.MM.yyyy").format(act.dateGen)
                    		+ " (код елементу "+ act.element.code +")"
                    		+ " (инв.№ "+ act.element.invNumber +")"
                    		+ " (код "+ actCode +") ... треба перерахувати один з зв_язаних з цим актом проведених актів ...";

                    // Пока будем вкидывать с нулевым временем, чтобы хоть как-то посчитать акты
                    //if (soLogic.isActForServicesObjectSupplierContract(act.code)) {
                    //if (userProfile.userName.equalsIgnoreCase("energynet")) {
                    //	System.out.println("@@@@@ ZERO TIME:" + errorMessage);
                    //} else {
                        // выдернем список связанных актов ...
                        removeFullActList("" + act.code);
                    	throw new EnergyproSystemException(errorMessage, userProfile);
                    //}
                }

				if (a2h.code == Integer.MIN_VALUE) {
					a2hDAO.add(a2h);
				} else {
					a2hDAO.save(a2h);
				}

            } // конец электромонтеры


            // удлаим то что осталось от предыдущих строк акта
			if (finWorkerList.totalCount < a2hArr.length) {
				for (int i = finWorkerList.totalCount; i < a2hArr.length; i++) {
					a2hDAO.remove(a2hArr[i]);
				}
			}

            System.out.println("##Drivers fillActDatas : act №" + act.numberGen + " ("+ act.code+")");

            // ЗП водителей ...
			TransportLogic transportLogic = new TransportLogic(connection, userProfile);
			PlanWorkLogic planLogic = new PlanWorkLogic(connection, userProfile);

			finWorkerCondition = " finworker.code in ( "
					+ " Select fw.code "
					+ " From entransportitem ent,finworker fw,  enact2enplanwork ena2, enplanworkitem pwi "
					+ " Where ena2.actrefcode = " + act.code
					+ " and ent.planrefcode = ena2.plancode "
					+ " and ent.finworkercode = fw.code "
					+ " and ent.planitemrefcode = pwi.code "
					+ " and pwi.countgen > 0 " + "  ) "
					+ (notEmptyTabNumbersList ? " and finworker.tabnumber in (" + tabNumbersList + ")" : "");

			order = 1;
			a2h = null;

			a2hFilter = new ENAct2HumenFilter();
			a2hFilter.actRef.code = actCode;
			a2hFilter.humenKindRef.code = ENHumenItemKind.DRIVER;
			a2hFilter.conditionSQL = (notEmptyTabNumbersList ? "tabnumber in (" + tabNumbersList + ")" : null);

			a2hArr = a2hDAO.getFilteredCodeArray(a2hFilter, a2hFilter.conditionSQL, null, 0, -1,null);

            // водители ....
			finWorkerList = fwDAO.getGroupedListByTabNumber2(null, finWorkerCondition, "FINWORKER.NAME", 0, -1, null);
			for (int i = 0; i < finWorkerList.totalCount; i++) {

    			/** 19.01.2015 +++ несколько графиков рабочего времени, норма определяется для каждого табельного...  */
				/*BigDecimal[]*/ monthTimes = getWorkTime(act.dateAct, finWorkerList.get(i).tabNumber);

                BigDecimal monthTime = monthTimes[0];
                BigDecimal monthDay = monthTimes[1];

                BigDecimal workerWorkTimeGEN = new BigDecimal(0);
                BigDecimal workerWorkTimeFACT = new BigDecimal(0);
                BigDecimal workerDeliveryTime = new BigDecimal(0);

				if (i < a2hArr.length) {
					a2h = a2hDAO.getObject(a2hArr[i]);
				} else {
					a2h = new ENAct2Humen();
					a2h.code = Integer.MIN_VALUE;
				}

                a2h.fio = null;
                a2h.actRef.code = actCode;
                a2h.humenKindRef.code = ENHumenItemKind.DRIVER;
                a2h.orederNum = order;
                order = order + 1;

				hKey = null;
				vKey = new HumenData15(
						finWorkerList.get(i).tabNumber,
						null,
						null,
						finWorkerList.get(i).positionCode,
						finWorkerList.get(i).priceGen,
						finWorkerList.get(i).positionId);

				hKey = (HumenData15) humenData.get(vKey);
				if (hKey != null) {
					a2h.fio = hKey.fio + " " + hKey.positionName;
					a2h.salary = new BigDecimal(hKey.salary);
				} else {
					removeFullActList("" + act.code);
                    throw new EnergyproSystemException("\n\n "
                    		+ "Данные по таб № " + finWorkerList.get(i).tabNumber + " не найдены ...");
                }


                a2h.tabNumber = finWorkerList.get(i).tabNumber;
                a2h.daysMonth = monthDay;

                ENPlan2HumenFilter p2hFilter = new ENPlan2HumenFilter();
                ENPlan2HumenDAO p2hDAO = new ENPlan2HumenDAO(connection, userProfile);
                p2hFilter.tabNumber = finWorkerList.get(i).tabNumber;
                p2hFilter.humenKindRef.code = ENHumenItemKind.DRIVER;
                ///// 12.12.11

                if (finWorkerList.get(i).positionId != null && !finWorkerList.get(i).positionId.equals("")) {
                	p2hFilter.positionId = finWorkerList.get(i).positionId;
                } else {
                	p2hFilter.positionCode = finWorkerList.get(i).positionCode;
                }

                p2hFilter.priceGen = new BigDecimal(finWorkerList.get(i).priceGen.doubleValue());
                /////

                p2hFilter.conditionSQL = "planrefcode in (" + planCodes + ")";

				ENPlan2HumenShortList p2hList = p2hDAO.getScrollableFilteredList(p2hFilter, 0, -1);
				for (int jj = 0; jj < p2hList.totalCount; jj++) {
					workerWorkTimeGEN = workerWorkTimeGEN.add(p2hList.get(jj).timeWorkGen).setScale(2, BigDecimal.ROUND_HALF_UP);
					workerWorkTimeFACT = workerWorkTimeFACT.add(p2hList.get(jj).timeWorkFact).setScale(2, BigDecimal.ROUND_HALF_UP);
				}


				if (finWorkerList.get(i).isSentAssignment == 1) {
					a2h.salary = null;
					a2h.timeMonth = null;

					month2Time = finLogic.getWorkTimeTwoPreviousMonth(act.dateGen);
					month2Pays = finLogic.getMiddlePayTwoPreviousMonth(act.dateGen, finWorkerList.get(i).tabNumber);
					a2h.salaryHours = month2Pays
							.setScale(2, java.math.BigDecimal.ROUND_HALF_UP).divide(month2Time, 2, java.math.BigDecimal.ROUND_HALF_UP)
							.setScale(2, java.math.BigDecimal.ROUND_HALF_UP).setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
				} else {
					a2h.timeMonth = monthTime;
					a2h.salaryHours = a2h.salary.setScale(2, java.math.BigDecimal.ROUND_HALF_UP)
							.divide(monthTime, 2, java.math.BigDecimal.ROUND_HALF_UP)
							.setScale(2, java.math.BigDecimal.ROUND_HALF_UP).setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
				}

				a2h.timeObjectWork = workerWorkTimeGEN;
				a2h.timeDelivery = workerDeliveryTime;
				a2h.timeWork = workerWorkTimeFACT;
				a2h.timeWorkFact = workerWorkTimeGEN;
				a2h.paysWork = a2h.salaryHours.multiply(a2h.timeWork.setScale(2, java.math.BigDecimal.ROUND_HALF_UP)).setScale(2, java.math.BigDecimal.ROUND_HALF_UP);

                ///////////////
                // 29.04.11 Хитрый совместитель с табельным 12240
				if ((a2h.tabNumber.equals("012240"))
						|| (a2h.tabNumber.equals("000274"))
						|| (a2h.tabNumber.equals("012791"))
						|| (a2h.tabNumber.equals("013080"))
						|| (a2h.tabNumber.equals("010735"))
						|| (a2h.tabNumber.equals("012092")/* SUPP-9345 */)
						|| (a2h.tabNumber.equals("014136")/* SUPP-75655 */) ) {

                    // типа совместитель ... накинем 15% (водителю!!!)
                    if (a2h.salary != null) {
                        //throw new EnergyproSystemException("У сумісника з таб№ " + a2h.tabNumber + " не знайден оклад");
                        a2h.salary = (a2h.salary.multiply(new BigDecimal(0.15)).setScale(2, BigDecimal.ROUND_HALF_UP));
                    }

                    a2h.salaryHours = (a2h.salaryHours.multiply(new BigDecimal(0.15)).setScale(2, BigDecimal.ROUND_HALF_UP));
                    a2h.paysWork = (a2h.paysWork.multiply(new BigDecimal(0.15)).setScale(2, BigDecimal.ROUND_HALF_UP));
                }
                ///////////////


				if (a2h.code == Integer.MIN_VALUE) {
					a2hDAO.add(a2h);
				} else {
					a2hDAO.save(a2h);
				}
            }


            // Выбрать все строки по акту и накинуть процент премии на строки Human
            BigDecimal Bonus = ENConsts.BONUS_PERCENT;
            BigDecimal Percent_additional_bonus_for_act_without_calculation = new BigDecimal(0);

            // SUPP-75899 +++++ добавить к стандартному проценту премии величину процента среднерасчитанной премии для актов которые без калькуляций но относятся к услугам на сторону 
            ENActFilter actFil = new ENActFilter();
            actFil.conditionSQL = " enact.code in (  /*акты по договорам на выполнение работ*/ \n"+
					 "	select a2p.actrefcode from enact2enplanwork a2p , enservices2plan s2p \n"+    
					 "	where a2p.actrefcode= " + actCode +  
					 "	and a2p.plancode=s2p.planrefcode \n"+
					 "	union \n"+
					 "	/*акты присоединение без калькуляций*/ \n"+
					 "	select a2p.actrefcode from enact2enplanwork a2p  , entechcond2planwork t2p \n"+
					 "	where a2p.actrefcode= " + actCode +  
					 "	and a2p.plancode=t2p.planrefcode "
					 + "	union \n"+
					/*Об'єкти договорів на ТО та ремонт add by SUPP-76878 так как это тоже услуги но без калькуляций   */
					" select a.code from enact a , enactincome2enact ai2a , enelement el \n"+ 
					" where a.code = " + actCode + 
					" and ai2a.actrefcode = a.code \n"+ 
					" and a.elementcode = el.code \n"+
					" and el.typerefcode = "+ENElementType.OPERATIVE_OBJECT +" ) ";
            int[] actArr = actDAO.getFilteredCodeArray(actFil, 0, -1);
            if(actArr.length>0){
            	
            	Percent_additional_bonus_for_act_without_calculation = dao.getENStandardConstEntryOnDate(ENStandardConst.PERCENT_ADDITIONAL_BONUS_FOR_ACT_WITHOUT_CALCULATION, act.dateAct);
            
            	if(Percent_additional_bonus_for_act_without_calculation!= null){
                	if (Percent_additional_bonus_for_act_without_calculation.doubleValue() > 0 ){
                		Bonus = Bonus.add(Percent_additional_bonus_for_act_without_calculation);
                	}
                }
            }
            
            
            
            
            ENAct2HumenFilter at2hFilter = new ENAct2HumenFilter();
            at2hFilter.actRef.code = actCode;

			int at2hArr[] = a2hDAO.getFilteredCodeArray(at2hFilter, 0, -1);
			for (int h = 0; h < at2hArr.length; h++) {
				ENAct2Humen at2hObj = a2hDAO.getObject(at2hArr[h]);
				at2hObj.bonus = Bonus;
				at2hObj.salaryHoursBonus = at2hObj.salaryHours.multiply(
						new BigDecimal(1).add((Bonus).divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP))).setScale(2, BigDecimal.ROUND_HALF_UP);
				at2hObj.paysWorkBonus = at2hObj.salaryHoursBonus.multiply(
						at2hObj.timeWork.setScale(2, java.math.BigDecimal.ROUND_HALF_UP)).setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
				a2hDAO.save(at2hObj);
			}

            // удалим то что осталось от предыдущих строк акта
			if (finWorkerList.totalCount < a2hArr.length) {
				for (int i = finWorkerList.totalCount; i < a2hArr.length; i++) {
					a2hDAO.remove(a2hArr[i]);
				}
			}


			if (act.element.typeRef.code == ENElementType.SERVICES_OBJECT
					|| act.element.typeRef.code == ENElementType.TECHCONDITIONSSERVICES) {
				this.calcTransport4SideWorks(act);

			} else {
				// транспортные расходы
				ENAct2TransportFilter a2tFilter = new ENAct2TransportFilter();
				ENAct2TransportDAO a2tDAO = new ENAct2TransportDAO(connection, userProfile);
				a2tFilter.actRef.code = act.code;
				int[] a2tArr = a2tDAO.getFilteredCodeArray(a2tFilter, null, null, 0, -1, null);


				TKTransportRealFilter trFilter = new TKTransportRealFilter();
				trFilter.conditionSQL = "tktransportreal.code in ( "
						+ " Select ent.transportrealcode  "
						+ " From entransportitem ent left join  finworker fw on ent.finworkercode = fw.code,  enact2enplanwork ena2, enplanworkitem pwi "
						+ " Where ena2.actrefcode = " + act.code
						+ " and ent.planrefcode = ena2.plancode "
						//" and ent.finworkercode = fw.code " +
						// У ЦППшников не считалась амортизация механизмов, потому что они не привязывают на них реальных людей
						//" and (ent.finworkercode = fw.code or (ent.finworkercode is null and ent.tktransporttypecode = " + TKTransportType.MECHANIZM + ")) " +
						+ " and ent.planitemrefcode = pwi.code "
						+ " and pwi.countgen > 0  "
						+ " and coalesce(ent.transportrealcode, -1) <> -1 "
						+ " ) ";

				/// Для того что бы брался только транспорт со статусом в работе. Откидываем сторонний транспорт
				trFilter.transportstatus.code = TKTransportStatus.TKTRANSPORTSTATUS_WORK;
				///
				trFilter.orderBySQL = "tktransportreal.invnumber";

				TKTransportRealDAO trDAO = new TKTransportRealDAO(connection, userProfile);
				TKTransportRealShortList trList = trDAO.getScrollableFilteredList(trFilter, 0, -1);
				ENAct2Transport a2t = null;
				for (int i = 0; i < trList.totalCount; i++) {
					BigDecimal workerWorkTime = new BigDecimal(0);

					ENTransportItemFilter transportFilter = new ENTransportItemFilter();
					transportFilter.transportReal.code = trList.get(i).code;
					transportFilter.conditionSQL = "entransportitem.code in ( "
							+ " Select ent.code  "
							+ " From entransportitem ent left join finworker fw on ent.finworkercode = fw.code,  enact2enplanwork ena2, enplanworkitem pwi "
							+ " Where ena2.actrefcode = " + act.code
							+ " and ent.planrefcode = ena2.plancode "
							//" and ent.finworkercode = fw.code " +
							// У ЦППшников не считалась амортизация механизмов, потому что они не привязывают на них реальных людей
							//" and (ent.finworkercode = fw.code or (ent.finworkercode is null and ent.tktransporttypecode = " + TKTransportType.MECHANIZM + ")) " +
							+ " and ent.planitemrefcode = pwi.code "
							+ " and pwi.countgen > 0  " + " ) ";

					ENTransportItemShortList transportList = transportDAO.getScrollableFilteredList(transportFilter, 0, -1);

					if (i < a2tArr.length) {
						a2t = a2tDAO.getObject(a2tArr[i]);
					} else {
						a2t = new ENAct2Transport();
						a2t.code = Integer.MIN_VALUE;
					}

					a2t.actRef.code = act.code;
					a2t.invNumber = trList.get(i).invNumber;
					a2t.name = trList.get(i).name + " (" + trList.get(i).gosNumber + ")";
					BigDecimal gsm = new BigDecimal(0);
					for (int j = 0; j < transportList.totalCount; j++) {
						// как то ЭТО не правильно !!!!
						// по идее в нормативах для Бригадных авто время
						// СПЕЦИАЛЬНО для износа !!!
						if (transportList.get(j).tktransportTypeCode != TKTransportType.BRIGADE) {
							workerWorkTime = workerWorkTime.add(transportList.get(j).countWorkFact);
						}

						// добавим время проезда ...
						BigDecimal[] distArr = transportLogic.getDistancesByTransport(transportList.get(j).code, false);
						if (distArr != null) {
							BigDecimal delTime = transportLogic.calcTimeByDistaces(
									distArr[0],
									distArr[1],
									distArr[2],
									planLogic.isWinterMonth(transportList.get(j).planRefDateStart),
									transportLogic.isTraktor(transportList.get(j).code)).setScale(2, BigDecimal.ROUND_HALF_UP);

							workerWorkTime = workerWorkTime.add(delTime).setScale(2, BigDecimal.ROUND_HALF_UP);
						}

						// кол-во ГСМ
						FINMaterialsDAO fDAO = new FINMaterialsDAO(connection, userProfile);
						FINMaterialsFilter fFilter = new FINMaterialsFilter();
						fFilter.statusRef.code = FINMaterialsStatus.GOOD;
						fFilter.conditionSQL = "finmaterials.estimateitemrefcode in ("
								+ "select entransport2enestimate.estimaterefcode from entransport2enestimate where entransport2enestimate.transportrefcode = "
								+ transportList.get(j).code + ")";
						FINMaterialsShortList fList = fDAO.getScrollableFilteredList(fFilter, 0, -1);
						for (int j1 = 0; j1 < fList.totalCount; j1++) {
							gsm = gsm.add(fList.get(j1).quantity).setScale(2, BigDecimal.ROUND_HALF_UP);
						}
					}

					a2t.expense = gsm;
					a2t.timeWork = workerWorkTime;

					if (trList.get(i).transportstatusCode == TKTransportStatus.TKTRANSPORTSTATUS_WORK) {
						a2t.depreciationMonth = finLogic.getDepreciation(trList.get(i).invNumber, act.dateGen).setScale(3, BigDecimal.ROUND_HALF_UP);

						/*BigDecimal[]*/ monthTimes = finLogic.getWorkTime(act.dateAct);
						BigDecimal monthTime = monthTimes[0];
						a2t.depreciationHours = a2t.depreciationMonth.divide(monthTime, 3, java.math.BigDecimal.ROUND_HALF_UP);

						BigDecimal vPaysWork = new BigDecimal(0);
						vPaysWork = (a2t.depreciationHours.multiply(workerWorkTime)).setScale(5, java.math.BigDecimal.ROUND_HALF_UP);

						if (vPaysWork.doubleValue() > 0.01) {
							a2t.paysWork = vPaysWork.setScale(2, java.math.BigDecimal.ROUND_HALF_UP);
						} else if (vPaysWork.doubleValue() == 0.0) {
							a2t.paysWork = new BigDecimal(0.0);
						} else {
							a2t.paysWork = new BigDecimal(0.01);
						}
					}

					if (a2t.code == Integer.MIN_VALUE) {
						a2tDAO.add(a2t);
					} else {
						a2tDAO.save(a2t);
					}
				}


				// удалим то что осталось от предыдущих строк акта
				if (trList.totalCount < a2tArr.length) {
					for (int i = trList.totalCount; i < a2tArr.length; i++) {
						a2tDAO.remove(a2tArr[i]);
					}
				}
			}


			if (!isServices) {
				if (act.actTypeRef.code != ENPlanWorkState.WORK_IN_OUT
						&& act.actTypeRef.code != ENPlanWorkState.SERVICES_FROM_OUT) {
					ActLogic actLogic = new ActLogic(connection, userProfile);
		            actLogic.calculateSalaryCharges(actCode);
				}
			}


            /////////////////////////////////
            // перезальем разбивку ЛЮДЕЙ с работами ....
            if (act.element.typeRef.code == ENElementType.SERVICES_OBJECT
                    || act.element.typeRef.code == ENElementType.TECHCONDITIONSSERVICES 
                    || (act.actTypeRef.code == ENPlanWorkState.WORK_IN_OUT && 
                                (act.element.typeRef.code == ENElementType.TY_BYT || act.element.typeRef.code == ENElementType.TY_PROM))
               ) {

            	// SUPP-70376 проставление шпз для актов услуг на сторону (были случаи когда они не были проставлены)
            	ENAct2HumenShortList act2HumenList = a2hDAO.getScrollableFilteredListByAct(act);
            	for(ENAct2HumenShort act2HumenShort : act2HumenList.list) {
            		if(act2HumenShort.balans == null || act2HumenShort.balans.trim().length() == 0
            				|| act2HumenShort.cehId == null || act2HumenShort.cehId.trim().length() == 0
            				|| act2HumenShort.podrcod == null || act2HumenShort.podrcod.trim().length() == 0) {
            			ENAct2Humen act2Humen = a2hDAO.getObject(act2HumenShort.code);

            			FINWorkerFilter finWorkerFilter = new FINWorkerFilter();
            			finWorkerFilter.tabNumber = act2Humen.tabNumber;
            			FINWorkerShortList workerShortList = fkWorkerDAO.getFINWorkerList(finWorkerFilter, 0, -1, act.dateAct, true);
            			if(workerShortList.totalCount > 0) {
            				FINWorkerShort balansAndMainCeh = workerShortList.get(0);
                			act2Humen.balans = balansAndMainCeh.balans;
                			act2Humen.cehId = balansAndMainCeh.mainCehid;
                			act2Humen.podrcod = balansAndMainCeh.departmentCode;
                			a2hDAO.save(act2Humen);
            			}
            		}
            	}

            	recalcPlanWorkItem2Humen(act.code);

                // 13.05.13 NET-4235 Вызываем расчет фактических затрат
                // Перенесено в ServicesLogic
                // recalcServicesFactCalcByAct(act.code);
            }
            ////////////////////////////////////////////



			System.out.println("Final fillActDatas : act №" + act.numberGen + " (" + act.code + ")");

		} catch (Exception e) {
			removeFullActList("" + actCode);
			throw new EnergyproSystemException(e);
		} finally {
			try {
				if ((fkConnection != null) && !fkConnection.isClosed())
					fkConnection.close();
			} catch (SQLException e) {
			}
		}
	}

	public void recalc4Norms(String tabNumber, Date planDate, int positionCode,
			BigDecimal salary, String positionId, int actCode) throws PersistenceException, DatasourceConnectException {

		ActLogic actLogic = new ActLogic(connection, userProfile);
		TransportLogic trLogic = new TransportLogic(connection, userProfile);

		BigDecimal[] monthTimes = getWorkTime(planDate, tabNumber);
		BigDecimal monthTime = monthTimes[0];
		BigDecimal monthDay = monthTimes[1];

		BigDecimal calendarTime = monthTime.divide(monthDay, 2, BigDecimal.ROUND_HALF_UP);

		ENPlan2HumenDAO p2hDAO = new ENPlan2HumenDAO(connection, userProfile);
		ENPlan2HumenShortList p2hList = p2hDAO.getListByDateAndTabNumberAndPositionAndSalary(tabNumber, planDate, positionCode, salary, positionId, actCode);
		BigDecimal allTimeInPlan = new BigDecimal(0);

		PlansData15 vPlan, hPlan;
		PlansData215 vPlan2, hPlan2;

		Hashtable<PlansData15, PlansData15> _plansData = new Hashtable<PlansData15, PlansData15>();
		Hashtable<PlansData215, PlansData215> _plansData2 = new Hashtable<PlansData215, PlansData215>();

		for (int i = 0; i < p2hList.totalCount; i++) {
			ENPlan2HumenShort p2h = p2hList.get(i);

            // время для водил вычислять только для ЧЕРНОВЫХ актов ... для остальных оно типа уже есть ...
			if ((p2h.humenKindRefCode == ENHumenItemKind.DRIVER)
					&& (p2h.actRefStatusCode == ENActStatus.GOOD)) {
				p2h.timeWorkGen = actLogic.getTimeGen4DriverByPositionAndSalary(
						p2h.tabNumber,
						p2h.planRefCode,
						p2h.planRefDateStart,
						p2h.positionCode,
						p2h.priceGen,
						p2h.positionId);
				p2hList.list.set(i, p2h);
			}

			if ((p2h.humenKindRefCode == ENHumenItemKind.ELTEH)
					&& (p2h.actRefStatusCode == ENActStatus.GOOD)) {

				// доставка ТОЛЬКО если этого ТН нет в водителях для этого плана
				if (!p2hDAO.isPersonHavingMoreThanOneJob(p2h.tabNumber, p2h.planRefCode)) {
					BigDecimal delTime = trLogic.calculateTimeForPlanByDistance(p2h.planRefCode, 0, false);
					p2h.timeWorkGen = p2h.timeWorkGen.add(delTime);
                    ///// 08.05.13 NET-4235 Будем отдельно хранить время доставки
                    p2h.timeDelivery = delTime;
                    /////
                    p2hList.list.set(i, p2h);
				}
			}

			vPlan = new PlansData15(p2h.tabNumber, p2h.planRefDateStart, p2h.humenKindRefCode, p2h.positionCode, p2h.priceGen, p2h.positionId);
			hPlan = (PlansData15) _plansData.get(vPlan);
			if (hPlan == null) {
				hPlan = new PlansData15(
						p2h.tabNumber,
						p2h.planRefDateStart,
						p2h.humenKindRefCode,
						p2h.positionCode,
						p2h.priceGen,
						p2h.positionId);
				hPlan.sumGen = new BigDecimal(0);
				hPlan.calendarTime = calendarTime;
			}

			if (p2h.actRefStatusCode == ENActStatus.GOOD) {
				hPlan.sumGen = hPlan.sumGen.add(p2h.timeWorkGen).setScale(2, BigDecimal.ROUND_HALF_UP);
			} else {
				hPlan.calendarTime = hPlan.calendarTime.subtract(p2h.timeWorkGen).setScale(2, BigDecimal.ROUND_HALF_UP);
			}

			_plansData.put(vPlan, hPlan);


			vPlan2 = new PlansData215(p2h.tabNumber, p2h.planRefDateStart, p2h.positionCode, p2h.priceGen, p2h.positionId);
			hPlan2 = (PlansData215) _plansData2.get(vPlan2);
			if (hPlan2 == null) {
				hPlan2 = new PlansData215(
						p2h.tabNumber,
						p2h.planRefDateStart,
						p2h.positionCode,
						p2h.priceGen,
						p2h.positionId);
				hPlan2.sumGen = new BigDecimal(0);
				hPlan2.calendarTime = calendarTime;
			}

			if (p2h.actRefStatusCode == ENActStatus.GOOD) {
				hPlan2.sumGen = hPlan2.sumGen.add(p2h.timeWorkGen).setScale(2, BigDecimal.ROUND_HALF_UP);
			} else {
				hPlan2.calendarTime = hPlan2.calendarTime.subtract(p2h.timeWorkGen).setScale(2, BigDecimal.ROUND_HALF_UP);
			}

			_plansData2.put(vPlan2, hPlan2);
		}

		BigDecimal allTime = new BigDecimal(0);
		ENPlan2HumenShort nextP2h = new ENPlan2HumenShort();
		nextP2h.tabNumber = "";
		nextP2h.planRefDateStart = new Date(0);
        ///// 12.12.11
        nextP2h.positionCode = Integer.MIN_VALUE;
        nextP2h.priceGen = new BigDecimal(Integer.MIN_VALUE);
        /////
        nextP2h.positionId = "";

		for (int i = 0; i < p2hList.totalCount; i++) {

			ENPlan2HumenShort p2h = p2hList.get(i);
			if (p2h.actRefStatusCode == ENActStatus.GOOD) {

				vPlan = new PlansData15(p2h.tabNumber, p2h.planRefDateStart, p2h.humenKindRefCode, p2h.positionCode, p2h.priceGen, p2h.positionId);
				hPlan = (PlansData15) _plansData.get(vPlan);
				if (hPlan == null) {
					removeFullActList("" + Integer.MIN_VALUE);
					throw new EnergyproSystemException("\n\n "
							+ "Даних для розрахунку не знайдено ...");
				}

				vPlan2 = new PlansData215(p2h.tabNumber, p2h.planRefDateStart, p2h.positionCode, p2h.priceGen, p2h.positionId);
				hPlan2 = (PlansData215) _plansData2.get(vPlan2);
				if (hPlan2 == null) {
					removeFullActList("" + Integer.MIN_VALUE);
					throw new EnergyproSystemException("\n\n "
							+ "Даних для розрахунку не знайдено (2) ...");
				}

				allTimeInPlan = hPlan2.sumGen;
				if (hPlan2.calendarTime.doubleValue() <= 0) {
					p2h.timeWorkFact = new BigDecimal(0);
				} else {
					if (allTimeInPlan.doubleValue() > hPlan2.calendarTime.doubleValue()) {

						BigDecimal coeff = p2h.timeWorkGen.divide(allTimeInPlan, 6, BigDecimal.ROUND_HALF_UP);
						p2h.timeWorkFact = hPlan2.calendarTime.multiply(coeff).setScale(2, BigDecimal.ROUND_HALF_UP);
					} else {
						p2h.timeWorkFact = (p2h.timeWorkGen).setScale(2, BigDecimal.ROUND_HALF_UP);
					}

					if (p2h.timeWorkFact.doubleValue() < 0) {
						p2h.timeWorkFact = new BigDecimal(0);
					}
				}

				if ((i + 1) < p2hList.totalCount) {
					nextP2h = p2hList.get(i + 1);
				} else {
					nextP2h = new ENPlan2HumenShort();
					nextP2h.planRefDateStart = new Date(0);
					nextP2h.tabNumber = "";
                    ///// 12.12.11
                    //nextP2h.humenKindRefCode = Integer.MIN_VALUE;
                    nextP2h.positionCode = Integer.MIN_VALUE;
                    nextP2h.priceGen = new BigDecimal(Integer.MIN_VALUE);
                    /////
                    nextP2h.positionId = "";
				}

				if ((nextP2h.planRefDateStart.getTime() != p2h.planRefDateStart.getTime())
						|| (!(nextP2h.tabNumber.equals(p2h.tabNumber)))
						|| (nextP2h.humenKindRefCode != p2h.humenKindRefCode)
						|| (nextP2h.positionCode != p2h.positionCode)
						|| (!(nextP2h.priceGen.equals(p2h.priceGen)))
						||  (  (p2h.positionId != null && !(nextP2h.positionId.equals(p2h.positionId)))  ) ) {
					if (allTime.doubleValue() == 0) {

					} else {
						if (p2h.timeWorkGen.doubleValue() != 0) {
							if (allTime.add(p2h.timeWorkFact).doubleValue() > hPlan2.calendarTime.doubleValue()) {
								p2h.timeWorkFact = hPlan2.calendarTime.subtract(allTime);
								allTime = new BigDecimal(0);
							}
						}
					}
				} else {
					allTime = allTime.add(p2h.timeWorkFact).setScale(2, BigDecimal.ROUND_HALF_UP);
				}

				if (p2h.timeWorkFact.doubleValue() > hPlan2.calendarTime.doubleValue()) {
					removeFullActList("" + Integer.MIN_VALUE);
					throw new EnergyproSystemException("\n\n "
							+ "У таб. № " + p2h.tabNumber + " вже використаний весь фонд робочого часу на "
							+ new SimpleDateFormat("dd.MM.yyyy").format(p2h.planRefDateStart)
							+ " у попередніх актах \n" + " (код плану: " + p2h.planRefCode + ")");
				}

				ENPlan2HumenFilter ff = new ENPlan2HumenFilter();
				ff.planRef.code = p2h.planRefCode;
				ff.tabNumber = p2h.tabNumber;
				ff.humenKindRef.code = p2h.humenKindRefCode;
                ///// 12.12.11

				if (p2h.positionId != null && !p2h.positionId.equals("")) {
					ff.positionId = p2h.positionId;
				} else {
					ff.positionCode = p2h.positionCode;
				}

                ff.priceGen = p2h.priceGen;
                /////


                int[] arr_ = p2hDAO.getFilteredCodeArray(ff, 0, -1);
				if (arr_.length == 0) {
					ENPlan2Humen p2hObj = new ENPlan2Humen();
					p2hObj.humenKindRef.code = p2h.humenKindRefCode;
					p2hObj.planRef.code = p2h.planRefCode;
					p2hObj.tabNumber = p2h.tabNumber;
					p2hObj.fio = p2h.fio;
					p2hObj.timeWorkGen = p2h.timeWorkGen;
					p2hObj.timeWorkFact = p2h.timeWorkFact;
                    ///// 12.12.11
                    p2hObj.positionCode = p2h.positionCode;
                    p2hObj.priceGen = p2h.priceGen;
                    /////

                    ///// 13.05.13 NET-4235
                    p2hObj.timeDelivery = p2h.timeDelivery;
                    /////
                    p2hObj.positionId = p2h.positionId;

					p2hDAO.add(p2hObj);

				} else {
					ENPlan2Humen p2hObj = p2hDAO.getObject(arr_[0]);
					p2hObj.humenKindRef.code = p2h.humenKindRefCode;
					p2hObj.planRef.code = p2h.planRefCode;
					p2hObj.tabNumber = p2h.tabNumber;
					p2hObj.fio = p2h.fio;
					p2hObj.timeWorkGen = p2h.timeWorkGen;
					p2hObj.timeWorkFact = p2h.timeWorkFact;
                    ///// 12.12.11
                    p2hObj.positionCode = p2h.positionCode;
                    p2hObj.priceGen = p2h.priceGen;
                    /////

                    ///// 13.05.13 NET-4235
                    p2hObj.timeDelivery = p2h.timeDelivery;
                    /////
                    p2hObj.positionId = p2h.positionId;

					p2hDAO.save(p2hObj);
				}
			}
		}
	}


	public void fillActs(int actCode, String actCodes, int departmentCode, Hashtable<HumenData15, HumenData15> humenData, List<String> tabNumbers)
			throws PersistenceException {

		ActsData hKey = new ActsData("" + actCode);
		ActsData vKey;

		_actsTable.put(hKey, hKey);

        ///// 07.09.12 Вначале проапдейтим з/п!!!
        ENActDAO actDAO = new ENActDAO(connection, userProfile);
        ENAct act = actDAO.getObjectNOSEGR(actCode);
        System.out.println("Start updateSalarys : act №" + act.numberGen + " ("+ act.code+")");
		if (act.statusRef.code == ENActStatus.GOOD) {
			updateSalarys(act, humenData);
		}
		 System.out.println("Final updateSalarys : act №" + act.numberGen + " ("+ act.code+")");
        /////

		ENPlan2HumenDAO p2hDAO = new ENPlan2HumenDAO(connection, userProfile);
		ENPlan2HumenShortList p2hList = p2hDAO.getTabNumberList(actCode, departmentCode);

		/*
		// При первом вызове сохраняем список табельных из основного акта, который мы переводим на подписание
		if (actCodes.equals("" + Integer.MIN_VALUE)) {
			tabNumbers = p2hList.list.stream().map(s -> s.tabNumber).collect(Collectors.toList());
			System.out.println("!!!!!!!!!!! ACT: " + actCode + " | TABNUMBERS: " + tabNumbers);
		}
		*/

        String actCodes_ = actCodes + ", " + actCode;

		for (int i = 0; i < p2hList.totalCount; i++) {
			if (! tabNumbers.isEmpty()) {
				// Работаем только с теми табельными, которые есть в основном акте, остальные пропускаем!!!
				if (! tabNumbers.contains(p2hList.get(i).tabNumber)) {
					continue;
				}
			}

			PlansData15 hPlan;
			PlansData15 vPlan = new PlansData15(
					p2hList.get(i).tabNumber,
					p2hList.get(i).planRefDateStart,
					p2hList.get(i).humenKindRefCode,
					p2hList.get(i).positionCode,
					p2hList.get(i).priceGen,
					p2hList.get(i).positionId);

			hPlan = (PlansData15) _plansData2.get(vPlan);
			if (hPlan == null) {
				_plansData2.put(vPlan, vPlan);
			}

			// 19.10.2021 Начиная с 01.10.2021 (по дате плана), НЕ пересчитываем связанные акты
			// (т.к. мы начали обрезать людям время еще на этапе планирования)
			if (p2hList.get(i).planRefDateStart.compareTo(ENConsts.ENACT_SIMPLE_CALC_START_DATE) < 0) {
				ENPlan2HumenShortList actList = p2hDAO.getActListByTabNumberAndDate2(
						p2hList.get(i).tabNumber,
						p2hList.get(i).planRefDateStart,
						actCodes_,
						departmentCode);

				for (int j = 0; j < actList.totalCount; j++) {
					// для чернового - вынесем предыдущий расчет План2Хумен ...
					if (actList.get(j).actRefStatusCode == ENActStatus.GOOD) {
						removePlan2HumenByActCode__(actList.get(j).actRefCode);
					}

					hKey = new ActsData("" + actList.get(j).actRefCode);
					vKey = (ActsData) _actsTable.get(hKey);

					if (vKey == null) {
						ActsData hAct = (ActsData) fullActList.get(hKey);
						if (hAct != null) {
							removeFullActList("" + Integer.MIN_VALUE);
							throw new EnergyproSystemException("\n\n "
									+ "Зв_язаний акт № "+ actList.get(j).actRefNumberGen
									+ " від " + new SimpleDateFormat("dd.MM.yyyy").format(actList.get(j).actRefDateGen)
									+ " зараз розраховує користувач " + hAct.userName);
						}

						synchronized (fullActList) {
							fullActList.put(hKey, hKey);
						}

						_actsTable.put(hKey, hKey);
						fillActs(actList.get(j).actRefCode, actCodes_, departmentCode, humenData, tabNumbers);
					}
				}
			}
		}
	}


	/**
	 *
	 * Обновление зарплатных данных персонала в таблице finworker по акту
	 *
	 * @param act акт
	 */
	public void updateSalarys(ENAct act, Hashtable<HumenData15, HumenData15> humenData) {
		Connection fkConnection = null;

		try {
			fkConnection = getConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
            FINWorkerDAO finWorkerFKDAO = new FINWorkerDAO(fkConnection, userProfile);
            FINWorkerDAO fwDAO = new FINWorkerDAO(connection, userProfile);
            ENTransportItemDAO transportDAO = new ENTransportItemDAO(connection, userProfile);


            HumenLogic hLogic = new HumenLogic(connection, userProfile);
            BigDecimal chargePercent = new BigDecimal(0);
            int chargePercentType = Integer.MIN_VALUE;

			HumenData15 vKey;

			String humenItemCondition = " enhumenitem.code in ( "
					+ " select enhumenitem.code  "
					+ " from enhumenitem, enplanwork, enplanworkitem, enact2enplanwork "
					+ "  where enact2enplanwork.actrefcode = " + act.code
					+ "  and enact2enplanwork.plancode = enplanwork.code "
					+ "  and enplanwork.code = enplanworkitem.planrefcode "
					+ "  and enhumenitem.planitemrefcode = enplanworkitem.code "
					+ "  and enhumenitem.countfact <> 0 "
					+ "  and enplanworkitem.countgen <> 0 " + "  ) "
					// непривязанные нам НЕ НАДО ??? ;)
					+ " and enhumenitem.finworkercode is not null ";

            ENHumenItemDAO humenDAO = new ENHumenItemDAO(connection, userProfile);

			// типа обновим ЗП из ФК ...
			ENHumenItemFilter humenFilter = new ENHumenItemFilter();
			humenFilter.conditionSQL = humenItemCondition;

			ENHumenItemShortList humenList = humenDAO.getHumen2FinWorkerList(humenFilter);
			for (int h = 0; h < humenList.totalCount; h++) {
				if (humenList.get(h).finWorkerTabNumber != null) {

					FINWorkerFilter ff = new FINWorkerFilter();
					ff.tabNumber = humenList.get(h).finWorkerTabNumber;

					FINWorkerShortList fList = finWorkerFKDAO.getFINWorkerList(ff, 0, -1, humenList.get(h).planRefDateStart, true);
					if (fList.totalCount > 0) {
						FINWorkerFilter fwFilter = new FINWorkerFilter();
						fwFilter.tabNumber = humenList.get(h).finWorkerTabNumber;
						fwFilter.conditionSQL = " finworker.code in "
								+ " ( "
								+ "   select distinct fw.code "
								+ "     from enplanwork p, enhumenitem hi, finworker fw "
								+ "    where p.datestart = '" + new SimpleDateFormat("dd.MM.yyyy").format(humenList.get(h).planRefDateStart) + "' "
								+ "      and p.kindcode = 4 "
								+ "      and p.code = hi.planrefcode "
								+ "      and hi.finworkercode = fw.code "
								+ "      and fw.tabnumber = '" + humenList.get(h).finWorkerTabNumber + "') ";

						int[] fwArr = fwDAO.getFilteredCodeArray(fwFilter, 0, -1);
						for (int f = 0; f < fwArr.length; f++) {
							FINWorker w = fwDAO.getObject(fwArr[f]);

							// if (fLogicFin.getCheckIsInvalid(w.tabNumber, humenList.get(h).planRefDateStart) > 0 ) {
							/*ymp 04.12.2018 сделал что бы признак инвалидности возвращался с листа сотрудников, а не дергался отдельным запросом */
							if (fList.get(0).chargeRefCode > 0 ) {
				                // если инвалид
				    	        chargePercent =  hLogic.getChargePercentByDate(FINChargeType.IS_INVALID, humenList.get(h).planRefDateStart);
				    	        chargePercentType = FINChargeType.IS_INVALID;
				    		} else {
				    			// если НЕ инвалид
				    			chargePercent =  hLogic.getChargePercentByDate(FINChargeType.IS_NOT_INVALID, humenList.get(h).planRefDateStart);
				    			chargePercentType = FINChargeType.IS_NOT_INVALID;
				    		}


							if ((w.priceGen.doubleValue() != fList.get(0).priceGen.doubleValue())
									|| (!w.positionName.equals(fList.get(0).positionName))
						            ||  chargePercent.doubleValue()!= w.chargePercent.doubleValue() 	                                 
									) {
								w.categor = fList.get(0).categor;
								w.departmentCode = fList.get(0).departmentCode;
								w.departmentName = fList.get(0).departmentName;
								w.finCode = fList.get(0).finCode;
								w.kindRef.code = (fList.get(0).categor == Integer.MIN_VALUE) ? FINWorkerKind.OTHER : fList.get(0).categor;
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
								w.positionId = fList.get(0).positionId;
								w.chargePercent = chargePercent;
								w.chargeRef.code = chargePercentType;

								int fwCode = fwDAO.add(w);


								ENHumenItemFilter hFilter = new ENHumenItemFilter();
								hFilter.conditionSQL = " enhumenitem.code in (" + humenList.get(h).humenItemCodesStr + ")";

								int humenArr[] = humenDAO.getFilteredCodeArray(hFilter, 0, -1);
								for (int g = 0; g < humenArr.length; g++) {
									ENHumenItem humen = humenDAO.getObject(humenArr[g]);
									humen.finWorker.code = fwCode;
									humenDAO.save(humen);
								}
							}

							vKey = new HumenData15(w.tabNumber, w.name, w.positionName, w.positionCode, w.priceGen, w.positionId);
							humenData.put(vKey, vKey);
						}
					}
				}
			}

			// ЗП водителей !!! хитро из ТРАНСПОРТА ;) .. а не Хуменов ;)
			String transportItemCondition = " entransportitem.code in ( "
					+ " Select ent.code "
					+ " From entransportitem ent ,  finworker fw,  enact2enplanwork ena2, enplanworkitem pwi "
					+ " Where ena2.actrefcode = " + act.code
					+ " and ent.planrefcode = ena2.plancode "
					+ " and ent.finworkercode = fw.code "
					+ " and ent.planitemrefcode = pwi.code "
					+ " and pwi.countgen > 0 " + "  ) ";


            /* 08.06.2012 +++ страшные тормоза после перехода на PostgreSQL9 */
			ENTransportItemFilter tr2Filter = new ENTransportItemFilter();
			tr2Filter.conditionSQL = transportItemCondition;
			int tr2Arr[] = transportDAO.getFilteredCodeArray(tr2Filter, 0, -1);
			if (tr2Arr.length > 0) {
				for (int j = 0; j < tr2Arr.length; j++) {

                    // типа обновим ЗП из ФК ...
					ENTransportItemFilter trFilter1 = new ENTransportItemFilter();
					trFilter1.conditionSQL = transportItemCondition;
					trFilter1.code = tr2Arr[j];

					ENTransportItemShortList trList1 = transportDAO.getScrollableFilteredList(trFilter1, 0, -1);
					for (int qq = 0; qq < trList1.totalCount; qq++) {

						if (trList1.get(qq).finWorkerTabNumber != null) {
							FINWorkerFilter ff = new FINWorkerFilter();
							ff.tabNumber = trList1.get(qq).finWorkerTabNumber;
 							FINWorkerShortList fList = finWorkerFKDAO.getFINWorkerList(ff, 0, -1, trList1.get(qq).planRefDateStart, true);
							if (fList.totalCount > 0) {

                                FINWorkerFilter fwFilter = new FINWorkerFilter();
                                fwFilter.tabNumber = trList1.get(qq).finWorkerTabNumber;
                                fwFilter.conditionSQL =
                                    " finworker.code in " +
                                    " ( " +
                                    "   select distinct fw.code " +
                                    "     from enplanwork p, entransportitem ent, finworker fw " +
                                    "    where p.datestart = '" + new SimpleDateFormat("dd.MM.yyyy").format(trList1.get(qq).planRefDateStart) + "' " +
                                    "      and p.kindcode = 4 " +
                                    "      and p.code = ent.planrefcode " +
                                    "      and ent.finworkercode = fw.code " +
                                    "      and fw.tabnumber = '" + trList1.get(qq).finWorkerTabNumber + "'" +
                                    " ) ";

                                int[] fwArr = fwDAO.getFilteredCodeArray(fwFilter, 0, -1);
                                for (int f = 0; f < fwArr.length; f++)
                                {
                                    FINWorker w = fwDAO.getObject(fwArr[f]);


									//if (fLogicFin.getCheckIsInvalid(w.tabNumber, trList1.get(qq).planRefDateStart) > 0) 
									 /*ymp 04.12.2018 сделал что бы признак инвалидности возвращался с листа сотрудников, а не дергался отдельным запросом */
									if (fList.get(0).chargeRefCode > 0 ) {
										// если инвалид
										chargePercent = hLogic.getChargePercentByDate(FINChargeType.IS_INVALID, trList1.get(qq).planRefDateStart);
										chargePercentType = FINChargeType.IS_INVALID;
									} else { // если НЕ инвалид
										chargePercent = hLogic.getChargePercentByDate(FINChargeType.IS_NOT_INVALID, trList1.get(qq).planRefDateStart);
										chargePercentType = FINChargeType.IS_NOT_INVALID;
									}


                                    if ((w.priceGen.doubleValue() != fList.get(0).priceGen.doubleValue())
                                    		|| ( ! w.positionName.equals(fList.get(0).positionName))
                                    		|| chargePercent.doubleValue() != w.chargePercent.doubleValue() )
                                    {
                                        w.categor = fList.get(0).categor;
                                        w.departmentCode = fList.get(0).departmentCode;
                                        w.departmentName = fList.get(0).departmentName;
                                        w.finCode = fList.get(0).finCode;
                                        w.kindRef.code = (fList.get(0).categor == Integer.MIN_VALUE) ? FINWorkerKind.OTHER : fList.get(0).categor;
                                        w.name = fList.get(0).name;
                                        w.positionCode = fList.get(0).positionCode;
                                        w.positionName = fList.get(0).positionName;
                                        w.priceGen = fList.get(0).priceGen;
                                        w.tabNumber = fList.get(0).tabNumber;
                                        w.positionId = fList.get(0).positionId;
                                        w.workTimeId = fList.get(0).workTimeId;
                                        w.chargePercent = chargePercent;
                                        w.chargeRef.code = chargePercentType;

                                        ////// 31.10.12
                                        //fwDAO.save(w);
                                        ENTransportItem transport = transportDAO.getObject(trList1.get(qq).code);
                                        int fwCode = fwDAO.add(w);
                                        transport.finWorker.code = fwCode;
                                        transportDAO.save(transport);
                                        //////
                                    }

									vKey = new HumenData15(w.tabNumber, w.name, w.positionName, w.positionCode, w.priceGen, w.positionId);
									humenData.put(vKey, vKey);
								}
							}
						}
					}
				}
			}

		} catch (Exception e) {
			removeFullActList("" + act.code);
			throw new EnergyproSystemException(e);
		} finally {

			try {
				if ((fkConnection != null) && !fkConnection.isClosed())
					fkConnection.close();
			} catch (SQLException e) {
			}
		}
	}



	class HumenData15 {
		String tabNumber;
		String fio;
		String positionName;
		String positionCode;
		String salary;
		String positionId;

		public HumenData15(String vTabNumber, String vFio,
				String vPositionName, int vPositionCode, BigDecimal vSalary,
				String vPositionId) {
			tabNumber = vTabNumber;
			fio = vFio;
			positionName = vPositionName;
			positionCode = "" + vPositionCode;
			salary = vSalary == null ? "0" : vSalary.toString();
			positionId = vPositionId == null ? "" : vPositionId;
		}

		@Override
		public int hashCode() {
			return (tabNumber + positionCode + salary + positionId).hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof HumenData15) {
				HumenData15 other = (HumenData15) obj;

				return this.tabNumber.equals(other.tabNumber)
						&& this.positionCode.equals(other.positionCode)
						&& this.salary.equals(other.salary)
						&& this.positionId.equals(other.positionId);
			} else {
				return false;
			}
		}
	}

	class PlansData15 {
		String tabNumber;
		String planDate;
		String humenKind;
		BigDecimal sumGen;
		BigDecimal calendarTime;
		///// 12.12.11
		String positionCode;
		String salary;
		/////
		String positionId;

		public PlansData15(String vTabNumber, Date vPlanDate, int vHumenKind,
				int vPositionCode, BigDecimal vSalary, String vPositionId) {
			tabNumber = vTabNumber;
			planDate = new SimpleDateFormat("dd.MM.yyyy").format(vPlanDate);
			humenKind = "" + vHumenKind;
			sumGen = new BigDecimal(0);
			calendarTime = new BigDecimal(0);

			positionCode = "" + vPositionCode;
			salary = vSalary == null ? "0" : vSalary.toString();
			positionId = vPositionId == null ? "" : vPositionId;
		}

		@Override
		public int hashCode() {
			return (tabNumber + planDate + humenKind + positionCode + salary + positionId).hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof PlansData15) {
				PlansData15 other = (PlansData15) obj;
				return this.tabNumber.equals(other.tabNumber)
						&& this.planDate.equals(other.planDate)
						&& this.humenKind.equals(other.humenKind)
						&& this.positionCode.equals(other.positionCode)
						&& this.salary.equals(other.salary)
						&& this.positionId.equals(other.positionId);
			} else {
				return false;
			}
		}
	}

	class PlansData215 {
		String tabNumber;
		String planDate;
		BigDecimal sumGen;
		BigDecimal calendarTime;
		///// 12.12.11
		String positionCode;
		String salary;
		/////
		String positionId;

		public PlansData215(String vTabNumber, Date vPlanDate,
				int vPositionCode, BigDecimal vSalary, String vPositionId) {
			tabNumber = vTabNumber;
			planDate = new SimpleDateFormat("dd.MM.yyyy").format(vPlanDate);
			sumGen = new BigDecimal(0);
			calendarTime = new BigDecimal(0);

			positionCode = "" + vPositionCode;
			salary = vSalary == null ? "0" : vSalary.toString();
			positionId = vPositionId == null ? "" : vPositionId;
		}

		@Override
		public int hashCode() {
			return (tabNumber + planDate + positionCode + salary + positionId).hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			if (obj instanceof PlansData215) {
				PlansData215 other = (PlansData215) obj;
				return this.tabNumber.equals(other.tabNumber)
						&& this.planDate.equals(other.planDate)
						&& this.positionCode.equals(other.positionCode)
						&& this.salary.equals(other.salary)
						&& this.positionId.equals(other.positionId);
			} else {
				return false;
			}
		}
	}


	public BigDecimal[] getWorkTime(Date dateAct, String tabNumber) {

	    Connection fkConnection;
	    BigDecimal[] monthTimes = { new BigDecimal(0), new BigDecimal(0) };

		try {
			fkConnection = getNEWConnection(AuthorizationJNDINames.FINMATERIAL_DATASOURCE);
			FINLogic finLogic = new FINLogic(fkConnection, userProfile);

		    monthTimes = finLogic.getWorkTime(dateAct, tabNumber);
		    BigDecimal monthTime = monthTimes[0];
		    BigDecimal monthDay = monthTimes[1];

			if (monthTime == null || monthDay == null) {
				removeFullActList("" + Integer.MIN_VALUE);

				throw new SystemException("\n\n"
						+ "Для табельного №" + tabNumber + " не знайдено графік робочого часу на " +  new SimpleDateFormat("dd.MM.yyyy").format(dateAct) + "!!!");
			}

			if (monthTime.doubleValue() == 0 || monthDay.doubleValue() == 0) {
				removeFullActList("" + Integer.MIN_VALUE);
				throw new SystemException("\n\n"
						+ "Для табельного №" + tabNumber + " не знайдено графік робочого часу на " +  new SimpleDateFormat("dd.MM.yyyy").format(dateAct) + "!!!");
			}

		} catch (DatasourceConnectException e) {
			removeFullActList("" + Integer.MIN_VALUE);
			throw new SystemException(e.getMessage(), e);
		} catch (PersistenceException e) {
			removeFullActList("" + Integer.MIN_VALUE);
			throw new SystemException(e.getMessage(), e);
		}

		return monthTimes;
	}
	
	public BigDecimal getActSumByActCode(int actCode) throws PersistenceException {
		ENActDAO actDao = new ENActDAO(connection, userProfile);
		return this.getActSumByAct(actDao.getObject(actCode));
	}
	
	public BigDecimal getActSumByAct(ENAct act) throws PersistenceException {
		if(act == null || act.code == Integer.MIN_VALUE) {
			throw new java.lang.NullPointerException("Не заданий акт");
		}
		
		if(act.actTypeRef == null || act.actTypeRef.code == Integer.MIN_VALUE) {
			throw new java.lang.NullPointerException("Не заданий тип акту");
		}
		
		String funcName = null;
		
		// По типу акта определяется какая функция БД должна быть вызвана
		switch(act.actTypeRef.code) {
		case ENPlanWorkState.WORK_IN_OUT:
			ServicesLogic servicesLogic = new ServicesLogic(connection, userProfile);
			/*SUPP-101712 07.07.2021 Изменил вызов getServicesObjectByElementCode(act.element.code) на getServicesObjectByElementCode(act.element.code, false)
			 * чтобы не было ошибке о количестве элементов */
			ENServicesObject servicesObject = servicesLogic.getServicesObjectByElementCode(act.element.code, false);
			if(servicesObject != null) {
				if(servicesObject.calcTypeRef.code == ENServicesObjectCalcType.BY_FACT) {
					funcName = "get_sum_by_act_services2";
				} else {
					funcName = "get_sum_by_act_services";
				}
				break;
			}
			
			default:
				funcName = "get_sum_by_act";
				break;
		}
		
		String sql = String.format("select coalesce(net.%s(?), 0)::decimal", funcName);
		
		Vector<Integer> binded = new Vector<Integer>();
		binded.add(act.code);
		
		BigDecimal out = BaseDAOUtils.executeStatementAndReadObject(connection, sql, binded
				, new BaseDAOUtils.BigDecimalFromResultSetTransformator(), false);
		
		return out;
	}

	private boolean checkIfActIsAutoByOZ(ENAct act) {
		if (act == null || act.code <= 0) {
			throw new IllegalArgumentException("\n\nНе заданий об'єкт акту!");
		}

		if (act.actTypeRef.code == ENPlanWorkState.WORK_IN_OUT) {
			return false;
		}

		try {
			SCUsageInputItemOZ2ENActDAO oz2actDao = new SCUsageInputItemOZ2ENActDAO(connection, userProfile);
			SCUsageInputItemOZ2ENActFilter oz2actFilter = new SCUsageInputItemOZ2ENActFilter();
			oz2actFilter.enActRef.code = act.code;
			int[] oz2actArr = oz2actDao.getFilteredCodeArray(oz2actFilter, 0, -1);

			return (oz2actArr.length > 0);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		}
	}

}

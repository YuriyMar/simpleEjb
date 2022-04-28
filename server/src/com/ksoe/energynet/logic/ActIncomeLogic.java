package com.ksoe.energynet.logic;

import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Vector;

import javax.ejb.CreateException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;

import com.ksoe.authorization.util.AuthorizationJNDINames;
import com.ksoe.docflow.logic.DocFlowLogic;
import com.ksoe.docflow.logic.signing.DocSigningLogic;
import com.ksoe.docflow.valueobject.DFDoc;
import com.ksoe.energyWorkflow.logic.WorkFlowLogic;
import com.ksoe.energyWorkflow.valueobject.WFPack;
import com.ksoe.energynet.dataminer.ENAct2ENPlanWorkDAO;
import com.ksoe.energynet.dataminer.ENActDAO;
import com.ksoe.energynet.dataminer.ENActInTechCond2ENActDAO;
import com.ksoe.energynet.dataminer.ENActIncomServ2ENActDAO;
import com.ksoe.energynet.dataminer.ENActIncome2ENActDAO;
import com.ksoe.energynet.dataminer.ENActIncomeDAO;
import com.ksoe.energynet.dataminer.ENActIncomeServices2SOBillDAO;
import com.ksoe.energynet.dataminer.ENActIncomeServicesDAO;
import com.ksoe.energynet.dataminer.ENActIncomeTech2DFDocDAO;
import com.ksoe.energynet.dataminer.ENActIncomeTechConditionsDAO;
import com.ksoe.energynet.dataminer.ENElement2ActDAO;
import com.ksoe.energynet.dataminer.ENElementDAO;
import com.ksoe.energynet.dataminer.ENPlanWorkDAO;
import com.ksoe.energynet.dataminer.ENSOBillDAO;
import com.ksoe.energynet.dataminer.ENServices2CalcAdditionalItemsDAO;
import com.ksoe.energynet.dataminer.ENServices2PlanDAO;
import com.ksoe.energynet.dataminer.ENServicesCostDAO;
import com.ksoe.energynet.dataminer.ENServicesObject2TechCondtnsServicesDAO;
import com.ksoe.energynet.dataminer.ENServicesObjectDAO;
import com.ksoe.energynet.dataminer.ENStandardConstDAO;
import com.ksoe.energynet.ejb.ENActController;
import com.ksoe.energynet.ejb.ENActControllerHome;
import com.ksoe.energynet.valueobject.ENAct;
import com.ksoe.energynet.valueobject.ENActInTechCond2ENAct;
import com.ksoe.energynet.valueobject.ENActIncomServ2ENAct;
import com.ksoe.energynet.valueobject.ENActIncome;
import com.ksoe.energynet.valueobject.ENActIncome2ENAct;
import com.ksoe.energynet.valueobject.ENActIncomeServices;
import com.ksoe.energynet.valueobject.ENActIncomeServices2SOBill;
import com.ksoe.energynet.valueobject.ENActIncomeStatus;
import com.ksoe.energynet.valueobject.ENActIncomeTech2DFDoc;
import com.ksoe.energynet.valueobject.ENActIncomeTechConditions;
import com.ksoe.energynet.valueobject.ENActStatus;
import com.ksoe.energynet.valueobject.ENElement;
import com.ksoe.energynet.valueobject.ENElementType;
import com.ksoe.energynet.valueobject.ENSOBill;
import com.ksoe.energynet.valueobject.ENServicesContractType;
import com.ksoe.energynet.valueobject.ENServicesCost;
import com.ksoe.energynet.valueobject.ENServicesObject;
import com.ksoe.energynet.valueobject.ENServicesObject2TechCondtnsServices;
import com.ksoe.energynet.valueobject.ENStandardConst;
import com.ksoe.energynet.valueobject.ENTechConditionsObjects;
import com.ksoe.energynet.valueobject.brief.ENAct2ENPlanWorkShort;
import com.ksoe.energynet.valueobject.brief.ENActIncomeServices2SOBillShort;
import com.ksoe.energynet.valueobject.brief.ENActShort;
import com.ksoe.energynet.valueobject.brief.ENElement2ActShort;
import com.ksoe.energynet.valueobject.brief.ENServicesObject2TechCondtnsServicesShort;
import com.ksoe.energynet.valueobject.filter.ENAct2ENPlanWorkFilter;
import com.ksoe.energynet.valueobject.filter.ENActFilter;
import com.ksoe.energynet.valueobject.filter.ENActInTechCond2ENActFilter;
import com.ksoe.energynet.valueobject.filter.ENActIncomServ2ENActFilter;
import com.ksoe.energynet.valueobject.filter.ENActIncomeFilter;
import com.ksoe.energynet.valueobject.filter.ENActIncomeServices2SOBillFilter;
import com.ksoe.energynet.valueobject.filter.ENActIncomeServicesFilter;
import com.ksoe.energynet.valueobject.filter.ENActIncomeTech2DFDocFilter;
import com.ksoe.energynet.valueobject.filter.ENActIncomeTechConditionsFilter;
import com.ksoe.energynet.valueobject.filter.ENElement2ActFilter;
import com.ksoe.energynet.valueobject.filter.ENPlanWorkFilter;
import com.ksoe.energynet.valueobject.filter.ENServices2PlanFilter;
import com.ksoe.energynet.valueobject.filter.ENServicesCostFilter;
import com.ksoe.energynet.valueobject.filter.ENServicesObject2TechCondtnsServicesFilter;
import com.ksoe.energynet.valueobject.lists.ENAct2ENPlanWorkShortList;
import com.ksoe.energynet.valueobject.lists.ENActInTechCond2ENActShortList;
import com.ksoe.energynet.valueobject.lists.ENActIncomServ2ENActShortList;
import com.ksoe.energynet.valueobject.lists.ENActIncomeServices2SOBillShortList;
import com.ksoe.energynet.valueobject.lists.ENActShortList;
import com.ksoe.energynet.valueobject.lists.ENElement2ActShortList;
import com.ksoe.energynet.valueobject.lists.ENServicesObject2TechCondtnsServicesShortList;
import com.ksoe.energynet.valueobject.references.ENServicesObjectRef;
import com.ksoe.energypro.logic.LogicModule;
import com.ksoe.energypro.util.Tools;
import com.ksoe.lla.persistence.exception.DatasourceConnectException;
import com.ksoe.lla.persistence.exception.PersistenceException;
import com.ksoe.lla.persistence.exception.SystemException;
import com.ksoe.lla.security.UserProfile;
import com.ksoe.rqorder.dataminer.RQFKOrder2PlanFactDAO;
import com.ksoe.rqorder.dataminer.RQFKOrderDAO;
import com.ksoe.rqorder.ejb.RQFKOrderController;
import com.ksoe.rqorder.ejb.RQFKOrderControllerHome;
import com.ksoe.rqorder.valueobject.RQFKOrder;
import com.ksoe.rqorder.valueobject.RQFKOrderStatus;
import com.ksoe.rqorder.valueobject.filter.RQFKOrder2PlanFactFilter;
import com.ksoe.rqorder.valueobject.lists.RQFKOrder2PlanFactShortList;

public class ActIncomeLogic extends LogicModule {

	/**
	 * Дата (дата тех. условий), начиная с которой должен работать механизм подписания ЭЦП доходных актов по присоединению
	 * (повідомлень про надання послуги з приєднання)
	 */
	private static final LocalDate ENACTINCOMETECHCONDITIONS_SIGNING_START = LocalDate.of(2021, 6, 1);

    public int checkActIncome(String ctNumber, Date startDate, Date endDate,
            boolean isException) throws PersistenceException {
        ENActIncomeDAO aDAO = new ENActIncomeDAO(connection, userProfile);
        ENActIncomeFilter aFilter = new ENActIncomeFilter();

        aFilter.contractNumber = ctNumber;
        aFilter.conditionSQL =
            "((enactincome.actdatestart < " + Tools.convertDateToSQLString(startDate) +
            " and enactincome.actdateend > " + Tools.convertDateToSQLString(endDate) + ")" +
            " or (enactincome.actdatestart between " + Tools.convertDateToSQLString(startDate) +
            " and " + Tools.convertDateToSQLString(endDate) +
            " and enactincome.actdateend > " + Tools.convertDateToSQLString(endDate) + ")" +
            " or (enactincome.actdatestart < " + Tools.convertDateToSQLString(startDate) +
            " and enactincome.actdateend between " + Tools.convertDateToSQLString(startDate) +
            " and " + Tools.convertDateToSQLString(endDate) + ")" +
            " or (enactincome.actdatestart between " + Tools.convertDateToSQLString(startDate) +
            " and " + Tools.convertDateToSQLString(endDate) +
            " and enactincome.actdateend between " + Tools.convertDateToSQLString(startDate) +
            " and " + Tools.convertDateToSQLString(endDate) + "))";


        // aFilter.actDateStart = startDate;
        // aFilter.actDateEnd = endDate;

        int[] aArr = aDAO.getFilteredCodeArray(aFilter, 0, -1);
        if (aArr.length != 0) {
            if (isException)
                throw new SystemException(
                        "За вказаний період за цім договором вже складено прибутковий акт ...");
            else
                return 0;
        }
        return 1;
    }


    public int validateActIncome(int aiCode, String ctNumber, Date startDate, Date endDate,
            boolean isException) throws PersistenceException {

        ENActDAO actDAO = new ENActDAO(connection, userProfile);
        ENActFilter actFilter = new ENActFilter();
        boolean actInValid = false;

        actFilter.conditionSQL = " code in (select a.code from enoperativeobject oe, enact a " +
                " where a.elementcode = oe.elementcode " +
                " and a.statusrefcode = " + ENActStatus.CLOSED +
                " and oe.contractnumber = '" + ctNumber + "'" +
                " and a.code not in (select a2a.actrefcode from enactincome2enact a2a) " +
                " and a.dategen " +
                " between " + Tools.convertDateToSQLString(startDate) +
                " and " + Tools.convertDateToSQLString(endDate) + ")";

        ENActShortList actList = actDAO.getScrollableFilteredList(actFilter, 0, -1);

        if (actList.totalCount == 0) {
            if (isException)
                throw new SystemException(
                        "За вказаний період не знайдено проведених видаткових актів ...");
            else
                return 0;
        }

        if (actList.totalCount != 0) {
            String actsUnclosed = "Видаткові акти, які не проведено у ФК: ";
            for (int i = 0; i < actList.totalCount; i++) {
                if (actList.get(i).statusRefCode != ENActStatus.CLOSED) {
                    actsUnclosed = actsUnclosed + actList.get(i).numberGen + ",";
                    actInValid = true;
                }
            }
            if (actInValid) {
                throw new SystemException(actsUnclosed);
            }
        }

        if (actList.totalCount != 0) {
            for (int j = 0; j < actList.totalCount; j++) {
                ENActIncome2ENActDAO a2aDAO = new ENActIncome2ENActDAO(connection, userProfile);
                ENActIncome2ENAct a2a = new ENActIncome2ENAct();
                a2a.actIncomeRef.code = aiCode;
                a2a.actRef.code = actList.get(j).code;
                a2aDAO.add(a2a);
            }
        }

        return 1;
    }

    public void sigantured(int actCode) throws PersistenceException {
        ENActIncomeDAO dao = new ENActIncomeDAO(connection, userProfile);
        ENActIncome actIn = dao.getObject(actCode);
        if (actIn.statusRef.code != ENActStatus.GOOD) {
            throw new SystemException("В статус На підписанни переводяться тільки ЧЕРНОВІ акти ...", userProfile);
        }

        actIn.statusRef.code = ENActStatus.SIGNATURE;
        dao.save(actIn);
    }

    public void unSigantured(int actCode) throws PersistenceException {
        ENActIncomeDAO dao = new ENActIncomeDAO(connection, userProfile);
        ENActIncome actIn = dao.getObject(actCode);
        if (actIn.statusRef.code != ENActStatus.SIGNATURE) {
            throw new SystemException("В статус Черновий переводяться тільки акти На підписанні...", userProfile);
        }

        actIn.statusRef.code = ENActStatus.GOOD;
        dao.save(actIn);
    }

    public ActIncomeLogic(Connection connection, UserProfile userProfile) {
        super(connection, userProfile);
    }


    public int checkActIncomeTech(int tcCode, Date startDate, Date endDate,
            boolean isException) throws PersistenceException {
        ENActIncomeTechConditionsDAO atDAO = new ENActIncomeTechConditionsDAO(connection, userProfile);
        ENActIncomeTechConditionsFilter atFilter = new ENActIncomeTechConditionsFilter();

        atFilter.techCondServicesRef.code = tcCode;
        atFilter.conditionSQL =
            "((enactincometechcondtns.actdatestart < " + Tools.convertDateToSQLString(startDate) +
            " and enactincometechcondtns.actdateend > " + Tools.convertDateToSQLString(endDate) + ")" +
            " or (enactincometechcondtns.actdatestart between " + Tools.convertDateToSQLString(startDate) +
            " and " + Tools.convertDateToSQLString(endDate) +
            " and enactincometechcondtns.actdateend > " + Tools.convertDateToSQLString(endDate) + ")" +
            " or (enactincometechcondtns.actdatestart < " + Tools.convertDateToSQLString(startDate) +
            " and enactincometechcondtns.actdateend between " + Tools.convertDateToSQLString(startDate) +
            " and " + Tools.convertDateToSQLString(endDate) + ")" +
            " or (enactincometechcondtns.actdatestart between " + Tools.convertDateToSQLString(startDate) +
            " and " + Tools.convertDateToSQLString(endDate) +
            " and enactincometechcondtns.actdateend between " + Tools.convertDateToSQLString(startDate) +
            " and " + Tools.convertDateToSQLString(endDate) + "))";


        int[] atArr = atDAO.getFilteredCodeArray(atFilter, 0, -1);
        if (atArr.length != 0) {
            if (isException)
                throw new SystemException(
                        "За вказаний період за цім договором вже складено прибутковий акт ...");
            else
                return 0;
        }
        return 1;
    }

    public int validateActIncomeTech(int aiCode, int tcCode, Date startDate, Date endDate,
            boolean isException) throws PersistenceException {

        ActCalculator actCalc = new ActCalculator(connection, userProfile);
        ENActDAO actDAO = new ENActDAO(connection, userProfile);
        ENActFilter actFilter = new ENActFilter();
        boolean actInValid = false;

        actFilter.conditionSQL = " code in ( " +
                " select a.code from enact a where a.code in (" +
                " select a2pl.actrefcode from enact2enplanwork a2pl " +
                " where a2pl.plancode in ( " +
                "  select ct2pl.planrefcode from entechcond2planwork ct2pl " +
                "   where ct2pl.techconservicesrefcode = " + tcCode + ")) " +
                " and a.code not in (select t2a.actrefcode from enactintechcond2enact t2a) " +
                " and a.dategen between " + Tools.convertDateToSQLString(startDate) +
                " and " + Tools.convertDateToSQLString(endDate) + ")";

        ENActShortList actList = actDAO.getScrollableFilteredList(actFilter, 0, -1);

        if (actList.totalCount == 0) {
            if (isException)
                throw new SystemException(
                        "За вказаний період не знайдено видаткових актів на підписанні або проведені...");
            else
                return 0;
        }

        if (actList.totalCount != 0) {
            String actsUnclosed = "Видаткові акти, які не в статусі (на підписанні) або не проведені : ";
            for (int i = 0; i < actList.totalCount; i++) {
                if (actList.get(i).statusRefCode != ENActStatus.SIGNATURE && actList.get(i).statusRefCode != ENActStatus.CLOSED ) {
                    actsUnclosed = actsUnclosed + actList.get(i).numberGen + ",";
                    actInValid = true;
                }
            }
            if (actInValid) {
                throw new SystemException(actsUnclosed);
            }
        }

        if (actList.totalCount != 0) {
            for (int j = 0; j < actList.totalCount; j++) {
                ENActInTechCond2ENActDAO at2aDAO = new ENActInTechCond2ENActDAO(connection, userProfile);
                ENActInTechCond2ENAct at2a = new ENActInTechCond2ENAct();

                /* акт может быть на услуги со стороны */
                RQFKOrder2PlanFactDAO fo2plDAO = new RQFKOrder2PlanFactDAO(connection, userProfile);
                RQFKOrder2PlanFactFilter fo2plFilter = new RQFKOrder2PlanFactFilter();
                fo2plFilter.act.code = actList.get(j).code;
                RQFKOrder2PlanFactShortList fo2plList = fo2plDAO.getScrollableFilteredList(fo2plFilter, 0, -1);

                if (fo2plList.totalCount > 0) {
                    RQFKOrderDAO fkOrdeDAO = new RQFKOrderDAO(connection, userProfile);
                    RQFKOrder fkOrder = fkOrdeDAO.getObject(fo2plList.get(0).fkorderCode);
                    at2a.summaGen = fkOrder.sumWithoutNds.add(fkOrder.sumNds);
                } else {
                    /* пересчитать сумму по расходному акту */
                    at2a.summaGen = actCalc.calcTotalCostByAct(actList.get(j).code);
                }

                at2a.actIncomeRef.code = aiCode;
                at2a.actRef.code = actList.get(j).code;
                at2aDAO.add(at2a);
            }
        }

        return 1;
    }

    /* Метод для "Приєднання (нова Методика)" */
    public int validateActIncomeTechForServicesConnections(int aiCode, int tcCode, Date startDate, Date endDate,
            ENServicesObject servicesObj,
            boolean isException) throws PersistenceException {

        if (servicesObj == null)
        {
            throw new SystemException("\n\nNET-4237 Не знайдено зв''язаний об''єкт [ENServicesObject]!");
        }

        if (servicesObj.code == Integer.MIN_VALUE)
        {
            throw new SystemException("\n\nNET-4237 Не знайдено зв''язаний об''єкт [ENServicesObject]!");
        }

        ActCalculator actCalc = new ActCalculator(connection, userProfile);
        ENActDAO actDAO = new ENActDAO(connection, userProfile);
        ENActFilter actFilter = new ENActFilter();
        boolean actInValid = false;

        actFilter.conditionSQL = " (code in ( " +
                " select a.code from enact a where a.code in (" +
                " select a2pl.actrefcode from enact2enplanwork a2pl " +
                " where a2pl.plancode in ( " +
                "  select ct2pl.planrefcode from entechcond2planwork ct2pl " +
                "   where ct2pl.techconservicesrefcode = " + tcCode + ")) " +
				" and a.code not in (select t2a.actrefcode from enactintechcond2enact t2a "
				+ "WHERE t2a.actincomerefcode != " + aiCode + ") " +
                /// 14.06.13 Эта часть условия скопирована с клиента (типа чтобы не попали автоматические акты по услугам?)
                " and a.code not in (select ff.actcode from rqfkorder2planfact ff ) " +
                ///
                " and a.dategen between " + Tools.convertDateToSQLString(startDate) +
                " and " + Tools.convertDateToSQLString(endDate) + ")) " +

                " or (ENACT.ELEMENTCODE = " + servicesObj.element.code +
                "       and (ENACT.dategen between " + Tools.convertDateToSQLString(startDate) +
                "                             and " + Tools.convertDateToSQLString(endDate) + ")) ";

        ENActShortList actList = actDAO.getScrollableFilteredList(actFilter, 0, -1);

        if (actList.totalCount == 0) {
            if (isException)
                throw new SystemException(
                        "За вказаний період не знайдено видаткових актів на підписанні або проведені...");
            else
                return 0;
        }

        if (actList.totalCount != 0) {
            String actsUnclosed = "Видаткові акти, які не в статусі (на підписанні) або не проведені : ";
            for (int i = 0; i < actList.totalCount; i++) {
                if (actList.get(i).statusRefCode != ENActStatus.SIGNATURE && actList.get(i).statusRefCode != ENActStatus.CLOSED ) {
                    actsUnclosed = actsUnclosed + actList.get(i).numberGen + ",";
                    actInValid = true;
                }
            }
            if (actInValid) {
                throw new SystemException(actsUnclosed);
            }
        }

        ENElementDAO elementDAO = new ENElementDAO(connection, userProfile);

        if (actList.totalCount != 0) {
            for (int j = 0; j < actList.totalCount; j++) {
                ENActInTechCond2ENActDAO at2aDAO = new ENActInTechCond2ENActDAO(connection, userProfile);
                ENActInTechCond2ENAct at2a = new ENActInTechCond2ENAct();

                /* акт может быть на услуги со стороны */
                RQFKOrder2PlanFactDAO fo2plDAO = new RQFKOrder2PlanFactDAO(connection, userProfile);
                RQFKOrder2PlanFactFilter fo2plFilter = new RQFKOrder2PlanFactFilter();
                fo2plFilter.act.code = actList.get(j).code;
                RQFKOrder2PlanFactShortList fo2plList = fo2plDAO.getScrollableFilteredList(fo2plFilter, 0, -1);

                if (fo2plList.totalCount > 0) {
                    RQFKOrderDAO fkOrdeDAO = new RQFKOrderDAO(connection, userProfile);
                    RQFKOrder fkOrder = fkOrdeDAO.getObject(fo2plList.get(0).fkorderCode);
                    at2a.summaGen = fkOrder.sumWithoutNds.add(fkOrder.sumNds);
                } else {

                    ///// 14.06.13 Для актов по работам на сторону сумма считается по-другому!
                    /* пересчитать сумму по расходному акту */
                    //at2a.summaGen = actCalc.calcTotalCostByAct(actList.get(j).code);
                    ENElement element = elementDAO.getObject(actList.get(j).elementCode);
                    if (element.typeRef.code == ENElementType.SERVICES_OBJECT)
                    {
                        at2a.summaGen = actCalc.calcTotalCostForServices(actList.get(j).code);
                    }
                    else
                    {
                        at2a.summaGen = actCalc.calcTotalCostByAct(actList.get(j).code);
                    }
                    /////
                }

                at2a.actIncomeRef.code = aiCode;
                at2a.actRef.code = actList.get(j).code;
                at2aDAO.add(at2a);
            }
        }

        return 1;
    }


    /* перевод доходного акта в статус на подписании */
    public void siganturedTech(int actCode) throws PersistenceException {
        ENActIncomeTechConditionsDAO dao = new ENActIncomeTechConditionsDAO(connection, userProfile);
        ENActIncomeTechConditions actTechIn = dao.getObject(actCode);
        if (actTechIn.statusRef.code != ENActStatus.GOOD) {
            throw new SystemException("В статус На підписанни переводяться тільки ЧЕРНОВІ акти ...", userProfile);
        }

        Connection docFlowConnection = null;
        try {
	        actTechIn.statusRef.code = ENActStatus.SIGNATURE;
	        dao.save(actTechIn);

			// NET-4596 Подписание документа
			if (DocSigningLogic.isSignable(actTechIn)) {
				docFlowConnection = getConnection(AuthorizationJNDINames.DOCFLOW_DATASOURCE);
				DocSigningLogic docSigningLogic = DocSigningLogic.getInstanceForObject(docFlowConnection, userProfile, actTechIn);
				if (isActIncomeTechConditionsReadyForSigning(actCode)) {
					validateTechConditionsForActIncome(actCode);
					docSigningLogic.checkDocCodeForObject(actTechIn);
				}
				docSigningLogic.createFirstDocMovementForSigning(actTechIn);
			}
        } catch (DatasourceConnectException e) {
			throw new SystemException(e.getMessage(), e);
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

    /*Перевод доходного акта в статус Черновой из подписанного*/
    public void unSiganturedTech(int actCode) throws PersistenceException {
        ENActIncomeTechConditionsDAO dao = new ENActIncomeTechConditionsDAO(connection, userProfile);
        ENActIncomeTechConditions actTechIn = dao.getObject(actCode);
        if (actTechIn.statusRef.code != ENActStatus.SIGNATURE) {
            throw new SystemException("В статус Черновий переводяться тільки акти На підписанні...", userProfile);
        }

        actTechIn.statusRef.code = ENActStatus.GOOD;
        dao.save(actTechIn);

		// NET-4596 Отменяем связанный с актом документ в документообороте
		cancelDFDocForENActIncomeTech(actCode);
    }

    /* перевод доходного акта в статус проведенный в ФК */
    public void closeTech(int actCode) throws PersistenceException {

        try {

        ENActIncomeTechConditionsDAO dao = new ENActIncomeTechConditionsDAO(connection, userProfile);
        ENActInTechCond2ENActDAO acti2actDAO = new ENActInTechCond2ENActDAO(connection, userProfile);
        ENActInTechCond2ENActFilter acti2actFilter = new ENActInTechCond2ENActFilter();
        ENActIncomeTechConditions actTechIn = dao.getObject(actCode);
        ENActDAO actDAO = new ENActDAO(connection, userProfile);
        if (actTechIn.statusRef.code != ENActStatus.SIGNATURE) {
            throw new SystemException("Акти проводяться в ФК тільки якщо вони підписані !!! ...", userProfile);
        }

        actTechIn.statusRef.code = ENActStatus.CLOSED;
        // выбрать зависимые расходные акты и провести их если они в статусе "На подписании"

        Context cnt = new InitialContext();
        Object objRef = cnt.lookup(ENActController.JNDI_NAME);
        ENActControllerHome actHome = (ENActControllerHome) PortableRemoteObject.narrow(objRef, ENActControllerHome.class);

        Object objRefFk = cnt.lookup(RQFKOrderController.JNDI_NAME);
        RQFKOrderControllerHome fkHome = (RQFKOrderControllerHome) PortableRemoteObject.narrow(objRefFk, RQFKOrderControllerHome.class);

        acti2actFilter.actIncomeRef.code = actCode;
        acti2actFilter.conditionSQL = " ENACT.CODE  not in (select distinct f2pf.actcode from rqfkorder2planfact f2pf )  ";
        ENActInTechCond2ENActShortList  acti2actList = acti2actDAO.getScrollableFilteredList(acti2actFilter, 0, -1);
        if (acti2actList.totalCount != 0) {
        // проверим что бы расходный акт был в статусе На подписании
            for (int i = 0; i < acti2actList.totalCount; i++) {
        ENAct enactObj = actDAO.getObject(acti2actList.get(i).actRefCode);
        if (enactObj.statusRef.code == ENActStatus.SIGNATURE) {
            ENActController actController = actHome.create();
            actController.close(acti2actList.get(i).actRefCode , 0);
            
            }

        }
        }
        /* проводим акты на услуги со стороны если он в статусе складений  */
        RQFKOrder2PlanFactDAO fo2plDAO = new RQFKOrder2PlanFactDAO(connection, userProfile);
        RQFKOrder2PlanFactFilter fo2plFilter = new RQFKOrder2PlanFactFilter();
        RQFKOrderDAO fkDAO = new RQFKOrderDAO(connection, userProfile);

        fo2plFilter.conditionSQL = " RQFKORDER2PLANFACT.ACTCODE in ( " +
                    " select i2a.actrefcode from enactintechcond2enact i2a where i2a.actincomerefcode = " + actCode + " ) " ;
        RQFKOrder2PlanFactShortList fo2plList = fo2plDAO.getScrollableFilteredList(fo2plFilter, 0, -1);
        RQFKOrder fk = null;
        if (fo2plList.totalCount > 0)
        {
            for (int f = 0; f < fo2plList.totalCount; f++)
            {
                fk = fkDAO.getObject(fo2plList.get(f).fkorderCode);
            if (fkDAO.checkFKOrderInStatuses(fk, false, RQFKOrderStatus.CREATED, RQFKOrderStatus.IN_WORK_ON_WAREHOUSE))
            {
                RQFKOrderController fkController = fkHome.create();
                fkController.movePostingToFK(fo2plList.get(0).fkorderCode , 0 );
            }
            }
        }
    // конец акт со стороны
        dao.save(actTechIn);
        }
        catch (NamingException e)            {throw new SystemException(e.getMessage(),e);}
        catch (RemoteException e)            {throw new SystemException(e.getMessage(),e);}
        catch (CreateException e)            {throw new SystemException(e.getMessage(),e);}

    }

    /* перевод доходного акта в статус на подписании из проведенного в ФК */
    public void unCloseTech(int actCode) throws PersistenceException {
        try {
        ENActIncomeTechConditionsDAO dao = new ENActIncomeTechConditionsDAO(connection, userProfile);
        ENActIncomeTechConditions actTechIn = dao.getObject(actCode);
        ENActInTechCond2ENActDAO acti2actDAO = new ENActInTechCond2ENActDAO(connection, userProfile);
        ENActInTechCond2ENActFilter acti2actFilter = new ENActInTechCond2ENActFilter();
        ENActDAO actDAO = new ENActDAO(connection, userProfile);
        if (actTechIn.statusRef.code != ENActStatus.CLOSED) {
            throw new SystemException("Акт не проведений в ФК !!! ...", userProfile);
        }

        actTechIn.statusRef.code = ENActStatus.SIGNATURE;

     // выбрать зависимые расходные акты и отменить их проведение если они в статусе "Проведены в ФК"

        Context cnt = new InitialContext();
        Object objRef = cnt.lookup(ENActController.JNDI_NAME);
        ENActControllerHome actHome = (ENActControllerHome) PortableRemoteObject.narrow(objRef, ENActControllerHome.class);

        Object objRefFk = cnt.lookup(RQFKOrderController.JNDI_NAME);
        RQFKOrderControllerHome fkHome = (RQFKOrderControllerHome) PortableRemoteObject.narrow(objRefFk, RQFKOrderControllerHome.class);

        acti2actFilter.actIncomeRef.code = actCode;
        acti2actFilter.conditionSQL = " ENACT.CODE  not in (select distinct f2pf.actcode from rqfkorder2planfact f2pf )  ";
        ENActInTechCond2ENActShortList  acti2actList = acti2actDAO.getScrollableFilteredList(acti2actFilter, 0, -1);
        if (acti2actList.totalCount != 0) {
        // проверим что бы расходный акт был в статусе Проведены в ФК"
            for (int i = 0; i < acti2actList.totalCount; i++) {
        ENAct enactObj = actDAO.getObject(acti2actList.get(i).actRefCode);
        if (enactObj.statusRef.code == ENActStatus.CLOSED) {
            ENActController actController = actHome.create();
            actController.unClose(acti2actList.get(i).actRefCode , 0);
            }
        }
        }

        /* переводим акті со стороны в статус складений  */
        RQFKOrder2PlanFactDAO fo2plDAO = new RQFKOrder2PlanFactDAO(connection, userProfile);
        RQFKOrder2PlanFactFilter fo2plFilter = new RQFKOrder2PlanFactFilter();
        RQFKOrderDAO fkDAO = new RQFKOrderDAO(connection, userProfile);

        fo2plFilter.conditionSQL = " RQFKORDER2PLANFACT.ACTCODE in ( " +
                    " select i2a.actrefcode from enactintechcond2enact i2a where i2a.actincomerefcode = " + actCode + " ) " ;
        RQFKOrder2PlanFactShortList fo2plList = fo2plDAO.getScrollableFilteredList(fo2plFilter, 0, -1);
        RQFKOrder fk = null;
        if (fo2plList.totalCount > 0)
        {
            for (int f = 0; f < fo2plList.totalCount; f++)
            {
                fk = fkDAO.getObject(fo2plList.get(f).fkorderCode);
            if (fk.status.code == RQFKOrderStatus.IN_FK)
            {
                RQFKOrderController fkController = fkHome.create();
                fkController.deletePostingFromFK(fo2plList.get(f).fkorderCode , 0);
            }
            }
        }
    // конец акт со стороны

        dao.save(actTechIn);
        }
        catch (NamingException e)            {throw new SystemException(e.getMessage(),e);}
        catch (RemoteException e)            {throw new SystemException(e.getMessage(),e);}
        catch (CreateException e)            {throw new SystemException(e.getMessage(),e);}
    }



    /**
     *	формирование данных для доходного акта по договору услуг на сторону
     *
     *	@param actIncomeCode
     *	@param actDateStart
     *	@param actDateEnd
     *	@param servicesObject
     *	@param isException
     *
     */
	public int validateActIncomeServices(int actIncomeCode, Date actDateStart, Date actDateEnd,
			ENServicesObject servicesObject, boolean isException) throws PersistenceException {

		if (servicesObject == null) {
			throw new SystemException("\n\n"
					+ "Не знайдено зв''язаний об''єкт [ENServicesObject]!");
		}

		if (servicesObject.code == Integer.MIN_VALUE) {
			throw new SystemException("\n\n"
					+ "Не знайдено зв''язаний об''єкт [ENServicesObject]!");
		}

		ServicesLogic servicesLogic = new ServicesLogic(connection, userProfile);
		ENServicesCostDAO servicesCostDao = new ENServicesCostDAO(connection, userProfile);
		ENActDAO actDao = new ENActDAO(connection, userProfile);
		ENActIncomServ2ENActDAO actIncomServ2ENActDao = new ENActIncomServ2ENActDAO(connection, userProfile);
		ENElement2ActDAO element2ActDao = new ENElement2ActDAO(connection, userProfile);
		ActCalculator actCalculator = new ActCalculator(connection, userProfile);
		ENServices2CalcAdditionalItemsDAO services2CalcAdditionalItemsDao = new ENServices2CalcAdditionalItemsDAO(connection, userProfile);

		ENActFilter actFilter = new ENActFilter();
		actFilter.conditionSQL = " ENACT.DATEGEN between " + Tools.convertDateToSQLString(actDateStart) + " and " + Tools.convertDateToSQLString(actDateEnd)
		+ " and ENACT.STATUSREFCODE in ( " + ENActStatus.CLOSED + ", " + ENActStatus.SIGNATURE + " )";
        actFilter.conditionSQL += " and enact.code in ( "
        		+ " select a.code from enact a "
        		+ "  where a.elementcode = " + servicesObject.element.code
        		+ ((servicesObject.contractTypeRef.code != ENServicesContractType.SHIFT_LINES) ? "" :
            	" union all "
                + " select distinct p2a.actrefcode "
                + " from enservices2plan s2p, enact2enplanwork p2a "
                + " where s2p.servicesobjectrefcode = " + servicesObject.code
                + " and s2p.planrefcode = p2a.plancode")
        		+ " ) "
        		+ " and enact.code not in ( "
        		+ "  select ai2a.actrefcode "
        		+ "    from enactincomserv2enact ai2a, enactincomeservices ais "
        		+ "   where ai2a.actincomerefcode = ais.code "
        		+ "     and ais.servicesobjectrefcode = " + servicesObject.code + " ) ";

        ENActShortList actList = actDao.getScrollableFilteredList(actFilter, 0, -1);
        
        // Выборка актов добавленых вручную
        ENElement2ActFilter element2ActFilter = new ENElement2ActFilter();
        element2ActFilter.elementRef.code = servicesObject.element.code;
        element2ActFilter.conditionSQL = String.format("%s between ? and ?", ENAct.dateAct_QFielld);
        
        Vector<Object> element2ActParams = new Vector<Object>();
        element2ActParams.add(actDateStart);
        element2ActParams.add(actDateEnd);
        
        ENElement2ActShortList element2ActList = element2ActDao.getScrollableFilteredList(element2ActFilter, 0, -1, element2ActParams);
        
		ENServicesObjectRef servicesObjectRef = new ENServicesObjectRef();
		servicesObjectRef.code = servicesObject.code;
		BigDecimal totalAdditionalSums = services2CalcAdditionalItemsDao.getSumOfItemsByENServicesObjectRef(servicesObjectRef);

        if (actList.totalCount == 0 && element2ActList.totalCount == 0 
        		&& (totalAdditionalSums.compareTo(BigDecimal.ZERO) == 0)) {
            if (isException)
                throw new SystemException("\n\n"
                		+ "За вказаний період не знайдено видаткових актів на підписанні або проведені...");
            else
                return 0;
        }


		if (actList.totalCount != 0) {
			for (ENActShort actShort : actList.list) {
				ENActIncomServ2ENAct actIncomServ2ENAct = new ENActIncomServ2ENAct();

				/**  07.06.2018...  похоже функция calcTotalCostForServices морально устарела.... выбирает суммы отличные от суммы в акте ?!?!?!  */
				if(servicesObject.contractTypeRef.code != ENServicesContractType.SHIFT_LINES) {
					actIncomServ2ENAct.summaGen = servicesLogic.getSumByActServicesOut(actShort.code , false);		
				} else {
					// 22.11.2018 Для договоров выполнения работ в доходные акты попадают суммы из калькуляций
					BigDecimal totalSumByCalculations = new BigDecimal(0).setScale(2, BigDecimal.ROUND_HALF_UP);
					// в метод просто будет посылаться пустая сущность с заполненным кодом
					ENAct act = new ENAct();
					act.code = actShort.code;
					List<Integer> servicesCostCodes = servicesCostDao.getListOfCodesByENServicesObjectAndAct(servicesObject.element, act);
					for(int servicesCostCode : servicesCostCodes) {
						ENServicesCost servicesCost = servicesCostDao.getObject(servicesCostCode);
						totalSumByCalculations = totalSumByCalculations.add(servicesCost.costWithoutVAT);
					}
					actIncomServ2ENAct.summaGen = totalSumByCalculations;
				}

				actIncomServ2ENAct.actIncomeRef.code = actIncomeCode;
				actIncomServ2ENAct.actRef.code = actShort.code;
				actIncomServ2ENActDao.add(actIncomServ2ENAct);
			}
		}
		
		// Обработка актов добавленных в договор вручную
		if(element2ActList.totalCount > 0) {
			for(ENElement2ActShort element2Act : element2ActList.list) {
				ENActIncomServ2ENAct actIncomServ2ENAct = new ENActIncomServ2ENAct();
				actIncomServ2ENAct.actIncomeRef.code = actIncomeCode;
				actIncomServ2ENAct.actRef.code = element2Act.actRefCode;
				actIncomServ2ENAct.summaGen = actCalculator.getActSumByActCode(element2Act.actRefCode);
				actIncomServ2ENActDao.add(actIncomServ2ENAct);
			}
		}

        return 1;
    }



	/**
	 *	Получить дату проведения по коду доходного акта
	 *
	 *	@param actIncomeServicesCode - код доходного акта
	 *	@return datePostings - дата проведения
	 */
	public Date getDatePostingsByActIncomeServicesCode(int actIncomeServicesCode) {

	        PreparedStatement statement = null;
	        ResultSet set = null;

	        Date datePostings = null;
	        String SQL = " select p.dateposting from enactincomserv2prov p "
	        		+ " where p.actincomerefcode = " + actIncomeServicesCode;

	        try {
	            statement = connection.prepareStatement(SQL);

	            set = statement.executeQuery();
	            if (set.next()) {
	                datePostings = set.getDate(1);
	            }

	        } catch (SQLException e) {
	        	throw new SystemException(e.getMessage(),e);
			}  finally {
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

	        return datePostings;
	}

	/**
	 * Пересчитать сумму по доходному акту
	 *
	 * @param actIncomeCode - код доходного акта
	 * @throws PersistenceException 
	 */
	public ENActIncomeServices recalcActIncomeServices(int actIncomeCode) throws PersistenceException {
		if (actIncomeCode == Integer.MIN_VALUE) {
			throw new IllegalArgumentException("\n\nНе заданий код доходного акту для перерахунку!");
		}

		ENActIncomeServicesDAO actIncomeServicesDao = new ENActIncomeServicesDAO(connection, userProfile);
        ENActIncomServ2ENActDAO actIncomServ2ENActDao = new ENActIncomServ2ENActDAO(connection, userProfile);
		ENServicesObjectDAO servicesObjectDao = new ENServicesObjectDAO(connection, userProfile);
		ENServices2CalcAdditionalItemsDAO services2CalcAdditionalItemsDao = new ENServices2CalcAdditionalItemsDAO(connection, userProfile);		
		ENStandardConstDAO standardConstDao = new ENStandardConstDAO(connection, userProfile);

		ENActIncomeServices actIncomeServices = actIncomeServicesDao.getObject(actIncomeCode);
		if (actIncomeServices == null) {
			throw new IllegalArgumentException("\n\nНе знайдено доходний акт з кодом " + actIncomeCode + " !");
		}

		BigDecimal vat = standardConstDao.getENStandardConstEntryOnDate(ENStandardConst.PDV, actIncomeServices.dateGen);
		vat = vat.divide(new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP);

		double totalSum = 0;
        BigDecimal totalVAT = new BigDecimal(0);
        BigDecimal totalSumWithVAT = new BigDecimal(0);

        ENServicesObject servicesObject = servicesObjectDao.getObject(actIncomeServices.servicesObjectRef.code);

        ENActIncomServ2ENActFilter actIncomServ2ActFilter = new ENActIncomServ2ENActFilter();
        actIncomServ2ActFilter.actIncomeRef.code = actIncomeCode;
        ENActIncomServ2ENActShortList actIncomServ2ActShortList = actIncomServ2ENActDao.getScrollableFilteredList(actIncomServ2ActFilter, 0, -1);

		if (actIncomServ2ActShortList.totalCount > 0) {
			for (int i = 0; i < actIncomServ2ActShortList.totalCount; i++) {
				totalSum += actIncomServ2ActShortList.get(i).summaGen.doubleValue();
			}

			/** (вартість робіт з видаткового акту + 10% рентабельності) + 20% ПДВ  */
			/* не для догоров на выполнение работ */
			if(servicesObject.contractTypeRef.code != ENServicesContractType.SHIFT_LINES) {
				BigDecimal profitability = new BigDecimal(totalSum).setScale(2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal(0.1)).setScale(2, BigDecimal.ROUND_HALF_UP);
				totalSum = totalSum + profitability.doubleValue();	
			}

			totalVAT = new BigDecimal(totalSum).setScale(2, BigDecimal.ROUND_HALF_UP).multiply(vat).setScale(2, BigDecimal.ROUND_HALF_UP);
			totalSumWithVAT = new BigDecimal(totalSum).setScale(2, BigDecimal.ROUND_HALF_UP).add(totalVAT).setScale(2, BigDecimal.ROUND_HALF_UP);
		}

		ENServicesObjectRef servicesObjectRef = new ENServicesObjectRef();
		servicesObjectRef.code = servicesObject.code;

		if (servicesObject.contractTypeRef.code == ENServicesContractType.SHIFT_LINES) {
			BigDecimal totalAdditionalSums = services2CalcAdditionalItemsDao.getSumOfItemsByENServicesObjectRef(servicesObjectRef);
			BigDecimal totalAdditionalSumsVAT = totalAdditionalSums.multiply(vat).setScale(2,  BigDecimal.ROUND_HALF_UP);
			BigDecimal totalAdditionalSumsWithVAT = totalAdditionalSums.add(totalAdditionalSumsVAT);

			totalVAT = totalVAT.add(totalAdditionalSumsVAT);
			totalSumWithVAT = totalSumWithVAT.add(totalAdditionalSumsWithVAT);		
		}

		ENActIncomeServices actIncomeServicesAdd = actIncomeServicesDao.getObject(actIncomeCode);

		actIncomeServicesAdd.summaGen = totalSumWithVAT;
		actIncomeServicesAdd.summaVat = totalVAT;
		actIncomeServicesDao.save(actIncomeServicesAdd);

		return actIncomeServicesAdd;
	}

	/**
	 * Метод для отвязки расходного акта от доходного
	 * 
	 * @param actCode - код расходного акта
	 * @throws PersistenceException 
	 */
	public void removeActFromActIncomeServices(int actCode) throws PersistenceException {
		if (actCode == Integer.MIN_VALUE) {
			throw new IllegalArgumentException("\n\nНе заданий код доходного акту для перерахунку!");
		}

		ServicesLogic servicesLogic = new ServicesLogic(connection, userProfile);

		if (! servicesLogic.isActForServicesObjectSupplierContract(actCode)) {
			throw new SystemException("\n\nДля такого типу договорів ця функція не використовується!");
		}

		ENServicesObject servicesObject = servicesLogic.getServicesObjectForSupplierByActCode(actCode, false);		
		if (servicesObject == null) {
			throw new SystemException("\n\nНе знайдено договір з послуг на сторону для видаткового акту з кодом " + actCode + " !");
		}
		if (servicesObject.element == null || servicesObject.element.code == Integer.MIN_VALUE) {
			throw new SystemException("\n\nНе заданий код елементу для договору з послуг на сторону з кодом " + servicesObject.code + " !");
		}

		// Только для таких договоров
		if(servicesObject.contractTypeRef.code != ENServicesContractType.SHIFT_LINES) {
			throw new SystemException("\n\nДля такого типу договорів ця функція не використовується!");
		}

		ENAct2ENPlanWorkDAO act2planDao = new ENAct2ENPlanWorkDAO(connection, userProfile);
		ENAct2ENPlanWorkFilter act2planFilter = new ENAct2ENPlanWorkFilter();
		act2planFilter.actRef.code = actCode;

		ENAct2ENPlanWorkShortList act2planList = act2planDao.getScrollableFilteredList(act2planFilter, 0, -1);
		if (act2planList.totalCount == 0) {
			throw new SystemException("\n\nУ видатковий акт не включено жодного Завдання-Факту! Код акту: " + actCode);
		}

		ENActIncomServ2ENActDAO actIncome2actDao = new ENActIncomServ2ENActDAO(connection, userProfile);
		ENActIncomeServicesDAO actIncomeServicesDao = new ENActIncomeServicesDAO(connection, userProfile);

		ENActIncomServ2ENActFilter actIncome2actFilter = new ENActIncomServ2ENActFilter();
		actIncome2actFilter.actRef.code = actCode;
		int[] actIncome2actArr = actIncome2actDao.getFilteredCodeArray(actIncome2actFilter, 0, -1);
		if (actIncome2actArr.length > 1) {
			throw new SystemException("\n\nЦей видатковий акт включено до декількох прибуткових актів! Код акту: " + actCode);
		}

		// Если расходный акт уже включен в доходный, нужно пересчитать доходный акт и пересоздать счет
		if (actIncome2actArr.length > 0) {
			ENActIncomServ2ENAct actIncome2act = actIncome2actDao.getObject(actIncome2actArr[0]);
			if (actIncome2act == null) {
				throw new SystemException("\n\nНе вдалося знайти зв'язок видаткового та прибуткового акту! Код: " + actIncome2actArr[0]);
			}

			ENActIncomeServices actIncomeServices = actIncomeServicesDao.getObject(actIncome2act.actIncomeRef.code);
			if (actIncomeServices == null) {
				throw new SystemException("\n\nНе знайдено прибутковий акт з кодом " + actIncome2act.actIncomeRef.code + " !");
			}

			if (! isActIncomeLastForServicesObject(actIncomeServices.code, servicesObject.code)) {
				throw new SystemException("\n\nПрибутковий акт з кодом " + actIncomeServices.code + " не є останнім " + 
						"для договору № " + servicesObject.contractNumberServices + " !");
			}

			actIncome2actDao.remove(actIncome2actArr[0]);

			actIncomeServices = recalcActIncomeServices(actIncomeServices.code);
			createSOBill(servicesObject, actIncomeServices, true);
		}

		PlanWorkLogic planLogic = new PlanWorkLogic(connection, userProfile);
		ENPlanWorkDAO planDao = new ENPlanWorkDAO(connection, userProfile);
		ENServices2PlanDAO services2PlanDao = new ENServices2PlanDAO(connection, userProfile);
		ENServicesCostDAO servicesCostDao = new ENServicesCostDAO(connection, userProfile);
		ENElement2ActDAO element2ActDao = new ENElement2ActDAO(connection, userProfile);

		for (ENAct2ENPlanWorkShort act2plan : act2planList.list) {
			int factCode = act2plan.planCode;

			String strPlanCodes = planLogic.getCodesHistoryUp(factCode);
			if (strPlanCodes == null || strPlanCodes.trim().isEmpty()) {
				throw new SystemException("\n\nДля Завдання-Факту з кодом " + factCode + " не знайдено зв'язаних планів!");
			}
			strPlanCodes += ", " + factCode;

			ENPlanWorkFilter planFilter = new ENPlanWorkFilter();
			planFilter.conditionSQL = "enplanwork.code in (" + strPlanCodes + ")";
			int[] planCodes = planDao.getFilteredCodeArray(planFilter, 0, -1);
			if (planCodes.length == 0) {
				throw new SystemException("\n\nДля Завдання-Факту з кодом " + factCode + " не знайдено зв'язаних планів!");
			}

			for (int planCode : planCodes) {
				ENServices2PlanFilter services2PlanFilter = new ENServices2PlanFilter();
				services2PlanFilter.planRef.code = planCode;
				int[] services2PlanCodes = services2PlanDao.getFilteredCodeArray(services2PlanFilter, 0, -1);
				for(int s2pCode : services2PlanCodes) {
					services2PlanDao.remove(s2pCode);
				}

				ENServicesCostFilter servicesCostFilter = new ENServicesCostFilter();
				servicesCostFilter.planRef.code = planCode;
				int[] servicesCostCodes = servicesCostDao.getFilteredCodeArray(servicesCostFilter, 0, -1);
				for (int scCode : servicesCostCodes) {
					ENServicesCost servicesCost = servicesCostDao.getObject(scCode);
					servicesCost.planRef.code = Integer.MIN_VALUE;
					servicesCostDao.save(servicesCost);					
				}
			}
		}

		ENElement2ActFilter element2ActFilter = new ENElement2ActFilter();
		element2ActFilter.elementRef.code = servicesObject.element.code;
		element2ActFilter.actRef.code = actCode;
		int[] element2ActCodes = element2ActDao.getFilteredCodeArray(element2ActFilter, 0, -1);
		for (int element2ActCode : element2ActCodes) {
			element2ActDao.remove(element2ActCode);
		}
	}

	public void createSOBill(ENServicesObject servicesObject, ENActIncomeServices actIncome) throws PersistenceException {
		createSOBill(servicesObject, actIncome, false);
	}

	/**
	 * Создание счета по доходному акту
	 *
	 * @param servicesObject - объект договора
	 * @param actIncome - объект доходного акта
	 * @param reCreate - пересоздание счета (если = true, сначала удаляем существующий счет)
	 *
	 * @throws PersistenceException
	 */
	public void createSOBill(ENServicesObject servicesObject, ENActIncomeServices actIncome, boolean reCreate) throws PersistenceException {
		// NET-4572 Формирование счета услуг на сторону
		if(servicesObject.contractTypeRef.code == ENServicesContractType.SHIFT_LINES) {
			ENSOBillDAO soBillDao = new ENSOBillDAO(connection, userProfile);
			ENActIncomeServices2SOBillDAO actIncome2SOBillDao = new ENActIncomeServices2SOBillDAO(connection, userProfile);

			if (reCreate) {
				// NET-4572 Удаление счета и связки со счетом
				ENActIncomeServices2SOBillFilter actIncome2SoBillFilter = new ENActIncomeServices2SOBillFilter();
				actIncome2SoBillFilter.actIncomeRef.code = actIncome.code;

				ENActIncomeServices2SOBillShortList actIncome2SoBillList = actIncome2SOBillDao.getScrollableFilteredList(actIncome2SoBillFilter, 0, -1);

				for(ENActIncomeServices2SOBillShort actIncome2SoBill : actIncome2SoBillList.list) {
					actIncome2SOBillDao.remove(actIncome2SoBill.code);
					soBillDao.remove(actIncome2SoBill.soBillRefCode);
				}
			}

			ENSOBill soBill = new ENSOBill();
			soBill.dateGen =  actIncome.dateGen;
			soBill.sumTotal = actIncome.summaGen;
			soBill.sumGen = actIncome.summaGen.subtract(actIncome.summaVat);
			soBill.sumVat = actIncome.summaVat;
			soBill.userGen = actIncome.userGen;
			soBill.dateEdit = actIncome.dateEdit;
			soBill.servicesObjectRef.code = servicesObject.code;

			soBillDao.add(soBill);

			ENActIncomeServices2SOBill actIncome2SOBill = new ENActIncomeServices2SOBill();
			actIncome2SOBill.actIncomeRef.code = actIncome.code;
			actIncome2SOBill.soBillRef.code = soBill.code;

			actIncome2SOBillDao.add(actIncome2SOBill);
		}
	}

	/**
	 * Проверяет, является ли заданный доходный акт последним для договора
	 * 
	 * @param actIncomeCode - код доходного акта
	 * @param servicesObjectCode - код договора по услугам на сторону
	 * 
	 * @return <b>true</b> - если после заданного доходного акта больше нет других для данного договора
	 * 
	 * @throws PersistenceException
	 */
	public boolean isActIncomeLastForServicesObject(int actIncomeCode, int servicesObjectCode) throws PersistenceException {
		if (actIncomeCode == Integer.MIN_VALUE) {
			throw new IllegalArgumentException("\n\nНе заданий код прибуткового акту!");
		}
		if (servicesObjectCode == Integer.MIN_VALUE) {
			throw new IllegalArgumentException("\n\nНе заданий код договору з послуг на сторону!");
		}

		ENActIncomeServicesDAO actIncomeServicesDao = new ENActIncomeServicesDAO(connection, userProfile);
		ENActIncomeServicesFilter actIncomeServicesFilter = new ENActIncomeServicesFilter();
		actIncomeServicesFilter.conditionSQL =
			" enactincomeservices.code in (" +
			"   select ai.code " +
			"   from enactincomeservices ai " +
			"   where ai.servicesobjectrefcode =  " + servicesObjectCode +
			"     and ai.code <> " + actIncomeCode +
			"     and ai.actdatestart > " +
			"     (select a.actdatestart " +
			"      from enactincomeservices a " +
			"      where a.code = " + actIncomeCode + ")" +
			")";
		int[] actIncomeCodes = actIncomeServicesDao.getFilteredCodeArray(actIncomeServicesFilter, 0, -1);

		return (actIncomeCodes.length == 0);
	}

	public void addENActIncomeTech2DFDoc(ENActIncomeTechConditions actIncome, DFDoc doc) {
		if (actIncome == null || actIncome.code <= 0) {
			throw new IllegalArgumentException("\n\nNET-4596 Не заданий об'єкт прибуткового акта!");
		}
		if (doc == null || doc.code <= 0) {
			throw new IllegalArgumentException("\n\nNET-4596 Не заданий об'єкт документу!");
		}

		try {
			ENActIncomeTech2DFDocDAO act2DFDocDao = new ENActIncomeTech2DFDocDAO(connection, userProfile);
			ENActIncomeTech2DFDoc act2DFDoc = new ENActIncomeTech2DFDoc();
			act2DFDoc.actIncomeRef.code = actIncome.code;
			act2DFDoc.dfDocCode = doc.code;
			act2DFDoc.dfDocDate = doc.dateRegistration;
			act2DFDoc.dfDocDescription = doc.description;
			act2DFDoc.dfDocNumber = doc.docNum;
			act2DFDoc.dfDocSubtypeCode = doc.docSubTypeRef.code;
			act2DFDoc.dfDocTypeCode = doc.docTypeRef.code;
			act2DFDocDao.add(act2DFDoc);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		}
	}

	public int getDFDocCodeForENActIncomeTech(int actIncomeCode) {
		if (actIncomeCode <= 0) {
			throw new IllegalArgumentException("\n\nNET-4596 Не заданий код прибуткового акта!");
		}

		Connection dfConnection = null;

		try {
			ENActIncomeTech2DFDocDAO act2DocDao = new ENActIncomeTech2DFDocDAO(connection, userProfile);
			ENActIncomeTech2DFDocFilter act2DocFilter = new ENActIncomeTech2DFDocFilter();
			act2DocFilter.actIncomeRef.code = actIncomeCode;
			int[] act2DocCodes = act2DocDao.getFilteredCodeArray(act2DocFilter, 0, -1);

			if (act2DocCodes.length == 0) {
				return Integer.MIN_VALUE;
			}

			String dfDocCodes = "";
			for (int act2DocCode : act2DocCodes) {
				ENActIncomeTech2DFDoc act2Doc = act2DocDao.getObject(act2DocCode);
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

	public ENActIncomeTechConditions getENActIncomeTechByDFDocCode(int dfDocCode) {
		if (dfDocCode <= 0) {
			throw new IllegalArgumentException("\n\nNET-4596 Не заданий код документу!");
		}

		try {
			ENActIncomeTech2DFDocDAO act2DocDao = new ENActIncomeTech2DFDocDAO(connection, userProfile);
			ENActIncomeTech2DFDocFilter act2DocFilter = new ENActIncomeTech2DFDocFilter();
			act2DocFilter.dfDocCode = dfDocCode;
			int[] act2DocCodes = act2DocDao.getFilteredCodeArray(act2DocFilter, 0, -1);

			if (act2DocCodes.length == 0) {
				return null;
			}

			ENActIncomeTech2DFDoc act2Doc = act2DocDao.getObject(act2DocCodes[0]);
			ENActIncomeTechConditionsDAO actDao = new ENActIncomeTechConditionsDAO(connection, userProfile);
			return actDao.getObject(act2Doc.actIncomeRef.code);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		}
	}

	public void cancelDFDocForENActIncomeTech(int actIncomeCode) {
		if (actIncomeCode <= 0) {
			throw new IllegalArgumentException("\n\nNET-4596 Не заданий код прибуткового акта!");
		}

		int docCode = getDFDocCodeForENActIncomeTech(actIncomeCode);
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

	public void removeENActIncomeTech2DFDoc(int actIncomeCode, int docCode) {
		removeENActIncomeTech2DFDoc(actIncomeCode, docCode, false);
	}

	public void removeENActIncomeTech2DFDoc(int actIncomeCode, int docCode, boolean removeAll) {
		if (actIncomeCode <= 0) {
			throw new IllegalArgumentException("\n\nNET-4596 Не заданий код прибуткового акта!");
		}
		if (docCode <= 0 && !removeAll) {
			throw new IllegalArgumentException("\n\nNET-4596 Не заданий код документа!");
		}

		try {
			ENActIncomeTech2DFDocDAO act2DFDocDao = new ENActIncomeTech2DFDocDAO(connection, userProfile);
			ENActIncomeTech2DFDocFilter act2DFDocFilter = new ENActIncomeTech2DFDocFilter();
			act2DFDocFilter.actIncomeRef.code = actIncomeCode;
			act2DFDocFilter.dfDocCode = docCode;
			int[] act2DFDocCodes = act2DFDocDao.getFilteredCodeArray(act2DFDocFilter, 0, -1);

			if (act2DFDocCodes.length > 0) {
				ENActIncomeTechConditionsDAO actDao = new ENActIncomeTechConditionsDAO(connection, userProfile);
				ENActIncomeTechConditions act = actDao.getObject(actIncomeCode);
				if (act.statusRef.code != ENActIncomeStatus.GOOD) {
					throw new SystemException("\n\nNET-4596 Прибутковий акт повинен бути чорновим! Акт з кодом " + act.code + " не є чорновим!");
				}
			}

			for (int act2DFDocCode : act2DFDocCodes) {
				act2DFDocDao.remove(act2DFDocCode);
			}
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		}
	}

	public int getENServicesObjectCodeByENActIncomeTechConditionsCode(int actIncomeCode) {
		if (actIncomeCode <= 0) {
			throw new IllegalArgumentException("\n\nНе заданий код прибуткового акта!");
		}

		try {
			ENServicesObject2TechCondtnsServicesDAO so2tcDao = new ENServicesObject2TechCondtnsServicesDAO(connection, userProfile);
			ENServicesObject2TechCondtnsServicesFilter so2tcFilter = new ENServicesObject2TechCondtnsServicesFilter();
			so2tcFilter.conditionSQL = "techcondservrefcode = " +
					" (select ait.techcondservicesrefcod from enactincometechcondtns ait where ait.code = " + actIncomeCode + ") ";
			int[] so2tcArr = so2tcDao.getFilteredCodeArray(so2tcFilter, 0, -1);

			if (so2tcArr.length == 0) {
				throw new SystemException("\n\nНе знайдено договір для прибуткового акта з кодом " + actIncomeCode + " !");
			}
			if (so2tcArr.length > 1) {
				throw new SystemException("\n\nЗнайдено декілька (" + so2tcArr.length + 
						") зв'язків договора та прибуткового акта з кодом " + actIncomeCode + " !");
			}

			ENServicesObject2TechCondtnsServices so2tc = so2tcDao.getObject(so2tcArr[0]);
			if (so2tc.servicesObjectRef == null || so2tc.servicesObjectRef.code == Integer.MIN_VALUE) {
				throw new SystemException("\n\nНе знайдено договір для прибуткового акта з кодом " + actIncomeCode + " !");
			}

			return so2tc.servicesObjectRef.code;
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		}
	}

	public int getENActIncomeTechConditionsCodeByENServicesObjectCode(int servicesObjectCode) {
		return getENActIncomeTechConditionsCodeByENServicesObjectCode(servicesObjectCode, true);
	}

	public int getENActIncomeTechConditionsCodeByENServicesObjectCode(int servicesObjectCode, boolean isException) {
		if (servicesObjectCode <= 0) {
			throw new IllegalArgumentException("\n\nНе заданий код договору!");
		}

		try {
			ENActIncomeTechConditionsDAO actIncomeDao = new ENActIncomeTechConditionsDAO(connection, userProfile);
			
			ENActIncomeTechConditionsFilter actIncomeFilter = new ENActIncomeTechConditionsFilter();
            actIncomeFilter.conditionSQL = " techcondservicesrefcod = ( " +
                   " select s2t.techcondservrefcode " +
                   " from enservicesobject2techcondtnsservices s2t " +
                   " where s2t.servicesobjectrefcode = " + servicesObjectCode + " ) ";

            int actIncomeArr[] = actIncomeDao.getFilteredCodeArray(actIncomeFilter, 0, -1);

			if (actIncomeArr.length == 0) {
				if (isException) {
					throw new SystemException("\n\nНе знайдено прибутковий акт для договора з кодом " + servicesObjectCode + " !");
				} else {
					return Integer.MIN_VALUE;
				}
			}
			if (actIncomeArr.length > 1) {
				throw new SystemException("\n\nЗнайдено декілька (" + actIncomeArr.length + 
						") зв'язків прибуткового акта та договора з кодом " + servicesObjectCode + " !");
			}

			return actIncomeArr[0];
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		}
	}

	public ENTechConditionsObjects getTechCoditionsByENActIncomeTechConditionsCode(int actIncomeCode) {
		if (actIncomeCode <= 0) {
			throw new IllegalArgumentException("\n\nНе заданий код прибуткового акта!");
		}

		int servicesObjectCode = getENServicesObjectCodeByENActIncomeTechConditionsCode(actIncomeCode);
		if (servicesObjectCode == Integer.MIN_VALUE) {
			throw new SystemException("\n\nНе знайдено договір для прибуткового акта з кодом " + actIncomeCode + " !");
		}

		ServicesLogic servicesLogic = new ServicesLogic(connection, userProfile);
		try {
			ENTechConditionsObjects techConditions = servicesLogic.getTechCoditionByServicesObjectCode(servicesObjectCode);
			if (techConditions == null) {
				throw new SystemException("\n\nНе знайдено тех. умови для договору на приєднання з кодом " + servicesObjectCode + " !");
			}
			return techConditions;
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		}
	}

	public int getDFDocCodeForENServicesObject(int servicesObjectCode) {
		return getDFDocCodeForENServicesObject(servicesObjectCode, true);
	}

	public int getDFDocCodeForENServicesObject(int servicesObjectCode, boolean isException) {
		int actIncomeCode = getENActIncomeTechConditionsCodeByENServicesObjectCode(servicesObjectCode, isException);
		if (actIncomeCode > 0) {
			return getDFDocCodeForENActIncomeTech(actIncomeCode);
		} else {
			return Integer.MIN_VALUE;
		}
	}

    /**
     * Получение пакета WorkFlow по коду доходного акта (для договоров на присоединение)
     *
     * @param actIncomeCode - код доходного акта ({@link ENActIncomeTechConditions})
     *
     * @return пакет WorkFlow ({@link WFPack})
     */
	public WFPack getWFPackByENActIncomeTechConditionsCode(int actIncomeCode) {
		if (actIncomeCode <= 0) {
			throw new IllegalArgumentException("\n\nНе заданий код прибуткового акта!");
		}

		Connection wfConnection = null;
		try {
			wfConnection = getConnection(AuthorizationJNDINames.ENERGYWORKFLOW_DATASOURCE);
			WorkFlowLogic workFlowLogic = new WorkFlowLogic(wfConnection, userProfile);

			int servicesObjectCode = getENServicesObjectCodeByENActIncomeTechConditionsCode(actIncomeCode);
			if (servicesObjectCode == Integer.MIN_VALUE) {
				return null;
			}

			return workFlowLogic.getWFPackByENServicesObjectCode(servicesObjectCode);

		} catch (DatasourceConnectException e) {
			throw new SystemException(e.getMessage(), e);
		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		} finally {
			try {
				if (wfConnection != null && !wfConnection.isClosed()) {
					wfConnection.close();
					wfConnection = null;
				}
			} catch (SQLException e) {
			}
		}
	}

	public void createWFPack2DFDocForENActIncomeTechConditions(int actIncomeCode) {
		if (actIncomeCode <= 0) {
			throw new IllegalArgumentException("\n\nНе заданий код прибуткового акта!");
		}

		WFPack wfPack = getWFPackByENActIncomeTechConditionsCode(actIncomeCode);
		if (wfPack == null) {
			return; // throw ???
		}
		int dfDocCode = getDFDocCodeForENActIncomeTech(actIncomeCode);
		if (dfDocCode <= 0) {
			return; // throw ???
		}

		ServicesLogic servicesLogic = new ServicesLogic(connection, userProfile);
		servicesLogic.createWFPack2DFDoc(wfPack.code, dfDocCode);
	}

	public boolean isActIncomeTechConditionsReadyForSigning(int actIncomeCode) {
		if (actIncomeCode <= 0) {
			throw new IllegalArgumentException("\n\nНе заданий код прибуткового акта!");
		}

		// 17.06.2021 SUPP-100875 Теперь повідомлення про виконання робіт з приєднання
		// будут формироваться для всех договоров, независимо от даты ТУ
		if (1 == 1) {
			return true;
		}

		Date dateStart = com.ksoe.energynet.util.Tools.localDateToDate(ENACTINCOMETECHCONDITIONS_SIGNING_START);

		ENTechConditionsObjects techConditions = getTechCoditionsByENActIncomeTechConditionsCode(actIncomeCode);
		if (techConditions == null) {
			throw new SystemException("\n\nNET-4596 Не знайдено об'єкт технічних умов для прибуткового акту з кодом " +
					actIncomeCode + " !");
		}

		return (techConditions.dateGen.compareTo(dateStart) >= 0);
	}

	public void validateActIncomeTechCondForMoveInFK(ENActIncomeTechConditions actIncome, Object caller) {
		if (actIncome == null || actIncome.code <= 0) {
			throw new IllegalArgumentException("\n\nНе задано прибутковий акт!");
		}

        Connection docFlowConnection = null;
        try {
            if (isActIncomeTechConditionsReadyForSigning(actIncome.code)) {
    			docFlowConnection = getConnection(AuthorizationJNDINames.DOCFLOW_DATASOURCE);
    			DocSigningLogic docSigningLogic = new DocSigningLogic(docFlowConnection, userProfile);
    			docSigningLogic.checkDocCodeForObject(actIncome);
            }

            int dfDocCode = getDFDocCodeForENActIncomeTech(actIncome.code);
            if (dfDocCode > 0) {
            	// Т.к. при проведении со стороны документооборота вызывается этот же метод, будем передавать при вызове оттуда 
            	// доп. параметр caller, чтобы понимать, откуда вызов (если не передан, значит, проводят по-старому со стороны договора)
            	if (caller == null) {
                	if (actIncome.statusRef.code != ENActIncomeStatus.SIGNATURE) {
                		throw new SystemException("\n\nNET-4596 Для проведення цього договору прибутковий акт " +
                				"спочатку потрібно перевести на підписання!");
                	}

                	throw new SystemException("\n\nNET-4596 Для проведення цього договору потрібно завершити " +
                			"пов'язане завдання в документообігу!\n" + "Код документу: " + dfDocCode);
            	}
            }
        } catch (DatasourceConnectException e) {
			throw new SystemException(e.getMessage(), e);
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

	public void validateTechConditionsForActIncome(int actIncomeCode) {
		if (actIncomeCode <= 0) {
			throw new IllegalArgumentException("\n\nНе заданий код прибуткового акта!");
		}

		ENTechConditionsObjects techConditions = getTechCoditionsByENActIncomeTechConditionsCode(actIncomeCode);
		if (techConditions == null) {
			throw new SystemException("\n\nSUPP-100352 Не знайдено об'єкт технічних умов для прибуткового акту з кодом " +
					actIncomeCode + " !");
		}

		Date dateStart = com.ksoe.energynet.util.Tools.localDateToDate(ENACTINCOMETECHCONDITIONS_SIGNING_START);

		if (techConditions.dateGen.compareTo(dateStart) >= 0) {
			if (techConditions.identNumber <= 0) {
				throw new SystemException("\n\nSUPP-100352 Не задано ідентифікатор технічних умов (код прибуткового акту: " +
						actIncomeCode + ") !");
			}
		}
	}

	// SUPP-107483 Генерация информации по ту в комментарий к движению документа
	public String genTuInfo(int dfDocCode) {
		String returnValue = "";
		try {
			ENActIncomeTechConditions actIncomeTechConditions = getENActIncomeTechByDFDocCode(dfDocCode);
			if (actIncomeTechConditions == null)
				return returnValue;
			
			ENServicesObject2TechCondtnsServicesDAO condtnsServicesDAO = new ENServicesObject2TechCondtnsServicesDAO(
					connection, userProfile);
			ENServicesObject2TechCondtnsServicesFilter condtnsServicesFilter = new ENServicesObject2TechCondtnsServicesFilter();
			condtnsServicesFilter.techCondServRef.code = actIncomeTechConditions.techCondServicesRef.code;
			ENServicesObject2TechCondtnsServicesShortList condtnsServicesShortList = condtnsServicesDAO
					.getFilteredList(condtnsServicesFilter);
			ENServicesObjectDAO enServicesObjectDAO = new ENServicesObjectDAO(connection, userProfile);
			if (condtnsServicesShortList.getList().size() == 0)
				return returnValue;
			
			ENServicesObject enServicesObject = enServicesObjectDAO.getObjectNoSegr(
					((ENServicesObject2TechCondtnsServicesShort) condtnsServicesShortList.getList().get(0))
							.getServicesObjectRefCode());
			if (enServicesObject == null)
				return returnValue;
			ENTechConditionsObjects conditionsObjects = enServicesObject.techConObjects;
			if (conditionsObjects == null)
				return returnValue;

			returnValue += "\nНайменування замовника - ";
			returnValue += enServicesObject.contragentName;
			returnValue += "\nНомер договору - " + enServicesObject.contractNumberServices + "/"
					+ enServicesObject.contractNumber + " від "
					+ new SimpleDateFormat("dd.MM.yyy").format(enServicesObject.contractDate);
			returnValue += "\nНомер ТУ - " + conditionsObjects.numberGen + " від "
					+ new SimpleDateFormat("dd.MM.yyy").format(conditionsObjects.dateGen);
			if (conditionsObjects.tyServicesPower != null) {
				returnValue += "\nПотужність замовлена - " + conditionsObjects.tyServicesPower;
			} else {
				returnValue += "\nПотужність замовлена - 0";
			}
			if (conditionsObjects.tyCurrentPower != null) {
				returnValue += "\nПотужність існуюча - " + conditionsObjects.tyCurrentPower;
			} else {
				returnValue += "\nПотужність існуюча - 0";
			}
			returnValue += "\nОб'єкт - " + conditionsObjects.building;
			returnValue += "\nАдреса об'єкту - " + conditionsObjects.address;

		} catch (PersistenceException e) {
			throw new SystemException(e.getMessage(), e);
		}
		return returnValue;
	}
}

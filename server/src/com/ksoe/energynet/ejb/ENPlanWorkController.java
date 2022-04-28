
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

import java.math.BigDecimal;
import java.util.Date;

import com.ksoe.energynet.valueobject.ENPlanWork;
import com.ksoe.energynet.valueobject.FINExecutor;
import com.ksoe.energynet.valueobject.brief.ENPlanWorkItemShort;
import com.ksoe.energynet.valueobject.filter.ENPlanWorkFilter;
import com.ksoe.energynet.valueobject.lists.BufetOrderShortList;
import com.ksoe.energynet.valueobject.lists.ENMaterialsShortList;
import com.ksoe.energynet.valueobject.lists.ENPlanWorkBillingShortList;
import com.ksoe.energynet.valueobject.lists.ENPlanWorkShortList;
import com.ksoe.lla.security.UserProfile;

public interface ENPlanWorkController extends javax.ejb.EJBObject
{
  public static final String JNDI_NAME = "ksoe/energynet/ENPlanWorkController";


  /*ENPlanWork. Добавить*/
  public int add(ENPlanWork aENPlanWork) throws java.rmi.RemoteException;

  /*ENPlanWork. Удалить*/
  public void remove(int anObjectCode) throws  java.rmi.RemoteException;

  /*ENPlanWork. Изменить*/
  public void save(ENPlanWork aENPlanWork) throws java.rmi.RemoteException;
  public void save(ENPlanWork object, boolean isFromWorkOrderByt) throws java.rmi.RemoteException;
  public void save(ENPlanWork object, boolean isFromWorkOrderByt, boolean isFromBilling) throws java.rmi.RemoteException;
  public void save(ENPlanWork object, boolean isFromWorkOrderByt, boolean isFromBilling, boolean isOnlyRebinding) throws java.rmi.RemoteException;
  public void save(ENPlanWork object, boolean isFromWorkOrderByt, boolean isFromBilling, boolean isOnlyRebinding, boolean isForSupplier) throws java.rmi.RemoteException;

  public void saveInner(ENPlanWork aENPlanWork) throws  java.rmi.RemoteException;

  public void saveAttributes(ENPlanWork object) throws java.rmi.RemoteException;
  public void saveAttributes(ENPlanWork object, boolean forAllPlans) throws java.rmi.RemoteException;

  /*ENPlanWork. Получить объект*/
  public ENPlanWork getObject(int anObjectCode) throws  java.rmi.RemoteException;

  /*ENPlanWork. Получить полный список*/
  public ENPlanWorkShortList getList() throws  java.rmi.RemoteException;

  /*ENPlanWork. Получить список по фильтру*/
  public ENPlanWorkShortList getFilteredList(ENPlanWorkFilter aENPlanWorkFilter) throws  java.rmi.RemoteException;

  /*ENPlanWork. Получить список для просмотра*/
  public ENPlanWorkShortList getScrollableList(int aFromPosition, int aQuantity) throws  java.rmi.RemoteException;

  /*ENPlanWork. Получить список для просмотра по фильтру*/
  public ENPlanWorkShortList getScrollableFilteredList(ENPlanWorkFilter aEPActFilter, int aFromPosition, int aQuantity) throws  java.rmi.RemoteException;

  public ENPlanWorkBillingShortList getScrollableBillingFilteredList(ENPlanWorkFilter aEPActFilter, int aFromPosition, int aQuantity) throws  java.rmi.RemoteException;

  /*ENPlanWork. Получить список планов по счетчикам*/
  public ENPlanWorkShortList getScrollableFilteredListForMetrologyCounters(ENPlanWorkFilter aFilterObject, int fromPosition, int quantity) throws java.rmi.RemoteException;

  /*ENPlanWork. Получить список планов для Энергосбыта*/
  public ENPlanWorkShortList getScrollableFilteredListForEnergosbyt(ENPlanWorkFilter aFilterObject, int fromPosition, int quantity) throws java.rmi.RemoteException;

  /*ENPlanWork. Получить список для просмотра по условию*/
  public ENPlanWorkShortList getScrollableListByCondition(String aCondition,int aFromPosition,int aQuantity) throws java.rmi.RemoteException;

  public int getFilteredCount(ENPlanWork aFilterObject, String anCondition) throws java.rmi.RemoteException;

  public void generateEsentialByPlan(int planCode) throws  java.rmi.RemoteException;
  public void generateEsentialByPlanItem(int planItemCode) throws  java.rmi.RemoteException;
  public void cancelEsentialByPlan(int planCode) throws  java.rmi.RemoteException;
  public void cancelEsentialByPlanItem(int planItemCode) throws  java.rmi.RemoteException;

  public int closePlanWork(int planCode, ENPlanWorkItemShort[] planWorkItemArr)
		  throws java.rmi.RemoteException;

  public int closePlanWork(int planCode) throws  java.rmi.RemoteException;
  public int closePlanWork(int planCode, boolean isFromBilling ) throws java.rmi.RemoteException;
  public int closePlanWork(int planCode, boolean isFromBilling, ENPlanWorkItemShort[] planWorkItemArr) throws java.rmi.RemoteException;
  public int closePlanWork(int planCode, boolean isFromBilling, ENPlanWorkItemShort[] planWorkItemArr, boolean isForSupplier)
		  throws java.rmi.RemoteException;
  public int closePlanWork(int planCode, boolean isFromBilling, ENPlanWorkItemShort[] planWorkItemArr, boolean isForSupplier, 
		  Date newPlanDate) throws java.rmi.RemoteException;
  
  public int closePlanWorkBilling(int planCode) throws java.rmi.RemoteException;

  public int closePlanWorkWithDate(int planCode, Date newPlanDate) throws java.rmi.RemoteException;
  public int closePlanWorkWithDate(int planCode, Date newPlanDate, ENPlanWorkItemShort[] planWorkItemArr) throws java.rmi.RemoteException;
  public int closePlanWorkWithDate(int planCode, Date newPlanDate, ENPlanWorkItemShort[] planWorkItemArr, boolean isForSupplier) throws java.rmi.RemoteException;

  //public int closePlanWorkForByt(int planCode, String molCode, String molName, FINExecutor finExecutor, Date newPlanDate) throws java.rmi.RemoteException;
  public int closePlanWorkForByt(int planCode, String molCode, String molName, FINExecutor finExecutor, Date newPlanDate,
		  String masterCode, String masterName, String mechanicCode, String mechanicName) throws java.rmi.RemoteException;

  public int closePlanWorkWithParams(int planCode, Date newPlanDate, Date timeStart, Date timeFinal,
		  String masterCode, String masterName, String mechanicCode, String mechanicName, ENPlanWorkItemShort[] planWorkItemArr) throws java.rmi.RemoteException;
  public int closePlanWorkWithParams(int planCode, Date newPlanDate,
		  String masterCode, String masterName, String mechanicCode, String mechanicName, ENPlanWorkItemShort[] planWorkItemArr) throws java.rmi.RemoteException;
  public int closePlanWorkWithParams(int planCode, Date newPlanDate, Date timeStart, Date timeFinal,
		  String masterCode, String masterName, String mechanicCode, String mechanicName) throws java.rmi.RemoteException;
  public int closePlanWorkWithParams(int planCode, Date newPlanDate,
		  String masterCode, String masterName, String mechanicCode, String mechanicName) throws java.rmi.RemoteException;
  public int closePlanWorkWithParams(int planCode, Date newPlanDate, Date timeStart, Date timeFinal,
		  String masterCode, String masterName, String mechanicCode, String mechanicName,
		  ENPlanWorkItemShort[] planWorkItemArr, boolean isForSupplier) throws java.rmi.RemoteException;

  public void closePlanWorkBySRS(int planCode) throws  java.rmi.RemoteException;
  public void closePlanWorkBySVES(int planCode) throws  java.rmi.RemoteException;
  public void closePlanWorkBySPS(int planCode) throws  java.rmi.RemoteException;

  public void openPlanWork(int planCode) throws  java.rmi.RemoteException;
  public void openPlanWork(int planCode, boolean isFromCallCenter) throws  java.rmi.RemoteException;
  public void openPlanWork(int planCode, boolean isFromCallCenter, boolean isFromBilling) throws java.rmi.RemoteException;

  public int addBySRS(ENPlanWork aENPlanWork) throws java.rmi.RemoteException;
  public int addBySVES(ENPlanWork aENPlanWork) throws java.rmi.RemoteException;
  public int addBySPS(ENPlanWork aENPlanWork) throws java.rmi.RemoteException;
  public int addByByt(ENPlanWork aENPlanWork) throws java.rmi.RemoteException;
  public int addByProm(ENPlanWork aENPlanWork) throws java.rmi.RemoteException;


  public int addByTransport(ENPlanWork object) throws  java.rmi.RemoteException;
  public int addByTrucking(ENPlanWork object) throws  java.rmi.RemoteException;
  public int addByMetrologyCounters(ENPlanWork object) throws  java.rmi.RemoteException;
  public int addByMetrologyDevices(ENPlanWork object) throws  java.rmi.RemoteException;
  public int addByMetrologyObject(ENPlanWork object) throws  java.rmi.RemoteException;
  public int addByMetrology(ENPlanWork object) throws  java.rmi.RemoteException;


  public int addByBuilder(ENPlanWork object) throws  java.rmi.RemoteException;
  public int addByIzolation(ENPlanWork object) throws  java.rmi.RemoteException;
  public int addBySIT(ENPlanWork object) throws  java.rmi.RemoteException;


  public void saveBySRS(ENPlanWork aENPlanWork) throws  java.rmi.RemoteException;
  public void saveBySVES(ENPlanWork aENPlanWork) throws  java.rmi.RemoteException;
  public void saveBySPS(ENPlanWork aENPlanWork) throws  java.rmi.RemoteException;
  public void saveByByt(ENPlanWork aENPlanWork) throws  java.rmi.RemoteException;
  public void saveByProm(ENPlanWork aENPlanWork) throws  java.rmi.RemoteException;

  public void removeBySRS(int anObjectCode) throws  java.rmi.RemoteException;
  public void removeBySVES(int anObjectCode) throws  java.rmi.RemoteException;
  public void removeBySPS(int anObjectCode) throws  java.rmi.RemoteException;
  public void removeByByt(int anObjectCode) throws  java.rmi.RemoteException;
  public void removeByProm(int anObjectCode) throws  java.rmi.RemoteException;

  public String getPlanCodesHistoryDown(int planCode) throws  java.rmi.RemoteException;
  public String getPlanCodesHistoryUp(int planCode)  throws  java.rmi.RemoteException;

  public int addBySDTU(ENPlanWork object)  throws  java.rmi.RemoteException;
  public int addByRZA(ENPlanWork object)  throws  java.rmi.RemoteException;

  // moved to CNPack2PlanWorkController - public int createPack(CNPack2PlanWork object) throws  java.rmi.RemoteException;
  public String getPlanStatusFromCN(int planCode) throws  java.rmi.RemoteException;

  public void preConfirm(int planCode)  throws  java.rmi.RemoteException;
  public void confirm(int planCode) throws  java.rmi.RemoteException;
  public void undoConfirm(int planCode) throws  java.rmi.RemoteException;

  public int[] getFilteredCodeArray(ENPlanWorkFilter f) throws  java.rmi.RemoteException;

  public ENMaterialsShortList getMaterialsFromPlans(ENPlanWorkFilter f, int tkMaterialCode) throws  java.rmi.RemoteException;
  public ENMaterialsShortList getMaterialsFromPlans(ENPlanWorkFilter f, String materialCondition, int tkMaterialCode) throws  java.rmi.RemoteException;
  public ENMaterialsShortList getServicesFromPlans(ENPlanWorkFilter f, int tkMaterialCode) throws  java.rmi.RemoteException;

  public void editPreConfirm(int planCode)  throws  java.rmi.RemoteException;

  public void saveAddInfo(ENPlanWork object)   throws  java.rmi.RemoteException;

  public int addByParent(ENPlanWork object, int parentPlanCode)    throws  java.rmi.RemoteException;

  public int addByPurchasesObject(ENPlanWork object) throws  java.rmi.RemoteException;
  public int addByPurchasesNoObject(ENPlanWork object) throws  java.rmi.RemoteException;

  public int addByTransformerObject(ENPlanWork object) throws  java.rmi.RemoteException;

  public void bind2parentPlan(int planCode, int parentPlanCode, int correctionReasonCode) throws  java.rmi.RemoteException;

  public int addByOperativeObject(ENPlanWork object)  throws  java.rmi.RemoteException;

  public int addByServicesObject(ENPlanWork object)  throws  java.rmi.RemoteException;

  public int addByPreproductionObject(ENPlanWork object)  throws  java.rmi.RemoteException;

  public int addByMetrologySubstation(ENPlanWork object)  throws  java.rmi.RemoteException;

  public int addByEB(ENPlanWork object)  throws  java.rmi.RemoteException;

  public int addByWritingNoObject(ENPlanWork object)  throws  java.rmi.RemoteException;

  public int addBySiz(ENPlanWork object)  throws  java.rmi.RemoteException;

  public int addByEquipmentRepair(ENPlanWork object)  throws  java.rmi.RemoteException;

  public int addByEquipment(ENPlanWork object)  throws  java.rmi.RemoteException;

  public int addByPVZ(ENPlanWork object)  throws  java.rmi.RemoteException;

  public int addByGift(ENPlanWork object)  throws  java.rmi.RemoteException;

  public int addByAVR16(ENPlanWork object)  throws  java.rmi.RemoteException;

  public void finishPlanWork(int planCode) throws java.rmi.RemoteException;

  public void undoFinishPlanWork(int planCode) throws java.rmi.RemoteException;

  public int addAutoBySiz(ENPlanWork object, UserProfile aUserProfile)  throws  java.rmi.RemoteException;
  public int addByWritingOffProtection(ENPlanWork object)  throws  java.rmi.RemoteException;

  public int addByServicesFromSideObject(ENPlanWork object) throws java.rmi.RemoteException;
  public void removeByServicesFromSideObject(int anObjectCode) throws  java.rmi.RemoteException;

  public void recalcTotalTime(int planCode) throws java.rmi.RemoteException;

  public int addAutoPlanByTires(ENPlanWork object, UserProfile aUserProfile) throws java.rmi.RemoteException;


  /* ENPlanWork. Добавить план для договора на ТУ */
  public int addPlanByTechConditions(ENPlanWork object, int techCondServicesCode) throws java.rmi.RemoteException;
  public int add(ENPlanWork object, boolean isForTechConditions) throws java.rmi.RemoteException;

  /* ENPlanWork. Список планов для договоров на ТУ */
  public ENPlanWorkShortList getTechConditionsPlanList(ENPlanWorkFilter aEPActFilter, int aFromPosition, int aQuantity) throws  java.rmi.RemoteException;

  public void saveComment(int aENPlanWorkCode,String comment) throws  java.rmi.RemoteException;

  public int copyPlanForTemplate(int planCode, ENPlanWork newPlan) throws java.rmi.RemoteException;

  /* 21.05.2012 +++ изменение поля "Содержание работ по инв. программе" на утверждённых планах..  */
  public void changeInvestDescription(int planCode, String description) throws  java.rmi.RemoteException;
  /*15.11.2012 +++ изменение поля "Объемов работ по инв. программе" на утверждённых планах..*/
  public void changeInvestAmount(int planCode, BigDecimal investWorksAmount) throws  java.rmi.RemoteException;

  public void changeInvestStartDate(int planCode, Date investWorkDateStart) throws  java.rmi.RemoteException;
  public void changeInvestMeasurement(int planCode, int investMeasurementCode) throws  java.rmi.RemoteException;

  /*NET-2106  смена исполнителя и подразделения на планах */
  public void saveAddInfoExecutorDepartment(ENPlanWork object)   throws  java.rmi.RemoteException;

  public void import2Bufet(int aENPlanWorkCode,String numberDoc,int typeCode) throws  java.rmi.RemoteException;
  public void importOrderBufet(int aENPlanWorkCode,String numberDoc) throws  java.rmi.RemoteException;

  public BufetOrderShortList getBufetOrderList(Date aDate, int aType) throws  java.rmi.RemoteException;
  public BufetOrderShortList getBufetOrderMaterialList(Date aDate) throws  java.rmi.RemoteException;

  public BufetOrderShortList getBufetPlanMenuOrderList(Date aDate, int aType) throws  java.rmi.RemoteException;

  /* ENPlanWork. Общий (упрощенный) Список планов  */
  public ENPlanWorkShortList getPlanWorkGeneralList(ENPlanWorkFilter aENPlanWorkFilter, int aFromPosition, int aQuantity) throws java.rmi.RemoteException;

  public void export2BufetVtrati(int aPlanCode) throws  java.rmi.RemoteException;

  public void linkMaterials(int aENPlanWorkCode)  throws  java.rmi.RemoteException;

  public void unlinkMaterials(int aENPlanWorkCode)  throws  java.rmi.RemoteException;

  public void addPlan2TechConditions(ENPlanWork object, int techCondServicesCode) throws java.rmi.RemoteException;
  public void removePlanFromTechConditions(ENPlanWork object,int techCondServicesCode) throws java.rmi.RemoteException;

	/** Копирование плана для договора на присоединение */
	public int copyPlanPriconnections(int planCode, int monthGen, int yearGen, int soCode, int elementCode)
			throws java.rmi.RemoteException;

	/** Изменение раздела и пункта ИнвестПрограммы на плане */
	public void updateInvestProgramData(int planCode, int investGroupCode, String investItemNumber)
			throws java.rmi.RemoteException;

	/** Добавить план для АВР */
	public int add(ENPlanWork object, boolean isForTechConditions, boolean isForAVR) throws java.rmi.RemoteException;

	public int add(ENPlanWork object, boolean isForTechConditions, boolean isForAVR, boolean isFromCallCenter)
			throws java.rmi.RemoteException;

	/** создание плана на основании листа осмотра. */
	public int add(ENPlanWork object, boolean isForTechConditions, boolean isForAVR, boolean isFromCallCenter,
			boolean createPlanFromInspectionSheet) throws java.rmi.RemoteException;

	public void changeObjectForCallCenterAVRPlan(ENPlanWork object) throws java.rmi.RemoteException;

	public void removeAVRPlanFromCallCenter(int ccDamageLogCode) throws java.rmi.RemoteException;

	public void editENIPImplementationType(int objPlanCode, int ipImplementationTypeCode)
			throws java.rmi.RemoteException;


  /**Перечень планов для пункта ИП  */
  public ENPlanWorkShortList getScrollableFilteredListIPitem2plan(ENPlanWorkFilter filterObject, int fromPosition, int quantity) throws  java.rmi.RemoteException;

  /** Связать событие CallCenter с планами EnergyNet */
  public void linkPlanWorksToCallCenter(int[] damageCodesList, int[] planWorkCodesList, String newspaperName, String newspaperNumber, Date datePubl, Date dateBegin, Date dateFinish, int needsApprovalByCustomer) throws java.rmi.RemoteException;
  /** Отвязать событие CallCenter от планов EnergyNet */
  public void unlinkPlanWorksToCallCenter(int damageCode) throws java.rmi.RemoteException;
  /** Установить признак causeDisconnection */
  public void setCauseDisconnectionOn(int planCode)  throws java.rmi.RemoteException;
  public void setCauseDisconnectionOff(int planCode)  throws java.rmi.RemoteException;

    /**
     *   Проверка возможности установить признак "С отключением потребителей"
     */
	public void checkCauseDisconnectionOn(ENPlanWork planWork) throws java.rmi.RemoteException;

	/**
	 * Возвращает код месячного плана по коду любого плана
	 *
	 * @param plancode
	 * @return plancode
	 */

	public int getMonthPlanCodeByAnyPlanCode(int plancode, boolean isException)
			throws java.rmi.RemoteException;


	/**
	 *  Создание плана на услуги к договору на тех.надзор по договору на присоединение
	 *
	 *  @param finDocID - PK договора в Фин.кол.
	 *  @param techCondServicesCode - код договора на ТУ
	 *
	 *  @return planCode - код плана
	 */
	public int creatingPlanForServicesByTechAgreement(int finDocID, int techCondServicesCode)
			throws java.rmi.RemoteException;

	/**
	 * Удаление плана из сменного задания Энергосбыта
	 * @param planCode - код плана
	 */
	public void removeFromWorkOrderByt(int planCode) throws java.rmi.RemoteException;


	/** Изменение объекта планирования */
	public void changeObjectOfPlanning(ENPlanWork object)
			throws java.rmi.RemoteException;

	/** Изменение типа акта */
	public void changePlanState(ENPlanWork object)
			throws java.rmi.RemoteException;

	/** Изменение вида работ */
	public void changePlanWorkForm(ENPlanWork object)
			throws java.rmi.RemoteException;

	public void changePlanDateForByt(int planCode, Date newDate) throws java.rmi.RemoteException;

    /**
    *  25.04.2017 +++ add с добавлением связки "план - договор на услуги на сторону"
    *  добавление плана
    *  связка плана с услугой
    */
    public int addPlanByShiftLinesServices(ENPlanWork object, int servicesObjectCode) throws java.rmi.RemoteException;
    public void addPlan2ShiftLineServices(ENPlanWork object,int servicesObjectCode) throws java.rmi.RemoteException;

	public int makePlanDisconnectionSupplier(int elementCode, int tkCode, Date planDate, FINExecutor finExecutor,
			String masterCode, String masterName, String mechanicCode, String mechanicName) throws java.rmi.RemoteException;
	public int makePlanDisconnectionSupplier(int elementCode, int tkCode, Date planDate, FINExecutor finExecutor,
			String masterCode, String masterName, String mechanicCode, String mechanicName, int dfPackCode , int departmentCode
			, int budgetCode) throws java.rmi.RemoteException;

	public void removePlanDisconnectionSupplier(int planCode) throws java.rmi.RemoteException;

	public void recalcPlanworkItemAndHumenItemsByPlanItemCode(int planworkItemCode) throws java.rmi.RemoteException;
	
	
	/* ENPlanWork. Добавить план по услугам на сторону а так же при наличии присоединения будет заполнен  techCondServicesCode */
	public int addPlanByTechConditionsAndServicesFromSide(ENPlanWork object, int techCondServicesCode , int servicesFromSideCode ) throws java.rmi.RemoteException;

	public boolean isPlanForRepairRequest(ENPlanWork plan) throws java.rmi.RemoteException;
	
	/*ENPlanWork. ENPlanWork(План робіт).Получить список для услугсо стороны */
	public ENPlanWorkShortList getScrollableFilteredListServicesFromSide(ENPlanWorkFilter aEPActFilter, int aFromPosition, int aQuantity) throws  java.rmi.RemoteException;

	/*SUPP-105980 отвязка плана от обьекта аренды*/
	public void removePlan2Rent(ENPlanWork object, int servicesObjectCode) throws  java.rmi.RemoteException;

}
unit  Main;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, ComCtrls, ToolWin, ElegantMDI, InvokeRegistry, xmldom, XMLIntf,
  msxmldom, XMLDoc, ExceptionLog, AppEvnts, ExtCtrls, Rio, SOAPHTTPClient, Menus,
  Registry, ChildFormUnit, SOAPHTTPTrans, strUtils, AbUnzper, winInet,
  RegistryTools, DialogFormUnit, JvComponentBase, JvZlibMultiple, DateUtils,
  ShowENCalcPowerReserveItem, ShowENCalcPowerReserve, ShowCCTree,
  ShowRQContactType, ShowFINCurrency, ShowFINAccountType, ShowENServicesGuard,
  ShowENPlanInformCustomer, ShowENCalcAdditionalItemType,
  ShowENServices2CalcAdditionalItems, ShowENInspectionSheet,
  ShowTKDefects2Inspection, ShowENServicesShift, ShowCCAddressStreet,
  ShowENSORItems2Post04, ShowENSORItems2Post10, ShowENAct2FinKodIst,
  ShowFinKodIst, ShowENSealNames, ShowENSealColors, ShowENBonusListItem,
  ShowENBonusList, reportRestsByOrder, ShowENSORentItems, ShowENSOPayBillProv,
  ShowENSOBill, ShowENDamageRecovery2ENAct, ShowENDamageRecoveryStatus,
  ShowENDamageRecovery, ShowENFamilyRelation, ShowENServicesRelaxation,
  ShowENCottage, ShowTKTransportClassification, ShowENFuelSheetVolumes,
  ShowENFuelInvResultType, ShowENFuelInvResult, ShowENFuelInventarizationStatus,
  ShowENFuelInventarization, ShowENFuelInventarizationItem,
  ShowENActFailureReason, ShowENActFailure, ShowENPlanWork2ActFailure,
  ShowENNormVolumeAVZItem, ShowENNormativeVolumeAVZ, ShowENDelayServices,
  ShowENSituationRPN, ShowENGauge150, EditRQWarehouseMaterialsMovement,
  EditENTransportManagement, ShowENMolFuelMotionType, ShowENMolFuelMotion,
  ShowRQAllocationListForm, ShowRQAllocationListStatus, ShowRQAllocationListType,
  ShowRQAllocationList, ShowRQAllocationListItem, ShowENEquipmentState,
  ShowENNomenclaturesOperative, ShowTKMaterialsAddGroup, ShowENAVRPlan,
  ShowEPWorker, ShowENAccess2Enelement, ShowENUnitedGroupConnections,
  ShowENCoordinates, ShowENPayment2SOType, ShowENHighVoltCellSupplies,
  ShowENLine10Supplies, ShowENTraversType, ShowENTravers, ShowENHookType,
  ShowENHook, ShowENStandType, ShowENStand, ShowENGroundType,
  ShowENLineRoute, ShowENLandscape, ShowENWiresType, ShowENWiresCut,
  ShowENWires, ShowENWiresItem, ShowENBranch10, ShowENBranch10Item,
  ShowENWiresFastening, ShowENObjCrossDown, ShowENObjCrossUp, ShowENAirCrossing,
  ShowENAirCrossingItem, ShowENCabelMuftaType, ShowENCabelOutType10,
  ShowENCabelOut10, ShowENCabelOut10Item, ShowENScheme, ShowENSchemeAttachment,
  ENConsts, ShowENElement, ShowENLine04, ShowENLine10, ShowENSubstation04,
  ShowTKTechCard, ShowENPlanWorkStatus, ShowENPlanWork, ShowENPlanWorkBilling,
  ShowENPlanWorkItem, ShowENEstimateItemType, ShowENEstimateItem,
  ShowENTransformerType, ShowENOwner, ShowENBelonging, ShowENPlanWorkType,
  ShowENPlanWorkState, ShowENSubstationType, ShowENTransformer, ShowENEPRen,
  ShowENPlanWorkMoveReason, ShowENPlanWorkMoveHistory, ShowENPlanCorrectReason,
  ShowENPlanCorrectHistory, ShowENDepartmentType, ShowENDepartment,
  ShowENDepartmentOrig, ShowENLine150, ShowENSubstation150, ShowENHumenItem,
  ShowENPlanWorkKind, ShowENPlanWorkForm, ShowENBindingOverOrganization,
  ShowENBindingOver, ShowENPreproductionObject, ShowENTransportDepartment,
  ShowENTransportOrder, ShowENTransportOrderStatus,
  ShowENTransportOrder2TransportItem

// ----------- techCards
//, ShowTKTechCard
//,ShowTKClassificationType
    , ShowTKElementType, ShowTKTechCardAttachment, ShowTKPosition,
    ShowTKPositionHistory, ShowTKTransportHistory, ShowTKElement, ShowTKDevice,
    ShowTKElement2TechCard, ShowTKMeasurement, ShowTKInstrument, ShowTKMechanism,
    ShowTKMaterials, ShowTKTransport, ShowTKClassificationType,
    ShowTKTechCardSource, ShowTKOperation, ShowTKFinType,
    ShowTKElementToFinCollection, ShowTKTransportMark, ShowTKTransportReal,
    ShowTKTransportType, ShowTKFuelType, ShowTKTransportStatus,
    ShowENTransportItem, ShowENPosition, ShowENWorker, ShowENManningTable,
    ShowFINMol, ShowFINMaterials, ShowFINMaterialsData, ShowFINMaterialsStatus,
    ShowENActStatus, ShowENAct, ShowENActForRecyclableMaterials,
    ShowENAct2ENPlanWork, ShowENRoadType, ShowENRoadTypeData, ShowFINWorker,
    ShowENDistanceType, ShowENDistance, ShowENRZAObjectType, ShowENRZAObject,
    ShowENSDTUObjectType, ShowENSDTUObject, ShowFINExecutor, ShowFINExecutorTree,
    ShowCNSubsystemType, ShowCNPack2PlanWork, ShowENEstimateItemKind,
    ShowENWorkOrderStatus, ShowENWorkOrder, ShowFINDocType
//,ShowFINDoc2WorkOrder
    , ShowENLineCable, ShowENDeliveryKind, ShowENDeliveryTime,
    ShowENDeliveryOrder, ShowENDeliveryTimePlan, ShowENBuilderObject,
    ShowENBuilderObjectType, ShowENMetrologyObject, ShowENMetrologyCounter,
    ShowENMetrologyDeviceType, ShowENMetrologyDevice, ShowENMOL2PlanWork,
    ShowTKTechCardSourceKoef, ShowENPlanWorkItem2TKKoef, ShowENHumenItemKind,
    ShowENAct2Humen, ShowENAct2Transport, ShowENSITEquipment,
    ShowENSITEquipState, ShowENSITEquipType, ShowENSITFeature,
    ShowENSITFeatureHistory, ShowENSITFeatureType, ShowENIzolationObjectType,
    ShowENIzolationObject, ShowENPurchasesObjectReason, ShowENPurchasesObject,
    ShowENPurchasesNoObject, ShowENTransformerObject, ShowENOperativeObject,
    ShowENServicesObject, ShowENServicesCalculation, ShowENServicesConnection,
    ShowENServicesSales, ShowENServicesRent, ShowENServicesProject,
    ShowENEstimateItemStatus, ShowENRecordPointProm, ShowENRecordPointByt,
    ShowENEstimateItemStatusHistory, ShowFINMolData, ShowFINMolType,
    ShowFINDoc2MolData, ShowENOtherObjectType, ShowENOtherObject,
    ShowENPurchasesNoObjectType, ShowENTravelSheetType, ShowENTravelWorkMode,
    ShowENTravelSheetStatus, ShowENTravelSheet, ShowENTravelSheetItemKind,
    ShowENTravelSheetItemStatus, ShowENTravelSheetItem, ShowENTravelSheetFuel,
    ShowENTravelSheetFuelRemains, ShowENTransportRealFuelRemains,
    ShowENManagement, ShowENPlanWorkReasonType, ShowENPlanWorkReason


// Request begin
    , ShowRQPackingList, ShowRQPackingListStatus, ShowRQPackingListItemType,
    ShowRQPackingListItem, RQPackingListController, ShowRQOrderType,
    ShowRQOrderKind, ShowRQOrderStatus, ShowRQOrderForm, ShowRQOrderResource,
    ShowRQOrder, ShowRQOrderStatusHistory, ShowRQOrg, ShowRQDDSCodes,
    ShowRQOrderItem, ShowRQMaterials, ShowRQMaterialsGroup,
    ShowRQMaterials2TKMaterials, ShowRQMeasurement,
    ShowRQOrderItem2ENEstimateItem, ShowRQOrderItem2ENEstimateItemStatus,
    ShowRQInvoiceStatus, ShowRQInvoice, ShowRQInvoiceItem, ShowRQBillStatus,
    ShowRQBill, ShowRQBillItem, ShowRQPayDoc, ShowRQPayDocItem,
    ShowRQFKOrderKind, ShowRQFKOrderStatus, ShowFINDoc2FKOrder, ShowRQFKOrder,
    ShowRQFKOrderItem, ShowRQFKOrder2FKOrderType,
    ShowRQFKOrderItem2ENEstimateItem, ShowRQInvoiceItem2ENEstimateItem,
    ShowRQInvoiceItem2ENEstimateItemStatus, ShowRQFKOrderItem2EstimateItemStatus,
    ShowRQFKOrderItemRemainder, ShowENEstimateItem2ENEstimateItem,
    ShowRQFKRemainder2EstimateItem


// ----------------
// SC
    , ShowSCInvoice, ShowSCOrder, ShowSCCounterStatus, ShowSCCounter,
    ShowSCUsageInputStatus, ShowSCUsageInput, ShowSCUsageInputItemKind,
    ShowSCUsageInputItem, ShowSCUsageInputItemOZ, ShowSCUsageInputItemOZInfo

// ------------------------
    , ShowNakl_List, ShowDov_List, ShowParty, ShowTmesure_unit, ShowTmaterial,
    ShowRQOrgBank, ShowRQOrgRschet, ShowAGSpecification, ShowAGSpecificationItem,
    ShowAGAgree, ShowRQPItem2BItem2OItem, ShowENMark, ShowENPlanTrans,
    ShowENPlanWriteOffProtection, ShowENContract, ShowENContractItem

/////
    , ShowTKTimeuse, ShowENSizObject, ShowENSzBrigade
/////
    , ShowENAutoTires, ShowENAutoTiresHistory, ShowENTiresInstallPlaces,
    ShowENAccumulators, ShowENAccumulatorsHistory, ShowSCMol,
    ShowENSizMaterials2TKMaterials, ShowENHighVoltageSellType,
    ShowENHighVoltageSell, ShowENDisconnectorType, ShowENDisconnectorDriveType,
    ShowENDisconnector, ShowENLoadSwitchType, ShowENLoadSwitchDriveType,
    ShowENLoadSwitch, ShowENFuseType, ShowENFuse, ShowENInsulatorType,
    ShowENInsulator, ShowENArresterType, ShowENArrester, ShowENArresterSite,
    ShowENCurrentTransformerType, ShowENCurrentTransformer, ShowENMarkBus,
    ShowENBus, ShowENLowVoltBoardType, ShowENLowVoltBoard, ShowENBranchType,
    ShowENConsumerCategory, ShowENPanelType, ShowENPanel, ShowENBranch,
    ShowENContactBreakerType, ShowENContactBreaker, ShowENAutomatType,
    ShowENAutomat, ShowENMeasurDeviceType, ShowENScale, ShowENMeasurDevice,
    ShowENWarrant, ShowENWarrantStatus, ShowENSpravMol, ShowENPlanOperative,
    ShowENActIncome, ShowENActIncomeStatus, ShowTKFINWorkType, ShowTKAXMaterials,
    ShowFKAccount, ShowENServicesFromSideObject, ShowENMol, ShowENMolType,
    ShowENMolStatus, ShowBalans, ShowKau, ShowCountry, ShowProvince, ShowRegion,
    ShowCityType, ShowCity, ShowStreetType, ShowStreet, ShowDistrict,
    ShowENTransformerChange, ShowENDisconnectorChange, ShowENLoadSwitchChange,
    ShowENFuseChange, ShowENInsulatorChange, ShowENArresterChange,
    ShowENCurTransformerChange, ShowENBusChange, ShowENMeasurDevChange,
    ShowENContBreakChange, ShowENAutomatChange, ShowEquipChangeWorker,
    ShowENTrafficGPS, ShowENInvestProgramGroups, ShowENAgreeJoint,
    ShowENLockType, ShowENFencing, ShowENSafetyPrecautions, ShowENCargoPlan,
    ShowRQFKBSDescription, ShowENTechConditionsObjects,
    ShowENTechConditionsServices, ShowCNPack, ShowENActIncomeTechConditions,
    ShowENActInTechCond2ENAct, ShowENContragent, ShowENTechCondResponsibles,
    ShowENTransportRoute, ShowENTransportRoute2RQFKOrder, ShowENDestinationPoint,
    ShowENDestinationOrder, ShowENDestinationPoint2Point, ShowENCargoObjectPlan,
    ShowENResponsibles, ShowFINContracts, ShowRQTransportInvoice,
    ShowRQTransportInvoiceItem, ShowOSMol, ShowOSGr, ShowOSIst, ShowOSKlass,
    ShowOSPodr, ShowOSPrivat, ShowOSSubsch, ShowOSVid, ShowOSGrNalog,
    ShowOSMetAm, ShowBookKind, ShowExpenseItem, ShowAcc_Obj_Types,
    ShowTrade_TaxWage, ShowPayForm, ShowENReconstrModernOZ,
    ShowENVoltageClass, ShowENSubst150Cell,
    ShowENSubst150PowerTrans, ShowENSubst150OwnTrans, ShowENSubst150VoltTrans,
    ShowENSubst150Switch, ShowENSubst150CurrentTrans, ShowENSubst150Disconnector,
    ShowENSubst150Discharger, ShowENSubst150ShortCircuiter,
    ShowENSubst150Separator, ShowENSubst150Battery,
    ShowENServicesObject2PaymentSchedule, ShowENStandardConst,
    ShowENStandardConstEntry, ShowFINExecutorType, ShowFINExecutor2Plan,
    ShowENReconstrModernOZStatus, ShowENCustomer04, ShowENBranch2Customer04,
    ShowENFiderGuage, ShowENAntsapf, ShowENAntsapfPosition, ShowRQStorage,
    ShowRQStorageZone, ShowRQStorageZone2RestPurpose, ShowRQStorage2ENMol,
    ShowRQStorageZone2TKMaterials, ShowRQStorage2ENMol2Zone,
    ShowENTransportTemperature, ShowENConnectionKind, ShowENConnectionTariff,
    ShowENConnectionTariffEntry, ShowENConnectionLevel, ShowENSettleType,
    ShowENPowerReliabilityCategory, ShowENConnectionPowerPoint,
    ShowENConnectionPhasity, ShowRQOrderItemTypePay, ShowRQTypePayValue,
    ShowRQOrg2TypePay, ShowRQPlanPayItemStatus, ShowRQPlanPayItemSecond,
    ShowRQPlanPayItemFirst, ShowRQPlanPay, ShowRQPlanPayStatus,
    ShowENBankingDetails, ShowTKMaterials2RQContracts, ReportFuelDistribution,
    ReportFuelSpendingByCar, ShowScanCounters, ReportConnectionFactCost,
    ReportUsingOfMeansByObjectsForConnection,
    ReportAmountOfSignedAgreementsForConnection, reportOnDohovoryPidryadu

/// DocFlow
    , DFMain, ShowDFTaskSPA, ServicesConsumerManagement, ShowUser2Staffing,
    ShowDFDocServicesConsumer, ShowDFJobStatus, ShowDFDocMovement,
    ShowDocumentManagement, ShowDFServicesList, ShowDFInfoSources,
    ShowDFPackType, ShowDFDepartment, ShowDFServicesRegistry, ShowDFDoc,
    ShowDFDocKind, ShowDFDocType, ShowDFDocSubType, ShowDFDocClassification,
    ShowDFDocAppeal, ShowDFDocOutbox, ShowDFDocSupplyEE, ShowDFDocDecree,
    ShowDFDocOrder, ShowDFDocJourney, ShowDFDocApplication, ShowDFDocServiceNote,
    ShowDFDoc2Worker, ShowDFWorker, ShowDFOrganization,
    ShowDFDoc2WorkerDirection, ShowDFDocForm, ShowDFDocAttachmentStatus,
    ShowDFDocAttachment, ShowDFResult, DFTaskSPAManagement, ShowDFDocAgreement,
    ShowDFDocLocation, ShowDFRoute2DFDocType, ShowDFRouteType, ShowDFRouteKind,
    ShowDFRouteItem, ShowDFRoute, ShowDFDocRoute, ShowDFStampType,
    ShowDFDocPrefix, ShowDFOrdersCatalog, DFOrdersCatalogManagement,
    ShowDFDocInBox
/// end of DocFlow units

/// WorkFlow
    , ShowWFAttachmentStatus, ShowWFAttachment, ShowWFProcess,
    ShowWFProcessMovement, ShowWFGroup2State, ShowWFGroupAction, ShowWFGroup,
    ShowWFSubsystem, ShowWFBaseProcess, ShowWFBaseProcessVersion,
    ShowWFBaseProcessItems, ShowWFProcessState, ShowWFProcessRole,
    ShowWorkFlowManagement, ShowWFProcessRole2User, ShowWFSubsystem2User,
    ShowWFDepartment2User, ShowWFDepartment, ShowWFBaseProcessType,
    ShowWFGroupType, ShowWFProcessStatus, ShowWFPackStatus, ShowWFPackType2User,
    ShowWFPackType, ShowWFBaseProcessLinkType, ShowWFBaseProcessLink,
    ShowWFPack2ServicesObject, reportDocumentMovement,
    reportDSTNumberConsumerAppeals

///  end or WorkFlow

/// EnergyPro
    ,frmRunReportUnit, frmEPRep_MarkerUnit
///  end of EnergyPro units

///  CallCenter
    , ShowCCDamagedEquipment, ShowCCDamage32Class, ShowCCCall, ShowCCCallType,
    ShowCCCallSubType, ShowCCCallInBrief, ShowCCCallInBrief32, ShowCCCallHistory,
    ShowCCCallComment, ShowCCCallResult, ShowCCNode, ShowCCDamageLog,
    ShowCCDamageRZA, ShowCCDamageReason, ShowCCOutageNotice, ShowCCDamageHistory,
    ShowCCDamageComment, ShowCCPeriod, frmCCRunReportUnit,
    frmCCRep_SAIDISAIFIUnit
///  end of CallCenterController units
    , ReportCallCenterPlannedWorksUnit, ShowENInvestProgram,
    ShowENIPImplementationType, ShowENCheckpointPassList, ShowENCheckpoint,
    ShowENIP, ShowENIPFinancing, ShowENMethodExecuteWork, ShowENIPPurposeProgram,
    ReportServicesFactPayments, ReportPmmNeeded, ShowENWorkOrderByt,
    ShowENWorkOrderBytType, ShowENWorkOrderBytStatus, ShowENSite, ShowRQBudget,
    ShowRQBudgetItem, FINExecutorController, ShowENPlanGraphFinancingFuel,
    ShowScanCountersForEnWorkOrderBytItem, ShowFINServicesObject, BeanShell,
    ShowENFuelDistributionSheet, ShowTKReplaceCounterKind,
    ShowENCoefficientExecPlan, ShowRQOrgEmails, EditCheckpointRegistration,
    ShowRQCentralExportGraph, ShowRQCentralExportGraphItem,
    ShowRQCentralExportGraphType, ShowRQCentralExportAnalyse, ShowRQPlanPurchase,
    ShowRQPurchaseItem, ShowRQSpravDKPP, ShowRQPurchaseItemType,
    ShowRQSpravDKPPItem, ShowRQPurchaseItemTender2RQPurchaseItem,
    ShowRQPurchaseItem2EstimateItem, ShowENPost04OKSN, ShowENPost10OKSN,
    ShowTKTariff2TKPosition, ShowTKSourceTariff, ShowAXDimensionsKS,
    ShowAXRassetTable, ShowENSettings, ShowENBudgets2Nomenclatures,
    ShowRQActCounterExpertise, ShowRQPurchaseItemAbstractSum, ShowENSheets4SO,
    ShowFKTrans2AXTrans
//,ShowENActPostingKind
    , ShowENCoefficientQualityStandards, reportTransportPlan,
    ReportWriteOffMaterialsByCar, ShowENBuilding, ShowENExecutor,
    ShowDFLettersLists, SystemOperationsController, ReportRegisterOrders
    , ReportWorkTeamsByDates, ShowRegulatoryAssetBaseCalculation;

type
  TfrmMain = class(TForm)
    ElegantMDI1: TElegantMDI;
    sbMain: TStatusBar;
    HTTPRIOAuth: THTTPRIO;
    Timer1: TTimer;
		ApplicationEvents1: TApplicationEvents;
    EurekaLog1: TEurekaLog;
    xmlLoc: TXMLDocument;
    MainMenuNet: TMainMenu;
    N5: TMenuItem;
    aboutMenuItem: TMenuItem;
    N7: TMenuItem;
    N1: TMenuItem;
    N041: TMenuItem;
    N6101: TMenuItem;
    N2: TMenuItem;
    N4: TMenuItem;
    mniENPost: TMenuItem;
    mniENPostType: TMenuItem;
    mniENHookType: TMenuItem;
    mniENDisconnectorType: TMenuItem;
    mniENWires: TMenuItem;
    mniENWiresType: TMenuItem;
    mniENWiresCut: TMenuItem;
    miENPlanWork: TMenuItem;
    miENPlanWorks: TMenuItem;
    N3: TMenuItem;
    N17: TMenuItem;
    N18: TMenuItem;
    mniENTransformerType: TMenuItem;
    N20: TMenuItem;
    N21: TMenuItem;
    N22: TMenuItem;
    N23: TMenuItem;
    N351501: TMenuItem;
    N351502: TMenuItem;
    N24PlansDeployedWork: TMenuItem;
    N27: TMenuItem;
    N28: TMenuItem;
    miNormTask: TMenuItem;
    N33: TMenuItem;
    miTransport: TMenuItem;
    N34: TMenuItem;
    N35: TMenuItem;
    N36: TMenuItem;
    N37: TMenuItem;
    N38: TMenuItem;
    N39: TMenuItem;
    N31: TMenuItem;
    N41: TMenuItem;
    miAnalysysMaterials: TMenuItem;
    N32: TMenuItem;
    N42: TMenuItem;
    miRZAObjects: TMenuItem;
    miSDTUObjects: TMenuItem;
    N16SearchPlansNormativeMaterials: TMenuItem;
    miMaterialsParametersEdit: TMenuItem;
    N43MaterialsPlansMonth: TMenuItem;
    miMaterials: TMenuItem;
    N30: TMenuItem;
    N046101: TMenuItem;
    miAddition4: TMenuItem;
    miTransportObjects: TMenuItem;
    N44: TMenuItem;
    N45: TMenuItem;
    N46: TMenuItem;
    N47: TMenuItem;
    N48: TMenuItem;
    miTMCRaznaryadkapodr: TMenuItem;
    miTMCRaznaryadkaMOL: TMenuItem;
    miActRegistry: TMenuItem;
    miMetrology: TMenuItem;
    miIzolation: TMenuItem;
    N49: TMenuItem;
    N51: TMenuItem;
    N52: TMenuItem;
    miAddition3PlanLoading: TMenuItem;
    miAddition3PlanLoadingYear: TMenuItem;
    miAddition3PlanLoading_CurrentPlans: TMenuItem;
    miAddition3PlanLoading_PlansPlans: TMenuItem;
    miAddition3PlanLoading_Fact: TMenuItem;
    miAddition3_migr_hum: TMenuItem;
    miAddition3_migr_hum_CurrentPlans: TMenuItem;
    miAddition3_migr_hum_ZadanPlan: TMenuItem;
    miAddition3_migr_hum_ZadanFact: TMenuItem;
    miAdition3_sht: TMenuItem;
    miAdition3_sht_CurrentPlan: TMenuItem;
    miAdition3_sht_ZadanPlan: TMenuItem;
    miAdition3_sht_ZadanFact: TMenuItem;
    N53: TMenuItem;
    N54: TMenuItem;
    N55: TMenuItem;
    miENObjects: TMenuItem;
    N6102: TMenuItem;
    N351503: TMenuItem;
    N610041: TMenuItem;
    N56: TMenuItem;
    N57: TMenuItem;
    N58: TMenuItem;
    N59: TMenuItem;
    N60: TMenuItem;
    N61: TMenuItem;
    N62: TMenuItem;
    N63: TMenuItem;
    N64: TMenuItem;
    miRepPercentPrem: TMenuItem;
    N65: TMenuItem;
    miPurchasesBinding: TMenuItem;
    N66: TMenuItem;
    N67: TMenuItem;
    miTMCRaznaryadkaMOLinCol: TMenuItem;
    miTMCRaznarMOLnotColnotObj: TMenuItem;
    miRequest: TMenuItem;
    miRequestDepartment: TMenuItem;
    miRequestResponsybility: TMenuItem;
    miRequestHOE: TMenuItem;
    miMetrologyPlansEdit: TMenuItem;
    N68: TMenuItem;
    N69: TMenuItem;
    N410miAddition4Zbyt: TMenuItem;
    miMetrologyObjectsPlans: TMenuItem;
    N70: TMenuItem;
    miENServicesObjects: TMenuItem;
    miAvrVneplanWork: TMenuItem;
    miReservedMaterials: TMenuItem;
    N72: TMenuItem;
    miraznarPoPodrPlani: TMenuItem;
    miraznarPoPodrZayavka: TMenuItem;
    miraznarNaSkladPlani: TMenuItem;
    mizarnarNaskladZayavka: TMenuItem;
    miraznarPoMolPlani: TMenuItem;
    miraznarPoMolZayavki: TMenuItem;
    miraznarkonsolPlani: TMenuItem;
    miraznarkonsolZayavki: TMenuItem;
    N73: TMenuItem;
    N74: TMenuItem;
    N75: TMenuItem;
    N76: TMenuItem;
    N77ComplianceNamesMaterialsComplex: TMenuItem;
    miAddition3PlanLoading_YearPlans: TMenuItem;
    mizayavkaDecoding: TMenuItem;
    N411miAddition4Pripisi: TMenuItem;
   // miAddition3PlanLoading: TMenuItem;
    miAddition3PlanLoading_YearPlansYear: TMenuItem;
    miAddition3PlanLoading_CurrentPlansYear: TMenuItem;
    miPayDoc: TMenuItem;
    N78: TMenuItem;
    miAnalyseLoadWorker: TMenuItem;
    Pfzdr1: TMenuItem;
    miRQBills: TMenuItem;
    miRQTransportInvoices: TMenuItem;
    miRQFKOrders: TMenuItem;
    miFKOrderOut: TMenuItem;
    miFKOrderIn: TMenuItem;
    N412miAddition4Transport: TMenuItem;
    N79MaterialsGroupsAnnualPlans: TMenuItem;
    N80: TMenuItem;
    N81: TMenuItem;
    miAddition3All: TMenuItem;
    miAddition3TechPers: TMenuItem;
    mmmm: TMenuItem;
    mizbrozgornpers: TMenuItem;
    mizbytfactzagrpers: TMenuItem;
    mizbytprognozmonth: TMenuItem;
    mizbytprognozyear: TMenuItem;
    mizbytprognozmonthYearplan: TMenuItem;
    mizbytprognozmonthPotochnplan: TMenuItem;
    mizbytfactzagrpersPlan: TMenuItem;
    mizbytfactzagrpersFact: TMenuItem;
    mizbrozgornpersPlan: TMenuItem;
    mizbrozgornpersFact: TMenuItem;
    miHOEAuto: TMenuItem;
    N82: TMenuItem;
    N413: TMenuItem;
    miNOPlanned: TMenuItem;
    miGrRepPlani: TMenuItem;
    miGrRepAkti: TMenuItem;
    miGrRepMaterials: TMenuItem;
    migrRepRaznar: TMenuItem;
    migrRepPersonal: TMenuItem;
    miGrRepSystem: TMenuItem;
    miGrRepOMTS: TMenuItem;
    miGrRepOrdermaterial: TMenuItem;
    N26: TMenuItem;
    N84: TMenuItem;
    N83FulfillmentPlansSectionApplications: TMenuItem;
    N85AnalysisWorkObject: TMenuItem;
    mizbytprognozyear_yearplan: TMenuItem;
    mizbytprognozyear_monthplan: TMenuItem;
    miAGSpecification: TMenuItem;
    miEstimate2Estimate: TMenuItem;
    miStateMaterial: TMenuItem;
    disparityMatOrderBill: TMenuItem;
    N87NumberScheduledWorkSubscribers_prom: TMenuItem;
    miTransportPlans: TMenuItem;
    miPhysVolumes: TMenuItem;
    miMterologySubstation: TMenuItem;
    miEB: TMenuItem;
    N88ExecutionRepairVerificationmetrologicalServices: TMenuItem;
    N91: TMenuItem;
    N610042: TMenuItem;
    miUseMaterialFact: TMenuItem;
    N92: TMenuItem;
    miMaterials2Contracts: TMenuItem;
    miMotionOfPartyOrder: TMenuItem;
    miImplementationOrder: TMenuItem;
    N94ApplicationsTermsSuppliersDelivery: TMenuItem;
    N95: TMenuItem;
    miTransportMain: TMenuItem;
    miTravelSheet: TMenuItem;
    N96ListMaterialsManufacturingApplications: TMenuItem;
    N99AnalysisWorkPerformance: TMenuItem;
    miFuelReport: TMenuItem;
    miTransportReports: TMenuItem;
    mivedUsetransport: TMenuItem;
    miObjectWorker: TMenuItem;
    miMaterialsfromAct: TMenuItem;
    minewzvit: TMenuItem;
    miBrigade: TMenuItem;
    GVV1: TMenuItem;
    GVV2: TMenuItem;
    N93: TMenuItem;
    miTransportReal: TMenuItem;
    miAutoTires: TMenuItem;
    miAccumulators: TMenuItem;
    miUsageInput: TMenuItem;
    miSCCounterMove: TMenuItem;
    miExecutedWorksFromAct: TMenuItem;
    miFKOrderOut2: TMenuItem;
    miPlanWithoutMOL: TMenuItem;
    miRepOrderByDatePay: TMenuItem;
    mirepusetransp2: TMenuItem;
    miSCCounterReport: TMenuItem;
    miEquipments: TMenuItem;
    miEquipment: TMenuItem;
    miEquipmentRepair: TMenuItem;
    miCheckplaseInstalllCounters: TMenuItem;
    miPersonatTabel: TMenuItem;
    miWorksExecuted: TMenuItem;
    miworkMonthFact: TMenuItem;
    mifrmCountWorkOrderByRezervedMaterials: TMenuItem;
    miRestocking: TMenuItem;
    miRepSz: TMenuItem;
    miCalculation: TMenuItem;
    miSprMol: TMenuItem;
    miServicesCalculation: TMenuItem;
    miOperativeObject: TMenuItem;
    miOperativeObjectPlan: TMenuItem;
    miOperativeObjectAct: TMenuItem;
    miRepByTD1History: TMenuItem;
    miAutoTiresSheetDistance: TMenuItem;
    miTiresPurchase: TMenuItem;
    miWorkedTimeByHuman: TMenuItem;
    miFKOrderOutOperative2Tranzit: TMenuItem;
    miFKOrderOutE2E: TMenuItem;
    N100UseAkum: TMenuItem;
    N86: TMenuItem;
    miFKOrderChangeMeasurement: TMenuItem;
    N101: TMenuItem;
    miFKOrderLoadExplMBP: TMenuItem;
    miFKOrderLoadExplMNMA: TMenuItem;
    mniWriteOffProtection1: TMenuItem;
    N102: TMenuItem;
    miFKOrderMBP: TMenuItem;
    miFKOrderMNMA: TMenuItem;
    miPlanedServices: TMenuItem;
    miNoPlanedServices: TMenuItem;
    miFromSideObjects: TMenuItem;
    miBillsTmc: TMenuItem;
    miBillsServices: TMenuItem;
    mniN104: TMenuItem;
    mniGPS1: TMenuItem;
    mnirepServices: TMenuItem;
    mnirepServicesContragent: TMenuItem;
    miENMol: TMenuItem;
    miInterface: TMenuItem;
    miChangeSkin: TMenuItem;
    miIsSkinsActive: TMenuItem;
    mniSlash1: TMenuItem;
    N103: TMenuItem;
    N104: TMenuItem;
    N105: TMenuItem;
    miRQFKOrderServicesFromSide: TMenuItem;
    N106: TMenuItem;
    N107: TMenuItem;
    miTransportManagement: TMenuItem;
    N108: TMenuItem;
    N109: TMenuItem;
    N110: TMenuItem;
    N111provisionServicesPartySelectedDate: TMenuItem;
    miCargoPlans: TMenuItem;
    mniAnnexOK: TMenuItem;
    N112: TMenuItem;
    miPriconnection: TMenuItem;
    miContractProject: TMenuItem;
    miContractRealization: TMenuItem;
    N113: TMenuItem;
    miFactTransport: TMenuItem;
    miENTechCondResponsibles: TMenuItem;
    N115: TMenuItem;
    miENTechConditionsObjects: TMenuItem;
    N414: TMenuItem;
    N415: TMenuItem;
    miUsedMaterials: TMenuItem;
    N114: TMenuItem;
    miFKOrderOutReturn: TMenuItem;
    miFactProtection: TMenuItem;
    miFactProtectionSubstation: TMenuItem;
    miFactProtectionBrigade: TMenuItem;
    miOMTSResponsibles: TMenuItem;
    miCargoObjectPlan: TMenuItem;
    miTransportRoute: TMenuItem;
    miCargoObjectPlans: TMenuItem;
    miExecutedContract: TMenuItem;
    N116ConsolidatedActamReportperMonth: TMenuItem;
    N117InfoMetersAccountValidityPeriodAccuracyClass: TMenuItem;
    miServicesOutBuh: TMenuItem;
    CountersMonth: TMenuItem;
    miSale: TMenuItem;
    miSaleProducts: TMenuItem;
    miFKOrderOutTranzit2Operative: TMenuItem;
    miReconstrModernizacOZ: TMenuItem;
    miSubst150Equipment: TMenuItem;
    miENSubst150PowerTransType: TMenuItem;
    miENSubst150OwnTransType: TMenuItem;
    miENSubst150VoltTransType: TMenuItem;
    miENSubst150SwitchType: TMenuItem;
    miENSubst150CurrentTransType: TMenuItem;
    miENSubst150DisconnectorType: TMenuItem;
    miENSubst150DischargerType: TMenuItem;
    miENSubst150BatteryType: TMenuItem;
    mifsGSM: TMenuItem;
    miENSubst150BatteryChargerType: TMenuItem;
    N118: TMenuItem;
    miReportLastBuyMaterial: TMenuItem;
    miENSubst150ShortCircuiterType: TMenuItem;
    miENSubst150SeparatorType: TMenuItem;
    N119: TMenuItem;
    N120: TMenuItem;
    miENStandardConst: TMenuItem;
    miPlanFactUseGSM: TMenuItem;
    migsmpf: TMenuItem;
    miFKOrderGift: TMenuItem;
    miGiftObjects: TMenuItem;
    miStorage: TMenuItem;
    miRQStorage: TMenuItem;
    miRQStorageZone: TMenuItem;
    miRestMaterialByPlaces: TMenuItem;
    N121miAddition4Transport2: TMenuItem;
    N122ReportTermsProductSalesBuffet: TMenuItem;
    miN4dodat4: TMenuItem;
    miPaymentContracts: TMenuItem;
    miAverageCountPersonal: TMenuItem;
    miPlanLineRouteForestClear: TMenuItem;
    N123: TMenuItem;
    miFiderGuageFullness: TMenuItem;
    miFKOrderOSExpl: TMenuItem;
    mniSlash2: TMenuItem;
    mniWarehouseMaterialsMovement: TMenuItem;
    mniTransportTemperature: TMenuItem;
    miSubstation04PowerReserve: TMenuItem;
    N14: TMenuItem;
    miGeoCoordinates: TMenuItem;
    miFKOrderOSMovement: TMenuItem;
    miEnePlanZagruzkaAndCount: TMenuItem;
    miFactZagrByBrigadeHMVE: TMenuItem;
    miENPlanWorksNew: TMenuItem;
    miENPlanWorksSimple: TMenuItem;
    miENPlanWorksDetailed: TMenuItem;
    N15: TMenuItem;
    miIsPlansModeSelect: TMenuItem;
    miContractRealizationStandart: TMenuItem;
    miContractRealizationNoStandart: TMenuItem;
    miEnePlanZagruzkaAndCountTech: TMenuItem;
    N124: TMenuItem;
    miENNomenclaturesOperative: TMenuItem;
    mniCompareMaterialPrice: TMenuItem;
    miFKOrderToStorage: TMenuItem;
    miPrintBarCodeByMaterials: TMenuItem;
    miAVRPlan: TMenuItem;
    miN125: TMenuItem;
    misetUnSetGlobus2transportreal: TMenuItem;
    miAccess2Enelement: TMenuItem;
    N125: TMenuItem;
    miTransportLoad: TMenuItem;
    miDriversLoad: TMenuItem;
    miFKOrderZoneChanging: TMenuItem;
    miZKU: TMenuItem;
    miServicesConnections: TMenuItem;
    N126: TMenuItem;
    miWorkedTimeInActs: TMenuItem;
    miFile: TMenuItem;
    miExit: TMenuItem;
    NLine6_10kV: TMenuItem;
    NLine04kV: TMenuItem;
    miN127Serv: TMenuItem;
    miRestockingObjects: TMenuItem;
    miRestockingAVR16: TMenuItem;
    miRepByTD1History2: TMenuItem;
    miPaymentRegistry: TMenuItem;
    N127: TMenuItem;
    N128: TMenuItem;
    miPaymentRegistry2: TMenuItem;
    miPaymentRegistryService: TMenuItem;
    miRepByTD1History2AndService: TMenuItem;
    miRQOrg2TypePay: TMenuItem;
    N129: TMenuItem;
    N130: TMenuItem;
    N131: TMenuItem;
    mniPlanPay: TMenuItem;
    miOrderInfo: TMenuItem;
    N132: TMenuItem;
    miFuelDistribution: TMenuItem;
    miInventary_list: TMenuItem;
    miLine132: TMenuItem;
    miFuelSpendingByCar: TMenuItem;
    miRQAllocationList: TMenuItem;
    mipersonalcarddriver: TMenuItem;
    JvZlibMultiple1: TJvZlibMultiple;
    N133: TMenuItem;
    miAmountOfSignedAgreementsForConnection: TMenuItem;
    miUsingOfMeansByObjectsForConnection: TMenuItem;
    miOrderOutFuel: TMenuItem;
    mniChartpayment: TMenuItem;
    N134: TMenuItem;
    N135AverageShutdownDuration: TMenuItem;
    N136: TMenuItem;
    miRepByTD1History22: TMenuItem;
    miServicesPlanedPayAndWorks: TMenuItem;
    N137: TMenuItem;
    N20141: TMenuItem;
    N138: TMenuItem;
    miDocFlowServices: TMenuItem;
    miDocAppeal: TMenuItem;
    N139: TMenuItem;
    miInvestProgram: TMenuItem;
    N141: TMenuItem;
    N142StagesFinancingInvestPrograms: TMenuItem;
    miInvestVikonannya: TMenuItem;
    miInvestVikonannyaExtended: TMenuItem;
    miServicesRegistry: TMenuItem;
    miDocSupplyEE: TMenuItem;
    miInvestpayment_with_monthsrez2: TMenuItem;
    miRemainder: TMenuItem;
    miDocFlow: TMenuItem;
    N143TasksPerformingWorks: TMenuItem;
    miPurchases: TMenuItem;
    N145ReportCompetitiveProcurement: TMenuItem;
    Timer2: TTimer;
    miENCheckpointPass: TMenuItem;
    N146: TMenuItem;
    miAppealRegistrationByDate: TMenuItem;
    miAppealRegistrationByQuarter: TMenuItem;
    miAppealRegistrationByRensAndDate: TMenuItem;
    miCheckpoints: TMenuItem;
    miIP: TMenuItem;
    miReportServicesFactPayments: TMenuItem;
    miReportPmmNeeded: TMenuItem;
    miNpz_otp_pl_fact: TMenuItem;
    miAddition3PlanLoadingWithZadanPlan: TMenuItem;
    miWorkOrderByt: TMenuItem;
    N147: TMenuItem;
    miPlanWorkBatch: TMenuItem;
    N29: TMenuItem;
    HTTPRIOConfig: THTTPRIO;
    miBudget: TMenuItem;
    miReloadFinExecutor: TMenuItem;
    HTTPRIOFINExecutor: THTTPRIO;
    N148InformationMetersFromBilling: TMenuItem;
    N149DebitDebtInformationBilits: TMenuItem;
    N210DebitDebtInformationBilits2months: TMenuItem;
    N150InformationControllersBilling: TMenuItem;
    TimerCheckVer: TTimer;
    N151NumberLunarPlansWithObjectsElectricNetworks: TMenuItem;
    N152GraphPlannedPayPMM: TMenuItem;
    miDocumentManagement: TMenuItem;
    miRoutes: TMenuItem;
    miTravelsheetDrivers: TMenuItem;
    N153: TMenuItem;
    miRQFKOrderAvarIn: TMenuItem;
    miRQFKOrderAvarOut: TMenuItem;
    HTTPRIOUserMembership: THTTPRIO;
    miPurchasesNoObjectAVZ: TMenuItem;
    N154: TMenuItem;
    miGSMReportByPeriod: TMenuItem;
    N155: TMenuItem;
    miENNormativeVolumeAVZ: TMenuItem;
    miServicesConsumerManagement: TMenuItem;
    miReportDodatok3YearSumZbyt: TMenuItem;
    miBeanShell: TMenuItem;
    miReportDodatok3YearSum: TMenuItem;
    miOrderAVZ: TMenuItem;
    N157ABBNeedforPurchases: TMenuItem;
    miGSMLimitReport: TMenuItem;
    N158: TMenuItem;
    miENFuelDistributionSheet: TMenuItem;
    mniRQPackingListCounters: TMenuItem;
    miworkMonthFactZbyt: TMenuItem;
    miworkMonthFactMetrology: TMenuItem;
    N159Task: TMenuItem;
    miWorksZbyt: TMenuItem;
    mireportServicesLicenz: TMenuItem;
    mistrCategoryWorkTKPosition: TMenuItem;
    mireportCategoryWorkTKPosition: TMenuItem;
    mireportCategoryWorkTKTechcard: TMenuItem;
    N160ReportConsolidTableSstateExecutionContracts: TMenuItem;
    N161: TMenuItem;
    miConnectionsTariff: TMenuItem;
    miWorkOrderBytRaid: TMenuItem;
    HTTPRIOENDepartment: THTTPRIO;
    miAwardByEnergozbyt: TMenuItem;
    mni_: TMenuItem;
    miAverageAccountingPersonalNPZ: TMenuItem;
    miInventarization: TMenuItem;
    N163: TMenuItem;
    N164: TMenuItem;
    miENFuelSheetVolumes: TMenuItem;
    N165MaterialsForOrderDeliveriesOrderedtenderPrice: TMenuItem;
    N166: TMenuItem;
    miAccountingBrigadeNPZ: TMenuItem;
    prem_new_tehn: TMenuItem;
    N162: TMenuItem;
    N167: TMenuItem;
    prem_new_zbyt: TMenuItem;
    mniReportServicesCountersSentOff: TMenuItem;
    prem_new_hmem: TMenuItem;
	  miOrderCountersServices: TMenuItem;
    N168miReportDodatok3YearSumByMontsPlan: TMenuItem;
    N169miReportDodatok3YearSumZbytByMonthPlan: TMenuItem;
    N170: TMenuItem;
    N171: TMenuItem;
    miDFTaskSPA: TMenuItem;
    miTaskSPA: TMenuItem;
    miServicesRelaxation: TMenuItem;
    miENCottage: TMenuItem;
    miReportExecuteDfWork: TMenuItem;
    miServicesRelaxationReestr: TMenuItem;
    miEnergetic: TMenuItem;
    N71: TMenuItem;
    mniDFRoutes: TMenuItem;
    N174SumIbfoAccommodationServices: TMenuItem;
    N175: TMenuItem;
    miDamageRecovery: TMenuItem;
    miRestsByOrder: TMenuItem;
    miReportPlanWorkItemsRegistry: TMenuItem;
    miServicesConsumerTask: TMenuItem;
    miZbytReportplan: TMenuItem;
    miServicesRent: TMenuItem;
    miReportSubstation: TMenuItem;
    N177_report_protection_tariff: TMenuItem;
    N178Purchases_tmc: TMenuItem;
    N177_bonusList: TMenuItem;
    N178_bonusList: TMenuItem;
    prem_new_zbyt_insp: TMenuItem;
    miWorkByBillingObject: TMenuItem;
    mniChkReg: TMenuItem;
    mienchangepersonbyt_report: TMenuItem;
    sks1: TMenuItem;
    mitest: TMenuItem;
    miLinecentralexp: TMenuItem;
    N177: TMenuItem;
    N178expcentr: TMenuItem;
    reportExportCentralGraph: TMenuItem;
    miAnalyseCentralExport: TMenuItem;
    mniN178: TMenuItem;
    miActsGroupReport: TMenuItem;
    miPlanPurchase: TMenuItem;
    miSepoObjects: TMenuItem;
    miSepoSmartObjects: TMenuItem;
    N178: TMenuItem;
    N179CostWork: TMenuItem;
    miReportCostWork: TMenuItem;
    miSealColors: TMenuItem;
    N179: TMenuItem;
    miSealNames: TMenuItem;
    N6: TMenuItem;
    N8: TMenuItem;
    N9: TMenuItem;
    N10: TMenuItem;
    N11BonnusDrivers: TMenuItem;
    N12DebtReportMore90Days: TMenuItem;
    N901DebitorReportDetailed: TMenuItem;
    miWorkOrderBytControl: TMenuItem;
    N13: TMenuItem;
    N19: TMenuItem;
    miENSettings: TMenuItem;
    N180: TMenuItem;
    N181: TMenuItem;
    miTechnicalManagementOtherReports: TMenuItem;
    N89: TMenuItem;
    miActsForRecyclableMaterials: TMenuItem;
    N90ReportVerificationMeterDate: TMenuItem;
    mniENBudgets2Nomenclatures: TMenuItem;
    miCounterExpertise: TMenuItem;
    miDFOrdersCatalog: TMenuItem;
    miIncomeCountersForExpertise: TMenuItem;
    miOutcomeCountersFromExpertise: TMenuItem;
    miSCRemains: TMenuItem;
    miTUTerms: TMenuItem;
    mItem182: TMenuItem;
    mitemFKtrans2AXTrans: TMenuItem;
    mi182Line: TMenuItem;
    miUndeliveredMaterials2Company: TMenuItem;
    miUnfinishedTasks: TMenuItem;
    miRegisterConsumers12NKRE: TMenuItem;
    miElectricLoadRegimDay: TMenuItem;
    miShiftLines: TMenuItem;
    mniN182: TMenuItem;
    N182: TMenuItem;
    N183AnalyseTVE: TMenuItem;
    N184ChartDeviationsFactsTVENorm: TMenuItem;
    N185ScheduleSAIFIReliabilityIndicator: TMenuItem;
    N186ScheduleSaidiReliabilityIndicator: TMenuItem;
    N187SchedulEjectionComparedSamePeriodLastYear: TMenuItem;
    mniN188: TMenuItem;
    miRQOrg: TMenuItem;
    N192: TMenuItem;
    N1110: TMenuItem;
    N193: TMenuItem;
    N194: TMenuItem;
    N195: TMenuItem;
    N196: TMenuItem;
    N197ActsViolationPREE: TMenuItem;
    N188DebtBalanceBeginningYear: TMenuItem;
    N189IncriminationReceivablesZV20power: TMenuItem;
    miRQOrgBank: TMenuItem;
    mniMaterialsMovement: TMenuItem;
    miGraphRemonti: TMenuItem;
    N198: TMenuItem;
    miGraphStaff: TMenuItem;
    N199: TMenuItem;
    miBindingCounters2ServicesObject: TMenuItem;
    miAnalyseWorkGraph_HOE: TMenuItem;
    SAIDISAIFI1: TMenuItem;
    N200: TMenuItem;
    N201: TMenuItem;
    N202AccrualPaymentPeriod: TMenuItem;
    NAwardGraph: TMenuItem;
    miListPlanNotIP203: TMenuItem;
    miFinContracts: TMenuItem;
    miDelimiter203: TMenuItem;
    miENInspectionSheet: TMenuItem;
    NEmptyMeasurement: TMenuItem;
    N203: TMenuItem;
    N204: TMenuItem;
    miDocAppealRES: TMenuItem;
    HTTPRIODFDepartment: THTTPRIO;
    miPlanLineRouteForestClear_2: TMenuItem;
    NActBytPeriod: TMenuItem;
    pmActWorkflowItog: TMenuItem;
    miJur2Month: TMenuItem;
    mimiDFSprav: TMenuItem;
    miDFServicesSprav: TMenuItem;
    miMetrologyObjectsWriteOffPlans: TMenuItem;
    miMetrologyWriteOff: TMenuItem;
    miMetrologyPlansWriteOffEdit: TMenuItem;
    N205: TMenuItem;
    miServicesQuality: TMenuItem;
    miServicesQualityGeneral: TMenuItem;
    miServicesQualityOutTerm: TMenuItem;
    HTTPRIOENPlanWorkItem2Graph: THTTPRIO;
    N206: TMenuItem;
    miResponseToAppeals: TMenuItem;
    mireportPlanworkItemGraph: TMenuItem;
    miAppealsByClassification: TMenuItem;
    N207: TMenuItem;
    miOutBoxRegistry: TMenuItem;
    mniFinanceCompendiums: TMenuItem;
    mniFINCurrency: TMenuItem;
    mniFINAccountType: TMenuItem;
    miCC_PlannedWorks: TMenuItem;
    mniRQContactType: TMenuItem;
    N212: TMenuItem;
    N214: TMenuItem;
    N215: TMenuItem;
    miDocDistributionEE: TMenuItem;
    miWorkflow: TMenuItem;
    miSpr: TMenuItem;
    N217: TMenuItem;
    N218: TMenuItem;
    N219: TMenuItem;
    N220: TMenuItem;
    N221: TMenuItem;
    N222: TMenuItem;
    miCoefficientQualityStandardsSprav: TMenuItem;
    miPrem_new_hmem_and_nkah: TMenuItem;
    miCoefficientProductionWork: TMenuItem;
    N144: TMenuItem;
    miRegForSupplier: TMenuItem;
    CCTree: TMenuItem;
    miSupplierEEContracts: TMenuItem;
    miReportInstallCounterIP: TMenuItem;
    N223: TMenuItem;
    N224_rprt: TMenuItem;
    mniBufetDiscount: TMenuItem;
    miShutdownConsumers: TMenuItem;
    miDFCustomerCategory: TMenuItem;
    miDFDocAppealSubject: TMenuItem;
    N224_24: TMenuItem;
    mniBuffetDiscountSummary: TMenuItem;
    miDFDocInboxType: TMenuItem;
    mniBuffetDiscountDbf: TMenuItem;
    mniBuffetCashlessDbf: TMenuItem;
    N225: TMenuItem;
    N226ConnectionDebtors: TMenuItem;
    N173Standard_accumulation_report: TMenuItem;
    NServiceAccessionNewTechnique: TMenuItem;
    N228_ReportJoiningLeadingEngineers: TMenuItem;
    N229PCDDesigning: TMenuItem;
    N230ActualExpensesJoiningElectricalInstallations: TMenuItem;
    N231ReportingInformationAttachmentsCustomerElectricNetworks: TMenuItem;
    N232CustomersElectricNetworks: TMenuItem;
    N233AccessionContracts: TMenuItem;
    N111CalendarCheduleElectricNetworks: TMenuItem;
    N235DetailedReportEnclosedAgreementsJoining: TMenuItem;
    mimaxminpricebybill: TMenuItem;
    miProject: TMenuItem;
    miCalendarPlanProject: TMenuItem;
    miCalendarPlanProjectPKD: TMenuItem;
    miLoadPersonalProject: TMenuItem;
    miTransportPlan: TMenuItem;
    miTransportPlanReport: TMenuItem;
    miTransportPlanReportObj: TMenuItem;
    miTransportInRepair: TMenuItem;
    miDFNormativeDocCatalog: TMenuItem;
    N190: TMenuItem;
    miCustomerServices: TMenuItem;
    N236: TMenuItem;
    miENSettingForDFDecree: TMenuItem;
    miWriteOffMaterialsByCarLine: TMenuItem;
    miWriteOffMaterialsByCar: TMenuItem;
    miENSheets4SO: TMenuItem;
    WorkFlow1: TMenuItem;
    N238ReportCurrentState: TMenuItem;
    N239: TMenuItem;
    mniTermsOfConnectionRealisation: TMenuItem;
    N240ReportActPREE: TMenuItem;
    miDFConsumerServices: TMenuItem;
    miBuilding: TMenuItem;
    miServicesQualityNoVoltage: TMenuItem;
    miENExecutors: TMenuItem;
    N241: TMenuItem;
    miDFParam4Classification: TMenuItem;
    N242: TMenuItem;
    N243: TMenuItem;
    mniUkrposhta_registers: TMenuItem;
    N244ReportContractContracts: TMenuItem;
    N245: TMenuItem;
    mniLandAllotmentHistogram: TMenuItem;
    miServicesConsumerOnline: TMenuItem;
    miDocumentMovement: TMenuItem;
    N213OrdersFood: TMenuItem;
    N610ChartNumbeControlIndicators: TMenuItem;
    N1910MissingControIndicatorsMore6Months: TMenuItem;
    N2061ScheduleControlindicatorsMonthsYur: TMenuItem;
    miDSTNumberConsumerAppeals: TMenuItem;
    N2062ScheduleMeanConsumptionSubscriber: TMenuItem;
    mniTechnicalConditionsPreparationHistogramManagementDepartment: TMenuItem;
    mniTechnicalConditionsPreparationHistogramMinorDepartments: TMenuItem;
    miReportOborotMaterialsByPurchase: TMenuItem;
    miENWarrant4Department: TMenuItem;
    miCatalog: TMenuItem;
    miCatalogOrder: TMenuItem;
    miCatalogNormativeDoc: TMenuItem;
    miCatalogProject: TMenuItem;
    mniBuffetSalesInvoice: TMenuItem;
    N216: TMenuItem;
    N246: TMenuItem;
    N247: TMenuItem;
    miline10000: TMenuItem;
    miFromSideObjectsGroup: TMenuItem;
    miAddition3OtherPers: TMenuItem;
    miAddition3OtherPersRazvern: TMenuItem;
    miAddition3OtherPersRazvernNPZ_PLAN: TMenuItem;
    miAddition3OtherPersRazvernNPZ_FACT: TMenuItem;
    miAddition3OtherPersSvern: TMenuItem;
    miAddition3OtherPersSvernNPZ_PLAN: TMenuItem;
    miAddition3OtherPersSvernNPZ_FACT: TMenuItem;
    miDocRegistrationByTerritorialDepartment: TMenuItem;
    miActCheckingWorkplace: TMenuItem;
    miSendingPaySheetsForEmployees: TMenuItem;
    HTTPRIOSystemOperations: THTTPRIO;
    N249: TMenuItem;
    miServicesByTransport: TMenuItem;
    miCoefficientProductionWork_pers: TMenuItem;
    miRegisterOrders: TMenuItem;
    miNotificationForSupplier: TMenuItem;
    mi250: TMenuItem;
    miGPSTracker: TMenuItem;
    HTTPRIOUserAction: THTTPRIO;
    N250: TMenuItem;
    miPriorityCoefficient: TMenuItem;
    miItemWeight: TMenuItem;
    miStructureWeight: TMenuItem;
    miDefects2ElementType: TMenuItem;
    N251ControlSettingMetersAfterPerformingJobs: TMenuItem;
    N252: TMenuItem;
    miInvestPercent: TMenuItem;
    miENReportAdditionZbytMetrology: TMenuItem;
    N253: TMenuItem;
    miConstructionPart: TMenuItem;
    mi1111: TMenuItem;
    N1501: TMenuItem;
    N6103: TMenuItem;
    N1502: TMenuItem;
    miHistogramJLC: TMenuItem;
    N254: TMenuItem;
    N255: TMenuItem;
    miTechnicalStatusHighVoltageEquipment: TMenuItem;
    miTechnicalStatusLine10: TMenuItem;
    miReportStates: TMenuItem;
    miRegisterOrdersVacation: TMenuItem;
    miSizFullDetail: TMenuItem;
    miHistogrammInvest: TMenuItem;
    miWarrantRQFKOrder: TMenuItem;
    miENInspectionSheetHighVoltage: TMenuItem;
    miCustomerWarrant: TMenuItem;
    miNormTaskTechnicalManagement: TMenuItem;
    miNormTaskOthers: TMenuItem;
    mniRegularAssetBase: TMenuItem;
    mniAverageDepartureTimeOfTransport: TMenuItem;
    mi256ReportExecIP: TMenuItem;
    miDocServicesConsumerByPeriod: TMenuItem;
    miReportListObj: TMenuItem;
    N256: TMenuItem;
    N258: TMenuItem;
    N259: TMenuItem;
    N260: TMenuItem;
    N261: TMenuItem;
    N262: TMenuItem;
    N263: TMenuItem;
    N264: TMenuItem;
    N265: TMenuItem;
    N266: TMenuItem;
    N267: TMenuItem;
    N268: TMenuItem;
    N269: TMenuItem;
    N270: TMenuItem;
    N271: TMenuItem;
    N272: TMenuItem;
    N273: TMenuItem;
    N274: TMenuItem;
    N275: TMenuItem;
    N276: TMenuItem;
    N277: TMenuItem;
    N278: TMenuItem;
    N279: TMenuItem;
    N280: TMenuItem;
    N281: TMenuItem;
    N98: TMenuItem;
    N99: TMenuItem;
    N156: TMenuItem;
    N40: TMenuItem;
    N282: TMenuItem;
    N283: TMenuItem;
    N284: TMenuItem;
    N285: TMenuItem;
    N286: TMenuItem;
    N140: TMenuItem;
    N287: TMenuItem;
    N288: TMenuItem;
    N289: TMenuItem;
    N50: TMenuItem;
    N191: TMenuItem;
    N224: TMenuItem;
    N310: TMenuItem;
    N11: TMenuItem;
    N621: TMenuItem;
    miConnectionServicesMonitoringProvision: TMenuItem;
    N12: TMenuItem;
    N16: TMenuItem;
    N24: TMenuItem;
    N25: TMenuItem;
    N610: TMenuItem;
    N43: TMenuItem;
    N77: TMenuItem;
    N79ReportCurrentStateCli: TMenuItem;
    miCountConsumerComplaints: TMenuItem;
    miFuelCard: TMenuItem;
    N79: TMenuItem;
    N87: TMenuItem;
    N311: TMenuItem;
    N90: TMenuItem;
    N97: TMenuItem;
    N111: TMenuItem;
    N116: TMenuItem;
    N121: TMenuItem;
    N122: TMenuItem;
    N135: TMenuItem;
    N142: TMenuItem;
    N143: TMenuItem;
    N145: TMenuItem;
    N83: TMenuItem;
    N2211: TMenuItem;
    N312: TMenuItem;
    N85: TMenuItem;
    EnergyPro1: TMenuItem;


    procedure FormCreate(Sender: TObject);
    procedure ApplicationEvents1Exception(Sender: TObject; E: Exception);
    procedure EurekaLog1ExceptionNotify(EurekaExceptionRecord: TEurekaExceptionRecord; var Handled: Boolean);
    procedure FormShow(Sender: TObject);
    procedure Timer1Timer(Sender: TObject);
    //procedure N3Click(Sender: TObject);
    procedure aboutMenuItemClick(Sender: TObject);
    procedure N6101Click(Sender: TObject);
    procedure N2Click(Sender: TObject);
    procedure N041Click(Sender: TObject);
    procedure miENPlanWorksClick(Sender: TObject);
    procedure N3Click(Sender: TObject);
    procedure N17Click(Sender: TObject);
    procedure N18Click(Sender: TObject);
    procedure mniENTransformerTypeClick(Sender: TObject);
    procedure N21Click(Sender: TObject);
    procedure N22Click(Sender: TObject);
    procedure N23Click(Sender: TObject);
    procedure N351501Click(Sender: TObject);
    procedure N24PlansDeployedWorkClick(Sender: TObject);
    procedure N351502Click(Sender: TObject);
    procedure N27Click(Sender: TObject);
    procedure N28Click(Sender: TObject);
    procedure miTransportClick(Sender: TObject);
    procedure miActClick(Sender: TObject);
    procedure N33Click(Sender: TObject);
    procedure N36Click(Sender: TObject);
    procedure N35Click(Sender: TObject);
    procedure N38Click(Sender: TObject);
    procedure N41Click(Sender: TObject);
    procedure miAnalysysMaterialsClick(Sender: TObject);
    procedure N32Click(Sender: TObject);
    procedure N42Click(Sender: TObject);
    procedure miRZAObjectsClick(Sender: TObject);
    procedure miSDTUObjectsClick(Sender: TObject);
    procedure N16SearchPlansNormativeMaterialsClick(Sender: TObject);
    procedure miMaterialsParametersEditClick(Sender: TObject);
    procedure N43MaterialsPlansMonthClick(Sender: TObject);
    procedure N046101Click(Sender: TObject);
    procedure miAddition4Click(Sender: TObject);
    procedure miTransportObjectsClick(Sender: TObject);
    procedure N44Click(Sender: TObject);
    procedure N46Click(Sender: TObject);
    procedure N48Click(Sender: TObject);
    procedure N47Click(Sender: TObject);
    procedure miTMCRaznaryadkaPodrClick(Sender: TObject);
    procedure miNPZ3PersonalClick(Sender: TObject);
    procedure miActRegistryClick(Sender: TObject);
    procedure miMetrologyClick(Sender: TObject);
    procedure N52Click(Sender: TObject);
    procedure N51Click(Sender: TObject);
    procedure miIzolationClick(Sender: TObject);
    procedure miAddition3PlanLoading_CurrentPlansClick(Sender: TObject);
    procedure miAddition3PlanLoading_PlansPlansClick(Sender: TObject);
    procedure miAddition3PlanLoading_FactClick(Sender: TObject);
    procedure miAddition3_razd_CurentPlansClick(Sender: TObject);
    procedure miAddition3_razd_ZadanPlansClick(Sender: TObject);
    procedure miAddition3_razd_ZadanFactClick(Sender: TObject);
    procedure miAddition3_migr_hum_CurrentPlansClick(Sender: TObject);
    procedure miAddition3_migr_hum_ZadanPlanClick(Sender: TObject);
    procedure miAddition3_migr_hum_ZadanFactClick(Sender: TObject);
    procedure miAdition3_sht_CurrentPlanClick(Sender: TObject);
    procedure miAdition3_sht_ZadanPlanClick(Sender: TObject);
    procedure miAdition3_sht_ZadanFactClick(Sender: TObject);
    procedure miENObjectsClick(Sender: TObject);
    procedure N55Click(Sender: TObject);
    procedure miRepPercentPremClick(Sender: TObject);
    procedure N65Click(Sender: TObject);
    procedure miPurchasesBindingClick(Sender: TObject);
    procedure N66Click(Sender: TObject);
    procedure N67Click(Sender: TObject);
    procedure miRequestDepartmentClick(Sender: TObject);
    procedure miMetrologyPlansEditClick(Sender: TObject);
    procedure N410miAddition4ZbytClick(Sender: TObject);
    procedure miMetrologyObjectsPlansClick(Sender: TObject);
    procedure N70Click(Sender: TObject);
    procedure miENServicesObjectsClick(Sender: TObject);
    procedure miAvrVneplanWorkClick(Sender: TObject);
    procedure miReservedMaterialsClick(Sender: TObject);
    procedure miraznarPoPodrPlaniClick(Sender: TObject);
    procedure miraznarPoPodrZayavkaClick(Sender: TObject);
    procedure miraznarNaSkladPlaniClick(Sender: TObject);
    procedure mizarnarNaskladZayavkaClick(Sender: TObject);
    procedure miraznarPoMolPlaniClick(Sender: TObject);
    procedure miraznarPoMolZayavkiClick(Sender: TObject);
    procedure miraznarkonsolPlaniClick(Sender: TObject);
    procedure miraznarkonsolZayavkiClick(Sender: TObject);
    procedure N77ComplianceNamesMaterialsComplexClick(Sender: TObject);
    procedure miAddition3PlanLoading_YearPlansClick(Sender: TObject);
    procedure mizayavkaDecodingClick(Sender: TObject);
    procedure N411miAddition4PripisiClick(Sender: TObject);
    procedure miAddition3PlanLoading_YearPlansYearClick(Sender: TObject);
    procedure miAddition3PlanLoading_CurrentPlansYearClick(Sender: TObject);
    procedure miPayDocClick(Sender: TObject);
    procedure N78Click(Sender: TObject);
    procedure miAnalyseLoadWorkerClick(Sender: TObject);
    procedure miRQBillsClick(Sender: TObject);
    procedure miRQPaymentsClick(Sender: TObject);
    procedure miRQTransportInvoicesClick(Sender: TObject);
    procedure miFKOrderInClick(Sender: TObject);
    procedure N39Click(Sender: TObject);
    procedure N412miAddition4TransportClick(Sender: TObject);
    procedure N79MaterialsGroupsAnnualPlansClick(Sender: TObject);
    procedure mizbytprognozmonthYearplanClick(Sender: TObject);
    procedure mizbytprognozmonthPotochnplanClick(Sender: TObject);
    procedure mizbytfactzagrpersPlanClick(Sender: TObject);
    procedure mizbytfactzagrpersFactClick(Sender: TObject);
    procedure mizbrozgornpersPlanClick(Sender: TObject);
    procedure mizbrozgornpersFactClick(Sender: TObject);
    procedure miRepOrdermaterialFromRQClick(Sender: TObject);
    procedure miGrRepOrdermaterialObjStrPlanClick(Sender: TObject);
    procedure miGrRepOrdermaterialObjStrRQClick(Sender: TObject);
    procedure N84Click(Sender: TObject);
    procedure N83FulfillmentPlansSectionApplicationsClick(Sender: TObject);
    procedure N85AnalysisWorkObjectClick(Sender: TObject);
    procedure mizbytprognozyear_yearplanClick(Sender: TObject);
    procedure mizbytprognozyear_monthplanClick(Sender: TObject);
    procedure miAGSpecificationClick(Sender: TObject);
    procedure miEstimate2EstimateClick(Sender: TObject);
    procedure miStateMaterialClick(Sender: TObject);
    procedure disparityMatOrderBillClick(Sender: TObject);
    procedure N87NumberScheduledWorkSubscribers_promClick(Sender: TObject);
    procedure miTransportPlansClick(Sender: TObject);
    procedure miPhysVolumesClick(Sender: TObject);
    procedure miEBClick(Sender: TObject);
    procedure N88ExecutionRepairVerificationmetrologicalServicesClick(Sender: TObject);
    procedure N610042Click(Sender: TObject);
    procedure miUseMaterialFactClick(Sender: TObject);
    procedure miMaterials2ContractsClick(Sender: TObject);
    procedure miMotionOfPartyOrderClick(Sender: TObject);
    procedure miImplementationOrderClick(Sender: TObject);
    procedure N94ApplicationsTermsSuppliersDeliveryClick(Sender: TObject);
    //procedure miVitratiFuelTransportClick(Sender: TObject);
    procedure miTravelSheetClick(Sender: TObject);
    procedure N96ListMaterialsManufacturingApplicationsClick(Sender: TObject);
    procedure N99AnalysisWorkPerformanceClick(Sender: TObject);
    procedure miFuelReportClick(Sender: TObject);
    procedure mivedUsetransportClick(Sender: TObject);
    procedure miObjectWorkerClick(Sender: TObject);
    procedure miMaterialsfromActClick(Sender: TObject);
    procedure minewzvitClick(Sender: TObject);
    procedure miBrigadeClick(Sender: TObject);
    procedure GVV1Click(Sender: TObject);
    procedure GVV2Click(Sender: TObject);
    procedure miTransportRealClick(Sender: TObject);
    procedure miAutoTiresClick(Sender: TObject);
    procedure miAccumulatorsClick(Sender: TObject);
    procedure miUsageInputClick(Sender: TObject);
    procedure miSCCounterMoveClick(Sender: TObject);
    procedure miExecutedWorksFromActClick(Sender: TObject);
    procedure miPlanWithoutMOLClick(Sender: TObject);
    procedure miRepOrderByDatePayClick(Sender: TObject);
    procedure mirepusetransp2Click(Sender: TObject);
    procedure miSCCounterReportClick(Sender: TObject);
    procedure miCheckplaseInstalllCountersClick(Sender: TObject);
    procedure miPersonatTabelClick(Sender: TObject);
    procedure miWorksExecutedClick(Sender: TObject);
    procedure miworkMonthFactClick(Sender: TObject);
    procedure mifrmCountWorkOrderByRezervedMaterialsClick(Sender: TObject);
    procedure miRestockingClick(Sender: TObject);
    procedure miRepSzClick(Sender: TObject);
    procedure miCalculationClick(Sender: TObject);
    procedure miSprMolClick(Sender: TObject);
    procedure miOperativeObjectPlanClick(Sender: TObject);
    procedure miOperativeObjectActClick(Sender: TObject);
    procedure miRepByTD1HistoryClick(Sender: TObject);
    procedure miAutoTiresSheetDistanceClick(Sender: TObject);
    procedure miTiresPurchaseClick(Sender: TObject);
    procedure miWorkedTimeByHumanClick(Sender: TObject);
    procedure N100UseAkumClick(Sender: TObject);
    procedure mniWriteOffProtection1Click(Sender: TObject);
    procedure miFromSideObjectsClick(Sender: TObject);
    procedure miENMolClick(Sender: TObject);
    procedure mniGPS1Click(Sender: TObject);
    procedure mnirepServicesContragentClick(Sender: TObject);
    procedure miIsSkinsActiveClick(Sender: TObject);
    procedure mniWriteOffCountersClick(Sender: TObject);
    procedure miTransportManagementClick(Sender: TObject);
    procedure N111provisionServicesPartySelectedDateClick(Sender: TObject);
    procedure miCargoPlansClick(Sender: TObject);
    procedure mniAnnexOKClick(Sender: TObject);
    procedure N112Click(Sender: TObject);
    procedure miContractProjectClick(Sender: TObject);
    procedure miFactTransportClick(Sender: TObject);
    procedure miENTechCondResponsiblesClick(Sender: TObject);
    procedure miENTechConditionsObjectsClick(Sender: TObject);
    procedure N415Click(Sender: TObject);
    procedure miUsedMaterialsClick(Sender: TObject);
    procedure miFactProtectionSubstationClick(Sender: TObject);
    procedure miFactProtectionBrigadeClick(Sender: TObject);
    procedure miOMTSResponsiblesClick(Sender: TObject);
    procedure miTransportRouteClick(Sender: TObject);
    procedure miCargoObjectPlansClick(Sender: TObject);
    procedure miExecutedContractClick(Sender: TObject);
    procedure N116ConsolidatedActamReportperMonthClick(Sender: TObject);
    procedure N117InfoMetersAccountValidityPeriodAccuracyClassClick(Sender: TObject);
    procedure miServicesOutBuhClick(Sender: TObject);
    procedure CountersMonthClick(Sender: TObject);
    procedure miServicesCalculationClick(Sender: TObject);
    procedure miReconstrModernizacOZClick(Sender: TObject);
    procedure mifsGSMClick(Sender: TObject);
    procedure miReportLastBuyMaterialClick(Sender: TObject);
    procedure miENStandardConstClick(Sender: TObject);
    procedure miPlanFactUseGSMClick(Sender: TObject);
    procedure miRQStorageClick(Sender: TObject);
    procedure miRQStorageZoneClick(Sender: TObject);
    procedure miRestMaterialByPlacesClick(Sender: TObject);
    procedure N121miAddition4Transport2Click(Sender: TObject);
    procedure N122ReportTermsProductSalesBuffetClick(Sender: TObject);
    procedure miN4dodat4Click(Sender: TObject);
    procedure miPaymentContractsClick(Sender: TObject);
    procedure miAverageCountPersonalClick(Sender: TObject);
    procedure miPlanLineRouteForestClearClick(Sender: TObject);
    procedure miFiderGuageFullnessClick(Sender: TObject);
    procedure mniWarehouseMaterialsMovementClick(Sender: TObject);
    procedure mniTransportTemperatureClick(Sender: TObject);
    procedure miSubstation04PowerReserveClick(Sender: TObject);
    procedure miGeoCoordinatesClick(Sender: TObject);
    procedure miEnePlanZagruzkaAndCountClick(Sender: TObject);
    procedure miFactZagrByBrigadeHMVEClick(Sender: TObject);
    procedure miIsPlansModeSelectClick(Sender: TObject);
    procedure miContractRealizationStandartClick(Sender: TObject);
    procedure miEnePlanZagruzkaAndCountTechClick(Sender: TObject);
    procedure miENNomenclaturesOperativeClick(Sender: TObject);
    procedure mniCompareMaterialPriceClick(Sender: TObject);
    procedure miPrintBarCodeByMaterialsClick(Sender: TObject);
    procedure miAVRPlanClick(Sender: TObject);
    procedure misetUnSetGlobus2transportrealClick(Sender: TObject);
    procedure miAccess2EnelementClick(Sender: TObject);
    procedure miDriversLoadClick(Sender: TObject);
    procedure miTransportLoadClick(Sender: TObject);
    procedure miZKUClick(Sender: TObject);
    procedure miServicesConnectionsClick(Sender: TObject);
    procedure miWorkedTimeInActsClick(Sender: TObject);
    procedure miExitClick(Sender: TObject);
    procedure miStandardConnectionClick(Sender: TObject);
    procedure NLine6_10kVClick(Sender: TObject);
    procedure NLine04kVClick(Sender: TObject);
    procedure miN127ConnectDebtorsClick(Sender: TObject);
    procedure miRepByTD1History2Click(Sender: TObject);
    procedure miPaymentRegistryClick(Sender: TObject);
    procedure miPaymentRegistry2Click(Sender: TObject);
    procedure miPaymentRegistryServiceClick(Sender: TObject);
    procedure miRepByTD1History2AndServiceClick(Sender: TObject);
    procedure miRQOrg2TypePayClick(Sender: TObject);
    procedure mniPlanPayClick(Sender: TObject);
    procedure test1Click(Sender: TObject);
    procedure miOrderInfoClick(Sender: TObject);
    procedure miInventary_listClick(Sender: TObject);
    procedure miFuelDistributionClick(Sender: TObject);
    procedure miFuelSpendingByCarClick(Sender: TObject);
   //procedure N041Click(Sender: TObject);
    procedure miRQAllocationListClick(Sender: TObject);
    procedure mipersonalcarddriverClick(Sender: TObject);
    procedure miAmountOfSignedAgreementsForConnectionClick(Sender: TObject);
    procedure ConnectionFactCostClick(Sender: TObject);
    procedure miUsingOfMeansByObjectsForConnectionClick(Sender: TObject);
    procedure miServicesOutBuhNewMetodClick(Sender: TObject);
    procedure mniChartpaymentClick(Sender: TObject);
    procedure N135AverageShutdownDurationClick(Sender: TObject);
    procedure miRepByTD1History22Click(Sender: TObject);
    procedure miServicesPlanedPayAndWorksClick(Sender: TObject);
    procedure N20141Click(Sender: TObject);
    procedure miDocFlowServicesClick(Sender: TObject);
    procedure miDFDocClick(Sender: TObject);
    procedure miDocAppealClick(Sender: TObject);
    procedure miInvestProgramClick(Sender: TObject);
    procedure N142StagesFinancingInvestProgramsClick(Sender: TObject);
    procedure miInvestVikonannyaClick(Sender: TObject);
    procedure miInvestVikonannyaExtendedClick(Sender: TObject);
    procedure miServicesRegistryClick(Sender: TObject);
    procedure miDocOutboxClick(Sender: TObject);
    procedure miRemainderClick(Sender: TObject);
    procedure miDocSupplyEEClick(Sender: TObject);
    procedure miInvestpayment_with_monthsrez2Click(Sender: TObject);
    procedure N143TasksPerformingWorksClick(Sender: TObject);
    procedure Timer2Timer(Sender: TObject);
    procedure miENCheckpointPassClick(Sender: TObject);
    procedure miAppealRegistrationByDateClick(Sender: TObject);
    procedure miAppealRegistrationByQuarterClick(Sender: TObject);
    procedure miAppealRegistrationByRensAndDateClick(Sender: TObject);
    procedure miCheckpointsClick(Sender: TObject);
    procedure miIPClick(Sender: TObject);
    procedure miReportServicesFactPaymentsClick(Sender: TObject);
    procedure miReportPmmNeededClick(Sender: TObject);
    procedure miNpz_otp_pl_factClick(Sender: TObject);
    procedure miAddition3PlanLoadingWithZadanPlanClick(Sender: TObject);
    procedure miWorkOrderBytClick(Sender: TObject);
    procedure miPlanWorkBatchClick(Sender: TObject);
    procedure miGrRepOrdermaterialClick(Sender: TObject);
    procedure miBudgetClick(Sender: TObject);
    procedure miRemainderDrawItem(Sender: TObject; ACanvas: TCanvas; ARect: TRect; Selected: Boolean);
    procedure miDrawItemWithRed(Sender: TObject; ACanvas: TCanvas; ARect: TRect; Selected: Boolean);
    procedure miReloadFinExecutorClick(Sender: TObject);
    procedure N148InformationMetersFromBillingClick(Sender: TObject);
    procedure N149DebitDebtInformationBilitsClick(Sender: TObject);
    procedure N210DebitDebtInformationBilits2monthsClick(Sender: TObject);
    procedure N150InformationControllersBillingClick(Sender: TObject);
    function IsVersionCorrect(): Boolean;
    procedure TimerCheckVerTimer(Sender: TObject);
    procedure CloseApp(Sender: TObject);
    procedure N151NumberLunarPlansWithObjectsElectricNetworksClick(Sender: TObject);
    procedure N152GraphPlannedPayPMMClick(Sender: TObject);
    procedure miDocumentManagementClick(Sender: TObject);
    procedure miRoutesClick(Sender: TObject);
    procedure miTravelsheetDriversClick(Sender: TObject);
    procedure miPurchasesNoObjectAVZClick(Sender: TObject);
    procedure miGSMReportByPeriodClick(Sender: TObject);
    procedure miENNormativeVolumeAVZClick(Sender: TObject);
    procedure miServicesConsumerManagementClick(Sender: TObject);
    procedure miBeanShellClick(Sender: TObject);
    procedure miReportDodatok3YearSumClick(Sender: TObject);
    procedure miReportDodatok3YearSumZbytClick(Sender: TObject);
    procedure N3DrawItem(Sender: TObject; ACanvas: TCanvas; ARect: TRect; Selected: Boolean);
    procedure N157ABBNeedforPurchasesClick(Sender: TObject);
    procedure N157ABBNeedforPurchasesDrawItem(Sender: TObject; ACanvas: TCanvas; ARect: TRect; Selected: Boolean);
    procedure miOrderAVZClick(Sender: TObject);
    procedure miGSMLimitReportClick(Sender: TObject);
    procedure miENFuelDistributionSheetClick(Sender: TObject);
    procedure mniRQPackingListCountersClick(Sender: TObject);
    procedure miworkMonthFactZbytClick(Sender: TObject);
    procedure miworkMonthFactMetrologyClick(Sender: TObject);
    procedure miWorksZbytClick(Sender: TObject);
    procedure mireportServicesLicenzClick(Sender: TObject);
    procedure mireportCategoryWorkTKPositionClick(Sender: TObject);
    procedure mireportCategoryWorkTKTechcardClick(Sender: TObject);
    procedure N160ReportConsolidTableSstateExecutionContractsClick(Sender: TObject);
    procedure miConnectionsTariffClick(Sender: TObject);
    procedure miAwardByEnergozbytClick(Sender: TObject);
    procedure miInventarizationClick(Sender: TObject);
    procedure miAverageAccountingPersonalNPZClick(Sender: TObject);
    procedure miGrRepOrdermaterialDrawItem(Sender: TObject; ACanvas: TCanvas; ARect: TRect; Selected: Boolean);
    procedure miENFuelSheetVolumesClick(Sender: TObject);
    procedure N165MaterialsForOrderDeliveriesOrderedtenderPriceClick(Sender: TObject);
    procedure miAccountingBrigadeNPZClick(Sender: TObject);
    procedure prem_new_tehnClick(Sender: TObject);
    procedure prem_new_zbytClick(Sender: TObject);
    procedure prem_new_hmemClick(Sender: TObject);
  	procedure miOrderCountersServicesClick(Sender: TObject);
	  procedure mniReportServicesCountersSentOffClick(Sender: TObject);
    procedure miDeficitProficitClick(Sender: TObject);
    procedure N168miReportDodatok3YearSumByMontsPlanClick(Sender: TObject);
    procedure N169miReportDodatok3YearSumZbytByMonthPlanClick(Sender: TObject);
    procedure N171Click(Sender: TObject);
    procedure miDFTaskSPAClick(Sender: TObject);
    procedure miTaskSPAClick(Sender: TObject);
    procedure miDFDocAgreementClick(Sender: TObject);
    procedure miServicesRelaxationClick(Sender: TObject);
    procedure miENCottageClick(Sender: TObject);
    procedure miReportExecuteDfWorkClick(Sender: TObject);
    procedure miServicesRelaxationReestrClick(Sender: TObject);
    procedure mniDFRoutesClick(Sender: TObject);
    procedure N174SumIbfoAccommodationServicesClick(Sender: TObject);
    procedure miDamageRecoveryClick(Sender: TObject);
    procedure miRestsByOrderClick(Sender: TObject);
    procedure miReportPlanWorkItemsRegistryClick(Sender: TObject);
    procedure miServicesConsumerTaskClick(Sender: TObject);
    procedure N175Click(Sender: TObject);
    procedure miServicesRentClick(Sender: TObject);
    procedure miReportSubstationClick(Sender: TObject);
    procedure N177_report_protection_tariffClick(Sender: TObject);
    procedure N178Purchases_tmcClick(Sender: TObject);
    procedure N178_bonusListClick(Sender: TObject);
    procedure prem_new_zbyt_inspClick(Sender: TObject);
    procedure miWorkByBillingObjectClick(Sender: TObject);
    procedure mienchangepersonbyt_reportClick(Sender: TObject);
    procedure sks1Click(Sender: TObject);
    procedure mniChkRegClick(Sender: TObject);
    procedure mitestClick(Sender: TObject);
    procedure N177Click(Sender: TObject);
    procedure reportExportCentralGraphClick(Sender: TObject);
    procedure miAnalyseCentralExportClick(Sender: TObject);
    procedure miActsGroupReportClick(Sender: TObject);
    procedure miPlanPurchaseClick(Sender: TObject);
    procedure miSepoSmartObjectsClick(Sender: TObject);
    procedure N178Click(Sender: TObject);
    procedure miReportCostWorkClick(Sender: TObject);
    procedure miSealColorsClick(Sender: TObject);
    procedure miSealNamesClick(Sender: TObject);
    procedure mniENPostTypeClick(Sender: TObject); //Типы опор
    procedure mniENHookTypeClick(Sender: TObject); //Типы крюков
    procedure mniENDisconnectorTypeClick(Sender: TObject); //Типы изоляторов
    procedure mniENWiresTypeClick(Sender: TObject); //Марки проводов
    procedure mniENWiresCutClick(Sender: TObject); //Сечения проводов
    procedure mireportExecPriconectionContractClick(Sender: TObject);
    procedure N8Click(Sender: TObject);
    procedure N9Click(Sender: TObject);
    procedure N11BonnusDriversClick(Sender: TObject);
    procedure N12DebtReportMore90DaysClick(Sender: TObject);
    procedure N901DebitorReportDetailedClick(Sender: TObject);
    procedure N19Click(Sender: TObject);
    procedure miENSettingsClick(Sender: TObject);
    procedure N180Click(Sender: TObject);
    procedure N181Click(Sender: TObject);
    procedure miActsForRecyclableMaterialsClick(Sender: TObject);
    procedure N90ReportVerificationMeterDateClick(Sender: TObject);
    procedure mniENBudgets2NomenclaturesClick(Sender: TObject);
    procedure miDFOrdersCatalogClick(Sender: TObject);
    procedure miIncomeCountersForExpertiseClick(Sender: TObject);
    procedure miOutcomeCountersFromExpertiseClick(Sender: TObject);
    procedure miSCRemainsDrawItem(Sender: TObject; ACanvas: TCanvas; ARect: TRect; Selected: Boolean);
    procedure miSCRemainsClick(Sender: TObject);
    procedure miTUTermsClick(Sender: TObject);
    procedure miUnfinishedTasksClick(Sender: TObject);
    procedure mitemFKtrans2AXTransClick(Sender: TObject);
    procedure miUndeliveredMaterials2CompanyClick(Sender: TObject);
    procedure miRegisterConsumers12NKREClick(Sender: TObject);
    procedure miElectricLoadRegimDayClick(Sender: TObject);
    procedure miShiftLinesClick(Sender: TObject);
    procedure N182Click(Sender: TObject);
    procedure N184ChartDeviationsFactsTVENormClick(Sender: TObject);
    procedure N185ScheduleSAIFIReliabilityIndicatorClick(Sender: TObject);
    procedure N186ScheduleSaidiReliabilityIndicatorClick(Sender: TObject);
    procedure N187SchedulEjectionComparedSamePeriodLastYearClick(Sender: TObject);
    procedure miRQOrgClick(Sender: TObject);
    procedure N193Click(Sender: TObject);
    procedure N194Click(Sender: TObject);
    procedure N195Click(Sender: TObject);
    procedure N196Click(Sender: TObject);
    procedure N197ActsViolationPREEClick(Sender: TObject);
    procedure N1110Click(Sender: TObject);
    procedure N188DebtBalanceBeginningYearClick(Sender: TObject);
    procedure N189IncriminationReceivablesZV20powerClick(Sender: TObject);
    procedure miRQOrgBankClick(Sender: TObject);
    procedure N190Click(Sender: TObject);
    procedure miGraphRemontiClick(Sender: TObject);
    procedure N198Click(Sender: TObject);
    procedure miGraphStaffClick(Sender: TObject);
    procedure miBindingCounters2ServicesObjectClick(Sender: TObject);
    procedure miAnalyseWorkGraph_HOEClick(Sender: TObject);
    procedure mniMaterialsMovementClick(Sender: TObject);
    procedure N199Click(Sender: TObject);
    procedure SAIDISAIFI1Click(Sender: TObject);
    procedure N200Click(Sender: TObject);
    procedure N201Click(Sender: TObject);
    procedure N202AccrualPaymentPeriodClick(Sender: TObject);
    procedure NAwardGraphClick(Sender: TObject);
    procedure miListPlanNotIP203Click(Sender: TObject);
    procedure miFinContractsClick(Sender: TObject);
    procedure miENInspectionSheetClick(Sender: TObject);
    procedure NEmptyMeasurementClick(Sender: TObject);
    procedure N204Click(Sender: TObject);
    procedure miPlanLineRouteForestClear_2Click(Sender: TObject);
    procedure NActBytPeriodClick(Sender: TObject);
    procedure pmActWorkflowItogClick(Sender: TObject);
    procedure miJur2MonthClick(Sender: TObject);
    procedure miDFServicesSpravClick(Sender: TObject);
    procedure miMetrologyObjectsWriteOffPlansClick(Sender: TObject);
    procedure miMetrologyWriteOffClick(Sender: TObject);
    procedure miMetrologyPlansWriteOffEditClick(Sender: TObject);
    procedure miReportTechConditionsPlanClick(Sender: TObject);
    procedure miServicesQualityGeneralClick(Sender: TObject);
    procedure miServicesQualityOutTermClick(Sender: TObject);
    procedure miResponseToAppealsClick(Sender: TObject);
    procedure mireportPlanworkItemGraphClick(Sender: TObject);
    procedure miAppealsByClassificationClick(Sender: TObject);
    procedure N207Click(Sender: TObject);
    procedure miOutBoxRegistryClick(Sender: TObject);
    procedure mniFINCurrencyClick(Sender: TObject);
    procedure mniFINAccountTypeClick(Sender: TObject);
    procedure mniRQContactTypeClick(Sender: TObject);
    procedure miCC_PlannedWorksClick(Sender: TObject);
    procedure miConnectionInfoSumClick(Sender: TObject);
    procedure miConnectionInfoSumStoimostClick(Sender: TObject);
    procedure N212Click(Sender: TObject);
    procedure miWorkflowClick(Sender: TObject);
    procedure N217Click(Sender: TObject);
    procedure N218Click(Sender: TObject);
    procedure N219Click(Sender: TObject);
    procedure N220Click(Sender: TObject);
    procedure N221Click(Sender: TObject);
    procedure N222Click(Sender: TObject);
    procedure miReportPremSumNKahClick(Sender: TObject);
    procedure miCoefficientQualityStandardsSpravClick(Sender: TObject);
    procedure miPrem_new_hmem_and_nkahClick(Sender: TObject);
    procedure miCoefficientProductionWorkClick(Sender: TObject);
    procedure miRegForSupplierClick(Sender: TObject);
    procedure CCTreeClick(Sender: TObject);
    procedure miSupplierEEContractsClick(Sender: TObject);
    procedure miReportInstallCounterIPClick(Sender: TObject);
    procedure miShutdownConsumersClick(Sender: TObject);
    procedure miDFCustomerCategoryClick(Sender: TObject);
    procedure miDFDocAppealSubjectClick(Sender: TObject);
    procedure mReportConnectionPKDClick(Sender: TObject);
    procedure mniBufetDiscountClick(Sender: TObject);
    procedure mniBuffetDiscountSummaryClick(Sender: TObject);
    procedure miDFDocInboxTypeClick(Sender: TObject);
    procedure mniBuffetDiscountDbfClick(Sender: TObject);
    procedure mniBuffetCashlessDbfClick(Sender: TObject);
    procedure N224Click(Sender: TObject);
    procedure mimaxminpricebybillClick(Sender: TObject);
    procedure miCalendarPlanProjectClick(Sender: TObject);
    procedure miCalendarPlanProjectPKDClick(Sender: TObject);
    procedure miLoadPersonalProjectClick(Sender: TObject);
    procedure miTransportPlanReportClick(Sender: TObject);
    procedure miTransportPlanReportObjClick(Sender: TObject);
    procedure miTransportInRepairClick(Sender: TObject);
    procedure miDFNormativeDocCatalogClick(Sender: TObject);
    procedure miCustomerServicesClick(Sender: TObject);
    procedure miCustomerServicesDrawItem(Sender: TObject; ACanvas: TCanvas; ARect: TRect; Selected: Boolean);
    procedure miDocumentManagementDrawItem(Sender: TObject; ACanvas: TCanvas; ARect: TRect; Selected: Boolean);
    procedure miENSettingForDFDecreeClick(Sender: TObject);
    procedure miWriteOffMaterialsByCarClick(Sender: TObject);
    procedure miENSheets4SOClick(Sender: TObject);
    procedure N238ReportCurrentStateClick(Sender: TObject);
    procedure mniTermsOfConnectionRealisationClick(Sender: TObject);
    procedure N240ReportActPREEClick(Sender: TObject);
    procedure miDFConsumerServicesClick(Sender: TObject);
    procedure miBuildingClick(Sender: TObject);
    procedure miServicesConsumerManagementDrawItem(Sender: TObject; ACanvas: TCanvas; ARect: TRect; Selected: Boolean);
    procedure miServicesQualityNoVoltageClick(Sender: TObject);
    procedure miENExecutorsClick(Sender: TObject);
    procedure miDFParam4ClassificationClick(Sender: TObject);
    procedure N243Click(Sender: TObject);
    procedure mniUkrposhta_registersClick(Sender: TObject);
    procedure N244ReportContractContractsClick(Sender: TObject);
    procedure mniLandAllotmentHistogramClick(Sender: TObject);
    procedure miServicesConsumerOnlineClick(Sender: TObject);
    procedure miDocumentMovementClick(Sender: TObject);
    procedure N213OrdersFoodClick(Sender: TObject);
    procedure N610ChartNumbeControlIndicatorsClick(Sender: TObject);
    procedure N1910MissingControIndicatorsMore6MonthsClick(Sender: TObject);
    procedure N2061ScheduleControlindicatorsMonthsYurClick(Sender: TObject);
    procedure miDSTNumberConsumerAppealsClick(Sender: TObject);
    procedure N2062ScheduleMeanConsumptionSubscriberClick(Sender: TObject);
    procedure mniTechnicalConditionsPreparationHistogramManagementDepartmentClick(Sender: TObject);
    procedure mniTechnicalConditionsPreparationHistogramMinorDepartmentsClick(Sender: TObject);
    procedure miReportOborotMaterialsByPurchaseClick(Sender: TObject);
    procedure miENWarrant4DepartmentClick(Sender: TObject);
    procedure miCatalogOrderClick(Sender: TObject);
    procedure miCatalogNormativeDocClick(Sender: TObject);
    procedure miCatalogProjectClick(Sender: TObject);
    procedure mniBuffetSalesInvoiceClick(Sender: TObject);
    procedure N247Click(Sender: TObject);
    procedure miFromSideObjectsGroupClick(Sender: TObject);
    procedure miAddition3OtherPersRazvernNPZ_PLANClick(Sender: TObject);
    procedure miAddition3OtherPersRazvernNPZ_FACTClick(Sender: TObject);
    procedure miAddition3OtherPersSvernNPZ_PLANClick(Sender: TObject);
    procedure miAddition3OtherPersSvernNPZ_FACTClick(Sender: TObject);
    procedure miDocRegistrationByTerritorialDepartmentClick(Sender: TObject);
    procedure miActCheckingWorkplaceClick(Sender: TObject);
    procedure miSendingPaySheetsForEmployeesClick(Sender: TObject);
    procedure miServicesByTransportClick(Sender: TObject);
    procedure miCoefficientProductionWork_persClick(Sender: TObject);
    procedure miRegisterOrdersClick(Sender: TObject);
    procedure miNotificationForSupplierClick(Sender: TObject);
    procedure miGPSTrackerClick(Sender: TObject);
    procedure miHighVoltHardwareTypeClick(Sender: TObject);
    procedure testClick(Sender: TObject);
    procedure miItemWeightClick(Sender: TObject);
    procedure miDefects2ElementTypeClick(Sender: TObject);
    procedure N251ControlSettingMetersAfterPerformingJobsClick(Sender: TObject);
    procedure miInvestPercentClick(Sender: TObject);
    procedure miENReportAdditionZbytMetrologyClick(Sender: TObject);
    procedure miHistogramJLCClick(Sender: TObject);
    procedure miTechnicalStatusHighVoltageEquipmentClick(Sender: TObject);
    procedure miTechnicalStatusLine10Click(Sender: TObject);
    procedure miSizFullDetailClick(Sender: TObject);
    procedure miHistogrammInvestClick(Sender: TObject);
    procedure miWarrantRQFKOrderClick(Sender: TObject);
    procedure miENInspectionSheetHighVoltageClick(Sender: TObject);
    procedure miCustomerWarrantClick(Sender: TObject);
    procedure miReportStatesClick(Sender: TObject);
    procedure miNormTaskTechnicalManagementClick(Sender: TObject);
    procedure miNormTaskOthersClick(Sender: TObject);
    procedure mniRegularAssetBaseClick(Sender: TObject);
    procedure mniAverageDepartureTimeOfTransportClick(Sender: TObject);
    procedure mi256ReportExecIPClick(Sender: TObject);
    procedure miDocServicesConsumerByPeriodClick(Sender: TObject);
    procedure miReportListObjClick(Sender: TObject);
    procedure N621Click(Sender: TObject);
    procedure miConnectionServicesMonitoringProvisionClick(Sender: TObject);
    procedure N12Click(Sender: TObject);
    procedure N16Click(Sender: TObject);
    procedure N24Click(Sender: TObject);
    procedure N25Click(Sender: TObject);
    procedure N610Click(Sender: TObject);
    procedure N43Click(Sender: TObject);
    procedure N77Click(Sender: TObject);
    procedure N79ReportCurrentStateCliClick(Sender: TObject);
    procedure miCountConsumerComplaintsClick(Sender: TObject);
    procedure miFuelCardClick(Sender: TObject);
    procedure OpenStandartReport(Sender: TObject);
    procedure N312Click(Sender: TObject);
    procedure N85Click(Sender: TObject);
    procedure EnergyPro1Click(Sender: TObject);



	//procedure N041Click(Sender: TObject);
{    procedure EditShemaMenuItemClick(Sender: TObject);
    procedure ShemaMenuItemClick(Sender: TObject);
    procedure SubstationTypeMenuItemClick(Sender: TObject);
    procedure SubstationOwnerMenuItemClick(Sender: TObject);
    procedure SwitchOffReasonMenuItemClick(Sender: TObject);
    procedure ElemetsTypeMenuItemClick(Sender: TObject);
    procedure offHistoryMenuItemClick(Sender: TObject);
    procedure esT1Click(Sender: TObject);
    procedure N351Click(Sender: TObject);
}

    private
    { Private declarations }
    procedure HTTPRIOBeforeExecute(const MethodName: string; var SOAPRequest: WideString);
    procedure HTTPRIOAfterExecute(const MethodName: string; SOAPResponse: TStream);
    {*** 16.10.06 DL ***}
    procedure HTTPRIOHTTPWebNodeBeforePost(const HTTPReqResp: THTTPReqResp; Data: Pointer);
    {*** 16.10.06 DL ***}
    procedure SetHTTPRIOProps;

    //procedure ReportPluginSelect(Sender: TObject);
  public
    { Public declarations }
    procedure RegisterFileType(ext: string; FileName: string);
    function UserIsRegistered(UName, UPassword: string): Boolean;
    //procedure GetReportPlugins;
    function TranslateException(msg: string): string;
  end;

var
  SCEF: string; //Графический редактор объектов энергетики SupChart.exe

  frmMain: TfrmMain;
  startTime: TDateTime;

  //frmRQContactTypeShow : TfrmRQContactTypeShow;
  frmFINCurrencyShow: TfrmFINCurrencyShow;
  frmFINAccountTypeShow: TfrmFINAccountTypeShow;
  frmENServicesGuardShow: TfrmENServicesGuardShow;
  frmENPlanInformCustomerShow: TfrmENPlanInformCustomerShow;
  frmENServices2CalcAdditionalItemsShow: TfrmENServices2CalcAdditionalItemsShow;
  frmENAct2FinKodIstShow: TfrmENAct2FinKodIstShow;
  frmENBonusListItemShow: TfrmENBonusListItemShow;
  frmENBonusListShow: TfrmENBonusListShow;
  //frmReportRestsByOrder : TfrmReportRestsByOrder;
  frmENSORentItemsShow: TfrmENSORentItemsShow;
  frmENSOPayBillProvShow: TfrmENSOPayBillProvShow;
  frmENSOBillShow: TfrmENSOBillShow;
  frmENDamageRecovery2ENActShow: TfrmENDamageRecovery2ENActShow;
  frmENDamageRecoveryStatusShow: TfrmENDamageRecoveryStatusShow;
  frmENDamageRecoveryShow: TfrmENDamageRecoveryShow;
  frmENFamilyRelationShow: TfrmENFamilyRelationShow;
  frmENServicesRelaxationShow: TfrmENServicesRelaxationShow;
  frmENCottageShow: TfrmENCottageShow;
  frmTKPositionHistoryShow: TfrmTKPositionHistoryShow;
  frmTKTransportHistoryShow: TfrmTKTransportHistoryShow;
  frmENFuelInvResultShow: TfrmENFuelInvResultShow;
  frmENFuelInvResultTypeShow: TfrmENFuelInvResultTypeShow;
  frmENFuelSheetVolumesShow: TfrmENFuelSheetVolumesShow;
  frmENFuelInventarizationStatusShow: TfrmENFuelInventarizationStatusShow;
  frmENFuelInventarizationShow: TfrmENFuelInventarizationShow;
  frmENFuelInventarizationItemShow: TfrmENFuelInventarizationItemShow;
  frmENActFailureShow: TfrmENActFailureShow;
  frmENPlanWork2ActFailureShow: TfrmENPlanWork2ActFailureShow;
  frmENNormVolumeAVZItemShow: TfrmENNormVolumeAVZItemShow;
  frmENNormativeVolumeAVZShow: TfrmENNormativeVolumeAVZShow;
  frmENSituationRPNShow: TfrmENSituationRPNShow;
  frmENEquipmentStateShow: TfrmENEquipmentStateShow;
  frmENGauge150Show: TfrmENGauge150Show;
  frmENDelayServicesShow: TfrmENDelayServicesShow;
  frmRQWarehouseMaterialsMovement: TfrmRQWarehouseMaterialsMovement;
  frmTransportManagement: TfrmTransportManagement;
  frmBeanShell: TfrmBeanShell;
  frmENMolFuelMotionTypeShow: TfrmENMolFuelMotionTypeShow;
  frmENMolFuelMotionShow: TfrmENMolFuelMotionShow;
  frmRQAllocationListFormShow: TfrmRQAllocationListFormShow;
  frmRQAllocationListStatusShow: TfrmRQAllocationListStatusShow;
  frmRQAllocationListTypeShow: TfrmRQAllocationListTypeShow;
  frmRQAllocationListShow: TfrmRQAllocationListShow;
  frmRQAllocationListItemShow: TfrmRQAllocationListItemShow;
  frmENCoordinatesShow: TfrmENCoordinatesShow;
  frmENHighVoltCellSuppliesShow: TfrmENHighVoltCellSuppliesShow;
  frmENLine10SuppliesShow: TfrmENLine10SuppliesShow;
  frmENTraversShow: TfrmENTraversShow;
  frmENHookTypeShow: TfrmENHookTypeShow;
  frmENHookShow: TfrmENHookShow;
  frmENLineRouteShow: TfrmENLineRouteShow;
  frmENWiresTypeShow: TfrmENWiresTypeShow;
  frmENWiresCutShow: TfrmENWiresCutShow;
  frmENWiresShow: TfrmENWiresShow;
  frmENWiresItemShow: TfrmENWiresItemShow;
  frmENBranch10Show: TfrmENBranch10Show;
  frmENBranch10ItemShow: TfrmENBranch10ItemShow;
  frmENCabelOut10Show: TfrmENCabelOut10Show;
  frmENCabelOut10ItemShow: TfrmENCabelOut10ItemShow;

  //frmTKMaterialsShow : TfrmTKMaterialsShow;
  frmENPlanWorkBillingShow: TfrmENPlanWorkBillingShow;
  frmENEstimateItemTypeShow: TfrmENEstimateItemTypeShow;
  frmENBelongingShow: TfrmENBelongingShow;
  frmENPlanWorkStateShow: TfrmENPlanWorkStateShow;
  frmENPlanWorkMoveHistoryShow: TfrmENPlanWorkMoveHistoryShow;
  frmENPlanCorrectHistoryShow: TfrmENPlanCorrectHistoryShow;
  frmENDepartmentTypeShow: TfrmENDepartmentTypeShow;
  frmENDepartmentShow: TfrmENDepartmentShow;
  frmENDepartmentOrigShow: TfrmENDepartmentOrigShow;
  frmENHumenItemShow: TfrmENHumenItemShow;
  frmENPlanWorkKindShow: TfrmENPlanWorkKindShow;
  frmENPreproductionObjectShow: TfrmENPreproductionObjectShow;
  frmENEstimateItemStatusHistoryShow: TfrmENEstimateItemStatusHistoryShow;
  frmENOtherObjectTypeShow: TfrmENOtherObjectTypeShow;
  frmENOtherObjectShow: TfrmENOtherObjectShow;
  frmENTravelSheetTypeShow: TfrmENTravelSheetTypeShow;
  frmENTravelWorkModeShow: TfrmENTravelWorkModeShow;
  frmENTravelSheetItemKindShow: TfrmENTravelSheetItemKindShow;
  frmENTravelSheetItemStatusShow: TfrmENTravelSheetItemStatusShow;
  frmENTravelSheetItemShow: TfrmENTravelSheetItemShow;
  frmENTravelSheetFuelShow: TfrmENTravelSheetFuelShow;
  frmENTravelSheetFuelRemainsShow: TfrmENTravelSheetFuelRemainsShow;
  frmENTransportRealFuelRemainsShow: TfrmENTransportRealFuelRemainsShow;
  frmFINDoc2FKOrderShow: TfrmFINDoc2FKOrderShow;
  frmRQFKOrderInShow: TfrmRQFKOrderShow;
  frmRQFKOrderOutShow: TfrmRQFKOrderShow;
  frmRQFKOrderOut2Show: TfrmRQFKOrderShow;
  frmRQFKOrderOutOperative2TranzitShow: TfrmRQFKOrderShow;
  frmRQFKOrderOutTranzit2OperativeShow: TfrmRQFKOrderShow;
  frmRQFKOrderOutE2E: TfrmRQFKOrderShow;
  frmRQFKOrderMeasurementChange: TfrmRQFKOrderShow;
  frmRQFKOrderLoadExplMBP: TfrmRQFKOrderShow;
  frmRQFKOrderLoadExplMNMA: TfrmRQFKOrderShow;
  frmRQFKOrderMBP: TfrmRQFKOrderShow;
  frmRQFKOrderMNMA: TfrmRQFKOrderShow;
  frmRQFKOrderWhiteOffCounters: TfrmRQFKOrderShow;
  frmRQFKOrderRashodMNMA: TfrmRQFKOrderShow;
  frmRQFKOrderServicesFS: TfrmRQFKOrderShow;
  frmRQFKOrderInBufetShow: TfrmRQFKOrderShow;
  frmRQFKOrderOutBufetShow: TfrmRQFKOrderShow;
  frmRQFKOrderReturnShow: TfrmRQFKOrderShow;
  frmRQFKOrderRashodGiftShow: TfrmRQFKOrderShow;
  frmRQFKOrderOSExplShow: TfrmRQFKOrderShow;
  frmRQFKOrderOSMovementShow: TfrmRQFKOrderShow;
  frmRQFKOrderRashodToStorageShow: TfrmRQFKOrderShow;
  frmRQFKOrderZoneChangingShow: TfrmRQFKOrderShow;
  frmRQFKOrderOutFuelShow: TfrmRQFKOrderShow;
  frmRQFKOrderAvarOutShow: TfrmRQFKOrderShow;
  frmRQFKOrderAvarInShow: TfrmRQFKOrderShow;
  frmRQFKOrderItemShow: TfrmRQFKOrderItemShow;

  // -------------------
  // TechKards ....

  frmTKDeviceShow: TfrmTKDeviceShow;
  frmTKTechCardAttachmentShow: TfrmTKTechCardAttachmentShow;
  frmTKFinTypeShow: TfrmTKFinTypeShow;
  frmTKElementToFinCollectionShow: TfrmTKElementToFinCollectionShow;
  frmTKMaterialsShowAddGroup: TfrmTKMaterialsShowAddGroup;

  // end TechCards ........

  frmFINMolShow: TfrmFINMolShow;
  frmFINMaterialsDataShow: TfrmFINMaterialsDataShow;
  frmFINMaterialsStatusShow: TfrmFINMaterialsStatusShow;
  frmENActForRecyclableMaterialsShow: TfrmENActForRecyclableMaterialsShow;
  frmENAct2ENPlanWorkShow: TfrmENAct2ENPlanWorkShow;
  frmENRoadTypeDataShow: TfrmENRoadTypeDataShow;
  frmENDistanceTypeShow: TfrmENDistanceTypeShow;
  frmENDistanceShow: TfrmENDistanceShow;
  frmENRZAObjectTypeShow: TfrmENRZAObjectTypeShow;
  frmENRZAObjectShow: TfrmENRZAObjectShow;
  frmENSDTUObjectTypeShow: TfrmENSDTUObjectTypeShow;
  frmENSDTUObjectShow: TfrmENSDTUObjectShow;
  frmENSEPOSmartObjectShow: TfrmENSDTUObjectShow;
  frmFINExecutorTreeShow: TfrmFINExecutorTreeShow;
  frmCNSubsystemTypeShow: TfrmCNSubsystemTypeShow;
  frmCNPack2PlanWorkShow: TfrmCNPack2PlanWorkShow;
  frmENEstimateItemKindShow: TfrmENEstimateItemKindShow;
  frmENWorkOrderShow: TfrmENWorkOrderShow;
  frmFINDocTypeShow: TfrmFINDocTypeShow;
  //frmFINDoc2WorkOrderShow : TfrmFINDoc2WorkOrderShow;

  frmENDeliveryTimeShow: TfrmENDeliveryTimeShow;
  frmENDeliveryOrderShow: TfrmENDeliveryOrderShow;
  frmENDeliveryTimePlanShow: TfrmENDeliveryTimePlanShow;
  frmENBuilderObjectShow: TfrmENBuilderObjectShow;
  frmENBuilderObjectTypeShow: TfrmENBuilderObjectTypeShow;
  frmENMetrologyDeviceTypeShow: TfrmENMetrologyDeviceTypeShow;
  frmENMetrologyDeviceShow: TfrmENMetrologyDeviceShow;
  frmENMOL2PlanWorkShow: TfrmENMOL2PlanWorkShow;
  frmENHumenItemKindShow: TfrmENHumenItemKindShow;
  frmENAct2HumenShow: TfrmENAct2HumenShow;
  frmENAct2TransportShow: TfrmENAct2TransportShow;
  frmENSITEquipmentShow: TfrmENSITEquipmentShow;
  frmENSITEquipTypeShow: TfrmENSITEquipTypeShow;
  frmENSITFeatureTypeShow: TfrmENSITFeatureTypeShow;
  frmENSITFeatureHistoryShow: TfrmENSITFeatureHistoryShow;
  frmENSITFeatureShow: TfrmENSITFeatureShow;
  frmENSITEquipStateShow: TfrmENSITEquipStateShow;
  frmENIzolationObjectTypeShow: TfrmENIzolationObjectTypeShow;
  frmENIzolationObjectShow: TfrmENIzolationObjectShow;
  frmENPurchasesObjectReasonShow: TfrmENPurchasesObjectReasonShow;
  frmENPurchasesObjectShow: TfrmENPurchasesObjectShow;
  frmENPurchasesNoObjectShow: TfrmENPurchasesNoObjectShow;
  frmENTransformerObjectShow: TfrmENTransformerObjectShow;
  frmENOperativeObjectShow: TfrmENOperativeObjectShow;
  frmENEstimateItemStatusShow: TfrmENEstimateItemStatusShow;
  frmFINMolTypeShow: TfrmFINMolTypeShow;
  frmFINDoc2MolDataShow: TfrmFINDoc2MolDataShow;
  frmENBindingOverShow: TfrmENBindingOverShow;
  frmENPurchasesNoObjectTypeShow: TfrmENPurchasesNoObjectTypeShow;


  // Request forms ...

  frmRQPlanPayItemStatusShow: TfrmRQPlanPayItemStatusShow;
  frmRQPlanPayItemSecondShow: TfrmRQPlanPayItemSecondShow;
  frmRQPlanPayItemFirstShow: TfrmRQPlanPayItemFirstShow;
  frmRQPlanPayShow: TfrmRQPlanPayShow;
  frmRQPlanPayStatusShow: TfrmRQPlanPayStatusShow;
  frmRQPackingListCountersShow: TfrmRQPackingListShow;
  frmRQPackingListMaterialsShow: TfrmRQPackingListShow;
  frmRQPackingListStatusShow: TfrmRQPackingListStatusShow;
  frmRQPackingListItemShow: TfrmRQPackingListItemShow;
  frmRQPackingListItemTypeShow: TfrmRQPackingListItemTypeShow;
  frmRQOrderKindShow: TfrmRQOrderKindShow;
  frmRQOrderStatusShow: TfrmRQOrderStatusShow;
  frmRQOrderDepartmentShow: TfrmRQOrderShow;
  frmRQOrderBudgetShow: TfrmRQOrderShow;
  frmRQOrderOEShow: TfrmRQOrderShow;
  frmRQOrderPlannedAutoShow: TfrmRQOrderShow;
  frmRQOrderNoPlannedShow: TfrmRQOrderShow;
  frmRQOrderProductionShow: TfrmRQOrderShow;
  frmRQOrderPlanedServicesShow: TfrmRQOrderShow;
  frmRQOrderNoPlanedServicesShow: TfrmRQOrderShow;
  frmRQOrderAVZShow: TfrmRQOrderShow;
  frmRQOrderCountersServicesShow: TfrmRQOrderShow;
  frmRQOrderStatusHistoryShow: TfrmRQOrderStatusHistoryShow;
  frmRQOrgShow: TfrmRQOrgShow;
  frmRQMaterialsGroupShow: TfrmRQMaterialsGroupShow;
  frmRQMaterials2TKMaterialsShow: TfrmRQMaterials2TKMaterialsShow;
  frmRQOrderItem2ENEstimateItemShow: TfrmRQOrderItem2ENEstimateItemShow;
  frmRQOrderItem2ENEstimateItemStatusShow: TfrmRQOrderItem2ENEstimateItemStatusShow;
  frmRQInvoiceStatusShow: TfrmRQInvoiceStatusShow;
  frmRQInvoiceItemShow: TfrmRQInvoiceItemShow;
  frmRQBillStatusShow: TfrmRQBillStatusShow;
  frmRQBillItemShow: TfrmRQBillItemShow;
  frmRQPayDocShow: TfrmRQPayDocShow;
  frmRQPayDocItemShow: TfrmRQPayDocItemShow;
  frmRQFKOrder2FKOrderTypeShow: TfrmRQFKOrder2FKOrderTypeShow;
  frmRQInvoiceItem2ENEstimateItemShow: TfrmRQInvoiceItem2ENEstimateItemShow;
  frmRQInvoiceItem2ENEstimateItemStatusShow: TfrmRQInvoiceItem2ENEstimateItemStatusShow;
  frmRQFKOrderItem2EstimateItemStatusShow: TfrmRQFKOrderItem2EstimateItemStatusShow;
  frmRQFKOrderItemRemainderShow: TfrmRQFKOrderItemRemainderShow;
  frmENEstimateItem2ENEstimateItemShow: TfrmENEstimateItem2ENEstimateItemShow;
  frmRQFKRemainder2EstimateItemShow: TfrmRQFKRemainder2EstimateItemShow;


  //--------------

  frmNakl_ListShow: TfrmNakl_ListShow;
  frmDov_ListShow: TfrmDov_ListShow;
  frmPartyShow: TfrmPartyShow;
  frmRQOrgBankShow: TfrmRQOrgBankShow;
  frmRQOrgRschetShow: TfrmRQOrgRschetShow;
  frmAGSpecificationShow: TfrmAGSpecificationShow;
  frmAGSpecificationItemShow: TfrmAGSpecificationItemShow;
  frmAGAgreeShow: TfrmAGAgreeShow;
  frmRQPItem2BItem2OItemShow: TfrmRQPItem2BItem2OItemShow;
  frmENPlanTransShow: TfrmENPlanTransShow;
  frmENPlanWriteOffProtectionShow: TfrmENPlanWriteOffProtectionShow;
  frmENContractItemShow: TfrmENContractItemShow;
  frmENContractShow: TfrmENContractShow;

  /////
  frmENSzBrigadeShow: TfrmENSzBrigadeShow;
  /////

  frmENAutoTiresShow: TfrmENAutoTiresShow;
  frmENAutoTiresHistoryShow: TfrmENAutoTiresHistoryShow;
  frmENTiresInstallPlacesShow: TfrmENTiresInstallPlacesShow;
  frmENAccumulatorsHistoryShow: TfrmENAccumulatorsHistoryShow;
  frmENSizMaterials2TKMaterialsShow: TfrmENSizMaterials2TKMaterialsShow;
  frmENLowVoltBoardTypeShow: TfrmENLowVoltBoardTypeShow;
  frmENBranchTypeShow: TfrmENBranchTypeShow;
  frmENBranch2Customer04Show: TfrmENBranch2Customer04Show;
  frmENAntsapfShow: TfrmENAntsapfShow;
  frmENAntsapfPositionShow: TfrmENAntsapfPositionShow;
  frmENServicesSalesShow: TfrmENServicesSalesShow;
  frmENServicesRentShow: TfrmENServicesRentShow;
  frmENServicesShiftShow: TfrmENServicesShiftShow;
  frmENServicesForSupplierShow: TfrmENServicesShiftShow;
  frmENServicesProjectShow: TfrmENServicesProjectShow;
  frmENSpravMolShow: TfrmENSpravMolShow;
  frmENPlanOperativeShow: TfrmENPlanOperativeShow;
  frmENActIncomeShow: TfrmENActIncomeShow;
  frmENActIncomeStatusShow: TfrmENActIncomeStatusShow;
  frmBalansShow: TfrmBalansShow;
  frmKauShow: TfrmKauShow;
  frmCountryShow: TfrmCountryShow;
  frmProvinceShow: TfrmProvinceShow;
  frmRegionShow: TfrmRegionShow;
  frmCityTypeShow: TfrmCityTypeShow;
  frmCityShow: TfrmCityShow;
  frmStreetTypeShow: TfrmStreetTypeShow;
  frmStreetShow: TfrmStreetShow;
  frmDistrictShow: TfrmDistrictShow;
  frmENTransportOrder2TransportItemShow: TfrmENTransportOrder2TransportItemShow;
  frmENTrafficGPSShow: TfrmENTrafficGPSShow;
  frmENAgreeJointShow: TfrmENAgreeJointShow;
  frmENLockTypeShow: TfrmENLockTypeShow;
  frmENFencingShow: TfrmENFencingShow;
  frmENSafetyPrecautionsShow: TfrmENSafetyPrecautionsShow;
  frmENCargoPlanShow: TfrmENCargoPlanShow;
  frmENTechConditionsServicesProjectShow: TfrmENTechConditionsServicesShow;
  frmENTechConditionsServicesRealizationShow: TfrmENTechConditionsServicesShow;
  frmENActIncomeTechConditionsShow: TfrmENActIncomeTechConditionsShow;
  frmENActInTechCond2ENActShow: TfrmENActInTechCond2ENActShow;
  frmENContragentShow: TfrmENContragentShow;
  frmENTechCondResponsiblesShow: TfrmENTechCondResponsiblesShow;
  frmENTransportRouteShow: TfrmENTransportRouteShow;
  frmENTransportRoute2RQFKOrderShow: TfrmENTransportRoute2RQFKOrderShow;
  frmENDestinationOrderShow: TfrmENDestinationOrderShow;
  frmENDestinationPoint2PointShow: TfrmENDestinationPoint2PointShow;
  frmENCargoObjectPlanShow: TfrmENCargoObjectPlanShow;
  frmRQTransportInvoiceItemShow: TfrmRQTransportInvoiceItemShow;
  frmAcc_Obj_TypesShow: TfrmAcc_Obj_TypesShow;
  frmPayFormShow: TfrmPayFormShow;
  frmENReconstrModernOZShow: TfrmENReconstrModernOZShow;
  frmENSubst150OwnTransShow: TfrmENSubst150OwnTransShow;
  frmENSubst150VoltTransShow: TfrmENSubst150VoltTransShow;
  frmENSubst150SwitchShow: TfrmENSubst150SwitchShow;
  frmENSubst150CurrentTransShow: TfrmENSubst150CurrentTransShow;
  frmENSubst150DisconnectorShow: TfrmENSubst150DisconnectorShow;
  frmENSubst150DischargerShow: TfrmENSubst150DischargerShow;
  frmENSubst150ShortCircuiterShow: TfrmENSubst150ShortCircuiterShow;
  frmENSubst150SeparatorShow: TfrmENSubst150SeparatorShow;
  frmENSubst150BatteryShow: TfrmENSubst150BatteryShow;
  frmENServicesObject2PaymentScheduleShow: TfrmENServicesObject2PaymentScheduleShow;
  frmENStandardConstShow: TfrmENStandardConstShow;
  frmENStandardConstEntryShow: TfrmENStandardConstEntryShow;
  frmFINExecutorTypeShow: TfrmFINExecutorTypeShow;
  frmFINExecutor2PlanShow: TfrmFINExecutor2PlanShow;
  frmENReconstrModernOZStatusShow: TfrmENReconstrModernOZStatusShow;
  frmRQStorageZone2RestPurposeShow: TfrmRQStorageZone2RestPurposeShow;
  frmRQStorage2ENMolShow: TfrmRQStorage2ENMolShow;
  frmRQStorageZone2TKMaterialsShow: TfrmRQStorageZone2TKMaterialsShow;
  frmRQStorage2ENMol2ZoneShow: TfrmRQStorage2ENMol2ZoneShow;
  frmENTransportTemperatureShow: TfrmENTransportTemperatureShow;
  frmENConnectionKindShow: TfrmENConnectionKindShow;
  frmENConnectionTariffEntryShow: TfrmENConnectionTariffEntryShow;
  frmENTechConditionsRealizationStandartShow: TfrmENTechConditionsServicesShow;
  frmENTechConditionsRealizationNoStandartShow: TfrmENTechConditionsServicesShow;
  frmENNomenclaturesOperativeShow: TfrmENNomenclaturesOperativeShow;
  frmENAccess2EnelementShow: TfrmENAccess2EnelementShow;
  frmENAVRPlanShow: TfrmENAVRPlanShow;
  frmEPWorkerShow: TfrmEPWorkerShow;
  frmENBankingDetailsShow: TfrmENBankingDetailsShow;
  frmTKMaterials2RQContractsShow: TfrmTKMaterials2RQContractsShow;
  frmENInvestProgramShow: TfrmENInvestProgramShow;

  /// DocFlow
  frmDFMain: TfrmDFMain;
  frmDFTaskSPAShow: TfrmDFTaskSPAShow;
  frmServicesConsumerManagement: TfrmServicesConsumerManagement;
  frmUser2StaffingShow: TfrmUser2StaffingShow;
  //frmDFDocServicesConsumerShow : TfrmDFDocServicesConsumerShow;
  frmDFDocMovementShow: TfrmDFDocMovementShow;
  frmDFInfoSourcesShow: TfrmDFInfoSourcesShow;
  frmDFPackTypeShow: TfrmDFPackTypeShow;
  frmDFServicesRegistryShow: TfrmDFServicesRegistryShow;
  frmDFDocAppealRESShow: TfrmDFDocAppealShow;
  frmDFDocAppealIKCShow: TfrmDFDocAppealShow;
  frmDFDocSupplyEEShow: TfrmDFDocSupplyEEShow;
  frmDFDocDistributionEEShow: TfrmDFDocSupplyEEShow;
  frmDFDocJourneyShow: TfrmDFDocJourneyShow;
  frmDFDocApplicationShow: TfrmDFDocApplicationShow;
  frmDFDoc2WorkerShow: TfrmDFDoc2WorkerShow;
  frmDFDoc2WorkerDirectionShow: TfrmDFDoc2WorkerDirectionShow;
  frmDFDocAttachmentStatusShow: TfrmDFDocAttachmentStatusShow;
  frmDFDocAttachmentShow: TfrmDFDocAttachmentShow;
  frmDFTaskSPAManagement: TfrmDFTaskSPAManagement;
  frmDFDocAgreementShow: TfrmDFDocAgreementShow;
  frmDFRoute2DFDocTypeShow: TfrmDFRoute2DFDocTypeShow;
  frmDFRouteTypeShow: TfrmDFRouteTypeShow;
  frmDFRouteKindShow: TfrmDFRouteKindShow;
  frmDFDocRouteShow: TfrmDFDocRouteShow;
  frmDFStampTypeShow: TfrmDFStampTypeShow;

  /// end of DocFlow forms

  /// WorkFlow
  frmWFAttachmentStatusShow: TfrmWFAttachmentStatusShow;
  frmWFProcessShow: TfrmWFProcessShow;
  frmWFProcessMovementShow: TfrmWFProcessMovementShow;
  frmWFGroup2StateShow: TfrmWFGroup2StateShow;
  frmWFGroupActionShow: TfrmWFGroupActionShow;
  frmWFGroupShow: TfrmWFGroupShow;
  frmWFSubsystemShow: TfrmWFSubsystemShow;
  frmWFBaseProcessShow: TfrmWFBaseProcessShow;
  frmWFBaseProcessVersionShow: TfrmWFBaseProcessVersionShow;
  frmWFBaseProcessItemsShow: TfrmWFBaseProcessItemsShow;
  frmWFProcessStateShow: TfrmWFProcessStateShow;
  frmWFProcessRoleShow: TfrmWFProcessRoleShow;
  frmWorkFlowManagementShow: TfrmWorkFlowManagementShow;
  frmWFProcessRole2UserShow: TfrmWFProcessRole2UserShow;
  frmWFSubsystem2UserShow: TfrmWFSubsystem2UserShow;
  frmWFDepartment2UserShow: TfrmWFDepartment2UserShow;
  frmWFDepartmentShow: TfrmWFDepartmentShow;
  frmWFBaseProcessTypeShow: TfrmWFBaseProcessTypeShow;
  frmWFGroupTypeShow: TfrmWFGroupTypeShow;
  frmWFProcessStatusShow: TfrmWFProcessStatusShow;
  frmWFPackStatusShow: TfrmWFPackStatusShow;
  frmWFPackType2UserShow: TfrmWFPackType2UserShow;
  frmWFPackTypeShow: TfrmWFPackTypeShow;
  frmWFBaseProcessLinkTypeShow: TfrmWFBaseProcessLinkTypeShow;
  frmWFBaseProcessLinkShow: TfrmWFBaseProcessLinkShow;
  frmWFPack2ServicesObjectShow: TfrmWFPack2ServicesObjectShow;

  /// end of WorkFlow

///  CallCenter
  frmCCDamagedEquipmentShow: TfrmCCDamagedEquipmentShow;
  frmCCDamage32ClassShow: TfrmCCDamage32ClassShow;
  frmCCCallShow: TfrmCCCallShow;
  frmCCCallTypeShow: TfrmCCCallTypeShow;
  frmCCCallSubTypeShow: TfrmCCCallSubTypeShow;
  frmCCCallInBriefShow: TfrmCCCallInBriefShow;
  frmCCCallInBrief32Show: TfrmCCCallInBrief32Show;
  frmCCCallHistoryShow: TfrmCCCallHistoryShow;
  frmCCCallCommentShow: TfrmCCCallCommentShow;
  frmCCCallResultShow: TfrmCCCallResultShow;
  frmCCNodeShow: TfrmCCNodeShow;
  frmCCDamageLogShow: TfrmCCDamagelogShow;
  frmCCDamageRZAShow: TfrmCCDamageRZAShow;
  frmCCDamageReasonShow: TfrmCCDamageReasonShow;
  frmCCOutageNoticeShow: TfrmCCOutageNoticeShow;
  frmCCDamageHistoryShow: TfrmCCDamageHistoryShow;
  frmCCDamageCommentShow: TfrmCCDamageCommentShow;
  frmENSettingsShow: TfrmENSettingsShow;
  frmCCAddressStreetShow: TfrmCCAddressStreetShow;
  frmCCPeriodShow: TfrmCCPeriodShow;
///  end of CallCenterController units
///

  frmENIPShow: TfrmENIPShow;
  frmENIPFinancingShow: TfrmENIPFinancingShow;
  frmENMethodExecuteWorkShow: TfrmENMethodExecuteWorkShow;
  frmENIPPurposeProgramShow: TfrmENIPPurposeProgramShow;
  frmReportServicesFactPayments: TfrmReportServicesFactPayments;
  frmReportPmmNeeded: TfrmReportPmmNeeded;
  frmENCheckpointPassListShow: TfrmENCheckpointPassListShow;
  frmENCheckpointShow: TfrmENCheckpointShow;
  frmCheckpointRegistration: TfrmCheckpointRegistration;
  frmENWorkOrderBytShow: TfrmENWorkOrderBytShow;
  frmENWorkOrderBytRaidShow: TfrmENWorkOrderBytShow;
  frmENWorkOrderBytControlShow: TfrmENWorkOrderBytShow;
  frmENWorkOrderBytTypeShow: TfrmENWorkOrderBytTypeShow;
  frmENWorkOrderBytStatusShow: TfrmENWorkOrderBytStatusShow;
  frmENPlanGraphFinancingFuelShow: TfrmENPlanGraphFinancingFuelShow;
  frmENFuelDistributionSheetShow: TfrmENFuelDistributionSheetShow;
  frmENCoefficientExecPlanShow: TfrmENCoefficientExecPlanShow;
  frmRQCentralExportGraphShow: TfrmRQCentralExportGraphShow;
  frmRQCentralExportGraphItemShow: TfrmRQCentralExportGraphItemShow;
  frmRQCentralExportGraphTypeShow: TfrmRQCentralExportGraphTypeShow;
  frmRQCentralExportAnalyseShow: TfrmRQCentralExportAnalyseShow;
  frmRQPlanPurchaseShow: TfrmRQPlanPurchaseShow;
  frmRQPurchaseItemShow: TfrmRQPurchaseItemShow;
  frmRQSpravDKPPShow: TfrmRQSpravDKPPShow;
  frmRQPurchaseItemTypeShow: TfrmRQPurchaseItemTypeShow;
  frmRQSpravDKPPItemShow: TfrmRQSpravDKPPItemShow;
  frmRQPurchaseItemTender2RQPurchaseItemShow: TfrmRQPurchaseItemTender2RQPurchaseItemShow;
  frmRQPurchaseItem2EstimateItemShow: TfrmRQPurchaseItem2EstimateItemShow;
  frmENPost04OKSNShow: TfrmENPost04OKSNShow;
  frmENPost10OKSNShow: TfrmENPost10OKSNShow;
  frmENSORItems2Post04Show: TfrmENSORItems2Post04Show;
  frmENSORItems2Post10Show: TfrmENSORItems2Post10Show;
  frmAXDimensionsKSShow: TfrmAXDimensionsKSShow;
  frmENBudgets2NomenclaturesShow: TfrmENBudgets2NomenclaturesShow;
  frmRQActCounterExpertiseIncomeShow: TfrmRQActCounterExpertiseShow;
  frmRQActCounterExpertiseOutcomeShow: TfrmRQActCounterExpertiseShow;
  frmRQPurchaseItemAbstractSumShow: TfrmRQPurchaseItemAbstractSumShow;
  frmFKTrans2AXTransShow: TfrmFKTrans2AXTransShow;
  //frmENActPostingKindShow : TfrmENActPostingKindShow;

  frmENCoefficientQualityStandardsShow: TfrmENCoefficientQualityStandardsShow;
  frmENCalcPowerReserveItemShow: TfrmENCalcPowerReserveItemShow;
  frmENCalcPowerReserveShow: TfrmENCalcPowerReserveShow;
  WaitForm: TForm;
  OldCursor: TCursor;
  IsRIOExecuting: Boolean = false;
  GetUserListException: Boolean = false;
  CheckUserException: Boolean = false;
  Unauthorized: Boolean = false;

  sessionUserName: string;
  sessionUserTabNum: string;
  sessionUserPodrName: string;

  chkPaymentByBudget: Boolean;
  userCode: Integer;
  userAdministrator, userOrdersCatalog: Boolean;
  dfDepartmentCode: Integer;
  changeWarrant: Boolean;

implementation

uses
  LoginUnit, IniTools, AuthorizationController, DlgUnit, SetupFormUnit, XSBuiltIns,
  AwardUnit, Globals, EnergyproController, DMReportsUnit, ENDateRangeFormUnit,
  ENDateRangeWithDepartmentFormUnit, frmENRunReportUnit, ENPeriodWithRENFormUnit,
  ENPeriodWithRENFormUnitTask, FINMolController,
  ENPeriodWithRENFormUnitTaskPlanFact, ENAnalysisMaterials, VersionInfo,
  reportPlanListByMaterial, aboutEnergyNet, ENPeriodFormUnit, ReportDodatok3,
  ReportDodatok4, ReportOutRealiztnUnit, ReportCallCenterByDatesUnit,
  ENPlanWorkCount, EditMetrologyCountersAddToPlan, EditMaterialsParameters,
  ReportRaznaryadka, ReportRaznaryadkaHOE, EditMetrologyCountersSelect,
  ENPurchasesObjectController, ENElementTypeController, EditENPurchasesBinding,
  EditMaterialsParametersOut, EditENMetrologyPlans
  //, RQRequestController,
  //RQRequestKindController
  , RQOrderController, RQOrderKindController, EditENMetrologyObjectsSelect,
    ENActController, EditENActPrintFilter, AvarVneplanWork, ReservedMaterials,
    ReportOrderDecoding, AnakyseLoadWorker, RQFKOrderController,
    RQFKOrderKindController, reportMaterialFromPlanByGroup,
    ENPeriodWithRENFormUnitRQ, reportEnergoSbyt, ReportOborotMaterials,
    AnalysExecutionWorks, ReportStateMaterialsByObject,
    EditENEstimateItem2ENEstimateItem, ENPlanWorkController,
    ENPlanWorkStatusController, EditENPlanWorkFilter, ReportPhysVolumes,
    ReportServicesForDate, ReportExecutionOfPlanMetrology, ReportExaminationTP,
    ENOtherObjectController, ENOtherObjectTypeController, MaterialsForOrder,
    ENPurchasesNoObjectController, ENPurchasesNoObjectTypeController,
    EditMaterials2Contracts, EditRQFKOrderFilter, TKAccountingTypeController,
  // matMotionOfPartyorder ,
  ImplementationOrder, OrderOnMonth, chargesFuelTransport,
  ListMaterialsOnRequests, matMotionOfPartyorder, vedUsedtransport,
  MaterialsFromAct, ReportOborotMatBuh, repSummaryListMechanic,
  ExecutedWorksFromAct, EditENEstimateItem2ENEstimateItemSCCounter,
  ReportPlanWithoutMOL, OrdersByDatePay, repAutoNaryadPytev,
  SumListMovedCounters, ScSearchPlaceSetting, PersonalTabel,
  ExecutedWorksFromMonthPlan, ENForImplementation,
  CountWorkOrderByRezervedMaterials, repsz, OrdersBytd1History, EditPostings,
  ReportTiresSheetDistance, ReportTiresPurchase, WorkedTimeByHuman,
  EditConvertTranzit2Operative, ENPlanWorkTypeController, GPSDataImport,
  ReportOborotServices, Annex_OK, ENTechConditionsServicesController,
  ENTechConditionsServicesTypeController, ReportFactTransport,
  ReportUsedMaterials, StateProtection, EditOMTSResponsibles, ExecuteContract,
  reportCounters, EditENServicesObjectFilter, ENServicesObjectController,
  ENServicesContractKindController, reportCounterYearUnit,
  ENServicesContractTypeController, ENReconstrModernOZController, FactStateGSM,
  PlanFactUseGSM, RepRestByPlaces, Dodatok4Invest, PaymentByContracts,
  AverageCountPersonal, EditGeoCoordinatesImport, reportPowerReserve,
  EnePlanZagruzkaAndCountPers, ENConnectionKindController,
  ComparePriceOrderedAndPurchaseMaterial, EditPrintBarCodeByMaterials,
  ReportLastBuyMaterials, ReportParamSbyt, ReportTransportLoad,
  reportTenderMaterials, ReportWorkedTimeInActs, RQBillController,
  RQBillTypeController, ReportStandardConnection, ConnectDebtors,
  ReportPersonalCardDriver, ServicesPlanedPayAndWorks, reporttenderplan,
  ReportUMCRest, ReportDamageT32, reportAppealRegistrationByDate,
  reportAppealRegistrationByQuarter, reportAppealRegistrationByRensAndDate,
  ENDepartmentController, ReportPercentLoadBrigades, EditENPlanWorkBatch,
  ReportGSMReportByPeriod, ReportAvzRestNormative, reportGSMLimit,
  RQOrderCreationMethodController, ExecutedWorksFromMonthPlanZbyt,
  reportWorksZbyt, reportActZbyt, reportServicesRegistryPrint2,
  reportCategoryWorkTKposition, reportServicesRegistryByPodrSum,
  ENWorkOrderBytController, ENWorkOrderBytTypeController,
  AverageAccountingPersonalNPZ, SCUsageInputController, ENTravelSheetController,
  ReportServicesCountersSentOff, DeficitProficit, EditDFTaskSPAFilter,
  TKTransportHistoryController, EditTKTransportHistoryFilter,
  reportDfExecutionWork, reportServicesRelaxation, ReportPlanWorkItemsRegistry,
  reportSubstation, ReportProtectionTariff, RepPurchases_tmc,
  AnalysWorkByBillingObject, enchangepersonbyt_report, AnalyseLoadWorkerInPodr,
  ReportCentralExport, ReportCostWork, reportExecPriconectionContracts,
  PlanPurchaseGroup, ReportPercentPremDriver, DebtBytParamUnit,
  ENElementController, ReportSCRest, reportJLC,
  reportUndeliveredMaterials2Company, DataModuleReportsENetObject,
  DataModuleReportsEWF, ZDataset, ZSqlProcessor, Gauge150, reportPlanPurchase,
  ReportGraphForAnalyse, BindingCounters2ServicesObject,
  reportlistPlanWithoutBinding2IP, DFDocAppealController, DFDocController,
  DFDepartmentController, DFConsts, ReportTechConditionsPlan,
  ENPlanWorkItem2GraphController, EditENPlanWorkItem2Graph,
  reportPlanworkItemGraph, ENSettingsConsts, EditENServicesGuard,
  reportControlOfResponseToAppeals, ReportConnectionInfoSum,
  ReportConnectionInfoSumStoimost, DFDocSupplyEEController,
  DFDocSupplyEETypeController, ShowENRegForSupplier, ReportInstallCountersIP,
  ShutdownConsumersReport, EditChoosePeriod, ShowDFCustomerCategory,
  ShowDFDocAppealSubject, ShowDFDocInboxType,
  ReportDetailedReportOnConcludedAgreementsConnection, ShowENCustomerServices,
  ShowDFNormativeDocCatalog, ShowENSettingForDFDecree, ENSheets4SOController,
  ENSheets4SOTypeController, reportActByDepartment,
  ShowDFDocServicesConsumerKind, ShowDFParam4Classification, reportCurrentStates,
  ShowENWarrant4Department, DFNormativeDocCatalogManagement,
  DFProjectDocCatalogManagement, ShowENDocAttachmentAction,
  ShowENDocAttachmentType, ShowENDocAttType2Action,
  ReportServicesFromSideByTransport, EditNotificationForSupplier,
  ShowENGPSTracker, ShowENHighVoltHardwareType, ENHighVoltHardwareTypeController
  , ENPriorityCoefficientController
  , ENPriorityCoeffTypeController
  , ShowENPriorityCoefficient
  , ShowTKDefects2ElementType
  , CorrespondenceReport, reportControInstallationCountersEEConnection
  , reportStates
  , ShowENReportAdditionZbytMetrology, reportInvestServicesFromSide
  , ENInspectionSheetController
  , ShowENActPostingKind
  , ReportTechnicalStatusHighVoltage
  , ReportTechnicalStatusLine10
  , ShowENPostType
  , ReportSizFullDetail
, EditENWarrantFilter, ENWarrantController, ENWarrantTypeController, EditChooseTXSDate,
  ReportInvestProgram, ReportListObj, ReportWFInvest, ReportMonitoringProvision,
  ShowENFuelCard;


const
  ReportPluginsPath = 'AditionalReports\'; {'ReportPlugins\';}
      LocalizationPath = 'localization\ua_ua.mloc';
      IniName = 'EnergyNet.ini';
      TempPath = 'tempReports';

//uses ShowENSubstation;

{$R *.dfm}



//////////////////////////////////////////////////////////////

function EnumProc(Wnd: HWND; Param: LongInt): Boolean; stdcall;
var
  WndCaption: array[0..255] of Char; // буфер для имени
begin
  Application.ProcessMessages;
  GetWindowText(Wnd, WndCaption, 255); // считываем  текст заголовка окна
  if (pos('Подключение к ', string(WndCaption)) > 0) or (pos('Безопа', string(WndCaption)) > 0) then
  begin
    if Wnd = GetForegroundWindow then
      SendMessage(Wnd, WM_CLOSE, 0, 0);
    Result := false;
  end
  else
    Result := true;  // продолжать искать окна
end;

procedure TfrmMain.ApplicationEvents1Exception(Sender: TObject; E: Exception);
begin
  if IsRIOExecuting then
  begin
    if WaitForm <> nil then
    begin
      WaitForm.Close;
      WaitForm.Free;
      WaitForm := nil;
    end;
    ///// 06.04.06
    sbMain.Panels[0].Text := '';
    frmMain.Update;
    /////
    Screen.Cursor := OldCursor;
    IsRIOExecuting := false;
  end;
  if (E is ESOAPHTTPException) then
    case ESOAPHTTPException(E).StatusCode of
      0:
        begin
          Application.MessageBox(PChar('Нет связи ...'), PChar('Ошибка!'), MB_ICONERROR + MB_OK);
          Exit;
        end;
      401:
        begin
          if GetUserListException then
            Unauthorized := true;
          Application.MessageBox(PChar('Неверный логин или пароль'), PChar('Ошибка!'), MB_ICONERROR + MB_OK);
          Exit;
        end;

      404:
        begin
          if GetUserListException then
            Unauthorized := true;
          Application.MessageBox(PChar('Пользователь не найден!!!'), PChar('Ошибка!'), MB_ICONERROR + MB_OK);
          Exit;
        end;

      503:
        begin
          Application.MessageBox(PChar('Служба недоступна'), PChar('Ошибка!'), MB_ICONERROR + MB_OK);
          Exit;
        end;
    end;
  if (E is ERemotableException) then
  begin
    Application.MessageBox(PChar(E.Message), PChar('Ошибка!'), MB_ICONERROR + MB_OK);
    Exit;
  end;
  //Unknown exception
  Application.MessageBox(PChar(E.Message), PChar('Ошибка!'), MB_ICONERROR + MB_OK);
  if CheckUserException then
    Unauthorized := true;
end;

procedure TfrmMain.EnergyPro1Click(Sender: TObject);
var
  frmEPRep_Marker: TfrmEPRep_Marker;
begin
  frmEPRep_Marker:=TfrmEPRep_Marker.Create(Application, dsEdit);
  try
    frmEPRep_Marker.ShowModal;
  finally
    frmEPRep_Marker.Free;
    frmEPRep_Marker:=nil;
  end;
end;

procedure TfrmMain.EurekaLog1ExceptionNotify(EurekaExceptionRecord: TEurekaExceptionRecord; var Handled: Boolean);
var
  msg: string;
begin
  if IsRIOExecuting then
  begin
    if WaitForm <> nil then
    begin
      WaitForm.Close;
      WaitForm.Free;
      WaitForm := nil;
    end;
    ///// 06.04.06
    sbMain.Panels[0].Text := '';
    frmMain.Update;
    /////
    Screen.Cursor := OldCursor;
    IsRIOExecuting := false;
    Timer1.Enabled := false;
  end;

  if (EurekaExceptionRecord.ExceptionObject is ESOAPHTTPException) then
    with ESOAPHTTPException(EurekaExceptionRecord.ExceptionObject) do
      case StatusCode of
        0, 12029, 12031:
          //Message:='Нет связи ...' + #13#10 + '[Код статуса: ' + IntToStr(StatusCode) + ']';
          Message := 'Нет связи ...';
        401:
          begin
            if GetUserListException then
              Unauthorized := true;
            Message := 'Неверный логин или пароль';
          end;
        503:
          Message := 'Служба недоступна';
      end;

  if (EurekaExceptionRecord.ExceptionObject is ERemotableException) then
  begin
    msg := ERemotableException(EurekaExceptionRecord.ExceptionObject).Message;
    //ShowMessage(msg);
    ERemotableException(EurekaExceptionRecord.ExceptionObject).Message := TranslateException(msg);
    //ShowMessage(TranslateException(msg));
  end;

  // === Для всех ошибок ===//
  //  if (EurekaExceptionRecord.ExceptionObject is Exception) then
  //    Exception(EurekaExceptionRecord.ExceptionObject).Message:='Ошибка';

  //if (EurekaExceptionRecord.ExceptionObject is EFOpenError) then
  if CheckUserException then
    Unauthorized := true;
end;

function TfrmMain.TranslateException(msg: string): string;
var
  i, p, p1: Integer;
  str, str1, AppPath, TranslationId, trans: string;
  params: array of string;
    ///// 22.09.06
    // f: Boolean;
    /////

  ///// 22.09.06
  // procedure GetParams(msg: String);

  procedure GetParams(var msg: string);
  /////
  var
    i, j, paramsCount: Integer;
    str, curParam: string;
  begin
    SetLength(params, 0);

    i := 1;

    while i <= Length(msg) do
    begin
      if ((msg[i] = '{') and ((i < Length(msg)) and (msg[i + 1] = '%'))) then
      begin
        if i >= Length(msg) - 1 then
          break;

        j := i + 2;
        curParam := '';

{        while (j <= Length(msg)) and (msg[j] <> '%') do
        begin
          curParam := curParam + msg[j];
          inc(j);
        end;}

        while j <= Length(msg) do
        begin
          //(msg[j] = '%') and (msg[j+1] = '}')
          if (j <= Length(msg) - 1) and (Copy(msg, j, 2) = '%}') then
          begin
            inc(j);
            break;
          end;
          curParam := curParam + msg[j];
          inc(j);
        end;

        paramsCount := High(params) + 1;
        SetLength(params, paramsCount + 1);
        params[paramsCount] := curParam;

        ///// 22.09.06
        Delete(msg, i, Length(curParam) + 4);
        // i := j + 1;
        /////
        Continue;
      end;

      inc(i);
    end;
  end; // procedure GetParams(msg: String);

  procedure SetParams(var msg: string);
  var
    i, j, k, paramsCount, curParamIdx: Integer;
    str, curParam: string;
  begin
    i := 1;

    while i <= Length(msg) do
    begin
      if ((msg[i] = '{') and ((i < Length(msg)) and (msg[i + 1] = '%'))) then
      begin
        if i >= Length(msg) - 1 then
          break;

        j := i + 2;
        k := j;
        curParam := '';

{        while (j <= Length(msg)) and (msg[j] <> '%') do
        begin
          curParam := curParam + msg[j];
          inc(j);
        end;}

        while j <= Length(msg) do
        begin
          //(msg[j] = '%') and (msg[j+1] = '}')
          if (j <= Length(msg) - 1) and (Copy(msg, j, 2) = '%}') then
          begin
            inc(j);
            break;
          end;
          curParam := curParam + msg[j];
          inc(j);
        end;

        curParamIdx := -1;
        try
          curParamIdx := StrToInt(curParam);
        except
          on EConvertError do
            curParamIdx := -1;
        end;

        if curParamIdx <> -1 then
        begin
{          str := Copy(msg, i, k + 1 - i) +
                 params[curParamIdx] +
                 Copy(msg, j + 1, Length(msg) - k);}
          str := Copy(msg, 1, i - 1) + params[curParamIdx] + Copy(msg, j + 1, Length(msg) - j);
          msg := str;
          j := Length(Copy(msg, 1, i - 1) + params[curParamIdx]);
        end;

        i := j + 1;
        Continue;
      end;

      inc(i);
    end;
  end; // procedure SetParams(msg: String);

  procedure RemoveSpaces(var str: string);
  begin
    str := AnsiReplaceText(str, ' ', '');
  end; // procedure RemoveSpaces(var str: String);

  function ReplaceChars(str: string; chars: array of string): string;
  var
    i: Integer;
    tempStr: string;
  begin
    tempStr := str;

    for i := Low(chars) to High(chars) do
      tempStr := AnsiReplaceText(tempStr, chars[i], '_');

    Result := tempStr;
  end; // function ReplaceChars(str: String; chars: array of String): String;



begin
  Result := msg;

  GetParams(msg);
//  for i:=0 to High(params) do
//    ShowMessage('(' + IntToStr(i) + '): ' + params[i]);

  i := 1;
  str := '';

  while i <= Length(msg) do
  begin
    if (msg[i] = #10) or (msg[i] = #9) then
    begin
      inc(i);
      Continue;
    end;
    //ShowMessage(msg[i] + ' (' + IntToStr(Ord(msg[i])) + ')');
    str := str + msg[i];
    inc(i);
  end;

  //ShowMessage(IntToStr(pos('Exception: ', str)));
  {p := pos('Exception: ', str);
  p1 := p + Length('Exception: ');
  str := Copy(str, p1, Length(str) - p1 + 1);}
  p := pos('Exception: ', str);
  while p <> 0 do
  begin
    p1 := p + Length('Exception: ');
    str := Copy(str, p1, Length(str) - p1 + 1);
    p := pos('Exception: ', str);
  end;

  //Result := str;

  i := 1;
  str1 := '';

  ///// 22.09.06
  // f := false;
  /////

///// 22.09.06
//  while i <= Length(str) do
//  begin
//    if not (str[i] in ['a'..'z', 'A'..'Z', '0'..'9']) then
//    begin
//      if ((str[i] = '{') and ((i < Length(str)) and (str[i+1] = '%'))) or
//         {(str[i] = ',')} (str[i] in [',', '!', '-', '''', '=']) then f := true;
//      if (str[i] in [',', '!', '-', '''', '=']) and
//         (i = Length(str)) and f then str1 := str1 + '_';
//      inc(i);
//      Continue;
//    end;
//    if f then
//    begin
//      str1 := str1 + '_';
//      f := false;
//    end;
//    str1 := str1 + str[i];
//    inc(i);
//  end;
/////

  ///// 22.09.06
  RemoveSpaces(str);
  str1 := ReplaceChars(str, [',', '"', '.', '!', '-', '''', '=']);
  /////

  //Result := str1;
  TranslationId := str1;

  // Result := TranslationId;

  AppPath := ExtractFilePath(Application.ExeName);

  xmlLoc.Active := false;
  xmlLoc.XML.Clear;
  xmlLoc.XML.LoadFromFile(AppPath + LocalizationPath);
  xmlLoc.Active := true;

  with xmlLoc.DocumentElement do
    for i := 0 to ChildNodes.Count - 1 do
    begin
      if ChildNodes[i].NodeName = 'translation' then
        if ChildNodes[i].Attributes['id'] = TranslationId then
        begin
          //Result := ChildNodes[i].Text;
          trans := ChildNodes[i].Text;
          SetParams(trans);
          Result := trans;
          break;
        end;
    end;

  xmlLoc.Active := false;

  // SetParams(trans);

  // Result := trans;
end;

procedure TfrmMain.miENSettingForDFDecreeClick(Sender: TObject);
begin
 if not Assigned(frmENSettingForDFDecreeShow) then
    frmENSettingForDFDecreeShow := TfrmENSettingForDFDecreeShow.Create(Application, fmChild);
  frmENSettingForDFDecreeShow.Show;
end;

// function TfrmMain.TranslateException(msg: String): String;

{procedure TfrmMain.SetHTTPRIOProps;
var i, p: Integer;
    OldWSDLPath, NewWSDLPath, OldPort, OldService: String;
begin
  ///// Setting UserName, Password and WSDLLocation
  for i:=0 to ComponentCount-1 do
    if (Components[i] is THTTPRIO) then
      with THTTPRIO(Components[i]) do
      begin
        HTTPWebNode.UserName:=UserName;
        HTTPWebNode.Password:=Password;
        OldWSDLPath:=WSDLLocation;
        OldService:=Service;
        OldPort:=Port;
        p:=pos('WSDL\',OldWSDLPath);
        NewWSDLPath:=ExtractFilePath(Application.ExeName)+
                     Copy(OldWSDLPath,p,Length(OldWSDLPath)-p+1);
        WSDLLocation:=NewWSDLPath;
        Service:=OldService;
        Port:=OldPort;
        OnBeforeExecute:=HTTPRIOBeforeExecute;
        OnAfterExecute:=HTTPRIOAfterExecute;
      end;
  /////
end;}

procedure TfrmMain.SAIDISAIFI1Click(Sender: TObject);
var
  frmCCRep_SAIDISAIFI: TfrmCCRep_SAIDISAIFI;
begin
  frmCCRep_SAIDISAIFI := TfrmCCRep_SAIDISAIFI.Create(Application, dsEdit);
  try
    //frmCCRunReport.reportName:=TMenuItem(Sender).Caption;
    frmCCRep_SAIDISAIFI.ShowModal;
  finally
    frmCCRep_SAIDISAIFI.Free;
    frmCCRep_SAIDISAIFI := nil;
  end;
end;

procedure TfrmMain.SetHTTPRIOProps;
var
  i, p: Integer;
  IP, Port_, IniPath: string;
  TimeOut: Integer;
    //OldWSDLPath, NewWSDLPath, OldPort, OldService: String;
begin
  ///// Setting UserName, Password and WSDLLocation
  IniPath := ExtractFilePath(Application.ExeName) + IniName;
  for i := 0 to ComponentCount - 1 do
    if (Components[i] is THTTPRIO) then
      with THTTPRIO(Components[i]) do
      begin
        HTTPWebNode.UserName := UserName;
        HTTPWebNode.Password := Password;
        HTTPWebNode.Agent := 'KSOE Client';

        {OldWSDLPath:=WSDLLocation;
        OldService:=Service;
        OldPort:=Port;
        p:=pos('WSDL\',OldWSDLPath);
        NewWSDLPath:=ExtractFilePath(Application.ExeName)+
                     Copy(OldWSDLPath,p,Length(OldWSDLPath)-p+1);
        WSDLLocation:=NewWSDLPath;
        Service:=OldService;
        Port:=OldPort;}

        if IniValueExists(IniPath, 'EnergyNet', 'IP') then
          IP := IniReadString(IniPath, 'EnergyNet', 'IP')
        else
          IP := 'localhost';
        if IniValueExists(IniPath, 'EnergyNet', 'Port') then
          Port_ := IniReadString(IniPath, 'EnergyNet', 'Port')
        else
          Port_ := '9080';

        URL := 'http://' + IP + ':' + Port_ + '/soap/servlet/rpcrouter';

        sbMain.Panels[3].Text := 'Сервер : ' + IP + ':' + Port_;

        OnBeforeExecute := HTTPRIOBeforeExecute;
        OnAfterExecute := HTTPRIOAfterExecute;
        {*** 16.10.06 DL ***}
        HTTPWebNode.OnBeforePost := HTTPRIOHTTPWebNodeBeforePost;
        {*** 16.10.06 DL ***}

        //HTTPWebNode.ConnectTimeout := 30000;
      end;





  /////
end;

procedure TfrmMain.sks1Click(Sender: TObject);
begin
 frmFrmAnalyseLoadWorkerInPOdr := TfrmFrmAnalyseLoadWorkerInPOdr.Create(Application, dsInsert);
 try
   frmFrmAnalyseLoadWorkerInPOdr.ShowModal;
 finally
   frmFrmAnalyseLoadWorkerInPOdr.Free;
 end;
end;

procedure TfrmMain.miCountConsumerComplaintsClick(Sender: TObject);
begin
   frmreportDSTNumberConsumerAppeals := TfrmreportDSTNumberConsumerAppeals.Create(Application, dsInsert);
  try
    frmreportDSTNumberConsumerAppeals.ShowModal;
  finally
    frmreportDSTNumberConsumerAppeals.Free;
  end;
end;

procedure TfrmMain.test1Click(Sender: TObject);
begin
	frmOrdersBytd1History := TfrmOrdersBytd1History.Create(Application, dsInsert);
	try
		frmOrdersBytd1History.reportVersion := 7; // test
		frmOrdersBytd1History.ShowModal;
  finally
		frmOrdersBytd1History.Free;
 end;
end;

procedure TfrmMain.testClick(Sender: TObject);
var

   frmenactpostingkindShow : TfrmenactpostingkindShow ;
   tElement : ENElement;
begin
   frmenactpostingkindShow:=TfrmenactpostingkindShow.Create(Application,fmNormal);
   try
		frmenactpostingkindShow.ShowModal;
  finally
		frmenactpostingkindShow.Free;
 end;
end;
procedure TfrmMain.miDocumentMovementClick(Sender: TObject);
begin
	frmreportDocumentMovement := TfrmreportDocumentMovement.Create(Application, dsInsert);
	try
		frmreportDocumentMovement.ShowModal;
  finally
		frmreportDocumentMovement.Free;
 end;
end;

procedure TfrmMain.miRepByTD1History22Click(Sender: TObject);
begin
	frmOrdersBytd1History := TfrmOrdersBytd1History.Create(Application, dsInsert);
	try
		frmOrdersBytd1History.reportVersion := 8; // test предыдущ заявки сумма берется со счета если выставлен счет
		frmOrdersBytd1History.ShowModal;
  finally
		frmOrdersBytd1History.Free;
 end;
end;

function TfrmMain.UserIsRegistered(UName, UPassword: string): Boolean;
var
  i: Integer;
  TempUser: UserControllerSoapPort;
  UserList: UserShortList;
  UserFound: Boolean;
  userFilter: AuthorizationController.UserFilter;
  AppVersion: string;
  FVI: TFileVersionInfo;
begin
  Result := false;
  TempUser := HTTPRIOAuth as UserControllerSoapPort;

  AppVersion := '';
  try
    FVI := FileVersionInfo(Application.ExeName);
    AppVersion := FVI.FileVersion;
  except
  end;

  try
    userFilter := AuthorizationController.UserFilter.Create();
    SetNullIntProps(userFilter);
    SetNullXSProps(userFilter);
    userFilter.name := UName;

    UserList := TempUser.getFilteredList(userFilter, AppVersion, CONFIG_ENERGYNET_CLIENT_VERSION);
  except
    GetUserListException := true;
    raise;
  end;

  UserFound := False;

if (UserList.totalCount = 0) then
 begin
    Application.MessageBox(PChar('Ваша версія програми [' + AppVersion + '] є застарілою та не може використовуватися далі.' + #13#10 + 'Перевірте, чи налаштовано автоматичне оновлення при старті програми.'), PChar('Необхідно оновити програму'), MB_ICONWARNING + MB_OK);
   Application.Terminate;
   Exit;
 end;
//
if not IsVersionCorrect then
 begin
    Application.MessageBox(PChar('Ваша версія програми [' + AppVersion + '] є застарілою та не може використовуватися далі.' + #13#10 + 'Перевірте, чи налаштовано автоматичне оновлення при старті програми.'), PChar('Необхідно оновити програму'), MB_ICONWARNING + MB_OK);
   Application.Terminate;
 end;


  for i := 0 to High(UserList.list) do
    if UserList.list[i].name = UName then
    begin
      Result := true;
      UserFound := true;

      // uShortName := UserList.list[i].fio;
      sessionUserName := UserList.list[i].fio;
      sessionUserTabNum := UserList.list[i].tabNumber;
      sessionUserPodrName := UserList.list[i].podrname;

      //sbMain.Panels[2].Text := 'РЭС: '+UserList.list[i].+' Пользователь : ' + UserName;
      break;
    end;

  if not UserFound then
    raise ESOAPHTTPException.Create('Неверный логин или пароль', 401)
  else
  begin
    if (UserList.list[i].changePassWord = 1) then
    begin
      frmLogin := TfrmLogin.Create(Application);
      changePassWord := True;
      setPasswrd := True;
      userCode := UserList.list[i].code;

      if (frmLogin.ShowModal = mrOk) then
      begin
        if (frmLogin.edtNewPassWord.Text <> frmLogin.edtConfirmPassword.Text) then
        begin
          Application.MessageBox(PChar('Не співпадає пароль!'), PChar('Увага!'), MB_ICONWARNING + MB_OK);
          Exit;
        end;
      end
      else
      begin
        changePassWord := False;
        setPasswrd := False;
        raise ESOAPHTTPException.Create('Неверный логин или пароль', 401);
      end;
    end;
  end;

end;


procedure TfrmMain.miReportListObjClick(Sender: TObject);
var
 argNames, args: ArrayOfString;
  reportName: string;
begin
     frmListObjReport := TfrmListObjReport.Create(Application, dsInsert);
  try
    frmListObjReport.ShowModal;

  finally
    frmListObjReport.Free;

 end;
end;

procedure TfrmMain.miPlanPurchaseClick(Sender: TObject);
begin
   if not Assigned(frmRQPlanPurchaseShow) then
    frmRQPlanPurchaseShow := TfrmRQPlanPurchaseShow.Create(Application, fmChild);
    frmRQPlanPurchaseShow.Show;
end;


procedure TfrmMain.miAnalyseCentralExportClick(Sender: TObject);
begin
     if not Assigned(frmRQCentralExportAnalyseShow) then
    frmRQCentralExportAnalyseShow := TfrmRQCentralExportAnalyseShow.Create(Application, fmChild);
  frmRQCentralExportAnalyseShow.Show;
end;

function TfrmMain.IsVersionCorrect(): Boolean;
var
  TempConfig: ConfigControllerSoapPort;
  ConfigObj: Config;
  CurrAppVer: string;
  FVI: TFileVersionInfo;
begin
  try
    FVI := FileVersionInfo(Application.ExeName);
    CurrAppVer := FVI.FileVersion;

    Result := False;
    TempConfig := HTTPRIOConfig as ConfigControllerSoapPort;
    ConfigObj := TempConfig.getClientVersion(CONFIG_ENERGYNET_CLIENT_VERSION);

    if ConfigObj <> nil then
    begin
      if ConfigObj.value = CurrAppVer then
        Result := True;
    end;

  except
    // Чтобы не срабатывало при потере связи
    on E: ESOAPHTTPException do
    begin
      Result := True;

      if IsRIOExecuting then
      begin
        Screen.Cursor := OldCursor;
        //WaitForm.Close;
        //WaitForm.Free;
        //WaitForm:=nil;

        ///// 16.03.06
        frmMain.sbMain.Panels[0].Text := '';
        frmMain.Update;

        IsRIOExecuting := false;
      end;

      raise;
    end
    else
      Result := False;
  end;
end;

procedure TfrmMain.HTTPRIOBeforeExecute(const MethodName: string; var SOAPRequest: WideString);
begin
 {
  WaitForm := WaitMessage('Внимание!',
                          'Подождите, пожалуйста' + #10#13 +
                          'Выполняется обработка данных...');
  WaitForm.Show;
  WaitForm.Update;
  }

  frmMain.sbMain.Panels[0].Text := ' Подождите, пожалуйста!  Выполняется загрузка данных...';
  frmMain.Update;

  OldCursor := Screen.Cursor;
  Screen.Cursor := crHourGlass;
  IsRIOExecuting := true;
  // Включить перехват окна запроса логина и пароля
  Timer1.Enabled := true;
end;

procedure TfrmMain.HTTPRIOAfterExecute(const MethodName: string; SOAPResponse: TStream);
var
  ArchiveFilePath, {ExtractedFilePath, }AppPath: string;
    tmpStream, tmpStream1: TMemoryStream;
    tmpUnZipper: TAbUnZipper;
begin
  if IsRIOExecuting then
  begin
    Screen.Cursor := OldCursor;
    //WaitForm.Close;
    //WaitForm.Free;
    //WaitForm:=nil;

        ///// 16.03.06
    frmMain.sbMain.Panels[0].Text := '';
    frmMain.Update;

    IsRIOExecuting := false;
  end;

  {*** 16.10.06 DL ***}
  if _IS_PACKED_RESPONSE then
  begin
    ///// ============== Распаковываем ответ сервера ==============/////

    AppPath := ExtractFilePath(Application.ExeName);
    ArchiveFilePath := AppPath + TempReportsPath + getFileName('tmpresponse') + IntToStr(GetTickCount) + '.gz';
    //ExtractedFilePath := AppPath + TempReportsPath + 'unknown';

    tmpStream := TMemoryStream.Create;
    try
      // Сохраняем сжатый ответ из потока во временный файл
      tmpStream.LoadFromStream(SOAPResponse);
      if not DirectoryExists(AppPath + TempReportsPath) then
        CreateDir(AppPath + TempReportsPath);
      tmpStream.SaveToFile(ArchiveFilePath);
    finally
      FreeAndNil(tmpStream);
    end;

  {  // Распаковываем ответ из временного файла
    tmpUnZipper := TAbUnZipper.Create(nil);
    try
      tmpUnZipper.BaseDirectory := AppPath + TempReportsPath;
      tmpUnZipper.FileName := ArchiveFilePath;
      tmpUnZipper.ExtractFiles('*.*');
    finally
      tmpUnZipper.Free;
    end;}

    tmpUnZipper := TAbUnZipper.Create(Self);
    try
      tmpUnZipper.BaseDirectory := AppPath + TempReportsPath;
      tmpUnZipper.FileName := ArchiveFilePath;
      //tmpUnZipper.ExtractFiles('*.*');

      tmpStream1 := TMemoryStream.Create;
      try
        // Распаковываем ответ из временного файла во временный поток
        tmpUnZipper.ExtractToStream('unknown', tmpStream1);
        // Копируем уже распаковаванный ответ обратно в исходный поток
        SOAPResponse.Position := 0;
        tmpStream1.Position := 0;
        SOAPResponse.CopyFrom(tmpStream1, tmpStream1.Size);
        SOAPResponse.Size := tmpStream1.Size;
        SOAPResponse.Position := 0;
      finally
        FreeAndNil(tmpStream1);
      end;

    finally
      tmpUnZipper.Free;
    end;

  {  // Копируем уже распаковаванный ответ обратно в поток
    tmpStream := TMemoryStream.Create;
    try
      tmpStream.LoadFromFile(ExtractedFilePath);
      SOAPResponse.Position := 0;
      tmpStream.Position := 0;
      SoapResponse.CopyFrom(tmpStream, tmpStream.Size);
      SoapResponse.Size := tmpStream.Size;
      SoapResponse.Position := 0;
    finally
      FreeAndNil(tmpStream);
    end;}

    // Удаляем все временные файлы
    if FileExists(ArchiveFilePath) then
      DeleteFile(ArchiveFilePath);
  //  if FileExists(ExtractedFilePath) then
  //    DeleteFile(ExtractedFilePath);
    ///// =========================================================/////
  end;
  {*** 16.10.06 DL ***}

  // Отключить перехват окна запроса логина и пароля
  Timer1.Enabled := false;
end;

procedure TfrmMain.HTTPRIOHTTPWebNodeBeforePost(const HTTPReqResp: THTTPReqResp; Data: Pointer);
/// 31.01.08
var
  TimeOut: integer;
///
begin
  //if not _IS_PACKED_RESPONSE then Exit;
  if _IS_PACKED_RESPONSE then
    HttpAddRequestHeaders(HTTPReqResp.Request, PChar('Accept-Encoding: gzip, deflate'), Length('Accept-Encoding: gzip, deflate'), HTTP_ADDREQ_FLAG_ADD);

  /// 31.01.08
   TimeOut := 10800000; // 3 часа


  InternetSetOption(Data, INTERNET_OPTION_RECEIVE_TIMEOUT, Pointer(@TimeOut), SizeOf(TimeOut));
{
  InternetSetOption(Data,
                    INTERNET_OPTION_SEND_TIMEOUT,
                    Pointer(@TimeOut),
                    SizeOf(TimeOut));

  InternetSetOption(Data,
                    INTERNET_OPTION_CONNECT_TIMEOUT,
                    Pointer(@TimeOut),
                    SizeOf(TimeOut));
}
  ///
end;


//=== Функция для удаления полос прокрутки на главной форме ===//
function ClientWindowProc(wnd: HWND; msg: Cardinal; wparam, lparam: Integer): Integer; stdcall;
var
  f: Pointer;
begin
//  if frmMain.sSkinManager1.Active then Exit;
  try
    f := Pointer(GetWindowLong(wnd, GWL_USERDATA));
    case msg of
      WM_NCCALCSIZE:
        if (GetWindowLong(wnd, GWL_STYLE) and (WS_HSCROLL or WS_VSCROLL)) <> 0 then
          SetWindowLong(wnd, GWL_STYLE, GetWindowLong(wnd, GWL_STYLE) and not (WS_HSCROLL or WS_VSCROLL));
    end;
    Result := CallWindowProc(f, wnd, msg, wparam, lparam);
  except
  end;
end;

procedure TfrmMain.miExecutedContractClick(Sender: TObject);
begin

    frmExecuteContract := TfrmExecuteContract.Create(Application, dsInsert);
  try
    frmExecuteContract.ShowModal;
  finally
    frmExecuteContract.Free;
 end;
end;

procedure TfrmMain.FormCreate(Sender: TObject);
var
  i: byte;
  isSkinsActive, isPlansModeSelect: Boolean;
  SkinName: string;
  TempUser: UserControllerSoapPort;
begin

  WindowState := wsMaximized;

  startTime := Now;

  //=== Прячем полосы прокрутки ===//
  if ClientHandle <> 0 then
  begin
    if GetWindowLong(ClientHandle, GWL_USERDATA) <> 0 then
      Exit;
    SetWindowLong(ClientHandle, GWL_USERDATA, SetWindowLong(ClientHandle, GWL_WNDPROC, Integer(@ClientWindowProc)));
  end;

  FormatSettings.DecimalSeparator := '.';
  SetHTTPRIOProps;
  for i := 1 to 3 do
  try
    if UserIsRegistered(UserName, Password) then
    begin

      if (setPasswrd) and (frmLogin.ModalResult = mrOk) then
      begin
        TempUser := HTTPRIOAuth as UserControllerSoapPort;
        TempUser.setPasswrd(userCode, frmLogin.edtConfirmPassword.Text);
      end;

      //...
      sbMain.Panels[2].Text := 'Користувач : ' + sessionUserName;

      ///// Включаем/отключаем скины
     // sSkinManager1.SkinDirectory := ExtractFilePath(Application.ExeName) + 'Skins';

      if RegValueExists(_SKINS_KEY, 'Active') then
      begin
        // 28.05.2013 +++ отключаем скины......
        RegWriteBool(_SKINS_KEY, 'Active', False);
        {
        isSkinsActive := RegReadBool(_SKINS_KEY, 'Active');
        SkinName := RegReadString(_SKINS_KEY, 'SkinName');

        if SkinName <> '' then
        begin
          // 24.01.12  Так как мы выносим скины из экзешника в отдельную папку,
          // попытаемся сделать так, чтобы установить скин, который был у пользователя до обновления
          // (название должно совпадать, только без слова "(internal)")
          if pos('(internal)', SkinName) > 0 then
            SkinName := copy(SkinName, 1, pos(' (internal)', SkinName) - 1);
          // Если такой файл не найден, ставим дефолтовский внутренний скин
          if not FileExists(sSkinManager1.SkinDirectory + '\' + SkinName + '.asz') then
          begin
            SkinName := 'Snow Leopard (internal)'; // <-- МЕНЯТЬ ДЕФОЛТНЫЙ СКИН ТУТ !!!!!
            RegWriteString(_SKINS_KEY, 'SkinName', SkinName);
          end;

          sSkinManager1.SkinName := SkinName;
        end;

        //sSkinManager1.Active := isSkinsActive;
        if isSkinsActive then
          sSkinManager1.Active := true;
        miIsSkinsActive.Checked := isSkinsActive;
      end
      else begin
        // Если в реестре ничего еще нет, включаем скин по умолчанию
        RegWriteBool(_SKINS_KEY, 'Active', true);

        SkinName := 'Snow Leopard (internal)'; // <-- МЕНЯТЬ ДЕФОЛТНЫЙ СКИН ТУТ !!!!!
        RegWriteString(_SKINS_KEY, 'SkinName', SkinName);

        sSkinManager1.SkinName := SkinName;
        sSkinManager1.Active := true;
        miIsSkinsActive.Checked := true;

      }
      end;

      /////

      ///// Включаем/отключаем возможность выбора режима отображения планов
      if RegValueExists(_INTERFACE_KEY, 'IsPlansModeSelect') then
      begin
        isPlansModeSelect := RegReadBool(_INTERFACE_KEY, 'IsPlansModeSelect');

        // 22.02.2016 В.С. Отключим выбор режима отображения планов
        isPlansModeSelect := false;

        miIsPlansModeSelect.Checked := isPlansModeSelect;

        if isPlansModeSelect then
        begin
          miENPlanWorks.Visible := false;
          miENPlanWorksNew.Visible := true;
        end
        else
        begin
          miENPlanWorks.Visible := true;
          miENPlanWorksNew.Visible := false;
        end;
      end
      else
      begin
        miENPlanWorks.Visible := true;
        miENPlanWorksNew.Visible := false;
      end;
      /////

      Exit;
    end
    else
       Continue;
    Close;
  except
    CheckUserException := true;
    raise;
  end;
  //ReportPluginsList:=TStringList.Create;
  //GetReportPlugins;
end;

procedure TfrmMain.RegisterFileType(ext: string; FileName: string);
var
  reg: TRegistry;
begin
  try
    reg := TRegistry.Create;
    with reg do
      begin
        RootKey := HKEY_CLASSES_ROOT;
        OpenKey('.' + ext, True);
        WriteString('', ext + 'file');
        CloseKey;
        CreateKey(ext + 'file');
        OpenKey(ext + 'file\DefaultIcon', True);
        WriteString('', FileName + ',0');
        CloseKey;
        OpenKey(ext + 'file\shell\open\command', True);
        WriteString('', FileName + ' "%1"');
        CloseKey;
        Free;
      end;
  except
  end;
end;

procedure TfrmMain.reportExportCentralGraphClick(Sender: TObject);
begin
    frmReportCentralExport := TfrmReportCentralExport.Create(Application, dsInsert);
  try
    frmReportCentralExport.ShowModal;
  finally
    frmReportCentralExport.Free;
  end;
end;


procedure TfrmMain.FormShow(Sender: TObject);
var
  AppVersion: string;
  FVI: TFileVersionInfo;
  userMembershipList: UserMembershipShortList;
  userMembershipFilter: AuthorizationController.UserMembershipFilter;
  TempUserMembership: UserMembershipControllerSoapPort;
  documentManagementVisible: Boolean;
  uActionFilter: AuthorizationController.UserActionFilter;
  uActionList: UserActionShortList;
  TempUserAction: UserActionControllerSoapPort;
begin

  if Unauthorized then
  begin
    Close;
    Exit;
  end;


  // при наличии прав можно изменить доверенность...
  changeWarrant := False;
  uActionFilter := AuthorizationController.UserActionFilter.Create;
  SetNullIntProps(uActionFilter);
  SetNullXSProps(uActionFilter);
  uActionFilter.userRef := UserRef.Create;
  uActionFilter.userRef.code := Low(Integer);
  uActionFilter.userRef.name := HTTPRIOAuth.HTTPWebNode.UserName;
  uActionFilter.action := AuthorizationController.Action.Create;
  uActionFilter.action.code := CHANGEWARRANT_ACTION;

  TempUserAction := HTTPRIOUserAction as UserActionControllerSoapPort;

  uActionList := TempUserAction.getScrollableFilteredList(uActionFilter, 0, -1);
  if (uActionList.totalCount > 0) then
    changeWarrant := True;

  // changeWarrant := (HTTPRIOAuth.HTTPWebNode.UserName = 'energynet');
  ///////////////////////////////////////////////////////

  N277.Visible := (HTTPRIOAuth.HTTPWebNode.UserName = 'energynet')  ;
  miGrRepSystem.Visible := (HTTPRIOAuth.HTTPWebNode.UserName = 'energynet');
  miBindingCounters2ServicesObject.Visible := ((HTTPRIOAuth.HTTPWebNode.UserName = 'energynet') or (HTTPRIOAuth.HTTPWebNode.UserName = 'marchenkoyp') or (HTTPRIOAuth.HTTPWebNode.UserName = 'MarchenkoYP'));
  //miFromSideObjectsGroup.Visible := ((HTTPRIOAuth.HTTPWebNode.UserName = 'energynet') or (HTTPRIOAuth.HTTPWebNode.UserName = 'marchenkoyp') or (HTTPRIOAuth.HTTPWebNode.UserName = 'MarchenkoYP'));

  //  SUPP-47224  скасування звіту -Прошу в програмному комплексі " Energy Net" вилучити звіт " Процент премії по персоналу по шкалі навантаження (енергозбутова частина, інспектора)
  prem_new_zbyt_insp.Visible := False;
//  N178_bonusList.Visible := (HTTPRIOAuth.HTTPWebNode.UserName = 'energynet');  // пока прикроем - когда на аксапте будем тогда открою
   // скроем т.к переходим на ведомость по премии с сохранением данных
   prem_new_tehn.Visible := (HTTPRIOAuth.HTTPWebNode.UserName = 'energynet');
   prem_new_zbyt.Visible := (HTTPRIOAuth.HTTPWebNode.UserName = 'energynet');

   miServicesRelaxationReestr.Visible := (HTTPRIOAuth.HTTPWebNode.UserName = 'energynet');
   miDocDistributionEE.Visible := (HTTPRIOAuth.HTTPWebNode.UserName = 'energynet');

//   if ((HTTPRIOAuth.HTTPWebNode.UserName = 'energynet') or
//      (HTTPRIOAuth.HTTPWebNode.UserName = 'PrometnayaOP')
//      ) then
//   reportExportCentralGraph.Visible := true;
//
//
//   if ((HTTPRIOAuth.HTTPWebNode.UserName = 'energynet') or
//      (HTTPRIOAuth.HTTPWebNode.UserName = 'PrometnayaOP')
//      ) then
//   miAnalyseCentralExport.Visible := true;



  SCEF := ExtractFilePath(Application.ExeName) + 'SupChart.exe';
  RegisterFileType('dsc', SCEF);
  //ShowMessage(SCEF);
  AppVersion := '';
  try
    FVI := FileVersionInfo(Application.ExeName);
    AppVersion := FVI.FileVersion;
    Caption := Caption + '  v.' + AppVersion;
  except
  end;

  // услуги на сторону
  // лезут добавлять, изменять и пишут, что не работает!!!!!!!!!!!!
  miENServicesObjects.Visible := (HTTPRIOAuth.HTTPWebNode.UserName = 'energynet');

  // miReportsKSOE.Visible := (HTTPRIOAuth.HTTPWebNode.UserName = 'LeontievaEV') or (HTTPRIOAuth.HTTPWebNode.UserName = 'promadmin') ;

  //miPriconnection.Visible := (HTTPRIOAuth.HTTPWebNode.UserName = 'energynet') or (HTTPRIOAuth.HTTPWebNode.UserName = 'promadmin');

  miENTechCondResponsibles.Visible := (HTTPRIOAuth.HTTPWebNode.UserName = 'energynet') or (HTTPRIOAuth.HTTPWebNode.UserName = 'promadmin') or (HTTPRIOAuth.HTTPWebNode.UserName = 'KichiyancVA');

  //miOMTSResponsibles.Visible := (HTTPRIOAuth.HTTPWebNode.UserName = 'energynet') or (HTTPRIOAuth.HTTPWebNode.UserName = 'promadmin');

  //miFKOrderOSExpl.Visible := (HTTPRIOAuth.HTTPWebNode.UserName = 'energynet');
  //miFKOrderOSMovement.Visible := (HTTPRIOAuth.HTTPWebNode.UserName = 'energynet');
  // miReconstrModernizacOZ.Visible := (HTTPRIOAuth.HTTPWebNode.UserName = 'energynet') or (HTTPRIOAuth.HTTPWebNode.UserName = 'SofyanikIP');
  // miStorage.Visible := (HTTPRIOAuth.HTTPWebNode.UserName = 'energynet');

  miENNomenclaturesOperative.Visible := (HTTPRIOAuth.HTTPWebNode.UserName = 'energynet');

  //miServicesConnections.Visible := (HTTPRIOAuth.HTTPWebNode.UserName = 'energynet');

  // SavickiyEA
  // miGeoCoordinates.Visible := (HTTPRIOAuth.HTTPWebNode.UserName = 'energynet') or (HTTPRIOAuth.HTTPWebNode.UserName = 'SavickiyEA');

  //Комментируя следующие строки, разрешаем всем смотреть Резерв мощности
  {miSubstation04PowerReserve.Visible := //Скрываем до выяснения лиц, формирующих
  (HTTPRIOAuth.HTTPWebNode.UserName = 'energynet') //Дополнение 7 к Правилам
  or (HTTPRIOAuth.HTTPWebNode.UserName = 'promadmin') //присоединения
  or (HTTPRIOAuth.HTTPWebNode.UserName = 'GoncharovVD')
  or (HTTPRIOAuth.HTTPWebNode.UserName = 'SavickiyEA')
  or (HTTPRIOAuth.HTTPWebNode.UserName = 'KnyazevaJN');}

  miDocFlowServices.Visible := ((HTTPRIOAuth.HTTPWebNode.UserName = 'energynet') or (HTTPRIOAuth.HTTPWebNode.UserName = 'ParfenovAU'));
  //miServicesRegistry.Visible := (HTTPRIOAuth.HTTPWebNode.UserName = 'energynet');

  miFile.Visible := (HTTPRIOAuth.HTTPWebNode.UserName <> 'energynet');
  miBeanShell.Visible := (HTTPRIOAuth.HTTPWebNode.UserName = 'energynet');
  miENSettings.Visible := (HTTPRIOAuth.HTTPWebNode.UserName = 'energynet');

//  miServicesRent.Visible := ((HTTPRIOAuth.HTTPWebNode.UserName = 'energynet') or
//                             (HTTPRIOAuth.HTTPWebNode.UserName = 'PetrivMI')  or
//                             (HTTPRIOAuth.HTTPWebNode.UserName = 'PerervaJG') or
//                             (HTTPRIOAuth.HTTPWebNode.UserName = 'ShevchenkoNN'));

  miReloadFinExecutor.Visible := (HTTPRIOAuth.HTTPWebNode.UserName = 'energynet');

  //miOrderCountersServices.Visible := (HTTPRIOAuth.HTTPWebNode.UserName = 'energynet');

  //miENFuelSheetVolumes.Visible := (HTTPRIOAuth.HTTPWebNode.UserName = 'energynet');

  //miRQOrg.Visible := (HTTPRIOAuth.HTTPWebNode.UserName = 'energynet');
  //miRQOrgBank.Visible := (HTTPRIOAuth.HTTPWebNode.UserName = 'energynet');
  //miFinContracts.Visible := (HTTPRIOAuth.HTTPWebNode.UserName = 'energynet');

  // miCustomerServices.Visible := (HTTPRIOAuth.HTTPWebNode.UserName = 'energynet');

  // V.S. 26.05.2015 +++ открыто для всех....
  documentManagementVisible := True;
  miDocumentManagement.Visible := True;

  userAdministrator := False;
  userOrdersCatalog := False;

  userMembershipFilter := AuthorizationController.UserMembershipFilter.Create;
  SetNullIntProps(userMembershipFilter);
  SetNullXSProps(userMembershipFilter);
  userMembershipFilter.userRef := UserRef.Create;
  userMembershipFilter.userRef.code := LOW_INT;
  userMembershipFilter.userRef.name := HTTPRIOAuth.HTTPWebNode.UserName;
  userMembershipFilter.groupRef := GroupRef.Create;
  userMembershipFilter.groupRef.code := USER_GROUP_ADMINISTRATORS;

  TempUserMembership := HTTPRIOUserMembership as UserMembershipControllerSoapPort;

  userMembershipList := TempUserMembership.getFilteredList(userMembershipFilter);
  if (userMembershipList.totalCount > 0) then
    userAdministrator := True;

  userMembershipFilter.groupRef.code := USER_GROUP_ORDERSCATALOG;
  userMembershipList := TempUserMembership.getFilteredList(userMembershipFilter);
  if (userMembershipList.totalCount > 0) then
    userOrdersCatalog := True;

  // miDocumentManagement.Visible := documentManagementVisible or (HTTPRIOAuth.HTTPWebNode.UserName = 'energynet');
  // miConnectionsTariff.Visible := documentManagementVisible or (HTTPRIOAuth.HTTPWebNode.UserName = 'energynet');

  miConnectionsTariff.Visible := False;
  userMembershipFilter.groupRef.code := USER_GROUP_CONNECTIONTARIFF;
  userMembershipList := TempUserMembership.getFilteredList(userMembershipFilter);
  if (userMembershipList.totalCount > 0) then
    miConnectionsTariff.Visible := True;

  // Отправка расчетных листов
  miSendingPaySheetsForEmployees.Visible := (HTTPRIOAuth.HTTPWebNode.UserName = 'energynet');
  userMembershipFilter.groupRef.code := USER_GROUP_PAYSHEETS_SENDING;
  userMembershipList := TempUserMembership.getFilteredList(userMembershipFilter);
  if (userMembershipList.totalCount > 0) then
    miSendingPaySheetsForEmployees.Visible := True;

  // miDocumentManagement.Visible := (HTTPRIOAuth.HTTPWebNode.UserName = 'energynet');
  // miServicesConsumerManagement.Visible := (HTTPRIOAuth.HTTPWebNode.UserName = 'energynet');

  ///  DEBUG !!! ///
  //miOrderAVZ.Visible := (HTTPRIOAuth.HTTPWebNode.UserName = 'energynet');

  //*  путевки в Энергетик - группа 901 */
  miEnergetic.Visible := False;
  miEnergetic.Visible := (HTTPRIOAuth.HTTPWebNode.UserName = 'energynet') or (HTTPRIOAuth.HTTPWebNode.UserName = 'MarchenkoYP');

  userMembershipFilter := AuthorizationController.UserMembershipFilter.Create;
  SetNullIntProps(userMembershipFilter);
  SetNullXSProps(userMembershipFilter);
  userMembershipFilter.userRef := UserRef.Create;
  userMembershipFilter.userRef.code := LOW_INT;
  userMembershipFilter.userRef.name := HTTPRIOAuth.HTTPWebNode.UserName;
  userMembershipFilter.groupRef := GroupRef.Create;
  userMembershipFilter.groupRef.code := USER_GROUP_SERVICESRELAXATION;

  TempUserMembership := HTTPRIOUserMembership as UserMembershipControllerSoapPort;

  try
    userMembershipList := TempUserMembership.getFilteredList(userMembershipFilter);
    if (userMembershipList.totalCount > 0) then
      miEnergetic.Visible := True;
  except
    raise;
  end;

  if (HTTPRIOAuth.HTTPWebNode.UserName = 'check1') or (HTTPRIOAuth.HTTPWebNode.UserName = 'check2') then
  begin
    mniChkRegClick(Sender);
  end;

  userMembershipFilter := AuthorizationController.UserMembershipFilter.Create;
  SetNullIntProps(userMembershipFilter);
  SetNullXSProps(userMembershipFilter);
  userMembershipFilter.userRef := UserRef.Create;
  userMembershipFilter.userRef.code := LOW_INT;
  userMembershipFilter.userRef.name := HTTPRIOAuth.HTTPWebNode.UserName;
  userMembershipFilter.groupRef := GroupRef.Create;
  userMembershipFilter.groupRef.code := USER_GROUP_OKSN_REPORT;

  TempUserMembership := HTTPRIOUserMembership as UserMembershipControllerSoapPort;

  try
    userMembershipList := TempUserMembership.getFilteredList(userMembershipFilter);
    if (userMembershipList.totalCount > 0) then
      N181.Visible := True;
  except
    raise;
  end;

  userMembershipFilter.groupRef.code := USER_GROUP_SYSTEM_ADMIN;
  try
    userMembershipList := TempUserMembership.getFilteredList(userMembershipFilter);
    if (userMembershipList.totalCount > 0) then
    begin
      N221.Visible := True;
      N222.Visible := True;
    end;
  except
    raise;
  end;

  // DEBUG!!!!!!
  // Журнал Звернень споживачів РЕЗ і ЕМ
  // miDocAppealRES.Visible :=
  //  (HTTPRIOAuth.HTTPWebNode.UserName = 'energynet') or (HTTPRIOAuth.HTTPWebNode.UserName = 'ParfenovAU');
  dfDepartmentCode := Low(Integer);

end;

procedure TfrmMain.Timer1Timer(Sender: TObject);
begin
  EnumWindows(@EnumProc, 0);
end;

procedure TfrmMain.Timer2Timer(Sender: TObject);
var
  DeltaTime: TDateTime;
begin
  DeltaTime := Now - startTime;
  sbMain.Panels[4].Text := ' :: ' + FormatDateTime('hh:mm:ss', DeltaTime) + ' :: ';
end;

procedure TfrmMain.TimerCheckVerTimer(Sender: TObject);
var
  timeNow: TDateTime;
begin

  timeNow := Time;

 if (TimeToStr(timeNow) >= '06:00') and (TimeToStr(timeNow) <= '21:00') then
  if not IsVersionCorrect then
  begin
    TimerCheckVer.OnTimer := CloseApp;

      Application.MessageBox(PChar('Ваша версія програми є застарілою та не може використовуватися далі.' + #13#10 + 'Для оновлення Вам необхідно закрити програму та запустити її заново.' + #13#10 + 'Через 5 хвилин програма буде закрита автоматично.' + #13#10 + #13#10 + 'Просимо вибачення за незручності.'), PChar('Необхідно оновити програму'), MB_ICONWARNING + MB_OK);

    Exit;
  end;
end;

procedure TfrmMain.CCTreeClick(Sender: TObject);
begin
   if not Assigned(frmCCTreeShow) then
    frmCCTreeShow := TfrmCCTreeShow.Create(Application, fmChild);
    frmCCTreeShow.Show;
end;

procedure TfrmMain.CloseApp(Sender: TObject);
begin
    Application.Terminate;
end;

procedure TfrmMain.aboutMenuItemClick(Sender: TObject);
var
  frmAbout: TfrmAbout;
begin
  frmAbout := TfrmAbout.Create(Application, dsEdit);
  try
    frmAbout.ShowModal;
  finally
    frmAbout.Free;
    frmAbout := nil;
  end;
end;

{Паспорт Воздушной Линии 6-10 кВ
  if not Assigned(frmENTraversTypeShow) then
    frmENTraversTypeShow := TfrmENTraversTypeShow.Create(Application,fmChild);
  frmEENTraversTypeShow.Show;

 if not Assigned(frmENPostTypeShow) then
    frmENPostTypeShow := TfrmENPostTypeShow.Create(Application,fmChild);
 frmEENPostTypeShow.Show;

 if not Assigned(frmENGroundTypeShow) then
    frmENGroundTypeShow := TfrmENGroundTypeShow.Create(Application,fmChild);
 frmEENGroundTypeShow.Show;

 if not Assigned(frmENStandTypeShow) then
    frmENStandTypeShow := TfrmENStandTypeShow.Create(Application,fmChild);
 frmEENStandTypeShow.Show;

 if not Assigned(frmENHookTypeShow) then
    frmENHookTypeShow := TfrmENHookTypeShow.Create(Application,fmChild);
 frmEENHookTypeShow.Show;}

{
procedure TfrmMain.N3Click(Sender: TObject);
begin
 if not Assigned(frmENShemaShow) then
    frmENShemaShow:=TfrmENShemaShow.Create(Application,fmChild);
 frmENShemaShow.Show;
end;


procedure TfrmMain.EditShemaMenuItemClick(Sender: TObject);
Var
 frmENShemaEdittingEdit : TfrmShemaEditting;
begin

  frmENShemaEdittingEdit:=TfrmShemaEditting.Create(Application, dsEdit);
  //ENShemaObj := ENShema.Create();


  try
    if frmENShemaEdittingEdit.ShowModal= mrOk then
      begin

      end;
  finally
   frmENShemaEdittingEdit.Free;
    frmENShemaEdittingEdit:=nil;
  end;

end;



procedure TfrmMain.ShemaMenuItemClick(Sender: TObject);
begin
 if not Assigned(frmENShemaShow) then
    frmENShemaShow:=TfrmENShemaShow.Create(Application,fmChild);
 frmENShemaShow.Show;
end;

procedure TfrmMain.SubstationTypeMenuItemClick(Sender: TObject);
//var
 //frmENSubstationTypeShow : TfrmENSubstationTypeShow ;
begin
 if not Assigned(frmENSubstationTypeShow) then
    frmENSubstationTypeShow := TfrmENSubstationTypeShow.Create(Application,fmChild);
 frmENSubstationTypeShow.Show;
end;

procedure TfrmMain.SubstationOwnerMenuItemClick(Sender: TObject);
begin
 if not Assigned(frmENSubstationOwnerShow) then
    frmENSubstationOwnerShow := TfrmENSubstationOwnerShow.Create(Application,fmChild);
 frmENSubstationOwnerShow.Show;
end;

procedure TfrmMain.SwitchOffReasonMenuItemClick(Sender: TObject);
begin
 if not Assigned(frmENSwitchReasonShow) then
    frmENSwitchReasonShow := TfrmENSwitchReasonShow.Create(Application,fmChild);
 frmENSwitchReasonShow.Show;
end;

procedure TfrmMain.ElemetsTypeMenuItemClick(Sender: TObject);
begin
 if not Assigned(frmENElementTypeShow) then
    frmENElementTypeShow := TfrmENElementTypeShow.Create(Application,fmChild);
 frmENElementTypeShow.Show;
end;

procedure TfrmMain.offHistoryMenuItemClick(Sender: TObject);
begin
 if not Assigned(frmENSwitchHistoryShow) then
    frmENSwitchHistoryShow := TfrmENSwitchHistoryShow.Create(Application,fmChild);
 frmENSwitchHistoryShow.Show;
end;

procedure TfrmMain.esT1Click(Sender: TObject);
var frmReports : TfrmReports;
begin
 //if not Assigned(frmReports) then
    frmReports := TfrmReports.Create(Application, dsEdit);
  try
    if frmReports.ShowModal= mrOk then
      begin

      end;
  finally
   frmReports.Free;
    frmReports:=nil;
  end;

end;

procedure TfrmMain.N351Click(Sender: TObject);
var frmReports : TfrmReports;
begin
 //if not Assigned(frmReports) then
    frmReports := TfrmReports.Create(Application, dsEdit);
  try
    if frmReports.ShowModal= mrOk then
      begin

      end;
  finally
   frmReports.Free;
    frmReports:=nil;
  end;
end;
}

{
procedure TfrmMain.N041Click(Sender: TObject);
begin
 if not Assigned(frmENLine04Show) then
    frmENLine04Show := TfrmENLine04Show.Create(Application,fmChild);
 frmENLine04Show.Show;
end;
}

procedure TfrmMain.N6101Click(Sender: TObject);
begin
 if not Assigned(frmENLine10Show) then
    frmENLine10Show := TfrmENLine10Show.Create(Application, fmChild);
 frmENLine10Show.Show;
end;

procedure TfrmMain.N610ChartNumbeControlIndicatorsClick(Sender: TObject);
begin
  frmCCRunReport := TfrmCCRunReport.Create(Application, dsEdit);
  try
    frmCCRunReport.reportName := TMenuItem(Sender).Caption;
    frmCCRunReport.ShowModal;
  finally
    frmCCRunReport.Free;
    frmCCRunReport := nil;
  end;

end;

procedure TfrmMain.N610Click(Sender: TObject);
begin
  frmEPRunReport:=TfrmEPRunReport.Create(Application, dsEdit);
  try
    frmEPRunReport.reportName:=TMenuItem(Sender).Caption;
    frmEPRunReport.ShowModal;
  finally
    frmEPRunReport.Free;
    frmEPRunReport:=nil;
  end;
end;

procedure TfrmMain.N621Click(Sender: TObject);
begin
  frmCCRunReport := TfrmCCRunReport.Create(Application, dsEdit);
  try
    frmCCRunReport.reportName := TMenuItem(Sender).Caption;
    frmCCRunReport.ShowModal;
  finally
    frmCCRunReport.Free;
    frmCCRunReport := nil;
  end;
end;

procedure TfrmMain.N2Click(Sender: TObject);
begin
 if not Assigned(frmENSubstation04Show) then
    frmENSubstation04Show := TfrmENSubstation04Show.Create(Application, fmChild);
 frmENSubstation04Show.Show;
end;

procedure TfrmMain.N041Click(Sender: TObject);
begin
 if not Assigned(frmENLine04Show) then
    frmENLine04Show := TfrmENLine04Show.Create(Application, fmChild);
 frmENLine04Show.Show;
end;

procedure TfrmMain.miENPlanWorksClick(Sender: TObject);
 { begin
  if not Assigned(frmENPlanWorkShow) then
     frmENPlanWorkShow := TfrmENPlanWorkShow.Create(Application,fmChild);
  frmENPlanWorkShow.Show; }

 {  28.12.2012 +++ пока всё по-старому}
var
  f: ENPlanWorkFilter;
begin
  f := ENPlanWorkFilter.Create();
  SetNullIntProps(f);
  SetNullXSProps(f);
  f.typeRef := ENPlanWorkTypeRef.Create;
  f.typeRef.code := TMenuItem(Sender).Tag;

  ////  TMenuItem.Tag   1 - обычный вид      ////
  ////  TMenuItem.Tag   2 - упрощенный вид   ////

  frmENPlanWorkShow := TfrmENPlanWorkShow.Create(Application, fmChild, f);
  try
    if TMenuItem(Sender).Name = 'miENPlanWorks' then
      frmENPlanWorkShow.Caption := 'Плани'
    else
      frmENPlanWorkShow.Caption := 'Плани - ' + TMenuItem(Sender).Caption;
    //frmENPlanWorkShow.Caption := TMenuItem(Sender).Caption;
    frmENPlanWorkShow.UpdateCaption;
    frmENPlanWorkShow.Show;
  finally

  end;
end;

procedure TfrmMain.miENReportAdditionZbytMetrologyClick(Sender: TObject);
begin
  if not Assigned(frmENReportAdditionZbytMetrologyShow) then
    frmENReportAdditionZbytMetrologyShow := TfrmENReportAdditionZbytMetrologyShow.Create(Application, fmChild);
  frmENReportAdditionZbytMetrologyShow.Show;
end;

procedure TfrmMain.N3Click(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName, strBudget, tmpmaskstring: string;
  i: integer;
  Flags: TReplaceFlags;
  strinmaterialGroupCodes: WideString;
  dep4nom, dep4nomCustom: string;
begin

  frmENForImplementation := TfrmENForImplementation.Create(Application, dsInsert);
  frmENForImplementation.HideControls([frmENForImplementation.CheckPr_mat_group], False);
  frmENForImplementation.HideControls([frmENForImplementation.edtTypeName, frmENForImplementation.lblTypeName, frmENForImplementation.spbType, frmENForImplementation.SpeedButton2], False);
  frmENForImplementation.Caption := 'Матеріали для виконання робіт';
  try

    frmENForImplementation.lblWorkState.Visible := True;
    frmENForImplementation.lblElementType.Visible := True;
    frmENForImplementation.edtWorkState.Visible := True;
    frmENForImplementation.cbElementType.Visible := True;
    frmENForImplementation.spbENPlanWorkState.Visible := True;
    frmENForImplementation.SpeedButton1.Visible := True;
    frmENForImplementation.GroupBox3.Visible := True;
    frmENForImplementation.chkOWN_PRODUCTION.Visible := True;

    frmENForImplementation.GroupBox4.Visible := false;
   frmENForImplementation.RadioGroup1.Visible := true;

    frmENForImplementation.HideControls([frmENForImplementation.ListBudget, frmENForImplementation.Label2, frmENForImplementation.btnCheckListAll, frmENForImplementation.btnClearCleckList, frmENForImplementation.Label1, frmENForImplementation.Label5], false);

    frmENForImplementation.HideControls([frmENForImplementation.lblENBudgetName, frmENForImplementation.edtENBudgetName, frmENForImplementation.spbENBudget, frmENForImplementation.spbENBudgetClear
]);
 //   frmENForImplementation.DisableControls([frmENForImplementation.btnOk]);


    if frmENForImplementation.ShowModal = mrOk then
    begin


          /// собираем строку кодов бюджетодержателей
      for i := 0 to frmENForImplementation.ListBudget.Count - 1 do
      begin
       if  frmENForImplementation.ListBudget.Checked[i] then
        if strBudget <>  '' then
            strBudget := strBudget + ' , ' + IntToStr(Integer(frmENForImplementation.ListBudget.Items.Objects[i]))
         else
            strBudget := strBudget + IntToStr(Integer(frmENForImplementation.ListBudget.Items.Objects[i]));

      end;

      // Parameters
      SetLength(argNames, 41);
      SetLength(args, 41);

      argNames[0] := 'yearGen';
      args[0] := frmENForImplementation.edtYearGen.Text;

      argNames[1] := 'monthGen';
      args[1] := IntToStr(frmENForImplementation.edtMonthGen.ItemIndex + 1);

      argNames[2] := 'renCode';
      args[2] := IntToStr(frmENForImplementation.renCode);

      argNames[3] := 'renName';
      if frmENForImplementation.renName <> '' then
        args[3] := frmENForImplementation.renName
      else
        args[3] := 'ХЕРСОНОБЛЕНЕРГО';

      argNames[4] := 'wholeYear';
      args[4] := IntToStr(Ord(frmENForImplementation.chbWholeYear.Checked));

      argNames[5] := 'dNameField';
      if frmENForImplementation.chbByRENs.Checked then
        args[5] := 'd.name'
      else
        args[5] := '1';

      argNames[6] := 'objNameField';
      if frmENForImplementation.chbByObjects.Checked then
        args[6] := '0'
      else
        args[6] := '1';

      argNames[7] := 'elementCode';
      args[7] := IntToStr(frmENForImplementation.elementCode);

      argNames[8] := 'elementName';
      if frmENForImplementation.elementName <> '' then
        args[8] := frmENForImplementation.elementName
      else
        args[8] := '';

      argNames[9] := 'budgName';
      if frmENForImplementation.budgetName <> '' then
        args[9] := frmENForImplementation.budgetName
      else
        args[9] := '';

      argNames[10] := 'budgCode';
      args[10] := IntToStr(frmENForImplementation.budgetCode);

      argNames[11] := 'dbNameField';
      if frmENForImplementation.chbByBudgets.Checked then
        args[11] := 'db.name'
      else
        args[11] := '1';

      argNames[12] := 'pKindCode';
      if frmENForImplementation.rbYear.Checked = true then
         args[12] := '1'   // годовой
      else
         args[12] := '2'; // текущий

      argNames[13] := 'kindrefcodemat';

      if frmENForImplementation.chkkindrefcodemat.Checked = True then
        args[13] := '2' // отчет с паливно мастильними материалами
      else
        args[13] := '1'; // отчет вытягивает материалы без паливно мастильних материалів.

      argNames[14] := 'stateworkcode';
      args[14] := IntToStr(frmENForImplementation.WorkStateCode);

      argNames[15] := 'stateworkname';
      if frmENForImplementation.WorkStateCode <> 0 then
      args[15] := frmENForImplementation.WorkStateName
      else
      args[15] := ' Всі типи робіт ';

      argNames[16] := 'elementtypecode';
      if (frmENForImplementation.cbElementType.ItemIndex = -1) or (frmENForImplementation.cbElementType.ItemIndex = 0) then
      args[16] := IntToStr(0)
      else
        args[16] := IntToStr(Integer(frmENForImplementation.cbElementTYpe.Items.Objects[frmENForImplementation.cbElementType.ItemIndex]));

      argNames[17] := 'elementtypename';
      if (frmENForImplementation.cbElementType.ItemIndex = -1) or (frmENForImplementation.cbElementType.ItemIndex = 0) then
      args[17] := ' Всі типи об"єктів   '
      else
     // args[17] := frmENForImplementation.cbElementTYpe.Items.ValueFromIndex[Integer(frmENForImplementation.cbElementTYpe.Items.Objects[frmENForImplementation.cbElementType.ItemIndex]) ] ;
        args[17] := frmENForImplementation.ObjectName;  // frmENForImplementation.cbElementTYpe.Items.Objects[frmENForImplementation.cbElementType.ItemIndex]
{
dbNameField
budgCode
budgName
elementName
}
      ///////
      argNames[18] := 'pr_par_mat';
      if frmENForImplementation.CheckPr_mat_group.Checked = True then
        args[18] := '1'
      else
        args[18] := '0';

    if frmENForImplementation.GroupBox3.Visible = true then
     begin
        argNames[19] := 'planform';
        if ((frmENForImplementation.chkFormWorkPozaPlan.Checked = True) and (frmENForImplementation.chkFormWorkPlan.Checked = True)) then
        args[19] := '0'
        else if frmENForImplementation.chkFormWorkPlan.Checked = True then // плановые работы
        args[19] := '1'
        else if frmENForImplementation.chkFormWorkPozaPlan.Checked = True then   // внеплановые работы
        args[19] := '2';

     end;

    argNames[20] := 'materialGroupCode';
    if frmENForImplementation.materialGroupCode > 0 then
      args[20] := IntToStr(frmENForImplementation.materialGroupCode)
    else
      args[20] := IntToStr(0);

    argNames[21] := 'typerefcode';
    if frmENForImplementation.WorkTypeCode <> 0 then
      args[21] := IntToStr(frmENForImplementation.WorkTypeCode)
    else
      args[21] := IntToStr(0);

      argNames[22] := 'pr_identid';
      if ((frmENForImplementation.CheckPr_identid.Checked and frmENForImplementation.rbDK1997.Checked) or (frmENForImplementation.edtIdentid.EditText <> '__.__.__.___._____')) then
        args[22] :=  '1'
      else if ((frmENForImplementation.CheckPr_identid.Checked and frmENForImplementation.rbDK2010.Checked) or (frmENForImplementation.edtIdentid.EditText <> '__.__.__.___._____')) then
         args[22] := '2'
      else if ((frmENForImplementation.CheckPr_identid.Checked and frmENForImplementation.rbDK2015.Checked) or (frmENForImplementation.edtIdentid.EditText <> '__.__.__.___._____')) then
         args[22] := '3'
      else
         args[22] := '0';

      argNames[23] := 'estimatestatus';

    { if  (( frmENForImplementation.chkzamovl.Checked = true )
      and ( frmENForImplementation.chknezamovl.Checked = false )
      and ( frmENForImplementation.chkNayavn.Checked = false ) )   then
         args[23] :=  ' and es.statusrefcode = 2 ' ; // заказаные
     if  (( frmENForImplementation.chkzamovl.Checked = false )
      and ( frmENForImplementation.chknezamovl.Checked = true )
      and ( frmENForImplementation.chkNayavn.Checked = false ) )   then
         args[23] :=  ' and (  ( es.statusrefcode not in (2,3)  )  ' +
                           '    or ' +
                           '    (    ' +
                           '      ( es.statusrefcode = 3 )   ' +
                           '       and ' +
                           '      ((select *  from get_pr_estimate(es.code))  = 0 )    ' +
                           '     ) ' +
                           ' )  '
                    ;// не  заказаные

     if  (( frmENForImplementation.chkzamovl.Checked = false )
      and ( frmENForImplementation.chknezamovl.Checked = false )
      and ( frmENForImplementation.chkNayavn.Checked = true ) )   then
         args[23] :=  ' and es.statusrefcode = 3 ' ; // в наличии
     if  (( frmENForImplementation.chkzamovl.Checked = true )
      and ( frmENForImplementation.chknezamovl.Checked = true )
      and ( frmENForImplementation.chkNayavn.Checked = true ) )   then
         args[23] :=  ' and 1 = 1 '  ; // заказаные не заказаные тоесть все
     if  (( frmENForImplementation.chkzamovl.Checked = true )
      and ( frmENForImplementation.chknezamovl.Checked = true )
      and ( frmENForImplementation.chkNayavn.Checked = false ) )   then
         args[23] :=  ' and (  ( es.statusrefcode <> 3  )  ' +
                           '    or ' +
                           '    (    ' +
                           '      ( es.statusrefcode = 3 )   ' +
                           '       and ' +
                           '      ((select *  from get_pr_estimate(es.code))  = 0 )    ' +
                           '     ) ' +
                           ' )  '  ; ; // заказаные не заказаные но кроме в наличии
     if  (( frmENForImplementation.chkzamovl.Checked = true )
      and ( frmENForImplementation.chknezamovl.Checked = false )
      and ( frmENForImplementation.chkNayavn.Checked = true ) )   then
         args[23] :=  ' and es.statusrefcode in (2,3) ' ; // заказаные и в наличии
     if  (( frmENForImplementation.chkzamovl.Checked = false )
      and ( frmENForImplementation.chknezamovl.Checked = true )
      and ( frmENForImplementation.chkNayavn.Checked = true ) )   then
         args[23] :=  ' and  ( es.statusrefcode <> 2 or es.statusrefcode = 3 )   ' ; // не заказаные и в наличии
     if  (( frmENForImplementation.chkzamovl.Checked = false )
      and ( frmENForImplementation.chknezamovl.Checked = false )
      and ( frmENForImplementation.chkNayavn.Checked = false ) )   then
         args[23] :=  ' and 1 = 1 ' ; // без фильтра по статусу  }

      if ((frmENForImplementation.rbzamovl.Checked = true) and (frmENForImplementation.rbnezamovl.Checked = false) and (frmENForImplementation.rball.Checked = false) and (frmENForImplementation.rbnotNepotrNotNayavn.Checked = false) and (frmENForImplementation.rbAllstatus.Checked = false) and (frmENForImplementation.rbNezakazanie.Checked = false)) then
        args[23] := ' and es.statusrefcode = 2 '; // заказаные

      if ((frmENForImplementation.rbzamovl.Checked = false) and (frmENForImplementation.rbnezamovl.Checked = true) and (frmENForImplementation.rball.Checked = false) and (frmENForImplementation.rbnotNepotrNotNayavn.Checked = false) and (frmENForImplementation.rbAllstatus.Checked = false) and (frmENForImplementation.rbNezakazanie.Checked = false)) then
         //args[23] :=  ' and (  ( es.statusrefcode not in (1,2,3,5)  )  ' +
         {args[23] :=  ' and (  ( es.statusrefcode = 1  )  ' +
                           '    or ' +
                           '    (    ' +
                           '      ( es.statusrefcode = 3 )   ' +
                           '       and ' +
                           '      ((select *  from get_pr_estimate(es.code))  = 0 )    ' +
                           '     ) ' +
                           '    or ' +
                           '    (    ' +
                           '      ( es.statusrefcode = 1 )   ' +
                           '       and ' +
                           '      ((select *  from get_pr_estimate(es.code))  = 0 )    ' +
                           '     ) ' +
                           ' )  '; }
          // 30.08.2016 - c Салыгиным решили что не проверять вручную был поставлен статус - в наявности или нет . просто смотрим на статус
        args[23] := ' and (  ( es.statusrefcode = 1  ) or ( es.statusrefcode = 3 ) )'; // не  заказаные

      if ((frmENForImplementation.rbzamovl.Checked = false) and (frmENForImplementation.rbnezamovl.Checked = false) and (frmENForImplementation.rball.Checked = true) and (frmENForImplementation.rbnotNepotrNotNayavn.Checked = false) and (frmENForImplementation.rbAllstatus.Checked = false) and (frmENForImplementation.rbNezakazanie.Checked = false)) then
        args[23] := ' and es.statusrefcode <> 5 '; // заказаные не заказаные тоесть все

      if ((frmENForImplementation.rbzamovl.Checked = false) and (frmENForImplementation.rbnezamovl.Checked = false) and (frmENForImplementation.rball.Checked = false) and (frmENForImplementation.rbnotNepotrNotNayavn.Checked = true) and (frmENForImplementation.rbAllstatus.Checked = false) and (frmENForImplementation.rbNezakazanie.Checked = false)) then
        args[23] := ' and es.statusrefcode not in (3,5,2) '; // кроме  ненужных и те которые со статусом в наличии и заявках

       // все статусы
      if ((frmENForImplementation.rbzamovl.Checked = false) and (frmENForImplementation.rbnezamovl.Checked = false) and (frmENForImplementation.rball.Checked = false) and (frmENForImplementation.rbnotNepotrNotNayavn.Checked = false) and (frmENForImplementation.rbAllstatus.Checked = true) and (frmENForImplementation.rbNezakazanie.Checked = false)) then
        args[23] := ' and 1=1 '; //

        // только незаказанные материалы
      if ((frmENForImplementation.rbzamovl.Checked = false) and (frmENForImplementation.rbnezamovl.Checked = false) and (frmENForImplementation.rball.Checked = false) and (frmENForImplementation.rbnotNepotrNotNayavn.Checked = false) and (frmENForImplementation.rbAllstatus.Checked = false) and (frmENForImplementation.rbNezakazanie.Checked = true)) then
        args[23] := ' and es.statusrefcode = 1 '; //

      argNames[24] := 'budgetstringcode';
       if strBudget <> '' then
       args[24] :=  ' p.budgetrefcode in (  ' + strBudget + ')'
       else
       args[24] := ' 1 = 1 ';

       // распределение по типам заявок в которые должны попасть материалы из планов
      argNames[25] := 'pBudgOrInv';
      if ((frmENForImplementation.cbOrderTypeBudg.Checked = True) and (frmENForImplementation.cbOrderTypeInvest.Checked = True)) then
        args[25] := '0'
      else if ((frmENForImplementation.cbOrderTypeBudg.Checked = False) and (frmENForImplementation.cbOrderTypeInvest.Checked = False)) then
        args[25] := '0'
      else if ((frmENForImplementation.cbOrderTypeBudg.Checked = True) and (frmENForImplementation.cbOrderTypeInvest.Checked = False)) then
        args[25] := '1'
      else if ((frmENForImplementation.cbOrderTypeBudg.Checked = False) and (frmENForImplementation.cbOrderTypeInvest.Checked = True)) then
        args[25] := '2';

      argNames[27] := 'kodmask';
      args[27] := frmENForImplementation.edtIdentid.EditText;

      argNames[28] := 'IsNotEnergozbyt';
       if  frmENForImplementation.chkIsNotEnergozbyt.Checked then
       args[28] := '1'
       else
       args[28] := '0';

       strinmaterialGroupCodes := frmENForImplementation.materialGroupCodes;
       argNames[29] := 'materialGroupCodes';
       if frmENForImplementation.materialGroupCodes <> '' then
       args[29] := ' and sm.code in (' + strinmaterialGroupCodes + ')'
       else
       args[29] := ' and 1 = 1';

       argNames[30] := 'planStatusForPozaplanPlans';
      if ((frmENForImplementation.chkEnplanworkStatus1.Checked = True) and (frmENForImplementation.chkEnplanworkStatus3.Checked = True)) then
       args[30] := '13'        // по внеплановым планам черновым и утвержденным вместе
      else if ((frmENForImplementation.chkEnplanworkStatus1.Checked = True) and (frmENForImplementation.chkEnplanworkStatus3.Checked = False)) then
       args[30] := '1'        // по внеплановым планам черновым  только
      else if ((frmENForImplementation.chkEnplanworkStatus1.Checked = False) and (frmENForImplementation.chkEnplanworkStatus3.Checked = True)) then
       args[30] := '3'        // по внеплановым планам утвержденным  только
      else if ((frmENForImplementation.chkEnplanworkStatus1.Checked = False) and (frmENForImplementation.chkEnplanworkStatus3.Checked = False)) then
       args[30] := '0';        // без фильтра по статусу позапланового плана

       argNames[31] := 'planStatusForInvest';
      if ((frmENForImplementation.chkEnplanworkStatus1_Invest.Checked = True) and (frmENForImplementation.chkEnplanworkStatus3_Invest.Checked = True)) then
       args[31] := '13'        // по планам  инвест программы черновым и утвержденным вместе
      else if ((frmENForImplementation.chkEnplanworkStatus1_Invest.Checked = True) and (frmENForImplementation.chkEnplanworkStatus3_Invest.Checked = False)) then
       args[31] := '1'        // по  планам инвест программы черновым  только
      else if ((frmENForImplementation.chkEnplanworkStatus1_Invest.Checked = False) and (frmENForImplementation.chkEnplanworkStatus3_Invest.Checked = True)) then
       args[31] := '3'        // по планам инвест программы утвержденным  только
      else if ((frmENForImplementation.chkEnplanworkStatus1_Invest.Checked = False) and (frmENForImplementation.chkEnplanworkStatus3_Invest.Checked = False)) then
       args[31] := '0';        // без фильтра по статусу  плана инвест программы

      argNames[32] := 'symbolcount1997';
        tmpmaskstring :=   frmENForImplementation.edtIdentid.EditText;
       if  frmENForImplementation.edtSymbolcount1997.Text = '' then
          if  frmENForImplementation.edtIdentid.EditText <> '__.__.__.___._____' then
          begin
          Flags := [rfReplaceAll];
            // если в фильтре по маске указали какие то цифры то вычесляем кол-во знаков

          args[32] := IntToStr(Length(Trim(StringReplace(StringReplace(tmpmaskstring, '_', '', Flags), '.', '', Flags))));
          end
          else
          args[32] := '0'
       else
        args[32] := frmENForImplementation.edtSymbolcount1997.Text;

      argNames[33] := 'symbolcount2010';
       args[33] := frmENForImplementation.edtSymbolcount2010.Text;

      argNames[34] := 'is_works_finished';
       if frmENForImplementation.chk_enplanworkstatus_works_finished.Checked = true then
        args[34] := '1'
       else
        args[34] := '0';

      argNames[35] := 'symbolcount2015';
       args[35] := frmENForImplementation.edtSymbolcount2015.Text;

       if frmENForImplementation.cbWithNN.Checked then
       begin
        argNames[36] := 'withNN';
              args[36] := '1';
              argNames[37] := 'dep4nom';
              dep4nom := '(';

              if frmENForImplementation.cbDepNomCur.Checked then
               dep4nom := dep4nom + ' substr(d.div_code, 1, 2) = ''43''';

              if frmENForImplementation.cbDepNomGP.Checked then
              begin
              if (Length(dep4nom) > 1) then
               dep4nom := dep4nom + ' or ';
               dep4nom := dep4nom + ' substr(d.div_code, 1, 2) = ''39''';
              end;

              if frmENForImplementation.cbDepNomVLep.Checked then
              begin
              if (Length(dep4nom) > 1) then
               dep4nom := dep4nom + ' or ';
               dep4nom := dep4nom + ' substr(d.div_code, 1, 2) = ''70''';
              end;

              if frmENForImplementation.cbDepNomVSP.Checked then
              begin
              if (Length(dep4nom) > 1) then
               dep4nom := dep4nom + ' or ';
               dep4nom := dep4nom + ' substr(d.div_code, 1, 2) = ''38''';
              end;

              if frmENForImplementation.cbDepNomSK.Checked then
              begin
              if (Length(dep4nom) > 1) then
               dep4nom := dep4nom + ' or ';
               dep4nom := dep4nom + ' substr(d.div_code, 1, 2) = ''41''';
              end;

              if frmENForImplementation.cbDepNomGEN.Checked then
              begin
              if (Length(dep4nom) > 1) then
               dep4nom := dep4nom + ' or ';
               dep4nom := dep4nom + ' substr(d.div_code, 1, 2) = ''72''';
              end;

              if frmENForImplementation.cbDepNomKAH.Checked then
              begin
              if (Length(dep4nom) > 1) then
               dep4nom := dep4nom + ' or ';
               dep4nom := dep4nom + ' substr(d.div_code, 1, 2) = ''75''';
              end;

              if frmENForImplementation.cbDepNomIVA.Checked then
              begin
              if (Length(dep4nom) > 1) then
               dep4nom := dep4nom + ' or ';
               dep4nom := dep4nom + ' substr(d.div_code, 1, 2) = ''76''';
              end;

              if frmENForImplementation.cbDepNomNTR.Checked then
              begin
              if (Length(dep4nom) > 1) then
               dep4nom := dep4nom + ' or ';
               dep4nom := dep4nom + ' substr(d.div_code, 1, 2) = ''78''';
              end;

              if frmENForImplementation.cbDepNomCHA.Checked then
              begin
              if (Length(dep4nom) > 1) then
               dep4nom := dep4nom + ' or ';
               dep4nom := dep4nom + ' substr(d.div_code, 1, 2) = ''79''';
              end;

              if frmENForImplementation.cbDepNomNKAH.Checked then
              begin
              if (Length(dep4nom) > 1) then
               dep4nom := dep4nom + ' or ';
               dep4nom := dep4nom + ' substr(d.div_code, 1, 2) = ''80''';
              end;

              if frmENForImplementation.cbDepNomHGES.Checked then
              begin
              if (Length(dep4nom) > 1) then
               dep4nom := dep4nom + ' or ';
               dep4nom := dep4nom + ' substr(d.div_code, 1, 2) = ''51''';
              end;

              if frmENForImplementation.cbDepNomSKLAD.Checked then
              begin
              if (Length(dep4nom) > 1) then
               dep4nom := dep4nom + ' or ';
               dep4nom := dep4nom + ' d.div_code in (''1889'', ''1888'', ''1823'')';
              end;

              if Length(frmENForImplementation.edtMOL.Text) > 0 then
               begin
                dep4nomCustom := StringReplace(frmENForImplementation.edtMOL.Text, '*', '%', [rfReplaceAll]);
                 if (Length(dep4nom) > 1) then
                 dep4nom := dep4nom + ' or ';
                 dep4nom := dep4nom + ' d.div_code like (''%' + dep4nomCustom + '%'')';
               end;

               dep4nom := dep4nom + ')';

              args[37] := dep4nom;
       end;

       // показывать или нет материалы по собственному производдству SUPP-53854
      argNames[38] := 'isOWN_PRODUCTION';
       if frmENForImplementation.chkOWN_PRODUCTION.Checked = true then
        args[38] := '0'
       else
        args[38] := '1';

      argNames[39] := 'show_last_buy_partner';
      if frmENForImplementation.chkDisplayLast_buy_partner.Checked then
      args[39] := '1'
      else
      args[39] := '0';


      argNames[40] := 'objNameFinExecutor';
      if frmENForImplementation.chbByFinExecutor.Checked then
        args[40] := '0'
      else
        args[40] := '1';




////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////
////////////////////////////////////////////////////////////////

      reportName := '';
      if ((not frmENForImplementation.CheckPr_identid.Checked) and (frmENForImplementation.edtIdentid.EditText = '__.__.__.___._____')) then
         begin
        if frmENForImplementation.chbByMonths.Checked then
              reportName := 'materialsWithMonths'
        else
               reportName := 'materialsNoMonths';
         end;

      if ((frmENForImplementation.CheckPr_identid.Checked) or (frmENForImplementation.edtIdentid.EditText <> '__.__.__.___._____')) then
         begin
        if frmENForImplementation.chbByMonths.Checked then
               reportName := 'materialsWithMonthsCodeKl'
        else
               reportName := 'materialsNoMonthsCodeKl';
         end;

         // 29.12.2016 extra mega srochno nada viborki for Shevchenko ///// potom ubrat nah vizov etogo REPORTa
         //reportName := 'tempReport/materialsWithMonths';

           makeReport(reportName, argNames, args, 'xls');

    end;
  finally
    frmENForImplementation.Free;
  end;
end;

procedure TfrmMain.N3DrawItem(Sender: TObject; ACanvas: TCanvas; ARect: TRect; Selected: Boolean);
var
  LeftPos: Integer;
  TopPos: Integer;
  TextLength: Integer;
  Text: string;
begin
  Text := (Sender as TMenuItem).Caption;
  //ACanvas.Brush.Color := clGray;
  ACanvas.FillRect(ARect);
  ACanvas.Font.Color := $00342695; //clWhite;
  ACanvas.Font.Style := [fsBold];
  // Draw right in the middle of the menu
  TopPos := ARect.Top + (ARect.Bottom - ARect.Top - ACanvas.TextHeight('W')) div 2;
  TextLength := Length(Text);
//  if TextLength > (ARect.Right - ARect.Left) then
//    LeftPos := ARect.Left + 3
//  else
//    LeftPos := ARect.Left + (ARect.Right - ARect.Left -
//      ACanvas.TextWidth(Text)) div 2;

  LeftPos := ARect.Left + 10;
  ACanvas.TextOut(LeftPos, TopPos, Text);
end;

procedure TfrmMain.N171Click(Sender: TObject);
begin
   if not Assigned(frmENCoefficientExecPlanShow) then
    frmENCoefficientExecPlanShow := TfrmENCoefficientExecPlanShow.Create(Application, fmChild);
  frmENCoefficientExecPlanShow.Show;
end;

procedure TfrmMain.N174SumIbfoAccommodationServicesClick(Sender: TObject);
begin
    frmServicesRelaxationReport := TfrmServicesRelaxationReport.Create(Application, dsInsert);
    frmServicesRelaxationReport.report_vid := 2; // узагальююча інформація
  try
    frmServicesRelaxationReport.ShowModal;
  finally
    frmServicesRelaxationReport.Free;
  end;
end;

procedure TfrmMain.miReportExecuteDfWorkClick(Sender: TObject);
begin
    FrmExecutionWork := TFrmExecutionWork.Create(Application, dsInsert);
  try
    FrmExecutionWork.ShowModal;
  finally
    FrmExecutionWork.Free;
  end;
end;

procedure TfrmMain.miReportInstallCounterIPClick(Sender: TObject);
begin
   frmReportInstallCountersIP := TfrmReportInstallCountersIP.Create(Application, dsInsert);
 try
   frmReportInstallCountersIP.ShowModal;
 finally
   frmReportInstallCountersIP.Free;
 end;
end;

procedure TfrmMain.miServicesRelaxationClick(Sender: TObject);
var
  f: ENServicesObjectFilter;
begin
  f := ENServicesObjectFilter.Create;
  SetNullIntProps(f);
  SetNullXSProps(f);

  f.contractTypeRef := ENServicesContractTypeRef.Create;
  f.contractTypeRef.code := TMenuItem(Sender).Tag;

  f.contractKindRef := ENServicesContractKindRef.Create;
  f.contractKindRef.code := SERVICES_CONTRACT_KIND_RELAXATION;

  if (f.contractTypeRef.code = ENSERVICESOBJECTTYPE_RELAXATION) then
  begin
    if not Assigned(frmENServicesRelaxationShow) then
      frmENServicesRelaxationShow := TfrmENServicesRelaxationShow.Create(Application, fmChild, f);
    frmENServicesRelaxationShow.Caption := TMenuItem(Sender).Caption;
    frmENServicesRelaxationShow.UpdateCaption;
    frmENServicesRelaxationShow.Show;
  end
  else
    ShowMessage('Ошибочка в определении типа договора...');
end;

procedure TfrmMain.miServicesRelaxationReestrClick(Sender: TObject);
begin
    frmServicesRelaxationReport := TfrmServicesRelaxationReport.Create(Application, dsInsert);
    frmServicesRelaxationReport.report_vid := 1;
  try
    frmServicesRelaxationReport.ShowModal;
  finally
    frmServicesRelaxationReport.Free;
  end;
end;

procedure TfrmMain.miServicesRentClick(Sender: TObject);
var
  f: ENServicesObjectFilter;
begin
  f := ENServicesObjectFilter.Create;
  SetNullIntProps(f);
  SetNullXSProps(f);

  f.contractTypeRef := ENServicesContractTypeRef.Create;
  f.contractTypeRef.code := TMenuItem(Sender).Tag;

  if (f.contractTypeRef.code = ENSERVICESOBJECTTYPE_OKSN) then
  begin
    if not Assigned(frmENServicesRentShow) then
      frmENServicesRentShow := TfrmENServicesRentShow.Create(Application, fmChild, f);
    frmENServicesRentShow.Caption := TMenuItem(Sender).Caption;
    frmENServicesRentShow.ToolButton6.Enabled := false;   // Добавлять договора через подсистему (Спільний підвіс ВОЛЗ)
    frmENServicesRentShow.UpdateCaption;
    frmENServicesRentShow.Show;
  end
  else
    ShowMessage('Ошибочка в определении типа договора...');
end;

procedure TfrmMain.N17Click(Sender: TObject);
var
  argNames, args: ArrayOfString;
begin
{
  frmENPeriod := TfrmENPeriod.Create(Application, dsInsert);
  try
    if frmENPeriod.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 2);
      SetLength(args, 2);

      argNames[0] := 'yearGen';
      args[0] := frmENPeriod.edtYearGen.Text;

      argNames[1] := 'monthGen';
      args[1] := IntToStr(frmENPeriod.edtMonthGen.ItemIndex + 1);
      ///////

      makeReport('materialByMonth', argNames, args, 'pdf');
    end;
  finally
    frmENPeriod.Free;
  end;
}

  frmENPeriodWithREN := TfrmENPeriodWithREN.Create(Application, dsInsert);
  try
    if frmENPeriodWithREN.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 5);
      SetLength(args, 5);

      argNames[0] := 'yearGen';
      args[0] := frmENPeriodWithREN.edtYearGen.Text;

      argNames[1] := 'monthGen';
      args[1] := IntToStr(frmENPeriodWithREN.edtMonthGen.ItemIndex + 1);

      argNames[2] := 'renCode';
      args[2] := IntToStr(frmENPeriodWithREN.renCode);

      argNames[3] := 'renName';
      if frmENPeriodWithREN.renName <> '' then
        args[3] := frmENPeriodWithREN.renName
      else
        args[3] := 'ХОЕ';

      argNames[4] := 'wholeYear';
      args[4] := IntToStr(Ord(frmENPeriodWithREN.chbWholeYear.Checked));
      ///////

      makeReport('materialByMonth', argNames, args, 'xls');
    end;
  finally
    frmENPeriodWithREN.Free;
  end;
end;

procedure TfrmMain.N180Click(Sender: TObject);
var
  f: ENServicesObjectFilter;
begin
  f := ENServicesObjectFilter.Create;
  SetNullIntProps(f);
  SetNullXSProps(f);

  f.contractTypeRef := ENServicesContractTypeRef.Create;
  f.contractTypeRef.code := TMenuItem(Sender).Tag;

  if (f.contractTypeRef.code = ENSERVICESOBJECTTYPE_PROJECT) then
  begin
    if not Assigned(frmENServicesProjectShow) then
      frmENServicesProjectShow := TfrmENServicesProjectShow.Create(Application, fmChild, f);
    frmENServicesProjectShow.Caption := TMenuItem(Sender).Caption;
    frmENServicesProjectShow.UpdateCaption;
    frmENServicesProjectShow.Show;
  end
  else
    ShowMessage('Ошибочка в определении типа договора...');
end;

procedure TfrmMain.N181Click(Sender: TObject);
begin

  frmReportJLC := TfrmReportJLC.Create(Application, dsInsert);
  try
    frmReportJLC.ShowModal;
  finally
    frmReportJLC.Free;
  end;

end;

procedure TfrmMain.N182Click(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin

  frmAward := TfrmAward.Create(Application, dsInsert);

  try
    if frmAward.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 2);
      SetLength(args, 2);

      argNames[0] := 'mon';
      args[0] := IntToStr(frmAward.cbMon.ItemIndex + 1);

      argNames[1] := 'year';
      args[1] := frmAward.cbYear.Text;

      reportName := 'RepEnergozbyt/Award/group_main';
      makeReport(reportName, argNames, args, 'xls');
   end;
  finally
    frmAward.Free;
  end;

end;

procedure TfrmMain.N184ChartDeviationsFactsTVENormClick(Sender: TObject);
begin
  frmCCRunReport := TfrmCCRunReport.Create(Application, dsEdit);
  try
    frmCCRunReport.reportName := TMenuItem(Sender).Caption;
    frmCCRunReport.ShowModal;
  finally
    frmCCRunReport.Free;
    frmCCRunReport := nil;
  end;
end;

procedure TfrmMain.N185ScheduleSAIFIReliabilityIndicatorClick(Sender: TObject);
begin
  frmCCRunReport := TfrmCCRunReport.Create(Application, dsEdit);
  try
    frmCCRunReport.reportName := TMenuItem(Sender).Caption;
    frmCCRunReport.ShowModal;
  finally
    frmCCRunReport.Free;
    frmCCRunReport := nil;
  end;
end;

procedure TfrmMain.N186ScheduleSaidiReliabilityIndicatorClick(Sender: TObject);
begin
  frmCCRunReport := TfrmCCRunReport.Create(Application, dsEdit);
  try
    frmCCRunReport.reportName := TMenuItem(Sender).Caption;
    frmCCRunReport.ShowModal;
  finally
    frmCCRunReport.Free;
    frmCCRunReport := nil;
  end;
end;

procedure TfrmMain.N187SchedulEjectionComparedSamePeriodLastYearClick(Sender: TObject);
begin
  frmCCRunReport := TfrmCCRunReport.Create(Application, dsEdit);
  try
    frmCCRunReport.reportName := TMenuItem(Sender).Caption;
    frmCCRunReport.ShowModal;
  finally
    frmCCRunReport.Free;
    frmCCRunReport := nil;
  end;
end;

procedure TfrmMain.N188DebtBalanceBeginningYearClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin

  frmDebtBytParam := TfrmDebtBytParam.Create(Application, dsInsert);

  frmDebtBytParam.Label1.visible := False;
  frmDebtBytParam.dtpDate.visible := False;
  frmDebtBytParam.cbItog.visible := False;
  frmDebtBytParam.Label2.visible := False;
  frmDebtBytParam.edtSum.visible := False;
  frmDebtBytParam.RadioGroup1.visible := False;
  frmDebtBytParam.GroupBox1.visible := True;

  try
    if frmDebtBytParam.ShowModal = mrOk then
    begin

      if frmDebtBytParam.edtEPRenName.Text = '' then
        frmDebtBytParam.renCode := -1;

       /////// Parameters
      SetLength(argNames, 1);
      SetLength(args, 1);

      argNames[0] := 'renCode';
      args[0] := IntToStr(frmDebtBytParam.renCode);

      reportName := 'RepEnergozbyt/Debt/group_main_year';
      makeReport(reportName, argNames, args, 'xls');
   end;
  finally
    frmDebtBytParam.Free;
  end;

end;

procedure TfrmMain.N189IncriminationReceivablesZV20powerClick(Sender: TObject);
begin
  frmCCRunReport := TfrmCCRunReport.Create(Application, dsEdit);
  try
    frmCCRunReport.reportName := TMenuItem(Sender).Caption;
    frmCCRunReport.ShowModal;
  finally
    frmCCRunReport.Free;
    frmCCRunReport := nil;
  end;

end;

procedure TfrmMain.N18Click(Sender: TObject);
var
  argNames, args: ArrayOfString;
begin
  frmENPeriod := TfrmENPeriod.Create(Application, dsInsert);
  try
    frmENPeriod.DisableControls([frmENPeriod.edtEPRenName, frmENPeriod.edtENBudgetName, frmENPeriod.edtENElementName]);

    if frmENPeriod.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 11);
      SetLength(args, 11);

      argNames[0] := 'yearGen';
      args[0] := frmENPeriod.edtYearGen.Text;

      argNames[1] := 'monthGen';
      args[1] := IntToStr(frmENPeriod.edtMonthGen.ItemIndex + 1);
      ///////

      argNames[2] := 'pKindCode';
      if frmENPeriod.rbYear.Checked = true then
         args[2] := '1'   // годовой
      else if frmENPeriod.rbTekush.Checked = true then
         args[2] := '2' // текущий
      else if frmENPeriod.rbNpz.Checked = true then
         args[2] := '3' // НПЗ
      else if frmENPeriod.rbfakt.Checked = true then
         args[2] := '4'; // ФАКТ

      argNames[3] := 'renCode';
      args[3] := IntToStr(frmENPeriod.renCode);

      argNames[4] := 'renName';
      if frmENPeriod.renName <> '' then
        args[4] := frmENPeriod.renName
      else
        args[4] := 'ХОЕ';

      argNames[5] := 'dNameField';
      if frmENPeriod.chbByRENs.Checked then
        args[5] := 'd.name'
      else
        args[5] := '1';

      argNames[6] := 'elementCode';
      args[6] := IntToStr(frmENPeriod.elementCode);

      argNames[7] := 'elementName';
      if frmENPeriod.elementName <> '' then
        args[7] := frmENPeriod.elementName
      else
        args[7] := '';

      argNames[8] := 'budgName';
      if frmENPeriod.budgetName <> '' then
        args[8] := frmENPeriod.budgetName
      else
        args[8] := '';

      argNames[9] := 'budgCode';
      args[9] := IntToStr(frmENPeriod.budgetCode);

      argNames[10] := 'dbNameField';
      if frmENPeriod.chbByBudgets.Checked then
        args[10] := 'db.name'
      else
        args[10] := '1';

      makeReport('Report_plan_period/planByMonth_2', argNames, args, 'xls');
    end;
  finally
    frmENPeriod.Free;
  end;
end;

procedure TfrmMain.N190Click(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin
    SetLength(argNames, 1);
    SetLength(args, 1);

    argNames[0] := 'emptyParam';
    args[0] :=  '';
    reportName := 'CN/cn_state_by_ing';

    makeReport(reportName, argNames, args, 'xls');
end;

procedure TfrmMain.N1910MissingControIndicatorsMore6MonthsClick(Sender: TObject);
begin
  frmCCRunReport := TfrmCCRunReport.Create(Application, dsEdit);
  try
    frmCCRunReport.reportName := TMenuItem(Sender).Caption;
    frmCCRunReport.ShowModal;
  finally
    frmCCRunReport.Free;
    frmCCRunReport := nil;
  end;

end;

procedure TfrmMain.N193Click(Sender: TObject);
begin
  frmCCRunReport := TfrmCCRunReport.Create(Application, dsEdit);
  try
    frmCCRunReport.reportName := TMenuItem(Sender).Caption;
    frmCCRunReport.ShowModal;
  finally
    frmCCRunReport.Free;
    frmCCRunReport := nil;
  end;

end;

procedure TfrmMain.N194Click(Sender: TObject);
begin
  frmCCRunReport := TfrmCCRunReport.Create(Application, dsEdit);
  try
    frmCCRunReport.reportName := TMenuItem(Sender).Caption;
    frmCCRunReport.ShowModal;
  finally
    frmCCRunReport.Free;
    frmCCRunReport := nil;
  end;

end;

procedure TfrmMain.N195Click(Sender: TObject);
begin
  frmCCRunReport := TfrmCCRunReport.Create(Application, dsEdit);
  try
    frmCCRunReport.reportName := TMenuItem(Sender).Caption;
    frmCCRunReport.ShowModal;
  finally
    frmCCRunReport.Free;
    frmCCRunReport := nil;
  end;

end;

procedure TfrmMain.N196Click(Sender: TObject);
begin
  frmCCRunReport := TfrmCCRunReport.Create(Application, dsEdit);
  try
    frmCCRunReport.reportName := TMenuItem(Sender).Caption;
    frmCCRunReport.ShowModal;
  finally
    frmCCRunReport.Free;
    frmCCRunReport := nil;
  end;

end;

procedure TfrmMain.N197ActsViolationPREEClick(Sender: TObject);
begin
  frmCCRunReport := TfrmCCRunReport.Create(Application, dsEdit);
  try
    frmCCRunReport.reportName := TMenuItem(Sender).Caption;
    frmCCRunReport.ShowModal;
  finally
    frmCCRunReport.Free;
    frmCCRunReport := nil;
  end;
end;

procedure TfrmMain.N198Click(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin

  frmAward := TfrmAward.Create(Application, dsInsert);

  try
    if frmAward.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 2);
      SetLength(args, 2);

      argNames[0] := 'mon';
      args[0] := IntToStr(frmAward.cbMon.ItemIndex + 1);

      argNames[1] := 'year';
      args[1] := frmAward.cbYear.Text;

      reportName := 'RepEnergozbyt/Award/group_main_detail';
      makeReport(reportName, argNames, args, 'xls');
   end;
  finally
    frmAward.Free;
  end;
end;

procedure TfrmMain.N199Click(Sender: TObject);
begin
  frmCCRunReport := TfrmCCRunReport.Create(Application, dsEdit);
  try
    frmCCRunReport.reportName := TMenuItem(Sender).Caption;
    frmCCRunReport.ShowModal;
  finally
    frmCCRunReport.Free;
    frmCCRunReport := nil;
  end;
end;

procedure TfrmMain.N19Click(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin
    SetLength(argNames, 1);
    SetLength(args, 1);

    argNames[0] := 'emptyParam';
    args[0] :=  '';
    reportName := 'Services/4Rent/oksn_report';

    makeReport(reportName, argNames, args, 'xls');
end;

procedure TfrmMain.mniENTransformerTypeClick(Sender: TObject);
begin //Типи трансформаторів
   if not Assigned(frmENTransformerTypeShow) then
    frmENTransformerTypeShow := TfrmENTransformerTypeShow.Create(Application, fmChild);
 frmENTransformerTypeShow.Show;
end;

procedure TfrmMain.miAnalyseWorkGraph_HOEClick(Sender: TObject);
var
  companyShortName: string;
begin
   frmReportGraphForAnalyse := TfrmReportGraphForAnalyse.Create(Application, dsInsert);
   companyShortName := DMReports.getSettingValueByKey(ENSettingsConsts.COMPANY_SHORTNAME);
   frmReportGraphForAnalyse.Caption := 'Аналіз відпрацьованого часу по типам робіт';
  frmReportGraphForAnalyse.kindGraph := 3; // набор графиков по анализу выполнения работ
 try
   frmReportGraphForAnalyse.ShowModal;
 finally
   frmReportGraphForAnalyse.Free;

 end;
end;

procedure TfrmMain.N200Click(Sender: TObject);
begin
  frmCCRunReport := TfrmCCRunReport.Create(Application, dsEdit);
  try
    frmCCRunReport.reportName := TMenuItem(Sender).Caption;
    frmCCRunReport.ShowModal;
  finally
    frmCCRunReport.Free;
    frmCCRunReport := nil;
  end;
end;

procedure TfrmMain.N20141Click(Sender: TObject);
begin
	frmreporttenderplan := Tfrmreporttenderplan.Create(Application, dsInsert);
	try
			frmreporttenderplan.ShowModal;
  finally
		frmreporttenderplan.Free;
 end;
end;

procedure TfrmMain.N201Click(Sender: TObject);
begin
  frmCCRunReport := TfrmCCRunReport.Create(Application, dsEdit);
  try
    frmCCRunReport.reportName := TMenuItem(Sender).Caption;
    frmCCRunReport.ShowModal;
  finally
    frmCCRunReport.Free;
    frmCCRunReport := nil;
  end;
end;

procedure TfrmMain.N202AccrualPaymentPeriodClick(Sender: TObject);
begin
  frmCCRunReport := TfrmCCRunReport.Create(Application, dsEdit);
  try
    frmCCRunReport.reportName := TMenuItem(Sender).Caption;
    frmCCRunReport.ShowModal;
  finally
    frmCCRunReport.Free;
    frmCCRunReport := nil;
  end;
end;

procedure TfrmMain.N204Click(Sender: TObject);
begin
    if not Assigned(frmENWarrantShow) then
    frmENWarrantShow := TfrmENWarrantShow.Create(Application, fmChild);
   frmENWarrantShow.Show;
end;

procedure TfrmMain.N2061ScheduleControlindicatorsMonthsYurClick(Sender: TObject);
begin
  frmCCRunReport := TfrmCCRunReport.Create(Application, dsEdit);
  try
    frmCCRunReport.reportName := TMenuItem(Sender).Caption;
    frmCCRunReport.ShowModal;
  finally
    frmCCRunReport.Free;
    frmCCRunReport := nil;
  end;

end;

procedure TfrmMain.N2062ScheduleMeanConsumptionSubscriberClick(Sender: TObject);
begin
  frmCCRunReport := TfrmCCRunReport.Create(Application, dsEdit);
  try
    frmCCRunReport.reportName := TMenuItem(Sender).Caption;
    frmCCRunReport.ShowModal;
  finally
    frmCCRunReport.Free;
    frmCCRunReport := nil;
  end;

end;

procedure TfrmMain.N207Click(Sender: TObject);
var
  f: ENServicesObjectFilter;
begin
  f := ENServicesObjectFilter.Create;
  SetNullIntProps(f);
  SetNullXSProps(f);

  f.contractTypeRef := ENServicesContractTypeRef.Create;
  f.contractTypeRef.code := TMenuItem(Sender).Tag;

  if (f.contractTypeRef.code = ENSERVICESOBJECTTYPE_OHRINA) then
  begin
    if not Assigned(frmENServicesGuardShow) then
      frmENServicesGuardShow := TfrmENServicesGuardShow.Create(Application, fmChild, f);
    frmENServicesGuardShow.Caption := TMenuItem(Sender).Caption;
    frmENServicesGuardShow.UpdateCaption;
    frmENServicesGuardShow.Show;
  end
  else
    ShowMessage('Ошибочка в определении типа договора...');
end;

procedure TfrmMain.miDFServicesSpravClick(Sender: TObject);
begin
  if not Assigned(frmDFServicesListShow) then
    frmDFServicesListShow := TfrmDFServicesListShow.Create(Application, fmChild);
  frmDFServicesListShow.Show;
end;

procedure TfrmMain.miListPlanNotIP203Click(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName, strBudget, tmpmaskstring: string;
  i: integer;
  Flags: TReplaceFlags;
  strinmaterialGroupCodes: WideString;
  dep4nom, dep4nomCustom: string;
begin

  frmReportlistPlanWithoutBinding2IP := TfrmReportlistPlanWithoutBinding2IP.Create(Application, dsInsert);
   try

   if frmReportlistPlanWithoutBinding2IP.ShowModal = mrOk then
    begin


          /// собираем строку кодов бюджетодержателей
      for i := 0 to frmReportlistPlanWithoutBinding2IP.ListBudget.Count - 1 do
      begin
           if  frmReportlistPlanWithoutBinding2IP.ListBudget.Checked[i] then
            if strBudget <>  '' then
            strBudget := strBudget + ' , ' + IntToStr(Integer(frmReportlistPlanWithoutBinding2IP.ListBudget.Items.Objects[i]))
             else
            strBudget := strBudget + IntToStr(Integer(frmReportlistPlanWithoutBinding2IP.ListBudget.Items.Objects[i]));

      end;

      // Parameters
      SetLength(argNames, 2);
      SetLength(args, 2);

      argNames[0] := 'planyear';
      args[0] := frmReportlistPlanWithoutBinding2IP.edtYearGen.Text;

      argNames[1] := 'budgetstringcode';
       if strBudget <> '' then
       args[1] :=  ' p.budgetrefcode in (  ' + strBudget + ')'
       else
       args[1] := ' 1 = 1 ';

       reportName := 'Invest/listPlanWithoutBinding2IP';

       makeReport(reportName, argNames, args, 'xls');

    end;
  finally
    frmReportlistPlanWithoutBinding2IP.Free;
  end;
end;

procedure TfrmMain.miLoadPersonalProjectClick(Sender: TObject);
begin
   	frmReportTechConditionsPlan := TfrmReportTechConditionsPlan.Create(Application, dsInsert);
  try
    frmReportTechConditionsPlan.reportvid := 4; // Завантаження персоналу проектанти
    frmReportTechConditionsPlan.ShowModal;
  finally
    frmReportTechConditionsPlan.Free;
 end;
end;

procedure TfrmMain.N210DebitDebtInformationBilits2monthsClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin
    SetLength(argNames, 1);
    SetLength(args, 1);

    argNames[0] := 'emptyParam';
    args[0] :=  '';
    reportName := 'RepEnergozbyt/friday_debetors_2month_main';

    makeReport(reportName, argNames, args, 'xls');
end;

procedure TfrmMain.N212Click(Sender: TObject);
begin
   if not Assigned(frmWFBaseProcessShow) then
    frmWFBaseProcessShow := TfrmWFBaseProcessShow.Create(Application, fmChild);
    frmWFBaseProcessShow.Show;
end;

procedure TfrmMain.N213OrdersFoodClick(Sender: TObject);
var
  period: EditChoosePeriod.TDateArray;
  frmPeriod: TfrmChoosePeriod;
  args, argNames: ArrayOfString;
begin
  frmPeriod := TfrmChoosePeriod.Create(Application, nil, 'Оберіть період');
  frmPeriod.ShowModal;
  period := frmPeriod.GetValue;
  if Assigned(period) then
  begin
    SetLength(args, 2);
    SetLength(argNames, 2);

    argNames[0] := 'dateStart';
    argNames[1] := 'dateFinal';

    args[0] := DateToStr(period[0]);
    args[1] := DateToSTr(period[1]);

    makeReport('Bufet/bufet_order_wrapper', argNames, args, 'xls');
  end;

  if Assigned(frmPeriod) then
    frmPeriod.Free;

end;

procedure TfrmMain.miWorkflowClick(Sender: TObject);
begin
  frmWorkFlowManagementShow := TfrmWorkFlowManagementShow.Create(Application, fmChild);
  frmWorkFlowManagementShow.Show;
end;

procedure TfrmMain.N217Click(Sender: TObject);
begin
   if not Assigned(frmWFProcessStateShow) then
    frmWFProcessStateShow := TfrmWFProcessStateShow.Create(Application, fmChild);
    frmWFProcessStateShow.Show;
end;

procedure TfrmMain.N218Click(Sender: TObject);
begin
  if Assigned(frmWFProcessRoleShow) then
    frmWFProcessRoleShow := nil;

 if not Assigned(frmWFProcessRoleShow) then
    frmWFProcessRoleShow := TfrmWFProcessRoleShow.Create(Application, fmChild);
    frmWFProcessRoleShow.Show;
end;

procedure TfrmMain.N219Click(Sender: TObject);
begin
    if not Assigned(frmWFGroupShow) then
    frmWFGroupShow := TfrmWFGroupShow.Create(Application, fmChild);
    frmWFGroupShow.Show;
end;

procedure TfrmMain.miJur2MonthClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin

  frmDebtBytParam := TfrmDebtBytParam.Create(Application, dsInsert);
  frmDebtBytParam.cbItog.visible := False;
  frmDebtBytParam.Label1.visible := False;
  frmDebtBytParam.dtpDate.visible := False;
  frmDebtBytParam.Label2.visible := False;
  frmDebtBytParam.edtSum.visible := False;
  frmDebtBytParam.RadioGroup1.visible := False;
  frmDebtBytParam.GroupBox1.visible := True;

  try
    if frmDebtBytParam.ShowModal = mrOk then
    begin

      if frmDebtBytParam.edtEPRenName.Text = '' then
        frmDebtBytParam.renCode := -1;

       /////// Parameters
      SetLength(argNames, 2);
      SetLength(args, 2);

      argNames[0] := 'dateStart';
      args[0] := DateToStr(Date());

      argNames[1] := 'renCode';
      args[1] := IntToStr(frmDebtBytParam.renCode);

      reportName := 'RepEnergozbyt/Debt/group_main_claims';
      makeReport(reportName, argNames, args, 'xls');
   end;
  finally
    frmDebtBytParam.Free;
  end;

end;

procedure TfrmMain.N21Click(Sender: TObject);
begin
   if not Assigned(frmENDepartmentOrigShow) then
    frmENDepartmentOrigShow := TfrmENDepartmentOrigShow.Create(Application, fmChild);
   frmENDepartmentOrigShow.Show;
end;

procedure TfrmMain.N220Click(Sender: TObject);
begin
    if not Assigned(frmWFBaseProcessLinkShow) then
    frmWFBaseProcessLinkShow := TfrmWFBaseProcessLinkShow.Create(Application, fmChild);
    frmWFBaseProcessLinkShow.Show;
end;

procedure TfrmMain.N221Click(Sender: TObject);
begin
    if not Assigned(frmWFPackTypeShow) then
    frmWFPackTypeShow := TfrmWFPackTypeShow.Create(Application, fmChild);
    frmWFPackTypeShow.Show;
end;

procedure TfrmMain.N222Click(Sender: TObject);
begin
    if not Assigned(frmWFDepartmentShow) then
    frmWFDepartmentShow := TfrmWFDepartmentShow.Create(Application, fmChild);
    frmWFDepartmentShow.Show;
end;

procedure TfrmMain.N224Click(Sender: TObject);
var
    frmReportDetailedReportOnConcludedAgreementsConnection: TfrmReportDetailedReportOnConcludedAgreementsConnection;
begin
        frmReportDetailedReportOnConcludedAgreementsConnection := TfrmReportDetailedReportOnConcludedAgreementsConnection.Create(Application);
        try
           frmReportDetailedReportOnConcludedAgreementsConnection.ShowModal;
        finally
           frmReportDetailedReportOnConcludedAgreementsConnection.Free;
        end;
end;

procedure TfrmMain.N22Click(Sender: TObject);
var
  argNames, args: ArrayOfString;
begin
  frmENDateRange := TfrmENDateRange.Create(Application, dsInsert);
  try
    if frmENDateRange.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 2);
      SetLength(args, 2);

      argNames[0] := 'startDate';
      args[0] := DateToStr(frmENDateRange.dtpDateFrom.Date);

      argNames[1] := 'finalDate';
      args[1] := DateToStr(frmENDateRange.dtpDateTo.Date);
      ///////

      makeReport('PlanWorkMoveHistory', argNames, args, 'pdf');
    end;
  finally
    frmENDateRange.Free;
  end;
end;

procedure TfrmMain.N238ReportCurrentStateClick(Sender: TObject);
begin
   	frmreportCurrentStates := TfrmreportCurrentStates.Create(Application, dsInsert);
  try
    frmreportCurrentStates.ShowModal;
  finally
    frmreportCurrentStates.Free;
 end;
end;

procedure TfrmMain.N23Click(Sender: TObject);
var
  argNames, args: ArrayOfString;
begin
  frmENDateRange := TfrmENDateRange.Create(Application, dsInsert);
  try
    if frmENDateRange.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 2);
      SetLength(args, 2);

      argNames[0] := 'startDate';
      args[0] := DateToStr(frmENDateRange.dtpDateFrom.Date);

      argNames[1] := 'finalDate';
      args[1] := DateToStr(frmENDateRange.dtpDateTo.Date);
      ///////

      makeReport('PlanWorkCorrectionHistory', argNames, args, 'pdf');
    end;
  finally
    frmENDateRange.Free;
  end;
end;

procedure TfrmMain.N351501Click(Sender: TObject);
begin
 if not Assigned(frmENLine150Show) then
    frmENLine150Show := TfrmENLine150Show.Create(Application, fmChild);
 frmENLine150Show.Show;
end;

procedure TfrmMain.N240ReportActPREEClick(Sender: TObject);
begin
   	frmreportActByDepartment := TfrmreportActByDepartment.Create(Application, dsInsert);
  try
    frmreportActByDepartment.ShowModal;
  finally
    frmreportActByDepartment.Free;
 end;
end;

procedure TfrmMain.N243Click(Sender: TObject);
begin
    if not Assigned(frmWFSubsystemShow) then
    frmWFSubsystemShow := TfrmWFSubsystemShow.Create(Application, fmChild);
    frmWFSubsystemShow.Show;
end;

procedure TfrmMain.N244ReportContractContractsClick(Sender: TObject);
var
    frmReportOnDohovoryPidryadu: TfrmReportOnDohovoryPidryadu;
begin
        frmReportOnDohovoryPidryadu := TfrmReportOnDohovoryPidryadu.Create(Application);
        try
           frmReportOnDohovoryPidryadu.ShowModal;
        finally
           frmReportOnDohovoryPidryadu.Free;
        end;

end;

procedure TfrmMain.N247Click(Sender: TObject);
begin
     if not Assigned(frmENDocAttachmentTypeShow) then
    frmENDocAttachmentTypeShow := TfrmENDocAttachmentTypeShow.Create(Application, fmChild);
    frmENDocAttachmentTypeShow.Show;
end;

procedure TfrmMain.N24Click(Sender: TObject);
begin
   frmEPRunReport:=TfrmEPRunReport.Create(Application, dsEdit);
  try
    frmEPRunReport.reportName:=TMenuItem(Sender).Caption;
    frmEPRunReport.ShowModal;
  finally
    frmEPRunReport.Free;
    frmEPRunReport:=nil;
  end;
end;

procedure TfrmMain.miCatalogOrderClick(Sender: TObject);
begin
  inherited;
  if not Assigned(frmDFOrdersCatalogManagement) then
    frmDFOrdersCatalogManagement := TfrmDFOrdersCatalogManagement.Create(Application, fmChild);
  frmDFOrdersCatalogManagement.Show;
end;

procedure TfrmMain.miCatalogNormativeDocClick(Sender: TObject);
begin
  if not Assigned(frmDFNormativeDocCatalogManagement) then
    frmDFNormativeDocCatalogManagement := TfrmDFNormativeDocCatalogManagement.Create(Application, fmChild);
  frmDFNormativeDocCatalogManagement.Show;
end;

procedure TfrmMain.miCatalogProjectClick(Sender: TObject);
begin
  if not Assigned(frmDFProjectDocCatalogManagement) then
    frmDFProjectDocCatalogManagement := TfrmDFProjectDocCatalogManagement.Create(Application, fmChild);
  frmDFProjectDocCatalogManagement.Show;
end;

procedure TfrmMain.N24PlansDeployedWorkClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
begin
  frmENPeriod := TfrmENPeriod.Create(Application, dsInsert);
  try
    frmENPeriod.DisableControls([frmENPeriod.edtEPRenName, frmENPeriod.edtENBudgetName, frmENPeriod.edtENElementName]);

    if frmENPeriod.ShowModal = mrOk then
    begin
       /////// Parameters
      SetLength(argNames, 11);
      SetLength(args, 11);

      argNames[0] := 'yearGen';
      args[0] := frmENPeriod.edtYearGen.Text;

      argNames[1] := 'monthGen';
      args[1] := IntToStr(frmENPeriod.edtMonthGen.ItemIndex + 1);
      ///////

      argNames[2] := 'pKindCode';
      if frmENPeriod.rbYear.Checked = true then
         args[2] := '1'   // годовой
      else if frmENPeriod.rbTekush.Checked = true then
         args[2] := '2' // текущий
      else if frmENPeriod.rbNpz.Checked = true then
         args[2] := '3' // НПЗ
      else if frmENPeriod.rbfakt.Checked = true then
         args[2] := '4'; // ФАКТ

      argNames[3] := 'renCode';
      args[3] := IntToStr(frmENPeriod.renCode);

      argNames[4] := 'renName';
      if frmENPeriod.renName <> '' then
        args[4] := frmENPeriod.renName
      else
        args[4] := 'ХОЕ';

      argNames[5] := 'dNameField';
      if frmENPeriod.chbByRENs.Checked then
        args[5] := 'd.name'
      else
        args[5] := '1';

      argNames[6] := 'elementCode';
      args[6] := IntToStr(frmENPeriod.elementCode);

      argNames[7] := 'elementName';
      if frmENPeriod.elementName <> '' then
        args[7] := frmENPeriod.elementName
      else
        args[7] := '';

      argNames[8] := 'budgName';
      if frmENPeriod.budgetName <> '' then
        args[8] := frmENPeriod.budgetName

      else
        args[8] := '';

      argNames[9] := 'budgCode';
      args[9] := IntToStr(frmENPeriod.budgetCode);

      argNames[10] := 'dbNameField';
      if frmENPeriod.chbByBudgets.Checked then
        args[10] := 'db.name'
      else
        args[10] := '1';

      makeReport('Report_plan_period/planByMonthTimeNorms_2', argNames, args, 'xls');

    end;
  finally
    frmENPeriod.Free;
  end;
end;

procedure TfrmMain.N251ControlSettingMetersAfterPerformingJobsClick(Sender: TObject);
var
    frmControInstallationCountersEEConnection: TfrmControInstallationCountersEEConnection;
begin
        frmControInstallationCountersEEConnection := TfrmControInstallationCountersEEConnection.Create(Application);
        try
           frmControInstallationCountersEEConnection.ShowModal;
        finally
           frmControInstallationCountersEEConnection.Free;
        end;
end;

procedure TfrmMain.N25Click(Sender: TObject);
begin
   frmEPRunReport:=TfrmEPRunReport.Create(Application, dsEdit);
  try
    frmEPRunReport.reportName:=TMenuItem(Sender).Caption;
    frmEPRunReport.ShowModal;
  finally
    frmEPRunReport.Free;
    frmEPRunReport:=nil;
  end;
end;

procedure TfrmMain.miDocServicesConsumerByPeriodClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
begin
  frmreportAppealRegistrationByRensAndDate := TfrmreportAppealRegistrationByRensAndDate.Create(Application, dsInsert);
  try
    frmreportAppealRegistrationByRensAndDate.Caption := (Sender as TMenuItem).Caption;
    if frmreportAppealRegistrationByRensAndDate.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 2);
      SetLength(args, 2);

      argNames[0] := 'DateRegistrationFrom';
      args[0] := DateToStr(frmreportAppealRegistrationByRensAndDate.edtDateStart.Date);

      argNames[1] := 'DateRegistrationTo';
      args[1] := DateToStr(frmreportAppealRegistrationByRensAndDate.edtDateFinal.Date);
      ///////

      DMReports.makeReport4DocFlow('reportDocServicesConsumer', argNames, args, 'xls');
    end;
  finally
    frmreportAppealRegistrationByRensAndDate.Free;
  end;
end;

procedure TfrmMain.mi256ReportExecIPClick(Sender: TObject);
begin
 frmReportInvestProgram := TfrmReportInvestProgram.Create(Application, dsInsert);
  try
    frmReportInvestProgram.ShowModal;
  finally
    frmReportInvestProgram.Free;
  end;
end;

procedure TfrmMain.miHistogrammInvestClick(Sender: TObject);
var
 args, argNames: ArrayOfString;
begin

   frmReportWFInvest := TfrmReportWFInvest.Create(Application, dsInsert);
  try

     frmReportWFInvest.ShowModal;

  finally
    frmReportWFInvest.Free;

 end;
end;

procedure TfrmMain.miInvestPercentClick(Sender: TObject);
var
  reportInvestServicesFromSide: TfrmreportInvestServicesFromSide;
begin
       reportInvestServicesFromSide := TfrmreportInvestServicesFromSide.Create(Application, dsInsert);
     try
       reportInvestServicesFromSide.ShowModal;
     finally
       reportInvestServicesFromSide.Free;
     end;
end;

procedure TfrmMain.miServicesByTransportClick(Sender: TObject);
var
  reportServicesFromSideByTransport: TfrmreportServicesFromSideByTransport;
begin
       reportServicesFromSideByTransport := TfrmreportServicesFromSideByTransport.Create(Application, dsInsert);
     try
       reportServicesFromSideByTransport.ShowModal;
     finally
       reportServicesFromSideByTransport.Free;
     end;
end;

procedure TfrmMain.N351502Click(Sender: TObject);
begin
 if not Assigned(frmENSubstation150Show) then
    frmENSubstation150Show := TfrmENSubstation150Show.Create(Application, fmChild);
 frmENSubstation150Show.Show;
end;

procedure TfrmMain.N27Click(Sender: TObject);
begin
{
 if not Assigned(frmTKPositionShow) then
    frmTKPositionShow := TfrmTKPositionShow.Create(Application,fmChild);
  //  frmTKPositionShow.DisableActions([frmTKPositionShow.actEdit, frmTKPositionShow.actInsert,frmTKPositionShow.actDelete]);
 frmTKPositionShow.Show;
 }
end;

procedure TfrmMain.N28Click(Sender: TObject);
begin
 if not Assigned(frmENWorkerShow) then
    frmENWorkerShow := TfrmENWorkerShow.Create(Application, fmChild);
    //frmTKPositionShow.DisableActions([frmTKPositionShow.actEdit, frmTKPositionShow.actInsert,frmTKPositionShow.actDelete]);
 frmENWorkerShow.Show;
end;

procedure TfrmMain.miNormTaskOthersClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin
     frmENPeriodWithRENTaskPlanFact := TfrmENPeriodWithRENTaskPlanFact.Create(Application, dsInsert);
  try
     frmENPeriodWithRENTaskPlanFact.chbByObjects.Checked := True;
    frmENPeriodWithRENTaskPlanFact.DisableControls([frmENPeriodWithRENTaskPlanFact.chbByObjects, frmENPeriodWithRENTaskPlanFact.chbByRENs]);
    frmENPeriodWithRENTaskPlanFact.ShowModal;
  finally
    frmENPeriodWithRENTaskPlanFact.Free;
 end;
end;

procedure TfrmMain.miNormTaskTechnicalManagementClick(Sender: TObject);
var
  args, argNames: ArrayOfString;
  countParameters : Integer;
  var workTeamsByDatesForm : TfrmReportWorkTeamsByDates;
begin
  try
    workTeamsByDatesForm := TfrmReportWorkTeamsByDates.Create(Application, dsInsert);
    workTeamsByDatesForm.ShowModal;
    if workTeamsByDatesForm.ModalResult = mrOk then begin

      if workTeamsByDatesForm.departmentCode <> Low(Integer)
        then countParameters := 4 else countParameters := 3;


      SetLength(args, countParameters);
      SetLength(argNames, countParameters);

      argNames[0] := 'dateStart';
      argNames[1] := 'dateFinish';

      args[0] := DateToStr(workTeamsByDatesForm.dateStart);
      args[1] := DateToStr(workTeamsByDatesForm.dateFinal);

      argNames[2] := 'budgetsCodesString';
      args[2] := workTeamsByDatesForm.strBudgetCodes;

      if workTeamsByDatesForm.departmentCode <> Low(Integer) then begin
        argNames[3] := 'departmentCode';
        args[3] := IntToStr(workTeamsByDatesForm.departmentCode);
      end;

      makeReport('workTeams/workTeamsByDates/workTeamsByDates_wrapper'
        , argNames, args, 'xls');
    end;
  finally
    workTeamsByDatesForm.Free;
  end;
end;

procedure TfrmMain.miNotificationForSupplierClick(Sender: TObject);
begin
  frmNotificationForSupplierEdit := TfrmNotificationForSupplierEdit.Create(Application, dsInsert);
  try
    frmNotificationForSupplierEdit.ShowModal;
  finally
    frmNotificationForSupplierEdit.Free;
  end;
end;

procedure TfrmMain.N33Click(Sender: TObject);
var
  f: FINMolFilter;
begin
 f := FINMolFilter.Create;
 SetNullXSProps(f);
 SetNullIntProps(f);
 if not Assigned(frmFINMolShow) then
    frmFINMolShow := TfrmFINMolShow.Create(Application, fmChild, f);
 frmFINMolShow.Show;
end;

procedure TfrmMain.miTransportClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
  strmanagement: string;
  i: integer;
  TempENDepartment: ENDepartmentControllerSoapPort;
  depCodes: string;
begin
    frmENPeriodWithRENtask := TfrmENPeriodWithRENTask.Create(Application, dsInsert);
  try
    frmENPeriodWithRENTask.lblTransportDepartment.Visible := True;
    frmENPeriodWithRENTask.edtENTransportDepartmentName.Visible := True;
    frmENPeriodWithRENTask.spbENTransportDepartment.Visible := True;
    frmENPeriodWithRENTask.spbENTransportDepartmentClear.Visible := True;
    frmENPeriodWithRENTask.gbSort.Visible := True;

    frmENPeriodWithRENTask.DisableControls([frmENPeriodWithRENTask.edtENTransportDepartmentName, frmENPeriodWithRENTask.edtRenWork]);

     frmENPeriodWithRENTask.chbByObjects.Checked := true;
     frmENPeriodWithRENTask.chktransportation.Visible := true;
     frmENPeriodWithRENTask.DisableControls([frmENPeriodWithRENTask.chbByObjects]);

    if frmENPeriodWithRENTask.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 16);
      SetLength(args, 16);

      argNames[0] := 'pdatestart';
      args[0] := DateToStr(frmENPeriodWithRENTask.edtDateStart.date);

      argNames[1] := 'pdatefinal';
      args[1] := DateToStr(frmENPeriodWithRENTask.edtDateFinal.date);

      argNames[2] := 'renCode';
      args[2] := IntToStr(frmENPeriodWithRENTask.renCode);

      argNames[10] := 'renCodeStr';
      if frmENPeriodWithRENTask.renCode > 0 then
      begin
        depCodes := DMReports.getDepartmentCodesDown(frmENPeriodWithRENTask.renCode);
        args[10] := depCodes;
      end
      else
      args[10] := '0';

      if frmENPeriodWithRENTask.renCode > 0 then
      begin
        args[10] := ' and enp.departmentrefcode in ( ' +  DMReports.getDepartmentCodesDown(frmENPeriodWithRENTask.renCode) + ')';

      end
      else
        args[10] := ' and 1 = 1 ';

      argNames[3] := 'renName';
      if frmENPeriodWithRENTask.renName <> '' then
        args[3] := frmENPeriodWithRENTask.renName
      else
        args[3] := 'ХОЕ';

      argNames[4] := 'elementCode';
      args[4] := IntToStr(frmENPeriodWithRENTask.elementCode);

      argNames[5] := 'elementName';
      if frmENPeriodWithRENTask.elementName <> '' then
        args[5] := 'Об"єкт : ' + frmENPeriodWithRENTask.elementName + ' '
      else
        args[5] := '';

      strmanagement := '';
  for i := 0 to frmENPeriodWithRENTask.CheckListBox1.Items.Count - 1 do
   if frmENPeriodWithRENTask.CheckListBox1.Checked[i] then
     AddListPos(strmanagement, IntToStr(i + 1));
   if length(trim(strmanagement)) = 0 then
   begin
        Application.MessageBox(PChar('Оберіть обов`язково дирекцію   !!!'), PChar('Увага!'), MB_ICONWARNING);
        ModalResult := mrNone;
      Exit;
   end;
      argNames[6] := 'managementCode';
      args[6] := '(' + strmanagement + ')';

      if ((frmENPeriodWithRENTask.renCode > 0) and (frmENPeriodWithRENTask.transportRenCode > 0)) then
       begin
        Application.MessageBox(PChar('Звіт формується або тільки по підрозділу плана, або тільки по транспортному підрозділу   !!!'), PChar('Увага!'), MB_ICONWARNING);
        ModalResult := mrNone;
         Exit;
        end;

      argNames[7] := 'budgCode';
      args[7] := IntToStr(frmENPeriodWithRENTask.budgetCode);

      argNames[8] := 'budgName';
      args[8] := frmENPeriodWithRENTask.budgetName;

      argNames[9] := 'transportation';

      if frmENPeriodWithRENTask.chktransportation.Checked = True then
        args[9] := '(' + '500004872 , 500004873 , 500004874 , 500004875 , 500004876 , 500004877' + ')'
      else
        args[9] := '(' + 'select code from tktechcard ' + ')';

      argNames[11] := 'transportRenCode';
      if frmENPeriodWithRENTask.transportRenCode > 0  then
      begin
        args[11] := IntToStr(frmENPeriodWithRENTask.transportRenCode);
         // если транпортное подразделение автоколона то код для подразделения примем как апарат управления
         if frmENPeriodWithRENTask.transportRenCode = 10000 then
         args[2] := IntToStr(ENDEPARTMENT_CO)
         else
         args[2] := IntToStr(frmENPeriodWithRENTask.transportRenCode);

      end
      else
      begin
        args[11] := IntToStr(0);
      end;

      argNames[12] := 'shownotusetransport';
      if frmENPeriodWithRENTask.chkshownotusetransport.Checked = True then
      args[12] := '1'
      else
      args[12] := '0';

      /// для выбора типа и вида работ планов
      ///  02.02.2015 по устному распоряжению Салыгина
      argNames[13] := 'typeCode';
      args[13] := IntToStr(frmENPeriodWithRENTask.typeCode);

      argNames[14] := 'stateCode';
      args[14] := IntToStr(frmENPeriodWithRENTask.stateCode);

      argNames[15] := 'renWorkCode';
      args[15] := IntToStr(frmENPeriodWithRENTask.renWorkCode);

      if frmENPeriodWithRENTask.renWorkCode > 0 then
      begin
        args[15] := ' ( ' +  DMReports.getDepartmentCodesDown(frmENPeriodWithRENTask.renWorkCode) + ')';

      end
      else
        args[15] := ' ( select code from endepartment dep ) ';


      //reportName := 'NPZ_dodat2/VedUseTechniq2';
      if frmENPeriodWithRENTask.transportRenCode > 0 then
       begin
         if frmENPeriodWithRENTask.rbSortData.Checked = true then
            reportName := 'NPZ_dodat2_by_trnsp_dep/VedUseTechniq2'
         else
            reportName := 'NPZ_dodat2_by_trnsp_dep_sort_auto/VedUseTechniq2';

       end
      else if True then
        if frmENPeriodWithRENTask.rbSortData.Checked = true then
            reportName := 'NPZ_dodat2_var2_2/VedUseTechniq2'
         else
            reportName := 'NPZ_dodat2_var2_2_sort_auto/VedUseTechniq2';

      makeReport(reportName, argNames, args, 'xls');
    end;
  finally
    frmENPeriodWithRENTask.Free;

  end;

end;

procedure TfrmMain.miTransportInRepairClick(Sender: TObject);
begin
      frmReportTransportPlan := TfrmReportTransportPlan.Create(Application, dsInsert);
//    frmReportTransportPlan.lblTransportDepartment.Visible:= True;
  //  frmReportTransportPlan.edtENTransportDepartmentName.Visible:= True;
   // frmReportTransportPlan.spbENTransportDepartment.Visible:= True;
    //frmReportTransportPlan.spbENTransportDepartmentClear.Visible:= True;
  frmReportTransportPlan.ListTransportDepartment.Visible := True;
  frmReportTransportPlan.edtDateStart.Visible := True;
  frmReportTransportPlan.lblDateStart.Visible := True;
  frmReportTransportPlan.cbbYearGen.Visible := False;
  frmReportTransportPlan.cbbMonthGen.Visible := False;
  frmReportTransportPlan.lblYearGen.Visible := False;
  frmReportTransportPlan.lblMonthGen.Visible := False;
  frmReportTransportPlan.RadioGroup1.Visible := False;

  try
    frmReportTransportPlan.ShowModal;
  finally
    frmReportTransportPlan.Free;
  end;
end;


procedure TfrmMain.miActCheckingWorkplaceClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
begin
  frmreportAppealRegistrationByDate := TfrmreportAppealRegistrationByDate.Create(Application, dsInsert);
  frmreportAppealRegistrationByDate.Caption := 'Звіт перевірок на робочому місці';
  frmreportAppealRegistrationByDate.gbPeriod.Caption := 'Період перевірки';
  frmreportAppealRegistrationByDate.chbByOperators.Visible := False;
  frmreportAppealRegistrationByDate.viewDepartment := True;

  try
    if frmreportAppealRegistrationByDate.ShowModal = mrOk then
    begin
      SetLength(argNames, 4);
      SetLength(args, 4);

      argNames[0] := 'dateStart';
      args[0] := DateToStr(frmreportAppealRegistrationByDate.edtDateStart.Date);

      argNames[1] := 'dateFinal';
      args[1] := DateToStr(frmreportAppealRegistrationByDate.edtDateFinal.Date);

      argNames[2] := 'departmentCode';
      args[2] := IntToStr(frmreportAppealRegistrationByDate.departmentCode);

      argNames[3] := 'departmentName';
      if frmreportAppealRegistrationByDate.edtDFDepartmentREN.Text <> '' then
        args[3] := frmreportAppealRegistrationByDate.edtDFDepartmentREN.Text
      else
        args[3] := '';


      DMReports.makeReport4DocFlow('actCheckingRegistry/actCheckingRegistry', argNames, args, 'xls');
    end;
  finally
    frmreportAppealRegistrationByDate.Free;
  end;
end;


procedure TfrmMain.miActClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin
   frmENPeriodWithRENtask := TfrmENPeriodWithRENTask.Create(Application, dsInsert);
  try
    frmENPeriodWithRENTask.chbByObjects.Checked := true;
    frmENPeriodWithRENTask.DisableControls([frmENPeriodWithRENTask.chbByObjects]);
    frmENPeriodWithRENTask.DisableControls([frmENPeriodWithRENTask.edtENBudgetName, frmENPeriodWithRENTask.spbENBudget, frmENPeriodWithRENTask.spbENBudgetClear, frmENPeriodWithRENTask.chbByBudgets, frmENPeriodWithRENTask.lblENBudgetName]);
    if frmENPeriodWithRENTask.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 1);
      SetLength(args, 1);

    {  argNames[0] := 'pdatestart';
      args[0] :=  DateToStr( frmENPeriodWithRENTask.edtDateStart.date );

      argNames[1] := 'pdatefinal';
      args[1] := DateToStr( frmENPeriodWithRENTask.edtDateFinal.date );

      argnames[2] := 'renCode';
      args[2] := IntToStr(frmENPeriodWithRENTask.renCode);

      argnames[3] := 'renName';
      if frmENPeriodWithRENTask.renName <> '' then
        args[3] := frmENPeriodWithRENTask.renName
      else
        args[3] := 'ХОЕ';



      argnames[4] := 'elementCode';
      args[4] := IntToStr(frmENPeriodWithRENTask.elementCode);

      argnames[5] := 'elementName';
      if frmENPeriodWithRENTask.elementName <> '' then
        args[5] := 'Об"єкт : ' + frmENPeriodWithRENTask.elementName + ' '
      else
        args[5] := '';  }

      argNames[0] := 'PcodeAkt';
      args[0] :=  '500000006';

     reportName := 'AktVipWorkAddition4/VedAktAddition4';
    // reportName := 'AktVipWorkAddition3/VedAktAddition3';
    // reportName := 'AktVipWorkAddition2/VedAktAddition2';
   // reportName := 'AktVipWorkAddition3/VedAktAddition3';
      makeReport(reportName, argNames, args, 'xls');
    end;
  finally
    frmENPeriodWithRENTask.Free;

  end;

end;

procedure TfrmMain.N36Click(Sender: TObject);
begin
 if not Assigned(frmENActShow) then
    frmENActShow := TfrmENActShow.Create(Application, fmChild);
 frmENActShow.isFiltered := true;
 frmENActShow.actInsert.Execute;
end;

procedure TfrmMain.N35Click(Sender: TObject);
begin
 if not Assigned(frmENActShow) then
    frmENActShow := TfrmENActShow.Create(Application, fmChild);
 frmENActShow.Show;
end;

procedure TfrmMain.N38Click(Sender: TObject);
begin
 if not Assigned(frmENRecordPointBytShow) then
    frmENRecordPointBytShow := TfrmENRecordPointBytShow.Create(Application, fmChild);
 frmENRecordPointBytShow.Show;
end;

procedure TfrmMain.N41Click(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin
      /////// Parameters
      SetLength(argNames, 1);
      SetLength(args, 1);
  argNames[0] := 'qqq';
      args[0] := 'qqq';
      reportName := 'TechCard/withoutResources';

      makeReport(reportName, argNames, args, 'xls');

end;

procedure TfrmMain.miAnalysysMaterialsClick(Sender: TObject);
var
 argNames, args: ArrayOfString;
  reportName: string;
begin
     frmAnalysisMaterials := TfrmAnalysisMaterials.Create(Application, dsInsert);
  try
    // frmAnalysisMaterials.chbByObjects.Checked := True;

    { frmAnalysisMaterials.DisableControls([frmAnalysisMaterials.edtENBudgetName ,
     frmAnalysisMaterials.spbENBudget ,
     frmAnalysisMaterials.spbENBudgetClear ,
     frmAnalysisMaterials.lblENBudgetName]); }

     frmAnalysisMaterials.ShowModal;

  finally
    frmAnalysisMaterials.Free;

 end;
end;

procedure TfrmMain.miAppealRegistrationByDateClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
begin
  frmreportAppealRegistrationByDate := TfrmreportAppealRegistrationByDate.Create(Application, dsInsert);
  frmreportAppealRegistrationByDate.chbByOperators.Visible := False;
  frmreportAppealRegistrationByDate.viewDepartment := True;
  try
    if frmreportAppealRegistrationByDate.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 4);
      SetLength(args, 4);

      argNames[0] := 'dateStart';
      args[0] := DateToStr(frmreportAppealRegistrationByDate.edtDateStart.Date);

      argNames[1] := 'dateFinal';
      args[1] := DateToStr(frmreportAppealRegistrationByDate.edtDateFinal.Date);

      argNames[2] := 'departmentCode';
      args[2] := IntToStr(frmreportAppealRegistrationByDate.departmentCode);

      argNames[3] := 'departmentName';
      args[3] := frmreportAppealRegistrationByDate.edtDFDepartmentREN.Text;
      ///////

      DMReports.makeReport4DocFlow('appeal_registration_by_date', argNames, args, 'xls');
    end;
  finally
    frmreportAppealRegistrationByDate.Free;
  end;
end;

procedure TfrmMain.miAppealRegistrationByQuarterClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
begin
  frmreportAppealRegistrationByQuarter := TfrmreportAppealRegistrationByQuarter.Create(Application, dsInsert);
  try
    if frmreportAppealRegistrationByQuarter.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 4);
      SetLength(args, 4);

      {
      argNames[0] := 'quarterGen';
      args[0] := frmreportAppealRegistrationByQuarter.cbQuarterGen.Text;

      argNames[1] := 'yearGen';
      args[1] := frmreportAppealRegistrationByQuarter.cbYearGen.Text;
      }

      argNames[0] := 'dateStart';
      args[0] := DateToStr(frmreportAppealRegistrationByQuarter.edtDateStart.Date);

      argNames[1] := 'dateFinal';
      args[1] := DateToStr(frmreportAppealRegistrationByQuarter.edtDateFinal.Date);

      argNames[2] := 'departmentCode';
      args[2] := IntToStr(frmreportAppealRegistrationByQuarter.departmentCode);

      argNames[3] := 'departmentName';
      args[3] := frmreportAppealRegistrationByQuarter.edtDFDepartmentREN.Text;
      ///////

      DMReports.makeReport4DocFlow('appeal_registration_by_quarter', argNames, args, 'xls');
    end;
  finally
    frmreportAppealRegistrationByQuarter.Free;
  end;
end;

procedure TfrmMain.miAppealRegistrationByRensAndDateClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
begin
  frmreportAppealRegistrationByRensAndDate := TfrmreportAppealRegistrationByRensAndDate.Create(Application, dsInsert);
  try
    if frmreportAppealRegistrationByRensAndDate.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 2);
      SetLength(args, 2);

      argNames[0] := 'dateStart';
      args[0] := DateToStr(frmreportAppealRegistrationByRensAndDate.edtDateStart.Date);

      argNames[1] := 'dateFinal';
      args[1] := DateToStr(frmreportAppealRegistrationByRensAndDate.edtDateFinal.Date);
      ///////

      DMReports.makeReport4DocFlow('appeal_registration_by_rens_and_date', argNames, args, 'xls');
    end;
  finally
    frmreportAppealRegistrationByRensAndDate.Free;
  end;
end;

procedure TfrmMain.miAppealsByClassificationClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
begin
  frmreportAppealRegistrationByDate := TfrmreportAppealRegistrationByDate.Create(Application, dsInsert);
  frmreportAppealRegistrationByDate.chbByOperators.Visible := False;
  frmreportAppealRegistrationByDate.viewDepartment := True;
  try
    if frmreportAppealRegistrationByDate.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 4);
      SetLength(args, 4);

      argNames[0] := 'datestart';
      args[0] := DateToStr(frmreportAppealRegistrationByDate.edtDateStart.Date);

      argNames[1] := 'datefinal';
      args[1] := DateToStr(frmreportAppealRegistrationByDate.edtDateFinal.Date);

      argNames[2] := 'departmentCode';
      args[2] := IntToStr(frmreportAppealRegistrationByDate.departmentCode);

      argNames[3] := 'departmentName';
      args[3] := frmreportAppealRegistrationByDate.edtDFDepartmentREN.Text;
      ///////

      DMReports.makeReport4DocFlow('servicesQuality/appealsByClassification', argNames, args, 'xls');
    end;
  finally
    frmreportAppealRegistrationByDate.Free;
  end;
end;

procedure TfrmMain.OpenStandartReport(Sender: TObject);
begin
  frmCCRunReport:=TfrmCCRunReport.Create(Application, dsEdit);
  try
    frmCCRunReport.reportName:=TMenuItem(Sender).Caption;
    frmCCRunReport.ShowModal;
  finally
    frmCCRunReport.Free;
    frmCCRunReport:=nil;
  end;
end;

procedure TfrmMain.N312Click(Sender: TObject);
begin
  OpenStandartReport(Sender);
end;

procedure TfrmMain.N32Click(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin
      /////// Parameters
      SetLength(argNames, 1);
      SetLength(args, 1);
  argNames[0] := 'qqq';
      args[0] := 'qqq';
      reportName := 'TechCard/withoutResourcesHumen';

      makeReport(reportName, argNames, args, 'xls');
end;

procedure TfrmMain.N42Click(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin
      /////// Parameters
      SetLength(argNames, 1);
      SetLength(args, 1);
  argNames[0] := 'qqq';
      args[0] := 'qqq';
      reportName := 'TechCard/withoutResourcesMaterials';

      makeReport(reportName, argNames, args, 'xls');
end;

procedure TfrmMain.miRZAObjectsClick(Sender: TObject);
begin
 if not Assigned(frmENRZAObjectShow) then
    frmENRZAObjectShow := TfrmENRZAObjectShow.Create(Application, fmChild);
 frmENRZAObjectShow.Show;
end;

procedure TfrmMain.miSDTUObjectsClick(Sender: TObject);
begin
 if not Assigned(frmENSDTUObjectShow) then
    frmENSDTUObjectShow := TfrmENSDTUObjectShow.Create(Application, fmChild);
 frmENSDTUObjectShow.Show;
end;

procedure TfrmMain.miWorksZbytClick(Sender: TObject);
begin
  frmreportWorksZbyt := TfrmreportWorksZbyt.Create(Application, dsInsert);
  try
     frmreportWorksZbyt.ShowModal;
  finally
     frmreportWorksZbyt.Free;
  end;
end;

procedure TfrmMain.miWriteOffMaterialsByCarClick(Sender: TObject);
var
  reportWriteOffForm: TfrmReportWriteOffMaterialsByCar;
begin
       reportWriteOffForm := TfrmReportWriteOffMaterialsByCar.Create(Application, dsInsert);
     try
       reportWriteOffForm.ShowModal;
     finally
       reportWriteOffForm.Free;
     end;
end;

procedure TfrmMain.N160ReportConsolidTableSstateExecutionContractsClick(Sender: TObject);
begin
   frmServicesRegistryByPodrSum := TfrmServicesRegistryByPodrSum.Create(Application, dsInsert);
 try
   frmServicesRegistryByPodrSum.ShowModal;
 finally
   frmServicesRegistryByPodrSum.Free;
   frmServicesRegistryByPodrSum := nil;
 end;
end;

procedure TfrmMain.N165MaterialsForOrderDeliveriesOrderedtenderPriceClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
  TempENDepartment: ENDepartmentControllerSoapPort;
  depCodes: string;
begin

  frmENPeriodWithREN := TfrmENPeriodWithREN.Create(Application, dsInsert);
  frmENPeriodWithREN.Caption := 'Матеріали для замовлення з урахуванням терм. поставки із планів ';
  try
    frmENPeriodWithREN.HideControls([frmENPeriodWithREN.CheckPr_mat_group], False);
     //frmENPeriodWithREN.HideControls([frmENPeriodWithREN.chkEstimateStatus], False  );

    frmENPeriodWithREN.lblWorkState.Visible := True;
    frmENPeriodWithREN.lblElementType.Visible := True;
    frmENPeriodWithREN.edtWorkState.Visible := True;
    frmENPeriodWithREN.cbElementType.Visible := True;
    frmENPeriodWithREN.spbENPlanWorkState.Visible := True;
    frmENPeriodWithREN.SpeedButton1.Visible := True;
    frmENPeriodWithREN.GroupBox3.Visible := True;

   frmENPeriodWithREN.lblENBudgetName.Visible := False;
   frmENPeriodWithREN.edtENBudgetName.Visible := False;
   frmENPeriodWithREN.spbENBudget.Visible := False;
   frmENPeriodWithREN.spbENBudgetClear.Visible := False;

   frmENPeriodWithREN.Label6.Visible := true;
   frmENPeriodWithREN.ListBudget.Visible := true;
   frmENPeriodWithREN.SpeedButton3.Visible := true;
   frmENPeriodWithREN.SpeedButton4.Visible := true;
   frmENPeriodWithREN.Label7.Visible := true;
   frmENPeriodWithREN.Label8.Visible := true;

   frmENPeriodWithREN.Label1.Visible := true;
   frmENPeriodWithREN.SpeedButton5.Visible := true;
   frmENPeriodWithREN.Label2.Visible := true;
   frmENPeriodWithREN.SpeedButton6.Visible := true;
   frmENPeriodWithREN.Label3.Visible := true;
   frmENPeriodWithREN.listDDS.Visible := true;

    if frmENPeriodWithREN.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 29);
      SetLength(args, 29);

      argNames[0] := 'yearGen';
      args[0] := frmENPeriodWithREN.edtYearGen.Text;

      argNames[1] := 'monthGen';
      args[1] := IntToStr(frmENPeriodWithREN.edtMonthGen.ItemIndex + 1);

      argNames[2] := 'renCode';
      args[2] := IntToStr(frmENPeriodWithREN.renCode);

      argNames[3] := 'renName';
      if frmENPeriodWithREN.renName <> '' then
        args[3] := frmENPeriodWithREN.renName
      else
        args[3] := 'ХЕРСОНОБЛЕНЕРГО';

      argNames[4] := 'wholeYear';
      args[4] := IntToStr(Ord(frmENPeriodWithREN.chbWholeYear.Checked));

      argNames[5] := 'dNameField';
      if frmENPeriodWithREN.chbByRENs.Checked then
        args[5] := 'd.name'
      else
        args[5] := '1';

      argNames[26] := 'dCodeField';
      if frmENPeriodWithREN.chbByRENs.Checked then
        args[26] := 'd.code'
      else
        args[26] := '1';

      argNames[6] := 'objNameField';
      if frmENPeriodWithREN.chbByObjects.Checked then
        args[6] := '0'
      else
        args[6] := '1';

      argNames[7] := 'elementCode';
      args[7] := IntToStr(frmENPeriodWithREN.elementCode);
      ///////

      argNames[8] := 'elementName';
      if frmENPeriodWithREN.elementName <> '' then
        args[8] := frmENPeriodWithREN.elementName
      else
        args[8] := '';

      argNames[9] := 'budgName';
      if frmENPeriodWithREN.budgetName <> '' then
        args[9] := frmENPeriodWithREN.budgetName
      else
        args[9] := '';

      argNames[10] := 'budgCode';
      //args[10] := IntToStr(frmENPeriodWithREN.budgetCode);
      args[10] := frmENPeriodWithREN.strBudget;

      argNames[11] := 'dbNameField';
      if frmENPeriodWithREN.chbByBudgets.Checked then
        args[11] := 'db.name'
      else
        args[11] := '1';

      argNames[27] := 'dbCodeField';
      if frmENPeriodWithREN.chbByBudgets.Checked then
        args[27] := 'db.code'
      else
        args[27] := '1';

      argNames[12] := 'pKindCode';
      if frmENPeriodWithREN.rbYear.Checked = true then
         args[12] := '1'   // годовой
      else
         args[12] := '2'; // текущий

      reportName := '';

      argNames[13] := 'kindrefcodemat';
      if frmENPeriodWithREN.chkkindrefcodemat.Checked = True then
       args[13] := '2' // отчет формируется с паливно мастильними материалами
      else
       args[13] := '1'; // отчет формируется без паливно мастильних материалов

      argNames[14] := 'stateworkcode';
      args[14] := IntToStr(frmENPeriodWithREN.WorkStateCode);

      argNames[15] := 'stateworkname';
      if frmENPeriodWithREN.WorkStateCode <> 0 then
      args[15] := frmENPeriodWithREN.WorkStateName
      else
      args[15] := ' Всі типи робіт ';

      argNames[16] := 'elementtypecode';
      if (frmENPeriodWithREN.cbElementType.ItemIndex = -1) or (frmENPeriodWithREN.cbElementType.ItemIndex = 0) then
      args[16] := IntToStr(0)
      else
        args[16] := IntToStr(Integer(frmENPeriodWithREN.cbElementTYpe.Items.Objects[frmENPeriodWithREN.cbElementType.ItemIndex]));

      argNames[17] := 'elementtypename';
      if (frmENPeriodWithREN.cbElementType.ItemIndex = -1) or (frmENPeriodWithREN.cbElementType.ItemIndex = 0) then
      args[17] := ' Всі типи об"єктів   '
      else
      args[17] := frmENPeriodWithREN.ObjectName;  // frmENPeriodWithREN.cbElementTYpe.Items.Objects[frmENPeriodWithREN.cbElementType.ItemIndex]

      ///////
      argNames[18] := 'pr_par_mat';
      if frmENPeriodWithREN.CheckPr_mat_group.Checked = True then
        args[18] := '1'
      else
        args[18] := '0';

      if frmENPeriodWithREN.GroupBox3.Visible = true then
     begin
        argNames[19] := 'planform';
        if ((frmENPeriodWithREN.chkFormWorkPozaPlan.Checked = True) and (frmENPeriodWithREN.chkFormWorkPlan.Checked = True)) then
        args[19] := '0'
        else if frmENPeriodWithREN.chkFormWorkPlan.Checked = True then // плановые работы
        args[19] := '1'
        else if frmENPeriodWithREN.chkFormWorkPozaPlan.Checked = True then   // внеплановые работы
        args[19] := '2';
     end;

     argNames[20] := 'materialGroupCode';
    if frmENPeriodWithREN.materialGroupCode > 0 then
      args[20] := IntToStr(frmENPeriodWithREN.materialGroupCode)
    else
      args[20] := IntToStr(0);

      argNames[21] := 'estimatestatus';
      if frmENPeriodWithREN.chkEstimateStatus.Checked =  True then
        args[21] := '1'
      else
        args[21] := '0';

      // распределение по типам заявок в которые должны попасть материалы из планов
      argNames[22] := 'pBudgOrInv';
      if ((frmENPeriodWithREN.cbOrderTypeBudg.Checked = True) and (frmENPeriodWithREN.cbOrderTypeInvest.Checked = True)) then
        args[22] := '0'
      else if ((frmENPeriodWithREN.cbOrderTypeBudg.Checked = False) and (frmENPeriodWithREN.cbOrderTypeInvest.Checked = False)) then
        args[22] := '0'
      else if ((frmENPeriodWithREN.cbOrderTypeBudg.Checked = True) and (frmENPeriodWithREN.cbOrderTypeInvest.Checked = False)) then
        args[22] := '1'
      else if ((frmENPeriodWithREN.cbOrderTypeBudg.Checked = False) and (frmENPeriodWithREN.cbOrderTypeInvest.Checked = True)) then
        args[22] := '2';

      if ((frmENPeriodWithREN.CheckPr_identid.Checked = True) and (frmENPeriodWithREN.CheckPr_identid2015.Checked = True)) then
       begin
        Application.MessageBox(PChar('Оберіть тільки один код ДК по якому групувати !!!'), PChar('Увага!'), MB_ICONWARNING);
        ModalResult := mrNone;
         Exit;
        end;

      argNames[23] := 'pr_identid';
      if  frmENPeriodWithREN.CheckPr_identid.Checked = True then
       args[23] :=  '1'
      else if frmENPeriodWithREN.CheckPr_identid2015.Checked = True  then
       args[23] :=  '2'
      else
       args[23] :=  '0';

      argNames[24] := 'maxorderperiod';
       args[24] := frmENPeriodWithREN.maxorderperiod;

      argNames[25] := 'strDDS';
       args[25] :=  frmENPeriodWithREN.strDDS;


       // dsit есть параметры 26 и 27
      argNames[28] := 'department';
        if frmENPeriodWithREN.renCode > 0 then
         begin
           TempENDepartment := HTTPRIOENDepartment as ENDepartmentControllerSoapPort;
           depCodes := TempENDepartment.getDepartmentCodesDown(frmENPeriodWithREN.renCode);
        args[28] := '' + depCodes + '';
         end
       else
       args[28] := ' select code from endepartment ';

      if frmENPeriodWithREN.chbWholeYear.Checked then
        if frmENPeriodWithREN.chkEstimateStatus.Checked then
            reportName := 'materialsOrderByRenWithMonths'
        else
           //reportName := 'materialsForOrderPlanAndOrder2'
           //reportName := 'Order_material_for_work2/materialsForOrderPlanAndOrder2'
           // SUPP-28684 для отчета по году в развернутом виде
           // прикручиваем данным с отчета "звіти СОТ / закінчення терміну використання )
          reportName := 'Order_material_for_work_tend/materialsForOrderPlanAndOrder2&sot'

      else
        reportName := 'materialsOrderByRenNoMonths';

      /////// 28.07.10
      //try
{
      if EncodeDate(StrToInt(frmENPeriodWithREN.edtYearGen.Text), frmENPeriodWithREN.edtMonthGen.ItemIndex + 1, 1) >
         StrToDate('01.08.2010') then
      begin
        Application.MessageBox(PChar('На данный момент этот отчет запрещено формировать за период позднее Августа 2010 г.!'),
                               PChar('Внимание!'), MB_ICONWARNING);
        Exit;
      end; }
      //except
      //  on EConvertError do Exit;
      //end;
      ///////

      makeReport(reportName, argNames, args, 'xls');
      //makeReportAuth(reportName, argNames, args, 'xls');
    end;
  finally
    frmENPeriodWithREN.Free;
  end;
end;

procedure TfrmMain.N168miReportDodatok3YearSumByMontsPlanClick(Sender: TObject);
begin
   FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try
   FrmReportDodatok3.Label1.Visible := False;
   FrmReportDodatok3.Label2.Visible := False;
   FrmReportDodatok3.edtDateStart.Visible := False;
   FrmReportDodatok3.edtDateFinal.Visible := False;
   FrmReportDodatok3.lblYearGen.Visible := True;
   FrmReportDodatok3.edtYearGen.Visible := True;
    FrmReportDodatok3.report_vid := 22; // Звіт по прогнозованому завантаженю технічного персоналу структуних підрозділів по МІСЯЧНИМ планам на рік" з розбивкою по місяцям, бригадам, структурним підрозділам
    FrmReportDodatok3.pplankind := 2; // годовые планы
   FrmReportDodatok3.edtEPRenName.Visible := False;
   FrmReportDodatok3.spbEPRen.Visible := False;
   FrmReportDodatok3.spbEPRenClear.Visible := False;
   FrmReportDodatok3.lblEPRenName.Visible := False;

   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;
end;

procedure TfrmMain.N169miReportDodatok3YearSumZbytByMonthPlanClick(Sender: TObject);
begin
   FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try
   FrmReportDodatok3.Label1.Visible := False;
   FrmReportDodatok3.Label2.Visible := False;
   FrmReportDodatok3.edtDateStart.Visible := False;
   FrmReportDodatok3.edtDateFinal.Visible := False;
   FrmReportDodatok3.lblYearGen.Visible := True;
   FrmReportDodatok3.edtYearGen.Visible := True;
    FrmReportDodatok3.report_vid := 23; // Звіт по прогнозованому завантаженю збутового персоналу структуних підрозділів по месячным планам на год
    FrmReportDodatok3.pplankind := 2; // годовые планы
   FrmReportDodatok3.edtEPRenName.Visible := False;
   FrmReportDodatok3.spbEPRen.Visible := False;
   FrmReportDodatok3.spbEPRenClear.Visible := False;
   FrmReportDodatok3.lblEPRenName.Visible := False;

   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;
end;

procedure TfrmMain.N16Click(Sender: TObject);
begin
  frmCCRunReport:=TfrmCCRunReport.Create(Application, dsEdit);
  try
    frmCCRunReport.reportName:=TMenuItem(Sender).Caption;
    frmCCRunReport.ShowModal;
  finally
    frmCCRunReport.Free;
    frmCCRunReport:=nil;
  end;
end;

procedure TfrmMain.miAverageAccountingPersonalNPZClick(Sender: TObject);
begin
   frmAverageAccountingPersonalNPZ := TfrmAverageAccountingPersonalNPZ.Create(Application, dsInsert);
   frmAverageAccountingPersonalNPZ.reportForBrigade := false;
   frmAverageAccountingPersonalNPZ.Caption := 'Середньооблікова чисельність штатних працівників';
 try
   frmAverageAccountingPersonalNPZ.ShowModal;
 finally
   frmAverageAccountingPersonalNPZ.Free;
 end;
end;

procedure TfrmMain.N16SearchPlansNormativeMaterialsClick(Sender: TObject);
var
  frm: TfrmReportPlanListByMaterial;
begin
     frm := TfrmReportPlanListByMaterial.Create(Application, dsInsert);
  try

     frm.report := 1;
     frm.ShowModal;

  finally
    frm.Free;

 end;
end;

procedure TfrmMain.miMaterialsParametersEditClick(Sender: TObject);
begin
  frmMaterialsParametersEdit := TfrmMaterialsParametersEdit.Create(Application, dsInsert);
  try
    frmMaterialsParametersEdit.ShowModal;
  finally
    frmMaterialsParametersEdit.Free;
  end;
end;

procedure TfrmMain.mimaxminpricebybillClick(Sender: TObject);
begin
  frmReportLastBuyMaterials := TfrmReportLastBuyMaterials.Create(Application, dsInsert);
  frmReportLastBuyMaterials.report_var := 2;
  try
    frmReportLastBuyMaterials.ShowModal;
  finally
    frmReportLastBuyMaterials.Free;
  end;

end;

procedure TfrmMain.N43Click(Sender: TObject);
begin
  frmEPRunReport:=TfrmEPRunReport.Create(Application, dsEdit);
  try
    frmEPRunReport.reportName:=TMenuItem(Sender).Caption;
    frmEPRunReport.ShowModal;
  finally
    frmEPRunReport.Free;
    frmEPRunReport:=nil;
  end;
end;

procedure TfrmMain.N43MaterialsPlansMonthClick(Sender: TObject);
var
  frm: TfrmReportPlanListByMaterial;
begin
     frm := TfrmReportPlanListByMaterial.Create(Application, dsInsert);
  try

     frm.report := 2;
     frm.ShowModal;

  finally
    frm.Free;

 end;
end;

procedure TfrmMain.N046101Click(Sender: TObject);
begin
 if not Assigned(frmENLineCableShow) then
    frmENLineCableShow := TfrmENLineCableShow.Create(Application, fmChild);
 frmENLineCableShow.Show;
end;

procedure TfrmMain.miAddition4Click(Sender: TObject);
begin
 frmReportDodatok4 := TFrmReportDodatok4.Create(Application, dsInsert);
 try
   frmReportDodatok4.reportType := 0;
   frmReportDodatok4.chkShowWorkFinish.Visible := True;
   frmReportDodatok4.chkShow_doll.Visible := True;
   frmReportDodatok4.chkShow_rechetka.Visible := True;

 //  frmReportDodatok4.CheckListBox1.Visible:= True;
  // frmReportDodatok4.CheckListBox1.Checked[0]:= True;
   frmReportDodatok4.ShowModal;

 finally
   frmReportDodatok4.Free;
 end;
end;

procedure TfrmMain.miTransportObjectsClick(Sender: TObject);
begin
  if not Assigned(frmTKTransportRealShow) then
      frmTKTransportRealShow := TfrmTKTransportRealShow.Create(Application, fmChild);
  frmTKTransportRealShow.Show;
end;

procedure TfrmMain.N44Click(Sender: TObject);
begin
  if not Assigned(frmENBuilderObjectShow) then
    frmENBuilderObjectShow := TfrmENBuilderObjectShow.Create(Application, fmChild);
  frmENBuilderObjectShow.Show;
end;

procedure TfrmMain.N46Click(Sender: TObject);
begin
  if not Assigned(frmENMetrologyObjectShow) then
    frmENMetrologyObjectShow := TfrmENMetrologyObjectShow.Create(Application, fmChild);
  frmENMetrologyObjectShow.Show;
end;

procedure TfrmMain.N48Click(Sender: TObject);
begin
  if not Assigned(frmENMetrologyDeviceShow) then
    frmENMetrologyDeviceShow := TfrmENMetrologyDeviceShow.Create(Application, fmChild);
  frmENMetrologyDeviceShow.Show;
end;

procedure TfrmMain.N47Click(Sender: TObject);
//var frm : TfrmMetrologyCountersAddToPlan;
begin
  if not Assigned(frmENMetrologyCounterShow) then
    frmENMetrologyCounterShow := TfrmENMetrologyCounterShow.Create(Application, fmChild);
  frmENMetrologyCounterShow.Show;

(*
   frm:=TfrmMetrologyCountersAddToPlan.Create(Application,dsEdit);
   try
      with frm do
        if ShowModal = mrOk then
        begin
{            try
               if ENMetrologyCounterObj.element = nil then ENMetrologyCounterObj.element := ENElement.Create();
               ENMetrologyCounterObj.element.code := StrToInt(GetReturnValue(sgENElement,0));
               edtENElementElementName.Text:=GetReturnValue(sgENElement,1);
            except
               on EConvertError do Exit;
            end;
            }
        end;
   finally
      frm.Free;
   end;
*)
end;

procedure TfrmMain.miTMCRaznaryadkaPodrClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin

end;

procedure TfrmMain.miNPZ3PersonalClick(Sender: TObject);
begin

 FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try
    FrmReportDodatok3.report_vid := 2; // вид отчета - развернутый по персоналу
    FrmReportDodatok3.pplankind := 4; // факт
   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;
end;

procedure TfrmMain.miNpz_otp_pl_factClick(Sender: TObject);
begin
  FrmReportPercentLoadBrigades  := TFrmReportPercentLoadBrigades.Create(Application, dsInsert);
 try
   FrmReportPercentLoadBrigades.ShowModal;
 finally
   FrmReportPercentLoadBrigades.Free;
 end;
end;

procedure TfrmMain.miActRegistryClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
begin
  frmENDateRangeWithDepartment := TfrmENDateRangeWithDepartment.Create(Application, dsInsert);
  try
    if frmENDateRangeWithDepartment.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 3);
      SetLength(args, 3);

      argNames[0] := 'departmentcode';
      args[0] := IntToStr(frmENDateRangeWithDepartment.departmentCode);

      argNames[1] := 'datefrom';
      args[1] := DateToStr(frmENDateRangeWithDepartment.dtpDateFrom.Date);

      argNames[2] := 'dateto';
      args[2] := DateToStr(frmENDateRangeWithDepartment.dtpDateTo.Date);
      ///////

      makeReport('actReestr', argNames, args, 'xls');
    end;
  finally
    frmENDateRangeWithDepartment.Free;
  end;
end;

procedure TfrmMain.miActsForRecyclableMaterialsClick(Sender: TObject);
var
  f: ENActFilter;
begin
  f := ENActFilter.Create;
  SetNullIntProps(f);
  SetNullXSProps(f);

  f.element := ENElement.Create();
  f.element.code := ENELEMENTCODE_RECYCLABLE_MATERIALS;

  if not Assigned(frmENActForRecyclableMaterialsShow) then
    frmENActForRecyclableMaterialsShow := TfrmENActForRecyclableMaterialsShow.Create(Application, fmChild, f);
  frmENActForRecyclableMaterialsShow.Show;
end;

procedure TfrmMain.miActsGroupReportClick(Sender: TObject);
begin
  frmreportActZbyt := TfrmreportActZbyt.Create(Application, dsInsert);
  try
     frmreportActZbyt.ShowModal;
  finally
     frmreportActZbyt.Free;
  end;
end;

procedure TfrmMain.miMetrologyClick(Sender: TObject);
begin
  frmMetrologyCountersSelectEdit := TfrmMetrologyCountersSelectEdit.Create(Application, dsInsert);
  try
    frmMetrologyCountersSelectEdit.isForWriteOff := false;

    if frmMetrologyCountersSelectEdit.ShowModal = mrOk then
    begin

    end;
  finally
    frmMetrologyCountersSelectEdit.Free;
  end;
end;

procedure TfrmMain.miIzolationClick(Sender: TObject);
begin
  if not Assigned(frmENIzolationObjectShow) then
    frmENIzolationObjectShow := TfrmENIzolationObjectShow.Create(Application, fmChild);
  frmENIzolationObjectShow.Show;
end;

procedure TfrmMain.N52Click(Sender: TObject);
begin
 if not Assigned(frmENSITEquipTypeShow) then
    frmENSITEquipTypeShow := TfrmENSITEquipTypeShow.Create(Application, fmChild);
  frmENSITEquipTypeShow.Show;
end;

procedure TfrmMain.N51Click(Sender: TObject);
begin
 if not Assigned(frmENSITEquipmentShow) then
    frmENSITEquipmentShow := TfrmENSITEquipmentShow.Create(Application, fmChild);
  frmENSITEquipmentShow.Show;
end;

procedure TfrmMain.miAddition3OtherPersRazvernNPZ_FACTClick(Sender: TObject);
begin
   FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try
    FrmReportDodatok3.report_vid := 52; // вид отчета - развернутый по персоналу other personal
    FrmReportDodatok3.pplankind := 4; // факт
   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;
end;

procedure TfrmMain.miAddition3OtherPersRazvernNPZ_PLANClick(Sender: TObject);
begin
   FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try
    FrmReportDodatok3.report_vid := 52; // вид отчета - развернутый по персоналу other personal
    FrmReportDodatok3.pplankind := 3; // план
   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;
end;

procedure TfrmMain.miAddition3OtherPersSvernNPZ_FACTClick(Sender: TObject);
begin
  FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try
    FrmReportDodatok3.report_vid := 53; // вид отчета - загрузка фактического персонала в бригаде  other personal
    FrmReportDodatok3.pplankind := 4; // факт
   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;
end;

procedure TfrmMain.miAddition3OtherPersSvernNPZ_PLANClick(Sender: TObject);
begin
  FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try
    FrmReportDodatok3.report_vid := 53; // вид отчета - загрузка фактического персонала в бригаде other personal
    FrmReportDodatok3.pplankind := 3; // план
   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;
end;

procedure TfrmMain.miAddition3PlanLoadingWithZadanPlanClick(Sender: TObject);
begin
  FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try

    FrmReportDodatok3.report_vid := 15; // вид отчета загрузка с месячн планов с учетом заданий планов с месячніми которіе в прошлом периоде
    FrmReportDodatok3.pplankind := 2; // поточні плани
   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;

end;

procedure TfrmMain.miAddition3PlanLoading_CurrentPlansClick(Sender: TObject);
begin
   FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try

    FrmReportDodatok3.report_vid := 4; // вид отчета нормативная загрузка
    FrmReportDodatok3.pplankind := 2; // поточні плани
   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;

end;

procedure TfrmMain.miAddition3PlanLoading_PlansPlansClick(Sender: TObject);
begin
   FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try
    FrmReportDodatok3.report_vid := 4; // вид отчета нормативная загрузка
    FrmReportDodatok3.pplankind := 3; // задание план
   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;

end;

procedure TfrmMain.miAddition3PlanLoading_FactClick(Sender: TObject);
begin
   FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try
    FrmReportDodatok3.report_vid := 4; // вид отчета нормативная загрузка
    FrmReportDodatok3.pplankind := 4; // завдання факт
   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;

end;

procedure TfrmMain.miAddition3_razd_CurentPlansClick(Sender: TObject);
begin

 FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try
    FrmReportDodatok3.report_vid := 1; // вид отчета обычный третий додаток
    FrmReportDodatok3.pplankind := 2; // поточні плани
   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;
end;

procedure TfrmMain.miAddition3_razd_ZadanPlansClick(Sender: TObject);
begin
  //ShowMessage('Этот отчет пока не работает ...');


 FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try
    FrmReportDodatok3.report_vid := 1; // вид отчета обычный третий додаток
    FrmReportDodatok3.pplankind := 3; // план
   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;
end;

procedure TfrmMain.miAddition3_razd_ZadanFactClick(Sender: TObject);
begin
  //ShowMessage('Этот отчет пока не работает ...');


 FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try
    FrmReportDodatok3.report_vid := 1; // вид отчета обычный третий додаток
    FrmReportDodatok3.pplankind := 4; // факт
   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;
end;

procedure TfrmMain.miAddition3_migr_hum_CurrentPlansClick(Sender: TObject);
begin
  FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try
    FrmReportDodatok3.report_vid := 2; // вид отчета - развернутый по персоналу
    FrmReportDodatok3.pplankind := 2; // Поточні плани
   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;
end;

procedure TfrmMain.miAddition3_migr_hum_ZadanPlanClick(Sender: TObject);
begin
   FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try
    FrmReportDodatok3.report_vid := 2; // вид отчета - развернутый по персоналу
    FrmReportDodatok3.pplankind := 3; // план
   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;
end;

procedure TfrmMain.miAddition3_migr_hum_ZadanFactClick(Sender: TObject);
begin
   FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try
    FrmReportDodatok3.report_vid := 2; // вид отчета - развернутый по персоналу
    FrmReportDodatok3.pplankind := 4; // факт
   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;
end;

procedure TfrmMain.miAdition3_sht_CurrentPlanClick(Sender: TObject);
begin
    FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try
    FrmReportDodatok3.report_vid := 3; // вид отчета - загрузка фактического персонала в бригаде
    FrmReportDodatok3.pplankind := 2; // поточні плани
   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;
end;

procedure TfrmMain.miAdition3_sht_ZadanPlanClick(Sender: TObject);
begin
  FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try
    FrmReportDodatok3.report_vid := 3; // вид отчета - загрузка фактического персонала в бригаде
    FrmReportDodatok3.pplankind := 3; // план
   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;
end;

procedure TfrmMain.miAdition3_sht_ZadanFactClick(Sender: TObject);
begin
  FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try
    FrmReportDodatok3.report_vid := 3; // вид отчета - загрузка фактического персонала в бригаде
    FrmReportDodatok3.pplankind := 4; // факт
   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;
end;

procedure TfrmMain.miENObjectsClick(Sender: TObject);
var
  f: ENPurchasesObjectFilter;
  frm: TfrmENPurchasesObjectShow;
begin

 f := ENPurchasesObjectFilter.create();
 SetNullIntProps(f);
 SetNullXSProps(f);
 f.elementTypeRef := ENElementTypeRef.Create();
 f.elementTypeRef.code := TMenuItem(Sender).Tag;

 frmENPurchasesObjectShow := TfrmENPurchasesObjectShow.Create(Application, fmChild, f);
 try
   frmENPurchasesObjectShow.Show;
 finally
   //frmENPurchasesObjectShow.Free;
   //f.Free;
 end;

end;

procedure TfrmMain.N55Click(Sender: TObject);
var
  f: ENPurchasesNoObjectFilter;
begin

 f := ENPurchasesNoObjectFilter.Create();
 SetNullIntProps(f);
 SetNullXSProps(f);
 f.typeRef := ENPurchasesNoObjectTypeRef.Create;
 if TMenuItem(Sender).Tag = EN_PURCHASES_NO_OBJECT then
 begin
   f.typeRef.code := NO_OBJECT_PURCHASES;
 end;

 if TMenuItem(Sender).Tag = EN_WRITING_NO_OBJECT then
 begin
   f.typeRef.code := NO_OBJECT_WRITING;
 end;

 if TMenuItem(Sender).Tag = EN_GIFT_NO_OBJECT then
 begin
   f.typeRef.code := NO_OBJECT_GIFT;
 end;

  if TMenuItem(Sender).Tag = EN_AVR16_NO_OBJECT then
 begin
   f.typeRef.code := NO_OBJECT_AVR16;
 end;

 frmENPurchasesNoObjectShow := TfrmENPurchasesNoObjectShow.Create(Application, fmChild, f);

 try
   frmENPurchasesNoObjectShow.Caption := TMenuItem(Sender).Caption;
    frmENPurchasesNoObjectShow.UpdateCaption;
   frmENPurchasesNoObjectShow.Show;
 finally
   //frmENPurchasesObjectShow.Free;
   //f.Free;
 end;

{
 if not Assigned(frmENPurchasesNoObjectShow) then
    frmENPurchasesNoObjectShow := TfrmENPurchasesNoObjectShow.Create(Application, fmChild);
  frmENPurchasesNoObjectShow.Show;
}
{

var
  f : ENOtherObjectFilter;
  //frm : TfrmENOtherObjectShow;
begin

 f := ENOtherObjectFilter.create();
 SetNullIntProps(f);
 SetNullXSProps(f);
 f.typeRef := ENOtherObjectTypeRef.Create();


 frmENOtherObjectShow := TfrmENOtherObjectShow.Create(Application, fmChild, f);

 if TMenuItem(Sender).Tag = EN_METROLOGY_SUBSTATION then
 begin
   f.typeRef.code := ABSTRACT_TYPE_METROLOGY;
 end;

 if TMenuItem(Sender).Tag = EN_EB_OBJECT then
 begin
   f.typeRef.code := ABSTRACT_TYPE_EB;
 end;


 try
   frmENOtherObjectShow.Caption := TMenuItem(Sender).Caption;
    frmENOtherObjectShow.UpdateCaption;
   frmENOtherObjectShow.Show;
 finally
   //frmENPurchasesObjectShow.Free;
   //f.Free;
 end;

}
end;

procedure TfrmMain.miReportStatesClick(Sender: TObject);
begin
 	frmreportStates := TfrmreportStates.Create(Application, dsInsert);
 	try
		frmreportStates.ShowModal;
  finally
		frmreportStates.Free;
 end;
end;

procedure TfrmMain.miReportSubstationClick(Sender: TObject);
begin
   frmReportSubstatioon := TfrmReportSubstatioon.Create(Application, dsInsert);
  try
     frmReportSubstatioon.ShowModal;
  finally
    frmReportSubstatioon.Free;
 end;
end;

procedure TfrmMain.miReportTechConditionsPlanClick(Sender: TObject);
begin
  	frmReportTechConditionsPlan := TfrmReportTechConditionsPlan.Create(Application, dsInsert);
  frmReportTechConditionsPlan.CheckBox1.Visible := True;
  try
   frmReportTechConditionsPlan.ShowModal;
  finally
    frmReportTechConditionsPlan.Free;
 end;
end;

procedure TfrmMain.miRepPercentPremClick(Sender: TObject);
begin
  FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try
   FrmReportDodatok3.Caption := 'Формування звіту по премії';
    FrmReportDodatok3.report_vid := 5; // Вид отчета - процент премии сотрудникам
    FrmReportDodatok3.pplankind := 4; // Фактичны плани
   FrmReportDodatok3.chkOnlyZatvAct.Visible := True;
   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;
end;

procedure TfrmMain.N65Click(Sender: TObject);
begin
 if not Assigned(frmENTransformerObjectShow) then
    frmENTransformerObjectShow := TfrmENTransformerObjectShow.Create(Application, fmChild);
  frmENTransformerObjectShow.Show;
end;

procedure TfrmMain.miPurchasesBindingClick(Sender: TObject);
begin
  frmENPurchasesBindingEdit := TfrmENPurchasesBindingEdit.Create(Application, dsInsert);
  try
    if frmENPurchasesBindingEdit.ShowModal = mrOk then
    begin

    end;
  finally
    frmENPurchasesBindingEdit.Free;
  end;
end;

procedure TfrmMain.miPurchasesNoObjectAVZClick(Sender: TObject);
var
  f: ENPurchasesNoObjectFilter;
begin
  f := ENPurchasesNoObjectFilter.Create();
  SetNullIntProps(f);
  SetNullXSProps(f);
  f.typeRef := ENPurchasesNoObjectTypeRef.Create;

  f.typeRef.code := NO_OBJECT_AVZ;

  frmENPurchasesNoObjectShow := TfrmENPurchasesNoObjectShow.Create(Application, fmChild, f);
  try
    frmENPurchasesNoObjectShow.Caption := TMenuItem(Sender).Caption;
    frmENPurchasesNoObjectShow.UpdateCaption;
    frmENPurchasesNoObjectShow.Show;
  finally
    //frmENPurchasesObjectShow.Free;
    //f.Free;
  end;
end;

procedure TfrmMain.N66Click(Sender: TObject);
begin
 if not Assigned(frmENOperativeObjectShow) then
    frmENOperativeObjectShow := TfrmENOperativeObjectShow.Create(Application, fmChild);
  frmENOperativeObjectShow.Show;
end;

procedure TfrmMain.N67Click(Sender: TObject);
begin
  frmMaterialsParametersOutEdit := TfrmMaterialsParametersOutEdit.Create(Application, dsInsert);
  try
    frmMaterialsParametersOutEdit.ShowModal;
  finally
    frmMaterialsParametersOutEdit.Free;
  end;
end;

procedure TfrmMain.miRequestDepartmentClick(Sender: TObject);
var
  f: RQOrderFilter;
begin
  f := RQOrderFilter.create();
  SetNullIntProps(f);
  SetNullXSProps(f);

  f.creationMethodRef := RQOrderCreationMethodRef.Create;
  f.creationMethodRef.code := RQORDER_CREATION_METHOD_MANUAL;

  f.kindRef := RQOrderKindRef.Create;
  f.kindRef.code := TMenuItem(Sender).Tag;

  if  f.kindRef.code = RQORDER_KIND_DEPARTMENT_ then
  begin
    if not Assigned(frmRQOrderDepartmentShow) then
      frmRQOrderDepartmentShow := TfrmRQOrderShow.Create(Application, fmChild, f);
    frmRQOrderDepartmentShow.Caption := TMenuItem(Sender).Caption;
    frmRQOrderDepartmentShow.UpdateCaption;
    frmRQOrderDepartmentShow.Show;
  end
  else if f.kindRef.code = RQORDER_KIND_BUDGET_ then
  begin
    if not Assigned(frmRQOrderBudgetShow) then
      frmRQOrderBudgetShow := TfrmRQOrderShow.Create(Application, fmChild, f);
    frmRQOrderBudgetShow.Caption := TMenuItem(Sender).Caption;
    frmRQOrderBudgetShow.UpdateCaption;
    frmRQOrderBudgetShow.Show;
  end
  else if f.kindRef.code = RQORDER_KIND_OE_ then
  begin
    if not Assigned(frmRQOrderOEShow) then
      frmRQOrderOEShow := TfrmRQOrderShow.Create(Application, fmChild, f);
    frmRQOrderOEShow.Caption := TMenuItem(Sender).Caption;
    frmRQOrderOEShow.UpdateCaption;
    frmRQOrderOEShow.Show;
  end
  else if f.kindRef.code = RQORDER_KIND_OE_PLANNED_AUTO then
  begin
    if not Assigned(frmRQOrderPlannedAutoShow) then
      frmRQOrderPlannedAutoShow := TfrmRQOrderShow.Create(Application, fmChild, f);
    frmRQOrderPlannedAutoShow.Caption := TMenuItem(Sender).Caption;
    frmRQOrderPlannedAutoShow.UpdateCaption;
    frmRQOrderPlannedAutoShow.Show;
  end
  else if f.kindRef.code = RQORDER_KIND_OE_NOPLANNED then
  begin
    if not Assigned(frmRQOrderNoPlannedShow) then
      frmRQOrderNoPlannedShow := TfrmRQOrderShow.Create(Application, fmChild, f);
    frmRQOrderNoPlannedShow.Caption := TMenuItem(Sender).Caption;
    frmRQOrderNoPlannedShow.UpdateCaption;
    frmRQOrderNoPlannedShow.Show;
  end
  else if f.kindRef.code = RQORDER_KIND_PRODUCTION then
  begin
    if not Assigned(frmRQOrderProductionShow) then
      frmRQOrderProductionShow := TfrmRQOrderShow.Create(Application, fmChild, f);
    frmRQOrderProductionShow.Caption := TMenuItem(Sender).Caption;
    frmRQOrderProductionShow.UpdateCaption;
    frmRQOrderProductionShow.Show;
  end
  else if f.kindRef.code = RQORDER_KIND_OE_PLANNED_SERVICES then
  begin
    if not Assigned(frmRQOrderPlanedServicesShow) then
      frmRQOrderPlanedServicesShow := TfrmRQOrderShow.Create(Application, fmChild, f);
    frmRQOrderPlanedServicesShow.Caption := TMenuItem(Sender).Caption;
    frmRQOrderPlanedServicesShow.UpdateCaption;
    frmRQOrderPlanedServicesShow.Show;
  end
  else if f.kindRef.code = RQORDER_KIND_OE_NOPLANNED_SERVICES then
  begin
    if not Assigned(frmRQOrderNoPlanedServicesShow) then
      frmRQOrderNoPlanedServicesShow := TfrmRQOrderShow.Create(Application, fmChild, f);
    frmRQOrderNoPlanedServicesShow.Caption := TMenuItem(Sender).Caption;
    frmRQOrderNoPlanedServicesShow.UpdateCaption;
    frmRQOrderNoPlanedServicesShow.Show;
  end;

end;

procedure TfrmMain.miMetrologyPlansEditClick(Sender: TObject);
begin
  frmENMetrologyPlansEdit := TfrmENMetrologyPlansEdit.Create(Application, dsInsert);
  try
    frmENMetrologyPlansEdit.isForWriteOff := false;

    if frmENMetrologyPlansEdit.ShowModal = mrOk then
    begin
    end;
  finally
    frmENMetrologyPlansEdit.Free;
  end;
end;

procedure TfrmMain.miMetrologyPlansWriteOffEditClick(Sender: TObject);
begin
  frmENMetrologyPlansEdit := TfrmENMetrologyPlansEdit.Create(Application, dsInsert);
  try
    frmENMetrologyPlansEdit.isForWriteOff := true;

    if frmENMetrologyPlansEdit.ShowModal = mrOk then
    begin
    end;
  finally
    frmENMetrologyPlansEdit.Free;
  end;
end;

procedure TfrmMain.miMetrologyWriteOffClick(Sender: TObject);
begin
  frmMetrologyCountersSelectEdit := TfrmMetrologyCountersSelectEdit.Create(Application, dsInsert);
  try
    frmMetrologyCountersSelectEdit.isForWriteOff := true;

    if frmMetrologyCountersSelectEdit.ShowModal = mrOk then
    begin

    end;
  finally
    frmMetrologyCountersSelectEdit.Free;
  end;
end;

procedure TfrmMain.N410miAddition4ZbytClick(Sender: TObject);
begin
 frmReportDodatok4 := TFrmReportDodatok4.Create(Application, dsInsert);
 try
   frmReportDodatok4.reportType := 1;
   frmReportDodatok4.ShowModal;

 finally
   frmReportDodatok4.Free;
 end;
end;

procedure TfrmMain.miMetrologyObjectsPlansClick(Sender: TObject);
begin
  frmENMetrologyObjectsSelectEdit := TfrmENMetrologyObjectsSelectEdit.Create(Application, dsInsert);
  try
    frmENMetrologyObjectsSelectEdit.isForWriteOff := false;

    if frmENMetrologyObjectsSelectEdit.ShowModal = mrOk then
    begin
    end;
  finally
    frmENMetrologyObjectsSelectEdit.Free;
  end;
end;

procedure TfrmMain.miMetrologyObjectsWriteOffPlansClick(Sender: TObject);
begin
  frmENMetrologyObjectsSelectEdit := TfrmENMetrologyObjectsSelectEdit.Create(Application, dsInsert);
  try
    frmENMetrologyObjectsSelectEdit.isForWriteOff := true;

    if frmENMetrologyObjectsSelectEdit.ShowModal = mrOk then
    begin
    end;
  finally
    frmENMetrologyObjectsSelectEdit.Free;
  end;
end;

procedure TfrmMain.N70Click(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin

  frmENActPrintFilterEdit := TfrmENActPrintFilterEdit.Create(Application, dsInsert);
  try
    ENActFilterObj := ENActFilter.Create;
    if frmENActPrintFilterEdit.ShowModal = mrOk then
    begin

      SetLength(argNames, 3);
      SetLength(args, 3);

      argNames[0] := 'dateGen';
      args[0] := XSDate2String(ENActFilterObj.dateGen);

      argNames[1] := 'codeMol';
      args[1] := ENActFilterObj.finMolCode;

      argNames[2] := 'codeRen';
      args[2] := inttostr(ENActFilterObj.element.renRef.code);

      reportName := 'ActByt/ActAll';

      if (ENActFilterObj.dateGen.Year = 2011) and (ENActFilterObj.dateGen.Month < 9) then
        reportName := '2011/' + reportName;
      if (((ENActFilterObj.dateGen.Year = 2011) and (ENActFilterObj.dateGen.Month >= 9)) or (ENActFilterObj.dateGen.Year > 2011)) then
        reportName := '201109/' + reportName;

      makeReport(reportName, argNames, args, 'xls');

    end;
  finally
    frmENActPrintFilterEdit.Free;
    frmENActPrintFilterEdit := nil;
    ENActFilterObj.Free;
    ENActFilterObj := nil;

  end;

end;

procedure TfrmMain.miENServicesObjectsClick(Sender: TObject);
begin

{
 frmENServicesObjectShow := TfrmENServicesObjectShow.Create(Application, fmChild);
 try
   frmENServicesObjectShow.Show;
 finally
   //frmENPurchasesObjectShow.Free;
   //f.Free;
 end;
}

  if not Assigned(frmENServicesObjectShow) then
    frmENServicesObjectShow := TfrmENServicesObjectShow.Create(Application, fmChild);
  frmENServicesObjectShow.Show;
end;

procedure TfrmMain.miENSettingsClick(Sender: TObject);
begin
  if not Assigned(frmENSettingsShow) then
    frmENSettingsShow := TfrmENSettingsShow.Create(Application, fmChild);
  frmENSettingsShow.Show;
end;

procedure TfrmMain.miENSheets4SOClick(Sender: TObject);
var
  sheets4SOFilter: ENSheets4SOFilter;
begin
  sheets4SOFilter := ENSheets4SOFilter.Create;
  SetNullIntProps(sheets4SOFilter);
  SetNullXSProps(sheets4SOFilter);
  sheets4SOFilter.sheet4sotype := ENSheets4SOTypeRef.Create;
  sheets4SOFilter.sheet4sotype.code := ENSHEETS4SOTYPE_LAND_SHEET;
  sheets4SOFilter.isLast := YES;
  sheets4SOFilter.conditionSQL := 'ENSHEETS4SO.NEXTSHEETDATE IS NOT NULL';
  sheets4SOFilter.orderBySQL := 'ENSHEETS4SO.NEXTSHEETDATE DESC';

  if not Assigned(frmENSheets4SOShow) then
  begin
    frmENSheets4SOShow := TfrmENSheets4SOShow.Create(Application, fmChild, sheets4SOFilter);
    frmENSheets4SOShow.DisableActions([frmENSheets4SOShow.actInsert, frmENSheets4SOShow.actEdit, frmENSheets4SOShow.actDelete{,
                                       frmENSheets4SOShow.actNoFilter}]);
    frmENSheets4SOShow.HideActions([frmENSheets4SOShow.actInsert, frmENSheets4SOShow.actEdit, frmENSheets4SOShow.actDelete{,
                                    frmENSheets4SOShow.actNoFilter}]);
  end;
  frmENSheets4SOShow.Show;
end;

procedure TfrmMain.miAvrVneplanWorkClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin

    frmAvarVneplanWork := TfrmAvarVneplanWork.Create(Application, dsInsert);

   try

    frmAvarVneplanWork.lblElementType.Visible := True;
    frmAvarVneplanWork.cbElementType.Visible := True;
   if frmAvarVneplanWork.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 14);
      SetLength(args, 14);

      argNames[0] := 'pdatestart';
      args[0] := dateToStr(frmAvarVneplanWork.dtpDateFrom.date);

      argNames[1] := 'pdatefinal';
      args[1] := dateToStr(frmAvarVneplanWork.dtpDateTo.date);

      argNames[2] := 'budgCode';
      args[2] := IntToStr(frmAvarVneplanWork.budgetCode);

      argNames[3] := 'renCode';
      args[3] :=  IntToStr(frmAvarVneplanWork.renCode);

      argNames[4] := 'elementtypecode';
      if (frmAvarVneplanWork.cbElementType.ItemIndex = -1) or (frmAvarVneplanWork.cbElementType.ItemIndex = 0) then
      args[4] := IntToStr(0)
      else
        args[4] := IntToStr(Integer(frmAvarVneplanWork.cbElementTYpe.Items.Objects[frmAvarVneplanWork.cbElementType.ItemIndex]));

      argNames[5] := 'elementCode';
      args[5] := IntToStr(frmAvarVneplanWork.elementCode);

      argNames[6] := 'elementName';
      if frmAvarVneplanWork.elementName <> '' then
        args[6] := frmAvarVneplanWork.elementName
      else
        args[6] := '';

      argNames[7] := 'pkindcode';
      if frmAvarVneplanWork.rbtypeAll.Checked = True then
         args[7] := '0'
      else if frmAvarVneplanWork.rbtypeYear.Checked = True then
         args[7] := '1'
      else if frmAvarVneplanWork.rbtypeMonth.Checked = True then
         args[7] := '2'
      else if frmAvarVneplanWork.rbtypePlan.Checked = True then
         args[7] := '3'
      else if frmAvarVneplanWork.rbtypeFact.Checked = True then
         args[7] := '4';

          //
      argNames[8] := 'techcardsourcecode';
        args[8] := IntToStr(frmAvarVneplanWork.techcardsourcecode);

      argNames[9] := 'tkclassificationtypecode';
        args[9] := IntToStr(frmAvarVneplanWork.tkclassificationtypecode);

      argNames[10] := 'NumberTechkard';
        if trim(frmAvarVneplanWork.edtNumberTechkard.Text) <> '' then
           args[10] := trim(frmAvarVneplanWork.edtNumberTechkard.Text)
        else
           args[10] := '';

      argNames[11] := 'NameTechkard';
        if trim(frmAvarVneplanWork.edtNameTechkard.Text) <> '' then
           args[11] := trim(frmAvarVneplanWork.edtNameTechkard.Text)
        else
           args[11] := '';

      argNames[12] := 'PlanWorkType';
        args[12] := IntToStr(frmAvarVneplanWork.WorkTypeCode);

      argNames[13] := 'ActTypeCode';
      args[13] := IntToStr(frmAvarVneplanWork.ActTypeCode);

        reportName := 'AvarVneplanWork';
        makeReport(reportName, argNames, args, 'xlsx');
    end;

   finally
    frmAvarVneplanWork.Free;
  end;

end;

procedure TfrmMain.miAwardByEnergozbytClick(Sender: TObject);
begin
   FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try
    FrmReportDodatok3.report_vid := 18; // вид отчета - премія
    FrmReportDodatok3.pplankind := 4; // план
   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;
end;

procedure TfrmMain.miReservedMaterialsClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin
   frmReservedMaterials := TfrmReservedMaterials.Create(Application, dsInsert);

   try

   if frmReservedMaterials.ShowModal = mrOk then
    begin

      SetLength(argNames, 5);
      SetLength(args, 5);

      if frmReservedMaterials.edtDateStart.Checked = True then
         begin
         argNames[0] := 'Pdate1';
         args[0] := dateToStr(frmReservedMaterials.edtDateStart.date);
         end
      else
         begin
         argNames[0] := 'Pdate1';
         args[0] := '01.01.2000';
         end;

      if frmReservedMaterials.edtDateFinal.Checked = True then
         begin
         argNames[1] := 'Pdate2';
         args[1] := dateToStr(frmReservedMaterials.edtDateFinal.date);
         end
      else
         begin
         argNames[1] := 'Pdate2';
         args[1] := '01.01.3000';
         end;

      if ((frmReservedMaterials.edtDateStart.Checked = True) and (frmReservedMaterials.edtDateFinal.Checked = False)) then
          begin
           argNames[1] := 'Pdate2';
           args[1] := dateToStr(frmReservedMaterials.edtDateStart.date);
          end;
      if ((frmReservedMaterials.edtDateStart.Checked = False) and (frmReservedMaterials.edtDateFinal.Checked = True)) then
          begin
           argNames[0] := 'Pdate1';
           args[0] := dateToStr(frmReservedMaterials.edtDateFinal.date);
          end;

      argNames[2] := 'DivCode';
      args[2] := frmReservedMaterials.molCode;

      argNames[3] := 'NN';
      if trim(frmReservedMaterials.edtnn.Text) = '' then
         args[3] := '0'
      else
         args[3] := frmReservedMaterials.edtnn.Text;

      argNames[4] := 'prizn';
      if ((frmReservedMaterials.chkRezerv.Checked = True) and (frmReservedMaterials.chkProv.Checked = True)) then
         args[4] := '0' // и зарезервированые и проведенные
      else if ((frmReservedMaterials.chkRezerv.Checked = False) and (frmReservedMaterials.chkProv.Checked = False)) then
         args[4] := '0' // и зарезервированые и проведенные
      else if ((frmReservedMaterials.chkRezerv.Checked = True) and (frmReservedMaterials.chkProv.Checked = False)) then
         args[4] := '1' // только зарезервированые
      else if ((frmReservedMaterials.chkRezerv.Checked = False) and (frmReservedMaterials.chkProv.Checked = True)) then
         args[4] := '2'; // только проведенные
      reportName := 'ReservedMaterials';
      makeReport(reportName, argNames, args, 'xls');

    end;

   finally
    frmReservedMaterials.Free;
  end;

end;

procedure TfrmMain.miResponseToAppealsClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
begin
  frmReportControlOfResponseToAppeals := TfrmReportControlOfResponseToAppeals.Create(Application, dsInsert);
  frmReportControlOfResponseToAppeals.Caption := 'Період для формування звіту';

  frmReportControlOfResponseToAppeals.pnlYellow.Visible := True;
  frmReportControlOfResponseToAppeals.pnlRed.Visible := True;
  frmReportControlOfResponseToAppeals.lblYellow.Visible := True;
  frmReportControlOfResponseToAppeals.lblRed.Visible := True;

  try
    if frmReportControlOfResponseToAppeals.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 4);
      SetLength(args, 4);

      argNames[0] := 'datestart';
      args[0] := DateToStr(frmReportControlOfResponseToAppeals.edtDateStart.Date);

      argNames[1] := 'datefinal';
      args[1] := DateToStr(frmReportControlOfResponseToAppeals.edtDateFinal.Date);

      argNames[2] := 'departmentCode';
      args[2] := IntToStr(frmReportControlOfResponseToAppeals.departmentCode);

      argNames[3] := 'withoutResponse';
      if frmReportControlOfResponseToAppeals.chkWithoutResponse.Checked then
        args[3] := ' and obxdocnum is null '
      else
        args[3] := ' ';
      ///////

      DMReports.makeReport4DocFlow('servicesQuality/controlOfResponseToAppeals', argNames, args, 'xls');
    end;
  finally
    frmReportControlOfResponseToAppeals.Free;
  end;
end;

procedure TfrmMain.miraznarPoPodrPlaniClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin
 frmReportRaznaryadka  := TfrmReportRaznaryadka.Create(Application, dsInsert);
  try
    if frmReportRaznaryadka.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 12);
      SetLength(args, 12);

//      argNames[0] := 'pdate1';
//      args[0] := dateToStr(frmReportRaznaryadka.edtDateStart.date);

//      argNames[1] := 'pdate2';
//      args[1] := dateToStr(frmReportRaznaryadka.edtDateFinal.date);

      argNames[0] := 'YearRaznar';
      args[0] :=  frmReportRaznaryadka.edtYearRaznar.Text;

      argNames[1] := 'MonthRaznar';
      args[1] :=  IntToStr(frmReportRaznaryadka.edtMonthRaznar.ItemIndex + 1);

      argNames[2] := 'YearVvod';
      args[2] :=  frmReportRaznaryadka.edtYearVvod.Text;

      argNames[3] := 'MonthVvod';
      args[3] :=  IntToStr(frmReportRaznaryadka.edtMonthVvod.ItemIndex + 1);

      argNames[4] := 'renCode';
      args[4] := IntToStr(frmReportRaznaryadka.renCode);

      argNames[5] := 'renName';
      if frmReportRaznaryadka.renName <> '' then
        args[5] := frmReportRaznaryadka.renName
      else
        args[5] := 'ХОЕ';

      argNames[6] := 'dNameField';
      args[6] := 'd.name';

      argNames[7] := 'budgName';
      if frmReportRaznaryadka.budgetName <> '' then
        args[7] := frmReportRaznaryadka.budgetName
      else
        args[7] := '';

      argNames[8] := 'budgCode';
      args[8] := IntToStr(frmReportRaznaryadka.budgetCode);

      argNames[9] := 'dbNameField';
      args[9] := 'db.name';

      argNames[10] := 'pKindCode';
       args[10] := '2'; // выбираем только текущие планы

      argNames[11] := 'condition';
       args[11] := frmReportRaznaryadka.estimateStatusCondition;

       //reportName:= '';
       reportName := 'materialsRaznaryadka';

      /////// 28.07.10
      //try
     { if EncodeDate(StrToInt(frmReportRaznaryadka.edtYearRaznar.Text), frmReportRaznaryadka.edtMonthRaznar.ItemIndex + 1, 1) >
         StrToDate('01.08.2010') then
      begin
        Application.MessageBox(PChar('На данный момент этот отчет запрещено формировать за период позднее Августа 2010 г.!'),
                               PChar('Внимание!'), MB_ICONWARNING);
        Exit;
      end;     }
      //except
      //  on EConvertError do Exit;
      //end;
      ///////

      if frmReportRaznaryadka.rbPDF.Checked = True then
         //makeReport(reportName, argNames, args, 'pdf');
         makeReportAuth(reportName, argNames, args, 'pdf');
      if frmReportRaznaryadka.rbXLS.Checked = True then
         //makeReport(reportName, argNames, args, 'xls');
         makeReportAuth(reportName, argNames, args, 'xls');
    end;
  finally
    frmReportRaznaryadka.Free;
  end;

end;

procedure TfrmMain.miraznarPoPodrZayavkaClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin
 frmReportRaznaryadkaHOE := TfrmReportRaznaryadkaHOE.Create(Application, dsInsert);
{ frmReportRaznaryadka.lbYearVvod.Visible:= false;
 frmReportRaznaryadka.lbMonthVvod.Visible:= false;
 frmReportRaznaryadka.edtYearVvod.Visible:= false;
 frmReportRaznaryadka.edtMonthVvod.Visible:= false;
 }

  try
    if frmReportRaznaryadkaHOE.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 12);
      SetLength(args, 12);

      argNames[0] := 'YearRaznar';
      args[0] :=  frmReportRaznaryadkaHOE.edtYearRaznar.Text;

      argNames[1] := 'MonthRaznar';
      args[1] :=  IntToStr(frmReportRaznaryadkaHOE.edtMonthRaznar.ItemIndex + 1);

      argNames[2] := 'renCode';
      args[2] := IntToStr(frmReportRaznaryadkaHOE.renCode);

      argNames[3] := 'renName';
      if frmReportRaznaryadkaHOE.renName <> '' then
        args[3] := frmReportRaznaryadkaHOE.renName
      else
        args[3] := 'ХОЕ';

      argNames[4] := 'dNameField';
      args[4] := 'd.name';

      argNames[5] := 'budgName';
      if frmReportRaznaryadkaHOE.budgetName <> '' then
        args[5] := frmReportRaznaryadkaHOE.budgetName
      else
        args[5] := '';

      argNames[6] := 'budgCode';
      args[6] := IntToStr(frmReportRaznaryadkaHOE.budgetCode);

      argNames[7] := 'dbNameField';
      args[7] := 'db.name';

      argNames[8] := 'pKindCode';
       args[8] := '2'; // выбираем только текущие планы


      argNames[9] := 'MonthVvod';
       if frmReportRaznaryadkaHOE.edtMonthVvod.ItemIndex = 0 then
       args[9] := '0'
        else
        args[9] := IntToStr(frmReportRaznaryadkaHOE.edtMonthVvod.ItemIndex);

      argNames[10] := 'YearVvod';
       if  frmReportRaznaryadkaHOE.edtYearVvod.ItemIndex = 0 then
       args[10] := '0'
       else
       args[10] := frmReportRaznaryadkaHOE.edtYearVvod.Text;

      argNames[11] := 'zayakind';
        if frmReportRaznaryadkaHOE.rborderHoeplan.Checked = True then
       args[11] := '4'; // из заявок по хое планових
        if frmReportRaznaryadkaHOE.rborderHoevneplan.Checked = True then
       args[11] := '5'; // из заявок по хое внепланових

      reportName := '';
       reportName := 'RepRaznar/materialsRaznarByPodrFromHOE';

      if frmReportRaznaryadkaHOE.rbPDF.Checked = True then
         //makeReport(reportName, argNames, args, 'pdf');
         makeReportAuth(reportName, argNames, args, 'pdf');
      if frmReportRaznaryadkaHOE.rbXLS.Checked = True then
         //makeReport(reportName, argNames, args, 'xls');
         makeReportAuth(reportName, argNames, args, 'xls');
    end;
  finally
    frmReportRaznaryadkaHOE.Free;
  end;

end;

procedure TfrmMain.miraznarNaSkladPlaniClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin
 frmReportRaznaryadka  := TfrmReportRaznaryadka.Create(Application, dsInsert);
  try
    frmReportRaznaryadka.chkObjDate.Visible := true;
    if frmReportRaznaryadka.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 12);
      SetLength(args, 12);

      argNames[0] := 'YearRaznar';
      args[0] :=  frmReportRaznaryadka.edtYearRaznar.Text;

      argNames[1] := 'MonthRaznar';
      args[1] :=  IntToStr(frmReportRaznaryadka.edtMonthRaznar.ItemIndex + 1);

      argNames[2] := 'YearVvod';
      args[2] :=  frmReportRaznaryadka.edtYearVvod.Text;

      argNames[3] := 'MonthVvod';
      args[3] :=  IntToStr(frmReportRaznaryadka.edtMonthVvod.ItemIndex + 1);

      argNames[4] := 'renCode';
      args[4] := IntToStr(frmReportRaznaryadka.renCode);

      argNames[5] := 'renName';
      if frmReportRaznaryadka.renName <> '' then
        args[5] := frmReportRaznaryadka.renName
      else
        args[5] := 'ХОЕ';

      argNames[6] := 'dNameField';
      args[6] := 'd.name';

      argNames[7] := 'budgName';
      if frmReportRaznaryadka.budgetName <> '' then
        args[7] := frmReportRaznaryadka.budgetName
      else
        args[7] := '';

      argNames[8] := 'budgCode';
      args[8] := IntToStr(frmReportRaznaryadka.budgetCode);

      argNames[9] := 'dbNameField';
      args[9] := 'db.name';

      argNames[10] := 'pKindCode';
       args[10] := '2'; // выбираем только текущие планы

      argNames[11] := 'condition';
       args[11] := frmReportRaznaryadka.estimateStatusCondition;
      reportName := '';

       if frmReportRaznaryadka.chkObjDate.Checked = true then
          reportName := 'matRaznarSkladByObj'
       else
          reportName := 'matRaznarMOLnotColnotObj';

       /////// 28.07.10
       //try
      { if EncodeDate(StrToInt(frmReportRaznaryadka.edtYearRaznar.Text), frmReportRaznaryadka.edtMonthRaznar.ItemIndex + 1, 1) >
          StrToDate('01.08.2010') then
       begin
         Application.MessageBox(PChar('На данный момент этот отчет запрещено формировать за период позднее Августа 2010 г.!'),
                                PChar('Внимание!'), MB_ICONWARNING);
         Exit;
       end;  }
       //except
       //  on EConvertError do Exit;
       //end;
       ///////

      if frmReportRaznaryadka.rbPDF.Checked = True then
         //makeReport(reportName, argNames, args, 'pdf');
         makeReportAuth(reportName, argNames, args, 'pdf');
      if frmReportRaznaryadka.rbXLS.Checked = True then
         //makeReport(reportName, argNames, args, 'xls');
         makeReportAuth(reportName, argNames, args, 'xls');
    end;
  finally
    frmReportRaznaryadka.Free;
  end;

end;

procedure TfrmMain.mizarnarNaskladZayavkaClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin
 frmReportRaznaryadkaHOE  := TfrmReportRaznaryadkaHOE.Create(Application, dsInsert);
  try
    if frmReportRaznaryadkaHOE.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 14);
      SetLength(args, 14);

      argNames[0] := 'YearRaznar';
      args[0] :=  frmReportRaznaryadkaHOE.edtYearRaznar.Text;

      argNames[1] := 'MonthRaznar';
      args[1] :=  IntToStr(frmReportRaznaryadkaHOE.edtMonthRaznar.ItemIndex + 1);

      argNames[2] := 'MonthVvod';
       if frmReportRaznaryadkaHOE.edtMonthVvod.ItemIndex = 0 then
       args[2] := '0'
        else
        args[2] := IntToStr(frmReportRaznaryadkaHOE.edtMonthVvod.ItemIndex);

      argNames[3] := 'YearVvod';
       if  frmReportRaznaryadkaHOE.edtYearVvod.ItemIndex = 0 then
       args[3] := '0'
       else
       args[3] := frmReportRaznaryadkaHOE.edtYearVvod.Text;



    {  argnames[2] := 'YearVvod';
      args[2] :=  frmReportRaznaryadkaHOE.edtYearVvod.Text;

      argnames[3] := 'MonthVvod';
      args[3] :=  IntToStr(frmReportRaznaryadkaHOE.edtMonthVvod.ItemIndex + 1); }


      argNames[4] := 'renCode';
      args[4] := IntToStr(frmReportRaznaryadkaHOE.renCode);

      argNames[5] := 'renName';
      if frmReportRaznaryadkaHOE.renName <> '' then
        args[5] := frmReportRaznaryadkaHOE.renName
      else
        args[5] := 'ХОЕ';

      argNames[6] := 'dNameField';
      args[6] := 'd.name';

      argNames[7] := 'budgName';
      if frmReportRaznaryadkaHOE.budgetName <> '' then
        args[7] := frmReportRaznaryadkaHOE.budgetName
      else
        args[7] := '';

      argNames[8] := 'budgCode';
      args[8] := IntToStr(frmReportRaznaryadkaHOE.budgetCode);

      argNames[9] := 'dbNameField';
      args[9] := 'db.name';

      argNames[10] := 'pKindCode';
       args[10] := '2'; // выбираем только текущие планы

      argNames[11] := 'zayakind';
        if frmReportRaznaryadkaHOE.rborderHoeplan.Checked = True then
       args[11] := '4'; // из заявок по хое планових
        if frmReportRaznaryadkaHOE.rborderHoevneplan.Checked = True then
       args[11] := '5'; // из заявок по хое внепланових


      reportName := '';

       reportName := 'RepRaznar/matRaznarMOLnotColnotObjHOE';

      if frmReportRaznaryadkaHOE.rbPDF.Checked = True then
         //makeReport(reportName, argNames, args, 'pdf');
         makeReportAuth(reportName, argNames, args, 'pdf');
      if frmReportRaznaryadkaHOE.rbXLS.Checked = True then
         //makeReport(reportName, argNames, args, 'xls');
         makeReportAuth(reportName, argNames, args, 'xls');
    end;
  finally
    frmReportRaznaryadkaHOE.Free;
  end;

end;

procedure TfrmMain.miraznarPoMolPlaniClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin
 frmReportRaznaryadka  := TfrmReportRaznaryadka.Create(Application, dsInsert);
  try
    if frmReportRaznaryadka.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 12);
      SetLength(args, 12);

//      argNames[0] := 'pdate1';
//      args[0] := dateToStr(frmReportRaznaryadka.edtDateStart.date);

//      argNames[1] := 'pdate2';
//      args[1] := dateToStr(frmReportRaznaryadka.edtDateFinal.date);

      argNames[0] := 'YearRaznar';
      args[0] :=  frmReportRaznaryadka.edtYearRaznar.Text;

      argNames[1] := 'MonthRaznar';
      args[1] :=  IntToStr(frmReportRaznaryadka.edtMonthRaznar.ItemIndex + 1);

      argNames[2] := 'YearVvod';
      args[2] :=  frmReportRaznaryadka.edtYearVvod.Text;

      argNames[3] := 'MonthVvod';
      args[3] :=  IntToStr(frmReportRaznaryadka.edtMonthVvod.ItemIndex + 1);

      argNames[4] := 'renCode';
      args[4] := IntToStr(frmReportRaznaryadka.renCode);

      argNames[5] := 'renName';
      if frmReportRaznaryadka.renName <> '' then
        args[5] := frmReportRaznaryadka.renName
      else
        args[5] := 'ХОЕ';

      argNames[6] := 'dNameField';
      args[6] := 'd.name';

      argNames[7] := 'budgName';
      if frmReportRaznaryadka.budgetName <> '' then
        args[7] := frmReportRaznaryadka.budgetName
      else
        args[7] := '';

      argNames[8] := 'budgCode';
      args[8] := IntToStr(frmReportRaznaryadka.budgetCode);

      argNames[9] := 'dbNameField';
      args[9] := 'db.name';

      argNames[10] := 'pKindCode';
       args[10] := '2'; // выбираем только текущие планы

      argNames[11] := 'condition';
       args[11] := frmReportRaznaryadka.estimateStatusCondition;

       //reportName:= '';

       reportName := 'materialsRaznaryadkaMOL';

       /////// 28.07.10
       //try
      { if EncodeDate(StrToInt(frmReportRaznaryadka.edtYearRaznar.Text), frmReportRaznaryadka.edtMonthRaznar.ItemIndex + 1, 1) >
          StrToDate('01.08.2010') then
       begin
         Application.MessageBox(PChar('На данный момент этот отчет запрещено формировать за период позднее Августа 2010 г.!'),
                                PChar('Внимание!'), MB_ICONWARNING);
         Exit;
       end;   }
       //except
       //  on EConvertError do Exit;
       //end;
       ///////

      if frmReportRaznaryadka.rbPDF.Checked = True then
         //makeReport(reportName, argNames, args, 'pdf');
         makeReportAuth(reportName, argNames, args, 'pdf');
      if frmReportRaznaryadka.rbXLS.Checked = True then
         //makeReport(reportName, argNames, args, 'xls');
         makeReportAuth(reportName, argNames, args, 'xls');
    end;
  finally
    frmReportRaznaryadka.Free;
  end;

end;

procedure TfrmMain.miraznarPoMolZayavkiClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin
 frmReportRaznaryadkaHOE  := TfrmReportRaznaryadkaHOE.Create(Application, dsInsert);
  try
    if frmReportRaznaryadkaHOE.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 14);
      SetLength(args, 14);

//      argNames[0] := 'pdate1';
//      args[0] := dateToStr(frmReportRaznaryadka.edtDateStart.date);

//      argNames[1] := 'pdate2';
//      args[1] := dateToStr(frmReportRaznaryadka.edtDateFinal.date);

      argNames[0] := 'YearRaznar';
      args[0] :=  frmReportRaznaryadkaHOE.edtYearRaznar.Text;

      argNames[1] := 'MonthRaznar';
      args[1] :=  IntToStr(frmReportRaznaryadkaHOE.edtMonthRaznar.ItemIndex + 1);

      argNames[2] := 'MonthVvod';
       if frmReportRaznaryadkaHOE.edtMonthVvod.ItemIndex = 0 then
       args[2] := '0'
        else
        args[2] := IntToStr(frmReportRaznaryadkaHOE.edtMonthVvod.ItemIndex);

      argNames[3] := 'YearVvod';
       if  frmReportRaznaryadkaHOE.edtYearVvod.ItemIndex = 0 then
       args[3] := '0'
       else
       args[3] := frmReportRaznaryadkaHOE.edtYearVvod.Text;

      argNames[4] := 'renCode';
      args[4] := IntToStr(frmReportRaznaryadkaHOE.renCode);

      argNames[5] := 'renName';
      if frmReportRaznaryadkaHOE.renName <> '' then
        args[5] := frmReportRaznaryadkaHOE.renName
      else
        args[5] := 'ХОЕ';

      argNames[6] := 'dNameField';
      args[6] := 'd.name';

      argNames[7] := 'budgName';
      if frmReportRaznaryadkaHOE.budgetName <> '' then
        args[7] := frmReportRaznaryadkaHOE.budgetName
      else
        args[7] := '';

      argNames[8] := 'budgCode';
      args[8] := IntToStr(frmReportRaznaryadkaHOE.budgetCode);

      argNames[9] := 'dbNameField';
      args[9] := 'db.name';

      argNames[10] := 'pKindCode';
       args[10] := '2'; // выбираем только текущие планы

      argNames[11] := 'zayakind';
        if frmReportRaznaryadkaHOE.rborderHoeplan.Checked = True then
       args[11] := '4'; // из заявок по хое планових
        if frmReportRaznaryadkaHOE.rborderHoevneplan.Checked = True then
       args[11] := '5'; // из заявок по хое внепланових


      reportName := '';

       reportName := 'RepRaznar/materialsRaznaryadkaMOLHOE';

      if frmReportRaznaryadkaHOE.rbPDF.Checked = True then
         //makeReport(reportName, argNames, args, 'pdf');
         makeReportAuth(reportName, argNames, args, 'pdf');
      if frmReportRaznaryadkaHOE.rbXLS.Checked = True then
         //makeReport(reportName, argNames, args, 'xls');
         makeReportAuth(reportName, argNames, args, 'xls');
    end;
  finally
    frmReportRaznaryadkaHOE.Free;
  end;

end;

procedure TfrmMain.miraznarkonsolPlaniClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin
 frmReportRaznaryadka  := TfrmReportRaznaryadka.Create(Application, dsInsert);
  try
    if frmReportRaznaryadka.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 12);
      SetLength(args, 12);

      argNames[0] := 'YearRaznar';
      args[0] :=  frmReportRaznaryadka.edtYearRaznar.Text;

      argNames[1] := 'MonthRaznar';
      args[1] :=  IntToStr(frmReportRaznaryadka.edtMonthRaznar.ItemIndex + 1);

      argNames[2] := 'YearVvod';
      args[2] :=  frmReportRaznaryadka.edtYearVvod.Text;

      argNames[3] := 'MonthVvod';
      args[3] :=  IntToStr(frmReportRaznaryadka.edtMonthVvod.ItemIndex + 1);

      argNames[4] := 'renCode';
      args[4] := IntToStr(frmReportRaznaryadka.renCode);

      argNames[5] := 'renName';
      if frmReportRaznaryadka.renName <> '' then
        args[5] := frmReportRaznaryadka.renName
      else
        args[5] := 'ХОЕ';

      argNames[6] := 'dNameField';
      args[6] := 'd.name';

      argNames[7] := 'budgName';
      if frmReportRaznaryadka.budgetName <> '' then
        args[7] := frmReportRaznaryadka.budgetName
      else
        args[7] := '';

      argNames[8] := 'budgCode';
      args[8] := IntToStr(frmReportRaznaryadka.budgetCode);

      argNames[9] := 'dbNameField';
      args[9] := 'db.name';

      argNames[10] := 'pKindCode';
       args[10] := '2'; // выбираем только текущие планы

      argNames[11] := 'condition';
       args[11] := frmReportRaznaryadka.estimateStatusCondition;

       //reportName:= '';


       reportName := 'materialsRaznaryadkaMOLinCol';

       /////// 28.07.10
       //try
      { if EncodeDate(StrToInt(frmReportRaznaryadka.edtYearRaznar.Text), frmReportRaznaryadka.edtMonthRaznar.ItemIndex + 1, 1) >
          StrToDate('01.08.2010') then
       begin
         Application.MessageBox(PChar('На данный момент этот отчет запрещено формировать за период позднее Августа 2010 г.!'),
                                PChar('Внимание!'), MB_ICONWARNING);
         Exit;
       end;   }
       //except
       //  on EConvertError do Exit;
       //end;
       ///////

      if frmReportRaznaryadka.rbPDF.Checked = True then
         //makeReport(reportName, argNames, args, 'pdf');
         makeReportAuth(reportName, argNames, args, 'pdf');
      if frmReportRaznaryadka.rbXLS.Checked = True then
         //makeReport(reportName, argNames, args, 'xls');
         makeReportAuth(reportName, argNames, args, 'xls');
    end;
  finally
    frmReportRaznaryadka.Free;
  end;

end;

procedure TfrmMain.miraznarkonsolZayavkiClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin
 frmReportRaznaryadkaHOE  := TfrmReportRaznaryadkaHOE.Create(Application, dsInsert);
  try
    if frmReportRaznaryadkaHOE.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 14);
      SetLength(args, 14);

      argNames[0] := 'YearRaznar';
      args[0] :=  frmReportRaznaryadkaHOE.edtYearRaznar.Text;

      argNames[1] := 'MonthRaznar';
      args[1] :=  IntToStr(frmReportRaznaryadkaHOE.edtMonthRaznar.ItemIndex + 1);

      argNames[2] := 'MonthVvod';
       if frmReportRaznaryadkaHOE.edtMonthVvod.ItemIndex = 0 then
       args[2] := '0'
        else
        args[2] := IntToStr(frmReportRaznaryadkaHOE.edtMonthVvod.ItemIndex);

      argNames[3] := 'YearVvod';
       if  frmReportRaznaryadkaHOE.edtYearVvod.ItemIndex = 0 then
       args[3] := '0'
       else
       args[3] := frmReportRaznaryadkaHOE.edtYearVvod.Text;

      argNames[4] := 'renCode';
      args[4] := IntToStr(frmReportRaznaryadkaHOE.renCode);

      argNames[5] := 'renName';
      if frmReportRaznaryadkaHOE.renName <> '' then
        args[5] := frmReportRaznaryadkaHOE.renName
      else
        args[5] := 'ХОЕ';

      argNames[6] := 'dNameField';
      args[6] := 'd.name';

      argNames[7] := 'budgName';
      if frmReportRaznaryadkaHOE.budgetName <> '' then
        args[7] := frmReportRaznaryadkaHOE.budgetName
      else
        args[7] := '';

      argNames[8] := 'budgCode';
      args[8] := IntToStr(frmReportRaznaryadkaHOE.budgetCode);

      argNames[9] := 'dbNameField';
      args[9] := 'db.name';

      argNames[10] := 'pKindCode';
       args[10] := '2'; // выбираем только текущие планы

      argNames[11] := 'zayakind';
        if frmReportRaznaryadkaHOE.rborderHoeplan.Checked = True then
       args[11] := '4'; // из заявок по хое планових
        if frmReportRaznaryadkaHOE.rborderHoevneplan.Checked = True then
       args[11] := '5'; // из заявок по хое внепланових


      reportName := '';

       reportName := 'RepRaznar/materialsRaznaryadkaMOLinColHOE';

      if frmReportRaznaryadkaHOE.rbPDF.Checked = True then
         //makeReport(reportName, argNames, args, 'pdf');
         makeReportAuth(reportName, argNames, args, 'pdf');
      if frmReportRaznaryadkaHOE.rbXLS.Checked = True then
         //makeReport(reportName, argNames, args, 'xls');
         makeReportAuth(reportName, argNames, args, 'xls');
    end;
  finally
    frmReportRaznaryadkaHOE.Free;
  end;

end;

procedure TfrmMain.N77Click(Sender: TObject);
begin
  frmEPRunReport:=TfrmEPRunReport.Create(Application, dsEdit);
  try
    frmEPRunReport.reportName:=TMenuItem(Sender).Caption;
    frmEPRunReport.ShowModal;
  finally
    frmEPRunReport.Free;
    frmEPRunReport:=nil;
  end;
end;

procedure TfrmMain.N77ComplianceNamesMaterialsComplexClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin
      SetLength(argNames, 1);
      SetLength(args, 1);

  argNames[0] := 'TEST';
      args[0] := 'test';
    makeReportAuth('RepOrder/RQMaterials2TKMaterials/RQMaterials2TKMaterials', argNames, args, 'xls');
end;

procedure TfrmMain.miAddition3PlanLoading_YearPlansClick(Sender: TObject);
begin
   FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try

    FrmReportDodatok3.report_vid := 4; // вид отчета прогнозированая  загрузка
    FrmReportDodatok3.pplankind := 1; // годовые планы
   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;
end;

procedure TfrmMain.mizayavkaDecodingClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin
 frmReportOrderDecoding := TfrmReportOrderDecoding.Create(Application, dsInsert);
{ frmReportRaznaryadka.lbYearVvod.Visible:= false;
 frmReportRaznaryadka.lbMonthVvod.Visible:= false;
 frmReportRaznaryadka.edtYearVvod.Visible:= false;
 frmReportRaznaryadka.edtMonthVvod.Visible:= false;
 }

  try
    if frmReportOrderDecoding.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 14);
      SetLength(args, 14);

      argNames[0] := 'YearRaznar';
      args[0] :=  frmReportOrderDecoding.edtYearRaznar.Text;

      argNames[1] := 'MonthRaznar';
      args[1] :=  IntToStr(frmReportOrderDecoding.edtMonthRaznar.ItemIndex + 1);

      argNames[2] := 'renCode';
      args[2] := IntToStr(frmReportOrderDecoding.renCode);

      argNames[3] := 'renName';
      if frmReportOrderDecoding.renName <> '' then
        args[3] := frmReportOrderDecoding.renName
      else
        args[3] := 'ХОЕ';

      argNames[4] := 'budgName';
      if frmReportOrderDecoding.budgetName <> '' then
        args[4] := frmReportOrderDecoding.budgetName
      else
        args[4] := '';

      argNames[5] := 'budgCode';
      args[5] := IntToStr(frmReportOrderDecoding.budgetCode);

      argNames[6] := 'MonthVvod';
       if frmReportOrderDecoding.edtMonthVvod.ItemIndex = 0 then
       args[6] := '0'
        else
        args[6] := IntToStr(frmReportOrderDecoding.edtMonthVvod.ItemIndex);

      argNames[7] := 'YearVvod';
       if  frmReportOrderDecoding.edtYearVvod.ItemIndex = 0 then
       args[7] := '0'
       else
       args[7] := frmReportOrderDecoding.edtYearVvod.Text;

      argNames[8] := 'zayakind';
        if ((frmReportOrderDecoding.rbkindHOEPlan.Checked = True) and (frmReportOrderDecoding.rbkindHOEVneplan.Checked = False)) then
       args[8] := '4'; // из хое плановых
        if ((frmReportOrderDecoding.rbkindHOEPlan.Checked = False) and (frmReportOrderDecoding.rbkindHOEVneplan.Checked = true)) then
       args[8] := '5'; // из Хое позапланових
        if ((frmReportOrderDecoding.rbkindHOEPlan.Checked = False) and (frmReportOrderDecoding.rbkindHOEVneplan.Checked = False)) then
       args[8] := '0'; // все
        if ((frmReportOrderDecoding.rbkindHOEPlan.Checked = true) and (frmReportOrderDecoding.rbkindHOEVneplan.Checked = true)) then
       args[8] := '0'; // все

      argNames[9] := 'orderform';  // вид заявки
        if frmReportOrderDecoding.cbOrderFormPlan.Checked = true then
        args[9] := '1';  // плановые
        if frmReportOrderDecoding.cbOrderFormNeplan.Checked = true then
        args[9] := '2';  // внеплановые
      if ((frmReportOrderDecoding.cbOrderFormPlan.Checked = true) and (frmReportOrderDecoding.cbOrderFormNeplan.Checked = true)) then
        args[9] := '0';  // плановые +  внеплановые

      argNames[10] := 'ordertype';
         if frmReportOrderDecoding.cbOrderTypeBudg.Checked = true then
         args[10] := '1'; // бюджетная заявка
         if frmReportOrderDecoding.cbOrderTypeInvest.Checked = true then
         args[10] := '2'; // заявка по инвест программе
         if ((frmReportOrderDecoding.cbOrderTypeBudg.Checked = true) and (frmReportOrderDecoding.cbOrderTypeInvest.Checked = true)) then
         args[10] := '0'; // заявка по инвест программе   + бюджетные заявки


        argNames[11] := 'pr_par_mat';
     if frmReportOrderDecoding.chkGr_mat.Checked = True then
        args[11] := '1'
     else
        args[11] := '0';

      reportName := '';
       reportName := 'RepOrder/rep_order_decoding';

      if frmReportOrderDecoding.rbPDF.Checked = True then
         //makeReport(reportName, argNames, args, 'pdf');
         makeReportAuth(reportName, argNames, args, 'pdf');
      if frmReportOrderDecoding.rbXLS.Checked = True then
         //makeReport(reportName, argNames, args, 'xls');
         makeReportAuth(reportName, argNames, args, 'xls');
    end;
  finally
    frmReportOrderDecoding.Free;
  end;

end;

procedure TfrmMain.miAddition3PlanLoading_YearPlansYearClick(Sender: TObject);
begin
   FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try
   FrmReportDodatok3.Label1.Visible := False;
   FrmReportDodatok3.Label2.Visible := False;
   FrmReportDodatok3.edtDateStart.Visible := False;
   FrmReportDodatok3.edtDateFinal.Visible := False;
   FrmReportDodatok3.lblYearGen.Visible := True;
   FrmReportDodatok3.edtYearGen.Visible := True;
    FrmReportDodatok3.report_vid := 6; // вид отчета прогнозированая  загрузка на год
    FrmReportDodatok3.pplankind := 1; // годовые планы
   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;
end;

procedure TfrmMain.miAddition3PlanLoading_CurrentPlansYearClick(Sender: TObject);
begin
  FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try
   FrmReportDodatok3.Label1.Visible := False;
   FrmReportDodatok3.Label2.Visible := False;
   FrmReportDodatok3.edtDateStart.Visible := False;
   FrmReportDodatok3.edtDateFinal.Visible := False;
   FrmReportDodatok3.lblYearGen.Visible := True;
   FrmReportDodatok3.edtYearGen.Visible := True;
    FrmReportDodatok3.report_vid := 6; // вид отчета нормативная загрузка
    FrmReportDodatok3.pplankind := 2; // поточні плани
   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;

end;

procedure TfrmMain.N411miAddition4PripisiClick(Sender: TObject);
begin
 frmReportDodatok4 := TFrmReportDodatok4.Create(Application, dsInsert);
 try
   frmReportDodatok4.reportType := 2;
   frmReportDodatok4.ShowModal;

 finally
   frmReportDodatok4.Free;
 end;
end;

procedure TfrmMain.miPayDocClick(Sender: TObject);
begin
 if not Assigned(frmRQPayDocShow) then
    frmRQPayDocShow := TfrmRQPayDocShow.Create(Application, fmChild);
 frmRQPayDocShow.Show;
end;

procedure TfrmMain.N78Click(Sender: TObject);
begin
 if not Assigned(frmENPreproductionObjectShow) then
    frmENPreproductionObjectShow := TfrmENPreproductionObjectShow.Create(Application, fmChild);
 frmENPreproductionObjectShow.Show;
end;

procedure TfrmMain.miAnalyseLoadWorkerClick(Sender: TObject);
begin

 FrmAnakyseLoadWorker := TFrmAnakyseLoadWorker.Create(Application, dsInsert);
 try
   FrmAnakyseLoadWorker.ShowModal;
 finally
   FrmAnakyseLoadWorker.Free;
 end;
end;

procedure TfrmMain.miRQAllocationListClick(Sender: TObject);
begin
  if not Assigned(frmRQAllocationListShow) then
    frmRQAllocationListShow := TfrmRQAllocationListShow.Create(Application, fmChild);
  frmRQAllocationListShow.Show;
end;

procedure TfrmMain.miRQBillsClick(Sender: TObject);
var
  f: RQBillFilter;
begin
   f := RQBillFilter.create();
   SetNullIntProps(f);
   SetNullXSProps(f);
   f.billType := RQBillType.Create;

   f.billType.code := TMenuItem(Sender).Tag;

   if f.billType.code = RQBILL_TYPE_TMC then
   begin
    if not Assigned(frmRQBillTmcShow) then
      frmRQBillTmcShow := TfrmRQBillShow.Create(Application, fmChild, f);
      frmRQBillTmcShow.Caption := frmRQBillTmcShow.Caption + ' ' + TMenuItem(Sender).Caption;
      frmRQBillTmcShow.UpdateCaption;
      frmRQBillTmcShow.Show;
    end
  else if f.billType.code = RQBILL_TYPE_SERVICES then
   begin
    if not Assigned(frmRQBillServicesShow) then
      frmRQBillServicesShow := TfrmRQBillShow.Create(Application, fmChild, f);
      frmRQBillServicesShow.Caption := frmRQBillServicesShow.Caption + ' ' + TMenuItem(Sender).Caption;
      frmRQBillServicesShow.UpdateCaption;
      frmRQBillServicesShow.Show;
   end;
end;

procedure TfrmMain.miRQPaymentsClick(Sender: TObject);
begin
//
//
//
//
end;

procedure TfrmMain.miRQTransportInvoicesClick(Sender: TObject);
begin
 if not Assigned(frmRQTransportInvoiceShow) then
    frmRQTransportInvoiceShow := TfrmRQTransportInvoiceShow.Create(Application, fmChild);
  frmRQTransportInvoiceShow.Show;
end;

procedure TfrmMain.miFKOrderInClick(Sender: TObject);
var
  f: RQFKOrderFilter;
begin
 f := RQFKOrderFilter.Create;
 SetNullIntProps(f);
 SetNullXSProps(f);

 f.kind := RQFKOrderKind.Create;
 f.kind.code :=  TMenuItem(Sender).Tag;

 if f.kind.code = RQFKORDER_KIND_PRIHOD_POSTAVKA  then
 begin
   if not Assigned(frmRQFKOrderInShow) then
      frmRQFKOrderInShow := TfrmRQFKOrderShow.Create(Application, fmChild, f);
    frmRQFKOrderInShow.Caption := TMenuItem(Sender).Caption;
   frmRQFKOrderInShow.UpdateCaption;
   frmRQFKOrderInShow.Show;
 end
  else if f.kind.code = RQFKORDER_KIND_RASHOD_OE2REM then
 begin
   if not Assigned(frmRQFKOrderOutShow) then
      frmRQFKOrderOutShow := TfrmRQFKOrderShow.Create(Application, fmChild, f);
    frmRQFKOrderOutShow.Caption := TMenuItem(Sender).Caption;
   frmRQFKOrderOutShow.UpdateCaption;
   frmRQFKOrderOutShow.Show;
 end
  else if f.kind.code = RQFKORDER_KIND_RASHOD_OE2OUT then
 begin
   if not Assigned(frmRQFKOrderOut2Show) then
      frmRQFKOrderOut2Show := TfrmRQFKOrderShow.Create(Application, fmChild, f);
    frmRQFKOrderOut2Show.Caption := TMenuItem(Sender).Caption;
   frmRQFKOrderOut2Show.UpdateCaption;
   frmRQFKOrderOut2Show.Show;
 end
  else if f.kind.code = RQFKORDER_KIND_RASHOD_OPERATIVE2TRANZIT then
 begin
   if not Assigned(frmRQFKOrderOutOperative2TranzitShow) then
      frmRQFKOrderOutOperative2TranzitShow := TfrmRQFKOrderShow.Create(Application, fmChild, f);
    frmRQFKOrderOutOperative2TranzitShow.Caption := TMenuItem(Sender).Caption;
   frmRQFKOrderOutOperative2TranzitShow.UpdateCaption;
   frmRQFKOrderOutOperative2TranzitShow.Show;
 end
  else if f.kind.code = RQFKORDER_KIND_RASHOD_TRANZIT2OPERATIVE then
 begin
   if not Assigned(frmRQFKOrderOutTranzit2OperativeShow) then
      frmRQFKOrderOutTranzit2OperativeShow := TfrmRQFKOrderShow.Create(Application, fmChild, f);
    frmRQFKOrderOutTranzit2OperativeShow.Caption := TMenuItem(Sender).Caption;
   frmRQFKOrderOutTranzit2OperativeShow.UpdateCaption;
   frmRQFKOrderOutTranzit2OperativeShow.Show;
 end
  else if f.kind.code = RQFKORDER_KIND_RASHOD_E2E then
 begin
   if not Assigned(frmRQFKOrderOutE2E) then
      frmRQFKOrderOutE2E := TfrmRQFKOrderShow.Create(Application, fmChild, f);
    frmRQFKOrderOutE2E.Caption := TMenuItem(Sender).Caption;
   frmRQFKOrderOutE2E.UpdateCaption;
   frmRQFKOrderOutE2E.Show;
 end
  else if f.kind.code = RQFKORDER_KIND_RASHOD_MEASUREMENT_CHANGE then
 begin
   if not Assigned(frmRQFKOrderMeasurementChange) then
      frmRQFKOrderMeasurementChange := TfrmRQFKOrderShow.Create(Application, fmChild, f);
    frmRQFKOrderMeasurementChange.Caption := TMenuItem(Sender).Caption;
   frmRQFKOrderMeasurementChange.UpdateCaption;
   frmRQFKOrderMeasurementChange.Show;
 end
  else if f.kind.code = RQFKORDER_KIND_LOADEXPL_MBP then
 begin
   if not Assigned(frmRQFKOrderLoadExplMBP) then
      frmRQFKOrderLoadExplMBP := TfrmRQFKOrderShow.Create(Application, fmChild, f);
    frmRQFKOrderLoadExplMBP.Caption := TMenuItem(Sender).Caption;
   frmRQFKOrderLoadExplMBP.UpdateCaption;
   frmRQFKOrderLoadExplMBP.Show;
 end
  else if f.kind.code = RQFKORDER_KIND_LOADEXPL_MNMA then
 begin
   if not Assigned(frmRQFKOrderLoadExplMNMA) then
      frmRQFKOrderLoadExplMNMA := TfrmRQFKOrderShow.Create(Application, fmChild, f);
    frmRQFKOrderLoadExplMNMA.Caption := TMenuItem(Sender).Caption;
   frmRQFKOrderLoadExplMNMA.UpdateCaption;
   frmRQFKOrderLoadExplMNMA.Show;
 end

  else if f.kind.code = RQFKORDER_KIND_MBP then
 begin
   if not Assigned(frmRQFKOrderMBP) then
     frmRQFKOrderMBP := TfrmRQFKOrderShow.Create(Application, fmChild, f);
   frmRQFKOrderMBP.Caption := TMenuItem(Sender).Caption;
   frmRQFKOrderMBP.UpdateCaption;
   frmRQFKOrderMBP.Show;
 end
  else if f.kind.code = RQFKORDER_KIND_MNMA then
 begin
   if not Assigned(frmRQFKOrderMNMA) then
     frmRQFKOrderMNMA := TfrmRQFKOrderShow.Create(Application, fmChild, f);
   frmRQFKOrderMNMA.Caption := TMenuItem(Sender).Caption;
   frmRQFKOrderMNMA.UpdateCaption;
   frmRQFKOrderMNMA.Show;
 end
  else if f.kind.code = RQFKORDER_KIND_RASHOD_MNMA then
 begin
   if not Assigned(frmRQFKOrderRashodMNMA) then
     frmRQFKOrderRashodMNMA := TfrmRQFKOrderShow.Create(Application, fmChild, f);
   frmRQFKOrderRashodMNMA.Caption := TMenuItem(Sender).Caption;
   frmRQFKOrderRashodMNMA.UpdateCaption;
   frmRQFKOrderRashodMNMA.Show;
 end
  else if f.kind.code = RQFKORDER_KIND_SERVICES_FROM_SIDE then
 begin
   if not Assigned(frmRQFKOrderServicesFS) then
     frmRQFKOrderServicesFS := TfrmRQFKOrderShow.Create(Application, fmChild, f);
   frmRQFKOrderServicesFS.Caption := TMenuItem(Sender).Caption;
   frmRQFKOrderServicesFS.UpdateCaption;
   frmRQFKOrderServicesFS.Show;
 end
  else if f.kind.code = RQFKORDER_KIND_PRIHOD_BUFET then
 begin
   if not Assigned(frmRQFKOrderInBufetShow) then
     frmRQFKOrderInBufetShow := TfrmRQFKOrderShow.Create(Application, fmChild, f);
   frmRQFKOrderInBufetShow.Caption := TMenuItem(Sender).Caption;
   frmRQFKOrderInBufetShow.UpdateCaption;
   frmRQFKOrderInBufetShow.Show;
 end
  else if f.kind.code = RQFKORDER_KIND_RASHOD_BUFET then
 begin
   if not Assigned(frmRQFKOrderOutBufetShow) then
     frmRQFKOrderOutBufetShow := TfrmRQFKOrderShow.Create(Application, fmChild, f);
   frmRQFKOrderOutBufetShow.Caption := TMenuItem(Sender).Caption;
   frmRQFKOrderOutBufetShow.UpdateCaption;
   frmRQFKOrderOutBufetShow.Show;
 end
   // возврат товара... тэг - 18
  else if f.kind.code = RQFKORDER_KIND_RASHOD_RETURN_PRODUCT then
 begin
   if not Assigned(frmRQFKOrderReturnShow) then
     frmRQFKOrderReturnShow := TfrmRQFKOrderShow.Create(Application, fmChild, f);
   frmRQFKOrderReturnShow.Caption := TMenuItem(Sender).Caption;
   frmRQFKOrderReturnShow.UpdateCaption;
   frmRQFKOrderReturnShow.Show;
 end
  else if f.kind.code = RQFKORDER_KIND_RASHOD_GIFT then
 begin
   if not Assigned(frmRQFKOrderRashodGiftShow) then
     frmRQFKOrderRashodGiftShow := TfrmRQFKOrderShow.Create(Application, fmChild, f);
   frmRQFKOrderRashodGiftShow.Caption := TMenuItem(Sender).Caption;
   frmRQFKOrderRashodGiftShow.UpdateCaption;
   frmRQFKOrderRashodGiftShow.Show;
 end
  else if f.kind.code = RQFKORDER_KIND_OS_EXPL then
 begin
   if not Assigned(frmRQFKOrderOSExplShow) then
     frmRQFKOrderOSExplShow := TfrmRQFKOrderShow.Create(Application, fmChild, f);
   frmRQFKOrderOSExplShow.Caption := TMenuItem(Sender).Caption;
   frmRQFKOrderOSExplShow.UpdateCaption;
   frmRQFKOrderOSExplShow.Show;
 end
  else if f.kind.code = RQFKORDER_KIND_OS_MOVEMENT then
 begin
   if not Assigned(frmRQFKOrderOSMovementShow) then
     frmRQFKOrderOSMovementShow := TfrmRQFKOrderShow.Create(Application, fmChild, f);
   frmRQFKOrderOSMovementShow.Caption := TMenuItem(Sender).Caption;
   frmRQFKOrderOSMovementShow.UpdateCaption;
   frmRQFKOrderOSMovementShow.Show;
 end
  else if f.kind.code = RQFKORDER_KIND_RASHOD_TO_STORAGE then
 begin
   if not Assigned(frmRQFKOrderRashodToStorageShow) then
     frmRQFKOrderRashodToStorageShow := TfrmRQFKOrderShow.Create(Application, fmChild, f);
   frmRQFKOrderRashodToStorageShow.Caption := TMenuItem(Sender).Caption;
   frmRQFKOrderRashodToStorageShow.UpdateCaption;
   frmRQFKOrderRashodToStorageShow.Show;
 end
  else if f.kind.code = RQFKORDER_KIND_ZONE_CHANGING then
 begin
   if not Assigned(frmRQFKOrderZoneChangingShow) then
     frmRQFKOrderZoneChangingShow := TfrmRQFKOrderShow.Create(Application, fmChild, f);
   frmRQFKOrderZoneChangingShow.Caption := TMenuItem(Sender).Caption;
   frmRQFKOrderZoneChangingShow.UpdateCaption;
   frmRQFKOrderZoneChangingShow.Show;
 end
  else if f.kind.code = RQFKORDER_KIND_OUT_FUEL then
 begin
   if not Assigned(frmRQFKOrderOutFuelShow) then
     frmRQFKOrderOutFuelShow := TfrmRQFKOrderShow.Create(Application, fmChild, f);
   frmRQFKOrderOutFuelShow.Caption := TMenuItem(Sender).Caption;
   frmRQFKOrderOutFuelShow.UpdateCaption;
   frmRQFKOrderOutFuelShow.Show;
 end
  else if f.kind.code = RQFKORDER_KIND_AVAR_IN then // NET-4400 Введення в аварійний запас
 begin
   if not Assigned(frmRQFKOrderAvarInShow) then
     frmRQFKOrderAvarInShow := TfrmRQFKOrderShow.Create(Application, fmChild, f);
   frmRQFKOrderAvarInShow.Caption := TMenuItem(Sender).Caption;
   frmRQFKOrderAvarInShow.UpdateCaption;
   frmRQFKOrderAvarInShow.Show;
 end
  else if f.kind.code = RQFKORDER_KIND_AVAR_OUT then // NET-4400 Виведення з аварійного запасу
 begin
   if not Assigned(frmRQFKOrderAvarOutShow) then
     frmRQFKOrderAvarOutShow := TfrmRQFKOrderShow.Create(Application, fmChild, f);
   frmRQFKOrderAvarOutShow.Caption := TMenuItem(Sender).Caption;
   frmRQFKOrderAvarOutShow.UpdateCaption;
   frmRQFKOrderAvarOutShow.Show;
 end
 else
   ShowMessage('Ошибка в определении типа накладной ... RQFKORDER_KIND = ' + IntToStr(f.kind.code));

 {
  if  f.kindRef.code = RQORDER_KIND_DEPARTMENT then
 begin
  if not Assigned(frmRQOrderDepartmentShow) then frmRQOrderDepartmentShow := TfrmRQOrderShow.Create(Application, fmChild, f);
  frmRQOrderDepartmentShow.Caption := TMenuItem(Sender).Caption;
  frmRQOrderDepartmentShow.Show;
 end
 }
end;

procedure TfrmMain.N39Click(Sender: TObject);
begin
 if not Assigned(frmENRecordPointPromShow) then
    frmENRecordPointPromShow := TfrmENRecordPointPromShow.Create(Application, fmChild);
 frmENRecordPointPromShow.Show;
end;

procedure TfrmMain.N412miAddition4TransportClick(Sender: TObject);
begin
 frmReportDodatok4 := TFrmReportDodatok4.Create(Application, dsInsert);
 try
   frmReportDodatok4.reportType := 4;
   frmReportDodatok4.ShowModal;
 finally
   frmReportDodatok4.Free;
 end;
end;

procedure TfrmMain.mizbytprognozmonthYearplanClick(Sender: TObject);
begin
   FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try

    FrmReportDodatok3.report_vid := 10; // вид отчета прогнозированая  загрузка по месяцу энергозбыт
    FrmReportDodatok3.pplankind := 1; // годовые планы
   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;
end;

procedure TfrmMain.N79MaterialsGroupsAnnualPlansClick(Sender: TObject);
begin
 frmReportMaterialFromPlanByGroup := TfrmReportMaterialFromPlanByGroup.Create(Application, dsInsert);
 try
   frmReportMaterialFromPlanByGroup.ShowModal;
 finally
   frmReportMaterialFromPlanByGroup.Free;
 end;
end;

procedure TfrmMain.N79ReportCurrentStateCliClick(Sender: TObject);
begin
   	frmreportCurrentStates := TfrmreportCurrentStates.Create(Application, dsInsert);
  try
    frmreportCurrentStates.ShowModal;
  finally
    frmreportCurrentStates.Free;
 end;
end;

procedure TfrmMain.mizbytprognozmonthPotochnplanClick(Sender: TObject);
begin
   FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try

    FrmReportDodatok3.report_vid := 10; // вид отчета прогнозированая  загрузка по месяцу энергозбыт
    FrmReportDodatok3.pplankind := 2; // поточные  планы
   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;
end;

procedure TfrmMain.mizbytfactzagrpersPlanClick(Sender: TObject);
begin
 FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try
    FrmReportDodatok3.report_vid := 11; // вид отчета - загрузка фактического персонала в бригаде энергосбыт
    FrmReportDodatok3.pplankind := 3; // план
   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;
end;

procedure TfrmMain.mizbytfactzagrpersFactClick(Sender: TObject);
begin
 FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try
    FrmReportDodatok3.report_vid := 11; // вид отчета - загрузка фактического персонала в бригаде енергосбыт
    FrmReportDodatok3.pplankind := 4; // факт
   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;
end;

procedure TfrmMain.mizbrozgornpersPlanClick(Sender: TObject);
begin
  FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try
    FrmReportDodatok3.report_vid := 12; // вид отчета - развернутый по персоналу  енергозбыт
    FrmReportDodatok3.pplankind := 3; // план
   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;
end;

procedure TfrmMain.mizbrozgornpersFactClick(Sender: TObject);
begin
   FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try
    FrmReportDodatok3.report_vid := 12; // вид отчета - развернутый по персоналу  енергозбыт
    FrmReportDodatok3.pplankind := 4; // план
   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;
end;

procedure TfrmMain.miRepOrdermaterialFromRQClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin

  frmENPeriodWithRENRQ := TfrmENPeriodWithRENRQ.Create(Application, dsInsert);
  frmENPeriodWithRENRQ.HideControls([frmENPeriodWithRENRQ.CheckPr_mat_group], False);

  frmENPeriodWithRENRQ.Caption := 'Матеріали для замовлення з урахуванням терм. поставки із заявок';

  try
    if frmENPeriodWithRENRQ.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 25);
      SetLength(args, 25);

      argNames[0] := 'yearGen';
      args[0] := frmENPeriodWithRENRQ.edtYearGen.Text;

      argNames[1] := 'monthGen';
      args[1] := IntToStr(frmENPeriodWithRENRQ.edtMonthGen.ItemIndex + 1);

      argNames[2] := 'renCode';
      args[2] := IntToStr(frmENPeriodWithRENRQ.renCode);

      argNames[3] := 'renName';
      if frmENPeriodWithRENRQ.renName <> '' then
        args[3] := frmENPeriodWithRENRQ.renName
      else
        args[3] := ' ХЕРСОНОБЛЕНЕРГО';

      argNames[4] := 'wholeYear';
      args[4] := IntToStr(Ord(frmENPeriodWithRENRQ.chbWholeYear.Checked));

      argNames[5] := 'dNameField';
      if frmENPeriodWithRENRQ.chbByRENs.Checked then
        args[5] := 'd.name'
      else
        args[5] := '1';

      argNames[6] := 'objNameField';
      if frmENPeriodWithRENRQ.chbByObjects.Checked then
        args[6] := '0'
      else
        args[6] := '1';

      argNames[7] := 'elementCode';
      args[7] := IntToStr(frmENPeriodWithRENRQ.elementCode);
      ///////

      argNames[8] := 'elementName';
      if frmENPeriodWithRENRQ.elementName <> '' then
        args[8] := frmENPeriodWithRENRQ.elementName
      else
        args[8] := '';

      argNames[9] := 'budgName';
      if frmENPeriodWithRENRQ.budgetName <> '' then
        args[9] := frmENPeriodWithRENRQ.budgetName
      else
        args[9] := '';

      argNames[10] := 'budgCode';
      args[10] := IntToStr(frmENPeriodWithRENRQ.budgetCode);

      argNames[11] := 'dbNameField';
      if frmENPeriodWithRENRQ.chbByBudgets.Checked then
        args[11] := 'db.name'
      else
        args[11] := '1';

      reportName := '';

      argNames[12] := 'kindrefcodemat';
      if frmENPeriodWithRENRQ.chkkindrefcodemat.Checked = True then
       args[12] := '2' // отчет формируется с паливно мастильними материалами
      else
       args[12] := '1'; // отчет формируется без паливно мастильних материалов


      argNames[14] := 'orderform';  // вид заявки
        if frmENPeriodWithRENRQ.cbOrderFormPlan.Checked = true then
        args[14] := '1';  // плановые
        if frmENPeriodWithRENRQ.cbOrderFormNeplan.Checked = true then
        args[14] := '2';  // внеплановые
      if ((frmENPeriodWithRENRQ.cbOrderFormPlan.Checked = true) and (frmENPeriodWithRENRQ.cbOrderFormNeplan.Checked = true)) then
        args[14] := '0';  // плановые +  внеплановые

      if ((frmENPeriodWithRENRQ.cbOrderFormPlan.Checked = false) and (frmENPeriodWithRENRQ.cbOrderFormNeplan.Checked = false)) then
      begin
        Application.MessageBox(PChar('Не вибраний не один із видів заявки !!!'), PChar('Увага!'), MB_ICONWARNING);
        ModalResult := mrNone;
            exit;
           end;

      argNames[15] := 'ordertype';
         if frmENPeriodWithRENRQ.cbOrderTypeBudg.Checked = true then
         args[15] := '1'; // бюджетная заявка
         if frmENPeriodWithRENRQ.cbOrderTypeInvest.Checked = true then
         args[15] := '2'; // заявка по инвест программе
         if ((frmENPeriodWithRENRQ.cbOrderTypeBudg.Checked = true) and (frmENPeriodWithRENRQ.cbOrderTypeInvest.Checked = true)) then
         args[15] := '0'; // заявка по инвест программе   + бюджетные заявки
         if ((frmENPeriodWithRENRQ.cbOrderTypeBudg.Checked = False) and (frmENPeriodWithRENRQ.cbOrderTypeInvest.Checked = False)) then
      begin
        Application.MessageBox(PChar('Не вибраний не один із типів заявки !!!'), PChar('Увага!'), MB_ICONWARNING);
        ModalResult := mrNone;
            exit;
           end;

      argNames[16] := 'pr_par_mat';
      if frmENPeriodWithRENRQ.CheckPr_mat_group.Checked = True then
          args[16] := '1'
      else
          args[16] := '0';

        argNames[17] := 'materialGroupCode';

       if frmENPeriodWithRENRQ.materialGroupCode > 0 then
        args[17] := IntToStr(frmENPeriodWithRENRQ.materialGroupCode)
       else
        args[17] := IntToStr(0);

      argNames[18] := 'pr_identid';
       if  frmENPeriodWithRENRQ.CheckPr_identid.Checked = True then
       args[18] :=  '1'
       else
       args[18] := '0';

      argNames[19] := 'priznperiod';
       if frmENPeriodWithRENRQ.chkPriznPeriod.Checked = true then
       args[19] := '1'
       else
       args[19] := '0';

      argNames[20] := 'datestart';
       args[20] := DateToStr(frmENPeriodWithRENRQ.dtpStartDate.DateTime);

      argNames[21] := 'datefinal';
       args[21] := DateToStr(frmENPeriodWithRENRQ.dtpEndDate.DateTime);

      argNames[22] := 'priznshoworder';
       if    frmENPeriodWithRENRQ.chkpriznshoworder.Checked = true then
       args[22] := '1'
       else
       args[22] := '0';

      if frmENPeriodWithRENRQ.chbWholeYear.Checked then
        reportName := 'RepOrder/OrdermaterialsFromRqWithMonths'
      else
        reportName := 'RepOrder/OrdermaterialsFromRqNoMonths';


    //  makeReportAuth(reportName, argNames, args, 'xls');
      makeReport(reportName, argNames, args, 'xls');
    end;
  finally
    frmENPeriodWithRENRQ.Free;
  end;
end;

procedure TfrmMain.mireportCategoryWorkTKPositionClick(Sender: TObject);
begin
    frmCategoryWorkTKposition := TfrmCategoryWorkTKposition.Create(Application, dsInsert);
    frmCategoryWorkTKposition.vid_report := 1;
 try
   frmCategoryWorkTKposition.ShowModal;
 finally
   frmCategoryWorkTKposition.Free;
   frmCategoryWorkTKposition := nil;
 end;
end;

procedure TfrmMain.mireportCategoryWorkTKTechcardClick(Sender: TObject);
begin
    frmCategoryWorkTKposition := TfrmCategoryWorkTKposition.Create(Application, dsInsert);
    frmCategoryWorkTKposition.vid_report := 2;
 try
   frmCategoryWorkTKposition.ShowModal;
 finally
   frmCategoryWorkTKposition.Free;
   frmCategoryWorkTKposition := nil;
 end;
end;

procedure TfrmMain.miGrRepOrdermaterialClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
  TempENDepartment: ENDepartmentControllerSoapPort;
  depCodes: string;
begin

  frmENPeriodWithREN := TfrmENPeriodWithREN.Create(Application, dsInsert);
  frmENPeriodWithREN.Caption := 'Матеріали для замовлення з урахуванням терм. поставки із планів ';
  try
    frmENPeriodWithREN.HideControls([frmENPeriodWithREN.CheckPr_mat_group], False);
     //frmENPeriodWithREN.HideControls([frmENPeriodWithREN.chkEstimateStatus], False  );

    frmENPeriodWithREN.lblWorkState.Visible := True;
    frmENPeriodWithREN.lblElementType.Visible := True;
    frmENPeriodWithREN.edtWorkState.Visible := True;
    frmENPeriodWithREN.cbElementType.Visible := True;
    frmENPeriodWithREN.spbENPlanWorkState.Visible := True;
    frmENPeriodWithREN.SpeedButton1.Visible := True;
    frmENPeriodWithREN.GroupBox3.Visible := True;

   frmENPeriodWithREN.lblENBudgetName.Visible := False;
   frmENPeriodWithREN.edtENBudgetName.Visible := False;
   frmENPeriodWithREN.spbENBudget.Visible := False;
   frmENPeriodWithREN.spbENBudgetClear.Visible := False;

   frmENPeriodWithREN.Label6.Visible := true;
   frmENPeriodWithREN.ListBudget.Visible := true;
   frmENPeriodWithREN.SpeedButton3.Visible := true;
   frmENPeriodWithREN.SpeedButton4.Visible := true;
   frmENPeriodWithREN.Label7.Visible := true;
   frmENPeriodWithREN.Label8.Visible := true;

   frmENPeriodWithREN.Label1.Visible := true;
   frmENPeriodWithREN.SpeedButton5.Visible := true;
   frmENPeriodWithREN.Label2.Visible := true;
   frmENPeriodWithREN.SpeedButton6.Visible := true;
   frmENPeriodWithREN.Label3.Visible := true;
   frmENPeriodWithREN.listDDS.Visible := true;

    if frmENPeriodWithREN.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 30);
      SetLength(args, 30);

      argNames[0] := 'yearGen';
      args[0] := frmENPeriodWithREN.edtYearGen.Text;

      argNames[1] := 'monthGen';
      args[1] := IntToStr(frmENPeriodWithREN.edtMonthGen.ItemIndex + 1);

      argNames[2] := 'renCode';
      args[2] := IntToStr(frmENPeriodWithREN.renCode);

      argNames[3] := 'renName';
      if frmENPeriodWithREN.renName <> '' then
        args[3] := frmENPeriodWithREN.renName
      else
        args[3] := 'ХЕРСОНОБЛЕНЕРГО';

      argNames[4] := 'wholeYear';
      args[4] := IntToStr(Ord(frmENPeriodWithREN.chbWholeYear.Checked));

      argNames[5] := 'dNameField';
      if frmENPeriodWithREN.chbByRENs.Checked then
        args[5] := 'd.name'
      else
        args[5] := '1';

      argNames[26] := 'dCodeField';
      if frmENPeriodWithREN.chbByRENs.Checked then
        args[26] := 'd.code'
      else
        args[26] := '1';

      argNames[6] := 'objNameField';
      if frmENPeriodWithREN.chbByObjects.Checked then
        args[6] := '0'
      else
        args[6] := '1';

      argNames[7] := 'elementCode';
      args[7] := IntToStr(frmENPeriodWithREN.elementCode);
      ///////

      argNames[8] := 'elementName';
      if frmENPeriodWithREN.elementName <> '' then
        args[8] := frmENPeriodWithREN.elementName
      else
        args[8] := '';

      argNames[9] := 'budgName';
      if frmENPeriodWithREN.budgetName <> '' then
        args[9] := frmENPeriodWithREN.budgetName
      else
        args[9] := '';

      argNames[10] := 'budgCode';
      //args[10] := IntToStr(frmENPeriodWithREN.budgetCode);
      args[10] := frmENPeriodWithREN.strBudget;

      argNames[11] := 'dbNameField';
      if frmENPeriodWithREN.chbByBudgets.Checked then
        args[11] := 'db.name'
      else
        args[11] := '1';

      argNames[27] := 'dbCodeField';
      if frmENPeriodWithREN.chbByBudgets.Checked then
        args[27] := 'db.code'
      else
        args[27] := '1';

      argNames[12] := 'pKindCode';
      if frmENPeriodWithREN.rbYear.Checked = true then
         args[12] := '1'   // годовой
      else
         args[12] := '2'; // текущий

      reportName := '';

      argNames[13] := 'kindrefcodemat';
      if frmENPeriodWithREN.chkkindrefcodemat.Checked = True then
       args[13] := '2' // отчет формируется с паливно мастильними материалами
      else
       args[13] := '1'; // отчет формируется без паливно мастильних материалов

      argNames[14] := 'stateworkcode';
      args[14] := IntToStr(frmENPeriodWithREN.WorkStateCode);

      argNames[15] := 'stateworkname';
      if frmENPeriodWithREN.WorkStateCode <> 0 then
      args[15] := frmENPeriodWithREN.WorkStateName
      else
      args[15] := ' Всі типи робіт ';

      argNames[16] := 'elementtypecode';
      if (frmENPeriodWithREN.cbElementType.ItemIndex = -1) or (frmENPeriodWithREN.cbElementType.ItemIndex = 0) then
      args[16] := IntToStr(0)
      else
        args[16] := IntToStr(Integer(frmENPeriodWithREN.cbElementTYpe.Items.Objects[frmENPeriodWithREN.cbElementType.ItemIndex]));

      argNames[17] := 'elementtypename';
      if (frmENPeriodWithREN.cbElementType.ItemIndex = -1) or (frmENPeriodWithREN.cbElementType.ItemIndex = 0) then
      args[17] := ' Всі типи об"єктів   '
      else
      args[17] := frmENPeriodWithREN.ObjectName;  // frmENPeriodWithREN.cbElementTYpe.Items.Objects[frmENPeriodWithREN.cbElementType.ItemIndex]

      ///////
      argNames[18] := 'pr_par_mat';
      if frmENPeriodWithREN.CheckPr_mat_group.Checked = True then
        args[18] := '1'
      else
        args[18] := '0';

      if frmENPeriodWithREN.GroupBox3.Visible = true then
     begin
        argNames[19] := 'planform';
        if ((frmENPeriodWithREN.chkFormWorkPozaPlan.Checked = True) and (frmENPeriodWithREN.chkFormWorkPlan.Checked = True)) then
        args[19] := '0'
        else if frmENPeriodWithREN.chkFormWorkPlan.Checked = True then // плановые работы
        args[19] := '1'
        else if frmENPeriodWithREN.chkFormWorkPozaPlan.Checked = True then   // внеплановые работы
        args[19] := '2';
     end;

     argNames[20] := 'materialGroupCode';
    if frmENPeriodWithREN.materialGroupCode > 0 then
      args[20] := IntToStr(frmENPeriodWithREN.materialGroupCode)
    else
      args[20] := IntToStr(0);

      argNames[21] := 'estimatestatus';
      if frmENPeriodWithREN.chkEstimateStatus.Checked =  True then
        args[21] := '1'
      else
        args[21] := '0';

      // распределение по типам заявок в которые должны попасть материалы из планов
      argNames[22] := 'pBudgOrInv';
      if ((frmENPeriodWithREN.cbOrderTypeBudg.Checked = True) and (frmENPeriodWithREN.cbOrderTypeInvest.Checked = True)) then
        args[22] := '0'
      else if ((frmENPeriodWithREN.cbOrderTypeBudg.Checked = False) and (frmENPeriodWithREN.cbOrderTypeInvest.Checked = False)) then
        args[22] := '0'
      else if ((frmENPeriodWithREN.cbOrderTypeBudg.Checked = True) and (frmENPeriodWithREN.cbOrderTypeInvest.Checked = False)) then
        args[22] := '1'
      else if ((frmENPeriodWithREN.cbOrderTypeBudg.Checked = False) and (frmENPeriodWithREN.cbOrderTypeInvest.Checked = True)) then
        args[22] := '2';

      argNames[23] := 'pr_identid';
      if  frmENPeriodWithREN.CheckPr_identid.Checked = True then
       args[23] :=  '1'
      else
       args[23] := '0';

      argNames[24] := 'maxorderperiod';
       args[24] := frmENPeriodWithREN.maxorderperiod;

      argNames[25] := 'strDDS';
       args[25] :=  frmENPeriodWithREN.strDDS;


       // dsit есть параметры 26 и 27
      argNames[28] := 'department';
        if frmENPeriodWithREN.renCode > 0 then
         begin
           TempENDepartment := HTTPRIOENDepartment as ENDepartmentControllerSoapPort;
           depCodes := TempENDepartment.getDepartmentCodesDown(frmENPeriodWithREN.renCode);
        args[28] := '' + depCodes + '';
         end
       else
       args[28] := ' select code from endepartment ';

       argNames[29] := 'showMatInStatusVnayavnosti';
       if frmENPeriodWithREN.chkShowMatInStatusVnayavnosti.Checked = true then
       args[29] := '1'
       else
       args[29] := '0';

      if frmENPeriodWithREN.chbWholeYear.Checked then
        if frmENPeriodWithREN.chkEstimateStatus.Checked then
            reportName := 'materialsOrderByRenWithMonths'
        else
           //reportName := 'materialsForOrderPlanAndOrder2'
           //reportName := 'Order_material_for_work2/materialsForOrderPlanAndOrder2'
           // SUPP-28684 для отчета по году в развернутом виде
           // прикручиваем данным с отчета "звіти СОТ / закінчення терміну використання )
          reportName := 'Order_material_for_work2/materialsForOrderPlanAndOrder2&sot'

      else
        reportName := 'materialsOrderByRenNoMonths';

      /////// 28.07.10
      //try
{
      if EncodeDate(StrToInt(frmENPeriodWithREN.edtYearGen.Text), frmENPeriodWithREN.edtMonthGen.ItemIndex + 1, 1) >
         StrToDate('01.08.2010') then
      begin
        Application.MessageBox(PChar('На данный момент этот отчет запрещено формировать за период позднее Августа 2010 г.!'),
                               PChar('Внимание!'), MB_ICONWARNING);
        Exit;
      end; }
      //except
      //  on EConvertError do Exit;
      //end;
      ///////

      makeReport(reportName, argNames, args, 'xls');
      //makeReportAuth(reportName, argNames, args, 'xls');
    end;
  finally
    frmENPeriodWithREN.Free;
  end;
end;

procedure TfrmMain.miGrRepOrdermaterialDrawItem(Sender: TObject; ACanvas: TCanvas; ARect: TRect; Selected: Boolean);
var
  LeftPos: Integer;
  TopPos: Integer;
  TextLength: Integer;
  Text: string;
begin
  Text := (Sender as TMenuItem).Caption;
  //ACanvas.Brush.Color := clGray;
  ACanvas.FillRect(ARect);
  ACanvas.Font.Color := $00342695; //clWhite;
  ACanvas.Font.Style := [fsBold];
  // Draw right in the middle of the menu
  TopPos := ARect.Top + (ARect.Bottom - ARect.Top - ACanvas.TextHeight('W')) div 2;
  TextLength := Length(Text) + 30;
//  if TextLength > (ARect.Right - ARect.Left) then
//    LeftPos := ARect.Left + 3
//  else
//    LeftPos := ARect.Left + (ARect.Right - ARect.Left -
//      ACanvas.TextWidth(Text)) div 2;

  LeftPos := ARect.Left {+ 30} ;

  ACanvas.TextOut(LeftPos, TopPos, Text + '');
end;

procedure TfrmMain.miGrRepOrdermaterialObjStrPlanClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin

  frmENPeriodWithREN := TfrmENPeriodWithREN.Create(Application, dsInsert);
  frmENPeriodWithREN.Caption := 'Матеріали для замовлення з урахуванням терм. поставки (розшифровка) із планів ';
  try
    frmENPeriodWithREN.HideControls([frmENPeriodWithREN.CheckPr_mat_group], False);
    frmENPeriodWithREN.HideControls([frmENPeriodWithREN.chbByObjects], True);
     //frmENPeriodWithREN.HideControls([frmENPeriodWithREN.chkEstimateStatus], False  );

    frmENPeriodWithREN.lblWorkState.Visible := True;
    frmENPeriodWithREN.lblElementType.Visible := True;
    frmENPeriodWithREN.edtWorkState.Visible := True;
    frmENPeriodWithREN.cbElementType.Visible := True;
    frmENPeriodWithREN.spbENPlanWorkState.Visible := True;
    frmENPeriodWithREN.SpeedButton1.Visible := True;
    frmENPeriodWithREN.GroupBox3.Visible := True;

    if frmENPeriodWithREN.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 24);
      SetLength(args, 24);

      argNames[0] := 'yearGen';
      args[0] := frmENPeriodWithREN.edtYearGen.Text;

      argNames[1] := 'monthGen';
      args[1] := IntToStr(frmENPeriodWithREN.edtMonthGen.ItemIndex + 1);

      argNames[2] := 'renCode';
      args[2] := IntToStr(frmENPeriodWithREN.renCode);

      argNames[3] := 'renName';
      if frmENPeriodWithREN.renName <> '' then
        args[3] := frmENPeriodWithREN.renName
      else
        args[3] := 'ХЕРСОНОБЛЕНЕРГО';

      argNames[4] := 'wholeYear';
      args[4] := IntToStr(Ord(frmENPeriodWithREN.chbWholeYear.Checked));

      argNames[5] := 'dNameField';
      if frmENPeriodWithREN.chbByRENs.Checked then
        args[5] := 'd.name'
      else
        args[5] := '1';

      argNames[6] := 'objNameField';
      if frmENPeriodWithREN.chbByObjects.Checked then
        args[6] := '0'
      else
        args[6] := '1';

      argNames[7] := 'elementCode';
      args[7] := IntToStr(frmENPeriodWithREN.elementCode);
      ///////

      argNames[8] := 'elementName';
      if frmENPeriodWithREN.elementName <> '' then
        args[8] := frmENPeriodWithREN.elementName
      else
        args[8] := '';

      argNames[9] := 'budgName';
      if frmENPeriodWithREN.budgetName <> '' then
        args[9] := frmENPeriodWithREN.budgetName
      else
        args[9] := '';

      argNames[10] := 'budgCode';
      args[10] := IntToStr(frmENPeriodWithREN.budgetCode);

      argNames[11] := 'dbNameField';
      if frmENPeriodWithREN.chbByBudgets.Checked then
        args[11] := 'db.name'
      else
        args[11] := '1';

      argNames[12] := 'pKindCode';
      if frmENPeriodWithREN.rbYear.Checked = true then
         args[12] := '1'   // годовой
      else
         args[12] := '2'; // текущий

      reportName := '';

      argNames[13] := 'kindrefcodemat';
      if frmENPeriodWithREN.chkkindrefcodemat.Checked = True then
       args[13] := '2' // отчет формируется с паливно мастильними материалами
      else
       args[13] := '1'; // отчет формируется без паливно мастильних материалов

      argNames[14] := 'stateworkcode';
      args[14] := IntToStr(frmENPeriodWithREN.WorkStateCode);

      argNames[15] := 'stateworkname';
      if frmENPeriodWithREN.WorkStateCode <> 0 then
      args[15] := frmENPeriodWithREN.WorkStateName
      else
      args[15] := ' Всі типи робіт ';

      argNames[16] := 'elementtypecode';
      if (frmENPeriodWithREN.cbElementType.ItemIndex = -1) or (frmENPeriodWithREN.cbElementType.ItemIndex = 0) then
      args[16] := IntToStr(0)
      else
        args[16] := IntToStr(Integer(frmENPeriodWithREN.cbElementTYpe.Items.Objects[frmENPeriodWithREN.cbElementType.ItemIndex]));

      argNames[17] := 'elementtypename';
      if (frmENPeriodWithREN.cbElementType.ItemIndex = -1) or (frmENPeriodWithREN.cbElementType.ItemIndex = 0) then
      args[17] := ' Всі типи об"єктів   '
      else
      args[17] := frmENPeriodWithREN.ObjectName;  // frmENPeriodWithREN.cbElementTYpe.Items.Objects[frmENPeriodWithREN.cbElementType.ItemIndex]

      ///////
      argNames[18] := 'pr_par_mat';
      if frmENPeriodWithREN.CheckPr_mat_group.Checked = True then
        args[18] := '1'
      else
        args[18] := '0';

      if frmENPeriodWithREN.GroupBox3.Visible = true then
     begin
        argNames[19] := 'planform';
        if ((frmENPeriodWithREN.chkFormWorkPozaPlan.Checked = True) and (frmENPeriodWithREN.chkFormWorkPlan.Checked = True)) then
        args[19] := '0'
        else if frmENPeriodWithREN.chkFormWorkPlan.Checked = True then // плановые работы
        args[19] := '1'
        else if frmENPeriodWithREN.chkFormWorkPozaPlan.Checked = True then   // внеплановые работы
        args[19] := '2';
     end;

      argNames[20] := 'materialGroupCode';

       if frmENPeriodWithREN.materialGroupCode > 0 then
        args[20] := IntToStr(frmENPeriodWithREN.materialGroupCode)
       else
        args[20] := IntToStr(0);

      argNames[21] := 'estimatestatus';

       if frmENPeriodWithREN.chkEstimateStatus.Checked =  True then
        args[21] := '1'
       else
        args[21] := '0';


   // распределение по типам заявок в которые должны попасть материалы из планов
      argNames[22] := 'pBudgOrInv';
      if ((frmENPeriodWithREN.cbOrderTypeBudg.Checked = True) and (frmENPeriodWithREN.cbOrderTypeInvest.Checked = True)) then
        args[22] := '0'
      else if ((frmENPeriodWithREN.cbOrderTypeBudg.Checked = False) and (frmENPeriodWithREN.cbOrderTypeInvest.Checked = False)) then
        args[22] := '0'
      else if ((frmENPeriodWithREN.cbOrderTypeBudg.Checked = True) and (frmENPeriodWithREN.cbOrderTypeInvest.Checked = False)) then
        args[22] := '1'
      else if ((frmENPeriodWithREN.cbOrderTypeBudg.Checked = False) and (frmENPeriodWithREN.cbOrderTypeInvest.Checked = True)) then
        args[22] := '2';

      argNames[23] := 'pr_identid';
       if  frmENPeriodWithREN.CheckPr_identid.Checked = True then
       args[23] :=  '1'
       else
       args[23] := '0';

      if frmENPeriodWithREN.chbWholeYear.Checked then
        reportName := 'material/matOrderWithMonthsObjStr'
      else
        reportName := 'material/matOrderNoMonthsObjStr';

      /////// 28.07.10
      //try
{
      if EncodeDate(StrToInt(frmENPeriodWithREN.edtYearGen.Text), frmENPeriodWithREN.edtMonthGen.ItemIndex + 1, 1) >
         StrToDate('01.08.2010') then
      begin
        Application.MessageBox(PChar('На данный момент этот отчет запрещено формировать за период позднее Августа 2010 г.!'),
                               PChar('Внимание!'), MB_ICONWARNING);
        Exit;
      end; }
      //except
      //  on EConvertError do Exit;
      //end;
      ///////

      makeReport(reportName, argNames, args, 'xls');
      //makeReportAuth(reportName, argNames, args, 'xls');
    end;
  finally
    frmENPeriodWithREN.Free;
  end;
end;

procedure TfrmMain.miGrRepOrdermaterialObjStrRQClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin

  frmENPeriodWithRENRQ := TfrmENPeriodWithRENRQ.Create(Application, dsInsert);
  frmENPeriodWithRENRQ.HideControls([frmENPeriodWithRENRQ.CheckPr_mat_group], False);
  frmENPeriodWithRENRQ.HideControls([frmENPeriodWithRENRQ.chbByObjects], True);
  frmENPeriodWithRENRQ.Caption := 'Матеріали для замовлення з урахуванням терм. поставки (розшифровка) із заявок ';
  try
    if frmENPeriodWithRENRQ.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 23);
      SetLength(args, 23);

      argNames[0] := 'yearGen';
      args[0] := frmENPeriodWithRENRQ.edtYearGen.Text;

      argNames[1] := 'monthGen';
      args[1] := IntToStr(frmENPeriodWithRENRQ.edtMonthGen.ItemIndex + 1);

      argNames[2] := 'renCode';
      args[2] := IntToStr(frmENPeriodWithRENRQ.renCode);

      argNames[3] := 'renName';
      if frmENPeriodWithRENRQ.renName <> '' then
        args[3] := frmENPeriodWithRENRQ.renName
      else
        args[3] := ' ХЕРСОНОБЛЕНЕРГО';

      argNames[4] := 'wholeYear';
      args[4] := IntToStr(Ord(frmENPeriodWithRENRQ.chbWholeYear.Checked));

      argNames[5] := 'dNameField';
      if frmENPeriodWithRENRQ.chbByRENs.Checked then
        args[5] := 'd.name'
      else
        args[5] := '1';

      argNames[6] := 'objNameField';
      if frmENPeriodWithRENRQ.chbByObjects.Checked then
        args[6] := '0'
      else
        args[6] := '1';

      argNames[7] := 'elementCode';
      args[7] := IntToStr(frmENPeriodWithRENRQ.elementCode);
      ///////

      argNames[8] := 'elementName';
      if frmENPeriodWithRENRQ.elementName <> '' then
        args[8] := frmENPeriodWithRENRQ.elementName
      else
        args[8] := '';

      argNames[9] := 'budgName';
      if frmENPeriodWithRENRQ.budgetName <> '' then
        args[9] := frmENPeriodWithRENRQ.budgetName
      else
        args[9] := '';

      argNames[10] := 'budgCode';
      args[10] := IntToStr(frmENPeriodWithRENRQ.budgetCode);

      argNames[11] := 'dbNameField';
      if frmENPeriodWithRENRQ.chbByBudgets.Checked then
        args[11] := 'db.name'
      else
        args[11] := '1';

      reportName := '';

      argNames[12] := 'kindrefcodemat';
      if frmENPeriodWithRENRQ.chkkindrefcodemat.Checked = True then
       args[12] := '2' // отчет формируется с паливно мастильними материалами
      else
       args[12] := '1'; // отчет формируется без паливно мастильних материалов


      argNames[14] := 'orderform';  // вид заявки
        if frmENPeriodWithRENRQ.cbOrderFormPlan.Checked = true then
        args[14] := '1';  // плановые
        if frmENPeriodWithRENRQ.cbOrderFormNeplan.Checked = true then
        args[14] := '2';  // внеплановые
      if ((frmENPeriodWithRENRQ.cbOrderFormPlan.Checked = true) and (frmENPeriodWithRENRQ.cbOrderFormNeplan.Checked = true)) then
        args[14] := '0';  // плановые +  внеплановые

      argNames[15] := 'ordertype';
         if frmENPeriodWithRENRQ.cbOrderTypeBudg.Checked = true then
         args[15] := '1'; // бюджетная заявка
         if frmENPeriodWithRENRQ.cbOrderTypeInvest.Checked = true then
         args[15] := '2'; // заявка по инвест программе
         if ((frmENPeriodWithRENRQ.cbOrderTypeBudg.Checked = true) and (frmENPeriodWithRENRQ.cbOrderTypeInvest.Checked = true)) then
         args[15] := '0'; // заявка по инвест программе   + бюджетные заявки

      argNames[16] := 'pr_par_mat';
      if frmENPeriodWithRENRQ.CheckPr_mat_group.Checked = True then
          args[16] := '1'
      else
          args[16] := '0';

        argNames[17] := 'materialGroupCode';
       if frmENPeriodWithRENRQ.materialGroupCode > 0 then
        args[17] := IntToStr(frmENPeriodWithRENRQ.materialGroupCode)
       else
        args[17] := IntToStr(0);

      argNames[18] := 'pr_identid';
       if  frmENPeriodWithRENRQ.CheckPr_identid.Checked = True then
       args[18] :=  '1'
       else
       args[18] := '0';

      argNames[19] := 'priznperiod';
       if frmENPeriodWithRENRQ.chkPriznPeriod.Checked = true then
       args[19] := '1'
       else
       args[19] := '0';

      argNames[20] := 'datestart';
       args[20] := DateToStr(frmENPeriodWithRENRQ.dtpStartDate.DateTime);

      argNames[21] := 'datefinal';
       args[21] := DateToStr(frmENPeriodWithRENRQ.dtpEndDate.DateTime);

      argNames[22] := 'priznshoworder';
       if    frmENPeriodWithRENRQ.chkpriznshoworder.Checked = true then
       args[22] := '1'
       else
       args[22] := '0';

      if frmENPeriodWithRENRQ.chbWholeYear.Checked then
        reportName := 'RepOrder/OrdermatRqWithMonthsObjStr'
      else
        reportName := 'RepOrder/OrdermatRqNoMonthsObjStr';


    //  makeReportAuth(reportName, argNames, args, 'xls');
      makeReport(reportName, argNames, args, 'xls');
    end;
  finally
    frmENPeriodWithRENRQ.Free;
  end;
end;

procedure TfrmMain.miGSMLimitReportClick(Sender: TObject);
begin
        if not Assigned(frmReportGSMLimit) then
    frmReportGSMLimit := TfrmReportGSMLimit.Create(Application, dsInsert);
  frmReportGSMLimit.Show;
end;

procedure TfrmMain.miGSMReportByPeriodClick(Sender: TObject);
begin
  if not Assigned(frmReportGSMReportByPeriod) then
    frmReportGSMReportByPeriod := TfrmReportGSMReportByPeriod.Create(Application, dsInsert);
  frmReportGSMReportByPeriod.Show;
end;

procedure TfrmMain.N84Click(Sender: TObject);
begin
    frmReportEnergoSbyt := TfrmReportEnergoSbyt.Create(Application, dsInsert);
  try
     frmReportEnergoSbyt.report := 2;
    frmReportEnergoSbyt.edtDateStart.Visible := false;
    frmReportEnergoSbyt.Label1.Visible := false;
    frmReportEnergoSbyt.rgkindPlan.Visible := True;
     frmReportEnergoSbyt.ShowModal;
  finally
    frmReportEnergoSbyt.Free;
 end;
end;

procedure TfrmMain.N83FulfillmentPlansSectionApplicationsClick(Sender: TObject);
begin
		frmReportOborotMaterials := TfrmReportOborotMaterials.Create(Application, dsInsert);
  try
    frmReportOborotMaterials.ShowModal;
  finally
    frmReportOborotMaterials.Free;
 end;
end;

procedure TfrmMain.N85AnalysisWorkObjectClick(Sender: TObject);
begin
  FrmAnalysExecutionWorks := TFrmAnalysExecutionWorks.Create(Application, dsInsert);
  try
  FrmAnalysExecutionWorks.ShowModal;
 finally
   FrmAnalysExecutionWorks.Free;
 end;
end;

procedure TfrmMain.N85Click(Sender: TObject);
begin
  OpenStandartReport(Sender);
end;

procedure TfrmMain.miAGSpecificationClick(Sender: TObject);
begin
  if not Assigned(frmAGSpecificationShow) then
    frmAGSpecificationShow := TfrmAGSpecificationShow.Create(Application, fmChild);
  frmAGSpecificationShow.Show;
end;

procedure TfrmMain.miAmountOfSignedAgreementsForConnectionClick(Sender: TObject);
var
  frmReportAmountOfSignedAgreementsForConnection: TfrmReportAmountOfSignedAgreementsForConnection;
begin
   frmReportAmountOfSignedAgreementsForConnection := TfrmReportAmountOfSignedAgreementsForConnection.Create(Application, dsInsert);
  try
    frmReportAmountOfSignedAgreementsForConnection.ShowModal;
  finally
    frmReportAmountOfSignedAgreementsForConnection.Free;
  end;
end;

procedure TfrmMain.mizbytprognozyear_yearplanClick(Sender: TObject);
begin
  FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try
   FrmReportDodatok3.Label1.Visible := False;
   FrmReportDodatok3.Label2.Visible := False;
   FrmReportDodatok3.edtDateStart.Visible := False;
   FrmReportDodatok3.edtDateFinal.Visible := False;
   FrmReportDodatok3.lblYearGen.Visible := True;
   FrmReportDodatok3.edtYearGen.Visible := True;
    FrmReportDodatok3.report_vid := 13; // вид отчета прогнозированая  загрузка Енергосбіт  на год
    FrmReportDodatok3.pplankind := 1; // годовые планы
   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;
end;

procedure TfrmMain.mizbytprognozyear_monthplanClick(Sender: TObject);
begin
   FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try
   FrmReportDodatok3.Label1.Visible := False;
   FrmReportDodatok3.Label2.Visible := False;
   FrmReportDodatok3.edtDateStart.Visible := False;
   FrmReportDodatok3.edtDateFinal.Visible := False;
   FrmReportDodatok3.lblYearGen.Visible := True;
   FrmReportDodatok3.edtYearGen.Visible := True;
    FrmReportDodatok3.report_vid := 13; // вид отчета прогнозированая  загрузка енергосбыт на год
    FrmReportDodatok3.pplankind := 2; // месячные  планы
   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;
end;

procedure TfrmMain.miEstimate2EstimateClick(Sender: TObject);
begin
{
 if not Assigned(frmENEstimateItem2ENEstimateItemShow) then
    frmENEstimateItem2ENEstimateItemShow := TfrmENEstimateItem2ENEstimateItemShow.Create(Application,fmChild);
 frmENEstimateItem2ENEstimateItemShow.Show;
}

 // try
  frmENEstimateItem2ENEstimateItemEdit := TfrmENEstimateItem2ENEstimateItemEdit.Create(Application, dsInsert);
    try
      if frmENEstimateItem2ENEstimateItemEdit.ShowModal = mrOk then
      begin
{
        if ENEstimateItem2ENEstimateItemObj<>nil then
            //TempENEstimateItem2ENEstimateItem.add(ENEstimateItem2ENEstimateItemObj);
            UpdateGrid(Sender);
}
      end;
    finally
      frmENEstimateItem2ENEstimateItemEdit.Free;
    frmENEstimateItem2ENEstimateItemEdit := nil;
    end;
//  finally
//    ENEstimateItem2ENEstimateItemObj.Free;
//  end;

end;

procedure TfrmMain.miStateMaterialClick(Sender: TObject);
begin
    frmReportStateMaterialsByObject := TfrmReportStateMaterialsByObject.Create(Application, dsInsert);
  try
    frmReportStateMAterialsByObject.ShowModal;
  finally
    frmReportStateMAterialsByObject.Free;
 end;
end;

procedure TfrmMain.disparityMatOrderBillClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
begin
    try
      /////// Parameters
      SetLength(argNames, 1);
      SetLength(args, 1);
    argNames[0] := 'qqq';
      args[0] := 'qqq';

    begin
      makeReport('disparityMatOrderBill', argNames, args, 'xls');
    end;
  finally
  end;
end;

procedure TfrmMain.N87NumberScheduledWorkSubscribers_promClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
begin
  frmENPeriod := TfrmENPeriod.Create(Application, dsInsert);
  frmENPeriod.GroupBox1.Visible := false;
  frmENPeriod.GroupBox2.Visible := false;
  frmENPeriod.edtMonthGen.Enabled := false;
  frmENPeriod.lblENBudgetName.Visible := false;
  frmENPeriod.edtENBudgetName.Visible := false;
  frmENPeriod.lblENElementName.Visible := false;
  frmENPeriod.edtENElementName.Visible := false;
  frmENPeriod.spbENBudget.Visible := false;
  frmENPeriod.spbENBudgetClear.Visible := false;
  frmENPeriod.spbENElement.Visible := false;
  frmENPeriod.spbENElementClear.Visible := false;
  frmENPeriod.chbYearPlansOnly.Visible := true;
  try
    if frmENPeriod.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 2);
      SetLength(args, 2);

      argNames[0] := 'renCode';
      args[0] := IntToStr(frmENPeriod.renCode);

      argNames[1] := 'yearGen';
      args[1] := frmENPeriod.edtYearGen.Text;
      ///////
       if frmENPeriod.chbYearPlansOnly.Checked then
       makeReport('RepEnergozbyt/year_plans_cnt_by_energozbyt', argNames, args, 'xls')
       else
      makeReport('RepEnergozbyt/year_cnt_detail_by_energozbyt', argNames, args, 'xls');
    end;
  finally
    frmENPeriod.Free;
  end;
end;

procedure TfrmMain.miTransportPlanReportClick(Sender: TObject);
begin

    frmReportTransportPlan := TfrmReportTransportPlan.Create(Application, dsInsert);
//    frmReportTransportPlan.lblTransportDepartment.Visible:= True;
  //  frmReportTransportPlan.edtENTransportDepartmentName.Visible:= True;
   // frmReportTransportPlan.spbENTransportDepartment.Visible:= True;
    //frmReportTransportPlan.spbENTransportDepartmentClear.Visible:= True;
  frmReportTransportPlan.ListTransportDepartment.Visible := True;
  try
    frmReportTransportPlan.ShowModal;
  finally
    frmReportTransportPlan.Free;
  end;
end;

procedure TfrmMain.miTransportPlanReportObjClick(Sender: TObject);
begin

    frmReportTransportPlan := TfrmReportTransportPlan.Create(Application, dsInsert);
  frmReportTransportPlan.lblEPRenName.Visible := True;
  frmReportTransportPlan.edtEPRenName.Visible := True;
  frmReportTransportPlan.spbEPRen.Visible := True;
  frmReportTransportPlan.spbEPRenClear.Visible := True;
  try
    frmReportTransportPlan.ShowModal;
  finally
    frmReportTransportPlan.Free;
  end;
end;

procedure TfrmMain.miTransportPlansClick(Sender: TObject);
begin
  if not Assigned(frmENPlanTransShow) then
    frmENPlanTransShow := TfrmENPlanTransShow.Create(Application, fmChild);
    frmENPlanTransShow.Caption := 'План робіт (транспорт)';
  frmENPlanTransShow.Show;
end;

procedure TfrmMain.miPhysVolumesClick(Sender: TObject);
begin
  frmReportPhysVolumes := TfrmReportPhysVolumes.Create(Application, dsInsert);
  try
    frmReportPhysVolumes.ShowModal;
  finally
    frmReportPhysVolumes.Free;
  end;
end;

procedure TfrmMain.miEBClick(Sender: TObject);
var
  f: ENOtherObjectFilter;
  //frm : TfrmENOtherObjectShow;
begin

 f := ENOtherObjectFilter.create();
 SetNullIntProps(f);
 SetNullXSProps(f);
 f.typeRef := ENOtherObjectTypeRef.Create();

 if TMenuItem(Sender).Tag = EN_METROLOGY_SUBSTATION then
 begin
   f.typeRef.code := ABSTRACT_TYPE_METROLOGY;
 end;

 if TMenuItem(Sender).Tag = EN_EB_OBJECT then
 begin
   f.typeRef.code := ABSTRACT_TYPE_EB;
 end;

 if TMenuItem(Sender).Tag = EN_EQUIPMENT then
 begin
   f.typeRef.code := ABSTRACT_TYPE_EQUIPMENT;
 end;

 if TMenuItem(Sender).Tag = EN_EQUIPMENT_REPAIR then
 begin
   f.typeRef.code := ABSTRACT_TYPE_EQUIPMENT_REPAIR;
 end;

 frmENOtherObjectShow := TfrmENOtherObjectShow.Create(Application, fmChild, f);
 try
   frmENOtherObjectShow.Caption := TMenuItem(Sender).Caption;
    frmENOtherObjectShow.UpdateCaption;
   frmENOtherObjectShow.Show;
 finally
   //frmENPurchasesObjectShow.Free;
   //f.Free;
 end;
end;

procedure TfrmMain.miElectricLoadRegimDayClick(Sender: TObject);
var
  vZSPSubst150TransformerGaugePowerUpd: TZSQLProcessor;
  i, vSubst150TransformerGaugePowerGenUpdRecCnt: Integer;
  strSubst150TrGaugePowerUpd, strSubst150TrGaugePowerUpdPart: string;
begin
  frmReportPowerReserve := TfrmReportPowerReserve.Create(Application, dsEdit);
  frmReportPowerReserve.cbPeriod1.Checked := True;
  frmReportPowerReserve.cbPeriod2.Checked := True;
  try
    with frmReportPowerReserve do
      begin
        cbPeriod1.Visible := False;
        gbDatesRange2.Visible := False;
        dtpDateFrom.ShowCheckbox := True;
        dtpDateTo.ShowCheckbox := True;
        dtpDateFrom.DateTime := StrToDate('15.06.2016');
        dtpDateTo.DateTime := StrToDate('31.12.2016');
        dtpDateFrom.Checked := False;
        dtpDateTo.Checked := False;
        lblEPRenName.Visible := False;
        edtEPRenName.Visible := False;
        spbEPRen.Visible := False;
        lblMessage.Visible := False;
        gbReportFileType.Visible := False;
        chkTechTerms.Visible := False;
        chkWithoutBillng.Visible := False;
        grpReserv.Visible := True;
        if ShowModal = mrOk then
          begin
            //Загрузка модуля невизуальных компонентов объектов энергетики EnergyNet
            if not Assigned(DMReportsENetObject) then
              Application.CreateForm(TDMReportsENetObject, DMReportsENetObject);

            //Загрузка модуля невизуальных компонентов внешней отчётности
            if not Assigned(DMReportsEWF) then
              Application.CreateForm(TDMReportsEWF, DMReportsEWF);

            try
              if not DMReportsENetObject.sesEN.Connected then
                DMReportsENetObject.sesEN.Connected := True;

          if (not DMReportsEWF.sesReportEWF.Connected) and (Application.Tag = ENConsts.CONFIG_ENERGYNET_CLIENT_VERSION) then
            DMReportsEWF.sesReportEWF.Connected := True;

              //Формирование скрипта обновления нагрузок Станций в режимный день
          DMReportsEWF.LoadZQueryReportFromDataBaseOrLocalFileSytstemByID(QRY_Subst150TransformerGaugePowerGenUpd, DMReportsENetObject.zqrySubst150TransformerGaugePowerGenUpd, nil, //Источник данных
                'zqrySubst150TransformerGaugePowerGenUpd.sql', //Запрос *.sql
                'queryNETOBJ', //Резервная директория хранения запроса
                '', //Файла FastReport-отчёта *.fr3
                0, //Код cn.ewfreports отчёта *.fr3 ноль во избежание загрузки
                False, //Запрос из cn.ewfreport_query_text
                0); //Потребности в TfrxDBDataSet НЕТ. Вид запроса - ВЫБОРКА

          vQRYSubst150TransformerGaugePowerGenUpd := DMReportsENetObject.zqrySubst150TransformerGaugePowerGenUpd;
              vQRYSubst150TransformerGaugePowerGenUpd.Close;
          vQRYSubst150TransformerGaugePowerGenUpd.ParamByName('substation150refcode').Clear;
              if dtpDateFrom.Checked then
            vQRYSubst150TransformerGaugePowerGenUpd.ParamByName('gauge_date_start').AsDateTime := dtpDateFrom.DateTime
          else
            vQRYSubst150TransformerGaugePowerGenUpd.ParamByName('gauge_date_start').Clear;
              if dtpDateTo.Checked then
            vQRYSubst150TransformerGaugePowerGenUpd.ParamByName('gauge_date').AsDateTime := dtpDateTo.DateTime
          else
            vQRYSubst150TransformerGaugePowerGenUpd.ParamByName('gauge_date').Clear;
              if rbReservOnlyNew.Checked then
            vQRYSubst150TransformerGaugePowerGenUpd.ParamByName('new').AsInteger := 1
              else if rbReservAll.Checked then
            vQRYSubst150TransformerGaugePowerGenUpd.ParamByName('new').AsInteger := 2
              else //if rbReservOnlyExist.Checked then
            vQRYSubst150TransformerGaugePowerGenUpd.ParamByName('new').Clear;
              vQRYSubst150TransformerGaugePowerGenUpd.Open;

          vZSPSubst150TransformerGaugePowerUpd := DMReportsENetObject.zspSubst150TransformerGaugePowerUpd;

          vSubst150TransformerGaugePowerGenUpdRecCnt := vQRYSubst150TransformerGaugePowerGenUpd.RecordCount;
              if vSubst150TransformerGaugePowerGenUpdRecCnt > 0 then
                begin
                  strSubst150TrGaugePowerUpd := '';
                  strSubst150TrGaugePowerUpdPart := '';
                  vZSPSubst150TransformerGaugePowerUpd.Script.Clear;
                  vQRYSubst150TransformerGaugePowerGenUpd.First;

            frmGauge150 := Gauge150.TfrmGauge150.Create(Application, DialogState);
                  try
                    frmGauge150.codeS150 := 0;
                    frmGauge150.nameS150 := '';
                    frmGauge150.elementCodeS150 := 0;

                    frmGauge150.tsMeasurementSheet.TabVisible := False;
                    frmGauge150.tsMeasurementData.TabVisible := False;
              frmGauge150.tsInstruction_KSRE_OS_16_20140330.TabVisible := False;
              frmGauge150.tsSTO_56947007_25_040_70_101_2011.TabVisible := False;
                    frmGauge150.tsElectricLoadRegimDay.TabVisible := True;
              frmGauge150.pcFiderGauge.ActivePage := frmGauge150.tsElectricLoadRegimDay;

              frmGauge150.sgElectricLoadRegimDay.RowCount := vSubst150TransformerGaugePowerGenUpdRecCnt + 2;

                    frmGauge150.sgElectricLoadRegimDay.ColWidths[0] := 0;

                    for i := 1 to vSubst150TransformerGaugePowerGenUpdRecCnt do
                      begin
                        if strSubst150TrGaugePowerUpd <> '' then
                  strSubst150TrGaugePowerUpd := strSubst150TrGaugePowerUpd + #13#10;
                strSubst150TrGaugePowerUpdPart := vQRYSubst150TransformerGaugePowerGenUpd.FieldByName('ad4subst150upd').AsString;
                strSubst150TrGaugePowerUpd := strSubst150TrGaugePowerUpd + strSubst150TrGaugePowerUpdPart;

                        //Скрипт изменения нагрузки станции
                frmGauge150.sgElectricLoadRegimDay.Cells[0, i] := strSubst150TrGaugePowerUpdPart;
                        //Код понижающей трансформаторной станции
                frmGauge150.sgElectricLoadRegimDay.Cells[1, i] := vQRYSubst150TransformerGaugePowerGenUpd.FieldByName('substation150refcode').AsString;
                        //Номинальное напряжение, кВ
                frmGauge150.sgElectricLoadRegimDay.Cells[2, i] := vQRYSubst150TransformerGaugePowerGenUpd.FieldByName('voltage').AsString;
                        //Район ЭнергоСнабжения
                frmGauge150.sgElectricLoadRegimDay.Cells[3, i] := vQRYSubst150TransformerGaugePowerGenUpd.FieldByName('renname').AsString;
                        //Понижающая станция 150 - 110 / 35 - 27 / 10 - 6 кВ
                frmGauge150.sgElectricLoadRegimDay.Cells[4, i] := vQRYSubst150TransformerGaugePowerGenUpd.FieldByName('substation150name').AsString;
                        //Сохранённая нагрузка (кВт) в режимный день
                frmGauge150.sgElectricLoadRegimDay.Cells[5, i] := vQRYSubst150TransformerGaugePowerGenUpd.FieldByName('gauge_s04_power_previous').AsString;
                        //Расчётная нагрузка (кВт) в режимный день
                frmGauge150.sgElectricLoadRegimDay.Cells[6, i] := vQRYSubst150TransformerGaugePowerGenUpd.FieldByName('gauge_s04_power').AsString;
                        vQRYSubst150TransformerGaugePowerGenUpd.Next;
                      end;

              vZSPSubst150TransformerGaugePowerUpd.Script.Text := strSubst150TrGaugePowerUpd;
                    frmGauge150.sgElectricLoadRegimDay.FixedCols := 1;

                    frmGauge150.ShowModal;
                  finally
                    frmGauge150.Free;
                    frmGauge150 := nil;
                  end;
                end;
            finally //Высвобождать из оперативной памяти модуль невизуальных
              //DMReportsENetObject.Free; //компонентов до завершения работы
              //DMReportsENetObject := nil; //с ними нельзя
              DMReportsEWF.Free;    //Компоненты внешней отчётности
              DMReportsEWF := nil;  //проекта EnergyWorkFlow
            end;
          end;
      end;
  finally
    frmReportPowerReserve.Free;
    frmReportPowerReserve := nil;
  end;
end;

procedure TfrmMain.mienchangepersonbyt_reportClick(Sender: TObject);
begin
   frmenchangepersonbyt := Tfrmenchangepersonbyt.Create(Application, dsInsert);
 try
   frmenchangepersonbyt.ShowModal;
 finally
   frmenchangepersonbyt.Free;
 end;
end;

procedure TfrmMain.miENCheckpointPassClick(Sender: TObject);
begin
 if not Assigned(frmENCheckpointPassListShow) then
    frmENCheckpointPassListShow := TfrmENCheckpointPassListShow.Create(Application, fmChild);
 frmENCheckpointPassListShow.Show;
end;

procedure TfrmMain.miENCottageClick(Sender: TObject);
begin
  if not Assigned(frmENCottageShow) then
    frmENCottageShow := TfrmENCottageShow.Create(Application, fmChild);
  frmENCottageShow.Show;
end;

procedure TfrmMain.N88ExecutionRepairVerificationmetrologicalServicesClick(Sender: TObject);
begin
  frmReportExecutionOfPlanMetrology := TfrmReportExecutionOfPlanMetrology.Create(Application, dsInsert);
  try
    frmReportExecutionOfPlanMetrology.ShowModal;
  finally
    frmReportExecutionOfPlanMetrology.Free;
  end;
end;

procedure TfrmMain.N8Click(Sender: TObject);
begin
 begin
  if not Assigned(frmENContractShow) then
    frmENContractShow := TfrmENContractShow.Create(Application, fmChild);
  frmENContractShow.Show;
  end;
end;


procedure TfrmMain.mniENPostTypeClick(Sender: TObject);
begin //Типы опор
  if not Assigned(frmENPostTypeShow) then
    frmENPostTypeShow := TfrmENPostTypeShow.Create(Application, fmChild);
  frmENPostTypeShow.Show;
end;


procedure TfrmMain.N610042Click(Sender: TObject);
begin
	frmReportExaminationTP := TfrmReportExaminationTP.Create(Application, dsInsert);
	frmReportExaminationTP.type_report := 1;

  try
    frmReportExaminationTP.ShowModal;
  finally
    frmReportExaminationTP.Free;
  end;
end;

procedure TfrmMain.miUseMaterialFactClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin

  frmMaterialsForOrder := TfrmMaterialsForOrder.Create(Application, dsInsert);

  frmMaterialsForOrder.Caption := 'Матеріали для виконання робіт';
  try

    frmMaterialsForOrder.lblWorkState.Visible := True;
    frmMaterialsForOrder.edtWorkState.Visible := True;
    frmMaterialsForOrder.spbENPlanWorkState.Visible := True;
    frmMaterialsForOrder.SpeedButton1.Visible := True;
    frmMaterialsForOrder.GroupBox3.Visible := True;

    if frmMaterialsForOrder.ShowModal = mrOk then
    begin
      // Parameters
      SetLength(argNames, 20);
      SetLength(args, 20);

      argNames[0] := 'yearGen';
      args[0] := frmMaterialsForOrder.edtYearGen.Text;

      argNames[1] := 'monthGen';
      args[1] := IntToStr(frmMaterialsForOrder.edtMonthGen.ItemIndex + 1);

      argNames[2] := 'renCode';
      args[2] := IntToStr(frmMaterialsForOrder.renCode);

      argNames[3] := 'renName';
      if frmMaterialsForOrder.renName <> '' then
        args[3] := frmMaterialsForOrder.renName
      else
        args[3] := 'ХЕРСОНОБЛЕНЕРГО';

      argNames[4] := 'wholeYear';
      args[4] := IntToStr(Ord(frmMaterialsForOrder.chbWholeYear.Checked));

      argNames[5] := 'dNameField';
      if frmMaterialsForOrder.chbByRENs.Checked then
        args[5] := '1' // Групировка есть по подр
      else
        args[5] := '0'; // Групировки нет  по подр

      argNames[6] := 'objNameField';
      args[6] := '0';

      argNames[7] := 'budgName';
      if frmMaterialsForOrder.budgetName <> '' then
        args[7] := frmMaterialsForOrder.budgetName
      else
        args[7] := '';

      argNames[8] := 'budgCode';
      args[8] := IntToStr(frmMaterialsForOrder.budgetCode);

      argNames[9] := 'dbNameField';
      if frmMaterialsForOrder.chbByBudgets.Checked then
        args[9] := '1' // групировка есть по бюджетодержателю
      else
        args[9] := '0'; // групировка нет по бюджетодержателю


      argNames[10] := 'kindrefcodemat';

      if frmMaterialsForOrder.chkkindrefcodemat.Checked = True then
        args[10] := '2' // отчет с паливно мастильними материалами
      else
        args[10] := '1'; // отчет вытягивает материалы без паливно мастильних материалів.

      argNames[11] := 'stateworkcode';
      args[11] := IntToStr(frmMaterialsForOrder.WorkStateCode);

      argNames[12] := 'stateworkname';
      if frmMaterialsForOrder.WorkStateCode <> 0 then
      args[12] := frmMaterialsForOrder.WorkStateName
      else
      args[12] := '  ';

      argNames[13] := 'pr_par_mat';
      if frmMaterialsForOrder.CheckPr_mat_group.Checked = True then
        args[13] := '1'
      else
        args[13] := '0';

    if frmMaterialsForOrder.GroupBox3.Visible = true then
     begin
        argNames[14] := 'planform';
        if ((frmMaterialsForOrder.chkFormWorkPozaPlan.Checked = True) and (frmMaterialsForOrder.chkFormWorkPlan.Checked = True)) then
        args[14] := '0'
        else if frmMaterialsForOrder.chkFormWorkPlan.Checked = True then // плановые работы
        args[14] := '1'
        else if frmMaterialsForOrder.chkFormWorkPozaPlan.Checked = True then   // внеплановые работы
        args[14] := '2';

     end;

      argNames[15] := 'typerefcode'; // под вид работ
       args[15]  := IntToStr(frmMaterialsForOrder.WorkTypeCode);

      argNames[16] := 'actstatusrefcode'; // статус акта в которых ищем материалы
      if ((frmMaterialsForOrder.chkNoAkt.Checked = True) and (frmMaterialsForOrder.chkActrough.Checked = False) and (frmMaterialsForOrder.chkActTrick.Checked = False)) then
        args[16] := '1';  // только зарезервированые
      if ((frmMaterialsForOrder.chkNoAkt.Checked = False) and (frmMaterialsForOrder.chkActrough.Checked = True) and (frmMaterialsForOrder.chkActTrick.Checked = False)) then
        args[16] := '2';  // только черновики актов
      if ((frmMaterialsForOrder.chkNoAkt.Checked = False) and (frmMaterialsForOrder.chkActrough.Checked = False) and (frmMaterialsForOrder.chkActTrick.Checked = True)) then
        args[16] := '3';  // только проведеные акты
      if ((frmMaterialsForOrder.chkNoAkt.Checked = True) and (frmMaterialsForOrder.chkActrough.Checked = True) and (frmMaterialsForOrder.chkActTrick.Checked = False)) then
        args[16] := '4';  // зарезервированые и черновики
      if ((frmMaterialsForOrder.chkNoAkt.Checked = False) and (frmMaterialsForOrder.chkActrough.Checked = True) and (frmMaterialsForOrder.chkActTrick.Checked = True)) then
        args[16] := '5';  // только черновики и проведенные акты
      if ((frmMaterialsForOrder.chkNoAkt.Checked = True) and (frmMaterialsForOrder.chkActrough.Checked = False) and (frmMaterialsForOrder.chkActTrick.Checked = True)) then
        args[16] := '6';  // только зарезервированые и проыведеннные
      if ((frmMaterialsForOrder.chkNoAkt.Checked = True) and (frmMaterialsForOrder.chkActrough.Checked = True) and (frmMaterialsForOrder.chkActTrick.Checked = True)) then
        args[16] := '7';  // для всех вариантов


      reportName := '';
      if frmMaterialsForOrder.chbByMonths.Checked then
        reportName := 'materialsWithMonths'
      else
        reportName := 'materialsFact';

      makeReport(reportName, argNames, args, 'xls');
    end;
  finally
    frmMaterialsForOrder.Free;
  end;
end;

procedure TfrmMain.miUsingOfMeansByObjectsForConnectionClick(Sender: TObject);
var
  frmReportUsingOfMeansByObjectsForConnection: TfrmReportUsingOfMeansByObjectsForConnection;
begin
   frmReportUsingOfMeansByObjectsForConnection := TfrmReportUsingOfMeansByObjectsForConnection.Create(Application, dsInsert);
  try
    frmReportUsingOfMeansByObjectsForConnection.ShowModal;
  finally
    frmReportUsingOfMeansByObjectsForConnection.Free;
  end;
end;

procedure TfrmMain.miMaterials2ContractsClick(Sender: TObject);
begin
  frmMaterials2Contracts := TfrmMaterials2Contracts.Create(Application, dsInsert);
  try
    frmMaterials2Contracts.ShowModal;
  finally
    frmMaterials2Contracts.Free;
  end;
end;

procedure TfrmMain.miMotionOfPartyOrderClick(Sender: TObject);
begin
   frmmatMotionOfPartyorder := TfrmmatMotionOfPartyorder.Create(Application, dsInsert);
   try
     frmmatMotionOfPartyorder.Caption := ' Виберіть ордер ';
     frmmatMotionOfPartyorder.DisableControls([frmmatMotionOfPartyorder.edtOrder]);
     if frmmatMotionOfPartyorder.ShowModal = mrOk then
     begin

     end;
   finally
     frmmatMotionOfPartyorder.Free;
   end;
end;

procedure TfrmMain.miImplementationOrderClick(Sender: TObject);
begin
    frmImplementationOrder := TfrmImplementationOrder.Create(Application, dsInsert);
  try
    frmImplementationOrder.ShowModal;
    frmImplementationOrder.DisableControls([frmImplementationOrder.edtENBudgetName, frmImplementationOrder.edtMaterialGroup, frmImplementationOrder.edtRQOrgOrgName, frmImplementationOrder.edtmaterial, frmImplementationOrder.edtOrder]);
  finally
    frmImplementationOrder.Free;
 end;
end;

procedure TfrmMain.miIncomeCountersForExpertiseClick(Sender: TObject);
begin
     if not Assigned(frmRQActCounterExpertiseIncomeShow) then
    frmRQActCounterExpertiseIncomeShow := TfrmRQActCounterExpertiseShow.Create(Application, fmChild, True);
  frmRQActCounterExpertiseIncomeShow.Show;
end;

procedure TfrmMain.miInventarizationClick(Sender: TObject);
begin
   if not Assigned(frmENFuelInventarizationShow) then
    frmENFuelInventarizationShow := TfrmENFuelInventarizationShow.Create(Application, fmChild);
  frmENFuelInventarizationShow.Show;
end;

procedure TfrmMain.miInventary_listClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin
   frmrepSummaryListMechanic := TfrmrepSummaryListMechanic.Create(Application, dsInsert);

  frmrepSummaryListMechanic.Caption := ' Звідна  відомість обліку ПММ ';
      frmrepSummaryListMechanic.chkActStatus.Checked := True;
      frmrepSummaryListMechanic.chkActStatus.Enabled := False;

      frmrepSummaryListMechanic.lblDateStart.Visible := True;
      frmrepSummaryListMechanic.lblDateFinal.Visible := True;
      frmrepSummaryListMechanic.dtpDateStart.Visible := True;
      frmrepSummaryListMechanic.dtpDateFinal.Visible := True;

      frmrepSummaryListMechanic.lblMonthGen.Visible := False;
      frmrepSummaryListMechanic.lblYearGen.Visible := False;
      frmrepSummaryListMechanic.edtMonthGen.Visible := False;
      frmrepSummaryListMechanic.edtYearGen.Visible := False;

      frmrepSummaryListMechanic.lblDateStart.Top := frmrepSummaryListMechanic.lblMonthGen.Top;
      frmrepSummaryListMechanic.dtpDateStart.Top := frmrepSummaryListMechanic.lblDateStart.Top;
      frmrepSummaryListMechanic.lblDateFinal.Top := frmrepSummaryListMechanic.lblMonthGen.Top;
      frmrepSummaryListMechanic.dtpDateFinal.Top := frmrepSummaryListMechanic.lblDateStart.Top;

      frmrepSummaryListMechanic.dtpDateStart.Checked := True;
      frmrepSummaryListMechanic.dtpDateFinal.Date := DateUtils.Today;

      frmrepSummaryListMechanic.dtpDateFinal.Checked := True;
      frmrepSummaryListMechanic.dtpDateFinal.Date := DateUtils.Today;

   try
   if frmrepSummaryListMechanic.ShowModal = mrOk then
    begin
        if frmrepSummaryListMechanic.molcode = -1 then
  begin
        Application.MessageBox(PChar('Необхідно вибрати МОЛ !'), PChar('Увага !'), MB_ICONWARNING + MB_OK);
      ModalResult := mrNone;
      exit;
  end;
  if (frmrepSummaryListMechanic.dtpDateStart.Checked = False) and (frmrepSummaryListMechanic.dtpDateFinal.Checked = False) then
  begin
        Application.MessageBox(PChar('Оберіть період !'), PChar('Увага !'), MB_ICONWARNING + MB_OK);
      ModalResult := mrNone;
      exit;
  end;

    if frmrepSummaryListMechanic.dtpDateStart.Date > frmrepSummaryListMechanic.dtpDateFinal.Date then
  begin
        Application.MessageBox(PChar('Дати повинні бути в хронологічній послідовності!'), PChar('Увага !'), MB_ICONWARNING + MB_OK);
      ModalResult := mrNone;
      exit;
  end;
      reportName := 'repSummaryListGSM/rework/SummaryListGSM'; //  без колонок суммы и цены

      SetLength(argNames, 9);
      SetLength(args, 9);

      argNames[0] := 'yearGen';
      //args[0] := frmrepSummaryListMechanic.edtYearGen.Text;
      args[0] := IntToStr(YearOf(frmrepSummaryListMechanic.dtpDateFinal.Date));

      argNames[1] := 'monthGen';
      //args[1] := IntToStr(frmrepSummaryListMechanic.edtMonthGen.ItemIndex + 1);
      args[1] := IntToStr(MonthOf(frmrepSummaryListMechanic.dtpDateFinal.Date));

      argNames[2] := 'molcode';
      args[2] := IntToStr(frmrepSummaryListMechanic.molCode); // codemol;

      argNames[3] := 'molname';
      args[3] := frmrepSummaryListMechanic.molName;

      argNames[4] := 'actstatus';
      if frmrepSummaryListMechanic.chkActStatus.Checked then
      args[4] := '1'
      else
        args[4] := '2';

      argNames[6] := 'dateStart';
      args[6] := DateToStr(frmrepSummaryListMechanic.dtpDateStart.Date);

      argNames[7] := 'dateFinal';
      args[7] := DateToStr(frmrepSummaryListMechanic.dtpDateFinal.Date);

      argNames[8] := 'reportType';
      args[8] := '1';

      argNames[5] := 'typefuel';
      if  frmrepSummaryListMechanic.chkBenza.Checked  then
       begin
        args[5] := '1';
       makeReport(reportName, argNames, args, 'xls');
       end;
      if  frmrepSummaryListMechanic.chkDiesel.Checked then
       begin
        args[5] := '2';
       makeReport(reportName, argNames, args, 'xls');
       end;

    end;

   finally

    frmrepSummaryListMechanic.Free;
  end;

end;

procedure TfrmMain.miInvestpayment_with_monthsrez2Click(Sender: TObject);
begin
 frmServicesPlanedPayAndWorks := TfrmServicesPlanedPayAndWorks.Create(Application, dsInsert);
 frmServicesPlanedPayAndWorks.Caption := 'Етапи фінансування Інвестпрограми з переносом оплат';
 frmServicesPlanedPayAndWorks.kindReport := 4;
  try
    frmServicesPlanedPayAndWorks.ShowModal;
  finally
    frmServicesPlanedPayAndWorks.Free;
  end;
end;

procedure TfrmMain.miInvestProgramClick(Sender: TObject);
begin
  if not Assigned(frmENInvestProgramShow) then
    frmENInvestProgramShow := TfrmENInvestProgramShow.Create(Application, fmChild);
  frmENInvestProgramShow.Show;
end;

procedure TfrmMain.miInvestVikonannyaClick(Sender: TObject);
begin
 frmServicesPlanedPayAndWorks := TfrmServicesPlanedPayAndWorks.Create(Application, dsInsert);
 frmServicesPlanedPayAndWorks.Caption := 'Етапи виконання Інвестпрограми';
 frmServicesPlanedPayAndWorks.kindReport := 2;
  try
    frmServicesPlanedPayAndWorks.ShowModal;
  finally
    frmServicesPlanedPayAndWorks.Free;
  end;
end;

procedure TfrmMain.miInvestVikonannyaExtendedClick(Sender: TObject);
begin
 frmServicesPlanedPayAndWorks := TfrmServicesPlanedPayAndWorks.Create(Application, dsInsert);
 frmServicesPlanedPayAndWorks.Caption := 'Виконання Інвестпрограми розгорнутий';
 frmServicesPlanedPayAndWorks.kindReport := 3;
  try
    frmServicesPlanedPayAndWorks.ShowModal;
  finally
    frmServicesPlanedPayAndWorks.Free;
  end;
end;

procedure TfrmMain.miIPClick(Sender: TObject);
begin
     if not Assigned(frmENIPShow) then
    frmENIPShow := TfrmENIPShow.Create(Application, fmChild);
  frmENIPShow.Show;
end;

procedure TfrmMain.N901DebitorReportDetailedClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
  printDetail: Integer;
begin

  frmDebtBytParam := TfrmDebtBytParam.Create(Application, dsInsert);
  frmDebtBytParam.RadioGroup1.Visible := True;
  frmDebtBytParam.GroupBox1.Visible := True;
  frmDebtBytParam.cbItog.Visible := False;
  frmDebtBytParam.RadioButton1.Visible := True;
  frmDebtBytParam.RadioButton2.Visible := True;
  frmDebtBytParam.Label1.visible := False;
  frmDebtBytParam.dtpDate.visible := False;

  try
    if frmDebtBytParam.ShowModal = mrOk then
    begin
      if frmDebtBytParam.edtEPRenName.Text = '' then
        frmDebtBytParam.renCode := -1;

      printDetail := 1;
       /////// Parameters
      SetLength(argNames, 6);
      SetLength(args, 6);

      argNames[0] := 'dateStart';
      args[0] := DateToStr(Date());

      argNames[1] := 'printDetail';
      args[1] := IntToStr(printDetail);

      argNames[2] := 'three_month';
      if frmDebtBytParam.RadioButton1.Checked then
        args[2] := '1'
      else
        args[2] := '0';

      argNames[3] := 'renCode';
      args[3] := IntToStr(frmDebtBytParam.renCode);

      argNames[4] := 'renName';
      args[4] := frmDebtBytParam.renName;

      argNames[5] := 'sumDebt';
      if frmDebtBytParam.edtSum.Text <> '' then
        args[5] := frmDebtBytParam.edtSum.Text
      else
        args[5] := '25';

      reportName := 'RepEnergozbyt/Debt/group_main_p';
      makeReport(reportName, argNames, args, 'xls');
   end;
  finally
    frmDebtBytParam.Free;
  end;

end;

procedure TfrmMain.N90ReportVerificationMeterDateClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin

  frmDebtBytParam := TfrmDebtBytParam.Create(Application, dsInsert);
  frmDebtBytParam.Caption := 'Счетчики с истекшей поверкой';
  frmDebtBytParam.RadioGroup1.Visible := False;
  frmDebtBytParam.cbItog.Visible := False;

  frmDebtBytParam.GroupBox1.Visible := True;

  frmDebtBytParam.RadioButton1.Visible := False;
  frmDebtBytParam.RadioButton2.Visible := False;
  frmDebtBytParam.Label1.visible := True;
  frmDebtBytParam.dtpDate.visible := True;

  try
    if frmDebtBytParam.ShowModal = mrOk then
    begin
      if frmDebtBytParam.edtEPRenName.Text = '' then
        frmDebtBytParam.renCode := -1;

       /////// Parameters
      SetLength(argNames, 3);
      SetLength(args, 3);

      argNames[0] := 'dateIst';
      args[0] := DateToStr(frmDebtBytParam.dtpDate.Date);
      ;

      argNames[1] := 'renCode';
      args[1] := IntToStr(frmDebtBytParam.renCode);

      argNames[2] := 'renName';
      args[2] := frmDebtBytParam.renName;

      reportName := 'RepEnergozbyt/CountersIstek/counter_istek_main';
      makeReport(reportName, argNames, args, 'xls');
   end;
  finally
    frmDebtBytParam.Free;
  end;

end;

procedure TfrmMain.miGraphRemontiClick(Sender: TObject);
var
  companyShortName: string;
begin

   frmReportGraphForAnalyse := TfrmReportGraphForAnalyse.Create(Application, dsInsert);
   companyShortName := DMReports.getSettingValueByKey(ENSettingsConsts.COMPANY_SHORTNAME);
   frmReportGraphForAnalyse.Caption := 'Аналіз виконання ремонтів по ' + companyShortName;
  frmReportGraphForAnalyse.kindGraph := 1; // график выполнение ремонтов
 try
   frmReportGraphForAnalyse.ShowModal;
 finally
   frmReportGraphForAnalyse.Free;

 end;
end;

procedure TfrmMain.miGraphStaffClick(Sender: TObject);
var
  companyShortName: string;
begin
   frmReportGraphForAnalyse := TfrmReportGraphForAnalyse.Create(Application, dsInsert);
   companyShortName := DMReports.getSettingValueByKey(ENSettingsConsts.COMPANY_SHORTNAME);
   frmReportGraphForAnalyse.Caption := 'Аналіз відпрацьованого часу по типам робіт';
  frmReportGraphForAnalyse.kindGraph := 2; // график загрузки персонала
 try
   frmReportGraphForAnalyse.ShowModal;
 finally
   frmReportGraphForAnalyse.Free;

 end;
end;

procedure TfrmMain.N94ApplicationsTermsSuppliersDeliveryClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin
   frmOrderOnMonth := TfrmOrderOnMonth.Create(Application, dsInsert);

   try

   if frmOrderOnMonth.ShowModal = mrOk then
    begin

      SetLength(argNames, 5);
      SetLength(args, 5);

      argNames[0] := 'year';
      args[0] := frmOrderOnMonth.edtYearGen.Text;

      argNames[1] := 'month';
      args[1] := IntToStr(frmOrderOnMonth.edtMonthGen.ItemIndex);

      argNames[2] := 'monthName';
      args[2] := frmOrderOnMonth.edtMonthGen.Items.Strings[frmOrderOnMonth.edtMonthGen.ItemIndex];

     reportName := 'OrderOnMonth';

     makeReport(reportName, argNames, args, 'xls');

    end;

   finally
    frmOrderOnMonth.Free;
  end;
end;

procedure TfrmMain.miTravelSheetClick(Sender: TObject);
begin
  if not Assigned(frmENTravelSheetShow) then
    frmENTravelSheetShow := TfrmENTravelSheetShow.Create(Application, fmChild);
  frmENTravelSheetShow.Show;
end;

procedure TfrmMain.miTravelsheetDriversClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin
 frmReportTransportLoad := TfrmReportTransportLoad.Create(Application, dsInsert);
 frmReportTransportLoad.reportLoadType := 3;
 frmReportTransportLoad.chkFullYear.Visible := false;
  try
     if frmReportTransportLoad.ShowModal = mrOk then
    begin

      SetLength(argNames, 4);
      SetLength(args, 4);

      argNames[0] := 'fin_department';
      args[0] := frmReportTransportLoad.fin_departmentCode;

      argNames[1] := 'transportdepartmentname';
      args[1] := frmReportTransportLoad.fin_departmentName;

      argNames[2] := 'monthGen';
      args[2] :=  IntToStr(frmReportTransportLoad.edtMonthGen.ItemIndex + 1);

      argNames[3] := 'yearGen';
      args[3] := frmReportTransportLoad.edtYearGen.Text;

      if DMReports.UsesMDAXDataForReport = false then
       reportName := 'transport/drivers_entravelsheet/drivers_percent_load'
      else
       reportName := 'transport/drivers_entravelsheet/ax_drivers_percent_load';

      makeReport(reportName, argNames, args, 'xls');
     end;

  finally
    frmReportTransportLoad.Free;
  end;
end;

procedure TfrmMain.miTUTermsClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin
    SetLength(argNames, 1);
    SetLength(args, 1);

    argNames[0] := 'emptyParam';
    args[0] :=  '';
    reportName := 'Services/4Rent/Terms/TU_Terms';

    makeReport(reportName, argNames, args, 'xls');
end;

procedure TfrmMain.N96ListMaterialsManufacturingApplicationsClick(Sender: TObject);
begin
   FrmListMaterialsOnRequests := TFrmListMaterialsOnRequests.Create(Application, dsInsert);
 try
   FrmListMaterialsOnRequests.ShowModal;
 finally
   FrmListMaterialsOnRequests.Free;
 end;
end;

procedure TfrmMain.N99AnalysisWorkPerformanceClick(Sender: TObject);
begin
    frmReportEnergoSbyt := TfrmReportEnergoSbyt.Create(Application, dsInsert);
  try
     frmReportEnergoSbyt.report := 2;
    frmReportEnergoSbyt.edtDateStart.Visible := true;
    frmReportEnergoSbyt.Label1.Visible := true;
     frmReportEnergoSbyt.ShowModal;
  finally
    frmReportEnergoSbyt.Free;
 end;
end;

procedure TfrmMain.N9Click(Sender: TObject);
begin
   if not Assigned(frmPlanPurchaseGroup) then
    frmPlanPurchaseGroup := TfrmPlanPurchaseGroup.Create(Application, fmChild);
    frmPlanPurchaseGroup.Show;
end;

procedure TfrmMain.NActBytPeriodClick(Sender: TObject);
begin
  frmCCRunReport := TfrmCCRunReport.Create(Application, dsEdit);
  try
    frmCCRunReport.reportName := TMenuItem(Sender).Caption;
    frmCCRunReport.ShowModal;
  finally
    frmCCRunReport.Free;
    frmCCRunReport := nil;
  end;
end;

procedure TfrmMain.NAwardGraphClick(Sender: TObject);
begin
  frmCCRunReport := TfrmCCRunReport.Create(Application, dsEdit);
  try
    frmCCRunReport.reportName := TMenuItem(Sender).Caption;
    frmCCRunReport.ShowModal;
  finally
    frmCCRunReport.Free;
    frmCCRunReport := nil;
  end;
end;

procedure TfrmMain.NEmptyMeasurementClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin
      /////// Parameters
      SetLength(argNames, 1);
      SetLength(args, 1);

      argNames[0] := 'P0';
      args[0] := '';

      reportName := 'RepEnergozbyt/Security/group_main';
      makeReport(reportName, argNames, args, 'xls');

end;

procedure TfrmMain.mniENHookTypeClick(Sender: TObject);
begin //Типы крюков
  if not Assigned(frmENHookTypeShow) then
    frmENHookTypeShow := TfrmENHookTypeShow.Create(Application, fmChild);
  frmENHookTypeShow.Show;
end;





procedure TfrmMain.miFuelCardClick(Sender: TObject);
begin
   if not Assigned(frmENFuelCardShow) then
    frmENFuelCardShow := TfrmENFuelCardShow.Create(Application, fmChild);
    frmENFuelCardShow.Show;
end;

procedure TfrmMain.miFuelDistributionClick(Sender: TObject);
var
  fuelDistribution: TfrmReportFuelDistribution;
begin
  fuelDistribution := TfrmReportFuelDistribution.Create(Application, dsInsert);
  try
    fuelDistribution.ShowModal;
  finally
    fuelDistribution.Free;
  end;
end;

procedure TfrmMain.miFuelReportClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin
   frmchargesFuelTransport := TfrmchargesFuelTransport.Create(Application, dsInsert);

  frmchargesFuelTransport.DisableControls([frmchargesFuelTransport.edtTKTransportRealTransportRealName, frmchargesFuelTransport.edtEPRenName]);

   try

   if frmchargesFuelTransport.ShowModal = mrOk then
    begin

      SetLength(argNames, 7);
      SetLength(args, 7);

      argNames[0] := 'pdatestart';
      args[0] := DateToStr(frmchargesFuelTransport.edtDateStart.date);

      argNames[1] := 'pdatefinal';
      args[1] := DateToStr(frmchargesFuelTransport.edtDateFinal.date);

      argNames[2] := 'pnumberlist';
      args[2] := trim(frmchargesFuelTransport.edttravelsheet.Text);

      argNames[3] := 'pcoderealtransport';
      args[3] := IntToStr(frmchargesFuelTransport.transportRealcode);

      argNames[4] := 'prencode';
      if  frmchargesFuelTransport.renCode = 1 then // если выбрали ХерсонОблЭнерго то выбираем по всем пдразделениям
      args[4] := '0'
      else
      args[4] := IntToStr(frmchargesFuelTransport.renCode);

      argNames[5] := 'prenname';
      args[5] := trim(frmchargesFuelTransport.renName);

       argNames[6] := 'is_act';
      if ((frmchargesFuelTransport.chbWithoutakt.Checked = True) and (frmchargesFuelTransport.chbWithakt.Checked = True)) then
          args[6] := '0';
      if ((frmchargesFuelTransport.chbWithoutakt.Checked = false) and (frmchargesFuelTransport.chbWithakt.Checked = false)) then
          args[6] := '0';
      if ((frmchargesFuelTransport.chbWithoutakt.Checked = True) and (frmchargesFuelTransport.chbWithakt.Checked = false)) then
          args[6] := '1';
      if ((frmchargesFuelTransport.chbWithoutakt.Checked = false) and (frmchargesFuelTransport.chbWithakt.Checked = True)) then
          args[6] := '2';

      if frmchargesFuelTransport.chkConvolute.Checked = False then
      reportName := 'chargesFuelTransport'
      else
      reportName := 'chargesFuelTransportConvolute';
     makeReport(reportName, argNames, args, 'xls');

    end;

   finally

    frmchargesFuelTransport.Free;
  end;

end;

procedure TfrmMain.miFuelSpendingByCarClick(Sender: TObject);
var
  spendingForm: TfrmReportFuelSpendingByCar;
begin
       spendingForm := TfrmReportFuelSpendingByCar.Create(Application, dsInsert);
       try
         spendingForm.ShowModal;
       finally
        spendingForm.Free;
       end;
end;

procedure TfrmMain.mivedUsetransportClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin
   frmvedUsedtransport := TfrmvedUsedtransport.Create(Application, dsInsert);

  frmvedUsedtransport.DisableControls([frmvedUsedtransport.edtTKTransportRealTransportRealName, frmvedUsedtransport.edtEPRenName]);
  frmvedUsedtransport.lblpodrname.Visible := true;
  frmvedUsedtransport.edtpodrname.Visible := true;
  frmvedUsedtransport.spbpodrname.Visible := true;
  frmvedUsedtransport.spbpodrnameclear.Visible := true;
         // скрыть выбор транспортного предприятия

  frmvedUsedtransport.DisableControls([frmvedUsedtransport.lblEPRenName, frmvedUsedtransport.edtEPRenName, frmvedUsedtransport.spbEPRen, frmvedUsedtransport.spbEPRenClear]);

         frmvedUsedtransport.DisableControls([frmvedUsedtransport.edtpodrname]);
   try

   if frmvedUsedtransport.ShowModal = mrOk then
    begin

      SetLength(argNames, 10);
      SetLength(args, 10);

      argNames[0] := 'datestart';
      args[0] := DateToStr(frmvedUsedtransport.edtDateStart.date);

      argNames[1] := 'datefinish';
      args[1] := DateToStr(frmvedUsedtransport.edtDateFinal.date);

    { argNames[2] := 'pnumberlist';
      args[2] := trim(frmvedUsedtransport.edttravelsheet.Text);   }

      argNames[2] := 'transportrealcode';
      args[2] := IntToStr(frmvedUsedtransport.transportRealcode);

      argNames[3] := 'departmentCode';
      if  frmvedUsedtransport.renCode = 1 then // если выбрали ХерсонОблЭнерго то выбираем по всем пдразделениям
      args[3] := '0'
      else
      args[3] := IntToStr(frmvedUsedtransport.renCode);

      argNames[4] := 'prenname';
      args[4] := trim(frmvedUsedtransport.renName);

      argNames[5] := 'plankind';
      if frmvedUsedtransport.cbPlanWorkKind.ItemIndex + 1 > 0 then
        args[5] := intToStr(frmvedUsedtransport.cbPlanWorkKind.ItemIndex + 1)
      else
      args[5] := '0';

      argNames[6] := 'strdepartment';
      if ((frmvedUsedtransport.renCode = 1) or (frmvedUsedtransport.planpodrCode = 1)) then // если выбрали ХерсонОблЭнерго то выбираем по всем пдразделениям
      args[6] := ' and 0 = 0 '
      else
if frmvedUsedtransport.renCode <> 0 then
      begin
             if frmvedUsedtransport.chkConvolute.Checked = true then
          args[6] := ' and epd.code in ( ' + DMReports.getDepartmentCodesDown(frmvedUsedtransport.renCode) + ' ) '
             else
          args[6] := ' and tr.departmentrefcode in ( ' + DMReports.getDepartmentCodesDown(frmvedUsedtransport.renCode) + ' ) ';
        end
      else if frmvedUsedtransport.planpodrCode <> 0 then
      begin
            if frmvedUsedtransport.chkConvolute.Checked = true then
          args[6] := ' and epd.code in ( ' + DMReports.getDepartmentCodesDown(frmvedUsedtransport.planpodrCode) + ' ) '
            else
          args[6] := ' and pw.departmentrefcode in ( ' + DMReports.getDepartmentCodesDown(frmvedUsedtransport.planpodrCode) + ' ) ';
        end
       else
        args[6] := ' and 1 = 1';



   {    argNames[6] := 'is_act';
      if ((frmvedUsedtransport.chbWithoutakt.Checked = True) and (frmvedUsedtransport.chbWithakt.Checked = True)) then
          args[6] := '0';
      if ((frmvedUsedtransport.chbWithoutakt.Checked = false) and (frmvedUsedtransport.chbWithakt.Checked = false)) then
          args[6] := '0';
      if ((frmvedUsedtransport.chbWithoutakt.Checked = True) and (frmvedUsedtransport.chbWithakt.Checked = false)) then
          args[6] := '1';
      if ((frmvedUsedtransport.chbWithoutakt.Checked = false) and (frmvedUsedtransport.chbWithakt.Checked = True)) then
          args[6] := '2'; }

      argNames[7] := 'molcode';
      if frmvedUsedtransport.molCode <> -1 then
      args[7] := IntToStr(frmvedUsedtransport.molCode)
      else
      args[7] := '';

       if  frmvedUsedtransport.chkConvolute.Checked = true then
           reportName := 'Auto/rp_auto_naryad_pytev_large'
       else
           reportName := 'tr';
      makeReport(reportName, argNames, args, 'xls');

    end;

   finally

    frmvedUsedtransport.Free;
  end;

end;

procedure TfrmMain.miObjectWorkerClick(Sender: TObject);
begin
  if not Assigned(frmENSizObjectShow) then
      frmENSizObjectShow := TfrmENSizObjectShow.Create(Application, fmChild);
  frmENSizObjectShow.Show;
end;

procedure TfrmMain.miMaterialsfromActClick(Sender: TObject);
begin
    frmMaterialsFromAct := TfrmMaterialsFromAct.Create(Application, dsInsert);
  try
    frmMaterialsFromAct.ShowModal;
  finally
    frmMaterialsFromAct.Free;
 end;
end;

procedure TfrmMain.minewzvitClick(Sender: TObject);
begin
    frmReportOborotMatBuh := TfrmReportOborotMatBuh.Create(Application, dsInsert);
  try
    frmReportOborotMatBuh.ShowModal;
  finally
    frmReportOborotMatBuh.Free;
 end;
end;

procedure TfrmMain.miBeanShellClick(Sender: TObject);
begin
    frmBeanShell := TfrmBeanShell.Create(Application, fmChild);
  frmBeanShell.Show;
end;

procedure TfrmMain.miBindingCounters2ServicesObjectClick(Sender: TObject);
begin
   FrmBindingCounters2ServicesObject := TFrmBindingCounters2ServicesObject.Create(Application, dsInsert);
 try
   FrmBindingCounters2ServicesObject.ShowModal;
 finally
   FrmBindingCounters2ServicesObject.Free;
 end;
end;

procedure TfrmMain.miBrigadeClick(Sender: TObject);
begin
  if not Assigned(frmENSzBrigadeShow) then
      frmENSzBrigadeShow := TfrmENSzBrigadeShow.Create(Application, fmChild);
  frmENSzBrigadeShow.Show;
end;

procedure TfrmMain.miBudgetClick(Sender: TObject);
begin
  if not Assigned(frmRQBudgetShow) then
    frmRQBudgetShow := TfrmRQBudgetShow.Create(Application, fmChild);
    frmRQBudgetShow.Show;
end;

procedure TfrmMain.miBuildingClick(Sender: TObject);
begin
  if not Assigned(frmENBuildingShow) then
    frmENBuildingShow := TfrmENBuildingShow.Create(Application, fmChild);
    frmENBuildingShow.Show;
end;

procedure TfrmMain.GVV1Click(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin
   frmrepSummaryListMechanic := TfrmrepSummaryListMechanic.Create(Application, dsInsert);

  frmrepSummaryListMechanic.Caption := ' Звідна  відомість обліку ПММ ';

     frmrepSummaryListMechanic.lblDateStart.Visible := True;
      frmrepSummaryListMechanic.lblDateFinal.Visible := True;
      frmrepSummaryListMechanic.dtpDateStart.Visible := True;
      frmrepSummaryListMechanic.dtpDateFinal.Visible := True;

      frmrepSummaryListMechanic.lblMonthGen.Visible := False;
      frmrepSummaryListMechanic.lblYearGen.Visible := False;
      frmrepSummaryListMechanic.edtMonthGen.Visible := False;
      frmrepSummaryListMechanic.edtYearGen.Visible := False;

      frmrepSummaryListMechanic.lblDateStart.Top := frmrepSummaryListMechanic.lblMonthGen.Top;
      frmrepSummaryListMechanic.dtpDateStart.Top := frmrepSummaryListMechanic.lblDateStart.Top;
      frmrepSummaryListMechanic.lblDateFinal.Top := frmrepSummaryListMechanic.lblMonthGen.Top;
      frmrepSummaryListMechanic.dtpDateFinal.Top := frmrepSummaryListMechanic.lblDateStart.Top;

      frmrepSummaryListMechanic.dtpDateStart.Checked := True;
      frmrepSummaryListMechanic.dtpDateFinal.Date := DateUtils.Today;

      frmrepSummaryListMechanic.dtpDateFinal.Checked := True;
      frmrepSummaryListMechanic.dtpDateFinal.Date := DateUtils.Today;

   try
   if frmrepSummaryListMechanic.ShowModal = mrOk then
    begin
        if frmrepSummaryListMechanic.molcode = -1 then
  begin
        Application.MessageBox(PChar('Необхідно вибрати МОЛ !'), PChar('Увага !'), MB_ICONWARNING + MB_OK);
      ModalResult := mrNone;
      exit;
  end;
  if (frmrepSummaryListMechanic.dtpDateStart.Checked = False) and (frmrepSummaryListMechanic.dtpDateFinal.Checked = False) then
  begin
        Application.MessageBox(PChar('Оберіть період !'), PChar('Увага !'), MB_ICONWARNING + MB_OK);
      ModalResult := mrNone;
      exit;
  end;

    if frmrepSummaryListMechanic.dtpDateStart.Date > frmrepSummaryListMechanic.dtpDateFinal.Date then
  begin
        Application.MessageBox(PChar('Дати повинні бути в хронологічній послідовності!'), PChar('Увага !'), MB_ICONWARNING + MB_OK);
      ModalResult := mrNone;
      exit;
  end;
      reportName := 'repSummaryListGSM/rework/SummaryListGSM'; //  без колонок суммы и цены

      SetLength(argNames, 9);
      SetLength(args, 9);

      argNames[0] := 'yearGen';
      //args[0] := frmrepSummaryListMechanic.edtYearGen.Text;
      args[0] := IntToStr(YearOf(frmrepSummaryListMechanic.dtpDateFinal.Date));

      argNames[1] := 'monthGen';
      //args[1] := IntToStr(frmrepSummaryListMechanic.edtMonthGen.ItemIndex + 1);
      args[1] := IntToStr(MonthOf(frmrepSummaryListMechanic.dtpDateFinal.Date));

      argNames[2] := 'molcode';
      args[2] := IntToStr(frmrepSummaryListMechanic.molCode); // codemol;

      argNames[3] := 'molname';
      args[3] := frmrepSummaryListMechanic.molName;

      argNames[4] := 'actstatus';
      if frmrepSummaryListMechanic.chkActStatus.Checked then
      args[4] := '1'
      else
        args[4] := '2';

      argNames[6] := 'dateStart';
      args[6] := DateToStr(frmrepSummaryListMechanic.dtpDateStart.Date);

      argNames[7] := 'dateFinal';
      args[7] := DateToStr(frmrepSummaryListMechanic.dtpDateFinal.Date);

      argNames[8] := 'reportType';
      args[8] := '0';

      argNames[5] := 'typefuel';
      if  frmrepSummaryListMechanic.chkBenza.Checked  then
       begin
        args[5] := '1';
       makeReport(reportName, argNames, args, 'xls');
       end;
      if  frmrepSummaryListMechanic.chkDiesel.Checked then
       begin
        args[5] := '2';
       makeReport(reportName, argNames, args, 'xls');
       end;

    end;

   finally

    frmrepSummaryListMechanic.Free;
  end;

end;

procedure TfrmMain.GVV2Click(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin
   frmrepSummaryListMechanic := TfrmrepSummaryListMechanic.Create(Application, dsInsert);
  frmrepSummaryListMechanic.Caption := 'Розшифровка звідної відомості обліку ПММ ';
  frmrepSummaryListMechanic.Label1.Caption := 'Дата тр.листів З';
  frmrepSummaryListMechanic.Label2.Caption := 'Дата тр.листів ПО';
       //frmrepSummaryListMechanic.chkActStatus.Visible := false;

  frmrepSummaryListMechanic.Label1.Visible := False;
  frmrepSummaryListMechanic.Label2.Visible := False;
       frmrepSummaryListMechanic.edtDateStart.Visible := False;
       frmrepSummaryListMechanic.edtDateFinal.Visible := False;

   try

   if frmrepSummaryListMechanic.ShowModal = mrOk then
    begin

        if frmrepSummaryListMechanic.molCode = -1 then
  begin
        Application.MessageBox(PChar('Необхідно вибрати МОЛ !'), PChar('Увага !'), MB_ICONWARNING + MB_OK);
      ModalResult := mrNone;
      exit;
  end;
      reportName := 'repSummaryListGSM/CheckbySheet/CheckbyList';

      SetLength(argNames, 7);
      SetLength(args, 7);

      argNames[0] := 'yearGen';
      args[0] := frmrepSummaryListMechanic.edtYearGen.Text;

      argNames[1] := 'monthGen';
      args[1] := IntToStr(frmrepSummaryListMechanic.edtMonthGen.ItemIndex + 1);

      argNames[2] := 'molcode';
      args[2] := IntToStr(frmrepSummaryListMechanic.molCode); // codemol;

      argNames[3] := 'molname';
      args[3] := frmrepSummaryListMechanic.molName;

      argNames[4] := 'actstatus';
      if frmrepSummaryListMechanic.chkActStatus.Checked then
       args[4] := '1'
      else
        args[4] := '2';

      argNames[5] := 'typefuel';
      if  frmrepSummaryListMechanic.chkBenza.Checked  then
       begin
        args[5] := '1';
       makeReport(reportName, argNames, args, 'xls');
       end;

      if  frmrepSummaryListMechanic.chkDiesel.Checked then
       begin
        args[5] := '2';
       makeReport(reportName, argNames, args, 'xls');
       end;

    end;

   finally

    frmrepSummaryListMechanic.Free;
  end;

end;

procedure TfrmMain.miTransportRealClick(Sender: TObject);
begin
  if not Assigned(frmTKTransportRealShow) then
      frmTKTransportRealShow := TfrmTKTransportRealShow.Create(Application, fmChild);
  frmTKTransportRealShow.Show;
end;

procedure TfrmMain.miAutoTiresClick(Sender: TObject);
begin
  if not Assigned(frmENAutoTiresShow) then
      frmENAutoTiresShow := TfrmENAutoTiresShow.Create(Application, fmChild);
  frmENAutoTiresShow.Show;
end;

procedure TfrmMain.miAccumulatorsClick(Sender: TObject);
begin
  if not Assigned(frmENAccumulatorsShow) then
      frmENAccumulatorsShow := TfrmENAccumulatorsShow.Create(Application, fmChild);
  frmENAccumulatorsShow.Show;
end;

procedure TfrmMain.miUnfinishedTasksClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
begin
  frmreportAppealRegistrationByDate := TfrmreportAppealRegistrationByDate.Create(Application, dsInsert);
  frmreportAppealRegistrationByDate.Caption := 'Виконання послуг за Заявами споживачів';
  frmreportAppealRegistrationByDate.unfinishedTasks := True;
  try
    if frmreportAppealRegistrationByDate.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 6);
      SetLength(args, 6);

      argNames[0] := 'dateStart';
      args[0] := DateToStr(frmreportAppealRegistrationByDate.edtDateStart.Date);

      argNames[1] := 'dateFinal';
      args[1] := DateToStr(frmreportAppealRegistrationByDate.edtDateFinal.Date);

      argNames[2] := 'departmentCode';
      args[2] := IntToStr(frmreportAppealRegistrationByDate.departmentCode);

      argNames[3] := 'servicesCode';
      args[3] := IntToStr(frmreportAppealRegistrationByDate.edtServices.ItemIndex);

      argNames[4] := 'departmentName';
      args[4] := frmreportAppealRegistrationByDate.edtDFDepartmentREN.Text;

      argNames[5] := 'servicesName';
      args[5] := frmreportAppealRegistrationByDate.edtServices.Text;
      ///////

      DMReports.makeReport4DocFlow('ServicesConsumer/unfinishedTasks', argNames, args, 'xls')
    end;
  finally
    frmreportAppealRegistrationByDate.Free;
  end;
end;

procedure TfrmMain.miUndeliveredMaterials2CompanyClick(Sender: TObject);
begin
      frmUndeliveredMaterials2Company := TfrmUndeliveredMaterials2Company.Create(Application, dsInsert);
    try
      frmUndeliveredMaterials2Company.ShowModal;
    finally
      frmUndeliveredMaterials2Company.Free;
   end;

end;

procedure TfrmMain.miUsageInputClick(Sender: TObject);
var
  ozFilter: SCUsageInputFilter;
begin
  ozFilter := SCUsageInputFilter.Create;
  SetNullIntProps(ozFilter);
  SetNullXSProps(ozFilter);
  ozFilter.autoCreated := SC_USAGE_INPUT_AUTO_CREATED_NO;

  if not Assigned(frmSCUsageInputShow) then
    frmSCUsageInputShow := TfrmSCUsageInputShow.Create(Application, fmChild, False, ozFilter);
    frmSCUsageInputShow.Caption := TMenuItem(Sender).Caption;
  frmSCUsageInputShow.Show;
end;

procedure TfrmMain.miSCCounterMoveClick(Sender: TObject);
begin
  frmENEstimateItem2ENEstimateItemEditSCCounter := TfrmENEstimateItem2ENEstimateItemEditSCCounter.Create(Application, dsInsert);
    try
      if frmENEstimateItem2ENEstimateItemEditSCCounter.ShowModal = mrOk then
      begin
{
        if ENEstimateItem2ENEstimateItemObj<>nil then
            //TempENEstimateItem2ENEstimateItem.add(ENEstimateItem2ENEstimateItemObj);
            UpdateGrid(Sender);
}
      end;
    finally
      frmENEstimateItem2ENEstimateItemEditSCCounter.Free;
    frmENEstimateItem2ENEstimateItemEditSCCounter := nil;
    end;
end;

procedure TfrmMain.miExecutedWorksFromActClick(Sender: TObject);
begin
  {   frmExecutedWorksFromAct:= TfrmExecutedWorksFromAct.Create(Application, dsInsert);
  try
     frmExecutedWorksFromAct.ShowModal;
  finally
     frmExecutedWorksFromAct.Free;
  end; }
end;

procedure TfrmMain.miPlanWithoutMOLClick(Sender: TObject);
begin
   frmReportPlanWithoutMOL := TfrmReportPlanWithoutMOL.Create(Application, dsInsert);
  try
    frmReportPlanWithoutMOL.ShowModal;
  finally
    frmReportPlanWithoutMOL.Free;
 end;
end;

procedure TfrmMain.miPlanWorkBatchClick(Sender: TObject);
begin
  frmENPlanWorkBatchEdit := TfrmENPlanWorkBatchEdit.Create(Application, dsInsert);
  try
    frmENPlanWorkBatchEdit.ShowModal;
  finally
    frmENPlanWorkBatchEdit.Free;
  end;
end;

procedure TfrmMain.miRepOrderByDatePayClick(Sender: TObject);
begin
    frmOrdersByDatePay := TfrmOrdersByDatePay.Create(Application, dsInsert);
  try
    frmOrdersByDatePay.ShowModal;
  finally
    frmOrdersByDatePay.Free;
 end;
end;

procedure TfrmMain.mirepusetransp2Click(Sender: TObject);
begin
    frmrepAutoNaryadPytev := TfrmrepAutoNaryadPytev.Create(Application, dsInsert);
  try
    frmrepAutoNaryadPytev.ShowModal;
  finally
    frmrepAutoNaryadPytev.Free;
 end;
end;

procedure TfrmMain.miSCCounterReportClick(Sender: TObject);
begin
 frmSumListMovedCounters := TfrmSumListMovedCounters.Create(Application, dsInsert);
 try
   frmSumListMovedCounters.ShowModal;
 finally
   frmSumListMovedCounters.Free;
  end;
end;

procedure TfrmMain.miSCRemainsClick(Sender: TObject);
begin
   frmReportSCRest := TfrmReportSCRest.Create(Application, dsInsert);
  try
    frmReportSCRest.ShowModal;
  finally
    frmReportSCRest.Free;
  end;
end;

procedure TfrmMain.miSCRemainsDrawItem(Sender: TObject; ACanvas: TCanvas; ARect: TRect; Selected: Boolean);
var
  LeftPos: Integer;
  TopPos: Integer;
  TextLength: Integer;
  Text: string;
begin
  Text := (Sender as TMenuItem).Caption;
  //ACanvas.Brush.Color := clGray;
  ACanvas.FillRect(ARect);
  ACanvas.Font.Color := $00342695; //clWhite;
  ACanvas.Font.Style := [fsBold];
  // Draw right in the middle of the menu
  TopPos := ARect.Top + (ARect.Bottom - ARect.Top - ACanvas.TextHeight('W')) div 2;
  TextLength := Length(Text);
//  if TextLength > (ARect.Right - ARect.Left) then
//    LeftPos := ARect.Left + 3
//  else
//    LeftPos := ARect.Left + (ARect.Right - ARect.Left -
//      ACanvas.TextWidth(Text)) div 2;

  LeftPos := ARect.Left + 30;
  ACanvas.TextOut(LeftPos, TopPos, Text);
end;

procedure TfrmMain.miCheckplaseInstalllCountersClick(Sender: TObject);
begin
    frmScSearchPlaceSetting := TfrmScSearchPlaceSetting.Create(Application, dsInsert);
  try
    frmScSearchPlaceSetting.ShowModal;
  finally
    frmScSearchPlaceSetting.Free;
 end;
end;

procedure TfrmMain.miCheckpointsClick(Sender: TObject);
begin
  if not Assigned(frmENCheckpointShow) then
      frmENCheckpointShow := TfrmENCheckpointShow.Create(Application, fmChild);
  frmENCheckpointShow.Show;
end;

procedure TfrmMain.miPersonatTabelClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin
 frmpersonaltabel := Tfrmpersonaltabel.Create(Application, dsInsert);

  try
     if frmpersonaltabel.ShowModal = mrOk then
    begin

      SetLength(argNames, 4);
      SetLength(args, 4);

      argNames[0] := 'yearGen';
      args[0] := frmpersonaltabel.edtYearGen.Text;

      argNames[1] := 'monthGen';
      args[1] := IntToStr(frmpersonaltabel.edtMonthGen.ItemIndex + 1);

      argNames[2] := 'rencode';
      args[2] := intToStr(frmpersonaltabel.rencode);

      argNames[3] := 'renname';
      args[3] := frmpersonaltabel.renname;

      if frmpersonaltabel.chkShowAct.Checked = True then
       if DMReports.UsesMDAXDataForReport = false then
        reportName := 'Personal/TabelOnActWithoutAct/TabelWithoutAct'
       else
        reportName := 'Personal/TabelOnActWithoutAct/ax_TabelWithoutAct'

      else if DMReports.UsesMDAXDataForReport = false then
        reportName := 'Personal/TabelOnAct/Tabel'
       else
        reportName := 'Personal/TabelOnAct/ax_Tabel';
      makeReport(reportName, argNames, args, 'xls');

     end;

  finally
    frmpersonaltabel.Free;
  end;

end;

procedure TfrmMain.miWorkOrderBytClick(Sender: TObject);
var
  f: ENWorkOrderBytFilter;
begin
  {
  if not Assigned(frmENWorkOrderBytShow) then
    frmENWorkOrderBytShow := TfrmENWorkOrderBytShow.Create(Application, fmChild);
  frmENWorkOrderBytShow.Show;
  }

  f := ENWorkOrderBytFilter.Create;
  SetNullIntProps(f);
  SetNullXSProps(f);

  f.typeRef := ENWorkOrderBytTypeRef.Create;
  f.typeRef.code := TMenuItem(Sender).Tag;

  if f.typeRef.code = ENWORKORDERBYTTYPE_BY_ENERGYNET then
  begin
    if not Assigned(frmENWorkOrderBytShow) then
      frmENWorkOrderBytShow := TfrmENWorkOrderBytShow.Create(Application, fmChild, f);
    frmENWorkOrderBytShow.Caption := TMenuItem(Sender).Caption;
    frmENWorkOrderBytShow.UpdateCaption;
    frmENWorkOrderBytShow.Show;
  end
  else if f.typeRef.code = ENWORKORDERBYTTYPE_RAID_BY_BILLING then
  begin
    if not Assigned(frmENWorkOrderBytRaidShow) then
      frmENWorkOrderBytRaidShow := TfrmENWorkOrderBytShow.Create(Application, fmChild, f);
    frmENWorkOrderBytRaidShow.Caption := TMenuItem(Sender).Caption;
    frmENWorkOrderBytRaidShow.UpdateCaption;
    frmENWorkOrderBytRaidShow.Show;
  end
  else if f.typeRef.code = ENWORKORDERBYTTYPE_CONTROL then
  begin
    if not Assigned(frmENWorkOrderBytControlShow) then
      frmENWorkOrderBytControlShow := TfrmENWorkOrderBytShow.Create(Application, fmChild, f);
    frmENWorkOrderBytControlShow.Caption := TMenuItem(Sender).Caption;
    frmENWorkOrderBytControlShow.UpdateCaption;
    frmENWorkOrderBytControlShow.Show;
  end;
end;

procedure TfrmMain.miWorksExecutedClick(Sender: TObject);
begin
  frmExecutedWorksFromAct := TfrmExecutedWorksFromAct.Create(Application, dsInsert);
  try
     frmExecutedWorksFromAct.ShowModal;
  finally
     frmExecutedWorksFromAct.Free;
  end;
end;

procedure TfrmMain.miworkMonthFactClick(Sender: TObject);
begin
  frmExecutedWorksFromMonthPlan := TfrmExecutedWorksFromMonthPlan.Create(Application, dsInsert);
  try
     frmExecutedWorksFromMonthPlan.ShowModal;
  finally
     frmExecutedWorksFromMonthPlan.Free;
  end;
end;

procedure TfrmMain.miworkMonthFactMetrologyClick(Sender: TObject);
begin
  frmExecutedWorksFromMonthPlanZbyt := TfrmExecutedWorksFromMonthPlanZbyt.Create(Application, dsInsert);
    frmExecutedWorksFromMonthPlanZbyt.report_vid := 2; // метрологія
  frmExecutedWorksFromMonthPlanZbyt.DisableControls([frmExecutedWorksFromMonthPlanZbyt.spbEPRen, frmExecutedWorksFromMonthPlanZbyt.spbEPRenClear]);

  try
     frmExecutedWorksFromMonthPlanZbyt.ShowModal;
  finally
     frmExecutedWorksFromMonthPlanZbyt.Free;
  end;
end;

procedure TfrmMain.miworkMonthFactZbytClick(Sender: TObject);
begin
    frmExecutedWorksFromMonthPlanZbyt := TfrmExecutedWorksFromMonthPlanZbyt.Create(Application, dsInsert);
    frmExecutedWorksFromMonthPlanZbyt.report_vid := 1; // енергосбыт.
    if TMenuItem(Sender).Tag =111 then
    frmExecutedWorksFromMonthPlanZbyt.ruta_rukoee:= 'rukoee';
    if TMenuItem(Sender).Tag =222 then
    frmExecutedWorksFromMonthPlanZbyt.ruta_rukoee:= 'ruta';
  try
     frmExecutedWorksFromMonthPlanZbyt.ShowModal;
  finally
     frmExecutedWorksFromMonthPlanZbyt.Free;
  end;
end;

procedure TfrmMain.mifrmCountWorkOrderByRezervedMaterialsClick(Sender: TObject);
begin
   FrmCountWorkOrderByRezervedMaterials := TFrmCountWorkOrderByRezervedMaterials.Create(Application, dsInsert);
 try
   FrmCountWorkOrderByRezervedMaterials.ShowModal;
 finally
   FrmCountWorkOrderByRezervedMaterials.Free;
 end;
end;

procedure TfrmMain.miRestockingClick(Sender: TObject);
var
  f: ENPurchasesNoObjectFilter;
begin

 f := ENPurchasesNoObjectFilter.Create();
 SetNullIntProps(f);
 SetNullXSProps(f);
 f.typeRef := ENPurchasesNoObjectTypeRef.Create;

 if TMenuItem(Sender).Tag = EN_WRITING_NO_OBJECT_RESTOCKING then
 begin
   f.typeRef.code := NO_OBJECT_RESTOCKING;
 end;

 frmENPurchasesNoObjectShow := TfrmENPurchasesNoObjectShow.Create(Application, fmChild, f);

 try
   frmENPurchasesNoObjectShow.Caption := TMenuItem(Sender).Caption;
    frmENPurchasesNoObjectShow.UpdateCaption;
   frmENPurchasesNoObjectShow.Show;
 finally
   //frmENPurchasesObjectShow.Free;
   //f.Free;
 end;

end;

procedure TfrmMain.miRestsByOrderClick(Sender: TObject);
begin
  frmReportRestsByOrder := TfrmReportRestsByOrder.Create(Application, dsInsert);
    try
     frmReportRestsByOrder.ShowModal;
    finally
     frmReportRestsByOrder.Free;
    end;
end;

procedure TfrmMain.miRoutesClick(Sender: TObject);
begin
    if not Assigned(frmENDestinationPoint2PointShow) then
      frmENDestinationPoint2PointShow := TfrmENDestinationPoint2PointShow.Create(Application, fmChild);
  frmENDestinationPoint2PointShow.Show;
end;

procedure TfrmMain.miRepSzClick(Sender: TObject);
begin
   frmSZ := TfrmSZ.Create(Application, dsInsert);
  try
    frmSZ.ShowModal;
  finally
    frmSZ.Free;
  end;
end;

procedure TfrmMain.miCalculationClick(Sender: TObject);
begin
  if not Assigned(frmENServicesCalculationShow) then
    frmENServicesCalculationShow := TfrmENServicesCalculationShow.Create(Application, fmChild);
  frmENServicesCalculationShow.Show;
end;

procedure TfrmMain.miCalendarPlanProjectClick(Sender: TObject);
begin
  	frmReportTechConditionsPlan := TfrmReportTechConditionsPlan.Create(Application, dsInsert);
  try
    frmReportTechConditionsPlan.reportvid := 2; // проектирование календарный график
    frmReportTechConditionsPlan.ShowModal;
  finally
    frmReportTechConditionsPlan.Free;
 end;
end;

procedure TfrmMain.miCalendarPlanProjectPKDClick(Sender: TObject);
begin
  	frmReportTechConditionsPlan := TfrmReportTechConditionsPlan.Create(Application, dsInsert);
  try
    frmReportTechConditionsPlan.reportvid := 3; //ПКД, яку розробляють сектори проектування
    frmReportTechConditionsPlan.ShowModal;
  finally
    frmReportTechConditionsPlan.Free;
 end;
end;

procedure TfrmMain.miShiftLinesClick(Sender: TObject);
var
  f: ENServicesObjectFilter;
begin
  f := ENServicesObjectFilter.Create;
  SetNullIntProps(f);
  SetNullXSProps(f);

  f.contractTypeRef := ENServicesContractTypeRef.Create;
  f.contractTypeRef.code := TMenuItem(Sender).Tag;

  if (f.contractTypeRef.code = ENSERVICESOBJECTTYPE_SHIFT_LINES) then
  begin
    if not Assigned(frmENServicesShiftShow) then
      frmENServicesShiftShow := TfrmENServicesShiftShow.Create(Application, fmChild, f);
    frmENServicesShiftShow.Caption := TMenuItem(Sender).Caption;
    frmENServicesShiftShow.UpdateCaption;
    frmENServicesShiftShow.Show;
  end
  else
    ShowMessage('Ошибочка в определении типа договора...');
end;

procedure TfrmMain.miShutdownConsumersClick(Sender: TObject);
begin
    frmShutdownConsumers := TfrmShutdownConsumers.Create(Application, dsInsert);
  try
    frmShutdownConsumers.ShowModal;
  finally
    frmShutdownConsumers.Free;
  end;
end;


procedure TfrmMain.miSizFullDetailClick(Sender: TObject);
begin
  frmReportSizFullDetail := TfrmReportSizFullDetail.Create(Application, dsInsert);
  try
    frmReportSizFullDetail.ShowModal;
  finally
    frmReportSizFullDetail.Free;
  end;
end;


procedure TfrmMain.miSprMolClick(Sender: TObject);
begin
 if not Assigned(frmENSpravMolShow) then
    frmENSpravMolShow := TfrmENSpravMolShow.Create(Application, fmChild);
 frmENSpravMolShow.Show;
end;


{procedure TfrmMain.miServicesCalculationClick(Sender: TObject);
begin
  if not Assigned(frmENServicesCalculationShow) then
    frmENServicesCalculationShow := TfrmENServicesCalculationShow.Create(Application, fmChild);
  frmENServicesCalculationShow.Show;
end;}

procedure TfrmMain.miSealColorsClick(Sender: TObject);
begin
    if not Assigned(frmENSealColorsShow) then
    frmENSealColorsShow := TfrmENSealColorsShow.Create(Application, fmChild);
 frmENSealColorsShow.Show;
end;

procedure TfrmMain.miSealNamesClick(Sender: TObject);
begin
 if not Assigned(frmENSealNamesShow) then
    frmENSealNamesShow := TfrmENSealNamesShow.Create(Application, fmChild);
 frmENSealNamesShow.Show;
end;


procedure TfrmMain.miSendingPaySheetsForEmployeesClick(Sender: TObject);
var
  TempSystemOperations: SystemOperationsControllerSoapPort;
begin
  if Application.MessageBox(PChar('Буде виконано масову відправку розрахункових листів на електронні адреси співробітників.' + #13#10 +
                                  'Продовжити?'),
                            PChar('Увага !'), MB_ICONQUESTION + MB_OKCANCEL + MB_DEFBUTTON1) <> IDOK then
    Exit;

  TempSystemOperations := HTTPRIOSystemOperations as SystemOperationsControllerSoapPort;
  TempSystemOperations.sendingPaySheetsForEmployees;

  Application.MessageBox(PChar('Відправку розрахункових листів завершено!'), PChar('Повідомлення'), MB_ICONINFORMATION);
end;


procedure TfrmMain.miSepoSmartObjectsClick(Sender: TObject);
begin
  if not Assigned(frmENSepoSmartObjectShow) then
  begin
    frmENSepoSmartObjectShow := TfrmENSDTUObjectShow.Create(Application, fmChild, ENSDTUObjectShowFormKind.SMART_IMS);
    frmENSepoSmartObjectShow.Caption := TMenuItem(Sender).Caption;
    frmENSepoSmartObjectShow.UpdateCaption;
 end;
 frmENSepoSmartObjectShow.Show;
end;

procedure TfrmMain.miServicesCalculationClick(Sender: TObject);
var
  f: ENServicesObjectFilter;
begin
  f := ENServicesObjectFilter.Create;
  SetNullIntProps(f);
  SetNullXSProps(f);

  f.conditionSQL := 'coalesce(contracttyperefcode, -1) <> ' + IntToStr(ENSERVICESOBJECTTYPE_CONNECTION);

  f.contractKindRef := ENServicesContractKindRef.Create;
  f.contractKindRef.code := TMenuItem(Sender).Tag;

  if f.contractKindRef.code = SERVICES_CONTRACT_KIND_SERVICES then
    begin
  if not Assigned(frmENServicesCalculationShow) then
      frmENServicesCalculationShow := TfrmENServicesCalculationShow.Create(Application, fmChild);
      frmENServicesCalculationShow.Caption := TMenuItem(Sender).Caption;
      frmENServicesCalculationShow.UpdateCaption;
      frmENServicesCalculationShow.Show;
    end
  else if f.contractKindRef.code = SERVICES_CONTRACT_KIND_SALES then
    begin
      if not Assigned(frmENServicesSalesShow) then
      frmENServicesSalesShow := TfrmENServicesSalesShow.Create(Application, fmChild);
      frmENServicesSalesShow.Caption := TMenuItem(Sender).Caption;
      frmENServicesSalesShow.UpdateCaption;
      frmENServicesSalesShow.Show;
    end

  else
  ShowMessage('Ошибочка в определении вида договора ... SERVICES_CONTRACT_KIND');

end;

procedure TfrmMain.miOperativeObjectPlanClick(Sender: TObject);
begin
  if not Assigned(frmENPlanOperativeShow) then
    frmENPlanOperativeShow := TfrmENPlanOperativeShow.Create(Application, fmChild);
    frmENPlanOperativeShow.Caption := 'План робіт (ТО та ремонт)';
  frmENPlanOperativeShow.Show;
end;

procedure TfrmMain.miOrderAVZClick(Sender: TObject);
var
  f: RQOrderFilter;
begin
  f := RQOrderFilter.create();
  SetNullIntProps(f);
  SetNullXSProps(f);

  f.kindRef := RQOrderKindRef.Create;
  f.kindRef.code := RQORDER_KIND_OE_NOPLANNED;

  f.creationMethodRef := RQOrderCreationMethodRef.Create;
  f.creationMethodRef.code := RQORDER_CREATION_METHOD_AUTO_AVZ;

  if not Assigned(frmRQOrderAVZShow) then
    frmRQOrderAVZShow := TfrmRQOrderShow.Create(Application, fmChild, f);
  frmRQOrderAVZShow.Caption := TMenuItem(Sender).Caption;
  frmRQOrderAVZShow.UpdateCaption;
  frmRQOrderAVZShow.Show;
end;

procedure TfrmMain.miOrderCountersServicesClick(Sender: TObject);
var
  f: RQOrderFilter;
begin
  f := RQOrderFilter.create();
  SetNullIntProps(f);
  SetNullXSProps(f);

  f.kindRef := RQOrderKindRef.Create;
  f.kindRef.code := RQORDER_KIND_OE_NOPLANNED;

  f.creationMethodRef := RQOrderCreationMethodRef.Create;
  f.creationMethodRef.code := RQORDER_CREATION_METHOD_AUTO_COUNTERS_SERVICES;

  if not Assigned(frmRQOrderCountersServicesShow) then
    frmRQOrderCountersServicesShow := TfrmRQOrderShow.Create(Application, fmChild, f);
  frmRQOrderCountersServicesShow.Caption := TMenuItem(Sender).Caption;
  frmRQOrderCountersServicesShow.UpdateCaption;
  frmRQOrderCountersServicesShow.Show;
end;


procedure TfrmMain.miOrderInfoClick(Sender: TObject);
begin
  if not Assigned(frmTKMaterials2RQContractsShow) then
    frmTKMaterials2RQContractsShow := TfrmTKMaterials2RQContractsShow.Create(Application, fmChild);
  frmTKMaterials2RQContractsShow.Show;
end;


procedure TfrmMain.miOutBoxRegistryClick(Sender: TObject);
begin
  frmCorrespondenceReport := TfrmCorrespondenceReport.Create(Application, dsInsert);
  try
    frmCorrespondenceReport.ShowModal;
  finally
    frmCorrespondenceReport.Free;
  end;
end;


procedure TfrmMain.miOutcomeCountersFromExpertiseClick(Sender: TObject);
begin
     if not Assigned(frmRQActCounterExpertiseOutcomeShow) then
    frmRQActCounterExpertiseOutcomeShow := TfrmRQActCounterExpertiseShow.Create(Application, fmChild, False);
  frmRQActCounterExpertiseOutcomeShow.Show;
end;


procedure TfrmMain.miOperativeObjectActClick(Sender: TObject);
begin
  if not Assigned(frmENActIncomeShow) then
    frmENActIncomeShow := TfrmENActIncomeShow.Create(Application, fmChild);
  frmENActIncomeShow.Show;
end;

procedure TfrmMain.miRepByTD1HistoryClick(Sender: TObject);
begin
    frmOrdersBytd1History := TfrmOrdersBytd1History.Create(Application, dsInsert);
  try
    frmOrdersBytd1History.ShowModal;
  finally
    frmOrdersBytd1History.Free;
 end;
end;

procedure TfrmMain.miAutoTiresSheetDistanceClick(Sender: TObject);
begin
    frmReportTiresSheetDistance := TfrmReportTiresSheetDistance.Create(Application, dsInsert);
    frmReportTiresSheetDistance.isTires := True;
  try
    frmReportTiresSheetDistance.ShowModal;
  finally
    frmReportTiresSheetDistance.Free;
  end;
end;

procedure TfrmMain.miTaskSPAClick(Sender: TObject);
var
  argNames, args: EnergyproController.ArrayOfString;
begin
  frmDFTaskSPAFilterEdit := TfrmDFTaskSPAFilterEdit.Create(Application, dsInsert);
  frmDFTaskSPAFilterEdit.Caption := 'Виконання завдань СПА';
  frmDFTaskSPAFilterEdit.forReports := True;
  try
    if frmDFTaskSPAFilterEdit.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 4);
      SetLength(args, 4);

      argNames[0] := 'dateStart';
      args[0] := DateToStr(frmDFTaskSPAFilterEdit.edtDateRegistrationFrom.Date);

      argNames[1] := 'dateFinal';
      args[1] := DateToStr(frmDFTaskSPAFilterEdit.edtDateRegistrationTo.Date);

      argNames[2] := 'departmentCode';
      args[2] := IntToStr(frmDFTaskSPAFilterEdit.departmentCode);

      argNames[3] := 'nodeName';
      if (frmDFTaskSPAFilterEdit.edtNodeTxt.Text <> '') then
        args[3] := frmDFTaskSPAFilterEdit.edtNodeTxt.Text
      else
        args[3] := '';
      ///////

      DMReports.makeReport4DocFlow('taskSPA', argNames, args, 'xls');
    end;
  finally
    frmDFTaskSPAFilterEdit.Free;
  end;
end;


procedure TfrmMain.miTechnicalStatusHighVoltageEquipmentClick(Sender: TObject);
begin
  frmReportTechnicalStatusHighVoltage := TfrmReportTechnicalStatusHighVoltage.Create(Application, dsInsert);
  try
    frmReportTechnicalStatusHighVoltage.ShowModal;
  finally
    frmReportTechnicalStatusHighVoltage.Free;
  end;
end;


procedure TfrmMain.miTechnicalStatusLine10Click(Sender: TObject);
begin
  frmReportTechnicalStatusLine10 := TfrmReportTechnicalStatusLine10.Create(Application, dsInsert);
  try
    frmReportTechnicalStatusLine10.ShowModal;
  finally
    frmReportTechnicalStatusLine10.Free;
  end;
end;


procedure TfrmMain.mitemFKtrans2AXTransClick(Sender: TObject);
begin
    if not Assigned(frmFKTrans2AXTransShow) then
    frmFKTrans2AXTransShow := TfrmFKTrans2AXTransShow.Create(Application, fmChild);
 frmFKTrans2AXTransShow.Show;
end;

procedure TfrmMain.mitestClick(Sender: TObject);
begin
  frmExecutedWorksFromMonthPlanZbyt := TfrmExecutedWorksFromMonthPlanZbyt.Create(Application, dsInsert);
    frmExecutedWorksFromMonthPlanZbyt.report_vid := 3; // енергосбыт test.

  try
     frmExecutedWorksFromMonthPlanZbyt.ShowModal;
  finally
     frmExecutedWorksFromMonthPlanZbyt.Free;
  end;
end;

procedure TfrmMain.miTiresPurchaseClick(Sender: TObject);
begin
    frmReportTiresPurchase := TfrmReportTiresPurchase.Create(Application, dsInsert);
  try
    frmReportTiresPurchase.ShowModal;
  finally
    frmReportTiresPurchase.Free;
  end;
end;

procedure TfrmMain.miWarrantRQFKOrderClick(Sender: TObject);
var frmENWarrantShow : TfrmENWarrantShow;
    f : ENWarrantFilter;
    warrant : ENWarrant;
   // datContract, datWarrant : TXSDate;
    dtdatContract, dtdatWarrant : TDateTime;
    power: Double;
begin

    // datContract := TXSDate.Create;
     //datWarrant := TXSDate.Create;

     f := ENWarrantFilter.Create();
     SetNullXSProps(f);
     SetNullIntProps(f);


     f.warrantTypeRef := ENWarrantTypeRef.Create;
     f.warrantTypeRef.code := ENConsts.ENWARRANT_TYPE_RQFKORDER;

     if Length(f.conditionSQL) = 0 then
     f.conditionSQL := '  warrantstatusrefcode = 0 and warranttyperefcode =  ' + IntToStr(ENConsts.ENWARRANT_TYPE_RQFKORDER)
     else
     f.conditionSQL := f.conditionSQL + ' and warrantstatusrefcode = 0 and warranttyperefcode =  ' + IntToStr(ENConsts.ENWARRANT_TYPE_RQFKORDER);

     frmENWarrantShow := TfrmENWarrantShow.Create(Application,fmChild, f);
     frmENWarrantShow.Caption:='Доручення ';
     frmENWarrantShow.enwarrantTypeCode :=  ENConsts.ENWARRANT_TYPE_RQFKORDER;
     frmENWarrantShow.Show;
     frmENWarrantShow.actUpdateExecute(Sender);


  end;

procedure TfrmMain.miWorkByBillingObjectClick(Sender: TObject);
begin
   frmAnalysWorkByBillingObject := TFrmAnalysWorkByBillingObject.Create(Application, dsInsert);
 try
   frmAnalysWorkByBillingObject.ShowModal;
 finally
   frmAnalysWorkByBillingObject.Free;
 end;
end;

procedure TfrmMain.miWorkedTimeByHumanClick(Sender: TObject);
begin
    frmWorkedTimeByHuman := TfrmWorkedTimeByHuman.Create(Application, dsInsert);
  try
    frmWorkedTimeByHuman.ShowModal;
  finally
    frmWorkedTimeByHuman.Free;
  end;
end;

procedure TfrmMain.N100UseAkumClick(Sender: TObject);
begin
    frmReportTiresSheetDistance := TfrmReportTiresSheetDistance.Create(Application, dsInsert);
    frmReportTiresSheetDistance.isTires := False;
  try
    frmReportTiresSheetDistance.ShowModal;
  finally
    frmReportTiresSheetDistance.Free;
  end;
end;

procedure TfrmMain.mniENBudgets2NomenclaturesClick(Sender: TObject);
begin
    if not Assigned(frmENBudgets2NomenclaturesShow) then
    frmENBudgets2NomenclaturesShow := TfrmENBudgets2NomenclaturesShow.Create(Application, fmChild);
  frmENBudgets2NomenclaturesShow.Show;
end;

procedure TfrmMain.mniENDisconnectorTypeClick(Sender: TObject);
begin //Типы изоляторов
  if not Assigned(frmENDisconnectorTypeShow) then
    frmENDisconnectorTypeShow := TfrmENDisconnectorTypeShow.Create(Application, fmChild);
  frmENDisconnectorTypeShow.Show;
end;

procedure TfrmMain.mniWriteOffProtection1Click(Sender: TObject);
begin
  if not Assigned(frmENPlanWriteOffProtectionShow) then
    frmENPlanWriteOffProtectionShow := TfrmENPlanWriteOffProtectionShow.Create(Application, fmChild);
  frmENPlanWriteOffProtectionShow.Show;
end;

procedure TfrmMain.mReportConnectionPKDClick(Sender: TObject);
var
  argNames, args: ArrayOfString; //Параметри
  reportName: string; //Путь к файлу *.jrxml-отчёта
   argCnt: Integer; //Количество параметров отчёта
begin
  reportName := 'TechConditions/zvit/connectionPKD';
  argCnt := 13;
    SetLength(argNames, argCnt);
    SetLength(args, argCnt);
    argNames[0] := 'qqq';
    args[0] := 'qqq';
  makeReport(reportName, argNames, args, 'xls');
end;

procedure TfrmMain.miFromSideObjectsClick(Sender: TObject);
begin
  if not Assigned(frmENServicesFromSideObjectShow) then
    frmENServicesFromSideObjectShow := TfrmENServicesFromSideObjectShow.Create(Application, fmChild);
  frmENServicesFromSideObjectShow.Show;
end;

procedure TfrmMain.miFromSideObjectsGroupClick(Sender: TObject);
begin
  if not Assigned(frmENServicesFromSideObjectShow) then
    frmENServicesFromSideObjectShow := TfrmENServicesFromSideObjectShow.Create(Application, fmChild);
  frmENServicesFromSideObjectShow.Show;
end;

procedure TfrmMain.miENExecutorsClick(Sender: TObject);
begin
  if not Assigned(frmENExecutorShow) then
    frmENExecutorShow := TfrmENExecutorShow.Create(Application, fmChild);
  frmENExecutorShow.Show;
end;

procedure TfrmMain.miENFuelDistributionSheetClick(Sender: TObject);
begin
  if not Assigned(frmENFuelDistributionSheetShow) then
    frmENFuelDistributionSheetShow := TfrmENFuelDistributionSheetShow.Create(Application, fmChild);
  frmENFuelDistributionSheetShow.Show;
end;

procedure TfrmMain.miENFuelSheetVolumesClick(Sender: TObject);
begin
  if not Assigned(frmENFuelSheetVolumesShow) then
    frmENFuelSheetVolumesShow := TfrmENFuelSheetVolumesShow.Create(Application, fmChild);
  frmENFuelSheetVolumesShow.Show;
end;


procedure TfrmMain.miENInspectionSheetClick(Sender: TObject);
var
  inspectionSheetFilter: ENInspectionSheetFilter;
begin
  inspectionSheetFilter := ENInspectionSheetFilter.Create;
  SetNullIntProps(inspectionSheetFilter);
  SetNullXSProps(inspectionSheetFilter);
  inspectionSheetFilter.equipmentKind := EQUIPMENTKIND_LOW_VOLTAGE;

  frmENInspectionSheetShow := TfrmENInspectionSheetShow.Create(Application, fmChild, inspectionSheetFilter);
  frmENInspectionSheetShow.Show;
end;


procedure TfrmMain.miENInspectionSheetHighVoltageClick(Sender: TObject);
var
  inspectionSheetFilter: ENInspectionSheetFilter;
begin
  inspectionSheetFilter := ENInspectionSheetFilter.Create;
  SetNullIntProps(inspectionSheetFilter);
  SetNullXSProps(inspectionSheetFilter);
  inspectionSheetFilter.equipmentKind := EQUIPMENTKIND_HIGH_VOLTAGE;

  frmENInspectionSheetShow := TfrmENInspectionSheetShow.Create(Application, fmChild, inspectionSheetFilter);
  frmENInspectionSheetShow.Show;
end;


procedure TfrmMain.miENMolClick(Sender: TObject);
begin
  if not Assigned(frmENMolShow) then
    frmENMolShow := TfrmENMolShow.Create(Application, fmChild);
  frmENMolShow.Show;
end;

procedure TfrmMain.mniGPS1Click(Sender: TObject);
begin

//     if not Assigned(frmENTrafficGPSShow) then
  frmENTrafficGPSShow := TfrmENTrafficGPSShow.Create(Application, fmChild);
       frmENTrafficGPSShow.Show;

end;

procedure TfrmMain.mniLandAllotmentHistogramClick(Sender: TObject);
begin
  makeReport('Histograms/LandAllotmentHistogram/LandAllotmentHistogram_wrapper', ArrayOfString.Create('dummy'), ArrayOfString.Create('dummy'), 'xls');
end;

procedure TfrmMain.mniMaterialsMovementClick(Sender: TObject);
var
  accountingType: TKAccountingType;
begin
  accountingType := TKAccountingType.Create;
  accountingType.code := ENConsts.TK_ACCOUNTINGTYPE_TMC;
  if not Assigned(frmRQPackingListMaterialsShow) then
  begin
    frmRQPackingListMaterialsShow := TfrmRQPackingListShow.Create(Application, fmChild, accountingType);
    frmRQPackingListMaterialsShow.Caption := TMenuItem(Sender).Caption;
  end;
    frmRQPackingListMaterialsShow.Show;
end;

procedure TfrmMain.mniPlanPayClick(Sender: TObject);
begin

  if not Assigned(frmRQPlanPayShow) then
    frmRQPlanPayShow := TfrmRQPlanPayShow.Create(Application, fmChild);
    frmRQPlanPayShow.Show;
end;

procedure TfrmMain.mniRegularAssetBaseClick(Sender: TObject);
var frmRegulatoryAssetBaseCalculationShow : TfrmRegulatoryAssetBaseCalculationShow;
begin
    frmRegulatoryAssetBaseCalculationShow := TfrmRegulatoryAssetBaseCalculationShow.Create(Application, fmChild);
    frmRegulatoryAssetBaseCalculationShow.Show;
end;

procedure TfrmMain.mniReportServicesCountersSentOffClick(Sender: TObject);
var
  form: TfrmReportServicesCountersSentOff;
begin
  form := TfrmReportServicesCountersSentOff.Create(Application, dsInsert);
  try
    form.ShowModal;
  finally
    form.Free;
  end;
end;

procedure TfrmMain.mnirepServicesContragentClick(Sender: TObject);
begin
    frmReportOborotServices := TfrmReportOborotServices.Create(Application, dsInsert);
  try
    frmReportOborotServices.ShowModal;
  finally
    frmReportOborotServices.Free;
  end;
end;

procedure TfrmMain.mniRQContactTypeClick(Sender: TObject);
begin
  if not Assigned(frmRQContactTypeShow) then
    frmRQContactTypeShow := TfrmRQContactTypeShow.Create(Application, fmChild);
  frmRQContactTypeShow.Show;
end;

procedure TfrmMain.mniRQPackingListCountersClick(Sender: TObject);
var
  accountingType: TKAccountingType;
begin
  accountingType := TKAccountingType.Create;
  accountingType.code := ENConsts.TK_ACCOUNTINGTYPE_COUNTER;
  if not Assigned(frmRQPackingListCountersShow) then
  begin
    frmRQPackingListCountersShow := TfrmRQPackingListShow.Create(Application, fmChild, accountingType);
    frmRQPackingListCountersShow.Caption := TMenuItem(Sender).Caption;
  end;
  frmRQPackingListCountersShow.Show;
end;

procedure TfrmMain.miIsSkinsActiveClick(Sender: TObject);
begin
  miIsSkinsActive.Checked := not miIsSkinsActive.Checked;
  RegWriteBool(_SKINS_KEY, 'Active', miIsSkinsActive.Checked);
  // Перечитаем еще раз из реестра, чтобы быть уверенными, что записалось
  miIsSkinsActive.Checked := RegReadBool(_SKINS_KEY, 'Active');

  Application.MessageBox(PChar('Перезайдіть у програму, щоб зміни набули чинності!'), PChar('Увага!'), MB_ICONINFORMATION);
end;


procedure TfrmMain.miItemWeightClick(Sender: TObject);
var
  priorityCoefficientFilter: ENPriorityCoefficientFilter;
begin
  priorityCoefficientFilter := ENPriorityCoefficientFilter.Create;
  SetNullIntProps(priorityCoefficientFilter);
  SetNullXSProps(priorityCoefficientFilter);
  priorityCoefficientFilter.coeffTypeRef := ENPriorityCoeffTypeRef.Create;
  priorityCoefficientFilter.coeffTypeRef.code := TMenuItem(Sender).Tag;

  frmENPriorityCoefficientShow := TfrmENPriorityCoefficientShow.Create(Application, fmChild, priorityCoefficientFilter);
  frmENPriorityCoefficientShow.coeffTypeCode := TMenuItem(Sender).Tag;
  frmENPriorityCoefficientShow.Caption := TMenuItem(Sender).Caption;
  frmENPriorityCoefficientShow.UpdateCaption;
  frmENPriorityCoefficientShow.Show;
end;


procedure TfrmMain.mniWriteOffCountersClick(Sender: TObject);
var
  f: RQFKOrderFilter;
begin

         f := RQFKOrderFilter.Create;
         SetNullIntProps(f);
         SetNullXSProps(f);

         f.kind := RQFKOrderKind.Create;
         f.kind.code :=  RQFKORDER_KIND_WRITEOFFCOUNTERS;

         if f.kind.code = RQFKORDER_KIND_WRITEOFFCOUNTERS then
         begin
           if not Assigned(frmRQFKOrderWhiteOffCounters) then
             frmRQFKOrderWhiteOffCounters := TfrmRQFKOrderShow.Create(Application, fmChild, f);

            frmRQFKOrderWhiteOffCounters.Caption := TMenuItem(Sender).Caption;
            frmRQFKOrderWhiteOffCounters.UpdateCaption;

            frmRQFKOrderWhiteOffCounters.Show;

         end

end;

procedure TfrmMain.miTransportManagementClick(Sender: TObject);
//var frmTransportManagement : TfrmTransportManagement;
begin
  if not Assigned(frmTransportManagement) then
    frmTransportManagement := TfrmTransportManagement.Create(Application, fmChild);
    frmTransportManagement.Show;
end;

procedure TfrmMain.N1110Click(Sender: TObject);
begin
  frmCCRunReport := TfrmCCRunReport.Create(Application, dsEdit);
  try
    frmCCRunReport.reportName := TMenuItem(Sender).Caption;
    frmCCRunReport.ShowModal;
  finally
    frmCCRunReport.Free;
    frmCCRunReport := nil;
  end;

end;

procedure TfrmMain.N111provisionServicesPartySelectedDateClick(Sender: TObject);
begin
  frmReportServicesForDate := TfrmReportServicesForDate.Create(Application, dsInsert);
  try
    frmReportServicesForDate.ShowModal;
  finally
    frmReportServicesForDate.Free;
  end;
end;

procedure TfrmMain.miCargoPlansClick(Sender: TObject);
begin
  if not Assigned(frmENCargoPlanShow) then
    frmENCargoPlanShow := TfrmENCargoPlanShow.Create(Application, fmChild);
    frmENCargoPlanShow.Caption := 'План робіт (перевезення вантажу)';
  frmENCargoPlanShow.Show;
end;

procedure TfrmMain.miCC_PlannedWorksClick(Sender: TObject);
var
  ReportCallCenterPlannedWorks: TReportCallCenterPlannedWorks;
begin
  ReportCallCenterPlannedWorks := TReportCallCenterPlannedWorks.Create(Application, dsEdit);
  try
    ReportCallCenterPlannedWorks.ShowModal;
  finally
    ReportCallCenterPlannedWorks.Free;
    ReportCallCenterPlannedWorks := nil;
  end;
end;

procedure TfrmMain.N112Click(Sender: TObject);
begin
 if not Assigned(frmENPlanWorkBillingShow) then
    frmENPlanWorkBillingShow := TfrmENPlanWorkBillingShow.Create(Application, fmChild);
 frmENPlanWorkBillingShow.Show;
end;

procedure TfrmMain.mniAnnexOKClick(Sender: TObject);
begin
   frmAnnex_OK := TfrmAnnex_OK.Create(Application, dsInsert);
 try
   frmAnnex_OK.ShowModal;
 finally
   frmAnnex_OK.Free;
 end;
end;

procedure TfrmMain.mniAverageDepartureTimeOfTransportClick(Sender: TObject);
var
  reportDate : TXSDate;
begin
    reportDate := TfrmChooseTXSDate.choose('Середній час виїзду автотранспорту по транспортних підрозділах'
    , 'Оберіть дату', nil);
    if Assigned(reportDate) then begin
      makeReport('transport/AverageDepartureTimeOfTransport/AverageDepartureTimeOfTransport'
      , ArrayOfString.Create('reportDate')
      , ArrayOfString.Create(XSDate2String(reportDate)), 'xls');
    end;
end;

procedure TfrmMain.mniBufetDiscountClick(Sender: TObject);
var
  period: EditChoosePeriod.TDateArray;
  frmPeriod: TfrmChoosePeriod;
  args, argNames: ArrayOfString;
begin
  frmPeriod := TfrmChoosePeriod.Create(Application, nil, 'Оберіть період');
  frmPeriod.ShowModal;
  period := frmPeriod.GetValue;
  if Assigned(period) then
  begin
    SetLength(args, 2);
    SetLength(argNames, 2);

    argNames[0] := 'dateStart';
    argNames[1] := 'dateFinal';

    args[0] := DateToStr(period[0]);
    args[1] := DateToSTr(period[1]);

    makeReport('Bufet/bufet_discount_wrapper', argNames, args, 'pdf');
  end;

  if Assigned(frmPeriod) then
    frmPeriod.Free;

end;

procedure TfrmMain.mniBuffetCashlessDbfClick(Sender: TObject);
var
  period: EditChoosePeriod.TDateArray;
  frmPeriod: TfrmChoosePeriod;
  args, argNames: ArrayOfString;
  fileName: string;
begin
  frmPeriod := TfrmChoosePeriod.Create(Application, nil, 'Оберіть період');
  frmPeriod.ShowModal;
  period := frmPeriod.GetValue;
  if Assigned(period) then
  begin
    SetLength(args, 2);
    SetLength(argNames, 2);

    argNames[0] := 'dateStart';
    argNames[1] := 'dateFinal';

    args[0] := DateToStr(period[0]);
    args[1] := DateToSTr(period[1]);

    fileName := Format('beznal_%s', [args[1]]);
    fileName := makeReport('Bufet/dbf/bufet_cashless_wrapper', argNames, args, 'dbf', fileName);

    if Length(fileName) > 0 then
    begin
      Application.MessageBox(PChar(Format('Файл %s сформовано!', [fileName])), PChar('Повідомлення'), MB_ICONINFORMATION);
    end;

  end;

  if Assigned(frmPeriod) then
    frmPeriod.Free;

end;

procedure TfrmMain.mniBuffetDiscountDbfClick(Sender: TObject);
var
  period: EditChoosePeriod.TDateArray;
  frmPeriod: TfrmChoosePeriod;
  args, argNames: ArrayOfString;
  fileName: string;
begin
  frmPeriod := TfrmChoosePeriod.Create(Application, nil, 'Оберіть період');
  frmPeriod.ShowModal;
  period := frmPeriod.GetValue;
  if Assigned(period) then
  begin
    SetLength(args, 2);
    SetLength(argNames, 2);

    argNames[0] := 'dateStart';
    argNames[1] := 'dateFinal';

    args[0] := DateToStr(period[0]);
    args[1] := DateToSTr(period[1]);

    fileName := Format('skidka_%s', [args[1]]);
    fileName := makeReport('Bufet/dbf/bufet_discount_wrapper', argNames, args, 'dbf', fileName);

    if Length(fileName) > 0 then
    begin
      Application.MessageBox(PChar(Format('Файл %s сформовано!', [fileName])), PChar('Повідомлення'), MB_ICONINFORMATION);
    end;

  end;

  if Assigned(frmPeriod) then
    frmPeriod.Free;

end;

procedure TfrmMain.mniBuffetDiscountSummaryClick(Sender: TObject);
var
  period: EditChoosePeriod.TDateArray;
  frmPeriod: TfrmChoosePeriod;
  args, argNames: ArrayOfString;
begin
  SetLength(period, 2);
  period[0] := StartOfTheMonth(Today());
  period[1] := EndOfTheMonth(Today());
  frmPeriod := TfrmChoosePeriod.Create(Application, period, 'Оберіть період');
  frmPeriod.ShowModal;
  period := frmPeriod.GetValue;
  if Assigned(period) then
  begin
    SetLength(args, 2);
    SetLength(argNames, 2);

    argNames[0] := 'dateStart';
    argNames[1] := 'dateFinal';

    args[0] := DateToStr(period[0]);
    args[1] := DateToSTr(period[1]);

    makeReport('Bufet/bufet_discount_summary_wrapper', argNames, args, 'pdf');
  end;

  if Assigned(frmPeriod) then
    frmPeriod.Free;

end;

procedure TfrmMain.mniBuffetSalesInvoiceClick(Sender: TObject);
var
  number : String;
begin
  if InputQuery('EnergyNet', 'Введіть номер видаткової накладної', number) then begin
    if Length(Trim(number)) = 0 then begin
      Application.MessageBox(PChar('Не введено номер видаткової накладної')
        , PChar('Увага'), MB_ICONWARNING);
      Exit;
    end;
    makeReport('Bufet/SalesInvoice_wrapper'
      , ArrayOfString.Create('number')
      , ArrayOfString.Create(number)
      , 'pdf');
  end;
end;

procedure TfrmMain.miConnectionInfoSumClick(Sender: TObject);
var
  frmReportConnectionInfoSum: TfrmReportConnectionInfoSum;
begin
   frmReportConnectionInfoSum := TfrmReportConnectionInfoSum.Create(Application, dsInsert);
  try
    frmReportConnectionInfoSum.ShowModal;
  finally
    frmReportConnectionInfoSum.Free;
  end;
end;

procedure TfrmMain.miConnectionInfoSumStoimostClick(Sender: TObject);
var
  frmReportConnectionInfoSumStoimost: TfrmReportConnectionInfoSumStoimost;
begin
   frmReportConnectionInfoSumStoimost := TfrmReportConnectionInfoSumStoimost.Create(Application, dsInsert);
  try
    frmReportConnectionInfoSumStoimost.ShowModal;
  finally
    frmReportConnectionInfoSumStoimost.Free;
  end;
end;

procedure TfrmMain.miConnectionServicesMonitoringProvisionClick(
  Sender: TObject);
var
  frmReportMonitoringProvision: TfrmReportMonitoringProvision;
begin
  frmReportMonitoringProvision := TfrmReportMonitoringProvision.Create(Application, dsInsert);
  try
    frmReportMonitoringProvision.ShowModal;
  finally
    frmReportMonitoringProvision.Free;
  end;
end;

procedure TfrmMain.miConnectionsTariffClick(Sender: TObject);
begin
  if not Assigned(frmENConnectionTariffShow) then
    frmENConnectionTariffShow := TfrmENConnectionTariffShow.Create(Application, fmChild);
  frmENConnectionTariffShow.Show;
end;

procedure TfrmMain.miContractProjectClick(Sender: TObject);
var
  f: ENTechConditionsServicesFilter;
begin
  f := ENTechConditionsServicesFilter.Create;
  SetNullIntProps(f);
  SetNullXSProps(f);

  f.connectionKindRef := ENConnectionKindRef.Create;
  f.connectionKindRef.code := ENCONNECTIONKIND_GENERAL_CONNECTION;

  f.techCondServicesType := ENTechConditionsServicesType.Create;
  f.techCondServicesType.code := TMenuItem(Sender).Tag;

  f.orderBySQL := 'cast(contractnumber as integer) desc';

  if  f.techCondServicesType.code = ENTECHCONDITIONSSERVICES_TYPE_PROJECT then
  begin
    if not Assigned(frmENTechConditionsServicesProjectShow) then
      frmENTechConditionsServicesProjectShow := TfrmENTechConditionsServicesShow.Create(Application, fmChild, f);
    frmENTechConditionsServicesProjectShow.Caption := TMenuItem(Sender).Caption;
    frmENTechConditionsServicesProjectShow.UpdateCaption;
    frmENTechConditionsServicesProjectShow.Show;
  end
  else if f.techCondServicesType.code = ENTECHCONDITIONSSERVICES_TYPE_REALIZATION then
  begin
    if not Assigned(frmENTechConditionsServicesRealizationShow) then
      frmENTechConditionsServicesRealizationShow := TfrmENTechConditionsServicesShow.Create(Application, fmChild, f);
    frmENTechConditionsServicesRealizationShow.Caption := TMenuItem(Sender).Caption;
    frmENTechConditionsServicesRealizationShow.UpdateCaption;
    frmENTechConditionsServicesRealizationShow.Show;
  end;
end;

procedure TfrmMain.miFactTransportClick(Sender: TObject);
begin
    frmReportFactTransport := TfrmReportFactTransport.Create(Application, dsInsert);
  try
    frmReportFactTransport.ShowModal;
  finally
    frmReportFactTransport.Free;
 end;
end;


procedure TfrmMain.miENTechCondResponsiblesClick(Sender: TObject);
begin
  if not Assigned(frmENTechCondResponsiblesShow) then
    frmENTechCondResponsiblesShow := TfrmENTechCondResponsiblesShow.Create(Application, fmChild);
  frmENTechCondResponsiblesShow.Show;
end;


procedure TfrmMain.miENWarrant4DepartmentClick(Sender: TObject);
begin
  if not Assigned(frmENWarrant4DepartmentShow) then
    frmENWarrant4DepartmentShow := TfrmENWarrant4DepartmentShow.Create(Application, fmChild);
  frmENWarrant4DepartmentShow.Show;
end;


procedure TfrmMain.miENTechConditionsObjectsClick(Sender: TObject);
begin
  if not Assigned(frmENTechConditionsObjectsShow) then
    frmENTechConditionsObjectsShow := TfrmENTechConditionsObjectsShow.Create(Application, fmChild);
  frmENTechConditionsObjectsShow.Show;
end;

procedure TfrmMain.N415Click(Sender: TObject);
begin
  frmParamsPlanSbyt := TfrmParamsPlanSbyt.Create(Application, dsInsert);
  try
     frmParamsPlanSbyt.ShowModal;
  finally
    frmParamsPlanSbyt.Free;
  end;
end;

procedure TfrmMain.miUsedMaterialsClick(Sender: TObject);
begin
    frmReportUsedMaterials := TfrmReportUsedMaterials.Create(Application, dsInsert);
  try
    frmReportUsedMaterials.ShowModal;
  finally
    frmReportUsedMaterials.Free;
 end;
end;

procedure TfrmMain.miFactProtectionSubstationClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin

  frmStateProtection := TfrmStateProtection.Create(Application, dsInsert);

  try
    if frmStateProtection.ShowModal = mrOk then
    begin
      SetLength(argNames, 1);
      SetLength(args, 1);

      argNames[0] := 'rencode';
      args[0] := IntToStr(frmStateProtection.coderen);

      reportName := 'Protection/fact_substation';
      makeReport(reportName, argNames, args, 'xls');
    end;
  finally
    frmStateProtection.Free;
  end;

end;

procedure TfrmMain.miFactProtectionBrigadeClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin

  frmStateProtection := TfrmStateProtection.Create(Application, dsInsert);

  try
    if frmStateProtection.ShowModal = mrOk then
    begin
      SetLength(argNames, 1);
      SetLength(args, 1);

      argNames[0] := 'rencode';
      args[0] := IntToStr(frmStateProtection.coderen);

      reportName := 'Protection/fact_brigade';
      makeReport(reportName, argNames, args, 'xls');
    end;
  finally
    frmStateProtection.Free;
  end;

end;

procedure TfrmMain.miOMTSResponsiblesClick(Sender: TObject);
begin
  frmOMTSResponsiblesEdit := TfrmOMTSResponsiblesEdit.Create(Application, dsInsert);
  try
    frmOMTSResponsiblesEdit.ShowModal;
  finally
    frmOMTSResponsiblesEdit.Free;
  end;
end;

procedure TfrmMain.miTransportRouteClick(Sender: TObject);
begin
  if not Assigned(frmENTransportRouteShow) then
    frmENTransportRouteShow := TfrmENTransportRouteShow.Create(Application, fmChild);
  frmENTransportRouteShow.Show;
end;

procedure TfrmMain.miCargoObjectPlansClick(Sender: TObject);
begin
  if not Assigned(frmENCargoObjectPlanShow) then
    frmENCargoObjectPlanShow := TfrmENCargoObjectPlanShow.Create(Application, fmChild);
    frmENCargoObjectPlanShow.Caption := 'План робіт (перевезення вантажу)';
  frmENCargoObjectPlanShow.Show;
end;

procedure TfrmMain.N116ConsolidatedActamReportperMonthClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
begin

//    frmtableActSelector := TfrmtableActSelector.Create(Application, dsInsert);
//  try
//    frmtableActSelector.ShowModal;
//  finally
//    frmtableActSelector.Free;
// end;

  frmENPeriod := TfrmENPeriod.Create(Application, dsInsert);
  try
    frmENPeriod.DisableControls([frmENPeriod.edtEPRenName, frmENPeriod.edtENBudgetName, frmENPeriod.edtENElementName]);
    frmENPeriod.HideControls([frmENPeriod.lblEPRenName, frmENPeriod.edtEPRenName, frmENPeriod.spbEPRen, frmENPeriod.spbEPRenClear, frmENPeriod.lblENBudgetName, frmENPeriod.edtENBudgetName, frmENPeriod.spbENBudget, frmENPeriod.spbENBudgetClear, frmENPeriod.lblENElementName, frmENPeriod.edtENElementName, frmENPeriod.spbENElement, frmENPeriod.spbENElementClear, frmENPeriod.GroupBox1, frmENPeriod.GroupBox2]);

    frmENPeriod.lblYearGen.Top := 20;
    frmENPeriod.edtYearGen.Top := 20;
    frmENPeriod.lblMonthGen.Top := 40;
    frmENPeriod.edtMonthGen.Top := 40;
    frmENPeriod.btnOk.Top := 100;
    frmENPeriod.btnCancel.Top := 100;

    frmENPeriod.Height := 200;

    frmENPeriod.edtYearGen.ItemIndex := 4;
    if frmENPeriod.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 2);
      SetLength(args, 2);

      argNames[0] := 'yearGen';
      args[0] := frmENPeriod.edtYearGen.Text;

      argNames[1] := 'monthGen';
      args[1] := IntToStr(frmENPeriod.edtMonthGen.ItemIndex + 1);
      ///////

      makeReport('WorkByAkt/tableByAct/tableByAct', argNames, args, 'xls');
    end;
  finally
    frmENPeriod.Free;
  end;

end;

procedure TfrmMain.N117InfoMetersAccountValidityPeriodAccuracyClassClick(Sender: TObject);
begin
     frmReportCounters := TfrmReportCounters.Create(Application, dsInsert);
  try
     frmReportCounters.ShowModal;
  finally
    frmReportCounters.Free;
 end;
end;

procedure TfrmMain.N11BonnusDriversClick(Sender: TObject);
begin

  frmReportPercentPremDriver := TfrmReportPercentPremDriver.Create(Application, dsInsert);
  try
   frmReportPercentPremDriver.ShowModal;
  finally
    frmReportPercentPremDriver.Free;
  end;

end;

procedure TfrmMain.miServicesOutBuhClick(Sender: TObject);
var
  conditionSQL: string;
  servicesObjFilter: ENServicesObjectFilter;
  argNames, args: ArrayOfString;
  reportName: string;
begin
  frmENServicesObjectFilterEdit := TfrmENServicesObjectFilterEdit.Create(Application, dsInsert);
  try
    ENServicesObjectFilterObj := ENServicesObjectFilter.Create;
    SetNullIntProps(ENServicesObjectFilterObj);
    SetNullXSProps(ENServicesObjectFilterObj);

    frmENServicesObjectFilterEdit.btnOk.Caption := 'Ок';
    frmENServicesObjectFilterEdit.Caption :=  'Звіт по послугам на сторону ';

    frmENServicesObjectFilterEdit.lblFinDocID.Visible := False;
    frmENServicesObjectFilterEdit.edtFinDocID.Visible := False;
    frmENServicesObjectFilterEdit.lblContragentType.Visible := False;
    frmENServicesObjectFilterEdit.cmbContragentType.Visible := False;
    frmENServicesObjectFilterEdit.Label1.Visible := False;
    frmENServicesObjectFilterEdit.cbContractStatus.Visible := False;
    frmENServicesObjectFilterEdit.Label2.Visible := False;
    frmENServicesObjectFilterEdit.cbStatus.Visible := False;
    frmENServicesObjectFilterEdit.lblInfo.Visible := True;
    frmENServicesObjectFilterEdit.lblInfo.Width := 200;
    frmENServicesObjectFilterEdit.lblInfo.Caption := 'Звіт формується по договорам з Бух.Статусом "Проведений у ФК" !!!';
    frmENServicesObjectFilterEdit.finWorkType := 0;
    frmENServicesObjectFilterEdit.Label7.Visible := True;
    frmENServicesObjectFilterEdit.edtTKFINWorkType.Visible := True;
    frmENServicesObjectFilterEdit.spbTKFINWorkType.Visible := True;
    frmENServicesObjectFilterEdit.edtTKFINWorkType.Enabled := False;
    frmENServicesObjectFilterEdit.chkShowCalcul.Visible := True;
    frmENServicesObjectFilterEdit.lblContractDateServices.Caption := 'Дата проведення доходного акту';
    frmENServicesObjectFilterEdit.lblKarta.Visible := true;
    frmENServicesObjectFilterEdit.edtTKClassificationTypeName.Visible := true;
    frmENServicesObjectFilterEdit.spbTKClassificationType.Visible := true;
    frmENServicesObjectFilterEdit.dtpEnActFinal.Visible := true;
    frmENServicesObjectFilterEdit.lbl2.Visible := true;
    frmENServicesObjectFilterEdit.dtpEnActStart.Visible := true;
    frmENServicesObjectFilterEdit.lbl1.Visible := true;

    //frmENServicesObjectFilterEdit.chkShowClassificationList.Visible := True;
    //frmENServicesObjectFilterEdit.chkShowClassificationList.Checked := False;

    frmENServicesObjectFilterEdit.chbReplaceCounter.Visible := False;
    frmENServicesObjectFilterEdit.cbCreatedFromSite.Visible := False;

    if frmENServicesObjectFilterEdit.ShowModal = mrOk then
    begin
      servicesObjFilter := ENServicesObjectFilter.Create;
      servicesObjFilter := ENServicesObjectFilterObj;
      conditionSQL := ENServicesObjectFilter(servicesObjFilter).conditionSQL;
      AddCondition(conditionSQL, 'contractnumberservices is not null');
      ENServicesObjectFilter(servicesObjFilter).conditionSQL := conditionSQL;
      ENServicesObjectFilter(servicesObjFilter).orderBySQL := 'dateedit desc, code desc';


      /////// Parameters
      SetLength(argNames, 20);
      SetLength(args, 20);

      argNames[0] := 'contractNumberServices';
      args[0] := ENServicesObjectFilterObj.contractNumberServices;

      argNames[1] := 'ContractDateServicesFrom';
      if frmENServicesObjectFilterEdit.edtContractDateServicesFrom.Checked then
      args[1] := DateToStr(frmENServicesObjectFilterEdit.edtContractDateServicesFrom.Date)
      else
      args[1] := '';

      argNames[2] := 'ContractDateServicesTo';
      if frmENServicesObjectFilterEdit.edtContractDateServicesTo.Checked then
      args[2] := DateToStr(frmENServicesObjectFilterEdit.edtContractDateServicesTo.Date)
      else
      args[2] := '';

      argNames[3] := 'ContragentName';
      args[3] :=   frmENServicesObjectFilterEdit.edtContragentName.Text;

      argNames[4] := 'ContragentOkpo';
      args[4] := frmENServicesObjectFilterEdit.edtContragentOkpo.Text;

      argNames[5] := 'ENDepartmentDepartment';
      if ENServicesObjectFilterObj.department <> nil then
      args[5] :=   IntToStr(ENServicesObjectFilterObj.department.code)
      else
      args[5] := '0';

      argNames[6] := 'ContragentAddress';
      args[6] := ENServicesObjectFilterObj.contragentAddress;

      argNames[7] := 'ContractNumber';
      args[7] := ENServicesObjectFilterObj.contractNumber;

      argNames[8] := 'ContractDateFrom';
      if frmENServicesObjectFilterEdit.edtContractDateFrom.Checked then
        args[8] := DateToStr(frmENServicesObjectFilterEdit.edtContractDateFrom.Date)
      else
        args[8] := '';

      argNames[9] := 'ContractDateTo';
      if frmENServicesObjectFilterEdit.edtContractDateTo.Checked then
        args[9] := DateToStr(frmENServicesObjectFilterEdit.edtContractDateTo.Date)
      else
        args[9] := '';

      argNames[10] := 'PartnerCode';
      args[10] := ENServicesObjectFilterObj.partnerCode;

      argNames[11] := 'FinDocCode';
      args[11] := ENServicesObjectFilterObj.finDocCode;

      argNames[12] := 'edtCommentGen';
      args[12] := ENServicesObjectFilterObj.commentGen;

      argNames[13] := 'finWorkType';
      args[13] := IntToStr(frmENServicesObjectFilterEdit.finWorkType);

      argNames[14] := 'showcalcul';
      if ((frmENServicesObjectFilterEdit.chkShowCalcul.Checked = True) and (frmENServicesObjectFilterEdit.chkShowCalcul.Visible = True)) then
         args[14] := '1'
      else
        args[14] := '0';

      argNames[15] := 'tkclassificationtypecodes';
      // args[15] := IntToStr(frmENServicesObjectFilterEdit.TKClassificationTypeCode);
      if trim(frmENServicesObjectFilterEdit.edtTKClassificationTypeName.Text) = '' then
        args[15] := ''
        else
        args[15] := frmENServicesObjectFilterEdit.edtTKClassificationTypeName.Text;

        argNames[16] := 'isNoPay';
      if ENServicesObjectFilterObj.isNoPay <> Low(Integer) then
      begin
          args[16] := IntToStr(ENServicesObjectFilterObj.isNoPay);
      end
      else
      begin
          args[16] := '-1';
        end;


      argNames[18] := 'EnActStart';
      if frmENServicesObjectFilterEdit.dtpEnActStart.Checked then
        args[18] := DateToStr(frmENServicesObjectFilterEdit.dtpEnActStart.Date)
      else
        args[18] := '';

      argNames[19] := 'EnActFinal';
      if frmENServicesObjectFilterEdit.dtpEnActFinal.Checked then
        args[19] := DateToStr(frmENServicesObjectFilterEdit.dtpEnActFinal.Date)
      else
        args[19] := '';

      makeReport('Services/ReportServicesBuh/ReportServicesBuh', argNames, args, 'xls');

    end;
  finally
    frmENServicesObjectFilterEdit.Free;
    frmENServicesObjectFilterEdit := nil;
  end;
end;

procedure TfrmMain.miServicesOutBuhNewMetodClick(Sender: TObject);
var
  conditionSQL: string;
  servicesObjFilter: ENServicesObjectFilter;
  argNames, args: ArrayOfString;
  reportName: string;
begin
  frmENServicesObjectFilterEdit := TfrmENServicesObjectFilterEdit.Create(Application, dsInsert);
  try
    ENServicesObjectFilterObj := ENServicesObjectFilter.Create;
    SetNullIntProps(ENServicesObjectFilterObj);
    SetNullXSProps(ENServicesObjectFilterObj);
    frmENServicesObjectFilterEdit.btnOk.Caption := 'Ок';
    frmENServicesObjectFilterEdit.Caption :=  'Звіт по послугам на сторону';

    frmENServicesObjectFilterEdit.lblFinDocID.Visible := False;
    frmENServicesObjectFilterEdit.edtFinDocID.Visible := False;
    frmENServicesObjectFilterEdit.Label1.Visible := False;
    frmENServicesObjectFilterEdit.cbContractStatus.Visible := False;
    frmENServicesObjectFilterEdit.Label2.Visible := False;
    frmENServicesObjectFilterEdit.cbStatus.Visible := False;
    frmENServicesObjectFilterEdit.lblInfo.Visible := True;
    frmENServicesObjectFilterEdit.lblInfo.Width := 200;
    frmENServicesObjectFilterEdit.lblInfo.Caption := 'Звіт формується по договорам з Бух.Статусом "Проведений у ФК" !!!';
    frmENServicesObjectFilterEdit.finWorkType := 0;
    frmENServicesObjectFilterEdit.Label7.Visible := True;
    frmENServicesObjectFilterEdit.edtTKFINWorkType.Visible := True;
    frmENServicesObjectFilterEdit.spbTKFINWorkType.Visible := True;
    frmENServicesObjectFilterEdit.edtTKFINWorkType.Enabled := False;
    frmENServicesObjectFilterEdit.chkShowCalcul.Visible := True;
    frmENServicesObjectFilterEdit.lblContractDateServices.Caption := 'Дата проведення доходного акту';
    if frmENServicesObjectFilterEdit.ShowModal = mrOk then
    begin
      servicesObjFilter := ENServicesObjectFilter.Create;
      servicesObjFilter := ENServicesObjectFilterObj;
      conditionSQL := ENServicesObjectFilter(servicesObjFilter).conditionSQL;
      AddCondition(conditionSQL, 'contractnumberservices is not null');
      ENServicesObjectFilter(servicesObjFilter).conditionSQL := conditionSQL;
      ENServicesObjectFilter(servicesObjFilter).orderBySQL := 'dateedit desc, code desc';


      /////// Parameters
      SetLength(argNames, 15);
      SetLength(args, 15);

      argNames[0] := 'contractNumberServices';
      args[0] := ENServicesObjectFilterObj.contractNumberServices;

      argNames[1] := 'ContractDateServicesFrom';
      if frmENServicesObjectFilterEdit.edtContractDateServicesFrom.Checked then
      args[1] := DateToStr(frmENServicesObjectFilterEdit.edtContractDateServicesFrom.Date)
      else
      args[1] := '';

      argNames[2] := 'ContractDateServicesTo';
      if frmENServicesObjectFilterEdit.edtContractDateServicesTo.Checked then
      args[2] := DateToStr(frmENServicesObjectFilterEdit.edtContractDateServicesTo.Date)
      else
      args[2] := '';

      argNames[3] := 'ContragentName';
      args[3] :=   frmENServicesObjectFilterEdit.edtContragentName.Text;

      argNames[4] := 'ContragentOkpo';
      args[4] := frmENServicesObjectFilterEdit.edtContragentOkpo.Text;

      argNames[5] := 'ENDepartmentDepartment';
      if ENServicesObjectFilterObj.department <> nil then
      args[5] :=   IntToStr(ENServicesObjectFilterObj.department.code)
      else
      args[5] := '0';

      argNames[6] := 'ContragentAddress';
      args[6] := ENServicesObjectFilterObj.contragentAddress;

      argNames[7] := 'ContractNumber';
      args[7] := ENServicesObjectFilterObj.contractNumber;

      argNames[8] := 'ContractDateFrom';
      if frmENServicesObjectFilterEdit.edtContractDateFrom.Checked then
        args[8] := DateToStr(frmENServicesObjectFilterEdit.edtContractDateFrom.Date)
      else
        args[8] := '';

      argNames[9] := 'ContractDateTo';
      if frmENServicesObjectFilterEdit.edtContractDateTo.Checked then
        args[9] := DateToStr(frmENServicesObjectFilterEdit.edtContractDateTo.Date)
      else
        args[9] := '';

      argNames[10] := 'PartnerCode';
      args[10] := ENServicesObjectFilterObj.partnerCode;

      argNames[11] := 'FinDocCode';
      args[11] := ENServicesObjectFilterObj.finDocCode;

      argNames[12] := 'edtCommentGen';
      args[12] := ENServicesObjectFilterObj.commentGen;

      argNames[13] := 'finWorkType';
      args[13] := IntToStr(frmENServicesObjectFilterEdit.finWorkType);

      argNames[14] := 'showcalcul';
      if ((frmENServicesObjectFilterEdit.chkShowCalcul.Checked = True) and (frmENServicesObjectFilterEdit.chkShowCalcul.Visible = True)) then
         args[14] := '1'
      else
        args[14] := '0';

      makeReport('Services/ReportServicesBuhNewMetod/ReportServicesBuh', argNames, args, 'xls');

    end;
  finally
    frmENServicesObjectFilterEdit.Free;
    frmENServicesObjectFilterEdit := nil;
  end;
end;

procedure TfrmMain.ConnectionFactCostClick(Sender: TObject);
var
  frmReportConnectionFactCost: TfrmReportConnectionFactCost;
begin
   frmReportConnectionFactCost := TfrmReportConnectionFactCost.Create(Application, dsInsert);
  try
    frmReportConnectionFactCost.ShowModal;
  finally
    frmReportConnectionFactCost.Free;
  end;
end;

procedure TfrmMain.CountersMonthClick(Sender: TObject);
begin
     frmReportCounterYear := TfrmReportCounterYear.Create(Application, dsInsert);
  try
     frmReportCounterYear.ShowModal;
  finally
    frmReportCounterYear.Free;
 end;
end;

procedure TfrmMain.miDFTaskSPAClick(Sender: TObject);
begin
  if not Assigned(frmDFTaskSPAManagement) then
    frmDFTaskSPAManagement := TfrmDFTaskSPAManagement.Create(Application, fmChild);
  frmDFTaskSPAManagement.Show;
end;

procedure TfrmMain.miReconstrModernizacOZClick(Sender: TObject);
begin
  if not Assigned(frmENReconstrModernOZShow) then
    frmENReconstrModernOZShow := TfrmENReconstrModernOZShow.Create(Application, fmChild);
    frmENReconstrModernOZShow.Show;
end;

procedure TfrmMain.miRegForSupplierClick(Sender: TObject);
begin
  if not Assigned(frmENRegForSupplierShow) then
    frmENRegForSupplierShow := TfrmENRegForSupplierShow.Create(Application, fmChild);
  frmENRegForSupplierShow.Show;
end;

procedure TfrmMain.miRegisterConsumers12NKREClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
begin
  frmreportAppealRegistrationByDate := TfrmreportAppealRegistrationByDate.Create(Application, dsInsert);
  frmreportAppealRegistrationByDate.chbByOperators.Visible := False;
  try
    if frmreportAppealRegistrationByDate.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 2);
      SetLength(args, 2);

      argNames[0] := 'dateStart';
      args[0] := DateToStr(frmreportAppealRegistrationByDate.edtDateStart.Date);

      argNames[1] := 'dateFinal';
      args[1] := DateToStr(frmreportAppealRegistrationByDate.edtDateFinal.Date);
      ///////

      DMReports.makeReport4DocFlow('registerConsumers12NKRE', argNames, args, 'xls');
    end;
  finally
    frmreportAppealRegistrationByDate.Free;
  end;
end;


procedure TfrmMain.miRegisterOrdersClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
begin
  frmReportRegisterOrders := TfrmReportRegisterOrders.Create(Application, dsInsert);
  try
    if Sender = miRegisterOrdersVacation then
      frmReportRegisterOrders.isVacationsReport := true;

    if frmReportRegisterOrders.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 6);
      SetLength(args, 6);

      argNames[0] := 'dateStart';
      args[0] := DateToStr(frmReportRegisterOrders.edtDateStart.Date);

      argNames[1] := 'dateFinal';
      args[1] := DateToStr(frmReportRegisterOrders.edtDateFinal.Date);

      argNames[2] := 'docSuffixCode';
      args[2] := IntToStr(frmReportRegisterOrders.docSuffixCode);

      argNames[3] := 'docTypeCode';
      args[3] := IntToStr(frmReportRegisterOrders.docTypeCode);

      argNames[4] := 'catalogCode';
      args[4] := IntToStr(frmReportRegisterOrders.catalogCode);

      argNames[5] := 'vacationsReport';
      if frmReportRegisterOrders.isVacationsReport then
        args[5] := '1'
      else
        args[5] := '0';
      ///////

      DMReports.makeReport4DocFlow('RegisterOrders/registerOrders', argNames, args, 'xls');
    end;
  finally
    frmReportRegisterOrders.Free;
  end;
end;


procedure TfrmMain.miReloadFinExecutorClick(Sender: TObject);
var
  TempFINExecutor: FINExecutorControllerSoapPort;
  FINExecutorList: FINExecutorShortList;
  exFilter: FINExecutorFilter;
begin
  TempFINExecutor := HTTPRIOFINExecutor as FINExecutorControllerSoapPort;

  exFilter := FINExecutorFilter.Create;
  SetNullIntProps(exFilter);
  SetNullXSProps(exFilter);
  exFilter.finCehCode := StrToInt('1'); // ХОЕ

  FINExecutorList := TempFINExecutor.getFINExecutorList(exFilter, 0, -1, True);
  Application.MessageBox(PChar('Довідник оновлено!'), PChar('Увага!'), MB_ICONINFORMATION);
end;

procedure TfrmMain.miRemainderClick(Sender: TObject);
begin
   frmReportUMCRest := TfrmReportUMCRest.Create(Application, dsInsert);
  try
    frmReportUMCRest.ShowModal;
  finally
    frmReportUMCRest.Free;
  end;
end;

procedure TfrmMain.miDrawItemWithRed(Sender: TObject; ACanvas: TCanvas; ARect: TRect; Selected: Boolean);
var
  LeftPos: Integer;
  TopPos: Integer;
  TextLength: Integer;
  Text: string;
begin
  Text := (Sender as TMenuItem).Caption;
  //ACanvas.Brush.Color := clGray;
  ACanvas.FillRect(ARect);
  ACanvas.Font.Color := $00342695; //clWhite;
  ACanvas.Font.Style := [fsBold];
  // Draw right in the middle of the menu
  TopPos := ARect.Top + (ARect.Bottom - ARect.Top - ACanvas.TextHeight('W')) div 2;
  TextLength := Length(Text);
//  if TextLength > (ARect.Right - ARect.Left) then
//    LeftPos := ARect.Left + 3
//  else
//    LeftPos := ARect.Left + (ARect.Right - ARect.Left -
//      ACanvas.TextWidth(Text)) div 2;

  LeftPos := ARect.Left + 30;
  ACanvas.TextOut(LeftPos, TopPos, Text);
end;

procedure TfrmMain.miRemainderDrawItem(Sender: TObject; ACanvas: TCanvas; ARect: TRect; Selected: Boolean);
var
  LeftPos: Integer;
  TopPos: Integer;
  TextLength: Integer;
  Text: string;
begin
  Text := (Sender as TMenuItem).Caption;
  //ACanvas.Brush.Color := clGray;
  ACanvas.FillRect(ARect);
  ACanvas.Font.Color := $00342695; //clWhite;
  ACanvas.Font.Style := [fsBold];
  // Draw right in the middle of the menu
  TopPos := ARect.Top + (ARect.Bottom - ARect.Top - ACanvas.TextHeight('W')) div 2;
  TextLength := Length(Text);
//  if TextLength > (ARect.Right - ARect.Left) then
//    LeftPos := ARect.Left + 3
//  else
//    LeftPos := ARect.Left + (ARect.Right - ARect.Left -
//      ACanvas.TextWidth(Text)) div 2;

  LeftPos := ARect.Left + 30;
  ACanvas.TextOut(LeftPos, TopPos, Text);
end;


procedure TfrmMain.mifsGSMClick(Sender: TObject);
begin
  frmFactStateGSM := TfrmFactStateGSM.Create(Application, dsInsert);
  try
    frmFactStateGSM.ShowModal;
  finally
    frmFactStateGSM.Free;
  end;
end;


procedure TfrmMain.miReportCostWorkClick(Sender: TObject);
begin
   frmReportCostWork := TfrmReportCostWork.Create(Application, dsInsert);
 try
   frmReportCostWork.ShowModal;
 finally
   frmReportCostWork.Free;
 end;
end;

procedure TfrmMain.miReportDodatok3YearSumClick(Sender: TObject);
begin
   FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try
   FrmReportDodatok3.Label1.Visible := False;
   FrmReportDodatok3.Label2.Visible := False;
   FrmReportDodatok3.edtDateStart.Visible := False;
   FrmReportDodatok3.edtDateFinal.Visible := False;
   FrmReportDodatok3.lblYearGen.Visible := True;
   FrmReportDodatok3.edtYearGen.Visible := True;
    FrmReportDodatok3.report_vid := 16; // Звіт по прогнозованому завантаженю технічного персоналу структуних підрозділів по річним планам на рік" з розбивкою по місяцям, бригадам, структурним підрозділам
    FrmReportDodatok3.pplankind := 1; // годовые планы
   FrmReportDodatok3.edtEPRenName.Visible := False;
   FrmReportDodatok3.spbEPRen.Visible := False;
   FrmReportDodatok3.spbEPRenClear.Visible := False;
   FrmReportDodatok3.lblEPRenName.Visible := False;

   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;
end;

procedure TfrmMain.miReportDodatok3YearSumZbytClick(Sender: TObject);
begin
   FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try
   FrmReportDodatok3.Label1.Visible := False;
   FrmReportDodatok3.Label2.Visible := False;
   FrmReportDodatok3.edtDateStart.Visible := False;
   FrmReportDodatok3.edtDateFinal.Visible := False;
   FrmReportDodatok3.lblYearGen.Visible := True;
   FrmReportDodatok3.edtYearGen.Visible := True;
    FrmReportDodatok3.report_vid := 17; // Звіт по прогнозованому завантаженю збутового персоналу структуних підрозділів по річним планам на рік" з розбивкою по місяцям, бригадам, структурним підрозділам
    FrmReportDodatok3.pplankind := 1; // годовые планы
   FrmReportDodatok3.edtEPRenName.Visible := False;
   FrmReportDodatok3.spbEPRen.Visible := False;
   FrmReportDodatok3.spbEPRenClear.Visible := False;
   FrmReportDodatok3.lblEPRenName.Visible := False;

   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;
end;

procedure TfrmMain.mireportExecPriconectionContractClick(Sender: TObject);
begin
  inherited;

   frmReportExecPriconectionContracts := TfrmReportExecPriconectionContracts.Create(Application, dsInsert);
 try
   frmReportExecPriconectionContracts.ShowModal;
 finally
   frmReportExecPriconectionContracts.Free;

 end;
end;

procedure TfrmMain.miReportLastBuyMaterialClick(Sender: TObject);
begin
  frmReportLastBuyMaterials := TfrmReportLastBuyMaterials.Create(Application, dsInsert);
  frmReportLastBuyMaterials.report_var := 1;
  try
    frmReportLastBuyMaterials.ShowModal;
  finally
    frmReportLastBuyMaterials.Free;
  end;

end;

procedure TfrmMain.miReportOborotMaterialsByPurchaseClick(Sender: TObject);
begin
		frmReportOborotMaterials := TfrmReportOborotMaterials.Create(Application, dsInsert);
    frmReportOborotMaterials.byPurchaseDate := true;
  try
    frmReportOborotMaterials.ShowModal;
  finally
    frmReportOborotMaterials.Free;
 end;
end;

procedure TfrmMain.mireportPlanworkItemGraphClick(Sender: TObject);
{var
   frmReportPlanworkItemGraph : TfrmReportPlanworkItemGraph;}
begin
   frmReportPlanworkItemGraph := TfrmReportPlanworkItemGraph.Create(Application, dsInsert);
  try
    frmReportPlanworkItemGraph.ShowModal;
  finally
    frmReportPlanworkItemGraph.Free;
  end;
end;

procedure TfrmMain.miReportServicesFactPaymentsClick(Sender: TObject);
begin
   frmReportServicesFactPayments := TfrmReportServicesFactPayments.Create(Application, dsInsert);
  try
    frmReportServicesFactPayments.ShowModal;
  finally
    frmReportServicesFactPayments.Free;
  end;
end;

procedure TfrmMain.mireportServicesLicenzClick(Sender: TObject);
begin
   frmServicesRegistryPrint2 := TfrmServicesRegistryPrint2.Create(Application, dsInsert);
 try
   frmServicesRegistryPrint2.ShowModal;
 finally
   frmServicesRegistryPrint2.Free;
   frmServicesRegistryPrint2 := nil;
 end;
end;

procedure TfrmMain.miENStandardConstClick(Sender: TObject);
begin
  if not Assigned(frmENStandardConstShow) then
    frmENStandardConstShow := TfrmENStandardConstShow.Create(Application, fmChild);
  frmENStandardConstShow.Show;
end;

procedure TfrmMain.miPlanFactUseGSMClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin
 {frmPlanFactUseGSM := TfrmPlanFactUseGSM.Create(Application, dsInsert);

  try
     if frmPlanFactUseGSM.ShowModal = mrOk then
    begin

      SetLength(argNames, 4);
      SetLength(args, 4);

      argNames[0] := 'dateStart';
      args[0] := DateToStr( frmPlanFactUseGSM.edtDateStart.date );

      argNames[1] := 'dateFinal';
      args[1] := DateToStr( frmPlanFactUseGSM.edtDateFinal.date );

      argNames[2] := 'actstatus';
      if  frmPlanFactUseGSM.chkActStatus.Checked then
      args[2] := '1'
      else
      args[2] := '2';

      argNames[3] := 'plankindcode';
      args[3] :=  IntToStr(frmPlanFactUseGSM.rgPlanKind.ItemIndex + 1);

      if frmPlanFactUseGSM.chkshowManagementZbyt.Checked then
      reportName := 'Auto/pmmByPodrManagement/pmmByPodr'
      else  if   frmPlanFactUseGSM.chbObjects.Checked then
      reportName := 'Auto/pmmByPodrObjects/pmmByPodr'
      else
      reportName := 'Auto/pmmByPodr/pmmByPodr';
      makeReport(reportName, argNames, args, 'xls');


     end;


  finally
    frmPlanFactUseGSM.Free;
  end;}
   frmReportPmmNeeded := TfrmReportPmmNeeded.Create(Application, dsInsert);
  try
    frmReportPmmNeeded.ShowModal;
  finally
    frmReportPmmNeeded.Free;
  end;
end;

procedure TfrmMain.miRQStorageClick(Sender: TObject);
begin
  if not Assigned(frmRQStorageShow) then
    frmRQStorageShow := TfrmRQStorageShow.Create(Application, fmChild);
    frmRQStorageShow.Show;
end;

procedure TfrmMain.miRQStorageZoneClick(Sender: TObject);
begin
  if not Assigned(frmRQStorageZoneShow) then
    frmRQStorageZoneShow := TfrmRQStorageZoneShow.Create(Application, fmChild);
    frmRQStorageZoneShow.Show;
end;

procedure TfrmMain.miRestMaterialByPlacesClick(Sender: TObject);
begin
   frmRepRestByPlaces := TfrmRepRestByPlaces.Create(Application, dsInsert);
  try
    frmRepRestByPlaces.ShowModal;
  finally
    frmRepRestByPlaces.Free;
  end;
end;

procedure TfrmMain.N121miAddition4Transport2Click(Sender: TObject);
begin
  frmReportDodatok4 := TFrmReportDodatok4.Create(Application, dsInsert);
 try
   frmReportDodatok4.reportType := 4;
   frmReportDodatok4.ShowModal;
 finally
   frmReportDodatok4.Free;
 end;
end;

procedure TfrmMain.N122ReportTermsProductSalesBuffetClick(Sender: TObject);
begin

  frmReportOutRealiztn := TfrmReportOutRealiztn.Create(Application, dsInsert);
 try
   frmReportOutRealiztn.ShowModal;
 finally
   frmReportOutRealiztn.Free;
 end;

end;

procedure TfrmMain.N12Click(Sender: TObject);
begin
   frmEPRunReport:=TfrmEPRunReport.Create(Application, dsEdit);
  try
    frmEPRunReport.reportName:=TMenuItem(Sender).Caption;
    frmEPRunReport.ShowModal;
  finally
    frmEPRunReport.Free;
    frmEPRunReport:=nil;
  end;
end;

procedure TfrmMain.N12DebtReportMore90DaysClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
  printDetail: Integer;
begin

  frmDebtBytParam := TfrmDebtBytParam.Create(Application, dsInsert);
  frmDebtBytParam.Label1.visible := False;
  frmDebtBytParam.dtpDate.visible := False;

  frmDebtBytParam.GroupBox1.visible := True;

  try
    if frmDebtBytParam.ShowModal = mrOk then
    begin

      if frmDebtBytParam.edtEPRenName.Text = '' then
        frmDebtBytParam.renCode := -1;

      if frmDebtBytParam.cbItog.Checked then
        printDetail := 0
      else
        printDetail := 1;
       /////// Parameters
      SetLength(argNames, 4);
      SetLength(args, 4);

      argNames[0] := 'dateStart';
      args[0] := DateToStr(Date());

      argNames[1] := 'printDetail';
      args[1] := IntToStr(printDetail);

      argNames[2] := 'renCode';
      args[2] := IntToStr(frmDebtBytParam.renCode);

      argNames[3] := 'sumdebt';
      args[3] := frmDebtBytParam.edtSum.Text;

      reportName := 'RepEnergozbyt/Debt/group_main';
      makeReport(reportName, argNames, args, 'xls');
   end;
  finally
    frmDebtBytParam.Free;
  end;

end;

procedure TfrmMain.mniENWiresTypeClick(Sender: TObject);
begin
  if not Assigned(frmENWiresTypeShow) then
    frmENWiresTypeShow := TfrmENWiresTypeShow.Create(Application, fmChild);
  frmENWiresTypeShow.Show;
end;

procedure TfrmMain.mniFINAccountTypeClick(Sender: TObject);
begin
  if not Assigned(frmFINAccountTypeShow) then
    frmFINAccountTypeShow := TfrmFINAccountTypeShow.Create(Application, fmChild);
  frmFINAccountTypeShow.Show;
end;

procedure TfrmMain.mniFINCurrencyClick(Sender: TObject);
begin
  if not Assigned(frmFINCurrencyShow) then
    frmFINCurrencyShow := TfrmFINCurrencyShow.Create(Application, fmChild);
  frmFINCurrencyShow.Show;
end;

procedure TfrmMain.N135AverageShutdownDurationClick(Sender: TObject);
begin
  ReportCallCenterByDates := TReportCallCenterByDates.Create(Application, dsInsert);
 try
   ReportCallCenterByDates.ShowModal;
 finally
   ReportCallCenterByDates.Free;
 end;
end;

procedure TfrmMain.mniENWiresCutClick(Sender: TObject);
begin //Сечения проводов
  if not Assigned(frmENWiresCutShow) then
    frmENWiresCutShow := TfrmENWiresCutShow.Create(Application, fmChild);
  frmENWiresCutShow.Show;
end;

procedure TfrmMain.N142StagesFinancingInvestProgramsClick(Sender: TObject);
begin
  frmServicesPlanedPayAndWorks := TfrmServicesPlanedPayAndWorks.Create(Application, dsInsert);
  frmServicesPlanedPayAndWorks.Caption := 'Етапи фінансування Інвестпрограми';
  frmServicesPlanedPayAndWorks.kindReport := 4;
  try
    frmServicesPlanedPayAndWorks.ShowModal;
  finally
    frmServicesPlanedPayAndWorks.Free;
  end;
end;

procedure TfrmMain.N143TasksPerformingWorksClick(Sender: TObject);
begin
   frmReportDamageT32 := TfrmReportDamageT32.Create(Application, dsInsert);
  try
    frmReportDamageT32.ShowModal;
  finally
    frmReportDamageT32.Free;
  end;
end;

procedure TfrmMain.miCoefficientProductionWorkClick(Sender: TObject);
begin
  FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try
    FrmReportDodatok3.report_vid := 26; // вид отчета - загрузка фактического персонала в бригаде
    FrmReportDodatok3.pplankind := 4; // факт
   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;
end;

procedure TfrmMain.miCoefficientProductionWork_persClick(Sender: TObject);
begin
  FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try
    FrmReportDodatok3.report_vid := 27; // вид отчета - загрузка фактического персонала в бригаде с разбивкой по персоналу
    FrmReportDodatok3.pplankind := 4; // факт
    FrmReportDodatok3.ShowModal;
 finally
    FrmReportDodatok3.Free;
 end;
end;

procedure TfrmMain.miCoefficientQualityStandardsSpravClick(Sender: TObject);
begin
   if not Assigned(frmENCoefficientQualityStandardsShow) then
    frmENCoefficientQualityStandardsShow := TfrmENCoefficientQualityStandardsShow.Create(Application, fmChild);
  frmENCoefficientQualityStandardsShow.Show;

end;

procedure TfrmMain.N148InformationMetersFromBillingClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin
    SetLength(argNames, 1);
    SetLength(args, 1);

    argNames[0] := 'emptyParam';
    args[0] :=  '';
    reportName := 'RepEnergozbyt/friday_counters_main';

    makeReport(reportName, argNames, args, 'xls');
end;

procedure TfrmMain.N149DebitDebtInformationBilitsClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin
    SetLength(argNames, 1);
    SetLength(args, 1);

    argNames[0] := 'emptyParam';
    args[0] :=  '';
    reportName := 'RepEnergozbyt/friday_debetors_main';

    makeReport(reportName, argNames, args, 'xls');
end;

procedure TfrmMain.N150InformationControllersBillingClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin
    SetLength(argNames, 1);
    SetLength(args, 1);

    argNames[0] := 'emptyParam';
    args[0] :=  '';
    reportName := 'RepEnergozbyt/friday_workorder_premija_main';

    makeReport(reportName, argNames, args, 'xls');
end;

procedure TfrmMain.N151NumberLunarPlansWithObjectsElectricNetworksClick(Sender: TObject);
begin
  FrmPlanWorkCount := TfrmPlanWorkCount.Create(Application, dsInsert);
 try
   FrmPlanWorkCount.ShowModal;
 finally
   FrmPlanWorkCount.Free;
 end;
end;

procedure TfrmMain.N152GraphPlannedPayPMMClick(Sender: TObject);
begin
    if not Assigned(frmENPlanGraphFinancingFuelShow) then
    frmENPlanGraphFinancingFuelShow := TfrmENPlanGraphFinancingFuelShow.Create(Application, fmChild);
    frmENPlanGraphFinancingFuelShow.Show;
end;

procedure TfrmMain.N157ABBNeedforPurchasesClick(Sender: TObject);
begin
  frmReportAvzRestNormative := TfrmReportAvzRestNormative.Create(Application, dsInsert);
  try
    frmReportAvzRestNormative.ShowModal;
  finally
    frmReportAvzRestNormative.Free;
  end;
end;

procedure TfrmMain.N157ABBNeedforPurchasesDrawItem(Sender: TObject; ACanvas: TCanvas; ARect: TRect; Selected: Boolean);
var
  LeftPos: Integer;
  TopPos: Integer;
  TextLength: Integer;
  Text: string;
begin
  Text := (Sender as TMenuItem).Caption;
  ACanvas.FillRect(ARect);
  ACanvas.Font.Color := $00342695; //clWhite;
  ACanvas.Font.Style := [fsBold];
  TopPos := ARect.Top + (ARect.Bottom - ARect.Top - ACanvas.TextHeight('W')) div 2;
  TextLength := Length(Text);

  LeftPos := ARect.Left + 10;
  ACanvas.TextOut(LeftPos, TopPos, Text);
end;

procedure TfrmMain.miReportPlanWorkItemsRegistryClick(Sender: TObject);
begin
  frmReportPlanWorkItemsRegistry := TfrmReportPlanWorkItemsRegistry.Create(Application, dsInsert);
  try
    frmReportPlanWorkItemsRegistry.ShowModal;
  finally
    frmReportPlanWorkItemsRegistry.Free;
  end;
end;

procedure TfrmMain.miReportPmmNeededClick(Sender: TObject);
begin
   frmReportPmmNeeded := TfrmReportPmmNeeded.Create(Application, dsInsert);
  try
    frmReportPmmNeeded.ShowModal;
  finally
    frmReportPmmNeeded.Free;
  end;
end;

procedure TfrmMain.miReportPremSumNKahClick(Sender: TObject);
begin
   	 FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try
   FrmReportDodatok3.Caption := 'Процент премії по персоналу по шкалі навантаження Н.Каховський РЕЗ і ЕМ';
    FrmReportDodatok3.report_vid := 25; // Вид отчета - процент премии технарям . по шкале нагрузки
    FrmReportDodatok3.pplankind := 4; // Фактичны плани
   FrmReportDodatok3.chkOnlyZatvAct.Visible := False; // формировать отчет Семенцова сказала по утвержд и не утвержд фактам
   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;
end;

procedure TfrmMain.miServicesPlanedPayAndWorksClick(Sender: TObject);
begin
  frmServicesPlanedPayAndWorks := TfrmServicesPlanedPayAndWorks.Create(Application, dsInsert);
  try
    frmServicesPlanedPayAndWorks.ShowModal;
  finally
    frmServicesPlanedPayAndWorks.Free;
  end;
end;

procedure TfrmMain.miServicesQualityGeneralClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
begin
  frmreportAppealRegistrationByRensAndDate := TfrmreportAppealRegistrationByRensAndDate.Create(Application, dsInsert);
  frmreportAppealRegistrationByRensAndDate.Caption := 'Період для формування звіту';
  try
    if frmreportAppealRegistrationByRensAndDate.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 2);
      SetLength(args, 2);

      argNames[0] := 'datestart';
      args[0] := DateToStr(frmreportAppealRegistrationByRensAndDate.edtDateStart.Date);

      argNames[1] := 'datefinal';
      args[1] := DateToStr(frmreportAppealRegistrationByRensAndDate.edtDateFinal.Date);
      ///////

      DMReports.makeReport4DocFlow('servicesQuality/servicesQuality_v2', argNames, args, 'xls');
    end;
  finally
    frmreportAppealRegistrationByRensAndDate.Free;
  end;
end;

procedure TfrmMain.miServicesQualityNoVoltageClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
begin
  frmreportAppealRegistrationByDate := TfrmreportAppealRegistrationByDate.Create(Application, dsInsert);
  frmreportAppealRegistrationByDate.Caption := 'Період для формування звіту';
  frmreportAppealRegistrationByDate.chbByOperators.Visible := False;
  frmreportAppealRegistrationByDate.viewDepartment := True;

  try
    if frmreportAppealRegistrationByDate.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 3);
      SetLength(args, 3);

      argNames[0] := 'datestart';
      args[0] := DateToStr(frmreportAppealRegistrationByDate.edtDateStart.Date);

      argNames[1] := 'datefinal';
      args[1] := DateToStr(frmreportAppealRegistrationByDate.edtDateFinal.Date);

      argNames[2] := 'eprencode';
      args[2] := IntToStr(frmreportAppealRegistrationByDate.epRenCode);
      ///////

      DMReports.makeReport4DocFlow('servicesQuality/servicesQualityNoVoltage_main', argNames, args, 'xls');
    end;
  finally
    frmreportAppealRegistrationByDate.Free;
  end;
end;

procedure TfrmMain.miServicesQualityOutTermClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
begin
  frmreportAppealRegistrationByDate := TfrmreportAppealRegistrationByDate.Create(Application, dsInsert);
  frmreportAppealRegistrationByDate.Caption := 'Період для формування звіту';
  frmreportAppealRegistrationByDate.chbByOperators.Visible := False;
  frmreportAppealRegistrationByDate.viewDepartment := True;

  try
    if frmreportAppealRegistrationByDate.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 3);
      SetLength(args, 3);

      argNames[0] := 'datestart';
      args[0] := DateToStr(frmreportAppealRegistrationByDate.edtDateStart.Date);

      argNames[1] := 'datefinal';
      args[1] := DateToStr(frmreportAppealRegistrationByDate.edtDateFinal.Date);

      argNames[2] := 'departmentCode';
      args[2] := IntToStr(frmreportAppealRegistrationByDate.departmentCode);
      ///////

      DMReports.makeReport4DocFlow('servicesQuality/servicesQualityOutTerm', argNames, args, 'xls');
    end;
  finally
    frmreportAppealRegistrationByDate.Free;
  end;
end;

procedure TfrmMain.miServicesRegistryClick(Sender: TObject);
begin
  if not Assigned(frmDFServicesRegistryShow) then
    frmDFServicesRegistryShow := TfrmDFServicesRegistryShow.Create(Application, fmChild);
    frmDFServicesRegistryShow.Show;
end;

procedure TfrmMain.miN4dodat4Click(Sender: TObject);
begin
  frmDodatok4Invest := TfrmDodatok4Invest.Create(Application, dsInsert);
 try
   frmDodatok4Invest.ShowModal;
 finally
   frmDodatok4Invest.Free;
 end;
end;

procedure TfrmMain.miPaymentContractsClick(Sender: TObject);
begin
   frmPaymentByContracts := TfrmPaymentByContracts.Create(Application, dsInsert);
 try
   frmPaymentByContracts.ShowModal;
 finally
   frmPaymentByContracts.Free;
 end;
end;

procedure TfrmMain.miAverageCountPersonalClick(Sender: TObject);
begin
   frmAverageCountPersonal := TfrmAverageCountPersonal.Create(Application, dsInsert);
 try
   frmAverageCountPersonal.ShowModal;
 finally
   frmAverageCountPersonal.Free;
 end;
end;

procedure TfrmMain.miPlanLineRouteForestClearClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
begin
	frmENPeriod := TfrmENPeriod.Create(Application, dsInsert);
	with frmENPeriod do
    try
			lblEPRenName.Visible := False;
			edtEPRenName.Visible := False;
			spbEPRen.Visible := False;
			spbEPRenClear.Visible := False;
      lblENBudgetName.Visible := False;
      edtENBudgetName.Visible := False;
      spbENBudget.Visible := False;
      spbENBudgetClear.Visible := False;
      lblENElementName.Visible := False;
      edtENElementName.Visible := False;
      spbENElement.Visible := False;
      spbENElementClear.Visible := False;
			GroupBox1.Visible := False;
      //GroupBox2.Visible := False;
      lblMonthGen.Visible := False;
      edtMonthGen.Visible := False;
			chbYearPlansOnly.Visible := False;
      lblBelonging.Visible := True;
      cbBelongingType.Visible := True;
       lblElementType.Visible := True;
      cbElementType.Visible := True;
      chkisPlanFact.Visible := True;

			lblren.Visible := True;
      edtren.Visible := True;
			spbren.visible := True;

			lblYearGen.Top := 10;
			lblYearGen.Left := 10;

			edtYearGen.Top := lblYearGen.Top;
			edtYearGen.Left := lblYearGen.Left + lblYearGen.Width + 10;

      lblren.Top := lblYearGen.Top + 30;
			lblren.Left := lblYearGen.Left;

			edtren.Top := lblren.Top;
			edtren.Left := lblren.Left + lblren.Width + 10;

			spbren.Top :=  lblren.Top;
			spbren.Left := lblren.Left + lblren.Width  +   edtren.Width + 10;

      lblBelonging.Top := spbren.Top + spbren.Height + 20;
      lblBelonging.Left := 10;

      cbBelongingType.Top := lblBelonging.Top;
      cbBelongingType.Left := lblBelonging.Left + lblBelonging.Width + 10;

      lblElementType.Top := cbBelongingType.Top + cbBelongingType.Height + 20;
      lblElementType.Left := 10;

      cbElementType.Top := lblElementType.Top;
      cbElementType.Left := lblElementType.Left + lblElementType.Width + 10;

      chkisPlanFact.Top := cbElementType.Top + cbElementType.Height + 20;
      chkisPlanFact.Left := 10;

      GroupBox2.Top := chkisPlanFact.Top + chkisPlanFact.Height + 20;
      GroupBox2.Left := 10;
      ///// zzzzz
      HideControls([edtGeograph, lblGeograph , btnGeograph , btnGeographClear] , false);
      lblGeograph.Top := GroupBox2.Top + GroupBox2.Height + 20;
			lblGeograph.Left := GroupBox2.Left;

			edtGeograph.Top := lblGeograph.Top;
			edtGeograph.Left := lblGeograph.Left + lblGeograph.Width + 10;


      edtGeograph.Top := GroupBox2.Top + GroupBox2.Height + 20;
      btnGeograph.Top := GroupBox2.Top + GroupBox2.Height + 20;
      btnGeographClear.Top := GroupBox2.Top + GroupBox2.Height + 20;

      rbNpz.Visible := false;
      rbFakt.Visible := fALSE;
      GroupBox2.Width := 150;
      rbTekush.Checked := True;

			btnOk.Top := edtGeograph.Top + edtGeograph.Height + 20;
			btnOk.Left := edtGeograph.Left + edtGeograph.Width + 10;

			btnCancel.Top := btnOk.Top;
			btnCancel.Left := btnOk.Left + btnOk.Width + 10;

      Height := btnCancel.Top +  btnCancel.Height + 50 ;
      Width := btnCancel.Left + btnCancel.Width + 230;

			if ShowModal = mrOk then
				begin
					SetLength(argNames, 8);
					SetLength(args, 8);
					argNames[0] := 'yeargen';
					args[0] := edtYearGen.Text;
					argNames[1] := 'rencode';
					if renCode2 = -1 then
					 args[1] := ' ren.code '
					else
        args[1] := IntToStr(renCode2);

					argNames[2] := 'renname';
      if ((renCode2 = -1) or (renCode2 = 0)) then
					 args[2] := ' ПАТ ''ЕК ''ХерсонОблEнерго'' '
					else
					 args[2] := renName2;

           argNames[3] := 'BelongingCode';
           args[3] := IntToStr(cbBelongingType.ItemIndex);

            argNames[4] := 'enelementtype';
            if cbElementType.ItemIndex <> -1 then
            args[4] := IntToStr(Integer(cbElementTYpe.Items.Objects[cbElementType.ItemIndex]))
            else
            args[4] := '-1';

            argNames[5] := 'planKindCode';
             if rbYear.Checked then
             args[5] := '1'
             else if rbTekush.Checked then
             args[5] := '2';

             argNames[6] := 'geoDeptCode';
             args[6] := IntToStr(geoDeptCode);

             argNames[7] := 'geoDeptName';
             args[7] :=  geoDeptName;

          if chkisPlanFact.Checked = True then
          makeReport('LineRouteForestClearPlanFact', argNames, args, 'xls')
          else
          makeReport('LineRouteForestClear2', argNames, args, 'xls');
        end;
    finally
      Free;
      frmENPeriod := nil;
    end;
end;

procedure TfrmMain.miPlanLineRouteForestClear_2Click(Sender: TObject);
var
  argNames, args: ArrayOfString;
begin
	frmENPeriod := TfrmENPeriod.Create(Application, dsInsert);
	with frmENPeriod do
    try
			lblEPRenName.Visible := False;
			edtEPRenName.Visible := False;
			spbEPRen.Visible := False;
			spbEPRenClear.Visible := False;
      lblENBudgetName.Visible := False;
      edtENBudgetName.Visible := False;
      spbENBudget.Visible := False;
      spbENBudgetClear.Visible := False;
      lblENElementName.Visible := False;
      edtENElementName.Visible := False;
      spbENElement.Visible := False;
      spbENElementClear.Visible := False;
			GroupBox1.Visible := False;
      //GroupBox2.Visible := False;
      lblMonthGen.Visible := False;
      edtMonthGen.Visible := False;
			chbYearPlansOnly.Visible := False;
      lblBelonging.Visible := True;
      cbBelongingType.Visible := True;
      lblElementType.Visible := True;
      cbElementType.Visible := True;
      chkisPlanFact.Visible := True;
      chk010111.Visible := True;

			lblren.Visible := True;
      edtren.Visible := True;
			spbren.visible := True;

			lblYearGen.Top := 10;
			lblYearGen.Left := 10;

			edtYearGen.Top := lblYearGen.Top;
			edtYearGen.Left := lblYearGen.Left + lblYearGen.Width + 10;

      lblren.Top := lblYearGen.Top + 30;
			lblren.Left := lblYearGen.Left;

			edtren.Top := lblren.Top;
			edtren.Left := lblren.Left + lblren.Width + 10;

			spbren.Top :=  lblren.Top;
			spbren.Left := lblren.Left + lblren.Width  +   edtren.Width + 10;

      lblBelonging.Top := spbren.Top + spbren.Height + 20;
      lblBelonging.Left := 10;

      cbBelongingType.Top := lblBelonging.Top;
      cbBelongingType.Left := lblBelonging.Left + lblBelonging.Width + 10;

      lblElementType.Top := cbBelongingType.Top + cbBelongingType.Height + 20;
      lblElementType.Left := 10;

      cbElementType.Top := lblElementType.Top;
      cbElementType.Left := lblElementType.Left + lblElementType.Width + 10;

      chkisPlanFact.Top := cbElementType.Top + cbElementType.Height + 20;
      chkisPlanFact.Left := 10;

     GroupBox2.Top := chkisPlanFact.Top + chkisPlanFact.Height + 20;
     GroupBox2.Left := 10;
     chk010111.Top := GroupBox2.Top;

     ///// zzzzz
      HideControls([edtGeograph, lblGeograph , btnGeograph , btnGeographClear] , false);
      lblGeograph.Top := GroupBox2.Top + GroupBox2.Height + 20;
			lblGeograph.Left := GroupBox2.Left;

			edtGeograph.Top := lblGeograph.Top;
			edtGeograph.Left := lblGeograph.Left + lblGeograph.Width + 10;


      edtGeograph.Top := GroupBox2.Top + GroupBox2.Height + 20;
      btnGeograph.Top := GroupBox2.Top + GroupBox2.Height + 20;
      btnGeographClear.Top := GroupBox2.Top + GroupBox2.Height + 20;

      rbNpz.Visible := false;
      rbFakt.Visible := fALSE;
      GroupBox2.Width := 150;
      rbTekush.Checked := True;

      btnOk.Top := edtGeograph.Top + edtGeograph.Height + 20;
			btnOk.Left := edtGeograph.Left + edtGeograph.Width + 10;

      btnCancel.Top := btnOk.Top;
			btnCancel.Left := btnOk.Left + btnOk.Width + 10;

      Height := btnCancel.Top +  btnCancel.Height + 50 ;
      Width := btnCancel.Left + btnCancel.Width + 230;

      chkisPlanFact.Checked := False;
      chkisPlanFact.Visible := False;

			if ShowModal = mrOk then
				begin
					SetLength(argNames, 9);
					SetLength(args, 9);
					argNames[0] := 'yeargen';
					args[0] := edtYearGen.Text;
					argNames[1] := 'rencode';
					if renCode2 = -1 then
					 args[1] := ' el.renrefcode '
					else
        args[1] := IntToStr(renCode2);

					argNames[2] := 'renname';
      if ((renCode2 = -1) or (renCode2 = 0)) then
					 args[2] := ' ПАТ ''ЕК ''ХерсонОблEнерго'' '
					else
					 args[2] := renName2;

           argNames[3] := 'BelongingCode';
           args[3] := IntToStr(cbBelongingType.ItemIndex);

            argNames[4] := 'enelementtype';
            if cbElementType.ItemIndex <> -1 then
        args[4] := IntToStr(Integer(cbElementTYpe.Items.Objects[cbElementType.ItemIndex]))
            else
        args[4] := '-1';

            argNames[5] := 'planKindCode';
             if rbYear.Checked then
             args[5] := '1'
             else if rbTekush.Checked then
             args[5] := '2';

             argNames[6] := 'parameter010111';
             args[6] := BoolToStr(chk010111.Checked, True);

             argNames[7] := 'geoDeptCode';
             args[7] := IntToStr(geoDeptCode);

             argNames[8] := 'geoDeptName';
             args[8] :=  geoDeptName;
          makeReport('LineRouteForestClear2_2', argNames, args, 'xls');
        end;
    finally
      Free;
      frmENPeriod := nil;
    end;
end;

procedure TfrmMain.miFiderGuageFullnessClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
begin
  SetLength(argNames, 1);
  SetLength(args, 1);
  argNames[0] := 'IS_IGNORE_PARGINATION';
  args[0] := 'Yes';

  makeReport('Passport/S04_TransformerFiderGauge/FiderGuageFullness', argNames, args, 'xls');
end;

procedure TfrmMain.miFinContractsClick(Sender: TObject);
var
  f: ENServicesObjectFilter;
begin
{
  if not Assigned(frmFINServicesObjectShow) then
    frmFINServicesObjectShow := TfrmFINServicesObjectShow.Create(Application, fmChild);
  frmFINServicesObjectShow.Show;
}
  if not Assigned(frmFINServicesObjectShow) then
  begin
    f := ENServicesObjectFilter.Create();
    SetNullXSProps(f);
    SetNullIntProps(f);

    f.conditionSQL := 'a.id = -1';

    frmFINServicesObjectShow := TfrmFINServicesObjectShow.Create(Application, fmChild, f);
  end;

  frmFINServicesObjectShow.Show;
end;

procedure TfrmMain.mniWarehouseMaterialsMovementClick(Sender: TObject);
//var frmRQWarehouseMaterialsMovement : TfrmRQWarehouseMaterialsMovement;
begin
  if not Assigned(frmRQWarehouseMaterialsMovement) then
    frmRQWarehouseMaterialsMovement := TfrmRQWarehouseMaterialsMovement.Create(Application, fmChild);
    frmRQWarehouseMaterialsMovement.Show;
end;

procedure TfrmMain.mniTechnicalConditionsPreparationHistogramManagementDepartmentClick(Sender: TObject);
begin
  makeReport('Histograms/TechnicalConditionsPreparation/TechnicalConditionsPreparation_wrapper', ArrayOfString.Create('isForManagementDepartment'), ArrayOfString.Create('true'), 'xls');
end;

procedure TfrmMain.mniTechnicalConditionsPreparationHistogramMinorDepartmentsClick(Sender: TObject);
begin
  makeReport('Histograms/TechnicalConditionsPreparation/TechnicalConditionsPreparation_wrapper', ArrayOfString.Create('isForManagementDepartment'), ArrayOfString.Create('false'), 'xls');
end;

procedure TfrmMain.mniTermsOfConnectionRealisationClick(Sender: TObject);
begin
  makeReport('Services/TermsOfConnectionRealisation', ArrayOfString.Create('dummy'), ArrayOfString.Create('dummy'), 'xls');
end;

procedure TfrmMain.mniTransportTemperatureClick(Sender: TObject);
begin
   if not Assigned(frmENTransportTemperatureShow) then
    frmENTransportTemperatureShow := TfrmENTransportTemperatureShow.Create(Application, fmChild);
    frmENTransportTemperatureShow.Show;
end;

procedure TfrmMain.mniUkrposhta_registersClick(Sender: TObject);
begin
  if not Assigned(frmDFLettersListsShow) then
    frmDFLettersListsShow := TfrmDFLettersListsShow.Create(Application, fmChild);
  frmDFLettersListsShow.Show;
end;

procedure TfrmMain.miSubstation04PowerReserveClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  str: string;
begin
  SetLength(argNames, 4);
  SetLength(args, 4);
  argNames[0] := 'gauge_date_start';
  argNames[1] := 'gauge_date';
  argNames[2] := 'rencode';
  argNames[3] := 'renname';

  frmReportPowerReserve := TfrmReportPowerReserve.Create(Application, dsEdit);

  frmReportPowerReserve.cbPeriod1.Checked := True;
  frmReportPowerReserve.cbPeriod2.Checked := True;

  try

    with frmReportPowerReserve do
    begin
      dtpDateFrom.DateTime := StrToDate('17.12.2014');
      dtpDateTo.DateTime := StrToDate('17.01.2015');

      dtpDateFrom2.DateTime := StrToDate('18.06.2015');
      dtpDateTo2.DateTime := StrToDate('18.07.2015');

      if ShowModal = mrOk then
        begin
          if (cbPeriod1.Checked) and (not cbPeriod2.Checked) then
          begin
            args[0] := DateToStr(dtpDateFrom.Date);
            args[1] := DateToStr(dtpDateTo.Date);
        end
        else if (not cbPeriod1.Checked) and (cbPeriod2.Checked) then
          begin
            args[0] := DateToStr(dtpDateFrom2.Date);
            args[1] := DateToStr(dtpDateTo2.Date);
        end
        else
          begin
            args[0] := DateToStr(dtpDateFrom.Date);
            args[1] := DateToStr(dtpDateTo2.Date);
          end;

          args[2] := IntToSTr(frmReportPowerReserve.rencode);
          args[3] := frmReportPowerReserve.renname;

          if chkTechTerms.Checked then
            str := //http://10.77.11.28:8080/browse/SUPP-26592
              'Passport/TpInfoAddition4/TpInfoResolution115WithCountTY/TpInfoAddition115_deployed'
          else
          str := 'Passport/TpInfoAddition4/TpInfoResolution115WithCountTY/TpInfoAddition115';

          if rbPDF.Checked then
            makeReport(str, argNames, args, 'pdf')
          else //if rbXLS.Checked then
            makeReport(str, argNames, args, 'xls');
        end;
    end;
  finally
    frmReportPowerReserve.Free;
    frmReportPowerReserve := nil;
  end;
end;

procedure TfrmMain.miSupplierEEContractsClick(Sender: TObject);
var
  f: ENServicesObjectFilter;
begin
  f := ENServicesObjectFilter.Create;
  SetNullIntProps(f);
  SetNullXSProps(f);

  f.contractTypeRef := ENServicesContractTypeRef.Create;
  f.contractTypeRef.code := ENSERVICESOBJECTTYPE_SHIFT_LINES;

  f.contractKindRef := ENServicesContractKindRef.Create;
  f.contractKindRef.code := SERVICES_CONTRACT_KIND_SUPPLIER;

  if not Assigned(frmENServicesForSupplierShow) then
    frmENServicesForSupplierShow := TfrmENServicesShiftShow.Create(Application, fmChild, f);
  frmENServicesForSupplierShow.Caption := TMenuItem(Sender).Caption;
  frmENServicesForSupplierShow.UpdateCaption;
  frmENServicesForSupplierShow.Show;
end;

procedure TfrmMain.miGeoCoordinatesClick(Sender: TObject);
begin
  frmGeoCoordinatesImportEdit := TfrmGeoCoordinatesImportEdit.Create(Application, dsInsert);
  try
    frmGeoCoordinatesImportEdit.ShowModal;
  finally
    frmGeoCoordinatesImportEdit.Free;
  end;
end;

procedure TfrmMain.miGPSTrackerClick(Sender: TObject);
begin
   if not Assigned(frmENGPSTrackerShow) then
    frmENGPSTrackerShow := TfrmENGPSTrackerShow.Create(Application, fmChild);
    frmENGPSTrackerShow.Show;
end;

procedure TfrmMain.miEnePlanZagruzkaAndCountClick(Sender: TObject);
begin
 frmEnePlanZagruzkaAndCountPers := TfrmEnePlanZagruzkaAndCountPers.Create(Application, dsInsert);
 try
    frmEnePlanZagruzkaAndCountPers.report_kind := 1; // енергосбыт
    frmEnePlanZagruzkaAndCountPers.ShowModal;
 finally
   frmEnePlanZagruzkaAndCountPers.Free;
 end;
end;

procedure TfrmMain.miFactZagrByBrigadeHMVEClick(Sender: TObject);
begin
  FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try
   FrmReportDodatok3.Label1.Visible := False;
   FrmReportDodatok3.Label2.Visible := False;
   FrmReportDodatok3.edtDateStart.Visible := False;
   FrmReportDodatok3.edtDateFinal.Visible := False;
   FrmReportDodatok3.lblYearGen.Visible := True;
   FrmReportDodatok3.edtYearGen.Visible := True;
    FrmReportDodatok3.report_vid := 14; // вид отчета фактическая загрузка по бригадам за год
    FrmReportDodatok3.pplankind := 4; //  планы факт
   FrmReportDodatok3.lblEPRenName.Visible := False;
   FrmReportDodatok3.edtEPRenName.Visible := False;
   FrmReportDodatok3.spbEPRen.Visible := False;
   FrmReportDodatok3.spbEPRenClear.Visible := False;
    FrmReportDodatok3.renCode := 482;
    FrmReportDodatok3.btnOk.Top := 100;
    FrmReportDodatok3.btnCancel.Top := 100;
   FrmReportDodatok3.Height := 200;

   FrmReportDodatok3.ShowModal;

 finally
   FrmReportDodatok3.Free;
 end;
end;

procedure TfrmMain.miIsPlansModeSelectClick(Sender: TObject);
begin
  miIsPlansModeSelect.Checked := not miIsPlansModeSelect.Checked;
  RegWriteBool(_INTERFACE_KEY, 'IsPlansModeSelect', miIsPlansModeSelect.Checked);
  // Перечитаем еще раз из реестра, чтобы быть уверенными, что записалось
  miIsPlansModeSelect.Checked := RegReadBool(_INTERFACE_KEY, 'IsPlansModeSelect');

  // 22.02.2016 В.С. Отключим выбор режима отображения планов
  miIsPlansModeSelect.Checked := false;

  if miIsPlansModeSelect.Checked then
  begin
    miENPlanWorks.Visible := false;
    miENPlanWorksNew.Visible := true;
  end
  else
  begin
    miENPlanWorks.Visible := true;
    miENPlanWorksNew.Visible := false;
  end;
end;

procedure TfrmMain.miContractRealizationStandartClick(Sender: TObject);
var
  f: ENTechConditionsServicesFilter;
begin

  f := ENTechConditionsServicesFilter.Create;
  SetNullIntProps(f);
  SetNullXSProps(f);

  f.techCondServicesType := ENTechConditionsServicesType.Create;
  f.techCondServicesType.code := ENTECHCONDITIONSSERVICES_TYPE_REALIZATION;

  f.connectionKindRef := ENConnectionKindRef.Create;
  f.connectionKindRef.code := TMenuItem(Sender).Tag;

  // 08.05.2013 +++ откинем новые договора на присоединение
  f.conditionSQL := ' code not in (select s2t.techcondservrefcode from enservicesobject2techcondtnsservices s2t)';
  f.orderBySQL := 'cast(contractnumber as integer) desc';

  if (f.connectionKindRef.code = ENCONNECTIONKIND_NO_STANDART) then
  begin
    if not Assigned(frmENTechConditionsRealizationNoStandartShow) then
      frmENTechConditionsRealizationNoStandartShow := TfrmENTechConditionsServicesShow.Create(Application, fmChild, f);
    frmENTechConditionsRealizationNoStandartShow.Caption := TMenuItem(Sender).Caption;
    frmENTechConditionsRealizationNoStandartShow.UpdateCaption;
    frmENTechConditionsRealizationNoStandartShow.Show;
  end
  else if (f.connectionKindRef.code = ENCONNECTIONKIND_STANDART) then
  begin
    if not Assigned(frmENTechConditionsRealizationStandartShow) then
      frmENTechConditionsRealizationStandartShow := TfrmENTechConditionsServicesShow.Create(Application, fmChild, f);
    frmENTechConditionsRealizationStandartShow.Caption := TMenuItem(Sender).Caption;
    frmENTechConditionsRealizationStandartShow.UpdateCaption;
    frmENTechConditionsRealizationStandartShow.Show;
  end;
end;

procedure TfrmMain.miCustomerServicesClick(Sender: TObject);
begin
  if not Assigned(frmENCustomerServicesShow) then
    frmENCustomerServicesShow := TfrmENCustomerServicesShow.Create(Application, fmChild);
  frmENCustomerServicesShow.Show;
end;

procedure TfrmMain.miCustomerServicesDrawItem(Sender: TObject; ACanvas: TCanvas; ARect: TRect; Selected: Boolean);
var
  LeftPos: Integer;
  TopPos: Integer;
  TextLength: Integer;
  Text: string;
begin
  Text := (Sender as TMenuItem).Caption;
  ACanvas.FillRect(ARect);
  ACanvas.Font.Color := clBlack;
  ACanvas.Font.Style := [fsBold];

  TopPos := ARect.Top + (ARect.Bottom - ARect.Top - ACanvas.TextHeight('W')) div 2;
  TextLength := Length(Text);

  LeftPos := ARect.Left + 5;
  ACanvas.TextOut(LeftPos, TopPos, Text);
end;


procedure TfrmMain.miCustomerWarrantClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin

  frmreportAppealRegistrationByRensAndDate := TfrmreportAppealRegistrationByRensAndDate.Create(Application, dsInsert);
  try
    frmreportAppealRegistrationByRensAndDate.Caption := 'Параметри звіту';
    if frmreportAppealRegistrationByRensAndDate.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 2);
      SetLength(args, 2);

      argNames[0] := 'dateStart';
      args[0] := DateToStr(frmreportAppealRegistrationByRensAndDate.edtDateStart.Date);

      argNames[1] := 'dateFinal';
      args[1] := DateToStr(frmreportAppealRegistrationByRensAndDate.edtDateFinal.Date);
      ///////

      reportName := 'warrant/customerWarrant';
      makeReport(reportName, argNames, args, 'pdf');
    end;
  finally
    frmreportAppealRegistrationByRensAndDate.Free;
  end;
end;


procedure TfrmMain.miEnePlanZagruzkaAndCountTechClick(Sender: TObject);
begin
  frmEnePlanZagruzkaAndCountPers := TfrmEnePlanZagruzkaAndCountPers.Create(Application, dsInsert);
 try
    frmEnePlanZagruzkaAndCountPers.report_kind := 2; // техническая часть
    frmEnePlanZagruzkaAndCountPers.ShowModal;
 finally
    frmEnePlanZagruzkaAndCountPers.Free;
 end;
end;

procedure TfrmMain.miENNomenclaturesOperativeClick(Sender: TObject);
begin
  if not Assigned(frmENNomenclaturesOperativeShow) then
    frmENNomenclaturesOperativeShow := TfrmENNomenclaturesOperativeShow.Create(Application, fmChild);
    frmENNomenclaturesOperativeShow.Show;
end;

procedure TfrmMain.miENNormativeVolumeAVZClick(Sender: TObject);
begin
  if not Assigned(frmENNormativeVolumeAVZShow) then
    frmENNormativeVolumeAVZShow := TfrmENNormativeVolumeAVZShow.Create(Application, fmChild);
    frmENNormativeVolumeAVZShow.Show;
end;

procedure TfrmMain.mniChartpaymentClick(Sender: TObject);
begin
	frmOrdersBytd1History := TfrmOrdersBytd1History.Create(Application, dsInsert);
	try
    frmOrdersBytd1History.chkShowGroup.Visible := false;
    frmOrdersBytd1History.chkShowPayOnlyCurPeriod.Visible := false;
    frmOrdersBytd1History.chkonlyNotPayItem.Visible := false;
		frmOrdersBytd1History.reportVersion := 7; // график оплат
		frmOrdersBytd1History.ShowModal;
  finally
		frmOrdersBytd1History.Free;
 end;
end;

procedure TfrmMain.mniChkRegClick(Sender: TObject);
begin
  frmCheckpointRegistration := TfrmCheckpointRegistration.Create(Application, fmChild);
  frmCheckpointRegistration.Show;
end;

procedure TfrmMain.mniCompareMaterialPriceClick(Sender: TObject);
begin
    frmComparePriceOrderedAndPurchaseMaterial := TfrmComparePriceOrderedAndPurchaseMaterial.Create(Application, dsInsert);
  try
    frmComparePriceOrderedAndPurchaseMaterial.ShowModal;
  finally
    frmComparePriceOrderedAndPurchaseMaterial.Free;
 end;
end;

procedure TfrmMain.mniDFRoutesClick(Sender: TObject);
begin
  if not Assigned(frmDFRouteShow) then
    frmDFRouteShow := TfrmDFRouteShow.Create(Application, fmChild);
    frmDFRouteShow.Show;
end;

procedure TfrmMain.miPrem_new_hmem_and_nkahClick(Sender: TObject);
begin
 FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try
   FrmReportDodatok3.Caption := 'Процент премії по персоналу по шкалі навантаження Херсонські РЕМ та Н.Каховські РЕМ';
    FrmReportDodatok3.report_vid := 2125; // Вид отчета - процент премии технарям .
    FrmReportDodatok3.pplankind := 4; // Фактичны плани
   FrmReportDodatok3.chkOnlyZatvAct.Visible := False; // формировать отчет Семенцова сказала по утвержд и не утвержд фактам
   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;
end;

procedure TfrmMain.miPrintBarCodeByMaterialsClick(Sender: TObject);
begin
    frmPrintBarCodeByMaterialsEdit := TfrmPrintBarCodeByMaterialsEdit.Create(Application, dsInsert);
  try
    frmPrintBarCodeByMaterialsEdit.ShowModal;
  finally
    frmPrintBarCodeByMaterialsEdit.Free;
  end;
end;

procedure TfrmMain.miAVRPlanClick(Sender: TObject);
begin
    if not Assigned(frmENAVRPlanShow) then
    frmENAVRPlanShow := TfrmENAVRPlanShow.Create(Application, fmChild);
    frmENAVRPlanShow.Caption := 'План робіт (Аварійні роботи)';
    frmENAVRPlanShow.Show;
end;

procedure TfrmMain.misetUnSetGlobus2transportrealClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
begin
    try
      /////// Parameters
      SetLength(argNames, 1);
      SetLength(args, 1);
    //  argnames[0] := 'qqq';
    //  args[0] := 'qqq';
    begin
      makeReport('Auto/transport_globus', argNames, args, 'xls');
    end;
  finally
  end;
end;

procedure TfrmMain.miAccess2EnelementClick(Sender: TObject);
begin
    if not Assigned(frmENAccess2EnelementShow) then
    frmENAccess2EnelementShow := TfrmENAccess2EnelementShow.Create(Application, fmChild);
    frmENAccess2EnelementShow.Show;
end;

procedure TfrmMain.miAccountingBrigadeNPZClick(Sender: TObject);
begin
   frmAverageAccountingPersonalNPZ := TfrmAverageAccountingPersonalNPZ.Create(Application, dsInsert);
   frmAverageAccountingPersonalNPZ.reportForBrigade := true;
   frmAverageAccountingPersonalNPZ.Caption := 'Перелік бригад, груп, служб, які працювали за нормово-виробничими завданнями';
 try
   frmAverageAccountingPersonalNPZ.ShowModal;
 finally
   frmAverageAccountingPersonalNPZ.Free;
 end;
end;

procedure TfrmMain.miDamageRecoveryClick(Sender: TObject);
begin
 if not Assigned(frmENDamageRecoveryShow) then
    frmENDamageRecoveryShow := TfrmENDamageRecoveryShow.Create(Application, fmChild);
    frmENDamageRecoveryShow.Show;
end;


procedure TfrmMain.miDefects2ElementTypeClick(Sender: TObject);
begin
  if not Assigned(frmTKDefects2ElementTypeShow) then
    frmTKDefects2ElementTypeShow := TfrmTKDefects2ElementTypeShow.Create(Application, fmChild);
  frmTKDefects2ElementTypeShow.Show;
end;


procedure TfrmMain.miDeficitProficitClick(Sender: TObject);
begin
    frmDeficitProficit := TfrmDeficitProficit.Create(Application, dsInsert);
 try
   frmDeficitProficit.ShowModal;
 finally
   frmDeficitProficit.Free;
   frmDeficitProficit := nil;
 end;
end;

procedure TfrmMain.miDFConsumerServicesClick(Sender: TObject);
begin
  if not Assigned(frmDFDocServicesConsumerKindShow) then
    frmDFDocServicesConsumerKindShow := TfrmDFDocServicesConsumerKindShow.Create(Application, fmChild);
  frmDFDocServicesConsumerKindShow.Show;
end;

procedure TfrmMain.miDFCustomerCategoryClick(Sender: TObject);
begin
  if not Assigned(frmDFCustomerCategoryShow) then
    frmDFCustomerCategoryShow := TfrmDFCustomerCategoryShow.Create(Application, fmChild);
  frmDFCustomerCategoryShow.Show;
end;

procedure TfrmMain.miDFDocAgreementClick(Sender: TObject);
begin
  if not Assigned(frmDFDocAgreementShow) then
    frmDFDocAgreementShow := TfrmDFDocAgreementShow.Create(Application, fmChild);
  frmDFDocAgreementShow.Show;
end;

procedure TfrmMain.miDFDocAppealSubjectClick(Sender: TObject);
begin
  if not Assigned(frmDFDocAppealSubjectShow) then
    frmDFDocAppealSubjectShow := TfrmDFDocAppealSubjectShow.Create(Application, fmChild);
  frmDFDocAppealSubjectShow.Show;
end;

procedure TfrmMain.miDFDocClick(Sender: TObject);
begin
  if not Assigned(frmDFDocShow) then
    frmDFDocShow := TfrmDFDocShow.Create(Application, fmChild);
  frmDFDocShow.Show;
end;

procedure TfrmMain.miDFDocInboxTypeClick(Sender: TObject);
begin
  if not Assigned(frmDFDocInboxTypeShow) then
    frmDFDocInboxTypeShow := TfrmDFDocInboxTypeShow.Create(Application, fmChild);
  frmDFDocInboxTypeShow.Show;
end;

procedure TfrmMain.miDFNormativeDocCatalogClick(Sender: TObject);
begin
  if not Assigned(frmDFNormativeDocCatalogShow) then
    frmDFNormativeDocCatalogShow := TfrmDFNormativeDocCatalogShow.Create(Application, fmChild);
  frmDFNormativeDocCatalogShow.Show;
end;

procedure TfrmMain.miDFOrdersCatalogClick(Sender: TObject);
begin
  if not Assigned(frmDFOrdersCatalogShow) then
    frmDFOrdersCatalogShow := TfrmDFOrdersCatalogShow.Create(Application, fmChild);
  frmDFOrdersCatalogShow.Show;
end;

procedure TfrmMain.miDFParam4ClassificationClick(Sender: TObject);
begin
  if not Assigned(frmDFParam4ClassificationShow) then
    frmDFParam4ClassificationShow := TfrmDFParam4ClassificationShow.Create(Application, fmChild);
  frmDFParam4ClassificationShow.Show;
end;


procedure TfrmMain.miServicesConsumerManagementClick(Sender: TObject);
var
  TempDFDepartment: DFDepartmentControllerSoapPort;
begin
  TempDFDepartment := HTTPRIODFDepartment as DFDepartmentControllerSoapPort;
  dfDepartmentCode := TempDFDepartment.getUserDepartment;

  if not Assigned(frmServicesConsumerManagement) then
    frmServicesConsumerManagement := TfrmServicesConsumerManagement.Create(Application, fmChild);
    frmServicesConsumerManagement.Show;
end;


procedure TfrmMain.miServicesConsumerManagementDrawItem(Sender: TObject; ACanvas: TCanvas; ARect: TRect; Selected: Boolean);
var
  LeftPos: Integer;
  TopPos: Integer;
  TextLength: Integer;
  Text: string;
begin
  Text := (Sender as TMenuItem).Caption;
  ACanvas.FillRect(ARect);
  ACanvas.Font.Color := clBlack;
  ACanvas.Font.Style := [fsBold];

  TopPos := ARect.Top + (ARect.Bottom - ARect.Top - ACanvas.TextHeight('W')) div 2;
  TextLength := Length(Text);

  LeftPos := ARect.Left + 5;
  ACanvas.TextOut(LeftPos, TopPos, Text);
end;


procedure TfrmMain.miServicesConsumerOnlineClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
begin
  try
    frmreportAppealRegistrationByRensAndDate := TfrmreportAppealRegistrationByRensAndDate.Create(Application, dsInsert);
    frmreportAppealRegistrationByRensAndDate.Caption := 'Період для формування звіту';
    frmreportAppealRegistrationByRensAndDate.lblRed.Visible := False;
    frmreportAppealRegistrationByRensAndDate.lblYellow.Visible := False;
    frmreportAppealRegistrationByRensAndDate.pnlRed.Visible := False;
    frmreportAppealRegistrationByRensAndDate.pnlYellow.Visible := False;

    if frmreportAppealRegistrationByRensAndDate.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 2);
      SetLength(args, 2);

      argNames[0] := 'dateStart';
      args[0] := DateToStr(frmreportAppealRegistrationByRensAndDate.edtDateStart.Date);

      argNames[1] := 'dateFinal';
      args[1] := DateToStr(frmreportAppealRegistrationByRensAndDate.edtDateFinal.Date);
      ///////

      DMReports.makeReport4DocFlow('ServicesConsumer/servicesConsumerOnline', argNames, args, 'xls');
    end;
  finally
    frmreportAppealRegistrationByRensAndDate.Free;
  end;
end;


procedure TfrmMain.miServicesConsumerTaskClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
begin
  frmreportAppealRegistrationByDate := TfrmreportAppealRegistrationByDate.Create(Application, dsInsert);
  frmreportAppealRegistrationByDate.Caption := 'Виконання послуг за Заявами споживачів';
  frmreportAppealRegistrationByDate.consumerTask := True;
  try
    if frmreportAppealRegistrationByDate.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 6);
      SetLength(args, 6);

      argNames[0] := 'dateStart';
      args[0] := DateToStr(frmreportAppealRegistrationByDate.edtDateStart.Date);

      argNames[1] := 'dateFinal';
      args[1] := DateToStr(frmreportAppealRegistrationByDate.edtDateFinal.Date);

      argNames[2] := 'departmentCode';
      args[2] := IntToStr(frmreportAppealRegistrationByDate.departmentCode);

      argNames[3] := 'servicesCode';
      args[3] := IntToStr(frmreportAppealRegistrationByDate.edtServices.ItemIndex);

      argNames[4] := 'departmentName';
      args[4] := frmreportAppealRegistrationByDate.edtDFDepartmentREN.Text;

      argNames[5] := 'servicesName';
      args[5] := frmreportAppealRegistrationByDate.edtServices.Text;
      ///////

      if (frmreportAppealRegistrationByDate.edtServices.ItemIndex = 14) then
        DMReports.makeReport4DocFlow('ServicesConsumer/servicesConsumerTask_14', argNames, args, 'xls')
      else
        DMReports.makeReport4DocFlow('ServicesConsumer/servicesConsumerTask', argNames, args, 'xls');
    end;
  finally
    frmreportAppealRegistrationByDate.Free;
  end;
end;


procedure TfrmMain.miDocAppealClick(Sender: TObject);
var
  docAppealFilter: DFDocAppealFilter;
  TempDFDepartment: DFDepartmentControllerSoapPort;
  department: DFDepartment;
begin
  docAppealFilter := DFDocAppealFilter.Create;
  SetNullIntProps(docAppealFilter);
  SetNullXSProps(docAppealFilter);

  docAppealFilter.doc := DFDoc.Create;
  SetNullIntProps(docAppealFilter.doc);
  SetNullXSProps(docAppealFilter.doc);

  docAppealFilter.doc.departmentRef := DFDepartmentRef.Create;
  docAppealFilter.doc.departmentRef.code := TMenuItem(Sender).Tag;

  if (docAppealFilter.doc.departmentRef.code = DFDEPARTMENT_IKC) then
  begin
    if not Assigned(frmDFDocAppealIKCShow) then
      frmDFDocAppealIKCShow := TfrmDFDocAppealShow.Create(Application, fmChild, docAppealFilter);
    frmDFDocAppealIKCShow.Caption := TMenuItem(Sender).Caption;
    frmDFDocAppealIKCShow.UpdateCaption;
    frmDFDocAppealIKCShow.Show;
  end
  else
  begin

    TempDFDepartment := HTTPRIODFDepartment as DFDepartmentControllerSoapPort;
    dfDepartmentCode := TempDFDepartment.getUserDepartment;

    if (dfDepartmentCode <> Low(Integer)) then
      department := TempDFDepartment.getObject(dfDepartmentCode);

    if (dfDepartmentCode <> Low(Integer)) then
    begin
      if (dfDepartmentCode = DFDEPARTMENT_IKC) then
      begin
        docAppealFilter.doc := nil;
        docAppealFilter.conditionSQL := ' dfdoc.departmentrefcode <> ' + IntToStr(DFDEPARTMENT_IKC);
      end
      else
       //docAppealFilter.doc.departmentRef.code := dfDepartmentCode;
       docAppealFilter.doc := nil;
       docAppealFilter.conditionSQL := ' dfdoc.departmentrefcode in ( ' +
            ' select dp.code from dfdepartment dp where dp.eprencode = ' + IntToStr(department.epRenCode) +
            ' )';
    end
    else
    begin
      docAppealFilter.conditionSQL := ' dfdoc.departmentrefcode <> ' + IntToStr(DFDEPARTMENT_IKC);
      docAppealFilter.doc.departmentRef.code := dfDepartmentCode;
    end;

    if not Assigned(frmDFDocAppealRESShow) then
      frmDFDocAppealRESShow := TfrmDFDocAppealShow.Create(Application, fmChild, docAppealFilter);

    if (dfDepartmentCode <> Low(Integer)) then
      frmDFDocAppealRESShow.Caption := 'Журнал Звернень споживачів'
    else
      frmDFDocAppealRESShow.Caption := TMenuItem(Sender).Caption;

    frmDFDocAppealRESShow.UpdateCaption;
    frmDFDocAppealRESShow.Show;
  end;
end;

procedure TfrmMain.miDocFlowServicesClick(Sender: TObject);
begin
  if not Assigned(frmDFMain) then
    frmDFMain := TfrmDFMain.Create(Application, fmChild);
    frmDFMain.Show;
end;

procedure TfrmMain.miDocOutboxClick(Sender: TObject);
begin
  if not Assigned(frmDFDocOutboxShow) then
    frmDFDocOutboxShow := TfrmDFDocOutboxShow.Create(Application, fmChild);
  frmDFDocOutboxShow.Show;
end;

procedure TfrmMain.miDocRegistrationByTerritorialDepartmentClick(
  Sender: TObject);
var
  argNames, args: ArrayOfString;
begin
  frmreportAppealRegistrationByRensAndDate := TfrmreportAppealRegistrationByRensAndDate.Create(Application, dsInsert);
  try
    frmreportAppealRegistrationByRensAndDate.Caption := 'Параметри для звіту "' +
                                                        miDocRegistrationByTerritorialDepartment.Caption + '"';
    if frmreportAppealRegistrationByRensAndDate.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 2);
      SetLength(args, 2);

      argNames[0] := 'dateStart';
      args[0] := DateToStr(frmreportAppealRegistrationByRensAndDate.edtDateStart.Date);

      argNames[1] := 'dateFinal';
      args[1] := DateToStr(frmreportAppealRegistrationByRensAndDate.edtDateFinal.Date);
      ///////

      DMReports.makeReport4DocFlow('doc_registration_by_territorial_department', argNames, args, 'xls');
    end;
  finally
    frmreportAppealRegistrationByRensAndDate.Free;
  end;
end;

procedure TfrmMain.miDocSupplyEEClick(Sender: TObject);
var
  f: DFDocSupplyEEFilter;
begin
  f := DFDocSupplyEEFilter.Create;
  SetNullIntProps(f);
  SetNullXSProps(f);

  f.typeRef := DFDocSupplyEETypeRef.Create;
  f.typeRef.code := TMenuItem(Sender).Tag;

  if f.typeRef.code = DFDOCSUPPLYEETYPE_SUPPLY then
  begin
    if not Assigned(frmDFDocSupplyEEShow) then
      frmDFDocSupplyEEShow := TfrmDFDocSupplyEEShow.Create(Application, fmChild, f);
    frmDFDocSupplyEEShow.Caption := TMenuItem(Sender).Caption;
    frmDFDocSupplyEEShow.UpdateCaption;
    frmDFDocSupplyEEShow.Show;
  end
  else if f.typeRef.code = DFDOCSUPPLYEETYPE_DISTRIBUTION then
  begin
    if not Assigned(frmDFDocDistributionEEShow) then
      frmDFDocDistributionEEShow := TfrmDFDocSupplyEEShow.Create(Application, fmChild, f);
    frmDFDocDistributionEEShow.Caption := TMenuItem(Sender).Caption;
    frmDFDocDistributionEEShow.UpdateCaption;
    frmDFDocDistributionEEShow.Show;
  end;

  {
  if not Assigned(frmDFDocSupplyEEShow) then
    frmDFDocSupplyEEShow := TfrmDFDocSupplyEEShow.Create(Application, fmChild);
  frmDFDocSupplyEEShow.Show;
  }
end;

procedure TfrmMain.miDocumentManagementClick(Sender: TObject);
begin
  if not Assigned(frmDocumentManagementShow) then
  begin
    frmDocumentManagementShow := TfrmDocumentManagementShow.Create(Application, fmChild);
    frmDocumentManagementShow.generalSearchFilter := nil;
  end;
  frmDocumentManagementShow.Show;
end;

procedure TfrmMain.miDocumentManagementDrawItem(Sender: TObject; ACanvas: TCanvas; ARect: TRect; Selected: Boolean);
var
  LeftPos: Integer;
  TopPos: Integer;
  TextLength: Integer;
  Text: string;
begin
  Text := (Sender as TMenuItem).Caption;
  ACanvas.FillRect(ARect);
  ACanvas.Font.Color := clBlack;
  ACanvas.Font.Style := [fsBold];

  TopPos := ARect.Top + (ARect.Bottom - ARect.Top - ACanvas.TextHeight('W')) div 2;
  TextLength := Length(Text);

  LeftPos := ARect.Left + 5;
  ACanvas.TextOut(LeftPos, TopPos, Text);
end;

procedure TfrmMain.miDriversLoadClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin
 frmReportTransportLoad := TfrmReportTransportLoad.Create(Application, dsInsert);
 frmReportTransportLoad.reportLoadType := 2;
  try
     if frmReportTransportLoad.ShowModal = mrOk then
    begin

      SetLength(argNames, 5);
      SetLength(args, 5);

      argNames[0] := 'fin_department';
      args[0] := frmReportTransportLoad.fin_departmentCode;

      argNames[1] := 'transportdepartmentname';
      args[1] := frmReportTransportLoad.fin_departmentName;

      argNames[2] := 'monthGen';
      args[2] :=  IntToStr(frmReportTransportLoad.edtMonthGen.ItemIndex + 1);

      argNames[3] := 'yearGen';
      args[3] := frmReportTransportLoad.edtYearGen.Text;
	  
	  argNames[4] := 'tabnumber';
	  args[4] := frmReportTransportLoad.tabnumber;

      if frmReportTransportLoad.chkFullYear.Checked then begin
        SetLength(argNames, 7);
        SetLength(args, 7);
        argNames[5] := 'monthGen1';
        args[5] := IntToStr(frmReportTransportLoad.edtMonthGen.ItemIndex + 1);
        argNames[6] := 'monthGen2';
        args[6] := IntToStr(frmReportTransportLoad.edtMonthGen2.ItemIndex + 1);
        if DMReports.UsesMDAXDataForReport = false  then begin
         reportName := 'transport/drivers_percent_load_whole_year'
       end else begin
         reportName := 'transport/ax_drivers_percent_load_whole_year';
       end;
      end else begin
       if DMReports.UsesMDAXDataForReport = false  then begin
         reportName := 'transport/drivers_percent_load'
       end else begin
         reportName := 'transport/ax_drivers_percent_load';
       end;
      end;

      if ((not frmReportTransportLoad.chkFullYear.Checked)
        and (frmReportTransportLoad.chkReportDriversPercentLoad.Checked))
        or (frmReportTransportLoad.chkFullYear.Checked) then begin
          makeReport(reportName, argNames, args, 'xls');
      end;

      if ((not frmReportTransportLoad.chkFullYear.Checked)
        and (frmReportTransportLoad.chkReportDriversLoadDaily.Checked)) then begin
          SetLength(argNames, 6);
          SetLength(args, 6);
          argNames[5] := 'departmentCode';
          args[5] := frmReportTransportLoad.fin_departmentCode;
          makeReport('transport/drivers_load_daily_for_month', argNames, args, 'xls');
      end;


     end;

  finally
    frmReportTransportLoad.Free;
  end;
end;

procedure TfrmMain.miDSTNumberConsumerAppealsClick(Sender: TObject);
begin
  frmreportDSTNumberConsumerAppeals := TfrmreportDSTNumberConsumerAppeals.Create(Application, dsInsert);
  try
    frmreportDSTNumberConsumerAppeals.ShowModal;
  finally
    frmreportDSTNumberConsumerAppeals.Free;
  end;
end;

procedure TfrmMain.miTransportLoadClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin
 frmReportTransportLoad := TfrmReportTransportLoad.Create(Application, dsInsert);
 frmReportTransportLoad.reportLoadType := 1;

  try
     if frmReportTransportLoad.ShowModal = mrOk then
    begin

      SetLength(argNames, 5);
      SetLength(args, 5);

      argNames[0] := 'transportdepartment';
      args[0] := IntToStr(frmReportTransportLoad.transportDepartmentCode);

      argNames[1] := 'transportdepartmentname';
      args[1] := frmReportTransportLoad.transportDepartmentName;

      argNames[2] := 'monthGen';
      args[2] :=  IntToStr(frmReportTransportLoad.edtMonthGen.ItemIndex + 1);

      argNames[3] := 'yearGen';
      args[3] := frmReportTransportLoad.edtYearGen.Text;

      argNames[4] := 'transportClassification';
      if frmReportTransportLoad.cbbTransportClassification.ItemIndex = -1 then
      args[4] := '0'
      else
      args[4] := IntToStr(Integer(frmReportTransportLoad.cbbTransportClassification.Items.Objects[frmReportTransportLoad.cbbTransportClassification.ItemIndex]));

      if frmReportTransportLoad.chkFullYear.Checked then
       if DMReports.UsesMDAXDataForReport = false  then
         reportName := 'transport/transport_percent_load_whole_year'
       else
         reportName := 'transport/ax_transport_percent_load_whole_year';

      if frmReportTransportLoad.chkFullYear.Checked = false then
      if DMReports.UsesMDAXDataForReport = false  then
       reportName := 'transport/transport_percent_load'
      else
       reportName := 'transport/ax_transport_percent_load';

      if frmReportTransportLoad.chkGraph.Checked then
      begin
         reportName := 'Graph/graph_AnalyseTransport';
         makeReport(reportName, argNames, args, 'pdf');
      end
      else
      makeReport(reportName, argNames, args, 'xls');

     end;

  finally
    frmReportTransportLoad.Free;
  end;
end;

procedure TfrmMain.miZKUClick(Sender: TObject);
var
  ozFilter: SCUsageInputFilter;
begin
  ozFilter := SCUsageInputFilter.Create;
  SetNullIntProps(ozFilter);
  SetNullXSProps(ozFilter);
  ozFilter.autoCreated := SC_USAGE_INPUT_AUTO_CREATED_NO;

  if not Assigned(frmSCUsageInputZKUShow) then
    frmSCUsageInputZKUShow := TfrmSCUsageInputShow.Create(Application, fmChild, True, ozFilter);
  frmSCUsageInputZKUShow.Caption := TMenuItem(Sender).Caption;
  frmSCUsageInputZKUShow.Show;
end;

procedure TfrmMain.miServicesConnectionsClick(Sender: TObject);
var
  f: ENServicesObjectFilter;
begin
  f := ENServicesObjectFilter.Create;
  SetNullIntProps(f);
  SetNullXSProps(f);

  f.contractTypeRef := ENServicesContractTypeRef.Create;
  f.contractTypeRef.code := TMenuItem(Sender).Tag;

  f.contractKindRef := ENServicesContractKindRef.Create;
  f.contractKindRef.code := SERVICES_CONTRACT_KIND_SERVICES;

  if (f.contractTypeRef.code = ENSERVICESOBJECTTYPE_CONNECTION) then
  begin
    if not Assigned(frmENServicesConnectionShow) then
      ///// 07.06.13
      // frmENServicesConnectionShow := TfrmENServicesCalculationShow.Create(Application, fmChild, f);
      frmENServicesConnectionShow := TfrmENServicesConnectionShow.Create(Application, fmChild, f);
      /////
    frmENServicesConnectionShow.Caption := TMenuItem(Sender).Caption;
    frmENServicesConnectionShow.UpdateCaption;
    frmENServicesConnectionShow.Show;
  end
  else
    ShowMessage('Ошибочка в определении типа договора ... SERVICES_CONTRACT_TYPE');
end;

procedure TfrmMain.miWorkedTimeInActsClick(Sender: TObject);
var
  formWorkedTimeInActs: TfrmReportWorkedTimeInActs;
begin

    formWorkedTimeInActs := TfrmReportWorkedTimeInActs.Create(Application, dsInsert);
  try
    formWorkedTimeInActs.ShowModal;
  finally
    formWorkedTimeInActs.Free;
  end;

end;

procedure TfrmMain.miExitClick(Sender: TObject);
begin
    Application.Terminate;
end;

procedure TfrmMain.miStandardConnectionClick(Sender: TObject);
begin
   frmReportStandardConnection := TfrmReportStandardConnection.Create(Application, dsInsert);
  try
    frmReportStandardConnection.ShowModal;
  finally
    frmReportStandardConnection.Free;
  end;
end;

procedure TfrmMain.NLine6_10kVClick(Sender: TObject);
begin
		frmReportExaminationTP := TfrmReportExaminationTP.Create(Application, dsInsert);
		frmReportExaminationTP.type_report := 2;
	try
		frmReportExaminationTP.ShowModal;
	finally
		frmReportExaminationTP.Free;
	end;
end;

procedure TfrmMain.pmActWorkflowItogClick(Sender: TObject);
begin
  frmCCRunReport := TfrmCCRunReport.Create(Application, dsEdit);
  try
    frmCCRunReport.reportName := TMenuItem(Sender).Caption;
    frmCCRunReport.ShowModal;
  finally
    frmCCRunReport.Free;
    frmCCRunReport := nil;
  end;

end;

procedure TfrmMain.prem_new_hmemClick(Sender: TObject);
begin
 FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try
   FrmReportDodatok3.Caption := 'Процент премії по персоналу по шкалі навантаження ХМЕМ';
    FrmReportDodatok3.report_vid := 21; // Вид отчета - процент премии технарям . по шкале нагрузки
    FrmReportDodatok3.pplankind := 4; // Фактичны плани
   FrmReportDodatok3.chkOnlyZatvAct.Visible := False; // формировать отчет Семенцова сказала по утвержд и не утвержд фактам
   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;
end;

procedure TfrmMain.prem_new_tehnClick(Sender: TObject);
begin
  FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try
   FrmReportDodatok3.Caption := 'Процент премії по персоналу по шкалі навантаження(технічна частина)';
    FrmReportDodatok3.report_vid := 19; // Вид отчета - процент премии технарям . по шкале нагрузки
    FrmReportDodatok3.pplankind := 4; // Фактичны плани
   FrmReportDodatok3.chkOnlyZatvAct.Visible := False; // формировать отчет Семенцова сказала по утвержд и не утвержд фактам
   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;
end;

procedure TfrmMain.prem_new_zbytClick(Sender: TObject);
begin
 FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try
   FrmReportDodatok3.Caption := 'Процент премії по персоналу по шкалі навантаження(енергосбутова частина)';
    FrmReportDodatok3.report_vid := 20; // Вид отчета - процент премии сбыт . по шкале нагрузки
    FrmReportDodatok3.pplankind := 4; // Фактичны плани
   FrmReportDodatok3.chkOnlyZatvAct.Visible := False; // формировать отчет Семенцова сказала по утвержд и не утвержд фактам
   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;
end;

procedure TfrmMain.prem_new_zbyt_inspClick(Sender: TObject);
begin
 FrmReportDodatok3 := TFrmReportDodatok3.Create(Application, dsInsert);
 try
   FrmReportDodatok3.Caption := 'Процент премії по персоналу по шкалі навантаження(енергосбутова частина, інспектора)';
    FrmReportDodatok3.report_vid := 24; // Вид отчета - процент премии сбыт . по шкале нагрузки
    FrmReportDodatok3.pplankind := 4; // Фактичны плани
   FrmReportDodatok3.chkOnlyZatvAct.Visible := False; // формировать отчет Семенцова сказала по утвержд и не утвержд фактам
   FrmReportDodatok3.ShowModal;
 finally
   FrmReportDodatok3.Free;
 end;
end;

procedure TfrmMain.NLine04kVClick(Sender: TObject);
begin
    frmReportExaminationTP := TfrmReportExaminationTP.Create(Application, dsInsert);
		frmReportExaminationTP.type_report := 3;
	try
		frmReportExaminationTP.ShowModal;
	finally
		frmReportExaminationTP.Free;
	end;
end;

procedure TfrmMain.miN127ConnectDebtorsClick(Sender: TObject);
begin
		frmConnectDebtors := TfrmConnectDebtors.Create(Application, dsInsert);
	try
		frmConnectDebtors.ShowModal;
	finally
    frmConnectDebtors.Free;
 end;
end;

procedure TfrmMain.miRepByTD1History2Click(Sender: TObject);
begin
	frmOrdersBytd1History := TfrmOrdersBytd1History.Create(Application, dsInsert);
	try
//		frmOrdersBytd1History.reportVersion := 2;
		frmOrdersBytd1History.reportVersion := 6; // вариант материалы и услуги по новым правилам
		frmOrdersBytd1History.ShowModal;
  finally
		frmOrdersBytd1History.Free;
 end;
end;

procedure TfrmMain.miPaymentRegistryClick(Sender: TObject);
begin
  frmOrdersBytd1History := TfrmOrdersBytd1History.Create(Application, dsInsert);
  try
    frmOrdersBytd1History.reportVersion := 3;
    frmOrdersBytd1History.ShowModal;
  finally
    frmOrdersBytd1History.Free;
 end;
end;

procedure TfrmMain.miPaymentRegistry2Click(Sender: TObject);
begin
	frmOrdersBytd1History := TfrmOrdersBytd1History.Create(Application, dsInsert);
	try
		frmOrdersBytd1History.reportVersion := 4;
		frmOrdersBytd1History.ShowModal;
	finally
		frmOrdersBytd1History.Free;
 end;
end;

procedure TfrmMain.miPaymentRegistryServiceClick(Sender: TObject);
begin
		frmOrdersBytd1History := TfrmOrdersBytd1History.Create(Application, dsInsert);
	try
		frmOrdersBytd1History.reportVersion := 5; // услуги
		frmOrdersBytd1History.ShowModal;
	finally
		frmOrdersBytd1History.Free;
 end;
end;

procedure TfrmMain.mipersonalcarddriverClick(Sender: TObject);
var
  ReportPersonalCardDriver: TfrmReportPersonalCardDriver;
begin
    ReportPersonalCardDriver := TfrmReportPersonalCardDriver.Create(Application, dsInsert);
  try
    ReportPersonalCardDriver.ShowModal;
  finally
    ReportPersonalCardDriver.Free;
  end;
end;

procedure TfrmMain.miRepByTD1History2AndServiceClick(Sender: TObject);
begin
	frmOrdersBytd1History := TfrmOrdersBytd1History.Create(Application, dsInsert);
	try
		frmOrdersBytd1History.reportVersion := 6;
		frmOrdersBytd1History.ShowModal;
  finally
		frmOrdersBytd1History.Free;
 end;
end;

procedure TfrmMain.miRQOrg2TypePayClick(Sender: TObject);
begin
  if not Assigned(frmRQOrg2TypePayShow) then
    frmRQOrg2TypePayShow := TfrmRQOrg2TypePayShow.Create(Application, fmChild);
    frmRQOrg2TypePayShow.Show;
end;

procedure TfrmMain.miRQOrgBankClick(Sender: TObject);
begin
  if not Assigned(frmRQOrgBankShow) then
    frmRQOrgBankShow := TfrmRQOrgBankShow.Create(Application, fmChild);
  frmRQOrgBankShow.Show;
end;

procedure TfrmMain.miRQOrgClick(Sender: TObject);
begin
  if not Assigned(frmRQOrgShow) then
    frmRQOrgShow := TfrmRQOrgShow.Create(Application, fmChild);
  frmRQOrgShow.Show;
end;

procedure TfrmMain.N175Click(Sender: TObject);
begin
    frmReportEnergoSbyt := TfrmReportEnergoSbyt.Create(Application, dsInsert);
  try
     frmReportEnergoSbyt.report := 3;
    frmReportEnergoSbyt.edtDateStart.Visible := false;
    frmReportEnergoSbyt.Label1.Visible := false;
    frmReportEnergoSbyt.rgkindPlan.Visible := True;
    frmReportEnergoSbyt.cbByYear.Visible := false;
    frmReportEnergoSbyt.Label3.Visible := false;
    frmReportEnergoSbyt.cbStartMonth.Visible := false;
    frmReportEnergoSbyt.lblEPRenName.Visible := true;
    frmReportEnergoSbyt.edtEPRenName.Visible := true;
    frmReportEnergoSbyt.spbEPRen.Visible := true;
    frmReportEnergoSbyt.spbEPRenClear.Visible := true;
    frmReportEnergoSbyt.lblEPRenName.Top := 35;
    frmReportEnergoSbyt.edtEPRenName.Top := 35;
    frmReportEnergoSbyt.spbEPRen.Top := 35;
    frmReportEnergoSbyt.spbEPRenClear.Top := 35;

     frmReportEnergoSbyt.ShowModal;
  finally
    frmReportEnergoSbyt.Free;
 end;
end;

procedure TfrmMain.N177Click(Sender: TObject);
begin
    if not Assigned(frmRQCentralExportGraphShow) then
      frmRQCentralExportGraphShow := TfrmRQCentralExportGraphShow.Create(Application, fmChild);
  frmRQCentralExportGraphShow.Show;
end;

procedure TfrmMain.N177_report_protection_tariffClick(Sender: TObject);
begin
    frmReportProtectionTariff := TfrmReportProtectionTariff.Create(Application, dsInsert);
	try
		frmReportProtectionTariff.ShowModal;
	finally
		frmReportProtectionTariff.Free;
 end;
end;

procedure TfrmMain.N178Click(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: string;
begin
  frmENRunReport := TfrmENRunReport.Create(Application, dsInsert);
  frmENRunReport.gbDepartment.Visible := True;
  frmENRunReport.gbDates.Visible := True;
  frmENRunReport.gbCheckBox1.Visible := True;
  frmENRunReport.chk1.Caption := 'Включити в звіт акти побутових споживачів';
  frmENRunReport.chk1.Checked := True;
  frmENRunReport.gbCheckBox2.Visible := True;
  frmENRunReport.chk2.Caption := 'Включити в звіт акти промислових споживачів';
  frmENRunReport.chk2.Checked := True;
  frmENRunReport.gbRoles.Visible := True;

  try
    if frmENRunReport.ShowModal = mrOk then
    begin
      /////// Parameters
      SetLength(argNames, 6);
      SetLength(args, 6);

      argNames[0] := 'dateStart';
      args[0] := DateToStr(frmENRunReport.dtpStartDate.Date);

      argNames[1] := 'dateEnd';
      args[1] := DateToStr(frmENRunReport.dtpEndDate.Date);

      argNames[2] := 'depCode';
      args[2] := IntToStr(frmENRunReport.departmentCode);

      argNames[3] := 'includeByt';
      if frmENRunReport.chk1.Checked then
        args[3] := '1'
      else
        args[3] := '0';

      argNames[4] := 'includeProm';
      if frmENRunReport.chk2.Checked then
        args[4] := '1'
      else
        args[4] := '0';

      argNames[5] := 'typecode';
      if (frmENRunReport.rbByt.Checked) then
        args[5] := '1';

      if (frmENRunReport.rbJur.Checked) then
        args[5] := '2';

      if (frmENRunReport.rbHead.Checked) then
        args[5] := '3';


      ///////
      ///  07.12.2017 +++ Для Сафронова
      //if (HTTPRIOAuth.HTTPWebNode.UserName = 'SafronovIN') then
        reportName := 'RepEnergozbyt/Acts/act_detailed_report/detailed_main_boss';
      //else
      //  reportName := 'RepEnergozbyt/Acts/act_detailed_report/detailed_main';

      makeReport(reportName, argNames, args, 'xls');
    end;
  finally
    frmENDateRangeWithDepartment.Free;
  end;
end;


procedure TfrmMain.N178Purchases_tmcClick(Sender: TObject);
begin
  frmReportPurchases_tmc := TfrmReportPurchases_tmc.Create(Application, dsInsert);
  try
    frmReportPurchases_tmc.ShowModal;
  finally
    frmReportPurchases_tmc.Free;
  end;
end;


procedure TfrmMain.N178_bonusListClick(Sender: TObject);
begin
  if not Assigned(frmENBonusListShow) then
    frmENBonusListShow := TfrmENBonusListShow.Create(Application, fmChild);
  frmENBonusListShow.Show;
end;


procedure TfrmMain.miHighVoltHardwareTypeClick(Sender: TObject);
var
  highVoltHardwareTypeFilter: ENHighVoltHardwareTypeFilter;
begin
  ///////////////////////////////////
  // В ТЭГАХ УКАЗАН КОД ТИПА ЭЛЕМЕНТА
  ///////////////////////////////////
  highVoltHardwareTypeFilter := ENHighVoltHardwareTypeFilter.Create;
  SetNullIntProps(highVoltHardwareTypeFilter);
  SetNullXSProps(highVoltHardwareTypeFilter);
  highVoltHardwareTypeFilter.elementTypeRef := ENElementTypeRef.Create;
  highVoltHardwareTypeFilter.elementTypeRef.code := TMenuItem(Sender).Tag;

  frmENHighVoltHardwareTypeShow := TfrmENHighVoltHardwareTypeShow.Create(Application, fmChild, highVoltHardwareTypeFilter);
  frmENHighVoltHardwareTypeShow.elementTypeCode := TMenuItem(Sender).Tag;
  frmENHighVoltHardwareTypeShow.Show;
end;



procedure TfrmMain.miHistogramJLCClick(Sender: TObject);
begin
  makeReport('Histograms/jlc/jlcHistogram_wrapper', ArrayOfString.Create('dummy'), ArrayOfString.Create('dummy'), 'xls');
end;

end.



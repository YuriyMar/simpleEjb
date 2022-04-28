unit EditENPlanWork;

interface

uses
    Windows, Messages, SysUtils, StdCtrls, Variants, Classes, Graphics, Controls, Forms,
    Dialogs, ChildFormUnit, InvokeRegistry, Rio, SOAPHTTPClient, ImgList,
    Grids, ComCtrls, ToolWin, Menus, ActnList, BaseGrid,  XSBuiltIns, GridHeadersUnit,
    DialogFormUnit, AdvGrid, Buttons,
	  EnergyproController, EnergyproController2, ENPlanWorkController, ENEstimateItemController, ExtCtrls
   ,SOAPHTTPTrans, TB2Item, TB2Dock, TB2Toolbar
   ,FINMolDataController, FINMolTypeController,  ENTechConditionsServicesController
   , ShowENTravelSheet
   , ENTravelSheetController, tmsAdvGridExcel, AdvObj ,ScanCountersController,SCCounterKindController,SCCounterStatusController
   , ENPlanWork2CCDamageLogController
   , ENPlanWorkENAct2OSDataController
   , CCDamageLogController, CCOutageNoticeController
   , IniTools
   , SetupFormUnit, SCCounterController, ENPlanProjectController, ENIPController
  ;

type
  TfrmENPlanWorkEdit = class(TDialogForm)

  btnOk: TButton;
  btnCancel: TButton;
    pcPlanWork: TPageControl;
    tsPlanWork: TTabSheet;
    tsPlanWorkItems: TTabSheet;
    lblDateGen: TLabel;
    lblYearGen: TLabel;
    lblMonthGen: TLabel;
    lblCommentGen: TLabel;
    spbENPlanWorkStatus: TSpeedButton;
    edtENPlanWorkStatusName: TEdit;
    lblENPlanWorkStatusName: TLabel;
    lblEnElement: TLabel;
    edtENElementName: TEdit;
    spbENElement: TSpeedButton;
    edtYearGen: TComboBox;
    edtMonthGen: TComboBox;
    ToolBar1: TToolBar;
    ToolButton1: TToolButton;
    ToolButton6: TToolButton;
    ToolButton7: TToolButton;
    ToolButton8: TToolButton;
    ToolButton11: TToolButton;
    ToolButton2: TToolButton;
    ToolButton3: TToolButton;
    sgENPlanWorkItem: TAdvStringGrid;
    ImageList1: TImageList;
    ActionList1: TActionList;
    actView: TAction;
    actInsert: TAction;
    actDelete: TAction;
    actEdit: TAction;
    actUpdate: TAction;
    actFilter: TAction;
    actNoFilter: TAction;
    PopupMenu1: TPopupMenu;
    N1: TMenuItem;
    N2: TMenuItem;
    N3: TMenuItem;
    N4: TMenuItem;
    N6: TMenuItem;
    N7: TMenuItem;
    N8: TMenuItem;
    HTTPRIOENPlanWorkItem: THTTPRIO;
    HTTPRIOENElement: THTTPRIO;
    tsEstimateItems: TTabSheet;
    HTTPRIOENEstimateItem: THTTPRIO;
    btnPlanReport: TButton;
    lblTypeName: TLabel;
    lblEPRenName: TLabel;
    edtEPRenName: TEdit;
    spbEPRen: TSpeedButton;
    tsMoves: TTabSheet;
    tsCorrections: TTabSheet;
    sgENPlanWorkMoveHistory: TAdvStringGrid;
    HTTPRIOENPlanWorkMoveHistory: THTTPRIO;
    sgENPlanCorrectHistory: TAdvStringGrid;
    HTTPRIOENPlanCorrectHistory: THTTPRIO;
    lblENBudgetName: TLabel;
    edtENBudgetName: TEdit;
    spbENBudget: TSpeedButton;
    lblResponsibility: TLabel;
    edtResponsibility: TEdit;
    spbResponsibility: TSpeedButton;
    lblDepartment: TLabel;
    edtDepartment: TEdit;
    spbDepartment: TSpeedButton;
    HTTPRIOENDepartment: THTTPRIO;



    lblDateStart: TLabel;
    lblDateFinal: TLabel;
    edtDateFinal: TDateTimePicker;
    edtDateStart: TDateTimePicker;
    edtTypeName: TEdit;
    spbType: TSpeedButton;
    HTTPRIOENPlanWorkType: THTTPRIO;
    tsHumens: TTabSheet;
    tsTransports: TTabSheet;
    ToolBar3: TToolBar;
    ToolButton15: TToolButton;
    ToolButton16: TToolButton;
    ToolButton17: TToolButton;
    ToolButton18: TToolButton;
    ToolButton19: TToolButton;
    ToolButton20: TToolButton;
    ToolButton21: TToolButton;
    ToolBar4: TToolBar;
    ToolButton22: TToolButton;
    ToolButton23: TToolButton;
    ToolButton24: TToolButton;
    ToolButton25: TToolButton;
    ToolButton26: TToolButton;
    ToolButton27: TToolButton;
    ToolButton28: TToolButton;
    sgENHumenItem: TAdvStringGrid;
    sgENTransportItem: TAdvStringGrid;
    HTTPRIOENHumenItem: THTTPRIO;
    HTTPRIOENTransportItem: THTTPRIO;
    lblENPlanWorkKindKindName: TLabel;
    edtENPlanWorkKindKindName_: TEdit;
    spbENPlanWorkKindKind_: TSpeedButton;
    cbPlanWorkKind: TComboBox;
    actMaterialBinding: TAction;
    N5: TMenuItem;
    N9: TMenuItem;
    lblDistanceTo: TLabel;
    edtDistanceTo: TEdit;
    lblDistanceAlong: TLabel;
    edtDistanceAlong: TEdit;
    edtENActNumber: TEdit;
    spbENAct: TSpeedButton;
    HTTPRIOENAct2ENPlanWork: THTTPRIO;
    lblWorkState: TLabel;
    edtWorkState: TEdit;
    spbENPlanWorkState: TSpeedButton;
    lblENActNumber: TLabel;
    HTTPRIOENPlanWorkState: THTTPRIO;
    HTTPRIOENPlanWork: THTTPRIO;
    HTTPRIOFINMaterials: THTTPRIO;
    actMaterialBindingToFIN: TAction;
    N10: TMenuItem;
    Panel1: TPanel;
    ToolBar2: TToolBar;
    ToolButton4: TToolButton;
    ToolButton5: TToolButton;
    ToolButton9: TToolButton;
    ToolButton10: TToolButton;
    ToolButton12: TToolButton;
    ToolButton13: TToolButton;
    ToolButton14: TToolButton;
    sgENEstimateItem: TAdvStringGrid;
    Splitter1: TSplitter;
    lblExecuter: TLabel;
    edtFINExecutorName: TEdit;
    spbFINExecutor: TSpeedButton;
    ToolButton29: TToolButton;
    pnlLegend: TPanel;
    Shape1: TShape;
    Shape2: TShape;
    Label1: TLabel;
    Label2: TLabel;
    ColorDialog1: TColorDialog;
    btnCloseAll: TButton;
    log: TMemo;
    edtInvNumber: TEdit;
    Label3: TLabel;
    gbWorkOrder__: TGroupBox;
    lblDateWorkOrder: TLabel;
    edtDateWorkOrder_: TDateTimePicker;
    lblWorkOrderNumber: TLabel;
    edtWorkOrderNumber_: TEdit;
    btnWorkOrderDetails: TButton;
    HTTPRIOENWorkOrder2ENPlanWork: THTTPRIO;
    HTTPRIOENWorkOrder: THTTPRIO;
    tsWorkOrder: TTabSheet;
    gbWorkOrder: TGroupBox;
    Label4: TLabel;
    edtWorkOrderNumber: TEdit;
    tbWorkOrder: TTBToolbar;
    TBSubmenuItem1: TTBSubmenuItem;
    tbiWorkOrderInsert: TTBItem;
    tbiWorkOrderEdit: TTBItem;
    tbiWorkOrderDelete: TTBItem;
    edtDateWorkOrder: TDateTimePicker;
    Label5: TLabel;
    Label6: TLabel;
    edtWorkOrderCommentGen: TMemo;
    actWorkOrderInsert: TAction;
    actWorkOrderEdit: TAction;
    actWorkOrderDelete: TAction;
    actWorkOrderView: TAction;
    ToolBar5: TToolBar;
    tbWorkOrderInsert: TToolButton;
    tbWorkOrderDelete: TToolButton;
    tbWorkOrderEdit: TToolButton;
    tbWorkOrderUpdate: TToolButton;
    actWorkOrderUpdate: TAction;
    btnWorkOrderSave: TBitBtn;
    btnWorkOrderCancel: TBitBtn;
    lblFinMolCode: TLabel;
    edtFinMolCode: TEdit;
    lblFinMolName: TLabel;
    edtFinMolName: TEdit;
    spbFINMol: TSpeedButton;
    lblFinMechanicCode: TLabel;
    edtFinMechanicCode: TEdit;
    lblFinMechanicName: TLabel;
    edtFinMechanicName: TEdit;
    spbFINMechanic: TSpeedButton;
    HTTPRIOENDepartment2EPRen: THTTPRIO;
    tsDismount: TTabSheet;
    sgDismount: TAdvStringGrid;
    ToolBar6: TToolBar;
    ToolButton30: TToolButton;
    ToolButton31: TToolButton;
    ToolButton32: TToolButton;
    ToolButton33: TToolButton;
    ToolButton34: TToolButton;
    ToolButton35: TToolButton;
    ToolButton36: TToolButton;
    tsGSM: TTabSheet;
    pnlGSM: TPanel;
    ToolBar7: TToolBar;
    ToolButton37: TToolButton;
    ToolButton38: TToolButton;
    ToolButton39: TToolButton;
    ToolButton40: TToolButton;
    ToolButton41: TToolButton;
    ToolButton42: TToolButton;
    ToolButton43: TToolButton;
    ToolButton44: TToolButton;
    Panel3: TPanel;
    Shape3: TShape;
    Shape4: TShape;
    Label7: TLabel;
    Label8: TLabel;
    Splitter2: TSplitter;
    gbFINGSM: TGroupBox;
    sgFINGSM: TAdvStringGrid;
    sgGSM: TAdvStringGrid;
    lblPK: TLabel;
    edtCode: TEdit;
    estimateItemPanel: TPanel;
    cbShowAll: TCheckBox;
    Panel2: TPanel;
    cbShowAllGSM: TCheckBox;
    edtWorkOrderCode: TEdit;
    HTTPRIOENMOL2PlanWork: THTTPRIO;
    gbPlanMOL: TGroupBox;
    lblMolName: TLabel;
    edtMolName: TEdit;
    spbPlanMOL: TSpeedButton;
    spbPlanMOLClear: TSpeedButton;
    HTTPRIOENMetrologyCounter: THTTPRIO;
    HTTPRIOENAct: THTTPRIO;
    actAddToRQOrder: TAction;
    HTTPRIORQOrderItem: THTTPRIO;
    actRemoveFromRQOrder: TAction;
    gbMOLData: TGroupBox;
    sgFINMolData: TAdvStringGrid;
    tbActions: TTBToolbar;
    TBItem5: TTBItem;
    TBItem6: TTBItem;
    TBItem47: TTBItem;
    TBItem7: TTBItem;
    TBItem46: TTBItem;
    actMOLDataInsert: TAction;
    actMOLDataEdit: TAction;
    actMOLDataDelete: TAction;
    actMOLDataView: TAction;
    actMOLDataUpdate: TAction;
    HTTPRIOFINMolData: THTTPRIO;
    gbPriConnection: TGroupBox;
    lblPriConnectionNumber: TLabel;
    edtPriConnectionNumber: TEdit;
    gbBindingOver: TGroupBox;
    edtBindingOver: TEdit;
    spbBindingOver: TSpeedButton;
    HTTPRIOENBindingOver: THTTPRIO;
    ToolButton45: TToolButton;
    actExpToExcel: TAction;
    workExcel: TAdvGridExcelIO;
    ToolButton46: TToolButton;
    actExpToExcel_material: TAction;
    materialExcel: TAdvGridExcelIO;
    actExpToExcelDemontaj: TAction;
    demontajExcel: TAdvGridExcelIO;
    ToolButton47: TToolButton;
    ToolButton48: TToolButton;
    brigadaExcel: TAdvGridExcelIO;
    actExpToExcelHuman: TAction;
    actChangeEstimateItemStatus: TAction;
    actSelectAll: TAction;
    actClearAll: TAction;
    N12: TMenuItem;
    N13: TMenuItem;
    N14: TMenuItem;
    lblPlanWorkForm: TLabel;
    HTTPRIOENPlanWorkForm: THTTPRIO;
    cbENPlanWorkFormName: TComboBox;
    N11: TMenuItem;
    N15: TMenuItem;
    HTTPRIOMarkEstimate: THTTPRIO;
    actMoveTO: TAction;
    actMoveFrom: TAction;
    N16: TMenuItem;
    N17: TMenuItem;
    N18: TMenuItem;
    lblENPlanWorkSource: TLabel;
    cbENPlanWorkSource: TComboBox;
    gbReason: TGroupBox;
    edtReason: TEdit;
    spbReason: TSpeedButton;
    HTTPRIOENPlanWorkReason: THTTPRIO;
    HTTPRIOENPlanWork2PlanWorkReason: THTTPRIO;
    spbReasonClear: TSpeedButton;
    Splitter4: TSplitter;
    gbSCCounter: TGroupBox;
    sgSCCounter: TAdvStringGrid;
    HTTPRIOSCCounter: THTTPRIO;
    pnlAdditional: TPanel;
    Splitter3: TSplitter;
    gbMarkers: TGroupBox;
    sgMarkers: TAdvStringGrid;
    pnlFINMaterialsAndCounters: TPanel;
    gbSCCountersMaterials: TGroupBox;
    sgSCCounterMaterials: TAdvStringGrid;
    gbFINMaterials: TGroupBox;
    sgFINMaterials: TAdvStringGrid;
    memoData: TMemo;
    log2: TMemo;
    gbFINUnmount: TGroupBox;
    sgFINUnmount: TAdvStringGrid;
    tsRefinement: TTabSheet;
    gbFINRefinement: TGroupBox;
    sgFINRefinement: TAdvStringGrid;
    sgRefinement: TAdvStringGrid;
    ToolBar8: TToolBar;
    ToolButton49: TToolButton;
    ToolButton50: TToolButton;
    ToolButton51: TToolButton;
    ToolButton52: TToolButton;
    ToolButton53: TToolButton;
    ToolButton54: TToolButton;
    ToolButton55: TToolButton;
    ToolButton56: TToolButton;
    btnConvertTranzit2Operative: TButton;
    tsRQFKOrder: TTabSheet;
    ToolBar9: TToolBar;
    ToolButton57: TToolButton;
    ToolButton58: TToolButton;
    sgRQFKOrder: TAdvStringGrid;
    HTTPRIORQFKOrder: THTTPRIO;
    tsProduced: TTabSheet;
    sgProduced: TAdvStringGrid;
    ToolBar10: TToolBar;
    ToolButton59: TToolButton;
    ToolButton60: TToolButton;
    ToolButton61: TToolButton;
    ToolButton62: TToolButton;
    ToolButton63: TToolButton;
    ToolButton64: TToolButton;
    ToolButton65: TToolButton;
    ToolButton66: TToolButton;
    gbFINProduced: TGroupBox;
    sgFINProduced: TAdvStringGrid;
    producedExcel: TAdvGridExcelIO;
    btn1: TButton;
    actFinWorkerAssignToAll: TAction;
    N19: TMenuItem;
    N20: TMenuItem;
    actFINWorkerAssignToAllTransport: TAction;
    N21: TMenuItem;
    N22: TMenuItem;
    actRealTransportAssignToAll: TAction;
    btnPrintWorkOrder: TButton;
    tsServicesItem: TTabSheet;
    ToolBar11: TToolBar;
    ToolButton67: TToolButton;
    ToolButton68: TToolButton;
    ToolButton69: TToolButton;
    ToolButton70: TToolButton;
    ToolButton71: TToolButton;
    ToolButton72: TToolButton;
    ToolButton73: TToolButton;
    ToolButton74: TToolButton;
    sgServicesItem: TAdvStringGrid;
    gbServicesFromSide: TGroupBox;
    lblServicesFromSide: TLabel;
    edtServicesFromSide: TEdit;
    spbServicesFromSide: TSpeedButton;
    gbDddsCodes: TGroupBox;
    spbDdsCodes: TSpeedButton;
    edtDdsCodes: TEdit;
    HTTPRIOENTransportOrder: THTTPRIO;
    HTTPRIOENTransportOrder2TransportItem: THTTPRIO;
    tsTransportOrder: TTabSheet;
    ToolBar12: TToolBar;
    ToolButton76: TToolButton;
    sgGroupedTransportItem: TAdvStringGrid;
    sgDetailedTransportItem: TAdvStringGrid;
    Splitter5: TSplitter;
    Panel4: TPanel;
    chbDetailed: TCheckBox;
    btnSetTime: TButton;
    btnDeleteTime: TButton;
    btnUpdateTime: TButton;
    gbInvestProgramGroups: TGroupBox;
    edtInvestProgramGroupsName: TEdit;
    spbInvestProgramGroups: TSpeedButton;
    HTTPRIOENInvestProgramGroups: THTTPRIO;
    tsLinkedPlans: TTabSheet;
    sgLinkedPlans: TAdvStringGrid;
    actChangeCountFact: TAction;
    N23: TMenuItem;
    N24: TMenuItem;
    tsActsServicesFS: TTabSheet;
    ToolBar13: TToolBar;
    ToolButton75: TToolButton;
    ToolButton77: TToolButton;
    sgActsServicesFS: TAdvStringGrid;
    btnInsertWorkFromCalcul: TToolButton;
    actInsertWorkFromCalcul: TAction;
    btnSaveComment: TButton;
    actZeroPlanWorkItems: TAction;
    N25: TMenuItem;
    miZeroPlanWorkItems: TMenuItem;
    tsTransportRoute: TTabSheet;
    ToolBar14: TToolBar;
    ToolButton78: TToolButton;
    ToolButton79: TToolButton;
    ToolButton80: TToolButton;
    ToolButton81: TToolButton;
    ToolButton82: TToolButton;
    ToolButton83: TToolButton;
    ToolButton84: TToolButton;
    sgENTransportRoute: TAdvStringGrid;
    HTTPRIOENTransportRoute: THTTPRIO;
    HTTPRIOENDestinationPoint: THTTPRIO;
    grpRQFKOrder: TGroupBox;
    tsObjectDetail: TTabSheet;
    ToolBar15: TToolBar;
    ToolButton85: TToolButton;
    ToolButton86: TToolButton;
    sgObjectDetail: TAdvStringGrid;
    HTTPRIOENRouteBytDetail: THTTPRIO;
    actAddRQFKOrder2TransportRoute: TAction;
    TBToolbar1: TTBToolbar;
    TBItem1: TTBItem;
    TBItem4: TTBItem;
    sgRQFKOrder2Route: TAdvStringGrid;
    HTTPRIOENTransportRoute2RQFKOrder: THTTPRIO;
    Label9: TLabel;
    Label10: TLabel;
    edtAllTY: TEdit;
    edtComplTY: TEdit;
    actViewRQFKOrder2TransportRoute: TAction;
    actDeleteRQFKOrder2TransportRoute: TAction;
    tbsbmntm1: TTBSubmenuItem;
    tbtm1: TTBItem;
    tbtm2: TTBItem;
    actAddPrihodOrder2transportRoute: TAction;
    edtDateEndPriconnection: TDateTimePicker;
    lblDateEndPriconnection: TLabel;
    lblControler: TLabel;
    edtConroler: TEdit;
    HTTPRIOFINWorker: THTTPRIO;
    tbtm3: TTBItem;
    actAddTrInvoice2transportRoute: TAction;
    HTTPRIOENTransportRoute2RQTransportInvoice: THTTPRIO;
    HTTPRIORQTransportInvoice: THTTPRIO;
    tsRQTransportInvoice: TTabSheet;
    sgRQTransportInvoice: TAdvStringGrid;
    tlbTransportInvoice: TToolBar;
    btnView: TToolButton;
    btnAdd: TToolButton;
    btnDelete: TToolButton;
    btnEdit: TToolButton;
    btnUpdate: TToolButton;
    btnFilter: TToolButton;
    btnNoFilter: TToolButton;
    btnChangeInvestDescription: TButton;
    tsCustomerMaterials: TTabSheet;
    pnlCustomerMaterials: TPanel;
    tlb1: TToolBar;
    btnView1: TToolButton;
    btn3: TToolButton;
    btn4: TToolButton;
    btn5: TToolButton;
    btn6: TToolButton;
    btn9: TToolButton;
    pnlLegendCustomerMaterials: TPanel;
    shp1: TShape;
    shp2: TShape;
    lbl1: TLabel;
    lbl2: TLabel;
    sgCustomerMaterials: TAdvStringGrid;
    pnl3: TPanel;
    chbShowAllCustomerMaterials: TCheckBox;
    spl1: TSplitter;
    gbCustomerMaterialsFin: TGroupBox;
    sgCustomerMaterialsFin: TAdvStringGrid;
    btnCreateOrder: TButton;
    actCreateOrderByPlan: TAction;
    HTTPRIORQOrder: THTTPRIO;
    Panel5: TPanel;
    btnCreateOrderByPlanServices: TButton;
    actCreateOrderByServicesPlan: TAction;
    actChangePlanWorkItem: TAction;
    miChangePlanWorkItem: TMenuItem;
    N26: TMenuItem;
    btnTempCalc: TButton;
    sgTransportForDistance: TAdvStringGrid;
    spl2: TSplitter;
    tlbDistanceForTransport: TToolBar;
    btnAddDistanceForTransport: TToolButton;
    btnRemoveDistanceForTransport: TToolButton;
    actAddDistanceForTransport: TAction;
    actRemoveDistanceForTransport: TAction;
    miN27_trdept: TMenuItem;
    actAssignToTransportDept: TAction;
    actunAssignToTransportDept: TAction;
    miunAssignToTransportDept: TMenuItem;
    tsFINExecutor2Plan: TTabSheet;
    ToolBar16: TToolBar;
    ToolButton87: TToolButton;
    ToolButton88: TToolButton;
    ToolButton89: TToolButton;
    ToolButton90: TToolButton;
    ToolButton91: TToolButton;
    ToolButton92: TToolButton;
    ToolButton93: TToolButton;
    sgFINExecutor2Plan: TAdvStringGrid;
    HTTPRIOFINExecutor2Plan: THTTPRIO;
    HTTPRIOFINExecutor: THTTPRIO;
    HTTPRIOENServicesObject: THTTPRIO;
    gbInvestWorks: TGroupBox;
    gbInvestWorksDescription: TGroupBox;
    edtInvestWorksDescription: TMemo;
    gbInvestWorksAmount: TGroupBox;
    edtInvestWorksAmount: TEdit;
    tsCompletionPlan: TTabSheet;
    sgCompletionPlan: TAdvStringGrid;
    ActionListCompetationPlan: TActionList;
    actViewPlan: TAction;
    actEditPlan: TAction;
    actClosePlan: TAction;
    actMakeNPZ: TAction;
    actUnClose: TAction;
    pMenuCopletationPlan: TPopupMenu;
    MenuItem1: TMenuItem;
    MenuItem4: TMenuItem;
    MenuItem10: TMenuItem;
    miClosePlan: TMenuItem;
    MenuItem11: TMenuItem;
    miUnClose: TMenuItem;
    ToolBar17: TToolBar;
    ToolButton94: TToolButton;
    ToolButton96: TToolButton;
    ToolButton97: TToolButton;
    sgENEstimateItemWithFin: TAdvStringGrid;
    sgGSMWithFin: TAdvStringGrid;
    btnAddToTravelSheet: TButton;
    HTTPRIOENTravelSheet: THTTPRIO;
    edtDefectShortDescription: TMemo;
    lblDefectShortDescription: TLabel;
    edtDateGen: TEdit;
    actAddRQFKOrderFromRQWarehouse2TransportRoute: TAction;
    TBAddRQFKOrderFromRQWarehouse2TransportRoute: TTBItem;
    tbtm4: TTBItem;
    actAddRashodOrderToStorage2transportRoute: TAction;
    edtInvestItemNumber: TEdit;
    lblInvestItemNumber: TLabel;
    edtCommentGen: TMemo;
    btnAddWorkItemByShifr: TToolButton;
    btnAttachMaterials: TButton;
    grpMOLStorekeeper: TGroupBox;
    lblMOLStorekeeper: TLabel;
    spbMOLStorekeeper: TSpeedButton;
    spbMOLStorekeeperClear: TSpeedButton;
    edtMOLStorekeeper: TEdit;
    HTTPRIOENMOLSTOREKEEPER2PlanWork: THTTPRIO;
    lblIPImplementationType: TLabel;
    edtIPImplementationType: TEdit;
    spbIPImplementationType: TSpeedButton;
    HTTPRIOENIPImplementationType: THTTPRIO;
    tsCCDamageLog: TTabSheet;
    sgENPlanWork2CCDamageLog: TAdvStringGrid;
    HTTPRIOENPlanWork2CCDamageLog: THTTPRIO;
    chkCauseDisconnection: TCheckBox;
    tsWriteOffOS: TTabSheet;
    lblCCDamageLog: TLabel;
    edtKod_inv: TEdit;
    edtName_inv: TEdit;
    lblName_inv: TLabel;
    lblKod_inv: TLabel;
    spbOSSelect: TSpeedButton;
    HTTPRIOENPlanWorkENAct2OSData: THTTPRIO;
    btnOSDataSave: TBitBtn;
    gbTypeWriteOff: TGroupBox;
    rbTypeWriteOffFull: TRadioButton;
    rbTypeWriteOffPartly: TRadioButton;
    edtSumBuhWriteOZ: TEdit;
    lbledtSumBuhWriteOZ: TLabel;
    tsCCOutageNotice: TTabSheet;
    sgOutageNotice: TAdvStringGrid;
    HTTPRIOCCOutageNotice: THTTPRIO;
    HTTPRIOCCDamageLog: THTTPRIO;
    tsOwnProduction: TTabSheet;
    TBToolbar4: TTBToolbar;
    TBItem12: TTBItem;
    TBItem14: TTBItem;
    sgOwnProduction: TAdvStringGrid;
    actViewPlanWithOwnProduction: TAction;
    TBItem2: TTBItem;
    pmOwnProduction: TPopupMenu;
    N27: TMenuItem;
    N28: TMenuItem;
    N29: TMenuItem;
    HTTPRIOENPayment2SO: THTTPRIO;
    tsFuelHistory: TTabSheet;
    sgENPlanWorkFuelHistory: TAdvStringGrid;
    HTTPRIOENPlanWorkFuelHistory: THTTPRIO;
    tsFuelSheetVolumes: TTabSheet;
    gbFuelSheetVolItemData: TGroupBox;
    Splitter6: TSplitter;
    gbFuelSheetVolumes: TGroupBox;
    sgENFuelSheetVolumes: TAdvStringGrid;
    sgENFuelSheetVolItemData: TAdvStringGrid;
    HTTPRIOENFuelSheetVolItemData: THTTPRIO;
    HTTPRIOENFuelSheetVolumes: THTTPRIO;
    actViewFuelSheetVolumes: TAction;
    actZeroEstimateItems: TAction;
    N30: TMenuItem;
    gbSCSeal: TGroupBox;
    HTTPRIOSCSeal: THTTPRIO;
    sgSCSeal: TAdvStringGrid;
    actMoveWorkToAbstractPlan: TAction;
    actMoveWorkToAbstractPlan1: TMenuItem;
    actMoveWorkFromAbstractPlan: TAction;
    N31: TMenuItem;
    N32: TMenuItem;
    actMoveTransportFromWorkToOtherWork: TAction;
    miMoveTransport: TMenuItem;
    btnGraph: TToolButton;
    HTTPRIOENPlanWorkItem2Graph: THTTPRIO;
    HTTPRIOENPlanwork2GeneralContracts: THTTPRIO;
    edtPartner: TEdit;
    lblPartner: TLabel;
    HTTPRIOENGeneralContracts: THTTPRIO;
    tsProject: TTabSheet;
    sgENPlanProject: TAdvStringGrid;
    HTTPRIOENPlanProject: THTTPRIO;
    ImageListProject: TImageList;
    PopupMenuProject: TPopupMenu;
    N1viewproject: TMenuItem;
    N2insertproject: TMenuItem;
    N3deleteproject: TMenuItem;
    N4editproject: TMenuItem;
    N6updateproject: TMenuItem;
    N7filterproject: TMenuItem;
    N8nofilterproject: TMenuItem;
    ActionListProject: TActionList;
    actViewproject: TAction;
    actInsertproject: TAction;
    actDeleteproject: TAction;
    actEditproject: TAction;
    actUpdateproject: TAction;
    actFilterproject: TAction;
    actNoFilterproject: TAction;
    ToolBarProject: TToolBar;
    ToolButton1project: TToolButton;
    ToolButton6project: TToolButton;
    ToolButton7project: TToolButton;
    ToolButton8project: TToolButton;
    ToolButton11project: TToolButton;
    ToolButton2project: TToolButton;
    ToolButton3project: TToolButton;
    chbNeedsApprovalByCustomer: TCheckBox;
    HTTPRIOENIP: THTTPRIO;
    btnBindUnmountedCounter: TToolButton;
    actRemoveUnmountedCounter: TAction;
    mniRemoveUnmountedCounter: TMenuItem;
    actBindUnmountedCounter: TAction;
    tsDFDocs: TTabSheet;
    sgDFDocs: TAdvStringGrid;
    HTTPRIODFDoc2Plan: THTTPRIO;
    HTTPRIODFDoc: THTTPRIO;
    ToolBar18: TToolBar;
    ToolButton95: TToolButton;
    ToolButton101: TToolButton;
    actTransformMaterial: TAction;
    actTransformMaterial1: TMenuItem;
    tsXtqnPercent: TTabSheet;
    alXqtnPercent: TActionList;
    actInsertXqtnPercent: TAction;
    actDeleteXqtnPercent: TAction;
    actUpdateXqtnPercent: TAction;
    pmXqtnPercent: TPopupMenu;
    MenuItem2: TMenuItem;
    MenuItem3: TMenuItem;
    MenuItem5: TMenuItem;
    MenuItem6: TMenuItem;
    MenuItem7: TMenuItem;
    MenuItem8: TMenuItem;
    MenuItem9: TMenuItem;
    ToolBar19: TToolBar;
    ToolButton99: TToolButton;
    ToolButton100: TToolButton;
    ToolButton103: TToolButton;
    sgENPlanXqtnHistory: TAdvStringGrid;
    HTTPRIOENPlanXqtnHistory: THTTPRIO;
    edtInvestWorkStartDate: TDateTimePicker;
    lblInvestWorkStartDate: TLabel;
    btnInvestMeasurement: TSpeedButton;
    edtInvestMeasurementName: TEdit;
    lblInvestMeasurementName: TLabel;
    HTTPRIOTKMeasurement: THTTPRIO;
    HTTPRIOENIPItem2Plan: THTTPRIO;
    lblGeograph: TLabel;
    edtGeograph: TEdit;

  procedure FormShow(Sender: TObject);
  procedure FormClose(Sender: TObject; var Action: TCloseAction);

  procedure spbENPlanWorkStatusClick(Sender : TObject);
    procedure spbENElementClick(Sender: TObject);
    procedure pcPlanWorkChange(Sender: TObject);
    procedure actInsertExecute(Sender: TObject);

  procedure UpdateGrid(Sender: TObject);
    procedure actEditExecute(Sender: TObject);
    procedure actViewExecute(Sender: TObject);
    procedure actUpdateExecute(Sender: TObject);
    procedure btnPlanReportClick(Sender: TObject);
    procedure actDeleteExecute(Sender: TObject);
    procedure sgENPlanCorrectHistoryDblClick(Sender: TObject);
    procedure spbENBudgetClick(Sender: TObject);
    procedure spbResponsibilityClick(Sender: TObject);
    procedure spbDepartmentClick(Sender: TObject);
    procedure spbTypeClick(Sender: TObject);
    procedure edtDateStartClick(Sender: TObject);
    procedure actMaterialBindingExecute(Sender: TObject);
    procedure PopupMenu1Popup(Sender: TObject);
    procedure edtDateStartChange(Sender: TObject);
    procedure spbENPlanWorkStateClick(Sender: TObject);
    procedure spbENActClick(Sender: TObject);
    procedure updateFINMaterialsGrid(estimateItemCode : Integer; stringGrid : TAdvStringGrid);
    procedure sgENEstimateItemClick(Sender: TObject);
    procedure actMaterialBindingToFINExecute(Sender: TObject);
    procedure cbPlanWorkKindChange(Sender: TObject);
    procedure spbFINExecutorClick(Sender: TObject);

    ///
    function checkMaterialsBinding_(estimateItemCode : Integer): Boolean;
    procedure Shape1MouseDown(Sender: TObject; Button: TMouseButton;
      Shift: TShiftState; X, Y: Integer);
    procedure btnCloseAllClick(Sender: TObject);
    procedure btnWorkOrderDetailsClick(Sender: TObject);
    procedure actWorkOrderInsertExecute(Sender: TObject);
    procedure actWorkOrderEditExecute(Sender: TObject);
    ///

    procedure LoadWorkOrder;
    procedure actWorkOrderUpdateExecute(Sender: TObject);
    procedure spbFINMolClick(Sender: TObject);
    procedure btnWorkOrderSaveClick(Sender: TObject);
    procedure btnWorkOrderCancelClick(Sender: TObject);
    procedure spbFINMechanicClick(Sender: TObject);
    procedure pcPlanWorkChanging(Sender: TObject;
      var AllowChange: Boolean);
    procedure actWorkOrderDeleteExecute(Sender: TObject);
    procedure cbShowAllClick(Sender: TObject);
    procedure spbPlanMOLClick(Sender: TObject);
    procedure spbPlanMOLClearClick(Sender: TObject);
    procedure actAddToRQOrderExecute(Sender: TObject);
    procedure actRemoveFromRQOrderExecute(Sender: TObject);

    procedure LoadMOLData(workOrderCode : Integer);
    procedure actMOLDataInsertExecute(Sender: TObject);
    procedure actMOLDataDeleteExecute(Sender: TObject);
    procedure actMOLDataEditExecute(Sender: TObject);
    procedure actMOLDataViewExecute(Sender: TObject);
    procedure actMOLDataUpdateExecute(Sender: TObject);
    procedure spbBindingOverClick(Sender: TObject);
    procedure actExpToExcelExecute(Sender: TObject);
    procedure actExpToExcel_materialExecute(Sender: TObject);
    procedure actExpToExcelDemontajExecute(Sender: TObject);
    procedure actExpToExcelProducedExecute(Sender: TObject);
    procedure actExpToExcelHumanExecute(Sender: TObject);
    procedure actChangeEstimateItemStatusExecute(Sender: TObject);
    procedure actSelectAllExecute(Sender: TObject);
    procedure actClearAllExecute(Sender: TObject);
    procedure actChangeEstimateItemStatusPLANEDExecute(Sender: TObject);
    procedure updateMarksGrid(estimateItemCode : Integer; stringGrid : TAdvStringGrid);
    procedure actMoveTOExecute(Sender: TObject);
    procedure actMoveFromExecute(Sender: TObject);
    procedure cbENPlanWorkFormNameCloseUp(Sender: TObject);
    procedure spbReasonClick(Sender: TObject);
    procedure spbReasonClearClick(Sender: TObject);

    procedure updateSCCounterGrid(estimateItemCode : Integer; stringGrid : TAdvStringGrid );
    procedure updateSCSealGrid(estimateItemCode: Integer);

    procedure sgDismountClick(Sender: TObject);
    procedure btnConvertTranzit2OperativeClick(Sender: TObject);
    procedure btn1Click(Sender: TObject);
    procedure actFinWorkerAssignToAllExecute(Sender: TObject);
    procedure actFINWorkerAssignToAllTransportExecute(Sender: TObject);
    procedure actRealTransportAssignToAllExecute(Sender: TObject);
    procedure btnPrintWorkOrderClick(Sender: TObject);
    procedure spbServicesFromSideClick(Sender: TObject);
    procedure spbDdsCodesClick(Sender: TObject);
    procedure sgGroupedTransportItemClick(Sender: TObject);
    procedure chbDetailedClick(Sender: TObject);
    procedure btnSetTimeClick(Sender: TObject);
    procedure btnDeleteTimeClick(Sender: TObject);
    procedure btnUpdateTimeClick(Sender: TObject);
    procedure spbInvestProgramGroupsClick(Sender: TObject);
    procedure sgLinkedPlansDblClick(Sender: TObject);
    procedure actChangeCountFactExecute(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure actInsertWorkFromCalculExecute(Sender: TObject);
    procedure btnSaveCommentClick(Sender: TObject);
    procedure actZeroPlanWorkItemsExecute(Sender: TObject);
    procedure actAddRQFKOrder2TransportRouteExecute(Sender: TObject);
    procedure sgENTransportRouteClick(Sender: TObject);
    procedure actViewRQFKOrder2TransportRouteExecute(Sender: TObject);
    procedure actDeleteRQFKOrder2TransportRouteExecute(Sender: TObject);
    procedure actAddPrihodOrder2transportRouteExecute(Sender: TObject);
    procedure sgRQFKOrder2RouteDblClick(Sender: TObject);
    procedure actAddTrInvoice2transportRouteExecute(Sender: TObject);
    procedure btnChangeInvestDescriptionClick(Sender: TObject);
    procedure actCreateOrderByPlanExecute(Sender: TObject);
    procedure actCreateOrderByServicesPlanExecute(Sender: TObject);
    procedure actChangePlanWorkItemExecute(Sender: TObject);
    procedure btnTempCalcClick(Sender: TObject);
    procedure actAddDistanceForTransportExecute(Sender: TObject);
    procedure actRemoveDistanceForTransportExecute(Sender: TObject);
    procedure actAssignToTransportDeptExecute(Sender: TObject);
    procedure actunAssignToTransportDeptExecute(Sender: TObject);
    procedure actMakeNPZExecute(Sender: TObject);
    procedure pMenuCopletationPlanPopup(Sender: TObject);
    procedure actClosePlanExecute(Sender: TObject);
    procedure actUnCloseExecute(Sender: TObject);

    procedure UpdateENEstimateItemWithFinGrid(grid: TAdvStringGrid; estimateItemFilter: ENEstimateItemFilter);
    procedure btnAddToTravelSheetClick(Sender: TObject);
    procedure actAddRashodOrderToStorage2transportRouteExecute(
      Sender: TObject);
    procedure btnAddWorkItemByShifrClick(Sender: TObject);
    procedure btnAttachMaterialsClick(Sender: TObject);
    procedure spbMOLStorekeeperClick(Sender: TObject);
    procedure spbMOLStorekeeperClearClick(Sender: TObject);
    procedure spbIPImplementationTypeClick(Sender: TObject);
    procedure chkCauseDisconnectionClick(Sender: TObject);
    procedure setCauseDisconnection();
    procedure verifyCauseDisconnection();
    procedure spbOSSelectClick(Sender: TObject);
    procedure btnOSDataSaveClick(Sender: TObject);
    procedure actViewPlanWithOwnProductionExecute(Sender: TObject);
    procedure actViewFuelSheetVolumesExecute(Sender: TObject);
    procedure actZeroEstimateItemsExecute(Sender: TObject);
    procedure actMoveWorkToAbstractPlanExecute(Sender: TObject);
    procedure actMoveWorkFromAbstractPlanExecute(Sender: TObject);
    procedure actMoveTransportFromWorkToOtherWorkExecute(Sender: TObject);
    procedure btnGraphClick(Sender: TObject);
    procedure actViewprojectExecute(Sender: TObject);
    procedure actInsertprojectExecute(Sender: TObject);
    procedure actDeleteprojectExecute(Sender: TObject);
    procedure actEditprojectExecute(Sender: TObject);
    procedure actUpdateprojectExecute(Sender: TObject);
    procedure actRemoveUnmountedCounterExecute(Sender: TObject);
    procedure actBindUnmountedCounterExecute(Sender: TObject);
    procedure spbPriConnectionNumberClick(Sender: TObject);
    procedure sgENPlanProjectDblClick(Sender: TObject);
    procedure actTransformMaterialExecute(Sender: TObject);
    procedure actUpdateXqtnPercentExecute(Sender: TObject);
    procedure actInsertXqtnPercentExecute(Sender: TObject);
    procedure actDeleteXqtnPercentExecute(Sender: TObject);
    procedure actEditXqtnPercentExecute(Sender: TObject);
    procedure btnInvestMeasurementClick(Sender: TObject);
  private
    { Private declarations }
    KindCode : Integer;
    WorkOrderEditState: TDialogState;

    arrayOfFINMOLData : array of FINMOLData;

    procedure InitWorkOrderFields;
    procedure ClearWorkOrderFields;

    procedure UpdateOwnProductionList;
    procedure UpdateFuelHistoryList;
    procedure UpdateFuelSheetVolumes;
    procedure UpdateFuelSheetVolItemData;

    procedure updateDFDocs;

    procedure AdjustYearCombobox;
    {Выборка привязанных счетчиков по коду эстимейта}
    function getSCCountersListByEstimate(estimateItemCode : Integer) : SCCounterShortList;
  public
    { Public declarations }
      ENPlanWorkObj: ENPlanWork;
      techCondServicesObjCode, soElementCode, servicesObjCode: Integer;
      actCode , techCondServicesType, viewPlanWork : Integer;
      connectionKind: Integer; //загальні договори, стандартне приєднання, нестандартне приєднання
      addPlanItemsMode, isTransport, isSiz, isOperative, isWriteOffProtection,
      inServices, isTechConditions, isMeasurement, isGiftObj, isPriconnection,
      isForCopy: Boolean;
      ENPlanWorkENAct2OSDataObj : ENPlanWorkENAct2OSData;
      transformatorsForRaw : Boolean;
      isTechAgreement : Boolean;
      showTransportRoute : Boolean;
      isShiftLinesServices : Boolean;
      // SUPP-67561... +++ услуги для договоров подряда на выполнение ПКД...
      isServicesProject : Boolean;
      //procedure ChangeCaptions;
      isServicesFromSide : Boolean;
      ServicesFromSideCode : Integer;
      investMeasCode : Integer;
  end;

var
  frmENPlanWorkEdit: TfrmENPlanWorkEdit;
  //ENPlanWorkObj: ENPlanWork;

implementation

uses
  ShowENPlanWorkStatus
  ,ENPlanWorkStatusController
, ShowENElement, ENElementController, ENPlanWorkItemController,
  EditENPlanWorkItem, {ENEstimateItemController,}
  EditENEstimateItem, ENConsts, DMReportsUnit, ENPlanWorkTypeController,
  ENPlanWorkMoveHistoryController, ENPlanCorrectHistoryController,
  ShowEPDepartment, ENDepartmentController, ShowENDepartment,
  ENDepartmentTypeController
  ,ShowENPlanWorkType,ShowENPlanWorkState, EditENPlanWorkState //,EditENPLanWorkFilter

  , ENHumenItemController, ENTransportItemController,
  ENPlanWorkKindController
  , DateUtils
  , EditMaterialToPlanItemBinding, EditENHumenItem, EditENTransportItem,
  EditENPlanWorkStateFilter, ENPlanWorkStateController
  ,ShowENAct, ENAct2ENPlanWorkController, ENActStatusController,
  ENActController, FINMaterialsController, EditFINMaterialsData,
  FINMaterialsStatusController, FINExecutorController, ShowFINExecutor,
  ShowFINExecutorTree, ENWorkOrder2ENPlanWorkController, EditENWorkOrder,
  ENWorkOrderController, FINMolController, ShowFINMol,
  ENDepartment2EPRenController, ENEstimateItemKindController,
  ENMOL2PlanWorkController, ENMetrologyCounterController,
  EditENMetrologyCounter, RQOrderController, ShowRQOrder,
  RQOrderKindController, RQOrderStatusController, RQOrderItemController,
  EditFINMolData, EditENBindingOver, ENBindingOverController , Globals ,  ShellAPI,
  ShowENPlanWorkForm, ENPlanWorkFormController,
  EditENEstimateItemStatusChange,
  ENMarkEstimateController, EditENEstimateItem2ENEstimateItem, Main,
  ENPlanWorkSourceController, ENPlanWorkReasonController,
  ENPlanWork2PlanWorkReasonController, ShowENPlanWorkReason,
  EditENEstimateItem2ENEstimateItemSCCounter,
  EditSCCounter, EditConvertTranzit2Operative, RQFKOrderController,
  EditRQFKOrder, EditFINWorkerAssignToAll, FINWorkerController, FINWorkerKindController,
  EditRealTransportAssignToAll, TKTransportRealController,
  ShowFINServicesObject, ENServicesObjectController, ShowRQDDSCodes, RQDDSCodesController,
  ShowENInvestProgramGroups, ENInvestProgramGroupsController, EditENEstimateItemChangeCountFact,
  ENTransportOrderController, EditSetTimeToTransportItem,
  addENPlanworkItemByCalculationType, ENRouteBytDetailController,
  ENDestinationPointController, ENTransportRouteController, EditENTransportRoute,
  RQFKOrderKindController, ShowRQFKOrder, ENTransportRoute2RQFKOrderController,
  RQFKOrderStatusController, ENTransportRoute2RQTransportInvoiceController,
  RQTransportInvoiceController, ShowRQTransportInvoice, EditRQTransportInvoice,
  RQTransportInvoiceStatusController, AddDistance, ShowENTransportDepartment, 
  ENTransportDepartmentController, FINExecutor2PlanController, EditFINExecutor2Plan,
  FINExecutorTypeController, ENEstimateItemStatusController,
  EditFINMaterialsDataNew
  , ShowScanCounters
, AddWorkItemByShifr, BindingMaterialsFromRem,
  ENMOLSTOREKEEPER2PlanWorkController, ENIPImplementationTypeController,
  ShowENIPImplementationType, EditENIPImplementationType, EditMaterialsSCCounter,
  ostableController, Showostable, OSMolController, ShowOSMol,
  EditCCDamageLog, ENPayment2SOController, ENPlanWorkFuelHistoryController,
  ENFuelSheetVolItemDataController, ENFuelSheetVolumesController,
  EditENFuelSheetVolumes, SCSealController, SCSealStatusController,
  ShowENPlanWorkItem, EditSCSealsWriteOff, ENPlanWorkItem2GraphController,
  EditENPlanWorkItem2Graph, ENPlanwork2GeneralContractsController,
  ENGeneralContractsController, EditENPlanProject, EditSCCounterUninstall,
  ShowENServicesConnection, ENServicesContractKindController,
  ENServicesContractTypeController,
  DFDocController, DFDoc2PlanController, DFConsts, ShowDocumentManagement,
  ENServicesFromSideObjectController, ShowENServicesFromSideObject,
  EditENEstimateItemTransform, ENPlanXqtnHistoryController,
  EditENPlanXqtnHistory, ShowTKMeasurement, TKMeasurementController,
  ENIPItem2PlanController;


{uses
    EnergyproController, EnergyproController2, ENPlanWorkController  ;
}
{$R *.dfm}

var
  planItemFilter: ENPlanWorkItemFilter;
  estimateItemFilter: ENEstimateItemFilter;
  moveFilterObject : ENPlanWorkMoveHistoryFilter;
  corrFilterObject : ENPlanCorrectHistoryFilter;
  planServicesFilter : ENPlanWorkItemFilter;
  linkedPlansFilter: ENPlanWorkFilter;
  isEditComment:boolean=false;

  //ENAct2ENPlanWorkObj : ENAct2ENPlanWork;

  ENPlanProjectHeaders: array [1..5] of String =
        ( 'Код'
          ,'шифр проекта '
          ,'название проекта '
          ,'Користувач, який вніс зміни'
          ,'Дата зміни'
        );

    ENGroupedTransportItemHeaders: array [1..11] of String =
        ( 'Код'
          ,'Нормативний транспорт'
          ,'Час роботи з'
          ,'Дата роботи з'
          ,'Час роботи по'
          ,'Дата роботи по'
          ,'Транспорт штатний (найменування)'
          ,'Транспорт штатний (інвентарний номер)'
          ,'Транспорт штатний (гос номер)'
          ,'Користувач, що вніс зміни'
          ,'Дата останньої зміни'
          //,'Дата останньої зміни'
        );

        ObjectDetailHeaders: array [1..6] of String =
        ( 'Код'
          ,'Номер'
          ,'Наименование'
         ,'ТУ'
         ,'№ задания'
         ,'Статус'
        );

   ENDetailedTransportItemHeaders: array [1..5] of String =
        ( 'Код'
          ,'Нормативний транспорт'
          ,'Робота (код та найменування)'
          ,'Відстань, км'
          ,'Норма часу'
        );

  ENPlanWorkItemHeaders: array [1..12] of String =
        ( 'Код'
          ,'Код роботи'
          ,'Робота'
          ,'Примітка' // NET-4503
          ,'Кількість'
          ,'Норма часу на од.'
          ,'Всього часу'
          ,'Коеф.'
          ,'Час з коеф.'
          ,'Вимірювач'
          ,'Од. виміру'
          ,'Джерело нормативу'
          //,'Користувач, що вніс зміни'
          //,'Дата останньої зміни'
        );

  ENEstimateItemHeaders: array [1..11] of String =
        ( 'Код'
          ,'Матеріал'
          ,'Кількість нормативна'
          ,'Кількість скорегована'           // !!! используется при разноске с ФинКол !!!
          ,'Од. виміру'
          ,'Код роботи'
          ,'Робота'
          ,'Тип строки кошторису'
          ,'Статус'
          ,'Користувач, що вніс зміни'
          ,'Дата останньої зміни'
        );

  ENEstimateItemWithFinHeaders: array [1..9] of String =
        ( 'Код'
          ,'Матеріал (ПЛАН)'
          ,'Кількість нормативна'
          ,'Кількість скорегована'           // !!! используется при разноске с ФинКол !!!
          //,'Од. вим. (ПЛАН)'
          ,'Од. вим.'

          ,'Прив''язано' // Уже привязанное кол-во с подотчета

          ,'Матеріал (ФК)'
          ,'Номенкл. №'
          ,'Обов`язковий для виконання'

          //,'Од. вим. (ФК)'
        );


  ENDismountItemHeaders: array [1..8] of String =
        ( 'Код'
          ,'Матеріал'
          ,'Кількість'
          ,'Од. виміру'
          ,'Код роботи'
          ,'Робота'
//          ,'Тип строки кошторису'
          ,'Користувач, що вніс зміни'
          ,'Дата останньої зміни'
        );

  ENProducedItemHeaders: array [1..6] of String =
        ( 'Код'
          ,'Матеріал'
          ,'Кількість'
          ,'Од. виміру'
//          ,'Тип строки кошторису'
          ,'Користувач, що вніс зміни'
          ,'Дата останньої зміни'
        );

  FINMaterialsShortHeaders: array [1..15] of String =
        ( 'Код'
          ,'Матеріал'
          ,'Од. виміру'
          ,'Номенклатурний номер'
          ,'Кількість'
          ,'Ціна'
          ,'Вартість'
          ,'МОЛ'
          ,'Призначення залишку'
          ,'Постачальник'
          ,'Дата постачання'
          ,'Опис постачання'
          ,'код партії'
          //,'тип строки кошторису'
          ,'користувач, що вніс зміни'
          ,'дата останньої зміни'
        );


  ENPlanWorkMoveHistoryHeaders: array [1..7] of String =
        ( 'Код'
          ,'Дата зміни плану'
          ,'Попередній рік для виконання плану'
          ,'Попередній місяц для виконання плану'
          ,'Причина зміни'
          ,'Користувач що вніс зміни'
          ,'Дата останньої зміни'
        );

  ENPlanCorrectHistoryHeaders: array [1..5] of String =
        ( 'Код'
          ,'Дата коригування плану'
          ,'Причина коригування'
          ,'Користувач що вніс зміни'
          ,'Дата останньої зміни'
        );

  ENHumenItemHeaders: array [1..12] of String =
        ( 'Код'
          ,'Посада нормативна'
          ,'Посада штатна'
          ,'Робітник'
          ,'Норма часу нормативна'
          ,'Норма часу скорегована'
          ,'Код роботи'
          ,'Робота'
          ,'Ціна нормочасу'
          ,'Вартість нормочасу'
          ,'Користувач що вніс зміни'
          ,'Дата останньої зміни'
        );

     CCOutageNoticeHeaders: array [1..10] of String =
        ( 'Код'
          ,'Дата отключения плановая'
          ,'Дата включения плановая'
          ,'Согласовано, можно отключать: 1 - да, 0 - нет'
          ,'Дата/время создания'
          ,'Дата/время последнего изменения'
          ,'Лицевой счет'
          ,'Название абонента'
          ,'res'
          ,'Статус'
        );


   ENTransportItemHeaders: array [1..13] of String =
        ( 'Код'
          ,'Транспорт нормативний'
          ,'Транспорт штатний'
          ,'Робітник'
          ,'Норма часу нормативна'
          ,'Норма часу скорегована'
          ,'Код роботи'
          ,'Робота'
          //,'Ціна нормочасу'
          //,'Вартість нормочасу'
          ,'Тип транспорту'
          ,'Відстані, км'
          ,'Транспорт підрозділу'
          ,'Користувач що вніс зміни'
          ,'Дата останньої зміни'
        );

  FINMolDataHeaders: array [1..5] of String =
        ( 'Код'
          ,'код МОЛа из фин.кол.'
          ,'ФИО МОЛа с фин.кол.'
          ,'Мобільний номер'
          ,'Тип МОЛа'
        );

  MarksHeaders: array [1..2] of String =
        ( 'Код'
          ,'Назва маркеру'
        );


  SCCounterHeaders: array [1..16] of String =
        ( 'Код'
          ,'Інв. №'
          ,'Найменування'
          ,'Заводський №'
          ,'Рахунок'
          ,'Підрозділ'
          ,'Код МВО'
          ,'Дата приходу'
          ,'Дата випуску'
          ,'Дата повірки'
          ,'Вартість'
          ,'Код зі ScanCounters'
          ,'Тип лічильника'
          ,'№ наряду на встановлення'
          ,'Показники'
          ,'Дата останньої зміни'
        );

  RQFKOrderHeaders: array [1..18] of String =
        ( 'Код'
          ,'Номер'
          ,'Дата документу'
          ,'Код відправника'
          ,'ПІБ відправника'
          ,'Код одержувача'
          ,'ПІБ одержувача'
          ,'Статус'
          ,'Код експедитора'
          ,'ПІБ експедитора'
          ,'№ доручення'
          ,'Дата доручення'
          ,'ПІБ в дорученні'
          ,'Загальна вага, кг'
          ,'Створив'
          ,'Дата ств.'
          ,'Змінив'
          ,'Дата зміни'
        );

  ServicesItemHeaders: array [1..9] of String =
        ( 'Код'
          ,'Код послуги'
          ,'Послуга'
          ,'Джерело нормативу'
          ,'Кількість'
          ,'Вартість'
          ,'Вимірювач'
          ,'Предмет виконання робіт'
          ,'Статус'
        );

  ENLinkedPlansHeaders: array [1..10] of String =
        ( 'Код'
          ,'Рік плану'
          ,'Місяць плану'
          ,'Дата початку робіт'
          ,'Вид плану'
          ,'Статус'
          ,'Початк. місяць та рік плану (до перенесення)'
          ,'Номер наряда'
          ,'Користувач, що вніс зміни'
          ,'Дата останньої зміни'
        );

  ActsServicesFSHeaders: array [1..11] of String =
        ( 'Код'
          ,'Номер'
          ,'Дата документу'
          ,'Код постачальника'
          ,'Постачальник'
          ,'сума (без ПДВ)'
          ,'Статус'
          ,'Створив'
          ,'Дата ств.'
          ,'Змінив'
          ,'Дата зміни'
        );

  ENTransportRouteHeaders: array [1..9] of String =
        ( 'Код'
          ,'Откуда (код)'
          ,'Откуда (наименование)'
          ,'Куда (код)'
          ,'Куда (наименование)'
          ,'Дистанція, км.'
          ,'Маса, кг.'
          ,'Дата зміни'
          ,'користувач'
        );

  RQFKOrder2RouteHeaders: array [1..14] of String =
        ( 'Код'
          ,'Номер'
          ,'Дата документу'
          ,'Код відправника'
          ,'ПІБ відправника'
          ,'Код одержувача'
          ,'ПІБ одержувача'
          ,'Загальна вага, кг'
          ,'тип'
          ,'Створив'
          ,'Дата ств.'
          ,'Змінив'
          ,'Дата зміни'
          ,'код типу'
        );

         RQTransportInvoiceHeaders: array [1..8] of String =
        ( 'Код'
          ,'Номер'
          ,'Номер проекту'
          ,'Дата накладної'
          ,'загальна вага, кг'
          ,'статус'
          ,'користувач'
          ,'дата зміни'
        );

         ENTransportItemForDistanceHeaders: array [1..4] of String =
        ( 'Код'
          ,'Транспорт нормативний'
          ,'Дистанція до об''єкту'
          ,'Кол-во поїздок'
        );

        FINExecutor2PlanHeaders: array [1..13] of String =
        ( 'Код'
          ,'Виконавець'
          ,'Тип виконавця'
          ,'Відсоток зайнятості'
          ,'Дата початку виконання робіт'
          ,'Дата закінчення виконання робіт'
          ,'Час вик. робіт, год.'
          ,'Час вик. робіт, днів'
          ,'Час вик. робіт, люд/год.'
          ,'Час доставки, год.'
          ,'Бюджетотримач'
          ,'Користувач, який вніс зміни'
          ,'Дата зміни'
        );

  CompletionPlansHeaders: array [1..5] of String =
        ( 'Код'
          ,'Дата початку робіт'
          ,'Статус плану'
          ,'№ наряд-завдання'
          ,'№ акту'
        );

  ENPlanWork2CCDamageLogHeaders: array [1..8] of String =
        ( 'Код'
          ,'Код відключення в CallCenter'
          ,'Видання, де опубліковано'
          ,'Номер видання'
          ,'Дата публікації'
          ,'Планова дата відключення'
          ,'Планова дата завершення'
          ,'Погодження споживачем'
        );

  ENPlanOwnProductionHeaders: array [1..10] of String =
        ( 'Код'
          ,'Матеріал'
          ,'Кількість'
          ,'Од. виміру'
          //,'Код роботи'
          //,'Робота'
          ,'Об''єкт планування'
          ,'Інв. №'
          ,'Рік плану'
          ,'Місяць плану'
          ,'Дата початку робіт'
          ,'Підрозділ'
          //,'Користувач, що вніс зміни'
          //,'Дата останньої зміни'
        );

  ENPlanWorkFuelHistoryHeaders: array [1..7] of String =
        ( 'Код'
          ,'Версія запису'
          ,'Дата плана'
          ,'Тип ПММ'
          ,'Обсяг ПММ, л.'
          ,'Користувач, який створив запис'
          ,'Дата створення'
          //,'Користувач, який вніс зміни'
          //,'Дата зміни'
        );

  ENFuelSheetVolumesHeaders: array [1..9] of String =
        ( 'Код'
          ,'Назва'
          //,'Дата відомості'
          ,'Період'
          ,'Тип ПММ'
          ,'Статус'
          //,'Дата початку'
          //,'Дата закінчення'
          ,'Користувач, який створив запис'
          ,'Дата створення'
          ,'Користувач, який вніс зміни'
          ,'Дата зміни'
        );

  ENFuelSheetVolItemDataHeaders: array [1..3] of String =
        ( 'Код'
          ,'Тип ПММ'
          ,'Кількість, л'
        );

  SCSealHeaders: array [1..12] of String =
        ( 'Код'
          ,'Серійний номер'
          ,'Найменування'
          ,'Інв. номер'
          ,'Рахунок'
          ,'Підрозділ'
          ,'Код МВО'
          ,'Дата приходу'
          ,'Дата випуску'
          ,'Вартість'
          ,'код зі ScanCounter'
          ,'Місце зберігання'
        );

  DFDocHeaders: array [1..7] of String =
    ( 'Код'
      ,'Документ'
      ,'Дата документа'
      ,'Тема документа'
      ,'Тип документа'
      ,'Поточний стан документа'
      ,'Користувач, який додав запис'
    );

  xqtnColCount, xqtnLastCount: Integer;
  xqtnLastRow: Integer = 1;
  ENPlanXqtnHistoryHeaders: array [1..5] of String =
        ( 'Код'
          ,'Дата встановлення відсотку виконання плану'
          ,'Відсоток виконання плану'
          ,'пользователь внесший изменение'
          ,'дата последнего изменения'
        );

  iColCount, iLastCount: Integer;
  iLastRow: Integer = 1;

  eiColCount, eiLastCount: Integer;
  eiLastRow: Integer = 1;
  selectedRow : Integer;


procedure TfrmENPlanWorkEdit.FormShow(Sender: TObject);
var
 eFilter : ENElementFilter;
 eList : ENElementShortList;
 TempENElement: ENElementControllerSoapPort;
 TempENDepartment: ENDepartmentControllerSoapPort;
 typeObj : ENPlanWorkType;
 TempENPlanWorkType : ENPlanWorkTypeControllerSoapPort;
 TempENPlanWorkState : ENPlanWorkStateControllerSoapPort;

 TempENPlanWork: ENPlanWorkControllerSoapPort;

  TempENAct: ENActControllerSoapPort;
  //i: Integer;
  act2planFilter : ENAct2ENPlanWorkFilter;
  //frmENActShow : TfrmENActShow;
  TempENAct2ENPlanWork: ENAct2ENPlanWorkControllerSoapPort;
  ENAct2ENPlanWorkList: ENAct2ENPlanWorkShortList;

  //TempENPlanWorkState: ENPlanWorkStateControllerSoapPort;
  stateObj : ENPlanWorkState;


  TempENMOL2PlanWork: ENMOL2PlanWorkControllerSoapPort;
  ENMOL2PlanWorkFilterObj : ENMOL2PlanWorkFilter;
  ENMOL2PlanWorkList: ENMOL2PlanWorkShortList;

  TempFINWorker: FINWorkerControllerSoapPort;
  FINWorkerFilterObj:FINWorkerFilter;
  FINWorkerList:FINWorkerShortList;

  i, routeCode, codeMonthPlan : Integer;
  planKindName : String;

  TempENBindingOver: ENBindingOverControllerSoapPort;
  ENBindingOverFilterObj: ENBindingOverFilter;
  ENBindingOverList: ENBindingOverShortList;

  TempENPlanWorkReason: ENPlanWorkReasonControllerSoapPort;
  ENPlanWorkReasonList: ENPlanWorkReasonShortList;
  ENPlanWorkReasonFilterObj: ENPlanWorkReasonFilter;
  TempENPlanWorkItem : ENPlanWorkItemControllerSoapPort;
  element : ENElement;
  TempENTransportOrder : ENTransportOrderControllerSoapPort;

  TempENInvestProgramGroups : ENInvestProgramGroupsControllerSoapPort;
  investObj : ENInvestProgramGroups;

  TempENServicesObject: ENServicesObjectControllerSoapPort;
  servicesObjectFilter: ENServicesObjectFilter;
  servicesObject: ENServicesObject;
  servicesObjectArr: ENServicesObjectController.ArrayOfInteger;

  TempENMOLStorekeeper2PlanWork: ENMOLStorekeeper2PlanWorkControllerSoapPort;
  ENMOLStorekeeper2PlanWorkFilterObj : ENMOLStorekeeper2PlanWorkFilter;
  ENMOLStorekeeper2PlanWorkList: ENMOLstorekeeper2PlanWorkShortList;

  TempENIPImplementationType: ENIPImplementationTypeControllerSoapPort;
  ipImplementationTypeObj: ENIPImplementationType;

  TempENPlanWork2CCDamageLog : ENPlanWork2CCDamageLogControllerSoapPort;
  pw2damFilterObj : ENPlanWork2CCDamageLogFilter;
  pw2damArr : ENPlanWork2CCDamageLogController.ArrayOfInteger;
  pw2dam : ENPlanWork2CCDamageLogShort;


  TempENPlanWorkENAct2OSData: ENPlanWorkENAct2OSDataControllerSoapPort;
  ENPlanWorkENAct2OSDataList: ENPlanWorkENAct2OSDataShortList;
  ENPlanWorkENActosdatafilter : ENPlanWorkENAct2OSDataFilter;

  ownProductionFilterObj: ENPlanWorkFilter;
  ownProductionArr: ENPlanWorkController.ArrayOfInteger;

  TempENPlanWorkFuelHistory: ENPlanWorkFuelHistoryControllerSoapPort;
  fuelHistoryFilter: ENPlanWorkFuelHistoryFilter;
  fuelHistoryArr: ENPlanWorkFuelHistoryController.ArrayOfInteger;

  TempENFuelSheetVolItemData: ENFuelSheetVolItemDataControllerSoapPort;
  fuelSheetVolItemDataFilter: ENFuelSheetVolItemDataFilter;
  fuelSheetVolItemDataArr: ENFuelSheetVolItemDataController.ArrayOfInteger;

  TempENGeneralContracts: ENGeneralContractsControllerSoapPort;
  iGC: Integer;
  ENGeneralContractsList: ENGeneralContractsShortList;
  generalContractFil : ENGeneralContractsFilter;
  TempTKMeasurement : TKMeasurementControllerSoapPort;

  ///
  ipItem2planFilter : ENIPItem2PlanFilter;
  TempENIPItem2Plan: ENIPItem2PlanControllerSoapPort;
  ENIPItem2PlanList: ENIPItem2PlanShortList;
  ///

begin

  HideControls([ lblPartner ,  edtPartner ] );
  DisableControls( [lblPartner,edtPartner] );
  edtPartner.ReadOnly:=true;

  lblCCDamageLog.Visible := False;

  tsTransportRoute.TabVisible := False;
  tsRQTransportInvoice.TabVisible := False;
  tsCustomerMaterials.TabVisible := False;
  tsCompletionPlan.TabVisible := False;
  tsOwnProduction.TabVisible := False;
  tsFuelHistory.TabVisible := False;
  tsFuelSheetVolumes.TabVisible := False;
  tsProject.TabVisible := False;
  tsDFDocs.TabVisible := False;

  if (DialogState <> dsEdit) and (DialogState <> dsView) then
  begin
      chkCauseDisconnection.Visible:=False;
      tsCCDamageLog.TabVisible := False;
      tsWriteOffOS.TabVisible:= False;
  end;




  // 30.07.2014 +++ Если проставлен признак "С отключением потребителей"
  // выводим дату публикации
  if (DialogState = dsEdit) or (DialogState = dsView) then
  begin
    if (ENPlanWorkObj.causeDisconnection = ENPLANWORK_CAUSE_DISCONNECTION) then
    begin
      TempENPlanWork := HTTPRIOENPlanWork as ENPlanWorkControllerSoapPort;
      codeMonthPlan := TempENPlanWork.getMonthPlanCodeByAnyPlanCode(ENPlanWorkObj.code, false);

      if codeMonthPlan <> LOW_INT then begin
        TempENPlanWork2CCDamageLog := HTTPRIOENPlanWork2CCDamageLog as ENPlanWork2CCDamageLogControllerSoapPort;

        pw2damFilterObj := ENPlanWork2CCDamageLogFilter.Create;
        SetNullIntProps(pw2damFilterObj);
        SetNullXSProps(pw2damFilterObj);

        pw2damFilterObj.planRef := ENPlanWorkRef.Create();
        pw2damFilterObj.planRef.code := codeMonthPlan;
        pw2damFilterObj.orderBySQL := ' enplanwork2ccdamagelog.datefinish desc ';

        pw2damArr := TempENPlanWork2CCDamageLog.getScrollableFilteredCodeArray(pw2damFilterObj, 0, -1);

        if High(pw2damArr) > -1 then
        begin
          pw2dam := TempENPlanWork2CCDamageLog.getShortObject(pw2damArr[0]);
          lblCCDamageLog.Visible := True;
          lblCCDamageLog.Caption :=
            'Увага! Цей план належить до публікації №' + pw2dam.newsPaperNumber + ', від ' + XSDate2String(pw2dam.datePubl) + ' ("' + pw2dam.newsPaperName + '")';
        end;
      end;
    end;
  end;


  /////// вкладка "Виконання плану" - только на годовом или месячном ///////
  if (viewPlanWork = PLANWORKSHOW_LIGHT) and (ENPlanWorkObj <> nil) and (ENPlanWorkObj.kind <> nil)
        and (ENPlanWorkObj.kind.code in [ENPLANWORKKIND_CURRENT, ENPLANWORKKIND_YEAR]) then
  begin
    tsCompletionPlan.TabVisible := True;
    tsCompletionPlan.PageIndex := 1;
    tsLinkedPlans.TabVisible := False;
  end;


  if (ENPlanWorkObj <> nil) and (ENPlanWorkObj.kind <> nil)
        and (ENPlanWorkObj.kind.code in [ENPLANWORKKIND_NPZ, ENPLANWORKKIND_FACT]) then
     tsRQTransportInvoice.TabVisible := True;

  if (ENPlanWorkObj <> nil) and (ENPlanWorkObj.kind <> nil)
        and (ENPlanWorkObj.kind.code in [ENConsts.ENPLANWORKKIND_CURRENT,ENConsts.ENPLANWORKKIND_YEAR]) then
     tsRQTransportInvoice.TabVisible := False;

  if (ENPlanWorkObj.elementRef <> nil) and (ENPlanWorkObj.elementRef.code = CARGO_OBJECT) then
  begin
    isTransport := True;
    tsRQTransportInvoice.TabVisible := False;
  end;

  if (ENPlanWorkObj <> nil) and (ENPlanWorkObj.kind <> nil)
       and (ENPlanWorkObj.kind.code in [ENConsts.ENPLANWORKKIND_CURRENT, ENConsts.ENPLANWORKKIND_YEAR])
       and (ENPlanWorkObj.status <> nil)
       and (ENPlanWorkObj.status.code <> ENConsts.ENPLANWORKSTATUS_WORKS_FINISHED) then
  begin
    DisableActions([actAddDistanceForTransport, actRemoveDistanceForTransport], false);
    DisableControls([sgTransportForDistance, tlbDistanceForTransport], false);
    HideControls([sgTransportForDistance, tlbDistanceForTransport], false);
  end;

  isEditComment:=false;

  /////
  {
  if (HTTPRIOENPlanWork.HTTPWebNode.UserName = 'energynet') then
  begin
    HideControls([log2, memoData, log, btnCloseAll], false);
    DisableControls([log2, memoData, log, btnCloseAll], false);
    log2.Enabled := true;
    memoData.Enabled := true;
    log.Enabled := true;
  end;
  }
  /////

  ///// 13.10.11
  HideControls([btnConvertTranzit2Operative]);
  //if (HTTPRIOENPlanWork.HTTPWebNode.UserName = 'energynet') and (DialogState <> dsInsert) then
  //  HideControls([btnConvertTranzit2Operative], false);
  /////

  HideActions([actZeroPlanWorkItems, actChangeCountFact, actZeroEstimateItems, actSelectAll, actClearAll, actChangePlanWorkItem, actMoveWorkToAbstractPlan, actMoveWorkFromAbstractPlan, actMoveTransportFromWorkToOtherWork]);

  SetGridHeaders(FINMolDataHeaders, sgFINMolData.ColumnHeaders);
  SetGridHeaders(RQFKOrderHeaders, sgRQFKOrder.ColumnHeaders);
  SetGridHeaders(ActsServicesFSHeaders, sgActsServicesFS.ColumnHeaders);
  SetGridHeaders(ENTransportRouteHeaders, sgENTransportRoute.ColumnHeaders);
  SetGridHeaders(ObjectDetailHeaders, sgObjectDetail.ColumnHeaders);
  SetGridHeaders(RQFKOrder2RouteHeaders, sgRQFKOrder2Route.ColumnHeaders);
  SetGridHeaders(FINExecutor2PlanHeaders, sgFINExecutor2Plan.ColumnHeaders);

  SetGridHeaders(ENEstimateItemWithFinHeaders, sgENEstimateItemWithFin.ColumnHeaders);
  SetGridHeaders(ENEstimateItemWithFinHeaders, sgGSMWithFin.ColumnHeaders);

  SetGridHeaders(ENPlanOwnProductionHeaders, sgOwnProduction.ColumnHeaders);

  SetGridHeaders(ENPlanWorkFuelHistoryHeaders, sgENPlanWorkFuelHistory.ColumnHeaders);
  SetGridHeaders(ENFuelSheetVolumesHeaders, sgENFuelSheetVolumes.ColumnHeaders);
  SetGridHeaders(ENFuelSheetVolItemDataHeaders, sgENFuelSheetVolItemData.ColumnHeaders);

  SetGridHeaders(SCSealHeaders, sgSCSeal.ColumnHeaders);

  SetGridHeaders(DFDocHeaders, sgDFDocs.ColumnHeaders);

  SetFloatStyle([edtDistanceTo, edtDistanceAlong]);

  sgENEstimateItemWithFin.SortSettings.Show := false;
  sgGSMWithFin.SortSettings.Show := false;

  actCode := LOW_INT;

  KindCode := cbPlanWorkKind.ItemIndex + 1;

  if tsPlanWork.TabVisible then
    pcPlanWork.ActivePage := tsPlanWork;

  //HideControls([btnPlanReport]{, DialogState = dsInsert});
  DisableControls([edtEPRenName, edtENElementName, edtInvNumber
      ,edtENBudgetName
      ,edtResponsibility
      ,edtDepartment
      ,edtTypeName
      ,edtCode
      ,edtENActNumber
      ,edtWorkState
      ,edtFINExecutorName
       // залочим Тип плана (год, тек, нпз)
      ,cbPlanWorkKind
      ,edtWorkOrderCode

      , cbENPlanWorkSource
  ]);

  HideControls([lblENActNumber, edtENActNumber, spbENAct]); // только ФАКТ !!! ... акт покажем только для НПЗ или ФАКТА ...
  HideControls([gbPriConnection, lblPriConnectionNumber, edtPriConnectionNumber , edtDateEndPriconnection , lblDateEndPriconnection ]); // покажем только для Присоединения

  ////  21.05.2012 +++ Зміст робіт (інв.прг.)
  HideControls([gbInvestWorks, gbInvestWorksDescription, btnChangeInvestDescription]);
  ////  показываем только для подвида работ "Инвест программа"

  {06/03/2012 - закомментарим условие, для того, чтобы заявку можно было добавлять на плане}
  //HideControls([btnSetTime, btnUpdateTime, btnDeleteTime]); // прячем кнопку установки времени

  // 29.12.2011 +++ только для Услуг со стороны на наших объектах
  HideControls([gbServicesFromSide, lblServicesFromSide, edtServicesFromSide, spbServicesFromSide,
                gbDddsCodes, edtDdsCodes, spbDdsCodes,
                gbInvestProgramGroups, spbInvestProgramGroups]);

  HideControls([gbBindingOver , gbReason ]); // покажем только для ПриписУ
  DisableControls([edtBindingOver, spbBindingOver, edtDdsCodes, edtInvestProgramGroupsName, edtIPImplementationType]);

  // Кнопки для сохранения наряд-задания (показывать будем только при нажатии на "Редактировать" или "Добавить")
  HideControls([btnWorkOrderSave, btnWorkOrderCancel]);
  DisableControls([spbFINMol, spbFINMechanic]);

  DisableControls([edtMolName, spbPlanMOL, spbPlanMOLClear , edtMOLStorekeeper]);
  


  //HideControls([gbWorkOrder]); // покажем только для НПЗ-Плана/Факта
  tsWorkOrder.TabVisible := false;
  // Спрячем вкладку установки времени... покажем только на НПЗ-Плане
  tsTransportOrder.TabVisible := false;

  WorkOrderEditState := dsView;

  //HideControls([btnMaterialBinding], DialogState in [dsInsert, dsView]);

  tsRefinement.TabVisible := False;

  tsRQFKOrder.TabVisible := false;

  tsProduced.TabVisible := false; // будет отображаться только для планов с типом актом на виготовлення

  gbPlanMOL.Visible := false;

  tsServicesItem.TabVisible := false;

  // вкладка "Акты выполненных работ и услуг"
  // показываем только на месячном по Услугам со стороны
  tsActsServicesFS.TabVisible := False;

  tsFINExecutor2Plan.TabVisible := False;

  // 05.02.13 NET-4061, п. 2
  HideControls([sgENEstimateItemWithFin, sgGSMWithFin]); // покажем только для Задания-Плана/Факта

  if (isTransport) and (ENPlanWorkObj.elementRef.code = CARGO_OBJECT) then
  begin
    HideControls([lblCommentGen, edtCommentGen]);
    tsTransportRoute.TabVisible := True;
  end;

  if (showTransportRoute) then tsTransportRoute.TabVisible := True;


  if (DialogState = dsEdit) and (isSiz) then
       DisableActions([actInsert]);

  if (isSiz) then
   begin
     tsGSM.TabVisible := false;
     tsDismount.TabVisible := false;
     tsHumens.TabVisible := false;
     tsTransports.TabVisible := false;
   end;



  TempENElement :=  HTTPRIOENElement as ENElementControllerSoapPort;

  if (DialogState = dsInsert) or (DialogState = dsEdit) then
  begin
    //DisableControls([edtENElementName]);
    DenyBlankValues([
      edtYearGen
      ,edtMonthGen
      ,edtENElementName
      ,edtENBudgetName
      ,edtResponsibility
      ,edtDepartment
      ,edtDateStart
      ,edtDateFinal
      ,edtTypeName
      ,edtWorkState
     ]);
   end;

  if  (DialogState = dsInsert) then
  begin
     if ENPlanWorkObj.status = nil then ENPlanWorkObj.status := ENPlanWorkStatus.Create;
     ENPlanWorkObj.status.code := 1;

     tsPlanWorkItems.TabVisible := false;
     tsEstimateItems.TabVisible := false;
     tsMoves.TabVisible := false;
     tsCorrections.TabVisible := false;
     tsHumens.TabVisible := false;
     tsTransports.TabVisible := false;

     tsWorkOrder.TabVisible := false;
     tsDismount.TabVisible := false;
     tsProduced.TabVisible := false;
     tsGSM.TabVisible := false;
     tsServicesItem.TabVisible := false;
     tsTransportRoute.TabVisible := False;

     tsFINExecutor2Plan.TabVisible := False;

     edtDateStartClick(Sender);

     HideControls([lblENActNumber, edtENActNumber, spbENAct, lblPK, edtCode, btnSaveComment]);

     // при вставке можно поменять ТИП плана (год, тек, нпз)
     DisableControls([cbPlanWorkKind , spbType ], false);
     DisableControls([spbENPlanWorkState]);

     // скроем МОЛ кладовщика на вставке
     DisableControls([ spbMOLStorekeeper, spbMOLStorekeeperClear]);
     HideControls([grpMOLStorekeeper]);


  end;

  if (DialogState = dsEdit) then
  begin

    tsTransportRoute.TabVisible := (ENPlanWorkObj.elementRef.code = CARGO_OBJECT);
    if (showTransportRoute) then tsTransportRoute.TabVisible := True;

    if ENPlanWorkObj.kind.code in [ENPLANWORKKIND_NPZ, ENPLANWORKKIND_FACT] then
    begin
      //tsPlanWork.Enabled := false;

      DisableControls(tsTransportOrder,false);
      DisableControls([btnSetTime, btnUpdateTime, btnDeleteTime], false);
      tsTransportOrder.TabVisible := true;
      HideControls([btnSetTime, btnUpdateTime, btnDeleteTime], false);

    end;
    ///////////////
    // 16.01.2012 +++ можно без договора
    //if (ENPlanWorkObj.typeRef.code in [ENPLANWORKTYPE_SERVICES_FROM_SIDE, ENPLANWORKTYPE_SERVICES_FROM_SIDE_INVEST]) then
    //     DenyBlankValues([edtServicesFromSide]);
    ///////////////

    // 23.01.2012 +++ но нужно с ДДС
    if (ENPlanWorkObj.typeRef.code in [ENPLANWORKTYPE_SERVICES_FROM_SIDE, ENPLANWORKTYPE_SERVICES_FROM_SIDE_INVEST]) then
         DenyBlankValues([edtDdsCodes]);

    ///// 08.01.2014 Поля "Раздел ИнвестПрограммы" и "Пункт ИнвестПрограммы" показываем для всех подвидов работ по ИП!!!
    {
    if (ENPlanWorkObj.typeRef.code in [ENPLANWORKTYPE_SERVICES_FROM_SIDE_INVEST]) then
    }
    if (ENPlanWorkObj.typeRef.code in [ENPLANWORKTYPE_INVEST,
                                       ENPLANWORKTYPE_SERVICES_FROM_SIDE_INVEST,
                                       ENPLANWORKTYPE_ESBYT_PZ,
                                       ENPLANWORKTYPE_ESBYT_ZKO_106,
                                       ENPLANWORKTYPE_ESBYT_ZKO_111,
                                       ENPLANWORKTYPE_ESBYT_ZKO_112]) then
      begin
       // для проэктирования пункты ИП не обязательны
       if ENPlanWorkObj.stateRef.code <> ENPLANWORKSTATE_DESIGNING then
          DenyBlankValues([edtInvestProgramGroupsName, edtInvestItemNumber]);
      end;

    /////


    if ((ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_CN ) or (ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_VRTUVD_PROJECT))then // Присоединение
    begin
      DenyBlankValues([edtPriConnectionNumber]);
    end;

    if (((ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_CN ) or
        (ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_VRTUVD_PROJECT)) and  (ENPlanWorkObj.kind.code = ENPLANWORKKIND_CURRENT)   )then // Присоединение
    begin
      DenyBlankValues([edtDateEndPriconnection ]);
    end;

    if (((ENPlanWorkObj.kind.code = ENPLANWORKKIND_FACT) or ( ENPlanWorkObj.kind.code = ENPLANWORKKIND_NPZ ))
        and (ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_TRANSPORT) and (ENPlanWorkObj.elementRef.code <> CARGO_OBJECT))
    then
    begin
        DenyBlankValues([edtCommentGen]);
        lblCommentGen.Caption := 'Маршрут КУДИ'
    end;

    //if ENPlanWorkObj.kind.code = ENPLANWORKKIND_FACT then
    //  DenyBlankValues([edtWorkOrderNumber]);

    DisableControls([cbENPlanWorkFormName]);

    if ENPlanWorkObj.kind.code = ENPLANWORKKIND_FACT then
    begin
      //tsPlanWork.Enabled := false;

      DisableControls(tsPlanWork);
      DisableControls([edtDateStart, spbENAct, {gbWorkOrder,} edtCommentGen, spbFINExecutor, spbDepartment], false);
      DenyBlankValues([edtDepartment]);
      tsWorkOrder.TabVisible := true;

      ///////////////////
      //// Не будем пока на Факте давать выбирать договор - он скопируется с Месячного плана, если есть на нем.
      //// (А то как-то плохо будет, если договор на Факте будет, а на Месячном плане - нет)
      {
      // 14.09.12 NET-3027 Для актов вып. работ по Договорам Подряда (ДП) нужно тоже выбирать договор!
      if (ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_TMC_TRANSFER) then
      begin
        DisableControls([gbServicesFromSide], false);
      end;
      }
      ///////////////////
    end;

    if   ( (
            ENPlanWorkObj.kind.code = ENPLANWORKKIND_CURRENT  )
       or  //  NET-4383 в моле для разнарядке будем хранить мол с основных для списания ОС
            ( ENPlanWorkObj.stateRef.code = ENConsts.ENPLANWORKSTATE_WRITINGS_OS )
        )
     then
    begin
        gbPlanMOL.Visible := true;
        DisableControls([spbPlanMOL, spbPlanMOLClear , gbPlanMOL], false);

    end;

    if ENPlanWorkObj.stateRef.code = ENConsts.ENPLANWORKSTATE_WRITINGS_OS then
         begin
           gbPlanMOL.Caption := 'МВО на якій значиться основний засіб ';
           lblMolName.Caption := 'ФІО МВО'
         end;

    if ENPlanWorkObj.stateRef.code = ENConsts.ENPLANWORKSTATE_WRITINGS_OS then
       DisableControls([ btnOSDataSave ], false );

  end;

  if ((DialogState = dsEdit) and (isTransport)) then
  begin

    tsTransportRoute.TabVisible := (ENPlanWorkObj.elementRef.code = CARGO_OBJECT);
    if (showTransportRoute) then tsTransportRoute.TabVisible := True;

    if ((ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_CN ) or (ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_VRTUVD_PROJECT))then // Присоединение
      DenyBlankValues([edtPriConnectionNumber  ]);

    if (((ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_CN ) or
        (ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_VRTUVD_PROJECT)) and  (ENPlanWorkObj.kind.code = ENPLANWORKKIND_CURRENT)   )then // Присоединение
    begin
      DenyBlankValues([edtDateEndPriconnection ]);
    end;

    DisableControls([cbENPlanWorkFormName, edtTypeName, edtWorkState, spbType, spbENPlanWorkState,
       edtResponsibility, spbResponsibility]);

       //  18.08.2011  даём изменять бюджетодержателя
       //edtENBudgetName, edtResponsibility, spbENBudget, spbResponsibility]);

    tsEstimateItems.TabVisible := true;
    tsDismount.TabVisible := false;
    tsProduced.TabVisible := false;

    tsEstimateItems.Caption := 'Перевоз. матеріали';


    if ((ENPlanWorkObj.kind.code = ENPLANWORKKIND_FACT) or ( ENPlanWorkObj.kind.code = ENPLANWORKKIND_NPZ ))
          and (ENPlanWorkObj.elementRef.code <> CARGO_OBJECT) then
    begin
        DenyBlankValues([edtCommentGen]);
        lblCommentGen.Caption := 'Маршрут КУДИ'
    end;


    if ENPlanWorkObj.kind.code = ENPLANWORKKIND_FACT then
    begin
      DisableControls(tsPlanWork);
      DisableControls([edtDateStart, spbENAct, {gbWorkOrder,} edtCommentGen, spbFINExecutor, spbDepartment], false);
      DenyBlankValues([edtDepartment]);
      tsWorkOrder.TabVisible := true;
    end;

    if ENPlanWorkObj.kind.code = ENPLANWORKKIND_CURRENT then
    begin
        gbPlanMOL.Visible := true;
        DisableControls([spbPlanMOL, spbPlanMOLClear], false);
    end;
  end;

   if ((DialogState = dsEdit) and (isWriteOffProtection)) then
  begin
    DisableControls([tsPlanWork,tsGSM,tsDismount,tsRefinement,tsHumens,tsTransports,tsRQFKOrder]);
  end;


  if ((DialogState = dsEdit) and (isSiz)) then
    DisableControls([spbENElement, edtTypeName, spbType, edtWorkState, spbENPlanWorkState,
      edtENBudgetName, edtResponsibility, spbENBudget, spbResponsibility]);


  if (DialogState = dsView) then
  begin
    DisableControls([spbResponsibility ,spbENBudget, spbDepartment, spbType
                    //,spbENAct
                    ,spbENPlanWorkState
                    , spbFINExecutor
                    , tbWorkOrder
                    //, btnWorkOrderSave
                    //, btnWorkOrderCancel
                    ,spbServicesFromSide
                    ,spbDdsCodes
                    {06.03.2012 - заккоментарим кнопки для того, чтобы
                              люди могли добавлять заявки на утвержденных
                              планах}
                    //, btnSetTime
                    //, btnUpdateTime
                    //, btnDeleteTime
                    ,spbInvestProgramGroups
                    ,spbIPImplementationType
                    ]);

    DisableActions([actInsert, actEdit, actDelete, actMaterialBinding,
                    actWorkOrderInsert, actWorkOrderEdit, actWorkOrderDelete]);

    HideControls([btnWorkOrderSave, btnWorkOrderCancel]);
    DisableControls([cbShowAll, cbShowAllGSM, chbDetailed], false);

    if ENPlanWorkObj.kind.code = ENPLANWORKKIND_CURRENT then
    begin
        gbPlanMOL.Visible := true;
        DisableControls([edtMolName, spbPlanMOL, spbPlanMOLClear]);
    end;
  end;

  if  ((DialogState = dsView) and (ENPlanWorkObj.kind.code in [ENPLANWORKKIND_NPZ, ENPLANWORKKIND_FACT]))  then
        tsTransportOrder.TabVisible := true;

  if  ((DialogState = dsView) and (isTransport)) then
  begin
    DisableControls([spbResponsibility ,spbENBudget, spbDepartment, spbType
                    //,spbENAct
                    ,spbENPlanWorkState
                    , spbFINExecutor
                    , tbWorkOrder
                    ]);

    DisableActions([actInsert, actEdit, actDelete, actMaterialBinding,
                    actWorkOrderInsert, actWorkOrderEdit, actWorkOrderDelete,
                    actAddRQFKOrder2TransportRoute, actDeleteRQFKOrder2TransportRoute,
                    actAddPrihodOrder2transportRoute, actAddTrInvoice2transportRoute]);

    tsTransportRoute.TabVisible := (ENPlanWorkObj.elementRef.code = CARGO_OBJECT);
    if (showTransportRoute) then tsTransportRoute.TabVisible := True;

    tsCCOutageNotice.TabVisible := true;

    tsEstimateItems.TabVisible := true;
    tsDismount.TabVisible := false;

    tsEstimateItems.Caption := 'Перевоз. матеріали';

    HideControls([btnWorkOrderSave, btnWorkOrderCancel]);
    DisableControls([cbShowAll, cbShowAllGSM], false);

    if ENPlanWorkObj.kind.code =  ENPLANWORKKIND_CURRENT then
    begin
        gbPlanMOL.Visible := true;
        DisableControls([edtMolName, spbPlanMOL, spbPlanMOLClear]);
    end;
  end;

  if ((DialogState = dsView) and (isWriteOffProtection)) then
  begin
    DisableControls([tsPlanWork,tsGSM,tsDismount,tsRefinement,tsHumens,tsTransports,tsRQFKOrder]);
  end;

  if  ((DialogState = dsInsert) and (isOperative)) then
  begin
     edtDateStartClick(Sender);
     HideControls([lblENActNumber, edtENActNumber, spbENAct, lblPK, edtCode]);

     DisableControls([cbPlanWorkKind , spbType ], false);
     DisableControls([edtWorkState, spbENPlanWorkState]);

     ENPlanWorkObj.stateRef := ENPlanWorkStateRef.Create();
     ENPlanWorkObj.stateRef.code := ENPLANWORKSTATE_SIDEWORKS;
     edtWorkState.Text := 'Роботи на сторону';
  end;


  if  (DialogState = dsEdit) or (DialogState = dsView) then
  begin
    TempENServicesObject := HTTPRIOENServicesObject as ENServicesObjectControllerSoapPort;
    isServicesProject := TempENServicesObject.checkServicesProject(ENPlanWorkObj.elementRef.code);
  
    // 17.08.2012 +++ вкладка исполнители только на месячных планах...
    tsFINExecutor2Plan.TabVisible := (ENPlanWorkObj.kind.code = ENPLANWORKKIND_CURRENT); {and
                                     (HTTPRIOENPlanWork.HTTPWebNode.UserName = 'energynet'); // пока только для себя ;) }

    if ENPlanWorkObj.causeDisconnection=1 then
       chkCauseDisconnection.Checked:=True
    else
       chkCauseDisconnection.Checked:=false;

    chbNeedsApprovalByCustomer.Checked := DMReports.checkIfApprovalByCustomer(ENPlanWorkObj.code);
    chbNeedsApprovalByCustomer.Visible := chbNeedsApprovalByCustomer.Checked;
    DisableControls([chbNeedsApprovalByCustomer]);

   ///////////////////////////////////// 05.02.13 NET-4061, п. 2
   if (ENPlanWorkObj.kind.code = ENPLANWORKKIND_FACT) or (ENPlanWorkObj.kind.code = ENPLANWORKKIND_NPZ) then
   begin
     // Материалы
     HideControls([sgENEstimateItem, pnlAdditional]);
     HideControls([sgENEstimateItemWithFin], false);
     Panel1.Align := alClient;
     // ГСМ
     HideControls([sgGSM, gbFINGSM]);
     HideControls([sgGSMWithFin], false);
     pnlGSM.Align := alClient;
   end
   else begin
     // Материалы
     HideControls([sgENEstimateItem], false);
     HideControls([sgENEstimateItemWithFin]);
     // ГСМ
     HideControls([sgGSM], false);
     HideControls([sgGSMWithFin]);
   end;
   /////////////////////////////////////

   ///// 07.03.14 Для АВР не будем лишние поля выводить
   if (ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_AVR) then
     gbPlanMOL.Visible := false;
   /////


    // 04.07.2012 +++ показываем вкладку "Материалы Заказчика"
    // если это Факт и тип плана "Работы на сторону"
		if ((ENPlanWorkObj.kind.code = ENPLANWORKKIND_FACT) and (ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_SIDEWORKS))
      then tsCustomerMaterials.TabVisible := True;

   //////////////////////////////////// 06.11.12 NET-3079
	 if ((ENPlanWorkObj.kind.code = ENPLANWORKKIND_CURRENT) and (ENPlanWorkObj.stateRef.code = ENPLANWORKSTATE_SIDEWORKS)) then begin
     element := TempENElement.getObject(ENPlanWorkObj.elementRef.code);
     if (element.typeRef.code = EN_SERVICES_OBJECT) then begin
       TempENServicesObject := HTTPRIOENServicesObject as ENServicesObjectControllerSoapPort;

       servicesObjectFilter := ENServicesObjectFilter.Create;
       SetNullIntProps(servicesObjectFilter);
       SetNullXSProps(servicesObjectFilter);
       servicesObjectFilter.conditionSQL := 'elementcode = ' + IntToStr(element.code);

       servicesObjectArr := TempENServicesObject.getScrollableFilteredCodeArray(servicesObjectFilter, 0, -1);

       if High(servicesObjectArr) > -1 then begin
         servicesObject := TempENServicesObject.getObject(servicesObjectArr[0]);
         if servicesObject <> nil then
           if servicesObject.isCustomerMaterials = 1 then
             tsCustomerMaterials.TabVisible := true;
       end;
     end;
   end;

 // 15.04.2019 Для планов которые связаны с договорами на выполнение работ тоже откроем вкладку
  if (ENPlanWorkObj.kind.code = ENPLANWORKKIND_CURRENT) then begin
    TempENServicesObject := HTTPRIOENServicesObject as ENServicesObjectControllerSoapPort;
	servicesObjectFilter := ENServicesObjectFilter.Create;
    SetNullIntProps(servicesObjectFilter);
    SetNullXSProps(servicesObjectFilter);
    servicesObjectFilter.conditionSQL := 'exists (select 1 from enservices2plan as sp1 where sp1.planrefcode = ' + IntToStr(ENPlanWorkObj.code) 
	  + ' and sp1.servicesobjectrefcode = enservicesobject.code)' ;
       servicesObjectArr := TempENServicesObject.getScrollableFilteredCodeArray(servicesObjectFilter, 0, -1);

       if High(servicesObjectArr) > -1 then begin
         servicesObject := TempENServicesObject.getObject(servicesObjectArr[0]);
         if servicesObject <> nil then
           if servicesObject.isCustomerMaterials = 1 then
             tsCustomerMaterials.TabVisible := true;
       end;
  end;

   ////////////////////////////////////


   ////  21.05.2012 +++ Зміст робіт (інв.прг.)
    HideControls([gbInvestWorks, gbInvestWorksDescription], not (ENPlanWorkObj.typeRef.code in [ENPLANWORKTYPE_INVEST, ENPLANWORKTYPE_SERVICES_FROM_SIDE_INVEST]));
    if (DialogState = dsEdit) then
    begin
      DenyBlankValues([edtInvestWorksDescription, edtInvestWorksAmount, edtInvestWorkStartDate], (ENPlanWorkObj.typeRef.code in [ENPLANWORKTYPE_INVEST,ENPLANWORKTYPE_SERVICES_FROM_SIDE_INVEST]));
    end;
   ////  показываем только для подвида работ "Инвест программа"

   ////  при просмотре, для утвержденных, с подвидом работ "Инвест программа"
   if (DialogState = dsView) and
        ((ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_INVEST) or (ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_SERVICES_FROM_SIDE_INVEST))
      and (ENPlanWorkObj.status.code = ENPLANWORKSTATUS_LOCKED) then
     HideControls([btnChangeInvestDescription], False);


    if (
        (
          (ENPlanWorkObj.stateRef.code = ENPLANWORKSTATE_REFINEMENT_BY_CONTRACT)
          or (ENPlanWorkObj.stateRef.code = ENPLANWORKSTATE_REFINEMENT)
          or (ENPlanWorkObj.stateRef.code = ENPLANWORKSTATE_TMC_TRANSFER)
        )
        and (ENPlanWorkObj.kind.code = ENPLANWORKKIND_FACT)
        // и НЕ метрология ..
        // проверим на серевере потом ...
       ) then
    begin
      tsRefinement.TabVisible := True;
    end;

    /// 03.11.11 NET-861
    if (ENPlanWorkObj.kind.code = ENPLANWORKKIND_CURRENT) and
       (ENPlanWorkObj.status.code = ENPLANWORKSTATUS_WORKS_FINISHED) then
    begin
      tsRQFKOrder.TabVisible := true;
    end;
    ///

    // Для списаний и ПВЗ - прячем лишние вкладки
    //
    if (ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_WRITING_OFF)
       or (ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_RESTOCKING)
    then
      for i := 0 to pcPlanWork.PageCount - 1 do
        if (pcPlanWork.Pages[i] <> tsPlanWork) and
           (pcPlanWork.Pages[i] <> tsWorkOrder) and
           (pcPlanWork.Pages[i] <> tsEstimateItems) and
           (pcPlanWork.Pages[i] <> tsCorrections) and
           (pcPlanWork.Pages[i] <> tsLinkedPlans)
        then
          pcPlanWork.Pages[i].TabVisible := false;

    // 05.12.11 Для списаний МБП и МНМА нужно показывать вкладку "Демонтаж"
    // NET-1026 29.05.2014 Для списаний счетчиков нужно показывать вкладку "Демонтаж"
    if (ENPlanWorkObj.stateRef.code in [ENPLANWORKSTATE_MATERIALS_MBP, ENPLANWORKSTATE_MATERIALS_MBP_INSTRUMENTS]) or
       (ENPlanWorkObj.stateRef.code = ENPLANWORKSTATE_MATERIALS_MNMA)
       or (ENPlanWorkObj.stateRef.code = ENPLANWORKSTATE_COUNTERS_WRITEOFF)
       or (ENPlanWorkObj.stateRef.code = ENPLANWORKSTATE_MATERIALS_TMC) then
      tsDismount.TabVisible := true;

    if (ENPlanWorkObj.elementRef.code = ENConsts.ENELEMENTCODE_FUEL_INVENTARIZATION_WRITE_OFF) then
        tsGSM.TabVisible := True;

    if isTransport then
     begin
       tsEstimateItems.TabVisible := true;
       tsDismount.TabVisible := false;
     end;

     routeCode := DMReports.getRouteCodeByPlan(ENPlanWorkObj.code);

     if (routeCode <> Low_Int)
         and (ENPlanWorkObj.kind.code = ENPLANWORKKIND_FACT)
         and (ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_TRANSPORT) then
     begin
       tsTransportRoute.TabVisible := true;
       HideControls([lblCommentGen, edtCommentGen]);
     end;

     if (showTransportRoute) then
     begin
       tsTransportRoute.TabVisible := true;
       HideControls([lblCommentGen, edtCommentGen]);
     end;


     ////////////
     //if (ENPlanWorkObj.kind.code = ENPLANWORKKIND_FACT) and
     //   (ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_TRANSPORT) then
     //begin
     //  element := TempENElement.getObject(ENPlanWorkObj.elementRef.code);
     //  if (element.typeRef.code = EN_TRANSPORT) then
     //    tsTransportRoute.TabVisible := true;
     //end;
     ////////////


    // 12.12.2011 +++  Для "Услуг со стороны" - прячем лишние вкладки
    if (ENPlanWorkObj.typeRef.code in [ENPLANWORKTYPE_SERVICES_FROM_SIDE, ENPLANWORKTYPE_SERVICES_FROM_SIDE_INVEST]) then
    begin
      for i := 0 to pcPlanWork.PageCount - 1 do
        if (pcPlanWork.Pages[i] <> tsPlanWork) and
           (pcPlanWork.Pages[i] <> tsPlanWorkItems) and
           (pcPlanWork.Pages[i] <> tsServicesItem) and
           (pcPlanWork.Pages[i] <> tsEstimateItems) and
           (pcPlanWork.Pages[i] <> tsCorrections) and
           (pcPlanWork.Pages[i] <> tsLinkedPlans) then
        begin
          pcPlanWork.Pages[i].TabVisible := false;
          //tsPlanWorkItems.Caption := 'Перелік послуг';
        end;
      tsServicesItem.TabVisible := True;
      DisableControls([edtTypeName, spbType]);
      inServices := True;
    end;

    // 17.03.2012 +++  планы для закупки покрышек и аккумуляторов - прячем лишние вкладки
    if (ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_TRANSPORT_SERVICES) then
     for i := 0 to pcPlanWork.PageCount - 1 do
       if (pcPlanWork.Pages[i] <> tsPlanWork) and
          (pcPlanWork.Pages[i] <> tsEstimateItems) then
        pcPlanWork.Pages[i].TabVisible := false;

    // 15.07.2012 +++  планы по договорам продажи товаров
    if (ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_SALE_PRODUCTS) then
     for i := 0 to pcPlanWork.PageCount - 1 do
       if (pcPlanWork.Pages[i] <> tsPlanWork) and
          (pcPlanWork.Pages[i] <> tsEstimateItems) then
        pcPlanWork.Pages[i].TabVisible := false;

    if (ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_SALE_PRODUCTS) then
      DisableControls([spbType, spbENPlanWorkState], (DialogState <> dsInsert));    


    if (((ENPlanWorkObj.kind.code = ENPLANWORKKIND_FACT) or ( ENPlanWorkObj.kind.code = ENPLANWORKKIND_NPZ ))
       and (ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_TRANSPORT) and (ENPlanWorkObj.elementRef.code <> CARGO_OBJECT))
    then
    begin
       DenyBlankValues([edtCommentGen]);
       lblCommentGen.Caption := 'Маршрут КУДИ'
    end;

    TempENPlanWork := HTTPRIOENPlanWork as ENPlanWorkControllerSoapPort;

    // 03.12.14 NET-4415 Показываем вкладку только для автоматических планов на изготовление
    if (ENPlanWorkObj.kind.code in [ENConsts.ENPLANWORKKIND_CURRENT, ENConsts.ENPLANWORKKIND_YEAR]) and
       (ENPlanWorkObj.stateRef.code = ENPLANWORKSTATE_PRODUCTION) then
    begin
      //TempENPlanWork := HTTPRIOENPlanWork as ENPlanWorkControllerSoapPort;

      // Ищем связанный план на изготовление
      ownProductionFilterObj := ENPlanWorkFilter.Create;
      SetNullIntProps(ownProductionFilterObj);
      SetNullXSProps(ownProductionFilterObj);

      ownProductionFilterObj.conditionSQL :=
          ' ENPLANWORK.CODE in ' +
          '   (select ep.planrefcode ' +
          '      from enestimateitem2plan ep ' +
          '     where ep.planrefcode = ' + IntToStr(ENPlanWorkObj.code) +
          '       and ep.typerefcode = ' + IntToStr(ENESTIMATEITEM2PLANTYPE_OWN_PRODUCTION) + ')';

      ownProductionArr := TempENPlanWork.getFilteredCodeArray(ownProductionFilterObj);

      tsOwnProduction.TabVisible := High(ownProductionArr) >= 0;
    end;

    // 15.07.2020 SUPP-92662 Заявки на ремонт транспорта
    if (ENPlanWorkObj.kind.code = ENPLANWORKKIND_CURRENT) then
    begin
      tsDFDocs.TabVisible := TempENPlanWork.isPlanForRepairRequest(ENPlanWorkObj);
    end;

    ////////////////////////////////////////////////////////////////////////////
    // 02.03.15 NET-4440 История изменения объемов ГСМ
    TempENPlanWorkFuelHistory := HTTPRIOENPlanWorkFuelHistory as ENPlanWorkFuelHistoryControllerSoapPort;

    fuelHistoryFilter := ENPlanWorkFuelHistoryFilter.Create;
    SetNullIntProps(fuelHistoryFilter);
    SetNullXSProps(fuelHistoryFilter);

    fuelHistoryFilter.planRef := ENPlanWorkRef.Create;
    fuelHistoryFilter.planRef.code := ENPlanWorkObj.code;

    //fuelHistoryFilter.orderBySQL := 'ENPLANWORKFUELHISTORY.VERSION, ENPLANWORKFUELHISTORY.DATEGEN, ENPLANWORKFUELHISTORY.CODE';

    fuelHistoryArr := TempENPlanWorkFuelHistory.getScrollableFilteredCodeArray(fuelHistoryFilter, 0, -1);

    tsFuelHistory.TabVisible := High(fuelHistoryArr) >= 0;
    ////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////////////////////////////////////////////
    // 16.03.15 NET-4440 Плановые ведомости выдачи ГСМ
    TempENFuelSheetVolItemData := HTTPRIOENFuelSheetVolItemData as ENFuelSheetVolItemDataControllerSoapPort;

    fuelSheetVolItemDataFilter := ENFuelSheetVolItemDataFilter.Create;
    SetNullIntProps(fuelSheetVolItemDataFilter);
    SetNullXSProps(fuelSheetVolItemDataFilter);

    fuelSheetVolItemDataFilter.plan_code := ENPlanWorkObj.code;

    fuelSheetVolItemDataArr := TempENFuelSheetVolItemData.getScrollableFilteredCodeArray(fuelSheetVolItemDataFilter, 0, -1);

    tsFuelSheetVolumes.TabVisible := High(fuelSheetVolItemDataArr) >= 0;
    ////////////////////////////////////////////////////////////////////////////

    tsProject.TabVisible:= ( ENPlanWorkObj.stateRef.code = ENPLANWORKSTATE_DESIGNING );

    edtCode.Text := IntToStr(ENPlanWorkObj.code);

    KindCode := ENPlanWorkObj.kind.code;

    if (ENPlanWorkObj.typeRef.code in [ENPLANWORKTYPE_SERVICES_FROM_SIDE, ENPLANWORKTYPE_SERVICES_FROM_SIDE_INVEST]) then
    begin
      // 13.09.12
      gbServicesFromSide.Caption := 'Послуги';
      
      HideControls([gbServicesFromSide, lblServicesFromSide, edtServicesFromSide, spbServicesFromSide,
                       gbDddsCodes, edtDdsCodes, spbDdsCodes], false);
       if (DialogState = dsEdit) then DenyBlankValues([edtDdsCodes]);

       // 24.02.2012 +++ Услуги со стороны Кап.строительство
       if (ENPlanWorkObj.stateRef.code = ENPLANWORKSTATE_CAPITALBUILDER)
            or (ENPlanWorkObj.stateRef.code = ENPLANWORKSTATE_RECONSTRUCTION)
            or (ENPlanWorkObj.stateRef.code = ENPLANWORKSTATE_INSTALLATION_REINSTALLATION) then
       begin
         HideControls([gbPriConnection, lblPriConnectionNumber, edtPriConnectionNumber , edtDateEndPriconnection , lblDateEndPriconnection ], False);
         if (DialogState = dsEdit) then DenyBlankValues([edtPriConnectionNumber  ]);
         if ((DialogState = dsEdit) and (ENPlanWorkObj.kind.code = ENPLANWORKKIND_CURRENT )  ) then
           begin
            DenyBlankValues([edtDateEndPriconnection ]);
            // DisableControls([edtDateEndPriconnection],false);
           end;
         gbPriConnection.Top := 160;
         gbPriConnection.Left := 617;
       end;

       if (KindCode = ENPLANWORKKIND_CURRENT) then tsActsServicesFS.TabVisible := True;

    end;

    ///////////////////
    // 13.09.12 NET-3027 Для актов вып. работ по Договорам Подряда (ДП) нужно тоже выбирать договор!
    if (ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_TMC_TRANSFER) then
    begin
      HideControls([gbServicesFromSide, lblServicesFromSide, edtServicesFromSide, spbServicesFromSide], false);

      // engeneralcontract для плана
      HideControls([ lblPartner ,  edtPartner ] , false);
      // DisableControls([lblPartner,edtPartner],false);

      generalContractFil  := ENGeneralContractsFilter.Create;
      SetNullIntProps(generalContractFil);
      SetNullXSProps(generalContractFil);
      generalContractFil.conditionSQL := ' engeneralcontracts.code in (select p2g.generalcontractrefcode from enplanwork2gnrlcntrcts p2g where p2g.planrefcode= '+ IntToStr(ENPlanWorkObj.code) + ')';
      TempENGeneralContracts :=  HTTPRIOENGeneralContracts as ENGeneralContractsControllerSoapPort;
      ENGeneralContractsList := TempENGeneralContracts.getScrollableFilteredList(generalContractFil,0,-1);
      if(High(ENGeneralContractsList.list) > -1 ) then
       begin
        ENPlanWorkObj.partnerCode:= ENGeneralContractsList.list[0].partnerCode;
        ENPlanWorkObj.finDocCode:= ENGeneralContractsList.list[0].finDocCode;
        edtPartner.text:=  ENGeneralContractsList.list[0].partnerName;
       end;
      //zzz

 
      DisableControls([edtServicesFromSide]);
      gbServicesFromSide.Caption := 'Договір підряду';
    end;
    ///////////////////

    ///// 08.01.2014 Поля "Раздел ИнвестПрограммы" и "Пункт ИнвестПрограммы" показываем для всех подвидов работ по ИП!!!
    {
    if (ENPlanWorkObj.typeRef.code in [ENPLANWORKTYPE_SERVICES_FROM_SIDE_INVEST]) then
    begin
      HideControls([gbInvestProgramGroups, spbInvestProgramGroups], false);
       if (DialogState = dsEdit) then DenyBlankValues([edtInvestProgramGroupsName]);
    end;
    }
    if (ENPlanWorkObj.typeRef.code in [ENPLANWORKTYPE_INVEST,
                                       ENPLANWORKTYPE_SERVICES_FROM_SIDE_INVEST,
                                       ENPLANWORKTYPE_ESBYT_PZ,
                                       ENPLANWORKTYPE_ESBYT_ZKO_106,
                                       ENPLANWORKTYPE_ESBYT_ZKO_111,
                                       ENPLANWORKTYPE_ESBYT_ZKO_112]) then
    begin
      // 09.01.14 Только для Месячных планов
      if (ENPlanWorkObj.kind.code = ENPLANWORKKIND_CURRENT) then
      begin
         // 04.02.14 Если планы ВРТУ, то это не ИнвестПрограмма, поэтому поля по ИП не показываем
        if ENPlanWorkObj.budgetRef <> nil then
        begin
          if (ENPlanWorkObj.budgetRef.code <> ENBUDGET_VRTUVD) then
            HideControls([gbInvestProgramGroups, spbInvestProgramGroups], false);
        end
        else
          HideControls([gbInvestProgramGroups, spbInvestProgramGroups], false);

        if (ENPlanWorkObj.typeRef.code in [ENPLANWORKTYPE_INVEST, // для таких в любом случае показываем
                                           ENPLANWORKTYPE_SERVICES_FROM_SIDE_INVEST]) then
          begin
            HideControls([gbInvestProgramGroups, spbInvestProgramGroups], false);
            tsXtqnPercent.TabVisible := true;
          end;

        if (DialogState = dsEdit) then DenyBlankValues([edtInvestProgramGroupsName, edtInvestItemNumber]);
      end;
    end;
    /////


    ///
    if ((ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_CN ) or (ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_VRTUVD_PROJECT))then begin  // Присоединение
      HideControls([gbPriConnection, lblPriConnectionNumber, edtPriConnectionNumber , edtDateEndPriconnection , lblDateEndPriconnection ], false);
      DisableControls([ edtDateEndPriconnection],false);
      end;

    // при редактировании предписания - вывести ввод данных
   if ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_PRIPIS then // Припис
   begin
     HideControls([gbBindingOver], false);
     DisableControls([spbBindingOver, edtBindingOver], not (DialogState = dsEdit));
     if (DialogState = dsEdit) then DenyBlankValues([edtBindingOver]);

     // + вычитать данные ...
     ENBindingOverFilterObj := ENBindingOverFilter.Create;
     SetNullIntProps(ENBindingOverFilterObj);
     SetNullXSProps(ENBindingOverFilterObj);
     ENBindingOverFilterObj.planRef := ENPlanWorkRef.Create;
     ENBindingOverFilterObj.planRef.code := ENPlanWorkObj.code;

     TempENBindingOver := HTTPRIOENBindingOver as ENBindingOverControllerSoapPort;
     ENBindingOverList := TempENBindingOver.getScrollableFilteredList(ENBindingOverFilterObj,0, -1);
     if ENBindingOverList.totalCount > 0 then
     begin
        edtBindingOver.Text := '№' + ENBindingOverList.list[0].numberGen + ', від ' + XSDate2String(ENBindingOverList.list[0].dateGen) + ', пункт ' + ENBindingOverList.list[0].itemText;
        //edtBindingOver.Text := ENBindingOverObj.numberGen + ' ' + XSDate2String(ENBindingOverObj.dateGen) + ' ' + ENBindingOverObj.itemText;
     end;
   end;

   if ( KindCode = ENPLANWORKKIND_CURRENT ) and ( ENPlanWorkObj.formRef.code = ENPLANWORKFORM_NOPLANNED ) then
   begin
     HideControls([gbReason], false);
     DisableControls([spbReason, spbReasonClear], DialogState = dsView );

     DisableControls([edtReason]);

     ENPlanWorkReasonFilterObj := ENPlanWorkReasonFilter.Create;
     SetNullIntProps(ENPlanWorkReasonFilterObj);
     SetNullXSProps(ENPlanWorkReasonFilterObj);
     ENPlanWorkReasonFilterObj.conditionSQL := 'code in (select q.planworkreasonrefcode from enplanwork2planworkrsn q where q.planrefcode = '+ IntToStr(ENPlanWorkObj.code) +')';
     TempENPlanWorkReason :=  HTTPRIOENPlanWorkReason as ENPlanWorkReasonControllerSoapPort;
     ENPlanWorkReasonList := TempENPlanWorkReason.getScrollableFilteredList(ENPlanWorkReasonFilterObj, 0, -1);
     if  ENPlanWorkReasonList.totalCount > 0 then
     begin
        edtReason.Text :=  ENPlanWorkReasonList.list[0].reasonTypeName + ' №' + ENPlanWorkReasonList.list[0].numberGen + ' від ' + XSDate2String(ENPlanWorkReasonList.list[0].dateGen) + '(' + ENPlanWorkReasonList.list[0].managementName + ')';
     end;

   end;

    if (KindCode in [ENPLANWORKKIND_NPZ, ENPLANWORKKIND_FACT]) then
      //HideControls([gbWorkOrder], false);
      tsWorkOrder.TabVisible := true;
    ///

    if KindCode in [ENPLANWORKKIND_NPZ, ENPLANWORKKIND_FACT] then
    begin
      DisableControls([edtDateFinal , edtYearGen, edtMonthGen, spbENElement, spbType, spbENPlanWorkState , spbENBudget,
                       spbResponsibility
                       //, spbDepartment
                       , spbEPRen, spbENPlanWorkStatus,
                       edtDateGen, cbPlanWorkKind
                       ]);

      if KindCode = ENPLANWORKKIND_FACT then
      begin

       HideControls([lblENActNumber, edtENActNumber, spbENAct], false);

      // подтянем акт ...

      act2planFilter := ENAct2ENPlanWorkFilter.Create;
      SetNullIntProps(act2planFilter);
      SetNullXSProps(act2planFilter);

      act2planFilter.plan := ENPlanWork.Create;
      act2planFilter.plan.code := ENPlanWorkObj.code;
      TempENAct2ENPlanWork :=  HTTPRIOENAct2ENPlanWork as ENAct2ENPlanWorkControllerSoapPort;
      ENAct2ENPlanWorkList := TempENAct2ENPlanWork.getScrollableFilteredList(act2planFilter, 0,-1);
      if  ENAct2ENPlanWorkList.totalCount > 0 then
      begin
         actCode := ENAct2ENPlanWorkList.list[0].actRefCode ;//ENAct2ENPlanWorkList.list[0].code;
         edtENActNumber.Text := ENAct2ENPlanWorkList.list[0].actRefNumberGen + ' від ' + XSDate2String(ENAct2ENPlanWorkList.list[0].actRefDateGen) + ' ' + ENAct2ENPlanWorkList.list[0].actRefFinMolName   ;
      end
      else
      begin
         actCode := LOW_INT;
         edtENActNumber.Text := 'НЕ ВЫБРАНО !!!';
      end;
     end; // if FACT


      //if ENPlanWorkObj.status.code = ENPLANWORKSTATUS_LOCKED then
      if ENPlanWorkObj.status.code in [ENPLANWORKSTATUS_LOCKED, ENPLANWORKSTATUS_CORRECTED] then
      begin
          DisableControls([edtYearGen, edtMonthGen
          //, spbENAct
          ]);
      end;

  end;

  {17.11.2011 - отображение вкладки "Виготовлені матеріали" - для всех видов планов,
                но только с Типом акта "Виготовлення"}
  if (ENPlanWorkObj.stateRef.code = ENConsts.ENPLANWORKSTATE_PRODUCTION)
  or (ENPlanWorkObj.stateRef.code = ENConsts.ENPLANWORKSTATE_SIDEWORKS) then
            tsProduced.TabVisible := true;


// --------------------------------------------------------------
    if KindCode <> ENPLANWORKKIND_YEAR then
    begin

             ///// 07.03.14 Для АВР не будем лишние поля выводить
             if (ENPlanWorkObj.typeRef.code <> ENPLANWORKTYPE_AVR) then
             /////
               gbPlanMOL.Visible := true;



               TempENMOL2PlanWork :=  HTTPRIOENMOL2PlanWork as ENMOL2PlanWorkControllerSoapPort;
               ENMOL2PlanWorkFilterObj := ENMOL2PlanWorkFilter.Create;
               SetNullXSProps(ENMOL2PlanWorkFilterObj);
               SetNullIntProps(ENMOL2PlanWorkFilterObj);

               ENMOL2PlanWorkFilterObj.planRef := ENPlanWorkRef.Create;
               ENMOL2PlanWorkFilterObj.planRef.code := ENPlanWorkObj.code;
               ENMOL2PlanWorkList := TempENMOL2PlanWork.getScrollableFilteredList(ENMOL2PlanWorkFilterObj, 0, -1);
               if ENMOL2PlanWorkList.totalCount > 0 then
                  //ENMOL2PlanWorkObj := TempENMOL2PlanWork.getObject(ENMOL2PlanWorkList.list[0].code)
                  //edtMolName.Text :=  ENMOL2PlanWorkList.list[0].molName
                  edtMolName.Text := ENMOL2PlanWorkList.list[0].molCode + ' ' + ENMOL2PlanWorkList.list[0].molName
               else
                  edtMolName.Text := '';
    end;
// --------------------------------------------------------------

    // если   план по присоединению
           // план месячный
           // код елемента на плане не совпадает с кодом елемента по договору присоединения
           // редактирование или просмотр
           //  то вытянем МОЛ кладовщика на плане
    if ((isPriconnection) and (ENPlanWorkObj.elementRef.code <> soElementCode) and (soElementCode > 0 )
          and ( ( DialogState = dsEdit )
             or ( DialogState = dsView ) )
        ) then
    begin
           TempENMOLStorekeeper2PlanWork :=  HTTPRIOENMOLSTOREKEEPER2PlanWork as ENMOLStorekeeper2PlanWorkControllerSoapPort;
           ENMOLStorekeeper2PlanWorkFilterObj := ENMOLStorekeeper2PlanWorkFilter.Create;
           SetNullXSProps(ENMOLStorekeeper2PlanWorkFilterObj);
           SetNullIntProps(ENMOLStorekeeper2PlanWorkFilterObj);

           ENMOLStorekeeper2PlanWorkFilterObj.planRef := ENPlanWorkRef.Create;
           ENMOLStorekeeper2PlanWorkFilterObj.planRef.code := ENPlanWorkObj.code;
           ENMOLStorekeeper2PlanWorkList := TempENMOLStorekeeper2PlanWork.getScrollableFilteredList(ENMOLStorekeeper2PlanWorkFilterObj, 0, -1);
           if ENMOLStorekeeper2PlanWorkList.totalCount > 0 then
              //ENMOL2PlanWorkObj := TempENMOL2PlanWork.getObject(ENMOL2PlanWorkList.list[0].code)
              //edtMolName.Text :=  ENMOL2PlanWorkList.list[0].molName
              edtMOLStorekeeper.Text := ENMOLStorekeeper2PlanWorkList.list[0].molCode + ' ' + ENMOLStorekeeper2PlanWorkList.list[0].molName
           else
              edtMOLStorekeeper.Text := '';
    end;

    ////////////////////////////////
    if (not isTechConditions)
    and not((ENPlanWorkObj.elementRef.code <> soElementCode)
      and (soElementCode > 0) and Self.isPriconnection)
    then //SUPP-4339
      begin
        DisableControls([btnInsertWorkFromCalcul]);
        DisableActions([actInsertWorkFromCalcul]);
        HideControls([btnInsertWorkFromCalcul]);


      end;
    ////////////////////////////////

    if ((isPriconnection) and (ENPlanWorkObj.elementRef.code <> soElementCode) and (soElementCode > 0 )) then
    begin
        // SUPP-8470
        //  добавление работ по типовой калькуляции
        if ( (ENPlanWorkObj.kind.code = ENConsts.ENPLANWORKKIND_CURRENT )
           and ( DialogState = dsEdit ) )
          then
          begin
            DisableControls([btnAddWorkItemByShifr],false);
            HideControls([btnAddWorkItemByShifr],false);
            // привязка материалов с оперативного запаса пвз или авр-16
            DisableControls([btnAttachMaterials],false);
            HideControls([btnAttachMaterials],false);
          end
          else
          begin
            DisableControls([btnAddWorkItemByShifr]);
            HideControls([btnAddWorkItemByShifr]);
            // привязка материалов с оперативного запаса пвз или авр-16
            DisableControls([btnAttachMaterials],false);
            HideControls([btnAttachMaterials],false);
          end;


        if ( (ENPlanWorkObj.kind.code = ENConsts.ENPLANWORKKIND_CURRENT )
           and ( (DialogState = dsEdit) or (DialogState = dsView ))  ) then
        begin
          // МОЛ кладовщика на который перемещаются материалы при автоматической подвязке материалов
          if DialogState = dsEdit then
           begin
            DisableControls([ spbMOLStorekeeper, spbMOLStorekeeperClear],false);
            HideControls([grpMOLStorekeeper],false);
           end
          else
          DisableControls([ spbMOLStorekeeper, spbMOLStorekeeperClear]);

        end
        else
        begin
          DisableControls([ spbMOLStorekeeper, spbMOLStorekeeperClear]);
          HideControls([grpMOLStorekeeper]);
        end;
    end
    else
    begin
         // скроем добавление работ по типовой калькуляции
        DisableControls([btnAddWorkItemByShifr]);
        HideControls([btnAddWorkItemByShifr]);
        // привязка материалов с оперативного запаса пвз или авр-16
        DisableControls([btnAttachMaterials]);
        HideControls([btnAttachMaterials]);
        // МОЛ кладовщика на который перемещаются материалы при автоматической подвязке материалов
        DisableControls([edtMOLStorekeeper, spbMOLStorekeeper, spbMOLStorekeeperClear]);
        HideControls([grpMOLStorekeeper]);
    end;



    
//      if ENPlanWorkObj.dateGen <> nil then     NET-4213
//      begin
//        edtDateGen.DateTime:=EncodeDate(ENPlanWorkObj.dateGen.Year,ENPlanWorkObj.dateGen.Month,ENPlanWorkObj.dateGen.Day);
//        edtDateGen.checked := true;
//      end
//      else
//      begin
//        edtDateGen.DateTime:=SysUtils.Date;
//        edtDateGen.checked := false;
//      end;

       if ENPlanWorkObj.dateGen <> nil then
       edtDateGen.Text :=  XSDateTimeWithDate2String(ENPlanWorkObj.dateGen);

      if ENPlanWorkObj.dateStart <> nil then
      begin
        edtDateStart.DateTime:=EncodeDate(ENPlanWorkObj.dateStart.Year,ENPlanWorkObj.dateStart.Month,ENPlanWorkObj.dateStart.Day);
        edtDateStart.checked := true;
      end
      else
      begin
        edtDateStart.DateTime:=SysUtils.Date;
        edtDateStart.checked := false;
      end;
      if ENPlanWorkObj.dateFinal <> nil then
      begin
        edtDateFinal.DateTime:=EncodeDate(ENPlanWorkObj.dateFinal.Year,ENPlanWorkObj.dateFinal.Month,ENPlanWorkObj.dateFinal.Day);
        edtDateFinal.checked := true;
      end
      else
      begin
        edtDateFinal.DateTime:=SysUtils.Date;
        edtDateFinal.checked := false;
      end;

    // 22.01.16 Вот это нужно для идиотских планов Энергосбыта из далекого будущего (2055, 2115 гг. и т.д.) -
    // чтобы корректно отображался год на форме при открытии плана на просмотр
    AdjustYearCombobox;

    if ( ENPlanWorkObj.yearGen <> Low(Integer) ) then
       //edtYearGen.Text := IntToStr(ENPlanWorkObj.yearGen)
       edtYearGen.ItemIndex := ENPlanWorkObj.yearGen - 2009
    else
       //edtYearGen.Text := '';
       edtYearGen.ItemIndex := -1;
    if ( ENPlanWorkObj.monthGen <> Low(Integer) ) then
       //edtMonthGen.Text := IntToStr(ENPlanWorkObj.monthGen)
       edtMonthGen.ItemIndex := ENPlanWorkObj.monthGen - 1
    else
       //edtMonthGen.Text := '';
       edtMonthGen.ItemIndex := -1;

    ///// 28.02.2014
    //edtCommentGen.Text := ENPlanWorkObj.commentGen;
    MakeMultiline(edtCommentGen.Lines, ENPlanWorkObj.commentGen);

    if ( ENPlanWorkObj.distanceTo <> nil ) then
       edtDistanceTo.Text := ENPlanWorkObj.distanceTo.decimalString
    else
       edtDistanceTo.Text := '';
    if ( ENPlanWorkObj.distanceAlong <> nil ) then
       edtDistanceAlong.Text := ENPlanWorkObj.distanceAlong.decimalString
    else
       edtDistanceAlong.Text := '';

{    edtUserGen.Text := ENPlanWorkObj.userGen;
      if ENPlanWorkObj.dateEdit <> nil then
      begin
        edtDateEdit.DateTime:=EncodeDate(ENPlanWorkObj.dateEdit.Year,ENPlanWorkObj.dateEdit.Month,ENPlanWorkObj.dateEdit.Day);
        edtDateEdit.checked := true;
      end
      else
      begin
        edtDateEdit.DateTime:=SysUtils.Date;
        edtDateEdit.checked := false;
      end;

    edtENPlanWorkStatusName.Text := ENPlanWorkObj.status.name;
}

{
    cbTypeName.ItemIndex := -1;
    if ENPlanWorkObj.typeRef <> nil then
      if ENPlanWorkObj.typeRef.code <> Low(Integer) then
        cbTypeName.ItemIndex := ENPlanWorkObj.typeRef.code - 1;
}

    if  ENPlanWorkObj.typeRef = nil then
       ENPlanWorkObj.typeRef := ENPlanWorkTypeRef.Create
    else
    if  ENPlanWorkObj.typeRef.code > Low(Integer) then
    begin

         try

             TempENPlanWorkType :=  HTTPRIOENPlanWorkType as ENPlanWorkTypeControllerSoapPort;
             typeObj := TempENPlanWorkType.getObject(ENPlanWorkObj.typeRef.code);
             if typeObj <> nil then
             begin
                 edtTypeName.Text := typeObj.name;
                 if  typeObj.code<>ENPLANWORKSTATE_EZ_SETUP_ZKU //zku
                 then HideControls([btnSaveComment]);

             end;
         finally

         end;
    end;

    if  ENPlanWorkObj.stateRef = nil then
       ENPlanWorkObj.stateRef := ENPlanWorkStateRef.Create
    else
    if  ENPlanWorkObj.stateRef.code > Low(Integer) then
    begin

         try

             TempENPlanWorkState :=  HTTPRIOENPlanWorkState as ENPlanWorkStateControllerSoapPort;
             stateObj := TempENPlanWorkState.getObject(ENPlanWorkObj.stateRef.code);
             if stateObj <> nil then
             begin
                 edtWorkState.Text := stateObj.name;
             end;
         finally

         end;
    end;

      if ENPlanWorkObj.dateStart <> nil then
      begin
        edtDateStart.DateTime:=EncodeDate(ENPlanWorkObj.dateStart.Year,ENPlanWorkObj.dateStart.Month,ENPlanWorkObj.dateStart.Day);
        edtDateStart.checked := true;
      end
      else
      begin
        edtDateStart.DateTime:=SysUtils.Date;
        edtDateStart.checked := false;
      end;
      if ENPlanWorkObj.dateFinal <> nil then
      begin
        edtDateFinal.DateTime:=EncodeDate(ENPlanWorkObj.dateFinal.Year,ENPlanWorkObj.dateFinal.Month,ENPlanWorkObj.dateFinal.Day);
        edtDateFinal.checked := true;
      end
      else
      begin
        edtDateFinal.DateTime:=SysUtils.Date;
        edtDateFinal.checked := false;
      end;

    cbPlanWorkKind.ItemIndex := ENPlanWorkObj.kind.code - 1;

    if ENPlanWorkObj.elementRef.code > Low(Integer) then
    begin
       eFilter := ENElementFilter.Create;
       try
         SetNullIntProps(eFilter);
         SetNullXSProps(eFilter);

         //s04Filter.conditionSQL := '';
         eFilter.code := ENPlanWorkObj.elementRef.code;
         //eFilter.element.code := ENLine04Obj.element.elementInRef.code;


         //TempENElement :=  HTTPRIOENElement as ENElementControllerSoapPort;

          element := TempENElement.getObject(ENPlanWorkObj.elementRef.code);
           if element.typeRef.code=53 //Маршрут быт
           then
           begin
           tsObjectDetail.TabVisible:=true;
           edtConroler.Visible:=true;
           lblControler.Visible:=true;

          TempFINWorker:= HTTPRIOFINWorker as FINWorkerControllerSoapPort;
          FINWorkerFilterObj:=FINWorkerFilter.Create;
          SetNullIntProps(FINWorkerFilterObj);
          SetNullXSProps(FINWorkerFilterObj);
          FINWorkerFilterObj.conditionSQL:='finworker.code='+
   ' (select coalesce(min(hi.finworkercode),0) from enhumenitem hi where hi.planrefcode='+IntToStr(ENPlanWorkObj.code)+')';

          FINWorkerList:=TempFINWorker.getScrollableFilteredList(FINWorkerFilterObj,0,1);
          if  HIGH(FINWorkerList.list)>=0 then
            edtConroler.Text:=FINWorkerList.list[0].name
          else edtConroler.Text:='';

           end
           else
           begin
           tsObjectDetail.TabVisible:=false;
           edtConroler.Visible:=false;
           lblControler.Visible:=false;
           end;


         eList := TempENElement.getScrollableFilteredList(eFilter,0,-1);
         if eList.totalCount > 0 then
         begin
             edtENElementName.Text := eList.list[0].objectName;

             edtInvNumber.Text := eList.list[0].objectInvNumber;

             /////
             {                 9
             if KindCode = ENPLANWORKKIND_FACT then
               frmENPlanWorkEdit.Caption := 'Завдання-Факт для ' + eList.list[0].objectName
             else
               frmENPlanWorkEdit.Caption := 'Завдання-План для ' + eList.list[0].objectName;
             }

             case KindCode of
               ENPLANWORKKIND_YEAR:    planKindName := 'Річний план для ';
               ENPLANWORKKIND_CURRENT: planKindName := 'Місячний план для ';
               ENPLANWORKKIND_NPZ:     planKindName := 'Завдання-План для ';
               ENPLANWORKKIND_FACT:    planKindName := 'Завдання-Факт для ';
             end;

             //frmENPlanWorkEdit.
             Caption := planKindName + eList.list[0].objectName;
             /////

             edtEPRenName.Text := eList.list[0].renRefName;
             edtGeograph.Text := eList.list[0].geoDepartmentRefName;
             ////// 11.04.12 NET-1622 После копирования плана даем менять объект *************
             //DisableControls([edtENElementName, edtInvNumber, spbENElement, edtEPRenName]);
             DisableControls([edtENElementName, edtInvNumber, edtEPRenName , edtGeograph ]);
             if not isForCopy then
               DisableControls([spbENElement]);
             //////***************************************************************************

             // 20.11.2014 +++ возможность изменить объект планирования
             // при редактировании планов на услугу тех.надзора
             if isTechAgreement then
               DisableControls([spbENElement], False);

         end;
       finally
         eFilter.Free;
       end;
    end
    else
    begin
       edtENElementName.Text := '';
       edtInvNumber.Text := '';
       edtEPRenName.Text := '';
    end;

      if ENPlanWorkObj.budgetRef <> nil then
        if ENPlanWorkObj.budgetRef.code <> low(Integer) then
        begin
          TempENDepartment := HTTPRIOENDepartment as ENDepartmentControllerSoapPort;

          edtENBudgetName.Text := TempENDepartment.getObject(ENPlanWorkObj.budgetRef.code).shortName;
        end;

      if ENPlanWorkObj.responsibilityRef <> nil then
        if ENPlanWorkObj.responsibilityRef.code <> low(Integer) then
        begin
          TempENDepartment := HTTPRIOENDepartment as ENDepartmentControllerSoapPort;

          edtResponsibility.Text := TempENDepartment.getObject(ENPlanWorkObj.responsibilityRef.code).shortName;
        end;

      if ENPlanWorkObj.departmentRef <> nil then
        if ENPlanWorkObj.departmentRef.code <> low(Integer) then
        begin
          TempENDepartment := HTTPRIOENDepartment as ENDepartmentControllerSoapPort;

          edtDepartment.Text := TempENDepartment.getObject(ENPlanWorkObj.departmentRef.code).shortName;
        end;


        if ENPlanWorkObj.stateRef <> nil then
        if ENPlanWorkObj.stateRef.code <> low(Integer) then
        begin
          TempENPlanWorkState := HTTPRIOENPlanWorkState as ENPlanWorkStateControllerSoapPort;

          edtWorkState.Text := TempENPlanWorkState.getObject(ENPlanWorkObj.stateRef.code).name;
        end;

        if ENPlanWorkObj.finExecutor <> nil then
          if ENPlanWorkObj.finExecutor.code > LOW_INT then
          begin
             edtFINExecutorName.Text := ENPlanWorkObj.finExecutor.name;
          end;

    {
    edtWorkOrderNumber.Text := ENPlanWorkObj.workOrderNumber;

    if ENPlanWorkObj.dateWorkOrder <> nil then
    begin
      edtDateWorkOrder.DateTime:=EncodeDate(ENPlanWorkObj.dateWorkOrder.Year,ENPlanWorkObj.dateWorkOrder.Month,ENPlanWorkObj.dateWorkOrder.Day);
      edtDateWorkOrder.checked := true;
    end
    else
    begin
      edtDateWorkOrder.DateTime:=SysUtils.Date;
      edtDateWorkOrder.checked := false;
    end;
    }

    edtServicesFromSide.Text := ENPlanWorkObj.servicesFSideCnNum;

    if (edtServicesFromSide.Text <> '') then
       DisableControls([edtServicesFromSide, spbServicesFromSide]);

    if (ENPlanWorkObj.ddsCodes <> nil) and (ENPlanWorkObj.ddsCodes.code <> low_int) then
       edtDdsCodes.Text := ENPlanWorkObj.ddsCodes.txtCode + ' ' + ENPlanWorkObj.ddsCodes.name;


    edtPriConnectionNumber.Text := ENPlanWorkObj.priConnectionNumber;

      if ENPlanWorkObj.DateEndPriconnection <> nil then
        begin
          edtDateEndPriconnection.DateTime:=EncodeDate(ENPlanWorkObj.dateEndPriConnection.Year,ENPlanWorkObj.dateEndPriConnection.Month,ENPlanWorkObj.dateEndPriConnection.Day);
          edtDateEndPriconnection.checked := true;
        end
       else
      begin
          edtDateEndPriconnection.DateTime:=SysUtils.Date;
          edtDateEndPriconnection.checked := false;
      end;


    MakeMultiline(edtInvestWorksDescription.Lines, ENPlanWorkObj.investWorksDescription);
    if(ENPlanWorkObj.investWorksAmount <> nil) then
      edtInvestWorksAmount.Text := ENPlanWorkObj.investWorksAmount.DecimalString
    else
      edtInvestWorksAmount.Text := '';

      if ENPlanWorkObj.investDateStartWork <> nil then
        begin
          edtInvestWorkStartDate.DateTime:=EncodeDate(ENPlanWorkObj.investDateStartWork.Year,ENPlanWorkObj.investDateStartWork.Month,ENPlanWorkObj.investDateStartWork.Day);
          edtInvestWorkStartDate.checked := true;
        end
       else
      begin
          edtInvestWorkStartDate.DateTime:=SysUtils.Date;
          edtInvestWorkStartDate.checked := false;
      end;

      if (ENPlanWorkObj.investWorkMeasCode > LOW_INT)  then
         begin
           TempTKMeasurement :=  HTTPRIOTKMeasurement as TKMeasurementControllerSoapPort;
           edtInvestMeasurementName.Text := TempTKMeasurement.getObject(ENPlanWorkObj.investWorkMeasCode).name;
         end;


    cbENPlanWorkFormName.ItemIndex := ENPlanWorkObj.formRef.code - 1;

    cbENPlanWorkSource.ItemIndex := ENPlanWorkObj.sourceRef.code - 1;


    if ENPlanWorkObj.invgroupRef = nil then
       ENPlanWorkObj.invgroupRef := ENInvestProgramGroupsRef.Create
    else
    if ENPlanWorkObj.invgroupRef.code > Low(Integer) then
    begin
         try
             TempENInvestProgramGroups :=  HTTPRIOENInvestProgramGroups as ENInvestProgramGroupsControllerSoapPort;
             investObj := TempENInvestProgramGroups.getObject(ENPlanWorkObj.invgroupRef.code);
             if investObj <> nil then
             begin
                 edtInvestProgramGroupsName.Text := investObj.name;
             end;
         finally

         end;
    end;

  if ENPlanWorkObj.ipImplementTypeRef <> nil then
    if ENPlanWorkObj.ipImplementTypeRef.code <> LOW_INT then
    begin
      TempENIPImplementationType := HTTPRIOENIPImplementationType as ENIPImplementationTypeControllerSoapPort;
      ipImplementationTypeObj := TempENIPImplementationType.getObject(ENPlanWorkObj.ipImplementTypeRef.code);

      if ipImplementationTypeObj <> nil then
        edtIPImplementationType.Text := ipImplementationTypeObj.name;
    end;

  ///// 08.01.14
  edtInvestItemNumber.Text := ENPlanWorkObj.investItemNumber;
  /////

  // test codesDown !!!!
  //TempENPlanWork := HTTPRIOENPlanWork as ENPlanWorkControllerSoapPort;
  //edtCommentGen.Text := TempENPlanWork.getPlanCodesHistoryDown(ENPlanWorkObj.code);

  end;
  // все что с актом пока спрячем ...
  // HideControls([lblENActNumber, edtENActNumber, spbENAct]);

  if (DialogState = dsInsert) then
    for i := 0 to pcPlanWork.PageCount - 1 do
      if pcPlanWork.Pages[i] <> tsPlanWork then
        pcPlanWork.Pages[i].TabVisible := false;

  // Если форма вызывается по нажатию кнопки "Работы" на списке планов,
  // прячем все вкладки, кроме "Перелік робіт"
  // (хоть этой кнопкой никто и не пользуется, но лучше проверять)
  if addPlanItemsMode then
    for i := 0 to pcPlanWork.PageCount - 1 do
      if pcPlanWork.Pages[i] <> tsPlanWorkItems then
        pcPlanWork.Pages[i].TabVisible := false;

   if isWriteOffProtection then
     begin
     tsPlanWorkItems.TabVisible := false;
     tsGSM.TabVisible := false;
     tsDismount.TabVisible := false;
     tsRefinement.TabVisible := false;
     tsHumens.TabVisible := false;
     tsTransports.TabVisible := false;
     tsRQFKOrder.TabVisible := false;

     gbPlanMOL.Visible:= False;
     end;

  ///// 27.02.12 NET-1355 Запрещаем изменять дату окончания выполнения работ руками - будем рассчитывать сами
  DisableControls([edtDateFinal]);

  if DialogState <> dsInsert then
  begin
    if ENPlanWorkObj.kind.code in [ENPLANWORKKIND_NPZ, ENPLANWORKKIND_FACT] then
      lblDateFinal.Caption := 'Дата закінчення виконання робіт'
    else
      lblDateFinal.Caption := 'Розрахункова дата виконання робіт';
  end;

  if (DialogState = dsInsert) or (DialogState = dsEdit) then
  begin
     if isTechConditions then
     begin
       // 06.11.2013 +++ даём изменять spbENBudget, spbResponsibility,
       if (DialogState <> dsInsert) and (element.typeRef.code = ENELEMENTTYPE_TECHCONDITIONSSERVICES) then
          DisableControls([spbType, spbENPlanWorkState]);
       DisableControls([cbPlanWorkKind, cbENPlanWorkFormName, edtPriConnectionNumber, edtCommentGen {, edtDateEndPriconnection}]);
     end;
  end;
  
  if (DialogState = dsInsert) then
  begin  
  /////// вкладка и птичка про "Відключення споживачів"  ///////
      chkCauseDisconnection.Visible := False;
      tsCCDamageLog.TabVisible := False;
      chbNeedsApprovalByCustomer.Visible := false;
  end;

  if (DialogState <> dsInsert) then
    if ENPlanWorkObj.kind.code <> ENPLANWORKKIND_CURRENT then
       DisableControls([edtDateEndPriconnection]);

  if ((DialogState = dsInsert) and isSiz) then
    DisableControls([spbType]);

  if (DialogState = dsView) then
  begin
     DisableControls([edtDateEndPriconnection]);
  end;

  // 10.04.12 10:25 Убираем ограничение пока, ВРТУшники хотят планы на старые договора заводить по-старому (Гончаров)
  // DisableControls([edtPriConnectionNumber]);


  // NET-4383 -- скрываем ненужные для списания ОЗ вкладки
  if (DialogState <> dsInsert) then
   if ( (ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_WRITEOFF_OS)
                 and (ENPlanWorkObj.stateRef.code = ENPLANWORKSTATE_WRITINGS_OS) )  then
   begin
     tsGSM.TabVisible := false;
     tsHumens.TabVisible := false;
     tsTransports.TabVisible := false;
     tsPlanWorkItems.TabVisible := false;
     tsEstimateItems.TabVisible := false;
     tsTransportOrder.TabVisible:= false;
     tsTransportRoute.TabVisible:= false;
     tsProduced.TabVisible:= false;
     tsLinkedPlans.TabVisible:= false;
     tsMoves.TabVisible:= false;
     tsCCDamageLog.TabVisible := false;
     tsRQTransportInvoice.TabVisible := false;

     HideControls([chkCauseDisconnection]);
     tsWriteOffOS.TabVisible:= True;
     tsWorkOrder.Caption := 'Наряд-завдання та МВО на яку буде приход від демонтажу';

   end
   else
   tsWriteOffOS.TabVisible:= False;


  // план для перемещения счетчиков!!!
  if (DialogState <> dsInsert) and (ENPlanWorkObj.code = NO) then
    for i := 0 to pcPlanWork.PageCount - 1 do
      if pcPlanWork.Pages[i] <> tsPlanWork then
        pcPlanWork.Pages[i].TabVisible := False;


   // ++++++++++++isServicesFromSide
   if isServicesFromSide then
   begin
      gbServicesFromSide.Caption := 'Послуги';
              HideControls([gbServicesFromSide, lblServicesFromSide,
                            edtServicesFromSide, {spbServicesFromSide,}
                            gbDddsCodes, edtDdsCodes, spbDdsCodes  ], false);
              DisableControls([spbENPlanWorkState],false);
              DisableControls([edtServicesFromSide]);
               if (DialogState = dsEdit) then DenyBlankValues([edtDdsCodes]);

               if (ENPlanWorkObj.kind.code = ENPLANWORKKIND_CURRENT) then
                   tsActsServicesFS.TabVisible := True;
   end;


    if (DialogState <> dsInsert) then
     begin
       ipItem2planFilter := ENIPItem2PlanFilter.Create;
       SetNullIntProps(ipItem2planFilter);
       SetNullXSProps(ipItem2planFilter);
       ipItem2planFilter.planRef := ENPlanWorkRef.Create;
       ipItem2planFilter.planRef.code := ENPlanWorkObj.code;
       TempENIPItem2Plan := HTTPRIOENIPItem2Plan as ENIPItem2PlanControllerSoapPort;
       ENIPItem2PlanList := TempENIPItem2Plan.getScrollableFilteredList(ipItem2planFilter,0,-1);
       if ENIPItem2PlanList.totalCount > 0 then
       DisableControls([spbInvestProgramGroups, spbIPImplementationType, edtInvestProgramGroupsName,
       edtInvestItemNumber, edtIPImplementationType]);
     end;

      HideControls([ edtEPRenName , lblEPRenName ] ); // NET-4606 Приналежність об`єктів до географічного підрозділу

end;



procedure TfrmENPlanWorkEdit.FormClose(Sender: TObject;  var Action: TCloseAction);
var TempENPlanWork: ENPlanWorkControllerSoapPort;
    TempENElement: ENElementControllerSoapPort;
    element : ENElement;
    //ENAct2ENPlanWorkObj : ENAct2ENPlanWork.Create;
    AllowClose: Boolean;

    dateStart: TDate;
    TempFINExecutor2Plan : FINExecutor2PlanControllerSoapPort;
    FINExecutor2PlanList : FINExecutor2PlanShortList;
    executor2PlanFilter : FINExecutor2PlanFilter;
begin
  // Проверяем, а не находится ли наряд-задание в состоянии редактирования
  pcPlanWorkChanging(Sender, AllowClose);

  if not AllowClose then
  begin
    Action := caNone;
    Exit;
  end;

// сохраним привязку с актом !!!
{
if actCode > low(Integer) then
begin
  ENAct2ENPlanWorkObj:=ENAct2ENPlanWork.Create;
  ENAct2ENPlanWorkObj.actRef := ENActRef.Create;
  ENAct2ENPlanWorkObj.plan := ENPlanWork.Create;

  ENAct2ENPlanWorkObj.plan.code := ENPlanWorkObj.code;
end;
}
  if (ModalResult = mrOk) and ((DialogState = dsEdit) or (DialogState = dsInsert)) then
  if not NoBlankValues([
      edtYearGen
      ,edtMonthGen
      ,edtENElementName
      ,edtENBudgetName
      ,edtResponsibility
      ,edtDepartment
      ,edtDateStart
      ,edtDateFinal
      ,edtTypeName
      ,edtWorkState
     ])  then
  begin
      Application.MessageBox(PChar('Пустые поля недопустимы !'),PChar('Внимание !'),MB_ICONWARNING+MB_OK);
      Action:=caNone;
  end
  else
  begin
    {if cbTypeName.ItemIndex = -1 then
    begin
      Application.MessageBox(PChar('Оберіть вид ремонту !'), PChar('Увага !'), MB_ICONWARNING + MB_OK);
      if cbTypeName.CanFocus then cbTypeName.SetFocus;
      Action := caNone;
      Exit;
    end;
    }

    if (DialogState = dsInsert) and (cbPlanWorkKind.ItemIndex + 1 = ENPLANWORKKIND_FACT) then
    begin
      // NET-4383
      if ENPlanWorkObj.typeRef.code <> ENPLANWORKTYPE_WRITEOFF_OS  then
         begin
          Application.MessageBox(PChar('Завдання-Факт можливо сформувати тільки з Завдання-Плану !'),PChar('Увага !'),MB_ICONWARNING+MB_OK);
          Action := caNone;
          cbPlanWorkKind.ItemIndex := ENPLANWORKKIND_NPZ - 1;
          Exit;
         end;
    end;

    /////
    if (DialogState = dsEdit) then
    begin
      dateStart := EncodeDate(ENPlanWorkObj.dateStart.Year, ENPlanWorkObj.dateStart.Month, ENPlanWorkObj.dateStart.Day);
      if DateOf(edtdateStart.DateTime) <> dateStart then
      begin
        TempFINExecutor2Plan := HTTPRIOFINExecutor2Plan as FINExecutor2PlanControllerSoapPort;

        executor2PlanFilter := FINExecutor2PlanFilter.Create;
        SetNullIntProps(executor2PlanFilter);
        SetNullXSProps(executor2PlanFilter);

        executor2PlanFilter.planRef := ENPlanWorkRef.Create;
        executor2PlanFilter.planRef.code := ENPlanWorkObj.code;
        FINExecutor2PlanList := TempFINExecutor2Plan.getScrollableFilteredList(executor2PlanFilter, 0, -1);

        if High(FINExecutor2PlanList.list) > 0 then
        begin
          if Application.MessageBox(PChar('При збереженні цього плану дата початку виконання робіт на плані не зміниться!' + #13#10 +
                                          'Для її зміни потрібно редагувати дати початку на вкладці "Виконавці плану"!' + #13#10 +
                                          'Продовжити?'),
                                    PChar('Увага!'), MB_ICONWARNING + MB_YESNO) <> IDYES then
          begin
            Action := caNone;
            Exit;
          end;
        end;
      end;
    end;
    /////



    ENPlanWorkObj.investWorksDescription := edtInvestWorksDescription.Text;

    if edtInvestWorkStartDate.checked then
     begin
       if ENPlanWorkObj.investDateStartWork = nil then
          ENPlanWorkObj.investDateStartWork := TXSDateTime.Create;
          ENPlanWorkObj.investDateStartWork.XSToNative(GetXSDate(edtInvestWorkStartDate.DateTime));
     end
     else
       ENPlanWorkObj.investDateStartWork := nil;

    if(edtInvestWorksAmount.Text <> '') then
    begin
      if ENPlanWorkObj.investWorksAmount = nil then
        ENPlanWorkObj.investWorksAmount := TXSDecimal.Create;
      try
        ENPlanWorkObj.investWorksAmount.DecimalString := FloatToStr(StrToFloat(edtInvestWorksAmount.Text));
      except on EConvertError do
      begin
          Application.MessageBox(PChar('''' + edtInvestWorksAmount.Text +''' не є числом, введіть число у поле ''Обсяги робіт (км / шт)'''),PChar('Внимание !'),MB_ICONWARNING+MB_OK);
          Action := caNone;
          Exit;
      end;

      end;
    end;

    ///// 08.01.14
    ENPlanWorkObj.investItemNumber := edtInvestItemNumber.Text;
    /////

    if Length(edtInvestMeasurementName.Text)>0 then
      ENPlanWorkObj.investWorkMeasCode := investMeasCode
      else  ENPlanWorkObj.investWorkMeasCode := LOW_INT;


    ///


    {
    if (cbPlanWorkKind.ItemIndex + 1 in [ENPLANWORKKIND_NPZ, ENPLANWORKKIND_FACT]) then
    begin
      if edtDateWorkOrder.Checked then
        if edtDateWorkOrder.Date <> edtDateStart.Date then
        begin
          Application.MessageBox(PChar('Дата наряду не співпадає з датою початку робіт !'),PChar('Увага !'),MB_ICONWARNING+MB_OK);
          if edtDateWorkOrder.CanFocus then edtDateWorkOrder.SetFocus;
        end;
    end;
    }
    
    TempENPlanWork := HTTPRIOENPlanWork as ENPlanWorkControllerSoapPort;

    ENPlanWorkObj.dateEdit := nil;
//NET-4213
//    if edtDateGen.Checked then
//    begin
//      if ENPlanWorkObj.dateGen = nil then
//        ENPlanWorkObj.dateGen := TXSDate.Create;
//      ENPlanWorkObj.dateGen.XSToNative(GetXSDate(edtdateGen.DateTime));
//    end else
//      ENPlanWorkObj.dateGen := nil;

     if edtdateStart.checked then
     begin
       if ENPlanWorkObj.dateStart = nil then
          ENPlanWorkObj.dateStart := TXSDate.Create;
          ENPlanWorkObj.dateStart.XSToNative(GetXSDate(edtdateStart.DateTime));
     end
     else
       ENPlanWorkObj.dateStart := nil;

     if edtdateFinal.checked then
     begin
       if ENPlanWorkObj.dateFinal = nil then
          ENPlanWorkObj.dateFinal := TXSDate.Create;
       ENPlanWorkObj.dateFinal.XSToNative(GetXSDate(edtdateFinal.DateTime));
     end
     else
       ENPlanWorkObj.dateFinal := nil;

     if ENPlanWorkObj.kind = nil then ENPlanWorkObj.kind := ENPlanWorkKind.Create;
     ENPlanWorkObj.kind.code := cbPlanWorkKind.ItemIndex + 1;

     //if ( edtYearGen.Text <> '' ) then
     //  ENPlanWorkObj.yearGen := StrToInt(edtYearGen.Text)
     if (edtYearGen.ItemIndex >= 0) then
       ENPlanWorkObj.yearGen := edtYearGen.ItemIndex + 2009
     else
       ENPlanWorkObj.yearGen := Low(Integer) ;

     //if ( edtMonthGen.Text <> '' ) then
       //ENPlanWorkObj.monthGen := StrToInt(edtMonthGen.Text)
     if (edtMonthGen.ItemIndex >= 0) then
       ENPlanWorkObj.monthGen := edtMonthGen.ItemIndex + 1
     else
       ENPlanWorkObj.monthGen := Low(Integer) ;

     ENPlanWorkObj.commentGen := edtCommentGen.Text;

     ENPlanWorkObj.distanceTo := TXSDecimal.Create;
     if edtDistanceTo.Text <> '' then
       ENPlanWorkObj.distanceTo.decimalString := edtDistanceTo.Text
     else
       ENPlanWorkObj.distanceTo := nil;

     ENPlanWorkObj.distanceAlong := TXSDecimal.Create;
     if edtDistanceAlong.Text <> '' then
       ENPlanWorkObj.distanceAlong.decimalString := edtDistanceAlong.Text
     else
       ENPlanWorkObj.distanceAlong := nil;
       
       {
     ENPlanWorkObj.userGen := edtUserGen.Text;

     if ENPlanWorkObj.dateEdit = nil then
        ENPlanWorkObj.dateEdit := TXSDate.Create;
      ENPlanWorkObj.dateEdit.XSToNative(GetXSDate(edtdateEdit.DateTime));
}

      {
      if ENPlanWorkObj.typeRef = nil then
        ENPlanWorkObj.typeRef := ENPlanWorkTypeRef.Create;
      ENPlanWorkObj.typeRef.code := cbTypeName.ItemIndex + 1;
      }

    // определим тип элемента по коду
    // и вызовм НУЖНЫЙ метод ... - чтоб левые юзера не планировали на правые обьекты

    {
    if (not NoBlankValues([ edtWorkOrderNumber, edtDateWorkOrder])) and
       (ENPlanWorkObj.kind.code = ENPLANWORKKIND_FACT) then
    begin
      Application.MessageBox(PChar('Пустые поля недопустимы !'),PChar('Внимание !'),MB_ICONWARNING+MB_OK);
      Action:=caNone;
      Exit;
    end;

     ENPlanWorkObj.workOrderNumber := edtWorkOrderNumber.Text;


     if edtdateWorkOrder.checked then
     begin
       if ENPlanWorkObj.dateWorkOrder = nil then
          ENPlanWorkObj.dateWorkOrder := TXSDate.Create;
       ENPlanWorkObj.dateWorkOrder.XSToNative(GetXSDate(edtdateWorkOrder.DateTime));
     end
     else
       ENPlanWorkObj.dateWorkOrder := nil;
     }

    ///////////////
    // 16.01.2012 +++ можно без договора
    // if (not NoBlankValues([edtServicesFromSide]))
    //     and (ENPlanWorkObj.typeRef.code in [ENPLANWORKTYPE_SERVICES_FROM_SIDE, ENPLANWORKTYPE_SERVICES_FROM_SIDE_INVEST]) then
    // begin
    //     Application.MessageBox(PChar('Пустые поля недопустимы !'),PChar('Внимание !'),MB_ICONWARNING+MB_OK);
    //     Action:=caNone;
    //     Exit;
    // end;
    ///////////////


     ////  21.05.2012 +++ Зміст робіт (інв.прг.)
     if not NoBlankValues([edtInvestWorksDescription, edtInvestWorksAmount])
           and (ENPlanWorkObj.typeRef.code in [ENPLANWORKTYPE_INVEST, ENPLANWORKTYPE_SERVICES_FROM_SIDE_INVEST] )
           and not ((ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_INVEST ) and ( ENPlanWorkObj.stateRef.code = ENPLANWORKSTATE_DESIGNING )) then
     begin
       Application.MessageBox(PChar('Пустые поля недопустимы !'),PChar('Внимание !'),MB_ICONWARNING+MB_OK);
       Action:=caNone;
       Exit;
     end;
     ////

     // 23.01.2012 +++ но нужно ДДС
     if (not NoBlankValues([edtDdsCodes]))
         and (ENPlanWorkObj.typeRef.code in [ENPLANWORKTYPE_SERVICES_FROM_SIDE, ENPLANWORKTYPE_SERVICES_FROM_SIDE_INVEST]) then
     begin
         Application.MessageBox(PChar('Пустые поля недопустимы !'),PChar('Внимание !'),MB_ICONWARNING+MB_OK);
         Action:=caNone;
         Exit;
     end;

     ///// 08.01.2014 Поля "Раздел ИнвестПрограммы" и "Пункт ИнвестПрограммы" обязательные для всех подвидов работ по ИП!!!
     {
     if (not NoBlankValues([edtInvestProgramGroupsName]))
          and (ENPlanWorkObj.typeRef.code in [ENPLANWORKTYPE_SERVICES_FROM_SIDE_INVEST]) then
     begin
         Application.MessageBox(PChar('Пустые поля недопустимы !'),PChar('Внимание !'),MB_ICONWARNING+MB_OK);
         Action:=caNone;
         Exit;
     end;
     }
     // 09.01.14 Только для Месячных планов!!!
     if ((DialogState = dsInsert) and (cbPlanWorkKind.ItemIndex + 1 = ENPLANWORKKIND_CURRENT)) or
        ((DialogState = dsEdit) and (ENPlanWorkObj.kind.code = ENPLANWORKKIND_CURRENT)) then

       if (not NoBlankValues([edtInvestProgramGroupsName, edtInvestItemNumber]))
            and (ENPlanWorkObj.typeRef.code in [ENPLANWORKTYPE_INVEST,
                                                ENPLANWORKTYPE_SERVICES_FROM_SIDE_INVEST,
                                                ENPLANWORKTYPE_ESBYT_PZ,
                                                ENPLANWORKTYPE_ESBYT_ZKO_106,
                                                ENPLANWORKTYPE_ESBYT_ZKO_111,
                                                ENPLANWORKTYPE_ESBYT_ZKO_112]) then
       begin
         if ENPlanWorkObj.budgetRef = nil then
         begin
           Application.MessageBox(PChar('Оберіть Бюджетотримача !'), PChar('Увага !'), MB_ICONWARNING + MB_OK);
           if edtENBudgetName.CanFocus then
             edtENBudgetName.SetFocus;
           Action := caNone;
           Exit;
         end;

         // 04.02.14 Если планы ВРТУ, то это не ИнвестПрограмма, соответственно, и поля по ИП - необязательные
         if ((ENPlanWorkObj.budgetRef.code <> ENBUDGET_VRTUVD) or
            (ENPlanWorkObj.typeRef.code in [ENPLANWORKTYPE_INVEST, // такие могут быть теоретически для ВРТУ
                                            ENPLANWORKTYPE_SERVICES_FROM_SIDE_INVEST]))
             and not ((ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_INVEST ) and ( ENPlanWorkObj.stateRef.code = ENPLANWORKSTATE_DESIGNING ))                                then
         begin
           Application.MessageBox(PChar('Пустые поля недопустимы !'), PChar('Внимание !'), MB_ICONWARNING + MB_OK);
           Action := caNone;
           Exit;
         end;

       end;
     /////

     // 24.02.2012 +++ Услуги со стороны Кап.строительство
    { if ((not NoBlankValues([edtPriConnectionNumber]))
              and (ENPlanWorkObj.typeRef.code in [ENPLANWORKTYPE_SERVICES_FROM_SIDE, ENPLANWORKTYPE_SERVICES_FROM_SIDE_INVEST])
              and (ENPlanWorkObj.stateRef.code = ENPLANWORKSTATE_CAPITALBUILDER)) then
          begin
              Application.MessageBox(PChar('Пустые поля недопустимы !'),PChar('Внимание !'),MB_ICONWARNING+MB_OK);
              Action:=caNone;
              Exit;
          end;
     }

     ENPlanWorkObj.servicesFSideCnNum := edtServicesFromSide.Text;

     if (not NoBlankValues([edtPriConnectionNumber  ])) and ((ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_CN) or (ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_VRTUVD_PROJECT )) then // Приєднання
     begin
       Application.MessageBox(PChar('Пустые поля недопустимы !'),PChar('Внимание !'),MB_ICONWARNING+MB_OK);

       // 10.04.12 10:25 Убираем ограничение пока, ВРТУшники хотят планы на старые договора заводить по-старому (Гончаров)
       // Application.MessageBox(PChar('Ці плани потрібно додавати з форми договору (меню "Послуги на сторону" -> "Приєднання")!'),
       //                        PChar('Увага !'), MB_ICONWARNING);

       Action := caNone;
       Exit;
     end;

     if (not NoBlankValues([edtDateEndPriconnection ])) and (ENPlanWorkObj.kind.code = ENPLANWORKKIND_CURRENT ) and
     ((ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_CN) or (ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_VRTUVD_PROJECT )) then // Приєднання
     begin
       Application.MessageBox(PChar('Пустые поля недопустимы !'),PChar('Внимание !'),MB_ICONWARNING+MB_OK);
       Action := caNone;
       Exit;
     end;

     ENPlanWorkObj.priConnectionNumber := edtPriConnectionNumber.Text;


     if edtDateEndPriconnection.checked then
     begin
       if ENPlanWorkObj.DateEndPriconnection = nil then
          ENPlanWorkObj.DateEndPriconnection := TXSDate.Create;
          ENPlanWorkObj.DateEndPriconnection.XSToNative(GetXSDate(edtDateEndPriconnection.DateTime));
     end
     else
       ENPlanWorkObj.DateEndPriconnection := nil;



     // Предписание ...
    // при редактировании предписания - вывести ввод данных
   if
       (ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_PRIPIS)
       and (DialogState = dsEdit) then
   begin
     if not NoBlankValues([edtBindingOver]) then
     begin
      Application.MessageBox(PChar('Заведіть данні про Припис ...'),PChar('Увага !'),MB_ICONWARNING+MB_OK);
      Action:=caNone;
     end;
   end;

   if  cbENPlanWorkFormName.ItemIndex = -1 then
   begin
      Application.MessageBox(PChar('Вкажіть Вид робіт ...'),PChar('Увага !'),MB_ICONWARNING+MB_OK);
      Action:=caNone;
   end;

   if ENPlanWorkObj.formRef = nil then ENPlanWorkObj.formRef := ENPlanWorkFormRef.Create;
   ENPlanWorkObj.formRef.code := cbENPlanWorkFormName.ItemIndex + 1;

   if ENPlanWorkObj.sourceRef = nil then ENPlanWorkObj.sourceRef := ENPlanWorkSourceRef.Create;
   ENPlanWorkObj.sourceRef.code := cbENPlanWorkSource.ItemIndex + 1;

   if (((ENPlanWorkObj.kind.code = ENPLANWORKKIND_FACT) or ( ENPlanWorkObj.kind.code = ENPLANWORKKIND_NPZ ))
       and (ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_TRANSPORT) and (ENPlanWorkObj.elementRef.code <> CARGO_OBJECT))
   then
   begin
       if not NoBlankValues([edtCommentGen])then
       begin
         Application.MessageBox(PChar('Введіть маршрут КУДИ ... !'),PChar('Увага !'),MB_ICONWARNING+MB_OK);
         Action := caNone;
         Exit;
       end;
       //lblCommentGen.Caption := 'Маршрут КУДИ'
   end;


    TempENElement := HTTPRIOENElement as ENElementControllerSoapPort;
    element := TempENElement.getObject(ENPlanWorkObj.elementRef.code);


    if DialogState = dsInsert then
    begin
      ENPlanWorkObj.code:=low(Integer);

      /////
      ENPlanWorkObj.yearOriginal := Low(Integer) ;
      ENPlanWorkObj.monthOriginal := Low(Integer) ;
      /////
      if ( (isTechConditions) and (not isServicesFromSide ) ) then
      begin
        ENPlanWorkObj.code := TempENPlanWork.addPlanByTechConditions(ENPlanWorkObj, techCondServicesObjCode);
      end
      else
      if ( isServicesFromSide  ) then
      begin
        ENPlanWorkObj.code := TempENPlanWork.addPlanByTechConditionsAndServicesFromSide(ENPlanWorkObj, techCondServicesObjCode , ServicesFromSideCode);
      end
      else
      if isShiftLinesServices then
      begin
         ENPlanWorkObj.code := TempENPlanWork.addPlanByShiftLinesServices(ENPlanWorkObj, servicesObjCode);
      end else
      
        case element.typeRef.code of
          1,2,3,11 : ENPlanWorkObj.code := TempENPlanWork.addBySRS(ENPlanWorkObj);
          5 : ENPlanWorkObj.code := TempENPlanWork.addBySVES(ENPlanWorkObj);
          6 : ENPlanWorkObj.code := TempENPlanWork.addBySPS(ENPlanWorkObj);
          7 :
          //не счетчики

          if
          (element.code = ENELEMENT_ABSTRACT_RECORDPOINT) then
          ENPlanWorkObj.code := TempENPlanWork.addByByt(ENPlanWorkObj)
          else

          Application.MessageBox(PChar('План треба створити з біллінгової програми!'),PChar('Увага !'),MB_ICONWARNING+MB_OK);
          8 :
          {
          if ((ENPlanWorkObj.typeRef.code<>100)
          and (ENPlanWorkObj.typeRef.code<>102)
          and (ENPlanWorkObj.typeRef.code<>103)
          and (ENPlanWorkObj.typeRef.code<>104)) then
          ENPlanWorkObj.code := TempENPlanWork.addByProm(ENPlanWorkObj)
          else
          }
          Application.MessageBox(PChar('План треба створити з біллінгової програми!'),PChar('Увага !'),MB_ICONWARNING+MB_OK);

          9 : ENPlanWorkObj.code := TempENPlanWork.addByRZA(ENPlanWorkObj);
          10 : ENPlanWorkObj.code := TempENPlanWork.addBySDTU(ENPlanWorkObj);
          12 : if ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_TRANSPORT then
              begin
                  Application.MessageBox(PChar('Плани на перевезення складаються з Рух транспорту ==> Перевезення'),PChar('Увага !'),MB_ICONERROR+MB_OK);
                  Exit;
              end
              else
                  ENPlanWorkObj.code := TempENPlanWork.addByTransport(ENPlanWorkObj);
          //13 : ENPlanWorkObj.code := TempENPlanWork.addByMetrologyCounters(ENPlanWorkObj);
          //14 : ENPlanWorkObj.code := TempENPlanWork.addByMetrologyDevices(ENPlanWorkObj);
          13,14,18 : ENPlanWorkObj.code := TempENPlanWork.addByMetrology(ENPlanWorkObj);
          15 : ENPlanWorkObj.code := TempENPlanWork.addByBuilder(ENPlanWorkObj);
          16 : ENPlanWorkObj.code := TempENPlanWork.addBySIT(ENPlanWorkObj);
          17 : ENPlanWorkObj.code := TempENPlanWork.addByIzolation(ENPlanWorkObj);
          19 : ENPlanWorkObj.code := TempENPlanWork.addByPurchasesObject(ENPlanWorkObj);
          20 : ENPlanWorkObj.code := TempENPlanWork.addByPurchasesNoObject(ENPlanWorkObj);
          21 : ENPlanWorkObj.code := TempENPlanWork.addByTransformerObject(ENPlanWorkObj);
          22 : ENPlanWorkObj.code := TempENPlanWork.addByOperativeObject(ENPlanWorkObj);
          // запретить ввод месячных планов по услугам на сторону с клиента 23 : ENPlanWorkObj.code := TempENPlanWork.addByServicesObject(ENPlanWorkObj);
          23 : if (( ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_SIDEWORKS) AND (ENPlanWorkObj.stateRef.code = ENPLANWORKSTATE_SIDEWORKS) and (ENPlanWorkObj.kind.code = 2) ) then
              begin
                  Application.MessageBox(PChar('Плани по послугам на сторону складаються з Послуги на сторону ==> Послуги на сторону'),PChar('Увага !'),MB_ICONERROR+MB_OK);
                  Exit;
              end
              else
                  ENPlanWorkObj.code := TempENPlanWork.addByServicesObject(ENPlanWorkObj);



          24 : ENPlanWorkObj.code := TempENPlanWork.addByPreproductionObject(ENPlanWorkObj);
          48 : ENPlanWorkObj.code := TempENPlanWork.addByServicesFromSideObject(ENPlanWorkObj);

          EN_TRUCKING : ENPlanWorkObj.code := TempENPlanWork.addByTrucking(ENPlanWorkObj);
          EN_METROLOGY_SUBSTATION : ENPlanWorkObj.code := TempENPlanWork.addByMetrologySubstation(ENPlanWorkObj);
          EN_EB_OBJECT : ENPlanWorkObj.code := TempENPlanWork.addByEB(ENPlanWorkObj);

          EN_WRITING_NO_OBJECT :  ENPlanWorkObj.code := TempENPlanWork.addByWritingNoObject(ENPlanWorkObj);

          EN_SIZ_OBJECT : ENPlanWorkObj.code := TempENPlanWork.addBySiz(ENPlanWorkObj);
          EN_SZ_BRIGADE : ENPlanWorkObj.code := TempENPlanWork.addBySiz(ENPlanWorkObj);

          EN_EQUIPMENT : ENPlanWorkObj.code := TempENPlanWork.addByEquipment(ENPlanWorkObj);
          EN_EQUIPMENT_REPAIR : ENPlanWorkObj.code := TempENPlanWork.addByEquipmentRepair(ENPlanWorkObj);

          EN_WRITING_NO_OBJECT_RESTOCKING : ENPlanWorkObj.code := TempENPlanWork.addByPVZ(ENPlanWorkObj);

          EN_GIFT_NO_OBJECT : ENPlanWorkObj.code := TempENPlanWork.addByGift(ENPlanWorkObj);

          EN_AVR16_NO_OBJECT : ENPlanWorkObj.code := TempENPlanWork.addByAVR16(ENPlanWorkObj);
          
          else
          begin
            Application.MessageBox(PChar('Невідомий тип Об''єкту !!!'), PChar('Помилка'), MB_ICONERROR);
            exit;
          end;
        end;

    end
    else
    if DialogState = dsEdit then
    begin

    if (((element.typeRef.code=7) or (element.typeRef.code=8))
       and (ENPlanWorkObj.kind.code <3)
// 20141010 - кроме годовых их и так генерим автоматом
       and (ENPlanWorkObj.kind.code <> 1) and (element.code <> ENELEMENT_ABSTRACT_RECORDPOINT))
    then
    Application.MessageBox(PChar('План треба створити з біллінгової програми!'),PChar('Увага !'),MB_ICONWARNING+MB_OK)
    else
      TempENPlanWork.save(ENPlanWorkObj);
    {
      case element.typeRef.code of
        1,2,3 : TempENPlanWork.saveBySRS(ENPlanWorkObj);
        5 : TempENPlanWork.saveBySVES(ENPlanWorkObj);
        6 : TempENPlanWork.saveBySPS(ENPlanWorkObj);
        7 : TempENPlanWork.saveByByt(ENPlanWorkObj);
        8 : TempENPlanWork.saveByProm(ENPlanWorkObj);
        else
        begin
          Application.MessageBox(PChar('Невідомий тип Об"єкту !!!'), PChar('Помилка'), MB_ICONERROR);
          exit;
        end;
      end;
     }
    end;
  end;
end;

procedure TfrmENPlanWorkEdit.spbENPlanWorkStatusClick(Sender : TObject);
var
   frmENPlanWorkStatusShow: TfrmENPlanWorkStatusShow;
begin
   frmENPlanWorkStatusShow:=TfrmENPlanWorkStatusShow.Create(Application,fmNormal);
   try
      with frmENPlanWorkStatusShow do
        if ShowModal = mrOk then
        begin
            try
               if ENPlanWorkObj.status = nil then ENPlanWorkObj.status := ENPlanWorkStatus.Create();
               ENPlanWorkObj.status.code := StrToInt(GetReturnValue(sgENPlanWorkStatus,0));
               edtENPlanWorkStatusName.Text := GetReturnValue(sgENPlanWorkStatus,1);
            except
               on EConvertError do Exit;
            end;
        end;
   finally
      frmENPlanWorkStatusShow.Free;
   end;
end;



procedure TfrmENPlanWorkEdit.spbENElementClick(Sender: TObject);
var
   frmENElementShow: TfrmENElementShow;
   f, tmpF : ENElementFilter;
   invNum , depName: String;
   depCode, elCode : Integer;
   depShort : ENDepartmentShort;
   //o : EN

   elList: ENElementShortList;
   elObj: ENElementShort;
   isMetrologyObject, isSizObject, isSzBrigade, isOperativeObj, isBuilderObject : Boolean;
begin
   f := ENElementFilter.Create;
     SetNullIntProps(f);
     SetNullXSProps(f);
     f.orderBySQL := 'typerefcode';
     //f.conditionSQL := 'code in (select elementrefcode from enline10 union all select elementcode from enlin150 union all select elementrefcode from ensubstation10 union all select elementrefcode from ensubstation150)
   frmENElementShow:=TfrmENElementShow.Create(Application,fmNormal, f);

   try
      DisableActions([frmENElementShow.actInsert, frmENElementShow.actEdit , frmENElementShow.actDelete]);
      if (isTransport) then frmENElementShow.isTransport := true;
      if (isOperative) then frmENElementShow.isOperative := true;

      with frmENElementShow do
        if ShowModal = mrOk then
        begin

            try
               //enObj := DMReports.getIByElement(StrToInt(GetReturnValue(sgENElement,0)));

               isMetrologyObject := false;
               isSizObject := false;
               isSzBrigade := false;
               isOperativeObj := false;
               isBuilderObject := false;
               inServices := false;
               isMeasurement := false;
               isGiftObj := false;
               
               try
                 //elCode := DMReports.s
                 tmpF := ENElementFilter.Create;
                 SetNullIntProps(tmpF);
                 SetNullXSProps(tmpF);

                 tmpF.code := StrToInt(GetReturnValue(sgENElement,0));
                 elList := DMReports.searchElements(tmpF, 0, -1, '', '');

                 if elList <> nil then
                   if elList.list <> nil then
                     if elList.list[0] <> nil then
                     begin
                       if elList.list[0].typeRefCode = EN_METROLOGY_OBJECT then
                         isMetrologyObject := true;
                       if elList.list[0].typeRefCode = EN_SIZ_OBJECT then
                         isSizObject := true;
                       if elList.list[0].typeRefCode = EN_SZ_BRIGADE then
                         isSzBrigade := true;
                       if elList.list[0].typeRefCode = EN_OPERATIVE_OBJECT then
                         isOperativeObj := true;

                       // 03.01.2012 +++ объекты строительной службы
                       if elList.list[0].typeRefCode = EN_BUILDER then
                         isBuilderObject := true;

                       // 05.01.2012 +++ объекты договор (Услуги со стороны)
                       if ((elList.list[0].typeRefCode = EN_SERVICES_FROM_SIDE_OBJECT) or (isServicesFromSide) ) then
                         inServices := true;

                       // 30.03.2012 +++ "Об'єкти договорів на ТО та ремонт"
                       // для планов с типом акта "Вимірювання та випробування"
                       if elList.list[0].typeRefCode = EN_OPERATIVE_OBJECT then
                         isMeasurement := true;

                       // Благодійна допомога
                       if elList.list[0].typeRefCode = EN_GIFT_NO_OBJECT then
                         isGiftObj := true;

                     end;
               except
               end;

               // инвентарные для Центра ответсвенности 

               invNum := GetReturnValue(sgENElement,3) ; //DMReports.getInvNumByElement(StrToInt(GetReturnValue(sgENElement,0)));

               //// 03.01.2012 +++  проверка ушла на сервак.....

              { if (length(invNum) < 5) and (not isMetrologyObject) and (not isSizObject) and (not isSzBrigade)
                    and (not isOperativeObj) and (not isBuilderObject) then
               begin
                   if ENPlanWorkObj.typeRef <> nil then
                   begin
                     if not (ENPlanWorkObj.typeRef.code in [ENPLANWORKTYPE_INVEST, ENPLANWORKTYPE_CN, ENPLANWORKTYPE_TMC_TRANSFER]) then // Приєднання, Виконання інвестиційної програми (капітальне будівництво)
                     begin
                       Application.MessageBox(PChar('Плани можливо заносити тільки для об''єктів з інвентарним номером !!!' + ' "' + invNum +'" < 5 символов'), PChar('Ошибка'), MB_ICONERROR);
                       exit;
                     end;
                   end
                   else
                   begin
                       Application.MessageBox(PChar('Плани можливо заносити тільки для об"єктів з інвентарним номером !!!' + ' "' + invNum +'" < 5 символов'), PChar('Ошибка'), MB_ICONERROR);
                       exit;
                   end;
               end; }

               if ENPlanWorkObj.elementRef = nil then ENPlanWorkObj.elementRef := ENElementRef.Create();
              // ENPlanWorkObj.status.code := StrToInt(GetReturnValue(sgENPlanWorkStatus,0));
               ENPlanWorkObj.elementRef.code := StrToInt(GetReturnValue(sgENElement,0));
               edtENElementName.Text := GetReturnValue(sgENElement,1);
               edtInvNumber.Text := GetReturnValue(sgENElement,3);



               // 20.11.2014 +++ возможность изменить объект планирования
               // при редактировании планов на услугу тех.надзора
               if not isTechAgreement then
               begin
                 /////************************************************************
                 // 11.04.12 NET-1622 После копирования плана даем менять объект,
                 // но после его изменения сбрасываем подвид работ, тип акта и т.д.
                 // (чтобы не создавали никаких левых планов)
                 ENPlanWorkObj.typeRef := nil;
                 ENPlanWorkObj.stateRef := nil;
                 ENPlanWorkObj.servicesFSideFinId := LOW_INT;
                 ENPlanWorkObj.servicesFSideCnNum := '';
                 ENPlanWorkObj.ddsCodes := nil;
                 ENPlanWorkObj.invgroupRef := nil;
                 {isservicesfromside}
                 if not isServicesFromSide then
                 ClearControls([edtTypeName, edtWorkState,
                                edtServicesFromSide, edtDdsCodes, edtInvestProgramGroupsName, edtInvestItemNumber]);

                 HideControls([gbServicesFromSide, lblServicesFromSide, edtServicesFromSide, spbServicesFromSide,
                               gbDddsCodes, edtDdsCodes, spbDdsCodes,
                               gbInvestProgramGroups, spbInvestProgramGroups]);
                 HideControls([gbBindingOver, gbReason]);
                 DisableControls([edtBindingOver, spbBindingOver, edtDdsCodes, edtInvestProgramGroupsName]);
                 /////************************************************************
               end;


               if  ENPlanWorkObj.renRef = nil then ENPlanWorkObj.renRef := EPRenRef.Create;
               ENPlanWorkObj.renRef.code := ENElementShort(sgENElement.Objects[0,sgENElement.Row]).renRefCode ;
               edtEPRenName.Text := GetReturnValue(sgENElement,2);

               if (isSizObject) then
                 begin
                 depShort := DMReports.getDepartmentBySizCode(ENElementShort(sgENElement.Objects[0,sgENElement.Row]).code);
                  if depShort = nil then
                   begin
                     depShort := DMReports.getDepartmentByRenCode(ENPlanWorkObj.renRef.code, ENMANAGEMENT_TYPE_TECHNICAL);
                   end
                 end else
                  // подкинуть депртамент ...
                 depShort := DMReports.getDepartmentByRenCode(ENPlanWorkObj.renRef.code, ENMANAGEMENT_TYPE_TECHNICAL);

              if not isTechAgreement then
              begin
                if depShort <> nil then
                begin
                  if ENPlanWorkObj.departmentRef = nil then  ENPlanWorkObj.departmentRef := ENDepartmentRef.Create;
                  ENPlanWorkObj.departmentRef.code := depShort.code;
                  edtDepartment.Text:= depShort.shortName;
                end;
              end;

              /// 30.08.11
              //DisableControls([spbType , spbENPlanWorkState], false);

              if (not inServices) then
                DisableControls([spbType], false);

              if (not isOperativeObj) then
                DisableControls([spbENPlanWorkState], false);
              ///


              if (isOperative) then
              begin
                HideControls([lblENActNumber, edtENActNumber, spbENAct, lblPK, edtCode]);

                DisableControls([cbPlanWorkKind , spbType ], false);
                DisableControls([edtWorkState, spbENPlanWorkState]);

                ENPlanWorkObj.stateRef := ENPlanWorkStateRef.Create();
                ENPlanWorkObj.stateRef.code := ENPLANWORKSTATE_SIDEWORKS;
                edtWorkState.Text := 'Роботи на сторону';
              end
              else
              if (isMeasurement) and (not isOperative) then
              begin
                DisableControls([edtWorkState, spbENPlanWorkState]);
                ENPlanWorkObj.stateRef := ENPlanWorkStateRef.Create();
                ENPlanWorkObj.stateRef.code := ENPLANWORKSTATE_MEASUREMENT;
                edtWorkState.Text := 'Вимірювання та випробування';
              end;

              ////// 11.04.12 NET-1622 После копирования плана даем менять объект *************
              //DisableControls([spbENElement]);
              if not isForCopy then
                DisableControls([spbENElement]);

             // 20.11.2014 +++ возможность изменить объект планирования
             // при редактировании планов на услугу тех.надзора
             if isTechAgreement then
               DisableControls([spbENElement], False);

              if (isSizObject) then
                begin
                  ENPlanWorkObj.typeRef := ENPlanWorkTypeRef.Create();
                  ENPlanWorkObj.typeRef.code := ENPLANWORKTYPE_SIZ;
                  edtTypeName.Text:= 'Засоби захисту';
                  ENPlanWorkObj.stateRef := ENPlanWorkStateRef.Create();
                  ENPlanWorkObj.stateRef.code := ENPLANWORKSTATE_SIZ;
                  edtWorkState.Text:= 'Спецодяг';
                  cbPlanWorkKind.ItemIndex := ENPLANWORKKIND_CURRENT-1;

                  ENPlanWorkObj.budgetRef := ENDepartmentRef.Create();
                  ENPlanWorkObj.budgetRef.code := ENBUDGET_SOT;
                  edtENBudgetName.Text := 'СОТ';

                  ENPlanWorkObj.responsibilityRef := ENDepartmentRef.Create();
                  ENPlanWorkObj.responsibilityRef.code := ENRESPONSIBILITY_SOT;
                  edtResponsibility.Text := 'СОТ';

    	            ENPlanWorkObj.finExecutor := FINExecutor.Create();
                  ENPlanWorkObj.finExecutor.name := 'Відділ матеріально-технічного постачання';
                  ENPlanWorkObj.finExecutor.finCode := 45;
                  ENPlanWorkObj.finExecutor.finTypeName := 'Промышленный персонал';
                  ENPlanWorkObj.finExecutor.finTypeCode := 1;
                  ENPlanWorkObj.finExecutor.finKindName := 'Відділ';
                  ENPlanWorkObj.finExecutor.finKindCode := 3;
                  ENPlanWorkObj.finExecutor.finCehName := 'Апарат управління';
                  ENPlanWorkObj.finExecutor.finCehCode := 3;
                  edtFINExecutorName.Text := 'Відділ матеріально-технічного постачання';

                  DisableControls([spbENElement, edtTypeName, spbType, edtWorkState, spbENPlanWorkState,
                      edtENBudgetName, edtResponsibility, spbENBudget, spbResponsibility]);
                end;

              if (isSzBrigade) then
                begin
                  ENPlanWorkObj.typeRef := ENPlanWorkTypeRef.Create();
                  ENPlanWorkObj.typeRef.code := ENPLANWORKTYPE_SIZ;
                  edtTypeName.Text:= 'Засоби захисту';
                  ENPlanWorkObj.stateRef := ENPlanWorkStateRef.Create();
                  ENPlanWorkObj.stateRef.code := ENPLANWORKSTATE_BSZ;
                  edtWorkState.Text:= 'Бригадні ЗЗ';
                  cbPlanWorkKind.ItemIndex := ENPLANWORKKIND_CURRENT-1;

                  ENPlanWorkObj.budgetRef := ENDepartmentRef.Create();
                  ENPlanWorkObj.budgetRef.code := 500000003;
                  edtENBudgetName.Text := 'СОТ';

                  ENPlanWorkObj.responsibilityRef := ENDepartmentRef.Create();
                  ENPlanWorkObj.responsibilityRef.code := 500000002;
                  edtResponsibility.Text := 'СОТ';

    	            ENPlanWorkObj.finExecutor := FINExecutor.Create();
                  ENPlanWorkObj.finExecutor.name := 'Відділ матеріально-технічного постачання';
                  ENPlanWorkObj.finExecutor.finCode := 45;
                  ENPlanWorkObj.finExecutor.finTypeName := 'Промышленный персонал';
                  ENPlanWorkObj.finExecutor.finTypeCode := 1;
                  ENPlanWorkObj.finExecutor.finKindName := 'Відділ';
                  ENPlanWorkObj.finExecutor.finKindCode := 3;
                  ENPlanWorkObj.finExecutor.finCehName := 'Апарат управління';
                  ENPlanWorkObj.finExecutor.finCehCode := 3;
                  edtFINExecutorName.Text := 'Відділ матеріально-технічного постачання';

                  DisableControls([spbENElement, edtTypeName, spbType, edtWorkState, spbENPlanWorkState,
                      edtENBudgetName, edtResponsibility, spbENBudget, spbResponsibility]);
                end;


                // +++++isservicesfromside
                if (isServicesFromSide) then
                begin
                  HideControls([gbServicesFromSide, lblServicesFromSide, edtServicesFromSide, spbServicesFromSide,
                               gbDddsCodes, edtDdsCodes, spbDdsCodes,
                               gbInvestProgramGroups, spbInvestProgramGroups],false);
                  DisableControls([spbServicesFromSide]);
                end;


            except
               on EConvertError do Exit;
            end;
        end;
   finally
      frmENElementShow.Free;
   end;
end;

procedure  TfrmENPlanWorkEdit.updateSCCounterGrid(estimateItemCode : Integer; stringGrid : TAdvStringGrid );
var
  i, ColCount : Integer;
  SCCounterList: SCCounterShortList;
begin

    ClearGrid(stringGrid);


    SCCounterList := Self.getSCCountersListByEstimate(estimateItemCode);


    ColCount := 100;

    if High(SCCounterList.list) > -1 then
       stringGrid.RowCount := High(SCCounterList.list) + 2
    else
       stringGrid.RowCount := 2;

     with stringGrid do
      for i:=0 to High(SCCounterList.list) do
        begin
          Application.ProcessMessages;
          if SCCounterList.list[i].code <> Low(Integer) then
            Cells[0,i+1] := IntToStr(SCCounterList.list[i].code)
          else
            Cells[0,i+1] := '';
          Cells[1,i+1] := SCCounterList.list[i].invNumber;
          Cells[2,i+1] := SCCounterList.list[i].name;
          Cells[3,i+1] := SCCounterList.list[i].buildNumber;
          Cells[4,i+1] := SCCounterList.list[i].account;
          Cells[5,i+1] := SCCounterList.list[i].departmetFKCode;
          Cells[6,i+1] := SCCounterList.list[i].molCode;
          if SCCounterList.list[i].dateIn = nil then
            Cells[7,i+1] := ''
          else
            Cells[7,i+1] := XSDate2String(SCCounterList.list[i].dateIn);
          if SCCounterList.list[i].dateBuild = nil then
            Cells[8,i+1] := ''
          else
            Cells[8,i+1] := XSDate2String(SCCounterList.list[i].dateBuild);
          if SCCounterList.list[i].dateCheck = nil then
            Cells[9,i+1] := ''
          else
            Cells[9,i+1] := XSDate2String(SCCounterList.list[i].dateCheck);
          if SCCounterList.list[i].cost = nil then
            Cells[10,i+1] := ''
          else
            Cells[10,i+1] := SCCounterList.list[i].cost.DecimalString;
          if SCCounterList.list[i].scCode = Low(Integer) then
            Cells[11,i+1] := ''
          else
            Cells[11,i+1] := IntToStr(SCCounterList.list[i].scCode);
          Cells[12,i+1] := SCCounterList.list[i].counterType;
          Cells[13,i+1] := SCCounterList.list[i].installOrderNumber;
          Cells[14,i+1] := SCCounterList.list[i].reading;
          if SCCounterList.list[i].dateEdit = nil then
            Cells[15,i+1] := ''
          else
            Cells[15,i+1] := XSDateTimeWithDate2String(SCCounterList.list[i].dateEdit);
          //LastRow:=i+1;
          stringGrid.RowCount := i + 2;
        end;
     ColCount := ColCount + 1;
     stringGrid.Row:=1;
end;

procedure TfrmENPlanWorkEdit.updateSCSealGrid(estimateItemCode: Integer);
var
  TempSCSeal: SCSealControllerSoapPort;
  scSealFilterObj: SCSealFilter;
  sealList: SCSealShortList;
  i: Integer;
begin
  ClearGrid(sgSCSeal);

  scSealFilterObj := SCSealFilter.Create;
  SetNullIntProps(scSealFilterObj);
  SetNullXSProps(scSealFilterObj);

  scSealFilterObj.estimateItemRef := ENEstimateItemRef.Create;
  scSealFilterObj.estimateItemRef.code := estimateItemCode;

  scSealFilterObj.statusRef := SCSealStatusRef.Create;
  scSealFilterObj.statusRef.code := SCSEALSTATUS_UNINSTALLED;

  scSealFilterObj.orderBySQL := 'buildnumber';

  TempSCSeal := HTTPRIOSCSeal as SCSealControllerSoapPort;

  sealList := TempSCSeal.getScrollableFilteredList(scSealFilterObj, 0, -1);

  if High(sealList.list) > -1 then
    sgSCSeal.RowCount := High(sealList.list) + 2
  else
    sgSCSeal.RowCount := 2;

  with sgSCSeal do
    for i := 0 to High(sealList.list) do
    begin
      Application.ProcessMessages;

      if sealList.list[i].code <> Low(Integer) then
        Cells[0,i+1] := IntToStr(sealList.list[i].code)
      else
        Cells[0,i+1] := '';

      //Cells[1,i+1] := sealList.list[i].invNumber;
      Cells[1,i+1] := sealList.list[i].buildNumber;

      Cells[2,i+1] := sealList.list[i].name;
      //Cells[3,i+1] := sealList.list[i].buildNumber;
      Cells[3,i+1] := sealList.list[i].invNumber;
      Cells[4,i+1] := sealList.list[i].account;
      Cells[5,i+1] := sealList.list[i].departmetFKCode;
      Cells[6,i+1] := sealList.list[i].molCode;

      if sealList.list[i].dateIn = nil then
        Cells[7,i+1] := ''
      else
        Cells[7,i+1] := XSDate2String(sealList.list[i].dateIn);
      if sealList.list[i].dateBuild = nil then
        Cells[8,i+1] := ''
      else
        Cells[8,i+1] := XSDate2String(sealList.list[i].dateBuild);

      if sealList.list[i].cost = nil then
        Cells[9,i+1] := ''
      else
        Cells[9,i+1] := sealList.list[i].cost.DecimalString;

      if sealList.list[i].scCode = Low(Integer) then
        Cells[10,i+1] := ''
      else
        Cells[10,i+1] := IntToStr(sealList.list[i].scCode);

      Cells[11,i+1] := sealList.list[i].zoneRefName;
      //Cells[12,i+1] := sealList.list[i].counterType;

      Objects[0, i+1] := TObject(sealList.list[i].typeRefCode);
      Objects[1, i+1] := TObject(sealList.list[i].zoneRefCode);

      sgSCSeal.RowCount := i + 2;
    end;

  sgSCSeal.Row := 1;
end;

procedure TfrmENPlanWorkEdit.updateFINMaterialsGrid(estimateItemCode : Integer; stringGrid : TAdvStringGrid );
var
  TempFINMaterials: FINMaterialsControllerSoapPort;
  j,i: Integer;
  finList: FINMaterialsShortList; //List_;
  finFilter : FINMaterialsFilter;
begin
   for i:=1 to stringGrid.RowCount-1 do
     for j:=0 to stringGrid.ColCount-1 do
       stringGrid.Cells[j,i]:='';

  SetGridHeaders(FINMaterialsShortHeaders, stringGrid.ColumnHeaders);

  finFilter := FINMaterialsFilter.Create;
  SetNullIntProps(finFilter);
  SetNullXSProps(finFilter);
  finFilter.estimateItemRef := ENEstimateItemRef.Create;
  finFilter.estimateItemRef.code := estimateItemCode;

  finFilter.statusRef := FINMaterialsStatusRef.Create;
  finFilter.statusRef.code := FINMATERIALSSTATUS_GOOD;

  {
  if ENPlanWorkObj.kind.code = ENPLANWORKKIND_CURRENT then
  begin

  end;
  }

  TempFINMaterials :=  HTTPRIOFINMaterials as FINMaterialsControllerSoapPort;

  finList := TempFINMaterials.getScrollableFilteredList(finFilter,0,-1);

  if High(finList.list) > -1 then
     stringGrid.RowCount:=High(finList.list)+2
  else
     stringGrid.RowCount:=2;

   with stringGrid do
    for i:=0 to High(finList.list) do
      begin
        Application.ProcessMessages;
        if finList.list[i].code <> Low(Integer) then
        Cells[0,i+1] := IntToStr(finList.list[i].code)
        else
        Cells[0,i+1] := '';

        Cells[1,i+1] := finList.list[i].mat_name;
        Cells[2,i+1] := finList.list[i].mu_name;
        Cells[3,i+1] := finList.list[i].nn;

        if finList.list[i].quantity = nil then
          Cells[4,i+1] := ''
        else
          Cells[4,i+1] := finList.list[i].quantity.DecimalString;

{перенесено в калк_прайс

        if finList.list[i].price = nil then
          Cells[5,i+1] := ''
        else
          Cells[5,i+1] := finList.list[i].price.DecimalString;
}
        if finList.list[i].calc_price = nil then
          Cells[5,i+1] := ''
        else
          Cells[5,i+1] := finList.list[i].calc_price.DecimalString;

        if finList.list[i].cost = nil then
          Cells[6,i+1] := ''
        else
          Cells[6,i+1] := finList.list[i].cost.DecimalString;


        Cells[7,i+1] := finList.list[i].div_name;

        Cells[8,i+1] := finList.list[i].rest_purpose_name;

        Cells[9,i+1] := finList.list[i].partner_name;

        if finList.list[i].doc_date = nil then
          Cells[10,i+1] := ''
        else
          Cells[10,i+1] := XSDate2String(finList.list[i].doc_date);

        Cells[11,i+1] := finList.list[i].party_discription;

        Cells[12,i+1] := IntToStr(finList.list[i].party_id);

        Cells[13, i+1] := finList.list[i].userGen ;

        if finList.list[i].dateEdit <> nil then
          Cells[14, i+1] := XSDateTimeWithDate2String(finList.list[i].dateEdit)
        else
          Cells[14, i+1] := '';

        {
        Cells[5,i+1] := finList.list[i].partner_name;
        if FINMaterialsList.list[i].doc_date = nil then
          Cells[6,i+1] := ''
        else
          Cells[6,i+1] := XSDate2String(FINMaterialsList.list[i].doc_date);
        Cells[7,i+1] := FINMaterialsList.list[i].party_discription;
        if FINMaterialsList.list[i].rest_purpose_id = Low(Integer) then
          Cells[8,i+1] := ''
        else
          Cells[8,i+1] := IntToStr(FINMaterialsList.list[i].rest_purpose_id);
        Cells[9,i+1] := FINMaterialsList.list[i].rest_purpose_name;
        if FINMaterialsList.list[i].rest_purpose_type_id = Low(Integer) then
          Cells[10,i+1] := ''
        else
          Cells[10,i+1] := IntToStr(FINMaterialsList.list[i].rest_purpose_type_id);
        if FINMaterialsList.list[i].budget_core_id = Low(Integer) then
          Cells[11,i+1] := ''
        else
          Cells[11,i+1] := IntToStr(FINMaterialsList.list[i].budget_core_id);
        if FINMaterialsList.list[i].frc_code = Low(Integer) then
          Cells[12,i+1] := ''
        else
          Cells[12,i+1] := IntToStr(FINMaterialsList.list[i].frc_code);
        Cells[13,i+1] := FINMaterialsList.list[i].frc_name;
        if FINMaterialsList.list[i].calc_price = nil then
          Cells[14,i+1] := ''
        else
          Cells[14,i+1] := FINMaterialsList.list[i].calc_price.DecimalString;
        if FINMaterialsList.list[i].quantity = nil then
          Cells[15,i+1] := ''
        else
          Cells[15,i+1] := FINMaterialsList.list[i].quantity.DecimalString;
        if FINMaterialsList.list[i].price = nil then
          Cells[16,i+1] := ''
        else
          Cells[16,i+1] := FINMaterialsList.list[i].price.DecimalString;
        if FINMaterialsList.list[i].cost = nil then
          Cells[17,i+1] := ''
        else
          Cells[17,i+1] := FINMaterialsList.list[i].cost.DecimalString;
        Cells[18,i+1] := FINMaterialsList.list[i].bal_sch;
        LastRow:=i+1;
        }
        stringGrid.RowCount:= i + 2;
      end;
   //ColCount:=ColCount+1;
   stringGrid.Row:=1;



end;



procedure TfrmENPlanWorkEdit.pcPlanWorkChange(Sender: TObject);
var
  TempENPlanWorkItem: ENPlanWorkItemControllerSoapPort;
  i,LastCountM: Integer;
  ENPlanWorkItemList: ENPlanWorkItemShortList;

  TempENEstimateItem: ENEstimateItemControllerSoapPort;
  ENEstimateItemList: ENEstimateItemShortList;

  TempENPlanWorkMoveHistory: ENPlanWorkMoveHistoryControllerSoapPort;
  ENPlanWorkMoveHistoryList: ENPlanWorkMoveHistoryShortList;

  TempENPlanCorrectHistory : ENPlanCorrectHistoryControllerSoapPort;
  ENPlanCorrectHistoryList : ENPlanCorrectHistoryShortList;

    TempENHumenItem: ENHumenItemControllerSoapPort;
    ENHumenItemList: ENHumenItemShortList;
    TempENTransportItem: ENTransportItemControllerSoapPort;
    ENTransportItemList: ENTransportItemShortList;

  humenItemFilter : ENHumenItemFilter;
  transportItemFilter : ENTransportItemFilter;

  CCOutageNoticeList:CCOutageNoticeShortList;

  TempENTransportOrder : ENTransportOrderControllerSoapPort;
  ENTransportOrderList : ENTransportOrderShortList;

  j : Integer;
  toCount, toLastRow : Integer;
  toColCount : Integer;

  vNormOfTime, vCountGen: Double;

  trArr : ArrayOfInteger;
  sql : string;
  intCodesList: ENPlanWorkController.ArrayOfInteger;
  strCodesList: String;

{  TempENWorkOrder2ENPlanWork: ENWorkOrder2ENPlanWorkControllerSoapPort;
  //i: Integer;
  ENWorkOrder2ENPlanWorkList: ENWorkOrder2ENPlanWorkShortList;
  ENWorkOrder2ENPlanWorkFilterObj : ENWorkOrder2ENPlanWorkFilter;

  frmENWorkOrderEdit : TfrmENWorkOrderEdit;
  TempENWorkOrder : ENWorkOrderControllerSoapPort; }

  TempRQFKOrder: RQFKOrderControllerSoapPort;
  RQFKOrderList: RQFKOrderShortList;
  RQFKOrderFilterObj : RQFKOrderFilter;

  TempENPlanWork: ENPlanWorkControllerSoapPort;


  TempCCOutageNotice:CCOutageNoticeControllerSoapPort;

  linkedPlansList: ENPlanWorkShortList;
  linkedPlansFilterObj : ENPlanWorkFilter;

  TempENPlanWork2CCDamageLog: ENPlanWork2CCDamageLogControllerSoapPort;
  pw2damList: ENPlanWork2CCDamageLogShortList;
  pw2damFilterObj : ENPlanWork2CCDamageLogFilter;

  TempRouteBytDetail : ENRouteBytDetailControllerSoapPort;
  TempRouteBytDetailFilter:ENRouteBytDetailFilter;
  TempRouteBytDetailList:ENRouteBytDetailShortList;

  TempENTransportRoute : ENTransportRouteControllerSoapPort;
  transportRouteList : ENTransportRouteShortList;
  transportRouteFilter : ENTransportRouteFilter;
  countAll:Integer;
  countVip:Integer;

  TempRQTransportInvoice : RQTransportInvoiceControllerSoapPort;
  transportInvoiceList : RQTransportInvoiceShortList;
  transportInvoiceFilter : RQTransportInvoiceFilter;
  tt : Integer;
  tInvCount, tInvLastRow : Integer;
  tInvColCount, tInvLastCount : Integer;

  TempFINExecutor2Plan : FINExecutor2PlanControllerSoapPort;
  FINExecutor2PlanList : FINExecutor2PlanShortList;
  executor2PlanFilter : FINExecutor2PlanFilter;

  TempFINMaterials: FINMaterialsControllerSoapPort;
  ei2FinList: ENEstimateItem2FinShortList;


  TempENPlanWorkENAct2OSData: ENPlanWorkENAct2OSDataControllerSoapPort;
  ENPlanWorkENAct2OSDataList: ENPlanWorkENAct2OSDataShortList;
  ENPlanWorkENActosdatafilter : ENPlanWorkENAct2OSDataFilter;

  element : ENElement;
  TempENElement: ENElementControllerSoapPort;
begin
  HideActions([actZeroPlanWorkItems, actChangeCountFact, actZeroEstimateItems, actSelectAll, actClearAll, actChangePlanWorkItem, actMoveWorkFromAbstractPlan, actMoveWorkToAbstractPlan, actMoveTransportFromWorkToOtherWork]);

  /////
  if DialogState = dsView then
    DisableActions([actInsert, actEdit, actDelete, actMaterialBinding,
                    actWorkOrderInsert, actWorkOrderEdit, actWorkOrderDelete]);
  /////

  if pcPlanWork.ActivePage = tsWriteOffOS then
  begin
    DisableControls([edtKod_inv, edtName_inv]);
  end;

  if pcPlanWork.ActivePage = tsRQTransportInvoice then
  begin
  SetGridHeaders(RQTransportInvoiceHeaders, sgRQTransportInvoice.ColumnHeaders);
  tInvColCount:=100;
  TempRQTransportInvoice :=  HTTPRIORQTransportInvoice as RQTransportInvoiceControllerSoapPort;

  if transportInvoiceFilter = nil then
  begin
     transportInvoiceFilter := RQTransportInvoiceFilter.Create;
     SetNullIntProps(transportInvoiceFilter);
     SetNullXSProps(transportInvoiceFilter);
  end;

     transportInvoiceFilter.planRef := ENPlanWorkRef.Create;
     transportInvoiceFilter.planRef.code := ENPlanWorkObj.code;

  transportInvoiceList := TempRQTransportInvoice.getScrollableFilteredList(transportInvoiceFilter,0,tInvColCount);

  tInvLastCount:=High(transportInvoiceList.list);

  if tInvLastCount > -1 then
     sgRQTransportInvoice.RowCount:=tInvLastCount+2
  else
     sgRQTransportInvoice.RowCount:=2;

   with sgRQTransportInvoice do
    for tt:=0 to tInvLastCount do
      begin
       // Application.ProcessMessages;
        if transportInvoiceList.list[tt].code <> Low(Integer) then
        Cells[0,tt+1] := IntToStr(transportInvoiceList.list[tt].code)
        else
        Cells[0,tt+1] := '';

        Cells[1,tt+1] := transportInvoiceList.list[tt].numberDoc;
        Cells[2,tt+1] := transportInvoiceList.list[tt].numberProject;

        if transportInvoiceList.list[tt].dateGen = nil then
          Cells[3,tt+1] := ''
        else
          Cells[3,tt+1] := XSDate2String(transportInvoiceList.list[tt].dateGen);

        if transportInvoiceList.list[tt].totalWeight = nil then
          Cells[4,tt+1] := ''
        else
          Cells[4,tt+1] := transportInvoiceList.list[tt].totalWeight.DecimalString;

        Cells[5,tt+1] := transportInvoiceList.list[tt].statusRefName;
        Cells[6,tt+1] := transportInvoiceList.list[tt].userGen;

        if transportInvoiceList.list[tt].dateEdit = nil then
          Cells[7,tt+1] := ''
        else
          Cells[7,tt+1] := XSDateTimeWithDate2String(transportInvoiceList.list[tt].dateEdit);

        tInvLastRow:=i+1;
        sgRQTransportInvoice.RowCount:=tInvLastRow+1;
      end;
   tInvColCount:=tInvColCount+1;
   sgRQTransportInvoice.Row:=1;
  end;  

  if pcPlanWork.ActivePage = tsTransportOrder then
  begin
     toColCount := 1;

     SetGridHeaders(ENGroupedTransportItemHeaders, sgGroupedTransportItem.ColumnHeaders);
     TempENTransportOrder := HTTPRIOENTransportOrder as ENTransportOrderControllerSoapPort;
     ENTransportOrderList := TempENTransportOrder.getGroupedTransportListByPlanCode(ENPlanWorkObj.code);

      toCount := High(ENTransportOrderList.list);

    if toCount > -1 then
      sgGroupedTransportItem.RowCount := toCount + 2
    else
      sgGroupedTransportItem.RowCount := 2;

     with sgGroupedTransportItem do
       for i := 0 to toCount do
       begin
         Application.ProcessMessages;

         if ENTransportOrderList.list[i].code <> Low(Integer) then
           Cells[0,i+1] := IntToStr(ENTransportOrderList.list[i].code)
         else
           Cells[0,i+1] := '';

         Cells[1,i+1] := ENTransportOrderList.list[i].transportName;

         if ENTransportOrderList.list[i].timeStart = nil then
           Cells[2,i+1] := ''
         else
           Cells[2,i+1] := XSDateTime2String(ENTransportOrderList.list[i].timeStart);

          if ENTransportOrderList.list[i].dateStart = nil then
           Cells[3,i+1] := ''
         else
           Cells[3,i+1] := XSDateTimeWithoutTime2String(ENTransportOrderList.list[i].dateStart);

           if ENTransportOrderList.list[i].timeFinal = nil then
           Cells[4,i+1] := ''
         else
           Cells[4,i+1] := XSDateTime2String(ENTransportOrderList.list[i].timeFinal);

           if ENTransportOrderList.list[i].dateFinal = nil then
           Cells[5,i+1] := ''
         else
           Cells[5,i+1] := XSDateTimeWithoutTime2String(ENTransportOrderList.list[i].dateFinal);

           Cells[6,i+1] := ENTransportOrderList.list[i].transportRealName;

           Cells[7,i+1] := ENTransportOrderList.list[i].transportRealInvNumber;

           Cells[8,i+1] := ENTransportOrderList.list[i].transportRealGosNumber;

           Cells[9,i+1] := ENTransportOrderList.list[i].userGen;

           if ENTransportOrderList.list[i].dateEdit = nil then
           Cells[10,i+1] := ''
         else
           Cells[10,i+1] := XSDateTimeWithDate2String(ENTransportOrderList.list[i].dateEdit);

         Objects[0,i+1] := TObject(ENTransportOrderList.list[i].transportCode);
         Objects[1,i+1] := TObject(ENTransportOrderList.list[i].transportRealCode);

         toLastRow := i + 1;
         sgGroupedTransportItem.RowCount := toLastRow + 1;
       end;

     //sgENEstimateItem.RowColor[1] := clGreen;

     toColCount := toColCount + 1;
     sgGroupedTransportItem.Row := 1;

  end;

  //DisableActions([actInsert,  actEdit, actDelete, actFilter, actNoFilter], false);

  if pcPlanWork.ActivePage = tsDismount then
  begin
    DisableActions([actInsert,  actEdit, actDelete, actFilter, actNoFilter], not (DialogState in [dsEdit, dsInsert]) );

    // NET-4402... при списании трансформаторов приходуемый лом добавляется автоматически...
    // изменить можно только бал.счет и стоимость...
    transformatorsForRaw := DMReports.checkTransformatorsForRawInPlan(ENPlanWorkObj.code);
    DisableActions([actInsert, actDelete], transformatorsForRaw);

    SetGridHeaders(ENDismountItemHeaders, sgDismount.ColumnHeaders);
    SetGridHeaders(SCCounterHeaders, sgSCCounter.ColumnHeaders);
    SetGridHeaders(ENDismountItemHeaders, sgFINUnmount.ColumnHeaders);

    TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;

    //if estimateItemFilter = nil then
    begin
       estimateItemFilter := ENEstimateItemFilter.Create;
       SetNullIntProps(estimateItemFilter);
       SetNullXSProps(estimateItemFilter);
    end;

    if estimateItemFilter.planRef = nil then estimateItemFilter.planRef := ENPlanWorkRef.Create;
    estimateItemFilter.planRef.code := ENPlanWorkObj.code;

    estimateItemFilter.kindRef := ENEstimateItemKindRef.Create;
    estimateItemFilter.kindRef.code := ENESTIMATEITEMKIND_DISMOUNT;

    ////
    estimateItemFilter.orderBySQL := 'KR.TECHKARTNUMBER, SM.NAME, ENESTIMATEITEMTYPE.CODE';
    ////

    ///
    //pnlLegend.Visible := (ENPlanWorkObj.kind.code in [ENPLANWORKKIND_NPZ, ENPLANWORKKIND_FACT]);
    ///

    ENEstimateItemList := TempENEstimateItem.getScrollableFilteredList(estimateItemFilter, 0, -1);

    eiLastCount := High(ENEstimateItemList.list);

    if eiLastCount > -1 then
      sgDismount.RowCount := eiLastCount + 2
    else
      sgDismount.RowCount := 2;

     with sgDismount do
       for i := 0 to eiLastCount do
       begin
         Application.ProcessMessages;

         if ENEstimateItemList.list[i].code <> Low(Integer) then
           Cells[0,i+1] := IntToStr(ENEstimateItemList.list[i].code)
         else
           Cells[0,i+1] := '';

         Cells[1,i+1] := ENEstimateItemList.list[i].materialRefName;
{
         if ENEstimateItemList.list[i].countGen = nil then
           Cells[2,i+1] := ''
         else
           Cells[2,i+1] := ENEstimateItemList.list[i].countGen.DecimalString;
}
         if ENEstimateItemList.list[i].countFact = nil then
           Cells[2,i+1] := ''
         else
           Cells[2,i+1] := ENEstimateItemList.list[i].countFact.DecimalString;

         Cells[3,i+1] := ENEstimateItemList.list[i].measureType;

         Cells[4,i+1] := ENEstimateItemList.list[i].kartaNum;
         Cells[5,i+1] := ENEstimateItemList.list[i].kartaRefName;
{
         Cells[7,i+1] := ENEstimateItemList.list[i].typeRefName;
}
         Cells[6,i+1] := ENEstimateItemList.list[i].userGen;

         if ENEstimateItemList.list[i].dateEdit = nil then
           Cells[7,i+1] := ''
         else
           Cells[7,i+1] := XSDate2String(ENEstimateItemList.list[i].dateEdit);



         Objects[0,i+1] := TObject(ENEstimateItemList.list[i].typeRefCode);
         Objects[2,i+1] := TObject(ENEstimateItemList.list[i].accountingTypeRefCode);

         eiLastRow := i + 1;
         sgDismount.RowCount := eiLastRow + 1;
       end;

     //sgENEstimateItem.RowColor[1] := clGreen;

     eiColCount := eiColCount + 1;
     sgDismount.Row := 1;

     //sgDismountClick(sgDismount);
     sgENEstimateItemClick(sgDismount);

  end; // selected tsDismountItems

  if pcPlanWork.ActivePage = tsProduced then
  begin
    DisableActions([actInsert,  actEdit, actDelete, actFilter, actNoFilter], not (DialogState in [dsEdit, dsInsert]) );


    SetGridHeaders(ENProducedItemHeaders, sgProduced.ColumnHeaders);
    SetGridHeaders(ENProducedItemHeaders, sgFINProduced.ColumnHeaders);

    TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;

    //if estimateItemFilter = nil then
    begin
       estimateItemFilter := ENEstimateItemFilter.Create;
       SetNullIntProps(estimateItemFilter);
       SetNullXSProps(estimateItemFilter);
    end;

    if estimateItemFilter.planRef = nil then estimateItemFilter.planRef := ENPlanWorkRef.Create;
    estimateItemFilter.planRef.code := ENPlanWorkObj.code;

    estimateItemFilter.kindRef := ENEstimateItemKindRef.Create;
    estimateItemFilter.kindRef.code := ENESTIMATEITEMKIND_PRODUCED;

    ////
    estimateItemFilter.orderBySQL := '';
    ////

    ///
    //pnlLegend.Visible := (ENPlanWorkObj.kind.code in [ENPLANWORKKIND_NPZ, ENPLANWORKKIND_FACT]);
    ///

    ENEstimateItemList := TempENEstimateItem.getScrollableFilteredList(estimateItemFilter, 0, -1);

    eiLastCount := High(ENEstimateItemList.list);

    if eiLastCount > -1 then
      sgProduced.RowCount := eiLastCount + 2
    else
      sgProduced.RowCount := 2;

     with sgProduced do
       for i := 0 to eiLastCount do
       begin
         Application.ProcessMessages;

         if ENEstimateItemList.list[i].code <> Low(Integer) then
           Cells[0,i+1] := IntToStr(ENEstimateItemList.list[i].code)
         else
           Cells[0,i+1] := '';
         Cells[1,i+1] := ENEstimateItemList.list[i].materialRefName;
         if ENEstimateItemList.list[i].countFact = nil then
           Cells[2,i+1] := ''
         else
           Cells[2,i+1] := ENEstimateItemList.list[i].countFact.DecimalString;
         Cells[3,i+1] := ENEstimateItemList.list[i].measureType;
         Cells[4,i+1] := ENEstimateItemList.list[i].userGen;

         if ENEstimateItemList.list[i].dateEdit = nil then
           Cells[5,i+1] := ''
         else
           Cells[5,i+1] := XSDate2String(ENEstimateItemList.list[i].dateEdit);



         Objects[0,i+1] := TObject(ENEstimateItemList.list[i].typeRefCode);
         Objects[2,i+1] := TObject(ENEstimateItemList.list[i].accountingTypeRefCode);

         eiLastRow := i + 1;
         sgProduced.RowCount := eiLastRow + 1;
       end;

     //sgENEstimateItem.RowColor[1] := clGreen;

     eiColCount := eiColCount + 1;
     sgProduced.Row := 1;
     
     sgENEstimateItemClick(sgProduced);

  end; // selected tsProducedItems


  if pcPlanWork.ActivePage = tsWorkOrder then
  begin
    DisableControls([edtWorkOrderNumber, edtDateWorkOrder, edtWorkOrderCommentGen,
                     edtFinMolCode, edtFinMolName, edtFinMechanicCode, edtFinMechanicName
                     {, gbMOLData}, edtDefectShortDescription]);
    DisableActions([actMOLDataInsert, actMOLDataEdit, actMOLDataDelete]);
    LoadWorkOrder;
    // прячем кнопку печати наряд задания если это перевозка грузов
    // 26.11.2014 +++ VS +++ открывем на факте
    //if (ENPlanWorkObj.elementRef.code = CARGO_OBJECT) or (ENPlanWorkObj.kind.code = ENPLANWORKKIND_FACT) then
    if (ENPlanWorkObj.elementRef.code = CARGO_OBJECT) then
       btnPrintWorkOrder.Visible := False;

  end

  else

  if pcPlanWork.ActivePage = tsPlanWorkItems then
  begin

     if ((DialogState = dsEdit) or (DialogState = dsView ))then
     begin
       TempENElement :=  HTTPRIOENElement as ENElementControllerSoapPort;
       element := TempENElement.getObject(ENPlanWorkObj.elementRef.code);

       if (
         (  ENPlanWorkObj.kind.code = ENConsts.ENPLANWORKKIND_CURRENT )
         and (   (element.typeRef.code = EN_LINE10) or
                 (element.typeRef.code = EN_LINE04) or
                 (element.typeRef.code = EN_SUBSTATION04) or
                 (element.typeRef.code = EN_LINE150) or
                 (element.typeRef.code = EN_SUBSTATION150) or
                 (element.typeRef.code = EN_LINE_CABLE) or
                 (element.typeRef.code = EN_RZA)
               )
          )
           then
       HideControls([btnGraph] , false)
       else
       HideControls([btnGraph]);
     end
     else
       HideControls([btnGraph]);


   // if (isTransport) then
   //   DisableActions([actInsert, actEdit, actDelete, actFilter, actNoFilter])
   // else
      DisableActions([actInsert, actEdit, actDelete, actFilter, actNoFilter], not (DialogState in [dsEdit, dsInsert]));

   HideActions([actChangePlanWorkItem, actMoveWorkFromAbstractPlan, actMoveWorkToAbstractPlan, actMoveTransportFromWorkToOtherWork], False);

   if (ENPlanWorkObj.elementRef.code = CARGO_OBJECT) then
    DisableActions([actInsert], true);

    if DialogState = dsEdit then
    begin
      HideActions([actZeroPlanWorkItems], false);
      HideActions([actSelectAll, actClearAll], false);
    end;

    SetGridHeaders(ENPlanWorkItemHeaders, sgENPlanWorkItem.ColumnHeaders);

    if DMReports.isCommentRequiredForPlanWorkItems(ENPlanWorkObj) then
      sgENPlanWorkItem.ColumnHeaders[3] := 'Місце роботи, номери опор, прогонів, ТП тощо'
    else
      sgENPlanWorkItem.ColumnHeaders[3] := 'Примітка';

    iColCount:=-1;
    TempENPlanWorkItem :=  HTTPRIOENPlanWorkItem as ENPlanWorkItemControllerSoapPort;

    if (ENPlanWorkObj.elementRef.code = CARGO_OBJECT) then
      sgENPlanWorkItem.ColumnHeaders[10] := 'опис вантажу';

    if planItemFilter = nil then
    begin
       planItemFilter := ENPlanWorkItemFilter.Create;
       SetNullIntProps(planItemFilter);
       SetNullXSProps(planItemFilter);
    end;

    if planItemFilter.planRef = nil then planItemFilter.planRef := ENPlanWorkRef.Create;
    planItemFilter.planRef.code := ENPlanWorkObj.code;

    // +++ кроме услуг со стороны
    planItemFilter.conditionSQL := 'TKTECHCARDSOURCE.CODE <> ' + IntToStr(TKTECHCARDSOURCE_SERVICES_FROM_SIDE);

    planItemFilter.orderBySQL := ' enplanworkitem.kartarefcode';

    ENPlanWorkItemList := TempENPlanWorkItem.getScrollableFilteredList(planItemFilter,0,-1);

    iLastCount:=High(ENPlanWorkItemList.list);

    if iLastCount > -1 then
       sgENPlanWorkItem.RowCount:=iLastCount+2
    else
       sgENPlanWorkItem.RowCount:=2;

     with sgENPlanWorkItem do
      for i:=0 to iLastCount do
        begin
          Application.ProcessMessages;
          if ENPlanWorkItemList.list[i].code <> Low(Integer) then
            Cells[0,i+1] := IntToStr(ENPlanWorkItemList.list[i].code)
          else
            Cells[0,i+1] := '';

          AddCheckBox(1, i+1, false, false);

          Cells[1,i+1] := ENPlanWorkItemList.list[i].kartaNum;
          Cells[2,i+1] := ENPlanWorkItemList.list[i].kartaRefName;
          ///// NET-4503
          //Cells[3, i+1] := ENPlanWorkItemList.list[i].sourceName;
          Cells[3,i+1] := ENPlanWorkItemList.list[i].commentGen;
          /////

          vCountGen := 0;

          if ENPlanWorkItemList.list[i].countGen = nil then
            Cells[4,i+1] := ''
          else begin
            Cells[4,i+1] := ENPlanWorkItemList.list[i].countGen.DecimalString;
            try
              vCountGen := StrToFloat(ENPlanWorkItemList.list[i].countGen.DecimalString);
            except
              vCountGen := 0;
            end;
          end;

          vNormOfTime := 0;

          if ENPlanWorkItemList.list[i].normOfTime = nil then
            Cells[5, i+1] := ''
          else begin
            Cells[5, i+1] := ENPlanWorkItemList.list[i].normOfTime.DecimalString;
            try
              vNormOfTime := StrToFloat(ENPlanWorkItemList.list[i].normOfTime.DecimalString);
            except
              vNormOfTime := 0;
            end;
          end;

          Cells[6,i+1] := FloatToStr(Conv(vNormOfTime * vCountGen, 3));

          if ENPlanWorkItemList.list[i].koef <> nil then
            Cells[7, i +1] := ENPlanWorkItemList.list[i].koef.DecimalString
          else
            Cells[7, i+1] := '';

          if ENPlanWorkItemList.list[i].timeGen <> nil then
            Cells[8, i +1] := ENPlanWorkItemList.list[i].timeGen.DecimalString
          else
            Cells[8, i+1] := '';

          Cells[9, i+1] := ENPlanWorkItemList.list[i].meter;

          if (ENPlanWorkObj.elementRef.code = CARGO_OBJECT) then
            Cells[10, i+1] := ENPlanWorkItemList.list[i].commentGen
          else
            Cells[10, i+1] := ENPlanWorkItemList.list[i].measurementUnit;

          //Cells[11,i+1] := ENPlanWorkItemList.list[i].userGen;
          Cells[11, i+1] := ENPlanWorkItemList.list[i].sourceName;

          {if ENPlanWorkItemList.list[i].dateEdit = nil then
            Cells[9,i+1] := ''
          else
            Cells[9,i+1] := XSDate2String(ENPlanWorkItemList.list[i].dateEdit);}

          //Cells[9,i+1] := FloatToStr(Conv(vNormOfTime * vCountGen, 3));

          /////
          try
            RowColor[i+1] := clWindow;

            // Если работа с нулевым кол-вом, выделяем строку красным цветом
            if ENPlanWorkItemList.list[i].countGen <> nil then
              if StrToFloat(ENPlanWorkItemList.list[i].countGen.DecimalString) = 0 then
                RowColor[i+1] := clTeal; //clRed;
          except
          end;
          /////

          iLastRow:=i+1;
          sgENPlanWorkItem.RowCount:=iLastRow+1;
        end;
     iColCount:=iColCount+1;
     sgENPlanWorkItem.Row:=1;
  end; // selected tsPlanWorkItems

  // --------------  tsServicesItem
  if pcPlanWork.ActivePage = tsServicesItem then
  begin
    DisableActions([actInsert,  actEdit, actDelete, actFilter, actNoFilter], not (DialogState in [dsEdit, dsInsert]) );

    HideActions([actCreateOrderByServicesPlan]);
    btnCreateOrderByPlanServices.Visible := False;

    // 02.05.2012 +++ для внеплановых месячных показываем кнопку "Скласти заявку"
    if ((ENPlanWorkObj.formRef.code = ENPLANWORKFORM_NOPLANNED)
         and (ENPlanWorkObj.kind.code = ENPLANWORKKIND_CURRENT) and (inServices)) then
    begin
      HideActions([actCreateOrderByServicesPlan], False);
      btnCreateOrderByPlanServices.Visible := True;
    end;

    SetGridHeaders(ServicesItemHeaders, sgServicesItem.ColumnHeaders);
    iColCount:=-1;
    TempENPlanWorkItem :=  HTTPRIOENPlanWorkItem as ENPlanWorkItemControllerSoapPort;

    if planServicesFilter = nil then
    begin
       planServicesFilter := ENPlanWorkItemFilter.Create;
       SetNullIntProps(planServicesFilter);
       SetNullXSProps(planServicesFilter);
    end;

    if planServicesFilter.planRef = nil then planServicesFilter.planRef := ENPlanWorkRef.Create;
    planServicesFilter.planRef.code := ENPlanWorkObj.code;

    // +++ только услуги со стороны
    planServicesFilter.conditionSQL := 'TKTECHCARDSOURCE.CODE = ' + IntToStr(TKTECHCARDSOURCE_SERVICES_FROM_SIDE);

    planServicesFilter.orderBySQL := ' enplanworkitem.kartarefcode';

    ENPlanWorkItemList := TempENPlanWorkItem.getScrollableFilteredList(planServicesFilter,0,-1);

    iLastCount:=High(ENPlanWorkItemList.list);

    if iLastCount > -1 then
       sgServicesItem.RowCount:=iLastCount+2
    else
       sgServicesItem.RowCount:=2;

     with sgServicesItem do
      for i:=0 to iLastCount do
        begin
          Application.ProcessMessages;
          if ENPlanWorkItemList.list[i].code <> Low(Integer) then
            Cells[0,i+1] := IntToStr(ENPlanWorkItemList.list[i].code)
          else
            Cells[0,i+1] := '';

          Cells[1,i+1] := ENPlanWorkItemList.list[i].kartaNum;
          Cells[2,i+1] := ENPlanWorkItemList.list[i].kartaRefName;
          Cells[3, i+1] := ENPlanWorkItemList.list[i].sourceName;

          vCountGen := 0;

          if ENPlanWorkItemList.list[i].countGen = nil then
            Cells[4,i+1] := ''
          else begin
            Cells[4,i+1] := ENPlanWorkItemList.list[i].countGen.DecimalString;
            try
              vCountGen := StrToFloat(ENPlanWorkItemList.list[i].countGen.DecimalString);
            except
              vCountGen := 0;
            end;
          end;

          //  вартість послуги
          if ENPlanWorkItemList.list[i].costServicesFS = nil then
            Cells[5,i+1] := ''
          else begin
            Cells[5,i+1] := ENPlanWorkItemList.list[i].costServicesFS.DecimalString;
          end;

          Cells[6, i+1] := ENPlanWorkItemList.list[i].meter;
          Cells[7, i+1] := ENPlanWorkItemList.list[i].commentGen;
          Cells[8, i+1] := ENPlanWorkItemList.list[i].statusName;

          /////
          try
            RowColor[i+1] := clWindow;
            
            // Если работа с нулевым кол-вом, выделяем строку красным цветом
            if ENPlanWorkItemList.list[i].countGen <> nil then
              if StrToFloat(ENPlanWorkItemList.list[i].countGen.DecimalString) = 0 then
                RowColor[i+1] := clTeal; //clRed;
          except
          end;
          /////

          iLastRow:=i+1;
          sgServicesItem.RowCount:=iLastRow+1;
        end;
     iColCount:=iColCount+1;
     sgServicesItem.Row:=1;
  end; // selected tsServicesItem

  //--------------------------------------------------------------------------------
  if (pcPlanWork.ActivePage = tsEstimateItems) and  (not isTransport) then
  begin

    //if (isTransport) then
    //  DisableActions([actInsert, actEdit, actDelete, actFilter, actNoFilter])
    //else
      DisableActions([actInsert, actEdit, actDelete, actFilter, actNoFilter], not (DialogState in [dsEdit, dsInsert]));

    HideActions([actChangeCountFact, actZeroEstimateItems], false);

    HideActions([actSelectAll, actClearAll], false);
    
    if (DialogState = dsEdit) and (isSiz) then DisableActions([actInsert]);


    HideActions([actCreateOrderByPlan]);
    btnCreateOrder.Visible := False;

    // 01.05.2012 +++ для внеплановых месячных показываем кнопку "Скласти заявку"
    if ((ENPlanWorkObj.formRef.code = ENPLANWORKFORM_NOPLANNED)
         and (ENPlanWorkObj.kind.code = ENPLANWORKKIND_CURRENT) and (not inServices)) then
    begin
      HideActions([actCreateOrderByPlan], False);
      btnCreateOrder.Visible := True;
    end;


    try
      actMaterialBindingToFIN.Enabled := (DialogState = dsEdit) and (not (isTransport)) and
                                         ((ENPlanWorkObj.kind.code = ENPLANWORKKIND_NPZ) or  (ENPlanWorkObj.kind.code = ENPLANWORKKIND_FACT))
                                         // and статус бы еще пробить ....
                                         ;

      actMaterialBindingToFIN.Visible := actMaterialBindingToFIN.Enabled;
    except
      actMaterialBindingToFIN.Enabled := false;
      actMaterialBindingToFIN.Visible := false;
    end;

    SetGridHeaders(ENEstimateItemHeaders, sgENEstimateItem.ColumnHeaders);
    SetGridHeaders(SCCounterHeaders, sgSCCounterMaterials.ColumnHeaders);

    eiColCount := -1;
    TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;

    //if estimateItemFilter = nil then
    //begin
       estimateItemFilter := ENEstimateItemFilter.Create;
       SetNullIntProps(estimateItemFilter);
       SetNullXSProps(estimateItemFilter);
    //end;

    if estimateItemFilter.planRef = nil then estimateItemFilter.planRef := ENPlanWorkRef.Create;
    estimateItemFilter.planRef.code := ENPlanWorkObj.code;

    //estimateItemFilter.conditionSQL := 'enestimateitem.kindrefcode in (1,2)';
    if estimateItemFilter.kindRef = nil then estimateItemFilter.kindRef := ENEstimateItemKindRef.Create;
    estimateItemFilter.kindRef.code := ENESTIMATEITEMKIND_MATERIALS;

    // обработаем вывод всех или не нулевых ..
    if not cbShowAll.Checked then
    begin
        estimateItemFilter.conditionSQL := ' ENESTIMATEITEM.COUNTFACT <> 0 ';
    end;

    ////
    estimateItemFilter.orderBySQL := 'KR.TECHKARTNUMBER, SM.NAME, ENESTIMATEITEMTYPE.CODE';
    ////

    ///
    pnlLegend.Visible := (ENPlanWorkObj.kind.code in [ENPLANWORKKIND_NPZ, ENPLANWORKKIND_FACT]);
    ///

    if sgENEstimateItem.Visible then
    begin
      ENEstimateItemList := TempENEstimateItem.getScrollableFilteredList(estimateItemFilter, 0, -1);

      eiLastCount := High(ENEstimateItemList.list);

      if eiLastCount > -1 then
        sgENEstimateItem.RowCount := eiLastCount + 2
      else
        sgENEstimateItem.RowCount := 2;

       with sgENEstimateItem do
         for i := 0 to eiLastCount do
         begin
           Application.ProcessMessages;

           if ENEstimateItemList.list[i].code <> Low(Integer) then
             Cells[0,i+1] := IntToStr(ENEstimateItemList.list[i].code)
           else
             Cells[0,i+1] := '';

           AddCheckBox(1, i+1, false, false);

           Cells[1,i+1] := ENEstimateItemList.list[i].materialRefName;

           if ENEstimateItemList.list[i].countGen = nil then
             Cells[2,i+1] := ''
           else
             Cells[2,i+1] := ENEstimateItemList.list[i].countGen.DecimalString;

           if ENEstimateItemList.list[i].countFact = nil then
             Cells[3,i+1] := ''
           else
             Cells[3,i+1] := ENEstimateItemList.list[i].countFact.DecimalString;

           Cells[4,i+1] := ENEstimateItemList.list[i].measureType;

           Cells[5,i+1] := ENEstimateItemList.list[i].kartaNum;
           Cells[6,i+1] := ENEstimateItemList.list[i].kartaRefName;

           Cells[7,i+1] := ENEstimateItemList.list[i].typeRefName;

           Cells[8,i+1] := ENEstimateItemList.list[i].statusRefName;

           Cells[9,i+1] := ENEstimateItemList.list[i].userGen;

           if ENEstimateItemList.list[i].dateEdit = nil then
             Cells[10,i+1] := ''
           else
             Cells[10,i+1] := XSDate2String(ENEstimateItemList.list[i].dateEdit);


           if (ENPlanWorkObj.kind.code in [ENPLANWORKKIND_NPZ, ENPLANWORKKIND_FACT]) then
           begin
             // Если к нормативному материалу привязаны материалы из ФК, выделяем строку цветом
             //if checkMaterialsBinding(ENEstimateItemList.list[i].code) then
             if ENEstimateItemList.list[i].countFINMaterials > 0 then
             begin
               RowColor[i+1] := Shape1.Brush.Color; //$0080FF80; //$EBFFF5
               Objects[1,i+1] := TObject(1); // признак (разнесенный)
             end
             else begin

             RowColor[i+1] := clWindow;
             Objects[1,i+1] := TObject(0);


             end;
             //else
             //  RowColor[i+1] := clYellow;
           end
           else begin
             RowColor[i+1] := clWindow;

             // Выделяем цветом ручной ввод
             if ENEstimateItemList.list[i].typeRefCode in [ENESTIMATEITEMTYPE_CORRECTED, ENESTIMATEITEMTYPE_MANUAL_BY_PLANITEM]  then
               RowColor[i+1] := clMoneyGreen; //$EBFFF5

             // Выделяем цветом строки, относящиеся ко всей смете
             if ENEstimateItemList.list[i].typeRefCode in [ENESTIMATEITEMTYPE_MANUAL_BY_PLAN]  then
               RowColor[i+1] := clYellow;

             try
             if ((ENPlanWorkObj.kind.code = ENPLANWORKKIND_YEAR) and
                 (ENPlanWorkObj.yearGen = CurrentYear+1) and
                (StrToFloat(ENEstimateItemList.list[i].countGen.DecimalString) <
                StrToFloat(ENEstimateItemList.list[i].countFact.DecimalString))) then
             RowColor[i+1] := clFuchsia;
             except
             end
           end;

           Objects[0,i+1] := TObject(ENEstimateItemList.list[i].typeRefCode);

           Objects[1,i+1] := TObject(ENEstimateItemList.list[i].statusRefCode);

           Objects[2,i+1] := TObject(ENEstimateItemList.list[i].accountingTypeRefCode);

           eiLastRow := i + 1;
           sgENEstimateItem.RowCount := eiLastRow + 1;
         end;

       //sgENEstimateItem.RowColor[1] := clGreen;

       eiColCount := eiColCount + 1;
       sgENEstimateItem.Row := 1;

       // выведем список ФИН материалов .... если они есть ВААЩЕ ...
       // и только для НПЗ и ФАКТА
       // а для ТЕКУЩЕГО могут быть уже приехавшие с заявки мат-лы
      //if ( ENPlanWorkObj.kind.code = ENPLANWORKKIND_NPZ) or ( ENPlanWorkObj.kind.code = ENPLANWORKKIND_FACT) then
      if ( ENPlanWorkObj.kind.code <> ENPLANWORKKIND_YEAR) then
      begin

          try
            j := StrToInt( sgENEstimateItem.Cells[0, sgENEstimateItem.Row ]); //   (sgENEstimateItem,0));
          except
            on EConvertError do
            begin
              //HideControls([gbFINMaterials, gbMarkers]);
              HideControls([pnlAdditional]);
              Panel1.Align := alClient;
              Exit;
            end;
          end;

          //sgENEstimateItem.Align := alClient;

          if ENPlanWorkObj.kind.code <> ENPLANWORKKIND_CURRENT then
          begin
            gbFINMaterials.Visible := true;
            updateFINMaterialsGrid(j, sgFINMaterials);
          end
          else begin
            gbFINMaterials.Visible := false;
            gbMarkers.Align := alBottom;
          end;

          gbMarkers.Visible := true;
          updateMarksGrid(j, sgMarkers);
      end
      else
      begin
         //HideControls([gbFINMaterials, gbMarkers]);
         HideControls([pnlAdditional]);
         Panel1.Align := alClient;
      end;

      sgENEstimateItemClick(sgENEstimateItem);

    end // if sgENEstimateItem.Visible
    else begin
      ////////////////// 04.02.13 NET-4061, п. 2
      UpdateENEstimateItemWithFinGrid(sgENEstimateItemWithFin, estimateItemFilter);
      //////////////////
    end;

  end;


  //--------------------------------------------------------------------------------
  if pcPlanWork.ActivePage = tsGSM then
  begin
    ///// 23.02.15 Нефиг ГСМ сгенеренный редактировать!!!
    //DisableActions([  actEdit], not (DialogState in [dsEdit, dsInsert]) );
    DisableActions([actInsert, actEdit, actDelete , actFilter, actNoFilter]);
    /////
    try
      actMaterialBindingToFIN.Enabled := (DialogState = dsEdit) and
                                         ((ENPlanWorkObj.kind.code = ENPLANWORKKIND_NPZ) or  (ENPlanWorkObj.kind.code = ENPLANWORKKIND_FACT))
                                         // and статус бы еще пробить ....
                                         ;

      actMaterialBindingToFIN.Visible := actMaterialBindingToFIN.Enabled;
    except
      actMaterialBindingToFIN.Enabled := false;
      actMaterialBindingToFIN.Visible := false;
    end;

    SetGridHeaders(ENEstimateItemHeaders, sgGSM.ColumnHeaders);
    eiColCount := -1;
    TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;

    //if estimateItemFilter = nil then
    //begin
       estimateItemFilter := ENEstimateItemFilter.Create;
       SetNullIntProps(estimateItemFilter);
       SetNullXSProps(estimateItemFilter);
    //end;

    if estimateItemFilter.planRef = nil then estimateItemFilter.planRef := ENPlanWorkRef.Create;
    estimateItemFilter.planRef.code := ENPlanWorkObj.code;

    //estimateItemFilter.conditionSQL := 'enestimateitem.kindrefcode in (1,2)';
    if estimateItemFilter.kindRef = nil then estimateItemFilter.kindRef := ENEstimateItemKindRef.Create;
    estimateItemFilter.kindRef.code := ENESTIMATEITEMKIND_GSM;

    // обработаем вывод всех или не нулевых ..
    if not cbShowAllGSM.Checked then
    begin
        estimateItemFilter.conditionSQL := ' ENESTIMATEITEM.COUNTFACT <> 0 ';
    end;

    ////
    estimateItemFilter.orderBySQL := 'KR.TECHKARTNUMBER, SM.NAME, ENESTIMATEITEMTYPE.CODE';
    ////

    ///
    pnlLegend.Visible := (ENPlanWorkObj.kind.code in [ENPLANWORKKIND_NPZ, ENPLANWORKKIND_FACT]);
    ///

    if sgGSM.Visible then
    begin
      ENEstimateItemList := TempENEstimateItem.getScrollableFilteredList(estimateItemFilter, 0, -1);

      eiLastCount := High(ENEstimateItemList.list);

      if eiLastCount > -1 then
        sgGSM.RowCount := eiLastCount + 2
      else
        sgGSM.RowCount := 2;

       with sgGSM do
         for i := 0 to eiLastCount do
         begin
           Application.ProcessMessages;

           if ENEstimateItemList.list[i].code <> Low(Integer) then
             Cells[0,i+1] := IntToStr(ENEstimateItemList.list[i].code)
           else
             Cells[0,i+1] := '';

           Cells[1,i+1] := ENEstimateItemList.list[i].materialRefName;

           if ENEstimateItemList.list[i].countGen = nil then
             Cells[2,i+1] := ''
           else
             Cells[2,i+1] := ENEstimateItemList.list[i].countGen.DecimalString;

           if ENEstimateItemList.list[i].countFact = nil then
             Cells[3,i+1] := ''
           else
             Cells[3,i+1] := ENEstimateItemList.list[i].countFact.DecimalString;

           Cells[4,i+1] := ENEstimateItemList.list[i].measureType;

           Cells[5,i+1] := ENEstimateItemList.list[i].kartaNum;
           Cells[6,i+1] := ENEstimateItemList.list[i].kartaRefName;

           Cells[7,i+1] := ENEstimateItemList.list[i].typeRefName;

           Cells[8,i+1] := ENEstimateItemList.list[i].statusRefName;

           Cells[9,i+1] := ENEstimateItemList.list[i].userGen;

           if ENEstimateItemList.list[i].dateEdit = nil then
             Cells[10,i+1] := ''
           else
             Cells[10,i+1] := XSDate2String(ENEstimateItemList.list[i].dateEdit);


           if (ENPlanWorkObj.kind.code in [ENPLANWORKKIND_NPZ, ENPLANWORKKIND_FACT]) then
           begin
             // Если к нормативному материалу привязаны материалы из ФК, выделяем строку цветом
             //if checkMaterialsBinding(ENEstimateItemList.list[i].code) then
             if ENEstimateItemList.list[i].countFINMaterials > 0 then
             begin
               RowColor[i+1] := Shape1.Brush.Color; //$0080FF80; //$EBFFF5
               Objects[1,i+1] := TObject(1); // признак (разнесенный)
             end
             else begin
               RowColor[i+1] := clWindow;
               Objects[1,i+1] := TObject(0);
             end;
             //else
             //  RowColor[i+1] := clYellow;
           end
           else begin
             RowColor[i+1] := clWindow;

             // Выделяем цветом ручной ввод
             if ENEstimateItemList.list[i].typeRefCode in [ENESTIMATEITEMTYPE_CORRECTED, ENESTIMATEITEMTYPE_MANUAL_BY_PLANITEM]  then
               RowColor[i+1] := clMoneyGreen; //$EBFFF5

             // Выделяем цветом строки, относящиеся ко всей смете
             if ENEstimateItemList.list[i].typeRefCode in [ENESTIMATEITEMTYPE_MANUAL_BY_PLAN]  then
               RowColor[i+1] := clYellow;
           end;

           Objects[0,i+1] := TObject(ENEstimateItemList.list[i].typeRefCode);

           eiLastRow := i + 1;
           sgGSM.RowCount := eiLastRow + 1;
         end;

       //sgENEstimateItem.RowColor[1] := clGreen;

       eiColCount := eiColCount + 1;
       sgGSM.Row := 1;

       // выведем список ФИН материалов .... если они есть ВААЩЕ ...
       // и только для НПЗ и ФАКТА

       if ( ENPlanWorkObj.kind.code = ENPLANWORKKIND_NPZ) or ( ENPlanWorkObj.kind.code = ENPLANWORKKIND_FACT) then
       begin

          try
            j := StrToInt( sgGSM.Cells[0, sgGSM.Row ]); //   (sgENEstimateItem,0));
          except
            on EConvertError do Exit;
          end;
          gbFINGSM.Visible := true;
          //sgENEstimateItem.Align := alClient;
          updateFINMaterialsGrid(j, sgFINGSM);
      end
      else
      begin
         gbFINGSM.Visible := false;
         pnlGSM.Align := alClient;
      end;

    end // if sgGSM.Visible
    else begin
      ////////////////// 04.02.13 NET-4061, п. 2
      UpdateENEstimateItemWithFinGrid(sgGSMWithFin, estimateItemFilter);
      //////////////////
    end;

  end;


// ----------------------------------------------------------
  if pcPlanWork.ActivePage = tsHumens then
  begin
  SetGridHeaders(ENHumenItemHeaders, sgENHumenItem.ColumnHeaders);
  DisableActions([actInsert,  actEdit, actDelete, actFilter, actNoFilter]);
  if humenItemFilter = nil then
  begin
    humenItemFilter := ENHumenItemFilter.Create;
    SetNullIntProps(humenItemFilter);
    SetNullXSProps(humenItemFilter);
  end;

  if HumenItemFilter.planRef = nil then HumenItemFilter.planRef := ENPlanWorkRef.Create;
  HumenItemFilter.planRef.code := ENPlanWorkObj.code;

  HumenItemFilter.conditionSQL := 'enhumenitem.planitemrefcode in ('+
                          ' select enplanworkitem.code from enplanworkitem where enplanworkitem.planrefcode ='+ IntToStr(ENPlanWorkObj.code) +
                          ' and enplanworkitem.countgen<>0'+
                          ')';

  HumenItemFilter.orderBySQL := 'enhumenitem.planitemrefcode';

  TempENHumenItem := HTTPRIOENHumenItem as ENHumenItemControllerSoapPort;

  ENHumenItemList := TempENHumenItem.getScrollableFilteredList(HumenItemFilter, 0, -1);

  //eiLastCount := High(ENHumenItemList.list);

  if High(ENHumenItemList.list) > -1 then
    sgENHumenItem.RowCount := High(ENHumenItemList.list) + 2
  else
    sgENHumenItem.RowCount := 2;

    {    ( 'Код'
          ,'Посада нормативна'
          ,'Посада штатна'
          ,'Робітник'
          ,'норма часу нормативна'
          ,'норма часу скорегована'
          ,'Ціна нормачасу'
          ,'Вартість нормочасу'
          ,'пользователь внесший изменение'
          ,'дата последнего изменения'
        );
        }
  with sgENHumenItem do
     for i := 0 to High(ENHumenItemList.list) do
     begin
        Application.ProcessMessages;
        if ENHumenItemList.list[i].code <> Low(Integer) then
        Cells[0,i+1] := IntToStr(ENHumenItemList.list[i].code)
        else
        Cells[0,i+1] := '';

        AddCheckBox(1,i+1,false, false);

        Cells[1, i+1] := ENHumenItemList.list[i].positionGenName + ' ' + ENHumenItemList.list[i].positionGenRank + ' розряду';

        Cells[2, i+1] := ENHumenItemList.list[i].finWorkerPositionName;
        Cells[3, i+1] := ENHumenItemList.list[i].finWorkerName;

//        Cells[2, i+1] := ENHumenItemList.list[i].manningTableName;
//        Cells[3, i+1] := ENHumenItemList.list[i].workerFactName;

        if ENHumenItemList.list[i].countGen = nil then
          Cells[4,i+1] := ''
        else
          Cells[4,i+1] := ENHumenItemList.list[i].countGen.DecimalString;

        if ENHumenItemList.list[i].countFact = nil then
          Cells[5,i+1] := ''
        else
          Cells[5,i+1] := ENHumenItemList.list[i].countFact.DecimalString;

        Cells[6, i+1] := ENHumenItemList.list[i].kartaNum;
        Cells[7,i + 1] := ENHumenItemList.list[i].kartaRefName;
        
          {
        if ENHumenItemList.list[i].price = nil then
          Cells[3,i+1] := ''
        else
          Cells[3,i+1] := ENHumenItemList.list[i].price.DecimalString;
        if ENHumenItemList.list[i].cost = nil then
          Cells[4,i+1] := ''
        else
          Cells[4,i+1] := ENHumenItemList.list[i].cost.DecimalString;
        Cells[5,i+1] := ENHumenItemList.list[i].userGen;
        if ENHumenItemList.list[i].dateEdit = nil then
          Cells[6,i+1] := ''
        else
          Cells[6,i+1] := XSDate2String(ENHumenItemList.list[i].dateEdit);
          }
        //LastRow:=i+1;
        //sgENHumenItem.RowCount:=LastRow+1;

  sgENHumenItem.Row := 1;
  end;
end;


// ----------------------------------------------------------
  if pcPlanWork.ActivePage = tsCCOutageNotice then
  begin
  SetGridHeaders(CCOutageNoticeHeaders, sgOutageNotice.ColumnHeaders);
  DisableActions([actInsert,  actEdit, actDelete, actFilter, actNoFilter]);


  TempCCOutageNotice := HTTPRIOCCOutageNotice as CCOutageNoticeControllerSoapPort;

  CCOutageNoticeList := TempCCOutageNotice.getCCOutageNoticeListByPlanCode(ENPlanWorkObj.code);

  if CCOutageNoticeList<>nil then
  begin
  if High(CCOutageNoticeList.list) > -1 then
    sgOutageNotice.RowCount := High(CCOutageNoticeList.list) + 2
  else
    sgOutageNotice.RowCount := 2;

  with sgOutageNotice do
     for i := 0 to High(CCOutageNoticeList.list) do
     begin
        Application.ProcessMessages;


       if CCOutageNoticeList.list[i].code <> Low(Integer) then
        Cells[0,i+1] := IntToStr(CCOutageNoticeList.list[i].code)
        else
        Cells[0,i+1] := '';
        if CCOutageNoticeList.list[i].dateBegin = nil then
          Cells[1,i+1] := ''
        else
          Cells[1,i+1] := XSDateTimeWithDate2String(CCOutageNoticeList.list[i].dateBegin);
        if CCOutageNoticeList.list[i].dateFinish = nil then
          Cells[2,i+1] := ''
        else
          Cells[2,i+1] := XSDateTimeWithDate2String(CCOutageNoticeList.list[i].dateFinish);
        if CCOutageNoticeList.list[i].isAgreed = Low(Integer) then
          Cells[3,i+1] := ''
        else
          Cells[3,i+1] := IntToStr(CCOutageNoticeList.list[i].isAgreed);
        if CCOutageNoticeList.list[i].dateGen = nil then
          Cells[4,i+1] := ''
        else
          Cells[4,i+1] := XSDateTimeWithDate2String(CCOutageNoticeList.list[i].dateGen);
        if CCOutageNoticeList.list[i].dateEdit = nil then
          Cells[5,i+1] := ''
        else
          Cells[5,i+1] := XSDateTimeWithDate2String(CCOutageNoticeList.list[i].dateEdit);
        Cells[6,i+1] := CCOutageNoticeList.list[i].accountnumber;
        Cells[7,i+1] := CCOutageNoticeList.list[i].customername;
        Cells[8,i+1] := CCOutageNoticeList.list[i].res;
        Cells[9,i+1] := CCOutageNoticeList.list[i].statusrefName;


       sgOutageNotice.Row := 1;
     end;
     end;

 end;

// -----------------------------------------------------------------------------------------------------




// -----------------------------------------------------------------------------------------------------

  if pcPlanWork.ActivePage = tsTransports then
  begin
     DisableActions([actInsert, actDelete, actFilter, actNoFilter]);

  SetGridHeaders(ENTransportItemHeaders, sgENTransportItem.ColumnHeaders);

  TempENTransportItem :=  HTTPRIOENTransportItem as ENTransportItemControllerSoapPort;

  transportItemFilter := ENTransportItemFilter.Create;
  SetNullIntProps(transportItemFilter);
  SetNullXSProps(transportItemFilter);

  transportItemFilter.planRef := ENPlanWorkRef.Create;
  transportItemFilter.planRef.code := ENPlanWorkObj.code;

  transportItemFilter.conditionSQL := 'entransportitem.planitemrefcode in ('+
                          'select enplanworkitem.code from enplanworkitem where enplanworkitem.planrefcode ='+ IntToStr(ENPlanWorkObj.code) +
                          ' and enplanworkitem.countgen<>0'+
                          ')';


   {
  transportItemFilter.conditionSQL := 'entransportitem.planitemrefcode in ('+ DMReports.getPlanItemCodes(ENPlanWorkObj.code) + ')';

  trArr :=  TempENTransportItem.getScrollableFilteredCodeArray(transportItemFilter,0,-1);
  sql := '';
  for i:=0 to High(trArr) do
     AddListPos(sql, IntToStr(trArr[i]));
  if sql = '' then Exit;

  transportItemFilter.conditionSQL := 'entransportitem.code in ('+ sql+')';
  transportItemFilter.orderBySQL := ' entransportitem.planitemrefcode';
  }
  ENTransportItemList := TempENTransportItem.getScrollableFilteredList(transportItemFilter,0,-1);

  if High(ENTransportItemList.list) > -1 then
    sgENTransportItem.RowCount := High(ENTransportItemList.list) + 2
  else
    sgENTransportItem.RowCount := 2;


  with sgENTransportItem do
     for i := 0 to High(ENTransportItemList.list) do
     begin
        Application.ProcessMessages;
        if ENTransportItemList.list[i].code <> Low(Integer) then
        Cells[0,i+1] := IntToStr(ENTransportItemList.list[i].code)
        else
        Cells[0,i+1] := '';

        AddCheckBox(1,i+1,false,false);

        Cells[1, i+1] := ENTransportItemList.list[i].transportName;
        Cells[2, i+1] := ENTransportItemList.list[i].transportRealName;

        //Cells[3, i+1] := ENTransportItemList.list[i].workerFactName;
        Cells[3, i+1] := ENTransportItemList.list[i].finWorkerName + ' ' + ENTransportItemList.list[i].finWorkerPositionName;

        if ENTransportItemList.list[i].countWorkGen = nil then
          Cells[4,i+1] := ''
        else
          Cells[4,i+1] := ENTransportItemList.list[i].countWorkGen.DecimalString;

        if ENTransportItemList.list[i].countWorkFact = nil then
          Cells[5,i+1] := ''
        else
          Cells[5,i+1] := ENTransportItemList.list[i].countWorkFact.DecimalString;

        Cells[6, i+1] := ENTransportItemList.list[i].kartaNum;
        Cells[7,i + 1] := ENTransportItemList.list[i].kartaRefName;

        Cells[8,i+1] := ENTransportItemList.list[i].tktransportTypeName;

        if ENTransportItemList.list[i].distance <> nil then
            Cells[9,i+1] :=  ENTransportItemList.list[i].distance.DecimalString
        else
            Cells[9,i+1] := '';


        Cells[10,i+1] := ENTransportItemList.list[i].transportDepartmentName;
        Cells[11,i+1] := ENTransportItemList.list[i].userGen;

        if ENTransportItemList.list[i].dateEdit = nil then
          Cells[12,i+1] := ''
        else
          Cells[12,i+1] := XSDate2String(ENTransportItemList.list[i].dateEdit);
        //LastRow:=i+1;
        //sgENHumenItem.RowCount:=LastRow+1;

                       // Выделяем цветом ручной ввод
       if ENTransportItemList.list[i].typeRefCode <> ENESTIMATEITEMTYPE_AUTO then
         sgENTransportItem.RowColor[i+1] := clMoneyGreen //$EBFFF5
       else
         sgENTransportItem.RowColor[i+1] := clWindow;

       Objects[0,i+1] := TObject(ENTransportItemList.list[i].typeRefCode);


  sgENTransportItem.Row := 1;
    end;

     SetGridHeaders(ENTransportItemForDistanceHeaders, sgTransportForDistance.ColumnHeaders);
     ENTransportItemList := TempENTransportItem.getListForDistances(ENPlanWorkObj.code);

      toCount := High(ENTransportItemList.list);

    if toCount > -1 then
      sgTransportForDistance.RowCount := toCount + 2
    else
      sgTransportForDistance.RowCount := 2;

     with sgTransportForDistance do
       for i := 0 to toCount do
       begin
         Application.ProcessMessages;

         if ENTransportItemList.list[i].code <> Low(Integer) then
           Cells[0,i+1] := IntToStr(ENTransportItemList.list[i].code)
         else
           Cells[0,i+1] := '';

         Cells[1,i+1] := ENTransportItemList.list[i].transportName;

         if ENTransportItemList.list[i].distanceNorm = nil then
          Cells[2,i+1] := ''
        else
          Cells[2,i+1] := ENTransportItemList.list[i].distanceNorm.DecimalString;

        if ENTransportItemList.list[i].amountOfJourneys <> Low(Integer) then
           Cells[3,i+1] := IntToStr(ENTransportItemList.list[i].amountOfJourneys)
         else
           Cells[3,i+1] := '';

         toLastRow := i + 1;
         sgTransportForDistance.RowCount := toLastRow + 1;
       end;

     //sgENEstimateItem.RowColor[1] := clGreen;

     toColCount := toColCount + 1;
     sgTransportForDistance.Row := 1;



  end;

  //--------------------------------------------------------------------------------

  if pcPlanWork.ActivePage = tsMoves then
  begin
  SetGridHeaders(ENPlanWorkMoveHistoryHeaders, sgENPlanWorkMoveHistory.ColumnHeaders);
  TempENPlanWorkMoveHistory :=  HTTPRIOENPlanWorkMoveHistory as ENPlanWorkMoveHistoryControllerSoapPort;

  if moveFilterObject = nil then
  begin
     moveFilterObject := ENPlanWorkMoveHistoryFilter.Create;
     SetNullIntProps(moveFilterObject);
     SetNullXSProps(moveFilterObject);
  end;

  moveFilterObject.planRef := ENPlanWorkRef.Create;
  moveFilterObject.planRef.code := ENPlanWorkObj.code;
  moveFilterObject.orderBySQL := ' dategen desc';

  ENPlanWorkMoveHistoryList := TempENPlanWorkMoveHistory.getScrollableFilteredList(moveFilterObject,0,-1);


  LastCountM:=High(ENPlanWorkMoveHistoryList.list);

  if LastCountM > -1 then
     sgENPlanWorkMoveHistory.RowCount:=LastCountM+2
  else
     sgENPlanWorkMoveHistory.RowCount:=2;

   with sgENPlanWorkMoveHistory do
    for i:=0 to LastCountM do
      begin
        Application.ProcessMessages;
        if ENPlanWorkMoveHistoryList.list[i].code <> Low(Integer) then
        Cells[0,i+1] := IntToStr(ENPlanWorkMoveHistoryList.list[i].code)
        else
        Cells[0,i+1] := '';
        if ENPlanWorkMoveHistoryList.list[i].dateGen = nil then
          Cells[1,i+1] := ''
        else
          Cells[1,i+1] := XSDate2String(ENPlanWorkMoveHistoryList.list[i].dateGen);
        if ENPlanWorkMoveHistoryList.list[i].yearGen = Low(Integer) then
          Cells[2,i+1] := ''
        else
          Cells[2,i+1] := IntToStr(ENPlanWorkMoveHistoryList.list[i].yearGen);
        if ENPlanWorkMoveHistoryList.list[i].monthGen = Low(Integer) then
          Cells[3,i+1] := ''
        else
          Cells[3,i+1] := IntToStr(ENPlanWorkMoveHistoryList.list[i].monthGen);

        Cells[4,i+1] := ENPlanWorkMoveHistoryList.list[i].reasonName;

        Cells[5,i+1] := ENPlanWorkMoveHistoryList.list[i].userGen;
        if ENPlanWorkMoveHistoryList.list[i].dateEdit = nil then
          Cells[6,i+1] := ''
        else
          Cells[6,i+1] := XSDate2String(ENPlanWorkMoveHistoryList.list[i].dateEdit);
        //LastRow:=i+1;
        sgENPlanWorkMoveHistory.RowCount:= i + 2; //LastRow+1;
      end;
   //ColCount:=ColCount+1;
   sgENPlanWorkMoveHistory.Row:=1;
  end; // pcPlanWork.ActivePage = tsMoves

  //--------------------------------------------------------------------------------

  if pcPlanWork.ActivePage = tsCorrections then
  begin
  SetGridHeaders(ENPlanCorrectHistoryHeaders, sgENPlanCorrectHistory.ColumnHeaders);

  TempENPlanCorrectHistory :=  HTTPRIOENPlanCorrectHistory as ENPlanCorrectHistoryControllerSoapPort;

  if corrFilterObject = nil then
  begin
     corrFilterObject := ENPlanCorrectHistoryFilter.Create;
     SetNullIntProps(corrFilterObject);
     SetNullXSProps(corrFilterObject);
  end;

  //corrFilterObject.planRef := ENPlanWorkRef.Create;
  //corrFilterObject.planRef.code := ENPlanWorkObj.code;

  if ENPlanWorkObj.kind.code = ENPLANWORKKIND_YEAR then
    corrFilterObject.conditionSQL := ' planrefcode = ' + IntToStr(ENPlanWorkObj.code)
  else
    corrFilterObject.conditionSQL := ' planrefcode in (select ph.planrefcode from enplancorrecthistory ph where ph.plannewrefcode = '+ IntToStr(ENPlanWorkObj.code) +')';

  ENPlanCorrectHistoryList := TempENPlanCorrectHistory.getScrollableFilteredList(corrFilterObject,0,-1);


  LastCountM:=High(ENPlanCorrectHistoryList.list);

  if LastCountM > -1 then
     sgENPlanCorrectHistory.RowCount:=LastCountM+2
  else
     sgENPlanCorrectHistory.RowCount:=2;

   with sgENPlanCorrectHistory do
    for i:=0 to LastCountM do
      begin
        Application.ProcessMessages;
        if ENPlanCorrectHistoryList.list[i].code <> Low(Integer) then
        Cells[0,i+1] := IntToStr(ENPlanCorrectHistoryList.list[i].code)
        else
        Cells[0,i+1] := '';
        if ENPlanCorrectHistoryList.list[i].dateGen = nil then
          Cells[1,i+1] := ''
        else
          Cells[1,i+1] := XSDate2String(ENPlanCorrectHistoryList.list[i].dateGen);

        Cells[2,i+1] := ENPlanCorrectHistoryList.list[i].reasonName;

        Cells[3,i+1] := ENPlanCorrectHistoryList.list[i].userGen;
        if ENPlanCorrectHistoryList.list[i].dateEdit = nil then
          Cells[4,i+1] := ''
        else
          Cells[4,i+1] := XSDate2String(ENPlanCorrectHistoryList.list[i].dateEdit);

        sgENPlanCorrectHistory.RowCount:=i+2;
      end;

   sgENPlanCorrectHistory.Row:=1;
   end;


  //--------------------------------------------------------------------------------
  if pcPlanWork.ActivePage = tsRefinement then
  begin
    DisableActions([ actInsert, actDelete , actEdit], not (DialogState in [dsEdit, dsInsert]) );
    DisableActions([ actFilter, actNoFilter]);


    SetGridHeaders(ENEstimateItemHeaders, sgRefinement.ColumnHeaders);
    SetGridHeaders(FINMaterialsShortHeaders, sgFINRefinement.ColumnHeaders);

    eiColCount := -1;
    TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;

    //if estimateItemFilter = nil then
    //begin
       estimateItemFilter := ENEstimateItemFilter.Create;
       SetNullIntProps(estimateItemFilter);
       SetNullXSProps(estimateItemFilter);
    //end;

    if estimateItemFilter.planRef = nil then estimateItemFilter.planRef := ENPlanWorkRef.Create;
    estimateItemFilter.planRef.code := ENPlanWorkObj.code;

    //estimateItemFilter.conditionSQL := 'enestimateitem.kindrefcode in (1,2)';
    if estimateItemFilter.kindRef = nil then estimateItemFilter.kindRef := ENEstimateItemKindRef.Create;
    estimateItemFilter.kindRef.code := ENESTIMATEITEMKIND_REFINEMENT;

    { Это же бред - последствия копипастинга!! (М-лы после доработки и чекбокс с ГСМа!!!)
    // обработаем вывод всех или не нулевых ..
    if not cbShowAllGSM.Checked then
    begin
        estimateItemFilter.conditionSQL := ' ENESTIMATEITEM.COUNTFACT <> 0 ';
    end;
    }

    ////
    estimateItemFilter.orderBySQL := 'KR.TECHKARTNUMBER, SM.NAME, ENESTIMATEITEMTYPE.CODE';
    ////

    ///
    pnlLegend.Visible := (ENPlanWorkObj.kind.code in [ENPLANWORKKIND_NPZ, ENPLANWORKKIND_FACT]);
    ///

    ENEstimateItemList := TempENEstimateItem.getScrollableFilteredList(estimateItemFilter, 0, -1);

    eiLastCount := High(ENEstimateItemList.list);

    if eiLastCount > -1 then
      sgRefinement.RowCount := eiLastCount + 2
    else
      sgRefinement.RowCount := 2;

     with sgRefinement do
       for i := 0 to eiLastCount do
       begin
         Application.ProcessMessages;

         if ENEstimateItemList.list[i].code <> Low(Integer) then
           Cells[0,i+1] := IntToStr(ENEstimateItemList.list[i].code)
         else
           Cells[0,i+1] := '';

         Cells[1,i+1] := ENEstimateItemList.list[i].materialRefName;

         if ENEstimateItemList.list[i].countGen = nil then
           Cells[2,i+1] := ''
         else
           Cells[2,i+1] := ENEstimateItemList.list[i].countGen.DecimalString;

         if ENEstimateItemList.list[i].countFact = nil then
           Cells[3,i+1] := ''
         else
           Cells[3,i+1] := ENEstimateItemList.list[i].countFact.DecimalString;

         Cells[4,i+1] := ENEstimateItemList.list[i].measureType;

         Cells[5,i+1] := ENEstimateItemList.list[i].kartaNum;
         Cells[6,i+1] := ENEstimateItemList.list[i].kartaRefName;

         Cells[7,i+1] := ENEstimateItemList.list[i].typeRefName;

         Cells[8,i+1] := ENEstimateItemList.list[i].userGen;

         if ENEstimateItemList.list[i].dateEdit = nil then
           Cells[9,i+1] := ''
         else
           Cells[9,i+1] := XSDate2String(ENEstimateItemList.list[i].dateEdit);


         if (ENPlanWorkObj.kind.code in [ENPLANWORKKIND_NPZ, ENPLANWORKKIND_FACT]) then
         begin
           // Если к нормативному материалу привязаны материалы из ФК, выделяем строку цветом
           //if checkMaterialsBinding(ENEstimateItemList.list[i].code) then
           if ENEstimateItemList.list[i].countFINMaterials > 0 then
           begin
             RowColor[i+1] := Shape1.Brush.Color; //$0080FF80; //$EBFFF5
             Objects[1,i+1] := TObject(1); // признак (разнесенный)
           end
           else begin
             RowColor[i+1] := clWindow;
             Objects[1,i+1] := TObject(0);
           end;
           //else
           //  RowColor[i+1] := clYellow;
         end
         else begin
           RowColor[i+1] := clWindow;

           // Выделяем цветом ручной ввод
           if ENEstimateItemList.list[i].typeRefCode in [ENESTIMATEITEMTYPE_CORRECTED, ENESTIMATEITEMTYPE_MANUAL_BY_PLANITEM]  then
             RowColor[i+1] := clMoneyGreen; //$EBFFF5

           // Выделяем цветом строки, относящиеся ко всей смете
           if ENEstimateItemList.list[i].typeRefCode in [ENESTIMATEITEMTYPE_MANUAL_BY_PLAN]  then
             RowColor[i+1] := clYellow;
         end;

         Objects[0,i+1] := TObject(ENEstimateItemList.list[i].typeRefCode);

         eiLastRow := i + 1;
         sgRefinement.RowCount := eiLastRow + 1;
       end;

     //sgENEstimateItem.RowColor[1] := clGreen;

     eiColCount := eiColCount + 1;
     sgRefinement.Row := 1;

     // выведем список ФИН материалов .... если они есть ВААЩЕ ...
     // и только для НПЗ и ФАКТА

     if ( ENPlanWorkObj.kind.code = ENPLANWORKKIND_NPZ) or ( ENPlanWorkObj.kind.code = ENPLANWORKKIND_FACT) then
     begin

        try
          j := StrToInt( sgRefinement.Cells[0, sgRefinement.Row ]); //   (sgENEstimateItem,0));
        except
          on EConvertError do Exit;
        end;
        gbFINRefinement.Visible := true;
        //sgENEstimateItem.Align := alClient;
        updateFINMaterialsGrid(j, sgFINRefinement);
    end
    else
    begin
       gbFINRefinement.Visible := false;
       //pnlGSM.Align := alClient;
       sgRefinement.Align := alClient;
    end;

  end;

  //end; //pcPlanWork.ActivePage = tsCorrections

  //--------------------------------------------------------------------------------
  if pcPlanWork.ActivePage = tsRQFKOrder then
  begin
    // только для месячных планов со статусом "Работы завершены"
    if ENPlanWorkObj.kind.code <> ENPLANWORKKIND_CURRENT then Exit;
    if ENPlanWorkObj.status.code <> ENPLANWORKSTATUS_WORKS_FINISHED then Exit;

    ClearGrid(sgRQFKOrder);

    TempRQFKOrder := HTTPRIORQFKOrder as RQFKOrderControllerSoapPort;

    RQFKOrderFilterObj := RQFKOrderFilter.Create;
    SetNullIntProps(RQFKOrderFilterObj);
    SetNullXSProps(RQFKOrderFilterObj);

    RQFKOrderFilterObj.conditionSQL := 'code in (' +
      'select p2o.fkorderrefcode from enplanwork2fkorder p2o ' +
      'where p2o.planrefcode = ' + IntToStr(ENPlanWorkObj.code) + ')';

    RQFKOrderFilterObj.orderBySQL := 'dategen desc, statuscode desc';

    RQFKOrderList := TempRQFKOrder.getScrollableFilteredListNoSegr(RQFKOrderFilterObj, 0, -1);

    if High(RQFKOrderList.list) > -1 then
       sgRQFKOrder.RowCount := High(RQFKOrderList.list) + 2
    else
       sgRQFKOrder.RowCount := 2;

     with sgRQFKOrder do
      for i := 0 to High(RQFKOrderList.list) do
        begin
          Application.ProcessMessages;
          if RQFKOrderList.list[i].code <> Low(Integer) then
          Cells[0,i+1] := IntToStr(RQFKOrderList.list[i].code)
          else
          Cells[0,i+1] := '';
          Cells[1,i+1] := RQFKOrderList.list[i].numberDoc;
          if RQFKOrderList.list[i].dateGen = nil then
            Cells[2,i+1] := ''
          else
            Cells[2,i+1] := XSDate2String(RQFKOrderList.list[i].dateGen);

          Cells[3,i+1] := RQFKOrderList.list[i].molInCode;

          Cells[4,i+1] := RQFKOrderList.list[i].molInName;

          Cells[5,i+1] := RQFKOrderList.list[i].molOutCode;
          Cells[6,i+1] := RQFKOrderList.list[i].molOutName;
          Cells[7, i+1] := RQFKOrderList.list[i].statusName;
          Cells[8,i+1] := RQFKOrderList.list[i].expeditorCode;
          Cells[9,i+1] := RQFKOrderList.list[i].expeditorName;
          Cells[10,i+1] := RQFKOrderList.list[i].warrantNumber;
          if RQFKOrderList.list[i].warrantDate = nil then
            Cells[11,i+1] := ''
          else
            Cells[11,i+1] := XSDate2String(RQFKOrderList.list[i].warrantDate);
          Cells[12,i+1] := RQFKOrderList.list[i].warrantFIO;

          Cells[13,i+1] := RQFKOrderList.list[i].userAdd;
          if RQFKOrderList.list[i].dateAdd = nil then
            Cells[14,i+1] := ''
          else
            Cells[14,i+1] := XSDateTimeWithDate2String(RQFKOrderList.list[i].dateAdd);

          Cells[15,i+1] := RQFKOrderList.list[i].userGen;
          if RQFKOrderList.list[i].dateEdit = nil then
            Cells[16,i+1] := ''
          else
            Cells[16,i+1] := XSDateTimeWithDate2String(RQFKOrderList.list[i].dateEdit);

          sgRQFKOrder.RowCount := i + 2;
        end;

     sgRQFKOrder.Row := 1;
  end;

  //--------------------------------------------------------------------------------
  if pcPlanWork.ActivePage = tsLinkedPlans then
  begin

    ClearGrid(sgLinkedPlans);
    SetGridHeaders(ENLinkedPlansHeaders, sgLinkedPlans.ColumnHeaders);

    TempENPlanWork := HTTPRIOENPlanWork as ENPlanWorkControllerSoapPort;

    linkedPlansFilterObj := ENPlanWorkFilter.Create;
    SetNullIntProps(linkedPlansFilterObj);
    SetNullXSProps(linkedPlansFilterObj);

    linkedPlansFilterObj.conditionSQL := 'code in (' +
      ' select plannewrefcode from enplancorrecthistory where planrefcode = '
          + IntToStr(ENPlanWorkObj.code) + // 1. Все дочерние. Сработает, если это - "корневой" план
      ' union ' +
      ' select ' + IntToStr(ENPlanWorkObj.code) + ' as planrefcode ' + // 2. Добавим себя
      ' union ' +
      ' select planrefcode from enplancorrecthistory where plannewrefcode = '
          + IntToStr(ENPlanWorkObj.code) + // 3. Присоедим "корневой", если это дочерний план
      ' union ' +
      ' select plannewrefcode from enplancorrecthistory where planrefcode in '+
      ' (select planrefcode from enplancorrecthistory where plannewrefcode = '
          + IntToStr(ENPlanWorkObj.code) + ')' + // 4. Все планы с таким же "корневым"
      ' )';

    intCodesList := TempENPlanWork.getFilteredCodeArray(linkedPlansFilterObj);
    for i:=0 to High(intCodesList) do
    begin
        if strCodesList <> '' then strCodesList := strCodesList + ', ';
        strCodesList := strCodesList + IntToStr(intCodesList[i]);
    end;

    if strCodesList <> '' then
    begin // если есть связанные планы
    linkedPlansFilterObj.conditionSQL := 'code in (' + strCodesList + ')';
    linkedPlansFilterObj.orderBySQL := ' kindcode, datestart, code';

    linkedPlansList := TempENPlanWork.getScrollableFilteredList(linkedPlansFilterObj, 0, -1);

    if High(linkedPlansList.list) > -1 then
       sgLinkedPlans.RowCount := High(linkedPlansList.list) + 2
    else
       sgLinkedPlans.RowCount := 2;

    with sgLinkedPlans do
      for i := 0 to High(linkedPlansList.list) do
        begin
          Application.ProcessMessages;
          if linkedPlansList.list[i].code <> Low(Integer) then
          Cells[0,i+1] := IntToStr(linkedPlansList.list[i].code)
          else
          Cells[0,i+1] := '';
          if linkedPlansList.list[i].yearGen <> Low(Integer) then
            Cells[1,i+1] := IntToStr(linkedPlansList.list[i].yearGen)
          else
            Cells[1,i+1] := '';
          if linkedPlansList.list[i].monthGen <> Low(Integer) then
            Cells[2,i+1] := MonthesNames[linkedPlansList.list[i].monthGen]
          else
            Cells[2,i+1] := '';
          if linkedPlansList.list[i].dateStart = nil then
            Cells[3,i+1] := ''
          else
            Cells[3,i+1] := XSDate2String(linkedPlansList.list[i].dateStart);
          Cells[4,i+1] := linkedPlansList.list[i].kindName;
          Cells[5,i+1] := linkedPlansList.list[i].statusName;
          Cells[6,i+1] := '';
          if linkedPlansList.list[i].yearOriginal > 0 then
          begin
          if linkedPlansList.list[i].monthOriginal > 0 then
            Cells[6,i+1] := MonthesNames[linkedPlansList.list[i].monthOriginal] + ' ' +
                            IntToStr(linkedPlansList.list[i].yearOriginal);
          end;
          Cells[7,i+1] := linkedPlansList.list[i].workOrderNumber;
          Cells[8,i+1] := linkedPlansList.list[i].userGen;
          if linkedPlansList.list[i].dateEdit = nil then
            Cells[9,i+1] := ''
          else
            Cells[9,i+1] := XSDate2String(linkedPlansList.list[i].dateEdit);
        end;
     sgLinkedPlans.Row := 1;
    end; // если были связанные планы
  end;

  // tsActsServicesFS ---------------------------------------------------------
  if pcPlanWork.ActivePage = tsActsServicesFS then
  begin

    ClearGrid(sgActsServicesFS);
    TempRQFKOrder := HTTPRIORQFKOrder as RQFKOrderControllerSoapPort;

    RQFKOrderFilterObj := RQFKOrderFilter.Create;
    SetNullIntProps(RQFKOrderFilterObj);
    SetNullXSProps(RQFKOrderFilterObj);

    RQFKOrderFilterObj.conditionSQL := 'code in (' +
      ' select pl.fkordercode from rqfkorder2planfact pl ' +
      ' where pl.plancode = ' + IntToStr(ENPlanWorkObj.code) + ')';

    RQFKOrderFilterObj.orderBySQL := 'dategen desc, statuscode desc';

    RQFKOrderList := TempRQFKOrder.getScrollableFilteredList(RQFKOrderFilterObj, 0, -1);

    if High(RQFKOrderList.list) > -1 then
       sgActsServicesFS.RowCount := High(RQFKOrderList.list) + 2
    else
       sgActsServicesFS.RowCount := 2;

     with sgActsServicesFS do
      for i := 0 to High(RQFKOrderList.list) do
        begin
          Application.ProcessMessages;
          if RQFKOrderList.list[i].code <> Low(Integer) then
          Cells[0,i+1] := IntToStr(RQFKOrderList.list[i].code)
          else
          Cells[0,i+1] := '';
          Cells[1,i+1] := RQFKOrderList.list[i].numberDoc;
          if RQFKOrderList.list[i].dateGen = nil then
            Cells[2,i+1] := ''
          else
            Cells[2,i+1] := XSDate2String(RQFKOrderList.list[i].dateGen);

          Cells[3,i+1] := RQFKOrderList.list[i].orgOkpo;
          Cells[4,i+1] := RQFKOrderList.list[i].orgName;
          Cells[5,i+1] := RQFKOrderList.list[i].sumWithoutNds.DecimalString;
          Cells[6,i+1] := RQFKOrderList.list[i].statusName;
          Cells[7,i+1] := RQFKOrderList.list[i].userAdd;

          if RQFKOrderList.list[i].dateAdd = nil then
            Cells[8,i+1] := ''
          else
            Cells[8,i+1] := XSDateTimeWithDate2String(RQFKOrderList.list[i].dateAdd);

          Cells[9,i+1] := RQFKOrderList.list[i].userGen;

          if RQFKOrderList.list[i].dateEdit = nil then
            Cells[10,i+1] := ''
          else
            Cells[10,i+1] := XSDateTimeWithDate2String(RQFKOrderList.list[i].dateEdit);

          sgActsServicesFS.RowCount := i + 2;
        end;

     sgActsServicesFS.Row := 1;
  end;
  // end tsActsServicesFS -----------------------------------------------------

   if pcPlanWork.ActivePage = tsObjectDetail then
  begin

    ClearGrid(sgObjectDetail);
    TempRouteBytDetail := HTTPRIOENRouteBytDetail as ENRouteBytDetailControllerSoapPort;
    TempRouteBytDetailFilter:=ENRouteBytDetailFilter.Create;

    SetNullIntProps(TempRouteBytDetailFilter);
    SetNullXSProps(TempRouteBytDetailFilter);

//    TempRouteBytDetailFilter.planRef:=ENPlanWorkRef.Create;
//    TempRouteBytDetailFilter.planRef.code:=ENPlanWorkObj.code;
    if  ENPlanWorkObj.kind.code=3
    then
    TempRouteBytDetailFilter.conditionSQL:='enroutebytdetail.planrefcode='+IntToStr(ENPlanWorkObj.code)
    else
    if ENPlanWorkObj.kind.code=4
    then
    TempRouteBytDetailFilter.conditionSQL:='enroutebytdetail.planrefcode='+
    ' (select enplancorrecthistory.planoldrefcode from enplancorrecthistory '+
    ' where enplancorrecthistory.plannewrefcode='+IntToStr(ENPlanWorkObj.code)+
    ' and enplancorrecthistory.reasoncode=5) '
    else  TempRouteBytDetailFilter.conditionSQL:='enroutebytdetail.planrefcode=-1';

    TempRouteBytDetailFilter.orderBySQL:='enroutebytdetail.statuscode desc';

    TempRouteBytDetailList := TempRouteBytDetail.getScrollableFilteredList(TempRouteBytDetailFilter, 0, -1);

    if High(TempRouteBytDetailList.list) > -1 then
       sgObjectDetail.RowCount := High(TempRouteBytDetailList.list) + 2
    else
       sgObjectDetail.RowCount := 2;

     countAll:=0;
     countVip:=0;

     with sgObjectDetail do
      for i := 0 to High(TempRouteBytDetailList.list) do
        begin
          Application.ProcessMessages;
          if TempRouteBytDetailList.list[i].code <> Low(Integer) then
          Cells[0,i+1] := IntToStr(TempRouteBytDetailList.list[i].code)
          else
          Cells[0,i+1] := '';

          Cells[1,i+1] := TempRouteBytDetailList.list[i].numbergen;
          Cells[2,i+1] := TempRouteBytDetailList.list[i].name;
          Cells[3,i+1] := TempRouteBytDetailList.list[i].rpName;
          Cells[4,i+1] := IntToStr(TempRouteBytDetailList.list[i].entryCode);
          countAll:=countAll+1;
          if (TempRouteBytDetailList.list[i].statusCode=1)
          then begin
          Cells[5,i+1] := 'Выполнено';
          countVip:=countVip+1;
          end
          else Cells[5,i+1] := 'Не выполнено';
          sgObjectDetail.RowCount := i + 2;
        end;

     sgObjectDetail.Row := 1;
     edtComplTY.Text:=IntToStr(countVip);
     edtAllTY.Text:=IntToStr(countAll);
     
  end;
  // end tsObjectDetail -----------------------------------------------------


  // start tsTransportRoute -----------------------------------------------------
  if pcPlanWork.ActivePage = tsTransportRoute then
  begin
    ClearGrid(sgENTransportRoute);
    TempENTransportRoute := HTTPRIOENTransportRoute as ENTransportRouteControllerSoapPort;
    SetGridHeaders(ENTransportRouteHeaders, sgENTransportRoute.ColumnHeaders);

    transportRouteFilter := ENTransportRouteFilter.Create;
    SetNullIntProps(transportRouteFilter);
    SetNullXSProps(transportRouteFilter);

    transportRouteFilter.planRef := ENPlanWorkRef.Create;
    transportRouteFilter.planRef.code := ENPlanWorkObj.code;
    transportRouteList := TempENTransportRoute.getScrollableFilteredList(transportRouteFilter, 0, -1);

    if High(transportRouteList.list) > -1 then
       sgENTransportRoute.RowCount := High(transportRouteList.list) + 2
    else
       sgENTransportRoute.RowCount := 2;

     with sgENTransportRoute do
      for i := 0 to High(transportRouteList.list) do
        begin
          Application.ProcessMessages;

          if transportRouteList.list[i].code <> Low(Integer) then
          Cells[0,i+1] := IntToStr(transportRouteList.list[i].code)
          else
          Cells[0,i+1] := '';

          if transportRouteList.list[i].elementInRefCode <> Low(Integer) then
          Cells[1,i+1] := IntToStr(transportRouteList.list[i].elementInRefCode)
          else
          Cells[1,i+1] := '';

          Cells[2,i+1] := transportRouteList.list[i].destinationPointStart;

          if transportRouteList.list[i].elementOutRefCode <> Low(Integer) then
          Cells[3,i+1] := IntToStr(transportRouteList.list[i].elementOutRefCode)
          else
          Cells[3,i+1] := '';

          Cells[4,i+1] := transportRouteList.list[i].destinationPointEnd;


          if transportRouteList.list[i].distance = nil then
            Cells[5,i+1] := ''
          else
            Cells[5,i+1] := transportRouteList.list[i].distance.DecimalString;

          if transportRouteList.list[i].weight = nil then
            Cells[6,i+1] := ''
          else
            Cells[6,i+1] := transportRouteList.list[i].weight.DecimalString;

          if transportRouteList.list[i].dateEdit = nil then
            Cells[7,i+1] := ''
          else
            Cells[7,i+1] := XSDateTimeWithDate2String(transportRouteList.list[i].dateEdit);

          Cells[8,i+1] := transportRouteList.list[i].userGen;

          sgENTransportRoute.RowCount := i + 2;
        end;

     sgENTransportRoute.Row := 1;

     sgENTransportRouteClick(Sender);
  end;
  // end tsTransportRoute -----------------------------------------------------


  // start tsEstimateItems ----------------------------------------------------
  if (pcPlanWork.ActivePage = tsEstimateItems) and  (isTransport) then
  begin

    if (isTransport) then
      DisableActions([actInsert, actEdit, actDelete, actFilter, actNoFilter])
    else
      DisableActions([actInsert, actEdit, actDelete, actFilter, actNoFilter], not (DialogState in [dsEdit, dsInsert]));

    HideActions([actChangeCountFact, actZeroEstimateItems], false);

    HideActions([actSelectAll, actClearAll], false);
    
    if (DialogState = dsEdit) and (isSiz) then DisableActions([actInsert]);

    try
      actMaterialBindingToFIN.Enabled := (DialogState = dsEdit) and (not (isTransport)) and
                                         ((ENPlanWorkObj.kind.code = ENPLANWORKKIND_NPZ) or  (ENPlanWorkObj.kind.code = ENPLANWORKKIND_FACT))
                                         // and статус бы еще пробить ....
                                         ;

      actMaterialBindingToFIN.Visible := actMaterialBindingToFIN.Enabled;
    except
      actMaterialBindingToFIN.Enabled := false;
      actMaterialBindingToFIN.Visible := false;
    end;

    SetGridHeaders(ENEstimateItemHeaders, sgENEstimateItem.ColumnHeaders);
    SetGridHeaders(SCCounterHeaders, sgSCCounterMaterials.ColumnHeaders);

    eiColCount := -1;
    TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;

    //if estimateItemFilter = nil then
    //begin
       estimateItemFilter := ENEstimateItemFilter.Create;
       SetNullIntProps(estimateItemFilter);
       SetNullXSProps(estimateItemFilter);
    //end;

    if estimateItemFilter.planRef = nil then estimateItemFilter.planRef := ENPlanWorkRef.Create;
    estimateItemFilter.planRef.code := ENPlanWorkObj.code;

    //estimateItemFilter.conditionSQL := 'enestimateitem.kindrefcode in (1,2)';
    if estimateItemFilter.kindRef = nil then estimateItemFilter.kindRef := ENEstimateItemKindRef.Create;
    estimateItemFilter.kindRef.code := ENESTIMATEITEMKIND_MATERIALS;

    // обработаем вывод всех или не нулевых ..
    if not cbShowAll.Checked then
    begin
        estimateItemFilter.conditionSQL := ' ENESTIMATEITEM.COUNTFACT <> 0 ';
    end;

    ////
    // estimateItemFilter.orderBySQL := 'KR.TECHKARTNUMBER, SM.NAME, ENESTIMATEITEMTYPE.CODE';
    estimateItemFilter.orderBySQL := '1';
    ////

    ///
    pnlLegend.Visible := (ENPlanWorkObj.kind.code in [ENPLANWORKKIND_NPZ, ENPLANWORKKIND_FACT]);
    ///

    ENEstimateItemList := TempENEstimateItem.getEstimateByTransportRouteList(estimateItemFilter, '', estimateItemFilter.orderBySQL, 0, -1);

    eiLastCount := High(ENEstimateItemList.list);

    if eiLastCount > -1 then
      sgENEstimateItem.RowCount := eiLastCount + 2
    else
      sgENEstimateItem.RowCount := 2;

     with sgENEstimateItem do
       for i := 0 to eiLastCount do
       begin
         Application.ProcessMessages;

         if ENEstimateItemList.list[i].code <> Low(Integer) then
           Cells[0,i+1] := IntToStr(ENEstimateItemList.list[i].code)
         else
           Cells[0,i+1] := '';

         AddCheckBox(1, i+1, false, false);

         Cells[1,i+1] := ENEstimateItemList.list[i].materialRefName;

         if ENEstimateItemList.list[i].countGen = nil then
           Cells[2,i+1] := ''
         else
           Cells[2,i+1] := ENEstimateItemList.list[i].countGen.DecimalString;

         if ENEstimateItemList.list[i].countFact = nil then
           Cells[3,i+1] := ''
         else
           Cells[3,i+1] := ENEstimateItemList.list[i].countFact.DecimalString;

         Cells[4,i+1] := ENEstimateItemList.list[i].measureType;

         Cells[5,i+1] := ENEstimateItemList.list[i].kartaNum;
         Cells[6,i+1] := ENEstimateItemList.list[i].kartaRefName;

         Cells[7,i+1] := ENEstimateItemList.list[i].typeRefName;

         Cells[8,i+1] := ENEstimateItemList.list[i].statusRefName;

         Cells[9,i+1] := ENEstimateItemList.list[i].userGen;

         if ENEstimateItemList.list[i].dateEdit = nil then
           Cells[10,i+1] := ''
         else
           Cells[10,i+1] := XSDate2String(ENEstimateItemList.list[i].dateEdit);


         if (ENPlanWorkObj.kind.code in [ENPLANWORKKIND_NPZ, ENPLANWORKKIND_FACT]) then
         begin
           // Если к нормативному материалу привязаны материалы из ФК, выделяем строку цветом
           //if checkMaterialsBinding(ENEstimateItemList.list[i].code) then
           if ENEstimateItemList.list[i].countFINMaterials > 0 then
           begin
             RowColor[i+1] := Shape1.Brush.Color; //$0080FF80; //$EBFFF5
             Objects[1,i+1] := TObject(1); // признак (разнесенный)
           end
           else begin
             RowColor[i+1] := clWindow;
             Objects[1,i+1] := TObject(0);
           end;
           //else
           //  RowColor[i+1] := clYellow;
         end
         else begin
           RowColor[i+1] := clWindow;

           // Выделяем цветом ручной ввод
           if ENEstimateItemList.list[i].typeRefCode in [ENESTIMATEITEMTYPE_CORRECTED, ENESTIMATEITEMTYPE_MANUAL_BY_PLANITEM]  then
             RowColor[i+1] := clMoneyGreen; //$EBFFF5

           // Выделяем цветом строки, относящиеся ко всей смете
           if ENEstimateItemList.list[i].typeRefCode in [ENESTIMATEITEMTYPE_MANUAL_BY_PLAN]  then
             RowColor[i+1] := clYellow;
         end;

         Objects[0,i+1] := TObject(ENEstimateItemList.list[i].typeRefCode);

         Objects[1,i+1] := TObject(ENEstimateItemList.list[i].statusRefCode);

         Objects[2,i+1] := TObject(ENEstimateItemList.list[i].accountingTypeRefCode);

         eiLastRow := i + 1;
         sgENEstimateItem.RowCount := eiLastRow + 1;
       end;

     //sgENEstimateItem.RowColor[1] := clGreen;

     eiColCount := eiColCount + 1;
     sgENEstimateItem.Row := 1;

     // выведем список ФИН материалов .... если они есть ВААЩЕ ...
     // и только для НПЗ и ФАКТА
     // а для ТЕКУЩЕГО могут быть уже приехавшие с заявки мат-лы
    //if ( ENPlanWorkObj.kind.code = ENPLANWORKKIND_NPZ) or ( ENPlanWorkObj.kind.code = ENPLANWORKKIND_FACT) then
    if ( ENPlanWorkObj.kind.code <> ENPLANWORKKIND_YEAR) then
    begin

        try
          j := StrToInt( sgENEstimateItem.Cells[0, sgENEstimateItem.Row ]); //   (sgENEstimateItem,0));
        except
          on EConvertError do
          begin
            //HideControls([gbFINMaterials, gbMarkers]);
            HideControls([pnlAdditional]);
            Panel1.Align := alClient;
            Exit;
          end;
        end;

        //sgENEstimateItem.Align := alClient;

        if ENPlanWorkObj.kind.code <> ENPLANWORKKIND_CURRENT then
        begin
          gbFINMaterials.Visible := true;
          updateFINMaterialsGrid(j, sgFINMaterials);
        end
        else begin
          gbFINMaterials.Visible := false;
          gbMarkers.Align := alBottom;
        end;

        gbMarkers.Visible := true;
        updateMarksGrid(j, sgMarkers);
    end
    else
    begin
       //HideControls([gbFINMaterials, gbMarkers]);
       HideControls([pnlAdditional]);
       Panel1.Align := alClient;
    end;

    sgENEstimateItemClick(sgENEstimateItem);
  end;
  // end tsEstimateItems ------------------------------------------------------

  // -- start CustomerMaterials
  if (pcPlanWork.ActivePage = tsCustomerMaterials) then
  begin
    DisableActions([actInsert, actEdit, actDelete, actFilter, actNoFilter], not (DialogState in [dsEdit, dsInsert]));

    HideActions([actChangeCountFact, actZeroEstimateItems], false);

    HideActions([actSelectAll, actClearAll], false);

    SetGridHeaders(FINMaterialsShortHeaders, sgCustomerMaterialsFin.ColumnHeaders);

    try
      actMaterialBindingToFIN.Enabled := (DialogState = dsEdit) and
                                         ((ENPlanWorkObj.kind.code = ENPLANWORKKIND_NPZ) or
                                          (ENPlanWorkObj.kind.code = ENPLANWORKKIND_FACT));

      actMaterialBindingToFIN.Visible := actMaterialBindingToFIN.Enabled;
    except
      actMaterialBindingToFIN.Enabled := false;
      actMaterialBindingToFIN.Visible := false;
    end;

    SetGridHeaders(ENEstimateItemHeaders, sgCustomerMaterials.ColumnHeaders);

    eiColCount := -1;
    TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;

    //if estimateItemFilter = nil then
    //begin
       estimateItemFilter := ENEstimateItemFilter.Create;
       SetNullIntProps(estimateItemFilter);
       SetNullXSProps(estimateItemFilter);
    //end;

    if estimateItemFilter.planRef = nil then estimateItemFilter.planRef := ENPlanWorkRef.Create;
    estimateItemFilter.planRef.code := ENPlanWorkObj.code;

    if estimateItemFilter.kindRef = nil then estimateItemFilter.kindRef := ENEstimateItemKindRef.Create;
    estimateItemFilter.kindRef.code := ENESTIMATEITEMKIND_CUSTOMER_MATERIALS;

    // обработаем вывод всех или не нулевых ..
    if not chbShowAllCustomerMaterials.Checked then
    begin
        estimateItemFilter.conditionSQL := ' ENESTIMATEITEM.COUNTFACT <> 0 ';
    end;

    ////
    estimateItemFilter.orderBySQL := 'KR.TECHKARTNUMBER, SM.NAME, ENESTIMATEITEMTYPE.CODE';
    ////

    ///
    pnlLegendCustomerMaterials.Visible := (ENPlanWorkObj.kind.code in [ENPLANWORKKIND_NPZ, ENPLANWORKKIND_FACT]);
    ///

    ENEstimateItemList := TempENEstimateItem.getScrollableFilteredList(estimateItemFilter, 0, -1);

    eiLastCount := High(ENEstimateItemList.list);

    if eiLastCount > -1 then
      sgCustomerMaterials.RowCount := eiLastCount + 2
    else
      sgCustomerMaterials.RowCount := 2;

     with sgCustomerMaterials do
       for i := 0 to eiLastCount do
       begin
         Application.ProcessMessages;

         if ENEstimateItemList.list[i].code <> Low(Integer) then
           Cells[0,i+1] := IntToStr(ENEstimateItemList.list[i].code)
         else
           Cells[0,i+1] := '';

         AddCheckBox(1, i+1, false, false);

         Cells[1,i+1] := ENEstimateItemList.list[i].materialRefName;

         if ENEstimateItemList.list[i].countGen = nil then
           Cells[2,i+1] := ''
         else
           Cells[2,i+1] := ENEstimateItemList.list[i].countGen.DecimalString;

         if ENEstimateItemList.list[i].countFact = nil then
           Cells[3,i+1] := ''
         else
           Cells[3,i+1] := ENEstimateItemList.list[i].countFact.DecimalString;

         Cells[4,i+1] := ENEstimateItemList.list[i].measureType;

         Cells[5,i+1] := ENEstimateItemList.list[i].kartaNum;
         Cells[6,i+1] := ENEstimateItemList.list[i].kartaRefName;

         Cells[7,i+1] := ENEstimateItemList.list[i].typeRefName;

         Cells[8,i+1] := ENEstimateItemList.list[i].statusRefName;

         Cells[9,i+1] := ENEstimateItemList.list[i].userGen;

         if ENEstimateItemList.list[i].dateEdit = nil then
           Cells[10,i+1] := ''
         else
           Cells[10,i+1] := XSDate2String(ENEstimateItemList.list[i].dateEdit);


         if (ENPlanWorkObj.kind.code in [ENPLANWORKKIND_NPZ, ENPLANWORKKIND_FACT]) then
         begin
           // Если к нормативному материалу привязаны материалы из ФК, выделяем строку цветом
           //if checkMaterialsBinding(ENEstimateItemList.list[i].code) then
           if ENEstimateItemList.list[i].countFINMaterials > 0 then
           begin
             RowColor[i+1] := Shape1.Brush.Color; //$0080FF80; //$EBFFF5
             Objects[1,i+1] := TObject(1); // признак (разнесенный)
           end
           else begin
             RowColor[i+1] := clWindow;
             Objects[1,i+1] := TObject(0);
           end;
         end
         else begin
           RowColor[i+1] := clWindow;

           // Выделяем цветом ручной ввод
           if ENEstimateItemList.list[i].typeRefCode in [ENESTIMATEITEMTYPE_CORRECTED, ENESTIMATEITEMTYPE_MANUAL_BY_PLANITEM]  then
             RowColor[i+1] := clMoneyGreen; //$EBFFF5

           // Выделяем цветом строки, относящиеся ко всей смете
           if ENEstimateItemList.list[i].typeRefCode in [ENESTIMATEITEMTYPE_MANUAL_BY_PLAN]  then
             RowColor[i+1] := clYellow;
         end;

         Objects[0,i+1] := TObject(ENEstimateItemList.list[i].typeRefCode);

         Objects[1,i+1] := TObject(ENEstimateItemList.list[i].statusRefCode);

         Objects[2,i+1] := TObject(ENEstimateItemList.list[i].accountingTypeRefCode);

         eiLastRow := i + 1;
         sgCustomerMaterials.RowCount := eiLastRow + 1;
       end;

     //sgENEstimateItem.RowColor[1] := clGreen;

     eiColCount := eiColCount + 1;
     sgCustomerMaterials.Row := 1;

     //sgENEstimateItemClick(sgCustomerMaterials);

     // выведем список ФИН материалов .... если они есть ВААЩЕ ...
     // и только для НПЗ и ФАКТА
     // а для ТЕКУЩЕГО могут быть уже приехавшие с заявки мат-лы
    //if ( ENPlanWorkObj.kind.code = ENPLANWORKKIND_NPZ) or ( ENPlanWorkObj.kind.code = ENPLANWORKKIND_FACT) then
    if ( ENPlanWorkObj.kind.code <> ENPLANWORKKIND_YEAR) then
    begin

        try
          j := StrToInt( sgCustomerMaterials.Cells[0, sgCustomerMaterials.Row ]); //   (sgENEstimateItem,0));
        except
          on EConvertError do
          begin
            //*** HideControls([gbCustomerMaterialsFin]);
            //*** pnlCustomerMaterials.Align := alClient;
            ClearGrid(sgCustomerMaterialsFin);
            Exit;
          end;
        end;

        //sgENEstimateItem.Align := alClient;

        //if (ENPlanWorkObj.kind.code <> ENPLANWORKKIND_CURRENT) then
        if (ENPlanWorkObj.kind.code in [ENPLANWORKKIND_CURRENT, ENPLANWORKKIND_NPZ, ENPLANWORKKIND_FACT]) then
        begin
          gbCustomerMaterialsFin.Visible := true;
          updateFINMaterialsGrid(j, sgCustomerMaterialsFin);
        end
        else begin
          gbCustomerMaterialsFin.Visible := false;
          //gbMarkers.Align := alBottom;
        end;

        {*******************************
        try
          j := StrToInt( sgRefinement.Cells[0, sgRefinement.Row ]); //   (sgENEstimateItem,0));
        except
          on EConvertError do Exit;
        end;
        gbFINRefinement.Visible := true;
        //sgENEstimateItem.Align := alClient;
        updateFINMaterialsGrid(j, sgFINRefinement);
        ********************************}
    end
    else
    begin
       //HideControls([gbFINMaterials, gbMarkers]);
       HideControls([gbCustomerMaterialsFin]);
       pnlCustomerMaterials.Align := alClient;
    end;

   // sgENEstimateItemClick(sgENEstimateItem);
  end;
  // --- end CustomerMaterials


  // start tsFINExecutor2Plan -----------------------------------------------------
  if pcPlanWork.ActivePage = tsFINExecutor2Plan then
  begin
    ClearGrid(sgFINExecutor2Plan);

    // Открываем возможность ввода на закрытых планах
    if (DialogState = dsView) then
      DisableActions([actInsert, actEdit, actDelete], false);

    TempFINExecutor2Plan := HTTPRIOFINExecutor2Plan as FINExecutor2PlanControllerSoapPort;

    executor2PlanFilter := FINExecutor2PlanFilter.Create;
    SetNullIntProps(executor2PlanFilter);
    SetNullXSProps(executor2PlanFilter);

    executor2PlanFilter.planRef := ENPlanWorkRef.Create;
    executor2PlanFilter.planRef.code := ENPlanWorkObj.code;
    FINExecutor2PlanList := TempFINExecutor2Plan.getScrollableFilteredList(executor2PlanFilter, 0, -1);

    if High(FINExecutor2PlanList.list) > -1 then
      sgFINExecutor2Plan.RowCount := High(FINExecutor2PlanList.list) + 2
    else
      sgFINExecutor2Plan.RowCount := 2;

     with sgFINExecutor2Plan do
      for i := 0 to High(FINExecutor2PlanList.list) do
        begin
          Application.ProcessMessages;

          if FINExecutor2PlanList.list[i].code <> Low(Integer) then
            Cells[0,i+1] := IntToStr(FINExecutor2PlanList.list[i].code)
          else
            Cells[0,i+1] := '';

          Cells[1,i+1] := FINExecutor2PlanList.list[i].finExecutorName;
          Cells[2,i+1] := FINExecutor2PlanList.list[i].finExecutorTypeRefName;

          if FINExecutor2PlanList.list[i].percentLoad = nil then
            Cells[3,i+1] := ''
          else
            Cells[3,i+1] := FINExecutor2PlanList.list[i].percentLoad.DecimalString;

          if FINExecutor2PlanList.list[i].dateStart = nil then
            Cells[4,i+1] := ''
          else
            Cells[4,i+1] := XSDate2String(FINExecutor2PlanList.list[i].dateStart);

          if FINExecutor2PlanList.list[i].dateFinal = nil then
            Cells[5,i+1] := ''
          else
            Cells[5,i+1] := XSDate2String(FINExecutor2PlanList.list[i].dateFinal);

          /////
          if FINExecutor2PlanList.list[i].totalTimeHours = nil then
            Cells[6,i+1] := ''
          else
            Cells[6,i+1] := FINExecutor2PlanList.list[i].totalTimeHours.DecimalString;

          if FINExecutor2PlanList.list[i].totalTimeDays = nil then
            Cells[7,i+1] := ''
          else
            Cells[7,i+1] := FINExecutor2PlanList.list[i].totalTimeDays.DecimalString;

          if FINExecutor2PlanList.list[i].totalTimeManHours = nil then
            Cells[8,i+1] := ''
          else
            Cells[8,i+1] := FINExecutor2PlanList.list[i].totalTimeManHours.DecimalString;

          if FINExecutor2PlanList.list[i].deliveryTime = nil then
            Cells[9,i+1] := ''
          else
            Cells[9,i+1] := FINExecutor2PlanList.list[i].deliveryTime.DecimalString;
          /////

          Cells[10,i+1] := FINExecutor2PlanList.list[i].budgetRefShortName;
          Cells[11,i+1] := FINExecutor2PlanList.list[i].userGen;

          if FINExecutor2PlanList.list[i].dateEdit = nil then
            Cells[12,i+1] := ''
          else
            Cells[12,i+1] := XSDateTimeWithDate2String(FINExecutor2PlanList.list[i].dateEdit);

          sgFINExecutor2Plan.RowCount := i + 2;
        end;

     sgFINExecutor2Plan.Row := 1;
  end;
  // end tsFINExecutor2Plan -----------------------------------------------------


  // start tsCompletionPlan  ----------------------------------------------------------
  if pcPlanWork.ActivePage = tsCompletionPlan then
  begin

    ClearGrid(sgCompletionPlan);
    SetGridHeaders(CompletionPlansHeaders, sgCompletionPlan.ColumnHeaders);

    TempENPlanWork := HTTPRIOENPlanWork as ENPlanWorkControllerSoapPort;

    linkedPlansFilterObj := ENPlanWorkFilter.Create;
    SetNullIntProps(linkedPlansFilterObj);
    SetNullXSProps(linkedPlansFilterObj);

    linkedPlansFilterObj.conditionSQL := 'code in (' +
      ' select plannewrefcode from enplancorrecthistory where planrefcode = '
          + IntToStr(ENPlanWorkObj.code) + // 1. Все дочерние. Сработает, если это - "корневой" план
      ' union ' +
      // ' select ' + IntToStr(ENPlanWorkObj.code) + ' as planrefcode ' + // 2. Добавим себя
      // ' union ' +
      ' select planrefcode from enplancorrecthistory where plannewrefcode = '
          + IntToStr(ENPlanWorkObj.code) + // 3. Присоедим "корневой", если это дочерний план
      ' union ' +
      ' select plannewrefcode from enplancorrecthistory where planrefcode in '+
      ' (select planrefcode from enplancorrecthistory where plannewrefcode = '
          + IntToStr(ENPlanWorkObj.code) + ')' + // 4. Все планы с таким же "корневым"
      ' )';

    intCodesList := TempENPlanWork.getFilteredCodeArray(linkedPlansFilterObj);
    for i:=0 to High(intCodesList) do
    begin
        if strCodesList <> '' then strCodesList := strCodesList + ', ';
        strCodesList := strCodesList + IntToStr(intCodesList[i]);
    end;

    if strCodesList <> '' then
    begin // если есть связанные планы
    linkedPlansFilterObj.conditionSQL :=
     '(enplanwork.statuscode = 1 or (enplanwork.statuscode = 3 and enplanwork.kindcode = 4) ) and ' +
     'code in (' + strCodesList + ')';
    linkedPlansFilterObj.orderBySQL := ' kindcode, datestart, code';

    linkedPlansList := TempENPlanWork.getPlanWorkGeneralList(linkedPlansFilterObj, 0, -1);

    if High(linkedPlansList.list) > -1 then
       sgCompletionPlan.RowCount := High(linkedPlansList.list) + 2
    else
       sgCompletionPlan.RowCount := 2;

    with sgCompletionPlan do
      for i := 0 to High(linkedPlansList.list) do
        begin
          Application.ProcessMessages;
          if linkedPlansList.list[i].code <> Low(Integer) then
            Cells[0,i+1] := IntToStr(linkedPlansList.list[i].code)
          else
            Cells[0,i+1] := '';

          if linkedPlansList.list[i].dateStart = nil then
            Cells[1,i+1] := ''
          else
            Cells[1,i+1] := XSDate2String(linkedPlansList.list[i].dateStart);

          Cells[2,i+1] := linkedPlansList.list[i].statusName;
          Cells[3,i+1] := linkedPlansList.list[i].workOrderNumber;
          Cells[4,i+1] := linkedPlansList.list[i].actInfo;

        end;
     sgCompletionPlan.Row := 1;
    end; // если были связанные планы
  end;
  // end tsCompletionPlan  ------------------------------------------------------------

  // start tsCCDamageLog  ----------------------------------------------------------
  if pcPlanWork.ActivePage = tsCCDamageLog then
  begin

    ClearGrid(sgENPlanWork2CCDamageLog);
    SetGridHeaders(ENPlanWork2CCDamageLogHeaders, sgENPlanWork2CCDamageLog.ColumnHeaders);

    TempENPlanWork2CCDamageLog := HTTPRIOENPlanWork2CCDamageLog as ENPlanWork2CCDamageLogControllerSoapPort;

    pw2damFilterObj := ENPlanWork2CCDamageLogFilter.Create;
    SetNullIntProps(pw2damFilterObj);
    SetNullXSProps(pw2damFilterObj);

    pw2damFilterObj.planRef:=ENPlanWorkRef.Create();
    pw2damFilterObj.planRef.code:=ENPlanWorkObj.code;

    pw2damList := TempENPlanWork2CCDamageLog.getScrollableFilteredList(pw2damFilterObj, 0, -1);

    if High(pw2damList.list) > -1 then
       sgENPlanWork2CCDamageLog.RowCount := High(pw2damList.list) + 2
    else
       sgENPlanWork2CCDamageLog.RowCount := 2;

    with sgENPlanWork2CCDamageLog do
      for i := 0 to High(pw2damList.list) do
      begin
        Application.ProcessMessages;
        if pw2damList.list[i].code <> Low(Integer) then
          Cells[0,i+1] := IntToStr(pw2damList.list[i].code)
        else
          Cells[0,i+1] := '';
        if pw2damList.list[i].ccDamageLogCode = Low(Integer) then
          Cells[1,i+1] := ''
        else
          Cells[1,i+1] := IntToStr(pw2damList.list[i].ccDamageLogCode);
        Cells[2,i+1] := pw2damList.list[i].newsPaperName;
        Cells[3,i+1] := pw2damList.list[i].newsPaperNumber;
        if pw2damList.list[i].datePubl = nil then
          Cells[4,i+1] := ''
        else
          Cells[4,i+1] := XSDate2String(pw2damList.list[i].datePubl);
        if pw2damList.list[i].dateBegin = nil then
          Cells[5,i+1] := ''
        else
          Cells[5,i+1] := XSDate2String(pw2damList.list[i].dateBegin);
        if pw2damList.list[i].dateFinish = nil then
          Cells[6,i+1] := ''
        else
          Cells[6,i+1] := XSDate2String(pw2damList.list[i].dateFinish);
        if pw2damList.list[i].needsApprovalByCustomer = 1 then
          Cells[7,i+1] := '+'
        else
          Cells[7,i+1] := '';
      end;
     sgENPlanWork2CCDamageLog.Row := 1;
  end;
  // end tsCCDamageLog  ------------------------------------------------------------
   if pcPlanWork.ActivePage = tsWriteOffOS then
  begin

     if ( ((DialogState = dsEdit) or (DialogState = dsView))
       and (ENPlanWorkObj.stateRef.code = ENConsts.ENPLANWORKSTATE_WRITINGS_OS) ) then
      begin
       if (ENPlanWorkObj.stateRef.code = ENPLANWORKSTATE_WRITINGS_OS ) then
       begin
         TempENPlanWorkENAct2OSData :=  HTTPRIOENPlanWorkENAct2OSData as ENPlanWorkENAct2OSDataControllerSoapPort;


         ENPlanWorkENActosdatafilter := ENPlanWorkENAct2OSDataFilter.Create;
         SetNullIntProps(ENPlanWorkENActosdatafilter);
         SetNullXSProps(ENPlanWorkENActosdatafilter);

         ENPlanWorkENActosdatafilter.planWorkRef := ENPlanWorkRef.Create;
         ENPlanWorkENActosdatafilter.planWorkRef.code := ENPlanWorkObj.code;


         ENPlanWorkENAct2OSDataList := TempENPlanWorkENAct2OSData.getScrollableFilteredList(ENPlanWorkENAct2OSDataFilter(ENPlanWorkENActosdatafilter),0,-1);

         if ENPlanWorkENAct2OSDataList.totalCount > 0 then
          begin

            ENPlanWorkENAct2OSDataObj :=  TempENPlanWorkENAct2OSData.getObject(ENPlanWorkENAct2OSDataList.list[0].code);

          end
         else
          begin
            ENPlanWorkENAct2OSDataObj := nil;
            ENPlanWorkENAct2OSDataObj := ENPlanWorkENAct2OSData.Create;
            SetNullIntProps(ENPlanWorkENAct2OSDataObj);
            SetNullXSProps(ENPlanWorkENAct2OSDataObj);
          end;

       end;
      end;


     SetFloatStyle([edtSumBuhWriteOZ]);

     if ENPlanWorkENAct2OSDataObj <> nil then
        begin
           edtKod_inv.Text :=  ENPlanWorkENAct2OSDataObj.kod_inv;
           edtName_inv.Text := ENPlanWorkENAct2OSDataObj.name_inv;

           if ENPlanWorkENAct2OSDataObj.typeWriteOff = 1 then
              rbTypeWriteOffFull.Checked := True
           else if ENPlanWorkENAct2OSDataObj.typeWriteOff = 0 then
              rbTypeWriteOffPartly.Checked := True;

           if ENPlanWorkENAct2OSDataObj.sumBuhWriteOZ = nil then
            edtSumBuhWriteOZ.Text := ''
          else
            edtSumBuhWriteOZ.Text := ENPlanWorkENAct2OSDataObj.sumBuhWriteOZ.DecimalString;

        end;

     if (DialogState = dsView) then
     DisableControls([spbOSSelect, gbTypeWriteOff]);

  end;

  //--------------------------------------------------------------------------------
  if pcPlanWork.ActivePage = tsOwnProduction then
  begin
    UpdateOwnProductionList;
  end;

  //--------------------------------------------------------------------------------
  if pcPlanWork.ActivePage = tsFuelHistory then
  begin
    UpdateFuelHistoryList;
  end;

  //--------------------------------------------------------------------------------
  if pcPlanWork.ActivePage = tsFuelSheetVolumes then
  begin
    UpdateFuelSheetVolumes;
    UpdateFuelSheetVolItemData;
  end;

  //--------------------------------------------------------------------------------
  if pcPlanWork.ActivePage = tsDFDocs then
  begin
    updateDFDocs;
  end;
end;

procedure TfrmENPlanWorkEdit.actInsertExecute(Sender: TObject);
Var TempENPlanWorkItem: ENPlanWorkItemControllerSoapPort;
    TempENEstimateItem: ENEstimateItemControllerSoapPort;
    TempENDestinationPoint : ENDestinationPointControllerSoapPort;
    TempENTransportRoute : ENTransportRouteControllerSoapPort;
    TempRQTransportInvoice : RQTransportInvoiceControllerSoapPort;
    TempFINExecutor2Plan : FINExecutor2PlanControllerSoapPort;
begin

  isSiz := false;

  //if ENPlanWorkObj.status.code <> ENPLANWORKSTATUS_GOOD then
  if not (ENPlanWorkObj.status.code in [ENPLANWORKSTATUS_GOOD, ENPLANWORKSTATUS_INCORRECTION]) then
  begin
    Application.MessageBox(PChar('План затверджений або видалений !'), PChar('Увага'), MB_ICONWARNING);
    exit;
  end;

  if pcPlanWork.ActivePage = tsPlanWorkItems then
  begin

    // SUPP-60844... 01.06.2017 +++ для плановых - запрещено добавление работ...
    if (ENPlanWorkObj.formRef.code = ENPLANWORKFORM_PLANNED)
          and ((ENPlanWorkObj.kind.code = ENPLANWORKKIND_NPZ) or (ENPlanWorkObj.kind.code = ENPLANWORKKIND_FACT)) then
    begin
      Application.MessageBox(PChar('Плановий обсяг робіт не змінюється!'), PChar('Увага'), MB_ICONWARNING);
      exit;
    end;

    selectedRow := sgENPlanWorkItem.Row;

    TempENPlanWorkItem := HTTPRIOENPlanWorkItem as ENPlanWorkItemControllerSoapPort;
    ENPlanWorkItemObj:=ENPlanWorkItem.Create;
    SetNullIntProps(ENPlanWorkItemObj);
    SetNullXSProps(ENPlanWorkItemObj);

     ENPlanWorkItemObj.countGen:= TXSDecimal.Create;
     //ENPlanWorkItemObj.dateEdit:= TXSDate.Create;
     ENPlanWorkItemObj.planRef := ENPlanWorkRef.Create;

    try
      frmENPlanWorkItemEdit:=TfrmENPlanWorkItemEdit.Create(Application, dsInsert);

      // 30.05.2012 Присваивание типа акта для формы frmENPlanWorkItemEdit
      frmENPlanWorkItemEdit.planWorkStateCode := ENPlanWorkObj.stateRef.code;

      if ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_SIZ then frmENPlanWorkItemEdit.isSiz := true;
      if isTransport then frmENPlanWorkItemEdit.isTransport := true;

      if ENPlanWorkObj.typeRef.code in [ENPLANWORKTYPE_SERVICES_FROM_SIDE, ENPLANWORKTYPE_SERVICES_FROM_SIDE_INVEST]
        then frmENPlanWorkItemEdit.inServices := false;

      try
        ENPlanWorkItemObj.planRef.code := ENPlanWorkObj.code;
        frmENPlanWorkItemEdit.pcEstimate.Visible := false;
        if frmENPlanWorkItemEdit.ShowModal = mrOk then
        begin
          if ENPlanWorkItemObj<>nil then
          begin
              //TempENPlanWorkItem.add(ENPlanWorkItemObj);
              UpdateGrid(Sender);
          end;
        end;
        sgENPlanWorkItem.Row :=  sgENPlanWorkItem.RowCount - 1;
      finally
        frmENPlanWorkItemEdit.Free;
        frmENPlanWorkItemEdit:=nil;
      end;
    finally
      ENPlanWorkItemObj.Free;
    end;
  end;

  // -------------------------------------------------------------------------------------------

  if pcPlanWork.ActivePage = tsServicesItem then
  begin
    selectedRow := sgServicesItem.Row;
    TempENPlanWorkItem := HTTPRIOENPlanWorkItem as ENPlanWorkItemControllerSoapPort;
    ENPlanWorkItemObj:=ENPlanWorkItem.Create;
    SetNullIntProps(ENPlanWorkItemObj);
    SetNullXSProps(ENPlanWorkItemObj);

    ENPlanWorkItemObj.countGen:= TXSDecimal.Create;
    ENPlanWorkItemObj.planRef := ENPlanWorkRef.Create;

    try
      frmENPlanWorkItemEdit:=TfrmENPlanWorkItemEdit.Create(Application, dsInsert);

      frmENPlanWorkItemEdit.isServicesProject := isServicesProject;

      if ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_SIZ then frmENPlanWorkItemEdit.isSiz := true;
      if isTransport then frmENPlanWorkItemEdit.isTransport := true;
      if ENPlanWorkObj.typeRef.code in [ENPLANWORKTYPE_SERVICES_FROM_SIDE, ENPLANWORKTYPE_SERVICES_FROM_SIDE_INVEST]
        then frmENPlanWorkItemEdit.inServices := true;

      try
        ENPlanWorkItemObj.planRef.code := ENPlanWorkObj.code;
        frmENPlanWorkItemEdit.pcEstimate.Visible := false;
        frmENPlanWorkItemEdit.lblCommentGen.Caption := 'Предмет виконання робіт';

        if frmENPlanWorkItemEdit.ShowModal = mrOk then
        begin
          if ENPlanWorkItemObj<>nil then
          begin
              //TempENPlanWorkItem.add(ENPlanWorkItemObj);
              UpdateGrid(Sender);
          end;
        end;
        sgServicesItem.Row := sgServicesItem.RowCount - 1;
      finally
        frmENPlanWorkItemEdit.Free;
        frmENPlanWorkItemEdit:=nil;
      end;
    finally
      ENPlanWorkItemObj.Free;
    end;
  end;

  // -------------------------------------------------------------------------------------------

  if pcPlanWork.ActivePage = tsEstimateItems then
  begin
    TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;
    ENEstimateItemObj := ENEstimateItem.Create;

    ENEstimateItemObj.countGen := TXSDecimal.Create;
    ENEstimateItemObj.countFact := TXSDecimal.Create;
    ENEstimateItemObj.dateEdit := TXSDate.Create;

    ENEstimateItemObj.planRef := ENPlanWorkRef.Create;
    ENEstimateItemObj.planRef.code := ENPlanWorkObj.code;

    ENEstimateItemObj.kindRef := ENEstimateItemKindRef.Create;
    ENEstimateItemObj.kindRef.code := ENESTIMATEITEMKIND_MATERIALS;


    try
      frmENEstimateItemEdit := TfrmENEstimateItemEdit.Create(Application, dsInsert);
      frmENEstimateItemEdit.ENPlanWorkCode := ENPlanWorkObj.code;
      if ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_SIZ then frmENEstimateItemEdit.isSiz := true;
      try
        if frmENEstimateItemEdit.ShowModal = mrOk then
        begin
          if ENEstimateItemObj <> nil then
            //TempENEstimateItem.add(ENEstimateItemObj);
            UpdateGrid(Sender);
        end;
      finally
        frmENEstimateItemEdit.Free;
        frmENEstimateItemEdit := nil;
      end;
    finally
      ENEstimateItemObj.Free;
    end;
  end;


  // -------------------------------------------------------------------------------------------
{
  if pcPlanWork.ActivePage = tsGSM then
  begin

    TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;
    ENEstimateItemObj := ENEstimateItem.Create;
    try
      ENEstimateItemObj.countGen := TXSDecimal.Create;
      ENEstimateItemObj.countFact := TXSDecimal.Create;
      ENEstimateItemObj.dateEdit := TXSDate.Create;

      ENEstimateItemObj.planRef := ENPlanWorkRef.Create;
      ENEstimateItemObj.planRef.code := ENPlanWorkObj.code;

      ENEstimateItemObj.kindRef := ENEstimateItemKindRef.Create;
      ENEstimateItemObj.kindRef.code := ENESTIMATEITEMKIND_GSM;

      frmENEstimateItemEdit := TfrmENEstimateItemEdit.Create(Application, dsInsert);
      
      try
        frmENEstimateItemEdit.ENPlanWorkCode := ENPlanWorkObj.code;

        frmENEstimateItemEdit.cbENEstimateItemKind.ItemIndex := ENESTIMATEITEMKIND_GSM - 1;

        DisableControls([ frmENEstimateItemEdit.cbENEstimateItemKind] );

        if frmENEstimateItemEdit.ShowModal = mrOk then
        begin
          if ENEstimateItemObj <> nil then
            //TempENEstimateItem.add(ENEstimateItemObj);
            UpdateGrid(Sender);
        end;
      finally
        frmENEstimateItemEdit.Free;
        frmENEstimateItemEdit := nil;
      end;
    finally
      ENEstimateItemObj.Free;
    end;
  end;
}

  if pcPlanWork.ActivePage = tsDismount then
  begin
    TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;
    ENEstimateItemObj := ENEstimateItem.Create;
    try
      ENEstimateItemObj.countGen := TXSDecimal.Create;
      ENEstimateItemObj.countFact := TXSDecimal.Create;
      ENEstimateItemObj.dateEdit := TXSDate.Create;

      ENEstimateItemObj.planRef := ENPlanWorkRef.Create;
      ENEstimateItemObj.planRef.code := ENPlanWorkObj.code;

      ENEstimateItemObj.kindRef := ENEstimateItemKindRef.Create;
      ENEstimateItemObj.kindRef.code := ENESTIMATEITEMKIND_DISMOUNT;

      frmENEstimateItemEdit := TfrmENEstimateItemEdit.Create(Application, dsInsert);
      try
        frmENEstimateItemEdit.ENPlanWorkCode := ENPlanWorkObj.code;
        frmENEstimateItemEdit.cbENEstimateItemKind.ItemIndex := ENESTIMATEITEMKIND_DISMOUNT - 1;
        frmENEstimateItemEdit.DisableControls([frmENEstimateItemEdit.cbENEstimateItemKind]);
        frmENEstimateItemEdit.HideControls([frmENEstimateItemEdit.lblCountGen, frmENEstimateItemEdit.edtCountGen]);
        frmENEstimateItemEdit.lblCountFact.Caption := 'кількість';

        if ( (ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_WRITEOFF_OS)
                 and (ENPlanWorkObj.stateRef.code = ENPLANWORKSTATE_WRITINGS_OS) )  then
        frmENEstimateItemEdit.isWriteOff_OZ := True
        else
        frmENEstimateItemEdit.isWriteOff_OZ := False;


        if frmENEstimateItemEdit.ShowModal = mrOk then
        begin
          if ENEstimateItemObj <> nil then
            //TempENEstimateItem.add(ENEstimateItemObj);
            UpdateGrid(Sender);
        end;
      finally
        frmENEstimateItemEdit.Free;
        frmENEstimateItemEdit := nil;
      end;
    finally
      ENEstimateItemObj.Free;
    end;
  end;


  // ------------------------------
 if pcPlanWork.ActivePage = tsRefinement then
  begin
    TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;
    ENEstimateItemObj := ENEstimateItem.Create;

    ENEstimateItemObj.countGen := TXSDecimal.Create;
    ENEstimateItemObj.countFact := TXSDecimal.Create;
    ENEstimateItemObj.dateEdit := TXSDate.Create;

    ENEstimateItemObj.planRef := ENPlanWorkRef.Create;
    ENEstimateItemObj.planRef.code := ENPlanWorkObj.code;

    ENEstimateItemObj.kindRef := ENEstimateItemKindRef.Create;
    ENEstimateItemObj.kindRef.code := ENESTIMATEITEMKIND_REFINEMENT;


    try
      frmENEstimateItemEdit := TfrmENEstimateItemEdit.Create(Application, dsInsert);
      frmENEstimateItemEdit.ENPlanWorkCode := ENPlanWorkObj.code;
      //if ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_SIZ then frmENEstimateItemEdit.isSiz := true;
      try
        if frmENEstimateItemEdit.ShowModal = mrOk then
        begin
          if ENEstimateItemObj <> nil then
            //TempENEstimateItem.add(ENEstimateItemObj);
            UpdateGrid(Sender);
        end;
      finally
        frmENEstimateItemEdit.Free;
        frmENEstimateItemEdit := nil;
      end;
    finally
      ENEstimateItemObj.Free;
    end;
  end;

    // ------------------------------
  if pcPlanWork.ActivePage = tsProduced then
  begin
    TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;
    ENEstimateItemObj := ENEstimateItem.Create;
    try
      ENEstimateItemObj.countGen := TXSDecimal.Create;
      ENEstimateItemObj.countFact := TXSDecimal.Create;
      ENEstimateItemObj.dateEdit := TXSDate.Create;

      ENEstimateItemObj.planRef := ENPlanWorkRef.Create;
      ENEstimateItemObj.planRef.code := ENPlanWorkObj.code;

      ENEstimateItemObj.kindRef := ENEstimateItemKindRef.Create;
      ENEstimateItemObj.kindRef.code := ENESTIMATEITEMKIND_PRODUCED;

      frmENEstimateItemEdit := TfrmENEstimateItemEdit.Create(Application, dsInsert);
      try
        frmENEstimateItemEdit.ENPlanWorkCode := ENPlanWorkObj.code;
        //frmENEstimateItemEdit.cbENEstimateItemKind.ItemIndex := ENESTIMATEITEMKIND_PRODUCED - 2;
        frmENEstimateItemEdit.cbENEstimateItemKind.ItemIndex := ENESTIMATEITEMKIND_PRODUCED - 1;
        frmENEstimateItemEdit.DisableControls([frmENEstimateItemEdit.cbENEstimateItemKind]);

        frmENEstimateItemEdit.HideControls([frmENEstimateItemEdit.lblCountGen, frmENEstimateItemEdit.edtCountGen]);
        frmENEstimateItemEdit.lblCountFact.Caption := 'кількість';

        if ENPlanWorkObj.stateRef.code <> ENPLANWORKSTATE_SIDEWORKS then
         frmENEstimateItemEdit.DisableControls([frmENEstimateItemEdit.spbPlanItem]);

        if frmENEstimateItemEdit.ShowModal = mrOk then
        begin
          if ENEstimateItemObj <> nil then
            //TempENEstimateItem.add(ENEstimateItemObj);
            UpdateGrid(Sender);
        end;
      finally
        frmENEstimateItemEdit.Free;
        frmENEstimateItemEdit := nil;
      end;
    finally
      ENEstimateItemObj.Free;
    end;
  end;

  // -------- start tsTransportRoute  --------
  if pcPlanWork.ActivePage = tsTransportRoute then
  begin
    TempENTransportRoute := HTTPRIOENTransportRoute as ENTransportRouteControllerSoapPort;
    ENTransportRouteObj := ENTransportRoute.Create;

    ENTransportRouteObj.distance := TXSDecimal.Create;
    ENTransportRouteObj.weight := TXSDecimal.Create;
    ENTransportRouteObj.dateEdit := TXSDateTime.Create;

    ENTransportRouteObj.planRef := ENPlanWorkRef.Create;
    ENTransportRouteObj.planRef.code := ENPlanWorkObj.code;

    try
      frmENTransportRouteEdit := TfrmENTransportRouteEdit.Create(Application, dsInsert);
      frmENTransportRouteEdit.ENPlanWorkCode := ENPlanWorkObj.code;
      try
        if frmENTransportRouteEdit.ShowModal = mrOk then
        begin
          if ENTransportRouteObj <> nil then
            //TempENEstimateItem.add(ENEstimateItemObj);
            UpdateGrid(Sender);
        end;
      finally
        frmENTransportRouteEdit.Free;
        frmENTransportRouteEdit := nil;
      end;
    finally
      ENTransportRouteObj.Free;
    end;
  end;
  // -------- end tsTransportRoute  --------


  // -------- start tsRQTransportInvoice  --------
  if pcPlanWork.ActivePage = tsRQTransportInvoice then
  begin
    TempRQTransportInvoice := HTTPRIORQTransportInvoice as RQTransportInvoiceControllerSoapPort;
    RQTransportInvoiceObj := RQTransportInvoice.Create;

    RQTransportInvoiceObj.planRef := ENPlanWorkRef.Create;
    RQTransportInvoiceObj.planRef.code := ENPlanWorkObj.code;

    try
      frmRQTransportInvoiceEdit := TfrmRQTransportInvoiceEdit.Create(Application, dsInsert);
      frmRQTransportInvoiceEdit.ENPlanWorkCode := ENPlanWorkObj.code;
      try
        if frmRQTransportInvoiceEdit.ShowModal = mrOk then
        begin
          if RQTransportInvoiceObj <> nil then
            UpdateGrid(Sender);
        end;
      finally
        frmRQTransportInvoiceEdit.Free;
        frmRQTransportInvoiceEdit := nil;
      end;
    finally
      RQTransportInvoiceObj.Free;
    end;
  end;
  // -------- end tsRQTransportInvoice  --------

  ///      start tsCustomerMaterials

  if pcPlanWork.ActivePage = tsCustomerMaterials then
  begin
    TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;
    ENEstimateItemObj := ENEstimateItem.Create;

    ENEstimateItemObj.countGen := TXSDecimal.Create;
    ENEstimateItemObj.countFact := TXSDecimal.Create;
    ENEstimateItemObj.dateEdit := TXSDate.Create;

    ENEstimateItemObj.planRef := ENPlanWorkRef.Create;
    ENEstimateItemObj.planRef.code := ENPlanWorkObj.code;

    ENEstimateItemObj.kindRef := ENEstimateItemKindRef.Create;
    ENEstimateItemObj.kindRef.code := ENESTIMATEITEMKIND_CUSTOMER_MATERIALS;

    //ENEstimateItemObj.statusRef := ENEstimateItemStatusRef.Create;
    //ENEstimateItemObj.statusRef.code := ENESTIMATEITEMSTATUS_UNUSED;

    try
      frmENEstimateItemEdit := TfrmENEstimateItemEdit.Create(Application, dsInsert);
      frmENEstimateItemEdit.ENPlanWorkCode := ENPlanWorkObj.code;
      frmENEstimateItemEdit.cbEstimateItemStatus.ItemIndex := ENESTIMATEITEMSTATUS_UNUSED-4;
      frmENEstimateItemEdit.cbENEstimateItemKind.ItemIndex := ENESTIMATEITEMKIND_CUSTOMER_MATERIALS-1;

      frmENEstimateItemEdit.HideControls([frmENEstimateItemEdit.lblCountGen, frmENEstimateItemEdit.edtCountGen]);
      frmENEstimateItemEdit.lblCountFact.Caption := 'кількість';

      frmENEstimateItemEdit.DisableControls([frmENEstimateItemEdit.cbENEstimateItemKind,
                                             frmENEstimateItemEdit.cbEstimateItemStatus]);

      if ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_SIZ then frmENEstimateItemEdit.isSiz := true;
      try
        if frmENEstimateItemEdit.ShowModal = mrOk then
        begin
          if ENEstimateItemObj <> nil then
            //TempENEstimateItem.add(ENEstimateItemObj);
            UpdateGrid(Sender);
        end;
      finally
        frmENEstimateItemEdit.Free;
        frmENEstimateItemEdit := nil;
      end;
    finally
      ENEstimateItemObj.Free;
    end;
  end;

  ///      end tsCustomerMaterials

  // -------- start tsFINExecutor2Plan  --------
  if (pcPlanWork.ActivePage = tsFINExecutor2Plan) then
  begin
    TempFINExecutor2Plan := HTTPRIOFINExecutor2Plan as FINExecutor2PlanControllerSoapPort;

    FINExecutor2PlanObj := FINExecutor2Plan.Create;
    SetNullIntProps(FINExecutor2PlanObj);
    SetNullXSProps(FINExecutor2PlanObj);
    FINExecutor2PlanObj.finExecutorTypeRef := FINExecutorTypeRef.Create;
    FINExecutor2PlanObj.finExecutorTypeRef.code := FINEXECUTOR_TYPE_ADDITIONAL;
    FINExecutor2PlanObj.planRef := ENPlanWorkRef.Create;
    FINExecutor2PlanObj.planRef.code := ENPlanWorkObj.code;

    frmFINExecutor2PlanEdit := TfrmFINExecutor2PlanEdit.Create(Application, dsInsert);
    try
      if frmFINExecutor2PlanEdit.ShowModal= mrOk then
        begin
          UpdateGrid(Sender);
        end;
    finally
      frmFINExecutor2PlanEdit.Free;
      frmFINExecutor2PlanEdit:=nil;
    end;
  end;
  // -------- end tsFINExecutor2Plan  --------

end;

procedure TfrmENPlanWorkEdit.actInsertprojectExecute(Sender: TObject);
// Var TempENPlanProject: ENPlanProjectControllerSoapPort;
begin
  // TempENPlanProject := HTTPRIOENPlanProject as ENPlanProjectControllerSoapPort;  /// Это здесь уже лишнее!!!
  ENPlanProjectObj:=ENPlanProject.Create;
  SetNullIntProps(ENPlanProjectObj);
  SetNullXSProps(ENPlanProjectObj);

   //ENPlanProjectObj.dateEdit:= TXSDateTime.Create;



  try
    frmENPlanProjectEdit:=TfrmENPlanProjectEdit.Create(Application, dsInsert);

    if ENPlanProjectObj.planRef  = nil then
       ENPlanProjectObj.planRef := ENPlanWorkRef.Create();
       ENPlanProjectObj.planRef.code := ENPlanWorkObj.code;

    try
      if frmENPlanProjectEdit.ShowModal = mrOk then
      begin
        if ENPlanProjectObj<>nil then
            //TempENPlanProject.add(ENPlanProjectObj);
            UpdateGrid(Sender);
      end;
    finally
      frmENPlanProjectEdit.Free;
      frmENPlanProjectEdit:=nil;
    end;
  finally
    ENPlanProjectObj.Free;
  end;
end;

procedure TfrmENPlanWorkEdit.UpdateFuelHistoryList;
var
  TempENPlanWorkFuelHistory: ENPlanWorkFuelHistoryControllerSoapPort;
  fuelHistoryFilter: ENPlanWorkFuelHistoryFilter;
  fuelHistoryList: ENPlanWorkFuelHistoryShortList;
  i, fuelHistoryCount: Integer;
begin
  ClearGrid(sgENPlanWorkFuelHistory);

  if DialogState = dsInsert then Exit;

  TempENPlanWorkFuelHistory := HTTPRIOENPlanWorkFuelHistory as ENPlanWorkFuelHistoryControllerSoapPort;

  fuelHistoryFilter := ENPlanWorkFuelHistoryFilter.Create;
  SetNullIntProps(fuelHistoryFilter);
  SetNullXSProps(fuelHistoryFilter);

  fuelHistoryFilter.planRef := ENPlanWorkRef.Create;
  fuelHistoryFilter.planRef.code := ENPlanWorkObj.code;

  fuelHistoryFilter.orderBySQL := 'ENPLANWORKFUELHISTORY.VERSION, ENPLANWORKFUELHISTORY.DATEGEN, TKFUELTYPE.NAME, ENPLANWORKFUELHISTORY.CODE';

  fuelHistoryList := TempENPlanWorkFuelHistory.getScrollableFilteredList(fuelHistoryFilter, 0, -1);

  fuelHistoryCount := High(fuelHistoryList.list);

  if fuelHistoryCount > -1 then
    sgENPlanWorkFuelHistory.RowCount := fuelHistoryCount + 2
  else
    sgENPlanWorkFuelHistory.RowCount := 2;

{
  ENPlanWorkFuelHistoryHeaders: array [1..7] of String =
        ( 'Код'
          ,'Версія запису'
          ,'Дата плана'
          ,'Тип ПММ'
          ,'Обсяг ПММ, л.'
          ,'Користувач, який створив запис'
          ,'Дата створення'
          //,'Користувач, який вніс зміни'
          //,'Дата зміни'
        );
}

   with sgENPlanWorkFuelHistory do
     for i := 0 to fuelHistoryCount do
     begin
       Application.ProcessMessages;

       if fuelHistoryList.list[i].code <> Low(Integer) then
         Cells[0,i+1] := IntToStr(fuelHistoryList.list[i].code)
       else
         Cells[0,i+1] := '';

       if fuelHistoryList.list[i].version = Low(Integer) then
         Cells[1,i+1] := ''
       else
         Cells[1,i+1] := IntToStr(fuelHistoryList.list[i].version);

       if fuelHistoryList.list[i].dateGen = nil then
         Cells[2,i+1] := ''
       else
         Cells[2,i+1] := XSDate2String(fuelHistoryList.list[i].dateGen);

       Cells[3,i+1] := fuelHistoryList.list[i].fuelTypeRefName;

       if fuelHistoryList.list[i].countGen = nil then
         Cells[4,i+1] := ''
       else
         Cells[4,i+1] := fuelHistoryList.list[i].countGen.DecimalString;

       Cells[5,i+1] := fuelHistoryList.list[i].userAdd;

       if fuelHistoryList.list[i].dateAdd = nil then
         Cells[6,i+1] := ''
       else
         Cells[6,i+1] := XSDateTimeWithDate2String(fuelHistoryList.list[i].dateAdd);

       sgENPlanWorkFuelHistory.RowCount := i + 2;
     end;

  sgENPlanWorkFuelHistory.Row := 1;
end;

procedure TfrmENPlanWorkEdit.UpdateFuelSheetVolItemData;
var
  TempENFuelSheetVolItemData: ENFuelSheetVolItemDataControllerSoapPort;
  itemDataFilter: ENFuelSheetVolItemDataFilter;
  itemDataList: ENFuelSheetVolItemDataShortList;
  i, itemDataCount, fuelSheetVolumesCode: Integer;
begin
  ClearGrid(sgENFuelSheetVolItemData);

  if DialogState = dsInsert then Exit;

  { Будем выбирать все строки, в которые попал план, а не по конкретной ведомости
  try
    fuelSheetVolumesCode := StrToInt(sgENFuelSheetVolumes.Cells[0, sgENFuelSheetVolumes.Row]);
  except
    on EConvertError do Exit;
  end;

  if fuelSheetVolumesCode <= 0 then Exit;
  }

  if ENPlanWorkObj.code <= 0 then Exit;

  itemDataFilter := ENFuelSheetVolItemDataFilter.Create;
  SetNullIntProps(itemDataFilter);
  SetNullXSProps(itemDataFilter);

  //itemDataFilter.sheetVolumesRef := ENFuelSheetVolumesRef.Create;
  //itemDataFilter.sheetVolumesRef.code := fuelSheetVolumesCode;

  itemDataFilter.plan_code := ENPlanWorkObj.code;

  TempENFuelSheetVolItemData := HTTPRIOENFuelSheetVolItemData as ENFuelSheetVolItemDataControllerSoapPort;

  itemDataList := TempENFuelSheetVolItemData.getScrollableFilteredList(itemDataFilter, 0, -1);

  itemDataCount := High(itemDataList.list);

  if itemDataCount > -1 then
    sgENFuelSheetVolItemData.RowCount := itemDataCount + 2
  else
    sgENFuelSheetVolItemData.RowCount := 2;

  with sgENFuelSheetVolItemData do
    for i := 0 to itemDataCount do
    begin
      Application.ProcessMessages;

      if itemDataList.list[i].code <> Low(Integer) then
        Cells[0,i+1] := IntToStr(itemDataList.list[i].code)
      else
        Cells[0,i+1] := '';

      Cells[1,i+1] := itemDataList.list[i].materialName;

      if itemDataList.list[i].countFact = nil then
        Cells[2,i+1] := ''
      else
        Cells[2,i+1] := itemDataList.list[i].countFact.DecimalString;

      sgENFuelSheetVolItemData.RowCount := i + 2;
    end;

  sgENFuelSheetVolItemData.Row := 1;
end;

procedure TfrmENPlanWorkEdit.UpdateFuelSheetVolumes;
var TempENFuelSheetVolumes: ENFuelSheetVolumesControllerSoapPort;
    fuelSheetVolumesFilter: ENFuelSheetVolumesFilter;
    fuelSheetVolumesList: ENFuelSheetVolumesShortList;
    i, fuelSheetVolumesCount: Integer;
begin
  ClearGrid(sgENFuelSheetVolumes);

  if DialogState = dsInsert then Exit;

  if ENPlanWorkObj.code <= 0 then Exit;

  TempENFuelSheetVolumes := HTTPRIOENFuelSheetVolumes as ENFuelSheetVolumesControllerSoapPort;

  fuelSheetVolumesFilter := ENFuelSheetVolumesFilter.Create;
  SetNullIntProps(fuelSheetVolumesFilter);
  SetNullXSProps(fuelSheetVolumesFilter);

  fuelSheetVolumesFilter.conditionSQL :=
    'ENFUELSHEETVOLUMES.CODE in ' +
    '( ' +
    '  select d.sheetvolumesrefcode ' +
    '  from enfuelsheetvolitemdata d ' +
    '  where d.plan_code = ' + IntToStr(ENPlanWorkObj.code) +
    ') ';

  fuelSheetVolumesFilter.orderBySQL := 'ENFUELSHEETVOLUMES.STARTDATE, ENFUELSHEETVOLUMES.FUELTYPE';

  fuelSheetVolumesList := TempENFuelSheetVolumes.getScrollableFilteredList(fuelSheetVolumesFilter, 0, -1);

  fuelSheetVolumesCount := High(fuelSheetVolumesList.list);

  if fuelSheetVolumesCount > -1 then
    sgENFuelSheetVolumes.RowCount := fuelSheetVolumesCount + 2
  else
    sgENFuelSheetVolumes.RowCount := 2;

   with sgENFuelSheetVolumes do
    for i := 0 to fuelSheetVolumesCount do
      begin
        Application.ProcessMessages;
        if fuelSheetVolumesList.list[i].code <> Low(Integer) then
          Cells[0,i+1] := IntToStr(fuelSheetVolumesList.list[i].code)
        else
          Cells[0,i+1] := '';
        Cells[1,i+1] := fuelSheetVolumesList.list[i].name;

        if fuelSheetVolumesList.list[i].startDate = nil then
          Cells[2,i+1] := ''
        else
          Cells[2,i+1] := MonthesNames[fuelSheetVolumesList.list[i].startDate.Month] + ' ' +
                          IntToStr(fuelSheetVolumesList.list[i].startDate.Year) + ', ' +
                          IntToStr(getDecadeNumber(fuelSheetVolumesList.list[i].startDate)) + ' декада';

        Cells[3,i+1] := fuelSheetVolumesList.list[i].fuelTypeName;
        Cells[4,i+1] := fuelSheetVolumesList.list[i].statusRefName;

        Cells[5,i+1] := fuelSheetVolumesList.list[i].userAdd;
        if fuelSheetVolumesList.list[i].dateAdd = nil then
          Cells[6,i+1] := ''
        else
          Cells[6,i+1] := XSDateTimeWithDate2String(fuelSheetVolumesList.list[i].dateAdd);

        Cells[7,i+1] := fuelSheetVolumesList.list[i].userGen;
        if fuelSheetVolumesList.list[i].dateEdit = nil then
          Cells[8,i+1] := ''
        else
          Cells[8,i+1] := XSDateTimeWithDate2String(fuelSheetVolumesList.list[i].dateEdit);

        sgENFuelSheetVolumes.RowCount := i + 2;
      end;

   sgENFuelSheetVolumes.Row := 1;
end;

procedure TfrmENPlanWorkEdit.UpdateGrid(Sender: TObject);
Var i, j: Integer;
begin

  if pcPlanWork.ActivePage = tsXtqnPercent then
  begin

   actUpdateXqtnPercentExecute(Sender);

  end;


  if pcPlanWork.ActivePage = tsProject then
  begin
    if (ENPlanWorkObj.kind.code = ENConsts.ENPLANWORKKIND_CURRENT) then
      HideControls([ToolButton6project, ToolButton7project ], false )
      else
      HideControls([ToolButton6project , ToolButton7project ], true );


    actUpdateprojectExecute(Sender);
  end;

 if pcPlanWork.ActivePage = tsPlanWorkItems then
 begin
   ClearGrid(sgENPlanWorkItem);
 end;

 if pcPlanWork.ActivePage = tsEstimateItems then
 begin
   ClearGrid(sgENEstimateItem);
   if (DialogState = dsEdit) and (isSiz) then DisableActions([actInsert]);
  end;

 if pcPlanWork.ActivePage = tsGSM then
 begin
   ClearGrid(sgGSM);
 end;

 if pcPlanWork.ActivePage = tsDismount then
 begin
   ClearGrid(sgDismount);
 end;

  if pcPlanWork.ActivePage = tsProduced then
 begin
   ClearGrid(sgProduced);
 end;

 if pcPlanWork.ActivePage = tsMoves then
 begin
   ClearGrid(sgENPlanWorkMoveHistory);
 end;

 if pcPlanWork.ActivePage = tsCorrections then
 begin
   ClearGrid(sgENPlanCorrectHistory);
 end;

 if pcPlanWork.ActivePage = tsHumens then
 begin
   ClearGrid(sgENHumenItem);
 end;

 if pcPlanWork.ActivePage = tsTransports then
 begin
   ClearGrid(sgENTransportItem);
   ClearGrid(sgTransportForDistance);
 end;

 if pcPlanWork.ActivePage = tsRefinement then
 begin
   ClearGrid(sgRefinement);
 end;

 if pcPlanWork.ActivePage = tsRQFKOrder then
 begin
   ClearGrid(sgRQFKOrder);
 end;

  if pcPlanWork.ActivePage = tsWorkOrder then
  begin
  // 26.11.2014 +++ VS +++ открывем на факте
  if (ENPlanWorkObj.kind.code = ENPLANWORKKIND_NPZ) or (ENPlanWorkObj.kind.code = ENPLANWORKKIND_FACT) then
    btnPrintWorkOrder.Visible:= True;
  end;

 if pcPlanWork.ActivePage = tsServicesItem then
 begin
   ClearGrid(sgServicesItem);
 end;

 if pcPlanWork.ActivePage = tsTransportOrder then
 begin
   ClearGrid(sgGroupedTransportItem);
   ClearGrid(sgDetailedTransportItem);
   chbDetailed.Checked := false;
 end;

 if pcPlanWork.ActivePage = tsTransportRoute then
 begin
   ClearGrid(sgENTransportRoute);
   ClearGrid(sgRQFKOrder2Route);
 end;

 if pcPlanWork.ActivePage = tsRQTransportInvoice then
 begin
   ClearGrid(sgRQTransportInvoice);
 end;

 if pcPlanWork.ActivePage = tsCustomerMaterials then
 begin
   ClearGrid(sgCustomerMaterials);
  end;

  if pcPlanWork.ActivePage = tsDFDocs then
  begin
    ClearGrid(sgDFDocs);
  end;

 pcPlanWorkChange(Sender);
end;

procedure TfrmENPlanWorkEdit.actEditExecute(Sender: TObject);
Var planCode : Integer;
    damCode: Integer;
    tPlan : ENPlanWork;
    TempENPlanWork : ENPlanWorkControllerSoapPort;
    TempENPlanWorkItem: ENPlanWorkItemControllerSoapPort;
    TempENEstimateItem: ENEstimateItemControllerSoapPort;
    TempENDestinationPoint : ENDestinationPointControllerSoapPort;
    TempENTransportRoute : ENTransportRouteControllerSoapPort;
    TempRQTransportInvoice : RQTransportInvoiceControllerSoapPort;
    TempFINExecutor2Plan : FINExecutor2PlanControllerSoapPort;
    TempFINExecutor : FINExecutorControllerSoapPort;
    TempCCDamageLog : CCDamageLogControllerSoapPort;
    TempENTransportItem : ENTransportItemControllerSoapPort;
    finExecutorCode: Integer;

    grid: TAdvStringGrid;
begin

  isSiz := false;

  //if ENPlanWorkObj.status.code <> ENPLANWORKSTATUS_GOOD then
  if not (ENPlanWorkObj.status.code in [ENPLANWORKSTATUS_GOOD, ENPLANWORKSTATUS_INCORRECTION]) then
  begin
      Application.MessageBox(PChar('План затверджений або видалений !'), PChar('Увага'), MB_ICONWARNING);
      exit;
  end;

  // ------------------------------
  if pcPlanWork.ActivePage = tsPlanWorkItems then
  begin
    TempENPlanWorkItem := HTTPRIOENPlanWorkItem as ENPlanWorkItemControllerSoapPort;
    try
      ENPlanWorkItemObj := TempENPlanWorkItem.getObject(StrToInt(sgENPlanWorkItem.Cells[0,sgENPlanWorkItem.Row]));
    except
      on EConvertError do Exit;
    end;

    selectedRow := sgENPlanWorkItem.Row;
    frmENPlanWorkItemEdit:=TfrmENPlanWorkItemEdit.Create(Application, dsEdit);

      // 30.05.2012 Присваивание типа акта для формы frmENPlanWorkItemEdit
    frmENPlanWorkItemEdit.planWorkStateCode := ENPlanWorkObj.stateRef.code;

    if ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_SIZ then
      begin
        frmENPlanWorkItemEdit.isSiz := true;
        isSiz := true;
      end;

    if isTransport then
      begin
        frmENPlanWorkItemEdit.isTransport := true;
        // для грузов показываем материалы
        if ((ENPlanWorkItemObj.kartaRef.code = 500004873) or (ENPlanWorkItemObj.kartaRef.code = 500004877)) then
           frmENPlanWorkItemEdit.tsMaterials.TabVisible := true
        else frmENPlanWorkItemEdit.tsMaterials.TabVisible := false;
      end;

    if isTransport then
      begin
        frmENPlanWorkItemEdit.isTransport := true;
        // для перевозок показываем персонал
        if ((ENPlanWorkItemObj.kartaRef.code = 500004875) or (ENPlanWorkItemObj.kartaRef.code = 500004876)) then
           frmENPlanWorkItemEdit.tsWorkers.TabVisible := true
        else frmENPlanWorkItemEdit.tsWorkers.TabVisible := false;
      end;

    if ENPlanWorkObj.typeRef.code in [ENPLANWORKTYPE_SERVICES_FROM_SIDE, ENPLANWORKTYPE_SERVICES_FROM_SIDE_INVEST] then
      begin
        frmENPlanWorkItemEdit.inServices := false;
        inServices := false;
      end;

    try
      //frmENPlanWorkItemEdit.DisableControls([frmENLine04Edit.edtENSubstation04Name, frmENLine04Edit.spbENSubstation04]);
      frmENPlanWorkItemEdit.lblMeasure.Caption := 'Вимірювач : ' + sgENPlanWorkItem.Cells[9,sgENPlanWorkItem.Row] + ' /  Од.виміру : ' + sgENPlanWorkItem.Cells[10,sgENPlanWorkItem.Row];
      
      if frmENPlanWorkItemEdit.ShowModal= mrOk then
        begin
          //TempENLine04.save(ENLine04Obj);
          UpdateGrid(Sender);
        end;

        if  sgENPlanWorkItem.RowCount > selectedRow then
           sgENPlanWorkItem.Row := selectedRow
        else
           sgENPlanWorkItem.Row :=  sgENPlanWorkItem.RowCount-1;
    finally
      frmENPlanWorkItemEdit.Free;
      frmENPlanWorkItemEdit:=nil;
    end;
  end;
///--------------------------------------------------------------------------------

// ------------------------------
  if pcPlanWork.ActivePage = tsServicesItem then
  begin
    TempENPlanWorkItem := HTTPRIOENPlanWorkItem as ENPlanWorkItemControllerSoapPort;
    try
      ENPlanWorkItemObj := TempENPlanWorkItem.getObject(StrToInt(sgServicesItem.Cells[0,sgServicesItem.Row]));
    except
      on EConvertError do Exit;
    end;

    selectedRow := sgServicesItem.Row;
    frmENPlanWorkItemEdit:=TfrmENPlanWorkItemEdit.Create(Application, dsEdit);

    frmENPlanWorkItemEdit.isServicesProject := isServicesProject;

    if ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_SIZ then
      begin
        frmENPlanWorkItemEdit.isSiz := true;
        isSiz := true;
      end;

    if isTransport then
      begin
        frmENPlanWorkItemEdit.isTransport := true;
        // для грузов показываем материалы
        if ((ENPlanWorkItemObj.kartaRef.code = 500004873) or (ENPlanWorkItemObj.kartaRef.code = 500004877)) then
           frmENPlanWorkItemEdit.tsMaterials.TabVisible := true
        else frmENPlanWorkItemEdit.tsMaterials.TabVisible := false;
      end;

    if isTransport then
      begin
        frmENPlanWorkItemEdit.isTransport := true;
        // для перевозок показываем персонал
        if ((ENPlanWorkItemObj.kartaRef.code = 500004875) or (ENPlanWorkItemObj.kartaRef.code = 500004876)) then
           frmENPlanWorkItemEdit.tsWorkers.TabVisible := true
        else frmENPlanWorkItemEdit.tsWorkers.TabVisible := false;
      end;

    if ENPlanWorkObj.typeRef.code in [ENPLANWORKTYPE_SERVICES_FROM_SIDE, ENPLANWORKTYPE_SERVICES_FROM_SIDE_INVEST] then
      begin
        frmENPlanWorkItemEdit.inServices := true;
        inServices := true;
      end;

    try
      //frmENPlanWorkItemEdit.DisableControls([frmENLine04Edit.edtENSubstation04Name, frmENLine04Edit.spbENSubstation04]);
      ///// 09.09.2015 Вообще мимо!!!
      //frmENPlanWorkItemEdit.lblMeasure.Caption := 'Вимірювач : ' + sgServicesItem.Cells[8,sgServicesItem.Row] + ' /  Од.виміру : ' + sgENPlanWorkItem.Cells[10,sgENPlanWorkItem.Row];
      frmENPlanWorkItemEdit.lblMeasure.Caption := 'Вимірювач : ' + sgServicesItem.Cells[6,sgServicesItem.Row];
      /////

      if frmENPlanWorkItemEdit.ShowModal= mrOk then
        begin
          //TempENLine04.save(ENLine04Obj);
          UpdateGrid(Sender);
        end;

        if  sgServicesItem.RowCount > selectedRow then
           sgServicesItem.Row := selectedRow
        else
           sgServicesItem.Row := sgServicesItem.RowCount-1;
    finally
      frmENPlanWorkItemEdit.Free;
      frmENPlanWorkItemEdit:=nil;
    end;
  end;

///--------------------------------------------------------------------------------

  if (pcPlanWork.ActivePage = tsEstimateItems) then
  begin
    if sgENEstimateItem.Visible then
      grid := sgENEstimateItem
    else if sgENEstimateItemWithFin.Visible then
      grid := sgENEstimateItemWithFin
    else
      raise Exception.Create('NET-4061 Unknown ENEstimateItem grid!');

    TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;
    try
      //ENEstimateItemObj := TempENEstimateItem.getObject(StrToInt(sgENEstimateItem.Cells[0,sgENEstimateItem.Row]));
      ENEstimateItemObj := TempENEstimateItem.getObject(StrToInt(grid.Cells[0, grid.Row]));
    except
      on EConvertError do Exit;
    end;

    {
     if  not (Integer(sgENEstimateItem.Objects[0,sgENEstimateItem.Row]) in [ENESTIMATEITEMTYPE_MANUAL_BY_PLAN ]) then //такие редактировать из РАБОТЫ
     begin
       Application.MessageBox(PChar('Цей матеріaл змінюється в роботі !!!'), PChar('Внимание !'),MB_ICONQUESTION+MB_OK);
       exit;
     end;
    }

    frmENEstimateItemEdit:=TfrmENEstimateItemEdit.Create(Application, dsEdit);

    if ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_SIZ then
      begin
        frmENEstimateItemEdit.isSiz := true;
        isSiz := true;
      end;

    try
    //ENEstimateItemObj.planRef.code := ENPlanWorkObj.code;
      if frmENEstimateItemEdit.ShowModal= mrOk then
      begin
        //TempENEstimateItem.save(ENEstimateItemObj);
        UpdateGrid(Sender);
      end;
    finally
      frmENEstimateItemEdit.Free;
      frmENEstimateItemEdit:=nil;
    end;
  end;


///--------------------------------------------------------------------------------

  if (pcPlanWork.ActivePage = tsGSM) then
  begin
    if sgGSM.Visible then
      grid := sgGSM
    else if sgGSMWithFin.Visible then
      grid := sgGSMWithFin
    else
      raise Exception.Create('NET-4061 Unknown ENEstimateItem(GSM) grid!');
        
    TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;
    try
      //ENEstimateItemObj := TempENEstimateItem.getObject(StrToInt(sgGSM.Cells[0,sgGSM.Row]));
      ENEstimateItemObj := TempENEstimateItem.getObject(StrToInt(grid.Cells[0, grid.Row]));
    except
      on EConvertError do Exit;
    end;
{
     if  not (Integer(sgENEstimateItem.Objects[0,sgENEstimateItem.Row]) in [ENESTIMATEITEMTYPE_MANUAL_BY_PLAN ]) then //такие редактировать из РАБОТЫ
     begin
       Application.MessageBox(PChar('Цей матеріaл змінюється в роботі !!!'), PChar('Внимание !'),MB_ICONQUESTION+MB_OK);
       exit;
     end;
}

    frmENEstimateItemEdit:=TfrmENEstimateItemEdit.Create(Application, dsEdit);
    try
    //ENEstimateItemObj.planRef.code := ENPlanWorkObj.code;
      if frmENEstimateItemEdit.ShowModal= mrOk then
      begin
        //TempENEstimateItem.save(ENEstimateItemObj);
        UpdateGrid(Sender);
      end;
    finally
      frmENEstimateItemEdit.Free;
      frmENEstimateItemEdit:=nil;
    end;
  end;

  // --------------------------------------------------------

  if  (pcPlanWork.ActivePage = tsDismount) then
  begin
    TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;
    try
      ENEstimateItemObj := TempENEstimateItem.getObject(StrToInt(sgDismount.Cells[0,sgDismount.Row]));
    except
      on EConvertError do Exit;
    end;
{
     if  not (Integer(sgENEstimateItem.Objects[0,sgDismount.Row]) in [ENESTIMATEITEMTYPE_MANUAL_BY_PLAN ]) then //такие редактировать из РАБОТЫ
     begin
       Application.MessageBox(PChar('Цей матеріaл змінюється в роботі !!!'), PChar('Внимание !'),MB_ICONQUESTION+MB_OK);
       exit;
     end;
}
    frmENEstimateItemEdit:=TfrmENEstimateItemEdit.Create(Application, dsEdit);
    try
      if (transformatorsForRaw) then
      begin
        frmENEstimateItemEdit.transformatorsForRaw := True;
        DisableControls([frmENEstimateItemEdit.edtAccount, frmENEstimateItemEdit.edtPrice], False);
        DisableControls([frmENEstimateItemEdit.edtCountFact,
          frmENEstimateItemEdit.spbOSSelect, frmENEstimateItemEdit.spbNomenclatureClear,
          frmENEstimateItemEdit.btnLinkWriteOffMaterial, frmENEstimateItemEdit.btnClearLinkWriteOffMaterial], True);
        DenyBlankValues([frmENEstimateItemEdit.edtAccount, frmENEstimateItemEdit.edtPrice]);
      end;

      frmENEstimateItemEdit.ENPlanWorkCode := ENPlanWorkObj.code;

      if frmENEstimateItemEdit.ShowModal= mrOk then
      begin
        //TempENEstimateItem.save(ENEstimateItemObj);
        UpdateGrid(Sender);
      end;
    finally
      frmENEstimateItemEdit.Free;
      frmENEstimateItemEdit:=nil;
    end;
  end;

  //-------------------------------
  if  (pcPlanWork.ActivePage = tsRefinement) then
  begin
    TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;
    try
      ENEstimateItemObj := TempENEstimateItem.getObject(StrToInt(sgRefinement.Cells[0,sgRefinement.Row]));
    except
      on EConvertError do Exit;
    end;
{
     if  not (Integer(sgENEstimateItem.Objects[0,sgDismount.Row]) in [ENESTIMATEITEMTYPE_MANUAL_BY_PLAN ]) then //такие редактировать из РАБОТЫ
     begin
       Application.MessageBox(PChar('Цей матеріaл змінюється в роботі !!!'), PChar('Внимание !'),MB_ICONQUESTION+MB_OK);
       exit;
     end;
}
    frmENEstimateItemEdit:=TfrmENEstimateItemEdit.Create(Application, dsEdit);
    try
    //ENEstimateItemObj.planRef.code := ENPlanWorkObj.code;
      if frmENEstimateItemEdit.ShowModal= mrOk then
      begin
        //TempENEstimateItem.save(ENEstimateItemObj);
        UpdateGrid(Sender);
      end;
    finally
      frmENEstimateItemEdit.Free;
      frmENEstimateItemEdit:=nil;
    end;
  end;

  //-------------------------------
    if  (pcPlanWork.ActivePage = tsProduced) then
  begin
    TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;
    try
      ENEstimateItemObj := TempENEstimateItem.getObject(StrToInt(sgProduced.Cells[0,sgProduced.Row]));
    except
      on EConvertError do Exit;
    end;

    frmENEstimateItemEdit:=TfrmENEstimateItemEdit.Create(Application, dsEdit);
    try
    //ENEstimateItemObj.planRef.code := ENPlanWorkObj.code;
      if frmENEstimateItemEdit.ShowModal= mrOk then
      begin
        //TempENEstimateItem.save(ENEstimateItemObj);
        UpdateGrid(Sender);
      end;
    finally
      frmENEstimateItemEdit.Free;
      frmENEstimateItemEdit:=nil;
    end;
  end;

  // -------- start tsTransportRoute  --------
  if (pcPlanWork.ActivePage = tsTransportRoute) then
  begin
    TempENTransportRoute := HTTPRIOENTransportRoute as ENTransportRouteControllerSoapPort;
    try
      ENTransportRouteObj := TempENTransportRoute.getObject(StrToInt(sgENTransportRoute.Cells[0,sgENTransportRoute.Row]));
    except
      on EConvertError do Exit;
    end;

    frmENTransportRouteEdit := TfrmENTransportRouteEdit.Create(Application, dsEdit);

    try
      if frmENTransportRouteEdit.ShowModal= mrOk then
      begin
        UpdateGrid(Sender);
      end;
    finally
      frmENTransportRouteEdit.Free;
      frmENTransportRouteEdit := nil;
    end;
  end;
  // -------- end tsTransportRoute  --------


  // -------- start tsRQTransportInvoice  --------
  if (pcPlanWork.ActivePage = tsRQTransportInvoice) then
  begin
    TempRQTransportInvoice := HTTPRIORQTransportInvoice as RQTransportInvoiceControllerSoapPort;
    try
      RQTransportInvoiceObj := TempRQTransportInvoice.getObject(StrToInt(sgRQTransportInvoice.Cells[0,sgRQTransportInvoice.Row]));
    except
      on EConvertError do Exit;
    end;

    frmRQTransportInvoiceEdit := TfrmRQTransportInvoiceEdit.Create(Application, dsEdit);

    try
      if frmRQTransportInvoiceEdit.ShowModal= mrOk then
      begin
        UpdateGrid(Sender);
      end;
    finally
      frmRQTransportInvoiceEdit.Free;
      frmRQTransportInvoiceEdit := nil;
    end;
  end;
  // -------- end tsRQTransportInvoice  --------

  ///   tsCustomerMaterials

  if (pcPlanWork.ActivePage = tsCustomerMaterials) then
  begin
    TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;
    try
      ENEstimateItemObj := TempENEstimateItem.getObject(StrToInt(sgCustomerMaterials.Cells[0,sgCustomerMaterials.Row]));
    except
      on EConvertError do Exit;
    end;

    frmENEstimateItemEdit:=TfrmENEstimateItemEdit.Create(Application, dsEdit);

    if ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_SIZ then
      begin
        frmENEstimateItemEdit.isSiz := true;
        isSiz := true;
      end;

    try
    //ENEstimateItemObj.planRef.code := ENPlanWorkObj.code;
      if frmENEstimateItemEdit.ShowModal= mrOk then
      begin
        //TempENEstimateItem.save(ENEstimateItemObj);
        UpdateGrid(Sender);
      end;
    finally
      frmENEstimateItemEdit.Free;
      frmENEstimateItemEdit:=nil;
    end;
  end;

  ///   end tsCustomerMaterials


  // -------- start tsFINExecutor2Plan  --------
  if (pcPlanWork.ActivePage = tsFINExecutor2Plan) then
  begin
    TempFINExecutor2Plan := HTTPRIOFINExecutor2Plan as FINExecutor2PlanControllerSoapPort;
    try
      FINExecutor2PlanObj := TempFINExecutor2Plan.getObject(StrToInt(sgFINExecutor2Plan.Cells[0,sgFINExecutor2Plan.Row]));
    except
      on EConvertError do Exit;
    end;

    // Нужно сохранить значение, потому что оно сбросится при изменении исполнителя на форме редактирования связки
    finExecutorCode := FINExecutor2PlanObj.finExecutor.code;

    frmFINExecutor2PlanEdit := TfrmFINExecutor2PlanEdit.Create(Application, dsEdit);
    try
      if frmFINExecutor2PlanEdit.ShowModal= mrOk then
        begin
          UpdateGrid(Sender);

          // Нужно перечитать исполнителя на плане, если изменяли основного исполнителя на вкладке "Виконавці плану"
          if FINExecutor2PlanObj.finExecutorTypeRef.code = FINEXECUTOR_TYPE_PRIMARY then
          begin
            TempFINExecutor := HTTPRIOFINExecutor as FINExecutorControllerSoapPort;
            ENPlanWorkObj.finExecutor := TempFINExecutor.getObject(finExecutorCode);
            edtFINExecutorName.Text := ENPlanWorkObj.finExecutor.name;
          end;
        end;
    finally
      frmFINExecutor2PlanEdit.Free;
      frmFINExecutor2PlanEdit:=nil;
    end;
  end;
  // -------- end tsFINExecutor2Plan  --------

  // -------- start tsCompletionPlan  --------
  if (pcPlanWork.ActivePage = tsCompletionPlan) then
  begin
    try
      planCode := StrToInt(sgCompletionPlan.Cells[0,sgCompletionPlan.Row]);
    except
      on EConvertError do Exit;
    end;

    tPlan := DMReports.getPlanByCode(planCode);

    if tPlan = nil then
    begin
      Exit;
    end;

    TempENPlanWork := HTTPRIOENPlanWork as ENPlanWorkControllerSoapPort;

    if (tPlan.status.code = ENPLANWORKSTATUS_PRECONFIRMED)  then
    begin
      try
          TempENPlanWork.editPreConfirm(tPlan.code);
      except
          Application.MessageBox(PChar('Цей план можуть коригувати тільки у ХОЕ !'), PChar('Увага'), MB_ICONWARNING);
          exit;
      end;
    end;

    if not (tPlan.status.code in [ENPLANWORKSTATUS_GOOD, ENPLANWORKSTATUS_INCORRECTION, ENPLANWORKSTATUS_PRECONFIRMED])

        //and
        //not ( tPlan.kind.code in [ENPLANWORKKIND_CURRENT ])
    then
    begin
        Application.MessageBox(PChar('План затверджений !'), PChar('Увага'), MB_ICONWARNING);
        exit;
    end;

    selectedRow := sgCompletionPlan.Row;
    frmENPlanWorkEdit := TfrmENPlanWorkEdit.Create(Application, dsEdit);

    try

      if (tPlan.typeRef.code = ENPLANWORKTYPE_TRANSPORT) and (tPlan.stateRef.code = ENPLANWORKSTATE_GSM) then
        frmENPlanWorkEdit.isTransport := true;

      if (tPlan.typeRef.code = ENPLANWORKTYPE_SIZ) then
        frmENPlanWorkEdit.isSiz := true;

      try
        frmENPlanWorkEdit.ENPlanWorkObj := TempENPlanWork.getObject(StrToInt(sgCompletionPlan.Cells[0,sgCompletionPlan.Row]));
      except
        on EConvertError do Exit;
      end;

      if frmENPlanWorkEdit.ShowModal= mrOk then
        begin
          //TempENPlanWork.save(ENPlanWorkObj);
          //UpdateGrid(Sender);
        end;

    finally
      frmENPlanWorkEdit.Free;
      frmENPlanWorkEdit:=nil;
    end;

    UpdateGrid(Sender);
  end;
  // -------- end tsCompletionPlan  --------

  // -------- start tsCCDamageLog  --------
  if (pcPlanWork.ActivePage = tsCCDamageLog)  then
  begin

    TempCCDamageLog:=HTTPRIOCCDamageLog as CCDamageLogControllerSoapPort;

    try
      damCode := StrToInt(sgENPlanWork2CCDamageLog.Cells[1, sgENPlanWork2CCDamageLog.row] );
    except
      on EConvertError do Exit;
    end;

    frmCCDamageLogEdit:=TfrmCCDamageLogEdit.Create(Application, dsEdit);
    frmCCDamageLogEdit.CCDamageLogObj:= TempCCDamageLog.getObject(damCode);
    try
      frmCCDamageLogEdit.ShowModal;
      UpdateGrid(Sender);
    finally
      frmCCDamageLogEdit.Free;
      frmCCDamageLogEdit:=nil;
    end;

  end;
  // -------- end tsCCDamageLog  --------

  if pcPlanWork.ActivePage = tsTransports then
  begin

    TempENTransportItem := HTTPRIOENTransportItem as ENTransportItemControllerSoapPort;
    try
      ENTransportItemObj := TempENTransportItem.getObject(StrToInt(sgENTransportItem.Cells[0, sgENTransportItem.Row]));
    except
      on EConvertError do Exit;
    end;

    frmENTransportItemEdit := TfrmENTransportItemEdit.Create(Application, dsEdit);
    try
      //frmENEstimateItemEdit.DisableControls([frmENLine04Edit.edtENSubstation04Name, frmENLine04Edit.spbENSubstation04]);
      if frmENTransportItemEdit.ShowModal= mrOk then
      begin
      UpdateGrid(Sender);
      end;
    finally
      frmENTransportItemEdit.Free;
      frmENTransportItemEdit := nil;
    end;
  end;

end;

procedure TfrmENPlanWorkEdit.actEditprojectExecute(Sender: TObject);
var
  TempENPlanProject: ENPlanProjectControllerSoapPort;
begin
 { TempENPlanProject := HTTPRIOENPlanProject as ENPlanProjectControllerSoapPort;
  try
    ENPlanProjectObj := TempENPlanProject.getObject(StrToInt(sgENPlanProject.Cells[0,sgENPlanProject.Row]));
  except
    on EConvertError do Exit;
  end;

  selectedRow := sgENPlanProject.Row;
  frmENPlanProjectEdit:=TfrmENPlanProjectEdit.Create(Application, dsEdit);

  try
    if frmENPlanProjectEdit.ShowModal= mrOk then
      begin
        //TempENPlanProject.save(ENPlanProjectObj);
        UpdateGrid(Sender);
      end;
  finally
    frmENPlanProjectEdit.Free;
    frmENPlanProjectEdit:=nil;
  end;

  if sgENPlanProject.RowCount > selectedRow then
    sgENPlanProject.Row := selectedRow
  else
    sgENPlanProject.Row := sgENPlanProject.RowCount - 1;  }

end;

procedure TfrmENPlanWorkEdit.actEditXqtnPercentExecute(Sender: TObject);
var
  TempENPlanXqtnHistory: ENPlanXqtnHistoryControllerSoapPort;
begin
  TempENPlanXqtnHistory := HTTPRIOENPlanXqtnHistory as ENPlanXqtnHistoryControllerSoapPort;
  try
    ENPlanXqtnHistoryObj := TempENPlanXqtnHistory.getObject(StrToInt(sgENPlanXqtnHistory.Cells[0,sgENPlanXqtnHistory.Row]));
  except
    on EConvertError do Exit;
  end;

  selectedRow := sgENPlanXqtnHistory.Row;
  frmENPlanXqtnHistoryEdit:=TfrmENPlanXqtnHistoryEdit.Create(Application, dsEdit);

  try
    if frmENPlanXqtnHistoryEdit.ShowModal= mrOk then
      begin
        actUpdateXqtnPercentExecute(Sender);
      end;
  finally
    frmENPlanXqtnHistoryEdit.Free;
    frmENPlanXqtnHistoryEdit:=nil;
  end;

  if sgENPlanXqtnHistory.RowCount > selectedRow then
    sgENPlanXqtnHistory.Row := selectedRow
  else
    sgENPlanXqtnHistory.Row := sgENPlanXqtnHistory.RowCount - 1;

end;

procedure TfrmENPlanWorkEdit.actViewExecute(Sender: TObject);
Var planCode : Integer;
    tPlan : ENPlanWork;
    damCode: Integer;
    TempENPlanWork : ENPlanWorkControllerSoapPort;
    TempENPlanWorkItem: ENPlanWorkItemControllerSoapPort;
    TempENEstimateItem: ENEstimateItemControllerSoapPort;
    TempENTransportItem: ENTransportItemControllerSoapPort;
    TempENHumenItem: ENHumenItemControllerSoapPort;
    TempRQFKOrder: RQFKOrderControllerSoapPort;
    TempENTransportRoute : ENTransportRouteControllerSoapPort;
    TempRQTransportInvoice : RQTransportInvoiceControllerSoapPort;
    TempFINExecutor2Plan : FINExecutor2PlanControllerSoapPort;
    TempCCDamageLog : CCDamageLogControllerSoapPort;

    grid: TAdvStringGrid;

    frmDFDocManagement: TfrmDocumentManagementShow;
    dfDocCode: Integer;
    dfDocFlter: DFDocFilter;
begin

  isSiz := false;

  if pcPlanWork.ActivePage = tsPlanWorkItems then
  begin

    TempENPlanWorkItem := HTTPRIOENPlanWorkItem as ENPlanWorkItemControllerSoapPort;
    try
      ENPlanWorkItemObj := TempENPlanWorkItem.getObject(StrToInt(sgENPlanWorkItem.Cells[0,sgENPlanWorkItem.Row]));
    except
      on EConvertError do Exit;
    end;

    frmENPlanWorkItemEdit:=TfrmENPlanWorkItemEdit.Create(Application, dsView);
          // 30.05.2012 Присваивание типа акта для формы frmENPlanWorkItemEdit
      frmENPlanWorkItemEdit.planWorkStateCode := ENPlanWorkObj.stateRef.code;
    if isTransport then frmENPlanWorkItemEdit.isTransport := true;
    if ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_SIZ then frmENPlanWorkItemEdit.isSiz := true;
    if ENPlanWorkObj.typeRef.code in [ENPLANWORKTYPE_SERVICES_FROM_SIDE, ENPLANWORKTYPE_SERVICES_FROM_SIDE_INVEST]
      then frmENPlanWorkItemEdit.inServices := false;

    try
      //frmENPlanWorkItemEdit.DisableControls([frmENLine04Edit.edtENSubstation04Name, frmENLine04Edit.spbENSubstation04]);
      frmENPlanWorkItemEdit.lblMeasure.Caption := 'Вимірювач : ' + sgENPlanWorkItem.Cells[9,sgENPlanWorkItem.Row] + ' /  Од.виміру : ' + sgENPlanWorkItem.Cells[10,sgENPlanWorkItem.Row];
      if frmENPlanWorkItemEdit.ShowModal= mrOk then
      begin
        //TempENLine04.save(ENLine04Obj);
        //UpdateGrid(Sender);
      end;
    finally
      frmENPlanWorkItemEdit.Free;
      frmENPlanWorkItemEdit:=nil;
    end;

  end;

  if pcPlanWork.ActivePage = tsServicesItem then
  begin

    TempENPlanWorkItem := HTTPRIOENPlanWorkItem as ENPlanWorkItemControllerSoapPort;
    try
      ENPlanWorkItemObj := TempENPlanWorkItem.getObject(StrToInt(sgServicesItem.Cells[0,sgServicesItem.Row]));
    except
      on EConvertError do Exit;
    end;

    frmENPlanWorkItemEdit:=TfrmENPlanWorkItemEdit.Create(Application, dsView);
    // 30.05.2012 Присваивание типа акта для формы frmENPlanWorkItemEdit
      frmENPlanWorkItemEdit.planWorkStateCode := ENPlanWorkObj.stateRef.code;

    if isTransport then frmENPlanWorkItemEdit.isTransport := true;
    if ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_SIZ then frmENPlanWorkItemEdit.isSiz := true;
    if ENPlanWorkObj.typeRef.code in [ENPLANWORKTYPE_SERVICES_FROM_SIDE, ENPLANWORKTYPE_SERVICES_FROM_SIDE_INVEST]
      then frmENPlanWorkItemEdit.inServices := true;

    try
      //frmENPlanWorkItemEdit.DisableControls([frmENLine04Edit.edtENSubstation04Name, frmENLine04Edit.spbENSubstation04]);
      ///// 09.09.2015 Вообще мимо!!!
      //frmENPlanWorkItemEdit.lblMeasure.Caption := 'Вимірювач : ' + sgServicesItem.Cells[8,sgServicesItem.Row] + ' /  Од.виміру : ' + sgServicesItem.Cells[9,sgServicesItem.Row];
      frmENPlanWorkItemEdit.lblMeasure.Caption := 'Вимірювач : ' + sgServicesItem.Cells[6,sgServicesItem.Row];
      /////

      if frmENPlanWorkItemEdit.ShowModal= mrOk then
      begin
        //TempENLine04.save(ENLine04Obj);
        //UpdateGrid(Sender);
      end;
    finally
      frmENPlanWorkItemEdit.Free;
      frmENPlanWorkItemEdit:=nil;
    end;

  end;

  if (pcPlanWork.ActivePage = tsEstimateItems)  then
  begin
    if sgENEstimateItem.Visible then
      grid := sgENEstimateItem
    else if sgENEstimateItemWithFin.Visible then
      grid := sgENEstimateItemWithFin
    else
      raise Exception.Create('NET-4061 Unknown ENEstimateItem grid!');

    TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;
    try
      //ENEstimateItemObj := TempENEstimateItem.getObject(StrToInt(sgENEstimateItem.Cells[0, sgENEstimateItem.Row]));
      ENEstimateItemObj := TempENEstimateItem.getObject(StrToInt(grid.Cells[0, grid.Row]));
    except
      on EConvertError do Exit;
    end;

    frmENEstimateItemEdit := TfrmENEstimateItemEdit.Create(Application, dsView);

    if ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_SIZ then
      begin
        frmENEstimateItemEdit.isSiz := true;
        isSiz := true;
      end;

    try
      //frmENEstimateItemEdit.DisableControls([frmENLine04Edit.edtENSubstation04Name, frmENLine04Edit.spbENSubstation04]);
      if frmENEstimateItemEdit.ShowModal= mrOk then
      begin
        //TempENLine04.save(ENLine04Obj);
        //UpdateGrid(Sender);
      end;
    finally
      frmENEstimateItemEdit.Free;
      frmENEstimateItemEdit := nil;
    end;
  end;


  if (pcPlanWork.ActivePage = tsGSM)  then
  begin
    if sgGSM.Visible then
      grid := sgGSM
    else if sgGSMWithFin.Visible then
      grid := sgGSMWithFin
    else
      raise Exception.Create('NET-4061 Unknown ENEstimateItem(GSM) grid!');

    TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;
    try
      //ENEstimateItemObj := TempENEstimateItem.getObject(StrToInt(sgGSM.Cells[0, sgGSM.Row]));
      ENEstimateItemObj := TempENEstimateItem.getObject(StrToInt(grid.Cells[0, grid.Row]));
    except
      on EConvertError do Exit;
    end;

    frmENEstimateItemEdit := TfrmENEstimateItemEdit.Create(Application, dsView);
    try
      //frmENEstimateItemEdit.DisableControls([frmENLine04Edit.edtENSubstation04Name, frmENLine04Edit.spbENSubstation04]);
      if frmENEstimateItemEdit.ShowModal= mrOk then
      begin
        //TempENLine04.save(ENLine04Obj);
        //UpdateGrid(Sender);
      end;
    finally
      frmENEstimateItemEdit.Free;
      frmENEstimateItemEdit := nil;
    end;
  end;


  if (pcPlanWork.ActivePage = tsDismount) then
  begin

    TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;
    try
      ENEstimateItemObj := TempENEstimateItem.getObject(StrToInt(sgDismount.Cells[0, sgDismount.Row]));

    except
      on EConvertError do Exit;
    end;

    frmENEstimateItemEdit := TfrmENEstimateItemEdit.Create(Application, dsView);
    try
      //frmENEstimateItemEdit.DisableControls([frmENLine04Edit.edtENSubstation04Name, frmENLine04Edit.spbENSubstation04]);
      frmENEstimateItemEdit.ENPlanWorkCode := ENPlanWorkObj.code;
      if frmENEstimateItemEdit.ShowModal= mrOk then
      begin
        //TempENLine04.save(ENLine04Obj);
        //UpdateGrid(Sender);
      end;
    finally
      frmENEstimateItemEdit.Free;
      frmENEstimateItemEdit := nil;
    end;
  end;

    if (pcPlanWork.ActivePage = tsProduced) then
  begin

    TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;
    try
      ENEstimateItemObj := TempENEstimateItem.getObject(StrToInt(sgProduced.Cells[0, sgProduced.Row]));

    except
      on EConvertError do Exit;
    end;

    frmENEstimateItemEdit := TfrmENEstimateItemEdit.Create(Application, dsView);
    try
      if frmENEstimateItemEdit.ShowModal= mrOk then
      begin
      end;
    finally
      frmENEstimateItemEdit.Free;
      frmENEstimateItemEdit := nil;
    end;
  end;

  if pcPlanWork.ActivePage = tsHumens then
  begin

    TempENHumenItem := HTTPRIOENHumenItem as ENHumenItemControllerSoapPort;
    try
      ENHumenItemObj := TempENHumenItem.getObject(StrToInt(sgENHumenItem.Cells[0, sgENHumenItem.Row]));
    except
      on EConvertError do Exit;
    end;

    frmENHumenItemEdit := TfrmENHumenItemEdit.Create(Application, dsView);
    try
      //frmENEstimateItemEdit.DisableControls([frmENLine04Edit.edtENSubstation04Name, frmENLine04Edit.spbENSubstation04]);
      if frmENHumenItemEdit.ShowModal= mrOk then
      begin
        //TempENLine04.save(ENLine04Obj);
        //UpdateGrid(Sender);
      end;
    finally
      frmENHumenItemEdit.Free;
      frmENHumenItemEdit := nil;
    end;
  end;

  if pcPlanWork.ActivePage = tsTransports then
  begin

    TempENTransportItem := HTTPRIOENTransportItem as ENTransportItemControllerSoapPort;
    try
      ENTransportItemObj := TempENTransportItem.getObject(StrToInt(sgENTransportItem.Cells[0, sgENTransportItem.Row]));
    except
      on EConvertError do Exit;
    end;

    frmENTransportItemEdit := TfrmENTransportItemEdit.Create(Application, dsView);
    try
      //frmENEstimateItemEdit.DisableControls([frmENLine04Edit.edtENSubstation04Name, frmENLine04Edit.spbENSubstation04]);
      if frmENTransportItemEdit.ShowModal= mrOk then
      begin
        //TempENLine04.save(ENLine04Obj);
        //UpdateGrid(Sender);
      end;
    finally
      frmENTransportItemEdit.Free;
      frmENTransportItemEdit := nil;
    end;
  end;

  //-------------------------------

  if (pcPlanWork.ActivePage = tsRefinement) then
  begin
    TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;
    try
      ENEstimateItemObj := TempENEstimateItem.getObject(StrToInt(sgRefinement.Cells[0,sgRefinement.Row]));
    except
      on EConvertError do Exit;
    end;

    {
     if  not (Integer(sgENEstimateItem.Objects[0,sgENEstimateItem.Row]) in [ENESTIMATEITEMTYPE_MANUAL_BY_PLAN ]) then //такие редактировать из РАБОТЫ
     begin
       Application.MessageBox(PChar('Цей матеріaл змінюється в роботі !!!'), PChar('Внимание !'),MB_ICONQUESTION+MB_OK);
       exit;
     end;
    }

    frmENEstimateItemEdit:=TfrmENEstimateItemEdit.Create(Application, dsView);

    try
    //ENEstimateItemObj.planRef.code := ENPlanWorkObj.code;
      if frmENEstimateItemEdit.ShowModal= mrOk then
      begin
        //TempENEstimateItem.save(ENEstimateItemObj);
        UpdateGrid(Sender);
      end;
    finally
      frmENEstimateItemEdit.Free;
      frmENEstimateItemEdit:=nil;
    end;
  end;

  if pcPlanWork.ActivePage = tsRQFKOrder then
  begin
    TempRQFKOrder := HTTPRIORQFKOrder as RQFKOrderControllerSoapPort;
    frmRQFKOrderEdit := TfrmRQFKOrderEdit.Create(Application, dsView);

    try
      try
        frmRQFKOrderEdit.RQFKOrderObj := TempRQFKOrder.getObject(StrToInt(sgRQFKOrder.Cells[0,sgRQFKOrder.Row]));
      except
        on EConvertError do Exit;
      end;

      frmRQFKOrderEdit.ShowModal;
    finally
      frmRQFKOrderEdit.Free;
      frmRQFKOrderEdit := nil;
    end;
  end;

  if pcPlanWork.ActivePage = tsActsServicesFS then
  begin
    TempRQFKOrder := HTTPRIORQFKOrder as RQFKOrderControllerSoapPort;
    frmRQFKOrderEdit := TfrmRQFKOrderEdit.Create(Application, dsView);

    try
      try
        frmRQFKOrderEdit.RQFKOrderObj := TempRQFKOrder.getObject(StrToInt(sgActsServicesFS.Cells[0,sgActsServicesFS.Row]));
      except
        on EConvertError do Exit;
      end;

      frmRQFKOrderEdit.ShowModal;
    finally
      frmRQFKOrderEdit.Free;
      frmRQFKOrderEdit := nil;
    end;
  end;

  // -------- start tsTransportRoute  --------
  if (pcPlanWork.ActivePage = tsTransportRoute) then
  begin
    TempENTransportRoute := HTTPRIOENTransportRoute as ENTransportRouteControllerSoapPort;
    try
      ENTransportRouteObj := TempENTransportRoute.getObject(StrToInt(sgENTransportRoute.Cells[0,sgENTransportRoute.Row]));
    except
      on EConvertError do Exit;
    end;

    frmENTransportRouteEdit := TfrmENTransportRouteEdit.Create(Application, dsView);
    try
      frmENTransportRouteEdit.ShowModal;
    finally
      frmENTransportRouteEdit.Free;
      frmENTransportRouteEdit := nil;
    end;
  end;
  // -------- end tsTransportRoute  --------

  // -------- start tsRQTransportInvoice  --------
  if (pcPlanWork.ActivePage = tsRQTransportInvoice) then
  begin
    TempRQTransportInvoice := HTTPRIORQTransportInvoice as RQTransportInvoiceControllerSoapPort;
    try
       RQTransportInvoiceObj := TempRQTransportInvoice.getObject(StrToInt(sgRQTransportInvoice.Cells[0,sgRQTransportInvoice.Row]));
     except
     on EConvertError do Exit;
    end;

    frmRQTransportInvoiceEdit:=TfrmRQTransportInvoiceEdit.Create(Application, dsView);
    try
      frmRQTransportInvoiceEdit.ShowModal;
    finally
      frmRQTransportInvoiceEdit.Free;
      frmRQTransportInvoiceEdit := nil;
    end;
  end;
  // -------- end tsRQTransportInvoice  --------

  // start tsCustomerMaterials

  if (pcPlanWork.ActivePage = tsCustomerMaterials)  then
  begin

    TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;
    try
      ENEstimateItemObj := TempENEstimateItem.getObject(StrToInt(sgCustomerMaterials.Cells[0, sgCustomerMaterials.Row]));

    except
      on EConvertError do Exit;
    end;

    frmENEstimateItemEdit := TfrmENEstimateItemEdit.Create(Application, dsView);

    if ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_SIZ then
      begin
        frmENEstimateItemEdit.isSiz := true;
        isSiz := true;
      end;

    try
      //frmENEstimateItemEdit.DisableControls([frmENLine04Edit.edtENSubstation04Name, frmENLine04Edit.spbENSubstation04]);
      if frmENEstimateItemEdit.ShowModal= mrOk then
      begin
        //TempENLine04.save(ENLine04Obj);
        //UpdateGrid(Sender);
      end;
    finally
      frmENEstimateItemEdit.Free;
      frmENEstimateItemEdit := nil;
    end;
  end;

  // end tsCustomerMaterials

  // -------- start tsFINExecutor2Plan  --------
  if (pcPlanWork.ActivePage = tsFINExecutor2Plan) then
  begin
    TempFINExecutor2Plan := HTTPRIOFINExecutor2Plan as FINExecutor2PlanControllerSoapPort;
    try
      FINExecutor2PlanObj := TempFINExecutor2Plan.getObject(StrToInt(sgFINExecutor2Plan.Cells[0,sgFINExecutor2Plan.Row]));
    except
      on EConvertError do Exit;
    end;

    frmFINExecutor2PlanEdit := TfrmFINExecutor2PlanEdit.Create(Application, dsView);
    try
      frmFINExecutor2PlanEdit.ShowModal;
    finally
      frmFINExecutor2PlanEdit.Free;
      frmFINExecutor2PlanEdit:=nil;
    end;
  end;
  // -------- end tsFINExecutor2Plan  --------

  // -------- start tsCompletionPlan  --------
  if (pcPlanWork.ActivePage = tsCompletionPlan) then
  begin
    try
      planCode := StrToInt(sgCompletionPlan.Cells[0, sgCompletionPlan.row] );
     except
      on EConvertError do Exit;
    end;

    tPlan := DMReports.getPlanByCode( planCode );

    if tPlan = nil then
    begin
      exit;
    end;

    TempENPlanWork := HTTPRIOENPlanWork as ENPlanWorkControllerSoapPort;
    selectedRow := sgCompletionPlan.Row;
    frmENPlanWorkEdit := TfrmENPlanWorkEdit.Create(Application, dsView);

    if (tPlan.typeRef.code = ENPLANWORKTYPE_TRANSPORT) and (tPlan.stateRef.code = ENPLANWORKSTATE_GSM) then
    frmENPlanWorkEdit.isTransport := true;

    if (tPlan.typeRef.code = ENPLANWORKTYPE_SIZ) then
    frmENPlanWorkEdit.isSiz := true;

    try

    try
      frmENPlanWorkEdit.ENPlanWorkObj := TempENPlanWork.getObject( planCode );
    except
      on EConvertError do Exit;
    end;

    frmENPlanWorkEdit.ShowModal;

    finally
      frmENPlanWorkEdit.Free;
      frmENPlanWorkEdit:=nil;
    end;

    UpdateGrid(Sender);
  end;
  // -------- end tsCompletionPlan  --------

  // -------- start tsCCDamageLog  --------
  if (pcPlanWork.ActivePage = tsCCDamageLog)  then
  begin

    TempCCDamageLog:=HTTPRIOCCDamageLog as CCDamageLogControllerSoapPort;

    try
      damCode := StrToInt(sgENPlanWork2CCDamageLog.Cells[1, sgENPlanWork2CCDamageLog.row] );
    except
      on EConvertError do Exit;
    end;

    frmCCDamageLogEdit:=TfrmCCDamageLogEdit.Create(Application, dsView);
    frmCCDamageLogEdit.CCDamageLogObj:= TempCCDamageLog.getObject(damCode);
    try
      frmCCDamageLogEdit.ShowModal;
    finally
      frmCCDamageLogEdit.Free;
      frmCCDamageLogEdit:=nil;
    end;

  end;
  // -------- end tsCCDamageLog  --------

  // -------- start tsOwnProduction  --------
  if (pcPlanWork.ActivePage = tsOwnProduction) then
  begin
    TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;
    try
      ENEstimateItemObj := TempENEstimateItem.getObject(StrToInt(sgOwnProduction.Cells[0, sgOwnProduction.Row]));
    except
      on EConvertError do Exit;
    end;

    frmENEstimateItemEdit := TfrmENEstimateItemEdit.Create(Application, dsView);
    try
      if frmENEstimateItemEdit.ShowModal= mrOk then
      begin
      end;
    finally
      frmENEstimateItemEdit.Free;
      frmENEstimateItemEdit := nil;
    end;
  end;
  // -------- end tsOwnProduction  --------

  // -------- start tsDFDocs --------
  if (pcPlanWork.ActivePage = tsDFDocs) then
  begin
    try
      dfDocCode := StrToInt(sgDFDocs.Cells[0, sgDFDocs.Row]);
    except
      on EConvertError do Exit;
    end;

    if dfDocCode <= 0 then Exit;

    dfDocFlter := DFDocFilter.Create;
    SetNullIntProps(dfDocFlter);
    SetNullXSProps(dfDocFlter);
    dfDocFlter.code := dfDocCode;

    frmDFDocManagement := TfrmDocumentManagementShow.Create(Application, fmNormal);
    frmDFDocManagement.generalSearchFilter := dfDocFlter;
    Application.Tag := ENConsts.CONFIG_ENERGYWORKFLOW_CLIENT_VERSION;
    try
      frmDFDocManagement.ShowModal;
    finally
      Application.Tag := ENConsts.CONFIG_ENERGYNET_CLIENT_VERSION;
      frmDFDocManagement.Free;
      frmDFDocManagement := nil;
    end;
  end;
  // -------- end tsDFDocs --------
end;

procedure TfrmENPlanWorkEdit.actViewFuelSheetVolumesExecute(Sender: TObject);
var TempENFuelSheetVolumes: ENFuelSheetVolumesControllerSoapPort;
begin
  TempENFuelSheetVolumes := HTTPRIOENFuelSheetVolumes as ENFuelSheetVolumesControllerSoapPort;
  try
    ENFuelSheetVolumesObj := TempENFuelSheetVolumes.getObject(StrToInt(sgENFuelSheetVolumes.Cells[0, sgENFuelSheetVolumes.Row]));
  except
    on EConvertError do Exit;
  end;

  frmENFuelSheetVolumesEdit := TfrmENFuelSheetVolumesEdit.Create(Application, dsView);
  try
    frmENFuelSheetVolumesEdit.ShowModal;
  finally
    frmENFuelSheetVolumesEdit.Free;
    frmENFuelSheetVolumesEdit := nil;
  end;
end;

procedure TfrmENPlanWorkEdit.actViewPlanWithOwnProductionExecute(
  Sender: TObject);
var
  frmViewPlan: TfrmENPlanWorkEdit;
  TempENPlanWork: ENPlanWorkControllerSoapPort;
  planCode: Integer;
begin
  planCode := Integer(sgOwnProduction.Objects[0, sgOwnProduction.Row]);

  if planCode <= 0 then Exit;

  frmViewPlan := TfrmENPlanWorkEdit.Create(Application, dsView);
  try
    TempENPlanWork := HTTPRIOENPlanWork as ENPlanWorkControllerSoapPort;
    frmViewPlan.ENPlanWorkObj := TempENPlanWork.getObject(planCode);

    //frmViewPlan.Caption := 'Пов''язаний план';
    frmViewPlan.ShowModal;
  finally
    frmViewPlan.Free;
    frmViewPlan := nil;
  end;
end;

procedure TfrmENPlanWorkEdit.actViewprojectExecute(Sender: TObject);
var
  TempENPlanProject: ENPlanProjectControllerSoapPort;
begin
  TempENPlanProject := HTTPRIOENPlanProject as ENPlanProjectControllerSoapPort;
  try
    ENPlanProjectObj := TempENPlanProject.getObject(StrToInt(sgENPlanProject.Cells[0,sgENPlanProject.Row]));
  except
    on EConvertError do Exit;
  end;

  selectedRow := sgENPlanProject.Row;
  frmENPlanProjectEdit:=TfrmENPlanProjectEdit.Create(Application, dsView);

  try
    frmENPlanProjectEdit.ShowModal;
  finally
    frmENPlanProjectEdit.Free;
    frmENPlanProjectEdit:=nil;
  end;

  if sgENPlanProject.RowCount > selectedRow then
    sgENPlanProject.Row := selectedRow
  else
    sgENPlanProject.Row := sgENPlanProject.RowCount - 1;

end;

procedure TfrmENPlanWorkEdit.actUpdateExecute(Sender: TObject);
begin
  inherited;
  //selectedRow := sgENPlanWorkItem.Row;
  UpdateGrid(Sender);
  {
  if  sgENPlanWorkItem.RowCount > selectedRow then
     sgENPlanWorkItem.Row := selectedRow
  else
     sgENPlanWorkItem.Row :=  sgENPlanWorkItem.RowCount-1;
  }
end;

procedure TfrmENPlanWorkEdit.actUpdateprojectExecute(Sender: TObject);
var
  TempENPlanProject: ENPlanProjectControllerSoapPort;
  TempENPlanWork : ENPlanWorkControllerSoapPort;
  i , k , l : Integer;
  ENPlanProjectList: ENPlanProjectShortList;
  ENPlanProjectFilt: ENPlanProjectFilter;
  LastCount , LastRow , ColCount, codeMonthPlan : integer;
begin
  LastRow:= 1;

  for k:=1 to sgENPlanProject.RowCount-1 do
   for l:=0 to sgENPlanProject.ColCount-1 do
     sgENPlanProject.Cells[l,k]:='';

  SetGridHeaders(ENPlanProjectHeaders, sgENPlanProject.ColumnHeaders);

  TempENPlanProject :=  HTTPRIOENPlanProject as ENPlanProjectControllerSoapPort;


     ENPlanProjectFilt := ENPlanProjectFilter.Create;
     SetNullIntProps(ENPlanProjectFilt);
     SetNullXSProps(ENPlanProjectFilt);
     TempENPlanWork := HTTPRIOENPlanWork as ENPlanWorkControllerSoapPort;
     codeMonthPlan := TempENPlanWork.getMonthPlanCodeByAnyPlanCode(ENPlanWorkObj.code, true);

  ENPlanProjectFilt.planRef := ENPlanWorkRef.Create;
  ENPlanProjectFilt.planRef.code := codeMonthPlan; //ENPlanWorkObj.code;

  ENPlanProjectList := TempENPlanProject.getScrollableFilteredList(ENPlanProjectFilt,0,100);
  LastCount:=High(ENPlanProjectList.list);

  if LastCount > -1 then
     sgENPlanProject.RowCount:=LastCount+2
  else
     sgENPlanProject.RowCount:=2;

   with sgENPlanProject do
    for i:=0 to LastCount do
      begin
        Application.ProcessMessages;
        if ENPlanProjectList.list[i].code <> Low(Integer) then
        Cells[0,i+1] := IntToStr(ENPlanProjectList.list[i].code)
        else
        Cells[0,i+1] := '';
        Cells[1,i+1] := ENPlanProjectList.list[i].projectCipher;
        Cells[2,i+1] := ENPlanProjectList.list[i].projectName;
        Cells[3,i+1] := ENPlanProjectList.list[i].userGen;
        if ENPlanProjectList.list[i].dateEdit = nil then
          Cells[4,i+1] := ''
        else
          Cells[4,i+1] := XSDateTimeWithDate2String(ENPlanProjectList.list[i].dateEdit);
        LastRow:=i+1;
        sgENPlanProject.RowCount:=LastRow+1;
      end;

    ColCount:=ColCount+1;
    sgENPlanProject.Row:=1;

    if selectedRow <> 0 then
    begin
    if sgENPlanProject.RowCount > selectedRow then
      sgENPlanProject.Row := selectedRow
    else
      sgENPlanProject.Row := sgENPlanProject.RowCount - 1;
    end
    else
      sgENPlanProject.Row:=1;
end;

procedure TfrmENPlanWorkEdit.actUpdateXqtnPercentExecute(Sender: TObject);
var
  TempENPlanXqtnHistory: ENPlanXqtnHistoryControllerSoapPort;
  i: Integer;
  ENPlanXqtnHistoryList: ENPlanXqtnHistoryShortList;
  xqtnFilter : ENPlanXqtnHistoryFilter;
begin
  ClearGrid(sgENPlanXqtnHistory);
  SetGridHeaders(ENPlanXqtnHistoryHeaders, sgENPlanXqtnHistory.ColumnHeaders);
  TempENPlanXqtnHistory :=  HTTPRIOENPlanXqtnHistory as ENPlanXqtnHistoryControllerSoapPort;

  xqtnFilter := ENPlanXqtnHistoryFilter.Create;
  SetNullIntProps(xqtnFilter);
  SetNullXSProps(xqtnFilter);
  xqtnFilter.planRef := ENPlanWorkRef.Create;
  xqtnFilter.planRef.code := ENPlanWorkObj.code;

  ENPlanXqtnHistoryList := TempENPlanXqtnHistory.getScrollableFilteredList(xqtnFilter,0,-1);
  xqtnLastCount:=High(ENPlanXqtnHistoryList.list);

  if xqtnLastCount > -1 then
     sgENPlanXqtnHistory.RowCount:=xqtnLastCount+2
  else
     sgENPlanXqtnHistory.RowCount:=2;

   with sgENPlanXqtnHistory do
    for i:=0 to xqtnLastCount do
      begin
        Application.ProcessMessages;
        if ENPlanXqtnHistoryList.list[i].code <> Low(Integer) then
        Cells[0,i+1] := IntToStr(ENPlanXqtnHistoryList.list[i].code)
        else
        Cells[0,i+1] := '';
        if ENPlanXqtnHistoryList.list[i].dateGen = nil then
          Cells[1,i+1] := ''
        else
          Cells[1,i+1] := XSDate2String(ENPlanXqtnHistoryList.list[i].dateGen);
        if ENPlanXqtnHistoryList.list[i].executionPercent = Low(Integer) then
          Cells[2,i+1] := ''
        else
          Cells[2,i+1] := IntToStr(ENPlanXqtnHistoryList.list[i].executionPercent);
        Objects[0, i+1] := ENPlanXqtnHistoryList.list[i];
        Cells[3,i+1] := ENPlanXqtnHistoryList.list[i].userGen;

        if ENPlanXqtnHistoryList.list[i].dateEdit = nil then
          Cells[4,i+1] := ''
        else
          Cells[4,i+1] := XSDate2String(ENPlanXqtnHistoryList.list[i].dateEdit);
        Objects[0, i+1] := ENPlanXqtnHistoryList.list[i];
        xqtnLastRow:=i+1;
        sgENPlanXqtnHistory.RowCount:=xqtnLastRow+1;
      end;

    sgENPlanXqtnHistory.Row:=1;
end;

procedure TfrmENPlanWorkEdit.btnPlanReportClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
begin
  if DialogState = dsInsert then Exit;

  /////// Parameters
{  SetLength(argNames, 2);
  SetLength(args, 2);

  argNames[0] := 'yearGen';
  args[0] := IntToStr(ENPlanWorkObj.yearGen);

  argNames[1] := 'monthGen';
  args[1] := IntToStr(ENPlanWorkObj.monthGen);
  ///////

  makeReport('materialByRen', argNames, args, 'pdf');
  }
  SetLength(argNames, 1);
  SetLength(args, 1);

  argNames[0] := 'planCode';
  args[0] := IntToStr(ENPlanWorkObj.code);

  ///////

  makeReport('materialUndeliveredByPlan', argNames, args, 'xls');
end;



procedure TfrmENPlanWorkEdit.actDeleteExecute(Sender: TObject);
Var TempENPlanWorkItem: ENPlanWorkItemControllerSoapPort;
  ObjCode: Integer;
  TempENEstimateItem: ENEstimateItemControllerSoapPort;
  eType : integer;
  TempENTransportRoute : ENTransportRouteControllerSoapPort;
  TempRQTransportInvoice : RQTransportInvoiceControllerSoapPort;
  TempFINExecutor2Plan : FINExecutor2PlanControllerSoapPort;
  TempENPlanWork: ENPlanWorkControllerSoapPort;
  plan: ENPlanWork;
  estimateItem: ENEstimateItem;

  grid: TAdvStringGrid;
begin

//EXIT;

  //if ENPlanWorkObj.status.code <> ENPLANWORKSTATUS_GOOD then
  if not (ENPlanWorkObj.status.code in [ENPLANWORKSTATUS_GOOD, ENPLANWORKSTATUS_INCORRECTION]) then
  begin
      Application.MessageBox(PChar('План затверджений або видалений !'), PChar('Увага'), MB_ICONWARNING);
      exit;
  end;

  if Application.MessageBox(PChar('Вы действительно хотите удалить запись ?'),
                            PChar('Внимание!'),
                            MB_ICONQUESTION + MB_OKCANCEL + MB_DEFBUTTON2) <> IDOK then
    Exit;

    //-----------------------
  if pcPlanWork.ActivePage = tsPlanWorkItems then
  begin
    selectedRow := sgENPlanWorkItem.Row;
    TempENPlanWorkItem := HTTPRIOENPlanWorkItem as ENPlanWorkItemControllerSoapPort;

    try
      ObjCode := StrToInt(sgENPlanWorkItem.Cells[0,sgENPlanWorkItem.Row]);
    except
      on EConvertError do Exit;
    end;

    // определим тип элемента по коду
    // и вызовм НУЖНЫЙ метод ... - чтоб левые юзера не планировали на правые обьекты
    //eType := DMReports.getElementTypeByPlanItem(ObjCode);
{
      if eType in [1,2,3] then
        TempENPlanWorkItem.removeBySRS(ObjCode)
      else
      if eType = 5 then
        TempENPlanWorkItem.removeBySVES(ObjCode);
}
      {
      case eType of
        1,2,3 : TempENPlanWorkItem.removeBySRS(ObjCode);
        5 : TempENPlanWorkItem.removeBySVES(ObjCode);
        6 : TempENPlanWorkItem.removeBySPS(ObjCode);
        7 : TempENPlanWorkItem.removeByByt(ObjCode);
        8 : TempENPlanWorkItem.removeByProm(ObjCode);
        else
        begin
          Application.MessageBox(PChar('Невідомий тип Об"єкту !!!'), PChar('Помилка'), MB_ICONERROR);
          exit;
        end;
      end;
      }

      TempENPlanWorkItem.remove(ObjCode);
      UpdateGrid(Sender);

        if  sgENPlanWorkItem.RowCount > selectedRow then
           sgENPlanWorkItem.Row := selectedRow - 1
        else
           sgENPlanWorkItem.Row :=  sgENPlanWorkItem.RowCount-1;

    //end;
  end; // pcPlanWork.ActivePage = tsPlanWorkItems

// ---------------------------------------------------------------------------------

  if pcPlanWork.ActivePage = tsServicesItem then
  begin
    selectedRow := sgServicesItem.Row;
    TempENPlanWorkItem := HTTPRIOENPlanWorkItem as ENPlanWorkItemControllerSoapPort;

    try
      ObjCode := StrToInt(sgServicesItem.Cells[0,sgServicesItem.Row]);
    except
      on EConvertError do Exit;
    end;

      TempENPlanWorkItem.remove(ObjCode);
      UpdateGrid(Sender);

      if sgServicesItem.RowCount > selectedRow then
        sgServicesItem.Row := selectedRow - 1
      else
        sgServicesItem.Row := sgServicesItem.RowCount-1;
  end;

// ---------------------------------------------------------------------------------

  if pcPlanWork.ActivePage = tsEstimateItems then
  begin
    if sgENEstimateItem.Visible then
      grid := sgENEstimateItem
    else if sgENEstimateItemWithFin.Visible then
      grid := sgENEstimateItemWithFin
    else
      raise Exception.Create('NET-4061 Unknown ENEstimateItem grid!');


     //if  Integer(sgENEstimateItem.Objects[0,sgENEstimateItem.Row]) in [ENESTIMATEITEMTYPE_MANUAL_BY_PLANITEM ] then //такие удалять из РАБОТЫ .., ENESTIMATEITEMTYPE_MANUAL_BY_PLANITEM]  then
     if Integer(grid.Objects[0, grid.Row]) in [ENESTIMATEITEMTYPE_MANUAL_BY_PLANITEM] then //такие удалять из РАБОТЫ .., ENESTIMATEITEMTYPE_MANUAL_BY_PLANITEM]  then
     begin
       Application.MessageBox(PChar('Цей матеріaл видаляється в роботі !!!'), PChar('Увага !'),MB_ICONQUESTION+MB_OK);
       exit;
     end;

     //if  Integer(sgENEstimateItem.Objects[0,sgENEstimateItem.Row]) in [ENESTIMATEITEMTYPE_MANUAL_BY_PLAN ] then //такие удалять из РАБОТЫ .., ENESTIMATEITEMTYPE_MANUAL_BY_PLANITEM]  then
     if Integer(grid.Objects[0, grid.Row]) in [ENESTIMATEITEMTYPE_MANUAL_BY_PLAN] then
     begin
       TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;
        try
          //ObjCode := StrToInt(sgENEstimateItem.Cells[0,sgENEstimateItem.Row]);
          ObjCode := StrToInt(grid.Cells[0, grid.Row]);
        except
          on EConvertError do Exit;
        end;
        //if Application.MessageBox(PChar('Вы действительно хотите удалить материал ?'),
        //                  PChar('Внимание !'),MB_ICONQUESTION+MB_OKCANCEL+MB_DEFBUTTON2)=IDOK then
        begin
            // определим тип элемента по коду
            // и вызовм НУЖНЫЙ метод ... - чтоб левые юзера не планировали на правые обьекты
            {
            eType := DMReports.getElementTypeByEstimateItem(ObjCode);
            case eType of
              1,2,3 : TempENEstimateItem.removeBySRS(ObjCode);
              5 : TempENEstimateItem.removeBySVES(ObjCode);
              6 : TempENEstimateItem.removeBySPS(ObjCode);
              7 : TempENPlanWorkItem.removeByByt(ObjCode);
              8 : TempENPlanWorkItem.removeByProm(ObjCode);
              else
              begin
                Application.MessageBox(PChar('Невідомий тип Об"єкту !!!'), PChar('Помилка'), MB_ICONERROR);
                exit;
              end;
            end;
            }
            TempENEstimateItem.remove(ObjCode);
            UpdateGrid(Sender);
        end;
     end
     else
        Application.MessageBox(PChar('Цю строку неможливо видалити! Змінюйте фактичну кількість або видаляйте строку плану !'),
                          PChar('Увага!'),MB_ICONWARNING);

  end; //pcPlanWork.ActivePage = tsEstimateItems


// ---------------------------------------------------------------------------------
  if pcPlanWork.ActivePage = tsDismount then
  begin
     TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;
     try
       ObjCode := StrToInt(sgDismount.Cells[0,sgDismount.Row]);
     except
       on EConvertError do Exit;
     end;

     //if Application.MessageBox(PChar('Вы действительно хотите удалить материал ?'),
     //                   PChar('Внимание !'),MB_ICONQUESTION+MB_OKCANCEL+MB_DEFBUTTON2)=IDOK then
     begin
          // определим тип элемента по коду
          // и вызовм НУЖНЫЙ метод ... - чтоб левые юзера не планировали на правые обьекты
          {
          eType := DMReports.getElementTypeByEstimateItem(ObjCode);
          case eType of
            1,2,3 : TempENEstimateItem.removeBySRS(ObjCode);
            5 : TempENEstimateItem.removeBySVES(ObjCode);
            6 : TempENEstimateItem.removeBySPS(ObjCode);
            7 : TempENPlanWorkItem.removeByByt(ObjCode);
            8 : TempENPlanWorkItem.removeByProm(ObjCode);
            else
            begin
              Application.MessageBox(PChar('Невідомий тип Об"єкту !!!'), PChar('Помилка'), MB_ICONERROR);
              exit;
            end;
          end;
          }

          if ((ENPlanWorkObj.stateRef.code in [ENPLANWORKSTATE_MATERIALS_MBP, ENPLANWORKSTATE_MATERIALS_MBP_INSTRUMENTS]) or
              (ENPlanWorkObj.stateRef.code = ENPLANWORKSTATE_MATERIALS_MNMA)) and
             (ENPlanWorkObj.typeRef.code <> ENPLANWORKTYPE_WRITEOFF_PROTECTION) then
            TempENEstimateItem.removeUnmountForWriteOff_MBP_MNMA(ObjCode)
          else
            TempENEstimateItem.removeUnmount(ObjCode);

          UpdateGrid(Sender);
     end;

  end; //pcPlanWork.ActivePage = tsDismount


    // -------------------------------------------

  if pcPlanWork.ActivePage = tsProduced then
  begin
     TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;
     try
       ObjCode := StrToInt(sgProduced.Cells[0,sgProduced.Row]);
     except
       on EConvertError do Exit;
     end;

     begin
          if ENPlanWorkObj.stateRef.code <> ENConsts.ENPLANWORKSTATE_SIDEWORKS then
          TempENEstimateItem.removeProduced(ObjCode)
          else TempENEstimateItem.removeProduced4Services(ObjCode);
          UpdateGrid(Sender);
     end;

  end; //pcPlanWork.ActivePage = tsProduced


  // -------------------------------------------
  if pcPlanWork.ActivePage = tsRefinement then
  begin
     TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;
     try
       ObjCode := StrToInt(sgRefinement.Cells[0,sgRefinement.Row]);
     except
       on EConvertError do Exit;
     end;

     //if Application.MessageBox(PChar('Вы действительно хотите удалить материал ?'),
     //                   PChar('Внимание !'),MB_ICONQUESTION+MB_OKCANCEL+MB_DEFBUTTON2)=IDOK then
     begin

          TempENEstimateItem.removeRefinement(ObjCode);
          UpdateGrid(Sender);
     end;

  end; //pcPlanWork.ActivePage = tsRefinement


  // -------- start tsTransportRoute  --------
  if (pcPlanWork.ActivePage = tsTransportRoute) then
  begin
    TempENTransportRoute := HTTPRIOENTransportRoute as ENTransportRouteControllerSoapPort;
    try
      ObjCode := StrToInt(sgENTransportRoute.Cells[0,sgENTransportRoute.Row]);
    except
      on EConvertError do Exit;
    end;

    begin
      TempENTransportRoute.remove(ObjCode);
      UpdateGrid(Sender);
    end;
  end;
  // -------- end tsTransportRoute  --------

  // -------- start tsRQTransportInvoice  --------
  if (pcPlanWork.ActivePage = tsRQTransportInvoice) then
  begin
    TempRQTransportInvoice := HTTPRIORQTransportInvoice as RQTransportInvoiceControllerSoapPort;
    try
      ObjCode := StrToInt(sgRQTransportInvoice.Cells[0,sgRQTransportInvoice.Row]);
    except
      on EConvertError do Exit;
    end;

    begin
      TempRQTransportInvoice.remove(ObjCode);
      UpdateGrid(Sender);
    end;
  end;
  // -------- end tsRQTransportInvoice  --------

  /// start tsCustomerMaterials

  if pcPlanWork.ActivePage = tsCustomerMaterials then
  begin
    TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;
    try
      ObjCode := StrToInt(sgCustomerMaterials.Cells[0, sgCustomerMaterials.Row]);
    except
      on EConvertError do Exit;
    end;


    /// Этот вопрос задается в самом начале! 
    // if Application.MessageBox(PChar('Вы действительно хотите удалить материал ?'),
    //                           PChar('Внимание !'), MB_ICONQUESTION + MB_OKCANCEL + MB_DEFBUTTON2) = IDOK then
    // begin
      estimateItem := TempENEstimateItem.getObject(ObjCode);

      TempENPlanWork := HTTPRIOENPlanWork as ENPlanWorkControllerSoapPort;
      plan := TempENPlanWork.getObject(estimateItem.planRef.code);

      // 09.11.12 NET-3079
      // Для месячного плана материал заказчика нужно удалять
      // вместе с привязанной к нему номенклатурой (с finMaterials'ом)
      if plan.kind.code = ENPLANWORKKIND_CURRENT then
        TempENEstimateItem.removeCustomerMaterial(ObjCode)
      else
        TempENEstimateItem.remove(ObjCode);

      UpdateGrid(Sender);
    // end;
  end;

  /// end tsCustomerMaterials

  // -------- start tsFINExecutor2Plan  --------
  if (pcPlanWork.ActivePage = tsFINExecutor2Plan) then
  begin
    TempFINExecutor2Plan := HTTPRIOFINExecutor2Plan as FINExecutor2PlanControllerSoapPort;

    try
      ObjCode := StrToInt(sgFINExecutor2Plan.Cells[0, sgFINExecutor2Plan.Row]);
    except
      on EConvertError do Exit;
    end;

    begin
      TempFINExecutor2Plan.remove(ObjCode);
      UpdateGrid(Sender);
    end;

  end;
  // -------- end tsFINExecutor2Plan  --------

end;


procedure TfrmENPlanWorkEdit.actDeleteprojectExecute(Sender: TObject);
var TempENPlanProject: ENPlanProjectControllerSoapPort;
ObjCode : Integer;
begin
 TempENPlanProject := HTTPRIOENPlanProject as ENPlanProjectControllerSoapPort;
   try
     ObjCode := StrToInt(sgENPlanProject.Cells[0,sgENPlanProject.Row]);
   except
   on EConvertError do Exit;
  end;
  if Application.MessageBox(PChar('Вы действительно хотите удалить проэкт ?'),
                    PChar('Внимание !'),MB_ICONQUESTION+MB_OKCANCEL+MB_DEFBUTTON2)=IDOK then
  begin
      TempENPlanProject.remove(ObjCode);
      actUpdateprojectExecute(Sender);
  end;
end;

procedure TfrmENPlanWorkEdit.sgENPlanCorrectHistoryDblClick(
  Sender: TObject);
  var
  frmViewOldPlan : TfrmENPlanWorkEdit;
  TempENPlanCorrectHistory : ENPlanCorrectHistoryControllerSoapPort;
  TempENPlanWork: ENPlanWorkControllerSoapPort;

  planCorrectHistory : ENPlanCorrectHistory;
  plan : ENPlanWork;
  objcode : integer;
begin

  try
    ObjCode := StrToInt(sgENPlanCorrectHistory.Cells[0,sgENPlanCorrectHistory.Row]);
  except
    on EConvertError do Exit;
  end;

  frmViewOldPlan := TfrmENPlanWorkEdit.Create(Application, dsView);

  try

    TempENPlanCorrectHistory  := HTTPRIOENPlanCorrectHistory as ENPlanCorrectHistoryControllerSoapPort;

    planCorrectHistory := TempENPlanCorrectHistory.getObject(ObjCode);

    TempENPlanWork  := HTTPRIOENPlanWork as ENPlanWorkControllerSoapPort;

    frmViewOldPlan.ENPlanWorkObj := ENPlanWork.Create;
    // 02.02.11 Будем открывать тот план (НОВЫЙ), который создался в результате операции, а не тот, с которого его создали
    //frmViewOldPlan.ENPlanWorkObj :=  TempENPlanWork.getObject(planCorrectHistory.planOldRef.code);
    frmViewOldPlan.ENPlanWorkObj := TempENPlanWork.getObject(planCorrectHistory.planNewRef.code);

   // frmViewOldPlan.DisableActions([frmViewOldPlan.actInsert, frmViewOldPlan.actEdit, frmViewOldPlan.actDelete]);
    frmViewOldPlan.tsCorrections.TabVisible := false;
    // 02.02.11 Будем открывать тот план (НОВЫЙ), который создался в результате операции, а не тот, с которого его создали
    //frmViewOldPlan.Caption := 'Змінений план при коригуванні';
    frmViewOldPlan.Caption := 'План, створений при коригуванні';
    frmViewOldPlan.ShowModal;
  finally
    frmViewOldPlan.Free;
    frmViewOldPlan:=nil;
  end;

end;

procedure TfrmENPlanWorkEdit.sgENPlanProjectDblClick(Sender: TObject);
begin
  inherited;
   actViewprojectExecute(Sender);
end;

procedure TfrmENPlanWorkEdit.spbENBudgetClick(Sender: TObject);
var
   frmENDepartmentShow: TfrmENDepartmentShow;
   f : ENDepartmentFilter;

begin
   f := ENDepartmentFilter.Create;
   SetNullIntProps(f);
   SetNullXSProps(f);
   f.typeRef := ENDepartmentTypeRef.Create;
   f.typeRef.code := ENDEPARTMENTTYPE_BUDGET;
   f.conditionSQL := ' parentrefcode is null';
   
   frmENDepartmentShow:=TfrmENDepartmentShow.Create(Application,fmNormal, f);
   try

      with frmENDepartmentShow do begin
   //     DisableActions([ actNoFilter, actEdit, actInsert ]);
        if ShowModal = mrOk then
        begin
            try
               if ENPlanWorkObj.budgetRef = nil then ENPlanWorkObj.budgetRef := ENDepartmentRef.Create();
               ENPlanWorkObj.budgetRef.code := ENDepartmentShort(tvDep.Selected.Data).code;
               edtENBudgetName.Text:=ENDepartmentShort(tvDep.Selected.Data).shortName ;

               /////
               // 04.02.14 Если планы по счетчикам и бюджетодержатель - ВРТУ, то это не ИнвестПрограмма, поэтому поля по ИП не показываем
               if ENPlanWorkObj.typeRef <> nil then
                 if ENPlanWorkObj.typeRef.code in [ENPLANWORKTYPE_ESBYT_PZ,
                                                   ENPLANWORKTYPE_ESBYT_ZKO_106,
                                                   ENPLANWORKTYPE_ESBYT_ZKO_111,
                                                   ENPLANWORKTYPE_ESBYT_ZKO_112] then
                   if ENPlanWorkObj.budgetRef.code = ENBUDGET_VRTUVD then
                     HideControls([gbInvestProgramGroups, spbInvestProgramGroups]);
               /////
            except
               on EConvertError do Exit;
            end;
        end;
      end;
   finally
      frmENDepartmentShow.Free;
   end;
end;

procedure TfrmENPlanWorkEdit.spbResponsibilityClick(Sender: TObject);
var
   frmENDepartmentShow: TfrmENDepartmentShow;
   f : ENDepartmentFilter;
begin
   f := ENDepartmentFilter.Create;
   SetNullIntProps(f);
   SetNullXSProps(f);
   f.typeRef := ENDepartmentTypeRef.Create;
   f.typeRef.code := ENDEPARTMENTTYPE_RESPOSIBILITY;
   f.conditionSQL := ' parentrefcode is null';

   frmENDepartmentShow:=TfrmENDepartmentShow.Create(Application,fmNormal, f);
   try
      with frmENDepartmentShow do begin
   //     DisableActions([ actNoFilter, actEdit, actInsert ]);
        if ShowModal = mrOk then
        begin
            try
               if ENPlanWorkObj.responsibilityRef = nil then ENPlanWorkObj.responsibilityRef := ENDepartmentRef.Create();
               ENPlanWorkObj.responsibilityRef.code := ENDepartmentShort(tvDep.Selected.Data).code;
               edtResponsibility.Text:= ENDepartmentShort(tvDep.Selected.Data).shortName;
            except
               on EConvertError do Exit;
            end;
        end;
      end;
   finally
      frmENDepartmentShow.Free;
   end;
end;

procedure TfrmENPlanWorkEdit.spbDepartmentClick(Sender: TObject);
var
   frmENDepartmentShow: TfrmENDepartmentShow;
   f : ENDepartmentFilter;
begin
   f := ENDepartmentFilter.Create;
   SetNullIntProps(f);
   SetNullXSProps(f);
   //f.typeRef := ENDepartmentTypeRef.Create;
   //f.typeRef.code := 0; // предприятие ХОЕ ... ENDEPARTMENTTYPE_DEPARTMENT;
   //f.conditionSQL := ' parentrefcode is null';
   //f.conditionSQL :=

   f.code := 1;
   if ENPlanWorkObj.elementRef <> nil then
      if ENPlanWorkObj.elementRef.code > LOW_INT then
         if ENPlanWorkObj.renRef <> nil then
            /// Если код РЭСа > 0 (т.е. это не ХОЭ), то фильтруем подразделения по РЭСу,
            /// иначе выводим все
            //if ENPlanWorkObj.renRef.code > low(Integer) then
            // можно переделать из ДМРепорта ...
            if ENPlanWorkObj.renRef.code > 0 then
            begin
               f.conditionSQL := 'code in (select departmentrefcode from endepartment2epren where renrefcode = ' + IntToStr(ENPlanWorkObj.renRef.code) +')';
               f.code := Low(integer);
            end;





   frmENDepartmentShow:=TfrmENDepartmentShow.Create(Application,fmNormal, f);
   try
      frmENDepartmentShow.isShowAll := true;
      with frmENDepartmentShow do begin
   //     DisableActions([ actNoFilter, actEdit, actInsert ]);
        if ShowModal = mrOk then
        begin
            try
               if ENPlanWorkObj.departmentRef = nil then ENPlanWorkObj.departmentRef := ENDepartmentRef.Create();
               ENPlanWorkObj.departmentRef.code := ENDepartmentShort(tvDep.Selected.Data).code;
               edtDepartment.Text:= ENDepartmentShort(tvDep.Selected.Data).shortName;
            except
               on EConvertError do Exit;
            end;
        end;
      end;
   finally
      frmENDepartmentShow.Free;
   end;

end;

procedure TfrmENPlanWorkEdit.spbTypeClick(Sender: TObject);
var
   frmENPlanWorkTypeShow: TfrmENPlanWorkTypeShow;
   f : ENPlanWorkTypeFilter;
begin

   if (ENPlanWorkObj.elementRef = nil) or (ENPlanWorkObj.elementRef.code = Low_Int) then
   begin
     Application.MessageBox(PChar('Спочатку оберіть Об''єкт планування !!!'), PChar('Ошибка'), MB_ICONERROR);
     exit;
   end;

   f:= ENPlanWorkTypeFilter.Create;
   SetNullIntProps(f);
   SetNullXSProps(f);

   if isOperative then
     f.conditionSQL := 'code in (' + IntToStr(ENPLANWORKTYPE_PLAN) + ', ' +
                                     IntToStr(ENPLANWORKTYPE_AVR)  + ', ' +
                                     IntToStr(ENPLANWORKTYPE_NOT_PLANNED) + ')';

   if isMeasurement then
     f.conditionSQL := 'code in (' + IntToStr(ENPLANWORKTYPE_PLAN) + ', ' +
                                     IntToStr(ENPLANWORKTYPE_AVR)  + ', ' +
                                     IntToStr(ENPLANWORKTYPE_NOT_PLANNED) + ')';
   if isServicesFromSide
     then
       f.conditionSQL := 'code in (' +
         IntToStr(ENPLANWORKTYPE_SERVICES_FROM_SIDE_INVEST)  + ', ' +
         IntToStr(ENPLANWORKTYPE_SERVICES_FROM_SIDE) + ')';

   if  ((isTechConditions)  and (isServicesFromSide <> true)) then
    begin //NET-4220
     if  techCondServicesType = ENTECHCONDITIONSSERVICES_TYPE_PROJECT then
       f.conditionSQL := 'code in (' + IntToStr(ENPLANWORKTYPE_VRTUVD_PROJECT) + ', ' +
         IntToStr(ENPLANWORKTYPE_SERVICES_FROM_SIDE) + ')'
     else if (techCondServicesType = ENTECHCONDITIONSSERVICES_TYPE_REALIZATION)
     and (connectionKind = ENCONNECTIONKIND_GENERAL_CONNECTION)
     then
       f.conditionSQL := 'code in (' + IntToStr(ENPLANWORKTYPE_CN)  + ', ' +
         IntToStr(ENPLANWORKTYPE_SERVICES_FROM_SIDE) + ')'
     else if (techCondServicesType = ENTECHCONDITIONSSERVICES_TYPE_REALIZATION)
     and ((connectionKind = ENCONNECTIONKIND_STANDART)
       or (connectionKind = ENCONNECTIONKIND_NO_STANDART)
       or (connectionKind = ENCONNECTIONKIND_NO_STANDART_READY_MADE))
     then
       f.conditionSQL := 'code in (' +
         IntToStr(ENPLANWORKTYPE_VRTUVD_PROJECT) + ', ' +
         IntToStr(ENPLANWORKTYPE_CN)  + ', ' +
         IntToStr(ENPLANWORKTYPE_SIDEWORKS)  + ', ' + //SUPP-4269
         IntToStr(ENPLANWORKTYPE_SERVICES_FROM_SIDE) + ')'

     else
       begin
        Application.MessageBox(PChar('Невідомий тип договору  !!!'), PChar('Ошибка'), MB_ICONERROR);
        Exit;
       end;

    end;

    if isGiftObj then
     f.conditionSQL := 'code in (' + IntToStr(ENPLANWORKTYPE_GIFT) + ')';
   f.orderBySQL := 'ordered';


   frmENPlanWorkTypeShow:=TfrmENPlanWorkTypeShow.Create(Application,fmNormal,f);
   try
      with frmENPlanWorkTypeShow do begin
        DisableActions([ actEdit, actInsert , actDelete]);
        if ShowModal = mrOk then
        begin
            try
               if ENPlanWorkObj.typeRef = nil then ENPlanWorkObj.typeRef := ENPlanWorkTypeRef.Create();
               ENPlanWorkObj.typeRef.code := StrToInt(GetReturnValue(sgENPlanWorkType,0));//ENDepartmentShort(tvDep.Selected.Data).code;
               edtTypeName.Text:= GetReturnValue(sgENPlanWorkType,1); //ENDepartmentShort(tvDep.Selected.Data).shortName;

               //DisableControls([spbENPlanWorkState], false);

               if (not isOperative) and (not isMeasurement) then
               begin
                 edtWorkState.Text := '';
                 ENPlanWorkObj.stateRef := nil;
               end;


               //if ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_CN then // Присоединение
               //begin
                 HideControls([ gbPriConnection, lblPriConnectionNumber, edtPriConnectionNumber , edtDateEndPriconnection  , lblDateEndPriconnection ],
                 ((ENPlanWorkObj.typeRef.code <> ENPLANWORKTYPE_CN) and (ENPlanWorkObj.typeRef.code <> ENPLANWORKTYPE_VRTUVD_PROJECT))  );
                 DenyBlankValues([edtPriConnectionNumber, edtDateEndPriconnection]);
                 DisableControls([edtDateEndPriconnection], ((ENPlanWorkObj.typeRef.code <> ENPLANWORKTYPE_CN) and (ENPlanWorkObj.typeRef.code <> ENPLANWORKTYPE_VRTUVD_PROJECT)) );

                { if edtPriConnectionNumber.Enabled  then
                 ShowMessage( 'edtPriConnectionNumber  ' + 'Enabled')
                 else
                 ShowMessage( 'edtPriConnectionNumber  ' + 'Disable');

                 if edtDateEndPriconnection.Enabled  then
                 ShowMessage( 'edtDateEndPriconnection  ' + 'Enabled')
                 else
                 ShowMessage( 'edtDateEndPriconnection  ' + 'Disable'); } 

               //end

               // 05.12.2012 +++  "Объекты договоров (услуги со стороны)"
               if (inServices) then
               begin
                 edtServicesFromSide.Text := edtInvNumber.Text;
                 DisableControls([edtServicesFromSide, spbServicesFromSide{, edtDdsCodes, spbDdsCodes}]);
               end;
               HideControls([gbServicesFromSide, lblServicesFromSide, edtServicesFromSide, spbServicesFromSide,
                         gbDddsCodes, edtDdsCodes, spbDdsCodes],
                   not (ENPlanWorkObj.typeRef.code in [ENPLANWORKTYPE_SERVICES_FROM_SIDE, ENPLANWORKTYPE_SERVICES_FROM_SIDE_INVEST]));

               ///////////////////
               // 13.09.12 NET-3027 Для актов вып. работ по Договорам Подряда (ДП) нужно тоже выбирать договор!
               if (ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_TMC_TRANSFER) then
               begin
                 HideControls([gbServicesFromSide, lblServicesFromSide, edtServicesFromSide, spbServicesFromSide], false);
                 DisableControls([edtServicesFromSide]);
                 HideControls([ lblPartner ,  edtPartner ] , false);
                 gbServicesFromSide.Caption := 'Договір підряду';
               end
               else begin
                 DisableControls([edtServicesFromSide], false);
                 gbServicesFromSide.Caption := 'Послуги';
               end;
               ///////////////////

               ///// 13.09.12 При изменении подвида работ очистим договор!
               if isServicesFromSide = false then
               begin
                edtServicesFromSide.Text := '';
                ENPlanWorkObj.servicesFSideCnNum := '';
                ENPlanWorkObj.servicesFSideFinId := LOW_INT;
               end;
               if isServicesFromSide then
               begin
                DisableControls([edtServicesFromSide]);
               end;




               ///////////////
               //DenyBlankValues([edtServicesFromSide]);
               DenyBlankValues([edtDdsCodes]);

               ///// 08.01.2014 Поля "Раздел ИнвестПрограммы" и "Пункт ИнвестПрограммы" показываем для всех подвидов работ по ИП!!!
               {
               // 13.02.2012 +++ только для услуг (инвест программа)
               HideControls([gbIndvestProgramGroups, spbInvestProgramGroups], not (ENPlanWorkObj.typeRef.code in [ENPLANWORKTYPE_SERVICES_FROM_SIDE_INVEST]));
               DenyBlankValues([edtInvestProgramGroupsName]);
               }
               // 09.01.14 Только для Месячных планов
               if ((DialogState = dsInsert) and (cbPlanWorkKind.ItemIndex + 1 = ENPLANWORKKIND_CURRENT)) or
                  ((DialogState = dsEdit) and (ENPlanWorkObj.kind.code = ENPLANWORKKIND_CURRENT)) then
               begin
                 HideControls([gbInvestProgramGroups, spbInvestProgramGroups],
                               not (ENPlanWorkObj.typeRef.code in [ENPLANWORKTYPE_INVEST,
                                                                   ENPLANWORKTYPE_SERVICES_FROM_SIDE_INVEST,
                                                                   ENPLANWORKTYPE_ESBYT_PZ,
                                                                   ENPLANWORKTYPE_ESBYT_ZKO_106,
                                                                   ENPLANWORKTYPE_ESBYT_ZKO_111,
                                                                   ENPLANWORKTYPE_ESBYT_ZKO_112]));

                   // для проэктирования пункты ИП не обязательны
                   if((ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_INVEST ) and
                       ( ENPlanWorkObj.stateRef.code = ENPLANWORKSTATE_DESIGNING )
                      ) then
                      DenyBlankValues([edtInvestProgramGroupsName, edtInvestItemNumber],false);



                 // 04.02.14 Если планы по счетчикам и бюджетодержатель - ВРТУ, то это не ИнвестПрограмма, поэтому поля по ИП не показываем
                 if ENPlanWorkObj.typeRef.code in [ENPLANWORKTYPE_ESBYT_PZ,
                                                   ENPLANWORKTYPE_ESBYT_ZKO_106,
                                                   ENPLANWORKTYPE_ESBYT_ZKO_111,
                                                   ENPLANWORKTYPE_ESBYT_ZKO_112] then
                   if ENPlanWorkObj.budgetRef <> nil then
                     if ENPlanWorkObj.budgetRef.code = ENBUDGET_VRTUVD then
                       HideControls([gbInvestProgramGroups, spbInvestProgramGroups]);

                 DenyBlankValues([edtInvestProgramGroupsName, edtInvestItemNumber]);
               end
               else
                 HideControls([gbInvestProgramGroups, spbInvestProgramGroups]);
               /////


               ////  21.05.2012 +++ Зміст робіт (інв.прг.)
               HideControls([gbInvestWorks, gbInvestWorksDescription], not (ENPlanWorkObj.typeRef.code in [ENPLANWORKTYPE_INVEST, ENPLANWORKTYPE_SERVICES_FROM_SIDE_INVEST]));
               DenyBlankValues([edtInvestWorksDescription, edtInvestWorksAmount], (ENPlanWorkObj.typeRef.code in [ENPLANWORKTYPE_INVEST, ENPLANWORKTYPE_SERVICES_FROM_SIDE_INVEST]));
               ////  показываем только для подвида работ "Инвест программа"


                { оно вводиться на УЖЕ существующий план ... должна быть ссылка !
               if ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_PRIPIS then // Предписание
               begin
                 HideControls([gbBindingOver], false);
                 DenyBlankValues([edtBindingOver]);
                 DisableControls([spbBindingOver],false);
               end;
                }
            except
               on EConvertError do Exit;
            end;
        end;
      end;
   finally
      frmENPlanWorkTypeShow.Free;
   end;
end;


procedure TfrmENPlanWorkEdit.updateDFDocs;
var
  TempDFDoc2Plan: DFDoc2PlanControllerSoapPort;
  TempDFDoc: DFDocControllerSoapPort;
  DFDocList: DFDocShortList;
  docFilter: DFDocFilter;
  doc2PlanFilter: DFDoc2PlanFilter;
  i, r, docsLastCount: Integer;
  doc2PlanList: DFDoc2PlanShortList;
  strDFDocCodes: String;
begin
  if DialogState = dsInsert then Exit;
  if ENPlanWorkObj = nil then Exit;
  if ENPlanWorkObj.code = LOW_INT then Exit;

  ClearGrid(sgDFDocs);

  TempDFDoc2Plan := HTTPRIODFDoc2Plan as DFDoc2PlanControllerSoapPort;

  doc2PlanFilter := DFDoc2PlanFilter.Create;
  SetNullIntProps(doc2PlanFilter);
  SetNullXSProps(doc2PlanFilter);
  doc2PlanFilter.planRef := ENPlanWorkRef.Create;
  doc2PlanFilter.planRef.code := ENPlanWorkObj.code;

  doc2PlanList := TempDFDoc2Plan.getScrollableFilteredList(doc2PlanFilter, 0, -1);
  if High(doc2PlanList.list) < 0 then Exit;

  strDFDocCodes := '';
  for i := 0 to High(doc2PlanList.list) do
    AddListPos(strDFDocCodes, IntToStr(doc2PlanList.list[i].docRefCode));

  if strDFDocCodes = '' then Exit;

  TempDFDoc := HTTPRIODFDoc as DFDocControllerSoapPort;

  docFilter := DFDocFilter.Create;
  SetNullIntProps(docFilter);
  SetNullXSProps(docFilter);

  docFilter.conditionSQL := 'docs.doccode in (' + strDFDocCodes + ')';
  docFilter.orderBySQL := ' 1 DESC';

  DFDocList := TempDFDoc.getLightDocFilteredList(docFilter, 0, -1);

  docsLastCount := High(DFDocList.list);
  if docsLastCount > -1 then
    sgDFDocs.RowCount := docsLastCount + 2
  else
    sgDFDocs.RowCount := 2;

  with sgDFDocs do
  for i := 0 to docsLastCount do
  begin
    // Application.ProcessMessages;
    if DFDocList.list[i].code <> Low(Integer) then
      Cells[0,i+1] := IntToStr(DFDocList.list[i].code)
    else
      Cells[0,i+1] := '';

    Cells[1,i+1] := DFDocList.list[i].docNum;

    if DFDocList.list[i].dateRegistration = nil then
      Cells[2,i+1] := ''
    else
      Cells[2,i+1] := XSDate2String(DFDocList.list[i].dateRegistration);

    if (DFDocList.list[i].name = '') then
      Cells[3,i+1] := DFDocList.list[i].description
    else begin
      ///////
      // 03.09.2015 Для договоров рядом с контрагентом выводим описание (пожелание И.Н.)
      if DFDocList.list[i].docTypeRefCode <> DFDOCTYPE_AGREEMENT then
        Cells[3,i+1] := DFDocList.list[i].name
      else begin
        if DFDocList.list[i].description = '' then
          Cells[3,i+1] := DFDocList.list[i].name + ' (без опису)'
        else
          Cells[3,i+1] := DFDocList.list[i].name + ' (' + DFDocList.list[i].description + ')';
      end;
      ///////
    end;

    Cells[4,i+1] := DFDocList.list[i].docTypeRefName;
    Cells[5,i+1] := DFDocList.list[i].resultRefName;
    Cells[6,i+1] := DFDocList.list[i].userAdd;
    Cells[7,i+1] := IntToStr(DFDocList.list[i].docTypeRefCode);

    if DFDocList.list[i].isRed > 0 then
    begin
      with sgDFDocs do
      for r:=0 to sgDFDocs.ColCount-1 do
      begin
        FontStyles[r,i+1] := FontStyles[r,i+1] + [fsBold];
        sgDFDocs.RowFontColor[i + 1] := clRed;
      end;
    end
    else begin
      with sgDFDocs do
      for r:=0 to sgDFDocs.ColCount-1 do
      begin
        FontStyles[r,i+1] := FontStyles[r,i+1] - [fsBold];
        sgDFDocs.RowFontColor[i + 1] := clWindowText;
      end;
    end;

    sgDFDocs.RowCount := i + 2;
  end;

  sgDFDocs.Row := 1;
end;

procedure TfrmENPlanWorkEdit.edtDateStartClick(Sender: TObject);
var
year : Integer;
begin
  year := StrToInt(edtYearGen.Text);
  edtDateStart.DateTime := EncodeDate(year, edtMonthGen.ItemIndex + 1, 1);

  if KindCode in [ENPLANWORKKIND_NPZ, ENPLANWORKKIND_FACT] then
    edtDateFinal.DateTime := edtDateStart.DateTime
  else
    edtDateFinal.DateTime := EncodeDate(year, edtMonthGen.ItemIndex + 1, DaysInMonth(edtDateStart.DateTime));

//end;

end;


procedure TfrmENPlanWorkEdit.actMaterialBindingExecute(Sender: TObject);
begin
  if DialogState in [dsView, dsInsert] then Exit;
  // Привязывать можно только материал, еще не привязанный к работе
  if Integer(sgENEstimateItem.Objects[0, sgENEstimateItem.Row]) <> ENESTIMATEITEMTYPE_MANUAL_BY_PLAN then Exit;

  frmMaterialToPlanItemBindingEdit := TfrmMaterialToPlanItemBindingEdit.Create(Application, dsInsert);
  try
    frmMaterialToPlanItemBindingEdit.materialCode := Low(Integer);
    frmMaterialToPlanItemBindingEdit.materialCount := 0;
    frmMaterialToPlanItemBindingEdit.materialName := '';

    frmMaterialToPlanItemBindingEdit.planCode := ENPlanWorkObj.code;

    try
      //frmMaterialToPlanItemBindingEdit.materialCode := StrToInt(sgENEstimateItem.Cells[0, sgENEstimateItem.Row]);
      frmMaterialToPlanItemBindingEdit.estimateItemCode := StrToInt(sgENEstimateItem.Cells[0, sgENEstimateItem.Row]);
      frmMaterialToPlanItemBindingEdit.materialName := sgENEstimateItem.Cells[1, sgENEstimateItem.Row];
      frmMaterialToPlanItemBindingEdit.materialCount := StrToFloat(sgENEstimateItem.Cells[3, sgENEstimateItem.Row]);
    except
      on EConvertError do Exit;
    end;

    //if frmMaterialToPlanItemBindingEdit.materialCode < 0 then Exit;
    if frmMaterialToPlanItemBindingEdit.estimateItemCode < 0 then Exit;
    if frmMaterialToPlanItemBindingEdit.materialCount <= 0 then Exit;

    if frmMaterialToPlanItemBindingEdit.ShowModal = mrOk then
    begin
      actUpdateExecute(Sender);
    end;
  finally
    frmMaterialToPlanItemBindingEdit.Free;
  end;
end;

procedure TfrmENPlanWorkEdit.PopupMenu1Popup(Sender: TObject);
var grid: TAdvStringGrid;
begin
  grid := sgENEstimateItem;

  if (pcPlanWork.ActivePage = tsEstimateItems) and
     ((ENPlanWorkObj.kind.code = ENPLANWORKKIND_NPZ) or (ENPlanWorkObj.kind.code = ENPLANWORKKIND_FACT)) then
  begin
    if sgENEstimateItem.Visible then
      grid := sgENEstimateItem
    else if sgENEstimateItemWithFin.Visible then
      grid := sgENEstimateItemWithFin
    else
      raise Exception.Create('NET-4061 Unknown ENEstimateItem grid!');
  end;

  if (pcPlanWork.ActivePage = tsEstimateItems) or (pcPlanWork.ActivePage = tsGSM) then
  begin
    if (sgENEstimateItemWithFin.Visible) or (sgGSMWithFin.Visible) then
    begin
      HideActions([actClearAll, actSelectAll]);
    end;
  end;

  actMaterialBinding.Enabled := (DialogState = dsEdit) and
                                (pcPlanWork.ActivePage = tsEstimateItems) and
                                //(Integer(sgENEstimateItem.Objects[0, sgENEstimateItem.Row]) = ENESTIMATEITEMTYPE_MANUAL_BY_PLAN);
                                (Integer(grid.Objects[0, grid.Row]) = ENESTIMATEITEMTYPE_MANUAL_BY_PLAN);

  actMaterialBinding.Visible := actMaterialBinding.Enabled;

  // тож самое и с развязкой с ФИННН
  actMaterialBindingToFIN.Enabled := (DialogState = dsEdit) and
                                     ((pcPlanWork.ActivePage = tsEstimateItems) or (pcPlanWork.ActivePage = tsGSM))and
                                     ( (ENPlanWorkObj.kind.code = ENPLANWORKKIND_NPZ) or  (ENPlanWorkObj.kind.code = ENPLANWORKKIND_FACT))
                                     // and статус бы еще пробить ....
                                     ;
  // !!! пока спрачем РАЗНОСКУ с ФинКол...
  //actMaterialBindingToFIN.Enabled := false;
  actMaterialBindingToFIN.Visible := actMaterialBindingToFIN.Enabled;

  // добавление в заявку ..

  actAddToRQOrder.Enabled := ( (pcPlanWork.ActivePage = tsEstimateItems)
                               and  (ENPlanWorkObj.kind.code = ENPLANWORKKIND_CURRENT)
                               and (
                                     (Integer(sgENEstimateItem.Objects[1, sgENEstimateItem.Row]) = ENESTIMATEITEMSTATUS_PLANNED)
                                     or (Integer(sgENEstimateItem.Objects[1, sgENEstimateItem.Row]) = ENESTIMATEITEMSTATUS_TMP_)
                                    )
                              );
  actAddToRQOrder.Visible := actAddToRQOrder.Enabled;
  // и удаление из нее ...
  actRemoveFromRQOrder.Enabled :=  ( (pcPlanWork.ActivePage = tsEstimateItems)
                               and  (ENPlanWorkObj.kind.code = ENPLANWORKKIND_CURRENT)
                               and  (Integer(sgENEstimateItem.Objects[1, sgENEstimateItem.Row]) = ENESTIMATEITEMSTATUS_ORDERED)
                              );
  actRemoveFromRQOrder.Visible := actRemoveFromRQOrder.Enabled;

  actFinWorkerAssignToAll.Enabled := ( (pcPlanWork.ActivePage = tsHumens)
                               and  (DialogState = dsEdit)
                              );
  actFinWorkerAssignToAll.Visible := actFinWorkerAssignToAll.Enabled;

  actFinWorkerAssignToAllTransport.Enabled := ( (pcPlanWork.ActivePage = tsTransports)
                               and  (DialogState = dsEdit)
                              );
  actFinWorkerAssignToAllTransport.Visible := actFinWorkerAssignToAllTransport.Enabled;

    actRealTransportAssignToAll.Enabled := ( (pcPlanWork.ActivePage = tsTransports)
                               and  (DialogState = dsEdit)
                              );
  actRealTransportAssignToAll.Visible := actRealTransportAssignToAll.Enabled;

  // открываем меню на привязку транспорта к другому РЕСу только на мес планах и если активна вкладка транспорта
  actAssignToTransportDept.Enabled := ( (pcPlanWork.ActivePage = tsTransports)
                               and  (ENPlanWorkObj.kind.code = ENPLANWORKKIND_CURRENT)
                              );
  actAssignToTransportDept.Visible := actAssignToTransportDept.Enabled;

  actunAssignToTransportDept.Enabled := ( (pcPlanWork.ActivePage = tsTransports)
                               and  (ENPlanWorkObj.kind.code = ENPLANWORKKIND_CURRENT)
                              );
  actunAssignToTransportDept.Visible := actunAssignToTransportDept.Enabled;


  if pcPlanWork.ActivePage = tsEstimateItems then
  begin
    actChangeEstimateItemStatus.Enabled := ((ENPlanWorkObj.kind.code = ENPLANWORKKIND_CURRENT)
    or (ENPlanWorkObj.kind.code = ENPLANWORKKIND_YEAR));
    // может для тех что в наличии
    actMoveTO.Enabled := (ENPlanWorkObj.kind.code = ENPLANWORKKIND_CURRENT);
    actMoveFROM.Enabled := (ENPlanWorkObj.kind.code = ENPLANWORKKIND_CURRENT);

  end
  else
  begin
           actChangeEstimateItemStatus.Enabled := false;
           actMoveTO.Enabled := false;
           actMoveFROM.Enabled := false;
  end;
  actChangeEstimateItemStatus.Visible := actChangeEstimateItemStatus.Enabled;
  actMoveTO.Visible := actMoveTO.Enabled;
  actMoveFROM.Visible := actMoveFrom.Enabled;

  actRemoveUnmountedCounter.Enabled := (PopupMenu1.PopupComponent = sgSCCounter) and (DialogState = dsEdit) and (ENPlanWorkObj.kind.code = ENPLANWORKKIND_FACT);
  actRemoveUnmountedCounter.Visible := actRemoveUnmountedCounter.Enabled;
end;

procedure TfrmENPlanWorkEdit.edtDateStartChange(Sender: TObject);
begin
//if ENPlanWorkObj.kind <> nil then
//  if ENPlanWorkObj.kind.code in [ENPLANWORKKIND_NPZ , ENPLANWORKKIND_FACT ] then
  if KindCode in [ENPLANWORKKIND_NPZ , ENPLANWORKKIND_FACT] then
  begin
    edtDateFinal.DateTime := edtDateStart.DateTime;
    //if ENPlanWorkObj.kind.code = ENPLANWORKKIND_FACT then

    //*** if KindCode = ENPLANWORKKIND_FACT then
    //***  edtDateWorkOrder.DateTime := edtDateStart.DateTime;
  end;

  if KindCode in [ENPLANWORKKIND_YEAR , ENPLANWORKKIND_CURRENT] then
    edtDateFinal.DateTime := EncodeDate(YearOf(edtDateStart.DateTime), MonthOf(edtDateStart.DateTime), DaysInMonth(edtDateStart.DateTime));
end;

procedure TfrmENPlanWorkEdit.spbENPlanWorkStateClick(Sender: TObject);
var
   frmENPlanWorkStateShow: TfrmENPlanWorkStateShow;
   f : ENPlanWorkStateFilter;
   e : ENElement;
begin
   if ENPlanWorkObj.typeRef = nil then
   begin
     Application.MessageBox(PChar('Спочатку оберіть ПідВид робіт !!!'), PChar('Ошибка'), MB_ICONERROR);
     exit;
   end
   else
   if  ENPlanWorkObj.typeRef.code = LOW_INT then
   begin
     Application.MessageBox(PChar('Спочатку оберіть ПідВид робіт !!!'), PChar('Ошибка'), MB_ICONERROR);
     exit;
   end;

   f:= ENPlanWorkStateFilter.Create;
   SetNullIntProps(f);
   SetNullXSProps(f);
   f.orderBySQL := 'ordered';
   f.conditionSQL := 'enplanworkstate.code in (select enplantype2planstate.staterefcode from enplantype2planstate where enplantype2planstate.typerefcode = '+ IntToStr(ENPlanWorkObj.typeRef.code) +')';

   // AS .. нфиг логику без ЭЛЕМЕНТА !!!
   if ENPlanWorkObj.elementRef <> nil then
     if  ENPlanWorkObj.elementRef.code <> LOW_INT then
     begin
       e := DMReports.getElementByCode(ENPlanWorkObj.elementRef.code);
       if (e.typeRef.code <> EN_SUBSTATION150) and
          {SUPP-62192}(e.typeRef.code <> EN_LINE150) and
          (e.typeRef.code <> EN_BUILDER) and
          (e.typeRef.code <> EN_METROLOGY_COUNTER) and
          (e.typeRef.code <> EN_METROLOGY_DEVICE) and
          (e.typeRef.code <> EN_METROLOGY_OBJECT) and
          (e.typeRef.code <> EN_BYT) and
          (e.typeRef.code <> EN_TRANSPORT) and
          (e.typeRef.code <> EN_SIT) and
          (e.typeRef.code <> EN_SDTU) and
          (e.typeRef.code <> EN_PURCHASES_OBJECT) and
          (e.typeRef.code <> EN_PURCHASES_NO_OBJECT) and
          (e.typeRef.code <> EN_SIZ_OBJECT) and
          (e.typeRef.code <> EN_SERVICES_OBJECT) and
          (e.typeRef.code <> EN_PREPRODUCTION_OBJECT) and
          (e.typeRef.code <> EN_EQUIPMENT_REPAIR) and
          {SUPP-61652}(e.typeRef.code <> EN_EQUIPMENT) and
          {SUPP-93756} (e.typeRef.code <> EN_SERVICES_FROM_SIDE_OBJECT)
          then
           f.conditionSQL := f.conditionSQL + ' and enplanworkstate.code <> ' + IntToStr(ENPLANWORKSTATE_CURRENTREPAIR);

       if ( e.typeRef.code = EN_EQUIPMENT) then
       begin
         //f.conditionSQL := f.conditionSQL + ' and enplanworkstate.code = ' + ;
         //f.code := ENPLANWORKSTATE_CAPITALREPAIR;
         {SUPP-7483}
         f.conditionSQL := f.conditionSQL + ' and enplanworkstate.code in (' + IntToStr(ENPLANWORKSTATE_CAPITALREPAIR) + ', ' + IntToStr(ENPLANWORKSTATE_TECHNICALSERVICE)
         {SUPP-61652}
         + ', ' + IntToStr(ENPLANWORKSTATE_CURRENTREPAIR) +')';
       end;

       {
       if ( e.typeRef.code = EN_EQUIPMENT_REPAIR) then
       begin
         //f.conditionSQL := f.conditionSQL + ' and enplanworkstate.code = ' + ;
         f.code := ENPLANWORKSTATE_REFINEMENT;
       end;
       }
       
   end;

   frmENPlanWorkStateShow:=TfrmENPlanWorkStateShow.Create(Application,fmNormal,f);
   try
      with frmENPlanWorkStateShow do begin
        DisableActions([ actEdit, actInsert, actDelete, actNoFilter, actFilter ]);
        if ShowModal = mrOk then
        begin
            try
               if ENPlanWorkObj.stateRef = nil then ENPlanWorkObj.stateRef := ENPlanWorkStateRef.Create();
               ENPlanWorkObj.stateRef.code := StrToInt(GetReturnValue(sgENPlanWorkState,0));//ENDepartmentShort(tvDep.Selected.Data).code;
               edtWorkState.Text:= GetReturnValue(sgENPlanWorkState,1); //ENDepartmentShort(tvDep.Selected.Data).shortName;

               // 24.02.2012 +++ Услуги со стороны Кап.строительство
               if (ENPlanWorkObj.typeRef.code in [ENPLANWORKTYPE_SERVICES_FROM_SIDE, ENPLANWORKTYPE_SERVICES_FROM_SIDE_INVEST]) then
               begin
                 if (ENPlanWorkObj.stateRef.code = ENPLANWORKSTATE_CAPITALBUILDER)
                       or (ENPlanWorkObj.stateRef.code = ENPLANWORKSTATE_RECONSTRUCTION)  then
                 begin
                    HideControls([gbPriConnection, lblPriConnectionNumber, edtPriConnectionNumber , edtDateEndPriconnection , lblDateEndPriconnection ], False);
                    DenyBlankValues([edtPriConnectionNumber , edtDateEndPriconnection]);
                    gbPriConnection.Top := 176;
                    gbPriConnection.Left := 617;
                 end
                 else HideControls([gbPriConnection, lblPriConnectionNumber, edtPriConnectionNumber  , edtDateEndPriconnection , lblDateEndPriconnection ], True);
               end;

               // NET-4383 если списание ОЗ то тип плана задание факт сразу
               if ( (ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_WRITEOFF_OS)
                 and (ENPlanWorkObj.stateRef.code = ENPLANWORKSTATE_WRITINGS_OS) )  then
               begin
                cbPlanWorkKind.ItemIndex := ENPLANWORKKIND_FACT -1;
                cbENPlanWorkFormName.ItemIndex := 1; // Вид работ ставим неплановые
               end;


            except
               on EConvertError do Exit;
            end;
        end;
      end;
   finally
      frmENPlanWorkStateShow.Free;
   end;
end;

procedure TfrmENPlanWorkEdit.spbENActClick(Sender: TObject);
var
  TempENAct: ENActControllerSoapPort;
  i: Integer;
  ENActList: ENActShortList;
  f : ENActFilter;
  frmENActShow : TfrmENActShow;
  TempENAct2ENPlanWork: ENAct2ENPlanWorkControllerSoapPort;

  //ENAct2ENPlanWorkObj : ENAct2ENPlanWork;
  a2 : ENAct2ENPlanWork;

  act2planFilter : ENAct2ENPlanWorkFilter;
  //frmENActShow : TfrmENActShow;
  //TempENAct2ENPlanWork: ENAct2ENPlanWorkControllerSoapPort;
  a2List: ENAct2ENPlanWorkShortList;
  plansType, {ElementTypeCode, }ElementInCode : Integer;
  element: ENElement;

  TempENMetrologyCounter: ENMetrologyCounterControllerSoapPort;
  counterFilter: ENMetrologyCounterFilter;
  counterList: ENMetrologyCounterShortList;
  molData : FINMolData;
  workOrder : ENWorkOrder;
begin

  f := ENActFilter.Create;
  SetNullIntProps(f);
  SetNullXSProps(f);

  f.element := ENElement.Create;
  f.element.code := ENPlanWorkObj.elementRef.code;

  //////////////////////////////////////////////////////////////////////////////
  // Смотрим на тип элемента. Если это СЧЕТЧИК МЕТРОЛОГИИ,
  // то акт ищем по его ТИПУ (абстрактному объекту)
  //ElementTypeCode := LOW_INT;
  element := DMReports.getElementByCode(ENPlanWorkObj.elementRef.code);

  if element <> nil then
    if element.typeRef <> nil then
    begin
      //ElementTypeCode := element.typeRef.code;

       if element.typeRef.code = EN_ROUTE_BYT
       then begin
       ElementInCode := LOW_INT;
       f.element.code:=LOW_INT;
       end;

      if element.typeRef.code = EN_METROLOGY_COUNTER then
      begin
        ElementInCode := LOW_INT;
        if element.elementInRef <> nil then
          ElementInCode := element.elementInRef.code;

        if ElementInCode = LOW_INT then
        begin
          Application.MessageBox(PChar('Необхідно вказати тип лічильника "' + edtENElementName.Text + '" !'),
                                 PChar('Увага!'), MB_ICONWARNING);

          /////// ******* ///////
          // В случае, если тип счетчика не указан (т.е. у него нет ElementIn'а),
          // сразу открываем форму редактирования счетчика (на который мы заводим факт)
          // и даем пользователю выбрать тип этого счетчика
          TempENMetrologyCounter := HTTPRIOENMetrologyCounter as ENMetrologyCounterControllerSoapPort;

          counterFilter := ENMetrologyCounterFilter.Create;
          SetNullIntProps(counterFilter);
          SetNullXSProps(counterFilter);

          counterFilter.element := ENElement.Create;
          counterFilter.element.code := element.code;

          counterList := TempENMetrologyCounter.getScrollableFilteredList(counterFilter, 0, -1);
          if counterList <> nil then
            if High(counterList.list) > -1 then
              if counterList.list[0] <> nil then
              begin
                ENMetrologyCounterObj := TempENMetrologyCounter.getObject(counterList.list[0].code);

                if ENMetrologyCounterObj <> nil then
                  if ENMetrologyCounterObj.code > LOW_INT then
                  begin
                    frmENMetrologyCounterEdit := TfrmENMetrologyCounterEdit.Create(Application, dsEdit);
                    try
                      if frmENMetrologyCounterEdit.ShowModal = mrOk then
                      begin
                        //UpdateGrid(Sender);
                        Application.MessageBox(PChar('Тип лічильника збережений! Спробуйте ще раз додати та вибрати акт!'),
                                               PChar('Увага!'), MB_ICONINFORMATION);
                      end;
                    finally
                      frmENMetrologyCounterEdit.Free;
                      frmENMetrologyCounterEdit := nil;
                    end;
                  end; // if ENMetrologyCounterObj.code > LOW_INT
              end; // if counterList.list[0] <> nil
          /////// ******* ///////
          
          Exit;
        end;

        f.element.code := ElementInCode;
      end;
    end;
  //////////////////////////////////////////////////////////////////////////////

  f.statusRef := ENActStatusRef.Create;
  f.statusRef.code := ENACT_GOOD;

  // акты толко с МОЛами - мастерами ...
  workOrder := DMReports.getWorkOrderByPlanCode(ENPlanWorkObj.code);
  molData := DMReports.getMOLData(workOrder.code, FINMOLTYPE_MASTER);
  if molData = nil then
  begin
     Application.MessageBox(PChar('Необхідно додати МОЛа-Майстра ...!'),
             PChar('Увага!'), MB_ICONWARNING);
     pcPlanWork.ActivePage := tsWorkOrder;        
     exit;
  end;

  // ограничим по ДАТЕ !!! выполнения и АКТА !!!

  //f.conditionSQL := 'enact.dategen >=''' + + ''';

  f.conditionSQL:= '((enact.finmolcode is null) or (enact.finmolcode in (' +

' select finmoldata.finmolcode from ' +
'   FINMOLDATA, ' +
'   enworkorder2enplanwork ' +
'   where  ' +
'    enworkorder2enplanwork.workordercode = FINMOLDATA.workordercode ' +
'   and enworkorder2enplanwork.plancode = ' + IntToStr(ENPlanWorkObj.code) +
'   and finmoldata.moltyperefcode = '+ IntToStr(FINMOLTYPE_MASTER) +' )))' +

                  // ' enact.finmolcode = (select enworkorder.finmolcode from enworkorder, enworkorder2enplanwork where enworkorder2enplanwork.workordercode = enworkorder.code and enworkorder2enplanwork.plancode='+ IntToStr(ENPlanWorkObj.code) +')'
                  //+ 'and enact.finmechaniccode = (select enworkorder.finmechaniccode from enworkorder, enworkorder2enplanwork where enworkorder2enplanwork.workordercode = enworkorder.code and enworkorder2enplanwork.plancode='+ IntToStr(ENPlanWorkObj.code) +')'
                  ' and enact.dategen >= (select enworkorder.dategen from enworkorder, enworkorder2enplanwork where enworkorder2enplanwork.workordercode = enworkorder.code and enworkorder2enplanwork.plancode='+ IntToStr(ENPlanWorkObj.code) +')';


{
  f.conditionSQL:=' enact.finmolcode = (select enworkorder.finmolcode from enworkorder, enworkorder2enplanwork where enworkorder2enplanwork.workordercode = enworkorder.code and enworkorder2enplanwork.plancode='+ IntToStr(ENPlanWorkObj.code) +')'
                  + 'and enact.finmechaniccode = (select enworkorder.finmechaniccode from enworkorder, enworkorder2enplanwork where enworkorder2enplanwork.workordercode = enworkorder.code and enworkorder2enplanwork.plancode='+ IntToStr(ENPlanWorkObj.code) +')'
                  +' and enact.dategen >= (select enworkorder.dategen from enworkorder, enworkorder2enplanwork where enworkorder2enplanwork.workordercode = enworkorder.code and enworkorder2enplanwork.plancode='+ IntToStr(ENPlanWorkObj.code) +')';
}

{
  planFilter.conditionSQL := ' enplanwork.statuscode <> ' + IntToStr(ENPLANWORKSTATUS_OLDER)
                           + ' and enplanwork.code not in (select enact2enplanwork.plancode from enact2enplanwork)'
                           + ' and enplanwork.code in (select enworkorder2enplanwork.plancode from enworkorder2enplanwork, enworkorder where  enworkorder2enplanwork.workordercode = enworkorder.code '
                           + ' and enworkorder.finmolcode = '''+ ENActObj.finMolCode +'''' // MOL
                           + ' and enworkorder.finmechaniccode = '''+ ENActObj.finMechanicCode +'''' // MEHANIK ???
                           +')';

}
  // УЖЕ НЕ тАК !!!!!!!!!!!!!!! класификация измениться ... стэйт меняЕться у плана в момент развязки с АКТОМ !!!
  // или так ;) ..
  if element.typeRef.code = EN_ROUTE_BYT
  then
  f.conditionSQL:=f.conditionSQL+' and enact.elementcode in ('+
  ' select el.code from enelement el,enroutebyt rb where el.code=rb.elementcode '+
  ' and el.renrefcode='+IntToStr(element.renRef.code)+')'+
  ' and enact.dategen='+''''+XSDate2String(ENPlanWorkObj.dateStart)+'''';

  f.actTypeRef := ENPlanWorkStateRef.Create;
  f.actTypeRef.code := ENPlanWorkObj.stateRef.code;

  //TempENAct :=  HTTPRIOENAct as ENActControllerSoapPort;

   frmENActShow:=TfrmENActShow.Create(Application,fmNormal, f);
   try

      frmENActShow.planCode := ENPlanWorkObj.code;
      frmENActShow.isFiltered := true;

      if ENPlanWorkObj.stateRef <> nil then
      begin
        frmENActShow.planStateCode := ENPlanWorkObj.stateRef.code;
        frmENActShow.planStateName := edtWorkState.Text;
      end;

      with frmENActShow do begin
        DisableActions([ actEdit, actDelete , actFilter, actNoFilter]);
        if ShowModal = mrOk then
        begin
{
            try
              ///ENAct2ENPlanWorkObj.actRef.code := StrToInt(GetReturnValue(sgENAct,0));
              actCode := StrToInt(GetReturnValue(sgENAct,0));
              edtENActNumber.Text := GetReturnValue(sgENAct,1);
            except
               on EConvertError do  /// МАТЮКАТЬ ... и гнать развызывать руками !!!!
               begin
                   Application.MessageBox(PChar('Не вдалося отримати код акту!!! Оберіть акт знову ...'),
                          PChar('Помилка'),MB_ICONWARNING);
                   edtENActNumber.Text := 'НЕ ВЫБРАНО !!!';
                   Exit;
               end;
            end;


            {
             сохранить связь АКТА и плана (НПЗ)!!!!!
            при ошибке ОБЯЗАТЕЛЬНО сообщить юзеру .. пусть привяжет из АКТА ...
            }

        TempENAct2ENPlanWork := HTTPRIOENAct2ENPlanWork as ENAct2ENPlanWorkControllerSoapPort;

        if (actCode = LOW_INT) then
        begin
            a2:=ENAct2ENPlanWork.Create;
            a2.actRef := ENActRef.Create;
            a2.plan := ENPlanWork.Create;

            a2.code := LOW_INT;
            a2.plan.code := ENPlanWorkObj.code;
        end
        else
        begin
           act2planFilter := ENAct2ENPlanWorkFilter.Create();
           SetNullIntProps(act2planFilter);
           SetNullXSProps(act2planFilter);
           act2planFilter.actRef := ENActRef.Create;
           act2planFilter.actRef.code := actCode;
           act2planFilter.plan := ENPlanWork.Create;
           act2planFilter.plan.code := ENPlanWorkObj.code;
           a2List := TempENAct2ENPlanWork.getScrollableFilteredList(act2planFilter, 0, -1);
           if (a2List.totalCount = 1) then
           begin
             a2 :=  TempENAct2ENPlanWork.getObject( a2List.list[0].code );
           end
           else
           begin
             // ERRRoor !!!
             Application.MessageBox(PChar('Ошибка с кол-вом актов на ПЛАНЕ !!!'), PChar('Помилка !!'), MB_ICONERROR);
             exit;
           end;
        end;

            try
              a2.actRef.code := StrToInt(GetReturnValue(sgENAct,0)); // ГРИД !!!
              edtENActNumber.Text := GetReturnValue(sgENAct,1) + ' ' + GetReturnValue(sgENAct,3);
            except
               on EConvertError do  /// МАТЮКАТЬ ... и гнать развызывать руками !!!!
               begin
                   Application.MessageBox(PChar('Не вдалося отримати код акту!!! Оберіть акт знову ...'),
                          PChar('Помилка'),MB_ICONWARNING);
                   edtENActNumber.Text := 'НЕ ВЫБРАНО !!!';
                   Exit;
               end;
            end;

            // проверим типы-виды планов которые ВОЗМОЖНО висят на этом акте
            plansType := DMReports.getPlanTypeByActCode( StrToInt(GetReturnValue(sgENAct,0)) );   // ГРИД
            if  (plansType  > -1) and (plansType <> ENPlanWorkObj.typeRef.code )  then
            begin
                   Application.MessageBox(PChar('Не совпадают  ВИДЫ работ ...'),
                          PChar('Помилка'),MB_ICONWARNING);
                   edtENActNumber.Text := 'НЕ ВЫБРАНО !!!';
                   Exit;
            end;

            try

              if (a2.code = LOW_INT) then
               a2.code := TempENAct2ENPlanWork.add(a2, 1)
              else
              begin
               //TempENAct2ENPlanWork.save(a2);
               ShowMessage('Видаліть прив''язку до акту (зайдіть до акту), потім обирайте потрібний акт ... ');
               actCode := LOW_INT;
              end;
              actCode := a2.actRef.code; //ENAct2ENPlanWorkObj.code;

            except
               // гнать развязывать руками !!!!
                   Application.MessageBox(PChar('Не вдалося зв"язати акт з планом!!! Оберіть акт знову ...'),
                          PChar('Помилка'),MB_ICONWARNING);
                  edtENActNumber.Text := 'НЕ ВЫБРАНО !!!';
                  raise;
                  Exit;
           end;
        end;
      end;
   finally
      frmENActShow.Free;
   end;

end;

procedure TfrmENPlanWorkEdit.sgENEstimateItemClick(Sender: TObject);
var
  j, accountingTypeCode : Integer;
begin

     ClearGrid(sgFINMaterials);
     ClearGrid(sgSCCounterMaterials);
     ClearGrid(sgFINUnmount);
     ClearGrid(sgSCCounter);
     ClearGrid(sgFINRefinement);
     ClearGrid(sgFINProduced);
     ClearGrid(sgSCSeal);
     ClearGrid(sgCustomerMaterialsFin);

     // выведем список ФИН материалов .... если они есть ВААЩЕ ...
    try
      j := StrToInt( TAdvStringGrid(Sender).Cells[0, TAdvStringGrid(Sender).Row ]); //   (sgENEstimateItem,0));
    except
      on EConvertError do Exit;
    end;

  if Sender = sgENEstimateItem then
  begin
    accountingTypeCode := Integer(TAdvStringGrid(Sender).Objects[2, TAdvStringGrid(Sender).Row]);
    if accountingTypeCode = TK_ACCOUNTINGTYPE_TMC then
      updateFINMaterialsGrid(j, sgFINMaterials)
    else if accountingTypeCode = TK_ACCOUNTINGTYPE_COUNTER then
      updateSCCounterGrid(j, sgSCCounterMaterials);

    HideControls([gbSCCountersMaterials], accountingTypeCode <> TK_ACCOUNTINGTYPE_COUNTER);
    HideControls([gbFINMaterials], accountingTypeCode <> TK_ACCOUNTINGTYPE_TMC);

    updateMarksGrid(j, sgMarkers);
  end
  else
  if Sender = sgGSM then
  begin
    updateFINMaterialsGrid(j, sgFINGSM);
    //updateMarksGrid(j, sgMarkers);
  end
  else
  if Sender = sgDismount then
  begin
    accountingTypeCode := Integer(TAdvStringGrid(Sender).Objects[2, TAdvStringGrid(Sender).Row]);
    if accountingTypeCode = TK_ACCOUNTINGTYPE_TMC then
      updateFINMaterialsGrid(j, sgFINUnmount)
    else
    if accountingTypeCode = TK_ACCOUNTINGTYPE_COUNTER then
      updateSCCounterGrid(j, sgSCCounter)
    else
    if (accountingTypeCode = TK_ACCOUNTINGTYPE_SEAL) or
       (accountingTypeCode = TK_ACCOUNTINGTYPE_IMP) or
       (accountingTypeCode = TK_ACCOUNTINGTYPE_HOLO) then
      updateSCSealGrid(j);

    HideControls([gbSCCounter], accountingTypeCode <> TK_ACCOUNTINGTYPE_COUNTER);
    HideControls([gbFINUnmount], accountingTypeCode <> TK_ACCOUNTINGTYPE_TMC);
    HideControls([gbSCSeal], ((accountingTypeCode <> TK_ACCOUNTINGTYPE_SEAL) and
                              (accountingTypeCode <> TK_ACCOUNTINGTYPE_IMP) and
                              (accountingTypeCode <> TK_ACCOUNTINGTYPE_HOLO)));

    //updateMarksGrid(j, sgMarkers);
  end
  else
  if Sender = sgProduced then
  begin
    accountingTypeCode := Integer(TAdvStringGrid(Sender).Objects[2, TAdvStringGrid(Sender).Row]);
    if accountingTypeCode = TK_ACCOUNTINGTYPE_TMC then
      updateFINMaterialsGrid(j, sgFINProduced)

  end
  else
  if Sender = sgRefinement then
  begin
     updateFINMaterialsGrid(j, sgFINRefinement)
  end

  else
  if Sender = sgCustomerMaterials then
  begin
     updateFINMaterialsGrid(j, sgCustomerMaterialsFin)
  end;
end;

procedure TfrmENPlanWorkEdit.actMaterialBindingToFINExecute(
  Sender: TObject);
var
//   frmFINMaterialsDataEdit : TfrmFINMaterialsDataEdit;
   temp , molTypeCode, eItemCode : Integer;
   temp2 : real;
   grid: TAdvStringGrid;
   TempENEstimateItem: ENEstimateItemControllerSoapPort;
   TempENEstimateItemCounter : ENEstimateItemControllerSoapPort;
   eItem: ENEstimateItem;
   eItemCounter : ENEstimateItem;
 //  frmScanCountersShow:TfrmScanCountersShow;
   scObj:SCCounter;
   TempSCCounter : SCCounterControllerSoapPort;
   TempENPlanWork: ENPlanWorkControllerSoapPort;
   TempENElement: ENElementControllerSoapPort;
   TempENElementObj:ENElement;
   TempENPlanWorkObj:ENPlanWork;

   TempFINMolData: FINMolDataControllerSoapPort;
   TempFINMolDataFilter: FINMolDataFilter;
   TempFINMolDataShortList: FINMolDataShortList;
   kodMol:String;
   nameMol, bindedInvNumber :String;
   frmMaterialsSCCounter : TfrmMaterialsSCCounterEdit;
   SCCountersList : SCCounterShortList;
   countersCodesToRemove : SCCounterController.ArrayOfInteger;

   isSealsWriteOff: Boolean;
begin


if ((pcPlanWork.ActivePage = tsEstimateItems) or (pcPlanWork.ActivePage = tsCustomerMaterials))  then
 begin
  TempENEstimateItemCounter := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;
  TempSCCounter:= HTTPRIOSCCounter as SCCounterControllerSoapPort;

  try
    if pcPlanWork.ActivePage = tsCustomerMaterials then
      eItemCounter := TempENEstimateItemCounter.getObject(StrToInt(sgCustomerMaterials.Cells[0, sgCustomerMaterials.Row]))
    else
      eItemCounter := TempENEstimateItemCounter.getObject(StrToInt(sgENEstimateItemWithFin.Cells[0, sgENEstimateItemWithFin.Row]));
  except
    on EConvertError do Exit;
  end;

  TempENPlanWork:=HTTPRIOENPlanWork as ENPlanWorkControllerSoapPort;
  TempENPlanWorkObj:=TempENPlanWork.getObject(eItemCounter.planRef.code);

  TempENElement:=HTTPRIOENElement as ENElementControllerSoapPort;
  TempENElementObj:=TempENElement.getObject(TempENPlanWorkObj.elementRef.code);

  if TempENElementObj.elementInRef <> nil then
    if TempENElementObj.elementInRef.code = ENELEMENT_METROLOGY_OBJECT_WRITEOFF then
      isSealsWriteOff := true;

  if (TempENElementObj.typeRef.code = EN_PROM) or
     (TempENElementObj.typeRef.code = EN_SERVICES_OBJECT) or
     (ENPlanWorkObj.stateRef.code = ENPLANWORKSTATE_COUNTERS_WRITEOFF) or
     isSealsWriteOff then
  begin
    TempFINMolData:=HTTPRIOFINMolData as FINMolDataControllerSoapPort;
    TempFINMolDataFilter:=FINMolDataFilter.Create;
    SetNullIntProps(TempFINMolDataFilter);
    SetNullXSProps(TempFINMolDataFilter);
    TempFINMolDataFilter.conditionSQL:=' finmoldata.moltyperefcode=1 '+
    ' and finmoldata.workordercode in ('+
    ' select wo2p.workordercode '+
    ' from enworkorder2enplanwork wo2p '+
    ' where wo2p.plancode='+IntToStr(TempENPlanWorkObj.code) +')';
    TempFINMolDataShortList:=TempFINMolData.getScrollableFilteredList(TempFINMolDataFilter,0,1);

    if (HIGH(TempFINMolDataShortList.list)>=0) then begin
       kodMol:= TempFINMolDataShortList.list[0].finMolCode;
       nameMol := TempFINMolDataShortList.list[0].finMolName;
    end;
  end;
    /////
  // 31.05.16 NET-4530 Для ПЛОМБ привязка происходит из СМЕННЫХ ЗАДАНИЙ!!!!!
  // Почему переменная обозвана eItemCounter?? Там может быть все, что угодно, а не только счетчик!!!
  if (eItemCounter.accountingTypeRef.code = TK_ACCOUNTINGTYPE_SEAL) or
     (eItemCounter.accountingTypeRef.code = TK_ACCOUNTINGTYPE_IMP) or
     (eItemCounter.accountingTypeRef.code = TK_ACCOUNTINGTYPE_HOLO) then
  begin
    if isSealsWriteOff then
    begin
      if TempENPlanWorkObj.kind.code <> ENPLANWORKKIND_FACT then
      begin
        Application.MessageBox(PChar('NET-4561 Прив''язуйте пломби на Завданні-Факті!'),
                               PChar('Увага!'), MB_ICONWARNING);
        Exit;
      end;

      if Length(kodMol) = 0 then
      begin
        Application.MessageBox(PChar('Не задан МОЛ-мастер на наряде!!!'), PChar('Внимание!'), MB_ICONWARNING);
        Exit;
      end;

      frmSCSealsWriteOffEdit := TfrmSCSealsWriteOffEdit.Create(Application, dsInsert);
      try
        frmSCSealsWriteOffEdit.estimateItemCode := eItemCounter.code;

        frmSCSealsWriteOffEdit.molCode := kodMol;
        frmSCSealsWriteOffEdit.edtMOLCode.Text := kodMol;
        frmSCSealsWriteOffEdit.edtMOLName.Text := nameMol;

        frmSCSealsWriteOffEdit.DisableControls([frmSCSealsWriteOffEdit.edtMOLCode,
                                                frmSCSealsWriteOffEdit.edtMOLName,
                                                frmSCSealsWriteOffEdit.spbMOLName]);

        frmSCSealsWriteOffEdit.ShowModal;
      finally
        frmSCSealsWriteOffEdit.Free;
      end;

      actUpdateExecute(Sender);
      Exit;
    end;

    Application.MessageBox(PChar('NET-4530 Згідно наказу № 276 від 26.04.2016 ' +
                                 'прив''язку пломб потрібно виконувати на Змінному завданні!'),
                           PChar('Увага!'), MB_ICONWARNING);
    Exit;
  end;
  /////
  ///
 end;


 if (((eItemCounter.accountingTypeRef.code = TK_ACCOUNTINGTYPE_COUNTER) and
     ((TempENElementObj.typeRef.code = EN_PROM) or (TempENElementObj.typeRef.code = EN_SERVICES_OBJECT)))
     or (ENPlanWorkObj.stateRef.code = ENPLANWORKSTATE_COUNTERS_WRITEOFF)) then
    begin
      if (((eItemCounter.accountingTypeRef.code = TK_ACCOUNTINGTYPE_COUNTER) and
          ((TempENElementObj.typeRef.code = EN_PROM) or (TempENElementObj.typeRef.code = EN_SERVICES_OBJECT)))) then
      begin
        SCCountersList := Self.getSCCountersListByEstimate(StrToInt(sgENEstimateItemWithFin.Cells[0, sgENEstimateItemWithFin.Row]));
        if SCCountersList.totalCount > 0 then
        begin
          bindedInvNumber := SCCountersList.list[0].invNumber;
          if Application.MessageBox(PChar('На плані вже прив''язаний лічильник інв. № ' + bindedInvNumber + '. Бажаєте видалити цю прив''язку?'),
                                PChar('Внимание!'),
                                MB_ICONQUESTION + MB_OKCANCEL + MB_DEFBUTTON2) <> IDOK then
            begin
              Exit;
            end;
          SetLength(countersCodesToRemove, 1);
          countersCodesToRemove[0] := SCCountersList.list[0].code;
          TempSCCounter.removeCountersForBilling(countersCodesToRemove);
          Self.UpdateGrid(sender);
          showmessage ('Лічильник з інвентарним № ' + bindedInvNumber + '  видалено');
          Exit;
        end;

       frmScanCountersShow:=TfrmScanCountersShow.Create(Application,fmNormal);
        try
          ShowScanCounters.budgetCode:=TempENPlanWorkObj.budgetRef.code;
          molCode:=kodMol;
          with frmScanCountersShow do
            if ShowModal = mrOk then
            begin
              scObj:=SCCounter.create;
              scObj.invNumber:=GetReturnValue(sgScanCounters,2);
              scObj.name:=GetReturnValue(sgScanCounters,1);
              scObj.buildNumber:=GetReturnValue(sgScanCounters,3);
              scObj.account:=GetReturnValue(sgScanCounters,8);
              scObj.departmetFKCode:='0'+GetReturnValue(sgScanCounters,4);
              scObj.molCode:=GetReturnValue(sgScanCounters,9);
              scObj.dateIn:=TempENPlanWorkObj.dateStart;
              scObj.cost:=TXSDecimal.Create;
              scObj.cost.DecimalString:= GetReturnValue(sgScanCounters,7);
              scObj.statusRef := SCCounterStatusRef.Create;
              scObj.statusRef.code := SCCOUNTERSTATUS_GOOD;
              scObj.phasity:=TXSDecimal.Create;
              scObj.phasity.DecimalString := '1';
              scObj.estimateItemRef:=ENestimateItemRef.Create;
              scObj.estimateItemRef.code:=eItemCounter.code;
              scObj.kindRef:= SCCounterKindRef.Create;
              scObj.kindRef.code:= SCCOUNTERKIND_WORK_FROM_BILLING;
              scObj.scCode:=StrToInt(GetReturnValue(sgScanCounters,0));
              if Length(GetReturnValue(sgScanCounters,5)) > 0 then
                begin
                  scObj.dateBuild:=TXSDate.Create;
                  scObj.dateBuild.XSToNative(
                  GetXSDate(StrToDate(GetReturnValue(sgScanCounters,5))));
                  scObj.dateCheck:=TXSDate.Create;
                  scObj.dateCheck:=scObj.dateBuild;
                end;
                try
                  TempSCCounter.add(scObj);
                  Self.UpdateGrid(sender);
                  showmessage ('Лічильник привязано.');
                except
                    on EConvertError do Exit;
                end;
            end;
        finally
           frmScanCountersShow.Free;
        end;
      end
      else
      begin
        if ENPlanWorkObj.kind.code <> ENPLANWORKKIND_FACT then
        begin
          Application.MessageBox(PChar('Лічильники прив''язуються на завданні-факті!'), PChar('Внимание!'), MB_ICONWARNING);
          Exit;
        end;

        if Length(kodMol) = 0 then
        begin
          Application.MessageBox(PChar('Не задан МОЛ-мастер на наряде!!!'), PChar('Внимание!'), MB_ICONWARNING);
          Exit;
        end;

       frmMaterialsSCCounter := TfrmMaterialsSCCounterEdit.Create(Application, dsInsert);
       try
        frmMaterialsSCCounter.molName := nameMol;
        frmMaterialsSCCounter.molCode := kodMol;
        frmMaterialsSCCounter.estimateItemCode := StrToInt(sgENEstimateItemWithFin.Cells[0, sgENEstimateItemWithFin.Row]);
        frmMaterialsSCCounter.plan := ENPlanWorkObj;
        frmMaterialsSCCounter.ShowModal;
        // Апдейт грида после привязки счетчиков
        Self.UpdateGrid(sender);
       finally
            frmMaterialsSCCounter.Free;
       end;
      end;
  end
else
  begin
    EditFINMaterialsDataNew.workOrder := DMReports.getWorkOrderByPlanCode(ENPlanWorkObj.code);
    if EditFINMaterialsDataNew.workOrder.code = LOW_INT then
    begin
      Application.MessageBox(PChar('Введите НАРЯД ! Ничего НЕ сохранится !!!'), PChar('Внимание!'), MB_ICONWARNING);
      pcPlanWork.ActivePage := tsWorkOrder;
      pcPlanWorkChange(Sender);
      Exit;
    end;

    if (pcPlanWork.ActivePage = tsEstimateItems) or (pcPlanWork.ActivePage = tsCustomerMaterials)
    then molTypeCode := 1; //   (sgENEstimateItem,0));
    if pcPlanWork.ActivePage = tsGSM
    then molTypeCode := 2;  //   (sgENEstimateItem,0));

    EditFINMaterialsDataNew.molData := DMReports.getMOLData(EditFINMaterialsDataNew.workOrder.code, molTypeCode);
    if (EditFINMaterialsDataNew.molData = nil) then
    begin
      Application.MessageBox(PChar('Введите МОЛов !!! Ничего НЕ сохранится !!!'), PChar('Внимание!'), MB_ICONWARNING);
      pcPlanWork.ActivePage := tsWorkOrder;
      pcPlanWorkChange(Sender);
      Exit;
    end;

    if pcPlanWork.ActivePage = tsEstimateItems then
    begin
      if sgENEstimateItem.Visible then
        grid := sgENEstimateItem
      else if sgENEstimateItemWithFin.Visible then
        grid := sgENEstimateItemWithFin
      else
        raise Exception.Create('NET-4061 Unknown ENEstimateItem grid!');
    end;

    if pcPlanWork.ActivePage = tsGSM then
    begin
      if sgGSM.Visible then
        grid := sgGSM
      else if sgGSMWithFin.Visible then
        grid := sgGSMWithFin
      else
        raise Exception.Create('NET-4061 Unknown ENEstimateItem(GSM) grid!');
    end;

    temp2 := 0;
    ///// 06.02.13
    try
      if pcPlanWork.ActivePage = tsCustomerMaterials then // Эта вкладка работает по-старому
        temp2 := StrToFloat( sgCustomerMaterials.Cells[3, sgCustomerMaterials.Row ])
      else
      begin
        eItemCode := StrToInt(grid.Cells[0, grid.Row]);
        TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;
        eItem := TempENEstimateItem.getObject(eItemCode);

        if eItem.countFact <> nil then
          if eItem.countFact.DecimalString <> '' then
            temp2 := StrToFloat(eItem.countFact.DecimalString);
      end;
    except
      on EConvertError do Exit;
    end;
    /////
    if temp2 < 0.00000001 then
    begin
      Application.MessageBox(PChar('Кол-во материалов = 0 :) ... Откорректируйте кол-во материалов в работе'), PChar('Внимание!'), MB_ICONWARNING);
      Exit;
    end;
     frmFINMaterialsDataNewEdit:= TfrmFINMaterialsDataNewEdit.Create(Application,dsInsert);
     try
        frmFINMaterialsDataNewEdit.planCode := ENPlanWorkObj.code; // ENEstimateItemObj.planRef.code;
       // выведем список ФИН материалов .... если они есть ВААЩЕ ...
      try
        if pcPlanWork.ActivePage = tsEstimateItems then
        begin
          //temp := StrToInt( sgENEstimateItem.Cells[0, sgENEstimateItem.Row ]); //   (sgENEstimateItem,0));
          temp := StrToInt(grid.Cells[0, grid.Row]);
          frmFINMaterialsDataNewEdit.estimateItemKind := ENESTIMATEITEMKIND_MATERIALS;
        end;
        if pcPlanWork.ActivePage = tsGSM then
        begin
          //temp := StrToInt( sgGSM.Cells[0, sgGSM.Row ]);
          temp := StrToInt(grid.Cells[0, grid.Row]);
          frmFINMaterialsDataNewEdit.estimateItemKind := ENESTIMATEITEMKIND_GSM;
        end;
        if pcPlanWork.ActivePage = tsCustomerMaterials then
        begin
          temp := StrToInt( sgCustomerMaterials.Cells[0, sgCustomerMaterials.Row ]);
          frmFINMaterialsDataNewEdit.estimateItemKind := ENESTIMATEITEMKIND_CUSTOMER_MATERIALS;
        end;
      except
        on EConvertError do Exit;
      end;

      frmFINMaterialsDataNewEdit.estimateCode := temp; //ENEstimateItemObj.code;

      with frmFINMaterialsDataNewEdit do
      begin
        ShowModal ;// = mrOk then
        self.UpdateGrid(sender);
      end;

     finally
        frmFINMaterialsDataNewEdit.Free;
     end;
  end;
end;
		  

procedure TfrmENPlanWorkEdit.cbPlanWorkKindChange(Sender: TObject);
begin
  KindCode := cbPlanWorkKind.ItemIndex + 1;

  //HideControls([gbWorkOrder], not (KindCode in [ENPLANWORKKIND_NPZ, ENPLANWORKKIND_FACT]));
  tsWorkOrder.TabVisible := (KindCode in [ENPLANWORKKIND_NPZ, ENPLANWORKKIND_FACT]) and (DialogState <> dsInsert);

  //DenyBlankValues([edtWorkOrderNumber], KindCode = ENPLANWORKKIND_FACT);

  if (DialogState = dsInsert) then
  begin
    {if (KindCode in [ENPLANWORKKIND_NPZ, ENPLANWORKKIND_FACT]) then
      edtDateFinal.DateTime := edtDateStart.DateTime
    else
      edtDateFinal.DateTime := EncodeDate(YearOf(edtDateStart.DateTime), MonthOf(edtDateStart.DateTime), DaysInMonth(edtDateStart.DateTime));}
    edtDateStartChange(Sender);


    ///// 27.02.12 NET-1355 Запрещаем изменять дату окончания выполнения работ руками - будем рассчитывать сами
    // DisableControls([edtDateFinal], KindCode in [ENPLANWORKKIND_NPZ, ENPLANWORKKIND_FACT]);
    /////
  end;

  if KindCode in [ENPLANWORKKIND_NPZ, ENPLANWORKKIND_FACT] then
    lblDateFinal.Caption := 'Дата закінчення виконання робіт'
  else
    lblDateFinal.Caption := 'Розрахункова дата виконання робіт';

  {
  if KindCode = ENPLANWORKKIND_FACT then
  begin
    //tsPlanWork.Enabled := false;

    DisableControls(tsPlanWork);
    DisableControls([edtDateStart, spbENAct, gbWorkOrder, edtCommentGen, edtFINExecutorName, spbFINExecutor], false);

  end;
  }

  ///// 10.01.14 Поля "Раздел ИнвестПрограммы" и "Пункт ИнвестПрограммы" обязательные для всех подвидов работ по ИП!!!
  if (DialogState = dsInsert) or (DialogState = dsEdit) then
  begin
    // Только для Месячных планов
    if KindCode = ENPLANWORKKIND_CURRENT then
    begin
      if ENPlanWorkObj.typeRef <> nil then
      begin
        HideControls([gbInvestProgramGroups, spbInvestProgramGroups],
                      not (ENPlanWorkObj.typeRef.code in [ENPLANWORKTYPE_INVEST,
                                                          ENPLANWORKTYPE_SERVICES_FROM_SIDE_INVEST,
                                                          ENPLANWORKTYPE_ESBYT_PZ,
                                                          ENPLANWORKTYPE_ESBYT_ZKO_106,
                                                          ENPLANWORKTYPE_ESBYT_ZKO_111,
                                                          ENPLANWORKTYPE_ESBYT_ZKO_112]));



        // 04.02.14 Если планы по счетчикам и бюджетодержатель - ВРТУ, то это не ИнвестПрограмма, поэтому поля по ИП не показываем
        if ENPlanWorkObj.typeRef.code in [ENPLANWORKTYPE_ESBYT_PZ,
                                          ENPLANWORKTYPE_ESBYT_ZKO_106,
                                          ENPLANWORKTYPE_ESBYT_ZKO_111,
                                          ENPLANWORKTYPE_ESBYT_ZKO_112] then
          if ENPlanWorkObj.budgetRef <> nil then
            if ENPlanWorkObj.budgetRef.code = ENBUDGET_VRTUVD then
              HideControls([gbInvestProgramGroups, spbInvestProgramGroups]);

        DenyBlankValues([edtInvestProgramGroupsName, edtInvestItemNumber]);

         // для проэктирования пункты ИП не обязательны
           if((ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_INVEST ) and
               ( ENPlanWorkObj.stateRef.code = ENPLANWORKSTATE_DESIGNING )
              ) then
              DenyBlankValues([edtInvestProgramGroupsName, edtInvestItemNumber],false);

      end
      else
        HideControls([gbInvestProgramGroups, spbInvestProgramGroups]);
    end
    else
      HideControls([gbInvestProgramGroups, spbInvestProgramGroups]);
  end;
  /////
end;

procedure TfrmENPlanWorkEdit.spbFINExecutorClick(Sender: TObject);
var
   frmFINExecutorTreeShow: TfrmFINExecutorTreeShow;
 //  f : EditENPlanWorkStateFilter;

{
 function getFullExecutorName(node : TTreeNode) : String;
 var
   outStr : String;
   tmpNode : TTreeNode;
 begin
  tmpNode := node;
  outStr := '';
   while  tmpNode <> nil do
   begin

      if  FINExecutorShort(tmpNode.Data).finKindName <> '' then
      begin
          if length(outStr) = 0 then
            outStr := FINExecutorShort(tmpNode.Data).name
          else
            outStr := outStr + ' ' + FINExecutorShort(tmpNode.Data).name ;
      end;

      if tmpNode.Parent <> nil then
        if tmpNode.Parent.Level = 0 then
          break;

      tmpNode := tmpNode.Parent;
   end;

   result := outStr;
 end;
 // end getFullExecutorName
}

begin
   frmFINExecutorTreeShow:=TfrmFINExecutorTreeShow.Create(Application,fmNormal);
   try
      with frmFINExecutorTreeShow do begin
        DisableActions([ actEdit, actInsert ]);
        if ShowModal = mrOk then
        begin
            try
               {
               if ENPlanWorkObj.finExecutor = nil then ENPlanWorkObj.finExecutor := FINExecutor.Create();
               ENPlanWorkObj.finExecutor.code := LOW_INT;
               ENPlanWorkObj.finExecutor.name := getFullExecutorName(tvDep.Selected) ; //FINExecutorShort(tvDep.Selected.Data).name;
               ENPlanWorkObj.finExecutor.finCode := FINExecutorShort(tvDep.Selected.Data).finCode;
               ENPlanWorkObj.finExecutor.finTypeName := FINExecutorShort(tvDep.Selected.Data).finTypeName;
               ENPlanWorkObj.finExecutor.finTypeCode := FINExecutorShort(tvDep.Selected.Data).finTypeCode;
               ENPlanWorkObj.finExecutor.finKindName := FINExecutorShort(tvDep.Selected.Data).finKindName;
               ENPlanWorkObj.finExecutor.finKindCode := FINExecutorShort(tvDep.Selected.Data).finKindCode;
               ENPlanWorkObj.finExecutor.finCehName := FINExecutorShort(tvDep.Selected.Data).finCehName;
               ENPlanWorkObj.finExecutor.finCehCode := FINExecutorShort(tvDep.Selected.Data).finCehCode;
               }
               ENPlanWorkObj.finExecutor :=
                 DMReports.finExecutorShort2finExecutor(FINExecutorShort(tvDep.Selected.Data),
                                                        DMReports.getFullExecutorName(tvDep.Selected));

               edtFINExecutorName.Text := ENPlanWorkObj.finExecutor.name;
            except
               on EConvertError do Exit;
            end;
        end;
      end;
   finally
      frmFINExecutorTreeShow.Free;
   end;
end;


function TfrmENPlanWorkEdit.checkMaterialsBinding_(
  estimateItemCode: Integer): Boolean;
var
  TempFINMaterials: FINMaterialsControllerSoapPort;
  finList: FINMaterialsShortList;
  finFilter: FINMaterialsFilter;
begin
  Result := false;

  finFilter := FINMaterialsFilter.Create;
  SetNullIntProps(finFilter);
  SetNullXSProps(finFilter);

  finFilter.estimateItemRef := ENEstimateItemRef.Create;
  finFilter.estimateItemRef.code := estimateItemCode;

  finFilter.statusRef := FINMaterialsStatusRef.Create;
  finFilter.statusRef.code := FINMATERIALSSTATUS_GOOD;

  TempFINMaterials :=  HTTPRIOFINMaterials as FINMaterialsControllerSoapPort;

  finList := TempFINMaterials.getScrollableFilteredList(finFilter, 0, -1);

  if High(finList.list) > -1 then
    Result := true
  else
    Result := false;
end;

procedure TfrmENPlanWorkEdit.chkCauseDisconnectionClick(Sender: TObject);
begin

  // В Delphi даже программная устновка Checked вызывает onClick
  if not TWinControl(Sender).Focused then Exit;

  try
      setCauseDisconnection;
  finally
      verifyCauseDisconnection;
  end;

end;

procedure TfrmENPlanWorkEdit.setCauseDisconnection();
var
  TempENPlanWork: ENPlanWorkControllerSoapPort;
begin

  TempENPlanWork:=HTTPRIOENPlanWork as ENPlanWorkControllerSoapPort;

  if chkCauseDisconnection.Checked then
  begin
     TempENPlanWork.setCauseDisconnectionOn(ENPlanWorkObj.code);
     ENPlanWorkObj.causeDisconnection:= 1;
  end
  else
  begin
     TempENPlanWork.setCauseDisconnectionOff(ENPlanWorkObj.code);
     ENPlanWorkObj.causeDisconnection:= 0;
  end;

end;

procedure TfrmENPlanWorkEdit.verifyCauseDisconnection();
var
  TempENPlanWork: ENPlanWorkControllerSoapPort;
  PlanWorkTmpObj: ENPlanWork;
  chkStateInDB: Boolean;
begin

  chkStateInDB:=not chkCauseDisconnection.Checked;

  TempENPlanWork:=HTTPRIOENPlanWork as ENPlanWorkControllerSoapPort;

  try
      PlanWorkTmpObj:=TempENPlanWork.getObject(ENPlanWorkObj.code);
      ENPlanWorkObj.modify_time:=PlanWorkTmpObj.modify_time;
      ENPlanWorkObj.causeDisconnection:=PlanWorkTmpObj.causeDisconnection;
      if PlanWorkTmpObj.causeDisconnection=1 then
         chkStateInDB:=True
      else
         chkStateInDB:=false;
      FreeAndNil(PlanWorkTmpObj);
  finally
      if (chkCauseDisconnection.Checked<>chkStateInDB) then
      begin
         chkCauseDisconnection.OnClick:=nil;
         chkCauseDisconnection.Checked:= chkStateInDB;
         chkCauseDisconnection.OnClick:=chkCauseDisconnectionClick;
      end;
  end;

end;


procedure TfrmENPlanWorkEdit.Shape1MouseDown(Sender: TObject;
  Button: TMouseButton; Shift: TShiftState; X, Y: Integer);
var i: Integer;
begin
  Exit; // спрячем пока эту фишку с цветом

  ColorDialog1.Color := Shape1.Brush.Color;

  if ColorDialog1.Execute then
  begin
    Shape1.Brush.Color := ColorDialog1.Color;

    for i := 1 to sgENEstimateItem.RowCount - 1 do
      if Integer(sgENEstimateItem.Objects[1, i]) = 1 then
        sgENEstimateItem.RowColor[i] := ColorDialog1.Color
      else
        sgENEstimateItem.RowColor[i] := clWindow;
  end;
end;

procedure TfrmENPlanWorkEdit.btnCloseAllClick(Sender: TObject);
var
  TempENPlanWork: ENPlanWorkControllerSoapPort;

  planFilter : ENPlanWorkFilter;
  plan : ENPlanWork;
  planList : ENPlanWorkShortList;
  i : integer;


  TempENAct: ENActControllerSoapPort;
  actFilter : ENActFilter;
  actList : ENActShortList;

  finMatFilter : FINMaterialsFilter;
  TempFinMaterials : FINMaterialsControllerSoapPort;
  finList : FINMaterialsShortList;
  p : integer;
  frm : TfrmSCCounterEdit;
  TempCCOutageNotice : CCOutageNoticeControllerSoapPort;
  isExistsOutages:boolean;
  TempENServicesObject: ENServicesObjectControllerSoapPort;
   TempENIP: ENIPControllerSoapPort;
begin
{
  frm:=TfrmSCCounterEdit.Create(Application, dsEdit);
  frm.edtPlanCode.Text := IntToStr(ENPlanWorkObj.code);
  SCCounterObj := SCCounter.Create;
  SetNullXSProps(SCCounterObj);
  SetNullIntProps(SCCounterObj);

  try
    if frm.ShowModal= mrOk then
      begin
        //TempSCCounter.save(SCCounterObj);
        //UpdateGrid(Sender);
      end;
  finally
    frm.Free;
    frm:=nil;
  end;
}


  TempENPlanWork  := HTTPRIOENPlanWork as ENPlanWorkControllerSoapPort;
  TempENIP := HTTPRIOENIP as ENIPControllerSoapPort;
  //TempENPlanWork.getPlanStatusFromCN(-1);
  //Exit;

  // TempCCOutageNotice := HTTPRIOCCOutageNotice as CCOutageNoticeControllerSoapPort;
  // TempENServicesObject := HTTPRIOENServicesObject as ENServicesObjectControllerSoapPort;

  for i:= 0 to memoData.Lines.Count -1 do
    try
       p := StrToInt(trim(memoData.Lines[i]));


       TempENPlanWork.finishPlanWork(p); //getPlanStatusFromCN(planList.list[i].code);
       //TempENPlanWork.recalcPlanworkItemAndHumenItemsByPlanItemCode(p);
       //TempENPlanWork.recalcTotalTime(p); //getPlanStatusFromCN(planList.list[i].code);

       // isExistsOutages := TempCCOutageNotice.isExistsNoAgreedOutages(p);
       // if (isExistsOutages=True) then Continue;

       // TempENServicesObject.remove(p);

      // TempENPlanWork.closePlanWork(p); //getPlanStatusFromCN(planList.list[i].code);

       frmMain.sbMain.Panels[2].Text := IntToStr(i+1) + ' из ' + IntToStr(memoData.Lines.Count );
       Application.ProcessMessages;
       //TempENPlanWork.getPlanStatusFromCN(planList.list[i].code); //preConfirm(planList.list[i].code);
     except

      on E: ESOAPHTTPException do
      begin
        case ESOAPHTTPException(E).StatusCode of
          0:
            begin
              //Application.MessageBox(PChar('Нет связи ...'),
              //                       PChar('Ошибка!'),MB_ICONERROR+MB_OK);
              Exit;
            end;

          503:
            begin
              //Application.MessageBox(PChar('Служба недоступна'),
              //                       PChar('Ошибка!'),MB_ICONERROR+MB_OK);
              Exit;
            end;
        end;
       end;
       on E: ERemotableException do
       begin
         log.Lines.Add('#' + IntToStr(p) + '$');
         log2.lines.Add(IntToStr(p) + ' : ' + e.Message);
         Application.ProcessMessages;
       end;

       on E: Exception do
       begin
         log2.Lines.Add('error: ' + IntToStr(p) + ' : ' +  e.message);
         Exit;
       end;

    end;

end;

procedure TfrmENPlanWorkEdit.btnWorkOrderDetailsClick(Sender: TObject);
var
  TempENWorkOrder2ENPlanWork: ENWorkOrder2ENPlanWorkControllerSoapPort;
  i: Integer;
  ENWorkOrder2ENPlanWorkList: ENWorkOrder2ENPlanWorkShortList;
  ENWorkOrder2ENPlanWorkFilterObj : ENWorkOrder2ENPlanWorkFilter;

  frmENWorkOrderEdit : TfrmENWorkOrderEdit;
  TempENWorkOrder : ENWorkOrderControllerSoapPort;

begin

  ENWorkOrder2ENPlanWorkFilterObj := ENWorkOrder2ENPlanWorkFilter.Create;
  SetNullIntProps(ENWorkOrder2ENPlanWorkFilterObj);
  SetNullXSProps(ENWorkOrder2ENPlanWorkFilterObj);

  ENWorkOrder2ENPlanWorkFilterObj.plan := ENPlanWork.Create;
  ENWorkOrder2ENPlanWorkFilterObj.plan.code := ENPlanWorkObj.code;

  TempENWorkOrder2ENPlanWork :=  HTTPRIOENWorkOrder2ENPlanWork as ENWorkOrder2ENPlanWorkControllerSoapPort;

  ENWorkOrder2ENPlanWorkList := TempENWorkOrder2ENPlanWork.getScrollableFilteredList(ENWorkOrder2ENPlanWorkFilterObj, 0, -1);

   TempENWorkOrder := HTTPRIOENWorkOrder as ENWorkOrderControllerSoapPort;

  if ENWorkOrder2ENPlanWorkList.totalCount > 0 then
  begin
     try
       ENWorkOrderObj := TempENWorkOrder.getObject(ENWorkOrder2ENPlanWorkList.list[0].workOrderCode );
     except
     on EConvertError do Exit;
     end;
  end
  else
  begin
      ENWorkOrderObj := ENWorkOrder.Create;
      SetNullIntProps(ENWorkOrderObj);
      SetNullXSProps(ENWorkOrderObj);
      ENWorkOrderObj.department := ENDepartment.Create;
      ENWorkOrderObj.department.code := ENPlanWorkObj.departmentRef.code;
      //ENWorkOrderObj.department.name := edt ;//ENPlanWorkObj.departmentRef.code;
  end;

  frmENWorkOrderEdit:=TfrmENWorkOrderEdit.Create(Application, dsView);
  try
    frmENWorkOrderEdit.ShowModal;
  finally
    frmENWorkOrderEdit.Free;
    frmENWorkOrderEdit:=nil;
  end;

  
end;

procedure TfrmENPlanWorkEdit.actWorkOrderInsertExecute(Sender: TObject);
var
   TempENWorkOrder: ENWorkOrderControllerSoapPort;
   frmENWorkOrderEdit : TfrmENWorkOrderEdit;
   TempENWorkOrder2ENPlanWork : ENWorkOrder2ENPlanWorkControllerSoapPort;
begin
  //inherited;

    if (((ENPlanWorkObj.kind.code = ENPLANWORKKIND_FACT) or ( ENPlanWorkObj.kind.code = ENPLANWORKKIND_NPZ ))
        and (ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_TRANSPORT) and (ENPlanWorkObj.elementRef.code <> CARGO_OBJECT))
    then
    begin
       if not NoBlankValues([edtCommentGen])then
       begin
          Application.MessageBox(PChar('Введіть маршрут КУДИ ... !'),PChar('Увага !'),MB_ICONWARNING+MB_OK);
          Exit;
        end;
        //lblCommentGen.Caption := 'Маршрут КУДИ'
    end;

      
  WorkOrderEditState := dsInsert;

  ClearWorkOrderFields;

  ENWorkOrder2ENPlanWorkObj := ENWorkOrder2ENPlanWork.Create;
  ENWorkOrder2ENPlanWorkObj.code := LOW_INT;
  ENWorkOrder2ENPlanWorkObj.plan := ENPlanWork.Create;
  ENWorkOrder2ENPlanWorkObj.plan.code := ENPlanWorkObj.code;

  ENWorkOrder2ENPlanWorkObj.workOrder := ENWorkOrder.Create;
  ENWorkOrder2ENPlanWorkObj.workOrder.code := LOW_INT;

  ENWorkOrder2ENPlanWorkObj.workOrder.department := ENDepartment.Create;
  ENWorkOrder2ENPlanWorkObj.workOrder.department.code := ENPlanWorkObj.departmentRef.code;

  InitWorkOrderFields;

  btnWorkOrderSaveClick(Sender);

  actMOLDataInsertExecute(Sender);

  //if edtWorkOrderNumber.CanFocus then edtWorkOrderNumber.SetFocus;

{
  try
    frmENWorkOrderEdit:=TfrmENWorkOrderEdit.Create(Application, dsInsert);
    try
      if frmENWorkOrderEdit.ShowModal = mrOk then
      begin
        if ENWorkOrderObj<>nil then
            //TempENWorkOrder.add(ENWorkOrderObj);
            //UpdateGrid(Sender);
            LoadWorkOrder;
      end;
    finally
      frmENWorkOrderEdit.Free;
      frmENWorkOrderEdit:=nil;
    end;
  finally
    ENWorkOrder2ENPlanWorkObj.Free;
  end;
}  
end;

procedure TfrmENPlanWorkEdit.actWorkOrderEditExecute(Sender: TObject);
var
   TempENWorkOrder: ENWorkOrderControllerSoapPort;
   frmENWorkOrderEdit : TfrmENWorkOrderEdit;
   TempENWorkOrder2ENPlanWork : ENWorkOrder2ENPlanWorkControllerSoapPort;

   ENWorkOrder2ENPlanWorkFilterObj : ENWorkOrder2ENPlanWorkFilter;
   ENWorkOrder2ENPlanWorkList : ENWorkOrder2ENPlanWorkShortList;
begin
  //inherited;
  WorkOrderEditState := dsEdit;

  ENWorkOrder2ENPlanWorkFilterObj := ENWorkOrder2ENPlanWorkFilter.Create;
  SetNullIntProps(ENWorkOrder2ENPlanWorkFilterObj);
  SetNullXSProps(ENWorkOrder2ENPlanWorkFilterObj);

  ENWorkOrder2ENPlanWorkFilterObj.plan := ENPlanWork.Create;
  ENWorkOrder2ENPlanWorkFilterObj.plan.code := ENPlanWorkObj.code;

  TempENWorkOrder2ENPlanWork :=  HTTPRIOENWorkOrder2ENPlanWork as ENWorkOrder2ENPlanWorkControllerSoapPort;

  ENWorkOrder2ENPlanWorkList := TempENWorkOrder2ENPlanWork.getScrollableFilteredList(ENWorkOrder2ENPlanWorkFilterObj, 0, -1);

  // TempENWorkOrder := HTTPRIOENWorkOrder as ENWorkOrderControllerSoapPort;

   // если не найден - добавление .. по идее залочить вызов !!!
  if ENWorkOrder2ENPlanWorkList.totalCount = 0 then
  begin
      actWorkOrderInsertExecute(Sender);
      exit;
  end;

  // редактируем ...
  // TempENWorkOrder := HTTPRIOENWorkOrder as ENWorkOrderControllerSoapPort;
  ENWorkOrder2ENPlanWorkObj :=  TempENWorkOrder2ENPlanWork.getObject(ENWorkOrder2ENPlanWorkList.list[0].code);

  InitWorkOrderFields;

  //if edtWorkOrderNumber.CanFocus then edtWorkOrderNumber.SetFocus;
  
{
  try
    frmENWorkOrderEdit:=TfrmENWorkOrderEdit.Create(Application, dsEdit);
    try
      if frmENWorkOrderEdit.ShowModal = mrOk then
      begin
        if ENWorkOrderObj<>nil then
          //TempENWorkOrder.add(ENWorkOrderObj);
          //UpdateGrid(Sender);
          LoadWorkOrder;
      end;
    finally
      frmENWorkOrderEdit.Free;
      frmENWorkOrderEdit:=nil;
    end;
  finally
    ENWorkOrder2ENPlanWorkObj.Free;
  end;
}  
end;

procedure TfrmENPlanWorkEdit.LoadMOLData(workOrderCode : Integer);
var
  i,j : Integer;
  f : FINMOLDataFilter;
  TempFINMolData: FINMolDataControllerSoapPort;
  FINMolDataList: FINMolDataShortList;
begin
   for i := 1 to  sgFINMolData.RowCount - 1 do
     for j := 0 to sgFINMOLData.ColCount - 1 do
       sgFINMolData.Cells[j,i] := '';
   sgFINMolData.RowCount := 2;

   if workOrderCode <= 0 then Exit;

   f := FINMOLDataFilter.Create;
   SetNullIntProps(f);
   SetNullXSProps(f);
   //f.conditionSQL := 'finmoldata.code in (select FINDOC2MOLDATA.MOLDATACODE from FINDOC2MOLDATA where FINDOC2MOLDATA.WORKORDERCODE = '+ IntToStr(workOrderCode)+')';
   f.workOrder := ENWorkOrder.Create;
   f.workOrder.code :=  workOrderCode;
   f.orderBySQL := 'finmoldata.moltyperefcode';

   TempFINMolData :=  HTTPRIOFINMolData as FINMolDataControllerSoapPort;
   FINMolDataList :=  TempFINMolData.getScrollableFilteredList(f, 0, -1);

  if High(FINMolDataList.list) > -1 then
     sgFINMolData.RowCount:=High(FINMolDataList.list)+2
  else
     sgFINMolData.RowCount:=2;

   with sgFINMolData do
    for i:=0 to High(FINMolDataList.list) do
      begin
        Application.ProcessMessages;
        if FINMolDataList.list[i].code <> Low(Integer) then
        Cells[0,i+1] := IntToStr(FINMolDataList.list[i].code)
        else
        Cells[0,i+1] := '';
        Cells[1,i+1] := FINMolDataList.list[i].finMolCode;
        Cells[2,i+1] := FINMolDataList.list[i].finMolName;
        Cells[3, i+1] := FINMolDataList.list[i].phoneNumber;
        Cells[4,i+1] := FINMolDataList.list[i].molTypeRefName;

        sgFINMolData.RowCount:=High(FINMolDataList.list)+2;
      end;
   sgFINMolData.Row:=1;

end;


procedure TfrmENPlanWorkEdit.LoadWorkOrder;
var
  TempENWorkOrder2ENPlanWork: ENWorkOrder2ENPlanWorkControllerSoapPort;

  ENWorkOrder2ENPlanWorkList: ENWorkOrder2ENPlanWorkShortList;
  ENWorkOrder2ENPlanWorkFilterObj: ENWorkOrder2ENPlanWorkFilter;

  frmENWorkOrderEdit: TfrmENWorkOrderEdit;
  TempENWorkOrder: ENWorkOrderControllerSoapPort;
begin

      ClearWorkOrderFields;

      ENWorkOrder2ENPlanWorkFilterObj := ENWorkOrder2ENPlanWorkFilter.Create;
      SetNullIntProps(ENWorkOrder2ENPlanWorkFilterObj);
      SetNullXSProps(ENWorkOrder2ENPlanWorkFilterObj);

      ENWorkOrder2ENPlanWorkFilterObj.plan := ENPlanWork.Create;
      ENWorkOrder2ENPlanWorkFilterObj.plan.code := ENPlanWorkObj.code;

      TempENWorkOrder2ENPlanWork :=  HTTPRIOENWorkOrder2ENPlanWork as ENWorkOrder2ENPlanWorkControllerSoapPort;

      ENWorkOrder2ENPlanWorkList := TempENWorkOrder2ENPlanWork.getScrollableFilteredList(ENWorkOrder2ENPlanWorkFilterObj, 0, -1);

      TempENWorkOrder := HTTPRIOENWorkOrder as ENWorkOrderControllerSoapPort;

      if ENWorkOrder2ENPlanWorkList.totalCount > 0 then
      begin // Если есть наряд
        DisableActions([actWorkOrderInsert]);
        DisableActions([actWorkOrderView, actWorkOrderEdit, actWorkOrderDelete], (DialogState = dsView));

        //DisableControls([gbMOLData], (DialogState = dsView));
        DisableActions([actMOLDataInsert, actMOLDataEdit, actMOLDataDelete], (DialogState = dsView));

        edtWorkOrderNumber.Text := ENWorkOrder2ENPlanWorkList.list[0].workOrderWorkOrderNumber;
        if ENWorkOrder2ENPlanWorkList.list[0].workOrderDateGen <> nil then
          edtDateWorkOrder.DateTime := EncodeDate(ENWorkOrder2ENPlanWorkList.list[0].workOrderDateGen.Year,
                                                  ENWorkOrder2ENPlanWorkList.list[0].workOrderDateGen.Month,
                                                  ENWorkOrder2ENPlanWorkList.list[0].workOrderDateGen.Day);

        //edtWorkOrderCommentGen.Text := ENWorkOrder2ENPlanWorkList.list[0].
        ///
        ENWorkOrderObj := TempENWorkOrder.getObject(ENWorkOrder2ENPlanWorkList.list[0].workOrderCode);
        if ENWorkOrderObj <> nil then
          if ENWorkOrderObj.code > LOW_INT then
          begin
            edtFinMolCode.Text := ENWorkOrderObj.finMolCode;
            edtFinMechanicCode.Text := ENWorkOrderObj.finMechanicCode;
            MakeMultiline(edtWorkOrderCommentGen.Lines, ENWorkOrderObj.commentGen);
            MakeMultiline(edtDefectShortDescription.Lines, ENWorkOrderObj.defectShortDesc);
            edtWorkOrderCode.Text := IntToStr(ENWorkOrderObj.code);
            // MOLDATA2
            DisableControls([edtWorkOrderCode]);
            LoadMOLData(ENWorkOrderObj.code);
          end;
        ///


        edtFinMolName.Text := ENWorkOrder2ENPlanWorkList.list[0].workOrderFinMolName;
        edtFinMechanicName.Text := ENWorkOrder2ENPlanWorkList.list[0].workOrderFinMechanicName;



{
         try
           ENWorkOrderObj := TempENWorkOrder.getObject(ENWorkOrder2ENPlanWorkList.list[0].workOrderCode );
         except
         on EConvertError do Exit;
         end;
}
      end
      else
      begin // Если наряда еще нет
        // оставляем только добавление
        if DialogState <> dsView then
          DisableActions([actWorkOrderInsert], false);
        DisableActions([actWorkOrderView, actWorkOrderEdit, actWorkOrderDelete]);

        /// Дату наряда-задания берем из даты начала выполнения работ по НПЗ
        edtDateWorkOrder.Date := edtDateStart.Date;
        edtDateWorkOrder.Checked := true;
        DisableControls([edtDateWorkOrder]);

        LoadMOLData(LOW_INT);
        
        ///

{          ENWorkOrderObj := ENWorkOrder.Create;
          SetNullIntProps(ENWorkOrderObj);
          SetNullXSProps(ENWorkOrderObj);
          ENWorkOrderObj.department := ENDepartment.Create;
          ENWorkOrderObj.department.code := ENPlanWorkObj.departmentRef.code;
          //ENWorkOrderObj.department.name := edt ;//ENPlanWorkObj.departmentRef.code;
}
      end;
{
      frmENWorkOrderEdit:=TfrmENWorkOrderEdit.Create(Application, dsView);
      try
        frmENWorkOrderEdit.ShowModal;
      finally
        frmENWorkOrderEdit.Free;
        frmENWorkOrderEdit:=nil;
      end;
}
end;

procedure TfrmENPlanWorkEdit.actWorkOrderUpdateExecute(Sender: TObject);
begin
  LoadWorkOrder;
end;

procedure TfrmENPlanWorkEdit.spbFINMolClick(Sender: TObject);
var
 f : FINMolFilter;
 frmFINMolShow : TfrmFINMolShow;

   TempENDepartment2EPRen: ENDepartment2EPRenControllerSoapPort;
  i: Integer;
  ENDepartment2EPRenList: ENDepartment2EPRenShortList;
  renFilter : ENDepartment2EPRenFilter;
  renList : ENDepartment2EPRenShortList;
  //plan : ENPlanWork;


  molSel : boolean;
begin
 f := FINMolFilter.Create;
 SetNullXSProps(f);
 SetNullIntProps(f);
 f.state := 1; // типа только работающие ...

      // РЭСы и МОЛы
      //plan := DMReports.getPlanByCode(planCode);

   if ENWorkOrder2ENPlanWorkObj.workOrder.department <> nil then
     if ENWorkOrder2ENPlanWorkObj.workOrder.department.code <> LOW_INT then
     begin
        TempENDepartment2EPRen :=  HTTPRIOENDepartment2EPRen as ENDepartment2EPRenControllerSoapPort;
        renFilter := ENDepartment2EPRenFilter.Create;
        SetNullXSProps(renFilter);
        SetNullIntProps(renFilter);
        renFilter.departmentRef := ENDepartmentRef.Create;
        renFilter.departmentRef.code :=  ENWorkOrder2ENPlanWorkObj.workOrder.department.code;

        renList := TempENDepartment2EPRen.getScrollableFilteredList(renFilter,0,-1);
        if renList.totalCount > 0 then
          f.conditionSQL := 'substr(UMC_DBA.TDIVISION.ID,1,2) = ' + IntToStr(renList.list[0].finRenCode) ;
      end;

   frmFINMolShow:=TfrmFINMolShow.Create(Application,fmNormal, f);

   try

      if length(f.conditionSQL) > 0 then
        frmFINMolShow.isFiltered := true;

      with frmFINMolShow do begin
        DisableActions([ actEdit, actInsert ]);
        if ShowModal = mrOk then
        begin
            try
               edtFinMolCode.Text := GetReturnValue(sgFINMol,0);
               edtFINMolName.Text := GetReturnValue(sgFINMol,1); //ENDepartmentShort(tvDep.Selected.Data).shortName;
               ENWorkOrder2ENPlanWorkObj.workOrder.finMolCode := GetReturnValue(sgFINMol,0);
               ENWorkOrder2ENPlanWorkObj.workOrder.finMolName := GetReturnValue(sgFINMol,1);
            except
               on EConvertError do Exit;
            end;
        end;
      end;
   finally
      frmFINMolShow.Free;
   end;
 {

 if not Assigned(frmFINMolShow) then
    frmFINMolShow := TfrmFINMolShow.Create(Application,fmChild,f);
 frmFINMolShow.Show;
 }


end;

procedure TfrmENPlanWorkEdit.InitWorkOrderFields;
begin
  DisableControls([edtWorkOrderCode]);
  
  if (WorkOrderEditState = dsInsert) or (WorkOrderEditState = dsEdit) then
  begin
    //DisableActions([actWorkOrderInsert, actWorkOrderEdit, actWorkOrderDelete, actWorkOrderUpdate]);
    DisableControls([edtFinMolCode, edtFinMolName, edtFinMechanicCode, edtFinMechanicName, edtWorkOrderNumber]);

    HideControls([btnWorkOrderSave, btnWorkOrderCancel], false);

    DisableControls([spbFINMol, spbFINMechanic], false);//, (WorkOrderEditState = dsEdit));
    DisableControls([{edtWorkOrderNumber,} {edtDateWorkOrder,} edtWorkOrderCommentGen, edtDefectShortDescription], false);

    DenyBlankValues([
      //edtFinMolCode,
      //edtWorkOrderNumber,
      edtDateGen,
      edtFinMolName,
      edtFinMechanicName
     ]);    
  end
  else begin
    //DisableActions([actWorkOrderInsert, actWorkOrderEdit, actWorkOrderDelete]);
    DisableControls([edtWorkOrderNumber, edtDateWorkOrder, edtWorkOrderCommentGen,
                     edtFinMolCode, edtFinMolName, edtFinMechanicCode, edtFinMechanicName, edtDefectShortDescription]);
    
    HideControls([btnWorkOrderSave, btnWorkOrderCancel]);
    DisableControls([spbFINMol, spbFINMechanic]);
  end;
end;

procedure TfrmENPlanWorkEdit.btnWorkOrderSaveClick(Sender: TObject);
var
   TempENWorkOrder: ENWorkOrderControllerSoapPort;
   TempENWorkOrder2ENPlanWork : ENWorkOrder2ENPlanWorkControllerSoapPort;
begin
  if not NoBlankValues([
      //edtFinMolCode,
      //edtWorkOrderNumber,
      edtDateWorkOrder
      //,edtFinMolName
      //edtFinMechanicName
     ])  then
  begin
      Application.MessageBox(PChar('Пустые поля недопустимы !'),PChar('Внимание !'),MB_ICONWARNING+MB_OK);
      Exit;
  end
  else
  begin
    //TempENWorkOrder := HTTPRIOENWorkOrder as ENWorkOrderControllerSoapPort;


     //ENWorkOrder2ENPlanWorkObj.workOrder.workOrderNumber := edtWorkOrderNumber.Text;

     if edtDateWorkOrder.Checked then
     begin 
       if ENWorkOrder2ENPlanWorkObj.workOrder.dateGen = nil then
          ENWorkOrder2ENPlanWorkObj.workOrder.dateGen := TXSDate.Create;
       ENWorkOrder2ENPlanWorkObj.workOrder.dateGen.XSToNative(GetXSDate(edtDateWorkOrder.DateTime));
     end
     else
       ENWorkOrder2ENPlanWorkObj.workOrder.dateGen := nil;

     ENWorkOrder2ENPlanWorkObj.workOrder.commentGen := edtWorkOrderCommentGen.Text;

     ENWorkOrder2ENPlanWorkObj.workOrder.finMolCode := edtFinMolCode.Text;

     ENWorkOrder2ENPlanWorkObj.workOrder.finMolName := edtFinMolName.Text;

     ENWorkOrder2ENPlanWorkObj.workOrder.defectShortDesc := edtDefectShortDescription.Text;
{
     ENWorkOrder2ENPlanWorkObj.workOrder.finMechanicCode := edtFinMechanicCode.Text;

     ENWorkOrder2ENPlanWorkObj.workOrder.finMechanicName := edtFinMechanicName.Text;
}
     //.userGen := edtUserGen.Text;
     {
     if edtdateEdit.checked then
     begin
       if ENWorkOrderObj.dateEdit = nil then
          ENWorkOrderObj.dateEdit := TXSDate.Create;
       ENWorkOrderObj.dateEdit.XSToNative(GetXSDate(edtdateEdit.DateTime));
     end
     else
       ENWorkOrderObj.dateEdit := nil;
     }

    TempENWorkOrder2ENPlanWork := HTTPRIOENWorkOrder2ENPlanWork as ENWorkOrder2ENPlanWorkControllerSoapPort;

    if WorkOrderEditState = dsInsert then
    begin
      //ENWorkOrderObj.code:=low(Integer);
      //TempENWorkOrder.add(ENWorkOrderObj);
      //TempENWorkOrder2ENPlanWork.add(ENWorkOrder2ENPlanWorkObj);
      TempENWorkOrder2ENPlanWork.addWithCheck(ENWorkOrder2ENPlanWorkObj,false); {SUPP-104240}
    end
    else
    if WorkOrderEditState = dsEdit then
    begin
      TempENWorkOrder2ENPlanWork.save(ENWorkOrder2ENPlanWorkObj);
    end;

    Application.MessageBox(PChar('Наряд-завдання збережено!'), PChar('Інформація'), MB_ICONINFORMATION);

    LoadWorkOrder;
    WorkOrderEditState := dsView;
    InitWorkOrderFields;
  end;
end;

procedure TfrmENPlanWorkEdit.btnWorkOrderCancelClick(Sender: TObject);
begin
  ClearWorkOrderFields;
  LoadWorkOrder;
  WorkOrderEditState := dsView;
  InitWorkOrderFields;
end;

procedure TfrmENPlanWorkEdit.ClearWorkOrderFields;
begin
  ClearControlChildren(gbWorkOrder);
  //edtDateWorkOrder.Date := Date;
  //edtDateWorkOrder.Checked := false;
end;

procedure TfrmENPlanWorkEdit.spbFINMechanicClick(Sender: TObject);
var
 f : FINMolFilter;
 frmFINMolShow : TfrmFINMolShow;

   TempENDepartment2EPRen: ENDepartment2EPRenControllerSoapPort;
  i: Integer;
  ENDepartment2EPRenList: ENDepartment2EPRenShortList;
  renFilter : ENDepartment2EPRenFilter;
  renList : ENDepartment2EPRenShortList;
  //plan : ENPlanWork;


  molSel : boolean;
begin
 f := FINMolFilter.Create;
 SetNullXSProps(f);
 SetNullIntProps(f);
 f.state := 1; // типа только работающие ...

      // РЭСы и МОЛы
      //plan := DMReports.getPlanByCode(planCode);

   if ENWorkOrder2ENPlanWorkObj.workOrder.department <> nil then
     if ENWorkOrder2ENPlanWorkObj.workOrder.department.code <> LOW_INT then
     begin
        TempENDepartment2EPRen :=  HTTPRIOENDepartment2EPRen as ENDepartment2EPRenControllerSoapPort;
        renFilter := ENDepartment2EPRenFilter.Create;
        SetNullXSProps(renFilter);
        SetNullIntProps(renFilter);
        //renFilter.renRef := EPRenRef.Create;
        //renFilter.renRef.code := renCode; //plan.renRef.code;
        renFilter.departmentRef := ENDepartmentRef.Create;
        renFilter.departmentRef.code :=  ENWorkOrder2ENPlanWorkObj.workOrder.department.code;

        renList := TempENDepartment2EPRen.getScrollableFilteredList(renFilter,0,-1);
        if renList.totalCount > 0 then
          f.conditionSQL := 'substr(UMC_DBA.TDIVISION.ID,1,2) = ' + IntToStr(renList.list[0].finRenCode) ;
      end;

   frmFINMolShow:=TfrmFINMolShow.Create(Application,fmNormal, f);
   try
   
      if length(f.conditionSQL) > 0 then
        frmFINMolShow.isFiltered := true;

      with frmFINMolShow do begin
        DisableActions([ actEdit, actInsert ]);
        if ShowModal = mrOk then
        begin
            try
               edtFinMechanicCode.Text:= GetReturnValue(sgFINMol,0);
               edtFinMechanicName.Text:= GetReturnValue(sgFINMol,1); //ENDepartmentShort(tvDep.Selected.Data).shortName;
               ENWorkOrder2ENPlanWorkObj.workOrder.finMechanicCode := GetReturnValue(sgFINMol,0);
               ENWorkOrder2ENPlanWorkObj.workOrder.finMechanicName := GetReturnValue(sgFINMol,1);
            except
               on EConvertError do Exit;
            end;
        end;
      end;
   finally
      frmFINMolShow.Free;
   end;
 {

 if not Assigned(frmFINMolShow) then
    frmFINMolShow := TfrmFINMolShow.Create(Application,fmChild,f);
 frmFINMolShow.Show;
 }

end;

procedure TfrmENPlanWorkEdit.pcPlanWorkChanging(Sender: TObject;
  var AllowChange: Boolean);
var answer: Integer;
begin
  AllowChange := true;

  if pcPlanWork.ActivePage = tsWorkOrder then

    if WorkOrderEditState <> dsView then
    begin
      //ShowMessage('КУДА ????????????');
      answer := Application.MessageBox(PChar('Зберегти наряд-завдання?'),
                                       PChar('Увага!'),
                                       MB_ICONQUESTION + MB_YESNOCANCEL + MB_DEFBUTTON3);

      case answer of
        IDYES: btnWorkOrderSaveClick(Sender);
        IDNO:  btnWorkOrderCancelClick(Sender);
        else begin
          AllowChange := false;
          Exit;
        end;
      end;

      AllowChange := (WorkOrderEditState = dsView);
    end;


end;

procedure TfrmENPlanWorkEdit.actWorkOrderDeleteExecute(Sender: TObject);
var
   TempENWorkOrder: ENWorkOrderControllerSoapPort;
   frmENWorkOrderEdit : TfrmENWorkOrderEdit;
   TempENWorkOrder2ENPlanWork : ENWorkOrder2ENPlanWorkControllerSoapPort;

  ENWorkOrder2ENPlanWorkFilterObj : ENWorkOrder2ENPlanWorkFilter;
  ENWorkOrder2ENPlanWorkList : ENWorkOrder2ENPlanWorkShortList ;

  finList: FINMaterialsShortList; //List_;
  finFilter : FINMaterialsFilter;
  TempFINMaterials: FINMaterialsControllerSoapPort;
  answer: Integer;
begin

// на Факте нельзя .. из за связей с ФинКол у плана на этом наряде ....
{
      if ENPlanWorkObj.kind.code = ENPLANWORKKIND_FACT then
      begin
        Application.MessageBox(PChar('На Завданні-Факт наряд-завдання НЕ ВИДАЛЯЄТЬСЯ !!!'), PChar('Увага!'), MB_ICONERROR);
        Exit;
      end;
}
  ENWorkOrder2ENPlanWorkFilterObj := ENWorkOrder2ENPlanWorkFilter.Create;
  SetNullIntProps(ENWorkOrder2ENPlanWorkFilterObj);
  SetNullXSProps(ENWorkOrder2ENPlanWorkFilterObj);

  ENWorkOrder2ENPlanWorkFilterObj.plan := ENPlanWork.Create;
  ENWorkOrder2ENPlanWorkFilterObj.plan.code := ENPlanWorkObj.code;

  TempENWorkOrder2ENPlanWork :=  HTTPRIOENWorkOrder2ENPlanWork as ENWorkOrder2ENPlanWorkControllerSoapPort;

  ENWorkOrder2ENPlanWorkList := TempENWorkOrder2ENPlanWork.getScrollableFilteredList(ENWorkOrder2ENPlanWorkFilterObj, 0, -1);

  //ENWorkOrder2ENPlanWorkList := TempENWorkOrder2ENPlanWork.getScrollableFilteredList(ENWorkOrder2ENPlanWorkFilterObj, 0, -1);

  // TempENWorkOrder := HTTPRIOENWorkOrder as ENWorkOrderControllerSoapPort;

  if ENWorkOrder2ENPlanWorkList.totalCount > 0 then
  begin

      answer := Application.MessageBox(PChar('Видалити наряд-завдання?'),
                                       PChar('Увага!'),
                                       MB_ICONQUESTION + MB_YESNO + MB_DEFBUTTON2);
      if answer = IDYES then
      begin
        // проверим есть ли развязанные матералы ...
        finFilter := FINMaterialsFilter.Create;
        SetNullIntProps(finFilter);
        SetNullXSProps(finFilter);
        //finFilter.estimateItemRef := ENEstimateItemRef.Create;
        //finFilter.estimateItemRef.code := estimateItemCode;

        finFilter.statusRef := FINMaterialsStatusRef.Create;
        finFilter.statusRef.code := FINMATERIALSSTATUS_GOOD;
        finFilter.conditionSQL := ' finmaterials.estimateitemrefcode in (select enestimateitem.code from enestimateitem where enestimateitem.planrefcode = '+ IntToStr(ENPlanWorkObj.code)+')';

        TempFINMaterials :=  HTTPRIOFINMaterials as FINMaterialsControllerSoapPort;

        finList := TempFINMaterials.getScrollableFilteredList(finFilter,0,-1);
        if finList.totalCount > 0 then
        begin
          answer := Application.MessageBox(PChar('Вже є зарезервовані МАТЕРІАЛИ !!! Видалення призведе до видалення привязки! Все одно видалити НАРЯД?'),
                                           PChar('Увага!'),
                                           MB_ICONQUESTION + MB_YESNO + MB_DEFBUTTON2);
          if answer = IDYES then
            TempENWorkOrder2ENPlanWork.remove( ENWorkOrder2ENPlanWorkList.list[0].code );
        end
        else
        begin
        // нет развязки .. просто в сад
          TempENWorkOrder2ENPlanWork.remove( ENWorkOrder2ENPlanWorkList.list[0].code );
        end;
      // обновить ....
      LoadWorkOrder;
      end;
  end;

end;

procedure TfrmENPlanWorkEdit.cbShowAllClick(Sender: TObject);
begin
  inherited;

  UpdateGrid(Sender);

end;

procedure TfrmENPlanWorkEdit.spbPlanMOLClick(Sender: TObject);
var
 f : FINMolFilter;
 frmFINMolShow : TfrmFINMolShow;

   TempENDepartment2EPRen: ENDepartment2EPRenControllerSoapPort;
  i: Integer;
  ENDepartment2EPRenList: ENDepartment2EPRenShortList;
  renFilter : ENDepartment2EPRenFilter;
  renList : ENDepartment2EPRenShortList;
  //plan : ENPlanWork;

  ENMOL2PlanWorkObj: ENMOL2PlanWork;
  TempENMOL2PlanWork: ENMOL2PlanWorkControllerSoapPort;
  ENMOL2PlanWorkFilterObj : ENMOL2PlanWorkFilter;
  ENMOL2PlanWorkList: ENMOL2PlanWorkShortList;

  molSel : boolean;

  fOS : OSMolFilter;
  frmOSMolShow : TfrmOSMolShow;
  
begin

 if ENPlanWorkObj.stateRef.code = ENConsts.ENPLANWORKSTATE_WRITINGS_OS then
   begin
     fOS := OSMolFilter.Create;
     SetNullXSProps(fOS);
     SetNullIntProps(fOS);
     //f.state := 1; // ???? ?????? ?????????? ...

     //f.text := edtMolInCode.Text;

       frmOSMolShow := TfrmOSMolShow.Create(Application, fmNormal, fOS);

       try

          // оно типа ТУТ отфильтровано ...
          //if length(f.conditionSQL) > 0 then
          //  frmSCMolShow.isFiltered := true;

          with frmOSMolShow do
          begin
            DisableActions([ actEdit, actInsert ]);
            if ShowModal = mrOk then
            begin
                try
                   TempENMOL2PlanWork :=  HTTPRIOENMOL2PlanWork as ENMOL2PlanWorkControllerSoapPort;
                   ENMOL2PlanWorkFilterObj := ENMOL2PlanWorkFilter.Create;
                   SetNullXSProps(ENMOL2PlanWorkFilterObj);
                   SetNullIntProps(ENMOL2PlanWorkFilterObj);

                   ENMOL2PlanWorkFilterObj.planRef := ENPlanWorkRef.Create;
                   ENMOL2PlanWorkFilterObj.planRef.code := ENPlanWorkObj.code;
                   ENMOL2PlanWorkList := TempENMOL2PlanWork.getScrollableFilteredList(ENMOL2PlanWorkFilterObj, 0, -1);
                   if ENMOL2PlanWorkList.totalCount > 0 then
                      ENMOL2PlanWorkObj := TempENMOL2PlanWork.getObject(ENMOL2PlanWorkList.list[0].code)
                   else
                   begin
                     ENMOL2PlanWorkObj := ENMOL2PlanWork.Create();
                     ENMOL2PlanWorkObj.code := LOW_INT;
                     ENMOL2PlanWorkObj.planRef := ENPlanWorkRef.Create();
                     ENMOL2PlanWorkObj.planRef.code := ENPlanWorkObj.code;
                   end;

                   ENMOL2PlanWorkObj.molName :=  GetReturnValue(sgOSMol,2);
                   ENMOL2PlanWorkObj.molCode :=  GetReturnValue(sgOSMol,0);


                   edtMolName.Text := ENMOL2PlanWorkObj.molCode + ' ' + ENMOL2PlanWorkObj.molName;

                   if ENMOL2PlanWorkObj.code > LOW_INT then
                      TempENMOL2PlanWork.save(ENMOL2PlanWorkObj)
                   else
                      TempENMOL2PlanWork.add(ENMOL2PlanWorkObj);

                except
                   on EConvertError do Exit;
                end;
            end;
          end;
       finally
          frmOSMolShow.Free;
       end;
   end

 else
 begin
     f := FINMolFilter.Create;
     SetNullXSProps(f);
     SetNullIntProps(f);
     f.state := 1; // типа только работающие ...

          // РЭСы и МОЛы
          //plan := DMReports.getPlanByCode(planCode);

    //   if ENWorkOrder2ENPlanWorkObj.workOrder.department <> nil then
       if ENPlanWorkObj.departmentRef <> nil then
         if ENPlanWorkObj.departmentRef.code <> LOW_INT then
         begin
            TempENDepartment2EPRen :=  HTTPRIOENDepartment2EPRen as ENDepartment2EPRenControllerSoapPort;
            renFilter := ENDepartment2EPRenFilter.Create;
            SetNullXSProps(renFilter);
            SetNullIntProps(renFilter);
            //renFilter.renRef := EPRenRef.Create;
            //renFilter.renRef.code := renCode; //plan.renRef.code;
            renFilter.departmentRef := ENDepartmentRef.Create;
            renFilter.departmentRef.code := ENPlanWorkObj.departmentRef.code;  // ENWorkOrder2ENPlanWorkObj.workOrder.department.code;

            renList := TempENDepartment2EPRen.getScrollableFilteredList(renFilter,0,-1);
            if renList.totalCount > 0 then
              f.conditionSQL := 'substr(UMC_DBA.TDIVISION.ID,1,2) = ' + IntToStr(renList.list[0].finRenCode) ;
          end;

       frmFINMolShow:=TfrmFINMolShow.Create(Application,fmNormal, f);

       try

          if length(f.conditionSQL) > 0 then
            frmFINMolShow.isFiltered := true;

          with frmFINMolShow do begin
            DisableActions([ actEdit, actInsert ]);
            if ShowModal = mrOk then
            begin
                try

                   TempENMOL2PlanWork :=  HTTPRIOENMOL2PlanWork as ENMOL2PlanWorkControllerSoapPort;
                   ENMOL2PlanWorkFilterObj := ENMOL2PlanWorkFilter.Create;
                   SetNullXSProps(ENMOL2PlanWorkFilterObj);
                   SetNullIntProps(ENMOL2PlanWorkFilterObj);

                   ENMOL2PlanWorkFilterObj.planRef := ENPlanWorkRef.Create;
                   ENMOL2PlanWorkFilterObj.planRef.code := ENPlanWorkObj.code;
                   ENMOL2PlanWorkList := TempENMOL2PlanWork.getScrollableFilteredList(ENMOL2PlanWorkFilterObj, 0, -1);
                   if ENMOL2PlanWorkList.totalCount > 0 then
                      ENMOL2PlanWorkObj := TempENMOL2PlanWork.getObject(ENMOL2PlanWorkList.list[0].code)
                   else
                   begin
                     ENMOL2PlanWorkObj := ENMOL2PlanWork.Create();
                     ENMOL2PlanWorkObj.code := LOW_INT;
                     ENMOL2PlanWorkObj.planRef := ENPlanWorkRef.Create();
                     ENMOL2PlanWorkObj.planRef.code := ENPlanWorkObj.code;
                   end;

                   ENMOL2PlanWorkObj.molName := GetReturnValue(sgFINMol,1);
                   ENMOL2PlanWorkObj.molCode := GetReturnValue(sgFINMol,0);

                   //edtMolName.Text := ENMOL2PlanWorkObj.molName;  //GetReturnValue(sgFINMol,0);
                   edtMolName.Text := ENMOL2PlanWorkObj.molCode + ' ' + ENMOL2PlanWorkObj.molName;
                   //edtFINMolName.Text := //GetReturnValue(sgFINMol,1); //ENDepartmentShort(tvDep.Selected.Data).shortName;
                   //ENWorkOrder2ENPlanWorkObj.workOrder.finMolCode := GetReturnValue(sgFINMol,0);
                   //ENWorkOrder2ENPlanWorkObj.workOrder.finMolName := GetReturnValue(sgFINMol,1);

                   if ENMOL2PlanWorkObj.code > LOW_INT then
                      TempENMOL2PlanWork.save(ENMOL2PlanWorkObj)
                   else
                      TempENMOL2PlanWork.add(ENMOL2PlanWorkObj);


                except
                   on EConvertError do Exit;
                end;
            end;
          end;
       finally
          frmFINMolShow.Free;
       end;

  end;

end;

procedure TfrmENPlanWorkEdit.spbPriConnectionNumberClick(Sender: TObject);
var
	frmShowENServicesConnection : TfrmENServicesConnectionShow;
	servicesFilter : ENServicesObjectFilter;
	servicesObjectCode : Integer;

begin

	servicesFilter := ENServicesObjectFilter.Create;
	SetNullXSProps(servicesFilter);
	SetNullIntProps(servicesFilter);
	servicesFilter.contractKindRef := ENServicesContractKindRef.Create;
	servicesFilter.contractKindRef.code := ENConsts.SERVICES_CONTRACT_KIND_SERVICES;
  servicesFilter.contractTypeRef := ENServicesContractTypeRef.Create;
  servicesFilter.contractTypeRef.code :=  ENConsts.ENSERVICESOBJECTTYPE_CONNECTION;
	frmShowENServicesConnection := TfrmENServicesConnectionShow.Create(Application,fmNormal, servicesFilter);

    DisableActions([frmShowENServicesConnection.actNoFilter]);
  try
		with frmShowENServicesConnection do
			if ShowModal = mrOk then begin

				try
					servicesObjectCode := StrToInt(GetReturnValue(sgENServicesObject,0));
        except on EConvertError do Exit;
				end;

        //ENBuildingObj.servicesobject := ENServicesObjectRef.Create;
        //ENBuildingObj.servicesobject.code := servicesObjectCode;

        edtPriConnectionNumber.Text := GetReturnValue(sgENServicesObject,1) ; // + ' | ' + GetReturnValue(sgENServicesObject,5);

			end;
	finally
        frmShowENServicesConnection.Free;
     end;
end;

procedure TfrmENPlanWorkEdit.spbPlanMOLClearClick(Sender: TObject);
var
  ENMOL2PlanWorkObj: ENMOL2PlanWork;
  TempENMOL2PlanWork: ENMOL2PlanWorkControllerSoapPort;
  ENMOL2PlanWorkFilterObj : ENMOL2PlanWorkFilter;
  ENMOL2PlanWorkList: ENMOL2PlanWorkShortList;
  i : integer;
begin
  inherited;
if Application.MessageBox(PChar('Видалити МОЛа ?'),
                                       PChar('Увага!'),
                                       MB_ICONQUESTION + MB_YESNO + MB_DEFBUTTON2) = IDYES then
begin
               TempENMOL2PlanWork :=  HTTPRIOENMOL2PlanWork as ENMOL2PlanWorkControllerSoapPort;
               ENMOL2PlanWorkFilterObj := ENMOL2PlanWorkFilter.Create;
               SetNullXSProps(ENMOL2PlanWorkFilterObj);
               SetNullIntProps(ENMOL2PlanWorkFilterObj);

               ENMOL2PlanWorkFilterObj.planRef := ENPlanWorkRef.Create;
               ENMOL2PlanWorkFilterObj.planRef.code := ENPlanWorkObj.code;
               ENMOL2PlanWorkList := TempENMOL2PlanWork.getScrollableFilteredList(ENMOL2PlanWorkFilterObj, 0, -1);
               for i:=0 to ENMOL2PlanWorkList.totalCount-1 do
               begin
                   TempENMOL2PlanWork.remove(ENMOL2PlanWorkList.list[i].code);
               end;
               edtMolName.Text := '';
end;

end;

procedure TfrmENPlanWorkEdit.actAddToRQOrderExecute(Sender: TObject);
var
  f : RQOrderFilter;
  frmRQOrderShow : TfrmRQOrderShow;
  TempRQOrderItem: RQOrderItemControllerSoapPort;

begin
{
 f := RQOrderFilter.create();
 SetNullIntProps(f);
 SetNullXSProps(f);

 f.kindRef := RQOrderKindRef.Create;
 f.kindRef.code := RQORDER_KIND_DEPARTMENT;

 f.departmentRef := ENDepartment.Create;
 f.departmentRef.code := ENPlanWorkObj.departmentRef.code;
 f.departmentRef.name := edtDepartment.Text;

 f.budgetRef := ENDepartment.Create;
 f.budgetRef.code := ENPlanWorkObj.budgetRef.code;
 f.budgetRef.name := edtENBudgetName.Text; //ENPlanWorkObj.budgetRef.

 f.statusRef := RQOrderStatusRef.Create;
 f.statusRef.code := RQORDER_STATUS_GOOD;

 frmRQOrderShow := TfrmRQOrderShow.Create(Application, fmNormal, f);
 try
   frmRQOrderShow.DisableActions([frmRQOrderShow.actEdit, frmRQOrderShow.actDelete, frmRQOrderShow.actNoFilter, frmRQOrderShow.actFilter]);
   if frmRQOrderShow.ShowModal = mrOk then
   begin
      // добавим в Заявку ...
      TempRQOrderItem := HTTPRIORQOrderItem as RQOrderItemControllerSoapPort;
      TempRQOrderItem.addFromEstimate(StrToInt(frmRQOrderShow.GetReturnValue(frmRQOrderShow.sgRQOrder,0)), StrToInt(sgENEstimateItem.Cells[0, sgENEstimateItem.row]) ); // ГРИД !!!);
   end;
   pcPlanWorkChange(Sender);

 finally
   frmRQOrderShow.Free;
   //f.Free;
 end;
}

end;

procedure TfrmENPlanWorkEdit.actRemoveFromRQOrderExecute(Sender: TObject);
var
  TempRQOrderItem: RQOrderItemControllerSoapPort;
  j : Integer;
begin
{
  inherited;
      if Application.MessageBox(PChar('Ви дійсно бажаєте видалити матеріал з заявки ?'),
                        PChar('Внимание !'),MB_ICONQUESTION+MB_OKCANCEL+MB_DEFBUTTON2)=IDOK then
      begin
          TempRQOrderItem :=  HTTPRIORQOrderItem as RQOrderItemControllerSoapPort;

          j := StrToInt(sgENEstimateItem.Cells[0, sgENEstimateItem.Row]);

          TempRQOrderItem.removeFromRQDepartmentByEstimate(j, LOW_INT);
          UpdateGrid(Sender);
          //updateEstimateItemGrid();
      end;
}
end;

procedure TfrmENPlanWorkEdit.actRemoveUnmountedCounterExecute(Sender: TObject);
var code : Integer;
TempSCCounter : SCCounterControllerSoapPort;

begin
  inherited;
  if (ENPlanWorkObj.kind.code <> ENPLANWORKKIND_FACT) then begin
    raise Exception.Create('Відмінити зняття лічильника можливо тільки на завданні факті!');
  end;
  try
   try
     code := StrToInt(sgSCCounter.Cells[0,sgSCCounter.Row]);
   except on EConvertError do Exit;
   end;
   if Application.MessageBox(PChar('Ви дійсно бажаєте відмінити зняття лічильника?'),
                    PChar('Внимание !'),MB_ICONQUESTION+MB_OKCANCEL+MB_DEFBUTTON2)=IDOK then
  begin
        TempSCCounter := HTTPRIOSCCounter as SCCounterControllerSoapPort;
        TempSCCounter.removeCounterUnmountedManually(code);
        UpdateGrid(Sender);
        Application.MessageBox(PChar('Лічильник на зняття видалено!'), PChar('Повідомлення'), MB_ICONINFORMATION);
  end;



  finally

  end;

end;

procedure TfrmENPlanWorkEdit.actMOLDataInsertExecute(Sender: TObject);
Var
  TempFINMolData: FINMolDataControllerSoapPort;
begin
  TempFINMolData := HTTPRIOFINMolData as FINMolDataControllerSoapPort;
  FINMolDataObj:=FINMolData.Create;
  FINMolDataObj.workOrder := ENWorkOrder.Create;
  FINMolDataObj.workOrder.code :=  StrToInt(edtWorkOrderCode.Text);

  try
    frmFINMolDataEdit:=TfrmFINMolDataEdit.Create(Application, dsInsert);
    try

      frmFINMolDataEdit.parentCode := StrToInt(edtWorkOrderCode.Text);
      frmFINMolDataEdit.parentTypeCode := 1;
      frmFINMolDataEdit.departmentCode := ENPlanWorkObj.departmentRef.code;

      if frmFINMolDataEdit.ShowModal = mrOk then
      begin
        if FINMolDataObj<>nil then
            //TempFINMolData.add(FINMolDataObj);
            //UpdateGrid(Sender);
            //LoadMOLData( StrToInt(edtWorkOrderCode.Text) );
            LoadWorkOrder();
      end;
    finally
      frmFINMolDataEdit.Free;
      frmFINMolDataEdit:=nil;
    end;
  finally
    FINMolDataObj.Free;
  end;

end;

procedure TfrmENPlanWorkEdit.actMOLDataDeleteExecute(Sender: TObject);
Var
  TempFINMolData: FINMolDataControllerSoapPort;
  objCode : Integer;
begin
 TempFINMolData := HTTPRIOFINMolData as FINMolDataControllerSoapPort;
   try
     ObjCode := StrToInt(sgFINMolData.Cells[0,sgFINMolData.Row]);
   except
   on EConvertError do Exit;
  end;
  if Application.MessageBox(PChar('Вы дійсно бажаєте видалити МОЛа з Наряду ?'),
                    PChar('Внимание !'),MB_ICONQUESTION+MB_OKCANCEL+MB_DEFBUTTON2)=IDOK then
  begin
      TempFINMolData.remove(ObjCode, StrToInt(edtWorkOrderCode.Text), 1);
      //UpdateGrid(Sender);
      //LoadMOLData( StrToInt(edtWorkOrderCode.Text) );
      LoadWorkOrder();
  end;
end;

procedure TfrmENPlanWorkEdit.actMOLDataEditExecute(Sender: TObject);
Var TempFINMolData: FINMolDataControllerSoapPort;
begin
 TempFINMolData := HTTPRIOFINMolData as FINMolDataControllerSoapPort;
   try
     FINMolDataObj := TempFINMolData.getObject(StrToInt(sgFINMolData.Cells[0,sgFINMolData.Row]));
   except
   on EConvertError do Exit;
  end;
  frmFINMolDataEdit:=TfrmFINMolDataEdit.Create(Application, dsEdit);
  try
      frmFINMolDataEdit.parentCode := StrToInt(edtWorkOrderCode.Text);
      frmFINMolDataEdit.parentTypeCode := 1;
      frmFINMolDataEdit.departmentCode := ENPlanWorkObj.departmentRef.code;

    if frmFINMolDataEdit.ShowModal= mrOk then
      begin
        //TempFINMolData.save(FINMolDataObj);
        //UpdateGrid(Sender);
        //LoadMOLData( StrToInt(edtWorkOrderCode.Text) );
        LoadWorkOrder();
      end;
  finally
    frmFINMolDataEdit.Free;
    frmFINMolDataEdit:=nil;
  end;
end;

procedure TfrmENPlanWorkEdit.actMOLDataViewExecute(Sender: TObject);
Var TempFINMolData: FINMolDataControllerSoapPort;
begin
 TempFINMolData := HTTPRIOFINMolData as FINMolDataControllerSoapPort;
   try
     FINMolDataObj := TempFINMolData.getObject(StrToInt(sgFINMolData.Cells[0,sgFINMolData.Row]));
   except
   on EConvertError do Exit;
  end;
  frmFINMolDataEdit:=TfrmFINMolDataEdit.Create(Application, dsView);
  try
    frmFINMolDataEdit.ShowModal;
  finally
    frmFINMolDataEdit.Free;
    frmFINMolDataEdit:=nil;
  end;
end;

procedure TfrmENPlanWorkEdit.actMOLDataUpdateExecute(Sender: TObject);
begin
  //inherited;
  if edtWorkOrderCode.Text <> '' then
    LoadMOLData( StrToInt(edtWorkOrderCode.Text) );
end;

procedure TfrmENPlanWorkEdit.spbBindingOverClick(Sender: TObject);
Var
TempENBindingOver: ENBindingOverControllerSoapPort;
frmENBindingOverEdit :  TfrmENBindingOverEdit;
ENBindingOverFilterObj : ENBindingOverFilter;
ENBindingOverList : ENBindingOverShortList;
isExist : boolean;
dsMode : TDialogState;
begin
  TempENBindingOver := HTTPRIOENBindingOver as ENBindingOverControllerSoapPort;

  isExist := false;
  dsMode :=  dsInsert;
  
  if edtBindingOver.Text = '' then
  begin
    //(DialogState = dsEdit)
    ENBindingOverObj:=ENBindingOver.Create;
    SetNullXSProps(ENBindingOverObj);
    SetNullIntProps(ENBindingOverObj);

     ENBindingOverObj.dateGen:= TXSDate.Create;
     //ENBindingOverObj.dateEdit:= TXSDate.Create;

     ENBindingOverObj.planRef := ENPlanWorkRef.Create;
     ENBindingOverObj.planRef.code := ENPlanWorkObj.code;
     dsMode := dsInsert;
   end
   else
   begin
     // + вычитать данные ...
     ENBindingOverFilterObj := ENBindingOverFilter.Create;
     SetNullIntProps(ENBindingOverFilterObj);
     SetNullXSProps(ENBindingOverFilterObj);
     ENBindingOverFilterObj.planRef := ENPlanWorkRef.Create;
     ENBindingOverFilterObj.planRef.code := ENPlanWorkObj.code;

     //TempENBindingOver := HTTPRIOENBindingOver as ENBindingOverControllerSoapPort;
     ENBindingOverList := TempENBindingOver.getScrollableFilteredList(ENBindingOverFilterObj,0, -1);
     if ENBindingOverList.totalCount > 0 then
     begin
        ENBindingOverObj := TempENBindingOver.GetObject(ENBindingOverList.list[0].code);
        dsMode := dsEdit;
        //edtBindingOver.Text := '№' + ENBindingOverList.list[0].numberGen + ', від ' + XSDate2String(ENBindingOverList.list[0].dateGen) + ', пункт ' + ENBindingOverList.list[0].itemText;
        //edtBindingOver.Text := ENBindingOverObj.numberGen + ' ' + XSDate2String(ENBindingOverObj.dateGen) + ' ' + ENBindingOverObj.itemText;
     end;

     isExist := true;
   end;

   //if DialogState =
   if ( ENPlanWorkObj.kind.code <> ENPLANWORKKIND_CURRENT ) and (dsMode = dsEdit) then
      dsMode := dsView;

  try
    frmENBindingOverEdit:=TfrmENBindingOverEdit.Create(Application, dsMode);
    try
      if frmENBindingOverEdit.ShowModal = mrOk then
      begin
        if ENBindingOverObj<>nil then
            //TempENBindingOver.add(ENBindingOverObj);
            //UpdateGrid(Sender);
            edtBindingOver.Text := '№' + ENBindingOverObj.numberGen + ', від ' + XSDate2String(ENBindingOverObj.dateGen) + ', пункт ' + ENBindingOverObj.itemText;
      end;
    finally
      frmENBindingOverEdit.Free;
      frmENBindingOverEdit:=nil;
    end;
  finally
    ENBindingOverObj.Free;
  end;
end;

procedure TfrmENPlanWorkEdit.actExpToExcelExecute(Sender: TObject);
var AppPath, FileName: String;
    OldCursor: TCursor;
begin
  OldCursor := Screen.Cursor;
  try
    Screen.Cursor := crHourGlass;

    AppPath := ExtractFilePath(Application.ExeName);
    if not DirectoryExists(AppPath + TempReportsPath) then
      CreateDir(AppPath + TempReportsPath);
    FileName := AppPath + TempReportsPath + GetFileName('request') + '.xls';

    workExcel.XLSExport(FileName);
    ShellExecute(0,
                 PChar('open'),
                 PChar('"' + FileName + '"'),
                 nil,
                 nil,
                 SW_SHOWMAXIMIZED);
  finally
    Screen.Cursor := OldCursor;
  end;

end;


procedure TfrmENPlanWorkEdit.actExpToExcel_materialExecute(Sender: TObject);
var
  AppPath, FileName: String;
  OldCursor: TCursor;
begin
  if sgENEstimateItem.Visible then
    materialExcel.AdvStringGrid := sgENEstimateItem
  else if sgENEstimateItemWithFin.Visible then
    materialExcel.AdvStringGrid := sgENEstimateItemWithFin
  else
    raise Exception.Create('NET-4061 Unknown ENEstimateItem grid!');

  OldCursor := Screen.Cursor;
  Screen.Cursor := crHourGlass;

  try

    AppPath := ExtractFilePath(Application.ExeName);
    if not DirectoryExists(AppPath + TempReportsPath) then
      CreateDir(AppPath + TempReportsPath);
    FileName := AppPath + TempReportsPath + GetFileName('request') + '.xls';

    materialExcel.XLSExport(FileName);
    ShellExecute(0,
                 PChar('open'),
                 PChar('"' + FileName + '"'),
                 nil,
                 nil,
                 SW_SHOWMAXIMIZED);
  finally
    Screen.Cursor := OldCursor;
  end;
end;


procedure TfrmENPlanWorkEdit.actExpToExcelDemontajExecute(Sender: TObject);
var AppPath, FileName: String;
    OldCursor: TCursor;
begin
  OldCursor := Screen.Cursor;
  try
    Screen.Cursor := crHourGlass;

    AppPath := ExtractFilePath(Application.ExeName);
    if not DirectoryExists(AppPath + TempReportsPath) then
      CreateDir(AppPath + TempReportsPath);
    FileName := AppPath + TempReportsPath + GetFileName('request') + '.xls';

    demontajExcel.XLSExport(FileName);
    ShellExecute(0,
                 PChar('open'),
                 PChar('"' + FileName + '"'),
                 nil,
                 nil,
                 SW_SHOWMAXIMIZED);
  finally
    Screen.Cursor := OldCursor;
  end;

end;

procedure TfrmENPlanWorkEdit.actExpToExcelProducedExecute(Sender: TObject);
var AppPath, FileName: String;
    OldCursor: TCursor;
begin
  OldCursor := Screen.Cursor;
  try
    Screen.Cursor := crHourGlass;

    AppPath := ExtractFilePath(Application.ExeName);
    if not DirectoryExists(AppPath + TempReportsPath) then
      CreateDir(AppPath + TempReportsPath);
    FileName := AppPath + TempReportsPath + GetFileName('request') + '.xls';

    producedExcel.XLSExport(FileName);
    ShellExecute(0,
                 PChar('open'),
                 PChar('"' + FileName + '"'),
                 nil,
                 nil,
                 SW_SHOWMAXIMIZED);
  finally
    Screen.Cursor := OldCursor;
  end;

end;

procedure TfrmENPlanWorkEdit.actExpToExcelHumanExecute(Sender: TObject);
var AppPath, FileName: String;
    OldCursor: TCursor;
begin
  OldCursor := Screen.Cursor;
  try
    Screen.Cursor := crHourGlass;

    AppPath := ExtractFilePath(Application.ExeName);
    if not DirectoryExists(AppPath + TempReportsPath) then
      CreateDir(AppPath + TempReportsPath);
    FileName := AppPath + TempReportsPath + GetFileName('request') + '.xls';

    brigadaExcel.XLSExport(FileName);
    ShellExecute(0,
                 PChar('open'),
                 PChar('"' + FileName + '"'),
                 nil,
                 nil,
                 SW_SHOWMAXIMIZED);
  finally
    Screen.Cursor := OldCursor;
  end;

end;


procedure TfrmENPlanWorkEdit.actChangeEstimateItemStatusExecute(
  Sender: TObject);
  var
    TempENEstimateItem: ENEstimateItemControllerSoapPort;
    i , newStatus, eCode : integer;
    state_, isSel : boolean;
    //frm : frmENEstimateItemStatusChangeEdit;
begin
  state_ := false;
  isSel := false;

  for i:=1 to sgENEstimateItem.RowCount - 1 do
  begin
     sgENEstimateItem.GetCheckBoxState(1,i,state_);
     if state_ then
     begin
       isSel := true;
       if (Integer(sgENEstimateItem.Objects[1, i]) in [ ENESTIMATEITEMSTATUS_ORDERED ]) then
       begin
         Application.MessageBox(PChar('У замовлених матеріалів статус вручну не змінюється !!!'), PChar('Увага !'), MB_ICONWARNING);
         Exit;
       end;
     end;
  end;

  if not isSel then
  begin
     Application.MessageBox(PChar('Виберіть хоча б один матеріал !!!'), PChar('Увага !'),MB_ICONWARNING);
     Exit;
  end;

  frmENEstimateItemStatusChangeEdit := TfrmENEstimateItemStatusChangeEdit.Create(Application, dsInsert);
  try
    if frmENEstimateItemStatusChangeEdit.ShowModal = mrOK then
    begin
      newStatus := frmENEstimateItemStatusChangeEdit.cbENEstimateItemStatus.ItemIndex + 1;

      TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;
      for i:=1 to sgENEstimateItem.RowCount - 1 do
      begin
         sgENEstimateItem.GetCheckBoxState(1,i,state_);
         if state_ then
         begin

            try
              eCode := StrToInt( sgENEstimateItem.Cells[0, i ]); //   (sgENEstimateItem,0));
            except
              on EConvertError do Exit;
            end;

            if not (Integer(sgENEstimateItem.Objects[1, i]) in [ ENESTIMATEITEMSTATUS_ORDERED ]) then
            begin
              //newStatus := ENESTIMATEITEMSTATUS_INSKLAD;
              TempENEstimateItem.changeStatus( eCode  , newStatus );
            end;

         end;
      end;

       pcPlanWorkChange(Sender);
    end;
  finally
     frmENEstimateItemStatusChangeEdit.Free;
  end;

end;

procedure TfrmENPlanWorkEdit.actSelectAllExecute(Sender: TObject);
begin
  inherited;

  if pcPlanWork.ActivePage = tsEstimateItems then
    checkGrid(sgENEstimateItem, 1, true);

  if pcPlanWork.ActivePage = tsPlanWorkItems then
    checkGrid(sgENPlanWorkItem, 1, true);
end;

procedure TfrmENPlanWorkEdit.actTransformMaterialExecute(Sender: TObject);
  var
    TempENEstimateItem: ENEstimateItemControllerSoapPort;
     eCode : integer;
    //frm : frmENEstimateItemStatusChangeEdit;
begin

    try
      eCode := StrToInt( sgENEstimateItem.Cells[0, sgENEstimateItem.Row ]); //   (sgENEstimateItem,0));
    except
      on EConvertError do Exit;
    end;

  frmENEstimateItemTransformEdit := TfrmENEstimateItemTransformEdit.Create(Application, dsInsert);
  frmENEstimateItemTransformEdit.eiCode := eCode;
  try
    if frmENEstimateItemTransformEdit.ShowModal = mrOK then
    begin
       pcPlanWorkChange(Sender);
    end;
  finally
     frmENEstimateItemTransformEdit.Free;
  end;
end;

procedure TfrmENPlanWorkEdit.actClearAllExecute(Sender: TObject);
begin
  inherited;

  if pcPlanWork.ActivePage = tsEstimateItems then
    checkGrid(sgENEstimateItem, 1, false);

  if pcPlanWork.ActivePage = tsPlanWorkItems then
    checkGrid(sgENPlanWorkItem, 1, false);
end;

procedure TfrmENPlanWorkEdit.actChangeEstimateItemStatusPLANEDExecute(
  Sender: TObject);
  var
    TempENEstimateItem: ENEstimateItemControllerSoapPort;
    i , newStatus, eCode : integer;
    state_, isSel : boolean;
begin
{
  inherited;
//
  state_ := false;
  isSel := false;
  TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;
  for i:=1 to sgENEstimateItem.RowCount - 1 do
  begin
     sgENEstimateItem.GetCheckBoxState(1,i,state_);
     if state_ then
     begin
        newStatus := LOW_INT;
        isSel := true;
        try
          eCode := StrToInt( sgENEstimateItem.Cells[0, i ]); //   (sgENEstimateItem,0));
        except
          on EConvertError do Exit;
        end;

        if Integer(sgENEstimateItem.Objects[1, i]) in [ ENESTIMATEITEMSTATUS_INSKLAD, ENESTIMATEITEMSTATUS_TMP ] then
        begin
          newStatus := ENESTIMATEITEMSTATUS_PLANED;

          TempENEstimateItem.changeStatus( eCode  , newStatus );
        end;
     end;
  end;

  if not isSel then
  begin
     Application.MessageBox(PChar('Виберіть хоча б один матеріал !!!'), PChar('Увага !'),MB_ICONWARNING);
  end
  else
    pcPlanWorkChange(Sender);
}
end;


procedure TfrmENPlanWorkEdit.updateMarksGrid(estimateItemCode : Integer; stringGrid : TAdvStringGrid );
var
  TempMarkEstimate: ENMarkEstimateControllerSoapPort;
  j,i: Integer;
  meList: ENMarkEstimateShortList;
  meFilter : ENMarkEstimateFilter;
begin
   for i:=1 to stringGrid.RowCount-1 do
     for j:=0 to stringGrid.ColCount-1 do
       stringGrid.Cells[j,i]:='';

  SetGridHeaders(MarksHeaders, stringGrid.ColumnHeaders);

  meFilter := ENMarkEstimateFilter.Create;
  SetNullIntProps(meFilter);
  SetNullXSProps(meFilter);
  meFilter.estimateItem := ENEstimateItem.Create;
  meFilter.estimateItem.code := estimateItemCode;

  TempMarkEstimate :=  HTTPRIOMarkEstimate as ENMarkEstimateControllerSoapPort;
  meList := TempMarkEstimate.getScrollableFilteredList(meFilter,0,-1);

  if High(meList.list) > -1 then
     stringGrid.RowCount:=High(meList.list)+2
  else
     stringGrid.RowCount:=2;

   with stringGrid do
    for i:=0 to High(meList.list) do
      begin
        Application.ProcessMessages;
        if meList.list[i].code <> Low(Integer) then
        Cells[0,i+1] := IntToStr(meList.list[i].code)
        else
        Cells[0,i+1] := '';
        Cells[1,i+1] := meList.list[i].markName;
        stringGrid.RowCount:= i + 2;
      end;

   stringGrid.Row:=1;
end;


procedure TfrmENPlanWorkEdit.UpdateOwnProductionList;
var
  TempENEstimateItem: ENEstimateItemControllerSoapPort;
  ENEstimateItemList: ENEstimateItemShortList;
  eiFilter: ENEstimateItemFilter;
  i, eiCount: Integer;
  eiArr: ENEstimateItemController.ArrayOfInteger;
  eiCodes: String;
begin
  ClearGrid(sgOwnProduction);

  if DialogState = dsInsert then Exit;

  TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;

  // В начале выберем коды эстимейтов, и потом уже подкинем их кондишеном в лист,
  // потому что если сразу выбирать лист по такому условию, то запрос работает
  // ацки долго :)
  eiFilter := ENEstimateItemFilter.Create;
  SetNullIntProps(eiFilter);
  SetNullXSProps(eiFilter);

  eiFilter.conditionSQL :=
      ' ENESTIMATEITEM.CODE in ' +
      '   (select ep.estimateitemrefcode ' +
      '      from enestimateitem2plan ep ' +
      '     where ep.planrefcode = ' + IntToStr(ENPlanWorkObj.code) +
      '       and ep.typerefcode = ' + IntToStr(ENESTIMATEITEM2PLANTYPE_OWN_PRODUCTION) + ')';

  eiArr := TempENEstimateItem.getFilteredCodeArray(eiFilter, 0, -1);

  if High(eiArr) < 0 then Exit;

  eiCodes := '';

  for i := 0 to High(eiArr) do
    AddListPos(eiCodes, IntToStr(eiArr[i]));

  if eiCodes = '' then Exit;

  eiFilter := ENEstimateItemFilter.Create;
  SetNullIntProps(eiFilter);
  SetNullXSProps(eiFilter);

  eiFilter.conditionSQL := 'ENESTIMATEITEM.CODE in (' + eiCodes + ')';

  ENEstimateItemList := TempENEstimateItem.getScrollableFilteredList(eiFilter, 0, -1);

  eiCount := High(ENEstimateItemList.list);

  if eiCount > -1 then
    sgOwnProduction.RowCount := eiCount + 2
  else
    sgOwnProduction.RowCount := 2;

{
  ENPlanOwnProductionHeaders: array [1..10] of String =
        ( 'Код'
          ,'Матеріал'
          ,'Кількість'
          ,'Од. виміру'
          ,'Об''єкт планування'
          ,'Інв. №'
          ,'Рік плану'
          ,'Місяць плану'
          ,'Дата початку робіт'
          ,'Підрозділ'
        );
}

   with sgOwnProduction do
     for i := 0 to eiCount do
     begin
       Application.ProcessMessages;

       if ENEstimateItemList.list[i].code <> Low(Integer) then
         Cells[0,i+1] := IntToStr(ENEstimateItemList.list[i].code)
       else
         Cells[0,i+1] := '';
       Cells[1,i+1] := ENEstimateItemList.list[i].materialRefName;
       if ENEstimateItemList.list[i].countFact = nil then
         Cells[2,i+1] := ''
       else
         Cells[2,i+1] := ENEstimateItemList.list[i].countFact.DecimalString;
       Cells[3,i+1] := ENEstimateItemList.list[i].measureType;

       Cells[4,i+1] := ENEstimateItemList.list[i].elementName;
       Cells[5,i+1] := ENEstimateItemList.list[i].invNumber;

       if ENEstimateItemList.list[i].planRefYearGen <> Low(Integer) then
         Cells[6,i+1] := IntToStr(ENEstimateItemList.list[i].planRefYearGen)
       else
         Cells[6,i+1] := '';

       if ENEstimateItemList.list[i].planRefMonthGen <> Low(Integer) then
         Cells[7,i+1] := MonthesNames[ENEstimateItemList.list[i].planRefMonthGen]
       else
         Cells[7,i+1] := '';

       if ENEstimateItemList.list[i].planRefDateStart = nil then
         Cells[8,i+1] := ''
       else
         Cells[8,i+1] := XSDate2String(ENEstimateItemList.list[i].planRefDateStart);

       Cells[9,i+1] := ENEstimateItemList.list[i].planRefDepartmentName;

       Objects[0,i+1] := TObject(ENEstimateItemList.list[i].planRefCode);

       sgOwnProduction.RowCount := i + 2;
     end;

  sgOwnProduction.Row := 1;
end;

procedure TfrmENPlanWorkEdit.actMoveTOExecute(Sender: TObject);
var
  planFull : ENPlanWorkShort;
  estAccountingType : Integer;
begin
  inherited;

    try
      estAccountingType := Integer(sgENEstimateItem.Objects[2, sgENEstimateItem.Row]);
    except
      on EConvertError do
      begin
        ShowMessage('Помилка у Типі обліку матеріалу ...');
        Exit;
      end;
    end;

    if estAccountingType = TK_ACCOUNTINGTYPE_TMC then
    begin
        frmENEstimateItem2ENEstimateItemEdit:=TfrmENEstimateItem2ENEstimateItemEdit.Create(Application, dsInsert);
        try
          frmENEstimateItem2ENEstimateItemEdit.planOUT := ENPlanWorkObj;

          planFull := DMReports.getPlanShortByCode(ENPlanWorkObj.code);

          frmENEstimateItem2ENEstimateItemEdit.edtPlanOut.Text :=  'Інв. № ' + planFull.invNumber + ' ' + planFull.objectName +
                          ' ' + IntToStr( planFull.monthGen) + ' ' + IntToStr( planFull.yearGen);

          with frmENEstimateItem2ENEstimateItemEdit do
          begin
              updateENEstimateItemGrid(sgENEstimateItemOUT, planOut.code);
              sgENEstimateItemOUTClick(Sender);
          end;



    {
      ENPlanWorkHeaders: array [1..17] of String =
            ( 'Код'
              ,'Об''єкт планування'
              ,'Інв. №'
              ,'РЕЗ та ЕМ'
              ,'Рік плану'
              ,'Місяць плану'
              ,'Дата початку робіт'
              ,'ПідВид робіт'
              ,'Тип акту'
              ,'Вид плану'
              ,'Статус'
              ,'Підрозділ'
              ,'БюджетоТримач'
              ,'Центр відповідальності'
              //,'Дата складання плану'
              ,'Номер наряда'
              ,'Користувач, що вніс зміни'
              ,'Дата останньої зміни'
            );

                edtPlanOUT.Text := 'Інв. № ' + frmENPlanWorkShow.GetReturnValue(frmENPlanWorkShow.sgENPlanWork, 2)
                                 + ', ' + frmENPlanWorkShow.GetReturnValue(frmENPlanWorkShow.sgENPlanWork, 1)
                                 + ', ' + frmENPlanWorkShow.GetReturnValue(frmENPlanWorkShow.sgENPlanWork, 5)
                                 + ' '  + frmENPlanWorkShow.GetReturnValue(frmENPlanWorkShow.sgENPlanWork, 4);
    }
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
          frmENEstimateItem2ENEstimateItemEdit:=nil;
        end;
    end
    else
    if estAccountingType = TK_ACCOUNTINGTYPE_COUNTER then
    begin
        frmENEstimateItem2ENEstimateItemEditSCCounter:=TfrmENEstimateItem2ENEstimateItemEditSCCounter.Create(Application, dsInsert);
        try
          frmENEstimateItem2ENEstimateItemEditSCCounter.planOUT := ENPlanWorkObj;

          planFull := DMReports.getPlanShortByCode(ENPlanWorkObj.code);

          frmENEstimateItem2ENEstimateItemEditSCCounter.edtPlanOut.Text :=  'Інв. № ' + planFull.invNumber + ' ' + planFull.objectName +
                          ' ' + IntToStr( planFull.monthGen) + ' ' + IntToStr( planFull.yearGen);

          with frmENEstimateItem2ENEstimateItemEditSCCounter do
          begin
              updateENEstimateItemGrid(sgENEstimateItemOUT, planOut.code);
              sgENEstimateItemOUTClick(Sender);
          end;



    {
      ENPlanWorkHeaders: array [1..17] of String =
            ( 'Код'
              ,'Об''єкт планування'
              ,'Інв. №'
              ,'РЕЗ та ЕМ'
              ,'Рік плану'
              ,'Місяць плану'
              ,'Дата початку робіт'
              ,'ПідВид робіт'
              ,'Тип акту'
              ,'Вид плану'
              ,'Статус'
              ,'Підрозділ'
              ,'БюджетоТримач'
              ,'Центр відповідальності'
              //,'Дата складання плану'
              ,'Номер наряда'
              ,'Користувач, що вніс зміни'
              ,'Дата останньої зміни'
            );

                edtPlanOUT.Text := 'Інв. № ' + frmENPlanWorkShow.GetReturnValue(frmENPlanWorkShow.sgENPlanWork, 2)
                                 + ', ' + frmENPlanWorkShow.GetReturnValue(frmENPlanWorkShow.sgENPlanWork, 1)
                                 + ', ' + frmENPlanWorkShow.GetReturnValue(frmENPlanWorkShow.sgENPlanWork, 5)
                                 + ' '  + frmENPlanWorkShow.GetReturnValue(frmENPlanWorkShow.sgENPlanWork, 4);
    }
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
          frmENEstimateItem2ENEstimateItemEditSCCounter:=nil;
        end;
    end
    else
      ShowMessage('Error in accountingType ' + IntToStr(estAccountingType));


end;

procedure TfrmENPlanWorkEdit.actMoveTransportFromWorkToOtherWorkExecute(
  Sender: TObject);
  var planItemFromCode, planItemToCode : Integer;
  frmENPlanWorkItemShow : TfrmENPlanWorkItemShow;
  piFilter : ENPlanWorkItemFilter;
  TempENPlanWorkItem: ENPlanWorkItemControllerSoapPort;
  begin
  inherited;

    if Application.MessageBox(PChar('Ви дійсно бажаєте перенести транспорт до обраної роботи?'),
                            PChar('Увага !'),
                            MB_ICONQUESTION + MB_OKCANCEL + MB_DEFBUTTON2) <> IDOK then
    Exit;

      try
        planItemFromCode := StrToInt(sgENPlanWorkItem.Cells[0, sgENPlanWorkItem.Row]);
      except
        on EConvertError do Exit;
      end;

     piFilter := ENPlanWorkItemFilter.Create;
     SetNullIntProps(piFilter);
     SetNullXSProps(piFilter);
     piFilter.planRef := ENPlanWorkRef.Create;
     piFilter.planRef.code := ENPlanWorkObj.code;

     TempENPlanWorkItem := HTTPRIOENPlanWorkItem as ENPlanWorkItemControllerSoapPort;

  frmENPlanWorkItemShow := TfrmENPlanWorkItemShow.Create(Application, fmNormal, piFilter);
  DisableActions([frmENPlanWorkItemShow.actFilter, frmENPlanWorkItemShow.actNoFilter,
  frmENPlanWorkItemShow.actInsert, frmENPlanWorkItemShow.actEdit]);
  try
    if  frmENPlanWorkItemShow.ShowModal = mrOK then
    begin
        try
           planItemToCode :=  StrToInt(frmENPlanWorkItemShow.GetReturnValue(frmENPlanWorkItemShow.sgENPlanWorkItem,0))
        except
         on EConvertError do Exit;
        end;
        TempENPlanWorkItem.moveTransportToSelectedWork(planItemFromCode, planItemToCode);
    end;
  finally
     frmENPlanWorkItemShow.Free;
  end;
end;

procedure TfrmENPlanWorkEdit.actMoveWorkFromAbstractPlanExecute(
  Sender: TObject);
var
  TempENPlanWorkItem: ENPlanWorkItemControllerSoapPort;
  planWorkItemObjCode, i: Integer;
  state, isSel: Boolean;
begin
  state := false;
  isSel := false;

  for i := 1 to sgENPlanWorkItem.RowCount - 1 do
  begin
    sgENPlanWorkItem.GetCheckBoxState(1, i, state);
    if state then
    begin
      isSel := true;
      break;
    end;
  end;

  if not isSel then
  begin
    Application.MessageBox(PChar('Виберіть хоча б одну роботу !'), PChar('Увага !'), MB_ICONWARNING);
    Exit;
  end;

  if Application.MessageBox(PChar('Ви дійсно бажаєте повернути у початковий план всі обрані роботи ?'),
                            PChar('Увага !'),
                            MB_ICONQUESTION + MB_OKCANCEL + MB_DEFBUTTON2) <> IDOK then
    Exit;

  TempENPlanWorkItem := HTTPRIOENPlanWorkItem as ENPlanWorkItemControllerSoapPort;

  for i := 1 to sgENPlanWorkItem.RowCount - 1 do
  begin
    sgENPlanWorkItem.GetCheckBoxState(1, i, state);
    if state then
    begin
      try
        planWorkItemObjCode := StrToInt(sgENPlanWorkItem.Cells[0, i]);
      except
        on EConvertError do Exit;
      end;

      TempENPlanWorkItem.moveWorkFromAbstractPlan(planWorkItemObjCode);
    end;
  end;

  UpdateGrid(Sender);

end;

procedure TfrmENPlanWorkEdit.actMoveWorkToAbstractPlanExecute(Sender: TObject);
var
  TempENPlanWorkItem: ENPlanWorkItemControllerSoapPort;
  planWorkItemObjCode, i: Integer;
  state, isSel: Boolean;
begin
  state := false;
  isSel := false;

  for i := 1 to sgENPlanWorkItem.RowCount - 1 do
  begin
    sgENPlanWorkItem.GetCheckBoxState(1, i, state);
    if state then
    begin
      isSel := true;
      break;
    end;
  end;

  if not isSel then
  begin
    Application.MessageBox(PChar('Виберіть хоча б одну роботу !'), PChar('Увага !'), MB_ICONWARNING);
    Exit;
  end;

  if Application.MessageBox(PChar('Ви дійсно бажаєте перенести у безоб''єктний план всі обрані роботи ?'),
                            PChar('Увага !'),
                            MB_ICONQUESTION + MB_OKCANCEL + MB_DEFBUTTON2) <> IDOK then
    Exit;

  TempENPlanWorkItem := HTTPRIOENPlanWorkItem as ENPlanWorkItemControllerSoapPort;

  for i := 1 to sgENPlanWorkItem.RowCount - 1 do
  begin
    sgENPlanWorkItem.GetCheckBoxState(1, i, state);
    if state then
    begin
      try
        planWorkItemObjCode := StrToInt(sgENPlanWorkItem.Cells[0, i]);
      except
        on EConvertError do Exit;
      end;

      TempENPlanWorkItem.moveWorkToAbstractPlan(planWorkItemObjCode);
    end;
  end;

  UpdateGrid(Sender);
  //pcPlanWorkChange(Sender);
end;

procedure TfrmENPlanWorkEdit.actMoveFromExecute(Sender: TObject);
var
  planFull : ENPlanWorkShort;
  estAccountingType : Integer;
begin
  inherited;

      try
      estAccountingType := Integer(sgENEstimateItem.Objects[2, sgENEstimateItem.Row]);
    except
      on EConvertError do
      begin
        ShowMessage('Помилка у Типі обліку матеріалу ...');
        Exit;
      end;
    end;

    if estAccountingType = TK_ACCOUNTINGTYPE_TMC then
    begin
        frmENEstimateItem2ENEstimateItemEdit:=TfrmENEstimateItem2ENEstimateItemEdit.Create(Application, dsInsert);
        try
          frmENEstimateItem2ENEstimateItemEdit.planIN := ENPlanWorkObj;

          planFull := DMReports.getPlanShortByCode(ENPlanWorkObj.code);

          frmENEstimateItem2ENEstimateItemEdit.edtPlanIN.Text :=  'Інв. № ' + planFull.invNumber + ' ' + planFull.objectName +
                          ' ' + IntToStr( planFull.monthGen) + ' ' + IntToStr( planFull.yearGen);

          with frmENEstimateItem2ENEstimateItemEdit do
          begin
              updateENEstimateItemGrid(sgENEstimateItemIN, planIN.code);
              sgENEstimateItemINClick(Sender);
          end;



    {
      ENPlanWorkHeaders: array [1..17] of String =
            ( 'Код'
              ,'Об''єкт планування'
              ,'Інв. №'
              ,'РЕЗ та ЕМ'
              ,'Рік плану'
              ,'Місяць плану'
              ,'Дата початку робіт'
              ,'ПідВид робіт'
              ,'Тип акту'
              ,'Вид плану'
              ,'Статус'
              ,'Підрозділ'
              ,'БюджетоТримач'
              ,'Центр відповідальності'
              //,'Дата складання плану'
              ,'Номер наряда'
              ,'Користувач, що вніс зміни'
              ,'Дата останньої зміни'
            );

                edtPlanOUT.Text := 'Інв. № ' + frmENPlanWorkShow.GetReturnValue(frmENPlanWorkShow.sgENPlanWork, 2)
                                 + ', ' + frmENPlanWorkShow.GetReturnValue(frmENPlanWorkShow.sgENPlanWork, 1)
                                 + ', ' + frmENPlanWorkShow.GetReturnValue(frmENPlanWorkShow.sgENPlanWork, 5)
                                 + ' '  + frmENPlanWorkShow.GetReturnValue(frmENPlanWorkShow.sgENPlanWork, 4);
    }
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
          frmENEstimateItem2ENEstimateItemEdit:=nil;
        end;
    end
    else
    if estAccountingType = TK_ACCOUNTINGTYPE_COUNTER then
    begin
        frmENEstimateItem2ENEstimateItemEditSCCounter := TfrmENEstimateItem2ENEstimateItemEditSCCounter.Create(Application, dsInsert);
        try
          frmENEstimateItem2ENEstimateItemEditSCCounter.planIN := ENPlanWorkObj;

          planFull := DMReports.getPlanShortByCode(ENPlanWorkObj.code);

          frmENEstimateItem2ENEstimateItemEditSCCounter.edtPlanIN.Text :=  'Інв. № ' + planFull.invNumber + ' ' + planFull.objectName +
                          ' ' + IntToStr( planFull.monthGen) + ' ' + IntToStr( planFull.yearGen);

          with frmENEstimateItem2ENEstimateItemEditSCCounter do
          begin
              updateENEstimateItemGrid(sgENEstimateItemIN, planIN.code);
              sgENEstimateItemINClick(Sender);
          end;



    {
      ENPlanWorkHeaders: array [1..17] of String =
            ( 'Код'
              ,'Об''єкт планування'
              ,'Інв. №'
              ,'РЕЗ та ЕМ'
              ,'Рік плану'
              ,'Місяць плану'
              ,'Дата початку робіт'
              ,'ПідВид робіт'
              ,'Тип акту'
              ,'Вид плану'
              ,'Статус'
              ,'Підрозділ'
              ,'БюджетоТримач'
              ,'Центр відповідальності'
              //,'Дата складання плану'
              ,'Номер наряда'
              ,'Користувач, що вніс зміни'
              ,'Дата останньої зміни'
            );

                edtPlanOUT.Text := 'Інв. № ' + frmENPlanWorkShow.GetReturnValue(frmENPlanWorkShow.sgENPlanWork, 2)
                                 + ', ' + frmENPlanWorkShow.GetReturnValue(frmENPlanWorkShow.sgENPlanWork, 1)
                                 + ', ' + frmENPlanWorkShow.GetReturnValue(frmENPlanWorkShow.sgENPlanWork, 5)
                                 + ' '  + frmENPlanWorkShow.GetReturnValue(frmENPlanWorkShow.sgENPlanWork, 4);
    }
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
    end
    else
      ShowMessage('Error in accountingType ' + IntToStr(estAccountingType));
end;

procedure TfrmENPlanWorkEdit.cbENPlanWorkFormNameCloseUp(Sender: TObject);
begin

  HideControls([gbReason], ((cbENPlanWorkFormName.ItemIndex <> 1) or (DialogState <> dsEdit)));

end;

procedure TfrmENPlanWorkEdit.spbReasonClick(Sender: TObject);
Var
TempENPlanWorkReason: ENPlanWorkReasonControllerSoapPort;

TempENPlanWork2PlanWorkReason: ENPlanWork2PlanWorkReasonControllerSoapPort;
p2prList : ENPlanWork2PlanWorkReasonShortList;
p2prFilter : ENPlanWork2PlanWorkReasonFilter;
reason2Plan : ENPlanWork2PlanWorkReason;
p2prArr : ENPlanWork2PlanWorkReasonController.ArrayOfInteger;
frmENPlanWorkReasonShow : TfrmENPlanWorkReasonShow;
begin
  TempENPlanWork2PlanWorkReason := HTTPRIOENPlanWork2PlanWorkReason as ENPlanWork2PlanWorkReasonControllerSoapPort;
  if (edtReason.Text = '') then
  begin
     reason2Plan :=  ENPlanWork2PlanWorkReason.Create;
     SetNullIntProps(reason2Plan);
     SetNullXSProps(reason2Plan);
     reason2Plan.planRef := ENPlanWorkRef.Create;
     reason2Plan.planRef.code := ENPlanWorkObj.code;
  end
  else
  begin
     p2prFilter := ENPlanWork2PlanWorkReasonFilter.Create;
     SetNullIntProps(p2prFilter);
     SetNullXSProps(p2prFilter);
     p2prFilter.planRef := ENPlanWorkRef.Create;
     p2prFilter.planRef.code := ENPlanWorkObj.code;
     p2prArr := TempENPlanWork2PlanWorkReason.getScrollableFilteredCodeArray(p2prFilter, 0, -1);
     if High(p2prArr) > -1 then
     begin
         reason2Plan := TempENPlanWork2PlanWorkReason.getObject(p2prArr[0]);
     end;

  end;


  frmENPlanWorkReasonShow := TfrmENPlanWorkReasonShow.Create(Application, fmNormal);
  try

    if  frmENPlanWorkReasonShow.ShowModal = mrOK then
    begin
        reason2Plan.planWorkReasonRef := ENPlanWorkReasonRef.Create;
        reason2Plan.planWorkReasonRef.code :=  StrToInt(frmENPlanWorkReasonShow.GetReturnValue(frmENPlanWorkReasonShow.sgENPlanWorkReason,0));

        if reason2Plan.code = LOW_INT then
           TempENPlanWork2PlanWorkReason.add(reason2Plan)
        else
           TempENPlanWork2PlanWorkReason.save(reason2Plan);

        edtReason.Text := frmENPlanWorkReasonShow.GetReturnValue(frmENPlanWorkReasonShow.sgENPlanWorkReason,4) + ' №' + frmENPlanWorkReasonShow.GetReturnValue(frmENPlanWorkReasonShow.sgENPlanWorkReason,2) + ' від ' + frmENPlanWorkReasonShow.GetReturnValue(frmENPlanWorkReasonShow.sgENPlanWorkReason,1) + '(' + frmENPlanWorkReasonShow.GetReturnValue(frmENPlanWorkReasonShow.sgENPlanWorkReason,5) + ')';
    end;

  finally
     frmENPlanWorkReasonShow.Free;
  end;

end;

procedure TfrmENPlanWorkEdit.spbReasonClearClick(Sender: TObject);
var
  TempENPlanWork2PlanWorkReason: ENPlanWork2PlanWorkReasonControllerSoapPort;
  p2prFilter : ENPlanWork2PlanWorkReasonFilter;
  p2prArr : ENPlanWork2PlanWorkReasonController.ArrayOfInteger;
begin
     TempENPlanWork2PlanWorkReason := HTTPRIOENPlanWork2PlanWorkReason as ENPlanWork2PlanWorkReasonControllerSoapPort;
     p2prFilter :=  ENPlanWork2PlanWorkReasonFilter.Create;
     SetNullIntProps(p2prFilter);
     SetNullXSProps(p2prFilter);
     
     p2prFilter.planRef := ENPlanWorkRef.Create;
     p2prFilter.planRef.code := ENPlanWorkObj.code;
     p2prArr := TempENPlanWork2PlanWorkReason.getScrollableFilteredCodeArray(p2prFilter, 0, -1);
     if High(p2prArr) > -1 then
       TempENPlanWork2PlanWorkReason.remove(p2prArr[0]);

     edtReason.Text := '';
end;

procedure TfrmENPlanWorkEdit.sgDismountClick(Sender: TObject);
var
  j, accountingTypeCode : Integer;
begin
   // выведем список ФИН материалов .... если они есть ВААЩЕ ...
  try
    j := StrToInt( TAdvStringGrid(Sender).Cells[0, TAdvStringGrid(Sender).Row ]); //   (sgENEstimateItem,0));
  except
    on EConvertError do
    begin
      if (gbSCCounter.Visible) or (gbSCSeal.Visible) then
        sgDismount.Align := alTop
      else
        sgDismount.Align := alClient;
      Exit;
    end;
  end;

  accountingTypeCode := Integer(TAdvStringGrid(Sender).Objects[2, TAdvStringGrid(Sender).Row]);

  if accountingTypeCode = TK_ACCOUNTINGTYPE_COUNTER then
  begin
    updateSCCounterGrid(j, sgSCCounter);
  end;

  if accountingTypeCode = TK_ACCOUNTINGTYPE_TMC then
  begin
    //updateSCCounterGrid(j, sgSCCounter);
    updateFINMaterialsGrid(j, sgFINUnmount)
  end;

  if (accountingTypeCode = TK_ACCOUNTINGTYPE_SEAL) or
     (accountingTypeCode = TK_ACCOUNTINGTYPE_IMP) or
     (accountingTypeCode = TK_ACCOUNTINGTYPE_HOLO) then
    updateSCSealGrid(j);

  HideControls([gbSCCounter], accountingTypeCode <> TK_ACCOUNTINGTYPE_COUNTER);
  HideControls([gbFINUnmount], accountingTypeCode <> TK_ACCOUNTINGTYPE_TMC);
  HideControls([gbSCSeal], ((accountingTypeCode <> TK_ACCOUNTINGTYPE_SEAL) and
                            (accountingTypeCode <> TK_ACCOUNTINGTYPE_IMP) and
                            (accountingTypeCode <> TK_ACCOUNTINGTYPE_HOLO)));

  if (gbSCCounter.Visible) or (gbFINUnmount.Visible) or (gbSCSeal.Visible) then
    sgDismount.Align := alTop
  else
    sgDismount.Align := alClient;

  //sgENEstimateItemClick(Sender);
end;

procedure TfrmENPlanWorkEdit.btnConvertTranzit2OperativeClick(
  Sender: TObject);
var estimateItemCode: Integer;
begin
  EXIT; // МЕТОД ЗАКРЫТ !!!

  if (HTTPRIOENPlanWork.HTTPWebNode.UserName <> 'energynet') then Exit;
  
  try
    estimateItemCode := StrToInt(sgENEstimateItem.Cells[0,sgENEstimateItem.Row]);
  except
    on EConvertError do Exit;
  end;

  frmConvertTranzit2OperativeEdit := TfrmConvertTranzit2OperativeEdit.Create(Application, dsInsert);
  try
    frmConvertTranzit2OperativeEdit.estimateItemCode := estimateItemCode;
    //frmConvertTranzit2OperativeEdit.departmentCode := ENPlanWorkObj.departmentRef.code;
    //frmConvertTranzit2OperativeEdit.departmentName := edtDepartment.Text;

    frmConvertTranzit2OperativeEdit.budgetCode := ENPlanWorkObj.budgetRef.code;
    frmConvertTranzit2OperativeEdit.budgetName := edtENBudgetName.Text;

    frmConvertTranzit2OperativeEdit.ShowModal;
  finally
    frmConvertTranzit2OperativeEdit.Free;
  end;
end;

procedure TfrmENPlanWorkEdit.btn1Click(Sender: TObject);
var
  TempENPlanWork: ENPlanWorkControllerSoapPort;
  planFilter : ENPlanWorkFilter;
  plan : ENPlanWork;
  planList : ENPlanWorkShortList;
  i : integer;

begin
   TempENPlanWork  := HTTPRIOENPlanWork as ENPlanWorkControllerSoapPort;

   planFilter := ENPlanWorkFilter.Create;
   SetNullIntProps(planFilter);
   SetNullXSProps(planFilter);

   planFilter.conditionSQL :=
     ' code in (select pw.code from finmaterials f, enestimateitem ei, enplanwork pw ' +
     ' where f.statusrefcode = 1 and ei.planrefcode = pw.code and f.estimateitemrefcode = ei.code ' +
     ' and ei.kindrefcode = 1 and pw.kindcode = 2 and pw.yeargen <= 2010 and pw.statuscode = 3 group by pw.code )';

   planList := TempENPlanWork.getScrollableFilteredList(planFilter,0,-1);

   for i:=0 to High(planList.list) do
    begin
        TempENPlanWork.finishPlanWork(planList.list[i].code);
    end;

end;

procedure TfrmENPlanWorkEdit.actFinWorkerAssignToAllExecute(
  Sender: TObject);
   var
    i, eCode : integer;
    state_, isSel, isNotFree : boolean;
    frmFINWorkerAssignToAllEdit : TfrmFINWorkerAssignToAllEdit;
    TempENHumenItem: ENHumenItemControllerSoapPort;
    ENHumenItemObj : ENHumenItem;
begin
  inherited;

  state_ := false;
  isSel := false;
  isNotFree := false;

  for i:=1 to sgENHumenItem.RowCount - 1 do
  begin
     sgENHumenItem.GetCheckBoxState(1,i,state_);
     if state_ then
     begin
       isSel := true;
     end;

     if (sgENHumenItem.Cells[3,i] <> '') and (state_) then
     begin
          isNotFree := true;
     end;

  end;

  if not isSel then
  begin
     Application.MessageBox(PChar('Виберіть хоча б одну посаду!!!'), PChar('Увага !'),MB_ICONWARNING);
     Exit;
  end;

  // 09.12.2011 - Проверка на то, что реальный работник присутствует убрана
  {if isNotFree then
  begin
      Application.MessageBox(PChar('Зніміть позначку з вже обраних працівників!'), PChar('Увага !'),MB_ICONWARNING);
     Exit;
  end;}

  frmFINWorkerAssignToAllEdit := TfrmFINWorkerAssignToAllEdit.Create(Application, dsInsert);
  frmFINWorkerAssignToAllEdit.planCode := ENPlanWorkObj.code;
  try
    if frmFINWorkerAssignToAllEdit.ShowModal = mrOK then
    begin


      TempENHumenItem := HTTPRIOENHumenItem as ENHumenItemControllerSoapPort;
      for i:=1 to sgENHumenItem.RowCount - 1 do
      begin
         sgENHumenItem.GetCheckBoxState(1,i,state_);
         if state_ then
         begin

            try
              eCode := StrToInt( sgENHumenItem.Cells[0, i ]); //   (sgENEstimateItem,0));
            except
              on EConvertError do Exit;
            end;
              ENHumenItemObj := TempENHumenItem.getObject(eCode);




              with frmFINWorkerAssignToAllEdit do
              begin
                  if ENHumenItemObj.finworker = nil then
                  begin
                      ENHumenItemObj.finworker := FINWorker.Create;
                      ENHumenItemObj.finworker.code := low(Integer);
                  end;

                  // 09.12.2011 - Проверка на то, что реальный работник присутствует убрана
                  {if ENHumenItemObj.finWorker.code <> LOW_INT then
                  begin
                      Application.MessageBox(PChar('На нормативній посаді ' +WideCharToString(PWideChar(ENHumenItemObj.positionGen.name)) + ' вже є реальний працівник ' + WideCharToString(PWideChar(ENHumenItemObj.finWorker.name))), PChar('Увага !'),MB_ICONWARNING);
                      pcPlanWorkChange(Sender);
                      Exit;
                  end;}

                  ENHumenItemObj.finworker.name := FINWorkerObj.name;
                  ENHumenItemObj.finworker.tabNumber := FINWorkerObj.tabNumber;
                  ENHumenItemObj.finworker.positionName := FINWorkerObj.positionName;
                  ENHumenItemObj.finworker.positionCode := FINWorkerObj.positionCode;
                  ENHumenItemObj.finworker.departmentName := FINWorkerObj.departmentName;
                  ENHumenItemObj.finworker.departmentCode := FINWorkerObj.departmentCode;
                  if ENHumenItemObj.finworker.priceGen = nil then ENHumenItemObj.finworker.priceGen := TXSDecimal.Create;
                  ENHumenItemObj.finworker.priceGen := FINWorkerObj.priceGen;

                  ENHumenItemObj.finworker.kindRef := FINWorkerKindRef.Create;

                  ENHumenItemObj.finworker.categor := FINWorkerObj.categor;

                  ENHumenItemObj.finWorker.kindRef.code := FINWorkerObj.kindRef.code;

                  ENHumenItemObj.finWorker.finCode := FINWorkerObj.finCode;
                  // 26.02.2015
                  ENHumenItemObj.finWorker.categorId := FINWorkerObj.categorId;
                  ENHumenItemObj.finWorker.categorName := FINWorkerObj.categorName;
                  ENHumenItemObj.finWorker.workTimeId := FINWorkerObj.workTimeId;

                  ENHumenItemObj.finWorker.workTimeId := FINWorkerObj.workTimeId;
                  ENHumenItemObj.finWorker.positionId := FINWorkerObj.positionId;

              end;

              TempENHumenItem.save(ENHumenItemObj);
         end;
      end;

       pcPlanWorkChange(Sender);
    end;
  finally
     frmFINWorkerAssignToAllEdit.Free;
  end;

end;

procedure TfrmENPlanWorkEdit.actFINWorkerAssignToAllTransportExecute(
  Sender: TObject);
   var
    i, eCode : integer;
    state_, isSel, isNotFree : boolean;
    frmFINWorkerAssignToAllEdit : TfrmFINWorkerAssignToAllEdit;
    TempENTransportItem: ENTransportItemControllerSoapPort;
    ENTransportItemObj : ENTransportItem;
begin
  inherited;

  state_ := false;
  isSel := false;
  isNotFree := false;

  for i:=1 to sgENTransportItem.RowCount - 1 do
  begin
     sgENTransportItem.GetCheckBoxState(1,i,state_);
     if state_ then
     begin
       isSel := true;
     end;


     if ((sgENTransportItem.Cells[3,i] <> '') or (sgENTransportItem.Cells[3,i] <> ' ')) and (state_) then
     begin
          isNotFree := true;
     end;

  end;

  if not isSel then
  begin
     Application.MessageBox(PChar('Виберіть хоча б однин транспорт!!!'), PChar('Увага !'),MB_ICONWARNING);
     Exit;
  end;

  // 09.12.2011 - Проверка на то, что реальный водитель присутствует убрана
  {if isNotFree then
  begin
      Application.MessageBox(PChar('Зніміть позначку із транспортів з вже обраними водіями!'), PChar('Увага !'),MB_ICONWARNING);
     Exit;
  end;}

  frmFINWorkerAssignToAllEdit := TfrmFINWorkerAssignToAllEdit.Create(Application, dsInsert);
  frmFINWorkerAssignToAllEdit.planCode := ENPlanWorkObj.code;
  frmFINWorkerAssignToAllEdit.isDriver := true;
  try
    if frmFINWorkerAssignToAllEdit.ShowModal = mrOK then
    begin


      TempENTransportItem := HTTPRIOENTransportItem as ENTransportItemControllerSoapPort;
      for i:=1 to sgENTransportItem.RowCount - 1 do
      begin
         sgENTransportItem.GetCheckBoxState(1,i,state_);
         if state_ then
         begin

            try
              eCode := StrToInt( sgENTransportItem.Cells[0, i ]); //   (sgENEstimateItem,0));
            except
              on EConvertError do Exit;
            end;
              ENTransportItemObj := TempENTransportItem.getObject(eCode);




              with frmFINWorkerAssignToAllEdit do
              begin
                  if ENTransportItemObj.finworker = nil then
                  begin
                      ENTransportItemObj.finworker := FINWorker.Create;
                      ENTransportItemObj.finworker.code := low(Integer);
                  end;

                  // 09.12.2011 - Проверка на то, что реальный водитель присутствует убрана
                  {if ENTransportItemObj.finWorker.code <> LOW_INT then
                  begin
                      Application.MessageBox(PChar('На нормативному транспорті ' +WideCharToString(PWideChar(ENTransportItemObj.transport.name)) + ' вже є реальний водій ' + WideCharToString(PWideChar(ENTransportItemObj.finWorker.name))), PChar('Увага !'),MB_ICONWARNING);
                      pcPlanWorkChange(Sender);
                      Exit;
                  end;}

                  ENTransportItemObj.finworker.name := FINWorkerObj.name;
                  ENTransportItemObj.finworker.tabNumber := FINWorkerObj.tabNumber;
                  ENTransportItemObj.finworker.positionName := FINWorkerObj.positionName;
                  ENTransportItemObj.finworker.positionCode := FINWorkerObj.positionCode;
                  ENTransportItemObj.finworker.departmentName := FINWorkerObj.departmentName;
                  ENTransportItemObj.finworker.departmentCode := FINWorkerObj.departmentCode;
                  if ENTransportItemObj.finworker.priceGen = nil then ENTransportItemObj.finworker.priceGen := TXSDecimal.Create;
                  ENTransportItemObj.finworker.priceGen := FINWorkerObj.priceGen;

                  ENTransportItemObj.finworker.kindRef := FINWorkerKindRef.Create;

                  ENTransportItemObj.finworker.categor := FINWorkerObj.categor;

                  ENTransportItemObj.finWorker.kindRef.code := FINWorkerObj.kindRef.code;

                  ENTransportItemObj.finWorker.finCode := FINWorkerObj.finCode;

                  // 26.02.2015
                  ENTransportItemObj.finWorker.categorId := FINWorkerObj.categorId;
                  ENTransportItemObj.finWorker.categorName := FINWorkerObj.categorName;
                  ENTransportItemObj.finWorker.workTimeId:= FINWorkerObj.workTimeId;

                  ENTransportItemObj.finWorker.positionId:= FINWorkerObj.positionId;

              end;

              TempENTransportItem.save(ENTransportItemObj);
         end;
      end;

       pcPlanWorkChange(Sender);
    end;
  finally
     frmFINWorkerAssignToAllEdit.Free;
  end;

end;


procedure TfrmENPlanWorkEdit.actRealTransportAssignToAllExecute(
  Sender: TObject);
   var
    i, eCode : integer;
    state_, isSel, isNotFree : boolean;
    frmRealTransportAssignToAllEdit : TfrmRealTransportAssignToAllEdit;
    TempENTransportItem: ENTransportItemControllerSoapPort;
    ENTransportItemObj : ENTransportItem;
begin
  inherited;

  state_ := false;
  isSel := false;
  isNotFree := false;

  for i:=1 to sgENTransportItem.RowCount - 1 do
  begin
     sgENTransportItem.GetCheckBoxState(1,i,state_);
     if state_ then
     begin
       isSel := true;
     end;


     if ((sgENTransportItem.Cells[2,i] <> '') or (sgENTransportItem.Cells[2,i] <> ' ')) and (state_) then
     begin
          isNotFree := true;
     end;

  end;

  if not isSel then
  begin
     Application.MessageBox(PChar('Виберіть хоча б однин транспорт!!!'), PChar('Увага !'),MB_ICONWARNING);
     Exit;
  end;

  // 09.12.2011 - Проверка на то, что реальный транспорт присутствует убрана
  {if isNotFree then
  begin
      Application.MessageBox(PChar('Зніміть позначку із транспортів з вже обраними реальними транспортами!'), PChar('Увага !'),MB_ICONWARNING);
     Exit;
  end;}

  frmRealTransportAssignToAllEdit := TfrmRealTransportAssignToAllEdit.Create(Application, dsInsert);
  frmRealTransportAssignToAllEdit.planCode := ENPlanWorkObj.code;
  try
    if frmRealTransportAssignToAllEdit.ShowModal = mrOK then
    begin


      TempENTransportItem := HTTPRIOENTransportItem as ENTransportItemControllerSoapPort;
      for i:=1 to sgENTransportItem.RowCount - 1 do
      begin
         sgENTransportItem.GetCheckBoxState(1,i,state_);
         if state_ then
         begin

            try
              eCode := StrToInt( sgENTransportItem.Cells[0, i ]); //   (sgENEstimateItem,0));
            except
              on EConvertError do Exit;
            end;
              ENTransportItemObj := TempENTransportItem.getObject(eCode);

              with frmRealTransportAssignToAllEdit do
              begin

                  // 09.12.2011 - Проверка на то, что реальный водитель присутствует убрана
                  {if ENTransportItemObj.transportReal.code <> LOW_INT then
                  begin
                      Application.MessageBox(PChar('На нормативному транспорті ' +WideCharToString(PWideChar(ENTransportItemObj.transport.name)) + ' вже обраний реальний транспорт ' + WideCharToString(PWideChar(ENTransportItemObj.finWorker.name))), PChar('Увага !'),MB_ICONWARNING);
                      pcPlanWorkChange(Sender);
                      Exit;
                  end;}

                  if ENTransportItemObj.transportReal = nil then ENTransportItemObj.transportReal := TKTransportReal.Create();
                     ENTransportItemObj.transportReal.code := TKTransportRealObj.code;
              end;

              TempENTransportItem.save(ENTransportItemObj);
         end;
      end;

       pcPlanWorkChange(Sender);
    end;
  finally
     frmFINWorkerAssignToAllEdit.Free;
  end;
end;



procedure TfrmENPlanWorkEdit.btnPrintWorkOrderClick(Sender: TObject);
var
  workOrder : ENWorkOrder;

  TempENHumenItem: ENHumenItemControllerSoapPort;
  ENHumenItemList: ENHumenItemShortList;
  humenItemFilter : ENHumenItemFilter;
  TempENPlanWorkItem : ENPlanWorkItemControllerSoapPort;
  planWorkItemFilter : ENPlanWorkItemFilter;
  planWorkItemList : ENPlanWorkItemShortList;
  argNames, args: ArrayOfString;
  condition : String;
  reportName: String;

  TempENServicesObject: ENServicesObjectControllerSoapPort;
  i: Integer;
  ENServicesObjectList: ENServicesObjectShortList;
  element : ENElement;
  TempENElement: ENElementControllerSoapPort;
  servicesObjectFilter: ENServicesObjectFilter;
  servicesObjectArr: ENServicesObjectController.ArrayOfInteger;
  servicesObject: ENServicesObject;
  TempENPayment2SO: ENPayment2SOControllerSoapPort;
  payment2SOFilter: ENPayment2SOFilter;
  payment2SOList: ENPayment2SOShortList;
begin
  inherited;
  // NET-4425 п2. не даем печатать наряд задание если это услуга и тип контрагента не юр.бюджет и не было оплаты (на вкладке договора фактичні оплати замовником )
  //
  if ( (ENPlanWorkObj.typeRef.code = ENConsts.ENPLANWORKTYPE_SIDEWORKS )
     and
       (ENPlanWorkObj.stateRef.code = ENConsts.ENPLANWORKSTATE_SIDEWORKS )
     )  then
     begin
     TempENElement := httprioenelement  as  ENElementControllerSoapPort;
     element := TempENElement.getObject(ENPlanWorkObj.elementRef.code);
     if (element.typeRef.code = EN_SERVICES_OBJECT) then
     begin
       TempENServicesObject := HTTPRIOENServicesObject as ENServicesObjectControllerSoapPort;

       servicesObjectFilter := ENServicesObjectFilter.Create;
       SetNullIntProps(servicesObjectFilter);
       SetNullXSProps(servicesObjectFilter);
       servicesObjectFilter.element := ENElement.Create;
       servicesObjectFilter.element.code :=  ENPlanWorkObj.elementRef.code;
       // кроме юр.бюджета (для них может печататься наряд задание без оплаты )
       // также кроме безоплатных договоров
       // проверка только по лицензионным работам
       servicesObjectFilter.conditionSQL := ' enservicesobject.contracttyperefcode in (1,2) and enservicesobject.contragenttyperefcode <> ' + IntToStr(ENConsts.ENSERVICESOBJECT_CONTRAGENTTYPE_JURIDICAL_BUDGET) + ' and coalesce(enservicesobject.isnopay,0) <> 1 ' +
        '/*лиценз работы*/ ' +
        ' and (select count(p2c.code) from enplanwork2classfctntp p2c , enplanwork p , tkclassificationtype tp ' +
        '        where p2c.planrefcode = p.code' +
        '        and p.kindcode =  ' + IntToStr( ENConsts.ENPLANWORKKIND_CALCULATION ) +
        '        and p.elementrefcode = enservicesobject.elementcode' +
        '        and p2c.classificationtyperfcd = tp.code' +
        '        and tp.isnotlicensedactivity = 0 ) > 0' ;

       servicesObjectArr := TempENServicesObject.getScrollableFilteredCodeArray(servicesObjectFilter, 0, -1);

       if High(servicesObjectArr) > -1 then
       begin
         servicesObject := TempENServicesObject.getObject(servicesObjectArr[0]);
         if servicesObject <> nil then
           begin

            TempENPayment2SO := HTTPRIOENPayment2SO as ENPayment2SOControllerSoapPort;

            payment2SOFilter := ENPayment2SOFilter.Create;
            SetNullIntProps(payment2SOFilter);
            SetNullXSProps(payment2SOFilter);
            payment2SOFilter.servicesObjectRef := ENServicesObjectRef.Create;
            payment2SOFilter.servicesObjectRef.code := servicesObject.code;
            payment2SOList := TempENPayment2SO.getScrollableFilteredList(payment2SOFilter,0,-1);

             if High(payment2SOList.list) = -1 then
               begin
                Application.MessageBox(PChar('Друк Наряд-завдання неможливий, на послугу не внесені оплати!!! '), PChar('Увага!'), MB_ICONWARNING);
                exit;
               end;
           end;
       end;
     end;
   end;


  // не даем печатать наряд-задание если нету на плане наряд-задания .
  workOrder := DMReports.getWorkOrderByPlanCode(ENPlanWorkObj.code);
  if workOrder.code = Low(Integer) then
  begin
     Application.MessageBox(PChar('Друк Наряд-завдання неможливий додайте Наряд-завдання на План ...!'), PChar('Увага!'), MB_ICONWARNING);

     exit;
  end;

  // не даем печататть наряд-задание если на плане ниводной работе не подвязан реальный человек.

          humenItemFilter := ENHumenItemFilter.Create;
          SetNullIntProps(humenItemFilter);
          SetNullXSProps(humenItemFilter);
        

        if HumenItemFilter.planRef = nil then HumenItemFilter.planRef := ENPlanWorkRef.Create;
        HumenItemFilter.planRef.code := ENPlanWorkObj.code;

        HumenItemFilter.conditionSQL := 'enhumenitem.planitemrefcode in ('+
                                'select enplanworkitem.code from enplanworkitem where enplanworkitem.planrefcode ='+ IntToStr(ENPlanWorkObj.code) +
                                ' and enplanworkitem.countgen<>0'+
                                ') and enhumenitem.finworkercode is not null ';

        HumenItemFilter.orderBySQL := 'enhumenitem.planitemrefcode';

        TempENHumenItem := HTTPRIOENHumenItem as ENHumenItemControllerSoapPort;

        ENHumenItemList := TempENHumenItem.getScrollableFilteredList(HumenItemFilter, 0, -1);

        if (High(ENHumenItemList.list) = -1) and (not isTransport) then
         begin
          Application.MessageBox(PChar('Друк Наряд-завдання неможливий додайте персонал на роботи ...!'), PChar('Увага!'), MB_ICONWARNING);
          exit;
         end;


         SetLength(argNames, 1);
         SetLength(args, 1);

         argnames[0] := 'planworkcode';
         args[0] := IntToStr(ENPlanWorkObj.code);
         
         reportName := 'Zavdannya_z_planu_A4/Main';
         makeReport(reportName, argNames, args, 'pdf');

         // Печать листа ОВБ если в плане есть работы с определенными кодами
         planWorkItemFilter := ENPlanWorkItemFilter.Create;
         SetNullXSProps(planWorkItemFilter);
         SetNullIntProps(planWorkItemFilter);
         if planWorkItemFilter.planRef = nil then planWorkItemFilter.planRef := ENPlanWorkRef.Create;
         planWorkItemFilter.planRef.code := ENPlanWorkObj.code;
         // SUPP-1417 - коды работ взяты со служебки
         condition := 'ENPLANWORKITEM.KARTAREFCODE IN (' +'500012655,' +
                                                  '500012592,' +
                                                  '500012593,' +
                                                  '500012594,' +
                                                  '500012652,' +
                                                  '500012653,' +
                                                  '500012654' + ')';
         planWorkItemFilter.conditionSQL := condition + ' AND ENPLANWORKITEM.COUNTGEN > 0';
         TempENPlanWorkItem := HTTPRIOENPlanWorkItem as ENPlanWorkItemControllerSoapPort;
         planWorkItemList := TempENPlanWorkItem.getScrollableFilteredList(planWorkItemFilter, 0, 1);
         if planWorkItemList.totalCount > 0 then
         begin
              SetLength(argNames, 1);
              SetLength(args, 1);

              argnames[0] := 'dull_parameter';
              args[0] := IntToStr(ENPlanWorkObj.code);

              reportName := 'Zavdannya_z_planu_A4/OVBList';
              makeReport(reportName, argNames, args, 'pdf');
         end;


end;

procedure TfrmENPlanWorkEdit.spbServicesFromSideClick(Sender: TObject);
var
   frmFINServicesObjectShow: TfrmFINServicesObjectShow;
   f : ENServicesFromSideObjectFilter;
   ENPlanwork2GeneralContractsObj: ENPlanwork2GeneralContracts;
   TempENPlanwork2GeneralContracts: ENPlanwork2GeneralContractsControllerSoapPort;

begin
  // теперь выбирать объект договора услуг со стороны, если нету в списке то должны добавить объект
   f := ENServicesFromSideObjectFilter.Create;
   SetNullXSProps(f);
   SetNullIntProps(f);

   if (edtServicesFromSide.Text <> '') then
     f.contractNumber := edtServicesFromSide.Text
   else
     f.contractNumber := '-1';

   //f.conditionSQL := 'a.io_flag = ''B''';

   frmENServicesFromSideObjectShow := TfrmENServicesFromSideObjectShow.Create(Application,fmNormal, f);
   try
      with frmENServicesFromSideObjectShow do
        if ShowModal = mrOk then
        begin
            try
               edtServicesFromSide.Text := GetReturnValue(sgENServicesFromSideObject, 1);
               ENPlanWorkObj.servicesFSideCnNum := GetReturnValue(sgENServicesFromSideObject, 1);
               ENPlanWorkObj.servicesFSideFinId := StrToInt(GetReturnValue(sgENServicesFromSideObject, 6));
               ENPlanWorkObj.partnerCode:= GetReturnValue(sgENServicesFromSideObject, 4); // код организации
               ENPlanWorkObj.finDocCode:=  GetReturnValue(sgENServicesFromSideObject, 5); // код договора
               edtPartner.Text:= GetReturnValue(sgENServicesFromSideObject, 3);


            except
               on EConvertError do Exit;
            end;
        end;
   finally
      frmFINServicesObjectShow.Free;
   end;
  { f := ENServicesObjectFilter.Create();
   SetNullXSProps(f);
   SetNullIntProps(f);

   if (edtServicesFromSide.Text <> '') then
     f.contractNumber := edtServicesFromSide.Text
   else
     f.contractNumber := '-1';

   f.conditionSQL := 'a.io_flag = ''B''';

   frmFINServicesObjectShow := TfrmFINServicesObjectShow.Create(Application,fmNormal, f);
   frmFINServicesObjectShow.contrAgentType := AX_CONTRAGENT_TYPE_VENDOR;


   try
      with frmFINServicesObjectShow do
        if ShowModal = mrOk then
        begin
            try
               edtServicesFromSide.Text := GetReturnValue(sgFINServicesObject, 1);
               ENPlanWorkObj.servicesFSideCnNum := GetReturnValue(sgFINServicesObject, 1);
               ENPlanWorkObj.servicesFSideFinId := StrToInt(GetReturnValue(sgFINServicesObject, 6));
               ENPlanWorkObj.partnerCode:= GetReturnValue(sgFINServicesObject, 4); // код организации
               ENPlanWorkObj.finDocCode:=  GetReturnValue(sgFINServicesObject, 5); // код договора
               edtPartner.Text:= GetReturnValue(sgFINServicesObject, 3);


            except
               on EConvertError do Exit;
            end;
        end;
   finally
      frmFINServicesObjectShow.Free;
   end;  }
end;


procedure TfrmENPlanWorkEdit.spbDdsCodesClick(Sender: TObject);
var
  frmRQDDSCodesShow : TfrmRQDDSCodesShow;
  f : RQDDSCodesFilter;
begin
  f := RQDDSCodesFilter.Create;
  SetNullIntProps(f);
  SetNullXSProps(f);
  f.isActual := 1;
  f.conditionSQL := 'q1.PARENTDDSCODESREFCODE is null';
  frmRQDDSCodesShow := TfrmRQDDSCodesShow.Create(Application,fmNormal,f);

  try
    with frmRQDDSCodesShow do
    if ShowModal = mrOk then
    begin

     if tvDDSCodes.Selected = nil then Exit;
     if tvDDSCodes.Selected.Data = nil then Exit;
        try
          if ENPlanWorkObj.ddsCodes = nil then ENPlanWorkObj.ddsCodes := RQDDSCodes.Create();
          ENPlanWorkObj.ddsCodes.code := RQDDSCodesShort(tvDDSCodes.Selected.Data).code;
          edtDdsCodes.Text := RQDDSCodesShort(tvDDSCodes.Selected.Data).txtCode + ' ' +
                  RQDDSCodesShort(tvDDSCodes.Selected.Data).name;
        except
          on EConvertError do Exit;
        end;
    end;
   finally
      frmRQDDSCodesShow.Free;
   end;
end;


procedure TfrmENPlanWorkEdit.spbInvestProgramGroupsClick(Sender: TObject);
var
   frmENInvestProgramGroupsShow : TfrmENInvestProgramGroupsShow;
   f : ENInvestProgramGroupsFilter;
begin
   f := ENInvestProgramGroupsFilter.Create;
   SetNullIntProps(f);
   SetNullXSProps(f);

   frmENInvestProgramGroupsShow := TfrmENInvestProgramGroupsShow.Create(Application, fmNormal, f);

   try
      with frmENInvestProgramGroupsShow do begin
        if ShowModal = mrOk then
        begin
            try
               if ENPlanWorkObj.invgroupRef = nil then ENPlanWorkObj.invgroupRef := ENInvestProgramGroupsRef.Create();
               ENPlanWorkObj.invgroupRef.code := StrToInt(GetReturnValue(sgENInvestProgramGroups,0));
               edtInvestProgramGroupsName.Text := GetReturnValue(sgENInvestProgramGroups,1);
            except
               on EConvertError do Exit;
            end;
        end;
      end;
   finally
      frmENInvestProgramGroupsShow.Free;
   end;
   
end;


procedure TfrmENPlanWorkEdit.spbIPImplementationTypeClick(Sender: TObject);
var frmENIPImplementationTypeShow: TfrmENIPImplementationTypeShow;
begin
  frmENIPImplementationTypeShow := TfrmENIPImplementationTypeShow.Create(Application, fmNormal);
  try
    frmENIPImplementationTypeShow.DisableActions([frmENIPImplementationTypeShow.actInsert,
                                                  frmENIPImplementationTypeShow.actEdit,
                                                  frmENIPImplementationTypeShow.actDelete]);
    with frmENIPImplementationTypeShow do
    begin
      if ShowModal = mrOk then
      begin
        try
          if ENPlanWorkObj.ipImplementTypeRef = nil then ENPlanWorkObj.ipImplementTypeRef := ENIPImplementationTypeRef.Create();
          ENPlanWorkObj.ipImplementTypeRef.code := StrToInt(GetReturnValue(sgENIPImplementationType, 0));
          edtIPImplementationType.Text := GetReturnValue(sgENIPImplementationType, 1);
        except
          on EConvertError do Exit;
        end;
      end;
    end;
  finally
    frmENIPImplementationTypeShow.Free;
  end;
end;

procedure TfrmENPlanWorkEdit.spbMOLStorekeeperClearClick(Sender: TObject);
var
  ENMOLStorekeeper2PlanWorkObj: ENMOLStorekeeper2PlanWork;
  TempENMOLStorekeeper2PlanWork: ENMOLStorekeeper2PlanWorkControllerSoapPort;
  ENMOLStorekeeper2PlanWorkFilterObj : ENMOLStorekeeper2PlanWorkFilter;
  ENMOLStorekeeper2PlanWorkList: ENMOLStorekeeper2PlanWorkShortList;
  i : integer;
begin
  inherited;
if Application.MessageBox(PChar('Видалити МОЛа комірника ?'),
                                       PChar('Увага!'),
                                       MB_ICONQUESTION + MB_YESNO + MB_DEFBUTTON2) = IDYES then
begin
               TempENMOLStorekeeper2PlanWork :=  HTTPRIOENMOLSTOREKEEPER2PlanWork as ENMOLStorekeeper2PlanWorkControllerSoapPort;
               ENMOLStorekeeper2PlanWorkFilterObj := ENMOLStorekeeper2PlanWorkFilter.Create;
               SetNullXSProps(ENMOLStorekeeper2PlanWorkFilterObj);
               SetNullIntProps(ENMOLStorekeeper2PlanWorkFilterObj);

               ENMOLStorekeeper2PlanWorkFilterObj.planRef := ENPlanWorkRef.Create;
               ENMOLStorekeeper2PlanWorkFilterObj.planRef.code := ENPlanWorkObj.code;
               ENMOLStorekeeper2PlanWorkList := TempENMOLStorekeeper2PlanWork.getScrollableFilteredList(ENMOLStorekeeper2PlanWorkFilterObj, 0, -1);
               for i:=0 to ENMOLStorekeeper2PlanWorkList.totalCount-1 do
               begin
                   TempENMOLStorekeeper2PlanWork.remove(ENMOLStorekeeper2PlanWorkList.list[i].code);
               end;
               edtMOLStorekeeper.Text := '';
end;

end;

procedure TfrmENPlanWorkEdit.spbMOLStorekeeperClick(Sender: TObject);
var
 f : FINMolFilter;
 frmFINMolShow : TfrmFINMolShow;

 TempENDepartment2EPRen: ENDepartment2EPRenControllerSoapPort;
 i: Integer;
 ENDepartment2EPRenList: ENDepartment2EPRenShortList;
 renFilter : ENDepartment2EPRenFilter;
 renList : ENDepartment2EPRenShortList;


 ENMOLStorekeeper2PlanWorkObj: ENMOLStorekeeper2PlanWork;
 TempENMOLStorekeeper2PlanWork: ENMOLStorekeeper2PlanWorkControllerSoapPort;
 ENMOLStorekeeper2PlanWorkFilterObj : ENMOLStorekeeper2PlanWorkFilter;
 ENMOLStorekeeper2PlanWorkList: ENMOLStorekeeper2PlanWorkShortList;

  molSel : boolean;

begin
 f := FINMolFilter.Create;
 SetNullXSProps(f);
 SetNullIntProps(f);
 f.state := 1; // типа только работающие ...



   if ENPlanWorkObj.departmentRef <> nil then
     if ENPlanWorkObj.departmentRef.code <> LOW_INT then
     begin
        TempENDepartment2EPRen :=  HTTPRIOENDepartment2EPRen as ENDepartment2EPRenControllerSoapPort;
        renFilter := ENDepartment2EPRenFilter.Create;
        SetNullXSProps(renFilter);
        SetNullIntProps(renFilter);

        renFilter.departmentRef := ENDepartmentRef.Create;
        renFilter.departmentRef.code := ENPlanWorkObj.departmentRef.code;  // ENWorkOrder2ENPlanWorkObj.workOrder.department.code;

        renList := TempENDepartment2EPRen.getScrollableFilteredList(renFilter,0,-1);
        if renList.totalCount > 0 then
          f.conditionSQL := 'substr(UMC_DBA.TDIVISION.ID,1,2) = ' + IntToStr(renList.list[0].finRenCode) ;
      end;

   frmFINMolShow:=TfrmFINMolShow.Create(Application,fmNormal, f);

   try

      if length(f.conditionSQL) > 0 then
        frmFINMolShow.isFiltered := true;

      with frmFINMolShow do begin
        DisableActions([ actEdit, actInsert ]);
        if ShowModal = mrOk then
        begin
            try

               TempENMOLStorekeeper2PlanWork :=  HTTPRIOENMOLSTOREKEEPER2PlanWork as ENMOLStorekeeper2PlanWorkControllerSoapPort;
               ENMOLStorekeeper2PlanWorkFilterObj := ENMOLStorekeeper2PlanWorkFilter.Create;
               SetNullXSProps(ENMOLStorekeeper2PlanWorkFilterObj);
               SetNullIntProps(ENMOLStorekeeper2PlanWorkFilterObj);

               ENMOLStorekeeper2PlanWorkFilterObj.planRef := ENPlanWorkRef.Create;
               ENMOLStorekeeper2PlanWorkFilterObj.planRef.code := ENPlanWorkObj.code;
               ENMOLStorekeeper2PlanWorkList := TempENMOLStorekeeper2PlanWork.getScrollableFilteredList(ENMOLStorekeeper2PlanWorkFilterObj, 0, -1);
               if ENMOLStorekeeper2PlanWorkList.totalCount > 0 then
                  ENMOLStorekeeper2PlanWorkObj := TempENMOLStorekeeper2PlanWork.getObject(ENMOLStorekeeper2PlanWorkList.list[0].code)
               else
               begin
                 ENMOLStorekeeper2PlanWorkObj := ENMOLStorekeeper2PlanWork.Create();
                 ENMOLStorekeeper2PlanWorkObj.code := LOW_INT;
                 ENMOLStorekeeper2PlanWorkObj.planRef := ENPlanWorkRef.Create();
                 ENMOLStorekeeper2PlanWorkObj.planRef.code := ENPlanWorkObj.code;
               end;

               ENMOLStorekeeper2PlanWorkObj.molName := GetReturnValue(sgFINMol,1);
               ENMOLStorekeeper2PlanWorkObj.molCode := GetReturnValue(sgFINMol,0);

               //edtMolName.Text := ENMOL2PlanWorkObj.molName;  //GetReturnValue(sgFINMol,0);
               edtMOLStorekeeper.Text := ENMOLStorekeeper2PlanWorkObj.molCode + ' ' + ENMOLStorekeeper2PlanWorkObj.molName;
               //edtFINMolName.Text := //GetReturnValue(sgFINMol,1); //ENDepartmentShort(tvDep.Selected.Data).shortName;
               //ENWorkOrder2ENPlanWorkObj.workOrder.finMolCode := GetReturnValue(sgFINMol,0);
               //ENWorkOrder2ENPlanWorkObj.workOrder.finMolName := GetReturnValue(sgFINMol,1);

               if ENMOLStorekeeper2PlanWorkObj.code > LOW_INT then
                  TempENMOLStorekeeper2PlanWork.save(ENMOLStorekeeper2PlanWorkObj)
               else
                  TempENMOLStorekeeper2PlanWork.add(ENMOLStorekeeper2PlanWorkObj);


            except
               on EConvertError do Exit;
            end;
        end;
      end;
   finally
      frmFINMolShow.Free;
   end;


end;

procedure TfrmENPlanWorkEdit.spbOSSelectClick(Sender: TObject);
var
frmOSTableShow: TfrmOSTableShow;
f : OStableFilter;
// workOrder : ENWorkOrder;

  TempENMOL2PlanWork: ENMOL2PlanWorkControllerSoapPort;
  ENMOL2PlanWorkFilterObj : ENMOL2PlanWorkFilter;
  ENMOL2PlanWorkList: ENMOL2PlanWorkShortList;
begin
    f := OStableFilter.Create;
         SetNullIntProps(f);
         SetNullXSProps(f);

      TempENMOL2PlanWork :=  HTTPRIOENMOL2PlanWork as ENMOL2PlanWorkControllerSoapPort;
      ENMOL2PlanWorkFilterObj := ENMOL2PlanWorkFilter.Create;
      SetNullXSProps(ENMOL2PlanWorkFilterObj);
      SetNullIntProps(ENMOL2PlanWorkFilterObj);

      ENMOL2PlanWorkFilterObj.planRef := ENPlanWorkRef.Create;
      ENMOL2PlanWorkFilterObj.planRef.code := ENPlanWorkObj.code;
      ENMOL2PlanWorkList := TempENMOL2PlanWork.getScrollableFilteredList(ENMOL2PlanWorkFilterObj, 0, -1);

     if ENMOL2PlanWorkList.totalCount = 0 then
     begin
       Application.MessageBox(PChar('Введіть на вкладці "Основне" МВО на якому значиться основний засіб!!!'), PChar('Увага !'),MB_ICONWARNING);
       Exit;
     end;
     if ENMOL2PlanWorkList.list[0].molCode = '' then
     begin
       Application.MessageBox(PChar('Введіть на вкладці "Основне" МВО на якому значиться основний засіб!!!'), PChar('Увага !'),MB_ICONWARNING);
       Exit;
     end;

     f.orderBySQL :=  'OSTABLE.STR_NAME';
     f.kod_mol := ENMOL2PlanWorkList.list[0].molCode;
     f.conditionSQL := ' nvl(ostable.energy_lock,0) < 1  '; // не берем залоченые
   frmOSTableShow:=TfrmOSTableShow.Create(Application,fmNormal,f);
   frmOSTableShow.isWriteOffOS := true;
   frmOSTableShow.kod_mol := ENMOL2PlanWorkList.list[0].molCode;
   DisableActions([frmOSTableShow.actNoFilter]);
   try
      with frmOSTableShow do
        if ShowModal = mrOk then
        begin
            try

               edtKod_inv.Text :=  GetReturnValue(sgOSTable,2);
               edtName_inv.Text := GetReturnValue(sgOSTable,1);
               //edtCodeMol.Text := GetReturnValue(sgOSTable,5);
               //edtCodePodr.Text := GetReturnValue(sgOSTable,6);

               ENPlanWorkENAct2OSDataObj.num_un := StrToInt(GetReturnValue(sgOSTable,0));
               ENPlanWorkENAct2OSDataObj.kod_inv :=   GetReturnValue(sgOSTable,2);
               ENPlanWorkENAct2OSDataObj.name_inv :=  GetReturnValue(sgOSTable,1);
               ENPlanWorkENAct2OSDataObj.kod_vid := GetReturnValue(sgOSTable,7);
               ENPlanWorkENAct2OSDataObj.kod_subsch_b := GetReturnValue(sgOSTable,3);

            except
               on EConvertError do Exit;
            end;
        end;
   finally
      frmOSTableShow.Free;
   end;

end;

procedure TfrmENPlanWorkEdit.sgLinkedPlansDblClick(Sender: TObject);
  var
  frmViewLinkedPlan : TfrmENPlanWorkEdit;
  TempENPlanWork: ENPlanWorkControllerSoapPort;
  objcode : integer;
begin

         try
           ObjCode := StrToInt(sgLinkedPlans.Cells[0,sgLinkedPlans.Row]);
         except
         on EConvertError do Exit;
        end;

  frmViewLinkedPlan := TfrmENPlanWorkEdit.Create(Application, dsView);

  try

    TempENPlanWork  := HTTPRIOENPlanWork as ENPlanWorkControllerSoapPort;
    frmViewLinkedPlan.ENPlanWorkObj := ENPlanWork.Create;
    frmViewLinkedPlan.ENPlanWorkObj := TempENPlanWork.getObject(ObjCode);

    frmViewLinkedPlan.Caption := 'Пов''язаний план';
    frmViewLinkedPlan.ShowModal;
  finally
    frmViewLinkedPlan.Free;
    frmViewLinkedPlan:=nil;
  end;

end;

procedure TfrmENPlanWorkEdit.actChangeCountFactExecute(Sender: TObject);
var
  TempENEstimateItem: ENEstimateItemControllerSoapPort;
  grid: TAdvStringGrid;
begin
  if sgENEstimateItem.Visible then
    grid := sgENEstimateItem
  else if sgENEstimateItemWithFin.Visible then
    grid := sgENEstimateItemWithFin
  else
    raise Exception.Create('NET-4061 Unknown ENEstimateItem grid!');

  TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;
  try
    //ENEstimateItemObj := TempENEstimateItem.getObject(StrToInt(sgENEstimateItem.Cells[0, sgENEstimateItem.Row]));
    ENEstimateItemObj := TempENEstimateItem.getObject(StrToInt(grid.Cells[0, grid.Row]));
  except
    on EConvertError do Exit;
  end;

  frmENEstimateItemChangeCountFactEdit := TfrmENEstimateItemChangeCountFactEdit.Create(Application, dsInsert);
  try
    begin
    if frmENEstimateItemChangeCountFactEdit.ShowModal = mrOK then
    begin
      ENEstimateItemObj.countFact := TXSDecimal.Create;
      ENEstimateItemObj.countFact.decimalString := frmENEstimateItemChangeCountFactEdit.edtCountFact.Text;
      TempENEstimateItem.changeCountFact(ENEstimateItemObj);
    end;

    pcPlanWorkChange(Sender);
    end;
  finally
     frmENEstimateItemChangeCountFactEdit.Free;
  end;
end;

procedure TfrmENPlanWorkEdit.sgGroupedTransportItemClick(Sender: TObject);
var
  TempENTransportItem: ENTransportItemControllerSoapPort;
  ENTransportItemList: ENTransportItemShortList;
  transportItemFilter: ENTransportItemFilter;
  transportCode,
  tiLastCount, tiLastRow, tiColCount, i,j: Integer;
  TempENTransportOrder :  ENTransportOrderControllerSoapPort;
    wopwList : ENWorkOrder2ENPlanWorkShortList;
    wopwFilter : ENWorkOrder2ENPlanWorkFilter;
    TempWOPW : ENWorkOrder2ENPlanWorkControllerSoapPort;
    TempFINMolData : FINMolDataControllerSoapPort;
    fmdList : FINMolDataShortList;
    fmdFilter : FINMolDataFilter;


begin
  {10.02.2012 - проверка на наличие наряда}
  wopwFilter := ENWorkOrder2ENPlanWorkFilter.Create;
  SetNullIntProps(wopwFilter);
  SetNullXSProps(wopwFilter);

  wopwFilter.plan := ENPlanWork.Create;
  wopwFilter.plan.code := ENPlanWorkObj.code;

  TempWOPW := HTTPRIOENWorkOrder2ENPlanWork as ENWorkOrder2ENPlanWorkControllerSoapPort;

  wopwList := TempWOPW.getScrollableFilteredList(wopwFilter, 0, -1);

 if (wopwList.totalCount = 0) then
  begin
     Application.MessageBox(PChar('Введіть наряд!!!'), PChar('Увага !'),MB_ICONWARNING);
     Exit;
  end;

  {10.02.2012 - проверка мастера в наряде}
  fmdFilter := FINMolDataFilter.Create;
  SetNullIntProps(fmdFilter);
  SetNullXSProps(fmdFilter);

  fmdFilter.workOrder := ENWorkOrder.Create;
  fmdFilter.workOrder.code := wopwList.list[0].workOrderCode;

  fmdFilter.molTypeRef := FINMolTypeRef.Create;
  fmdFilter.molTypeRef.code := ENConsts.FINMOLTYPE_MASTER;

  TempFINMolData := HTTPRIOFINMolData as FINMolDataControllerSoapPort;

  fmdList := TempFINMolData.getScrollableFilteredList(fmdFilter, 0, -1);

   if (fmdList.totalCount = 0) then
  begin
     Application.MessageBox(PChar('Введіть майстра в наряді!!!'), PChar('Увага !'),MB_ICONWARNING);
     Exit;
  end;

  {15/03/2012 - проверка виконавця на плане}
  if ENPlanWorkObj.finExecutor.code = Low(Integer) then
  begin
      Application.MessageBox(PChar('Введіть виконавця на плані!!!'), PChar('Увага !'),MB_ICONWARNING);
      Exit;
  end;

    tiColCount := 1;

    SetGridHeaders(ENDetailedTransportItemHeaders, sgDetailedTransportItem.ColumnHeaders);


    transportCode := Integer(sgGroupedTransportItem.Objects[0, sgGroupedTransportItem.Row]);

    transportItemFilter := ENTransportItemFilter.Create;
       SetNullIntProps(transportItemFilter);
       SetNullXSProps(transportItemFilter);

    TempENTransportItem := HTTPRIOENTransportItem as ENTransportItemControllerSoapPort;

    if  sgGroupedTransportItem.Cells[0, sgGroupedTransportItem.Row]  = '' then exit;

    transportItemFilter.conditionSQL := ' and tr.TRANSPORTCODE in ' +
                                        ' (select tktransport.code ' +
                                        ' from tktransport where tktransport.transportclassifictncd =  ' +
                                        IntToStr(transportCode) + ')' +
                                        ' and p.code = ' + IntToStr(ENPlanWorkObj.code);

    if  StrToInt(sgGroupedTransportItem.Cells[0, sgGroupedTransportItem.Row])  > 0 then
       begin
       transportItemFilter.conditionSQL :=  transportItemFilter.conditionSQL +
                                            ' and tr.code in ' +
                                            '(select entrnsprtrdr2trnsprttm.transportitemcode from entrnsprtrdr2trnsprttm ' +
                                            ' where entrnsprtrdr2trnsprttm.transportordercode =  ' +
                                        sgGroupedTransportItem.Cells[0, sgGroupedTransportItem.Row] + ')';
     ENTransportItemList := TempENTransportItem.getListDetailedForTransportOrder(transportItemFilter);
       end

    else
    begin
    transportItemFilter.conditionSQL :=  transportItemFilter.conditionSQL +
                                              ' and tr.code not in ' +
                                              ' (select entrnsprtrdr2trnsprttm.transportitemcode from entrnsprtrdr2trnsprttm ' +
                                              ' where entrnsprtrdr2trnsprttm.transportordercode in ' +
                                              ' (select entransportorder.code from entransportorder ' +
                                              ' where entransportorder.transportcode in ' + ' (select tktransport.code ' +
                                        ' from tktransport where tktransport.transportclassifictncd =  ' +
                                        IntToStr(transportCode) + ')' +
                                              ' and entransportorder.planrefcode = ' + IntToStr(ENPlanWorkObj.code) + '))';
     ENTransportItemList := TempENTransportItem.getListDetailedForTravelSheetItem(transportItemFilter);

    end;


     tiLastCount := High(ENTransportItemList.list);

    if tiLastCount > -1 then
      sgDetailedTransportItem.RowCount := tiLastCount + 2
    else
      sgDetailedTransportItem.RowCount := 2;

     with sgDetailedTransportItem do
       for i := 0 to tiLastCount do
       begin
       //  Application.ProcessMessages;

         if ENTransportItemList.list[i].code <> Low(Integer) then
           Cells[0,i+1] := IntToStr(ENTransportItemList.list[i].code)
         else
           Cells[0,i+1] := '';

           Cells[1,i+1] := ENTransportItemList.list[i].transportName;
           AddCheckBox(1, i+1, false, false);

           Cells[2,i+1] := ENTransportItemList.list[i].kartaNum + ' ' + ENTransportItemList.list[i].kartaRefName;

        if ENTransportItemList.list[i].distance <> nil then
           Cells[3,i+1] :=  ENTransportItemList.list[i].distance.DecimalString
        else
           Cells[3,i+1] := '';

        if ENTransportItemList.list[i].countWorkFact = nil then
          Cells[4,i+1] := ''
        else
          Cells[4,i+1] := ENTransportItemList.list[i].countWorkFact.DecimalString;


         {if ENEstimateItemList.list[i].countFact = nil then
           Cells[2,i+1] := ''
         else
           Cells[2,i+1] := ENEstimateItemList.list[i].countFact.DecimalString; }


      {   if ENEstimateItemList.list[i].dateEdit = nil then
           Cells[7,i+1] := ''
         else
           Cells[7,i+1] := XSDate2String(ENEstimateItemList.list[i].dateEdit);    }

         tiLastRow := i + 1;
         sgDetailedTransportItem.RowCount := tiLastRow + 1;

         Objects[0,i+1] := TObject(ENTransportItemList.list[i].transportCode);
       end;

     tiColCount := tiColCount + 1;
     sgDetailedTransportItem.Row := 1;
end;

procedure TfrmENPlanWorkEdit.chbDetailedClick(Sender: TObject);
begin
  inherited;
 HideControls([sgDetailedTransportItem], not chbDetailed.Checked);
 if chbDetailed.Checked then sgGroupedTransportItemClick(Sender);
end;                                       

procedure TfrmENPlanWorkEdit.btnSetTimeClick(Sender: TObject);
 var
    i, eCode, n, transportCode, isAssignment : integer;
    transportName : String;
    state_, isSel : boolean;
    TempENTransportItem: ENTransportItemControllerSoapPort;
    ENTransportItemObj : ENTransportItem;
    transportItemFilter : ENTransportItemFilter;
    tiList : ENTransportItemShortList;
    tiShort : ENTransportItemShort;
    tiArr : ArrayOfENTransportItemShort;
    dateStart, dateFinal : TXSDate;
    timeStart, timeFinal : TXSDateTime;
    TempENTransportOrder :  ENTransportOrderControllerSoapPort;
    wopwList : ENWorkOrder2ENPlanWorkShortList;
    wopwFilter : ENWorkOrder2ENPlanWorkFilter;
    TempWOPW : ENWorkOrder2ENPlanWorkControllerSoapPort;
    TempFINMolData : FINMolDataControllerSoapPort;
    fmdList : FINMolDataShortList;
    fmdFilter : FINMolDataFilter;


begin
  inherited;

  {10.02.2012 - проверка на наличие наряда}
  wopwFilter := ENWorkOrder2ENPlanWorkFilter.Create;
  SetNullIntProps(wopwFilter);
  SetNullXSProps(wopwFilter);

  wopwFilter.plan := ENPlanWork.Create;
  wopwFilter.plan.code := ENPlanWorkObj.code;

  TempWOPW := HTTPRIOENWorkOrder2ENPlanWork as ENWorkOrder2ENPlanWorkControllerSoapPort;

  wopwList := TempWOPW.getScrollableFilteredList(wopwFilter, 0, -1);

 if (wopwList.totalCount = 0) then
  begin
     Application.MessageBox(PChar('Введіть наряд!!!'), PChar('Увага !'),MB_ICONWARNING);
     Exit;
  end;

  {10.02.2012 - проверка мастера в наряде}
  fmdFilter := FINMolDataFilter.Create;
  SetNullIntProps(fmdFilter);
  SetNullXSProps(fmdFilter);

  fmdFilter.workOrder := ENWorkOrder.Create;
  fmdFilter.workOrder.code := wopwList.list[0].workOrderCode;

  fmdFilter.molTypeRef := FINMolTypeRef.Create;
  fmdFilter.molTypeRef.code := ENConsts.FINMOLTYPE_MASTER;

  TempFINMolData := HTTPRIOFINMolData as FINMolDataControllerSoapPort;

  fmdList := TempFINMolData.getScrollableFilteredList(fmdFilter, 0, -1);

   if (fmdList.totalCount = 0) then
  begin
     Application.MessageBox(PChar('Введіть майстра в наряді!!!'), PChar('Увага !'),MB_ICONWARNING);
     Exit;
  end;

  
  {15/03/2012 - проверка виконавця на плане}
  if ENPlanWorkObj.finExecutor.code = Low(Integer) then
  begin
      Application.MessageBox(PChar('Введіть виконавця на плані!!!'), PChar('Увага !'),MB_ICONWARNING);
      Exit;
  end;


  if ((Sender = sgGroupedTransportItem) and (DialogState = dsView)) then Exit;

  if sgGroupedTransportItem.Cells[0, sgGroupedTransportItem.Row] = '' then Exit;

  if StrToInt(sgGroupedTransportItem.Cells[0, sgGroupedTransportItem.Row]) > 0 then
    begin
     btnUpdateTimeClick(Sender);
     exit;
    end;

  if chbDetailed.Checked then
  begin

  state_ := false;
  isSel := false;

  n:= 0;

  for i:=1 to sgDetailedTransportItem.RowCount - 1 do
  begin
     sgDetailedTransportItem.GetCheckBoxState(1,i,state_);
     if state_ then
     begin
       isSel := true;
       n:= n+1;
     end;

  end;

    if not isSel then
  begin
     Application.MessageBox(PChar('Виберіть хоча б один транспорт!!!'), PChar('Увага !'),MB_ICONWARNING);
     Exit;
  end;

  transportCode := Integer(sgDetailedTransportItem.Objects[0, sgDetailedTransportItem.Row]);

  tiList := ENTransportItemShortList.Create;
  tiList.totalCount := 0;
  SetLength(tiArr, n);
  state_ := false;
  n:= 0;
  for i := 1 to sgDetailedTransportItem.RowCount - 1 do
  begin
    sgDetailedTransportItem.GetCheckBoxState(1, i, state_);
    if state_ then
    begin
       tiShort := ENTransportItemShort.Create;
       SetNullIntProps(tiShort);
       SetNullXSProps(tiShort);
       tiShort.code :=  StrToInt(sgDetailedTransportItem.Cells[0, i]);
       tiShort.planRefCode := ENPlanWorkObj.code;
       tiShort.transportCode := transportCode;
       tiShort.transportName := sgDetailedTransportItem.Cells[1, i];
       tiArr[n] := tiShort;
       n := n + 1;
    end;
  end;

  end;

  if chbDetailed.Checked = False then
    begin
    transportItemFilter := ENTransportItemFilter.Create;
    SetNullIntProps(transportItemFilter);
    SetNullXSProps(transportItemFilter);

    transportCode := Integer(sgGroupedTransportItem.Objects[0, sgGroupedTransportItem.Row]);

    TempENTransportItem := HTTPRIOENTransportItem as ENTransportItemControllerSoapPort;


    transportItemFilter.conditionSQL := ' and tr.TRANSPORTCODE in (select tktransport.code ' +
                                                                  ' from tktransport where tktransport.transportclassifictncd =  ' +
                                                                  IntToStr(transportCode) + ')' +
                                        ' and p.code = ' + IntToStr(ENPlanWorkObj.code) +
                                        ' and tr.code not in ' +
                                        ' (select entrnsprtrdr2trnsprttm.transportitemcode from entrnsprtrdr2trnsprttm ' +
                                        ' where entrnsprtrdr2trnsprttm.transportordercode in ' +
                                        ' (select entransportorder.code from entransportorder ' +
                                        ' where entransportorder.transportcode in ' + ' (select tktransport.code ' +
                                        ' from tktransport where tktransport.transportclassifictncd =  ' +
                                        IntToStr(transportCode) + ')' +
                                        ' and entransportorder.planrefcode = ' + IntToStr(ENPlanWorkObj.code) + '))';

    tiList := TempENTransportItem.getListDetailedForTravelSheetItem(transportItemFilter);
    tiArr := tiList.list;
   end;

  frmSetTimeToTransportItem := TfrmSetTimeToTransportItem.Create(Application, dsInsert);

     {Дата наряда}
   frmSetTimeToTransportItem.edtDateStart.Date := StrToDate(XSDate2String(fmdList.list[0].workOrderDateGen));
   frmSetTimeToTransportItem.edtDateFinal.Date := StrToDate(XSDate2String(fmdList.list[0].workOrderDateGen));

  frmSetTimeToTransportItem.Caption := 'Встановлення часу роботи транспорта';
  try
    if frmSetTimeToTransportItem.ShowModal = mrOK then
       begin
            dateStart := TXSDate.Create;
            dateStart.XSToNative(GetXSDate(frmSetTimeToTransportItem.edtDateStart.Date));
            dateFinal := TXSDate.Create;
            dateFinal.XSToNative(GetXSDate(frmSetTimeToTransportItem.edtDateFinal.Date));
            timeStart := TXSDateTime.Create;
            timeStart.XSToNative(GetXSDateTime(frmSetTimeToTransportItem.edtTimeStart.DateTime));
            timeFinal := TXSDateTime.Create;
            timeFinal.XSToNative(GetXSDateTime(frmSetTimeToTransportItem.edtTimeFinal.DateTime));


            {24/03/2012 - Определение признака командировки}
            isAssignment := ENConsts.ENTRANSPORTORDER_ISASSIGNMENT_FALSE;
             if frmSetTimeToTransportItem.chbIsAssignment.Checked = true then
              isAssignment := ENConsts.ENTRANSPORTORDER_ISASSIGNMENT_TRUE;

            TempENTransportOrder := HTTPRIOENTransportOrder as  ENTransportOrderControllerSoapPort;
            TempENTransportOrder.addTransportOrderWithTransportItems(tiArr,
                                                                    timeStart,
                                                                    dateStart,
                                                                    timeFinal,
                                                                    dateFinal,
                                                                    frmSetTimeToTransportItem.transportDepartmentCode,
                                                                    isAssignment);
         end;
       UpdateGrid(Sender);
  finally
     frmSetTimeToTransportItem.Free;
  end;
end;

procedure TfrmENPlanWorkEdit.btnDeleteTimeClick(Sender: TObject);
var
    i, eCode, n, transportCode, transportOrderCode : integer;
    transportName : String;
    state_, isSel : boolean;
    TempENTransportItem: ENTransportItemControllerSoapPort;
    ENTransportItemObj : ENTransportItem;
    transportItemFilter : ENTransportItemFilter;
    tiList : ENTransportItemShortList;
    tiShort : ENTransportItemShort;
    tiArr : ArrayOfENTransportItemShort;
    dateStart, dateFinal : TXSDate;
    timeStart, timeFinal : TXSDateTime;
    TempENTransportOrder :  ENTransportOrderControllerSoapPort;

begin
  inherited;

  if ((Sender = sgGroupedTransportItem) and (DialogState = dsView)) then Exit;

  if sgGroupedTransportItem.Cells[0, sgGroupedTransportItem.Row] = '' then Exit;

  transportOrderCode := StrToInt(sgGroupedTransportItem.Cells[0, sgGroupedTransportItem.Row]);

  if transportOrderCode < 0 then
    begin
     Application.MessageBox(PChar('Ще немає введеного часу для цього транспорту'), PChar('Внимание'), MB_OK + MB_ICONWARNING);
     exit;
    end;

  if chbDetailed.Checked then
  chbDetailed.Checked := false;


    TempENTransportOrder := HTTPRIOENTransportOrder as  ENTransportOrderControllerSoapPort;

   if Application.MessageBox(PChar('Ви дійсно бажаєте видалити час робіт для цього транспорту?'),
                           PChar('Внимание'), MB_OKCANCEL + MB_ICONQUESTION) <> IDOK then Exit;

    TempENTransportOrder.removeTransportOrderWithTransportItems(transportOrderCode);
       UpdateGrid(Sender);

end;

procedure TfrmENPlanWorkEdit.btnGraphClick(Sender: TObject);
var
  TempENPlanWorkItem2Graph: ENPlanWorkItem2GraphControllerSoapPort;
begin
  TempENPlanWorkItem2Graph := HTTPRIOENPlanWorkItem2Graph as ENPlanWorkItem2GraphControllerSoapPort;

  ENPlanWorkItem2GraphObj:=ENPlanWorkItem2Graph.Create;
  SetNullIntProps(ENPlanWorkItem2GraphObj);
  SetNullXSProps(ENPlanWorkItem2GraphObj);

  ENPlanWorkItem2GraphObj.planWorkRef := ENPlanWorkRef.Create;
  ENPlanWorkItem2GraphObj.planWorkRef.code := ENPlanWorkObj.code;


  frmENPlanWorkItem2GraphEdit:=TfrmENPlanWorkItem2GraphEdit.Create(Application, dsEdit);

  frmENPlanWorkItem2GraphEdit.edtYearGen.ItemIndex := edtYearGen.ItemIndex;
  frmENPlanWorkItem2GraphEdit.edtMonthGen.ItemIndex := edtMonthGen.ItemIndex;

  frmENPlanWorkItem2GraphEdit.edtYearGen.Enabled := True;
  frmENPlanWorkItem2GraphEdit.edtMonthGen.Enabled := True;
  frmENPlanWorkItem2GraphEdit.departmentCode := ENPlanWorkObj.departmentRef.code;


  try
    if frmENPlanWorkItem2GraphEdit.ShowModal= mrOk then
      begin
        // UpdateGrid(Sender);
      end;
  finally
    frmENPlanWorkItem2GraphEdit.Free;
    frmENPlanWorkItem2GraphEdit:=nil;
  end;



end;

procedure TfrmENPlanWorkEdit.btnInvestMeasurementClick(Sender: TObject);
var
   frmTKMeasurementShow: TfrmTKMeasurementShow;
begin
   frmTKMeasurementShow:=TfrmTKMeasurementShow.Create(Application,fmNormal);
   frmTKMeasurementShow.isMDAXUnits := False;
   try
      with frmTKMeasurementShow do
        if ShowModal = mrOk then
        begin
            try
                 investMeasCode := StrToInt(GetReturnValue(sgTKMeasurement,0));
                 edtInvestMeasurementName.Text := GetReturnValue(sgTKMeasurement,1);
            except
               on EConvertError do Exit;
            end;
        end;
   finally
      frmTKMeasurementShow.Free;
   end;
end;

procedure TfrmENPlanWorkEdit.btnOSDataSaveClick(Sender: TObject);
var
 TempENPlanWorkENAct2OSData : ENPlanWorkENAct2OSDataControllerSoapPort;
begin
  try;
     TempENPlanWorkENAct2OSData := HTTPRIOENPlanWorkENAct2OSData as ENPlanWorkENAct2OSDataControllerSoapPort;

    if ENPlanWorkENAct2OSDataObj = nil then
    begin
      ENPlanWorkENAct2OSDataObj := ENPlanWorkENAct2OSData.Create;
      SetNullIntProps(ENPlanWorkENAct2OSDataObj);
      SetNullXSProps(ENPlanWorkENAct2OSDataObj);


    end;

      ENPlanWorkENAct2OSDataObj.planWorkRef := ENPlanWorkRef.Create;
      ENPlanWorkENAct2OSDataObj.planWorkRef.code := ENPlanWorkObj.code;


    if rbTypeWriteOffFull.Checked = True  then
       ENPlanWorkENAct2OSDataObj.typeWriteOff := 1
    else if rbTypeWriteOffPartly.Checked = True then
       ENPlanWorkENAct2OSDataObj.typeWriteOff := 0;

    if (ENPlanWorkENAct2OSDataObj.sumBuhWriteOZ = nil ) then
       ENPlanWorkENAct2OSDataObj.sumBuhWriteOZ := TXSDecimal.Create;
    if edtSumBuhWriteOZ.Text <> '' then
       ENPlanWorkENAct2OSDataObj.sumBuhWriteOZ.DecimalString := edtSumBuhWriteOZ.Text
    else
       ENPlanWorkENAct2OSDataObj.sumBuhWriteOZ := nil;



    // Добавляем (или сохраняем, если уже есть) связку
    if ENPlanWorkENAct2OSDataObj.code = LOW_INT then
    begin
      ENPlanWorkENAct2OSDataObj := TempENPlanWorkENAct2OSData.getObject(TempENPlanWorkENAct2OSData.add(ENPlanWorkENAct2OSDataObj));
    end
    else begin
      TempENPlanWorkENAct2OSData.save(ENPlanWorkENAct2OSDataObj);
      ENPlanWorkENAct2OSDataObj := TempENPlanWorkENAct2OSData.getObject(ENPlanWorkENAct2OSDataObj.code);
    end;

    Application.MessageBox(PChar('Збережено!'), PChar('Інформація'), MB_ICONINFORMATION);
  except

   on E: Exception do
   begin
     //ENPlanWorkENAct2OSDataObj := nil;
     ShowMessage (E.Message);
   end;

  end;
end;

procedure TfrmENPlanWorkEdit.btnUpdateTimeClick(Sender: TObject);
var
    i, eCode, n, transportCode, transportOrderCode : integer;
    dateStart, dateFinal : TXSDateTime;
    timeStart, timeFinal : TXSDateTime;
    TempENTransportOrder :  ENTransportOrderControllerSoapPort;
    ENTransportOrderObj : ENTransportOrder;

begin
  inherited;

  if ((Sender = sgGroupedTransportItem) and (DialogState = dsView)) then Exit;

  if sgGroupedTransportItem.Cells[0, sgGroupedTransportItem.Row] = '' then Exit;

  transportOrderCode := StrToInt(sgGroupedTransportItem.Cells[0, sgGroupedTransportItem.Row]);

  if transportOrderCode < 0 then
    begin
     Application.MessageBox(PChar('Ще немає введеного часу для цього транспорту'), PChar('Внимание'), MB_OK + MB_ICONWARNING);
     exit;
    end;

     TempENTransportOrder := HTTPRIOENTransportOrder as  ENTransportOrderControllerSoapPort;

     ENTransportOrderObj := ENTransportOrder.Create;
     ENTransportOrderObj := TempENTransportOrder.getObject(transportOrderCode);


  if chbDetailed.Checked then
  chbDetailed.Checked := false;

   frmSetTimeToTransportItem := TfrmSetTimeToTransportItem.Create(Application, dsEdit);
   frmSetTimeToTransportItem.Caption := 'Редагування часу роботи транспорта';
   frmSetTimeToTransportItem.ENTransportOrderObj := TempENTransportOrder.getObject(transportOrderCode);

  try
    if frmSetTimeToTransportItem.ShowModal = mrOK then
       begin
            dateStart := TXSDateTime.Create;
            dateStart.XSToNative(GetXSDateTime(frmSetTimeToTransportItem.edtDateStart.Date));
            dateFinal := TXSDateTime.Create;
            dateFinal.XSToNative(GetXSDateTime(frmSetTimeToTransportItem.edtDateFinal.Date));
            timeStart := TXSDateTime.Create;
            timeStart.XSToNative(GetXSDateTime(frmSetTimeToTransportItem.edtTimeStart.DateTime));
            timeFinal := TXSDateTime.Create;
            timeFinal.XSToNative(GetXSDateTime(frmSetTimeToTransportItem.edtTimeFinal.DateTime));

            {24/03/2012 - Определение признака командировки}
            ENTransportOrderObj.isAssignment := ENConsts.ENTRANSPORTORDER_ISASSIGNMENT_FALSE;
             if frmSetTimeToTransportItem.chbIsAssignment.Checked = true then
              ENTransportOrderObj.isAssignment := ENConsts.ENTRANSPORTORDER_ISASSIGNMENT_TRUE;

            ENTransportOrderObj.timeStart := timeStart;
            ENTransportOrderObj.timeFinal := timeFinal;
            ENTransportOrderObj.dateStart := dateStart;
            ENTransportOrderObj.dateFinal := dateFinal;
            ENTransportOrderObj.transportDepartment.code :=  frmSetTimeToTransportItem.transportDepartmentCode;
            ENTransportOrderObj.parentRef.code := frmSetTimeToTransportItem.ENTransportOrderObj.parentRef.code;

            TempENTransportOrder.save(ENTransportOrderObj);

         end;
       UpdateGrid(Sender);
  finally
     frmSetTimeToTransportItem.Free;
  end;
end;

procedure TfrmENPlanWorkEdit.FormCreate(Sender: TObject);
var  IPCallCenter,PortCallCenter_:string;
IniPath:string;
begin
  inherited;
  isTechConditions := False;
  isForCopy := false;
  isTechAgreement := False;
  isServicesProject := False;
  investMeasCode := LOW_INT;

  // Для удобства при добавлении поля год и месяц сетяться по текущей дате
  // При открытии уже существующего плана значения полей перетруться при FormShow
  // на те, что в объекте
  SetComboBoxCurrentYearWithStart(edtYearGen, 2009, 2);
  SetComboBoxCurrentMonth(edtMonthGen);

  HTTPRIOCCOutageNotice.HTTPWebNode.UserName:='callcenter';
  HTTPRIOCCOutageNotice.HTTPWebNode.Password:='callcenter_1';

end;

procedure TfrmENPlanWorkEdit.actInsertWorkFromCalculExecute(
  Sender: TObject);
begin
   frmaddENPlanworkItemByCalculationType:=TfrmaddENPlanworkItemByCalculationType.Create(Application, dsInsert);
  try

    frmaddENPlanworkItemByCalculationType.planCode:= ENPlanWorkObj.code;


   if frmaddENPlanworkItemByCalculationType.ShowModal = mrOk then
     begin
       actUpdateExecute(sender);
     end;

  finally
    frmaddENPlanworkItemByCalculationType.Free;

  end;


end;

procedure TfrmENPlanWorkEdit.actInsertXqtnPercentExecute(Sender: TObject);
begin
  // TempENPlanXqtnHistory := HTTPRIOENPlanXqtnHistory as ENPlanXqtnHistoryControllerSoapPort;  /// Это здесь уже лишнее!!!
  ENPlanXqtnHistoryObj:=ENPlanXqtnHistory.Create;
  SetNullIntProps(ENPlanXqtnHistoryObj);
  SetNullXSProps(ENPlanXqtnHistoryObj);

  ENPlanXqtnHistoryObj.planRef := ENPlanWorkRef.Create;
  ENPlanXqtnHistoryObj.planRef.code := ENPlanWorkObj.code;

   //ENPlanXqtnHistoryObj.dateGen:= TXSDate.Create;
   //ENPlanXqtnHistoryObj.dateEdit:= TXSDate.Create;


  try
    frmENPlanXqtnHistoryEdit:=TfrmENPlanXqtnHistoryEdit.Create(Application, dsInsert);
    try
      if frmENPlanXqtnHistoryEdit.ShowModal = mrOk then
      begin
        if ENPlanXqtnHistoryObj<>nil then
            //TempENPlanXqtnHistory.add(ENPlanXqtnHistoryObj);
            actUpdateXqtnPercentExecute(Sender);
      end;
    finally
      frmENPlanXqtnHistoryEdit.Free;
      frmENPlanXqtnHistoryEdit:=nil;
    end;
  finally
    ENPlanXqtnHistoryObj.Free;
  end;
end;

procedure TfrmENPlanWorkEdit.btnSaveCommentClick(Sender: TObject);
var TempENPlanWork: ENPlanWorkControllerSoapPort;
begin
  if ENPlanWorkObj.code = LOW_INT then Exit;


if not isEditComment
 then
  begin
   DisableControls([edtCommentGen],false);
   isEditComment:=true;
   btnSaveComment.Caption:='Зберегти';
  end
  else
  begin
  TempENPlanWork := HTTPRIOENPlanWork as ENPlanWorkControllerSoapPort;
  TempENPlanWork.saveComment(ENPlanWorkObj.code,edtCommentGen.text);
  DisableControls([edtCommentGen]);
  isEditComment:=false;
  btnSaveComment.Caption:='Додати примітку';
  end;
end;


procedure TfrmENPlanWorkEdit.actZeroEstimateItemsExecute(Sender: TObject);
var
  TempENEstimateItem: ENEstimateItemControllerSoapPort;
  grid: TAdvStringGrid;

  estimateItemObj: ENEstimateItem;
  i: Integer;
  state, isSel: Boolean;
begin
  if sgENEstimateItem.Visible then
    grid := sgENEstimateItem
  else if sgENEstimateItemWithFin.Visible then
    grid := sgENEstimateItemWithFin
  else
    raise Exception.Create('NET-4061 Unknown ENEstimateItem grid!');

  state := false;
  isSel := false;

  for i := 1 to grid.RowCount - 1 do
  begin
    grid.GetCheckBoxState(1, i, state);
    if state then
    begin
      isSel := true;
      break;
    end;
  end;

  if not isSel then
  begin
    Application.MessageBox(PChar('Виберіть хоча б один матеріал !'), PChar('Увага !'), MB_ICONWARNING);
    Exit;
  end;

  if Application.MessageBox(PChar('Ви дійсно бажаєте обнулити всі обрані матеріали ?'),
                            PChar('Увага !'),
                            MB_ICONQUESTION + MB_OKCANCEL + MB_DEFBUTTON2) <> IDOK then
    Exit;

  TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;

  state := false;

  for i := 1 to grid.RowCount - 1 do
  begin
    try
      grid.GetCheckBoxState(1, i, state);
      if state then
      begin
        try
          estimateItemObj := TempENEstimateItem.getObject(StrToInt(grid.Cells[0, i]));
        except
          on EConvertError do Exit;
        end;

        if (estimateItemObj.countFact = nil) then
          estimateItemObj.countFact := TXSDecimal.Create;
        estimateItemObj.countFact.DecimalString := '0';

        TempENEstimateItem.save(estimateItemObj);
      end;
    except
      on e: Exception do
      begin
        Application.MessageBox(PChar(e.Message), PChar('Помилка !'), MB_ICONERROR);
        continue;
      end;
    end;
  end;

  UpdateGrid(Sender);
  //pcPlanWorkChange(Sender);
end;

procedure TfrmENPlanWorkEdit.actZeroPlanWorkItemsExecute(Sender: TObject);
var
  TempENPlanWorkItem: ENPlanWorkItemControllerSoapPort;
  planWorkItemObj: ENPlanWorkItem;
  i: Integer;
  state, isSel: Boolean;
begin
  state := false;
  isSel := false;

  for i := 1 to sgENPlanWorkItem.RowCount - 1 do
  begin
    sgENPlanWorkItem.GetCheckBoxState(1, i, state);
    if state then
    begin
      isSel := true;
      break;
    end;
  end;

  if not isSel then
  begin
    Application.MessageBox(PChar('Виберіть хоча б одну роботу !'), PChar('Увага !'), MB_ICONWARNING);
    Exit;
  end;

  if Application.MessageBox(PChar('Ви дійсно бажаєте обнулити всі обрані роботи ?'),
                            PChar('Увага !'),
                            MB_ICONQUESTION + MB_OKCANCEL + MB_DEFBUTTON2) <> IDOK then
    Exit;

  TempENPlanWorkItem := HTTPRIOENPlanWorkItem as ENPlanWorkItemControllerSoapPort;

  for i := 1 to sgENPlanWorkItem.RowCount - 1 do
  begin
    sgENPlanWorkItem.GetCheckBoxState(1, i, state);
    if state then
    begin
      try
        planWorkItemObj := TempENPlanWorkItem.getObject(StrToInt(sgENPlanWorkItem.Cells[0, i]));
      except
        on EConvertError do Exit;
      end;

      if (planWorkItemObj.countGen = nil) then
        planWorkItemObj.countGen := TXSDecimal.Create;
      planWorkItemObj.countGen.DecimalString := '0';

      TempENPlanWorkItem.save(planWorkItemObj);
    end;
  end;

  UpdateGrid(Sender);
  //pcPlanWorkChange(Sender);
end;


procedure TfrmENPlanWorkEdit.AdjustYearCombobox;
var
  maxYear, i: Integer;
begin
  if DialogState = dsInsert then
    Exit;

  if ENPlanWorkObj = nil then
    Exit;

  try
    maxYear := StrToInt(edtYearGen.Items[edtYearGen.Items.Count - 1]);
  except
    on EConvertError do Exit;
  end;

  if ENPlanWorkObj.yearGen > maxYear then
  begin
    for i := maxYear + 1 to ENPlanWorkObj.yearGen do
      edtYearGen.Items.Add(IntToStr(i));

    edtYearGen.ItemIndex := edtYearGen.Items.Count - 1;
  end;
end;

procedure TfrmENPlanWorkEdit.actAddRQFKOrder2TransportRouteExecute(
  Sender: TObject);
var
  f : RQFKOrderFilter;
  TempTransportRoute2FKorder : ENTransportRoute2RQFKOrderControllerSoapPort;
  transportRoute2RQFKOrder : ENTransportRoute2RQFKOrder;
  i : Integer;
  checked :boolean;
  frmRQFKOrderOutShow: TfrmRQFKOrderShow;
begin

  f := RQFKOrderFilter.Create;
  SetNullIntProps(f);
  SetNullXSProps(f);

  f.kind := RQFKOrderKind.Create;
  f.kind.code := RQFKORDER_KIND_RASHOD_OE2REM;

  //f.status := RQFKOrderStatus.Create;
  //f.status.code := RQFKORDER_STATUS_CREATED;

  ///////   УУУУУУУУУУУУУ
  ///////   показываем складені, проведені
  f.conditionSQL := ' rqfkorder.statuscode in ('
    + IntToStr(RQFKORDER_STATUS_CREATED) + ',' + IntToStr(RQFKORDER_STATUS_IN_WORK_ON_WAREHOUSE) + ','
    + IntToStr(RQFKORDER_STATUS_IN_FK) + ')';
  frmRQFKOrderOutShow := TfrmRQFKOrderShow.Create(Application, fmNormal, f);
  try

    frmRQFKOrderOutShow.Caption := 'Видаткові ордера';
    frmRQFKOrderOutShow.sgRQFKOrder.ColWidths[7] := 0;
    frmRQFKOrderOutShow.sgRQFKOrder.ColWidths[8] := 0;
    frmRQFKOrderOutShow.sgRQFKOrder.ColWidths[9] := 0;
    frmRQFKOrderOutShow.sgRQFKOrder.ColWidths[10] := 0;
    frmRQFKOrderOutShow.sgRQFKOrder.ColWidths[11] := 0;
    frmRQFKOrderOutShow.sgRQFKOrder.ColWidths[12] := 0;
    frmRQFKOrderOutShow.sgRQFKOrder.ColWidths[13] := 150;

    frmRQFKOrderOutShow.isTransportRoutes := True;

    transportRoute2RQFKOrder := ENTransportRoute2RQFKOrder.Create;
    transportRoute2RQFKOrder.transportRouteRef := ENTransportRouteRef.Create;

    try
      transportRoute2RQFKOrder.transportRouteRef.code := StrToInt(sgENTransportRoute.Cells[0,sgENTransportRoute.Row]);
    except
      on EConvertError do Exit;
    end;


     with frmRQFKOrderOutShow do
       if ShowModal = mrOk then
       begin
           try
             for i := 0 to sgRQFKOrder.RowCount - 1  do
             begin
               checked:= False;
               sgRQFKOrder.Row := i;
               sgRQFKOrder.GetCheckBoxState(1, i, checked);
               TempTransportRoute2FKorder := HTTPRIOENTransportRoute2RQFKOrder as ENTransportRoute2RQFKOrderControllerSoapPort;
               if checked then
               begin

                transportRoute2RQFKOrder.fkOrderRef := RQFKOrderRef.Create;
                transportRoute2RQFKOrder.fkOrderRef.code := StrToInt(GetReturnValue(sgRQFKOrder,0));

                TempTransportRoute2FKorder.add(transportRoute2RQFKOrder);

                end;
               end;
           except
              on EConvertError do Exit;
           end;
       end;
  finally
     frmRQFKOrderOutShow.Free;
     //frmRQFKOrderOutShow := nil;
  end;

  UpdateGrid(Sender);
end;

procedure TfrmENPlanWorkEdit.sgENTransportRouteClick(Sender: TObject);
var
  routeCode, i, ColCount, LastCount, LastRow : Integer;
  TempRQFKOrder : RQFKOrderControllerSoapPort;
  fkOrderList : RQFKOrderShortList;
  fkOrderFilter : RQFKOrderFilter;

begin

   ClearGrid(sgRQFKOrder2Route);
   ColCount := 100;
   TempRQFKOrder := HTTPRIORQFKOrder as RQFKOrderControllerSoapPort;

   if sgENTransportRoute.Cells[0, sgENTransportRoute.Row]  = '' then exit;
   routeCode := StrToInt(sgENTransportRoute.Cells[0,sgENTransportRoute.Row]);

   fkOrderFilter := RQFKOrderFilter.Create;
   SetNullIntProps(fkOrderFilter);
   SetNullXSProps(fkOrderFilter);
   fkOrderFilter.conditionSQL := ' to2fo.transportrouterefcode = ' + IntToStr(routeCode);

   fkOrderList := TempRQFKOrder.getListByTransportRoute(fkOrderFilter, fkOrderFilter.conditionSQL, '', 0,ColCount);

   LastCount:=High(fkOrderList.list);

   if LastCount > -1 then
      sgRQFKOrder2Route.RowCount:=LastCount+2
   else
      sgRQFKOrder2Route.RowCount:=2;

   with sgRQFKOrder2Route do
    for i:=0 to LastCount do
      begin
        Application.ProcessMessages;

        if fkOrderList.list[i].code <> Low(Integer) then
        Cells[0,i+1] := IntToStr(fkOrderList.list[i].code)
        else
        Cells[0,i+1] := '';
        Cells[1,i+1] := fkOrderList.list[i].numberDoc;
        if fkOrderList.list[i].dateGen = nil then
          Cells[2,i+1] := ''
        else
          Cells[2,i+1] := XSDate2String(fkOrderList.list[i].dateGen);

        Cells[3,i+1] := fkOrderList.list[i].molInCode;
        Cells[4,i+1] := fkOrderList.list[i].molInName;
        Cells[5,i+1] := fkOrderList.list[i].molOutCode;
        Cells[6,i+1] := fkOrderList.list[i].molOutName;

        if fkOrderList.list[i].totalWeight = nil then
          Cells[7,i+1] := ''
        else
          Cells[7,i+1] := fkOrderList.list[i].totalWeight.DecimalString;

        Cells[8,i+1] := fkOrderList.list[i].kindName;

        Cells[9,i+1] := fkOrderList.list[i].userAdd;

        if fkOrderList.list[i].dateGen = nil then
          Cells[10,i+1] := ''
        else
          Cells[10,i+1] := XSDate2String(fkOrderList.list[i].dateGen);

        Cells[11,i+1] := fkOrderList.list[i].userGen;

        if fkOrderList.list[i].dateEdit = nil then
          Cells[12,i+1] := ''
        else
          Cells[12,i+1] := XSDateTimeWithDate2String(fkOrderList.list[i].dateEdit);

        if fkOrderList.list[i].kindCode <> Low(Integer) then
          Cells[13,i+1] := IntToStr(fkOrderList.list[i].kindCode)
        else
          Cells[13,i+1] := '';

        LastRow:=i+1;
        sgRQFKOrder2Route.RowCount:=LastRow+1;
      end;
   ColCount:=ColCount+1;
   sgRQFKOrder2Route.Row:=1;
end;

procedure TfrmENPlanWorkEdit.actViewRQFKOrder2TransportRouteExecute(
  Sender: TObject);
var
  typeCode : Integer;
  TempRQFKOrder : RQFKOrderControllerSoapPort;
  TempRQTransportInvoice : RQTransportInvoiceControllerSoapPort;
begin
  TempRQFKOrder := HTTPRIORQFKOrder as RQFKOrderControllerSoapPort;
  TempRQTransportInvoice := HTTPRIORQTransportInvoice as RQTransportInvoiceControllerSoapPort;

  // по типу определяем какой ордер (фк-шный или транспортный)
  try
    typeCode := StrToInt(sgRQFKOrder2Route.Cells[13,sgRQFKOrder2Route.Row]);
  except
    on EConvertError do Exit;
  end;

  if (typeCode = RQTRANSPORT_INVOICE_KIND) then
  begin
      try
        RQTransportInvoiceObj := TempRQTransportInvoice.getObject(StrToInt(sgRQFKOrder2Route.Cells[0,sgRQFKOrder2Route.Row]))
      except
        on EConvertError do Exit;
      end;

      frmRQTransportInvoiceEdit := TfrmRQTransportInvoiceEdit.Create(Application, dsView);

      try
        if (frmRQTransportInvoiceEdit.ShowModal = mrOk) then
          begin
            UpdateGrid(Sender);
          end;

      finally
        frmRQTransportInvoiceEdit.Free;
        frmRQTransportInvoiceEdit:=nil;
      end;
  end
  else
  begin
    frmRQFKOrderEdit:=TfrmRQFKOrderEdit.Create(Application, dsView);

    try
      try
        frmRQFKOrderEdit.RQFKOrderObj := TempRQFKOrder.getObject(StrToInt(sgRQFKOrder2Route.Cells[0,sgRQFKOrder2Route.Row]));
      except
        on EConvertError do Exit;
      end;

      if (frmRQFKOrderEdit.ShowModal = mrOk) then
        begin
          UpdateGrid(Sender);
        end;

    finally
      frmRQFKOrderEdit.Free;
      frmRQFKOrderEdit:=nil;
    end;
  end;

end;

procedure TfrmENPlanWorkEdit.actDeleteRQFKOrder2TransportRouteExecute(
  Sender: TObject);
var
  typeCode : Integer;
  r2fkFilter : ENTransportRoute2RQFKOrderFilter;
  TempTransportRoute2FKorder : ENTransportRoute2RQFKOrderControllerSoapPort;
  transportRoute2RQFKOrder : ENTransportRoute2RQFKOrder;
  list : ENTransportRoute2RQFKOrderShortList;

  r2invFilter : ENTransportRoute2RQTransportInvoiceFilter;
  TempTransportRoute2Invoice : ENTransportRoute2RQTransportInvoiceControllerSoapPort;
  transportRoute2RQInvoice : ENTransportRoute2RQTransportInvoice;
  invoiceList : ENTransportRoute2RQTransportInvoiceShortList;

begin

  // по типу определяем какой ордер (фк-шный или транспортный)
  try
    typeCode := StrToInt(sgRQFKOrder2Route.Cells[13,sgRQFKOrder2Route.Row]);
  except
    on EConvertError do Exit;
  end;

  if (typeCode = RQTRANSPORT_INVOICE_KIND) then
  begin
     try
        r2invFilter := ENTransportRoute2RQTransportInvoiceFilter.Create;
        SetNullIntProps(r2invFilter);
        SetNullXSProps(r2invFilter);

        r2invFilter.invoiceRef := RQTransportInvoiceRef.Create();
        r2invFilter.invoiceRef.code := StrToInt(sgRQFKOrder2Route.Cells[0,sgRQFKOrder2Route.Row]);

        r2invFilter.transportRouteRef := ENTransportRouteRef.Create();
        r2invFilter.transportRouteRef.code := StrToInt(sgENTransportRoute.Cells[0,sgENTransportRoute.Row]);

        TempTransportRoute2Invoice := HTTPRIOENTransportRoute2RQTransportInvoice as ENTransportRoute2RQTransportInvoiceControllerSoapPort;
        invoiceList := TempTransportRoute2Invoice.getScrollableFilteredList(r2invFilter, 0, -1);

        transportRoute2RQInvoice := ENTransportRoute2RQTransportInvoice.Create;
        SetNullIntProps(transportRoute2RQInvoice);
        SetNullXSProps(transportRoute2RQInvoice);

        if invoiceList.totalCount = 1 then
           TempTransportRoute2Invoice.remove(invoiceList.list[0].code);
     except
        on EConvertError do Exit;
     end;
  end
  else
  begin
     try
        r2fkFilter := ENTransportRoute2RQFKOrderFilter.Create;
        SetNullIntProps(r2fkFilter);
        SetNullXSProps(r2fkFilter);

        r2fkFilter.fkOrderRef := RQFKOrderRef.Create();
        r2fkFilter.fkOrderRef.code := StrToInt(sgRQFKOrder2Route.Cells[0,sgRQFKOrder2Route.Row]);

        r2fkFilter.transportRouteRef := ENTransportRouteRef.Create();
        r2fkFilter.transportRouteRef.code := StrToInt(sgENTransportRoute.Cells[0,sgENTransportRoute.Row]);

        TempTransportRoute2FKorder := HTTPRIOENTransportRoute2RQFKOrder as ENTransportRoute2RQFKOrderControllerSoapPort;
        list := TempTransportRoute2FKorder.getScrollableFilteredList(r2fkFilter, 0, -1);

        transportRoute2RQFKOrder := ENTransportRoute2RQFKOrder.Create;
        SetNullIntProps(transportRoute2RQFKOrder);
        SetNullXSProps(transportRoute2RQFKOrder);

        if list.totalCount = 1 then
           TempTransportRoute2FKorder.remove(list.list[0].code);
     except
        on EConvertError do Exit;
     end;
  end;

   UpdateGrid(Sender);
end;




procedure TfrmENPlanWorkEdit.actDeleteXqtnPercentExecute(Sender: TObject);
Var TempENPlanXqtnHistory: ENPlanXqtnHistoryControllerSoapPort;
  ObjCode: Integer;
begin
 TempENPlanXqtnHistory := HTTPRIOENPlanXqtnHistory as ENPlanXqtnHistoryControllerSoapPort;
   try
     ObjCode := StrToInt(sgENPlanXqtnHistory.Cells[0,sgENPlanXqtnHistory.Row]);
   except
   on EConvertError do Exit;
  end;
  if Application.MessageBox(PChar('Ви дійсно бажаєте видалити (Історія відсотків виконання плану робіт)?'),
                    PChar('Увага!'),MB_ICONQUESTION+MB_OKCANCEL+MB_DEFBUTTON2)=IDOK then
  begin
      TempENPlanXqtnHistory.remove(ObjCode);
      actUpdateXqtnPercentExecute(Sender);
  end;
end;

procedure TfrmENPlanWorkEdit.actAddPrihodOrder2transportRouteExecute(
  Sender: TObject);
var
  f : RQFKOrderFilter;
  TempTransportRoute2FKorder : ENTransportRoute2RQFKOrderControllerSoapPort;
  transportRoute2RQFKOrder : ENTransportRoute2RQFKOrder;
  i : Integer;
  checked :boolean;
  frmRQFKOrderOutShow: TfrmRQFKOrderShow;
begin

  f := RQFKOrderFilter.Create;
  SetNullIntProps(f);
  SetNullXSProps(f);

  f.kind := RQFKOrderKind.Create;
  f.kind.code := RQFKORDER_KIND_PRIHOD_POSTAVKA;

  //f.status := RQFKOrderStatus.Create;
  //f.status.code := RQFKORDER_STATUS_CREATED;

  ///////   УУУУУУУУУУУУУ
  ///////   показываем складені, проведені
  f.conditionSQL := ' rqfkorder.statuscode in ('
    + IntToStr(RQFKORDER_STATUS_CREATED) + ',' + IntToStr(RQFKORDER_STATUS_IN_FK) + ')';

  frmRQFKOrderOutShow := TfrmRQFKOrderShow.Create(Application, fmNormal, f);
  try
    frmRQFKOrderOutShow.Caption := 'Прибуткові ордера';
    frmRQFKOrderOutShow.sgRQFKOrder.ColWidths[7] := 0;
    frmRQFKOrderOutShow.sgRQFKOrder.ColWidths[8] := 0;
    frmRQFKOrderOutShow.sgRQFKOrder.ColWidths[9] := 0;
    frmRQFKOrderOutShow.sgRQFKOrder.ColWidths[10] := 0;
    frmRQFKOrderOutShow.sgRQFKOrder.ColWidths[11] := 0;
    frmRQFKOrderOutShow.sgRQFKOrder.ColWidths[12] := 0;
    frmRQFKOrderOutShow.sgRQFKOrder.ColWidths[13] := 150;

    frmRQFKOrderOutShow.isTransportRoutes := True;

    transportRoute2RQFKOrder := ENTransportRoute2RQFKOrder.Create;
    transportRoute2RQFKOrder.transportRouteRef := ENTransportRouteRef.Create;

    try
      transportRoute2RQFKOrder.transportRouteRef.code := StrToInt(sgENTransportRoute.Cells[0,sgENTransportRoute.Row]);
    except
      on EConvertError do Exit;
    end;


     with frmRQFKOrderOutShow do
       if ShowModal = mrOk then
       begin
           try
             for i := 0 to sgRQFKOrder.RowCount - 1  do
             begin
               checked:= False;
               sgRQFKOrder.Row := i;
               sgRQFKOrder.GetCheckBoxState(1, i, checked);
               TempTransportRoute2FKorder := HTTPRIOENTransportRoute2RQFKOrder as ENTransportRoute2RQFKOrderControllerSoapPort;
               if checked then
               begin

                transportRoute2RQFKOrder.fkOrderRef := RQFKOrderRef.Create;
                transportRoute2RQFKOrder.fkOrderRef.code := StrToInt(GetReturnValue(sgRQFKOrder,0));

                TempTransportRoute2FKorder.add(transportRoute2RQFKOrder);

                end;
               end;
           except
              on EConvertError do Exit;
           end;
       end;
  finally
     frmRQFKOrderOutShow.Free;
     //frmRQFKOrderOutShow := nil;
  end;

  UpdateGrid(Sender);
end;


procedure TfrmENPlanWorkEdit.sgRQFKOrder2RouteDblClick(Sender: TObject);
begin
  actViewRQFKOrder2TransportRouteExecute(Sender);
end;



procedure TfrmENPlanWorkEdit.actAddTrInvoice2transportRouteExecute(Sender: TObject);
var
  f : RQTransportInvoiceFilter;
  TempENTransportRoute2Invoice : ENTransportRoute2RQTransportInvoiceControllerSoapPort;
  transportRoute2Invoice : ENTransportRoute2RQTransportInvoice;
  frmRQTransportInvoiceShow : TfrmRQTransportInvoiceShow;
begin

  f := RQTransportInvoiceFilter.Create;
  SetNullIntProps(f);
  SetNullXSProps(f);

  //  при добавлении из плана показываем только Черновые
  f.statusRef := RQTransportInvoiceStatusRef.Create;
  f.statusRef.code := RQTRANSPORTINVOICESTATUS_NEW;

  frmRQTransportInvoiceShow := TfrmRQTransportInvoiceShow.Create(Application, fmNormal, f);

  try
    transportRoute2Invoice := ENTransportRoute2RQTransportInvoice.Create;
    transportRoute2Invoice.transportRouteRef := ENTransportRouteRef.Create;

    try
      transportRoute2Invoice.transportRouteRef.code := StrToInt(sgENTransportRoute.Cells[0,sgENTransportRoute.Row]);
    except
      on EConvertError do Exit;
    end;

     with frmRQTransportInvoiceShow do
       if ShowModal = mrOk then
       begin
           try
             TempENTransportRoute2Invoice := HTTPRIOENTransportRoute2RQTransportInvoice as ENTransportRoute2RQTransportInvoiceControllerSoapPort;

             transportRoute2Invoice.invoiceRef := RQTransportInvoiceRef.Create;
             transportRoute2Invoice.invoiceRef.code := StrToInt(GetReturnValue(sgRQTransportInvoice,0));

             TempENTransportRoute2Invoice.add(transportRoute2Invoice);

           except
             on EConvertError do Exit;
           end;
       end;
  finally
     frmRQTransportInvoiceShow.Free;
  end;

  UpdateGrid(Sender);
end;


procedure TfrmENPlanWorkEdit.btnChangeInvestDescriptionClick(Sender: TObject);
var
  planCode : Integer;
  TempENPlanWork : ENPlanWorkControllerSoapPort;
  amount : TXSDecimal;
  investWorkDateStart : TXSDateTime;
begin
  if ENPlanWorkObj.code = LOW_INT then Exit;

  if not isEditComment then
  begin
    DisableControls([edtInvestWorksDescription, edtInvestWorksAmount, edtInvestWorkStartDate], False);
    DenyBlankValues([edtInvestWorksDescription, edtInvestWorksAmount, edtInvestWorkStartDate]);
    btnChangeInvestDescription.Caption := 'Зберегти';
    isEditComment := True;
  end
  else
  begin
    TempENPlanWork := HTTPRIOENPlanWork as ENPlanWorkControllerSoapPort;
    TempENPlanWork.changeInvestDescription(ENPlanWorkObj.code, edtInvestWorksDescription.text);

    if(edtInvestWorksAmount.Text <> '') then
    begin
      amount := TXSDecimal.Create;
      amount.DecimalString := edtInvestWorksAmount.Text;
      TempENPlanWork.changeInvestAmount(ENPlanWorkObj.code, amount);
    end;

    if edtInvestWorkStartDate.Checked then
    begin
       investWorkDateStart := TXSDateTime.Create;
       investWorkDateStart.XSToNative(GetXSDate(edtInvestWorkStartDate.DateTime));
       TempENPlanWork.changeInvestStartDate(ENPlanWorkObj.code, investWorkDateStart);
    end;

    if investMeasCode <> LOW_INT then
    begin
       TempENPlanWork.changeInvestMeasurement(ENPlanWorkObj.code, investMeasCode);
    end;

    DisableControls([edtInvestWorksDescription, edtInvestWorksAmount]);
    isEditComment := False;
    btnChangeInvestDescription.Caption := 'Змінити зміст та обсяг';

    //перечитать объект
    planCode := ENPlanWorkObj.code;
    ENPlanWorkObj := nil;
    ENPlanWorkObj := ENPlanWork.Create;
    ENPlanWorkObj := TempENPlanWork.getObject(planCode);
    FormShow(Sender);
  end;

end;

procedure TfrmENPlanWorkEdit.actCreateOrderByPlanExecute(Sender: TObject);
var
  orderCode, i : integer;
  state_, isSel : boolean;
  strCodes, mesg : string;
  TempRQOrder : RQOrderControllerSoapPort;
  order : RQOrder;
  
begin
  state_ := false;
  isSel := false;

  for i := 1 to sgENEstimateItem.RowCount - 1 do
  begin
     sgENEstimateItem.GetCheckBoxState(1, i, state_);
     if state_ then
     begin
       isSel := true;
       if not (Integer(sgENEstimateItem.Objects[1, i]) in [ENESTIMATEITEMSTATUS_PLANNED]) then
       begin
         Application.MessageBox(PChar('Вибрано вже замовлений матеріал!!!'), PChar('Увага!'), MB_ICONWARNING);
         Exit;
       end;
     end;
  end;

  if not isSel then
  begin
     Application.MessageBox(PChar('Виберіть хоча б один матеріал!!!'), PChar('Увага!'),MB_ICONWARNING);
     Exit;
  end;

  strCodes := '';
  if isSel then
  begin

    for i := 1 to sgENEstimateItem.RowCount - 1 do
    begin
      sgENEstimateItem.GetCheckBoxState(1, i, state_);
      if state_ then
        AddListPos(strCodes, sgENEstimateItem.Cells[0, i]);
    end;

    if Application.MessageBox(PChar('Ви дійсно бажаєте скласти заявку з обраного плану?'),
                            PChar('Увага !'), MB_ICONQUESTION + MB_OKCANCEL + MB_DEFBUTTON2) = IDOK then
    begin
      TempRQOrder := HTTPRIORQOrder as RQOrderControllerSoapPort;
      orderCode := TempRQOrder.createOrderByPlan(ENPlanWorkObj.code, strCodes);

      if (orderCode <> LOW_INT) then
      begin
        order := TempRQOrder.getObject(orderCode);
        mesg := 'Заявку №= ' + order.numberDoc + ' успішно сформовано!!!';
        Application.MessageBox(PChar(mesg), PChar('Повідомлення'), MB_ICONINFORMATION);
      end;
      
    end;

  end;

  pcPlanWorkChange(Sender);

end;

procedure TfrmENPlanWorkEdit.actCreateOrderByServicesPlanExecute(Sender: TObject);
var
  orderCode : integer;
  strCodes, mesg : string;
  TempRQOrder : RQOrderControllerSoapPort;
  order : RQOrder;
begin
    if Application.MessageBox(PChar('Ви дійсно бажаєте скласти заявку з обраного плану?'),
                            PChar('Увага !'), MB_ICONQUESTION + MB_OKCANCEL + MB_DEFBUTTON2) = IDOK then
    begin
      TempRQOrder := HTTPRIORQOrder as RQOrderControllerSoapPort;
      strCodes := '';
      orderCode := TempRQOrder.createOrderByPlan(ENPlanWorkObj.code, strCodes);

      if (orderCode <> LOW_INT) then
      begin
        order := TempRQOrder.getObject(orderCode);
        mesg := 'Заявку №= ' + order.numberDoc + ' успішно сформовано!!!';
        Application.MessageBox(PChar(mesg), PChar('Повідомлення'), MB_ICONINFORMATION);
      end;
      
    end;

    pcPlanWorkChange(Sender);
    
end;


procedure TfrmENPlanWorkEdit.actChangePlanWorkItemExecute(Sender: TObject);
var TempENPlanWorkItem : ENPlanWorkItemControllerSoapPort;
begin
  if pcPlanWork.ActivePage = tsPlanWorkItems then
  begin
    TempENPlanWorkItem := HTTPRIOENPlanWorkItem as ENPlanWorkItemControllerSoapPort;
    try
      ENPlanWorkItemObj := TempENPlanWorkItem.getObject(StrToInt(sgENPlanWorkItem.Cells[0,sgENPlanWorkItem.Row]));
    except
      on EConvertError do Exit;
    end;
   


    try
      frmENPlanWorkItemEdit := TfrmENPlanWorkItemEdit.Create(Application, dsInsert);

      // 30.05.2012 Присваивание типа акта для формы frmENPlanWorkItemEdit
      frmENPlanWorkItemEdit.planWorkStateCode := ENPlanWorkObj.stateRef.code;

      if ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_SIZ then frmENPlanWorkItemEdit.isSiz := true;
      if isTransport then frmENPlanWorkItemEdit.isTransport := true;

      if ENPlanWorkObj.typeRef.code in [ENPLANWORKTYPE_SERVICES_FROM_SIDE, ENPLANWORKTYPE_SERVICES_FROM_SIDE_INVEST]
        then frmENPlanWorkItemEdit.inServices := false;

      try
        ENPlanWorkItemObj.planRef.code := ENPlanWorkObj.code;
        frmENPlanWorkItemEdit.pcEstimate.Visible := false;
        frmENPlanWorkItemEdit.isChangePlanWorkitem := True;

        if frmENPlanWorkItemEdit.ShowModal = mrOk then
        begin
          if ENPlanWorkItemObj<>nil then
          begin
              //TempENPlanWorkItem.add(ENPlanWorkItemObj);
              UpdateGrid(Sender);
          end;
        end;
        sgENPlanWorkItem.Row :=  sgENPlanWorkItem.RowCount - 1;
      finally
        frmENPlanWorkItemEdit.Free;
        frmENPlanWorkItemEdit:=nil;
      end;
    finally
      ENPlanWorkItemObj.Free;
    end;


  end;  
end;

procedure TfrmENPlanWorkEdit.btnTempCalcClick(Sender: TObject);
var TempENTransportItem :  ENTransportItemControllerSoapPort;

  TempENPlanWork: ENPlanWorkControllerSoapPort;

  planFilter : ENPlanWorkFilter;
  plan : ENPlanWork;
  planList : ENPlanWorkShortList;
  i : integer;


  TempENAct: ENActControllerSoapPort;
  actFilter : ENActFilter;
  actList : ENActShortList;

  finMatFilter : FINMaterialsFilter;
  TempFinMaterials : FINMaterialsControllerSoapPort;
  finList : FINMaterialsShortList;
  p : integer;
  frm : TfrmSCCounterEdit;
begin
    TempENTransportItem := HTTPRIOENTransportItem as ENTransportItemControllerSoapPort;

  for i:= 0 to memoData.Lines.Count -1 do
     try
       p := StrToInt(trim(memoData.Lines[i]));
      TempENTransportItem.TEMP_GENERATE_GSM(p);
       frmMain.sbMain.Panels[2].Text := IntToStr(i+1) + ' из ' + IntToStr(memoData.Lines.Count );
       Application.ProcessMessages;

     except

      on E: ESOAPHTTPException do
      begin
        case ESOAPHTTPException(E).StatusCode of
          0:
            begin
              //Application.MessageBox(PChar('Нет связи ...'),
              //                       PChar('Ошибка!'),MB_ICONERROR+MB_OK);
              Exit;
            end;

          503:
            begin
              //Application.MessageBox(PChar('Служба недоступна'),
              //                       PChar('Ошибка!'),MB_ICONERROR+MB_OK);
              Exit;
            end;
        end;
       end;
       on E: ERemotableException do
       begin
         log.Lines.Add('#' + IntToStr(p) + '$');
         log2.lines.Add(IntToStr(p) + ' : ' + e.Message);
         Application.ProcessMessages;
       end;

       on E: Exception do
       begin
         log2.Lines.Add('error: ' + IntToStr(p) + ' : ' +  e.message);
         Exit;
       end;

    end;
end;

procedure TfrmENPlanWorkEdit.actAddDistanceForTransportExecute(
  Sender: TObject);
  var TempENTransportItem : ENTransportItemControllerSoapPort;
  transportItemCode, amountOfJourneys : Integer;
  distance : TXSDecimal;
  frmAddDistance : TfrmAddDistance;
begin

    frmAddDistance := TfrmAddDistance.Create(Application);
  try
    if frmAddDistance.ShowModal = mrOK then
       begin
             distance := TXSDecimal.Create;
             distance.DecimalString := trim(frmAddDistance.edtDistance.Text);
             amountOfJourneys := StrToInt(frmAddDistance.edtAmountOfJourney.Text);
             TempENTransportItem := HTTPRIOENTransportItem as ENTransportItemControllerSoapPort;
             TempENTransportItem.addDistanceForTransport(StrToInt(sgTransportForDistance.Cells[0,sgTransportForDistance.Row]),  distance, amountOfJourneys);
         end;
       UpdateGrid(Sender);
  finally
     frmAddDistance.Free;
  end;
end;

procedure TfrmENPlanWorkEdit.actRemoveDistanceForTransportExecute(
  Sender: TObject);
var TempENTransportItem : ENTransportItemControllerSoapPort;
begin
   TempENTransportItem := HTTPRIOENTransportItem as ENTransportItemControllerSoapPort;
   TempENTransportItem.removeDistanceForTransport(StrToInt(sgTransportForDistance.Cells[0,sgTransportForDistance.Row]));
   UpdateGrid(Sender);
end;

procedure TfrmENPlanWorkEdit.actAssignToTransportDeptExecute(
  Sender: TObject);
  var
    i, eCode : integer;
    state_, isSel, isNotFree : boolean;
    frmRealTransportAssignToAllEdit : TfrmRealTransportAssignToAllEdit;
    TempENTransportItem: ENTransportItemControllerSoapPort;
    ENTransportItemObj : ENTransportItem;
    frmENTransportDepartmentShow: TfrmENTransportDepartmentShow;
    transportdepartmentCode : Integer;
begin
  inherited;
  transportdepartmentCode := 0;

  state_ := false;
  isSel := false;
  isNotFree := false;

  for i:=1 to sgENTransportItem.RowCount - 1 do
  begin
     sgENTransportItem.GetCheckBoxState(1,i,state_);
     if state_ then
     begin
       isSel := true;
     end;


     if ((sgENTransportItem.Cells[2,i] <> '') or (sgENTransportItem.Cells[2,i] <> ' ')) and (state_) then
     begin
          isNotFree := true;
     end;

  end;

  if not isSel then
  begin
     Application.MessageBox(PChar('Виберіть хоча б однин транспорт!!!'), PChar('Увага !'),MB_ICONWARNING);
     Exit;
  end;

  TempENTransportItem := HTTPRIOENTransportItem as ENTransportItemControllerSoapPort;
  frmENTransportDepartmentShow:=TfrmENTransportDepartmentShow.Create(Application,fmNormal);
  try
    with frmENTransportDepartmentShow do begin

        if ShowModal = mrOk then
        begin

                try

                   transportdepartmentCode := StrToInt(GetReturnValue(sgENTransportDepartment,0));
                    
                except
                   on EConvertError do Exit;
                end;
          for i:=1 to sgENTransportItem.RowCount - 1 do
           begin
           sgENTransportItem.GetCheckBoxState(1,i,state_);
            if state_ then
             begin
             if transportdepartmentCode <> 0 then
             begin               
               TempENTransportItem.updateTransportDepartment( StrToInt( sgENTransportItem.Cells[0, i ]) , transportdepartmentCode );
             end;
            end;
        end;
       pcPlanWorkChange(Sender);
      end;
    end;
  finally
     frmENTransportDepartmentShow.Free;
  end;
end;

procedure TfrmENPlanWorkEdit.actBindUnmountedCounterExecute(Sender: TObject);
var
formCounterUninstall :  TfrmSCCounterUninstallEdit;
TempSCCounter : SCCounterControllerSoapPort;
TempENPlanCorrectHistory : ENPlanCorrectHistoryControllerSoapPort;
listCorrHistory : ENPlanCorrectHistoryShortList;
corrHistoryFilter : ENPlanCorrectHistoryFilter;
counterFilter : SCCounterFilter;
SCCountersList : SCCounterShortList;
bindedInvNumber : String;
begin
  inherited;
  if ENPlanWorkObj.kind.code <> ENPLANWORKKIND_FACT then begin
    raise Exception.Create('Знімати лічильник у ПК "EnergyNet" можно тільки на Завданні-факт!');
  end;
  TempSCCounter := HTTPRIOSCCounter as SCCounterControllerSoapPort;
  counterFilter := SCCounterFilter.Create;
  SetNullXSProps(counterFilter);
  SetNullIntProps(counterFilter);
  counterFilter.conditionSQL := Format('exists (select from enestimateitem as es1 where es1.code = SCCOUNTER.ESTIMATEITEMREFCODE ' +
    ' and es1.planrefcode = %d' +
    ' and es1.kindrefcode = %d' +
    ') and sccounter.statusrefcode <> %d'
    , [ENPlanWorkObj.code, ENESTIMATEITEMKIND_DISMOUNT, SCCOUNTERSTATUS_CANCELED]);
  SCCountersList := TempSCCounter.getScrollableFilteredList(counterFilter, 0, -1);
  if SCCountersList.totalCount > 0 then begin
          bindedInvNumber := SCCountersList.list[0].invNumber;
          if Application.MessageBox(PChar('На плані вже прив''язаний лічильник для зняття інв. № ' + bindedInvNumber + '. Бажаєте видалити цю прив''язку?'),
                                PChar('Внимание!'),
                                MB_ICONQUESTION + MB_OKCANCEL + MB_DEFBUTTON2) <> IDOK then
            begin
              Exit;
            end;
          TempSCCounter.removeCounterUnmountedManually(SCCountersList.list[0].code);
          Self.UpdateGrid(sender);
          ShowMessage('Зняття лічильника з інвентарним № ' + bindedInvNumber + '  відмінено!');
          Exit;
  end;
  formCounterUninstall := TfrmSCCounterUninstallEdit.Create(Application, dsEdit);
  formCounterUninstall.planCode := ENPlanWorkObj.code;
  if formCounterUninstall.ShowModal = mrOk then begin
    if Application.MessageBox(PChar(Format('Ви дійсно бажаєте зняти лічильник %s?'
        , [formCounterUninstall.counter.invNumber])),
        PChar('Увага!'),MB_ICONQUESTION+MB_OKCANCEL+MB_DEFBUTTON2)=IDOK then
    begin
          formCounterUninstall.counter.dateIn := ENPlanWorkObj.dateFinal;
          TempENPlanCorrectHistory := HTTPRIOENPlanCorrectHistory as ENPlanCorrectHistoryControllerSoapPort;
          corrHistoryFilter := ENPlanCorrectHistoryFilter.Create;
          SetNullXSProps(corrHistoryFilter);
          SetNullIntProps(corrHistoryFilter);
          corrHistoryFilter.planNewRef := ENPlanWorkRef.Create;
          corrHistoryFilter.planNewRef.code := ENPlanWorkObj.code;
          listCorrHistory := TempENPlanCorrectHistory.getScrollableFilteredList(corrHistoryFilter, 0, -1);
          if listCorrHistory.totalCount = 0 then Exit;
          TempSCCounter.unInstallCounterManually(listCorrHistory.list[0].planOldRefCode
            , formCounterUninstall.kartaRefCode
            , formCounterUninstall.tabNumber
            , formCounterUninstall.counter);
          UpdateGrid(Sender);
          Application.MessageBox(PChar('Знятий лічильник додано!')
            , PChar('Повідомлення'), MB_ICONINFORMATION);
    end;

  end;
end;

procedure TfrmENPlanWorkEdit.actunAssignToTransportDeptExecute(
  Sender: TObject);
 var
    i, eCode : integer;
    state_, isSel, isNotFree : boolean;
    frmRealTransportAssignToAllEdit : TfrmRealTransportAssignToAllEdit;
    TempENTransportItem: ENTransportItemControllerSoapPort;
    ENTransportItemObj : ENTransportItem;
    frmENTransportDepartmentShow: TfrmENTransportDepartmentShow;
    transportdepartmentCode : Integer;
begin
  inherited;
  transportdepartmentCode := 0;

  state_ := false;
  isSel := false;
  isNotFree := false;

  for i:=1 to sgENTransportItem.RowCount - 1 do
  begin
     sgENTransportItem.GetCheckBoxState(1,i,state_);
     if state_ then
     begin
       isSel := true;
     end;


     if ((sgENTransportItem.Cells[2,i] <> '') or (sgENTransportItem.Cells[2,i] <> ' ')) and (state_) then
     begin
          isNotFree := true;
     end;

  end;

  if not isSel then
  begin
     Application.MessageBox(PChar('Виберіть хоча б однин транспорт!!!'), PChar('Увага !'),MB_ICONWARNING);
     Exit;
  end;


      TempENTransportItem := HTTPRIOENTransportItem as ENTransportItemControllerSoapPort;

        for i:=1 to sgENTransportItem.RowCount - 1 do
         begin
           sgENTransportItem.GetCheckBoxState(1,i,state_);
            if state_ then
             begin

               TempENTransportItem.updateTransportDepartment( StrToInt( sgENTransportItem.Cells[0, i ]) , LOW_INT);

            end;
        end;
       pcPlanWorkChange(Sender);

end;


procedure TfrmENPlanWorkEdit.actMakeNPZExecute(Sender: TObject);
Var TempENPlanWork: ENPlanWorkControllerSoapPort;
    ENPlanWorkList: ENPlanWorkShortList;
    ENPlanWorkFilterObj: ENPlanWorkFilter;
    ObjCode: Integer;
    tPlan : ENPlanWork;
    TempENElement: ENElementControllerSoapPort;
    tEObj:ENElement;
    TempENPlanWorkItem: ENPlanWorkItemControllerSoapPort;
    ENPlanWorkItemList: ENPlanWorkItemShortList;
    ENPlanWorkItemFilterObj: ENPlanWorkItemFilter;
    isMove:Integer;
    TempCCOutageNotice : CCOutageNoticeControllerSoapPort;
    isExistsOutages:boolean;
begin
  try
    ObjCode := ENPlanWorkObj.code;
  except
    on EConvertError do Exit;
  end;

  tPlan := DMReports.getPlanByCode(ObjCode);
  if tPlan = nil then
  begin
     Application.MessageBox(PChar('План not found !'), PChar('Увага'), MB_ICONWARNING);
     exit;
  end;

   TempENElement := HTTPRIOENElement as ENElementControllerSoapPort;
   tEObj:=TempENElement.getObject(tPlan.elementRef.code);

  if
    (
      not (tPlan.status.code in [ENPLANWORKSTATUS_GOOD, ENPLANWORKSTATUS_INCORRECTION])
    )
    and ( tPlan.kind.code <> ENPLANWORKKIND_CURRENT )
    then
  begin
      Application.MessageBox(PChar('План ВЖЕ затверджений або видалений !'), PChar('Увага'), MB_ICONWARNING);
      exit;
  end;


  isMove:=0;

  if (
  (tEObj.typeRef.code=7)
  and ((tPlan.typeRef.code=102)or (tPlan.typeRef.code=103) or (tPlan.typeRef.code=104))
  and (tPlan.formRef.code=ENPLANWORKFORM_NOPLANNED)
  ) then begin

  TempENPlanWorkItem := HTTPRIOENPlanWorkItem as ENPlanWorkItemControllerSoapPort;
  ENPlanWorkItemFilterObj:= ENPlanWorkItemFilter.Create;
  SetNullIntProps(ENPlanWorkItemFilterObj);
  SetNullXSProps(ENPlanWorkItemFilterObj);

  ENPlanWorkItemFilterObj.planRef:=ENPlanWorkRef.Create;
  ENPlanWorkItemFilterObj.planRef.code:=tPlan.code;

  ENPlanWorkItemFilterObj.conditionSQL := 'enplanworkitem.countgen<>0 and enplanworkitem.kartarefcode in  (select tki.code from tktechcard tki where (tki.name like '+''''+'%Планова%'+''''+' or tki.name like '+''''+'%трифаз%'+''''+'))'+
 ' and enplanworkitem.planrefcode IN (SELECT planrefcode from enplanworkmovehistory where planrefcode='+IntToStr(tPlan.code)+')';

  ENPlanWorkItemList:=TempENPlanWorkItem.getScrollableFilteredList(ENPlanWorkItemFilterObj,0,1);

  if (HIGH(ENPlanWorkItemList.list)>=0)
   then isMove:=1;

  end;


  if
 (
  (tEObj.typeRef.code=7)
  and ((tPlan.typeRef.code=102) or (tPlan.typeRef.code=103) or (tPlan.typeRef.code=104))
  and (tPlan.formRef.code=ENPLANWORKFORM_NOPLANNED)
  and (isMove=0)
  )

 then
  begin
      Application.MessageBox(PChar('Треба вибрати - Затвердити план(біллінг) або затвердити план з біллінгу !'), PChar('Увага'), MB_ICONWARNING);
      exit;
  end;

  if ((tEObj.typeRef.code=7)and(tPlan.typeRef.code=101))
   then
  begin
      Application.MessageBox(PChar('Треба  затвердити план з біллінгу !'), PChar('Увага'), MB_ICONWARNING);
      exit;
  end;

      if tPlan.causeDisconnection = 1 then
      begin
         TempCCOutageNotice := HTTPRIOCCOutageNotice as CCOutageNoticeControllerSoapPort;
         isExistsOutages:=TempCCOutageNotice.isExistsNoAgreedOutages(ENPlanWorkObj.code);
         if (isExistsOutages=True) then
         begin
            Application.MessageBox(PChar('Їснують непогоджені споживачем попередження!'),
                            PChar('Увага!'), MB_ICONWARNING);
            Exit;
         end;
      end;

  if Application.MessageBox(PChar('Ви дійсно бажаєте скласти Завдання План ?'),
                    PChar('Увага !'),MB_ICONQUESTION+MB_OKCANCEL+MB_DEFBUTTON2)=IDOK then
  begin
    TempENPlanWork := HTTPRIOENPlanWork as ENPlanWorkControllerSoapPort;

        TempENPlanWork.closePlanWork(ENPlanWorkObj.code);
        Application.MessageBox(PChar('План затверджено !'), PChar('Повідомлення'), MB_ICONINFORMATION);
        UpdateGrid(Sender);

  end;
end;


procedure TfrmENPlanWorkEdit.pMenuCopletationPlanPopup(Sender: TObject);
var
  plan : ENPlanWork;
  planCode : Integer;

begin
  DisableActions([actClosePlan, actUnClose], False);
  HideActions([actClosePlan, actUnClose], False);

  try
    planCode := StrToInt(sgCompletionPlan.Cells[0,sgCompletionPlan.Row]);
  except
    on EConvertError do Exit;
  end;

  plan := DMReports.getPlanByCode(planCode);
  if plan = nil then
  begin
    Exit;
  end;

  miClosePlan.Caption := 'Затвердити План';

  if plan.kind.code = ENPLANWORKKIND_CURRENT then
     miClosePlan.Caption := 'Скласти Завдання ПЛАН'
  else
  if plan.kind.code = ENPLANWORKKIND_NPZ then
     miClosePlan.Caption := 'Розпочати виконання'
  else
  if plan.kind.code = ENPLANWORKKIND_FACT then
     miClosePlan.Caption := 'Затвердити Факт, для якого не потрібно формувати Акт ...';//'Затвердити ФАКТ';

  actClosePlan.Enabled := //(plan.kind.code <> ENPLANWORKKIND_FACT) and
                          (
                             ( plan.status.code = ENPLANWORKSTATUS_GOOD) or ( plan.kind.code = ENPLANWORKKIND_CURRENT)
                             // для статусов на корректировке - ограничим на сервере ... and (plan.status.code <> ENPLANWORKSTATUS_PRECONFIRMED)
                           );

  actClosePlan.Visible := actClosePlan.Enabled;
  

  // planUnclose ...
  actUnClose.Enabled := (
                          (plan.kind.code = ENPLANWORKKIND_NPZ) or (plan.kind.code = ENPLANWORKKIND_FACT)
                        // для 2011 года можно удалять .. ПОКА .. до создания ЗАЯВКИ!!!!
                         or ((plan.kind.code = ENPLANWORKKIND_CURRENT) and (plan.yearGen = 2011))

                         )
                        and (plan.status.code = ENPLANWORKSTATUS_GOOD) ;

   // отмена утвержденных без Актов .... на сервере чекним есть ли акт ;)
   if  (plan.status.code = ENPLANWORKSTATUS_LOCKED) and (plan.kind.code = ENPLANWORKKIND_FACT) then
   begin
     actUnClose.Enabled :=  True;
     miUnClose.Caption := 'Відмінити затвердження Факту, для якого не потрібно формувати Акт ...'
   end
   else begin
     miUnClose.Caption := 'Видалити для коригування попереднього';
   end;

  if (plan.typeRef.code in [ENPLANWORKTYPE_SERVICES_FROM_SIDE, ENPLANWORKTYPE_SERVICES_FROM_SIDE_INVEST]) then
  begin
    DisableActions([actClosePlan, actUnClose]);
    HideActions([actClosePlan, actUnClose]);
  end;

end;


procedure TfrmENPlanWorkEdit.actClosePlanExecute(Sender: TObject);
Var TempENPlanWork: ENPlanWorkControllerSoapPort;
    ENPlanWorkList: ENPlanWorkShortList;
    ENPlanWorkFilterObj: ENPlanWorkFilter;
    planCode : Integer;
    tPlan : ENPlanWork;
    TempENElement: ENElementControllerSoapPort;
    tEObj:ENElement;
    TempENPlanWorkItem: ENPlanWorkItemControllerSoapPort;
    ENPlanWorkItemList: ENPlanWorkItemShortList;
    ENPlanWorkItemFilterObj: ENPlanWorkItemFilter;
    isMove:Integer;
    TempCCOutageNotice :CCOutageNoticeControllerSoapPort;
    isExistsOutages:Boolean;
begin
  try
    planCode := StrToInt(sgCompletionPlan.Cells[0,sgCompletionPlan.Row]);
  except
    on EConvertError do Exit;
  end;

  //if not (Integer(sgENPlanWork.Objects[0,sgENPlanWork.Row]) in [ ENPLANWORKSTATUS_GOOD, ENPLANWORKSTATUS_INCORRECTION]) then
  //tPlan := DMReports.getPlanByCode(StrToInt(sgENPlanWork.Cells[0,sgENPlanWork.Row]));
  tPlan := DMReports.getPlanByCode(planCode);
  if tPlan = nil then
  begin
     Application.MessageBox(PChar('План not found !'), PChar('Увага'), MB_ICONWARNING);
     exit;
  end;

   TempENElement := HTTPRIOENElement as ENElementControllerSoapPort;
   tEObj:=TempENElement.getObject(tPlan.elementRef.code);


  if
    (
      not (tPlan.status.code in [ENPLANWORKSTATUS_GOOD, ENPLANWORKSTATUS_INCORRECTION])
    )
    and ( tPlan.kind.code <> ENPLANWORKKIND_CURRENT )
    then
  begin
      Application.MessageBox(PChar('План ВЖЕ затверджений або видалений !'), PChar('Увага'), MB_ICONWARNING);
      exit;
  end;



  isMove:=0;
  if (
  (tEObj.typeRef.code=7)
  and ((tPlan.typeRef.code=102)or (tPlan.typeRef.code=103) or (tPlan.typeRef.code=104))
  and (tPlan.formRef.code=ENPLANWORKFORM_NOPLANNED)
  ) then begin

  TempENPlanWorkItem := HTTPRIOENPlanWorkItem as ENPlanWorkItemControllerSoapPort;
  ENPlanWorkItemFilterObj:= ENPlanWorkItemFilter.Create;
  SetNullIntProps(ENPlanWorkItemFilterObj);
  SetNullXSProps(ENPlanWorkItemFilterObj);

  ENPlanWorkItemFilterObj.planRef:=ENPlanWorkRef.Create;
  ENPlanWorkItemFilterObj.planRef.code:=tPlan.code;

  ENPlanWorkItemFilterObj.conditionSQL := 'enplanworkitem.countgen<>0 and enplanworkitem.kartarefcode in  (select tki.code from tktechcard tki where (tki.name like '+''''+'%Планова%'+''''+' or tki.name like '+''''+'%трифаз%'+''''+'))'+
 ' and enplanworkitem.planrefcode IN (SELECT planrefcode from enplanworkmovehistory where planrefcode='+IntToStr(tPlan.code)+')';

  ENPlanWorkItemList:=TempENPlanWorkItem.getScrollableFilteredList(ENPlanWorkItemFilterObj,0,1);

  if (HIGH(ENPlanWorkItemList.list)>=0)
   then isMove:=1;

  end;


  if
 (
  (tEObj.typeRef.code=7)
  and ((tPlan.typeRef.code=102) or (tPlan.typeRef.code=103) or (tPlan.typeRef.code=104))
  and (tPlan.formRef.code=ENPLANWORKFORM_NOPLANNED)
  and (tPlan.kind.code<>4)
  and (isMove=0)
  )

  then
  begin
      Application.MessageBox(PChar('Треба вибрати - Затвердити план(біллінг) або затвердити план з біллінгу !'), PChar('Увага'), MB_ICONWARNING);
      exit;
  end;

  if ((tEObj.typeRef.code=7)and(tPlan.typeRef.code=101)and(tPlan.kind.code<>4))
   then
  begin
      Application.MessageBox(PChar('Треба  затвердити план з біллінгу !'), PChar('Увага'), MB_ICONWARNING);
      exit;
  end;

      if tPlan.causeDisconnection = 1 then
      begin
         TempCCOutageNotice := HTTPRIOCCOutageNotice as CCOutageNoticeControllerSoapPort;
         isExistsOutages:=TempCCOutageNotice.isExistsNoAgreedOutages(ENPlanWorkObj.code);
         if (isExistsOutages=True) then
         begin
            Application.MessageBox(PChar('Їснують непогоджені споживачем попередження!'),
                            PChar('Увага!'), MB_ICONWARNING);
            Exit;
         end;
      end;

  if Application.MessageBox(PChar('Ви дійсно бажаєте '+ miClosePlan.Caption +' ?'),
                    PChar('Увага !'),MB_ICONQUESTION+MB_OKCANCEL+MB_DEFBUTTON2)=IDOK then
  begin
    TempENPlanWork := HTTPRIOENPlanWork as ENPlanWorkControllerSoapPort;

    TempENPlanWork.closePlanWork(StrToInt(sgCompletionPlan.Cells[0,sgCompletionPlan.Row]));
    Application.MessageBox(PChar('План затверджено !'), PChar('Повідомлення'), MB_ICONINFORMATION);
    UpdateGrid(Sender);
  end;

end;


procedure TfrmENPlanWorkEdit.actUnCloseExecute(Sender: TObject);
var
  TempENPlanWork : ENPlanWorkControllerSoapPort;
  planCode : Integer;
begin

  try
    planCode := StrToInt(sgCompletionPlan.Cells[0,sgCompletionPlan.Row]);
  except
    on EConvertError do Exit;
  end;

  if Application.MessageBox(PChar('Ви дійсно бажаєте Відминити затвердження ... ?'),
                    PChar('Увага !'),MB_ICONQUESTION+MB_OKCANCEL+MB_DEFBUTTON2)=IDOK then
  begin
    TempENPlanWork := HTTPRIOENPlanWork as ENPlanWorkControllerSoapPort;

    TempENPlanWork.openPlanWork(planCode);

    Application.MessageBox(PChar('Видалено ...'), PChar('Повідомлення'), MB_ICONINFORMATION);
    UpdateGrid(Sender);
  end;
  
end;


procedure TfrmENPlanWorkEdit.UpdateENEstimateItemWithFinGrid(grid: TAdvStringGrid; estimateItemFilter: ENEstimateItemFilter);
var
  TempFINMaterials: FINMaterialsControllerSoapPort;
  ei2FinList: ENEstimateItem2FinShortList;
  i, ei2FinLastCount, tmpEstimateCode: Integer;
  requiredQuantity, filledQuantity: Double;
begin
  ClearGrid(grid);

  TempFINMaterials := HTTPRIOFINMaterials as FINMaterialsControllerSoapPort;
  //estimateItemFilter.orderBySQL := '';
  estimateItemFilter.orderBySQL := 'sub_info.tkmaterialname, ENESTIMATEITEM.CODE';
  
  ei2FinList := TempFINMaterials.getShortListWithFinMaterialsForFact(estimateItemFilter, 0, -1);

    ei2FinLastCount := High(ei2FinList.list);

    if ei2FinLastCount > -1 then
      grid.RowCount := ei2FinLastCount + 2
    else
      grid.RowCount := 2;


  tmpEstimateCode := LOW_INT;

   with grid do
     for i := 0 to ei2FinLastCount do
     begin
       Application.ProcessMessages;

       if ei2FinList.list[i].estimateCode <> Low(Integer) then
         Cells[0,i+1] := IntToStr(ei2FinList.list[i].estimateCode)
       else
         Cells[0,i+1] := '';

       // Если под один estimate подвязано несколько разных номенклатур, то данные в левой части грида (по estimat'у)
       // выводим только один раз (в первой строке, для остальных строк - пусто)
       if ei2FinList.list[i].estimateCode <> tmpEstimateCode then
       begin
         tmpEstimateCode := ei2FinList.list[i].estimateCode;

         Cells[1,i+1] := ei2FinList.list[i].materialRefName;

         if ei2FinList.list[i].estimateCountGen = nil then
           Cells[2,i+1] := ''
         else
           Cells[2,i+1] := ei2FinList.list[i].estimateCountGen.DecimalString;

         if ei2FinList.list[i].estimateCountFact = nil then
           Cells[3,i+1] := ''
         else
           Cells[3,i+1] := ei2FinList.list[i].estimateCountFact.DecimalString;

         Cells[4,i+1] := ei2FinList.list[i].measureType;

         CellProperties[8, i+1].ReadOnly := true;
         if (ei2FinList.list[i].isobligatory > 0 )  then
            AddCheckBox(8, i+1, true, false);




       end
       else begin
         Cells[1,i+1] := '';
         Cells[2,i+1] := '';
         Cells[3,i+1] := '';
         Cells[4,i+1] := '';
       end;

       if ei2FinList.list[i].quantity = nil then
         Cells[5,i+1] := ''
       else
         Cells[5,i+1] := ei2FinList.list[i].quantity.DecimalString;

       Cells[6,i+1] := ei2FinList.list[i].mat_name;
       Cells[7,i+1] := ei2FinList.list[i].nn;

       /////////////////////////////////
       requiredQuantity := 0;
       try
         if ei2FinList.list[i].estimateCountFact <> nil then
           if ei2FinList.list[i].estimateCountFact.DecimalString <> '' then
             requiredQuantity := StrToFloat(ei2FinList.list[i].estimateCountFact.DecimalString);
       except
         on EConvertError do requiredQuantity := 0;
       end;

       filledQuantity := 0;
       try
         if ei2FinList.list[i].quantityFINMaterials <> nil then
           if ei2FinList.list[i].quantityFINMaterials.DecimalString <> '' then
             filledQuantity := StrToFloat(ei2FinList.list[i].quantityFINMaterials.DecimalString);
       except
         on EConvertError do filledQuantity := 0;
       end;

       if filledQuantity = requiredQuantity then
       begin
         RowColor[i + 1] := $0080FF80;
       end
       else
         RowColor[i + 1] := clWindow;
       /////////////////////////////////

       Objects[0,i+1] := TObject(ei2FinList.list[i].estimateTypeRefCode);

       ///// Выводим количества жирным шрифтом
       CellProperties[3, i + 1].FontStyle := CellProperties[3, i + 1].FontStyle + [fsBold];
       CellProperties[5, i + 1].FontStyle := CellProperties[5, i + 1].FontStyle + [fsBold];
       /////

       grid.RowCount := i + 2;
     end;

  grid.Row := 1;
end;

procedure TfrmENPlanWorkEdit.btnAddToTravelSheetClick(Sender: TObject);
var
  frmENTravelSheet : TfrmENTravelSheetShow;
  TempENTravelSheet : ENTravelSheetControllerSoapPort;
  TempENTransportOrder : ENTransportOrderControllerSoapPort;
  travelSheetFilter : ENTravelSheetFilter;
  travelSheet : ENTravelSheet;
  transportOrderCode, transportDepartmentCode, travelSheetCode, transportRealCode : Integer;
  transportOrder : ENTransportOrder;
  date : TDateTime;
  condition : String;
  finWorkerTabNumber, numberTravel : String;

begin
  inherited;

  if ((Sender = sgGroupedTransportItem) and (DialogState = dsView)) then Exit;

  if sgGroupedTransportItem.Cells[0, sgGroupedTransportItem.Row] = '' then Exit;

  TempENTransportOrder := HTTPRIOENTransportOrder as ENTransportOrderControllerSoapPort;
  TempENTravelSheet := HTTPRIOENTravelSheet as ENTravelSheetControllerSoapPort;

  transportOrderCode := StrToInt(sgGroupedTransportItem.Cells[0, sgGroupedTransportItem.Row]);
  transportDepartmentCode := Integer(sgGroupedTransportItem.Objects[2, sgGroupedTransportItem.Row]);

  if transportOrderCode < 0 then
    begin
     Application.MessageBox(PChar('Ще немає введеного часу для цього транспорту'), PChar('Внимание'), MB_OK + MB_ICONWARNING);
     exit;
    end;

    transportOrder := TempENTransportOrder.getObject(transportOrderCode);
    transportDepartmentCode := transportOrder.transportDepartment.code;

    if transportDepartmentCode < 0 then
    begin
     Application.MessageBox(PChar('Введіть транспортний підрозділ'), PChar('Внимание'), MB_OK + MB_ICONWARNING);
     exit;
    end;

  if chbDetailed.Checked then
    chbDetailed.Checked := false;

    date := EncodeDateTime( ENPlanWorkObj.dateStart.Year,            ENPlanWorkObj.dateStart.Month,
                                                                      ENPlanWorkObj.dateStart.Day,
                                                                      0,
                                                                      0,
                                                                     0,0);

  // Отображение формы путевых листов
  travelSheetFilter := ENTravelSheetFilter.Create;
  SetNullXSProps(travelSheetFilter);
  SetNullIntProps(travelSheetFilter);

  condition := 'select entravelsheet.code from entravelsheet where entravelsheet.transportrealcode in (select tr.code from tktransportreal as tr where tr.transportdepartmntrfcd =' + IntToStr(transportDepartmentCode) + ' and tr.isonduty = ' +
                IntToStr(ENConsts.TKTRANSPORTREAL_ISONDUTY) + ') and ' +
                      ' ((entravelsheet.datestart >= '' ' + DateTimeToStr(date) +  ' '' and entravelsheet.datefinal <='' '+ DateTimeToStr(date)+ ' '' ) ' +
                      ' or ' +
                      '(''' + DateTimeToStr(date) + ''' >= entravelsheet.datestart and ''' + DateTimeToStr(date) + ''' <= entravelsheet.datefinal)' + ')';

  travelSheetFilter.conditionSQL := 'ENTRAVELSHEET.CODE IN (' + condition + ')';

  frmENTravelSheet := TfrmENTravelSheetShow.Create(Application, fmNormal, travelSheetFilter);

  with frmENTravelSheet do
  begin
    DisableActions([actFilter, actNoFilter]);
    if ShowModal = mrOk then
    begin
          travelSheetCode := StrToInt(GetReturnValue(sgENTravelSheet,0));
          travelSheet := TempENTravelSheet.getObject(travelSheetCode);
          numberTravel := travelSheet.numberGen;
          if Application.MessageBox(PChar('Ви дійсно бажаєте включити обраний транспорт до подорожнього листа № ' + numberTravel + '?'),
                           PChar('Внимание'), MB_OKCANCEL + MB_ICONQUESTION) <> IDOK then Exit;

          transportRealCode := travelSheet.transportReal.code;
          finWorkerTabNumber := travelSheet.finWorker.tabNumber;

          TempENTransportOrder.addTransportWithWorker(transportOrderCode, finWorkerTabNumber, transportRealCode);

    end;
  end;
  UpdateGrid(Sender);
  DialogState := dsView;
  Self.Show;
end;

procedure TfrmENPlanWorkEdit.btnAddWorkItemByShifrClick(Sender: TObject);
begin

   frmAddWorkItemByShifr:=TfrmAddWorkItemByShifr.Create(Application, dsInsert);
  try
    frmAddWorkItemByShifr.planCode:= ENPlanWorkObj.code;
    frmAddWorkItemByShifr.yeargen:= ENPlanWorkObj.yearGen;

   if frmAddWorkItemByShifr.ShowModal = mrOk then
     begin
       actUpdateExecute(sender);
     end;

  finally
    frmAddWorkItemByShifr.Free;

  end;
end;

procedure TfrmENPlanWorkEdit.btnAttachMaterialsClick(Sender: TObject);
begin
  frmBindingMaterialsFromRem:=TfrmBindingMaterialsFromRem.Create(Application, DialogState  {dsEdit});
  try

   if frmBindingMaterialsFromRem.ShowModal = mrOk then
     begin
       actUpdateExecute(sender);
     end;

  finally
    frmBindingMaterialsFromRem.Free;

  end;
  pcPlanWorkChange(Sender);
end;

procedure TfrmENPlanWorkEdit.actAddRashodOrderToStorage2transportRouteExecute(
  Sender: TObject);
var
  f : RQFKOrderFilter;
  TempTransportRoute2FKorder : ENTransportRoute2RQFKOrderControllerSoapPort;
  transportRoute2RQFKOrder : ENTransportRoute2RQFKOrder;
  i : Integer;
  checked :boolean;
  frmRQFKOrderOutShow: TfrmRQFKOrderShow;
begin

  f := RQFKOrderFilter.Create;
  SetNullIntProps(f);
  SetNullXSProps(f);

  f.kind := RQFKOrderKind.Create;
  f.kind.code := RQFKORDER_KIND_RASHOD_TO_STORAGE;

  //f.status := RQFKOrderStatus.Create;
  //f.status.code := RQFKORDER_STATUS_CREATED;

  ///////   УУУУУУУУУУУУУ
  ///////   показываем складені, проведені
  f.conditionSQL := ' rqfkorder.statuscode in ('
    + IntToStr(RQFKORDER_STATUS_CREATED) + ',' + IntToStr(RQFKORDER_STATUS_IN_WORK_ON_WAREHOUSE) + ','
    + IntToStr(RQFKORDER_STATUS_IN_FK) + ')';
  frmRQFKOrderOutShow := TfrmRQFKOrderShow.Create(Application, fmNormal, f);
  try

    frmRQFKOrderOutShow.Caption := 'Видаткові ордера';
    frmRQFKOrderOutShow.sgRQFKOrder.ColWidths[7] := 0;
    frmRQFKOrderOutShow.sgRQFKOrder.ColWidths[8] := 0;
    frmRQFKOrderOutShow.sgRQFKOrder.ColWidths[9] := 0;
    frmRQFKOrderOutShow.sgRQFKOrder.ColWidths[10] := 0;
    frmRQFKOrderOutShow.sgRQFKOrder.ColWidths[11] := 0;
    frmRQFKOrderOutShow.sgRQFKOrder.ColWidths[12] := 0;
    frmRQFKOrderOutShow.sgRQFKOrder.ColWidths[13] := 150;

    frmRQFKOrderOutShow.isTransportRoutes := True;

    transportRoute2RQFKOrder := ENTransportRoute2RQFKOrder.Create;
    transportRoute2RQFKOrder.transportRouteRef := ENTransportRouteRef.Create;

    try
      transportRoute2RQFKOrder.transportRouteRef.code := StrToInt(sgENTransportRoute.Cells[0,sgENTransportRoute.Row]);
    except
      on EConvertError do Exit;
    end;


     with frmRQFKOrderOutShow do
       if ShowModal = mrOk then
       begin
           try
             for i := 0 to sgRQFKOrder.RowCount - 1  do
             begin
               checked:= False;
               sgRQFKOrder.Row := i;
               sgRQFKOrder.GetCheckBoxState(1, i, checked);
               TempTransportRoute2FKorder := HTTPRIOENTransportRoute2RQFKOrder as ENTransportRoute2RQFKOrderControllerSoapPort;
               if checked then
               begin

                transportRoute2RQFKOrder.fkOrderRef := RQFKOrderRef.Create;
                transportRoute2RQFKOrder.fkOrderRef.code := StrToInt(GetReturnValue(sgRQFKOrder,0));

                TempTransportRoute2FKorder.add(transportRoute2RQFKOrder);

                end;
               end;
           except
              on EConvertError do Exit;
           end;
       end;
  finally
     frmRQFKOrderOutShow.Free;
     //frmRQFKOrderOutShow := nil;
  end;

  UpdateGrid(Sender);
end;

function TfrmENPlanWorkEdit.getSCCountersListByEstimate(estimateItemCode : Integer) : SCCounterShortList;
var
  TempSCCounter: SCCounterControllerSoapPort;
  SCCounterList: SCCounterShortList;
  counterFilter: SCCounterFilter;
begin
	TempSCCounter := HTTPRIOSCCounter as SCCounterControllerSoapPort;
	counterFilter := SCCounterFilter.Create;
	SetNullIntProps(counterFilter);
	SetNullXSProps(counterFilter);
	counterFilter.estimateItemRef := ENEstimateItemRef.Create;
	counterFilter.estimateItemRef.code := estimateItemCode;
	SCCounterList := TempSCCounter.getScrollableFilteredList(counterFilter, 0, -1);
	Result := SCCounterList;
end;
end.

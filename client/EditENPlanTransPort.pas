unit EditENPlanTransPort;

interface

uses
    Windows, Messages, SysUtils, StdCtrls, Variants, Classes, Graphics, Controls, Forms,
    Dialogs, ChildFormUnit, InvokeRegistry, Rio, SOAPHTTPClient, ImgList,
    Grids, ComCtrls, ToolWin, Menus, ActnList, BaseGrid,  XSBuiltIns, GridHeadersUnit,
    DialogFormUnit, AdvGrid, Buttons,
	EnergyproController, EnergyproController2, ENPlanWorkController, ExtCtrls
   ,SOAPHTTPTrans, TB2Item, TB2Dock, TB2Toolbar
   ,FINMolDataController, tmsAdvGridExcel, AdvObj
  ;

type
  TfrmENPlanTransPortEdit = class(TDialogForm)

  btnOk: TButton;
  btnCancel: TButton;
    pcPlanWork: TPageControl;
    tsPlanWork: TTabSheet;
    tsPlanWorkItems: TTabSheet;
    lblDateGen: TLabel;
    edtDateGen: TDateTimePicker;
    lblYearGen: TLabel;
    lblMonthGen: TLabel;
    lblCommentGen: TLabel;
    edtCommentGen: TEdit;
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
    HTTPRIO1: THTTPRIO;
    HTTPRIOFINMaterials: THTTPRIO;
    actMaterialBindingToFIN: TAction;
    N10: TMenuItem;
    gbFINMaterials: TGroupBox;
    sgFINMaterials: TAdvStringGrid;
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
    gbMarkers: TGroupBox;
    sgMarkers: TAdvStringGrid;
    Splitter3: TSplitter;
    HTTPRIOMarkEstimate: THTTPRIO;
    actMoveTO: TAction;
    actMoveFrom: TAction;
    N16: TMenuItem;
    N17: TMenuItem;
    N18: TMenuItem;
    HTTPRIOTKTechCardPWI: THTTPRIO;
    lblMeasure: TLabel;
    gbPlanWorkItem: TGroupBox;
    spbEPKard: TSpeedButton;
    lblKarta: TLabel;
    edtKartiName: TEdit;
    lblCountGen: TLabel;
    edtCountGen: TEdit;
    Label9: TLabel;
    edtKartiNum: TEdit;

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
    procedure actExpToExcelHumanExecute(Sender: TObject);
    procedure actChangeEstimateItemStatusExecute(Sender: TObject);
    procedure actSelectAllExecute(Sender: TObject);
    procedure actClearAllExecute(Sender: TObject);
    procedure actChangeEstimateItemStatusPLANEDExecute(Sender: TObject);
    procedure updateMarksGrid(estimateItemCode : Integer; stringGrid : TAdvStringGrid);
    procedure actMoveTOExecute(Sender: TObject);
    procedure actMoveFromExecute(Sender: TObject);
    procedure spbEPKardClick(Sender: TObject);

  private
    { Private declarations }
    KindCode: Integer;
    WorkOrderEditState: TDialogState;

    arrayOfFINMOLData : array of FINMOLData;

    procedure InitWorkOrderFields;
    procedure ClearWorkOrderFields;
  public
    { Public declarations }
      ENPlanWorkObj: ENPlanWork;
      actCode, itemCode : Integer;
      molCode, molName : String;
      addPlanItemsMode, isTransport : Boolean;
      //procedure ChangeCaptions;
end;

var
  frmENPlanTransPortEdit: TfrmENPlanTransPortEdit;
  //ENPlanWorkObj: ENPlanWork;

implementation

uses
  ShowENPlanWorkStatus
  ,ENPlanWorkStatusController
, ShowENElement, ENElementController, ENPlanWorkItemController,
  EditENPlanWorkItem, ENEstimateItemController,
  EditENEstimateItem, ENConsts, DMReportsUnit, ENPlanWorkTypeController,
  ENPlanWorkMoveHistoryController, ENPlanCorrectHistoryController,
  ShowEPDepartment, ENDepartmentController, ShowENDepartment,
  ENDepartmentTypeController
  ,ShowENPlanWorkType,ShowENPlanWorkState, EditENPlanWorkState ,EditENPLanWorkFilter

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
  ENMarkEstimateController, EditENEstimateItem2ENEstimateItem,
  TKTechCardController, ShowTKTechCard;

{uses  
    EnergyproController, EnergyproController2, ENPlanWorkController  ;
}
{$R *.dfm}

var
  planItemFilter: ENPlanWorkItemFilter;
  estimateItemFilter: ENEstimateItemFilter;
  moveFilterObject : ENPlanWorkMoveHistoryFilter;
  corrFilterObject : ENPlanCorrectHistoryFilter;

  //ENAct2ENPlanWorkObj : ENAct2ENPlanWork;

  ENPlanWorkItemHeaders: array [1..11] of String =
        ( 'Код'
          ,'Код роботи'
          ,'Робота'
          ,'Джерело нормативу'
          ,'Кількість'
          ,'Норма часу на од.'
          ,'Всього часу'
          ,'Час з коеф.'
          ,'Вимірювач'
          ,'Од. виміру'
          ,'Користувач, що вніс зміни'
          //,'Дата останньої зміни'
        );

  ENEstimateItemHeaders: array [1..11] of String =
        ( 'Код'
          ,'Матеріал'
          ,'Кількість нормативна'
          ,'Кількість скорегована'           // !!! используеться при разноске с ФинКол !!!
          ,'Од. виміру'
          ,'Код роботи'
          ,'Робота'
          ,'Тип строки кошторису'
          ,'Статус'
          ,'Користувач, що вніс зміни'
          ,'Дата останньої зміни'
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
        
   ENTransportItemHeaders: array [1..12] of String =
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
          ,'Користувач що вніс зміни'
          ,'Дата останньої зміни'
        );

  FINMolDataHeaders: array [1..4] of String =
        ( 'Код'
          ,'код МОЛа из фин.кол.'
          ,'ФИО МОЛа с фин.кол.'
          ,'Тип МОЛа'
        );

  MarksHeaders: array [1..2] of String =
        ( 'Код'
          ,'Назва маркеру'
        );

  iColCount, iLastCount: Integer;
  iLastRow: Integer = 1;

  eiColCount, eiLastCount: Integer;
  eiLastRow: Integer = 1;
  selectedRow : Integer;


procedure TfrmENPlanTransPortEdit.FormShow(Sender: TObject);
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

    
  i: Integer;
  planKindName: String;

  TempENBindingOver: ENBindingOverControllerSoapPort;
  ENBindingOverFilterObj: ENBindingOverFilter;
  ENBindingOverList: ENBindingOverShortList;


begin


  SetComboBoxCurrentYearWithStart(edtYearGen, 2009, 2);
  SetComboBoxCurrentMonth(edtMonthGen);

  SetGridHeaders(FINMolDataHeaders, sgFINMolData.ColumnHeaders);
  
  SetFloatStyle([edtDistanceTo, edtDistanceAlong]);

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
  ]);

  HideControls([lblENActNumber, edtENActNumber, spbENAct]); // только ФАКТ !!! ... акт покажем только для НПЗ или ФАКТА ...
  HideControls([gbPriConnection, lblPriConnectionNumber, edtPriConnectionNumber]); // покажем только для Присоединения

  HideControls([gbBindingOver]); // покажем только для ПриписУ
  DisableControls([edtBindingOver, spbBindingOver]);

  // Кнопки для сохранения наряд-задания (показывать будем только при нажатии на "Редактировать" или "Добавить")
  HideControls([btnWorkOrderSave, btnWorkOrderCancel]);
  DisableControls([spbFINMol, spbFINMechanic]);

  DisableControls([edtMolName, spbPlanMOL, spbPlanMOLClear]);

  //HideControls([gbWorkOrder]); // покажем только для НПЗ-Плана/Факта
  tsWorkOrder.TabVisible := false;

  WorkOrderEditState := dsView;

  //HideControls([btnMaterialBinding], DialogState in [dsInsert, dsView]);

  gbPlanMOL.Visible := false;
  gbPlanWorkItem.Visible := false;

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
     tsGSM.TabVisible := false;

     edtDateStartClick(Sender);

     HideControls([lblENActNumber, edtENActNumber, spbENAct, lblPK, edtCode]);

     // при вставке можно поменять ТИП плана (год, тек, нпз)
     DisableControls([cbPlanWorkKind , spbType ], false);
     DisableControls([spbENPlanWorkState]);

  end;


  if  ((DialogState = dsInsert) and (isTransport)) then
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
     tsGSM.TabVisible := false;

     edtDateStartClick(Sender);

     HideControls([lblENActNumber, edtENActNumber, spbENAct, lblPK, edtCode]);

     // при вставке можно поменять ТИП плана (год, тек, нпз)
     DisableControls([cbPlanWorkKind , spbType ], false);
     DisableControls([spbENPlanWorkState]);

    DisableControls([edtTypeName, spbType, edtWorkState, spbENPlanWorkState,
                     edtENBudgetName, edtResponsibility, spbENBudget, spbResponsibility]);

    ENPlanWorkObj.typeRef := ENPlanWorkTypeRef.Create();
    ENPlanWorkObj.typeRef.code := 11;
    edtTypeName.Text := 'Перевезення';
    ENPlanWorkObj.stateRef := ENPlanWorkStateRef.Create();
    ENPlanWorkObj.stateRef.code := 12;
    edtWorkState.Text := 'Списання паливно-мастильних матеріалів';

    ENPlanWorkObj.budgetRef := ENDepartmentRef.Create();
    ENPlanWorkObj.budgetRef.code := 75000016;
    edtENBudgetName.Text := 'служба транспорту';

    ENPlanWorkObj.responsibilityRef := ENDepartmentRef.Create();
    ENPlanWorkObj.responsibilityRef.code := 75000017;
    edtResponsibility.Text := 'служба Транспорту';

    cbPlanWorkKindChange(Sender);

  end;


  if (DialogState = dsEdit) then
  begin
    if ENPlanWorkObj.typeRef.code = 7 then // Присоединение
      DenyBlankValues([edtPriConnectionNumber]);

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

    end;

    if ENPlanWorkObj.kind.code =  ENPLANWORKKIND_CURRENT then
    begin
        gbPlanMOL.Visible := true;
        DisableControls([spbPlanMOL, spbPlanMOLClear], false);
    end;

  end;


  if ((DialogState = dsEdit) and (isTransport)) then
  begin
    if ENPlanWorkObj.typeRef.code = 7 then // Присоединение
      DenyBlankValues([edtPriConnectionNumber]);

    DisableControls([cbENPlanWorkFormName]);

    tsEstimateItems.TabVisible := true;
    tsDismount.TabVisible := false;

    if ENPlanWorkObj.kind.code = ENPLANWORKKIND_FACT then
    begin
      DisableControls(tsPlanWork);
      DisableControls([edtDateStart, spbENAct, {gbWorkOrder,} edtCommentGen, spbFINExecutor, spbDepartment], false);
      DenyBlankValues([edtDepartment]);
      tsWorkOrder.TabVisible := true;
    end;

    if ENPlanWorkObj.kind.code =  ENPLANWORKKIND_CURRENT then
    begin
        gbPlanMOL.Visible := true;
        DisableControls([spbPlanMOL, spbPlanMOLClear], false);
    end;
  end;


  if  (DialogState = dsView) then
  begin
    DisableControls([spbResponsibility ,spbENBudget, spbDepartment, spbType
                    ,spbENAct,spbENPlanWorkState
                    , spbFINExecutor
                    , tbWorkOrder
                    //, btnWorkOrderSave
                    //, btnWorkOrderCancel
                    ]);

    DisableActions([actInsert, actEdit, actDelete, actMaterialBinding,
                    actWorkOrderInsert, actWorkOrderEdit, actWorkOrderDelete]);

    HideControls([btnWorkOrderSave, btnWorkOrderCancel]);
    DisableControls([cbShowAll, cbShowAllGSM], false);

    if ENPlanWorkObj.kind.code =  ENPLANWORKKIND_CURRENT then
    begin
        gbPlanMOL.Visible := true;
        DisableControls([edtMolName, spbPlanMOL, spbPlanMOLClear]);
    end;
  end;


  if  ((DialogState = dsView) and (isTransport)) then
  begin
    DisableControls([spbResponsibility ,spbENBudget, spbDepartment, spbType
                    ,spbENAct,spbENPlanWorkState
                    , spbFINExecutor
                    , tbWorkOrder
                    ]);

    DisableActions([actInsert, actEdit, actDelete, actMaterialBinding,
                    actWorkOrderInsert, actWorkOrderEdit, actWorkOrderDelete]);


    tsEstimateItems.TabVisible := true;
    tsDismount.TabVisible := false;

    HideControls([btnWorkOrderSave, btnWorkOrderCancel]);
    DisableControls([cbShowAll, cbShowAllGSM], false);

    if ENPlanWorkObj.kind.code =  ENPLANWORKKIND_CURRENT then
    begin
        gbPlanMOL.Visible := true;
        DisableControls([edtMolName, spbPlanMOL, spbPlanMOLClear]);
    end;
  end;



  if  (DialogState = dsEdit) or (DialogState = dsView) then
  begin

    if isTransport then
     begin
       tsEstimateItems.TabVisible := true;
       tsDismount.TabVisible := false;
     end;

    edtCode.Text := IntToStr(ENPlanWorkObj.code);

    KindCode := ENPlanWorkObj.kind.code;

    ///
    if ENPlanWorkObj.typeRef.code = 7 then // Присоединение
      HideControls([gbPriConnection, lblPriConnectionNumber, edtPriConnectionNumber], false);

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
         edtENActNumber.Text := ENAct2ENPlanWorkList.list[0].actRefNumberGen + ' ' + ENAct2ENPlanWorkList.list[0].actRefFinMolName;
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
          , spbENAct
          ]);
      end;
     
  end;

// --------------------------------------------------------------
    if KindCode <> ENPLANWORKKIND_YEAR then
    begin

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


      if ENPlanWorkObj.dateGen <> nil then
      begin
        edtDateGen.DateTime:=EncodeDate(ENPlanWorkObj.dateGen.Year,ENPlanWorkObj.dateGen.Month,ENPlanWorkObj.dateGen.Day);
        edtDateGen.checked := true;
      end
      else
      begin
        edtDateGen.DateTime:=SysUtils.Date;
        edtDateGen.checked := false;
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
    edtCommentGen.Text := ENPlanWorkObj.commentGen;

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

         TempENElement :=  HTTPRIOENElement as ENElementControllerSoapPort;
         eList := TempENElement.getScrollableFilteredList(eFilter,0,-1);
         if eList.totalCount > 0 then
         begin
             edtENElementName.Text := eList.list[0].objectName;

             edtInvNumber.Text := eList.list[0].objectInvNumber;

             /////
             {
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
             DisableControls([edtENElementName, edtInvNumber, spbENElement, edtEPRenName]);
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
    
    edtPriConnectionNumber.Text := ENPlanWorkObj.priConnectionNumber;

    cbENPlanWorkFormName.ItemIndex := ENPlanWorkObj.formRef.code - 1;

  // test codesDown !!!!
  //TempENPlanWork := HTTPRIOENPlanWork as ENPlanWorkControllerSoapPort;
  //edtCommentGen.Text := TempENPlanWork.getPlanCodesHistoryDown(ENPlanWorkObj.code);

  end;
  // все что с актом пока спрячем ...
  // HideControls([lblENActNumber, edtENActNumber, spbENAct]);

  // Если форма вызывается по нажатию кнопки "Работы" на списке планов,
  // прячем все вкладки, кроме "Перелік робіт"
  // (хоть этой кнопкой никто и не пользуется, но лучше проверять)
  if addPlanItemsMode then
    for i := 0 to pcPlanWork.PageCount - 1 do
      if pcPlanWork.Pages[i] <> tsPlanWorkItems then
        pcPlanWork.Pages[i].TabVisible := false;

end;



procedure TfrmENPlanTransPortEdit.FormClose(Sender: TObject;  var Action: TCloseAction);
var TempENPlanWork: ENPlanWorkControllerSoapPort;
    TempENElement: ENElementControllerSoapPort;
    element : ENElement;
    //ENAct2ENPlanWorkObj : ENAct2ENPlanWork.Create;
    AllowClose: Boolean;
    TempENPlanWorkItem: ENPlanWorkItemControllerSoapPort;
    plan : ENPlanWork;
    pwiF : ENPlanWorkItemFilter;
    pwiList : ENPlanWorkItemShortList;
    err : String;
    TempENMOL2PlanWork : ENMOL2PlanWorkControllerSoapPort;
    ENMOL2PlanWorkObj: ENMOL2PlanWork;
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

    if ((DialogState = dsInsert) and (isTransport) and (not NoBlankValues([edtMolName, edtKartiName, edtCountGen])) and (KindCode = 2))  then
    begin
        Application.MessageBox(PChar('Пустые поля недопустимы !'),PChar('Внимание !'),MB_ICONWARNING+MB_OK);
        Action := caNone;
        Exit;
    end;

    if (DialogState = dsInsert) and (cbPlanWorkKind.ItemIndex + 1 = ENPLANWORKKIND_FACT) then
    begin                 
      Application.MessageBox(PChar('Завдання-Факт можливо сформувати тільки з Завдання-Плану !'),PChar('Увага !'),MB_ICONWARNING+MB_OK);
      Action := caNone;
      cbPlanWorkKind.ItemIndex := ENPLANWORKKIND_NPZ - 1;
      Exit;
    end;

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

     
     if (not NoBlankValues([edtPriConnectionNumber])) and (ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_CN) then // Приєднання
     begin
       Application.MessageBox(PChar('Пустые поля недопустимы !'),PChar('Внимание !'),MB_ICONWARNING+MB_OK);
       Action:=caNone;
       Exit;
     end;

     ENPlanWorkObj.priConnectionNumber := edtPriConnectionNumber.Text;

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

    TempENElement := HTTPRIOENElement as ENElementControllerSoapPort;
    element := TempENElement.getObject(ENPlanWorkObj.elementRef.code);


    if DialogState = dsInsert then
    begin
      ENPlanWorkObj.code:=low(Integer);

      case element.typeRef.code of
        EN_TRANSPORT: ENPlanWorkObj.code := TempENPlanWork.addByTrucking(ENPlanWorkObj);
        else
       begin
          Application.MessageBox(PChar('Невідомий тип Об''єкту !!!'), PChar('Помилка'), MB_ICONERROR);
          exit;
        end;
      end;


        ///////////////////////////////

      if frmENPlanTransPortEdit.molCode <> '' then
      begin
        TempENMOL2PlanWork :=  HTTPRIOENMOL2PlanWork as ENMOL2PlanWorkControllerSoapPort;

        ENMOL2PlanWorkObj := ENMOL2PlanWork.Create();
        ENMOL2PlanWorkObj.code := LOW_INT;
        ENMOL2PlanWorkObj.planRef := ENPlanWorkRef.Create();
        ENMOL2PlanWorkObj.planRef.code := ENPlanWorkObj.code;
        ENMOL2PlanWorkObj.molCode := frmENPlanTransPortEdit.molCode;
        ENMOL2PlanWorkObj.molName := frmENPlanTransPortEdit.molName;

        TempENMOL2PlanWork.add(ENMOL2PlanWorkObj);
      end;

      TempENPlanWorkItem := HTTPRIOENPlanWorkItem as ENPlanWorkItemControllerSoapPort;

      if (ENPlanWorkItemObj.countGen = nil ) then
        ENPlanWorkItemObj.countGen := TXSDecimal.Create;
      if edtCountGen.Text <> '' then
        ENPlanWorkItemObj.countGen.decimalString := edtCountGen.Text
      else
        ENPlanWorkItemObj.countGen := nil;

      ENPlanWorkItemObj.commentGen := edtCommentGen.Text;

      ENPlanWorkItemObj.planRef := ENPlanWorkRef.Create;
      ENPlanWorkItemObj.planRef.code := ENPlanWorkObj.code;

      if DialogState = dsInsert then
      begin

        if ENPlanWorkItemObj.countGen <> nil then
          try
            if StrToFloat(ENPlanWorkItemObj.countGen.DecimalString) < 0.000001 then
            begin
              Application.MessageBox(PChar('Додавати роботу з нульовою кількістю неможна!'), PChar('Увага!'), MB_ICONWARNING);
              ModalResult := mrNone;
              Exit;
            end;
          except
            raise;
          end;

        // перевозка админ.персонала
        if ENPlanWorkItemObj.kartaRef.code = ADMIN_TRUCKING then
          try
            if ((ENPlanWorkObj.departmentRef.code <> ENDEPARTMENT_CO)
                  and (ENPlanWorkObj.departmentRef.code <> ENDEPARTMENT_KSOE)) then
            begin
              TempENPlanWork.remove(ENPlanWorkObj.code);
              Application.MessageBox(PChar('Ця робота тільки для апарату управління!'), PChar('Увага!'), MB_ICONWARNING);
              ModalResult := mrNone;
              Exit;
            end;
          except
            raise;
          end;


        ENPlanWorkItemObj.code:=low(Integer);
        itemCode := TempENPlanWorkItem.add(ENPlanWorkItemObj);
      end;
        ///////////////////////////////

    end
    else
    if DialogState = dsEdit then
    begin
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

procedure TfrmENPlanTransPortEdit.spbENPlanWorkStatusClick(Sender : TObject);
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



procedure TfrmENPlanTransPortEdit.spbENElementClick(Sender: TObject);
var
   frmENElementShow: TfrmENElementShow;
   f, tmpF : ENElementFilter;
   invNum , depName: String;
   depCode, elCode : Integer;
   depShort : ENDepartmentShort;
   //o : EN

   elList: ENElementShortList;
   elObj: ENElementShort;
   isMetrologyObject: Boolean;
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

      with frmENElementShow do
        if ShowModal = mrOk then
        begin

            try
               //enObj := DMReports.getIByElement(StrToInt(GetReturnValue(sgENElement,0)));

               isMetrologyObject := false;
               
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
                       if elList.list[0].typeRefCode = EN_METROLOGY_OBJECT then
                         isMetrologyObject := true;
               except
               end;

               // инвентарные для Центра ответсвенности 

               invNum := GetReturnValue(sgENElement,3) ; //DMReports.getInvNumByElement(StrToInt(GetReturnValue(sgENElement,0)));

               if (length(invNum) < 5) and (not isMetrologyObject) then
               begin
                   if ENPlanWorkObj.typeRef <> nil then
                   begin
                     if not (ENPlanWorkObj.typeRef.code in [5,7]) then // Приєднання, Виконання інвестиційної програми (капітальне будівництво)
                     begin
                       Application.MessageBox(PChar('Плани можливо заносити тільки для об"єктів з інвентарним номером !!!' + ' "' + invNum +'" < 5 символов'), PChar('Ошибка'), MB_ICONERROR);
                       exit;
                     end;
                   end
                   else
                   begin
                       Application.MessageBox(PChar('Плани можливо заносити тільки для об"єктів з інвентарним номером !!!' + ' "' + invNum +'" < 5 символов'), PChar('Ошибка'), MB_ICONERROR);
                       exit;
                   end;
               end;



               if ENPlanWorkObj.elementRef = nil then ENPlanWorkObj.elementRef := ENElementRef.Create();
              // ENPlanWorkObj.status.code := StrToInt(GetReturnValue(sgENPlanWorkStatus,0));
               ENPlanWorkObj.elementRef.code := StrToInt(GetReturnValue(sgENElement,0));
               edtENElementName.Text := GetReturnValue(sgENElement,1);
               edtInvNumber.Text := GetReturnValue(sgENElement,3);

               if  ENPlanWorkObj.renRef = nil then ENPlanWorkObj.renRef := EPRenRef.Create;
               ENPlanWorkObj.renRef.code := ENElementShort(sgENElement.Objects[0,sgENElement.Row]).renRefCode ;
               edtEPRenName.Text := GetReturnValue(sgENElement,2);

               // подкинуть депртамент ...
              depShort := DMReports.getDepartmentByRenCode(ENPlanWorkObj.renRef.code, ENMANAGEMENT_TYPE_TECHNICAL);
              if depShort <> nil then
              begin
                  if ENPlanWorkObj.departmentRef = nil then  ENPlanWorkObj.departmentRef := ENDepartmentRef.Create;
                  ENPlanWorkObj.departmentRef.code := depShort.code;
                  edtDepartment.Text:= depShort.shortName;
              end;

              if (not isTransport) then
               begin
                 DisableControls([spbType , spbENPlanWorkState], false);
                 DisableControls([spbENElement]);
               end;  

            except
               on EConvertError do Exit;
            end;
        end;
   finally
      frmENElementShow.Free;
   end;
end;

procedure TfrmENPlanTransPortEdit.updateFINMaterialsGrid(estimateItemCode : Integer; stringGrid : TAdvStringGrid );
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



procedure TfrmENPlanTransPortEdit.pcPlanWorkChange(Sender: TObject);
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

  j : Integer;

  vNormOfTime, vCountGen: Double;

{  TempENWorkOrder2ENPlanWork: ENWorkOrder2ENPlanWorkControllerSoapPort;
  //i: Integer;
  ENWorkOrder2ENPlanWorkList: ENWorkOrder2ENPlanWorkShortList;
  ENWorkOrder2ENPlanWorkFilterObj : ENWorkOrder2ENPlanWorkFilter;

  frmENWorkOrderEdit : TfrmENWorkOrderEdit;
  TempENWorkOrder : ENWorkOrderControllerSoapPort; }

begin

  //DisableActions([actInsert,  actEdit, actDelete, actFilter, actNoFilter], false);

  if pcPlanWork.ActivePage = tsDismount then
  begin
    DisableActions([actInsert,  actEdit, actDelete, actFilter, actNoFilter], not (DialogState in [dsEdit, dsInsert]) );


    SetGridHeaders(ENDismountItemHeaders, sgDismount.ColumnHeaders);

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

         eiLastRow := i + 1;
         sgDismount.RowCount := eiLastRow + 1;
       end;

     //sgENEstimateItem.RowColor[1] := clGreen;

     eiColCount := eiColCount + 1;
     sgDismount.Row := 1;



  end; // selected tsDismountItems


  if pcPlanWork.ActivePage = tsWorkOrder then
  begin
    DisableControls([edtWorkOrderNumber, edtDateWorkOrder, edtWorkOrderCommentGen,
                     edtFinMolCode, edtFinMolName, edtFinMechanicCode, edtFinMechanicName
                     {, gbMOLData}]);
    DisableActions([actMOLDataInsert, actMOLDataEdit, actMOLDataDelete]);
    LoadWorkOrder;
  end
  else
  if pcPlanWork.ActivePage = tsPlanWorkItems then
  begin
    DisableActions([actInsert,  actEdit, actDelete, actFilter, actNoFilter], not (DialogState in [dsEdit, dsInsert]) );

    SetGridHeaders(ENPlanWorkItemHeaders, sgENPlanWorkItem.ColumnHeaders);
    iColCount:=-1;
    TempENPlanWorkItem :=  HTTPRIOENPlanWorkItem as ENPlanWorkItemControllerSoapPort;

    if planItemFilter = nil then
    begin
       planItemFilter := ENPlanWorkItemFilter.Create;
       SetNullIntProps(planItemFilter);
       SetNullXSProps(planItemFilter);
    end;

    if planItemFilter.planRef = nil then planItemFilter.planRef := ENPlanWorkRef.Create;
    planItemFilter.planRef.code := ENPlanWorkObj.code;

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

          if ENPlanWorkItemList.list[i].timeGen <> nil then
            Cells[7, i +1] := ENPlanWorkItemList.list[i].timeGen.DecimalString
          else
            Cells[7, i+1] := '';

          Cells[8, i+1] := ENPlanWorkItemList.list[i].meter;
          Cells[9, i+1] := ENPlanWorkItemList.list[i].measurementUnit;

          Cells[10,i+1] := ENPlanWorkItemList.list[i].userGen;

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

  //--------------------------------------------------------------------------------
  if pcPlanWork.ActivePage = tsEstimateItems then
  begin

    DisableActions([actInsert,  actEdit, actDelete, actFilter, actNoFilter], not (DialogState in [dsEdit, dsInsert]) );

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

    SetGridHeaders(ENEstimateItemHeaders, sgENEstimateItem.ColumnHeaders);
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
         end;

         Objects[0,i+1] := TObject(ENEstimateItemList.list[i].typeRefCode);

         Objects[1,i+1] := TObject(ENEstimateItemList.list[i].statusRefCode);

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
            HideControls([gbFINMaterials, gbMarkers]);
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
        else
          gbFINMaterials.Visible := false;

        gbMarkers.Visible := true;
        updateMarksGrid(j, sgMarkers);
    end
    else
    begin
       HideControls([gbFINMaterials, gbMarkers]);
       Panel1.Align := alClient;
    end;


  end;


  //--------------------------------------------------------------------------------
  if pcPlanWork.ActivePage = tsGSM then
  begin
    DisableActions([  actEdit], not (DialogState in [dsEdit, dsInsert]) );
    DisableActions([actInsert, actDelete , actFilter, actNoFilter]);
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
                          'select enplanworkitem.code from enplanworkitem where enplanworkitem.planrefcode ='+ IntToStr(ENPlanWorkObj.code) +
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

// -----------------------------------------------------------------------------------------------------

  if pcPlanWork.ActivePage = tsTransports then
  begin
     DisableActions([actInsert,  actEdit, actDelete, actFilter, actNoFilter]);

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

  transportItemFilter.orderBySQL := ' entransportitem.planitemrefcode';

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

        Cells[10,i+1] := ENTransportItemList.list[i].userGen;

        if ENTransportItemList.list[i].dateEdit = nil then
          Cells[11,i+1] := ''
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
   
  //end; //pcPlanWork.ActivePage = tsCorrections

end;

procedure TfrmENPlanTransPortEdit.actInsertExecute(Sender: TObject);
Var TempENPlanWorkItem: ENPlanWorkItemControllerSoapPort;
    TempENEstimateItem: ENEstimateItemControllerSoapPort;
begin

  //if ENPlanWorkObj.status.code <> ENPLANWORKSTATUS_GOOD then
  if not (ENPlanWorkObj.status.code in [ENPLANWORKSTATUS_GOOD, ENPLANWORKSTATUS_INCORRECTION]) then
  begin
      Application.MessageBox(PChar('План затверджений або видалений !'), PChar('Увага'), MB_ICONWARNING);
      exit;
  end;

  if pcPlanWork.ActivePage = tsPlanWorkItems then
  begin

    selectedRow := sgENPlanWorkItem.Row;

    TempENPlanWorkItem := HTTPRIOENPlanWorkItem as ENPlanWorkItemControllerSoapPort;
    ENPlanWorkItemObj:=ENPlanWorkItem.Create;
           SetNullIntProps(ENPlanWorkItemObj);
           SetNullXSProps(ENPlanWorkItemObj);

     ENPlanWorkItemObj.countGen:= TXSDecimal.Create;
     ENPlanWorkItemObj.dateEdit:= TXSDate.Create;
     ENPlanWorkItemObj.planRef := ENPlanWorkRef.Create;

    try
      frmENPlanWorkItemEdit:=TfrmENPlanWorkItemEdit.Create(Application, dsInsert);

      if isTransport then frmENPlanWorkItemEdit.isTransport := true;

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
  
  if pcPlanWork.ActivePage = tsEstimateItems then
  begin
    TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;
    ENEstimateItemObj := ENEstimateItem.Create;

    ENEstimateItemObj.countGen := TXSDecimal.Create;
    ENEstimateItemObj.countFact := TXSDecimal.Create;
    ENEstimateItemObj.dateEdit := TXSDate.Create;

    ENEstimateItemObj.planRef := ENPlanWorkRef.Create;
    ENEstimateItemObj.planRef.code := ENPlanWorkObj.code;

    try
      frmENEstimateItemEdit := TfrmENEstimateItemEdit.Create(Application, dsInsert);
      frmENEstimateItemEdit.ENPlanWorkCode := ENPlanWorkObj.code;
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
end;

procedure TfrmENPlanTransPortEdit.UpdateGrid(Sender: TObject);
Var i, j: Integer;
begin
 if pcPlanWork.ActivePage = tsPlanWorkItems then
 begin
   for i:=1 to sgENPlanWorkItem.RowCount-1 do
     for j:=0 to sgENPlanWorkItem.ColCount-1 do
       sgENPlanWorkItem.Cells[j,i]:='';
 end;

 if pcPlanWork.ActivePage = tsEstimateItems then
 begin
   for i:=1 to sgENEstimateItem.RowCount-1 do
     for j:=0 to sgENEstimateItem.ColCount-1 do
       sgENEstimateItem.Cells[j,i]:='';
 end;

 if pcPlanWork.ActivePage = tsGSM then
 begin
   for i:=1 to sgGSM.RowCount-1 do
     for j:=0 to sgGSM.ColCount-1 do
       sgGSM.Cells[j,i]:='';
 end;

 if pcPlanWork.ActivePage = tsDismount then
 begin
   for i:=1 to sgDismount.RowCount-1 do
     for j:=0 to sgDismount.ColCount-1 do
       sgDismount.Cells[j,i]:='';
 end;

 if pcPlanWork.ActivePage = tsMoves then
 begin
   for i:=1 to sgENPlanWorkMoveHistory.RowCount-1 do
     for j:=0 to sgENPlanWorkMoveHistory.ColCount-1 do
       sgENPlanWorkMoveHistory.Cells[j,i]:='';
 end;

 if pcPlanWork.ActivePage = tsCorrections then
 begin
   for i:=1 to sgENPlanCorrectHistory.RowCount-1 do
     for j:=0 to sgENPlanCorrectHistory.ColCount-1 do
       sgENPlanCorrectHistory.Cells[j,i]:='';
 end;

 if pcPlanWork.ActivePage = tsHumens then
 begin
   for i:=1 to sgENHumenItem.RowCount-1 do
     for j:=0 to sgENHumenItem.ColCount-1 do
       sgENHumenItem.Cells[j,i]:='';
 end;

 if pcPlanWork.ActivePage = tsTransports then
 begin
   for i:=1 to sgENTransportItem.RowCount-1 do
     for j:=0 to sgENTransportItem.ColCount-1 do
       sgENTransportItem.Cells[j,i]:='';
 end;

 pcPlanWorkChange(Sender);
end;

procedure TfrmENPlanTransPortEdit.actEditExecute(Sender: TObject);
Var TempENPlanWorkItem: ENPlanWorkItemControllerSoapPort;
    TempENEstimateItem: ENEstimateItemControllerSoapPort;
begin
  //if ENPlanWorkObj.status.code <> ENPLANWORKSTATUS_GOOD then
  if not (ENPlanWorkObj.status.code in [ENPLANWORKSTATUS_GOOD, ENPLANWORKSTATUS_INCORRECTION]) then
  begin
      Application.MessageBox(PChar('План затверджений або видалений !'), PChar('Увага'), MB_ICONWARNING);
      exit;
  end;

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

    if isTransport then frmENPlanWorkItemEdit.isTransport := true;

    try
      //frmENPlanWorkItemEdit.DisableControls([frmENLine04Edit.edtENSubstation04Name, frmENLine04Edit.spbENSubstation04]);
      frmENPlanWorkItemEdit.lblMeasure.Caption := 'Вимірювач : ' + sgENPlanWorkItem.Cells[8,sgENPlanWorkItem.Row] + ' /  Од.виміру : ' + sgENPlanWorkItem.Cells[9,sgENPlanWorkItem.Row];
      
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

  if (pcPlanWork.ActivePage = tsEstimateItems) then
  begin
    TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;
    try
      ENEstimateItemObj := TempENEstimateItem.getObject(StrToInt(sgENEstimateItem.Cells[0,sgENEstimateItem.Row]));
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


///--------------------------------------------------------------------------------

  if (pcPlanWork.ActivePage = tsGSM) then
  begin
    TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;
    try
      ENEstimateItemObj := TempENEstimateItem.getObject(StrToInt(sgGSM.Cells[0,sgGSM.Row]));
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


end;

procedure TfrmENPlanTransPortEdit.actViewExecute(Sender: TObject);
Var TempENPlanWorkItem: ENPlanWorkItemControllerSoapPort;
    TempENEstimateItem: ENEstimateItemControllerSoapPort;
    TempENTransportItem: ENTransportItemControllerSoapPort;
    TempENHumenItem: ENHumenItemControllerSoapPort;
begin
  if pcPlanWork.ActivePage = tsPlanWorkItems then
  begin

    TempENPlanWorkItem := HTTPRIOENPlanWorkItem as ENPlanWorkItemControllerSoapPort;
    try
      ENPlanWorkItemObj := TempENPlanWorkItem.getObject(StrToInt(sgENPlanWorkItem.Cells[0,sgENPlanWorkItem.Row]));
    except
      on EConvertError do Exit;
    end;

    frmENPlanWorkItemEdit:=TfrmENPlanWorkItemEdit.Create(Application, dsView);
    try
      //frmENPlanWorkItemEdit.DisableControls([frmENLine04Edit.edtENSubstation04Name, frmENLine04Edit.spbENSubstation04]);
      frmENPlanWorkItemEdit.lblMeasure.Caption := 'Вимірювач : ' + sgENPlanWorkItem.Cells[8,sgENPlanWorkItem.Row] + ' /  Од.виміру : ' + sgENPlanWorkItem.Cells[9,sgENPlanWorkItem.Row];
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

    TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;
    try
      ENEstimateItemObj := TempENEstimateItem.getObject(StrToInt(sgENEstimateItem.Cells[0, sgENEstimateItem.Row]));

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


  if (pcPlanWork.ActivePage = tsGSM)  then
  begin

    TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;
    try
      ENEstimateItemObj := TempENEstimateItem.getObject(StrToInt(sgGSM.Cells[0, sgGSM.Row]));

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

end;

procedure TfrmENPlanTransPortEdit.actUpdateExecute(Sender: TObject);
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

procedure TfrmENPlanTransPortEdit.btnPlanReportClick(Sender: TObject);
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



procedure TfrmENPlanTransPortEdit.actDeleteExecute(Sender: TObject);
Var TempENPlanWorkItem: ENPlanWorkItemControllerSoapPort;
  ObjCode: Integer;
  TempENEstimateItem: ENEstimateItemControllerSoapPort;
  eType : integer;
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
  if pcPlanWork.ActivePage = tsEstimateItems then
  begin
     if  Integer(sgENEstimateItem.Objects[0,sgENEstimateItem.Row]) in [ENESTIMATEITEMTYPE_MANUAL_BY_PLANITEM ] then //такие удалять из РАБОТЫ .., ENESTIMATEITEMTYPE_MANUAL_BY_PLANITEM]  then
     begin
       Application.MessageBox(PChar('Цей матеріaл видаляється в роботі !!!'), PChar('Увага !'),MB_ICONQUESTION+MB_OK);
       exit;
     end;

     if  Integer(sgENEstimateItem.Objects[0,sgENEstimateItem.Row]) in [ENESTIMATEITEMTYPE_MANUAL_BY_PLAN ] then //такие удалять из РАБОТЫ .., ENESTIMATEITEMTYPE_MANUAL_BY_PLANITEM]  then
     begin
       TempENEstimateItem := HTTPRIOENEstimateItem as ENEstimateItemControllerSoapPort;
         try
           ObjCode := StrToInt(sgENEstimateItem.Cells[0,sgENEstimateItem.Row]);
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
          TempENEstimateItem.remove(ObjCode);
          UpdateGrid(Sender);
     end;

  end; //pcPlanWork.ActivePage = tsDismount
end;

procedure TfrmENPlanTransPortEdit.sgENPlanCorrectHistoryDblClick(
  Sender: TObject);
  var
  frmViewOldPlan : TfrmENPlanTransPortEdit;
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

   frmViewOldPlan := TfrmENPlanTransPortEdit.Create(Application, dsView);

  try

    TempENPlanCorrectHistory  := HTTPRIOENPlanCorrectHistory as ENPlanCorrectHistoryControllerSoapPort;

    planCorrectHistory := TempENPlanCorrectHistory.getObject(ObjCode);

    TempENPlanWork  := HTTPRIOENPlanWork as ENPlanWorkControllerSoapPort;

    frmViewOldPlan.ENPlanWorkObj := ENPlanWork.Create;
    frmViewOldPlan.ENPlanWorkObj :=  TempENPlanWork.getObject(planCorrectHistory.planOldRef.code);

   // frmViewOldPlan.DisableActions([frmViewOldPlan.actInsert, frmViewOldPlan.actEdit, frmViewOldPlan.actDelete]);
    frmViewOldPlan.tsCorrections.TabVisible := false;
    frmViewOldPlan.Caption := 'Змінений план при коригуванні';
    frmViewOldPlan.ShowModal;
  finally
    frmViewOldPlan.Free;
    frmViewOldPlan:=nil;
  end;

end;

procedure TfrmENPlanTransPortEdit.spbENBudgetClick(Sender: TObject);
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
            except
               on EConvertError do Exit;
            end;
        end;
      end;
   finally
      frmENDepartmentShow.Free;
   end;
end;

procedure TfrmENPlanTransPortEdit.spbResponsibilityClick(Sender: TObject);
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

procedure TfrmENPlanTransPortEdit.spbDepartmentClick(Sender: TObject);
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

procedure TfrmENPlanTransPortEdit.spbTypeClick(Sender: TObject);
var
   frmENPlanWorkTypeShow: TfrmENPlanWorkTypeShow;
   f : ENPlanWorkTypeFilter;
begin
   f:= ENPlanWorkTypeFilter.Create;
   SetNullIntProps(f);
   SetNullXSProps(f);
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

               edtWorkState.Text := '';
               ENPlanWorkObj.stateRef := nil;

               //if ENPlanWorkObj.typeRef.code = ENPLANWORKTYPE_CN then // Присоединение
               //begin
                 HideControls([ gbPriConnection, lblPriConnectionNumber, edtPriConnectionNumber], ENPlanWorkObj.typeRef.code <> ENPLANWORKTYPE_CN);
                 DenyBlankValues([edtPriConnectionNumber]);
               //end

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


procedure TfrmENPlanTransPortEdit.edtDateStartClick(Sender: TObject);
begin
//if edtDateStart.Checked then
//begin

  edtDateStart.DateTime := EncodeDate(edtYearGen.ItemIndex + 2009, edtMonthGen.ItemIndex + 1, 1);

  if KindCode in [ENPLANWORKKIND_NPZ, ENPLANWORKKIND_FACT] then
    edtDateFinal.DateTime := edtDateStart.DateTime
  else
    edtDateFinal.DateTime := EncodeDate(edtYearGen.ItemIndex + 2009, edtMonthGen.ItemIndex + 1, DaysInMonth(edtDateStart.DateTime));

//end;

end;


procedure TfrmENPlanTransPortEdit.actMaterialBindingExecute(Sender: TObject);
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

procedure TfrmENPlanTransPortEdit.PopupMenu1Popup(Sender: TObject);
begin
  actMaterialBinding.Enabled := (DialogState = dsEdit) and
                                (pcPlanWork.ActivePage = tsEstimateItems) and
                                (Integer(sgENEstimateItem.Objects[0, sgENEstimateItem.Row]) = ENESTIMATEITEMTYPE_MANUAL_BY_PLAN);

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

  if pcPlanWork.ActivePage = tsEstimateItems then
  begin
    actChangeEstimateItemStatus.Enabled := (ENPlanWorkObj.kind.code = ENPLANWORKKIND_CURRENT) ;
    // может для тех что в наличии
    actMoveTO.Enabled := (ENPlanWorkObj.kind.code = ENPLANWORKKIND_CURRENT);
    actMoveFROM.Enabled := (ENPlanWorkObj.kind.code = ENPLANWORKKIND_CURRENT);


{
                                            and (
       (Integer(sgENEstimateItem.Objects[1, sgENEstimateItem.Row]) = ENESTIMATEITEMSTATUS_PLANED)
       or (Integer(sgENEstimateItem.Objects[1, sgENEstimateItem.Row]) = ENESTIMATEITEMSTATUS_TMP)
       or  (Integer(sgENEstimateItem.Objects[1, sgENEstimateItem.Row]) = ENESTIMATEITEMSTATUS_INSKLAD)
                                            )
}
                                            ;
    {
    if Integer(sgENEstimateItem.Objects[1, sgENEstimateItem.Row]) = ENESTIMATEITEMSTATUS_INSKLAD  then
       actChangeEstimateItemStatus.Caption := 'Змінити статус на "Запланований"';

    if (
          (Integer(sgENEstimateItem.Objects[1, sgENEstimateItem.Row]) = ENESTIMATEITEMSTATUS_PLANED)
       or (Integer(sgENEstimateItem.Objects[1, sgENEstimateItem.Row]) = ENESTIMATEITEMSTATUS_TMP)
    )  then
       actChangeEstimateItemStatus.Caption := 'Змінити статус на "На Складі"';
}

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

end;

procedure TfrmENPlanTransPortEdit.edtDateStartChange(Sender: TObject);
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

procedure TfrmENPlanTransPortEdit.spbENPlanWorkStateClick(Sender: TObject);
var
   frmENPlanWorkStateShow: TfrmENPlanWorkStateShow;
   f : ENPlanWorkStateFilter;
   e : ENElement;
begin
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
          (e.typeRef.code <> EN_BUILDER) and
          (e.typeRef.code <> EN_METROLOGY_COUNTER) and
          (e.typeRef.code <> EN_METROLOGY_DEVICE) and
          (e.typeRef.code <> EN_METROLOGY_OBJECT) and
          (e.typeRef.code <> EN_BYT) and
          (e.typeRef.code <> EN_TRANSPORT) and
          (e.typeRef.code <> EN_SIT) and
          (e.typeRef.code <> EN_PURCHASES_OBJECT) and
          (e.typeRef.code <> EN_PURCHASES_NO_OBJECT) then
         f.conditionSQL := f.conditionSQL + ' and enplanworkstate.code <> ' + IntToStr(ENPLANWORKSTATE_CURRENTREPAIR);
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
            except
               on EConvertError do Exit;
            end;
        end;
      end;
   finally
      frmENPlanWorkStateShow.Free;
   end;
end;

procedure TfrmENPlanTransPortEdit.spbENActClick(Sender: TObject);
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
               ShowMessage('Tak delat nelZy');
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

procedure TfrmENPlanTransPortEdit.sgENEstimateItemClick(Sender: TObject);
var
  j : Integer;
begin
     // выведем список ФИН материалов .... если они есть ВААЩЕ ...
    try
      j := StrToInt( TAdvStringGrid(Sender).Cells[0, TAdvStringGrid(Sender).Row ]); //   (sgENEstimateItem,0));
    except
      on EConvertError do Exit;
    end;

  if Sender = sgENEstimateItem then
  begin
    updateFINMaterialsGrid(j, sgFINMaterials);
    updateMarksGrid(j, sgMarkers);
  end
  else
  if Sender = sgGSM then
  begin
    updateFINMaterialsGrid(j, sgFINGSM);
    updateMarksGrid(j, sgMarkers);
  end;


end;

procedure TfrmENPlanTransPortEdit.actMaterialBindingToFINExecute(
  Sender: TObject);
var
//   frmFINMaterialsDataEdit : TfrmFINMaterialsDataEdit;
   temp , molTypeCode : Integer;
   temp2 : real;
begin
{
  if actCode < 0 then
  begin
    Application.MessageBox(PChar('Выберите АКТ!'), PChar('Внимание!'), MB_ICONWARNING);
    pcPlanWork.ActivePage := tsPlanWork;
    spbENAct.Click;
    Exit;
  end;
}



  workOrder := DMReports.getWorkOrderByPlanCode(ENPlanWorkObj.code);

  if workOrder.code = LOW_INT then
  begin
    Application.MessageBox(PChar('Введите НАРЯД ! Ничего НЕ сохранится !!!'), PChar('Внимание!'), MB_ICONWARNING);
    pcPlanWork.ActivePage := tsWorkOrder;
    pcPlanWorkChange(Sender);
    Exit;
  end;


      if pcPlanWork.ActivePage = tsEstimateItems then
        molTypeCode := 1; //   (sgENEstimateItem,0));

      if pcPlanWork.ActivePage = tsGSM then
        molTypeCode := 2;  //   (sgENEstimateItem,0));

  molData := DMReports.getMOLData(workOrder.code, molTypeCode);
  if (molData = nil) then
  begin
    Application.MessageBox(PChar('Введите МОЛов !!! Ничего НЕ сохранится !!!'), PChar('Внимание!'), MB_ICONWARNING);
    pcPlanWork.ActivePage := tsWorkOrder;
    pcPlanWorkChange(Sender);
    Exit;
  end;

    
    temp2 := 0;
    try
      if pcPlanWork.ActivePage = tsEstimateItems then
        temp2 := StrToFloat( sgENEstimateItem.Cells[3, sgENEstimateItem.Row ]); //   (sgENEstimateItem,0));

      if pcPlanWork.ActivePage = tsGSM then
        temp2 := StrToFloat( sgGSM.Cells[3, sgGSM.Row ]); //   (sgENEstimateItem,0));

    except
      on EConvertError do Exit;
    end;


  if temp2 < 0.00000001 then
  begin
    Application.MessageBox(PChar('Кол-во материалов = 0 :) ... Откорректируйте кол-во материалов в работе'), PChar('Внимание!'), MB_ICONWARNING);
    Exit;
  end;

   frmFINMaterialsDataEdit:= TfrmFINMaterialsDataEdit.Create(Application,dsInsert);
   try
      frmFINMaterialsDataEdit.planCode := ENPlanWorkObj.code; // ENEstimateItemObj.planRef.code;

     // выведем список ФИН материалов .... если они есть ВААЩЕ ...
    try
      if pcPlanWork.ActivePage = tsEstimateItems then
      begin
        temp := StrToInt( sgENEstimateItem.Cells[0, sgENEstimateItem.Row ]); //   (sgENEstimateItem,0));
        frmFINMaterialsDataEdit.estimateItemKind := ENESTIMATEITEMKIND_MATERIALS;
      end;
      if pcPlanWork.ActivePage = tsGSM then
      begin
        temp := StrToInt( sgGSM.Cells[0, sgGSM.Row ]);
        frmFINMaterialsDataEdit.estimateItemKind := ENESTIMATEITEMKIND_GSM;
      end;
    except
      on EConvertError do Exit;
    end;

      frmFINMaterialsDataEdit.estimateCode := temp; //ENEstimateItemObj.code;


      {
      frmFINMaterialsDataEdit.edtTKMaterial.Text := edtMaterialName.Text ;

      frmFINMaterialsDataEdit.edtTKCount.Text := ENEstimateItemObj.countGen.DecimalString ;
      }

      with frmFINMaterialsDataEdit do
      begin
        ShowModal ;// = mrOk then
        //begin
        {
            try
            except
               on EConvertError do Exit;
            end;
         }
            //pcPlanWorkChange(Sender);

           { уехало в АпдейтГрид ..
          if checkMaterialsBinding(temp) then
          begin
            sgENEstimateItem.RowColor[sgENEstimateItem.Row] := Shape1.Brush.Color;
            sgENEstimateItem.Objects[1, sgENEstimateItem.Row] := TObject(1); // признак (разнесенный)
          end
          else begin
            sgENEstimateItem.RowColor[sgENEstimateItem.Row] := clWindow;
            sgENEstimateItem.Objects[1, sgENEstimateItem.Row] := TObject(0);
          end;
           }
          //frmENPlanTransPortEdit.updateFINMaterialsGrid(temp);
          self.UpdateGrid(sender);
        end;
   finally
      frmFINMaterialsDataEdit.Free;
   end;

end;


procedure TfrmENPlanTransPortEdit.cbPlanWorkKindChange(Sender: TObject);
var ENMOL2PlanWorkObj: ENMOL2PlanWork;
    TempENMOL2PlanWork: ENMOL2PlanWorkControllerSoapPort;
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

    DisableControls([edtDateFinal], KindCode in [ENPLANWORKKIND_NPZ, ENPLANWORKKIND_FACT]);
  end;


  // NET-3368... Служебная. 16.10.2012. СТ Вишневский. надати можливість формувати річні плани на перевезення
  if ((isTransport) and (KindCode in [ENPLANWORKKIND_CURRENT, ENPLANWORKKIND_YEAR])) then
  begin
    gbPlanMOL.Visible := true;
    gbPlanWorkItem.Visible := true;
    DisableControls([edtKartiNum]);
    DisableControls([edtMolName, spbPlanMOL, spbPlanMOLClear, edtMolName, edtKartiName, edtCountGen], false);
    DenyBlankValues([edtMolName, edtKartiName, edtCountGen]);
    NoBlankValues([edtMolName, edtKartiName, edtCountGen]);

    {ENMOL2PlanWorkObj := ENMOL2PlanWork.Create();
    ENMOL2PlanWorkObj.code := LOW_INT;
    ENMOL2PlanWorkObj.planRef := ENPlanWorkRef.Create();
    ENMOL2PlanWorkObj.planRef.code := ENPlanWorkObj.code;

    ENMOL2PlanWorkObj.molCode := '7020';
    edtMolName.Text := 'Курган А.А. мастер';
    molCode := ENMOL2PlanWorkObj.molCode;
    molName := edtMolName.Text;}
  end;


  {
  if KindCode = ENPLANWORKKIND_FACT then
  begin
    //tsPlanWork.Enabled := false;

    DisableControls(tsPlanWork);
    DisableControls([edtDateStart, spbENAct, gbWorkOrder, edtCommentGen, edtFINExecutorName, spbFINExecutor], false);

  end;
  }
end;

procedure TfrmENPlanTransPortEdit.spbFINExecutorClick(Sender: TObject);
var
   frmFINExecutorTreeShow: TfrmFINExecutorTreeShow;
 //  f : EditENPlanTransPortStateFilter;
begin
   frmFINExecutorTreeShow:=TfrmFINExecutorTreeShow.Create(Application,fmNormal);
   try
      with frmFINExecutorTreeShow do begin
        DisableActions([ actEdit, actInsert ]);
        if ShowModal = mrOk then
        begin
            try
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


function TfrmENPlanTransPortEdit.checkMaterialsBinding_(
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

procedure TfrmENPlanTransPortEdit.Shape1MouseDown(Sender: TObject;
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

procedure TfrmENPlanTransPortEdit.btnCloseAllClick(Sender: TObject);
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
begin
{
  finMatFilter := FINMaterialsFilter.Create;
  SetNullIntProps(finMatFilter);
  SetNullXSProps(finMatFilter);
  finMatFilter.conditionSQL := 'code in (' +

' 1017000345,  ' +
' 1017000341,  ' +
' 1017000349,  ' +
' 1017000120,  ' +
' 500001809,   ' +
' 1017000101,  ' +
' 500001810,   ' +
' 1017000121,  ' +
' 1017000129,  ' +
' 1017000130,  ' +
' 1017000131,  ' +
' 1017000103,  ' +
' 1017000114,  ' +
' 1017000107,  ' +
' 1017000109,  ' +
' 1017000105,  ' +
' 1017000116,  ' +
' 1017000111,  ' +
' 500001808,   ' +
' 1017000112,  ' +
' 500001987,   ' +
' 500001811,   ' +
' 1017000134,  ' +
' 1017000124,  ' +
' 1017000144,  ' +
' 1017000123,  ' +
' 1017000127,  ' +
' 1017000126,  ' +
' 1017000136,  ' +
' 1017000128,  ' +
' 1017000138,  ' +
' 500001902,   ' +
' 1017000350,  ' +
' 500001907,   ' +
' 500001913,   ' +
' 500001903,   ' +
' 1000000177,  ' +
' 1017000183,  ' +
' 1017000143,  ' +
' 1017000139,  ' +
' 500002001,   ' +
' 1017000184,  ' +
' 500002625,   ' +
' 1017000145,  ' +
' 1017000185,  ' +
' 1017000355,  ' +
' 500002033,   ' +
' 1017000151,  ' +
' 500002058,   ' +
' 1017000150,  ' +
' 1017000153,  ' +
' 1017000149,  ' +
' 1017000152,  ' +
' 500002059,   ' +
' 1017000158,  ' +
' 1017000160,  ' +
' 1017000159,  ' +
' 1017000356,  ' +
' 500002084,   ' +
' 500002664,   ' +
' 500002083,   ' +
' 500002086,   ' +
' 500002087,   ' +
' 1017000165,  ' +
' 1017000170,  ' +
' 1017000381,  ' +
' 1017000171,  ' +
' 500002102,   ' +
' 500002103,   ' +
' 500002104,   ' +
' 1017000357,  ' +
' 1017000382,  ' +
' 500002109,   ' +
' 500002108,   ' +
' 500002110,   ' +
' 1017000172,  ' +
' 1017000174,  ' +
' 1017000182,  ' +
' 1017000175,  ' +
' 500002560,   ' +
' 500002665,   ' +
' 1017000351,  ' +
' 1017000188,  ' +
' 1017000383,  ' +
' 500002666,   ' +
' 500002668,   ' +
' 500002670,   ' +
' 500002620,   ' +
' 500002621,   ' +
' 900000319,   ' +
' 500002622,   ' +
' 500002623,   ' +
' 500002569,   ' +
' 500002571,   ' +
' 500002624,   ' +
' 1000000178,  ' +
' 900000321,   ' +
' 500002572,   ' +
' 500002611,   ' +
' 500002677,   ' +
' 500002678,   ' +
' 1017000352,  ' +
' 1000000179,  ' +
' 500002679,   ' +
' 1017000307,  ' +
' 500002680,   ' +
' 1000000181,  ' +
' 1017000353,  ' +
' 1000000182,  ' +
' 1000000183,  ' +
' 900000320,   ' +
' 500002615,   ' +
' 1000000184,  ' +
' 1000000187,  ' +
' 1000000185,  ' +
' 500002686,   ' +
' 500002553,   ' +
' 1000000186,  ' +
' 500002570,   ' +
' 500002696,   ' +
' 500002699,   ' +
' 500002691,   ' +
' 500002694,   ' +
' 1017000354,  ' +
' 500002645,   ' +
' 1017000313,  ' +
' 500002698,   ' +
' 500002651,   ' +
' 500002695,   ' +
' 500002692,   ' +
' 500002693,   ' +
' 500002652,   ' +
' 500002654,   ' +
' 500002653,   ' +
' 900000322,   ' +
' 900000324,   ' +
' 500002655,   ' +
' 900000325,   ' +
' 900000326,   ' +
' 500002656,   ' +
' 900000327,   ' +
' 500002660,   ' +
' 500002663,   ' +
' 900000328,   ' +
' 900000330,   ' +
' 900000331,   ' +
' 900000329,   ' +
' 900000334,   ' +
' 900000332,   ' +
' 900000335,   ' +
' 900000336,   ' +
' 900000337,   ' +
' 1017000314,  ' +
' 1017000316,  ' +
' 1017000315,  ' +
' 1017000320,  ' +
' 1017000319,  ' +
' 1017000321,  ' +
' 1017000317,  ' +
' 1017000318,  ' +
' 1017000346,  ' +
' 500002772,   ' +
' 1017000328,  ' +
' 1017000324,  ' +
' 500002790,   ' +
' 500002778,   ' +
' 1017000327,  ' +
' 1017000337,  ' +
' 500002780,   ' +
' 1017000325,  ' +
' 1017000326,  ' +
' 1017000336,  ' +
' 500002788,   ' +
' 1017000333,  ' +
' 1017000342,  ' +
' 1017000334,  ' +
' 1017000330,  ' +
' 1017000331,  ' +
' 1017000332,  ' +
' 1017000335,  ' +
' 500002789,   ' +
' 1017000347,  ' +
' 1017000338,  ' +
' 1017000340,  ' +
' 1017000339,  ' +
' 1017000348,  ' +
' 1017000362,  ' +
' 1017000358,  ' +
' 1017000360,  ' +
' 1017000361,  ' +
' 1017000363,  ' +
' 1017000364,  ' +
' 900000345,   ' +
' 900000340,   ' +
' 900000338,   ' +
' 900000341,   ' +
' 900000342,   ' +
' 900000346,   ' +
' 900000343,   ' +
' 900000344,   ' +
' 900000347,   ' +
' 900000348,   ' +
' 1017000367,  ' +
' 500002807,   ' +
' 500002808,   ' +
' 500002815,   ' +
' 500002809,   ' +
' 500002812,   ' +
' 500002817,   ' +
' 500002827,   ' +
' 500002813,   ' +
' 500002816,   ' +
' 1017000375,  ' +
' 1017000371,  ' +
' 500002826,   ' +
' 1017000368,  ' +
' 500002819,   ' +
' 500002825,   ' +
' 1017000373,  ' +
' 500002821,   ' +
' 500002823,   ' +
' 500002818,   ' +
' 500002822,   ' +
' 1017000376,  ' +
' 1017000372,  ' +
' 1017000374,  ' +
' 500002824,   ' +
' 500002828,   ' +
' 1017000377,  ' +
' 1000000191,  ' +
' 500002833,   ' +
' 500002838,   ' +
' 500002842,   ' +
' 500002843,   ' +
' 1017000378,  ' +
' 1017000379,  ' +
' 1017000380 ) ' ;

TempFINMaterials :=  HTTPRIOFINMaterials as FINMaterialsControllerSoapPort;
finList := TempFinMaterials.getScrollableFilteredList(finMatFilter,0,-1);
for i:=0 to finList.totalCount - 1 do
begin
  try
    //TempFINMaterials.remove_(finList.list[i].code);
  except
    on e : Exception do
    begin
    log.Lines.Add(IntToStr(finList.list[i].code) + ' ; ' + e.Message);
    Continue;
    end;
  end;
end;
}

{
  planFilter := ENPlanWorkFilter.Create;
  SetNullIntProps(planFilter);
  SetNullXSProps(planFilter);

  //planFilter.status := ENPlanWorkStatus.Create;
  //planFilter.status.code := ENPLANWORKSTATUS_GOOD;

  //planFilter.budgetRef := ENDepartmentRef.Create;
  //planFilter.budgetRef.code := 75000001;

  //planFilter.yearGen := 2010;
  //planFilter.monthGen := 6;
  //planFilter.conditionSQL := ' monthgen in (11,12) and statuscode = 1';
  //planFilter.orderBySQL := 'ENPLANWORK.DEPARTMENTREFCODE, ENELEMENTDATA.ENAME, ENPLANWORK.YEARGEN, ENPLANWORK.MONTHGEN';
  planFilter.yearGen := 2011;
  planFilter.kind := ENPlanWorkKind.Create;
  planFilter.kind.code := ENPLANWORKKIND_CURRENT;
  planFilter.typeRef := ENPlanWorkTypeRef.Create;
  planFilter.typeRef.code := 100;


  planFilter.conditionSQL := 'code in (select p.code from enplanwork p,enplanworkitem pi,tktechcard tk '+
'where p.monthgen>2'+
' and p.code=pi.planrefcode '+
' and pi.kartarefcode=tk.code '+
' and ((tk.techkartnumber=''01030401'')or(tk.techkartnumber=''01030402'') or (tk.techkartnumber=''02030101'') or (tk.techkartnumber=''02030102'') ) '+
' and p.elementrefcode in ( select rp.elementcode from enrecordpointbyt rp where rp.domain_info=''root.lep''' +
' and upper(address) like ''%ВЕРХНІЙ РОГАЧИК%''' +
' and rpcode>=157000000 '+
' ))';

  TempENPlanWork  := HTTPRIOENPlanWork as ENPlanWorkControllerSoapPort;



  planList :=  TempENPlanWork.getScrollableFilteredList(planFilter, 0, -1);
  for i := 0 to planList.totalCount -1 do
  begin
     try
       TempENPlanWork.getPlanStatusFromCN(planList.list[i].code);
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
       log.Lines.Add('Ошибка при утверждении ПЛАНА : ' + planList.list[i].departmentRefShortName + ', объект:' + planList.list[i].objectName + ', год:' + IntToStr(planList.list[i].yeargen) + ', месяц:' + intToStr(planList.list[i].monthGen) );
       log.lines.Add(e.Message);
       Application.ProcessMessages;
     end;

     on E: Exception do
     begin
     log.Lines.Add('error: ' + e.message);
     exit;
     end;

end;
end;
}


{
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
          else
    begin
       log.Lines.Add('Ошибка при утверждении ПЛАНА : ' + planList.list[i].departmentRefShortName + ', объект:' + planList.list[i].objectName + ', год:' + IntToStr(planList.list[i].yeargen) + ', месяц:' + intToStr(planList.list[i].monthGen) );
       log.lines.Add(e.Message);
       Application.ProcessMessages;
    end;

      end;


    end
    else
    begin
       log.Lines.Add('Ошибка при утверждении ПЛАНА : ' + planList.list[i].departmentRefShortName + ', объект:' + planList.list[i].objectName + ', год:' + IntToStr(planList.list[i].yeargen) + ', месяц:' + intToStr(planList.list[i].monthGen) );
       log.lines.Add(e.Message);
       Application.ProcessMessages;
    end;
     {
     on E: Exception do
     begin
       log.Lines.Add('Ошибка при утверждении ПЛАНА : ' + planList.list[i].departmentRefShortName + ', объект:' + planList.list[i].objectName + ', год:' + IntToStr(planList.list[i].yeargen) + ', месяц:' + intToStr(planList.list[i].monthGen) );
       log.lines.Add(e.Message);
       Application.ProcessMessages;
     end;
     }


//  end;

{
  TempENAct := HTTPRIOENAct as ENActControllerSoapPort;
  actFilter := ENActFilter.Create;
  SetNullXSProps(actFilter);
  SetNullIntProps(actFilter);
  //actFilter.statusRef := ENActStatusRef.Create;
  //actFilter.statusRef.code :=
  actFilter.conditionSQL := 'enact.statusrefcode <> 2 and enact.elementcode in (select enelement.code from enelement where enelement.typerefcode = 15) ';
  actList :=  TempENAct.getScrollableFilteredList(actFilter, 0, -1);
  for i := 0 to actList.totalCount -1 do
  begin
     try
       //TempENPlanWork.closePlanWork(planList.list[i].code);
       TempENAct.fillActData(actList.list[i].code);
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
       log.Lines.Add('Ошибка заливки акта : ' + actList.list[i].numberGen + ', объект:' + intToStr(actList.list[i].code) );
       log.lines.Add(e.Message);
       Application.ProcessMessages;
     end;

     on E: Exception do
     begin
     log.Lines.Add('error: ' + e.message);
     exit;
     end;

end;
end;
}
end;

procedure TfrmENPlanTransPortEdit.btnWorkOrderDetailsClick(Sender: TObject);
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

procedure TfrmENPlanTransPortEdit.actWorkOrderInsertExecute(Sender: TObject);
var
   TempENWorkOrder: ENWorkOrderControllerSoapPort;
   frmENWorkOrderEdit : TfrmENWorkOrderEdit;
   TempENWorkOrder2ENPlanWork : ENWorkOrder2ENPlanWorkControllerSoapPort;
begin
  //inherited;
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

procedure TfrmENPlanTransPortEdit.actWorkOrderEditExecute(Sender: TObject);
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

procedure TfrmENPlanTransPortEdit.LoadMOLData(workOrderCode : Integer);
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
        Cells[3,i+1] := FINMolDataList.list[i].molTypeRefName;

        sgFINMolData.RowCount:=High(FINMolDataList.list)+2;
      end;
   sgFINMolData.Row:=1;

end;


procedure TfrmENPlanTransPortEdit.LoadWorkOrder;
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

procedure TfrmENPlanTransPortEdit.actWorkOrderUpdateExecute(Sender: TObject);
begin
  LoadWorkOrder;
end;

procedure TfrmENPlanTransPortEdit.spbFINMolClick(Sender: TObject);
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

procedure TfrmENPlanTransPortEdit.InitWorkOrderFields;
begin
  DisableControls([edtWorkOrderCode]);
  
  if (WorkOrderEditState = dsInsert) or (WorkOrderEditState = dsEdit) then
  begin
    //DisableActions([actWorkOrderInsert, actWorkOrderEdit, actWorkOrderDelete, actWorkOrderUpdate]);
    DisableControls([edtFinMolCode, edtFinMolName, edtFinMechanicCode, edtFinMechanicName, edtWorkOrderNumber]);

    HideControls([btnWorkOrderSave, btnWorkOrderCancel], false);

    DisableControls([spbFINMol, spbFINMechanic], false);//, (WorkOrderEditState = dsEdit));
    DisableControls([{edtWorkOrderNumber,} {edtDateWorkOrder,} edtWorkOrderCommentGen], false);

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
                     edtFinMolCode, edtFinMolName, edtFinMechanicCode, edtFinMechanicName]);
    
    HideControls([btnWorkOrderSave, btnWorkOrderCancel]);
    DisableControls([spbFINMol, spbFINMechanic]);
  end;
end;

procedure TfrmENPlanTransPortEdit.btnWorkOrderSaveClick(Sender: TObject);
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
      TempENWorkOrder2ENPlanWork.addWithCheck(ENWorkOrder2ENPlanWorkObj , false ); {SUPP-104240}
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

procedure TfrmENPlanTransPortEdit.btnWorkOrderCancelClick(Sender: TObject);
begin
  ClearWorkOrderFields;
  LoadWorkOrder;
  WorkOrderEditState := dsView;
  InitWorkOrderFields;
end;

procedure TfrmENPlanTransPortEdit.ClearWorkOrderFields;
begin
  ClearControlChildren(gbWorkOrder);
  //edtDateWorkOrder.Date := Date;
  //edtDateWorkOrder.Checked := false;
end;

procedure TfrmENPlanTransPortEdit.spbFINMechanicClick(Sender: TObject);
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

procedure TfrmENPlanTransPortEdit.pcPlanWorkChanging(Sender: TObject;
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

procedure TfrmENPlanTransPortEdit.actWorkOrderDeleteExecute(Sender: TObject);
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

procedure TfrmENPlanTransPortEdit.cbShowAllClick(Sender: TObject);
begin
  inherited;

  UpdateGrid(Sender);

end;

procedure TfrmENPlanTransPortEdit.spbPlanMOLClick(Sender: TObject);
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

               if isTransport then
                begin
                  molCode := ENMOL2PlanWorkObj.molCode;
                  molName := ENMOL2PlanWorkObj.molName;
                end
               else begin
               if ENMOL2PlanWorkObj.code > LOW_INT then
                  TempENMOL2PlanWork.save(ENMOL2PlanWorkObj)
               else
                  TempENMOL2PlanWork.add(ENMOL2PlanWorkObj);
               end;

            except
               on EConvertError do Exit;
            end;
        end;
      end;
   finally
      frmFINMolShow.Free;
   end;


end;

procedure TfrmENPlanTransPortEdit.spbPlanMOLClearClick(Sender: TObject);
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

procedure TfrmENPlanTransPortEdit.actAddToRQOrderExecute(Sender: TObject);
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

procedure TfrmENPlanTransPortEdit.actRemoveFromRQOrderExecute(Sender: TObject);
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

procedure TfrmENPlanTransPortEdit.actMOLDataInsertExecute(Sender: TObject);
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

procedure TfrmENPlanTransPortEdit.actMOLDataDeleteExecute(Sender: TObject);
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

procedure TfrmENPlanTransPortEdit.actMOLDataEditExecute(Sender: TObject);
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

procedure TfrmENPlanTransPortEdit.actMOLDataViewExecute(Sender: TObject);
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

procedure TfrmENPlanTransPortEdit.actMOLDataUpdateExecute(Sender: TObject);
begin
  //inherited;
  if edtWorkOrderCode.Text <> '' then
    LoadMOLData( StrToInt(edtWorkOrderCode.Text) );
end;

procedure TfrmENPlanTransPortEdit.spbBindingOverClick(Sender: TObject);
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

procedure TfrmENPlanTransPortEdit.actExpToExcelExecute(Sender: TObject);
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

procedure TfrmENPlanTransPortEdit.actExpToExcel_materialExecute(
  Sender: TObject);
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

procedure TfrmENPlanTransPortEdit.actExpToExcelDemontajExecute(Sender: TObject);
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

procedure TfrmENPlanTransPortEdit.actExpToExcelHumanExecute(Sender: TObject);
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


procedure TfrmENPlanTransPortEdit.actChangeEstimateItemStatusExecute(
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

procedure TfrmENPlanTransPortEdit.actSelectAllExecute(Sender: TObject);
begin
  inherited;
  checkGrid(sgENEstimateItem, 1, true);
end;

procedure TfrmENPlanTransPortEdit.actClearAllExecute(Sender: TObject);
begin
  inherited;
  checkGrid(sgENEstimateItem, 1, false);
end;

procedure TfrmENPlanTransPortEdit.actChangeEstimateItemStatusPLANEDExecute(
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


procedure TfrmENPlanTransPortEdit.updateMarksGrid(estimateItemCode : Integer; stringGrid : TAdvStringGrid );
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


procedure TfrmENPlanTransPortEdit.actMoveTOExecute(Sender: TObject);
var
  planFull : ENPlanWorkShort;
begin
  inherited;
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

end;

procedure TfrmENPlanTransPortEdit.actMoveFromExecute(Sender: TObject);
var
  planFull : ENPlanWorkShort;
begin
  inherited;
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

end;

procedure TfrmENPlanTransPortEdit.spbEPKardClick(Sender: TObject);
var
   frmKartiShow : TfrmTKTechCardShow;
   TempTKTechCard : TKTechCardControllerSoapPort;
   tcObj : TKTechCard;
   TempENPlanWorkItem : ENPlanWorkItemControllerSoapPort;
begin
   frmKartiShow:=TfrmTKTechCardShow.Create(Application,fmNormal);

   if isTransport then

   frmKartiShow.isTransport := true;
   frmKartiShow.techCardSourceCondition := 'tktechcardsource.code = 500000024';

   try
      with frmKartiShow do
      begin
        DisableActions([actInsert, actEdit, actDelete]);
        if ShowModal = mrOk then
        begin
            try

               TempENPlanWorkItem := HTTPRIOENPlanWorkItem as ENPlanWorkItemControllerSoapPort;
               ENPlanWorkItemObj := ENPlanWorkItem.Create;

               if ENPlanWorkItemObj.kartaRef = nil then ENPlanWorkItemObj.kartaRef := TKTechCardRef.Create();

               TempTKTechCard := HTTPRIOTKTechCardPWI as TKTechCardControllerSoapPort;
               tcObj := TempTKTechCard.getObject(StrToInt(GetReturnValue(sgTKTechCard,0)));

               ENPlanWorkItemObj.kartaRef.code := tcObj.code;
               edtKartiName.Text := tcObj.name;
               edtKartiNum.Text := tcObj.techKartNumber;
               lblMeasure.Caption := 'Вимірювач : ' + tcObj.meter + ' /  Од.виміру : ' + tcObj.measurement.name;

            except
               on EConvertError do Exit;
            end;
        end;
      end;
   finally
      frmKartiShow.Free;
   end;
end;


end.

unit MaterialsForOrder;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, DialogFormUnit, StdCtrls, ComCtrls, Buttons, ExtCtrls , ENPlanWorkController,
  InvokeRegistry, Rio, SOAPHTTPClient;

type
  TfrmMaterialsForOrder = class(TDialogForm)
    lblYearGen: TLabel;
    edtYearGen: TComboBox;
    lblMonthGen: TLabel;
    edtMonthGen: TComboBox;
    btnOk: TBitBtn;
    btnCancel: TBitBtn;
    lblEPRenName: TLabel;
    edtEPRenName: TEdit;
    spbEPRen: TSpeedButton;
    chbWholeYear: TCheckBox;
    chbByMonths: TCheckBox;
    GroupBox1: TGroupBox;
    chbByRENs: TCheckBox;
    spbEPRenClear: TSpeedButton;
    lblENBudgetName: TLabel;
    edtENBudgetName: TEdit;
    spbENBudget: TSpeedButton;
    spbENBudgetClear: TSpeedButton;
    chbByBudgets: TCheckBox;
    chkkindrefcodemat: TCheckBox;
    spbENPlanWorkState: TSpeedButton;
    edtWorkState: TEdit;
    lblWorkState: TLabel;
    SpeedButton1: TSpeedButton;
    HTTPRIOENElementType: THTTPRIO;
    CheckPr_mat_group: TCheckBox;
    GroupBox3: TGroupBox;
    chkFormWorkPlan: TCheckBox;
    chkFormWorkPozaPlan: TCheckBox;
    lblTypeName: TLabel;
    edtTypeName: TEdit;
    spbType: TSpeedButton;
    SpeedButton2: TSpeedButton;
    GroupBox2: TGroupBox;
    chkActrough: TCheckBox;
    chkActTrick: TCheckBox;
    chkNoAkt: TCheckBox;
    procedure chbWholeYearClick(Sender: TObject);
    procedure spbEPRenClick(Sender: TObject);
    procedure FormShow(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure spbENElementClick(Sender: TObject);
    procedure spbEPRenClearClick(Sender: TObject);
    procedure spbENElementClearClick(Sender: TObject);
    procedure spbENBudgetClick(Sender: TObject);
    procedure spbENBudgetClearClick(Sender: TObject);
    procedure spbENPlanWorkStateClick(Sender: TObject);
    procedure SpeedButton1Click(Sender: TObject);
    procedure cbElementTypeChange(Sender: TObject);
    procedure spbTypeClick(Sender: TObject);
    procedure SpeedButton2Click(Sender: TObject);
   
  private
    { Private declarations }
  public
    { Public declarations }
    renCode: Integer;
    renName: String;
    elementCode: Integer;
    elementName: String;
    budgetCode: Integer;
    budgetName: String;
    WorkStateCode : Integer;
    WorkStateName : String ;
    WorkTypeCode :  Integer ;
    WorkTypeName : String ;
    ObjectName : String;
  end;

var
  frmMaterialsForOrder: TfrmMaterialsForOrder;
  ENPlanWorkFilterObj: ENPlanWorkFilter;

implementation

uses ShowENEPRen, ChildFormUnit, ShowENElement, ENElementController,
  EnergyproController, ENConsts, ShowENDepartment, ENDepartmentController,
  ENDepartmentTypeController , EditENPlanWorkState,
  ShowENPlanWorkState , ENPlanWorkStateController , ENElementTypeController , ShowENPlanWorkType , ENPlanWorkTypeController ;

{$R *.dfm}

procedure TfrmMaterialsForOrder.chbWholeYearClick(Sender: TObject);
begin
  HideControls([lblMonthGen, edtMonthGen], chbWholeYear.Checked);
  HideControls([chbByMonths], not chbWholeYear.Checked);
end;

procedure TfrmMaterialsForOrder.spbEPRenClick(Sender: TObject);
var
    frmENDepartmentShow: TfrmENDepartmentShow;
   f : ENDepartmentFilter;
begin
   f := ENDepartmentFilter.Create;
   SetNullIntProps(f);
   SetNullXSProps(f);

   f.code := 1;
   frmENDepartmentShow:=TfrmENDepartmentShow.Create(Application,fmNormal, f);
   try
      with frmENDepartmentShow do begin
   //     DisableActions([ actNoFilter, actEdit, actInsert ]);
        if ShowModal = mrOk then
        begin

          renCode := ENDepartmentShort(tvDep.Selected.Data).code;
          renName := ENDepartmentShort(tvDep.Selected.Data).shortName;
          edtEPRenName.Text := renName;
        end;
      end;
   finally
      frmENDepartmentShow.Free;
   end;

end;

procedure TfrmMaterialsForOrder.FormShow(Sender: TObject);
var
 TempENElementType: ENElementTypeControllerSoapPort;
 ENElementTypeList: ENElementTypeShortList;
 f : ENElementTypeFilter;
 i : integer;
begin
  DisableControls([edtEPRenName,  edtENBudgetName , edtWorkState]);
  HideControls([{lblENElementName, edtENElementName, spbENElement, spbENElementClear,} chbByMonths]);






end;

procedure TfrmMaterialsForOrder.FormCreate(Sender: TObject);
begin
  renCode := 0;
  renName := '';
  elementCode := 0;
  elementName := '';
  budgetCode := 0;
  budgetName := '';
  WorkStateCode := 0; // тип работ
end;

procedure TfrmMaterialsForOrder.spbENElementClick(Sender: TObject);
var
  frmENElementShow: TfrmENElementShow;
  f: ENElementFilter;
begin
  {f := ENElementFilter.Create;
  SetNullIntProps(f);
  SetNullXSProps(f);
  // если определен тип объекта
  if frmMaterialsForOrder.cbElementType.ItemIndex > -1 then
  begin
    if Integer(frmMaterialsForOrder.cbElementTYpe.Items.Objects[frmMaterialsForOrder.cbElementType.ItemIndex]) <> 0 then
    begin
      f.typeRef := ENElementTypeRef.Create;
      f.typeRef.code :=  Integer(frmMaterialsForOrder.cbElementTYpe.Items.Objects[frmMaterialsForOrder.cbElementType.ItemIndex]);
    end;
  end;

  
  f.orderBySQL := 'typerefcode';

  if renCode > 0 then
  begin
    f.renRef := EPRenRef.Create;
    f.renRef.code := renCode;
  end;
  // А для ХОЭ (renCode = 0) выбираем все объекты

  frmENElementShow:=TfrmENElementShow.Create(Application,fmNormal, f);
  try
     frmENElementShow.isFiltered := True;
    with frmENElementShow do
      if ShowModal = mrOk then
      begin
        try
          elementCode := StrToInt(GetReturnValue(sgENElement,0));
          elementName := GetReturnValue(sgENElement,1);
          edtENElementName.Text := elementName;
          /// показываем тип объекта в cbElementType
          cbElementType.Enabled := False;
          cbElementType.ItemIndex := cbElementType.Items.IndexOf(GetReturnValue(sgENElement,4));
         // ComboBox'->ItemIndex = ComboBox'->Items->IndexOf(your_text);

          ///
          if elementCode > 0 then chbByObjects.Checked := false;
          HideControls([chbByObjects], (elementCode > 0));
          ///
        except
          on EConvertError do Exit;
        end;
      end;
  finally
    frmENElementShow.Free;
  end;  }
end;

procedure TfrmMaterialsForOrder.spbEPRenClearClick(Sender: TObject);
begin
  renCode := 0;
  renName := '';
  edtEPRenName.Text := '';
  HideControls([chbByRENs], false);
end;

procedure TfrmMaterialsForOrder.spbENElementClearClick(Sender: TObject);
begin
  elementCode := 0;
  elementName := '';



 // DisableControls([cbElementType] , False  ) ;
 // cbElementType.ItemIndex := cbElementType.Items.IndexOf('');
end;

procedure TfrmMaterialsForOrder.spbENBudgetClick(Sender: TObject);
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

      with frmENDepartmentShow do
      begin
   //     DisableActions([ actNoFilter, actEdit, actInsert ]);
        if ShowModal = mrOk then
        begin
            try
              budgetCode := ENDepartmentShort(tvDep.Selected.Data).code;
              budgetName := ENDepartmentShort(tvDep.Selected.Data).shortName;
              edtENBudgetName.Text := budgetName;
              ///
              if budgetCode > 0 then chbByBudgets.Checked := false;
              HideControls([chbByBudgets], (budgetCode > 0));
              ///
            except
               on EConvertError do Exit;
            end;
        end;
      end;
   finally
      frmENDepartmentShow.Free;
   end;
end;

procedure TfrmMaterialsForOrder.spbENBudgetClearClick(Sender: TObject);
begin
  budgetCode := 0;
  budgetName := '';
  edtENBudgetName.Text := '';
  HideControls([chbByBudgets], false);
end;

procedure TfrmMaterialsForOrder.spbENPlanWorkStateClick(Sender: TObject);
var
   frmENPlanWorkStateShow: TfrmENPlanWorkStateShow;
 //  f : EditENPlanWorkStateFilter;
begin
   frmENPlanWorkStateShow:=TfrmENPlanWorkStateShow.Create(Application,fmNormal);
   try
      with frmENPlanWorkStateShow do begin
      DisableActions([ actEdit, actInsert ],DialogState = dsView  );
        if ShowModal = mrOk then
        begin
            try
              // if ENPlanWorkFilterObj.stateRef = nil then ENPlanWorkFilterObj.stateRef := ENPlanWorkStateRef.Create();
              // ENPlanWorkFilterObj.stateRef.code := StrToInt(GetReturnValue(sgENPlanWorkState,0));//ENDepartmentShort(tvDep.Selected.Data).code;
               edtWorkState.Text:= GetReturnValue(sgENPlanWorkState,1); //ENDepartmentShort(tvDep.Selected.Data).shortName;
               WorkStateCode:= StrToInt(GetReturnValue(sgENPlanWorkState,0));
               WorkStateName:= GetReturnValue(sgENPlanWorkState,1);
            except
               on EConvertError do Exit;
            end;
        end;
      end;
   finally
      frmENPlanWorkStateShow.Free;
   end;
end;

procedure TfrmMaterialsForOrder.SpeedButton1Click(Sender: TObject);
begin

  WorkStateCode := 0 ;
  WorkStateName := ' ' ;
  edtWorkState.Text := '' ;
  
end;

procedure TfrmMaterialsForOrder.cbElementTypeChange(Sender: TObject);
begin

//  ObjectName:= frmMaterialsForOrder.cbElementTYpe.Text ;
//  ObjectName:= ObjectName ;

end;

procedure TfrmMaterialsForOrder.spbTypeClick(Sender: TObject);
var
   frmENPlanWorkTypeShow: TfrmENPlanWorkTypeShow;
   f : ENPlanWorkTypeFilter;
begin
   frmENPlanWorkTypeShow:=TfrmENPlanWorkTypeShow.Create(Application,fmNormal);
   try
      with frmENPlanWorkTypeShow do begin
        DisableActions([ actEdit, actInsert ]);
        if ShowModal = mrOk then
        begin
            try

              // ENPlanWorkFilterObj.typeRef.code := StrToInt(GetReturnValue(sgENPlanWorkType,0));//ENDepartmentShort(tvDep.Selected.Data).code;
               edtTypeName.Text:= GetReturnValue(sgENPlanWorkType,1); //ENDepartmentShort(tvDep.Selected.Data).shortName;
               WorkTypeCode :=   StrToInt(GetReturnValue(sgENPlanWorkType,0));
            except
               on EConvertError do Exit;
            end;
        end;
      end;
   finally
      frmENPlanWorkTypeShow.Free;
   end;
end;

procedure TfrmMaterialsForOrder.SpeedButton2Click(Sender: TObject);
begin
  WorkTypeCode := 0 ;
  WorkTypeName := ' ' ;
  edtTypeName.Text := '' ;
end;



end.

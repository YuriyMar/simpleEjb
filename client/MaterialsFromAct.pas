unit MaterialsFromAct;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, ComCtrls, StdCtrls, Buttons , DialogFormUnit , EnergyproController , DMReportsUnit,
  InvokeRegistry, Rio, SOAPHTTPClient , ENActController , ChildFormUnit , FINMaterialsController,
  CheckLst ;

type
  TfrmMaterialsFromAct = class(TDialogForm)
    lblYearRaznar: TLabel;
    lblMonthRaznar: TLabel;
    dtpStartDate: TDateTimePicker;
    Label1: TLabel;
    dtpEndDate: TDateTimePicker;
    btnOk: TBitBtn;
    btnCancel: TBitBtn;
    chkAllAct: TCheckBox;
    lblENBudgetName: TLabel;
    edtENBudgetName: TEdit;
    spbENBudget: TSpeedButton;
    spbENBudgetClear: TSpeedButton;
    spbEPRenClear: TSpeedButton;
    spbEPRen: TSpeedButton;
    edtEPRenName: TEdit;
    lblEPRenName: TLabel;
    lblENElementName: TLabel;
    edtENElementName: TEdit;
    spbENElement: TSpeedButton;
    spbENElementClear: TSpeedButton;
    lblElementType: TLabel;
    cbElementType: TComboBox;
    HTTPRIOENElementType: THTTPRIO;
    lblWorkState: TLabel;
    edtActType: TEdit;
    spbENPlanWorkState: TSpeedButton;
    spbTypeactClear: TSpeedButton;
    lblPlanWorkForm: TLabel;
    cbENPlanWorkFormName: TComboBox;
    lblTypeName: TLabel;
    edtTypeName: TEdit;
    spbType: TSpeedButton;
    spbPodVidClear: TSpeedButton;
    Label2: TLabel;
    edtNumberAct: TEdit;
    SpeedButton3: TSpeedButton;
    SpeedButton4: TSpeedButton;
    GroupBox1: TGroupBox;
    Label3: TLabel;
    edtfinmaterials: TEdit;
    GroupBox2: TGroupBox;
    btnCheckListAll: TSpeedButton;
    btnClearCleckList: TSpeedButton;
    lblMaterial: TLabel;
    spbMaterial: TSpeedButton;
    spbMaterialClear: TSpeedButton;
    Label4: TLabel;
    Label5: TLabel;
    CheckListBox1: TCheckListBox;
    edtMaterial: TEdit;
    HTTPRIOTKMaterials: THTTPRIO;
    CheckBox1: TCheckBox;
    CheckBox2: TCheckBox;
    chkMakeReportByServices: TCheckBox;
    Label6: TLabel;
    procedure btnOkClick(Sender: TObject);
    procedure chkAllActClick(Sender: TObject);
    procedure spbENBudgetClick(Sender: TObject);
    procedure spbEPRenClearClick(Sender: TObject);
    procedure spbEPRenClick(Sender: TObject);
    procedure spbENBudgetClearClick(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure spbENElementClick(Sender: TObject);
    procedure spbENElementClearClick(Sender: TObject);
    procedure FormShow(Sender: TObject);
    procedure spbENPlanWorkStateClick(Sender: TObject);
    procedure spbTypeactClearClick(Sender: TObject);
    procedure spbTypeClick(Sender: TObject);
    procedure spbPodVidClearClick(Sender: TObject);
    procedure SpeedButton3Click(Sender: TObject);
    procedure SpeedButton4Click(Sender: TObject);
    procedure btnCheckListAllClick(Sender: TObject);
    procedure btnClearCleckListClick(Sender: TObject);
    procedure spbMaterialClick(Sender: TObject);
    procedure spbMaterialClearClick(Sender: TObject);
    procedure CheckBox1Click(Sender: TObject);
    procedure CheckBox2Click(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
    renCode: Integer;
    renName: String;
    budgCode: Integer;
    budgName: String;
    elementCode : integer;
    elementName : String;
    ActTypeCode : Integer;
    ActTypeName : String;
    WorkTypeCode : Integer ;
    WorkTypeName : String ;
    NumberActCode : Integer ;

    materialCode : Integer;
    materialName : String;
  end;

var
  frmMaterialsFromAct: TfrmMaterialsFromAct;

implementation

uses Math , ENDepartmentController, ENDepartmentTypeController , ShowENDepartment ,
 ENConsts , ShowENElement, ENElementController , ENElementTypeController ,
ShowENPlanWorkState , ENPlanWorkStateController , ShowENPlanWorkType , ENPlanWorkTypeController ,
EditENAct, EditENActFilter , ShowENAct  , ShowFINMaterials , ShowTKMaterials, TKMaterialsController  ;

{$R *.dfm}

procedure TfrmMaterialsFromAct.btnOkClick(Sender: TObject);
var
  argNames, args : ArrayOfString;
  reportName : String;
  i : integer;
  strGroupmaterials : String;
begin
   // if not NoBlankValues([dtpStartDate, dtpEndDate]) then
 // begin
   //   Application.MessageBox(PChar('Необхідно вибрати період заявки !'),PChar('Увага !'),MB_ICONWARNING+MB_OK);
   //   ModalResult := mrNone;
 // end
  // else
  begin
    SetLength(argNames, 13);
    SetLength(args, 13);

    argNames[0] := 'startDate';
     if not chkAllAct.Checked then
    args[0] := DateToStr(dtpStartDate.DateTime)
     else
    args[0] := '01.01.2000';

    argNames[1] := 'endDate';
    if not chkAllAct.Checked then
     args[1] := DateToStr(dtpEndDate.DateTime)
    else
     args[1] := '01.01.2050';

     argNames[2] := 'podr';
     args[2] :=  IntToStr(renCode);

     argNames[3] := 'budj';
     args[3] :=  IntToStr(budgCode);


     argNames[4] := 'obj';
     args[4] :=  IntToStr(elementCode);


     argnames[5] := 'objtype';
      if (cbElementType.ItemIndex = -1  ) or
          (cbElementType.ItemIndex = 0 ) then
      args[5] := IntToStr(0)
      else
      args[5] := IntToStr(Integer(cbElementTYpe.Items.Objects[cbElementType.ItemIndex])) ;


     argNames[6] := 'typeact';
     args[6] :=  IntToStr(ActTypeCode);

     argNames[7] := 'vidwork';
     if cbENPlanWorkFormName.ItemIndex <= 0 then
     args[7] := '0'
     else
     args[7] := IntToStr(cbENPlanWorkFormName.ItemIndex) ;

     argNames[8] := 'podvidwork';
     args[8] := IntToStr(WorkTypeCode);

     argNames[9] := 'NumberActCode';
     args[9] := IntToStr(NumberActCode);

       argNames[10] := 'strmatnamenn';
       if CheckBox1.Checked = true then
       args[10] := '%'  +  edtfinmaterials.text +  '%'
       else
       args[10] := '%'  +  '' +  '%' ;

     if NumberActCode <> 0 then
     begin
      args[0] := '01.01.2000';
      args[1] := '01.01.2050';
      args[2] := '0';
      args[3] := '0';
      args[4] := '0';
      args[5] := '0';
      args[6] := '0';
      args[7] := '0';
      args[8] := '0';
      args[10] := '%'+'%';
     end;



   if CheckBox2.Checked = true then
   begin
      strGroupmaterials:= '';
   /// собираем строку групп материалов
   For i:=0 to CheckListBox1.Count -1  do
    Begin
       if  CheckListBox1.Checked[i] then
        if strGroupmaterials <>  '' then
           strGroupmaterials := strGroupmaterials + ' , ' + IntToStr(  Integer( CheckListBox1.Items.Objects[i] ) )
         else
           strGroupmaterials := strGroupmaterials + IntToStr(  Integer( CheckListBox1.Items.Objects[i] ) ) ;

    End;
     if materialCode <> -1 then
        strGroupmaterials := ' and  tkm.code in ' +
                   ' (select tm.code from tkmaterials tm ' +
                   ' where tm.code in ( '+ IntToStr(materialCode)+'))' ;



     if (( trim(strGroupmaterials) <> '' ) and  ( materialCode = -1 )) then
         strGroupmaterials := ' and  tkm.code in  ' +
                  ' (select tm.code from tkmaterials tm ' +
                  ' where tm.rootgrouprefcode in ('+strGroupmaterials+'))';

  end
     else
     strGroupmaterials := ' and  tkm.code in  ' +
                 ' (select tm.code from tkmaterials tm ' +
                 '  where tm.code in ( select code from tkmaterials ))' ;

     argnames[11] := 'strGroupmaterials';
     args[11]:= strGroupmaterials;

     argNames[12] := 'showServices';
     if chkMakeReportByServices.Checked = True  then
     args[12]:= '1';
     if chkMakeReportByServices.Checked = False  then
     args[12]:= '0';


     reportName := 'material/materialsfromact' ;

     makeReport(reportName, argNames, args, 'xls');

{if chkMakeReportByServices.Checked = True  then
        makeReport('Services/servicesfromact', argNames, args, 'xls'); }


  end;

end;

procedure TfrmMaterialsFromAct.chkAllActClick(Sender: TObject);
begin
   if chkAllAct.Checked then
   begin
   DisableControls([ dtpStartDate , dtpEndDate ]);
   dtpStartDate.Checked:= false;
   dtpEndDate.Checked:= false;
   end
   else
   begin
   DisableControls([ dtpStartDate , dtpEndDate ],false);

   end;
end;

procedure TfrmMaterialsFromAct.spbENBudgetClick(Sender: TObject);
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
              budgCode := ENDepartmentShort(tvDep.Selected.Data).code;
              budgName := ENDepartmentShort(tvDep.Selected.Data).shortName;
              edtENBudgetName.Text := budgName;

            except
               on EConvertError do Exit;
            end;
        end;
      end;
   finally
      frmENDepartmentShow.Free;
   end;
end;

procedure TfrmMaterialsFromAct.spbEPRenClearClick(Sender: TObject);
begin


  renCode := 0;
  renName := '';
  edtEPRenName.Text := '';
end;

procedure TfrmMaterialsFromAct.spbEPRenClick(Sender: TObject);
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

procedure TfrmMaterialsFromAct.spbENBudgetClearClick(Sender: TObject);
begin
  budgCode := 0;
  budgName := '';
  edtENBudgetName.Text := '';
end;

procedure TfrmMaterialsFromAct.FormCreate(Sender: TObject);
begin
   renCode:= 0;
   budgCode:= 0;
   elementCode :=0 ;
   ActTypeCode :=0 ;
   WorkTypeCode :=0 ;
   NumberActCode := 0;
   materialCode := -1;

   
end;

procedure TfrmMaterialsFromAct.spbENElementClick(Sender: TObject);

   var
  frmENElementShow: TfrmENElementShow;
  f: ENElementFilter;
begin
  f := ENElementFilter.Create;
  SetNullIntProps(f);
  SetNullXSProps(f);

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

        except
          on EConvertError do Exit;
        end;
      end;
  finally
    frmENElementShow.Free;
  end;


end;


procedure TfrmMaterialsFromAct.spbENElementClearClick(Sender: TObject);
begin
  elementCode := 0;
  elementName := '';
  edtENElementName.Text := '';

end;

procedure TfrmMaterialsFromAct.FormShow(Sender: TObject);
var
 TempENElementType: ENElementTypeControllerSoapPort;
 ENElementTypeList: ENElementTypeShortList;
 f : ENElementTypeFilter;
 i : integer;

  TempTKMaterials: TKMaterialsControllerSoapPort;
  ti: Integer;
  TKMaterialsList: TKMaterialsShortList;
  materialsfilter : TkMaterialsFilter;
begin
  DisableControls([edtEPRenName, edtENElementName, edtENBudgetName , edtActType , edtTypeName , edtNumberAct
                   , edtfinmaterials , CheckListBox1 , btnCheckListAll , btnClearCleckList , edtMaterial , spbMaterial , spbMaterialClear
                    ]);


  { заполняем выпадающий список типов объектов }
  cbElementType.Clear;

  f:= ENElementTypeFilter.Create;
  SetNullIntProps(f);
  f.conditionSQL := SQL_NO_SELECTED_ELEMENT_TYPE; //'code <> 4';  
  f.orderBySQL := 'code';

  cbElementType.Items.Add('');

  TempENElementType := HTTPRIOENElementType as ENElementTypeControllerSoapPort;
  ENElementTypeList := TempENElementType.getScrollableFilteredList(f,0,-1);
  for i:=0 to ENElementTypeList.totalCount-1 do
  begin
    cbElementType.Items.AddObject(ENElementTypeList.list[i].name, TObject(ENElementTypeList.list[i].code));
  end;

    cbElementType.DropDownCount := ENElementTypeList.totalCount + 1;


    {заполняем группы материалов}

     
     materialsfilter := TKMaterialsFilter.Create;
     SetNullIntProps(materialsfilter);
     SetNullXSProps(materialsfilter);

     materialsfilter.conditionSQL := 'tk1.parentrefcode is null and tk1.measurementcode is not null';
     materialsfilter.orderBySQL := 'TK1.NAME';


     TempTKMaterials:= HTTPRIOTKMaterials as TKMaterialsControllerSoapPort;
     TKMaterialsList := TempTKMaterials.getScrollableFilteredList(materialsfilter,0,-1);
     CheckListBox1.Items.Clear;

    for i:=0 to High(TKMaterialsList.list) do
      begin
        ///////
        
        CheckListBox1.Items.AddObject(TKMaterialsList.list[i].name , TObject( TKMaterialsList.list[i].code )  );
      end;

end;

procedure TfrmMaterialsFromAct.spbENPlanWorkStateClick(Sender: TObject);
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
               edtActType.Text:= GetReturnValue(sgENPlanWorkState,1); //ENDepartmentShort(tvDep.Selected.Data).shortName;
               ActTypeCode:= StrToInt(GetReturnValue(sgENPlanWorkState,0));
               ActTypeName:= GetReturnValue(sgENPlanWorkState,1);
            except
               on EConvertError do Exit;
            end;
        end;
      end;
   finally
      frmENPlanWorkStateShow.Free;
   end;
end;

procedure TfrmMaterialsFromAct.spbTypeactClearClick(Sender: TObject);
begin
  ActTypeCode := 0 ;
  ActTypeName := ' ' ;
  edtActType.Text := '' ;
end;

procedure TfrmMaterialsFromAct.spbTypeClick(Sender: TObject);
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

procedure TfrmMaterialsFromAct.spbPodVidClearClick(Sender: TObject);
begin
  WorkTypeCode := 0 ;
  WorkTypeName := ' ' ;
  edtTypeName.Text := '' ;

end;

procedure TfrmMaterialsFromAct.SpeedButton3Click(Sender: TObject);
 var
 frmENActShow : TfrmENActShow;
   f : ENActFilter;
begin
 frmENActShow:= TfrmENActShow.Create(Application,fmNormal);
   try
      with frmENActShow do begin
        DisableActions([ actEdit, actInsert ]);
        if ShowModal = mrOk then
        begin
            try
               edtNumberAct.Text:= GetReturnValue(sgENAct,1); //ENDepartmentShort(tvDep.Selected.Data).shortName;
               NumberActCode :=   StrToInt(GetReturnValue(sgENAct,0));

               // обнуляем остальные параметры с очищением полей
                  if   NumberActCode > 0 then
                  begin
                    spbENBudgetClear.Click();
                    spbEPRenClear.Click();
                    spbENElementClear.Click();
                    cbElementType.ItemIndex:= -1;
                    spbTypeactClear.Click();
                    cbENPlanWorkFormName.ItemIndex := -1;
                    spbPodVidClear.Click();

                    
                  end;


            except
               on EConvertError do Exit;
            end;
        end;
      end;
   finally
      frmENActShow.Free;
   end;


end;

procedure TfrmMaterialsFromAct.SpeedButton4Click(Sender: TObject);
begin

   edtNumberAct.Text := '';
   NumberActCode:= 0 ;

end;

procedure TfrmMaterialsFromAct.btnCheckListAllClick(Sender: TObject);
 var
  I : Integer;
begin


     For i:=0 to CheckListBox1.Count -1  do
    Begin
       if  CheckListBox1.Checked[i] = false then
           CheckListBox1.Checked[i] := true;


    End;

end;

procedure TfrmMaterialsFromAct.btnClearCleckListClick(Sender: TObject);
var
  I : Integer;
begin


     For i:=0 to CheckListBox1.Count -1  do
    Begin
       if  CheckListBox1.Checked[i] = True then
           CheckListBox1.Checked[i] := False;
    End;

end;

procedure TfrmMaterialsFromAct.spbMaterialClick(Sender: TObject);
var
 frmSpr_matherialShow: TfrmTKMaterialsShow;
 fmFilter : TkMaterialsFilter;
 strGroupmaterials : String;
 i : integer;
begin
      strGroupmaterials:= '';
   ///\\\ собираем строку групп материалов
   For i:=0 to CheckListBox1.Count -1  do
    Begin
       if  CheckListBox1.Checked[i] then
        if strGroupmaterials <>  '' then
           strGroupmaterials := strGroupmaterials + ' , ' + IntToStr(  Integer( CheckListBox1.Items.Objects[i] ) )
         else
           strGroupmaterials := strGroupmaterials + IntToStr(  Integer( CheckListBox1.Items.Objects[i] ) ) ;

    End;
     if trim(strGroupmaterials) <> '' then
         strGroupmaterials := '('+strGroupmaterials+')';
     ///\\\

     if strGroupmaterials <>  '' then
     begin

     fmFilter := TkMaterialsFilter.Create;


     SetNullIntProps(fmFilter);
     SetNullXSProps(fmFilter);

     fmFilter.conditionSQL := 'TK1.code in ' + strGroupmaterials ;
     fmFilter.orderBySQL := 'TK1.NAME';


     frmSpr_matherialShow := TfrmTKMaterialsShow.Create(Application, fmNormal , fmFilter);
    end
    else
    frmSpr_matherialShow := TfrmTKMaterialsShow.Create(Application, fmNormal );

  try
    with frmSpr_matherialShow do
    begin

      DisableActions([actInsert, actEdit, actDelete]);

      if ShowModal = mrOk then
      begin

        try
          materialCode := TKMaterialsShort(tvDep.Selected.Data).code;
          edtMaterial.Text :=  TKMaterialsShort(tvDep.Selected.Data).name ;
          // очищаем лочим чек лист
        //  btnClearCleckListClick(sender);
          DisableControls([btnCheckListAll , btnClearCleckList , CheckListBox1 ]);


        except
          on EConvertError do Exit;
        end;
      end;
    end;
  finally
    frmSpr_matherialShow.Free;
  end;
end;

procedure TfrmMaterialsFromAct.spbMaterialClearClick(Sender: TObject);
begin
  materialCode := -1;
  materialName := '0';
  edtMaterial.Text := '';
  DisableControls([btnCheckListAll , btnClearCleckList , CheckListBox1 ],False);

end;

procedure TfrmMaterialsFromAct.CheckBox1Click(Sender: TObject);
var
i : integer;
begin

   if CheckBox1.Checked then
        // очищаем лочим чек лист
        begin
        CheckBox2.Checked := false;
        DisableControls([btnCheckListAll , btnClearCleckList , CheckListBox1 ]);
        DisableControls ([edtfinmaterials],false);

        // снимаем отметки в чек листе

                For i:=0 to CheckListBox1.Count -1  do
            Begin
               if  CheckListBox1.Checked[i] = True then
                   CheckListBox1.Checked[i] := False;
            End;

        // очищаем поле факт материала и обнуляем код
        edtMaterial.Clear;
        DisableControls ([spbmaterial ,spbmaterialClear ]);
        materialCode:= -1;
        end;


        if CheckBox1.Checked = false  then
        // разлочим чек лист
        begin
        edtfinmaterials.Clear;
        DisableControls([edtfinmaterials]);


        end;


end;

procedure TfrmMaterialsFromAct.CheckBox2Click(Sender: TObject);
var
i : integer;
begin

     if CheckBox2.Checked = True then
     begin
        CheckBox1.Checked := false ;
        edtfinmaterials.Clear;
        DisableControls ([edtfinmaterials]);

        DisableControls([btnCheckListAll , btnClearCleckList , CheckListBox1 ], false );
        DisableControls ([spbmaterial ,spbmaterialClear ], false);
     end;

     if CheckBox2.Checked = False then
     begin

      


        // снимаем отметки в чек листе

                For i:=0 to CheckListBox1.Count -1  do
            Begin
               if  CheckListBox1.Checked[i] = True then
                   CheckListBox1.Checked[i] := False;
            End;

        DisableControls([btnCheckListAll , btnClearCleckList , CheckListBox1 ] );
        DisableControls ([spbmaterial ,spbmaterialClear ]);

        // очищаем поле факт материала и обнуляем код
        edtMaterial.Clear;
       // spbmaterialClear.Click();
        materialCode:= -1;
     end;


end;

end.

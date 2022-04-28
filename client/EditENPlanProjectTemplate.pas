
unit EditENPlanProjectTemplate;

interface

uses
    Windows, Messages, SysUtils, StdCtrls, Variants, Classes, Graphics, Controls, Forms,
    Dialogs, ChildFormUnit, InvokeRegistry, Rio, SOAPHTTPClient, ImgList,
    Grids, ComCtrls, ToolWin, Menus, ActnList, BaseGrid,  XSBuiltIns, GridHeadersUnit,
    DialogFormUnit, AdvGrid, Buttons,
	EnergyproController, EnergyproController2, ENPlanProjectTemplateController ;

type
  TfrmENPlanProjectTemplateEdit = class(TDialogForm)
  
    lblCode : TLabel;
	edtCode : TEdit;
    lblTag : TLabel;
    edtTag: TEdit;
    lblElementName : TLabel;
    edtElementName: TMemo;
    lblElementcode : TLabel;
    edtElementcode: TEdit;
    lblUserGen : TLabel;
    edtUserGen: TEdit;
    lblDateEdit : TLabel;
    edtDateEdit: TDateTimePicker;


  HTTPRIOENPlanProjectTemplate: THTTPRIO;

  btnOk: TButton;
  btnCancel: TButton;

  procedure FormShow(Sender: TObject);
  procedure FormClose(Sender: TObject; var Action: TCloseAction);
  
  
  private
    { Private declarations }
  public
    { Public declarations }

end;

var
  frmENPlanProjectTemplateEdit: TfrmENPlanProjectTemplateEdit;
  ENPlanProjectTemplateObj: ENPlanProjectTemplate;

implementation


{uses  
    EnergyproController, EnergyproController2, ENPlanProjectTemplateController  ;
}
{$R *.dfm}



procedure TfrmENPlanProjectTemplateEdit.FormShow(Sender: TObject);

begin

  if DialogState = dsView then
  begin
//     DisableControls([
  end;

  if (DialogState = dsInsert) or (DialogState = dsEdit) then
  begin
    DenyBlankValues([
      edtTag
      ,edtElementName
      ,edtElementcode
      ,edtUserGen
      ,edtDateEdit
     ]);
   end;

  if  (DialogState = dsEdit) or (DialogState = dsView) then
  begin
      edtCode.Text := IntToStr(ENPlanProjectTemplateObj.code);
    edtTag.Text := ENPlanProjectTemplateObj.tag; 
    MakeMultiline(edtElementName.Lines, ENPlanProjectTemplateObj.elementName);
    if ( ENPlanProjectTemplateObj.elementcode <> Low(Integer) ) then
       edtElementcode.Text := IntToStr(ENPlanProjectTemplateObj.elementcode)
    else
       edtElementcode.Text := '';
    edtUserGen.Text := ENPlanProjectTemplateObj.userGen; 
      SetDateFieldForDateTimePicker(edtDateEdit, ENPlanProjectTemplateObj.dateEdit);


  end;
end;



procedure TfrmENPlanProjectTemplateEdit.FormClose(Sender: TObject;  var Action: TCloseAction);
var TempENPlanProjectTemplate: ENPlanProjectTemplateControllerSoapPort;
begin
  if (ModalResult = mrOk) and ((DialogState = dsEdit) or (DialogState = dsInsert)) then
  if not NoBlankValues([
      edtTag
      ,edtElementName
      ,edtElementcode
      ,edtUserGen
     ])  then
  begin
      Application.MessageBox(PChar('Пустые поля недопустимы !'),PChar('Внимание !'),MB_ICONWARNING+MB_OK);
      Action:=caNone;
  end
  else
  begin
    TempENPlanProjectTemplate := HTTPRIOENPlanProjectTemplate as ENPlanProjectTemplateControllerSoapPort;


     ENPlanProjectTemplateObj.tag := edtTag.Text; 

     ENPlanProjectTemplateObj.elementName := edtElementName.Text; 

     if ( edtElementcode.Text <> '' ) then
       ENPlanProjectTemplateObj.elementcode := StrToInt(edtElementcode.Text)
     else
       ENPlanProjectTemplateObj.elementcode := Low(Integer) ;

     ENPlanProjectTemplateObj.userGen := edtUserGen.Text; 

     ENPlanProjectTemplateObj.dateEdit := GetTXSDateTimeFromTDateTimePicker(edtdateEdit);	   

    if DialogState = dsInsert then
    begin
      ENPlanProjectTemplateObj.code:=low(Integer);
      TempENPlanProjectTemplate.add(ENPlanProjectTemplateObj);
    end
    else
    if DialogState = dsEdit then
    begin
      TempENPlanProjectTemplate.save(ENPlanProjectTemplateObj);
    end;
  end;
end;


end.
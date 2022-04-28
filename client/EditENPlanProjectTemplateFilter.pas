
unit EditENPlanProjectTemplateFilter;

interface

uses
    Windows, Messages, SysUtils, StdCtrls, Variants, Classes, Graphics, Controls, Forms,
    Dialogs, ChildFormUnit, InvokeRegistry, Rio, SOAPHTTPClient, ImgList,
    Grids, ComCtrls, ToolWin, Menus, ActnList, BaseGrid,  XSBuiltIns, GridHeadersUnit,
    DialogFormUnit, AdvGrid, Buttons,
	EnergyproController, EnergyproController2, ENPlanProjectTemplateController ;

type
  TfrmENPlanProjectTemplateFilterEdit = class(TDialogForm)

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
  frmENPlanProjectTemplateFilterEdit: TfrmENPlanProjectTemplateFilterEdit;
  ENPlanProjectTemplateFilterObj: ENPlanProjectTemplateFilter;

implementation


{uses  
    EnergyproController, EnergyproController2, ENPlanProjectTemplateController  ;
}
{$R *.dfm}



procedure TfrmENPlanProjectTemplateFilterEdit.FormShow(Sender: TObject);

begin

{ пока ниче не делать ...

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

    edtTag.Text := ENPlanProjectTemplateObj.tag; 



    MakeMultiline(edtElementName.Lines, ENPlanProjectTemplateObj.elementName);



    if ( ENPlanProjectTemplateObj.elementcode <> Low(Integer) ) then
       edtElementcode.Text := IntToStr(ENPlanProjectTemplateObj.elementcode)
    else
       edtElementcode.Text := '';



    edtUserGen.Text := ENPlanProjectTemplateObj.userGen; 



      if ENPlanProjectTemplateObj.dateEdit <> nil then
      begin
        edtDateEdit.DateTime:=EncodeDate(ENPlanProjectTemplateObj.dateEdit.Year,ENPlanProjectTemplateObj.dateEdit.Month,ENPlanProjectTemplateObj.dateEdit.Day);
        edtDateEdit.checked := true;
      end
      else
      begin
        edtDateEdit.DateTime:=SysUtils.Date;
        edtDateEdit.checked := false;
      end;	  


  end;

}

end;



procedure TfrmENPlanProjectTemplateFilterEdit.FormClose(Sender: TObject;  var Action: TCloseAction);
var TempENPlanProjectTemplate: ENPlanProjectTemplateControllerSoapPort;
begin
  if (ModalResult = mrOk)  then
  begin

     ENPlanProjectTemplateFilterObj.tag := edtTag.Text; 



     ENPlanProjectTemplateFilterObj.elementName := edtElementName.Text; 



     if ( edtElementcode.Text <> '' ) then
       ENPlanProjectTemplateFilterObj.elementcode := StrToInt(edtElementcode.Text)
     else
       ENPlanProjectTemplateFilterObj.elementcode := Low(Integer) ;
	   



     ENPlanProjectTemplateFilterObj.userGen := edtUserGen.Text; 



     if edtdateEdit.checked then
     begin 
       if ENPlanProjectTemplateFilterObj.dateEdit = nil then
          ENPlanProjectTemplateFilterObj.dateEdit := TXSDateTime.Create;
       ENPlanProjectTemplateFilterObj.dateEdit.XSToNative(GetXSDate(edtdateEdit.DateTime));
     end
     else
       ENPlanProjectTemplateFilterObj.dateEdit := nil;




  end;
end;




end.
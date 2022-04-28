unit matMotionOfPartyorder;

interface

uses
  Windows, Messages, SysUtils, Variants, Classes, Graphics, Controls, Forms,
  Dialogs, DialogFormUnit, Buttons, StdCtrls , ChildFormUnit,
  InvokeRegistry, Rio, SOAPHTTPClient;

type
  TfrmmatMotionOfPartyorder = class(TDialogForm)
    lblOrder: TLabel;
    edtOrder: TEdit;
    spbOrder: TSpeedButton;
    spbDelorder: TSpeedButton;
    HTTPRIORQFKOrder: THTTPRIO;
    cbOrderKind: TComboBox;
    btnOk: TBitBtn;
    btnCancel: TBitBtn;
    Label1: TLabel;
    procedure spbOrderClick(Sender: TObject);
    procedure FormCreate(Sender: TObject);
    procedure btnOkClick(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
    porder : Integer;
  end;

var
  frmmatMotionOfPartyorder: TfrmmatMotionOfPartyorder;

implementation

uses EditRQFKOrderFilter , EditRQFKOrder ,RQFKOrderController , RQFKOrderKindController
, ShowRQFKOrder ,
  //Main,
  ENCfoDataController, ENConsts, EnergyproController , DMReportsUnit ;

{$R *.dfm}

procedure TfrmmatMotionOfPartyorder.spbOrderClick(Sender: TObject);
var kindCode : Integer;
    frmRQFKOrderShow : TfrmRQFKOrderShow;
begin

  frmRQFKOrderFilterEdit:=TfrmRQFKOrderFilterEdit.Create(Application, dsInsert);
  try
    RQFKOrderFilterObj := RQFKOrderFilter.Create;
    SetNullIntProps(RQFKOrderFilterObj);
    SetNullXSProps(RQFKOrderFilterObj);

    RQFKOrderFilterObj.kind := RQFKOrderKind.Create;
    RQFKOrderFilterObj.kind.code := LOW_INT;
    if cbOrderKind.ItemIndex > 0 then
      RQFKOrderFilterObj.kind.code := cbOrderKind.ItemIndex;

    if frmRQFKOrderFilterEdit.ShowModal = mrOk then
    begin
       frmRQFKOrderShow := TfrmRQFKOrderShow.Create(Application , fmNormal , RQFKOrderFilterObj );
        try
         if  frmRQFKOrderShow.ShowModal = mrOk then
             begin

              edtOrder.Text:= frmRQFKOrderShow.GetReturnValue(frmRQFKOrderShow.sgRQFKOrder , 1 );
              try
              porder :=  strtoint(frmRQFKOrderShow.GetReturnValue(frmRQFKOrderShow.sgRQFKOrder , 0 ));
              except
               on EConvertError do exit;
              end;


             end;
        finally
         frmRQFKOrderShow.free;

        end;
    end;
  finally
    frmRQFKOrderFilterEdit.Free;
    frmRQFKOrderFilterEdit:=nil;
  end;

end;

procedure TfrmmatMotionOfPartyorder.FormCreate(Sender: TObject);
begin
 porder:= -1;
end;

procedure TfrmmatMotionOfPartyorder.btnOkClick(Sender: TObject);
var
  argNames, args: ArrayOfString;
  reportName: String;
begin
   if porder = -1 then
      Application.MessageBox(PChar(' Не вибраний  ордер !!!'), PChar('Увага!'),MB_ICONWARNING)
   else
   begin

      SetLength(argNames, 1);
      SetLength(args, 1);

      argNames[0] := 'pordercode';
      args[0] := IntToStr( porder);
      reportName := 'MatMotionOfPartyOrder';
      makeReport(reportName , argNames , args , 'xls' );
   end;
end;

end.

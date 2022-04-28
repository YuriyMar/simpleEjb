unit AXLedgerTransController;

interface

uses InvokeRegistry, SOAPHTTPClient, Types, XSBuiltIns, EnergyProController, EnergyProController2
;

type

  // ************************************************************************ //
  // The following types, referred to in the WSDL document are not being represented
  // in this file. They are either aliases[@] of other types represented or were referred
  // to but never[!] declared in the document. The types from the latter category
  // typically map to predefined/known XML or Borland types; however, they could also
  // indicate incorrect WSDL documents that failed to declare or import a schema type.
  // ************************************************************************ //
  // !:int             - "http://www.w3.org/2001/XMLSchema"
  // !:string          - "http://www.w3.org/2001/XMLSchema"
  // !:decimal         - "http://www.w3.org/2001/XMLSchema"
  // !:date            - "http://www.w3.org/2001/XMLSchema"
  // !:long            - "http://www.w3.org/2001/XMLSchema"
  // !:dateTime        - "http://www.w3.org/2001/XMLSchema"

  AXLedgerTrans            = class;                 { "http://ksoe.org/EnergyproControllerService/type/" }



  // ************************************************************************ //
  // Namespace : http://ksoe.org/EnergyproControllerService/type/
  // ************************************************************************ //
  AXLedgerTransRef = class(TRemotable)
  private
    Fcode: Integer;
  published
    property code: Integer read Fcode write Fcode;
  end;


  // ************************************************************************ //
  // Namespace : http://ksoe.org/EnergyproControllerService/type/
  // ************************************************************************ //
  AXLedgerTrans = class(TRemotable)
  private
    Fcode : Integer;
    FtransDate : TXSDate;
    FamountCur : TXSDecimal;
    Fcurrency : WideString;
    FamountMST : TXSDecimal;
    FaccountNum : WideString;
    FoffsetAccountNum : WideString;
    FaccountDimension1 : WideString;
    FaccountDimension2 : WideString;
    FaccountDimension3 : WideString;
    FaccountDimension4 : WideString;
    FaccountDimension5 : WideString;
    FaccountDimension6 : WideString;
    FaccountDimension7 : WideString;
    FaccountDimension8 : WideString;
    FaccountDimension9 : WideString;
    FaccountDimension10 : WideString;
    FaccountDimension11 : WideString;
    FaccountDimension12 : WideString;
    FaccountDimension13 : WideString;
    FcorAccountDimension1 : WideString;
    FcorAccountDimension2 : WideString;
    FcorAccountDimension3 : WideString;
    FcorAccountDimension4 : WideString;
    FcorAccountDimension5 : WideString;
    FcorAccountDimension6 : WideString;
    FcorAccountDimension7 : WideString;
    FcorAccountDimension8 : WideString;
    FcorAccountDimension9 : WideString;
    FcorAccountDimension10 : WideString;
    FcorAccountDimension11 : WideString;
    FcorAccountDimension12 : WideString;
    FcorAccountDimension13 : WideString;
    FledgerTxt : WideString;
    Fvoucher : WideString;
  public
    destructor Destroy; override;
  published
    property  code : Integer read Fcode write Fcode;
    property transDate : TXSDate read FtransDate write FtransDate;
    property amountCur : TXSDecimal read FamountCur write FamountCur;
    property currency : WideString read Fcurrency write Fcurrency;
    property amountMST : TXSDecimal read FamountMST write FamountMST;
    property accountNum : WideString read FaccountNum write FaccountNum;
    property offsetAccountNum : WideString read FoffsetAccountNum write FoffsetAccountNum;
    property accountDimension1 : WideString read FaccountDimension1 write FaccountDimension1;
    property accountDimension2 : WideString read FaccountDimension2 write FaccountDimension2;
    property accountDimension3 : WideString read FaccountDimension3 write FaccountDimension3;
    property accountDimension4 : WideString read FaccountDimension4 write FaccountDimension4;
    property accountDimension5 : WideString read FaccountDimension5 write FaccountDimension5;
    property accountDimension6 : WideString read FaccountDimension6 write FaccountDimension6;
    property accountDimension7 : WideString read FaccountDimension7 write FaccountDimension7;
    property accountDimension8 : WideString read FaccountDimension8 write FaccountDimension8;
    property accountDimension9 : WideString read FaccountDimension9 write FaccountDimension9;
    property accountDimension10 : WideString read FaccountDimension10 write FaccountDimension10;
    property accountDimension11 : WideString read FaccountDimension11 write FaccountDimension11;
    property accountDimension12 : WideString read FaccountDimension12 write FaccountDimension12;
    property accountDimension13 : WideString read FaccountDimension13 write FaccountDimension13;
    property corAccountDimension1 : WideString read FcorAccountDimension1 write FcorAccountDimension1;
    property corAccountDimension2 : WideString read FcorAccountDimension2 write FcorAccountDimension2;
    property corAccountDimension3 : WideString read FcorAccountDimension3 write FcorAccountDimension3;
    property corAccountDimension4 : WideString read FcorAccountDimension4 write FcorAccountDimension4;
    property corAccountDimension5 : WideString read FcorAccountDimension5 write FcorAccountDimension5;
    property corAccountDimension6 : WideString read FcorAccountDimension6 write FcorAccountDimension6;
    property corAccountDimension7 : WideString read FcorAccountDimension7 write FcorAccountDimension7;
    property corAccountDimension8 : WideString read FcorAccountDimension8 write FcorAccountDimension8;
    property corAccountDimension9 : WideString read FcorAccountDimension9 write FcorAccountDimension9;
    property corAccountDimension10 : WideString read FcorAccountDimension10 write FcorAccountDimension10;
    property corAccountDimension11 : WideString read FcorAccountDimension11 write FcorAccountDimension11;
    property corAccountDimension12 : WideString read FcorAccountDimension12 write FcorAccountDimension12;
    property corAccountDimension13 : WideString read FcorAccountDimension13 write FcorAccountDimension13;
    property ledgerTxt : WideString read FledgerTxt write FledgerTxt;
    property voucher : WideString read Fvoucher write Fvoucher;
  end;

{
  AXLedgerTransFilter = class(TRemotable)
  private
    FconditionSQL: WideString;
    ForderBySQL: WideString;
    Fcode : Integer;
    FtransDate : TXSDate;
    FamountCur : TXSDecimal;
    Fcurrency : WideString;
    FamountMST : TXSDecimal;
    FaccountNum : WideString;
    FoffsetAccountNum : WideString;
    FaccountDimension1 : WideString;
    FaccountDimension2 : WideString;
    FaccountDimension3 : WideString;
    FaccountDimension4 : WideString;
    FaccountDimension5 : WideString;
    FaccountDimension6 : WideString;
    FaccountDimension7 : WideString;
    FaccountDimension8 : WideString;
    FaccountDimension9 : WideString;
    FaccountDimension10 : WideString;
    FaccountDimension11 : WideString;
    FaccountDimension12 : WideString;
    FaccountDimension13 : WideString;
    FcorAccountDimension1 : WideString;
    FcorAccountDimension2 : WideString;
    FcorAccountDimension3 : WideString;
    FcorAccountDimension4 : WideString;
    FcorAccountDimension5 : WideString;
    FcorAccountDimension6 : WideString;
    FcorAccountDimension7 : WideString;
    FcorAccountDimension8 : WideString;
    FcorAccountDimension9 : WideString;
    FcorAccountDimension10 : WideString;
    FcorAccountDimension11 : WideString;
    FcorAccountDimension12 : WideString;
    FcorAccountDimension13 : WideString;
    FledgerTxt : WideString;
    Fvoucher : WideString;
  public
    destructor Destroy; override;
  published
    property conditionSQL: WideString read FconditionSQL write FconditionSQL;
    property orderBySQL: WideString read ForderBySQL write ForderBySQL;
    property  code : Integer read Fcode write Fcode;
    property transDate : TXSDate read FtransDate write FtransDate;
    property amountCur : TXSDecimal read FamountCur write FamountCur;
    property currency : WideString read Fcurrency write Fcurrency;
    property amountMST : TXSDecimal read FamountMST write FamountMST;
    property accountNum : WideString read FaccountNum write FaccountNum;
    property offsetAccountNum : WideString read FoffsetAccountNum write FoffsetAccountNum;
    property accountDimension1 : WideString read FaccountDimension1 write FaccountDimension1;
    property accountDimension2 : WideString read FaccountDimension2 write FaccountDimension2;
    property accountDimension3 : WideString read FaccountDimension3 write FaccountDimension3;
    property accountDimension4 : WideString read FaccountDimension4 write FaccountDimension4;
    property accountDimension5 : WideString read FaccountDimension5 write FaccountDimension5;
    property accountDimension6 : WideString read FaccountDimension6 write FaccountDimension6;
    property accountDimension7 : WideString read FaccountDimension7 write FaccountDimension7;
    property accountDimension8 : WideString read FaccountDimension8 write FaccountDimension8;
    property accountDimension9 : WideString read FaccountDimension9 write FaccountDimension9;
    property accountDimension10 : WideString read FaccountDimension10 write FaccountDimension10;
    property accountDimension11 : WideString read FaccountDimension11 write FaccountDimension11;
    property accountDimension12 : WideString read FaccountDimension12 write FaccountDimension12;
    property accountDimension13 : WideString read FaccountDimension13 write FaccountDimension13;
    property corAccountDimension1 : WideString read FcorAccountDimension1 write FcorAccountDimension1;
    property corAccountDimension2 : WideString read FcorAccountDimension2 write FcorAccountDimension2;
    property corAccountDimension3 : WideString read FcorAccountDimension3 write FcorAccountDimension3;
    property corAccountDimension4 : WideString read FcorAccountDimension4 write FcorAccountDimension4;
    property corAccountDimension5 : WideString read FcorAccountDimension5 write FcorAccountDimension5;
    property corAccountDimension6 : WideString read FcorAccountDimension6 write FcorAccountDimension6;
    property corAccountDimension7 : WideString read FcorAccountDimension7 write FcorAccountDimension7;
    property corAccountDimension8 : WideString read FcorAccountDimension8 write FcorAccountDimension8;
    property corAccountDimension9 : WideString read FcorAccountDimension9 write FcorAccountDimension9;
    property corAccountDimension10 : WideString read FcorAccountDimension10 write FcorAccountDimension10;
    property corAccountDimension11 : WideString read FcorAccountDimension11 write FcorAccountDimension11;
    property corAccountDimension12 : WideString read FcorAccountDimension12 write FcorAccountDimension12;
    property corAccountDimension13 : WideString read FcorAccountDimension13 write FcorAccountDimension13;
    property ledgerTxt : WideString read FledgerTxt write FledgerTxt;
    property voucher : WideString read Fvoucher write Fvoucher;
  end;
}

  AXLedgerTransFilter = class(AXLedgerTrans)
  private
    FconditionSQL: WideString;
    ForderBySQL: WideString;
  public
    destructor Destroy; override;
  published
    property conditionSQL: WideString read FconditionSQL write FconditionSQL;
    property orderBySQL: WideString read ForderBySQL write ForderBySQL;
  end;

  AXLedgerTransShort = class(TRemotable)
  private
    Fcode : Integer;
    FtransDate : TXSDate;
    FamountCur : TXSDecimal;
    Fcurrency : WideString;
    FamountMST : TXSDecimal;
    FaccountNum : WideString;
    FoffsetAccountNum : WideString;
    FaccountDimension1 : WideString;
    FaccountDimension2 : WideString;
    FaccountDimension3 : WideString;
    FaccountDimension4 : WideString;
    FaccountDimension5 : WideString;
    FaccountDimension6 : WideString;
    FaccountDimension7 : WideString;
    FaccountDimension8 : WideString;
    FaccountDimension9 : WideString;
    FaccountDimension10 : WideString;
    FaccountDimension11 : WideString;
    FaccountDimension12 : WideString;
    FaccountDimension13 : WideString;
    FcorAccountDimension1 : WideString;
    FcorAccountDimension2 : WideString;
    FcorAccountDimension3 : WideString;
    FcorAccountDimension4 : WideString;
    FcorAccountDimension5 : WideString;
    FcorAccountDimension6 : WideString;
    FcorAccountDimension7 : WideString;
    FcorAccountDimension8 : WideString;
    FcorAccountDimension9 : WideString;
    FcorAccountDimension10 : WideString;
    FcorAccountDimension11 : WideString;
    FcorAccountDimension12 : WideString;
    FcorAccountDimension13 : WideString;
    FledgerTxt : WideString;
    Fvoucher : WideString;
  public
    destructor Destroy; override;
  published
    property  code : Integer read Fcode write Fcode;
    property transDate : TXSDate read FtransDate write FtransDate;
    property amountCur : TXSDecimal read FamountCur write FamountCur;
    property currency : WideString read Fcurrency write Fcurrency;
    property amountMST : TXSDecimal read FamountMST write FamountMST;
    property accountNum : WideString read FaccountNum write FaccountNum;
    property offsetAccountNum : WideString read FoffsetAccountNum write FoffsetAccountNum;
    property accountDimension1 : WideString read FaccountDimension1 write FaccountDimension1;
    property accountDimension2 : WideString read FaccountDimension2 write FaccountDimension2;
    property accountDimension3 : WideString read FaccountDimension3 write FaccountDimension3;
    property accountDimension4 : WideString read FaccountDimension4 write FaccountDimension4;
    property accountDimension5 : WideString read FaccountDimension5 write FaccountDimension5;
    property accountDimension6 : WideString read FaccountDimension6 write FaccountDimension6;
    property accountDimension7 : WideString read FaccountDimension7 write FaccountDimension7;
    property accountDimension8 : WideString read FaccountDimension8 write FaccountDimension8;
    property accountDimension9 : WideString read FaccountDimension9 write FaccountDimension9;
    property accountDimension10 : WideString read FaccountDimension10 write FaccountDimension10;
    property accountDimension11 : WideString read FaccountDimension11 write FaccountDimension11;
    property accountDimension12 : WideString read FaccountDimension12 write FaccountDimension12;
    property accountDimension13 : WideString read FaccountDimension13 write FaccountDimension13;
    property corAccountDimension1 : WideString read FcorAccountDimension1 write FcorAccountDimension1;
    property corAccountDimension2 : WideString read FcorAccountDimension2 write FcorAccountDimension2;
    property corAccountDimension3 : WideString read FcorAccountDimension3 write FcorAccountDimension3;
    property corAccountDimension4 : WideString read FcorAccountDimension4 write FcorAccountDimension4;
    property corAccountDimension5 : WideString read FcorAccountDimension5 write FcorAccountDimension5;
    property corAccountDimension6 : WideString read FcorAccountDimension6 write FcorAccountDimension6;
    property corAccountDimension7 : WideString read FcorAccountDimension7 write FcorAccountDimension7;
    property corAccountDimension8 : WideString read FcorAccountDimension8 write FcorAccountDimension8;
    property corAccountDimension9 : WideString read FcorAccountDimension9 write FcorAccountDimension9;
    property corAccountDimension10 : WideString read FcorAccountDimension10 write FcorAccountDimension10;
    property corAccountDimension11 : WideString read FcorAccountDimension11 write FcorAccountDimension11;
    property corAccountDimension12 : WideString read FcorAccountDimension12 write FcorAccountDimension12;
    property corAccountDimension13 : WideString read FcorAccountDimension13 write FcorAccountDimension13;
    property ledgerTxt : WideString read FledgerTxt write FledgerTxt;
    property voucher : WideString read Fvoucher write Fvoucher;

  end;

  ArrayOfAXLedgerTransShort = array of AXLedgerTransShort;  // { "http://ksoe.org/EnergyproControllerService/type/" }

  AXLedgerTransShortList = class(TRemotable)
  private
    FtotalCount: Integer;
    Flist: ArrayOfAXLedgerTransShort;
  public
  destructor Destroy; override;
  published
    property totalCount: Integer read FtotalCount write FtotalCount;
    property list: ArrayOfAXLedgerTransShort read Flist write Flist;
  end;

  ArrayOfInteger = array of Integer;

  // ************************************************************************ //
  // Namespace : http://ksoe.org/AXLedgerTransController/message/
  // soapAction: http://ksoe.org/AXLedgerTransController/action/AXLedgerTransController.%operationName%
  // transport : http://schemas.xmlsoap.org/soap/http
  // style     : rpc
  // binding   : AXLedgerTransControllerSoapBinding
  // service   : EnergyproControllerService
  // port      : AXLedgerTransController
  // URL       : http://soap.ksoe.com.ua/energypro
  // ************************************************************************ //


  AXLedgerTransControllerSoapPort = interface(IInvokable)
  ['{68A650CF-805F-45A7-94D8-6C8FFD40AB71}']
    function add(const aAXLedgerTrans: AXLedgerTrans): Integer; stdcall;
    procedure remove(const anObjectCode: Integer); stdcall;
    procedure save(const aAXLedgerTrans: AXLedgerTrans); stdcall;
    function getObject(const anObjectCode: Integer): AXLedgerTrans; stdcall;
    function getList: AXLedgerTransShortList; stdcall;
    function getFilteredList(const aAXLedgerTransFilter: AXLedgerTransFilter): AXLedgerTransShortList; stdcall;
    function getScrollableList(const aFromPosition: Integer; const aQuantity: Integer): AXLedgerTransShortList; stdcall;
    function getScrollableFilteredList(const aAXLedgerTransFilter: AXLedgerTransFilter; const aFromPosition: Integer; const aQuantity: Integer): AXLedgerTransShortList; stdcall;
    function getScrollableListByCondition(const aCondition: WideString; const aFromPosition: Integer; const aQuantity: Integer): AXLedgerTransShortList; stdcall;
    function getScrollableFilteredCodeArray(const aAXLedgerTransFilter: AXLedgerTransFilter; const aFromPosition: Integer; const aQuantity: Integer): ArrayOfInteger; stdcall;
    function getShortObject(const anObjectCode: Integer): AXLedgerTransShort; stdcall;

    function getPostingByVoucher(const aVoucher: String): AXLedgerTransShortList; stdcall;


  end;


implementation

  destructor AXLedgerTrans.Destroy;
  begin
    if Assigned(FtransDate) then
      transDate.Free;
    if Assigned(FamountCur) then
      amountCur.Free;
    if Assigned(FamountMST) then
      amountMST.Free;
    inherited Destroy;
  end;

{
  destructor AXLedgerTransFilter.Destroy;
  begin
    if Assigned(FtransDate) then
      transDate.Free;
    if Assigned(FamountCur) then
      amountCur.Free;
    if Assigned(FamountMST) then
      amountMST.Free;
    inherited Destroy;
  end;
}

  destructor AXLedgerTransFilter.Destroy;
  begin
    inherited Destroy;
  end;

  destructor AXLedgerTransShort.Destroy;
  begin
    if Assigned(FtransDate) then
      transDate.Free;
    if Assigned(FamountCur) then
      amountCur.Free;
    if Assigned(FamountMST) then
      amountMST.Free;
    inherited Destroy;
  end;

  destructor AXLedgerTransShortList.Destroy;
  var
    I: Integer;
  begin
    for I := 0 to Length(Flist)-1 do
     if Assigned(Flist[I]) then
       Flist[I].Free;
     SetLength(Flist, 0);
     inherited Destroy;
  end;



initialization

  RemClassRegistry.RegisterXSClass(AXLedgerTrans, 'http://ksoe.org/EnergyproControllerService/type/', 'AXLedgerTrans');
  RemClassRegistry.RegisterXSClass(AXLedgerTransRef, 'http://ksoe.org/EnergyproControllerService/type/', 'AXLedgerTransRef');
  RemClassRegistry.RegisterXSClass(AXLedgerTransFilter, 'http://ksoe.org/EnergyproControllerService/type/', 'AXLedgerTransFilter');
  RemClassRegistry.RegisterXSClass(AXLedgerTransShort, 'http://ksoe.org/EnergyproControllerService/type/', 'AXLedgerTransShort');
  RemClassRegistry.RegisterXSClass(AXLedgerTransShortList, 'http://ksoe.org/EnergyproControllerService/type/', 'AXLedgerTransShortList');
  RemClassRegistry.RegisterXSInfo(TypeInfo(ArrayOfAXLedgerTransShort), 'http://ksoe.org/EnergyproControllerService/type/', 'ArrayOfAXLedgerTransShort');

  InvRegistry.RegisterInterface(TypeInfo(AXLedgerTransControllerSoapPort), 'http://ksoe.org/AXLedgerTransController/message/', 'UTF-8');
  InvRegistry.RegisterDefaultSOAPAction(TypeInfo(AXLedgerTransControllerSoapPort), 'http://ksoe.org/AXLedgerTransController/action/AXLedgerTransController.%operationName%');


end.

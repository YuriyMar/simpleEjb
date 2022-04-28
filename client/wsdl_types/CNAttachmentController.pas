unit CNAttachmentController;

interface

uses InvokeRegistry, SOAPHTTPClient, Types, XSBuiltIns, EnergyProController, EnergyProController2,
      ENDocAttachmentController;

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

  CNAttachment            = class;                 { "http://ksoe.org/EnergyproControllerService/type/" }

  ArrayOfInteger = array of Integer;


  // ************************************************************************ //
  // Namespace : http://ksoe.org/EnergyproControllerService/type/
  // ************************************************************************ //
  CNAttachmentRef = class(TRemotable)
  private
    Fcode: Integer;
  published
    property code: Integer read Fcode write Fcode;
  end;


  // ************************************************************************ //
  // Namespace : http://ksoe.org/EnergyproControllerService/type/
  // ************************************************************************ //
  CNAttachment = class(TRemotable)
  private
    Fcode : Integer;
    FsoCode : Integer;
    Fname : WideString;
    Fdate_doc : TXSDate;
    Ffilename : WideString;
    Ffilelink : WideString;
  public
    destructor Destroy; override;
  published
    property code : Integer read Fcode write Fcode;
    property soCode : Integer read FsoCode write FsoCode;
    property name : WideString read Fname write Fname;
    property date_doc : TXSDate read Fdate_doc write Fdate_doc;
    property filename : WideString read Ffilename write Ffilename;
    property filelink : WideString read Ffilelink write Ffilelink;
  end;

{
  CNAttachmentFilter = class(TRemotable)
  private
    FconditionSQL: WideString;
    ForderBySQL: WideString;
    Fcode : Integer;
    FsoCode : Integer;
    Fname : WideString;
    Fdate_doc : TXSDate;
    Ffilename : WideString;
    Ffilelink : WideString;
  public
    destructor Destroy; override;
  published
    property conditionSQL: WideString read FconditionSQL write FconditionSQL;
    property orderBySQL: WideString read ForderBySQL write ForderBySQL;
    property code : Integer read Fcode write Fcode;
    property soCode : Integer read FsoCode write FsoCode;
    property name : WideString read Fname write Fname;
    property date_doc : TXSDate read Fdate_doc write Fdate_doc;
    property filename : WideString read Ffilename write Ffilename;
    property filelink : WideString read Ffilelink write Ffilelink;
  end;
}

  CNAttachmentFilter = class(CNAttachment)
  private
    FconditionSQL: WideString;
    ForderBySQL: WideString;
  public
    destructor Destroy; override;
  published
    property conditionSQL: WideString read FconditionSQL write FconditionSQL;
    property orderBySQL: WideString read ForderBySQL write ForderBySQL;
  end;

  CNAttachmentShort = class(TRemotable)
  private
    Fcode : Integer;
    FsoCode : Integer;
    Fname : WideString;
    Fdate_doc : TXSDate;
    Ffilename : WideString;
    Ffilelink : WideString;
  public
    destructor Destroy; override;
  published
    property  code : Integer read Fcode write Fcode;
    property  soCode : Integer read FsoCode write FsoCode;
    property name : WideString read Fname write Fname;
    property date_doc : TXSDate read Fdate_doc write Fdate_doc;
    property filename : WideString read Ffilename write Ffilename;
    property filelink : WideString read Ffilelink write Ffilelink;

  end;

  ArrayOfCNAttachmentShort = array of CNAttachmentShort;  // { "http://ksoe.org/EnergyproControllerService/type/" }

  CNAttachmentShortList = class(TRemotable)
  private
    FtotalCount: Integer;
    Flist: ArrayOfCNAttachmentShort;
  public
  destructor Destroy; override;
  published
    property totalCount: Integer read FtotalCount write FtotalCount;
    property list: ArrayOfCNAttachmentShort read Flist write Flist;
  end;


  // ************************************************************************ //
  // Namespace : http://ksoe.org/CNAttachmentController/message/
  // soapAction: http://ksoe.org/CNAttachmentController/action/CNAttachmentController.%operationName%
  // transport : http://schemas.xmlsoap.org/soap/http
  // style     : rpc
  // binding   : CNAttachmentControllerSoapBinding
  // service   : EnergyproControllerService
  // port      : CNAttachmentController
  // URL       : http://soap.ksoe.com.ua/energypro
  // ************************************************************************ //


  CNAttachmentControllerSoapPort = interface(IInvokable)
  ['{FA5C25F0-CF1F-45FE-8DBE-AA634A9569AE}']
    function add(const aCNAttachment: CNAttachment): Integer; stdcall ;
    procedure remove(const anObjectCode: Integer); stdcall;
    procedure save(const aCNAttachment: CNAttachment); stdcall;
    function getObject(const anObjectCode: Integer): CNAttachment; stdcall;
    function getList: CNAttachmentShortList; stdcall;
    function getFilteredList(const aCNAttachmentFilter: CNAttachmentFilter): CNAttachmentShortList; stdcall;
    function getScrollableList(const aFromPosition: Integer; const aQuantity: Integer): CNAttachmentShortList; stdcall;
    function getScrollableFilteredList(const aCNAttachmentFilter: CNAttachmentFilter; const aFromPosition: Integer; const aQuantity: Integer): CNAttachmentShortList; stdcall;
    function getScrollableListByCondition(const aCondition: WideString; const aFromPosition: Integer; const aQuantity: Integer): CNAttachmentShortList; stdcall;
    function getScrollableFilteredCodeArray(const aCNAttachmentFilter: CNAttachmentFilter; const aFromPosition: Integer; const aQuantity: Integer): ArrayOfInteger; stdcall;
    function getShortObject(const anObjectCode: Integer): CNAttachmentShort; stdcall;
    procedure convertAtt(const code: Integer); stdcall;
    procedure transliterateAtt(const code: Integer); stdcall;
    procedure removeCN(const cnSubSystem: WideString; const id: Integer; const codeENDoccAttachment: Integer); stdcall;
    procedure addDocAttachments(const cnSubSystem: WideString; const attachDocName: WideString; const idPack: Integer;
    const idMovement: Integer; const idUser: Integer; const aENDocAttachment: ENDocAttachment; const aFile: ArrayOfByte;  const fileName: WideString;  const dirToCreate: WideString); stdcall;
  end;


implementation

  destructor CNAttachment.Destroy;
  begin
    if Assigned(Fdate_doc) then
      date_doc.Free;
    inherited Destroy;
  end;

{
  destructor CNAttachmentFilter.Destroy;
  begin
    if Assigned(Fdate_doc) then
      date_doc.Free;
    inherited Destroy;
  end;
}

  destructor CNAttachmentFilter.Destroy;
  begin
    inherited Destroy;
  end;

  destructor CNAttachmentShort.Destroy;
  begin
    if Assigned(Fdate_doc) then
      date_doc.Free;
    inherited Destroy;
  end;

  destructor CNAttachmentShortList.Destroy;
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

  RemClassRegistry.RegisterXSClass(CNAttachment, 'http://ksoe.org/EnergyproControllerService/type/', 'CNAttachment');
  RemClassRegistry.RegisterXSClass(CNAttachmentRef, 'http://ksoe.org/EnergyproControllerService/type/', 'CNAttachmentRef');
  RemClassRegistry.RegisterXSClass(CNAttachmentFilter, 'http://ksoe.org/EnergyproControllerService/type/', 'CNAttachmentFilter');
  RemClassRegistry.RegisterXSClass(CNAttachmentShort, 'http://ksoe.org/EnergyproControllerService/type/', 'CNAttachmentShort');
  RemClassRegistry.RegisterXSClass(CNAttachmentShortList, 'http://ksoe.org/EnergyproControllerService/type/', 'CNAttachmentShortList');
  RemClassRegistry.RegisterXSInfo(TypeInfo(ArrayOfCNAttachmentShort), 'http://ksoe.org/EnergyproControllerService/type/', 'ArrayOfCNAttachmentShort');

  InvRegistry.RegisterInterface(TypeInfo(CNAttachmentControllerSoapPort), 'http://ksoe.org/CNAttachmentController/message/', 'UTF-8');
  InvRegistry.RegisterDefaultSOAPAction(TypeInfo(CNAttachmentControllerSoapPort), 'http://ksoe.org/CNAttachmentController/action/CNAttachmentController.%operationName%');


end.
